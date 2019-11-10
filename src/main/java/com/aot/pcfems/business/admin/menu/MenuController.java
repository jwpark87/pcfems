/*****************************************************************************
 * 프로그램명  : MenuController.java
 * 설     명  : 메뉴관리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2007.09.23  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.menu;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 메뉴관리 컨트롤러
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class MenuController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuService menuService;

    /**
     * 메뉴 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menu.do")
    public String getMenu(final Model model, final HttpServletRequest request, final MenuInfo vo) throws UserSysException {
        final List<MenuInfo> listData = this.menuService.getMenuInfoList(AuthorityUtil.getUserInfo(request));
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        for (final MenuInfo menuInfo : listData) {
            menuInfo.setMenuType(CodeTableMng.getName(userInfo, "W004", menuInfo.getMenuType()));
            menuInfo.setMenuNm(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), "&nbsp;"));
        }
        model.addAttribute("MENU_LIST", listData);
        return "/admin/menu/MenuList";
    }

    /**
     * 메뉴 관리 등록 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menuPopup.do")
    public ModelAndView goAddPop(final HttpServletRequest request, final MenuInfo menuInfo) throws UserSysException {
        return new ModelAndView("/admin/menu/MenuAdd", "MENU_INFO_DATA", menuInfo);
    }

    /**
     * 메뉴 관리 정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menuPopup.do", params = "actionID=ACTION_INSERT_OK")
    @ResponseBody
    public JsonObject doAddMenu(final HttpServletRequest request, final MenuInfo menuInfo) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        menuInfo.setCrtUser(userInfo.getUser_id());
        menuInfo.setUpdtUser(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.menuService.insertMenuInfo(menuInfo);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * 메뉴 관리 정보 메모리 갱신 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menu.do", params = "actionID=ACTION_RELOAD_OK")
    public String doReloadMenu(final Model model, final HttpServletRequest request, final MenuInfo menuInfo) throws UserSysException {
        return this.getMenu(model, request, menuInfo);
    }

    /**
     * 메뉴 관리 수정 팝업 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menuModPopup.do")
    public ModelAndView goModdPop(final HttpServletRequest request) throws UserSysException {
        return new ModelAndView("/admin/menu/MenuEdit", "MENU_INFO_DATA", this.menuService.getMenuInfoByMenuId(StringUtils.defaultString(request.getParameter("menuId"))));
    }

    /**
     * 메뉴 관리 정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menuModPopup.do", params = "actionID=ACTION_UPDATE_OK")
    @ResponseBody
    public JsonObject doModMenu(final HttpServletRequest request, final MenuInfo menuInfo) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        menuInfo.setUpdtUser(userInfo.getUser_id());

        final String[] url = menuInfo.getUrl().split("&");

        // 타입을 팝업으로 수정시 뒤에 파라미터를 붙여 팝업 효과를 보게 함
        if ("W".equals(menuInfo.getMenuType())) {
            if (!menuInfo.getUrl().contains("&decorator=modaless") && menuInfo.getUrl().contains(".do")) {
                menuInfo.setUrl(menuInfo.getUrl() + "&decorator=modaless&confirm=true");
            }
        } else {
            if (menuInfo.getUrl().contains("&decorator=modaless")) {
                menuInfo.setUrl(url[0]);
            }
        }

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");

        try {
            result = this.menuService.updateMenuInfo(menuInfo);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * 메뉴 관리 정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/admin/menu/menu.do", params = "actionID=ACTION_DELETE_OK")
    @ResponseBody
    public JsonObject doDelMenu(final HttpServletRequest request, final MenuInfo menuInfo) throws UserSysException {
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.FAIL", "");
        try {
            result = this.menuService.deleteMenuInfo(menuInfo);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + AotMessageUtils.getMessage("MSG.SUCCESS");
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }
}