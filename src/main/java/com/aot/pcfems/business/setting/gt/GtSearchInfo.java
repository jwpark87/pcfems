/*****************************************************************************
 * 프로그램명  : DiameterSearchInfo.java
 * 설     명  : Diameter Host 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.03.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.gt;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class GtSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 736639073826561432L;
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
     * DB Name
     */
    private String db_name = "";
    /**
     * GT
     */
    private String srch_gt = "";
    /**
     * PC LIST NAME(EX: INTL_TEMP_2348)
     */
    private String pc_list_nm = "";
    /**
     * 국가
     */
    private String country = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * 출력여부
     */
    private String dsbd_yn = "";
    /**
     * 출력여부명
     */
    private String dsbd_yn_nm = "";

    /**
     * 출력여부 설정
     *
     * @param dsbd_yn 출력여부
     */
    public void setDsbd_yn(final String dsbd_yn) {
        this.dsbd_yn = dsbd_yn;
    }

    /**
     * 출력여부 취득
     *
     * @return dsbd_yn 출력여부
     */
    public String getDsbd_yn() {
        return StringUtils.defaultString(this.dsbd_yn);
    }

    /**
     * 출력여부명 설정
     *
     * @param dsbd_yn_nm 출력여부명
     */
    public void setDsbd_yn_nm(final String dsbd_yn_nm) {
        this.dsbd_yn_nm = dsbd_yn_nm;
    }

    /**
     * 출력여부명 취득
     *
     * @return dsbd_yn_nm 출력여부명
     */
    public String getDsbd_yn_nm() {
        return StringUtils.defaultString(this.dsbd_yn_nm);
    }

    /**
     * DB Name 설정
     *
     * @param db_name DB Name
     */
    public void setDb_name(final String db_name) {
        this.db_name = db_name;
    }

    /**
     * DB Name 취득
     *
     * @return db_name DB Name
     */
    public String getDb_name() {
        return StringUtils.defaultString(this.db_name);
    }

    /**
     * GT 설정
     *
     * @param srch_gt GT
     */
    public void setSrch_gt(final String srch_gt) {
        this.srch_gt = srch_gt;
    }

    /**
     * GT 취득
     *
     * @return srch_gt GT
     */
    public String getSrch_gt() {
        return StringUtils.defaultString(this.srch_gt);
    }

    /**
     * PC LIST NAME(EX: INTL_TEMP_2348) 설정
     *
     * @param pc_list_nm PC LIST NAME(EX: INTL_TEMP_2348)
     */
    public void setPc_list_nm(final String pc_list_nm) {
        this.pc_list_nm = pc_list_nm;
    }

    /**
     * PC LIST NAME(EX: INTL_TEMP_2348) 취득
     *
     * @return pc_list_nm PC LIST NAME(EX: INTL_TEMP_2348)
     */
    public String getPc_list_nm() {
        return StringUtils.defaultString(this.pc_list_nm);
    }

    /**
     * 국가 설정
     *
     * @param country 국가
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * 국가 취득
     *
     * @return country 국가
     */
    public String getCountry() {
        return StringUtils.defaultString(this.country);
    }

    /**
     * 사업자명 설정
     *
     * @param carrier_nm 사업자명
     */
    public void setCarrier_nm(final String carrier_nm) {
        this.carrier_nm = carrier_nm;
    }

    /**
     * 사업자명 취득
     *
     * @return carrier_nm 사업자명
     */
    public String getCarrier_nm() {
        return StringUtils.defaultString(this.carrier_nm);
    }

    /**
     * 생성자
     */
    public GtSearchInfo() {
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

}