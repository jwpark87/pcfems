/*****************************************************************************
 * 프로그램명  : DiameterInfo.java
 * 설     명  : Diameter Host 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameterrealm;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DiameterRealmInfo implements Serializable {
    private transient static final long serialVersionUID = -7074296192150876566L;
    /**
     * 장비명
     */
    private String locality = "";
    /**
     * Locality Nm
     */
    private String locality_nm = "";
    /**
     * 사업자
     */
    private String carrier_nm = "";
    /**
     * Realm
     */
    private String realm = "";
    /**
     * 개통일
     */
    private String open_dt = "";
    /**
     * country
     */
    private String country = "";
    /**
     * routing_peer
     */
    private String routing_peer = "";
    /**
     * contact_email
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
     * country 설정
     *
     * @param country country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * country 취득
     *
     * @return country country
     */
    public String getCountry() {
        return StringUtils.defaultString(this.country);
    }

    /**
     * routing_peer 설정
     *
     * @param routing_peer routing_peer
     */
    public void setRouting_peer(final String routing_peer) {
        this.routing_peer = routing_peer;
    }

    /**
     * routing_peer 취득
     *
     * @return routing_peer routing_peer
     */
    public String getRouting_peer() {
        return StringUtils.defaultString(this.routing_peer);
    }

    /**
     * contact_email 설정
     *
     * @param contact_email contact_email
     */
    public void setContact_email(final String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * contact_email 취득
     *
     * @return contact_email contact_email
     */
    public String getContact_email() {
        return StringUtils.defaultString(this.contact_email);
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
     * Locality Nm 설정
     *
     * @param locality_nm Locality Nm
     */
    public void setLocality_nm(final String locality_nm) {
        this.locality_nm = locality_nm;
    }

    /**
     * Locality Nm 취득
     *
     * @return locality_nm Locality Nm
     */
    public String getLocality_nm() {
        return StringUtils.defaultString(this.locality_nm);
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
     * Realm 설정
     *
     * @param realm Realm
     */
    public void setRealm(final String realm) {
        this.realm = realm;
    }

    /**
     * Realm 취득
     *
     * @return realm Realm
     */
    public String getRealm() {
        return StringUtils.defaultString(this.realm);
    }

    /**
     * 개통일 설정
     *
     * @param open_dt 개통일
     */
    public void setOpen_dt(final String open_dt) {
        this.open_dt = open_dt;
    }

    /**
     * 개통일 취득
     *
     * @return open_dt 개통일
     */
    public String getOpen_dt() {
        return StringUtils.defaultString(this.open_dt);
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
     * 엑셀
     */
    private String excel_file = "";

    /**
     * 엑셀 설정
     *
     * @param excel_file 엑셀
     */
    public void setExcel_file(final String excel_file) {
        this.excel_file = excel_file;
    }

    /**
     * 엑셀 취득
     *
     * @return excel_file 엑셀
     */
    public String getExcel_file() {
        return StringUtils.defaultString(this.excel_file);
    }

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