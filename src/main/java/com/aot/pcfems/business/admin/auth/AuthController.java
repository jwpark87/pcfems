/*****************************************************************************
 * 프로그램명  : AuthController.java
 * 설     명  : 권한레벨관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2016.03.08 kym    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.auth;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 권한레벨관리 리스트 controller-layer class.
 *
 * @author aot
 * @version 1.0
 */
@Controller
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AuthService authService;

    /**
     * 권한레벨 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/auth.do")
    public ModelAndView goAuth(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        return new ModelAndView("/admin/auth/auth", "SEARCH_DATA", vo);
    }

    /**
     * 권한레벨 목록 취득
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/auth.do", params = "actionID=ACTION_AUTH_LIST")
    @ResponseBody
    public JsonObject goAuthList(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        return JqGridUtil.getJsonData(vo, this.authService.getAuthLevelList(vo, AuthorityUtil.getUserInfo(request)));
    }

    /**
     * 권한레벨 등록 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/authPopup.do", params = "actionID=ACTION_AUTH_INSERT")
    @ResponseBody
    public ModelAndView goAuthInsert(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final ModelAndView modelAndView = new ModelAndView("/admin/auth/authAddPopup", "SEARCH_DATA", vo);
        return modelAndView;
    }

    /**
     * 권한레벨 수정 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/authPopup.do", params = "actionID=ACTION_AUTH_UPDATE")
    public ModelAndView goAuthUpdate(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final AuthVO result = this.authService.getAuthLevelInfo(vo);
        final ModelAndView modelAndView = new ModelAndView("/admin/auth/authModPopup", "SEARCH_DATA", vo);
        modelAndView.addObject("AUTH_DATA", result);
        return modelAndView;
    }

    /**
     * 권한레벨 등록 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/authPopup.do", params = "actionID=ACTION_AUTH_INSERT_OK")
    @ResponseBody
    public JsonObject doAuthInsert(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);

        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("message.alert.fail", "");

        vo.setUser_id(userInfo.getUser_id());

        try {
            result = this.authService.insertAuthLevel(vo);
        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("message.alert.success");
        } else {
            returnMsg = "warn:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 권한레벨 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/authPopup.do", params = "actionID=ACTION_AUTH_UPDATE_OK")
    @ResponseBody
    public JsonObject doAuthUpdate(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);

        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("message.alert.fail", "");

        vo.setUser_id(userInfo.getUser_id());

        try {
            result = this.authService.updateAuthLevel(vo);
        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("message.alert.success");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 권한레벨 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/auth/authPopup.do", params = "actionID=ACTION_AUTH_DELETE_OK")
    @ResponseBody
    public JsonObject doAuthDelete(final HttpServletRequest request, final AuthVO vo) throws UserSysException {
        vo.doJqGridBind(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("message.alert.fail", "");

        try {
            result = this.authService.deleteAuthLevel(vo);
        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("message.alert.success");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }
}