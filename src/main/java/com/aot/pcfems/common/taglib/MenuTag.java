package com.aot.pcfems.common.taglib;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * 왼쪽메뉴표시태그라이브러리 설정
 *
 * @author eaction
 * @version 1.0
 */
public class MenuTag extends TagSupport {
    private transient static final long serialVersionUID = -7074200858566127257L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTag.class);

    /**
     * 보여줄 루트메뉴 값 전달받는다. (top에서는 "top" 값이 넘어온다)
     */
    private String root = "";
    private String menu_url = "";
    private String top_root = "";

    /**
     * 시작 태그 처리
     *
     * @return String 권한별표시여부태그문자열
     */
    @Override
    public int doStartTag() throws JspException {
        final StringBuffer sbMenu = new StringBuffer();
        final HttpSession session = this.pageContext.getSession();
        final UserVO userInfo = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

        List<MenuInfo> arList = null;
        try {
            arList = MenuMng.getMenuListByGroupLevelCod(session, userInfo.getGroupLevelCod());
        } catch (final UserSysException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        MenuInfo menuInfo = new MenuInfo();
        int rootLevel = 0;
        String preLevel = "";
        // String rootName = "";
        // int isFirstGroup = 0;
        // boolean firstMenuYn = true;

        // String preMenuId = "";
        // String preMenuType = "";
        String menuIdChk = "";
        String topMenuId = "";

        String menuIcon = "";

        if (arList != null && arList.size() > 0) {
            // top메뉴? left메뉴?
            if (StringUtils.equals(this.root, "top_menu")) {
                for (int i = 0; i < arList.size(); i++) {
                    menuInfo = arList.get(i);
                    if (menuInfo.getMenuType().equals("G")) {
                        if (menuInfo.getLevel().equals("2")) {
                            if (StringUtils.isEmpty(preLevel)) {
                                sbMenu.append("<li id=\"top\"");
                                if (StringUtils.equals(this.top_root, menuInfo.getMenuId())) {
                                    sbMenu.append(" class=\"clicked\" ");
                                }
                                sbMenu.append(">");
                            } else if (preLevel.equals("3") || preLevel.equals("2")) {
                                sbMenu.append("</ul></li><li id=\"top\"");
                                if (StringUtils.equals(this.top_root, menuInfo.getMenuId())) {
                                    sbMenu.append(" class=\"clicked\" ");
                                }
                                sbMenu.append(">");
                            }
                            preLevel = menuInfo.getLevel();
                            sbMenu.append("<a onclick=\"goMenu('");
                            sbMenu.append(menuInfo.getMenuId());
                            sbMenu.append("','");
                            sbMenu.append(menuInfo.getUrl()); // 추가 2015.12.09 KYM
                            sbMenu.append("','');return false;\">");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</a>");
                            sbMenu.append("<ul style=\"width:380px;\">");
                        } else if (menuInfo.getLevel().equals("3")) {
                            preLevel = menuInfo.getLevel();
                            sbMenu.append("<li id=\"sub\"><a onclick=\"goMenu('");
                            sbMenu.append(menuInfo.getMenuId());
                            sbMenu.append("','");
                            sbMenu.append(menuInfo.getUrl()); // 추가 2015.12.09 KYM
                            sbMenu.append("','");
                            sbMenu.append(menuInfo.getUpMenuId());
                            sbMenu.append("');return false;\">");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</a></li>");
                        }
                    }
                }
            } else {
                final StringBuffer sb = new StringBuffer();
                sb.append(this.root + ";");

                /*************** 클릭한 URL의 최상위 메뉴 아이디 취득 *******************/
                if (StringUtils.isNotEmpty(this.menu_url)) {
                    try {
                        menuIdChk = StringUtils.defaultString(MenuMng.getMenuIdByUrl(this.menu_url));
                    } catch (final UserSysException e) {
                        LOGGER.warn(ExceptionUtils.getStackTrace(e));
                        throw new JspException(ExceptionUtils.getStackTrace(e));
                    }
                }
                if (!"".equals(menuIdChk)) {
                    topMenuId = this.getTopUpMenuId(menuIdChk, 0);
                    topMenuId = topMenuId.split("&")[0];
                }
                /*************** 클릭한 URL의 최상위 메뉴 아이디 취득 *******************/

                for (int i = 0; i < arList.size(); i++) {
                    menuInfo = arList.get(i);
                    if (menuInfo == null) {
                        menuInfo = new MenuInfo();
                    }
                    if (userInfo.getUser_lang().equals("KOR")) {
                        menuInfo.setMenuNm(menuInfo.getMenuNmKor());
                    } else if (userInfo.getUser_lang().equals("ENG")) {
                        menuInfo.setMenuNm(menuInfo.getMenuNmEng());
                    } else if (userInfo.getUser_lang().equals("JPN")) {
                        menuInfo.setMenuNm(menuInfo.getMenuNmJpn());
                    } else {
                        menuInfo.setMenuNm(menuInfo.getMenuNmKor());
                    }

                    if (menuInfo.getMenuId().equals(this.root)) {
                        rootLevel = Integer.parseInt(menuInfo.getLevel());
                        // rootName = StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor());
                        sbMenu.append("<nav>");
                        sbMenu.append("<ul>");
                        // 1depth
                    } else if (!menuInfo.getUpMenuId().equals("") && sb.toString().indexOf(menuInfo.getUpMenuId() + ";") != -1 && menuInfo.getLevel().equals(String.valueOf(rootLevel + 1))) {

                        if (!preLevel.equals("") && Integer.parseInt(preLevel) > Integer.parseInt(menuInfo.getLevel())) {
                            for (int z = 0; z < Integer.parseInt(preLevel) - Integer.parseInt(menuInfo.getLevel()); z++) {
                                sbMenu.append("</ul></li>");
                            }
                        }

                        if (menuInfo.getMenuType().equals("G")) {
                            // 메뉴 아이콘
                            menuIcon = menuInfo.getMenu_icon();

                            sbMenu.append("<li ");
                            if (menuInfo.getMenuId().equals(topMenuId)) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }

                            sbMenu.append("<a href=\"#\" >");
                            sbMenu.append("<i class=\"fa fa-lg fa-fw ");
                            sbMenu.append(menuIcon);
                            sbMenu.append("\">");
                            sbMenu.append("</i>");
                            sbMenu.append("<span class=\"menu-item-parent\"> ");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</span>");
                            sbMenu.append("</a>");
                            sbMenu.append("<ul>");
                        } else {
                            // 메뉴 아이콘
                            menuIcon = menuInfo.getMenu_icon();

                            sbMenu.append("<li");
                            if (this.menu_url != null && this.menu_url.equals(menuInfo.getUrl())) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }
                            sbMenu.append("<a href=\"" + CommonCode.CONTEXT_PATH + menuInfo.getUrl());
                            sbMenu.append("\">");
                            if (!"".equals(menuIcon)) {
                                sbMenu.append("<i class=\"fa fa-lg fa-fw ");
                                sbMenu.append(menuIcon);
                                sbMenu.append("\">");
                                sbMenu.append("</i>");
                            }
                            sbMenu.append("<span class=\"menu-item-parent\"> ");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</span>");

                            sbMenu.append("</a>");
                            sbMenu.append("</li>");

                        }
                        preLevel = menuInfo.getLevel();
                        // preMenuId = menuInfo.getMenuId();
                        // preMenuType = menuInfo.getMenuType();
                        sb.append(menuInfo.getMenuId() + ";");
                        // 2depth 이상
                    } else if (!menuInfo.getUpMenuId().equals("") && sb.toString().indexOf(menuInfo.getUpMenuId() + ";") != -1 && rootLevel < Integer.parseInt(menuInfo.getLevel()) - 1) {

                        if (Integer.parseInt(preLevel) > Integer.parseInt(menuInfo.getLevel())) {
                            for (int z = 0; z < Integer.parseInt(preLevel) - Integer.parseInt(menuInfo.getLevel()); z++) {
                                sbMenu.append("</ul></li>");
                            }
                        }

                        if (menuInfo.getMenuType().equals("G")) {
                            // 메뉴 아이콘
                            menuIcon = menuInfo.getMenu_icon();

                            boolean selFlg2 = false;
                            for (int j = 0; j < arList.size(); j++) {
                                final MenuInfo menuInfo2 = arList.get(j);
                                if (menuInfo.getMenuId().equals(menuInfo2.getUpMenuId())) {
                                    if (this.menu_url != null && this.menu_url.equals(menuInfo2.getUrl())) {
                                        selFlg2 = true;
                                    }
                                }
                            }
                            sbMenu.append("<li ");
                            if (selFlg2) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }

                            sbMenu.append("<a href=\"#\" >");
                            sbMenu.append("<i class=\"fa fa-lg fa-fw ");
                            sbMenu.append(menuIcon);
                            sbMenu.append("\">");
                            sbMenu.append("</i>");
                            sbMenu.append("<span class=\"menu-item-parent\"> ");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</span>");
                            sbMenu.append("</a>");
                            sbMenu.append("<ul>");
                        } else if (menuInfo.getMenuType().equals("W")) {
                            String width = "";
                            String height = "";
                            String url = "";
                            if (menuInfo.getUrl().matches(".*,.*")) {
                                url = CommonCode.CONTEXT_PATH + menuInfo.getUrl().substring(0, menuInfo.getUrl().indexOf(","));
                            } else {
                                url = CommonCode.CONTEXT_PATH + menuInfo.getUrl();
                            }

                            if (menuInfo.getUrl().matches(".*W=.*")) {
                                width = StringUtils.substringBetween(menuInfo.getUrl(), "W=", ",H=");
                            } else {
                                width = "1025";
                            }

                            if (menuInfo.getUrl().matches(".*H=.*")) {
                                height = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("H=") + 2);
                            } else {
                                height = "700";
                            }

                            sbMenu.append("<li");
                            if (this.menu_url != null && this.menu_url.equals(menuInfo.getUrl())) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }
                            sbMenu.append("<a onclick=\"AotCommon.goWindow('");
                            sbMenu.append(url);
                            sbMenu.append("', { width : " + width + ", height : " + height + "});");
                            sbMenu.append("\" href=\"javascript: void(0);\">");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</a>");
                            sbMenu.append("</li>");

                            // firstMenuYn = false;

                        } else if (menuInfo.getMenuType().equals("W")) {
                            String width = "";
                            String height = "";
                            String url = "";
                            if (menuInfo.getUrl().matches(".*,.*")) {
                                url = CommonCode.CONTEXT_PATH + menuInfo.getUrl().substring(0, menuInfo.getUrl().indexOf(","));
                            } else {
                                url = CommonCode.CONTEXT_PATH + menuInfo.getUrl();
                            }

                            if (menuInfo.getUrl().matches(".*W=.*")) {
                                width = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("W=") + 2, menuInfo.getUrl().indexOf(",H="));
                            } else {
                                width = "1025";
                            }

                            if (menuInfo.getUrl().matches(".*H=.*")) {
                                height = menuInfo.getUrl().substring(menuInfo.getUrl().indexOf("H=") + 2);
                            } else {
                                height = "700";
                            }

                            sbMenu.append("<li");
                            if (this.menu_url != null && this.menu_url.equals(menuInfo.getUrl())) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }
                            sbMenu.append("<a onclick=\"AotCommon.goWindow('");
                            sbMenu.append(url);
                            sbMenu.append("', { width : " + width + ", height : " + height + "});");
                            sbMenu.append("\" href=\"javascript: void(0);\">");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</a>");
                            sbMenu.append("</li>");

                            // firstMenuYn = false;

                        } else {
                            sbMenu.append("<li");
                            if (this.menu_url != null && this.menu_url.equals(menuInfo.getUrl())) {
                                sbMenu.append(" class=\"active\" >");
                            } else {
                                sbMenu.append(">");
                            }
                            sbMenu.append("<a href=\"" + CommonCode.CONTEXT_PATH + menuInfo.getUrl());
                            sbMenu.append("\">");
                            sbMenu.append(StringUtils.defaultIfEmpty(menuInfo.getMenuNm(), menuInfo.getMenuNmKor()));
                            sbMenu.append("</a>");
                            sbMenu.append("</li>");

                            // firstMenuYn = false;
                        }
                        preLevel = menuInfo.getLevel();
                        // preMenuId = menuInfo.getMenuId();
                        // preMenuType = menuInfo.getMenuType();
                        sb.append(menuInfo.getMenuId() + ";");

                    }
                }
            }
            if (preLevel.equals("")) {
                preLevel = "0";
            }

            if (Integer.parseInt(preLevel) >= Integer.parseInt(menuInfo.getLevel())) {
                for (int z = 0; z < Integer.parseInt(preLevel) - rootLevel - 1; z++) {
                    sbMenu.append("</ul></li>");
                }
            }
        }
        sbMenu.append("</ul></nav>");

        final JspWriter out = this.pageContext.getOut();
        try {
            out.print(sbMenu.toString());
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }

        return SKIP_BODY;
    }

    /**
     * @return the root
     */
    public String getRoot() {
        return StringUtils.defaultIfEmpty(this.root, "MENUID_0000");
    }

    /**
     * @param root the root to set
     */
    public void setRoot(final String root) {
        this.root = StringUtils.defaultIfEmpty(root, "MENUID_0000");
    }

    /**
     * 메뉴URL 설정
     *
     * @param menu_url 메뉴URL
     */
    public void setMenu_url(final String menu_url) {
        this.menu_url = menu_url;
    }

    /**
     * 메뉴URL 취득
     *
     * @return menu_url 메뉴URL
     */
    public String getMenu_url() {
        return StringUtils.defaultString(this.menu_url);
    }

    /**
     * 탑메뉴값 설정
     *
     * @param top_root 탑메뉴값
     */
    public void setTop_root(final String top_root) {
        this.top_root = top_root;
    }

    /**
     * 탑메뉴값 취득
     *
     * @return top_root 탑메뉴값
     */
    public String getTop_root() {
        return StringUtils.defaultString(this.top_root);
    }

    private String getTopUpMenuId(final String menuId, final int idx) throws JspException {
        final StringBuffer name = new StringBuffer();
        /* String name=""; */
        try {
            // MenuInfo menuInfo = MenuMng.getMenuInfo(menuId);
            final Hashtable<String, MenuInfo> arMenu = MenuMng.getMenuMap();
            LOGGER.debug("menuId : {}", menuId);
            final MenuInfo menuInfo = arMenu.get(menuId);
            final String upMenuId = menuInfo.getUpMenuId();

            MenuTag.LOGGER.debug("upMenuId   : " + upMenuId);

            if (!"".equals(StringUtils.defaultString(upMenuId)) && !"MENUID_0000".equals(StringUtils.defaultString(upMenuId))) {
                final int nextIdx = idx + 1;
                name.append(this.getTopUpMenuId(upMenuId, nextIdx));
                name.append(upMenuId);
                name.append("&");
            }
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }

        return name.toString();

    }

}
