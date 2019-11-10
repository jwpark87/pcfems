package com.aot.pcfems.business.stcs.summary;

import com.aot.pcfems.business.stcs.varstats.VarStats;
import com.aot.pcfems.business.stcs.varstats.VarStatsSearchInfo;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractDownloadView;
import com.eact.web.lib.utils.ESShareUtils;
import com.eact.web.lib.utils.ESUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
public class StcsSummaryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StcsSummaryService stcsSummaryService;

    @RequestMapping(value = "/stcs/summary/stcsSummary.view")
    public String view(final Model model, @RequestParam(value = "intervalCode", defaultValue = "VSTAT_MULTI_INTERVAL", required = false) final String intervalCode) {
        model.addAttribute("intervalCode", intervalCode);
        return "/stcs/summary/StcsSummary";
    }

    /**
     * 조회 기본값 취득
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/summary/getVarStatsDefInfo.json")
    @ResponseBody
    public JsonObject getVarStatsDefInfo(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        // view에서 검색 기준일자 취득
        final List<VarStats> defVarStats = this.stcsSummaryService.getVarStatsDefaultInfo(searchInfo);
        // 그리드 컬럼 목록
        final JsonObject jsonObject = new JsonObject();
        if (defVarStats != null && defVarStats.size() > 0) {
            jsonObject.addProperty("srch_date_from", defVarStats.get(0).getDef_fromdt());
            jsonObject.addProperty("srch_date_to", defVarStats.get(0).getDef_todt());
            jsonObject.addProperty("srch_es_base_url", defVarStats.get(0).getEs_base_url());
            jsonObject.addProperty("srch_out_col_ids", defVarStats.get(0).getOut_col_ids());
            jsonObject.addProperty("srch_out_col_names", defVarStats.get(0).getOut_col_names());
            jsonObject.addProperty("srch_out_col_types", defVarStats.get(0).getOut_col_types());
            jsonObject.addProperty("srch_def_col_id", defVarStats.get(0).getDef_col_id());

            final JsonArray jsonArray = new JsonArray();
            for (final VarStats varStats : defVarStats) {
                final JsonObject json = new JsonObject();
                json.addProperty("index_type", varStats.getIndex_type());
                json.addProperty("format_name", varStats.getFormat_name());
                searchInfo.setSrch_index_type(varStats.getIndex_type());
                json.add("columnList", AotMapperUtils.writeObjectAsJsonElement(this.stcsSummaryService.getVarStatsColumnList(searchInfo)));
                jsonArray.add(json);
            }
            jsonObject.add("index_type_list", jsonArray);
        }
        // jsonObject.addProperty("filter", StringUtil.comboString(null, true, "ALL", AotMessageUtils.getMessage("TEXT.COMM.SEL.PLEASE"), AuthorityUtil.getUserInfo(request)));
        return jsonObject;
    }

    @RequestMapping(value = "/stcs/summary/getVarStatsList.json")
    @ResponseBody
    public JsonObject getVarStatsList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo, @RequestParam(value = "start", defaultValue = "0") final Integer start,
                                      @RequestParam(value = "srch_parent_index_type") final String srch_parent_index_type, @RequestParam(value = "srch_interval") final String srch_interval)
            throws UserSysException {
        searchInfo.doJqGridBind(request);
        final VarStatsSearchInfo searchInfo2 = new VarStatsSearchInfo();
        searchInfo2.setSrch_index_type(srch_parent_index_type);
        final List<VarStats> defVarStats = this.stcsSummaryService.getVarStatsDefaultInfo(searchInfo2);
        for (final VarStats varStats : defVarStats) {
            if (StringUtils.equals(varStats.getIndex_type(), searchInfo.getSrch_index_type())) {
                searchInfo.setSrch_es_base_url(varStats.getEs_base_url());
                searchInfo.setSrch_out_col_ids(varStats.getOut_col_ids());
                searchInfo.setSrch_out_col_names(varStats.getOut_col_names());
                searchInfo.setSrch_out_col_types(varStats.getOut_col_types());
                searchInfo.setSrch_def_col_id(varStats.getDef_col_id());
                searchInfo.setSrch_index_type(searchInfo.getSrch_index_type().concat("-").concat(srch_interval));
                if (start.intValue() == 0) {
                    searchInfo.setJqPage(1);
                } else {
                    searchInfo.setJqPage(start / searchInfo.getJqRows() + 1);
                }
                return this.stcsSummaryService.getVarStatsList(request, searchInfo, start);
            }
        }
        throw CommonException.EXCEPTION_ERROR_SYSTEM;
    }

    /**
     * 조회 엑셀 다운
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/summary/getVarStatsExcelList.json")
    @ResponseBody
    public JsonObject getVarStatsExcelList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo, @RequestParam(value = "srch_interval") final String srch_interval)
            throws UserSysException {
        final VarStatsSearchInfo searchInfo2 = new VarStatsSearchInfo();
        searchInfo2.setSrch_index_type(searchInfo.getSrch_index_type());
        final List<VarStats> defVarStats = this.stcsSummaryService.getVarStatsDefaultInfo(searchInfo2);

        final ESUtils[] esUtils = new ESUtils[defVarStats.size()];
        for (int i = 0; i < defVarStats.size(); i++) {
            final String indexType = defVarStats.get(i).getIndex_type();
            searchInfo2.setSrch_index_type(indexType);
            final Map<String, JsonElement> varStats = this.stcsSummaryService.getVarStatsColumnList(searchInfo2);

            final StringBuilder outColIds = new StringBuilder();
            for (final JsonElement json : varStats.get("ColModel").getAsJsonArray()) {
                outColIds.append(json.getAsJsonObject().get("index").getAsString()).append(",");
            }
            final StringBuilder outColNames = new StringBuilder();
            for (final JsonElement json : varStats.get("ColNames").getAsJsonArray()) {
                outColNames.append(json.getAsString()).append(",");
            }

            this.logger.debug("defVarStats.get({}) : {}", i, AotMapperUtils.writeObjectAsString(defVarStats.get(i)));
            this.logger.debug("esUtils[{}].prepare(\n{},\n{},\n{},\n{},\n{},\n{},\n{},\n{}\n);", i, defVarStats.get(i).getEs_base_url(), StringUtils.removeEnd(outColIds.toString(), ","),
                    StringUtils.removeEnd(outColNames.toString(), ","), defVarStats.get(i).getOut_col_types(), defVarStats.get(i).getDef_col_id(), searchInfo.getSrch_date_from(),
                    searchInfo.getSrch_date_to(), indexType.concat("-").concat(srch_interval));
            esUtils[i] = new ESUtils();
            esUtils[i].prepare(defVarStats.get(i).getEs_base_url(), StringUtils.removeEnd(outColIds.toString(), ","), StringUtils.removeEnd(outColNames.toString(), ","),
                    defVarStats.get(i).getOut_col_types(), defVarStats.get(i).getDef_col_id(), searchInfo.getSrch_date_from(), searchInfo.getSrch_date_to(),
                    indexType.concat("-").concat(srch_interval));
            esUtils[i].addExcelTitle(defVarStats.get(i).getFormat_name());
            if (i != 0) {
                esUtils[0].addExcelESUtils(esUtils[i]);
            }
        }

        final String strUuid = searchInfo.getSrch_index_type() + "_" + StringUtils.replaceChars(defVarStats.get(0).getDef_fromdt(), " :-", "") + "_"
                + StringUtils.replaceChars(defVarStats.get(0).getDef_todt(), " :-", "");
        esUtils[0].buildExcelPrepare(strUuid, 1000);
        esUtils[0].start();

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", strUuid);
        return jsonObject;
    }

    /**
     * 조회 엑셀 다운 - 그리드 내용 그대로 엑셀출력
     *
     * @param request
     *            Request객체
     * @param response
     *            Response객체
     * @exception UserSysException
     *                시스템예외
     */
    // @RequestMapping(value = "/stcs/summary/getVarStatsExcelListGrid.json")
    // @ResponseBody
    // public JsonObject getVarStatsExcelListGrid(final HttpServletRequest request, final VarStatsSearchInfo searchInfo)
    // throws UserSysException {
    // searchInfo.doJqGridBind(request);
    // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
    // final String indexType = searchInfo.getSrch_index_type();
    // final String esBaseUrl = searchInfo.getSrch_es_base_url();
    // final String outColIds = searchInfo.getSrch_out_col_ids();
    // final String outColNames = searchInfo.getSrch_out_col_names();
    // final String outColTypes = searchInfo.getSrch_out_col_types();
    // final String defColId = searchInfo.getSrch_def_col_id();
    // final String fromDt = searchInfo.getSrch_date_from();
    // final String toDt = searchInfo.getSrch_date_to();
    //
    // this.logger.debug("esBaseUrl:{}, defColId: {}, fromDt: {}, toDt: {}, indexType: {}", esBaseUrl, defColId, fromDt, toDt, indexType);
    // this.logger.debug("outColIds:{}, outColNames: {}, outColTypes: {}", outColIds, outColNames, outColTypes);
    // this.logger.debug("getStartPageIndex: {}, getEndPageIndex: {}", searchInfo.getStartPageIndex(), searchInfo.getEndPageIndex());
    //
    // final ESUtils esUtils = new ESUtils();
    // esUtils.prepare(esBaseUrl, outColIds, outColNames, outColTypes, defColId, fromDt, toDt, indexType);
    // final String strUuid = "uuid_" + userInfo.getUser_id() + "_" + DateUtils.getSysdateMilli();
    //
    // this.logger.debug("-----strUuid----------- : " + strUuid);
    // final boolean b_ret = esUtils.buildExcelDoc(strUuid, searchInfo.getSrch_grid_data());
    //
    // final JsonObject jsonObject = new JsonObject();
    // if (b_ret) {
    // jsonObject.addProperty("uuid", strUuid);
    // } else {
    // jsonObject.addProperty("uuid", "");
    // }
    // return jsonObject;
    // }

    /**
     * 조회 엑셀 상태
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/summary/getVarStatsExcelStatus.json")
    @ResponseBody
    public JsonObject getVarStatsExcelStatus(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");
        searchInfo.doJqGridBind(request);
        final String strStatus = ESShareUtils.getStatus(searchInfo.getSrch_uuid());

        this.logger.debug("strStatus: {}" + strStatus);

        if (strStatus == null) { // mean error occur.
            this.logger.debug("strStatus is null.");
        } else {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        final String[] statusArray = StringUtils.split(strStatus, "/");

        final String uploadPath = System.getProperty("java.io.tmpdir");
        this.logger.debug("-----uploadPath-----------   :   " + uploadPath);

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
        jsonObject.addProperty("status", statusArray[0]);
        jsonObject.addProperty("total", statusArray[1]);
        jsonObject.addProperty("recv", statusArray[2]);
        jsonObject.addProperty("path", uploadPath);
        return jsonObject;
    }

    /**
     * 조회 엑셀 중지 요청
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/summary/getVarStatsExcelStop.json")
    @ResponseBody
    public JsonObject getVarStatsExcelStop(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        // logger.debug("-----STOP----------- : "+searchInfo.getSrch_uuid());
        ESShareUtils.setCancel(searchInfo.getSrch_uuid());

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", "success:" + AotMessageUtils.getMessage("MSG.SUCCESS"));
        return jsonObject;
    }

    /**
     * 조회 엑셀 다운로드
     *
     * @param request  Request객체
     * @param response Response객체
     * @return
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/summary/getVarStatsExcelDown.json")
    public String getVarStatsExcelDown(final Model model, final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final String file = searchInfo.getSrch_uuid() + ".xlsx";
        final StringBuffer sbFileName = new StringBuffer(System.getProperty("java.io.tmpdir"));
        sbFileName.append(System.getProperty("file.separator"));
        sbFileName.append(file);
        model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, new File(sbFileName.toString()));
        model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, file);
        return AbstractDownloadView.VIEW_NAME;
    }
}
