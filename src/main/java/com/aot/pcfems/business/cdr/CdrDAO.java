/*****************************************************************************
 * 프로그램명  : CdrDAO.java
 * 설     명  : 진행 중 CDR 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.cdr;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 진행 중 CDR 조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class CdrDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = CdrDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    /**
     * 진행 중 CDR 조회 목록 갯수
     *
     * @param CdrSearchInfo 검색데이타
     * @return int 진행중 리스트 갯수
     */

    public int getCdrIngListCnt(final CdrSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getCdrIngListCnt", searchInfo);
    }

    /**
     * 진행 중 CDR 조회 목록
     *
     * @param CdrSearchInfo 검색데이타
     * @return List 진행중 리스트
     */

    public List<CdrInfo> getCdrIngList(final CdrSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCdrIngList", searchInfo);
    }

}
