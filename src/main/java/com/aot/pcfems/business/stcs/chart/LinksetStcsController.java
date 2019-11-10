/*****************************************************************************
 * 프로그램명  : LinksetStcsController.java
 * 설     명  : Linkset 별 통계 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.09   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
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
 * Linkset 별 통계 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class LinksetStcsController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChartService chartService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Linkset 별 통계 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/linksetstatistic.do")
    public String getLinksetChart(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        searchInfo.setSrch_date_from(today + "0000");
        searchInfo.setSrch_date_to(today + "2355");
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        return "/stcs/chart/Linkset";
    }

    /**
     * Linkset 별 통계 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/linksetstatistic.do", params = "actionID=ACTION_LINKSET_LIST")
    @ResponseBody
    public JsonObject getLinksetChartList(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.chartService.getLinksetStatistic(searchInfo));
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
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/stcs/linksetstatistic.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.OCCUR_DT"), "occur_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.LOCALITY"), "locality", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.LINKSET"), "linkset", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.COUNTRY_NM"), "country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.CLLI"), "clli", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN_OCCUR_CNT"), "in_occur_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN_PKT_SIZE"), "in_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN_MAP_OCCUR_CNT"),
                "in_map_occur_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.IN_MAP_PKT_SIZE"),
                "in_map_pkt_size", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT_OCCUR_CNT"),
                "out_occur_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT_PKT_SIZE"), "out_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT_MAP_OCCUR_CNT"),
                "out_map_occur_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.LINKSET.GRID.OUT_MAP_PKT_SIZE"),
                "out_pkt_size", ExcelService.CellType.INTEGER);
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
            final PagingUtil resultList = this.chartService.getLinksetStatistic(searchInfo);
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
                if ("-".equals(chartInfo.getLinkset())) {
                    chartInfo.setCountry_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCountry_nm(chartInfo.getLinkset());
                }
                if ("-".equals(chartInfo.getCountry_nm())) {
                    chartInfo.setCountry_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCountry_nm(chartInfo.getCountry_nm());
                }
                if ("-".equals(chartInfo.getCarrier_nm())) {
                    chartInfo.setCarrier_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setCarrier_nm(chartInfo.getCarrier_nm());
                }
                if ("-".equals(chartInfo.getClli())) {
                    chartInfo.setClli(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setClli(chartInfo.getClli());
                }
            }
        }
        model.addAttribute(AbstractExcelXView.LIST_DATA, list);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}