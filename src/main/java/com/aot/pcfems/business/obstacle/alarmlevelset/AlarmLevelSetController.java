/*****************************************************************************
 * 프로그램명  : AlarmLevelSetController.java
 * 설     명  : 알람 등급 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       SysCodor  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.05 KYM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.alarmlevelset;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 알람 등급 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class AlarmLevelSetController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlarmLevelSetService alarmLevelSetService;

    /**
     * 장애 관리 알람 조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstaclemst/alarmlevel.do")
    public ModelAndView alarmlevelView(final HttpServletRequest request, final AlarmLevelSet alarmLevelSet) throws UserSysException {
        alarmLevelSet.doJqGridBind(request);
        return new ModelAndView("/obstacle/AlarmLevelSet/AlarmlevelSet", "SEARCH_DATA", alarmLevelSet);
    }

    /**
     * CPU/MEM 사용율 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstaclemst/alarmlevel.do", params = "actionID=ALM_LEVEL_MST_LIST")
    @ResponseBody
    public JsonObject getAlmLevelMstList(final HttpServletRequest request, final AlarmLevelSet alarmLevelSet) throws UserSysException {
        alarmLevelSet.doJqGridBind(request);
        alarmLevelSet.setUser_lang(AuthorityUtil.getUserInfo(request).getUser_lang());
        return JqGridUtil.getJsonData(alarmLevelSet, this.alarmLevelSetService.getCommAlarmMst(alarmLevelSet));
    }

    /**
     * 알람 등급 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstaclemst/updalarmlevelPopup.do")
    public ModelAndView goAlarmSetList(final HttpServletRequest request, final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return new ModelAndView("/obstacle/AlarmLevelSet/UpdAlarmlevelSet", "INFO_DATA", this.alarmLevelSetService.getCommAlarmMstOne(alarmLevelSet));
    }

    /**
     * 알람 등급 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstaclemst/updalarmlevelPopup.do", params = "actionID=ACTION_ALM_LEVEL_UPDATE_PROCESS")
    @ResponseBody
    public JsonObject doAlarmSetUpd(final HttpServletRequest request, final AlarmLevelSet alarmLevelSet) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        alarmLevelSet.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.alarmLevelSetService.updateCommAlarmMst(alarmLevelSet);
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

}