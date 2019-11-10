/*****************************************************************************
 * 프로그램명  : MEMUsageController.java
 * 설     명  : MEM분석 보고서 컨트롤러
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
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotSessionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * MEM분석 보고서 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class MemUsageController {
    @Resource
    private UsageService usageService;

    // @Autowired
    // private MessageSourceAccessor messageSourceAccessor;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * MEM분석 화면으로 이동
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/usage/mem.pop")
    public String popUsage(final Model model, final HttpServletRequest request, final UsageSearchInfo searchInfo) throws UserSysException {
        // 검색조건의 SBC NODE IP용 콤보박스리스트를 취득한다.
        searchInfo.setUser_lang(this.LANGUAGE);

        // SBC-IP 콤보 코드 값
        final List<CodeInfo> srchSbcNodeIpList = this.usageService.getSbcCodeInfoList(searchInfo);
        // 검색정보 취득
        if (searchInfo.getSrch_sbc_node_ip() == null || searchInfo.getSrch_sbc_node_ip().equals("")) {
            if (srchSbcNodeIpList != null && srchSbcNodeIpList.size() > 1) {
                final CodeInfo unit = srchSbcNodeIpList.get(0);
                searchInfo.setSrch_sbc_node_ip(unit.getCode());
            }
        }
        if (searchInfo.getSrch_reload() == null || searchInfo.getSrch_reload().equals("")) {
            searchInfo.setSrch_reload("01");
        }

        // 차트이미지 파일명 가져오기
        // List<String> chartList = FileUtil.getFileList(request.getSession().getServletContext().getRealPath(PATH_STCS_IMG),
        // ConfigMng.getValue(IPropertyKey.FILE_NAME_MEMUSAGE) + searchInfo.getSrch_sbc_node_ip() + "_");
        // if (chartList != null) {
        // Collections.sort(chartList);
        // model.addAttribute("chartList", chartList);
        // }
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("SBC_IP_LIST", srchSbcNodeIpList);
        model.addAttribute("srchReloadList", CodeTableMng.getCodeListByGrcode(AotSessionUtils.getUserVO(request.getSession()), "RELOAD_TIME"));
        model.addAttribute("now", AotDateUtils.getStringNow("yyyy-MM-dd HH:mm:ss"));
        return "/stcs/usage/Mem";
    }

}