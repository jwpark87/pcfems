/*****************************************************************************
 * 프로그램명  : MenuDAO.java
 * 설     명  : 메뉴관리메뉴관리DB처리구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.26   LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.admin.menu;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ȸ����메뉴관리 DB처리 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class MenuDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = MenuDAO.class.getPackage().getName() + ".";
    private final String CLASS_NAME = this.getClass().getName();

    @Autowired
    private SqlSession sqlSession;

    /**
     * 메뉴관리리스트 취득
     *
     * @param prefix 메뉴관리정보
     * @return List 메뉴관리리스트
     */

    public List<MenuInfo> getMenuInfoList(final UserVO userInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetMenuList", userInfo);
    }

    /**
     * 메뉴관리등록처리
     *
     * @param menuInfo 메뉴관리정보
     */

    public ProcessResult insertMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertMenuInfo", menuInfo);
        return new ProcessResult(this.CLASS_NAME, "insertMenuInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 메뉴관리내용을 표시
     *
     * @param no 취득할 메뉴관리 글번호
     * @return MenuInfo 메뉴관리글 정보
     */

    public MenuInfo getAdminMenuInfo(final String prefix, final String inParam) throws UserSysException {
        final Map<String, String> map = new HashMap<>();
        final String[] param = inParam.split("=");
        map.put("p_dataparam", param[1]);

        return (MenuInfo) this.sqlSession.selectOne(NS + "getAdminMenuInfo", map);

    }

    /**
     * 메뉴관리수정처리
     *
     * @param menuInfo 메뉴관리정보
     */

    public ProcessResult updateMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        this.sqlSession.insert(NS + "doUpdateMenuInfo", menuInfo);
        return new ProcessResult(this.CLASS_NAME, "updateMenuInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * 메뉴관리삭제처리
     *
     * @param no 메뉴관리글번호정보
     */

    public ProcessResult deleteMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteMenuInfo", menuInfo);
        return new ProcessResult(this.CLASS_NAME, "deleteMenuInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    public MenuInfo getMenuInfoByUrl(final String url) throws UserSysException {
        return (MenuInfo) this.sqlSession.selectOne(NS + "getMenuInfoByUrl", url);
    }

    public MenuInfo getMenuInfoByMenuId(final String menuId) throws UserSysException {
        return (MenuInfo) this.sqlSession.selectOne(NS + "getMenuInfoByMenuId", menuId);
    }
}
