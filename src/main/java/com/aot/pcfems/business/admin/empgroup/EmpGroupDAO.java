/*****************************************************************************
 * 프로그램명  : EmpGroupDAO.java
 * 설     명  : ȸ��사원 정보 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.12.24  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.empgroup;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserGroupInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ȸ���� ȸ��사원 정보 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class EmpGroupDAO {
    // end must be point .
    private static final String NS = EmpGroupDAO.class.getPackage().getName() + ".";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();

    @Autowired
    private SqlSession sqlSession;

    /**
     * 유저정보 리스트 수 취득
     *
     * @param searchInfo 검색정보
     * @return int 리스트수
     */

    public int getUserGroupInfoListCount(final EmpGroupSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getUserGroupInfoListCount", searchInfo);
    }

    /**
     * 유저정보 리스트 취득
     *
     * @param boardPageTable 유저정보 리스트 정보
     * @return PagingTable 유저정보 리스트를 담은 페이징처리 객체
     */

    public List<UserGroupInfo> getUserGroupInfoList(final EmpGroupSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getUserGroupInfoList", searchInfo);
    }

    /**
     * 유저정보 취득
     *
     * @param groupId 검색유저아이디
     * @return User 유저정보객체
     */

    public UserGroupInfo getUserGroupInfo(final EmpGroupSearchInfo searchInfo) throws UserSysException {
        this.logger.debug("getUserInfo", "getUserGroupInfo");
        return (UserGroupInfo) this.sqlSession.selectOne(NS + "getUserGroupInfo", searchInfo);
    }

    /**
     * 유저정보 등록처리
     *
     * @param userGroupInfo 등록할 유저정보
     */

    public ProcessResult insertUserGroupInfo(final UserGroupInfo userGroupInfo) throws BizException, UserSysException {
        this.sqlSession.insert(NS + "doInsertUserGroupInfo", userGroupInfo);
        return new ProcessResult(this.CLASS_NAME, "insertUserGroupInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 유저정보 갱신처리
     *
     * @param userGroupInfo 갱신할 유저정보
     */

    public ProcessResult updateUserGroupInfo(final UserGroupInfo userGroupInfo) throws BizException, UserSysException {
        this.sqlSession.update(NS + "doUpdateUserGroupInfo", userGroupInfo);
        return new ProcessResult(this.CLASS_NAME, "updateUserGroupInfo", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));

    }

    /**
     * 유저정보 삭제처리
     *
     * @param userGroupInfo 삭제할유저아이디
     */

    public ProcessResult deleteUserGroupInfo(final String groupId) throws BizException, UserSysException {
        this.sqlSession.delete(NS + "doDeleteUserGroupInfo", groupId);
        return new ProcessResult(this.CLASS_NAME, "deleteUserGroupInfo", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * 권한레벨 정보 리스트
     *
     * @param inParam, dataParam 환경정보, 입력데이타
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        final Map<String, String> map = new HashMap<>();
        map.put("id", userInfo.getUser_id());
        // 파라메터 수에 관계없이 일관적인 처리를 위해서 이 방법을 사용함.
        return this.sqlSession.selectList(NS + "getAuthLevelListEmpGrp", map);

    }

}
