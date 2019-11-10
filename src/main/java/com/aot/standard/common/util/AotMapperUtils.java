package com.aot.standard.common.util;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import com.aot.standard.common.util.mapper.*;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.*;

public class AotMapperUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AotMapperUtils.class);

    private AotMapperUtils() {
        throw new UnsupportedOperationException();
    }

    private static final Gson GSON_INSTANCE = new GsonBuilder().registerTypeAdapter(Date.class, new MapperDateDeserializer()).registerTypeAdapter(Date.class, new MapperDateSerializer())
            .registerTypeAdapter(DateTime.class, new MapperDateTimeDeserializer()).registerTypeAdapter(DateTime.class, new MapperDateTimeSerializer())
            .registerTypeAdapter(Map.class, new MapperMapDeserializer()).registerTypeAdapter(HashMap.class, new MapperMapDeserializer())
            .registerTypeAdapter(LinkedTreeMap.class, new MapperMapDeserializer()).disableHtmlEscaping().create();

    public static final <T> T writeObjectAsObject(final Object content, final Class<T> returnType) throws CommonException {
        return GSON_INSTANCE.fromJson(getJsonElementWithExceptionString(content), returnType);
    }

    public static final <T> T writeObjectAsObject(final Object content, final Type typeOfT) throws CommonException {
        return GSON_INSTANCE.fromJson(getJsonElementWithExceptionString(content), typeOfT);
    }

    public static final JsonArray writeObjectAsJsonArray(final Object content) throws CommonException {
        return getCollectionTypeExceptionWithJsonPrimitive(content, JsonArray.class);
    }

    public static final JsonObject writeObjectAsJsonObject(final Object content) throws CommonException {
        return getCollectionTypeExceptionWithJsonPrimitive(content, JsonObject.class);
    }

    public static final Map<String, Object> writeObjectAsHashMap(final Object content) throws CommonException {
        return writeObjectAsObject(getCollectionTypeExceptionWithJsonPrimitive(content, JsonObject.class), new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    public static final JsonPrimitive writeObjectAsJsonPrimitive(final Object content) throws CommonException {
        return getJsonElementWithExceptionString(content).getAsJsonPrimitive();
    }

    public static final JsonElement writeObjectAsJsonElement(final Object content) throws CommonException {
        return GSON_INSTANCE.toJsonTree(content);
    }

    public static final String writeObjectAsString(final Object content) throws CommonException {
        return GSON_INSTANCE.toJson(content);
    }

    public static final <T> List<T> writeObjectAsArrayList(final Object content, final Class<T> returnType) throws CommonException {
        final JsonArray array = writeObjectAsObject(content, JsonArray.class);
        final List<T> lst = new ArrayList<>();
        for (final JsonElement json : array) {
            lst.add(writeObjectAsObject(json, returnType));
        }
        return lst;
        // return GSON_INSTANCE.fromJson(getJsonElementWithExceptionString(content), new TypeToken<ArrayList<T>>() {
        // }.getType());
    }

    public static final Gson getGsonObject() {
        return GSON_INSTANCE;
    }

    private static JsonElement getJsonElementWithExceptionString(final Object object) {
        JsonElement jsonElement;
        if (object != null && object instanceof String) {
            jsonElement = new JsonParser().parse((String) object);
        } else {
            jsonElement = writeObjectAsJsonElement(object);
        }
        return jsonElement;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getCollectionTypeExceptionWithJsonPrimitive(final Object content, final Class<T> returnType) {
        final JsonElement jsonElement = getJsonElementWithExceptionString(content);
        if (jsonElement.isJsonPrimitive()) {
            LOGGER.warn(CommonExceptionCode.ERROR_FAIL_TRANSFORM_DATA.toString());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_TRANSFORM_DATA, jsonElement);
        } else if (jsonElement.isJsonNull()) {
            if (returnType == JsonObject.class) {
                return (T) new JsonObject();
            } else if (returnType == JsonArray.class) {
                return (T) new JsonArray();
            }
        }

        return writeObjectAsObject(jsonElement, returnType);
    }
}
