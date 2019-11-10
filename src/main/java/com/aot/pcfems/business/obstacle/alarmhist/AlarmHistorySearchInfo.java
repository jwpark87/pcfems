/*****************************************************************************
 * 프로그램명  : AlarmHistorySearchInfo.java
 * 설     명  : 알람이력조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   HSI     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmhist;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 알람이력조회 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class AlarmHistorySearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 8376551028887420649L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
    /**
     * 페이지
     */
    private String srch_page = "";
    /**
     * 시작인덱스
     */
    private int startIndex = 0;
    /**
     * 종료인덱스
     */
    private int endIndex = 0;

    /**
     * 검색키
     */
    private String srch_key = "";
    /**
     * 검색값
     */
    private String srch_val = "";

    /**
     * 검색용FROM날짜
     */
    private String srch_date_from = "";
    /**
     * 검색용TO날짜
     */
    private String srch_date_to = "";
    /**
     * 검색용그룹ID
     */
    private String srch_sbc_group_id = "";
    /**
     * 검색용진행시간
     */
    private String srch_ing_time_s = "";
    private String srch_alm_host = "";
    private String srch_alm_remark1 = "";
    private String srch_alm_code = "";
    /**
     * 알람 등급
     */
    private String srch_alm_level = "";
    private String srch_alm_instance = "";
    private String srch_alm_msg = "";
    /**
     * 알람 등급 배열
     */
    private String[] ar_search_alm_level = null;

    public String getSrch_alm_level() {
        return this.srch_alm_level;
    }

    public void setSrch_alm_level(final String srch_alm_level) {
        this.srch_alm_level = srch_alm_level;
    }

    public String[] getAr_search_alm_level() {
        return Arrays.copyOf(this.ar_search_alm_level, this.ar_search_alm_level.length);
    }

    public void setAr_search_alm_level(final String[] ar_search_alm_level) {
        this.ar_search_alm_level = Arrays.copyOf(ar_search_alm_level, ar_search_alm_level.length);
    }

    public String getSrch_alm_code() {
        return this.srch_alm_code;
    }

    public void setSrch_alm_code(final String srch_alm_code) {
        this.srch_alm_code = srch_alm_code;
    }

    public String getSrch_alm_remark1() {
        return this.srch_alm_remark1;
    }

    public void setSrch_alm_remark1(final String srch_alm_remark1) {
        this.srch_alm_remark1 = srch_alm_remark1;
    }

    public String getSrch_alm_host() {
        return this.srch_alm_host;
    }

    public void setSrch_alm_host(final String srch_alm_host) {
        this.srch_alm_host = srch_alm_host;
    }

    /**
     * 생성자
     */
    public AlarmHistorySearchInfo() {
    }

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public String getUser_lang() {
        return this.user_lang;
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 우편번호
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 엑셀여부 설정
     *
     * @param search_excel_type 엑셀여부
     */
    public void setSearch_excel_type(final String search_excel_type) {
        this.search_excel_type = search_excel_type;
    }

    /**
     * 엑셀여부 취득
     *
     * @return search_excel_type 엑셀여부
     */
    public String getSearch_excel_type() {
        return StringUtils.defaultString(this.search_excel_type);
    }

    /**
     * 페이지 설정
     *
     * @param srch_page 페이지
     */
    public void setSrch_page(final String srch_page) {
        this.srch_page = srch_page;
    }

    /**
     * 페이지 취득
     *
     * @return srch_page 페이지
     */
    public String getSrch_page() {
        return StringUtils.defaultString(this.srch_page);
    }

    /**
     * 시작인덱스설정
     *
     * @param str 시작인덱스
     */
    public void setStartIndex(final int str) {
        this.startIndex = str;
    }

    /**
     * 시작인덱스취득
     *
     * @return int 시작인덱스
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /**
     * 종료인덱스설정
     *
     * @param str 종료인덱스
     */
    public void setEndIndex(final int str) {
        this.endIndex = str;
    }

    /**
     * 종료인덱스취득
     *
     * @return int 종료인덱스
     */
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * 검색키설정
     *
     * @param srch_key 검색키
     */
    public void setSearch_key(final String srch_key) {
        this.srch_key = StringUtils.defaultString(srch_key);
    }

    /**
     * 검색키취득
     *
     * @return srch_key 검색키
     */
    public String getSearch_key() {
        return this.srch_key;
    }

    /**
     * 검색값설정
     *
     * @param srch_val 검색키
     */
    public void setSearch_val(final String srch_val) {
        this.srch_val = StringUtils.defaultString(srch_val);
    }

    /**
     * 검색값취득
     *
     * @return srch_val 검색값
     */
    public String getSearch_val() {
        return this.srch_val;
    }

    /**
     * 검색용FROM날짜 설정
     *
     * @param srch_date_from 검색용FROM날짜
     */
    public void setSrch_date_from(final String srch_date_from) {
        this.srch_date_from = srch_date_from;
    }

    /**
     * 검색용FROM날짜 취득
     *
     * @return srch_date_from 검색용FROM날짜
     */
    public String getSrch_date_from() {
        return StringUtils.defaultString(this.srch_date_from);
    }

    /**
     * 검색용TO날짜 설정
     *
     * @param srch_date_to 검색용TO날짜
     */
    public void setSrch_date_to(final String srch_date_to) {
        this.srch_date_to = srch_date_to;
    }

    /**
     * 검색용TO날짜 취득
     *
     * @return srch_date_to 검색용TO날짜
     */
    public String getSrch_date_to() {
        return StringUtils.defaultString(this.srch_date_to);
    }

    /**
     * 검색용그룹ID 설정
     *
     * @param srch_sbc_group_id 검색용그룹ID
     */
    public void setSrch_sbc_group_id(final String srch_sbc_group_id) {
        this.srch_sbc_group_id = srch_sbc_group_id;
    }

    /**
     * 검색용그룹ID 취득
     *
     * @return srch_sbc_group_id 검색용그룹ID
     */
    public String getSrch_sbc_group_id() {
        return StringUtils.defaultString(this.srch_sbc_group_id);
    }

    /**
     * 검색용진행시간 설정
     *
     * @param srch_ing_time_s 검색용진행시간
     */
    public void setSrch_ing_time_s(final String srch_ing_time_s) {
        this.srch_ing_time_s = srch_ing_time_s;
    }

    /**
     * 검색용진행시간 취득
     *
     * @return srch_ing_time_s 검색용진행시간
     */
    public String getSrch_ing_time_s() {
        return StringUtils.defaultString(this.srch_ing_time_s);
    }

    public String getSrch_alm_instance() {
        return this.srch_alm_instance;
    }

    public void setSrch_alm_instance(final String srch_alm_instance) {
        this.srch_alm_instance = srch_alm_instance;
    }

    public String getSrch_key() {
        return this.srch_key;
    }

    public void setSrch_key(final String srch_key) {
        this.srch_key = srch_key;
    }

    public String getSrch_val() {
        return this.srch_val;
    }

    public void setSrch_val(final String srch_val) {
        this.srch_val = srch_val;
    }

    public String getSrch_alm_msg() {
        return this.srch_alm_msg;
    }

    public void setSrch_alm_msg(final String srch_alm_msg) {
        this.srch_alm_msg = srch_alm_msg;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}