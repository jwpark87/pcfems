/*****************************************************************************
 * 프로그램명  : AuthDAO.java
 * 설     명  : 권한레벨관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.10.20  Ventus    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.auth;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 권한레벨관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class AuthDAO {
    // end must be point .
    private static final String NS = AuthDAO.class.getPackage().getName() + ".";
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();

    @Autowired
    private SqlSession sqlSession;

    /**
     * 권한레벨 목록 갯수
     *
     * @param AuthVO 검색데이타
     * @return int 갯수
     */

    public int getAuthLevelCount(final AuthVO Info) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getAuthLevelCount", Info);
    }

    /**
     * 권한레벨 목록
     *
     * @param AuthVO 검색데이타
     * @return List 리스트
     */

    public List<AuthVO> getAuthLevelList(final AuthVO Info) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAuthLevelList", Info);
    }

    /**
     * 권한레벨 정보
     *
     * @param AuthVO 검색데이타
     * @return Auth 정보
     */

    public AuthVO getAuthLevelInfo(final AuthVO Info) throws UserSysException {
        return (AuthVO) this.sqlSession.selectOne(NS + "getAuthLevelInfo", Info);
    }

    /**
     * 기등록여부 체크
     *
     * @param AuthVO 입력데이타
     * @return int 카운트
     */

    public int getAuthLevelExist(final AuthVO info) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getAuthLevelExist", info);
    }

    /**
     * 권한레벨 등록처리
     *
     * @param AuthVO 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult insertAuthLevel(final AuthVO info) throws UserSysException {
        this.sqlSession.insert(NS + "insertAuthLevel", info);
        return new ProcessResult(this.CLASS_NAME, "insertAuthLevel", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 권한레벨 수정처리
     *
     * @param AuthVO 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult updateAuthLevel(final AuthVO info) throws UserSysException {
        this.sqlSession.update(NS + "updateAuthLevel", info);
        return new ProcessResult(this.CLASS_NAME, "updateAuthLevel", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * 권한레벨 삭제처리
     *
     * @param AuthVO 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult deleteAuthLevel(final AuthVO info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteAuthLevel", info);
        return new ProcessResult(this.CLASS_NAME, "deleteAuthLevel", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

}