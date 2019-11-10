/*****************************************************************************
 * 프로그램명  : SysCodDAO.java
 * 설     명  : 시스템그룹코드관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.10.20  Ventus    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.syscod;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 시스템그룹코드관리 DAO persistence-layer class.
 *
 * @version 1.0
 */
@Repository
public class SysCodDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = SysCodDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    /**
     * 시스템그룹코드 목록 갯수
     *
     * @param searchInfo 검색정보
     * @return List 시스템그룹코드 목록
     */

    public int getSysCodCnt(final SysCodVO vo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getSysCodCnt", vo);
    }

    /**
     * 시스템그룹코드 목록
     *
     * @param searchInfo 검색정보
     * @return List 시스템그룹코드 목록
     */

    public List<SysCodVO> getSysCodList(final SysCodVO vo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getSysCod", vo);
    }

    /**
     * 기등록여부 체크
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return int 카운트
     */

    public int getSysCodExist(final SysCodVO info) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getSysCodExist", info);
    }

    /**
     * 보안관리>시스템그룹코드 등록처리
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult insertSysCod(final SysCodVO info) throws BizException, UserSysException {
        this.sqlSession.insert(NS + "insertSysCod", info);
        return new ProcessResult(this.CLASS_NAME, "insertSysCod", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 보안관리>시스템그룹코드 수정처리
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult updateSysCod(final SysCodVO info) throws BizException, UserSysException {
        this.sqlSession.update(NS + "updateSysCod", info);
        return new ProcessResult(this.CLASS_NAME, "updateSysCod", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * 삭제전 하위 코드 검색
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return int 카운트
     */

    public int detailCodeCount(final SysCodVO info) throws UserSysException {
        return this.sqlSession.selectOne(NS + "detailCodeCount", info);
    }

    /**
     * 보안관리>시스템그룹코드 삭제처리
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult deleteSysCod(final String inParam, final SysCodVO info) throws BizException, UserSysException {
        this.sqlSession.delete(NS + "deleteSysCod", info);
        return new ProcessResult(this.CLASS_NAME, "deleteSysCod", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * @param SysCodVO 검색데이타
     * @return int 카운트
     */

    public SysCodVO getMaxSortNo(final SysCodVO info) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getMaxSortNo", info);
    }

    /********************************** 상세정보 *************************************/
    /**
     * 시스템그룹코드 목록 갯수 - 상세
     *
     * @param searchInfo 검색정보
     * @return List 시스템그룹코드 목록
     */

    public int getSysCodDtlCnt(final SysCodVO vo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getSysCodDtlCnt", vo);
    }

    /**
     * 시스템그룹코드 목록 - 상세
     *
     * @param searchInfo 검색정보
     * @return List 시스템그룹코드 목록
     */

    public List<SysCodVO> getSysCodDtl(final SysCodVO vo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getSysCodDtl", vo);
    }

    /**
     * 시스템그룹코드 목록 - 상세 코드 등록처리
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult insertSysCodDtl(final SysCodVO info) throws BizException, UserSysException {
        this.sqlSession.insert(NS + "insertSysCodDtl", info);
        return new ProcessResult(this.CLASS_NAME, "insertSysCodDtl", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 기등록여부 체크
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return int 카운트
     */

    public int getSysCodDtlExist(final SysCodVO info) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getSysCodDtlExist", info);
    }

    /**
     * 시스템그룹코드 목록 - 상세 코드 수정처리
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult updateSysCodDtl(final String inParam, final SysCodVO info) throws BizException, UserSysException {
        this.sqlSession.update(NS + "updateSysCodDtl", info);
        return new ProcessResult(this.CLASS_NAME, "updateSysCodDtl", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * 시스템그룹코드 목록 - 상세 코드 삭제처리
     *
     * @param dataParam 입력데이타
     * @return ProcessResult 결과데이터
     */

    public ProcessResult deleteSysCodDtl(final SysCodVO info) throws BizException, UserSysException {
        return new ProcessResult(this.CLASS_NAME, "deleteSysCodDtl", 1, this.sqlSession.delete(NS + "deleteSysCodDtl", info), AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * 권한레벨 정보 리스트
     *
     * @param inParam, dataParam 환경정보, 입력데이타
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAuthLevelListSys", userInfo);
    }

}