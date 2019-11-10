/*****************************************************************************
 * 프로그램명  : AlarmHistoryDAO.java
 * 설     명  : 알람이력조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   HSI     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.alarmhist;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 알람이력조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Mapper
public interface AlarmHistoryDAO {
    int getAlarmHistoryListCnt(final AlarmHistorySearchInfo searchInfo) throws UserSysException;

    List<AlarmHistoryInfo> getAlarmHistoryList(final AlarmHistorySearchInfo searchInfo) throws UserSysException;
}
