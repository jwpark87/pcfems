/*****************************************************************************
 * 프로그램명  : EmpController.java
 * 설     명  : 사용자관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2015.05.08  WYA    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.emp;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.common.util.AotSessionUtils;
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
 * 사용자 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class EmpController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EmpService empService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * 사용자 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/emp.do")
    public String getEmp(final Model model, final HttpServletRequest request, final EmpSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        model.addAttribute("srchGubunList", CodeTableMng.getCodeListByGrcode(AuthorityUtil.getUserInfo(request), "USER_SRCH_GUBUN"));
        model.addAttribute("SEARCH_DATA", searchInfo);
        return "/admin/emp/EmpList";
    }

    /**
     * 사용자 관리 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/emp.do", params = "actionID=ACTION_EMP_LIST")
    @ResponseBody
    public JsonObject getEmpList(final HttpServletRequest request, final EmpSearchInfo searchInfo) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        searchInfo.doJqGridBind(request);

        searchInfo.setSessionLoginId(userInfo.getUser_id());
        searchInfo.setUser_group_id(userInfo.getGroupId());
        searchInfo.setUser_lang(userInfo.getUser_lang());

        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.empService.getUserInfoList(searchInfo, userInfo));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * 사용자관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/empPopup.do")
    public ModelAndView insertEmp(final HttpServletRequest request, final EmpSearchInfo searchInfo, final UserVO info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/admin/emp/EmpAdd", "SEARCH_DATA", searchInfo);
    }

    @RequestMapping(value = "/admin/emp/empPopup.do", params = "actionID=getUserGroupCodeInfo")
    @ResponseBody
    public List<CodeInfo> getUserGroupCodeInfo(final HttpServletRequest request) throws UserSysException {
        return this.empService.getUserGroupCodeInfo(new EmpSearchInfo(), AotSessionUtils.getUserVO(request.getSession()));
    }

    /**
     * 사용자관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/empPopup.do", params = "actionID=ACTION_EMP_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertEmp(final HttpServletRequest request, final EmpSearchInfo searchInfo, final EmpInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_user(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.empService.insertEmp(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 사용자관리정보 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/empModPopup.do")
    public ModelAndView updateEmp(final HttpServletRequest request, final EmpSearchInfo searchInfo, final EmpInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final ModelAndView modelAndView = new ModelAndView("/admin/emp/EmpEdit", "SEARCH_DATA", searchInfo);
        info.setEmp_id(request.getParameter("emp_id"));
        info.setUser_lang(this.LANGUAGE);
        modelAndView.addObject("EMP_DETAIL_DATA", this.empService.getEmpDetail(info));
        return modelAndView;
    }

    /**
     * 사용자관리정보 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/empModPopup.do", params = "actionID=ACTION_EMP_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateEmp(final HttpServletRequest request, final EmpSearchInfo searchInfo, final EmpInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_user(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.empService.updateEmp(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 사용자관리정보 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/emp/emp.do", params = "actionID=ACTION_EMP_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteEmp(final HttpServletRequest request, final EmpSearchInfo searchInfo, final UserVO info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.empService.deleteEmp(request.getParameter("emp_id"));

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 사용자아이디 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/admin/emp/empPopup.do", params = "actionID=ACTION_CHECK_USERID")
    @ResponseBody
    public JsonObject dupEmp(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String userId = request.getParameter("id");
        userId = new String(userId.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding  :    " + request.getCharacterEncoding());
        this.logger.debug("userId   :  " + userId);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        final String checkId = this.empService.getUserInfoExist(userId);

        jsonObject.addProperty("checkId", checkId);
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
    @RequestMapping(value = "/admin/emp/emp.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final EmpSearchInfo searchInfo, final EmpInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.EMP.MGMT.TITLE.EMP_MGMT"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.USER_ID"), "emp_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.USER_NAME"), "empNm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.EMP_GROUP"), "user_group_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.AUTH_LEVEL"), "levelCodNm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.TEL"), "smsPhone", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.EMP.MGMT.GRID.EMAIL"), "email", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setSessionLoginId(userInfo.getUser_id());
        searchInfo.setUser_group_id(userInfo.getGroupId());
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.empService.getUserInfoList(searchInfo, userInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}