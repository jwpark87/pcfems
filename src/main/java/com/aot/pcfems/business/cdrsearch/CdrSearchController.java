/*****************************************************************************
 * 프로그램명  : CdrSearchController.java
 * 설     명  : CDR 조회 통합 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.19   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.cdrsearch;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractDownloadView;
import com.eact.web.lib.utils.DateUtils;
import com.eact.web.lib.utils.ESShareUtils;
import com.eact.web.lib.utils.ESUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * CDR 조회 통합 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class CdrSearchController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CdrSearchService cdrSearchService;

    /**
     * CDR 조회 통합 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do")
    public String goCdrSearch(final Model model, final HttpServletRequest request, final CdrSearchSearchInfo searchInfo,
                              @RequestParam(value = "record_status_yn", required = false) final String record_status_yn) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setSrch_index_type(request.getParameter("index_type"));
        // view에서 검색 기준일자 취득
        final CdrSearchVO defCdrSearch = this.cdrSearchService.getCdrDefaultInfo(searchInfo);
        searchInfo.setSrch_date_from(defCdrSearch.getDef_fromdt());
        searchInfo.setSrch_date_to(defCdrSearch.getDef_todt());
        searchInfo.setSrch_es_base_url(defCdrSearch.getEs_base_url());
        searchInfo.setSrch_out_col_ids(defCdrSearch.getOut_col_ids());
        searchInfo.setSrch_out_col_names(defCdrSearch.getOut_col_names());
        searchInfo.setSrch_out_col_types(defCdrSearch.getOut_col_types());
        searchInfo.setSrch_def_col_id(defCdrSearch.getDef_col_id());

        searchInfo.setSrch_total_row_cnt(StringUtil.toComma(defCdrSearch.getTotal_row_cnt()));
        searchInfo.setSrch_oldest_dt(defCdrSearch.getOldest_dt());
        searchInfo.setSrch_newest_dt(defCdrSearch.getNewest_dt());

        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_date_from", searchInfo.getSrch_date_from().substring(0, 10));
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(12, 14));
        model.addAttribute("srch_input_date_to", searchInfo.getSrch_date_to().substring(0, 10));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(10, 12));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(12, 14));
        model.addAttribute("RESULT_DATA", this.cdrSearchService.getCdrFilterColumnList(searchInfo));
        model.addAttribute("RESULT_DATA2", MenuMng.getMenuTypeByUrl((String) request.getAttribute("MENU_URL")));
        model.addAttribute("record_status_yn", record_status_yn);

        final String menuType = (String) request.getAttribute("RESULT_DATA2");

        String type = "main";
        if ("W".equals(menuType)) {
            type = "panel";
        }
        model.addAttribute("type", type);
        return "/cdrsearch/CdrSearchList";
    }

    /**
     * CDR 조회 컬럼 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_COLUMN_LIST")
    @ResponseBody
    public Map<String, JsonElement> getCdrColumnList(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.cdrSearchService.getCdrColumnList(searchInfo);
    }

    /**
     * CDR 조회 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_DATA_LIST")
    @ResponseBody
    public JsonObject getCdrSearchList(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
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

        final String rowArrayString = esUtils.executeSearch(searchInfo.getStartPageIndex(), searchInfo.getEndPageIndex());

        final JsonArray jsonArray = new JsonArray();
        if (rowArrayString == null || "".equals(rowArrayString)) {
            this.logger.debug("executeSearch failed.");
        } else {
            final JsonArray arrayResult = AotMapperUtils.writeObjectAsJsonArray(rowArrayString);
            totalRows = esUtils.getTotalRows();
            for (final JsonElement json : arrayResult) {
                jsonArray.add(json.getAsJsonObject().get("_source"));
            }
        }
        this.logger.debug("------total----------   :   " + totalRows);
        final JsonObject result = JqGridUtil.getJsonData(searchInfo, jsonArray, totalRows);
        return result;
    }

    /**
     * CDR 조회 엑셀 다운
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_EXCEL_DATA_LIST")
    @ResponseBody
    public JsonObject getCdrSearchExcelList(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
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
            this.logger.debug("-----condition-----------   :   " + prefix[i] + "||" + column[i] + "||" + type[i] + "||" + input[i]);
        }

        final String strUuid = indexType + "_" + StringUtils.remove(StringUtils.remove(StringUtils.remove(fromDt, " "), ":"), "-") + "_"
                + StringUtils.remove(StringUtils.remove(StringUtils.remove(toDt, " "), ":"), "-");
        esUtils.buildExcelPrepare(strUuid, 1000);
        esUtils.start();

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", strUuid);
        return jsonObject;
    }

    /**
     * CDR 조회 엑셀 다운 - 그리드 내용 그대로 엑셀출력
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_EXCEL_DATA_LIST_GRID")
    @ResponseBody
    public JsonObject getCdrSearchExcelListGrid(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
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
        final String strUuid = "uuid_" + userInfo.getUser_id() + "_" + DateUtils.getSysdateMilli();

        this.logger.debug("-----strUuid-----------   :   " + strUuid);
        final boolean b_ret = esUtils.buildExcelDoc(strUuid, searchInfo.getSrch_grid_data());

        final JsonObject jsonObject = new JsonObject();
        if (b_ret) {
            jsonObject.addProperty("uuid", strUuid);
        } else {
            jsonObject.addProperty("uuid", "");
        }
        return jsonObject;
    }

    /**
     * CDR 조회 엑셀 상태
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_EXCEL_STATUS_LIST")
    @ResponseBody
    public JsonObject getCdrSearchExcelStatus(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");
        searchInfo.doJqGridBind(request);
        final String strStatus = ESShareUtils.getStatus(searchInfo.getSrch_uuid());

        this.logger.debug("strStatus" + strStatus);

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
     * CDR 조회 엑셀 중지 요청
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_EXCEL_STOP")
    @ResponseBody
    public JsonObject getCdrSearchExcelStop(final HttpServletRequest request, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        // logger.debug("-----STOP----------- : "+searchInfo.getSrch_uuid());
        ESShareUtils.setCancel(searchInfo.getSrch_uuid());
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", "success:" + AotMessageUtils.getMessage("MSG.SUCCESS"));
        return jsonObject;
    }

    /**
     * CDR 조회 엑셀 다운로드
     *
     * @param request  Request객체
     * @param response Response객체
     * @return
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_CDR_EXCEL_DOWN")
    public String getCdrSearchExcelDown(final Model model, final HttpServletRequest request, final HttpServletResponse response, final CdrSearchSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final StringBuffer sbFileName = new StringBuffer(System.getProperty("java.io.tmpdir"));
        sbFileName.append(System.getProperty("file.separator"));
        final String file = searchInfo.getSrch_uuid() + ".xlsx";
        sbFileName.append(file);
        model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, new File(sbFileName.toString()));
        model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, file);
        return AbstractDownloadView.VIEW_NAME;
    }

    @RequestMapping(value = "/admin/CdrSearch/getPrcHandleSnmpalarmList.do", params = "actionID=getPrcHandleSnmpalarmList")
    @ResponseBody
    public Map<String, Object> getPrcHandleSnmpalarmList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.cdrSearchService.getPrcHandleSnmpalarmList(searchInfo);
    }

    @RequestMapping(value = "/admin/CdrSearch/cdrSearch.do", params = "actionID=ACTION_KILL_SESSION")
    @ResponseBody
    public CommonException killSession(final HttpServletRequest request, @RequestParam("jsonStr") final String jsonStr, @RequestParam("password") final String password)
            throws UserSysException, BizException, CommonException {
        this.cdrSearchService.killSession(jsonStr, password);
        return CommonException.EXCEPTION_SUCCESS_NORMAL;
    }

}