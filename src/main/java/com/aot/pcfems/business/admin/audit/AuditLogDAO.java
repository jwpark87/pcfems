/*****************************************************************************
 * 프로그램명  : AuditLogDAO.java
 * 설     명  : Audit Log 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.31   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.audit;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Audit Log 조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class AuditLogDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = AuditLogDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Audit Log 조회 목록 갯수
     *
     * @param AuditLogSearchInfo 검색데이타
     * @return int Audit Log 리스트 갯수
     */

    public int getAuditLogListCnt(final AuditLogSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getAuditLogListCnt", searchInfo);
    }

    /**
     * Audit Log 조회 목록
     *
     * @param AuditLogSearchInfo 검색데이타
     * @return List Audit Log 리스트
     */

    public List<AuditLogInfo> getAuditLogList(final AuditLogSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAuditLogList", searchInfo);
    }
}
