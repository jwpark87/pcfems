package com.aot.standard.common.tablevo;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotNullUtils;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SqlForTableVO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String COUNT = "countTableVO";
    public static final String SELECT = "selectTableVO";
    public static final String SELECT_ONE = "selectOneTableVO";
    public static final String INSERT = "insertTableVO";
    public static final String UPDATE = "updateTableVO";
    public static final String DELETE = "deleteTableVO";
    private static final String TABLE_COLUMN_NAME_REG_ID = "CRT_ID";
    private static final String TABLE_COLUMN_NAME_REG_DT = "CRT_DT";
    private static final String TABLE_COLUMN_NAME_UPD_DT = "UPD_DT";
    private static final String VARIABLE_NAME_REG_ID = "crt_id";
    private static final String VARIABLE_NAME_REG_DT = "crt_dt";
    private static final String VARIABLE_NAME_UPD_DT = "upd_dt";
    private static final String SYSDATE = "now()";

    private static final List<String> EXCLUDE_FIELD_LIST = Arrays.asList("serialVersionUID");
    // 참고용: 각VO에 암호화 컬럼 정의 방법
    // public static transient final Set<String> ENCRYPTED_COLUMN_LIST = Arrays.asList("mbrMobl", "emailId").stream().collect(Collectors.toSet());
    private static final String INSERT_BIND_STRING = "#'{'{0}{1}'}'";
    private static final String SET_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String WHERE_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";

    public <T extends Object> String countTableVO(final T vo, final List<String> whereKey) {
        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        sql.SELECT("COUNT(1) AS CNT").FROM(tableName);
        if (AotNullUtils.isNotEmpty(whereKey)) {
            final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
            for (final String key : whereKey) {
                if (!param.containsKey(key) || param.get(key) == null) {
                    this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                    throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
                }
            }
            for (final Entry<String, Object> entry : param.entrySet()) {
                final String fieldName = entry.getKey();
                if (whereKey.contains(fieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, fieldName, this.getJdbcType(entry.getValue().getClass().getSimpleName())));
                }
            }
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String selectTableVO(final T vo, final List<String> whereKey, final String orderByColumns) {
        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        final Field[] fields = vo.getClass().getDeclaredFields();
        for (final Field field : fields) {
            final String fieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else {
                sql.SELECT(fieldName);
            }
        }
        sql.FROM(tableName);

        if (AotNullUtils.isNotEmpty(whereKey)) {
            final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
            for (final String key : whereKey) {
                if (!param.containsKey(key) || param.get(key) == null) {
                    this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                    throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
                }
            }
            for (final Entry<String, Object> entry : param.entrySet()) {
                final String fieldName = entry.getKey();
                if (whereKey.contains(fieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, fieldName, this.getJdbcType(entry.getValue().getClass().getSimpleName())));
                }
            }
        }
        if (StringUtils.isNotEmpty(orderByColumns)) {
            sql.ORDER_BY(orderByColumns);
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String selectOneTableVO(final T vo, final List<String> whereKey) {
        if (AotNullUtils.size(whereKey) < 1) {
            this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
            throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
        }
        final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
        for (final String key : whereKey) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
            }
        }

        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        final Field[] fields = vo.getClass().getDeclaredFields();
        for (final Field field : fields) {
            final String fieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else {
                sql.SELECT(fieldName);
            }
        }
        sql.FROM(tableName);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String fieldName = entry.getKey();
            if (whereKey.contains(fieldName)) {
                sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, fieldName, this.getJdbcType(entry.getValue().getClass().getSimpleName())));
            }
        }
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String insertTableVO(final T vo) {
        final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        sql.INSERT_INTO(tableName);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String fieldName = entry.getKey();
            if (StringUtils.equalsAny(fieldName, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPD_DT)) {
                sql.VALUES(fieldName, SYSDATE);
            } else {
                sql.VALUES(fieldName, MessageFormat.format(INSERT_BIND_STRING, fieldName, this.getJdbcType(entry.getValue().getClass().getSimpleName())));
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String fieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, fieldName))) {
                continue;
            } else if (StringUtils.equals(fieldName, VARIABLE_NAME_REG_DT)) {
                sql.VALUES(TABLE_COLUMN_NAME_REG_DT, SYSDATE);
            } else if (StringUtils.equals(fieldName, VARIABLE_NAME_UPD_DT)) {
                sql.VALUES(TABLE_COLUMN_NAME_UPD_DT, SYSDATE);
            }
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String updateTableVO(final T vo, final List<String> whereKey, final List<String> forcedUpdateKey) {
        if (AotNullUtils.size(whereKey) < 1) {
            this.logger.warn(CommonExceptionCode.ERROR_NO_DATA_SUCCESS.toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }
        final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
        for (final String key : whereKey) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
            }
        }

        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        sql.UPDATE(tableName);
        final Field[] fields = vo.getClass().getDeclaredFields();
        for (final Field field : fields) {
            final String fieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else if (StringUtils.equalsAny(fieldName, VARIABLE_NAME_REG_ID, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPD_DT)) {
                continue;
            }
            final String columnTypeName = field.getType().getSimpleName();
            if (forcedUpdateKey != null && !whereKey.contains(fieldName) && (forcedUpdateKey.contains("**") || forcedUpdateKey.contains(fieldName))
                    && !StringUtils.equalsAny(fieldName, TABLE_COLUMN_NAME_REG_ID, TABLE_COLUMN_NAME_REG_DT)) {
                sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, fieldName, this.getJdbcType(columnTypeName)));
            } else if (param.get(fieldName) != null && StringUtils.isNotEmpty(param.get(fieldName).toString())) {
                if (whereKey.contains(fieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, fieldName, this.getJdbcType(columnTypeName)));
                } else if (StringUtils.equalsAny(fieldName, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPD_DT)) {
                    sql.SET(fieldName + " = " + SYSDATE);
                } else {
                    sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, fieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String fieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else if (StringUtils.equals(fieldName, VARIABLE_NAME_UPD_DT)) {
                sql.SET(TABLE_COLUMN_NAME_UPD_DT + " = " + SYSDATE);
            }
        }
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String deleteTableVO(final T vo, final List<String> whereKey) {
        if (AotNullUtils.size(whereKey) < 1) {
            this.logger.warn(CommonExceptionCode.ERROR_NO_DATA_SUCCESS.toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }
        final Map<String, Object> param = AotMapperUtils.writeObjectAsHashMap(vo);
        for (final String key : whereKey) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn("{} not in {}\n{}", key, AotMapperUtils.writeObjectAsString(param), CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
            }
        }
        final SQL sql = new SQL();
        final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
        sql.DELETE_FROM(tableName);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String fieldName = entry.getKey();
            if (whereKey.contains(fieldName)) {
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, fieldName, this.getJdbcType(columnTypeName)));
            }
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private String getJdbcType(final String columnTypeName) {
        String jdbcType;
        if (StringUtils.equalsAny(columnTypeName, "String")) {
            jdbcType = ", jdbcType=VARCHAR";
        } else if (StringUtils.equalsAny(columnTypeName, "Short")) {
            jdbcType = ", jdbcType=SMALLINT";
        } else if (StringUtils.equalsAny(columnTypeName, "Integer")) {
            jdbcType = ", jdbcType=INTEGER";
        } else if (StringUtils.equalsAny(columnTypeName, "Long")) {
            jdbcType = ", jdbcType=BIGINT";
        } else if (StringUtils.equalsAny(columnTypeName, "DateTime", "LocalDateTime")) {
            jdbcType = ", jdbcType=TIMESTAMP";
        } else if (StringUtils.equalsAny(columnTypeName, "BLOB")) {
            jdbcType = ", jdbcType=BLOB";
        } else {
            jdbcType = "";
            this.logger.warn("케이스 빠짐 {}", columnTypeName);
        }
        return jdbcType;
    }
}
