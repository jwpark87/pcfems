/*****************************************************************************
 * 프로그램명  : LinkSearchInfo.java
 * 설     명  : Link 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.03.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.linkset;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Link 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class LinksetSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 7731930810000698071L;
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
     * 국소(장비명)
     */
    private String srch_locality = "";
    /**
     * NA
     */
    private String na = "";
    /**
     * Access Point Code
     */
    private String srch_apc = "";
    /**
     * 국가
     */
    private String country = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * CLLI NAME(EX: BEL_WIS_Belgium_0)
     */
    private String clli = "";
    /**
     * 상태(그룹코드: LINK_STATUS)
     */
    private String status = "";
    /**
     * Dashboard 출력 여부(Y/N)
     */
    private String dsbd_yn = "";

    /**
     * 국소(장비명) 설정
     *
     * @param srch_locality 국소(장비명)
     */
    public void setSrch_locality(final String srch_locality) {
        this.srch_locality = srch_locality;
    }

    /**
     * 국소(장비명) 취득
     *
     * @return srch_locality 국소(장비명)
     */
    public String getSrch_locality() {
        return StringUtils.defaultString(this.srch_locality);
    }

    /**
     * NA 설정
     *
     * @param na NA
     */
    public void setNa(final String na) {
        this.na = na;
    }

    /**
     * NA 취득
     *
     * @return na NA
     */
    public String getNa() {
        return StringUtils.defaultString(this.na);
    }

    /**
     * Access Point Code 설정
     *
     * @param srch_apc Access Point Code
     */
    public void setSrch_apc(final String srch_apc) {
        this.srch_apc = srch_apc;
    }

    /**
     * Access Point Code 취득
     *
     * @return srch_apc Access Point Code
     */
    public String getSrch_apc() {
        return StringUtils.defaultString(this.srch_apc);
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
     * CLLI NAME(EX: BEL_WIS_Belgium_0) 설정
     *
     * @param clli CLLI NAME(EX: BEL_WIS_Belgium_0)
     */
    public void setClli(final String clli) {
        this.clli = clli;
    }

    /**
     * CLLI NAME(EX: BEL_WIS_Belgium_0) 취득
     *
     * @return clli CLLI NAME(EX: BEL_WIS_Belgium_0)
     */
    public String getClli() {
        return StringUtils.defaultString(this.clli);
    }

    /**
     * 상태(그룹코드: LINK_STATUS) 설정
     *
     * @param status 상태(그룹코드: LINK_STATUS)
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 상태(그룹코드: LINK_STATUS) 취득
     *
     * @return status 상태(그룹코드: LINK_STATUS)
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
    }

    /**
     * Dashboard 출력 여부(Y/N) 설정
     *
     * @param dsbd_yn Dashboard 출력 여부(Y/N)
     */
    public void setDsbd_yn(final String dsbd_yn) {
        this.dsbd_yn = dsbd_yn;
    }

    /**
     * Dashboard 출력 여부(Y/N) 취득
     *
     * @return dsbd_yn Dashboard 출력 여부(Y/N)
     */
    public String getDsbd_yn() {
        return StringUtils.defaultString(this.dsbd_yn);
    }

    /**
     * 생성자
     */
    public LinksetSearchInfo() {
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