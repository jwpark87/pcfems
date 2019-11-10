/*****************************************************************************
 * 프로그램명  : AuditLogSearchInfo.java
 * 설     명  : Audit Log 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.31   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.audit;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Audit Log 조회 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class AuditLogSearchInfo extends JqGridVO implements Serializable {
    private transient static final long serialVersionUID = 1697224305975982786L;
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
     * 변경제목
     */
    private String srch_audit_subject = "";
    /**
     * 변경 Description
     */
    private String srch_audit_desc = "";
    /**
     * 변경 전 데이타
     */
    private String srch_audit_before_data = "";
    /**
     * 변경 후 데이타
     */
    private String srch_audit_after_data = "";
    /**
     * 생성자
     */
    private String srch_crt_id = "";

    /**
     * 변경제목 설정
     *
     * @param srch_audit_subject 변경제목
     */
    public void setSrch_audit_subject(final String srch_audit_subject) {
        this.srch_audit_subject = srch_audit_subject;
    }

    /**
     * 변경제목 취득
     *
     * @return srch_audit_subject 변경제목
     */
    public String getSrch_audit_subject() {
        return StringUtils.defaultString(this.srch_audit_subject);
    }

    /**
     * 변경 Description 설정
     *
     * @param srch_audit_desc 변경 Description
     */
    public void setSrch_audit_desc(final String srch_audit_desc) {
        this.srch_audit_desc = srch_audit_desc;
    }

    /**
     * 변경 Description 취득
     *
     * @return srch_audit_desc 변경 Description
     */
    public String getSrch_audit_desc() {
        return StringUtils.defaultString(this.srch_audit_desc);
    }

    /**
     * 변경 전 데이타 설정
     *
     * @param srch_audit_before_data 변경 전 데이타
     */
    public void setSrch_audit_before_data(final String srch_audit_before_data) {
        this.srch_audit_before_data = srch_audit_before_data;
    }

    /**
     * 변경 전 데이타 취득
     *
     * @return srch_audit_before_data 변경 전 데이타
     */
    public String getSrch_audit_before_data() {
        return StringUtils.defaultString(this.srch_audit_before_data);
    }

    /**
     * 변경 후 데이타 설정
     *
     * @param srch_audit_after_data 변경 후 데이타
     */
    public void setSrch_audit_after_data(final String srch_audit_after_data) {
        this.srch_audit_after_data = srch_audit_after_data;
    }

    /**
     * 변경 후 데이타 취득
     *
     * @return srch_audit_after_data 변경 후 데이타
     */
    public String getSrch_audit_after_data() {
        return StringUtils.defaultString(this.srch_audit_after_data);
    }

    /**
     * 생성자 설정
     *
     * @param srch_crt_id 생성자
     */
    public void setSrch_crt_id(final String srch_crt_id) {
        this.srch_crt_id = srch_crt_id;
    }

    /**
     * 생성자 취득
     *
     * @return srch_crt_id 생성자
     */
    public String getSrch_crt_id() {
        return StringUtils.defaultString(this.srch_crt_id);
    }

    /**
     * 생성자
     */
    public AuditLogSearchInfo() {
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

}