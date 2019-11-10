/*****************************************************************************
 * 프로그램명  : VarStatsController.java
 * 설     명  : var/stats 조회 통합 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.21   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.varstats;

import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractDownloadView;
import com.eact.web.lib.utils.DateUtils;
import com.eact.web.lib.utils.ESShareUtils;
import com.eact.web.lib.utils.ESUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * var/stats 조회 통합 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class VarStatsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private VarStatsService varStatsService;

    /**
     * 조회 통합 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do")
    public String goVarStats(final Model model, final HttpServletRequest request, final VarStatsSearchInfo searchInfo,
                             @RequestParam(value = "hfixed_left_col_cnt", required = false, defaultValue = "0") final Integer hfixed_left_col_cnt,
                             @RequestParam(value = "hfixed_right_col_cnt", required = false, defaultValue = "0") final Integer hfixed_right_col_cnt) throws UserSysException {

        searchInfo.doJqGridBind(request); // 목록 화면으로 이동
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("hfixed_left_col_cnt", hfixed_left_col_cnt);
        model.addAttribute("hfixed_right_col_cnt", hfixed_right_col_cnt);
        return "/stcs/var/VarList";
    }

    @RequestMapping(value = "/stcs/var/VarListGraphPop.pop", method = RequestMethod.GET)
    public String goVarStats() throws UserSysException {
        return "/stcs/var/VarListGraphPop";
    }

    /**
     * 조회 기본값 취득
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_DEFAULT_LIST")
    @ResponseBody
    public JsonObject getVarStatsDefInfo(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        // view에서 검색 기준일자 취득
        final VarStats defVarStats = this.varStatsService.getVarStatsDefaultInfo(searchInfo);
        // 그리드 컬럼 목록
        final JsonObject jsonObject = new JsonObject();
        if (defVarStats != null) {
            jsonObject.addProperty("srch_date_from", defVarStats.getDef_fromdt());
            jsonObject.addProperty("srch_date_to", defVarStats.getDef_todt());
            jsonObject.addProperty("srch_es_base_url", defVarStats.getEs_base_url());
            jsonObject.addProperty("srch_out_col_ids", defVarStats.getOut_col_ids());
            jsonObject.addProperty("srch_out_col_names", defVarStats.getOut_col_names());
            jsonObject.addProperty("srch_out_col_types", defVarStats.getOut_col_types());
            jsonObject.addProperty("srch_def_col_id", defVarStats.getDef_col_id());
            jsonObject.addProperty("hfixed_left_col_cnt", defVarStats.getHfixed_left_col_cnt());
        }
        jsonObject.addProperty("filter", StringUtil.comboString(this.varStatsService.getVarStatsFilterColumnList(searchInfo), true, "ALL", AotMessageUtils.getMessage("TEXT.COMM.SEL.PLEASE"),
                AuthorityUtil.getUserInfo(request)));
        return jsonObject;
    }

    /**
     * 조회 컬럼 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_COLUMN_LIST")
    @ResponseBody
    public Map<String, JsonElement> getVarStatsColumnList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.varStatsService.getVarStatsColumnList(searchInfo);
    }

    /**
     * 조회 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_DATA_LIST")
    @ResponseBody
    public JsonObject getVarStatsList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo, @RequestParam(value = "start", defaultValue = "0") final Integer start)
            throws UserSysException {
        searchInfo.doJqGridBind(request);
        if (start.intValue() == 0) {
            searchInfo.setJqPage(1);
        } else {
            searchInfo.setJqPage(start / searchInfo.getJqRows() + 1);
        }
        return this.varStatsService.getVarStatsList(request, searchInfo, start);
    }

    @RequestMapping(value = "/stcs/var/getChartData.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject getChartData(final VarStatsSearchInfo searchInfo, @RequestParam(value = "strGraphType") final String strGraphType,
                                   @RequestParam(value = "strXColumnId") final String strXColumnId, @RequestParam(value = "strTgtType") final String strTgtType,
                                   @RequestParam(value = "strTgtColumnIdList") final String strTgtColumnIdList,
                                   @RequestParam(value = "strYColumnIdList", required = false) final String strYColumnIdList)
            throws UserSysException {
        return this.varStatsService.getChartData(searchInfo, strGraphType, strXColumnId, strTgtType, strTgtColumnIdList, strYColumnIdList);
    }

    @RequestMapping(value = "/stcs/var/getNodeNameListBySvrId.do", method = RequestMethod.POST)
    @ResponseBody
    public List<TableEmsmVmStatusVO> getNodeNameListBySvrId(@RequestParam(value = "svrId", defaultValue = "PCF01", required = false) final String svrId) throws UserSysException {
        return this.varStatsService.getNodeNameListBySvrId(svrId);
    }

    /**
     * 조회 엑셀 다운
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_EXCEL_DATA_LIST")
    @ResponseBody
    public JsonObject getVarStatsExcelList(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
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
        // esUtils.addSearchCondition("AND", "locality", "=", searchInfo.getSrch_locality());

        final String strUuid = indexType + "_" + StringUtils.remove(StringUtils.remove(StringUtils.remove(fromDt, " "), ":"), "-") + "_"
                + StringUtils.remove(StringUtils.remove(StringUtils.remove(toDt, " "), ":"), "-");
        esUtils.buildExcelPrepare(strUuid, 1000);
        esUtils.start();

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", strUuid);
        return jsonObject;
    }

    /**
     * 조회 엑셀 다운 - 그리드 내용 그대로 엑셀출력
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_EXCEL_DATA_LIST_GRID")
    @ResponseBody
    public JsonObject getVarStatsExcelListGrid(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
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
     * 조회 엑셀 상태
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_EXCEL_STATUS_LIST")
    @ResponseBody
    public JsonObject getVarStatsExcelStatus(final HttpServletRequest request, final VarStatsSearchInfo searchInfo) throws UserSysException {
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
     * 조회 엑셀 중지 요청
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_EXCEL_STOP")
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
    @RequestMapping(value = "/stcs/var/stats.do", params = "actionID=ACTION_VARSTATS_EXCEL_DOWN")
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
