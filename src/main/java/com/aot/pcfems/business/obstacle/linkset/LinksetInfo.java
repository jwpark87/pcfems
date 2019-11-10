/*****************************************************************************
 * 프로그램명  : LinksetInfo.java
 * 설     명  : Linkset DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.27   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.linkset;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class LinksetInfo implements Serializable {
    private transient static final long serialVersionUID = 8370795077991603102L;
    /**
     * 장비명
     */
    private String locality = "";
    /**
     * na
     */
    private String na = "";
    /**
     * apc
     */
    private String apc = "";
    /**
     * 국가
     */
    private String country = "";
    /**
     * 사업자
     */
    private String carrier_nm = "";
    /**
     * CLLI
     */
    private String clli = "";
    /**
     * 상태
     */
    private String status = "";
    /**
     * 출력여부
     */
    private String dsbd_yn = "";
    /**
     * 장비명명
     */
    private String locality_nm = "";
    /**
     * 상태명
     */
    private String status_nm = "";
    /**
     * 출력여부명
     */
    private String dsbd_yn_nm = "";

    /**
     * 장비명명 설정
     *
     * @param locality_nm 장비명명
     */
    public void setLocality_nm(final String locality_nm) {
        this.locality_nm = locality_nm;
    }

    /**
     * 장비명명 취득
     *
     * @return locality_nm 장비명명
     */
    public String getLocality_nm() {
        return StringUtils.defaultString(this.locality_nm);
    }

    /**
     * 상태명 설정
     *
     * @param status_nm 상태명
     */
    public void setStatus_nm(final String status_nm) {
        this.status_nm = status_nm;
    }

    /**
     * 상태명 취득
     *
     * @return status_nm 상태명
     */
    public String getStatus_nm() {
        return StringUtils.defaultString(this.status_nm);
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
     * 장비명 설정
     *
     * @param locality 장비명
     */
    public void setLocality(final String locality) {
        this.locality = locality;
    }

    /**
     * 장비명 취득
     *
     * @return locality 장비명
     */
    public String getLocality() {
        return StringUtils.defaultString(this.locality);
    }

    /**
     * na 설정
     *
     * @param na na
     */
    public void setNa(final String na) {
        this.na = na;
    }

    /**
     * na 취득
     *
     * @return na na
     */
    public String getNa() {
        return StringUtils.defaultString(this.na);
    }

    /**
     * apc 설정
     *
     * @param apc apc
     */
    public void setApc(final String apc) {
        this.apc = apc;
    }

    /**
     * apc 취득
     *
     * @return apc apc
     */
    public String getApc() {
        return StringUtils.defaultString(this.apc);
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
     * 사업자 설정
     *
     * @param carrier_nm 사업자
     */
    public void setCarrier_nm(final String carrier_nm) {
        this.carrier_nm = carrier_nm;
    }

    /**
     * 사업자 취득
     *
     * @return carrier_nm 사업자
     */
    public String getCarrier_nm() {
        return StringUtils.defaultString(this.carrier_nm);
    }

    /**
     * CLLI 설정
     *
     * @param clli CLLI
     */
    public void setClli(final String clli) {
        this.clli = clli;
    }

    /**
     * CLLI 취득
     *
     * @return clli CLLI
     */
    public String getClli() {
        return StringUtils.defaultString(this.clli);
    }

    /**
     * 상태 설정
     *
     * @param status 상태
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 상태 취득
     *
     * @return status 상태
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
    }

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
     * 등록자아이디
     */
    private String crt_id = "";
    /**
     * 등록자성명
     */
    private String crt_id_name = "";
    /**
     * 등록시간
     */
    private String crt_dt = "";
    /**
     * 수정자아이디
     */
    private String upd_id = "";
    /**
     * 수정자성명
     */
    private String upd_id_name = "";
    /**
     * 수정시간
     */
    private String upd_dt = "";

    /**
     * 등록자아이디 설정
     *
     * @param crt_id 등록자아이디
     */
    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    /**
     * 등록자아이디 취득
     *
     * @return crt_id 등록자아이디
     */
    public String getCrt_id() {
        return StringUtils.defaultString(this.crt_id);
    }

    /**
     * 등록자성명 설정
     *
     * @param crt_id_name 등록자성명
     */
    public void setCrt_id_name(final String crt_id_name) {
        this.crt_id_name = crt_id_name;
    }

    /**
     * 등록자성명 취득
     *
     * @return crt_id_name 등록자성명
     */
    public String getCrt_id_name() {
        return StringUtils.defaultString(this.crt_id_name);
    }

    /**
     * 수정시간 설정
     *
     * @param crt_dt 수정시간
     */
    public void setCrt_dt(final String crt_dt) {
        this.crt_dt = crt_dt;
    }

    /**
     * 수정시간 취득
     *
     * @return crt_dt 수정시간
     */
    public String getCrt_dt() {
        return StringUtils.defaultString(this.crt_dt);
    }

    /**
     * 수정자아이디 설정
     *
     * @param upd_id 수정자아이디
     */
    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    /**
     * 수정자아이디 취득
     *
     * @return upd_id 수정자아이디
     */
    public String getUpd_id() {
        return StringUtils.defaultString(this.upd_id);
    }

    /**
     * 수정자성명 설정
     *
     * @param upd_id_name 수정자성명
     */
    public void setUpd_id_name(final String upd_id_name) {
        this.upd_id_name = upd_id_name;
    }

    /**
     * 수정자성명 취득
     *
     * @return upd_id_name 수정자성명
     */
    public String getUpd_id_name() {
        return StringUtils.defaultString(this.upd_id_name);
    }

    /**
     * 수정시간 설정
     *
     * @param upd_dt 수정시간
     */
    public void setUpd_dt(final String upd_dt) {
        this.upd_dt = upd_dt;
    }

    /**
     * 수정시간 취득
     *
     * @return upd_dt 수정시간
     */
    public String getUpd_dt() {
        return StringUtils.defaultString(this.upd_dt);
    }

}