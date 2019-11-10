/*****************************************************************************
 * 프로그램명  : EmpGroupServiceImpl.java
 * 설     명  : ȸ��사원 정보  persistence-layer class.
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
import com.aot.pcfems.common.util.PagingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ȸ�� 사원 정보 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class EmpGroupService {
    @Resource
    private EmpGroupDAO empGroupDAO;

    /**
     * 유저정보 리스트 취득
     *
     * @param boardPageTable 유저정보 리스트 정보
     * @return PagingTable 유저정보 리스트를 담은 페이징처리 객체
     */

    public PagingUtil getUserGroupInfoList(final EmpGroupSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.empGroupDAO.getUserGroupInfoListCount(searchInfo), this.empGroupDAO.getUserGroupInfoList(searchInfo));
    }

    /**
     * 고객의 소리 관리자의 이메일 리스트 취득
     *
     * @param groupId 검색유저아이디
     * @return User 유저정보객체
     */

    public UserGroupInfo getUserGroupInfo(final EmpGroupSearchInfo searchInfo) throws UserSysException {
        return this.empGroupDAO.getUserGroupInfo(searchInfo);
    }

    /**
     * 유저정보 등록처리
     *
     * @param userGroupInfo 등록할 유저정보
     */

    @Transactional
    public ProcessResult insertUserGroupInfo(final UserGroupInfo userGroupInfo) throws BizException, UserSysException {
        return this.empGroupDAO.insertUserGroupInfo(userGroupInfo);

    }

    /**
     * 유저정보 갱신처리
     *
     * @param userGroupInfo 갱신할 유저정보
     */

    @Transactional
    public ProcessResult updateUserGroupInfo(final UserGroupInfo userGroupInfo) throws BizException, UserSysException {
        return this.empGroupDAO.updateUserGroupInfo(userGroupInfo);
    }

    /**
     * 유저정보 삭제처리
     *
     * @param userGroupInfo 삭제할유저아이디
     */

    @Transactional
    public ProcessResult deleteUserGroupInfo(final String groupId) throws BizException, UserSysException {
        return this.empGroupDAO.deleteUserGroupInfo(groupId);
    }

    /**
     * 권한레벨 정보 취득처리
     *
     * @param userInfo 유저정보
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        return this.empGroupDAO.getAuthLevelList(userInfo);
    }
}
