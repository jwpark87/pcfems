/*****************************************************************************
 * 프로그램명  : CountrySearchInfo.java
 * 설     명  : COUNTRY설정정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.country;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * COUNTRY설정정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class CountrySearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 4495501729249565290L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
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
     * Prefix
     */
    private String srch_prefix = "";
    /**
     * 검색용COUNTRY코드
     */
    private String srch_country_cd = "";
    /**
     * 검색용COUNTRY코드명칭
     */
    private String srch_country_cd_name = "";

    /**
     * 생성자
     */
    public CountrySearchInfo() {
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
     * Prefix 설정
     *
     * @param srch_prefix Prefix
     */
    public void setSrch_prefix(final String srch_prefix) {
        this.srch_prefix = srch_prefix;
    }

    /**
     * Prefix 취득
     *
     * @return srch_prefix Prefix
     */
    public String getSrch_prefix() {
        return StringUtils.defaultString(this.srch_prefix);
    }

    /**
     * 검색용COUNTRY코드 설정
     *
     * @param srch_country_cd 검색용COUNTRY코드
     */
    public void setSrch_country_cd(final String srch_country_cd) {
        this.srch_country_cd = srch_country_cd;
    }

    /**
     * 검색용COUNTRY코드 취득
     *
     * @return srch_country_cd 검색용COUNTRY코드
     */
    public String getSrch_country_cd() {
        return StringUtils.defaultString(this.srch_country_cd);
    }

    /**
     * 검색용COUNTRY코드명칭 설정
     *
     * @param srch_country_cd_name 검색용COUNTRY코드명칭
     */
    public void setSrch_country_cd_name(final String srch_country_cd_name) {
        this.srch_country_cd_name = srch_country_cd_name;
    }

    /**
     * 검색용COUNTRY코드명칭 취득
     *
     * @return srch_country_cd_name 검색용COUNTRY코드명칭
     */
    public String getSrch_country_cd_name() {
        return StringUtils.defaultString(this.srch_country_cd_name);
    }
}