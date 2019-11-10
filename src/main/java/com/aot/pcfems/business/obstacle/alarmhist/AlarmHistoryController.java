package com.aot.pcfems.business.obstacle.alarmhist;
/*****************************************************************************
 * 프로그램명  : AlarmHistoryController.java
 * 설     명  : 알람이력조회 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   HSI     1.0     초기작성
 *****************************************************************************/

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 알람이력조회 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class AlarmHistoryController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AlarmHistoryService alarmHistoryService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * 알람이력조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = {"/obstaclehist/alarmhistory.do", "/obstaclehist/alarmhistory.pop"})
    public String getAlarmHistory(final Model model, final HttpServletRequest request, final AlarmHistorySearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        if (StringUtils.endsWith(request.getRequestURI(), ".pop")) {
            searchInfo.setSrch_date_from(DateTime.now().minusMonths(1).toString(AotDateUtils.YYYYMMDD) + "0000");
            searchInfo.setSrch_date_to(DateTime.now().toString(AotDateUtils.YYYYMMDD) + "2355");
        } else {
            final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
            searchInfo.setSrch_date_from(today + AotDateUtils.getStringNow(AotDateUtils.HHMMSS).substring(0, 2) + "00");
            searchInfo.setSrch_date_to(today + "2355");
        }

        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_yyyymmdd_from", searchInfo.getSrch_date_from().substring(0, 8));
        model.addAttribute("srch_input_yyyymmdd_to", searchInfo.getSrch_date_to().substring(0, 8));
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        model.addAttribute("srch24HHList", CodeTableMng.getCodeListByGrcode(userInfo, "TIME_24HH", request.getSession()));
        model.addAttribute("srchMMList", CodeTableMng.getCodeListByGrcode(userInfo, "TIME_MM", request.getSession()));
        final List<CodeInfo> almLevelList = CodeTableMng.getCodeListByGrcode(userInfo, "ALM_LEVEL", request.getSession());
        for (int r = 0; r < almLevelList.size(); r++) {
            final CodeInfo cd = almLevelList.get(r);
            if ("CLEAR".equals(cd.getCode())) {
                almLevelList.remove(cd);
            }
        }
        model.addAttribute("almLevelList", almLevelList);
        return "/obstacle/AlarmHistory/AlarmHistory";
    }

    /**
     * 알람이력조회 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstaclehist/alarmhistory.do", params = "actionID=ACTION_ALARM_HISTORY_LIST")
    @ResponseBody
    public JsonObject getAlarmHistoryList(final HttpServletRequest request, final AlarmHistorySearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        searchInfo.setAr_search_alm_level(searchInfo.getSrch_alm_level().split(","));

        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.alarmHistoryService.getAlarmHistoryList(searchInfo, AuthorityUtil.getUserInfo(request)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * 엑셀출력
     *
     * @param request
     * @param response
     * @return modelAndView
     * @throws UserSysException
     * @throws IOException
     */
    @RequestMapping(value = "/obstaclehist/alarmhistory.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final AlarmHistorySearchInfo searchInfo, final AlarmHistoryInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.ALM_HIST.TITLE"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_STATUS"), "alm_status", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.HOST_NM"), "host_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_OCCUR_DT"), "alm_occur_dt", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_KNOW_DT"), "alm_know_dt", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_CLEAR_DT"), "alm_clear_dt", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "Alarm Category", "alm_group", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_CODE"), "alm_code", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_LEVEL"), "alm_level", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.SOUND_YN"), "sound_yn", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_MSG"), "alm_msg", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "Alarm Instance", "alm_instance", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_REMARK1"), "alm_remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.ALM_CONFIRM_USER"), "alm_confirm_user", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "Alarm Recovery", "alm_recovery", ExcelService.CellType.STRING);
        // AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.OCCUR_CNT"), "occur_cnt", ExcelService.CellType.STRING);
        // AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ALM_HIST.GRID.FIRST_OCCUR_DT"), "first_occur_dt", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setAr_search_alm_level(searchInfo.getSrch_alm_level().split(","));
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        @SuppressWarnings("unchecked") final List<AlarmHistoryInfo> currList = (List<AlarmHistoryInfo>) this.alarmHistoryService.getAlarmHistoryList(searchInfo, userInfo).getCurrList();
        for (final AlarmHistoryInfo alarmHistoryInfo : currList) {
            if ("HAPPEN".equals(alarmHistoryInfo.getAlm_status())) {
                alarmHistoryInfo.setAlm_status(AotMessageUtils.getMessage("TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.HAPPEN"));
            } else if ("TERMINATE".equals(alarmHistoryInfo.getAlm_status())) {
                alarmHistoryInfo.setAlm_status(AotMessageUtils.getMessage("TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.TERMINATE"));
            } else if ("KNOW".equals(alarmHistoryInfo.getAlm_status())) {
                alarmHistoryInfo.setAlm_status(AotMessageUtils.getMessage("TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.KNOW"));
            }
        }

        model.addAttribute(AbstractExcelXView.LIST_DATA, currList);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}