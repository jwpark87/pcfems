/*****************************************************************************
 * 프로그램명  : NaviTag.java
 * 설     명  : 네비게이션 표시 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.12.09  LYS      1.0    초기작성
 *****************************************************************************/
package com.aot.pcfems.common.taglib;

import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.MenuInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 왼쪽보드메뉴표시태그라이브러리 설정
 *
 * @author eaction
 * @version 1.0
 */
public class NaviTag extends TagSupport {
    private transient static final long serialVersionUID = -6633909246128863066L;
    private static final Logger LOGGER = LoggerFactory.getLogger(NaviTag.class);
    /**
     * 검색대상 게시판 메뉴 아이디
     */
    private String pageId = "";
    /**
     * EXCEL에서 사용하는 navi 여부
     */
    private String excel = "";
    /**
     * 고유 id value
     */
    private String id = "";
    /**
     * 팝업 navi 여부
     */
    private String popYn = "";
    /**
     * 메뉴URL
     */
    private String menuUrl = "";

    /**
     * 시작 태그 처리
     *
     * @return String 권한별표시여부태그문자열
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            final UserVO userInfo = (UserVO) this.pageContext.getSession().getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

            final StringBuffer sbBoardMenu = new StringBuffer();
            // menu_url로 검색
            final String menuId = MenuMng.getMenuIdByUrl(this.menuUrl);
            final String pageNm = StringUtils.defaultString(MenuMng.getMenuName(menuId, userInfo));

            sbBoardMenu.append("<li>Home</li>");

            if (!"".equals(StringUtils.defaultString(menuId)) && !this.popYn.equals("Y")) {
                // 페이지의 메뉴명칭과 상위메뉴들을 구성한다
                sbBoardMenu.append(this.getNavigationName(menuId, menuId, pageNm, 0, userInfo));
            } else {
                sbBoardMenu.append(this.getNavigationName2(menuId, menuId, pageNm, 0));
            }
            sbBoardMenu.append("<li>");
            /****************** 마지막 화면 이름 *************************/
            sbBoardMenu.append(pageNm, 0, pageNm.lastIndexOf(">") + 1);

            // 화면타이틀 추가
            sbBoardMenu.append(pageNm.substring(pageNm.lastIndexOf(">") + 1));
            /****************** 마지막 화면 이름 *************************/
            sbBoardMenu.append("</li>");
            final JspWriter out = this.pageContext.getOut();
            out.print(sbBoardMenu.toString());
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return SKIP_BODY;
    }

    /**
     * 최종 메뉴명 직전 카테고리까지만 취득
     *
     * @return String 권한별표시여부태그문자열
     * @throws JspException
     */
    private String getNavigationName(final String menuId, final String me, final String pageNm, final int idx, final UserVO userInfo) throws JspException {
        final StringBuffer name = new StringBuffer();
        try {
            // Hashtable<String, MenuInfo> arMenu = MenuMng.getMenuMap();
            MenuInfo menuInfo = MenuMng.getMenuInfo(menuId);
            if (menuInfo == null) {
                menuInfo = new MenuInfo();
            }
            final String upMenuId = menuInfo.getUpMenuId();
            if (userInfo.getUser_lang().equals("KOR")) {
                menuInfo.setMenuNm(menuInfo.getMenuNmKor());
            } else if (userInfo.getUser_lang().equals("ENG")) {
                menuInfo.setMenuNm(menuInfo.getMenuNmEng());
            } else if (userInfo.getUser_lang().equals("JPN")) {
                menuInfo.setMenuNm(menuInfo.getMenuNmJpn());
            } else {
                menuInfo.setMenuNm(menuInfo.getMenuNmKor());
            }

            if (!"".equals(StringUtils.defaultString(upMenuId)) && !"-1".equals(StringUtils.defaultString(upMenuId))) {
                final int nextIdx = idx + 1;
                final String tmpNavtName = this.getNavigationName(upMenuId, me, pageNm, nextIdx, userInfo);
                if (idx == 0) {
                    name.append(tmpNavtName);
                } else {
                    name.append(tmpNavtName);
                    name.append("<li>");
                    name.append(menuInfo.getMenuNm());
                    name.append("&nbsp;");
                    name.append("</li>");
                }
            } else {
            }

        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return name.toString();

    }

    private String getNavigationName2(final String menuId, final String me, final String pageNm, final int idx) throws JspException {
        final StringBuffer name = new StringBuffer();
        try {
            // name.append("<img src=\"");
            // name.append(CommonCode.PATH_PUBLISH + "/img");
            // name.append("/icon_home.gif\" align=\"absmiddle\">&nbsp;");
            name.append("&nbsp;<i class=\"fa fa-home\" style=\"font-size: 16px;\" aria-hidden=\"true\"></i>");
            // name.append(pageNm);
            // name.append("&gt;");
        } catch (final Exception e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return name.toString();

    }

    /**
     * 페이지아이디프로퍼티 취득
     *
     * @return String 페이지아이디프로퍼티
     */
    public String getPageId() {
        return this.pageId;
    }

    /**
     * 페이지아이디프로퍼티 설정
     *
     * @param str 페이지아이디프로퍼티
     */
    public void setPageId(final String str) {
        this.pageId = str;
    }

    /**
     * 페이지아이디프로퍼티 취득
     *
     * @return String 페이지아이디프로퍼티
     */
    public String getExcel() {
        return this.excel;
    }

    /**
     * 페이지아이디프로퍼티 설정
     *
     * @param str 페이지아이디프로퍼티
     */
    public void setExcel(final String str) {
        this.excel = str;
    }

    /**
     * 페이지아이디프로퍼티 취득
     *
     * @return String 페이지아이디프로퍼티
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 페이지아이디프로퍼티 설정
     *
     * @param str 페이지아이디프로퍼티
     */
    @Override
    public void setId(final String str) {
        this.id = str;
    }

    /**
     * 페이지아이디프로퍼티 취득
     *
     * @return String 페이지아이디프로퍼티
     */
    public String getPopYn() {
        return this.popYn;
    }

    /**
     * 페이지아이디프로퍼티 설정
     *
     * @param str 페이지아이디프로퍼티
     */
    public void setPopYn(final String str) {
        this.popYn = str;
    }

    /**
     * 메뉴URL 설정
     *
     * @param menuUrl 메뉴URL
     */
    public void setMenuUrl(final String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 메뉴URL 취득
     *
     * @return menuUrl 메뉴URL
     */
    public String getMenuUrl() {
        return this.menuUrl;
    }
}
