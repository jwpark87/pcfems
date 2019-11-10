/*****************************************************************************
 * 프로그램명  : VarStatsDAO.java
 * 설     명  : var/stats 통합 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.21   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.varstats;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * var/stats 조회 통합 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class VarStatsDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = VarStatsDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * 조회 기본값 취득
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return VarStats 기본값 취득
     */

    public VarStats getVarStatsDefaultInfo(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getVarStatsDefaultInfo", searchInfo);
    }

    /**
     * 조회 컬럼 목록
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return List 컬럼 리스트
     */

    public List<VarStats> getVarStatsColumnList(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getVarStatsColumnList", searchInfo);
    }

    /**
     * 조회 필터 컬럼 목록
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return List 필터 컬럼 리스트
     */

    public List<CodeInfo> getVarStatsFilterColumnList(final VarStatsSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getVarStatsFilterColumnList", searchInfo);
    }
}
