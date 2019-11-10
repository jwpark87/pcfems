/*****************************************************************************
 * 프로그램명  : EmpGroupController.java
 * 설     명  : 사원 관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.12.24  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.empgroup;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserGroupInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 사원 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class EmpGroupController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmpGroupService empGroupService;

    /**
     * 사용자 그룹 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroup.do")
    public ModelAndView goEmpGroup(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final ModelAndView modelAndView = new ModelAndView("/admin/empgroup/EmpGroupList", "SEARCH_DATA", searchInfo);
        return modelAndView;
    }

    /**
     * 사용자 그룹 List
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroup.do", params = "actionID=ACTION_LIST")
    @ResponseBody
    public JsonObject goEmpGroupList(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        searchInfo.setSessionLoginId(userInfo.getUser_id());
        searchInfo.setUser_group_id(userInfo.getGroupId());
        searchInfo.setUser_lang(userInfo.getUser_lang());

        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.empGroupService.getUserGroupInfoList(searchInfo));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * 사용자 그룹 등록 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroupPopup.do")
    public ModelAndView goEmpGroupAdd(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        this.getAuthLevelList(request, userInfo);
        final ModelAndView modelAndView = new ModelAndView("/admin/empgroup/EmpGroupAdd", "SEARCH_DATA", searchInfo);
        return modelAndView;
    }

    /**
     * 사용자 그룹 등록 팝업 등록 처리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroupPopup.do", params = "actionID=ACTION_INSERT_OK")
    @ResponseBody
    public JsonObject doEmpGroupAdd(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        groupInfo.setCrtUser(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.empGroupService.insertUserGroupInfo(groupInfo);
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
     * 사용자 그룹 수정 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroupModPopup.do")
    public ModelAndView goEmpGroupMod(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        this.getAuthLevelList(request, userInfo);

        final String groupId = request.getParameter("groupId");
        searchInfo.setUser_group_id(groupId);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        final UserGroupInfo userGroupInfo = this.empGroupService.getUserGroupInfo(searchInfo);

        final ModelAndView modelAndView = new ModelAndView("/admin/empgroup/EmpGroupEdit", "EMPGROUP_INFO_DATA", userGroupInfo);
        return modelAndView;
    }

    /**
     * 사용자 그룹 등록 팝업 등록 처리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroupModPopup.do", params = "actionID=ACTION_UPDATE_OK")
    @ResponseBody
    public JsonObject doEmpGroupMod(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        groupInfo.setCrtUser(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.empGroupService.updateUserGroupInfo(groupInfo);
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
     * 사용자 그룹 삭제 처리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws ProcessResult    비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/empgroup/empgroup.do", params = "actionID=ACTION_DELETE_OK")
    @ResponseBody
    public JsonObject doEmpGroupDel(final HttpServletRequest request, final EmpGroupSearchInfo searchInfo, final UserGroupInfo groupInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        groupInfo.setCrtUser(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");
        try {
            result = this.empGroupService.deleteUserGroupInfo(request.getParameter("groupId"));
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
     * 업무별 처리 메인메소드
     *
     * @param request
     *            Request객체
     * @param response
     *            Response객체
     * @exception ProcessResult
     *                비즈니스예외
     * @exception UserSysException
     *                시스템예외
     */
    /*
     * @RequestMapping(value="/admin/empgroup/empgroup.do") public ModelAndView goController(HttpServletRequest request, HttpServletResponse response, EmpGroupSearchInfo searchInfo, UserGroupInfo
     * groupInfo) throws UserSysException { ModelAndView modelAndView = null;
     *
     * String actionID = StringUtils.defaultString(request.getParameter("actionID") ,"");
     *
     * HttpSession session = request.getSession(); User uInfo = (User)session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);
     *
     * String cPage = StringUtils.defaultString(request.getParameter("currPage"), "1"); int nPage = Integer.parseInt(cPage); if (nPage < 1) { nPage = 1; }
     *
     * getUserGroupCodeInfo(request, uInfo, searchInfo); getAuthLevelList(request, uInfo);
     *
     * groupInfo.setCrtUser(uInfo.getUser_id()); groupInfo.setUpdtUser(uInfo.getUser_id());
     *
     * //리스트 표시처리 if("".equals(actionID) || "ACTION_LIST".equals(actionID)) { //리스트 데이터를 취득 (리스트가 0건인 경우 null로 설정된다) modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo); //신규등록화면으로 이동 } else
     * if ("ACTION_INSERT".equals(actionID)) { request.setAttribute("EMPGROUP_INFO_DATA", groupInfo); //입력화면으로 이동; modelAndView = new ModelAndView("/admin/empgroup/EmpGroupAdd"); //신규등록처리 } else if
     * ("ACTION_INSERT_OK".equals(actionID)) { request.setAttribute("EMPGROUP_INFO_DATA", groupInfo);
     *
     * //게시물 신규등록처리수행 try{ ProcessResult result = empGroupService.insertUserGroupInfo(groupInfo); request.setAttribute("PROCESS_RESULT", result); }catch(BizException be){
     * request.setAttribute("EX_ERR_OBJ", be); }
     *
     * //등록완료후 게시판 리스트 화면으로 이동 modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo);
     *
     * //갱신화면으로 이동 } else if ("ACTION_UPDATE".equals(actionID)) { String groupId = request.getParameter("groupId"); searchInfo.setUser_group_id(groupId); searchInfo.setUser_lang(uInfo.getUser_lang());
     * UserGroupInfo userGroupInfo = empGroupService.getUserGroupInfo(searchInfo);
     *
     * //처리후 이동할 화면을 지정 modelAndView = new ModelAndView("/admin/empgroup/EmpGroupEdit", "EMPGROUP_INFO_DATA" , userGroupInfo); //갱신처리 } else if ("ACTION_UPDATE_OK".equals(actionID)) {
     * request.setAttribute("EMPGROUP_INFO_DATA", groupInfo);
     *
     * //게시물 갱신처리를 수행 try{ ProcessResult result = empGroupService.updateUserGroupInfo(groupInfo); request.setAttribute("PROCESS_RESULT", result); }catch(BizException be){
     * request.setAttribute("EX_ERR_OBJ", be); }
     *
     * //수정완료후 게시판 리스트 화면으로 이동 modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo); //삭제처리 } else if ("ACTION_DELETE_OK".equals(actionID)) { request.setAttribute("EMPGROUP_INFO_DATA",
     * groupInfo);
     *
     * //수정화면정보와 데이터빈을 연결시키는 바인딩 처리 String groupId = request.getParameter("code");
     *
     * //게시물 삭제 처리를 수행 try{ ProcessResult result = empGroupService.deleteUserGroupInfo(groupId); request.setAttribute("PROCESS_RESULT", result); }catch(BizException be){
     * request.setAttribute("EX_ERR_OBJ", be); }
     *
     * //수정완료후 게시판 리스트 화면으로 이동 modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo);
     *
     * //삭제처리 } else if (ConstKey.ACTION_DELETE_LIST_OK.equals(actionID)) { //수정화면정보와 데이터빈을 연결시키는 바인딩 처리 String[] groupId = request.getParameterValues("chkGroupId"); searchInfo.setArGroupId(groupId);
     *
     * //게시물 삭제 처리를 수행 empGroupService.deleteUserGroupInfo(searchInfo);
     *
     * //수정완료후 게시판 리스트 화면으로 이동 modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo);
     *
     * //유저그룹정보를 새롭게 반영 } else if ("ACTION_RELOAD_OK".equals(actionID)) { //메뉴정보 관리 빈을 재 설정한다 otherCodeTableMng.reloadMenuInfo();
     *
     * //수정완료후 게시판 리스트 화면으로 이동 modelAndView = doUserGroupInfoList(nPage, searchInfo, uInfo); }
     *
     * return modelAndView; }
     */

    /**
     * 권한레벨 정보를 취득한다.
     *
     * @param request  리퀘스트
     * @param userInfo 유저정보
     * @return ModelAndView 결과처리 객체
     * @throws UserSysException 예외
     */
    private void getAuthLevelList(final HttpServletRequest request, final UserVO userInfo) throws UserSysException {
        request.setAttribute("AUTHLEVEL_LIST", this.empGroupService.getAuthLevelList(userInfo));
    }

}