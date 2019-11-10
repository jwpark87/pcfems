/*****************************************************************************
 * 프로그램명  : GroupMenuServiceImpl.java
 * 설     명  : 그룹별그룹별메뉴관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.groupmenu;

import com.aot.pcfems.business.admin.syscod.SysCodDAO;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * 그룹별그룹별메뉴관리 비즈니스 로직
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class GroupMenuService {
    @Resource
    private GroupMenuDAO groupMenuDAO;
    @Resource
    private SysCodDAO sysCodDAO;

    /**
     * 그룹별메뉴관리리스트 취득
     *
     * @return List 그룹별메뉴관리리스트를 담은 페이징처리 객체
     */

    public List<MenuInfo> getMenuInfoList(final GroupMenuInfo groupMenuInfo) throws UserSysException {
        return this.groupMenuDAO.getMenuInfoList(StringUtil.getCommonPrefix(), groupMenuInfo);
    }

    /**
     * 그룹별메뉴관리등록처리
     *
     * @param menuInfo 그룹별메뉴관리정보
     */

    @Transactional
    public void insertMenuInfo(final GroupMenuInfo groupMenuInfo) throws UserSysException {
        this.groupMenuDAO.deleteMenuInfo(groupMenuInfo);
        for (final String menuId : groupMenuInfo.getArMenuId()) {
            if (menuId != null && !menuId.equals("")) {
                final String[] splits = StringUtils.split(menuId, ";");
                final Vector<String> menuIdVec = new Vector<>();
                for (final String split : splits) {
                    menuIdVec.add(split);
                }
                if (menuIdVec != null && menuIdVec.size() > 0) {
                    groupMenuInfo.setMenuId(menuIdVec.get(0));
                    if (menuIdVec.size() > 1) {
                        groupMenuInfo.setUpMenuId(menuIdVec.get(1));
                    }
                }
                this.groupMenuDAO.insertMenuInfo(groupMenuInfo);
            }
        }
    }

    /**
     * 권한레벨 정보 취득처리
     *
     * @param userInfo 유저정보
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        return this.groupMenuDAO.getAuthLevelList(userInfo);
    }

}