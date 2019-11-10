/*****************************************************************************
 * 프로그램명  : UsageDAO.java
 * 설     명  : 통계관리>사용량추이(Cpu,Mem,Interface) DAO
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.27   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.usage;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 통계관리>사용량추이(CPU,MEM,DISK) DB처리 구현객체
 *
 * @author aot
 * @version 1.0
 */
@Repository
public class UsageDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = UsageDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * SBC정보 취득
     *
     * @param UsageSearchInfo 검색데이타
     * @return List SBC정보리스트(코드용)
     */

    public List<CodeInfo> getSbcCodeInfoList(final UsageSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getSbcCodeInfoList", searchInfo);
    }

}