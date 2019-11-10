/*****************************************************************************
 * 프로그램명  : MenuMng.java
 * 설     명  : 메뉴관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.10  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.menu;

import com.aot.pcfems.business.admin.menu.MenuService;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.spring.menu.CommMenuService;
import com.aot.standard.common.util.AotAccessBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;

/**
 * 메뉴정보 데이터관리（레벨별메뉴정보）Bean
 *
 * @author eaction
 * @version 1.0
 */
@Component
public class MenuMng {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuMng.class);

    /**
     * 업무키에대한코드명칭데이터BeanObject의리스트데이터취득
     *
     * @param user  유저정보
     * @param group 그룹코드
     * @return List 메뉴정보데이터빈의 리스트
     * @throws UserSysException
     */
    @SuppressWarnings("unchecked")
    public static List<MenuInfo> getMenuListByGroupLevelCod(final HttpSession session, final String levelcod) throws UserSysException {
        if (session.getAttribute("getMenuList." + levelcod) == null) {
            session.setAttribute("getMenuList." + levelcod, AotAccessBeanUtils.getBean(CommMenuService.class).getMenuInfoByLevelCod(StringUtils.defaultIfEmpty(levelcod, "101")));
            LOGGER.debug("save ==> getMenuList.{}.", levelcod);
        }
        LOGGER.debug("cache ==> getMenuList.{}.", levelcod);
        return (List<MenuInfo>) session.getAttribute("getMenuList." + levelcod);
    }

    public static MenuInfo getMenuInfoByMenuId(final String menuId) throws UserSysException {
        return AotAccessBeanUtils.getBean(MenuService.class).getMenuInfoByMenuId(menuId);
    }

    /**
     * 메뉴아이디의 메뉴이름을 취득한다
     *
     * @param menuId 메뉴아이디
     * @return String 메뉴이름
     * @throws UserSysException
     */
    public static String getMenuName(final String menuId, final UserVO userInfo) throws UserSysException {
        LOGGER.debug("menuId : {}", menuId);
        String menuName = "";
        if (StringUtils.isNotEmpty(menuId)) {
            final MenuInfo menuInfo = AotAccessBeanUtils.getBean(MenuService.class).getMenuInfoByMenuId(menuId);
            if (menuInfo != null) {
                if (userInfo.getUser_lang().equals("KOR")) {
                    menuName = menuInfo.getMenuNmKor();
                } else if (userInfo.getUser_lang().equals("ENG")) {
                    menuName = menuInfo.getMenuNmEng();
                } else if (userInfo.getUser_lang().equals("JPN")) {
                    menuName = menuInfo.getMenuNmJpn();
                } else {
                    menuName = menuInfo.getMenuNmKor();
                }
                if (StringUtils.isEmpty(menuName)) {
                    menuName = menuInfo.getMenuNm();
                }
            }
        }
        return menuName;
    }

    public static Hashtable<String, MenuInfo> getMenuMap() throws UserSysException {
        final Hashtable<String, MenuInfo> result = new Hashtable<>();
        final List<MenuInfo> allMenuList = AotAccessBeanUtils.getBean(CommMenuService.class).getAllMenuList();
        for (final MenuInfo menuInfo : allMenuList) {
            result.put(menuInfo.getMenuId(), menuInfo);
        }
        return result;
    }

    public static MenuInfo getMenuInfo(final String menuId) throws UserSysException {
        return AotAccessBeanUtils.getBean(MenuService.class).getMenuInfoByMenuId(menuId);
    }

    public static String getMenuUrlByMenuId(final String menuId) throws UserSysException {
        final MenuInfo menuInfo = getMenuInfo(menuId);
        if (menuInfo != null) {
            return menuInfo.getUrl();
        } else {
            return null;
        }
    }

    public static String getMenuIdByUrl(final String url) throws UserSysException {
        final MenuInfo menuInfoByUrl = AotAccessBeanUtils.getBean(MenuService.class).getMenuInfoByUrl(url);
        if (menuInfoByUrl != null) {
            return menuInfoByUrl.getMenuId();
        } else {
            return null;
        }
    }

    public static String getMenuTypeByUrl(final String url) throws UserSysException {
        final MenuInfo menuInfoByUrl = AotAccessBeanUtils.getBean(MenuService.class).getMenuInfoByUrl(url);
        if (menuInfoByUrl != null) {
            return menuInfoByUrl.getMenuType();
        } else {
            return null;
        }
    }

}
