/*****************************************************************************
 * 프로그램명  : MenuServiceImpl.java
 * 설     명  : 메뉴정보관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.24  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.menu;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 메뉴정보관리 비즈니스 로직
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class CommMenuService {
    @Resource
    private CommMenuDAO commMenuDAO;

    /**
     * 메뉴 리스트 취득
     *
     * @param nPage      현재페이지번호
     * @param searchInfo 검색조건
     * @return PagingTable 코드리스트를 담은 페이징처리 객체
     */

    public List<MenuInfo> getMenuInfoByLevelCod(final String levelcod) throws UserSysException {
        // 코드의 총갯수를 취득
        return this.commMenuDAO.getMenuInfoByLevelCod(levelcod);
    }

    /**
     * 메뉴관리리스트 취득
     *
     * @return List 메뉴관리리스트를 담은 페이징처리 객체
     */

    public List<MenuInfo> getAllMenuList() throws UserSysException {
        return this.commMenuDAO.getAllMenuList();
    }
}