/*****************************************************************************
 * 프로그램명  : GtController.java
 * 설     명  : Gt별 통계 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.09   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
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
 * Gt별 통계 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class GtByController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChartService chartService;

    /**
     * Gt별 통계 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/gtstatistic.do")
    public String getGtChart(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        searchInfo.setSrch_date_from(today + "0000");
        searchInfo.setSrch_date_to(today + "2355");
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        final List<CodeInfo> pc_list_nm_ls = new ArrayList<>();
        final CodeInfo pc_list_cd = new CodeInfo();
        pc_list_cd.setCode("Y");
        pc_list_cd.setCodenm_k("PC List Name별");
        pc_list_nm_ls.add(pc_list_cd);
        model.addAttribute("pc_list_nm_ls", pc_list_nm_ls);
        return "/stcs/chart/Gt";
    }

    /**
     * Gt별 통계 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/gtstatistic.do", params = "actionID=ACTION_GT_LIST")
    @ResponseBody
    public JsonObject getGtChartList(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return JqGridUtil.getJsonData(searchInfo, this.chartService.getGtStatistic(searchInfo));
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
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/stcs/gtstatistic.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OCCUR_DT"), "occur_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.LOCALITY"), "locality", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG.GT"), "cg_gt",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG.PC_LIST_NM"), "cg_pc_list_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CG.CARRIER_NM"), "cg_carrier_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD.GT"), "cd_gt",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD.PC_LIST_NM"), "cd_pc_list_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.CD.CARRIER_NM"), "cd_carrier_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN.IN_OCCUR_CNT"), "in_occur_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN.IN_SUCC_CNT"), "in_succ_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN.IN_PKT_SIZE"), "in_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.IN.IN_SUCC_PRECENT"), "in_succ_percent",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT.OUT_OCCUR_CNT"), "out_occur_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT.OUT_SUCC_CNT"), "out_succ_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT.OUT_SUCC_PRECENT"), "out_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.GRID.GT.OUT.OUT_PKT_SIZE"), "out_succ_percent",
                ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        final String data_excel = searchInfo.getStcs_excel_data();
        List<ChartInfo> participantJsonList = null;
        List<ChartInfo> list = null;
        if (StringUtils.isNotEmpty(data_excel)) {
            participantJsonList = AotMapperUtils.writeObjectAsArrayList(data_excel, ChartInfo.class);
        }
        if (participantJsonList != null && participantJsonList.size() > 0) {
            list = participantJsonList;
        } else {
            searchInfo.setUser_lang(userInfo.getUser_lang());
            searchInfo.setSearch_excel_type("EXCEL");
            searchInfo.setJqSidx(request.getParameter("srchSidx"));
            searchInfo.setJqSord(request.getParameter("srchSord"));
            final PagingUtil resultList = this.chartService.getGtStatistic(searchInfo);
            if (resultList != null) {
                list = (List<ChartInfo>) resultList.getCurrList();
            }
        }
        if (list != null) {
            for (final ChartInfo chartInfo : list) {
                if ("-".equals(chartInfo.getLocality())) {
                    chartInfo.setLocality(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setLocality(chartInfo.getLocality());
                }
                if ("-".equals(chartInfo.getCg_gt())) {
                    chartInfo.setCg_gt(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCg_gt(chartInfo.getCg_gt());
                }
                if ("-".equals(chartInfo.getCg_pc_list_nm())) {
                    chartInfo.setCg_pc_list_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCg_pc_list_nm(chartInfo.getCg_pc_list_nm());
                }
                if ("-".equals(chartInfo.getCg_carrier_nm())) {
                    chartInfo.setCg_carrier_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCg_carrier_nm(chartInfo.getCg_carrier_nm());
                }
                if ("-".equals(chartInfo.getCd_gt())) {
                    chartInfo.setCd_gt(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCd_gt(chartInfo.getCd_gt());
                }
                if ("-".equals(chartInfo.getCd_pc_list_nm())) {
                    chartInfo.setCd_pc_list_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCd_pc_list_nm(chartInfo.getCd_pc_list_nm());
                }
                if ("-".equals(chartInfo.getCd_carrier_nm())) {
                    chartInfo.setCd_carrier_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCd_carrier_nm(chartInfo.getCd_carrier_nm());
                }
            }
        }
        model.addAttribute(AbstractExcelXView.LIST_DATA, list);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}