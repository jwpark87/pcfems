package com.aot.pcfems.business.admin.audit;
/*****************************************************************************
 * 프로그램명  : AuditLogController.java
 * 설     명  : Audit Log 조회 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.31   KYM     1.0     초기작성
 *****************************************************************************/

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.eact.web.lib.utils.AuditLogUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Audit Log 조회 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class AuditLogController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AuditLogService auditLogService;

    /**
     * Audit Log 조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/audit/log.do")
    public String getAuditLog(final Model model, final HttpServletRequest request, final AuditLogSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        searchInfo.setSrch_date_from(today + "0000");
        searchInfo.setSrch_date_to(today + "2355");

        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        return "/admin/audit/AuditLog";
    }

    /**
     * Audit Log 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/audit/log.do", params = "actionID=ACTION_AUDIT_LOG_LIST")
    @ResponseBody
    public JsonObject getAuditLogList(final HttpServletRequest request, final AuditLogSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return JqGridUtil.getJsonData(searchInfo, this.auditLogService.getAuditLogList(searchInfo));
    }

    /**
     * Audit Log 팝업 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/audit/log.pop", params = "actionID=ACTION_AUDIT_LOG_DET_POP")
    public ModelAndView getAuditLogPop(final HttpServletRequest request, final AuditLogSearchInfo searchInfo, final AuditLogInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/admin/audit/AuditLogPop", "SEARCH_DATA", info);
    }

    /**
     * Audit Log 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/audit/log.pop", params = "actionID=ACTION_AUDIT_LOG_DET_POP_LIST")
    @ResponseBody
    public JsonObject getAuditLogPopList(final HttpServletRequest request, final AuditLogSearchInfo searchInfo, final AuditLogInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        long totalRows = 0;

        final String resultJArray = AuditLogUtils.auditLogToJSONArray(info.getAudit_before_data(), info.getAudit_after_data());
        this.logger.debug("resultJArray.toString()::: " + resultJArray);

        final List<JsonElement> list = new ArrayList<>();
        if (resultJArray == null || "".equals(resultJArray)) {
            this.logger.debug("executeSearch failed.");
        } else {
            final JsonArray arrayResult = AotMapperUtils.writeObjectAsJsonArray(resultJArray);
            totalRows = arrayResult.size();
            for (final JsonElement json : arrayResult) {
                list.add(json);
            }
        }
        return JqGridUtil.getJsonData(searchInfo, list, totalRows);
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
    @RequestMapping(value = "/admin/audit/log.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final AuditLogSearchInfo searchInfo) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        searchInfo.doJqGridBind(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.TITLE"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.CRT_DT"), "crt_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_SUBJECT"), "audit_subject", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_DESC"), "audit_desc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_BEFORE_DATA"), "audit_before_data", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_AFTER_DATA"), "audit_after_data", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.ADMIN.AUDIT_LOG.GRID.CRT_NM"), "crt_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.auditLogService.getAuditLogList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}