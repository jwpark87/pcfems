package com.aot.pcfems.business.obstacle.alarminquiry;
/*****************************************************************************
 * 프로그램명  : alarmInquiryController.java
 * 설     명  : 장애 관리 - 알람 조회 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       SysCodor  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.01 KYM    1.0     초기작성
 *****************************************************************************/

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.DisplayUtil;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 장애 관리 - 알람 조회 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class AlarmInquiryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlarmInquiryService alarmInquiryService;

    /**
     * 장애 관리 알람 조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/alarmpop.pop")
    public String goAlarmList(final Model model, final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        model.addAttribute("SEARCH_DATA", alarmInquiry);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        final List<CodeInfo> timerList = CodeTableMng.getCodeListByGrcode(userInfo, "ALM_UPDATE_TIMER");
        model.addAttribute("timerList", timerList);

        final List<CodeInfo> almLevelList = CodeTableMng.getCodeListByGrcode(userInfo, "ALM_LEVEL");
        for (int r = 0; r < almLevelList.size(); r++) {
            final CodeInfo cd = almLevelList.get(r);
            if ("CLEAR".equals(cd.getCode())) {
                almLevelList.remove(cd);
            }
        }
        model.addAttribute("almLevelList", almLevelList);

        return "/obstacle/AlarmInquiry/AlarmPop";
    }

    /**
     * CPU/MEM 사용율 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/alarmpop.pop", params = "actionID=CURR_CPUMEM_LIST")
    @ResponseBody
    public JsonObject getCpuMemist(final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        final HttpSession session = request.getSession();
        // 하루로 계속 할당
        session.setMaxInactiveInterval(1440 * 60);
        this.logger.debug("session --- LIST   -------   :   " + session.getMaxInactiveInterval());
        return JqGridUtil.getJsonData(alarmInquiry, this.alarmInquiryService.getCurrCpuMem(alarmInquiry));
    }

    /**
     * 장애 관리 알람 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/getAlarmList.json")
    @ResponseBody
    public JsonObject getAlarmList(final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        alarmInquiry.setAr_search_alm_level(alarmInquiry.getSrch_alm_level().split(","));
        return JqGridUtil.getJsonData(alarmInquiry, this.alarmInquiryService.getCurrSnmpAlarm(alarmInquiry));
    }

    /**
     * 사운드 설정
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/doSouneSet.json")
    @ResponseBody
    public JsonObject doSouneSet(final HttpServletRequest request, final AlarmInquiry alarmInquiry, @RequestParam(value = "kind", required = false, defaultValue = "SOUND_PLAY") final String kind)
            throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        final JsonObject jsonObject = new JsonObject();

        userInfo.setAlarmStatus(kind);
        userInfo.setAlarmStatusSetTime(AotDateUtils.getStringNow(AotDateUtils.YYYY_MM_DD_HH_MM_SS));
        AuthorityUtil.setUserInfo(request.getSession(), userInfo);
        jsonObject.addProperty("result_cd", "0");
        return jsonObject;
    }

    /**
     * 알람 레벨 건수 취득
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/alarmpop.pop", params = "actionID=ACTION_SNMPALARM_ALM_LEVEL_NUMBER")
    @ResponseBody
    public AlarmInquiry getAlmLevelNumber(final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        return this.alarmInquiryService.getSnmpAlarmAlmLevelNumber(alarmInquiry);
    }

    @RequestMapping(value = "/obstacle/getAlarmStatus.json")
    @ResponseBody
    public CommonException getAlarmStatus(final HttpServletRequest request) throws UserSysException {
        return new CommonException(CommonExceptionCode.SUCCESS_NORMAL, StringUtils.defaultString(AuthorityUtil.getUserInfo(request).getAlarmStatus(), "SOUND_PLAY"));
    }

    /**
     * 종합알람조회 화면상단 사운드 파일 취득 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/alarmpop.pop", params = "actionID=ACTION_OBSTACLE_ALARM_LIST")
    @ResponseBody
    public AlarmInquiry actionObstacleAlarmList(final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        alarmInquiry.doJqGridBind(request);
        return this.editObstacleAlarmList(AuthorityUtil.getUserInfo(request), this.alarmInquiryService.getObstacleAlarmList(alarmInquiry));
    }

    private AlarmInquiry editObstacleAlarmList(final UserVO userInfo, final List<AlarmInquiry> listData) {
        final AlarmInquiry ret = new AlarmInquiry();
        final StringBuffer message = new StringBuffer();

        double max = 0;
        AlarmInquiry maxInfo = new AlarmInquiry();
        // SOUND_YN 이 Y 인것만 가져와 레벨 순위 순서중 높은것 취득
        if (listData != null && listData.size() > 0) {
            for (int i = 0; i < listData.size(); i++) {
                final AlarmInquiry info = listData.get(i);
                // 가장 큰 값이 레벨이 높은것. 레벨 높은것으로 사운드 지정
                if (Double.parseDouble(info.getAlm_priority()) > max) {
                    max = Double.parseDouble(info.getAlm_priority());
                    maxInfo = info;
                }
            }

            if (StringUtils.equals(userInfo.getUser_lang(), "KOR")) {
                message.append("[");
                message.append(maxInfo.getHost_nm());
                message.append("]에서 장애 정보를 수신하였습니다.");
            } else {
                message.append("[");
                message.append(maxInfo.getHost_nm());
                message.append("] Fault information received.");
            }

            ret.setResult(CommonCode.YES);
            ret.setMessage(message.toString());
            ret.setLevel(maxInfo.getAlm_level());
            ret.setFile(DisplayUtil.getSoundFileNew(maxInfo.getAlm_level()));
            ret.setStatus(StringUtils.defaultString(userInfo.getAlarmStatus(), "SOUND_PLAY"));
        } else {
            ret.setResult(CommonCode.NO);
            message.append("수신된 장애 정보가 없습니다.");
            ret.setMessage(message.toString());
            ret.setLevel("");
            ret.setFile("");
            ret.setStatus(StringUtils.defaultString(userInfo.getAlarmStatus(), "SOUND_PLAY"));
        }

        return ret;
    }

    @RequestMapping(value = "/obstacle/emptyCommand.do", params = "actionID=emptyCommand")
    @ResponseBody
    public JsonObject emptyCommand() throws UserSysException {
        return CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
    }

    /**
     * 장애 관리 알람 조회 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/goRemarkPop.pop")
    public String goRemarkPop(final Model model, final HttpServletRequest request, @RequestParam("seqno") final String seq_no) throws UserSysException {
        model.addAttribute("seqno", seq_no);

        final String[] splits = StringUtils.split(seq_no, ",");
        final StringBuilder seqNo = new StringBuilder();
        final StringBuilder hostNm = new StringBuilder();
        final StringBuilder almCategory = new StringBuilder();
        final StringBuilder almGroup = new StringBuilder();
        final StringBuilder almInstance = new StringBuilder();
        final StringBuilder almRecovery = new StringBuilder();
        final StringBuilder almMsg = new StringBuilder();
        // StringBuilder almRemark1 = new StringBuilder();

        for (final String split : splits) {
            final AlarmInquiry alarmInquiry = this.alarmInquiryService.getCurrSnmpAlarmObjectBySeqno(Integer.parseInt(split));
            if(alarmInquiry != null){
                if (StringUtils.isNotEmpty(seqNo)) {
                    seqNo.append(", ");
                    hostNm.append(", ");
                    almCategory.append(", ");
                    almGroup.append(", ");
                    almInstance.append(",");
                    almRecovery.append(",<br/>");
                    almMsg.append(",<br/>");
                    // almRemark1.append(", ");
                }
                seqNo.append(alarmInquiry.getSeqno());
                hostNm.append(alarmInquiry.getHost_nm());
                almCategory.append(alarmInquiry.getAlm_category());
                almGroup.append(alarmInquiry.getAlm_group());
                almInstance.append(alarmInquiry.getAlm_instance());
                almRecovery.append(alarmInquiry.getAlm_recovery());
                almMsg.append(alarmInquiry.getAlm_msg());
                // almRemark1.append(alarmInquiry.getAlm_remark1());
            }
        }

        final AlarmInquiry alarmInquiry = this.alarmInquiryService.getCurrSnmpAlarmObjectBySeqno(Integer.parseInt(StringUtils.substringBefore(seq_no, ",")));
        if(alarmInquiry != null) {
            alarmInquiry.setSeqno(seqNo.toString());
            alarmInquiry.setHost_nm(hostNm.toString());
            alarmInquiry.setAlm_category(almCategory.toString());
            alarmInquiry.setAlm_group(almGroup.toString());
            alarmInquiry.setAlm_instance(almInstance.toString());
            alarmInquiry.setAlm_recovery(almRecovery.toString());
            alarmInquiry.setAlm_msg(almMsg.toString());
            // alarmInquiry.setAlm_remark1(almRemark1.toString());
            model.addAttribute("SEARCH_DATA", alarmInquiry);
        }

        return "/obstacle/AlarmInquiry/RemarkTerminatePop";
    }

    /**
     * 코멘트 수정 및 해지 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/doCommentAndTerminate.json")
    @ResponseBody
    public JsonObject doCommentAndTerminate(final HttpServletRequest request, final AlarmInquiry alarmInquiry) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        alarmInquiry.doJqGridBind(request);
        alarmInquiry.setWork_type(StringUtils.defaultString(request.getParameter("work_type")));

        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        final AlarmInquiry ObstacleResult = this.alarmInquiryService.updateObstacleAlarmMst(alarmInquiry, userInfo);

        if (ObstacleResult.getP_retcode() == 0) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        } else {
            returnMsg = "fail: " + ObstacleResult.getP_retmsg();
        }

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }
}