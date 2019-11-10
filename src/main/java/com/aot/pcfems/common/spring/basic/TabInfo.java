/*****************************************************************************
 * 프로그램명  : TabInfo.java
 * 설     명  : 화면별 탭표시 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2016.02.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.basic;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 화면별 탭표시 정보
 *
 * @author eaction
 * @version 1.0
 */

public class TabInfo implements Serializable {
    private transient static final long serialVersionUID = 8876011851931989679L;
    /**
     * 권한레벨
     */
    private String group_level = "";
    /**
     * 페이지아이디
     */
    private String page_id = "";
    /**
     * 탭마스타아이디
     */
    private String mast_id = "";
    /**
     * 활성화여부
     */
    private String enable_yn = "";
    /**
     * 보여주기여부
     */
    private String visible_yn = "";
    /**
     * 탭이름 한글
     */
    private String page_nm_kor = "";
    /**
     * 탭이름 영어
     */
    private String page_nm_eng = "";
    /**
     * 탭이름 일어
     */
    private String page_nm_jpn = "";
    /**
     * 클릭함수
     */
    private String onclick_func = "";
    /**
     * 정렬순서
     */
    private String order_no = "";
    /**
     * 탭표시이름
     */
    private String page_nm = "";

    /**
     * 탭표시이름 설정
     *
     * @param page_nm 탭표시이름
     */
    public void setPage_nm(final String page_nm) {
        this.page_nm = page_nm;
    }

    /**
     * 탭표시이름 취득
     *
     * @return page_nm 탭표시이름
     */
    public String getPage_nm() {
        return StringUtils.defaultString(this.page_nm);
    }

    /**
     * 권한레벨 설정
     *
     * @param group_level 권한레벨
     */
    public void setGroup_level(final String group_level) {
        this.group_level = group_level;
    }

    /**
     * 권한레벨 취득
     *
     * @return group_level 권한레벨
     */
    public String getGroup_level() {
        return StringUtils.defaultString(this.group_level);
    }

    /**
     * 페이지아이디 설정
     *
     * @param page_id 페이지아이디
     */
    public void setPage_id(final String page_id) {
        this.page_id = page_id;
    }

    /**
     * 페이지아이디 취득
     *
     * @return page_id 페이지아이디
     */
    public String getPage_id() {
        return StringUtils.defaultString(this.page_id);
    }

    /**
     * 탭마스타아이디 설정
     *
     * @param mast_id 탭마스타아이디
     */
    public void setMast_id(final String mast_id) {
        this.mast_id = mast_id;
    }

    /**
     * 탭마스타아이디 취득
     *
     * @return mast_id 탭마스타아이디
     */
    public String getMast_id() {
        return StringUtils.defaultString(this.mast_id);
    }

    /**
     * 활성화여부 설정
     *
     * @param enable_yn 활성화여부
     */
    public void setEnable_yn(final String enable_yn) {
        this.enable_yn = enable_yn;
    }

    /**
     * 활성화여부 취득
     *
     * @return enable_yn 활성화여부
     */
    public String getEnable_yn() {
        return StringUtils.defaultString(this.enable_yn);
    }

    /**
     * 보여주기여부 설정
     *
     * @param visible_yn 보여주기여부
     */
    public void setVisible_yn(final String visible_yn) {
        this.visible_yn = visible_yn;
    }

    /**
     * 보여주기여부 취득
     *
     * @return visible_yn 보여주기여부
     */
    public String getVisible_yn() {
        return StringUtils.defaultString(this.visible_yn);
    }

    /**
     * 탭이름 한글 설정
     *
     * @param page_nm_kor 탭이름 한글
     */
    public void setPage_nm_kor(final String page_nm_kor) {
        this.page_nm_kor = page_nm_kor;
    }

    /**
     * 탭이름 한글 취득
     *
     * @return page_nm_kor 탭이름 한글
     */
    public String getPage_nm_kor() {
        return StringUtils.defaultString(this.page_nm_kor);
    }

    /**
     * 탭이름 영어 설정
     *
     * @param page_nm_eng 탭이름 영어
     */
    public void setPage_nm_eng(final String page_nm_eng) {
        this.page_nm_eng = page_nm_eng;
    }

    /**
     * 탭이름 영어 취득
     *
     * @return page_nm_eng 탭이름 영어
     */
    public String getPage_nm_eng() {
        return StringUtils.defaultString(this.page_nm_eng);
    }

    /**
     * 탭이름 일어 설정
     *
     * @param page_nm_jpn 탭이름 일어
     */
    public void setPage_nm_jpn(final String page_nm_jpn) {
        this.page_nm_jpn = page_nm_jpn;
    }

    /**
     * 탭이름 일어 취득
     *
     * @return page_nm_jpn 탭이름 일어
     */
    public String getPage_nm_jpn() {
        return StringUtils.defaultString(this.page_nm_jpn);
    }

    /**
     * 클릭함수 설정
     *
     * @param onclick_func 클릭함수
     */
    public void setOnclick_func(final String onclick_func) {
        this.onclick_func = onclick_func;
    }

    /**
     * 클릭함수 취득
     *
     * @return onclick_func 클릭함수
     */
    public String getOnclick_func() {
        return StringUtils.defaultString(this.onclick_func);
    }

    /**
     * 정렬순서 설정
     *
     * @param order_no 정렬순서
     */
    public void setOrder_no(final String order_no) {
        this.order_no = order_no;
    }

    /**
     * 정렬순서 취득
     *
     * @return order_no 정렬순서
     */
    public String getOrder_no() {
        return StringUtils.defaultString(this.order_no);
    }
}
