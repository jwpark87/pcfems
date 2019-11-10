/*****************************************************************************
 * 프로그램명  : AlarmInquiryServiceImpl.java
 * 설     명  : 장애 관리 - 알람 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       	 or		Version Description
 * ---------- --------- ------- ----------------------------------------------
 * 2018.02.01  KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarminquiry;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.constant.CommonCode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 장애 관리 - 알람 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class AlarmInquiryService {
    @Resource
    private AlarmInquiryDAO alarmInquiryDAO;

    /**
     * 알람 조회 리스트 취득
     *
     * @param AlarmInquiry 검색정보빈
     * @return PagingUtil 알람 조회 정보 리스트
     */

    public PagingUtil getCurrSnmpAlarm(final AlarmInquiry alarmInquiry) throws UserSysException {
        if (alarmInquiry.getJqFilters() != null && !alarmInquiry.getJqFilters().isJsonNull() && alarmInquiry.getJqFilters().get("rules") != null) {
            final JsonArray arr = alarmInquiry.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("alm_status")) {
                        alarmInquiry.setAlm_status(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("host_nm")) {
                        alarmInquiry.setHost_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("alm_code")) {
                        alarmInquiry.setAlm_code(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("alm_level")) {
                        alarmInquiry.setAlm_level(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("sound_yn")) {
                        alarmInquiry.setSound_yn(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("alm_msg")) {
                        alarmInquiry.setAlm_msg(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("alm_instance")) {
                        alarmInquiry.setAlm_instance(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.alarmInquiryDAO.getCurrSnmpAlarmCnt(alarmInquiry), this.alarmInquiryDAO.getCurrSnmpAlarm(alarmInquiry));
    }

    /**
     * CPU/MEM 사용율 현황 목록
     *
     * @param AlarmInquiry 검색정보
     * @return List 목록
     */

    public PagingUtil getCurrCpuMem(final AlarmInquiry alarmInquiry) throws UserSysException {
        return new PagingUtil(this.alarmInquiryDAO.getCurrCpuMemCnt(alarmInquiry), this.alarmInquiryDAO.getCurrCpuMem(alarmInquiry));
    }

    /**
     * 알람 조회
     *
     * @param AlarmInquiry 검색정보
     * @return List 목록
     */

    public List<AlarmInquiry> getObstacleAlarmList(final AlarmInquiry alarmInquiry) throws UserSysException {
        return this.alarmInquiryDAO.getObstacleAlarmList(alarmInquiry);
    }

    /**
     * 알람 레벨 건수
     *
     * @param AlarmInquiry 검색정보
     * @return alarmInquiry 레벨 건수
     */

    public AlarmInquiry getSnmpAlarmAlmLevelNumber(final AlarmInquiry alarmInquiry) throws UserSysException {
        return this.alarmInquiryDAO.getSnmpAlarmAlmLevelNumber(alarmInquiry);
    }

    public AlarmInquiry getCurrSnmpAlarmObjectBySeqno(final Integer seqno) throws UserSysException {
        return this.alarmInquiryDAO.getCurrSnmpAlarmObjectBySeqno(seqno);
    }

    /**
     * 코멘트 수정 & 해지 처리 처리
     *
     * @param AlarmInquiry 검색정보
     * @return alarmInquiry 처리결과
     */

    @Transactional
    public AlarmInquiry updateObstacleAlarmMst(final AlarmInquiry alarmInquiry, final UserVO userInfo) throws UserSysException {
        final StringBuffer dataParam = new StringBuffer();
        if (!"COMMENT".equals(alarmInquiry.getWork_type())) {
            dataParam.append("CLEAR");
        }
        dataParam.append(CommonCode.PROC_DATA_SEP);
        dataParam.append(alarmInquiry.getSeqno());
        dataParam.append(CommonCode.PROC_DATA_SEP);
        dataParam.append(alarmInquiry.getAlm_remark1());
        dataParam.append(CommonCode.PROC_DATA_SEP);

        alarmInquiry.setP_src_env(StringUtil.makeSrcEnv(userInfo));
        alarmInquiry.setP_data_param(dataParam.toString());

        this.alarmInquiryDAO.updateObstacleAlarmMst(alarmInquiry);
        return alarmInquiry;
    }
}
