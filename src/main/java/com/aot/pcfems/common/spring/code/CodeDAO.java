/*****************************************************************************
 * 프로그램명  : CodeDAO.java
 * 설     명  : 코드정보관리 DB처리 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23   LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.code;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 코드정보관리 DB처리 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class CodeDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = CodeDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * 그룹코드정보 취득
     *
     * @param inParam
     *            입력파라메터
     * @return List 결과그룹코드리스트정보
     */

    // public List<GroupCodeInfo> getCodeInfo(final String inParam) throws UserSysException {
    // return this.sqlSession.selectList(NS + "doGetGroupCodeInfo", StringUtils.split(inParam, "=")[1]);
    // }

    /**
     * 기타마스터코드정보 취득
     *
     * @param inParam
     *            입력파라메터
     * @return List 결과그룹코드리스트정보
     */

    // public List<UserGroupInfo> getUserGroupCodeInfo(final String inParam) throws UserSysException {
    // return this.sqlSession.selectList(NS + "doGetUserGroupCodeInfo", inParam.split("=")[1]);
    // }

    /**
     * 상위코드가 있는 코드정보 취득
     *
     * @return List 결과화면표시정보리스트
     */

    // public List<GroupCodeInfo> getUpCodeInfo() throws UserSysException {
    // return this.sqlSession.selectList(NS + "getUpCodeInfo");
    // }

    /**
     * 특정 사용자그룹에 속한 사용자 리스트를 취득
     *
     * @param code 특정사용자그룹
     * @return List 사용자 리스트를 담은 객체
     */

    // public List<CodeInfo> getGroupUser(final String code) throws UserSysException {
    // return this.sqlSession.selectList(NS + "getGroupUser", code);
    // }
    public List<CodeInfo> getCodeListByGrcode(final String grcode) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCodeListByGrcode", grcode);
    }

    public List<CodeInfo> getAllCodeListByGrcode(final String grcode) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAllCodeListByGrcode", grcode);
    }

}
