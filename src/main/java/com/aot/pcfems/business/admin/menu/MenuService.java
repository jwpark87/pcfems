/*****************************************************************************
 * 프로그램명  : MenuServiceImpl.java
 * 설     명  : 메뉴관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.menu;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.context.init.InitWebConstantContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 메뉴관리 비즈니스 로직
 */
@Service
public class MenuService {
    @Resource
    private MenuDAO menuDAO;

    /**
     * 메뉴관리리스트 취득
     *
     * @return List 메뉴관리리스트를 담은 페이징처리 객체
     */

    public List<MenuInfo> getMenuInfoList(final UserVO userInfo) throws UserSysException {
        final List<MenuInfo> arList = this.menuDAO.getMenuInfoList(userInfo);

        if (arList != null) {
            // 말단항목인지 체크한다 (삭제가능한 항목인지 체크)
            MenuInfo menuInfo = null;
            MenuInfo nextInfo = null;
            for (int i = 0; i < arList.size(); i++) {
                menuInfo = arList.get(i);
                // 카테고리인경우는 체크한다.
                if ("G".equals(menuInfo.getMenuType())) {
                    if (i == arList.size() - 1) {
                        menuInfo.setIsEnd(CommonCode.YES);
                    } else {
                        nextInfo = arList.get(i + 1);
                        if (menuInfo.getMenuId().equals(nextInfo.getUpMenuId())) {
                            menuInfo.setIsEnd(CommonCode.NO);
                        } else {
                            menuInfo.setIsEnd(CommonCode.YES);
                        }
                    }
                    // 카테고리 이외는 전부 말단항목이다
                } else {
                    menuInfo.setIsEnd(CommonCode.YES);
                }
            }
        }
        return arList;
    }

    /**
     * 메뉴관리등록처리
     *
     * @param menuInfo 메뉴관리정보
     */

    public ProcessResult insertMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        return this.menuDAO.insertMenuInfo(menuInfo);
    }

    /**
     * 메뉴관리내용을 표시
     */

    public MenuInfo getAdminMenuInfo(final String menuId) throws UserSysException {
        final StringBuffer dataParam = new StringBuffer();
        dataParam.append("MENU_ID=");
        dataParam.append(menuId);

        // 게시물취득처리 객체를 실행
        return this.menuDAO.getAdminMenuInfo(StringUtil.getCommonPrefix(), dataParam.toString());
    }

    /**
     * 메뉴관리수정처리
     */

    public ProcessResult updateMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        return this.menuDAO.updateMenuInfo(menuInfo);
    }

    /**
     * 메뉴관리삭제처리
     */

    public ProcessResult deleteMenuInfo(final MenuInfo menuInfo) throws UserSysException {
        return this.menuDAO.deleteMenuInfo(menuInfo);
    }

    public MenuInfo getMenuInfoByUrl(final String url) throws UserSysException {
        if (StringUtils.isNotEmpty(url)) {
            return this.menuDAO.getMenuInfoByUrl(StringUtils.removeStart(url, InitWebConstantContext.getContextPath()));
        } else {
            return null;
        }
    }

    public MenuInfo getMenuInfoByMenuId(final String menuId) throws UserSysException {
        return this.menuDAO.getMenuInfoByMenuId(menuId);
    }
}
