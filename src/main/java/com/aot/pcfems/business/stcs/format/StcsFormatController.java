package com.aot.pcfems.business.stcs.format;

import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotSessionUtils;
import com.aot.standard.common.valuelabel.ValueLabelVO;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StcsFormatController {

    @Autowired
    private StcsFormatService stcsFormatService;

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(final Model model, final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.doJqGridBind(request);
        model.addAttribute("SEARCH_DATA", stcsFormatVO);
        return "/stcs/format/stcsFormatList";
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=getSrchFormatMstList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject getSrchFormatMstList(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.doJqGridBind(request);
        return this.stcsFormatService.getSrchFormatMstList(stcsFormatVO);
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=getSrchFormatDetList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject getSrchFormatDetList(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.doJqGridBind(request);
        return this.stcsFormatService.getSrchFormatDetList(stcsFormatVO);
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=mstModal", method = {RequestMethod.GET, RequestMethod.POST})
    public String stcsFormatAddMstModal(final Model model, final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        return "/stcs/format/stcsFormatMstModal";
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=detModal", method = {RequestMethod.GET, RequestMethod.POST})
    public String stcsFormatAddDetModal(final Model model, final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        return "/stcs/format/stcsFormatDetModal";
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=insertSrchFormatMst", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject insertSrchFormatMst(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        stcsFormatVO.setCrt_id(userVO.getUser_id());
        stcsFormatVO.setUpd_id(userVO.getUser_id());
        this.stcsFormatService.insertSrchFormatMst(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=updateSrchFormatMst", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject updateSrchFormatMst(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        stcsFormatVO.setUpd_id(userVO.getUser_id());
        this.stcsFormatService.updateSrchFormatMst(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=deleteSrchFormatMst", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject deleteSrchFormatMst(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        this.stcsFormatService.deleteSrchFormatMst(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=insertSrchFormatDet", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject insertSrchFormatDet(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        stcsFormatVO.setCrt_id(userVO.getUser_id());
        stcsFormatVO.setUpd_id(userVO.getUser_id());
        this.stcsFormatService.insertSrchFormatDet(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=updateSrchFormatDet", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject updateSrchFormatDet(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        final UserVO userVO = AotSessionUtils.getUserVO(request.getSession());
        stcsFormatVO.setUpd_id(userVO.getUser_id());
        this.stcsFormatService.updateSrchFormatDet(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=deleteSrchFormatDet", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonObject deleteSrchFormatDet(final HttpServletRequest request, final StcsFormatVO stcsFormatVO) {
        this.stcsFormatService.deleteSrchFormatDet(stcsFormatVO);
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    @RequestMapping(value = "/stcs/format/stcsFormatList.do", params = "actionID=getGrcodeValueLabelList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ValueLabelVO> getGrcodeValueLabelList() {
        return this.stcsFormatService.getGrcodeValueLabelList();
    }
}
