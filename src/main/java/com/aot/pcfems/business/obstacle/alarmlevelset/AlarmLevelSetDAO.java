/*****************************************************************************
 * 프로그램명  : ObstacleMstDAO.java
 * 설     명  : 장애 관리 > 알람 등급 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.05  KYM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.alarmlevelset;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 장애 관리 > 알람 등급 DAO persistence-layer class.
 *
 * @version 1.0
 */
@Repository
public class AlarmLevelSetDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = AlarmLevelSetDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    /**
     * 알람 등급 목록 갯수
     *
     * @param alarmLevelSet 검색정보
     * @return cnt 갯수
     */

    public int getCommAlarmMstCnt(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getCommAlarmMstCnt", alarmLevelSet);
    }

    /**
     * 알람 등급 목록
     *
     * @param alarmLevelSet 검색정보
     * @return List 목록
     */

    public List<AlarmLevelSetSearch> getCommAlarmMst(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCommAlarmMst", alarmLevelSet);
    }

    /**
     * 알람 등급 수정 정보 취득
     *
     * @param alarmLevelSet 검색정보
     * @return AlarmLevelSet 알람 등급 정보
     */

    public AlarmLevelSet getCommAlarmMstOne(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        return (AlarmLevelSet) this.sqlSession.selectOne(NS + "getCommAlarmMstOne", alarmLevelSet);
    }

    /**
     * 알람 등급 수정처리
     *
     * @param AlarmLevelSet 입력데이타
     */

    public ProcessResult updateCommAlarmMst(final AlarmLevelSet alarmLevelSet) throws UserSysException {
        this.sqlSession.update(NS + "updateCommAlarmMst", alarmLevelSet);
        return new ProcessResult(this.CLASS_NAME, "updateCommAlarmMst", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

}