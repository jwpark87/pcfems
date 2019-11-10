/*****************************************************************************
 * 프로그램명  : GroupMenuInfo.java
 * 설     명  : ȸ��그룹별메뉴지정 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.26   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.groupmenu;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 그룹별메뉴지정 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class GroupMenuInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -264862857622358188L;
    /**
     * 그룹레벨코드
     */
    private String groupLevel = "";
    /**
     * 그룹별 메뉴아이디 리스트
     */
    private String[] arMenuId = null;
    /**
     * 메뉴아이디 설정
     */
    private String menuId = "";
    /**
     * 상위 메뉴아이디 설정
     */
    private String upMenuId = "";
    /**
     * 등록시간
     */
    private String crtDt = "";
    /**
     * 갱신시간
     */
    private String updtDt = "";
    /**
     * 등록유저아이디
     */
    private String crtUser = "";
    /**
     * 갱신유저아이디
     */
    private String updtUser = "";
    /**
     * 갱신유저명
     */
    private String updtUserNm = "";
    /**
     * 유저아이디
     */
    private String userId = "";
    /**
     * 언어설정
     */
    private String lang = "";
    /**
     * 선택그룹레벨
     */
    private String selGroupLevel = "";
    /**
     * 시작메뉴
     */
    private String initMenu = "";
    /**
     * 레벨별화면제어
     */
    private String levelControl = "";

    /**
     * 레벨별화면제어 설정
     *
     * @param levelControl 레벨별화면제어
     */
    public void setLevelControl(final String levelControl) {
        this.levelControl = levelControl;
    }

    /**
     * 레벨별화면제어 취득
     *
     * @return levelControl 레벨별화면제어
     */
    public String getLevelControl() {
        return StringUtils.defaultString(this.levelControl);
    }

    /**
     * 시작메뉴 설정
     *
     * @param initMenu 시작메뉴
     */
    public void setInitMenu(final String initMenu) {
        this.initMenu = initMenu;
    }

    /**
     * 시작메뉴 취득
     *
     * @return initMenu 시작메뉴
     */
    public String getInitMenu() {
        return StringUtils.defaultString(this.initMenu);
    }

    /**
     * 선택그룹레벨 설정
     *
     * @param selGroupLevel 선택그룹레벨
     */
    public void setSelGroupLevel(final String selGroupLevel) {
        this.selGroupLevel = selGroupLevel;
    }

    /**
     * 선택그룹레벨 취득
     *
     * @return selGroupLevel 선택그룹레벨
     */
    public String getSelGroupLevel() {
        return StringUtils.defaultString(this.selGroupLevel);
    }

    /**
     * 그룹레벨코드 설정
     *
     * @param str 그룹레벨코드
     */
    public void setGroupLevel(final String str) {
        this.groupLevel = str;
    }

    /**
     * 그룹레벨코드 취득
     *
     * @return String 그룹레벨코드
     */
    public String getGroupLevel() {
        return this.groupLevel;
    }

    /**
     * 그룹별 메뉴아이디 리스트 설정
     *
     * @param str 그룹별 메뉴아이디 리스트
     */
    public void setArMenuId(final String[] str) {
        this.arMenuId = Arrays.copyOf(str, str.length);
    }

    /**
     * 그룹별 메뉴아이디 리스트 취득
     *
     * @return String[] 그룹별 메뉴아이디 리스트
     */
    public String[] getArMenuId() {
        return Arrays.copyOf(this.arMenuId, this.arMenuId.length);
    }

    /**
     * 그룹별 메뉴아이디 설정
     *
     * @param str 그룹별 메뉴아이디
     */
    public void setMenuId(final String str) {
        this.menuId = str;
    }

    /**
     * 그룹별 메뉴아이디 취득
     *
     * @return String 그룹별 메뉴아이디
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 상위 그룹별 메뉴아이디 설정
     *
     * @param str 상위 그룹별 메뉴아이디
     */
    public void setUpMenuId(final String str) {
        this.upMenuId = str;
    }

    /**
     * 상위 그룹별 메뉴아이디 취득
     *
     * @return String 상위 그룹별 메뉴아이디
     */
    public String getUpMenuId() {
        return this.upMenuId;
    }

    /**
     * 등록시간 설정
     *
     * @param str 등록시간
     */
    public void setCrtDt(final String str) {
        this.crtDt = str;
    }

    /**
     * 등록시간 취득
     *
     * @return String 등록시간
     */
    public String getCrtDt() {
        return this.crtDt;
    }

    /**
     * 갱신시간 설정
     *
     * @param str 갱신시간
     */
    public void setUpdtDt(final String str) {
        this.updtDt = str;
    }

    /**
     * 갱신시간 취득
     *
     * @return String 갱신시간
     */
    public String getUpdtDt() {
        return this.updtDt;
    }

    /**
     * 등록유저 설정
     *
     * @param str 등록유저
     */
    public void setCrtUser(final String str) {
        this.crtUser = str;
    }

    /**
     * 등록유저 취득
     *
     * @return String 등록유저
     */
    public String getCrtUser() {
        return this.crtUser;
    }

    /**
     * 갱신유저 설정
     *
     * @param str 갱신유저
     */
    public void setUpdtUser(final String str) {
        this.updtUser = str;
    }

    /**
     * 갱신유저 취득
     *
     * @return String 갱신유저
     */
    public String getUpdtUser() {
        return this.updtUser;
    }

    /**
     * 갱신유저명 설정
     *
     * @param str 갱신유저명
     */
    public void setUpdtUserNm(final String str) {
        this.updtUserNm = str;
    }

    /**
     * 갱신유저명 취득
     *
     * @return String 갱신유저명
     */
    public String getUpdtUserNm() {
        return this.updtUserNm;
    }

    /**
     * 유저아이디 설정
     *
     * @param str 유저아이디
     */
    public void setUserId(final String str) {
        this.userId = str;
    }

    /**
     * 유저아이디 취득
     *
     * @return String 유저아이디
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 언어설정 설정
     *
     * @param str 언어설정
     */
    public void setLang(final String str) {
        this.lang = str;
    }

    /**
     * 언어설정 취득
     *
     * @return String 언어설정
     */
    public String getLang() {
        return this.lang;
    }
}