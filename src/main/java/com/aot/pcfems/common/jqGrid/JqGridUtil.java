/*****************************************************************************
 * �꾨줈洹몃옩紐� : JqGridUtil.java
 * ��    紐� : JqGrid Utility
 * 李멸퀬  �ы빆  : �놁쓬
 *****************************************************************************
 * Date       Author      Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2013.06.04  bestheroz    1.0
 *****************************************************************************/

package com.aot.pcfems.common.jqGrid;

import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class JqGridUtil {
    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final PagingUtil listPage) {
        final JsonObject result = new JsonObject();
        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", listPage.getTotalSize());
        if (listPage.getTotalSize() % jqGridInfo.getJqRows() == 0) {
            result.addProperty("total", listPage.getTotalSize() / jqGridInfo.getJqRows());
        } else {
            result.addProperty("total", listPage.getTotalSize() / jqGridInfo.getJqRows() + 1);
        }
        result.add("root", AotMapperUtils.writeObjectAsJsonArray(listPage.getCurrList()));

        return result;
    }

    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final PagingUtil listPage, final JsonArray jArray) {
        final JsonObject result = new JsonObject();

        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", listPage.getTotalSize());
        if (listPage.getTotalSize() % jqGridInfo.getJqRows() == 0) {
            result.addProperty("total", listPage.getTotalSize() / jqGridInfo.getJqRows());
        } else {
            result.addProperty("total", listPage.getTotalSize() / jqGridInfo.getJqRows() + 1);
        }
        result.add("root", jArray);
        return result;
    }

    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final List<?> listData, final long total) {
        final JsonObject result = new JsonObject();
        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", total);
        if (total % jqGridInfo.getJqRows() == 0) {
            result.addProperty("total", total / jqGridInfo.getJqRows());
        } else {
            result.addProperty("total", total / jqGridInfo.getJqRows() + 1);
        }
        result.add("root", AotMapperUtils.writeObjectAsJsonArray(listData));
        return result;
    }

    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final JsonArray jsonData, final long total) {
        final JsonObject result = new JsonObject();
        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", total);
        if (total % jqGridInfo.getJqRows() == 0) {
            result.addProperty("total", total / jqGridInfo.getJqRows());
        } else {
            result.addProperty("total", total / jqGridInfo.getJqRows() + 1);
        }
        result.add("root", jsonData);
        return result;
    }

    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final List<?> listData) {
        final JsonObject result = new JsonObject();
        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", listData.size());
        result.add("root", AotMapperUtils.writeObjectAsJsonArray(listData));
        return result;
    }

    public static JsonObject getJsonData(final JqGridVO jqGridInfo, final JsonArray jsonData) {
        final JsonObject result = new JsonObject();
        result.addProperty("page", jqGridInfo.getJqPage());
        result.addProperty("records", jsonData.size());
        result.add("root", jsonData);
        return result;
    }

    public static JsonObject getJsonDataView(final List<?> listData) {
        final JsonObject result = new JsonObject();
        result.add("root", AotMapperUtils.writeObjectAsJsonArray(listData));
        return result;
    }

    public static JsonArray getJsonData(final PagingUtil listPage) {
        return AotMapperUtils.writeObjectAsJsonArray(listPage.getCurrList());
    }

    public static JsonArray getJsonData(final List<?> listData) {
        return AotMapperUtils.writeObjectAsJsonArray(listData);
    }
}