/*****************************************************************************
 * 프로그램명  : SbcController.java
 * 설     명  : SBC설정관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.sbc;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * SBC설정 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class SbcController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SbcService sbcService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * SBC설정 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbc.do")
    public String getSbc(final Model model, final HttpServletRequest request, final SbcSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        model.addAttribute("SEARCH_DATA", searchInfo);
        model.addAttribute("srchSbcGroupList", CodeTableMng.getCodeListByGrcode(AuthorityUtil.getUserInfo(request), "SBC_GROUP_ID"));
        return "/setting/sbc/SbcList";
    }

    /**
     * SBC설정 관리 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbc.do", params = "actionID=ACTION_SBC_LIST")
    @ResponseBody
    public JsonObject getSbcList(final HttpServletRequest request, final SbcSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);

        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.sbcService.getSbcInfoList(searchInfo, AuthorityUtil.getUserInfo(request)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * SBC설정관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbcInsertPopup.do")
    public ModelAndView insertSbc(final HttpServletRequest request, final SbcSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/setting/sbc/SbcAdd");
    }

    /**
     * SBC설정관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbcInsertPopup.do", params = "actionID=ACTION_SBC_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertSbc(final HttpServletRequest request, final SbcSearchInfo searchInfo, final SbcInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.sbcService.insertSbc(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * SBC설정관리정보 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbcModPopup.do")
    public ModelAndView updateSbc(final HttpServletRequest request, final SbcSearchInfo searchInfo, final SbcInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        info.setSbc_node_name(request.getParameter("sbc_node_name").replace("@", "#"));
        info.setUser_lang(this.LANGUAGE);

        final ModelAndView modelAndView = new ModelAndView("/setting/sbc/SbcEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("SBC_DETAIL_DATA", this.sbcService.getSbcDetail(info));
        return modelAndView;
    }

    /**
     * SBC설정관리정보 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbcModPopup.do", params = "actionID=ACTION_SBC_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateSbc(final HttpServletRequest request, final SbcSearchInfo searchInfo, final SbcInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.sbcService.updateSbc(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * SBC설정관리정보 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/sbc/sbc.do", params = "actionID=ACTION_SBC_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteSbc(final HttpServletRequest request, final SbcSearchInfo searchInfo, final UserVO info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.sbcService.deleteSbc(request.getParameter("sbc_node_name"));

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * SBC Node Name 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/sbc/sbcPopup.do", params = "actionID=ACTION_SBC_NAME_CHECK")
    @ResponseBody
    public JsonObject checkSbcNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String sbc_node_name = request.getParameter("sbc_node_name");
        sbc_node_name = new String(sbc_node_name.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug("sbc_node_name :  " + sbc_node_name);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        jsonObject.addProperty("checkRet", this.sbcService.getSbcNodeNameExist(sbc_node_name));
        return jsonObject;
    }

    /**
     * SBC Node IP 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/sbc/sbcPopup.do", params = "actionID=ACTION_SBC_IP_CHECK")
    @ResponseBody
    public JsonObject checkSbcNodeIpDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String sbc_node_ip = request.getParameter("sbc_node_ip");
        sbc_node_ip = new String(sbc_node_ip.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug("sbc_node_ip :  " + sbc_node_ip);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        jsonObject.addProperty("checkRet", this.sbcService.getSbcNodeIpExist(sbc_node_ip));
        return jsonObject;
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
    @RequestMapping(value = "/setting/sbc/sbc.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final SbcSearchInfo searchInfo, final SbcInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.SBC.MGMT.TITLE.SBC_MGMT"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.SBC_ID"), "sbc_node_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.SBC_NAME"), "sbc_node_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.SBC_GROUP"), "sbc_group_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.AUTH_LEVEL"), "sbc_group_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.TEL"), "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SBC.MGMT.GRID.EMAIL"), "upd_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_id_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_dt", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.sbcService.getSbcInfoList(searchInfo, userInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}