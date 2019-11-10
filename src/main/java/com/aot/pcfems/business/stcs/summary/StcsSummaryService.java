package com.aot.pcfems.business.stcs.summary;

import com.aot.pcfems.business.stcs.varstats.VarStats;
import com.aot.pcfems.business.stcs.varstats.VarStatsSearchInfo;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.standard.common.util.AotMapperUtils;
import com.eact.web.lib.utils.ESUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class StcsSummaryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StcsSummaryDAO stcsSummaryDAO;

    /**
     * 조회 기본값 취득
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return VarStats 기본값 취득
     */

    public List<VarStats> getVarStatsDefaultInfo(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.stcsSummaryDAO.getVarStatsDefaultInfo(searchInfo);
    }

    /**
     * 컬럼 조회 취득
     *
     * @param searchInfo 검색정보빈
     * @return List<VarStats>컬럼 리스트
     */

    public Map<String, JsonElement> getVarStatsColumnList(final VarStatsSearchInfo searchInfo) throws UserSysException {
        final Map<String, JsonElement> rtnMap = new HashMap<>();

        // 타이틀
        final List<String> colNames = new ArrayList<>();
        // 모델
        final List<Map<String, String>> colModel = new ArrayList<>();

        final List<VarStats> result = this.stcsSummaryDAO.getVarStatsColumnList(searchInfo);
        if (result.size() > 0) {
            for (final VarStats obj : result) {
                colNames.add(obj.getCol_nm());
                final Map<String, String> rowMap = new HashMap<>();
                rowMap.put("name", obj.getCol_cd());
                rowMap.put("index", obj.getCol_cd());
                rowMap.put("width", obj.getDsp_width());
                if ("L".equals(obj.getText_align())) {
                    rowMap.put("align", "left");
                } else if ("R".equals(obj.getText_align())) {
                    rowMap.put("align", "right");
                } else if ("C".equals(obj.getText_align())) {
                    rowMap.put("align", "center");
                }
                rowMap.put("display_name", obj.getCol_nm());
                colModel.add(rowMap);
            }
            rtnMap.put("ColNames", JqGridUtil.getJsonData(colNames));
            rtnMap.put("ColModel", JqGridUtil.getJsonData(colModel));
        }
        return rtnMap;
    }

    public JsonObject getVarStatsList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo, final Integer start) throws UserSysException {
        long totalRows = 0;
        final int allItemCnt = searchInfo.getSrch_text_prefix().split(",").length;
        final String[] prefix = searchInfo.getSrch_text_prefix().split(",", allItemCnt);
        final String[] column = searchInfo.getSrch_filter_column().split(",", allItemCnt);
        final String[] type = searchInfo.getSrch_filter_type().split(",", allItemCnt);
        final String[] input = searchInfo.getSrch_filter_input().split(",", allItemCnt);
        final String indexType = searchInfo.getSrch_index_type();
        final String esBaseUrl = searchInfo.getSrch_es_base_url();
        final String outColIds = searchInfo.getSrch_out_col_ids();
        final String outColNames = searchInfo.getSrch_out_col_names();
        final String outColTypes = searchInfo.getSrch_out_col_types();
        final String defColId = searchInfo.getSrch_def_col_id();
        final String fromDt = searchInfo.getSrch_date_from();
        final String toDt = searchInfo.getSrch_date_to();

        // this.logger.debug("esBaseUrl:{}, defColId: {}, fromDt: {}, toDt: {}, indexType: {}", esBaseUrl, defColId, fromDt, toDt, indexType);
        // this.logger.debug("outColIds:{}, outColNames: {}, outColTypes: {}", outColIds, outColNames, outColTypes);
        // this.logger.debug("getStartPageIndex: {}, getEndPageIndex: {}", searchInfo.getStartPageIndex(), searchInfo.getEndPageIndex());

        final ESUtils esUtils = new ESUtils();

        this.logger.debug("esUtils.prepare(\n{},\n{},\n{},\n{},\n{},\n{},\n{},\n{}\n);", esBaseUrl, outColIds, outColNames, outColTypes, defColId, fromDt, toDt, indexType);
        esUtils.prepare(esBaseUrl, outColIds, outColNames, outColTypes, defColId, fromDt, toDt, indexType);
        for (int i = 0; i < allItemCnt; i++) {
            if (!"ALL".equals(column[i])) {
                esUtils.addSearchCondition(prefix[i], column[i], type[i], input[i]);
            }
            // logger.debug("-----condition----------- : "+prefix[i] + "^^" + column[i]+ "^^" + type[i] + "^^" +input[i]);
        }
        // esUtils.addSearchCondition("AND", "locality", "=", searchInfo.getSrch_locality());

        final String rowArrayString = esUtils.executeSearch(searchInfo.getStartPageIndex(), searchInfo.getEndPageIndex());

        final JsonArray jsonArray = new JsonArray();
        if (rowArrayString == null || "".equals(rowArrayString)) {
            this.logger.debug("executeSearch failed.");
        } else {
            final JsonArray arrayResult = AotMapperUtils.writeObjectAsJsonArray(rowArrayString);
            totalRows = esUtils.getTotalRows();
            for (final JsonElement json : arrayResult) {
                final JsonObject jsonObject = json.getAsJsonObject();
                for (final Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    if (entry.getValue().isJsonObject()) {
                        for (final Entry<String, JsonElement> entry2 : entry.getValue().getAsJsonObject().entrySet()) {
                            if (StringUtils.isNumeric(entry2.getValue().getAsString())) {
                                entry2.setValue(AotMapperUtils.writeObjectAsJsonElement(NumberFormat.getInstance().format(Long.parseLong(entry2.getValue().getAsString()))));
                            }
                        }
                    }
                }
                jsonArray.add(json.getAsJsonObject().get("_source"));
            }
        }
        this.logger.debug("------total----------   :   " + totalRows);
        return JqGridUtil.getJsonData(searchInfo, jsonArray, totalRows);
    }
}
