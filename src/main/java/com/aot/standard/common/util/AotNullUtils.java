package com.aot.standard.common.util;

import com.aot.standard.common.exception.CommonException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AotNullUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AotNullUtils.class);

    protected AotNullUtils() {
        throw new UnsupportedOperationException();
    }

    // int 도 함께 커버됨
    public static final boolean equals(final Integer n1, final Integer n2) throws CommonException {
        try {
            return n1 != null && n2 != null && n1.intValue() == n2.intValue();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static final boolean equals(final Long n1, final Long n2) throws CommonException {
        try {
            return n1 != null && n2 != null && n1.longValue() == n2.longValue();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static final boolean exists(final File file) {
        try {
            return file != null && file.exists();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static final boolean hasNext(final Iterator<?> iterator) {
        try {
            return iterator != null && iterator.hasNext();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public static final boolean isEmpty(final JsonElement json) {
        try {
            if (json == null) {
                return true;
            }
            if (json.isJsonObject()) {
                return json.isJsonNull() || json.getAsJsonObject().entrySet().isEmpty();
            } else if (json.isJsonArray()) {
                return json.isJsonNull() || json.getAsJsonArray().size() == 0;
            } else {
                return json.isJsonNull();
            }
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return true;
        }
    }

    public static boolean isEmpty(final MultipartFile multipartFile) {
        try {
            return multipartFile == null || multipartFile.isEmpty();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return true;
        }
    }

    public static boolean isEmpty(final List<?> list) {
        try {
            return size(list) == 0;
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return true;
        }
    }

    public static final boolean isNotEmpty(final JsonElement json) {
        return !isEmpty(json);
    }

    public static boolean isNotEmpty(final MultipartFile multipartFile) {
        return !isEmpty(multipartFile);
    }

    public static boolean isNotEmpty(final List<?> list) {
        return !isEmpty(list);
    }

    public static final int size(final List<?> list) throws CommonException {
        try {
            return list == null ? 0 : list.size();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return 0;
        }
    }

    public static final int size(final JsonArray list) throws CommonException {
        try {
            return list == null ? 0 : list.size();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return 0;
        }
    }

    public static final int size(final Map<String, ?> map) throws CommonException {
        try {
            return map == null ? 0 : map.size();
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return 0;
        }
    }

}
