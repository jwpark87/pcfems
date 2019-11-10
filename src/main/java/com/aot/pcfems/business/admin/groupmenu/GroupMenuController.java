/*****************************************************************************
 * 프로그램명  : GroupMenuController.java
 * 설     명  : 메뉴관리 개발사 전용 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2007.09.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.groupmenu;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴관리 컨트롤러
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class GroupMenuController {
    @Autowired
    private GroupMenuService groupMenuService;

    /**
     * 권한별 메뉴 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws BizException     비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/groupmenu/menu.do")
    public ModelAndView goGroupMenuList(final HttpServletRequest request) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        return new ModelAndView("/admin/groupmenu/GroupMenuList");
    }

    /**
     * 권한별 메뉴 관리 목록
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws BizException     비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/groupmenu/menu.do", params = "actionID=GROUPMENU_ACTION_LIST")
    @ResponseBody
    public ModelAndView doGroupMenuList(final HttpServletRequest request) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        // 리스트 데이터를 취득 (리스트가 0건인 경우 null로 설정된다)
        return this.doGroupMenuInfoList(userInfo, StringUtils.defaultString(request.getParameter("groupLevel")), request);
    }

    /**
     * 권한별 메뉴 관리 메뉴 등록
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws BizException     비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/groupmenu/menu.do", params = "actionID=GROUPMENU_ACTION_MENU_SETTING")
    @ResponseBody
    public ModelAndView doGroupMenuSet(final HttpServletRequest request) throws UserSysException {
        ModelAndView modelAndView = null;
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        final String groupLevel = StringUtils.defaultString(request.getParameter("groupLevel"));
        final String[] menuId = request.getParameterValues("menuIdStr");
        final GroupMenuInfo groupMenuInfo = new GroupMenuInfo();
        groupMenuInfo.setGroupLevel(groupLevel);
        groupMenuInfo.setCrtUser(userInfo.getUser_id());
        groupMenuInfo.setArMenuId(menuId);

        // 게시물 신규등록처리수행
        this.groupMenuService.insertMenuInfo(groupMenuInfo);

        // 등록완료후 게시판 리스트 화면으로 이동
        modelAndView = this.doGroupMenuInfoList(userInfo, groupLevel, request);

        return modelAndView;
    }

    /**
     * 권한별 메뉴 관리 메모리 리로드
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws BizException     비즈니스예외
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/groupmenu/menu.do", params = "actionID=GROUPMENU_ACTION_RELOAD_OK")
    @ResponseBody
    public ModelAndView doGroupMenuReload(final HttpServletRequest request) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        this.getAuthLevelList(request, userInfo);

        return this.doGroupMenuInfoList(userInfo, StringUtils.defaultString(request.getParameter("groupLevel")), request);
    }

    /**
     * 게시물 리스트 데이터를 취득한다
     *
     * @param nPage      현제 페이지
     * @param searchInfo 검색조건
     * @return ModelAndView 결과처리 객체
     * @throws UserSysException 예외
     */
    private ModelAndView doGroupMenuInfoList(final UserVO userInfo, final String groupLevel, final HttpServletRequest request) throws UserSysException {
        List<MenuInfo> arMenu;
        if (!"".equals(groupLevel)) {
            final GroupMenuInfo groupMenuInfo = new GroupMenuInfo();
            groupMenuInfo.setGroupLevel(groupLevel);
            groupMenuInfo.setUserId(AuthorityUtil.getUserInfo(request).getUser_id());
            groupMenuInfo.setLang(userInfo.getUser_lang());

            // 리스트 데이터를 취득 (리스트가 0건인 경우 null로 설정된다)
            arMenu = this.groupMenuService.getMenuInfoList(groupMenuInfo);

            for (final MenuInfo menuInfo : arMenu) {
                menuInfo.setMenuType(CodeTableMng.getName(userInfo, "W004", menuInfo.getMenuType()));
                menuInfo.setMenuNm(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), "&nbsp;"));
            }
        } else {
            arMenu = new ArrayList<>();
        }

        final ModelAndView modelAndView = new ModelAndView("/admin/groupmenu/GroupMenuList", "GROUPMENU_LIST", arMenu);
        modelAndView.addObject("groupLevel", groupLevel);
        return modelAndView;
    }

    /**
     * 권한레벨 정보를 취득한다.
     *
     * @param request  리퀘스트
     * @param userInfo 유저정보
     * @return ModelAndView 결과처리 객체
     * @throws UserSysException 예외
     */
    private void getAuthLevelList(final HttpServletRequest request, final UserVO userInfo) throws UserSysException {
        request.setAttribute("AUTHLEVEL_LIST", this.groupMenuService.getAuthLevelList(userInfo));
    }

}