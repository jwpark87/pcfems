/*****************************************************************************
 * 프로그램명  : LinkInfo.java
 * 설     명  : Link 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.link;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class LinkInfo implements Serializable {
    private transient static final long serialVersionUID = 7248050490586551765L;
    /**
     * access point code
     */
    private String na = "";
    /**
     * access point code(linkset)
     */
    private String apc = "";
    /**
     * link
     */
    private String slc = "";
    /**
     * slot/ch(2자리/2자리 포멧)
     */
    private String slot_ch = "";
    /**
     * 국가명
     */
    private String country_nm = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * 교환(e1)단자
     */
    private String e1_cd = "";
    /**
     * bearrer명
     */
    private String bearrer_nm = "";
    /**
     * e1 time slot
     */
    private String e1_time_slot = "";
    /**
     * 케이블명
     */
    private String cable_nm = "";
    /**
     * ndcs tie
     */
    private String ndcs_tie = "";
    /**
     * 연결상태
     */
    private String status = "";

    /**
     * access point code 설정
     *
     * @param na access point code
     */
    public void setNa(final String na) {
        this.na = na;
    }

    /**
     * access point code 취득
     *
     * @return na access point code
     */
    public String getNa() {
        return StringUtils.defaultString(this.na);
    }

    /**
     * access point code(linkset) 설정
     *
     * @param apc access point code(linkset)
     */
    public void setApc(final String apc) {
        this.apc = apc;
    }

    /**
     * access point code(linkset) 취득
     *
     * @return apc access point code(linkset)
     */
    public String getApc() {
        return StringUtils.defaultString(this.apc);
    }

    /**
     * link 설정
     *
     * @param slc link
     */
    public void setSlc(final String slc) {
        this.slc = slc;
    }

    /**
     * link 취득
     *
     * @return slc link
     */
    public String getSlc() {
        return StringUtils.defaultString(this.slc);
    }

    /**
     * slot/ch(2자리/2자리 포멧) 설정
     *
     * @param slot_ch slot/ch(2자리/2자리 포멧)
     */
    public void setSlot_ch(final String slot_ch) {
        this.slot_ch = slot_ch;
    }

    /**
     * slot/ch(2자리/2자리 포멧) 취득
     *
     * @return slot_ch slot/ch(2자리/2자리 포멧)
     */
    public String getSlot_ch() {
        return StringUtils.defaultString(this.slot_ch);
    }

    /**
     * 국가명 설정
     *
     * @param country_nm 국가명
     */
    public void setCountry_nm(final String country_nm) {
        this.country_nm = country_nm;
    }

    /**
     * 국가명 취득
     *
     * @return country_nm 국가명
     */
    public String getCountry_nm() {
        return StringUtils.defaultString(this.country_nm);
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
     * 교환(e1)단자 설정
     *
     * @param e1_cd 교환(e1)단자
     */
    public void setE1_cd(final String e1_cd) {
        this.e1_cd = e1_cd;
    }

    /**
     * 교환(e1)단자 취득
     *
     * @return e1_cd 교환(e1)단자
     */
    public String getE1_cd() {
        return StringUtils.defaultString(this.e1_cd);
    }

    /**
     * bearrer명 설정
     *
     * @param bearrer_nm bearrer명
     */
    public void setBearrer_nm(final String bearrer_nm) {
        this.bearrer_nm = bearrer_nm;
    }

    /**
     * bearrer명 취득
     *
     * @return bearrer_nm bearrer명
     */
    public String getBearrer_nm() {
        return StringUtils.defaultString(this.bearrer_nm);
    }

    /**
     * e1 time slot 설정
     *
     * @param e1_time_slot e1 time slot
     */
    public void setE1_time_slot(final String e1_time_slot) {
        this.e1_time_slot = e1_time_slot;
    }

    /**
     * e1 time slot 취득
     *
     * @return e1_time_slot e1 time slot
     */
    public String getE1_time_slot() {
        return StringUtils.defaultString(this.e1_time_slot);
    }

    /**
     * 케이블명 설정
     *
     * @param cable_nm 케이블명
     */
    public void setCable_nm(final String cable_nm) {
        this.cable_nm = cable_nm;
    }

    /**
     * 케이블명 취득
     *
     * @return cable_nm 케이블명
     */
    public String getCable_nm() {
        return StringUtils.defaultString(this.cable_nm);
    }

    /**
     * ndcs tie 설정
     *
     * @param ndcs_tie ndcs tie
     */
    public void setNdcs_tie(final String ndcs_tie) {
        this.ndcs_tie = ndcs_tie;
    }

    /**
     * ndcs tie 취득
     *
     * @return ndcs_tie ndcs tie
     */
    public String getNdcs_tie() {
        return StringUtils.defaultString(this.ndcs_tie);
    }

    /**
     * 연결상태 설정
     *
     * @param status 연결상태
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 연결상태 취득
     *
     * @return status 연결상태
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
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