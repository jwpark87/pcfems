/*****************************************************************************
 * 프로그램명  : MenuInfo.java
 * 설     명  : ȸ��메뉴관리  데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.11   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.constant.CommonCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 메뉴관리 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class MenuInfo implements Serializable {
    /**
     * UID
     */
    private static final long serialVersionUID = 3046483563644426095L;

    /**
     * 메뉴아이디
     */
    private String menuId = "";
    /**
     * 페이지아이디
     */
    private String pageId = "";
    /**
     * 메뉴이름
     */
    private String menuNm = "";
    /**
     * 메뉴이름한글
     */
    private String menuNmKor = "";
    /**
     * 메뉴이름영문
     */
    private String menuNmEng = "";
    /**
     * 메뉴이름일본어
     */
    private String menuNmJpn = "";
    /**
     * 상위메뉴아이디
     */
    private String upMenuId = "";
    /**
     * 메뉴타입
     */
    private String menuType = "";
    /**
     * 일련번호
     */
    private int seq = 0;
    /**
     * 메뉴의 URL
     */
    private String url = "";
    /**
     * 등록유저아이디
     */
    private String crtUser = "";
    /**
     * 업데이터유저
     */
    private String updtUser = "";
    /**
     * 수정자명
     */
    private String updtUserNm = "";
    /**
     * 등록날짜
     */
    private String crtDt = "";
    /**
     * 갱신날짜
     */
    private String updtDt = "";
    /**
     * 메뉴레벨
     */
    private String level = "";
    /**
     * 말단 항목여부
     */
    private String isEnd = "";
    /**
     * 그룹 레벨
     */
    private String groupLevel = "";
    /**
     * 유저그룹별 메뉴관리의 체크정보
     */
    private String isChecked = "";

    /**
     * 메뉴종류
     */
    private String menuKind = "";
    /**
     * 메뉴길이
     */
    private String menuWidth = "";
    /**
     * 메뉴아이콘
     */
    private String menu_icon = "";

    /**
     * 메뉴아이콘 설정
     *
     * @param menu_icon 메뉴아이콘
     */
    public void setMenu_icon(final String menu_icon) {
        this.menu_icon = menu_icon;
    }

    /**
     * 메뉴아이콘 취득
     *
     * @return menu_icon 메뉴아이콘
     */
    public String getMenu_icon() {
        return StringUtils.defaultString(this.menu_icon);
    }

    /**
     * 메뉴아이디 설정
     *
     * @param str 메뉴아이디
     */
    public void setMenuId(final String str) {
        this.menuId = StringUtils.defaultString(str);
    }

    /**
     * 메뉴아이디 취득
     *
     * @return String 메뉴아이디
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 메뉴이름 설정
     *
     * @param str 메뉴이름
     */
    public void setMenuNm(final String str) {
        this.menuNm = StringUtils.defaultString(str);
    }

    /**
     * 메뉴이름 취득
     *
     * @return String 메뉴이름
     */
    public String getMenuNm() {
        return this.menuNm;
    }

    /**
     * 메뉴이름한글 설정
     *
     * @param str 메뉴이름한글
     */
    public void setMenuNmKor(final String str) {
        this.menuNmKor = StringUtils.defaultString(str);
    }

    /**
     * 메뉴이름한글 취득
     *
     * @return String 메뉴이름한글
     */
    public String getMenuNmKor() {
        return this.menuNmKor;
    }

    /**
     * 메뉴이름영문 설정
     *
     * @param str 메뉴이름영문
     */
    public void setMenuNmEng(final String str) {
        this.menuNmEng = StringUtils.defaultString(str);
    }

    /**
     * 메뉴이름영문 취득
     *
     * @return String 메뉴이름영문
     */
    public String getMenuNmEng() {
        return this.menuNmEng;
    }

    /**
     * 메뉴이름일본어 설정
     *
     * @param str 메뉴이름일본어
     */
    public void setMenuNmJpn(final String str) {
        this.menuNmJpn = StringUtils.defaultString(str);
    }

    /**
     * 메뉴이름일본어 취득
     *
     * @return String 메뉴이름일본어
     */
    public String getMenuNmJpn() {
        return this.menuNmJpn;
    }

    /**
     * 상위메뉴아이디 설정
     *
     * @param str 상위메뉴아이디
     */
    public void setUpMenuId(final String str) {
        this.upMenuId = StringUtils.defaultString(str);
    }

    /**
     * 상위메뉴아이디 취득
     *
     * @return String 상위메뉴아이디
     */
    public String getUpMenuId() {
        return this.upMenuId;
    }

    /**
     * 메뉴타입 설정
     *
     * @param str 메뉴타입
     */
    public void setMenuType(final String str) {
        this.menuType = StringUtils.defaultString(str);
    }

    /**
     * 메뉴타입 취득
     *
     * @return String 메뉴타입
     */
    public String getMenuType() {
        return this.menuType;
    }

    /**
     * 일련번호설정
     *
     * @param str 일련번호
     */
    public void setSeq(final int str) {
        this.seq = str;
    }

    /**
     * 일련번호취득
     *
     * @return no 일련번호
     */
    public int getSeq() {
        return this.seq;
    }

    /**
     * 메뉴의 URL 설정
     *
     * @param str 메뉴의 URL
     */
    public void setUrl(final String str) {
        this.url = StringUtils.defaultString(str);
    }

    /**
     * 메뉴의 URL 취득
     *
     * @return String 메뉴의 URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 등록유저아이디 설정
     *
     * @param str 등록유저아이디
     */
    public void setCrtUser(final String str) {
        this.crtUser = StringUtils.defaultString(str);
    }

    /**
     * 등록유저아이디 취득
     *
     * @return String 등록유저아이디
     */
    public String getCrtUser() {
        return this.crtUser;
    }

    /**
     * 업데이터유저 설정
     *
     * @param str 업데이터유저
     */
    public void setUpdtUser(final String str) {
        this.updtUser = StringUtils.defaultString(str);
    }

    /**
     * 업데이터유저 취득
     *
     * @return String 업데이터유저
     */
    public String getUpdtUser() {
        return this.updtUser;
    }

    /**
     * 수정자명 설정
     *
     * @param updtUserNm 수정자명
     */
    public void setUpdtUserNm(final String updtUserNm) {
        this.updtUserNm = updtUserNm;
    }

    /**
     * 수정자명 취득
     *
     * @return updtUserNm 수정자명
     */
    public String getUpdtUserNm() {
        return StringUtils.defaultString(this.updtUserNm);
    }

    /**
     * 등록날짜 설정
     *
     * @param str 등록날짜
     */
    public void setCrtDt(final String str) {
        this.crtDt = StringUtils.defaultString(str);
    }

    /**
     * 등록날짜 취득
     *
     * @return String 등록날짜
     */
    public String getCrtDt() {
        return this.crtDt;
    }

    /**
     * 갱신날짜 설정
     *
     * @param str 갱신날짜
     */
    public void setUpdtDt(final String str) {
        this.updtDt = StringUtils.defaultString(str);
    }

    /**
     * 갱신날짜 취득
     *
     * @return String 갱신날짜
     */
    public String getUpdtDt() {
        return this.updtDt;
    }

    /**
     * 메뉴레벨 설정
     *
     * @param str 메뉴레벨
     */
    public void setLevel(final String str) {
        this.level = StringUtils.defaultString(str);
    }

    /**
     * 메뉴레벨 취득
     *
     * @return String 메뉴레벨
     */
    public String getLevel() {
        return this.level;
    }

    /**
     * 말단항목여부체크 설정
     *
     * @param str 말단항목여부체크
     */
    public void setIsEnd(final String str) {
        this.isEnd = str;
    }

    /**
     * 말단항목여부체크 취득
     *
     * @return String 말단항목여부체크
     */
    public String getIsEnd() {
        return this.isEnd;
    }

    /**
     * 하위메뉴입력버튼을 사용가능하게 할지 사용불가로 할지 결정
     *
     * @return disabled문자열
     */
    public boolean checkDownMenu() {
        boolean bReturn = false;
        if ("G".equals(this.getMenuType())) {
            bReturn = true;
        }
        return bReturn;
    }

    /**
     * 메뉴입력버튼을 사용가능하게 할지 사용불가로 할지 결정
     *
     * @return disabled문자열
     */
    public boolean checkInputMenu() {
        boolean bReturn = false;
        if (this.getSeq() != 1) {
            bReturn = true;
        }
        return bReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     *
     * @return disabled문자열
     */
    public boolean checkDelete() {
        boolean bReturn = false;
        if (CommonCode.YES.equals(this.getIsEnd())) {
            bReturn = true;
        }
        return bReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     *
     * @return disabled문자열
     */
    public String checkMenuType() {
        String strReturn = "readOnly";
        if (CommonCode.YES.equals(this.getIsEnd())) {
            strReturn = "";
        }
        return strReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     *
     * @return disabled문자열
     */
    public String checkUrl() {
        String strReturn = "";
        if ("G".equals(this.getMenuType())) {
            strReturn = "readOnly";
        }
        return strReturn;
    }

    /**
     * 그룹레벨값취득
     *
     * @return String 그룹레벨값
     */
    public String getGroupLevel() {
        return this.groupLevel;
    }

    /**
     * 그룹레벨값설정
     *
     * @param str 그룹레벨값
     */
    public void setGroupLevel(final String str) {
        this.groupLevel = str;
    }

    /**
     * 유저그룹별 메뉴관리의 체크정보취득
     *
     * @return String 유저그룹별 메뉴관리의 체크정보
     */
    public String getIsChecked() {
        return this.isChecked;
    }

    /**
     * 유저그룹별 메뉴관리의 체크정보설정
     *
     * @param str 유저그룹별 메뉴관리의 체크정보
     */
    public void setIsChecked(final String str) {
        this.isChecked = str;
    }

    /**
     * 메뉴종류 설정
     *
     * @param str 메뉴종류
     */
    public void setMenuKind(final String str) {
        this.menuKind = StringUtils.defaultString(str);
    }

    /**
     * 메뉴종류 취득
     *
     * @return String 메뉴종류
     */
    public String getMenuKind() {
        return this.menuKind;
    }

    /**
     * 메뉴길이 설정
     *
     * @param str 메뉴길이
     */
    public void setMenuWidth(final String str) {
        this.menuWidth = StringUtils.defaultString(str);
    }

    /**
     * 메뉴길이 취득
     *
     * @return String 메뉴길이
     */
    public String getMenuWidth() {
        return this.menuWidth;
    }

    public String getPageId() {
        return StringUtils.defaultString(this.pageId);
    }

    public void setPageId(final String pageId) {
        this.pageId = pageId;
    }

    public String getMenuTitle() {
        return StringUtil.printMenuTitle(this.getLevel());
    }

}