package com.aot.pcfems.business.stcs.threshold;

import com.aot.pcfems.business.stcs.format.StcsFormatVO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotSessionUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StcsThresholdController {
    @Autowired
    private StcsThresholdService stcsThresholdService;

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(final Model model, final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.doJqGridBind(request);
        model.addAttribute("SEARCH_DATA", stcsFormatVO);
        return "/stcs/threshold/stcsThreshold";
    }

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", params = "actionID=getStcsThresholdList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject getStcsThresholdList(final HttpServletRequest request, final StcsThresholdVO stcsThresholdVO) {
        stcsThresholdVO.doJqGridBind(request);
        return this.stcsThresholdService.getStcsThresholdList(stcsThresholdVO);
    }

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", params = "actionID=stcsThresholdModal", method = {RequestMethod.GET, RequestMethod.POST})
    public String stcsFormatAddMstModal(final Model model, final HttpServletRequest request, final StcsThresholdVO stcsThresholdVO) {
        return "/stcs/threshold/stcsThresholdModal";
    }

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", params = "actionID=insertStcsThreshold", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject insertStcsThreshold(final HttpServletRequest request, final TableEmsmVmStatusVO tableEmsmVmStatusVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        tableEmsmVmStatusVO.setCrt_id(userVO.getUser_id());
        tableEmsmVmStatusVO.setUpd_id(userVO.getUser_id());
        this.stcsThresholdService.insertEmsmVmStatus(tableEmsmVmStatusVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", params = "actionID=updateStcsThreshold", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject updateStcsThreshold(final HttpServletRequest request, final TableEmsmVmStatusVO tableEmsmVmStatusVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        tableEmsmVmStatusVO.setUpd_id(userVO.getUser_id());
        this.stcsThresholdService.updateEmsmVmStatus(tableEmsmVmStatusVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/threshold/stcsThreshold.do", params = "actionID=deleteStcsThreshold", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject deleteStcsThreshold(final HttpServletRequest request, final TableEmsmVmStatusVO tableEmsmVmStatusVO) {
        this.stcsThresholdService.deleteEmsmVmStatus(tableEmsmVmStatusVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }
}
