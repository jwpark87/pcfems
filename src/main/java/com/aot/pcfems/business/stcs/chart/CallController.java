/*****************************************************************************
 * 프로그램명  : ChartController.java
 * 설     명  : 호처리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.chart;

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
 * 호처리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class CallController {
    @Resource
    private ChartService chartService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * 호처리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/chart/call.do")
    public String getChart(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        searchInfo.setSrch_date_from(today + "0000");
        searchInfo.setSrch_date_to(today + "2355");

        // 검색조건의 SBC NODE IP용 콤보박스리스트를 취득한다.
        searchInfo.setUser_lang(this.LANGUAGE);
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        model.addAttribute("REALM_LIST", this.chartService.getRealmCodeList(searchInfo));
        return "/stcs/chart/Call";
    }

    /**
     * 호처리 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/chart/call.do", params = "actionID=ACTION_CALL_LIST")
    @ResponseBody
    public JsonObject getChartList(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.chartService.getChartCallProcessList(searchInfo));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * 호처리 차트 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/chart/call.do", params = "actionID=ACTION_CALL_GRAPH")
    @ResponseBody
    public List<List<List<Object>>> getCallGraph(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        // AotMapperUtils.writeObjectAsJsonArray(listGraph).toString();
        return this.chartService.getCallGraph(searchInfo);
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
    @RequestMapping(value = "/stcs/chart/call.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.CHART.CALL.TITLE"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.OCCUR_DT"), "occur_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_SESSION_INGRESS_REALM"), "ingress_realm_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_SESSION_EGRESS_REALM"), "egress_realm_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.TRY_CALL_CNT"), "try_call_cnt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.CONN_CALL_CNT"), "conn_call_cnt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.COMP_CALL_CNT"), "comp_call_cnt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.PERCENT_COMP_CALL"), "percent_comp_call", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));

        @SuppressWarnings("unchecked") final List<ChartInfo> list = (List<ChartInfo>) this.chartService.getChartCallProcessList(searchInfo).getCurrList();
        for (final ChartInfo chartInfo : list) {
            if ("-".equals(chartInfo.getIngress_realm_name())) {
                chartInfo.setIngress_realm_name(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
            } else {
                chartInfo.setIngress_realm_name(chartInfo.getIngress_realm_name());
            }
            if ("-".equals(chartInfo.getEgress_realm_name())) {
                chartInfo.setEgress_realm_name(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
            } else {
                chartInfo.setEgress_realm_name(chartInfo.getEgress_realm_name());
            }
        }
        model.addAttribute(AbstractExcelXView.LIST_DATA, list);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}