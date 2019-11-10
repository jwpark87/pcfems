/*****************************************************************************
 * 프로그램명  : VarStatsServiceImpl.java
 * 설     명  : var/stats 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.21   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.varstats;

import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusDAO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.standard.common.util.AotMapperUtils;
import com.eact.web.lib.utils.ESUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * var/stats 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class VarStatsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private VarStatsDAO varStatsDAO;
    @Resource
    private TableEmsmVmStatusDAO tableEmsmVmStatusDAO;

    /**
     * 조회 기본값 취득
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return VarStats 기본값 취득
     */

    public VarStats getVarStatsDefaultInfo(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.varStatsDAO.getVarStatsDefaultInfo(searchInfo);
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

        final List<VarStats> result = this.varStatsDAO.getVarStatsColumnList(searchInfo);
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

    /**
     * 조회 필터 컬럼 목록
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return List 필터 컬럼 리스트
     */

    public List<CodeInfo> getVarStatsFilterColumnList(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.varStatsDAO.getVarStatsFilterColumnList(searchInfo);
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

        this.logger.debug("esBaseUrl:{}, defColId: {}, fromDt: {}, toDt: {}, indexType: {}", esBaseUrl, defColId, fromDt, toDt, indexType);
        this.logger.debug("outColIds:{}, outColNames: {}, outColTypes: {}", outColIds, outColNames, outColTypes);
        this.logger.debug("getStartPageIndex: {}, getEndPageIndex: {}", searchInfo.getStartPageIndex(), searchInfo.getEndPageIndex());

        final ESUtils esUtils = new ESUtils();
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

    public JsonObject getChartData(final VarStatsSearchInfo searchInfo, final String strGraphType, final String strXColumnId, final String strTgtType, final String strTgtColumnIdList,
                                   final String strYColumnIdList) throws UserSysException {
        final ESUtils esUtils = new ESUtils();
        esUtils.prepare(searchInfo.getSrch_es_base_url(), searchInfo.getSrch_out_col_ids(), searchInfo.getSrch_out_col_names(), searchInfo.getSrch_out_col_types(), searchInfo.getSrch_def_col_id(),
                searchInfo.getSrch_date_from(), searchInfo.getSrch_date_to(), searchInfo.getSrch_index_type());
        final String strJSon = esUtils.fetchGraphData(strGraphType, strXColumnId, strTgtType, strTgtColumnIdList, strYColumnIdList, 1000);
        // this.logger.debug(strJSon);

        final JsonArray jsonArray = AotMapperUtils.writeObjectAsJsonArray(strJSon);
        final Set<String> set = new ListOrderedSet<>();
        for (final JsonElement jsonElement : jsonArray) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            set.add(jsonObject.get("tgt_name").getAsString());
        }

        final JsonObject result = new JsonObject();
        for (final String graphColumn : set) {
            final JsonObject data = new JsonObject();
            data.add("xvalList", new JsonArray());
            data.add("yvalList", new JsonArray());
            data.addProperty("tgt_name", graphColumn);
            result.add(graphColumn, data);
        }

        // this.logger.debug(AotMapperUtils.writeObjectAsString(result));
        for (final JsonElement jsonElement : jsonArray) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            // if (StringUtils.equals(strTgtType, "COLNAME")) {
            // final String data = jsonObject.get("tgt_name").getAsString();
            // result.get(data).getAsJsonObject().get("xvalList").getAsJsonArray().add(jsonObject.get("xval"));
            // result.get(data).getAsJsonObject().get("yvalList").getAsJsonArray().add(jsonObject.get("yval"));
            // } else if (StringUtils.equals(strTgtType, "COLVAL")) {
            final String data = jsonObject.get("tgt_name").getAsString();
            result.get(data).getAsJsonObject().get("xvalList").getAsJsonArray().add(jsonObject.get("xval"));
            result.get(data).getAsJsonObject().get("yvalList").getAsJsonArray().add(jsonObject.get("yval"));
            // }
        }
        // this.logger.debug(AotMapperUtils.writeObjectAsString(result));
        return result;
    }

    public List<TableEmsmVmStatusVO> getNodeNameListBySvrId(final String svrId) {
        final TableEmsmVmStatusVO tableEmsmVmStatusVO = new TableEmsmVmStatusVO();
        tableEmsmVmStatusVO.setSvr_id(svrId);
        tableEmsmVmStatusVO.setHa_status("A");
        return this.tableEmsmVmStatusDAO.getList(tableEmsmVmStatusVO, Arrays.asList("svr_id", "ha_status"), "sortseq asc");
    }

}
