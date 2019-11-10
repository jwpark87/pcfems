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
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class CopyOfCallController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChartService chartService;

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
    @RequestMapping(value = "/stcs/chart/call.do", params = "actionID=ACTION_GET_EXCEL4")
    public String goExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.CHART.TITLE"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.OCCUR_DT"), "occur_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.FROM_COUNTRY_CD_NAME"), "from_country_cd_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.TO_COUNTRY_CD_NAME"), "to_country_cd_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_SESSION_INGRESS_REALM"), "ingress_realm_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_SESSION_EGRESS_REALM"), "egress_realm_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.TRY_CALL_CNT"), "try_call_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.CONN_CALL_CNT"), "conn_call_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.COMP_CALL_CNT"), "comp_call_cnt", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.PERCENT_COMP_CALL"), "percent_comp_call", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_CALLING_PACKETS_FS1"), "acme_calling_packets_fs1", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_CALLING_OCTETS_FS1"), "acme_calling_octets_fs1", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_CALLED_PACKETS_FS1"), "acme_called_packets_fs1", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.CHART.GRID.ACME_CALLED_OCTETS_FS1"), "acme_called_octets_fs1", ExcelService.CellType.INTEGER);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        final String data_excel = searchInfo.getStcs_excel_data();// request.getParameter("stcs_excel_data");
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
            final PagingUtil resultList = this.chartService.getChartCallProcessList(searchInfo);
            if (resultList != null) {
                list = (List<ChartInfo>) resultList.getCurrList();
            }
        }
        if (list != null) {
            for (final ChartInfo chartInfo : list) {
                if ("-".equals(chartInfo.getFrom_country_cd_name())) {
                    chartInfo.setFrom_country_cd_name(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setFrom_country_cd_name(chartInfo.getFrom_country_cd_name());
                }
                if ("-".equals(chartInfo.getTo_country_cd_name())) {
                    chartInfo.setTo_country_cd_name(AotMessageUtils.getMessage("TEXT.COMM.SEL.ALL"));
                } else {
                    chartInfo.setTo_country_cd_name(chartInfo.getTo_country_cd_name());
                }
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
        }
        model.addAttribute(AbstractExcelXView.LIST_DATA, list);

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}