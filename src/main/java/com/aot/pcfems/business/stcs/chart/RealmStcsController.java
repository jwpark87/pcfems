/*****************************************************************************
 * 프로그램명  : RealmStcsController.java
 * 설     명  : Realm별 통계 리스트 controller-layer class.
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
 * Realm별 통계 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class RealmStcsController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChartService chartService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Realm별 통계 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/realmstatistic.do")
    public String getRealmChart(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String today = AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD);
        searchInfo.setSrch_date_from(today + "0000");
        searchInfo.setSrch_date_to(today + "2355");
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srch_input_hh_from", searchInfo.getSrch_date_from().substring(8, 10));
        model.addAttribute("srch_input_mm_from", searchInfo.getSrch_date_from().substring(10, 12));
        model.addAttribute("srch_input_hh_to", searchInfo.getSrch_date_to().substring(8, 10));
        model.addAttribute("srch_input_mm_to", searchInfo.getSrch_date_to().substring(10, 12));
        return "/stcs/chart/Realm";
    }

    /**
     * Realm별 통계 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/realmstatistic.do", params = "actionID=ACTION_REALM_LIST")
    @ResponseBody
    public JsonObject getRealmStatistic(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.chartService.getRealmStatistic(searchInfo));
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
    @RequestMapping(value = "/stcs/realmstatistic.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        final String menuName = MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo);
        model.addAttribute(AbstractExcelXView.FILE_NAME, menuName);
        model.addAttribute(AbstractExcelXView.TITLE, menuName);

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.OCCUR_DT"), "occur_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.LOCALITY"), "locality", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ORIGIN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.COUNTRY_NM"), "country_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ORIGIN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.CARRIER_NM"), "carrier_nm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ORIGIN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ROUTING_PEER"), "routing_peer",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ORIGIN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.REALM"), "realm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.DESTINATION") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.COUNTRY_NM"),
                "dst_country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.DESTINATION") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.CARRIER_NM"),
                "dst_carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.DESTINATION") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.ROUTING_PEER"),
                "dst_routing_peer", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.DESTINATION") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.REALM"), "dst_realm",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_OCCUR_CNT"), "in_occur_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_TPS"), "in_tps",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_PKT_SIZE"), "in_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_SUCC_CNT"), "in_succ_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_FAIL_CNT"), "in_fail_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_SUCC_PERCENT"), "in_succ_percent",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.IN_FAIL_PERCENT"), "in_fail_percent",
                ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_OCCUR_CNT"), "out_occur_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_TPS"), "out_tps",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_PKT_SIZE"), "out_pkt_size",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_SUCC_CNT"), "out_succ_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_FAIL_CNT"), "out_fail_cnt",
                ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_SUCC_PERCENT"),
                "out_succ_percent", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT") + " " + AotMessageUtils.getMessage("TEXT.CHART.REALM.GRID.OUT_FAIL_PERCENT"),
                "out_fail_percent", ExcelService.CellType.STRING);
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
            final PagingUtil resultList = this.chartService.getRealmStatistic(searchInfo);
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
                if ("-".equals(chartInfo.getRouting_peer())) {
                    chartInfo.setRouting_peer(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setRouting_peer(chartInfo.getRouting_peer());
                }
                if ("-".equals(chartInfo.getRealm())) {
                    chartInfo.setRealm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setRealm(chartInfo.getRealm());
                }

                if ("-".equals(chartInfo.getDst_country_nm())) {
                    chartInfo.setDst_country_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setDst_country_nm(chartInfo.getDst_country_nm());
                }
                if ("-".equals(chartInfo.getDst_carrier_nm())) {
                    chartInfo.setDst_carrier_nm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setDst_carrier_nm(chartInfo.getDst_carrier_nm());
                }
                if ("-".equals(chartInfo.getDst_routing_peer())) {
                    chartInfo.setDst_routing_peer(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setDst_routing_peer(chartInfo.getDst_routing_peer());
                }
                if ("-".equals(chartInfo.getDst_realm())) {
                    chartInfo.setDst_realm(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setDst_realm(chartInfo.getDst_realm());
                }
            }
        }
        model.addAttribute(AbstractExcelXView.LIST_DATA, list);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}