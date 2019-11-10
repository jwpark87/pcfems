/*****************************************************************************
 * 프로그램명  : GroupMenuDAO.java
 * 설     명  : 그룹별그룹별메뉴관리그룹별메뉴관리DB처리구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.26   LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.groupmenu;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ȸ����그룹별그룹별메뉴관리 DB처리 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class GroupMenuDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = GroupMenuDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * 그룹별메뉴관리리스트 취득
     *
     * @param prefix 그룹별메뉴관리정보
     * @return List 그룹별메뉴관리리스트
     */

    public List<MenuInfo> getMenuInfoList(final String prefix, final GroupMenuInfo groupMenuInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetGroupMenuList", groupMenuInfo);
    }

    /**
     * 그룹별메뉴관리등록처리
     *
     * @param menuInfo 그룹별메뉴관리정보
     */

    public void insertMenuInfo(final GroupMenuInfo groupMenuInfo) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertGroupMenuInfo", groupMenuInfo);
    }

    /**
     * 그룹별메뉴관리삭제처리
     *
     * @param no 그룹별메뉴관리글번호정보
     */

    public void deleteMenuInfo(final GroupMenuInfo groupMenuInfo) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteGroupMenuInfo", groupMenuInfo);
    }

    /**
     * 권한레벨 정보 리스트
     *
     * @param inParam, dataParam 환경정보, 입력데이타
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAuthLevelListGrMenu", userInfo);
    }
}
