package com.aot.standard.common.tablevo;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Transactional
public class TestCreateTableVO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String SYSDATE = "now()";
    private static final String UPD_DT = "upd_dt";
    private static final String CRT_DT = "crt_dt";
    @Autowired(required = false)
    private SqlSession sqlSession;

    @Test
    public void test11() throws SQLException {
        try (Statement stmt = this.sqlSession.getConnection().createStatement()) {

            final String tableName = "emsm_vm_status";

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1")) {
                final ResultSetMetaData metaInfo = rs.getMetaData();

                // 1. VO만들기
                final StringBuilder voSb = new StringBuilder();
                // 2. Mapper - insert 만들기
                final StringBuilder insertSb = new StringBuilder();
                // 2. Mapper - update 만들기
                final StringBuilder updateSb = new StringBuilder();
                // 3. Mapper - delete 만들기
                final StringBuilder deleteSb = new StringBuilder();
                // 4. Mapper - where key 만들기
                final StringBuilder whereSb = new StringBuilder();
                // 5. jqGrid용 - Mapper where key 만들기
                final StringBuilder jqGridWhereSb = new StringBuilder();
                // 6. jqGrid용 - Service Search key 만들기
                final StringBuilder jqGridSearchKeySb = new StringBuilder();

                final String camelTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);

                this.appendNewline(insertSb, "<insert id=\"insert" + camelTableName + "\" parameterType=\"Table" + camelTableName + "VO\">");
                this.appendNewline(insertSb, "INSERT INTO " + tableName + " (", 1);

                this.appendNewline(updateSb, "<update id=\"update" + camelTableName + "\" parameterType=\"Table" + camelTableName + "VO\">");
                this.appendNewline(updateSb, "UPDATE " + tableName, 1);
                this.appendNewline(updateSb, "<set>", 1);

                for (int i = 0; i < metaInfo.getColumnCount(); i++) {
                    if (i < metaInfo.getColumnCount() - 1) {
                        this.appendNewline(insertSb, metaInfo.getColumnName(i + 1) + ",", 2);
                    } else {
                        this.appendNewline(insertSb, metaInfo.getColumnName(i + 1), 2);
                    }
                }

                this.appendNewline(insertSb, ") VALUES (", 1);

                this.appendNewline(jqGridSearchKeySb, "if (alarmInquiry.getJqFilters() != null && !alarmInquiry.getJqFilters().isJsonNull() && alarmInquiry.getJqFilters().get(\"rules\") != null) {",
                        0);
                this.appendNewline(jqGridSearchKeySb, "JsonArray arr = stcsFormatVO.getJqFilters().get(\"rules\").getAsJsonArray();", 1);
                this.appendNewline(jqGridSearchKeySb, "if (arr != null) {", 1);
                this.appendNewline(jqGridSearchKeySb, "for (JsonElement jsonElement : arr) {", 2);
                this.appendNewline(jqGridSearchKeySb, "JsonObject jo = jsonElement.getAsJsonObject();", 3);
                this.appendNewline(jqGridSearchKeySb, "if (jo != null && !jo.isJsonNull()) {", 3);

                for (int i = 0; i < metaInfo.getColumnCount(); i++) {
                    final String columnName = metaInfo.getColumnName(i + 1);
                    final String columnTypeName = metaInfo.getColumnTypeName(i + 1);
                    // String camelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                    final String camelColumnName = StringUtils.lowerCase(columnName);
                    String fieldType;
                    String jdbcType;
                    if (StringUtils.equalsAny(columnTypeName, "VARCHAR", "VARCHAR2", "CHAR", "CLOB")) {
                        fieldType = "String";
                        jdbcType = ", jdbcType=VARCHAR";
                    } else if (StringUtils.equalsAny(columnTypeName, "BIGINT UNSIGNED")) {
                        fieldType = "Long";
                        jdbcType = ", jdbcType=BIGINT";
                    } else if (StringUtils.equalsAny(columnTypeName, "SMALLINT", "SMALLINT UNSIGNED")) {
                        fieldType = "Short";
                        jdbcType = ", jdbcType=SMALLINT";
                    } else if (StringUtils.equalsAny(columnTypeName, "INTEGER", "NUMBER", "INT")) {
                        fieldType = "Integer";
                        jdbcType = ", jdbcType=INTEGER";
                    } else if (StringUtils.equalsAny(columnTypeName, "TIMESTAMP", "DATE", "DATETIME")) {
                        fieldType = "DateTime";
                        jdbcType = ", jdbcType=TIMESTAMP";
                    } else if (StringUtils.equalsAny(columnTypeName, "BLOB")) {
                        fieldType = "Byte[]";
                        jdbcType = ", jdbcType=BLOB";
                        this.logger.debug("private Byte[] " + camelColumnName + "; // XXX: spotbugs 피하기 : Arrays.copyOf(value, value.length)");
                    } else {
                        fieldType = "Unknown";
                        jdbcType = "";
                        this.logger.warn("케이스 빠짐 {} : {}", columnName, columnTypeName);
                    }
                    this.appendNewline(voSb, "private " + fieldType + " " + camelColumnName + ";");

                    if (i < metaInfo.getColumnCount() - 1) {
                        if (StringUtils.equalsAny(camelColumnName, CRT_DT, UPD_DT)) {
                            this.appendNewline(insertSb, SYSDATE + ",", 2);
                        } else {
                            this.appendNewline(insertSb, "#{" + camelColumnName + jdbcType + "},", 2);
                        }
                    } else {
                        if (StringUtils.equalsAny(camelColumnName, CRT_DT, UPD_DT)) {
                            this.appendNewline(insertSb, SYSDATE, 2);
                        } else {
                            this.appendNewline(insertSb, "#{" + camelColumnName + jdbcType + "}", 2);
                        }
                    }

                    if (i == 0) {
                        // 보통 첫번째 컬럼은 key값이므로 where 조건에 쓰도록 하자
                        whereSb.append("AND " + columnName + " = #{" + camelColumnName + jdbcType + "}");
                    } else {

                        if (StringUtils.equalsAny(camelColumnName, UPD_DT)) {
                            this.appendNewline(updateSb, columnName + " = " + SYSDATE + ",", 2);
                        } else if (StringUtils.equalsAny(camelColumnName, CRT_DT)) {
                            // skip
                        } else {
                            this.appendNewline(updateSb, "<if test=\"" + camelColumnName + " != null\">", 2);
                            this.appendNewline(updateSb, columnName + " = #{" + camelColumnName + jdbcType + "},", 2);
                            this.appendNewline(updateSb, "</if>", 2);
                        }
                    }

                    if (StringUtils.equals(fieldType, "String")) {
                        this.appendNewline(jqGridWhereSb, "<if test=\"@org.apache.commons.lang3.StringUtils@isNotEmpty( " + camelColumnName + " )\">", 0);
                        this.appendNewline(jqGridWhereSb, "AND " + columnName + " LIKE CONCAT('%', #{" + camelColumnName + jdbcType + "}, '%')", 1);
                        this.appendNewline(jqGridWhereSb, "</if>", 0);

                        this.appendNewline(jqGridSearchKeySb, "if (jo.get(\"field\").getAsString().equals(\"" + camelColumnName + "\")) {", 4);
                        this.appendNewline(jqGridSearchKeySb, "stcsFormatVO.set" + StringUtils.capitalize(camelColumnName) + "(jo.get(\"data\").getAsString());", 5);
                        this.appendNewline(jqGridSearchKeySb, "}", 4);
                    }
                }
                this.appendNewline(insertSb, ")", 1);
                this.appendNewline(insertSb, "</insert>");

                this.appendNewline(updateSb, "</set>", 1);
                this.appendNewline(updateSb, "<where>", 1);
                this.appendNewline(updateSb, whereSb.toString(), 1);
                this.appendNewline(updateSb, "</where>", 1);
                this.appendNewline(updateSb, "</update>");

                this.appendNewline(deleteSb, "<delete id=\"delete" + camelTableName + "\" parameterType=\"Table" + camelTableName + "VO\">");
                this.appendNewline(deleteSb, "DELETE FROM " + tableName, 1);
                this.appendNewline(deleteSb, "<where>", 1);
                this.appendNewline(deleteSb, whereSb.toString(), 1);
                this.appendNewline(deleteSb, "</where>", 1);
                this.appendNewline(deleteSb, "</delete>");

                this.appendNewline(jqGridSearchKeySb, "}", 3);
                this.appendNewline(jqGridSearchKeySb, "}", 2);
                this.appendNewline(jqGridSearchKeySb, "}", 1);
                this.appendNewline(jqGridSearchKeySb, "}", 0);

                System.out.println(voSb);
                System.out.println("\n\n");
                // System.out.println(insertSb);
                // System.out.println("\n\n");
                // System.out.println(updateSb);
                // System.out.println("\n\n");
                // System.out.println(deleteSb);
                // System.out.println("\n\n");
                System.out.println(jqGridWhereSb);
                System.out.println("\n\n");
                System.out.println(jqGridSearchKeySb);

            } catch (final Exception e) {
                throw e;
            }
            System.out.println();
        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    private StringBuilder appendNewline(final StringBuilder sb, final String appendStr) {
        return this.appendNewline(sb, appendStr, 0);
    }

    private StringBuilder appendNewline(final StringBuilder sb, final String appendStr, final int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append("    ");
        }
        sb.append(appendStr);
        return sb.append("\n");
    }

}
