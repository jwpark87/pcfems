/*****************************************************************************
 * 프로그램명  : EmpDAO.java
 * 설     명  : 사용자정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2015.05.08  WYA    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.emp;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 사용자정보관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class EmpDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = EmpDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    /**
     * 사용자정보관리 목록 갯수
     *
     * @param EmpSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getEmpListCnt(final EmpSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getUserInfoListCount", searchInfo);
    }

    /**
     * 사용자정보 목록
     *
     * @param EmpSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<EmpInfo> getUserInfoList(final EmpSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getUserInfoList", searchInfo);
    }

    /**
     * 사용자정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertEmp(final EmpInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertEmpInfo", info);
        return new ProcessResult(this.CLASS_NAME, "insertUserInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 사용자관리 상세 정보
     *
     * @param userId 검색유저아이디
     * @return User 사용자정보 상세보기
     */

    public EmpInfo getEmpDetail(final EmpInfo info) throws UserSysException {
        return (EmpInfo) this.sqlSession.selectOne(NS + "getEmpDetail", info);
    }

    /**
     * 사용자정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateEmp(final EmpInfo info) throws UserSysException {
        this.sqlSession.update(NS + "doUpdateEmpInfo", info);
        return new ProcessResult(this.CLASS_NAME, "updateUserInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 사용자정보 삭제처리
     *
     * @param userInfo 삭제할유저아이디
     */

    public ProcessResult deleteEmp(final String inParam) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteEmpInfo", inParam);
        return new ProcessResult(this.CLASS_NAME, "deleteMenuInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 사용자그룹 정보 리스트
     *
     * @param inParam, dataParam 환경정보, 입력데이타
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getUserGroupCodeInfo(final EmpSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getUserGroupCodeInfoEmp", searchInfo);
    }

    /**
     * 유저아이디 존재 체크
     *
     * @param userId ȸ��유저 아이디
     * @return boolean (true:유저가 존재하지 않을때,false:유저가 존재할때)
     */

    public String getUserInfoExist(final String userId) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getUserInfoExist", userId);
    }

}
