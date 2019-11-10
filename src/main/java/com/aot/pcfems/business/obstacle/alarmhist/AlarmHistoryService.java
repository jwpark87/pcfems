/*****************************************************************************
 * 프로그램명  : AlarmHistoryServiceImpl.java
 * 설     명  : 알람이력조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   HSI     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmhist;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 알람이력조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class AlarmHistoryService {
    @Resource
    private AlarmHistoryDAO obstaclehistDAO;

    /**
     * 진행 중 알람이력조회 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil 진행 중 알람이력조회 리스트
     */

    public PagingUtil getAlarmHistoryList(final AlarmHistorySearchInfo vo, final UserVO userInfo) throws UserSysException {
        return new PagingUtil(this.obstaclehistDAO.getAlarmHistoryListCnt(vo), this.obstaclehistDAO.getAlarmHistoryList(vo));
    }
}
