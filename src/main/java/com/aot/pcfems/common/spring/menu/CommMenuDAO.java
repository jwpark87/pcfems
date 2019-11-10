/*****************************************************************************
 * 프로그램명  : MenuDAO.java
 * 설     명  : 메뉴정보관리 DB처리 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.10   LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.menu;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 메뉴정보관리 DB처리 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class CommMenuDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SqlSession sqlSession;
    // end must be point .
    private static final String NS = CommMenuDAO.class.getPackage().getName() + ".";

    /**
     * 그룹코드정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과그룹코드리스트정보
     */

    public List<MenuInfo> getMenuInfoByLevelCod(final String levelcod) throws UserSysException {
        return this.sqlSession.selectList(NS + "getMenuInfoByLevelCod", levelcod);
    }

    /**
     * 메뉴관리리스트 취득
     *
     * @param menuPageTable 메뉴관리정보
     * @return PagingTable 메뉴관리리스트
     */

    public List<MenuInfo> getAllMenuList() throws UserSysException {
        return this.sqlSession.selectList(NS + "getAllMenuList");
    }
}
