/*****************************************************************************
 * 프로그램명  : DiameterInfo.java
 * 설     명  : Diameter Host 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.gt;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class GtInfo implements Serializable {
    private transient static final long serialVersionUID = -2301864022012764757L;
    /**
     * gt
     */
    private String gt = "";
    /**
     * pc list name
     */
    private String pc_list_nm = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * db_name
     */
    private String db_name = "";
    /**
     * 국가
     */
    private String country = "";
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
     * db_name 설정
     *
     * @param db_name db_name
     */
    public void setDb_name(final String db_name) {
        this.db_name = db_name;
    }

    /**
     * db_name 취득
     *
     * @return db_name db_name
     */
    public String getDb_name() {
        return StringUtils.defaultString(this.db_name);
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
     * gt 설정
     *
     * @param gt gt
     */
    public void setGt(final String gt) {
        this.gt = gt;
    }

    /**
     * gt 취득
     *
     * @return gt gt
     */
    public String getGt() {
        return StringUtils.defaultString(this.gt);
    }

    /**
     * pc list name 설정
     *
     * @param pc_list_nm pc list name
     */
    public void setPc_list_nm(final String pc_list_nm) {
        this.pc_list_nm = pc_list_nm;
    }

    /**
     * pc list name 취득
     *
     * @return pc_list_nm pc list name
     */
    public String getPc_list_nm() {
        return StringUtils.defaultString(this.pc_list_nm);
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