/*****************************************************************************
 * 프로그램명  : CdrSearchDAO.java
 * 설     명  : CDR 통합 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.19   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.cdrsearch;

import com.aot.pcfems.business.obstacle.alarminquiry.AlarmInquiry;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * CDR 조회 통합 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class CdrSearchDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = CdrSearchDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * CDR 조회 기본값 취득
     *
     * @param CdrSearchSearchInfo 검색데이타
     * @return CdrSearch 기본값 취득
     */

    public CdrSearchVO getCdrDefaultInfo(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getCdrDefaultInfo", searchInfo);
    }

    /**
     * CDR 조회 컬럼 목록
     *
     * @param CdrSearchSearchInfo 검색데이타
     * @return List 컬럼 리스트
     */

    public List<CdrSearchVO> getCdrColumnList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCdrColumnList", searchInfo);
    }

    /**
     * CDR 조회 필터 컬럼 목록
     *
     * @param CdrSearchSearchInfo 검색데이타
     * @return List 필터 컬럼 리스트
     */

    public List<CodeInfo> getCdrFilterColumnList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCdrFilterColumnList", searchInfo);
    }

    public Map<String, Object> getPrcHandleSnmpalarmList(final AlarmInquiry alarmInquiry) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getPrcHandleSnmpalarmList", alarmInquiry);
    }
}
