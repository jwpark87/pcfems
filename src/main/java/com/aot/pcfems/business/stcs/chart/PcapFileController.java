/*****************************************************************************
 * 프로그램명  : PcapFileController.java
 * 설     명  : Pcap 파일 다운로드 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.06   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractDownloadView;
import com.google.gson.JsonObject;
import org.apache.commons.io.comparator.NameFileComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Host별 통계 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class PcapFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChartService chartService;

    @Value("#{enviromentProperties['PCAP_FILE_ROOT']}")
    private String PCAP_FILE_ROOT;

    public static String getReadableSize(final long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        final int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        final NumberFormat df = new DecimalFormat("#,##0");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
        /*
         * return new DecimalFormat("#,##0").setRoundingMode(RoundingMode.CEILING).format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
         */
    }

    /**
     * Pcap 파일 다운로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/pcapfile.do")
    public ModelAndView getChart(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/stcs/chart/Pcap", "SEARCH_DATA", searchInfo);
    }

    /**
     * Pcap 파일 다운로드 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/pcapfile.do", params = "actionID=ACTION_PCAP_LIST")
    @ResponseBody
    public JsonObject getChartList(final HttpServletRequest request, final ChartSearchInfo searchInfo) throws UserSysException, IOException, DirectoryIteratorException {
        searchInfo.doJqGridBind(request);

        final HttpSession session = request.getSession();
        final String path = this.PCAP_FILE_ROOT;
        final String destDir = session.getServletContext().getRealPath(path);

        String parentFolder = searchInfo.getSrch_pcap_file_parent();
        final String strFolder = searchInfo.getSrch_pcap_file_folder();

        this.logger.debug("param-parent::::" + parentFolder);
        this.logger.debug("param-folder::::" + strFolder);

        String dirUrl = destDir.replace("\\", "/");

        // 이전
        if (!"".equals(parentFolder) && "".equals(strFolder)) {
            if (parentFolder.contains(path)) {
                parentFolder = parentFolder.replace(path, "");
            }
            dirUrl = dirUrl + "/" + parentFolder;
            // 폴더 안으로 접근시
        } else if (!"".equals(parentFolder) && !"".equals(strFolder)) {
            if (parentFolder.contains(path)) {
                parentFolder = parentFolder.replace(path, "");
            }
            dirUrl = dirUrl + "/" + parentFolder + "/" + strFolder;
            // 제일 처음 폴더 경로 클릭시
        } else if ("".equals(parentFolder) && !"".equals(strFolder)) {
            dirUrl = dirUrl + "/" + strFolder;
        }

        final File directory = new File(dirUrl);
        // get all the files from a directory
        final File[] fList = directory.listFiles();
        String file_parent_all = "";
        final List<ChartInfo> pcapFileList = new ArrayList<>();
        if (fList != null && fList.length > 0) {
            // desc 정렬
            // http://www.avajava.com/tutorials/lessons/how-do-i-sort-an-array-of-files-according-to-their-names.html
            Arrays.sort(fList, NameFileComparator.NAME_INSENSITIVE_REVERSE);

            for (final File file : fList) {

                /*
                 * logger.debug("click~" + file.getName()); logger.debug("click~parent~" +file.getParentFile().getName());
                 */

                final ChartInfo chart = new ChartInfo();
                String tmpParent = "";
                if (!"".equals(parentFolder) && "".equals(strFolder)) {
                    tmpParent = parentFolder;
                } else if (!"".equals(parentFolder) && !"".equals(strFolder)) {
                    if (parentFolder.contains(path)) {
                        parentFolder = parentFolder.replace(path, "");
                    }
                    tmpParent = parentFolder + "/" + file.getParentFile().getName();
                } else if ("".equals(parentFolder) && !"".equals(strFolder)) {
                    tmpParent = file.getParentFile().getName();
                }
                // 부모경로 붙이기
                chart.setPcap_file_parent(tmpParent);

                chart.setPcap_file_name(file.getName());
                chart.setPcap_file_view(file.getName());
                if (file.isFile()) {
                    chart.setPcap_file_type("F");
                    // chart.setPcap_file_size(Long.toString(file.length()));
                    chart.setPcap_file_size(getReadableSize(file.length()));
                } else if (file.isDirectory()) {
                    chart.setPcap_file_type("D");
                    chart.setPcap_file_size("");
                }
                file_parent_all = chart.getPcap_file_parent();
                pcapFileList.add(chart);
            }
            // 파일유형 ASC 정렬
            if (pcapFileList != null) {
                Collections.sort(pcapFileList, ChartInfo.ChartTypeComparator);
            }
        } else {
            // 디렉토리에 접근 했는데 파일 없을때 상위로 가기 만들기
            if (!"".equals(parentFolder) && !"".equals(strFolder)) {
                final Path paths = Paths.get(dirUrl);
                /* String[] absolute_route = paths.getParent().toString().split("war\\\\"); */
                /* 리눅스 "/" 윈도우 "\" */
                final ChartInfo chartParent = new ChartInfo();
                if (paths != null) {
                    final Path parent = paths.getParent();
                    if (parent != null) {
                        chartParent.setPcap_file_parent(parent.toString().replace("\\", "/").split("war/")[1]);
                    }
                }
                chartParent.setPcap_file_name("");
                chartParent.setPcap_file_view(AotMessageUtils.getMessage("TEXT.CHART.GRID.PCAP.FILE_TYPE.TEXT.PARENT_DIRECTORY"));
                chartParent.setPcap_file_type("P");
                chartParent.setPcap_file_size("");
                pcapFileList.add(0, chartParent);
            }
        }

        // 상단에 이전 폴더 이동 로우 추가
        if (!"".equals(file_parent_all) && !path.equals(file_parent_all)) {
            final Path paths = Paths.get(dirUrl);
            final ChartInfo chartParent = new ChartInfo();
            if (paths != null) {
                final Path parent = paths.getParent();
                if (parent != null) {
                    chartParent.setPcap_file_parent(parent.toString().replace("\\", "/").split("war/")[1]);
                }
            }
            chartParent.setPcap_file_name("");
            chartParent.setPcap_file_view(AotMessageUtils.getMessage("TEXT.CHART.GRID.PCAP.FILE_TYPE.TEXT.PARENT_DIRECTORY"));
            chartParent.setPcap_file_type("P");
            chartParent.setPcap_file_size("");
            pcapFileList.add(0, chartParent);
        }

        return JqGridUtil.getJsonData(searchInfo, pcapFileList);
    }

    /**
     * CDR 조회 엑셀 다운로드
     *
     * @param request  Request객체
     * @param response Response객체
     * @return
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/pcapfile.do", params = "actionID=ACTION_PCAP_FILE_DOWN")
    public String getPcapExcelDown(final Model model, final HttpServletRequest request, final ChartSearchInfo searchInfo, final ChartInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final StringBuffer sbFileName = new StringBuffer(request.getServletContext().getRealPath(this.PCAP_FILE_ROOT));
        sbFileName.append(System.getProperty("file.separator"));
        sbFileName.append(searchInfo.getSrch_pcap_file_parent());
        sbFileName.append(System.getProperty("file.separator"));
        sbFileName.append(searchInfo.getSrch_pcap_file());

        model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, new File(sbFileName.toString()));
        model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, searchInfo.getSrch_pcap_file());
        return AbstractDownloadView.VIEW_NAME;
    }

}
