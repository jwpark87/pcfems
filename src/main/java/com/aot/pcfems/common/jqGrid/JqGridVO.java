/*****************************************************************************
 * 프로그램명  : JqGridInfo.java
 * 설     명  : JqGrid DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author      Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2013.05.10  bestheroz    1.0
 *****************************************************************************/

package com.aot.pcfems.common.jqGrid;

import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * JqGridInfo
 *
 * @author eaction
 * @version 1.0
 */
public class JqGridVO {
    /**
     * 요청페이지
     */
    private int jqPage = 1;
    /**
     * 한페이지에 보여줄 rows 수
     */
    private int jqRows = 100;
    /**
     * 정렬 대상 컬럼값
     */
    private String jqSidx = "";
    /**
     * 정렬기준(오름차순/내림차순)
     */
    private String jqSord = "";
    /**
     * 검색파라미터 전체
     */
    private JsonObject jqFilters = null;
    /**
     * 검색그룹 (AND/OR)
     */
    private String jqGroupOp = "";
    /**
     * 검색필드(rules)
     */
    private JsonArray jqRules = null;
    /**
     * 검색필드(rules Size)
     */
    private int jqRuleSize = 0;
    /**
     * 검색필드(컬럼명)
     */
    private String[] jqField = null;
    /**
     * 검색Operation(like, equil..)
     */
    private String[] jqOp = null;
    /**
     * 검색Value
     */
    private String[] jqData = null;
    /**
     * FCS read count
     */
    private int fcsReadCount = 1;

