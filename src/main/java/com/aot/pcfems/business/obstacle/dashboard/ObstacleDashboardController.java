/*****************************************************************************
 * 프로그램명  : DashboardController.java
 * 설     명  : Dashboard  controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.26   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.dashboard;

import com.aot.pcfems.business.tablevo.emsmconnstatus.TableEmsmConnStatusVO;
import com.aot.pcfems.business.tablevo.emsmhwconf.TableEmsmHwConfVO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DASHBOARD controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class ObstacleDashboardController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ObstacleDashboardService obstacleDashboardService;

    @Value("#{enviromentProperties['WS_PORT']}")
    private String WS_PORT;
    @Value("#{enviromentProperties['VM_HW_NAME']}")
    private String VM_HW_NAME;

    /**
     * Dashboard 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = {"/obstacle/dashboard.do", "/obstacle/dashboard.pop"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String view(final Model model, final HttpServletRequest request, final ObstacleDashboardSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        model.addAttribute("SEARCH_DATA", searchInfo);

        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        model.addAttribute("timerList", CodeTableMng.getCodeListByGrcode(userInfo, "ALM_UPDATE_TIMER"));
        final List<CodeInfo> almLevelList = new ArrayList<>();
        for (final CodeInfo codeInfo : CodeTableMng.getCodeListByGrcode(userInfo, "ALM_LEVEL")) {
            if (!StringUtils.equals(codeInfo.getCode(), "CLEAR")) {
                almLevelList.add(codeInfo);
            }
        }
        model.addAttribute("almLevelList", almLevelList);
        model.addAttribute("VM_HW_NAME", this.VM_HW_NAME);
        model.addAttribute("wsUrl", StringUtils.replace("ws://" + request.getServerName() + ":" + this.WS_PORT, "localhost", "stkim.ddns.net"));

        return "/obstacle/dashboard/ObstacleDashboard";
    }

    // @RequestMapping(value = "/obstacle/dashboardVm.pop", method = { RequestMethod.GET, RequestMethod.POST })
    // public String viewVmPop() throws UserSysException {
    // return "/obstacle/dashboard/ObstacleDashboardVmPop";
    // }

    @RequestMapping(value = "/obstacle/dashboardGrid.pop", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewGridPop(final Model model, final HttpServletRequest request, final ObstacleDashboardSearchInfo searchInfo) throws UserSysException {
        this.view(model, request, searchInfo);
        return "/obstacle/dashboard/ObstacleDashboardGridPop";
    }

    @RequestMapping(value = "/obstacle/dashboard.do", params = "actionID=getEmsmHwConf", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TableEmsmHwConfVO> getEmsmHwConf() throws UserSysException {
        return this.obstacleDashboardService.getEmsmHwConf();
    }

    @RequestMapping(value = "/obstacle/getEmsmVmStatusBySvrId.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrId(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id) throws UserSysException {
        return this.obstacleDashboardService.getEmsmVmStatusBySvrId(svr_id);
    }

    @RequestMapping(value = "/obstacle/dashboard.do", params = "actionID=getEmsmVmStatusBySvrIdAndHwName", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrIdAndHwName(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id,
                                                                     @RequestParam(value = "hw_name") final String hw_name) throws UserSysException {
        return this.obstacleDashboardService.getEmsmVmStatusBySvrIdAndHwName(svr_id, hw_name);
    }

    @RequestMapping(value = "/obstacle/dashboard.do", params = "actionID=getResTypeList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getResTypeList(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id) throws UserSysException {
        return this.obstacleDashboardService.getResTypeList(svr_id);
    }

    @RequestMapping(value = "/obstacle/getRtgrpNodeList.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ObstacleDashboardVO> getRtgrpNodeList(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id) throws UserSysException {
        return this.obstacleDashboardService.getRtgrpNodeList(svr_id);
    }

    @RequestMapping(value = "/obstacle/getEmsmRealtimeGraphByResType.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject getEmsmRealtimeGraphByResType(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id,
                                                    @RequestParam(value = "res_type", required = false) final String res_type) throws UserSysException {
        return this.obstacleDashboardService.getEmsmRealtimeGraphByResType(svr_id, null);
    }

    @RequestMapping(value = "/obstacle/dashboardStatus.pop", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewStatusPop(final Model model, final HttpServletRequest request, final ObstacleDashboardSearchInfo searchInfo,
                                @RequestParam(value = "node_name", required = false) final String node_name, @RequestParam(value = "node_group", required = false) final String node_group,
                                @RequestParam(value = "host_name", required = false) final String host_name) throws UserSysException {
        this.view(model, request, searchInfo);
        model.addAttribute("node_name", node_name);
        model.addAttribute("node_group", node_group);
        model.addAttribute("host_name", host_name);
        if (StringUtils.equalsAny(node_name, "DRA", "CPG", "UDR")) {
            return "/obstacle/dashboard/ObstacleDashboardStatusPeerPop";
        } else {
            return "/obstacle/dashboard/ObstacleDashboardStatusPop";
        }
    }

    @RequestMapping(value = "/obstacle/dashboardStatus.pop", params = "actionID=getResTypeNGraphValueByRtgrpNode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, String>> getResTypeNGraphValueByRtgrpNode(@RequestParam(value = "node_name", required = false) final String node_name,
                                                                      @RequestParam(value = "node_group", required = false) final String node_group) throws UserSysException {
        return this.obstacleDashboardService.getResTypeNGraphValueByRtgrpNode(node_name, node_group);
    }

    @RequestMapping(value = "/obstacle/dashboardStatus.pop", params = "actionID=getEmsmVmStatusVO", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TableEmsmVmStatusVO getEmsmVmStatusVO(@RequestParam(value = "node_name", required = false) final String node_name) throws UserSysException {
        return this.obstacleDashboardService.getEmsmVmStatusVO(node_name);
    }

    @RequestMapping(value = "/obstacle/getEmsmConnStatusVOList.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TableEmsmConnStatusVO> getEmsmConnStatusVOList(@RequestParam(value = "svr_id", required = false, defaultValue = "PCF01") final String svr_id) throws UserSysException {
        return this.obstacleDashboardService.getEmsmConnStatusVOList(svr_id);
    }
}