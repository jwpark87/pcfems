/*****************************************************************************
 * 프로그램명  : MenuPageInfo.java
 * 설     명  : 페이지정보 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.12.10    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.basic;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 페이지정보 데이터빈
 *
 * @author eaction
 * @version 1.0
 */

public class MenuPageInfo implements Serializable {
    /**
     * UID
     */
    private static final long serialVersionUID = 442149973453095275L;

    /**
     * 페이지아이디
     */
    private String pageId = "";
    /**
     * 메뉴아이디
     */
    private String menuId = "";
    /**
     * 페이지명칭
     */
    private String pageNm = "";
    /**
     * 페이지명한글
     */
    private String page_nm_kor = "";
    /**
     * 페이지명영문
     */
    private String page_nm_eng = "";
    /**
     * 페이지명일어
     */
    private String page_nm_jpn = "";

    /**
     * 페이지명한글 설정
     *
     * @param page_nm_kor 페이지명한글
     */
    public void setPage_nm_kor(final String page_nm_kor) {
        this.page_nm_kor = page_nm_kor;
    }

    /**
     * 페이지명한글 취득
     *
     * @return page_nm_kor 페이지명한글
     */
    public String getPage_nm_kor() {
        return StringUtils.defaultString(this.page_nm_kor);
    }

    /**
     * 페이지명영문 설정
     *
     * @param page_nm_eng 페이지명영문
     */
    public void setPage_nm_eng(final String page_nm_eng) {
        this.page_nm_eng = page_nm_eng;
    }

    /**
     * 페이지명영문 취득
     *
     * @return page_nm_eng 페이지명영문
     */
    public String getPage_nm_eng() {
        return StringUtils.defaultString(this.page_nm_eng);
    }

    /**
     * 페이지명일어 설정
     *
     * @param page_nm_jpn 페이지명일어
     */
    public void setPage_nm_jpn(final String page_nm_jpn) {
        this.page_nm_jpn = page_nm_jpn;
    }

    /**
     * 페이지명일어 취득
     *
     * @return page_nm_jpn 페이지명일어
     */
    public String getPage_nm_jpn() {
        return StringUtils.defaultString(this.page_nm_jpn);
    }

    /**
     * 생성자
     */
    public MenuPageInfo() {
    }

    /**
     * 페이지아이디취득
     *
     * @return String 페이지아이디
     */
    public String getPageId() {
        return this.pageId;
    }

    /**
     * 페이지아이디설정
     *
     * @param str 페이지아이디
     */
    public void setPageId(final String str) {
        this.pageId = str;
    }

    /**
     * 메뉴아이디취득
     *
     * @return String 메뉴아이디
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 메뉴아이디설정
     *
     * @param str 메뉴아이디
     */
    public void setMenuId(final String str) {
        this.menuId = str;
    }

    /**
     * 페이지명칭취득
     *
     * @return String 페이지명칭
     */
    public String getPageNm() {
        return this.pageNm;
    }

    /**
     * 페이지명칭설정
     *
     * @param str 페이지명칭
     */
    public void setPageNm(final String str) {
        this.pageNm = str;
    }
}
