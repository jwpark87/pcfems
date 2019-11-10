/*****************************************************************************
 * 프로그램명  : AlarmInquiryDAO.java
 * 설     명  : 알람 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.01  KYM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.alarminquiry;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 장애 관리 DAO persistence-layer class.
 *
 * @version 1.0
 */
@Mapper
public interface AlarmInquiryDAO {
    int getCurrCpuMemCnt(final AlarmInquiry alarmInquiry) throws UserSysException;

    List<AlarmInquiry> getCurrCpuMem(final AlarmInquiry alarmInquiry) throws UserSysException;

    int getCurrSnmpAlarmCnt(final AlarmInquiry alarmInquiry) throws UserSysException;

    List<AlarmInquiry> getCurrSnmpAlarm(final AlarmInquiry alarmInquiry) throws UserSysException;

    List<AlarmInquiry> getObstacleAlarmList(final AlarmInquiry alarmInquiry) throws UserSysException;

    AlarmInquiry getCurrSnmpAlarmObjectBySeqno(final Integer seqno) throws UserSysException;

    AlarmInquiry getSnmpAlarmAlmLevelNumber(final AlarmInquiry alarmInquiry) throws UserSysException;

    AlarmInquiry updateObstacleAlarmMst(final AlarmInquiry alarmInquiry) throws UserSysException;

}