    public JqGridVO doJqGridBind(final HttpServletRequest request) {
        final JqGridVO jqGridInfo = new JqGridVO();
        String filters = request.getParameter("filters");
        if (filters != null) {
            filters = filters.replace("%22", "\"");
            filters = filters.replace("%27", "\'");
        }

        // String jqSidxs = StringUtil.nvlOrder(request.getParameter("sidx"), "");
        String jqSidxs = StringUtils.defaultString(request.getParameter("sidx"));
        if (jqSidxs != null) {
            jqSidxs = jqSidxs.replace("%22", "\"");
            jqSidxs = jqSidxs.replace("%27", "\'");
        }

        this.setJqPage(Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("page"), "1")));
        this.setJqRows(Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("rows"), "100")));
        this.setJqSidx(jqSidxs);
        this.setJqSord(StringUtils.defaultIfEmpty(request.getParameter("sord"), ""));
        this.setJqGroupOp(StringUtils.defaultIfEmpty(request.getParameter("groupOp"), ""));
        this.setJqFilters(StringUtils.defaultIfEmpty(filters, ""));
        if (this.getJqFilters() != null && !this.getJqFilters().isJsonNull() && this.getJqFilters().size() > 0) {
            this.setJqGroupOp(this.jqFilters.get("groupOp").getAsString());
            this.setJqRules(this.jqFilters.get("rules").getAsJsonArray());
            this.setJqRuleSize(this.getJqRules().size());
            // rule사이즈가 1보다 크면 jqField, jqOp, jqData를 배열로 재선언한다.
            if (this.getJqRuleSize() > 0) {
                this.jqField = new String[this.getJqRuleSize()];
                this.jqOp = new String[this.getJqRuleSize()];
                this.jqData = new String[this.getJqRuleSize()];
            }
            for (int i = 0; i < this.getJqRuleSize(); i++) {
                final JsonObject jo = this.jqRules.get(i).getAsJsonObject();
                this.setJqField(i, jo.get("field").getAsString());
                this.setJqOp(i, jo.get("op").getAsString());
                this.setJqData(i, jo.get("data").getAsString());
            }

        }

        return jqGridInfo;
    }

    /**
     * limit 쿼리는 한페이지 5개인경우 0,5 & 5,5 뒷자리는 고정으로 알고 있으나 기존 BSS에서는 뒷자리가 변함 일단 뒷자리는 한 페이지에서 보여주는 것으로 고정함 2015-12-29 KYM
     */
    public int getStartPageIndex() {
        /* return this.getJqRows() * (this.getJqPage() - 1) + 1; */
        return (this.getJqPage() - 1) * this.getJqRows() + 1 - 1;

        /* end = String.valueOf( Integer.parseInt(start) + Integer.parseInt(rows)) ; */
    }

    public int getEndPageIndex() {
        /* return this.getJqRows() * this.getJqPage(); */
        return this.getJqRows();
    }

    /**
     * @return the jqSidx
     */
    public String getJqSidx() {
        return this.jqSidx;
    }

    /**
     * @param jqSidx the jqSidx to set
     */
    public void setJqSidx(final String jqSidx) {
        this.jqSidx = jqSidx;
    }

    /**
     * @return the jqSord
     */
    public String getJqSord() {
        return StringUtils.defaultString(this.jqSord);
    }

    /**
     * @param jqSord the jqSord to set
     */
    public void setJqSord(final String jqSord) {
        this.jqSord = jqSord;
    }

    /**
     * @return the jqGroupOp
     */
    public String getJqGroupOp() {
        return StringUtils.defaultString(this.jqGroupOp);
    }

    /**
     * @param jqGroupOp the jqGroupOp to set
     */
    public void setJqGroupOp(final String jqGroupOp) {
        this.jqGroupOp = jqGroupOp;
    }

    /**
     * @return the jqField
     */
    public String getJqField(final int index) {
        return StringUtils.defaultString(this.jqField[index]);
    }

    /**
     * @param jqField the jqField to set
     */
    public void setJqField(final int index, final String jqField) {
        this.jqField[index] = jqField;
    }

    /**
     * @return the jqOp
     */
    public String getJqOp(final int index) {
        return StringUtils.defaultString(this.jqOp[index]);
    }

    /**
     * @param jqOp the jqOp to set
     */
    public void setJqOp(final int index, final String jqOp) {
        this.jqOp[index] = jqOp;
    }

    /**
     * @return the jqData
     */
    public String getJqData(final int index) {
        return StringUtils.defaultString(this.jqData[index]);
    }

    /**
     * @param jqData the jqData to set
     */
    public void setJqData(final int index, final String jqData) {
        this.jqData[index] = jqData;
    }

    /**
     * @return the jqPage
     */
    public int getJqPage() {
        return this.jqPage;
    }

    /**
     * @param jqPage the jqPage to set
     */
    public void setJqPage(final int jqPage) {
        this.jqPage = jqPage;
    }

    /**
     * @return the jqRows
     */
    public int getJqRows() {
        return this.jqRows;
    }

    /**
     * @param jqRows the jqRows to set
     */
    public void setJqRows(final int jqRows) {
        this.jqRows = jqRows;
    }

    /**
     * @return the jqFilters
     */
    public JsonObject getJqFilters() {
        return this.jqFilters;
    }

    /**
     * @param jqFilters the jqFilters to set
     */
    public void setJqFilters(String jqFilters) {
        if (jqFilters.indexOf("{") < 0) {
            jqFilters = "{" + jqFilters + "}";
        }
        this.jqFilters = AotMapperUtils.writeObjectAsJsonObject(jqFilters);
    }

    /**
     * @return the jqRules
     */
    public JsonArray getJqRules() {
        return this.jqRules;
    }

    /**
     * @param jqRules the jqRules to set
     */
    public void setJqRules(final JsonArray jqRules) {
        this.jqRules = jqRules;
    }

    /**
     * @return the jqRules
     */
    public int getJqRuleSize() {
        return this.jqRuleSize;
    }

    /**
     * @param jqRules the jqRules to set
     */
    public void setJqRuleSize(final int jqRuleSize) {
        this.jqRuleSize = jqRuleSize;
    }

    public int getFcsReadCount() {
        return this.fcsReadCount;
    }

    public void setFcsReadCount(final int fcsReadCount) {
        this.fcsReadCount = fcsReadCount;
    }

}