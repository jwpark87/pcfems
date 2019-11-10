/*****************************************************************************
 * 프로그램명  : CPUUsageController.java
 * 설     명  : CPU분석 보고서 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.27   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.usage;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.util.AotDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CPU분석 보고서 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class CpuUsageController {
    @Resource
    private UsageService usageService;

    // @Value("#{enviromentProperties['PATH_STCS_IMG']}")
    // private static String PATH_STCS_IMG;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * CPU분석 화면으로 이동
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/usage/cpu.pop")
    public ModelAndView popUsage(final HttpServletRequest request, final UsageSearchInfo searchInfo) throws UserSysException {
        // 검색조건의 SBC NODE IP용 콤보박스리스트를 취득한다.
        searchInfo.setUser_lang(this.LANGUAGE);
        final ModelAndView modelAndView = new ModelAndView("/stcs/usage/Cpu", "SEARCH_DATA", searchInfo);

        // SBC-IP 콤보 코드 값
        modelAndView.addObject("srchReloadList", CodeTableMng.getCodeListByGrcode(AuthorityUtil.getUserInfo(request), "RELOAD_TIME"));

        final List<CodeInfo> srchSbcNodeIpList = this.usageService.getSbcCodeInfoList(searchInfo);
        if (searchInfo.getSrch_sbc_node_ip() == null || searchInfo.getSrch_sbc_node_ip().equals("")) {
            if (srchSbcNodeIpList != null && srchSbcNodeIpList.size() > 1) {
                final CodeInfo unit = srchSbcNodeIpList.get(0);
                searchInfo.setSrch_sbc_node_ip(unit.getCode());
            }
        }
        if (srchSbcNodeIpList != null) {
            modelAndView.addObject("srchSbcNodeIpList", srchSbcNodeIpList);
        }

        if (StringUtils.isEmpty(searchInfo.getSrch_reload())) {
            searchInfo.setSrch_reload("01");
        }

        // 차트이미지 파일명 가져오기
        // List<String> chartList = FileUtil.getFileList(request.getSession().getServletContext().getRealPath(PATH_STCS_IMG),
        // ConfigMng.getValue(IPropertyKey.FILE_NAME_CPUUSAGE) + searchInfo.getSrch_sbc_node_ip() + "_");
        // if (chartList != null) {
        // Collections.sort(chartList);
        // modelAndView.addObject("chartList", chartList);
        // }

        modelAndView.addObject("now", AotDateUtils.getStringNow("yyyy-MM-dd HH:mm:ss"));
        return modelAndView;
    }

}