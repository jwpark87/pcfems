/*****************************************************************************
 * 프로그램명  : SysCodController.java
 * 설     명  : 시스템그룹코드관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       SysCodor  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.10.23 Ventus    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.syscod;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.ProcessResult;
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
 * 시스템그룹코드관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class SysCodController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysCodService sysCodService;

    /**
     * 시스템코드관리 목록 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do")
    public ModelAndView goSysCodList(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        return new ModelAndView("/admin/syscod/SysCodList", "SEARCH_DATA", vo);
    }

    /**
     * 시스템코드관리 목록
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCOD_LIST")
    @ResponseBody
    public JsonObject getSysCodList(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final JsonObject result = JqGridUtil.getJsonData(vo, this.sysCodService.getSysCodList(vo, AuthorityUtil.getUserInfo(request)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(vo));
        return result;
    }

    /**
     * 시스템코드관리 목록 - 상세
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCOD_LIST_AJAX")
    @ResponseBody
    public JsonObject getSysCodListDet(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final JsonObject result = JqGridUtil.getJsonData(vo, this.sysCodService.getSysCodDtl(vo, AuthorityUtil.getUserInfo(request)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(vo));
        return result;
    }

    /**
     * 시스템코드관리 등록 - 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/grcodePopup.do")
    public ModelAndView goInsertSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        ModelAndView modelAndView = null;
        vo.doJqGridBind(request);
        modelAndView = new ModelAndView("/admin/syscod/GrCodeAdd", "SYSCOD_INSERT_DATA", vo);
        return modelAndView;
    }

    /**
     * 시스템코드관리 등록처리 - 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/grcodePopup.do", params = "actionID=ACTION_SYSCOD_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        vo.setUpd_id(userInfo.getUser_id());
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        ProcessResult result = new ProcessResult();
        try {
            result = this.sysCodService.insertSysCod(vo, userInfo);
        } catch (final BizException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            request.setAttribute("EX_ERR_OBJ", e);
        }

        // 데이터 중복
        if (result.getRetCod() == 2) {
            returnMsg = "keyfail:" + result.getRetMsg();
        } else if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * 시스템코드관리 수정 - 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/grcodeModPopup.do")
    public ModelAndView goModSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        return new ModelAndView("/admin/syscod/GrCodeMod", "SYSCOD_INSERT_DATA", vo);
    }

    /**
     * 시스템코드관리 수정처리 - 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/grcodeModPopup.do", params = "actionID=ACTION_SYSCOD_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        vo.setUpd_id(userInfo.getUser_id());
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        ProcessResult result = new ProcessResult();

        try {
            result = this.sysCodService.updateSysCod(vo, userInfo);
        } catch (final BizException be) {
            this.logger.warn(ExceptionUtils.getStackTrace(be));
            request.setAttribute("EX_ERR_OBJ", be);
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * 시스템코드관리 삭제 전 하위코드 검색
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCOD_DELETE_CNT")
    @ResponseBody
    public JsonObject doDelSysCodCnt(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", String.valueOf(this.sysCodService.detailCodeCount(vo)));
        return jsonObject;
    }

    /**
     * 시스템코드관리 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCOD_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        ProcessResult result = new ProcessResult();

        try {
            result = this.sysCodService.deleteSysCod(vo, userInfo);
        } catch (final BizException be) {
            this.logger.warn(ExceptionUtils.getStackTrace(be));
            request.setAttribute("EX_ERR_OBJ", be);
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCOD_MAXSORTNO")
    @ResponseBody
    public JsonObject doMaxsortNoSysCod(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);

        final SysCodVO result = this.sysCodService.getMaxSortNo(vo);
        jsonObject.addProperty("max_sort_no", String.valueOf(result.getMax_sort_no()));
        return jsonObject;
    }

    /**
     * 시스템코드관리 등록 - 상세 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/codePopup.do")
    public ModelAndView goInsertSysCodDtl(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        return new ModelAndView("/admin/syscod/CodeAdd", "SYSCODDTL_INSERT_DATA", vo);
    }

    /**
     * 시스템코드관리 상세 등록처리 - 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/codePopup.do", params = "actionID=ACTION_SYSCODDTL_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertSysCodDtl(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        vo.setUpd_id(userInfo.getUser_id());
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        ProcessResult result = new ProcessResult();
        try {
            result = this.sysCodService.insertSysCodDtl(vo, userInfo);
        } catch (final BizException be) {
            this.logger.warn(ExceptionUtils.getStackTrace(be));
            request.setAttribute("EX_ERR_OBJ", be);
        }

        // 데이터 중복
        if (result.getRetCod() == 2) {
            returnMsg = "keyfail:" + result.getRetMsg();
        } else if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;

    }

    /**
     * 시스템코드관리 등록 - 상세 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/codeModPopup.do")
    public ModelAndView goUpdateSysCodDtl(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        return new ModelAndView("/admin/syscod/CodeMod", "SYSCODDTL_INSERT_DATA", vo);
    }

    /**
     * 시스템코드관리 상세 수정처리 - 팝업
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/codeModPopup.do", params = "actionID=ACTION_SYSCODDTL_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateSysCodDtl(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        vo.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        vo.setUpd_id(userInfo.getUser_id());
        String returnMsg = "fail_update:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        ProcessResult result = new ProcessResult();
        try {
            result = this.sysCodService.updateSysCodDtl(vo, userInfo);
        } catch (final BizException be) {
            this.logger.warn(ExceptionUtils.getStackTrace(be));
            request.setAttribute("EX_ERR_OBJ", be);
        }

        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * 시스템코드관리 상세 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/syscod/syscod.do", params = "actionID=ACTION_SYSCODDTL_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteSysCodDtl(final HttpServletRequest request, final SysCodVO vo) throws UserSysException {
        final JsonObject jsonObject = new JsonObject();
        String returnMsg = "fail_update:" + AotMessageUtils.getMessage("MSG.FAIL", "");
        ProcessResult result = new ProcessResult();

        try {
            result = this.sysCodService.deleteSysCodDtl(vo);
        } catch (final BizException be) {
            this.logger.warn(ExceptionUtils.getStackTrace(be));
            request.setAttribute("EX_ERR_OBJ", be);
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * 권한레벨 정보를 취득한다.
     *
     * @param request  리퀘스트
     * @param userInfo 유저정보
     * @return ModelAndView 결과처리 객체
     * @throws UserSysException 예외
     */
    private void getAuthLevelList(final HttpServletRequest request, final UserVO userInfo) throws UserSysException {
        request.setAttribute("AUTHLEVEL_LIST", this.sysCodService.getAuthLevelList(userInfo));
    }

}