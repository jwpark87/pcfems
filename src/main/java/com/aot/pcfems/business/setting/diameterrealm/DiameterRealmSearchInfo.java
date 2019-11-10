/*****************************************************************************
 * 프로그램명  : DiameterSearchInfo.java
 * 설     명  : Diameter Host 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.03.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameterrealm;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DiameterRealmSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -5576466885452062348L;
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
     * 국소
     */
    private String srch_locality = "";
    /**
     * 국가
     */
    private String country = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * 라우팅 Peer
     */
    private String routing_peer = "";
    /**
     * Realm
     */
    private String srch_realm = "";
    /**
     * 담당자이메일
     */
    private String contact_email = "";

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
     * 국소 설정
     *
     * @param srch_locality 국소
     */
    public void setSrch_locality(final String srch_locality) {
        this.srch_locality = srch_locality;
    }

    /**
     * 국소 취득
     *
     * @return srch_locality 국소
     */
    public String getSrch_locality() {
        return StringUtils.defaultString(this.srch_locality);
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
     * 라우팅 Peer 설정
     *
     * @param routing_peer 라우팅 Peer
     */
    public void setRouting_peer(final String routing_peer) {
        this.routing_peer = routing_peer;
    }

    /**
     * 라우팅 Peer 취득
     *
     * @return routing_peer 라우팅 Peer
     */
    public String getRouting_peer() {
        return StringUtils.defaultString(this.routing_peer);
    }

    /**
     * Realm 설정
     *
     * @param srch_realm Realm
     */
    public void setSrch_realm(final String srch_realm) {
        this.srch_realm = srch_realm;
    }

    /**
     * Realm 취득
     *
     * @return srch_realm Realm
     */
    public String getSrch_realm() {
        return StringUtils.defaultString(this.srch_realm);
    }

    /**
     * 담당자이메일 설정
     *
     * @param contact_email 담당자이메일
     */
    public void setContact_email(final String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * 담당자이메일 취득
     *
     * @return contact_email 담당자이메일
     */
    public String getContact_email() {
        return StringUtils.defaultString(this.contact_email);
    }

    /**
     * 생성자
     */
    public DiameterRealmSearchInfo() {
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

}