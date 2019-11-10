/*****************************************************************************
 * 프로그램명  : CdrController.java
 * 설     명  : 진행 중 CDR 조회 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.cdr;

import com.aot.pcfems.common.code.CodeTableMng;
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
import com.google.gson.JsonObject;
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
 * 진행 중 CDR 조회 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class CdrController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CdrService cdrService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * 진행 중 CDR 조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/cdr/ing.do")
    public String getCdr(final Model model, final HttpServletRequest request, final CdrSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        final String time = AotDateUtils.getStringNow(AotDateUtils.HHMMSS);
        searchInfo.setSrch_date_from(today + time.substring(0, 2) + "00");
        searchInfo.setSrch_date_to(today + "2355");

        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        model.addAttribute("srch24HHList", CodeTableMng.getCodeListByGrcode(userInfo, "TIME_24HH"));
        model.addAttribute("srchMMList", CodeTableMng.getCodeListByGrcode(userInfo, "TIME_MM"));
        model.addAttribute("sbcGroupList", CodeTableMng.getCodeListByGrcode(userInfo, "SBC_GROUP_ID"));
        return "/cdr/CdrIng";
    }

    /**
     * 진행 중 CDR 조회 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/cdr/ing.do", params = "actionID=ACTION_ING_LIST")
    @ResponseBody
    public JsonObject getCdrList(final HttpServletRequest request, final CdrSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.cdrService.getCdrIngList(searchInfo, AuthorityUtil.getUserInfo(request)));
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
    @RequestMapping(value = "/cdr/ing.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final CdrSearchInfo searchInfo, final CdrInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.CDR.TITLE"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.SBC_GROUP_NAME"), "sbc_group_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.H323_SETUP_TIME"), "h323_setup_time", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.H323_CONNECT_TIME"), "h323_connect_time", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.CALLING_NUMBER"), "calling_number", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.CALLED_NUMBER"), "called_number", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.FROM_COUNTRY_CD_NAME"), "from_country_cd_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.TO_COUNTRY_CD_NAME"), "to_country_cd_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACCT_SESSION_ID"), "acct_session_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.CALLING_STATION_ID"), "calling_station_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.CALLED_STATION_ID"), "called_station_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.NAS_IP_ADDRESS"), "nas_ip_address", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.NAS_PORT"), "nas_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_SESSION_INGRESS_REALM"), "acme_session_ingress_realm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_SESSION_EGRESS_REALM"), "acme_session_egress_realm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_SESSION_PROTOCOL_TYPE"), "acme_session_protocol_type", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_FLOWTYPE_FS1_F"), "acme_flowtype_fs1_f", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_LOCAL_TIME_ZONE"), "acme_local_time_zone", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_POST_DIAL_DELAY"), "acme_post_dial_delay", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_ORIGINATING_TRUNK_GROUP"), "acme_originating_trunk_group", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_TERMINATING_TRUNK_GROUP"), "acme_terminating_trunk_group", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_INGRESS_LOCAL_ADDR"), "acme_ingress_local_addr", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_INGRESS_REMOTE_ADDR"), "acme_ingress_remote_addr", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_EGRESS_LOCAL_ADDR"), "acme_egress_local_addr", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACME_EGRESS_REMOTE_ADDR"), "acme_egress_remote_addr", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.CLIENT_IP_ADDRESS"), "client_ip_address", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CDR.GRID.ACCT_UNIQUE_SESSION_ID"), "acct_unique_session_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_id_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_dt", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.cdrService.getCdrIngList(searchInfo, userInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}