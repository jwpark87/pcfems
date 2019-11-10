/*****************************************************************************
 * 프로그램명  : AlarmLevelSetServiceImpl.java
 * 설     명  : 알람 등급  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       	 or		Version Description
 * ---------- --------- ------- ----------------------------------------------
 * 2018.02.05  KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmlevelset;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.util.PagingUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 장애 관리 > 알람 등급 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class AlarmLevelSetService {
    @Resource
    private AlarmLevelSetDAO alarmLevelSetDAO;

    /**
     * 알람 등급 목록
     *
     * @param AlarmLevelSetSearch 검색정보
     * @return List 목록
     */

    public PagingUtil getCommAlarmMst(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        if (alarmLevelSet.getJqFilters() != null && !alarmLevelSet.getJqFilters().isJsonNull() && alarmLevelSet.getJqFilters().get("rules") != null) {
            final JsonArray arr = alarmLevelSet.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo != null && !jo.isJsonNull()) {
                        if (jo.get("field").getAsString().equals("alm_code")) {
                            alarmLevelSet.setAlm_code(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_level")) {
                            alarmLevelSet.setAlm_level(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_level_nm")) {
                            alarmLevelSet.setAlm_level_nm(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("snmp_name")) {
                            alarmLevelSet.setSnmp_name(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_desc")) {
                            alarmLevelSet.setAlm_desc(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_category")) {
                            alarmLevelSet.setAlm_category(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_recovery")) {
                            alarmLevelSet.setAlm_recovery(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_clear_code")) {
                            alarmLevelSet.setAlm_clear_code(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("sound_yn")) {
                            alarmLevelSet.setSound_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("use_yn")) {
                            alarmLevelSet.setUse_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("crt_id")) {
                            alarmLevelSet.setCrt_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("upd_id")) {
                            alarmLevelSet.setUpd_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("upd_dt")) {
                            alarmLevelSet.setUpd_dt(jo.get("data").getAsString());
                        }
                    }
                }
            }
        }

        return new PagingUtil(this.alarmLevelSetDAO.getCommAlarmMstCnt(alarmLevelSet), this.alarmLevelSetDAO.getCommAlarmMst(alarmLevelSet));
    }

    /**
     * 알람 등급 수정 정보 취득
     *
     * @param alarmLevelSet 검색정보
     * @return AlarmLevelSet 알람 등급 정보
     */

    public AlarmLevelSet getCommAlarmMstOne(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return this.alarmLevelSetDAO.getCommAlarmMstOne(alarmLevelSet);
    }

    /**
     * 알람 등급 수정처리
     *
     * @param AlarmLevelSet 입력데이타
     */

    @Transactional
    public ProcessResult updateCommAlarmMst(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return this.alarmLevelSetDAO.updateCommAlarmMst(alarmLevelSet);
    }

}
