/*****************************************************************************
 * 프로그램명  : CdrInfo.java
 * 설     명  : CDR 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.cdr;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * CDR 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class CdrInfo implements Serializable {
    private transient static final long serialVersionUID = -1336679285184710758L;

    /**
     * 사용자 언어
     */
    private String user_lang = "";

    /**
     * 그룹 ID
     */
    private String sbc_group_id = "";
    /**
     * 그룹 ID 명칭
     */
    private String sbc_group_name = "";
    /**
     * 시도 시간
     */
    private String h323_setup_time = "";
    /**
     * 연결 시간
     */
    private String h323_connect_time = "";
    /**
     * 발신번호
     */
    private String calling_number = "";
    /**
     * 착신번호
     */
    private String called_number = "";
    /**
     * 발신국가
     */
    private String from_country_cd = "";
    /**
     * 발신국가명칭
     */
    private String from_country_cd_name = "";
    /**
     * 착신국가
     */
    private String to_country_cd = "";
    /**
     * 착신국가명칭
     */
    private String to_country_cd_name = "";
    /**
     * session id
     */
    private String acct_session_id = "";
    /**
     * callingPartyNumber
     */
    private String calling_station_id = "";
    /**
     * calledPartyNumber
     */
    private String called_station_id = "";
    /**
     * NAS IP
     */
    private String nas_ip_address = "";
    /**
     * NAS Port
     */
    private String nas_port = "";
    /**
     * Ingress Realm
     */
    private String acme_session_ingress_realm = "";
    /**
     * Egress Realm
     */
    private String acme_session_egress_realm = "";
    /**
     * protocol
     */
    private String acme_session_protocol_type = "";
    /**
     * codec(fs1_f)
     */
    private String acme_flowtype_fs1_f = "";
    /**
     * timezone
     */
    private String acme_local_time_zone = "";
    /**
     * PDD
     */
    private String acme_post_dial_delay = "";
    /**
     * Ori. Trunk Group
     */
    private String acme_originating_trunk_group = "";
    /**
     * Term. Trunk Group
     */
    private String acme_terminating_trunk_group = "";
    /**
     * Ingress Local Addr
     */
    private String acme_ingress_local_addr = "";
    /**
     * Ingress Remote Addr
     */
    private String acme_ingress_remote_addr = "";
    /**
     * Egress Local Addr
     */
    private String acme_egress_local_addr = "";
    /**
     * Egress Remote Addr
     */
    private String acme_egress_remote_addr = "";
    /**
     * Client IP
     */
    private String client_ip_address = "";
    /**
     * Uniqie Session ID
     */
    private String acct_unique_session_id = "";
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
     * 그룹 ID 설정
     *
     * @param sbc_group_id 그룹 ID
     */
    public void setSbc_group_id(final String sbc_group_id) {
        this.sbc_group_id = sbc_group_id;
    }

    /**
     * 그룹 ID 취득
     *
     * @return sbc_group_id 그룹 ID
     */
    public String getSbc_group_id() {
        return StringUtils.defaultString(this.sbc_group_id);
    }

    /**
     * 그룹 ID 명칭 설정
     *
     * @param sbc_group_name 그룹 ID 명칭
     */
    public void setSbc_group_name(final String sbc_group_name) {
        this.sbc_group_name = sbc_group_name;
    }

    /**
     * 그룹 ID 명칭 취득
     *
     * @return sbc_group_name 그룹 ID 명칭
     */
    public String getSbc_group_name() {
        return StringUtils.defaultString(this.sbc_group_name);
    }

    /**
     * 시도 시간 설정
     *
     * @param h323_setup_time 시도 시간
     */
    public void setH323_setup_time(final String h323_setup_time) {
        this.h323_setup_time = h323_setup_time;
    }

    /**
     * 시도 시간 취득
     *
     * @return h323_setup_time 시도 시간
     */
    public String getH323_setup_time() {
        return StringUtils.defaultString(this.h323_setup_time);
    }

    /**
     * 연결 시간 설정
     *
     * @param h323_connect_time 연결 시간
     */
    public void setH323_connect_time(final String h323_connect_time) {
        this.h323_connect_time = h323_connect_time;
    }

    /**
     * 연결 시간 취득
     *
     * @return h323_connect_time 연결 시간
     */
    public String getH323_connect_time() {
        return StringUtils.defaultString(this.h323_connect_time);
    }

    /**
     * 발신번호 설정
     *
     * @param calling_number 발신번호
     */
    public void setCalling_number(final String calling_number) {
        this.calling_number = calling_number;
    }

    /**
     * 발신번호 취득
     *
     * @return calling_number 발신번호
     */
    public String getCalling_number() {
        return StringUtils.defaultString(this.calling_number);
    }

    /**
     * 착신번호 설정
     *
     * @param called_number 착신번호
     */
    public void setCalled_number(final String called_number) {
        this.called_number = called_number;
    }

    /**
     * 착신번호 취득
     *
     * @return called_number 착신번호
     */
    public String getCalled_number() {
        return StringUtils.defaultString(this.called_number);
    }

    /**
     * 발신국가 설정
     *
     * @param from_country_cd 발신국가
     */
    public void setFrom_country_cd(final String from_country_cd) {
        this.from_country_cd = from_country_cd;
    }

    /**
     * 발신국가 취득
     *
     * @return from_country_cd 발신국가
     */
    public String getFrom_country_cd() {
        return StringUtils.defaultString(this.from_country_cd);
    }

    /**
     * 발신국가명칭 설정
     *
     * @param from_country_cd_name 발신국가명칭
     */
    public void setFrom_country_cd_name(final String from_country_cd_name) {
        this.from_country_cd_name = from_country_cd_name;
    }

    /**
     * 발신국가명칭 취득
     *
     * @return from_country_cd_name 발신국가명칭
     */
    public String getFrom_country_cd_name() {
        return StringUtils.defaultString(this.from_country_cd_name);
    }

    /**
     * 착신국가 설정
     *
     * @param to_country_cd 착신국가
     */
    public void setTo_country_cd(final String to_country_cd) {
        this.to_country_cd = to_country_cd;
    }

    /**
     * 착신국가 취득
     *
     * @return to_country_cd 착신국가
     */
    public String getTo_country_cd() {
        return StringUtils.defaultString(this.to_country_cd);
    }

    /**
     * 착신국가명칭 설정
     *
     * @param to_country_cd_name 착신국가명칭
     */
    public void setTo_country_cd_name(final String to_country_cd_name) {
        this.to_country_cd_name = to_country_cd_name;
    }

    /**
     * 착신국가명칭 취득
     *
     * @return to_country_cd_name 착신국가명칭
     */
    public String getTo_country_cd_name() {
        return StringUtils.defaultString(this.to_country_cd_name);
    }

    /**
     * session id 설정
     *
     * @param acct_session_id session id
     */
    public void setAcct_session_id(final String acct_session_id) {
        this.acct_session_id = acct_session_id;
    }

    /**
     * session id 취득
     *
     * @return acct_session_id session id
     */
    public String getAcct_session_id() {
        return StringUtils.defaultString(this.acct_session_id);
    }

    /**
     * callingPartyNumber 설정
     *
     * @param calling_station_id callingPartyNumber
     */
    public void setCalling_station_id(final String calling_station_id) {
        this.calling_station_id = calling_station_id;
    }

    /**
     * callingPartyNumber 취득
     *
     * @return calling_station_id callingPartyNumber
     */
    public String getCalling_station_id() {
        return StringUtils.defaultString(this.calling_station_id);
    }

    /**
     * calledPartyNumber 설정
     *
     * @param called_station_id calledPartyNumber
     */
    public void setCalled_station_id(final String called_station_id) {
        this.called_station_id = called_station_id;
    }

    /**
     * calledPartyNumber 취득
     *
     * @return called_station_id calledPartyNumber
     */
    public String getCalled_station_id() {
        return StringUtils.defaultString(this.called_station_id);
    }

    /**
     * NAS IP 설정
     *
     * @param nas_ip_address NAS IP
     */
    public void setNas_ip_address(final String nas_ip_address) {
        this.nas_ip_address = nas_ip_address;
    }

    /**
     * NAS IP 취득
     *
     * @return nas_ip_address NAS IP
     */
    public String getNas_ip_address() {
        return StringUtils.defaultString(this.nas_ip_address);
    }

    /**
     * NAS Port 설정
     *
     * @param nas_port NAS Port
     */
    public void setNas_port(final String nas_port) {
        this.nas_port = nas_port;
    }

    /**
     * NAS Port 취득
     *
     * @return nas_port NAS Port
     */
    public String getNas_port() {
        return StringUtils.defaultString(this.nas_port);
    }

    /**
     * Ingress Realm 설정
     *
     * @param acme_session_ingress_realm Ingress Realm
     */
    public void setAcme_session_ingress_realm(final String acme_session_ingress_realm) {
        this.acme_session_ingress_realm = acme_session_ingress_realm;
    }

    /**
     * Ingress Realm 취득
     *
     * @return acme_session_ingress_realm Ingress Realm
     */
    public String getAcme_session_ingress_realm() {
        return StringUtils.defaultString(this.acme_session_ingress_realm);
    }

    /**
     * Egress Realm 설정
     *
     * @param acme_session_egress_realm Egress Realm
     */
    public void setAcme_session_egress_realm(final String acme_session_egress_realm) {
        this.acme_session_egress_realm = acme_session_egress_realm;
    }

    /**
     * Egress Realm 취득
     *
     * @return acme_session_egress_realm Egress Realm
     */
    public String getAcme_session_egress_realm() {
        return StringUtils.defaultString(this.acme_session_egress_realm);
    }

    /**
     * protocol 설정
     *
     * @param acme_session_protocol_type protocol
     */
    public void setAcme_session_protocol_type(final String acme_session_protocol_type) {
        this.acme_session_protocol_type = acme_session_protocol_type;
    }

    /**
     * protocol 취득
     *
     * @return acme_session_protocol_type protocol
     */
    public String getAcme_session_protocol_type() {
        return StringUtils.defaultString(this.acme_session_protocol_type);
    }

    /**
     * codec(fs1_f) 설정
     *
     * @param acme_flowtype_fs1_f codec(fs1_f)
     */
    public void setAcme_flowtype_fs1_f(final String acme_flowtype_fs1_f) {
        this.acme_flowtype_fs1_f = acme_flowtype_fs1_f;
    }

    /**
     * codec(fs1_f) 취득
     *
     * @return acme_flowtype_fs1_f codec(fs1_f)
     */
    public String getAcme_flowtype_fs1_f() {
        return StringUtils.defaultString(this.acme_flowtype_fs1_f);
    }

    /**
     * timezone 설정
     *
     * @param acme_local_time_zone timezone
     */
    public void setAcme_local_time_zone(final String acme_local_time_zone) {
        this.acme_local_time_zone = acme_local_time_zone;
    }

    /**
     * timezone 취득
     *
     * @return acme_local_time_zone timezone
     */
    public String getAcme_local_time_zone() {
        return StringUtils.defaultString(this.acme_local_time_zone);
    }

    /**
     * PDD 설정
     *
     * @param acme_post_dial_delay PDD
     */
    public void setAcme_post_dial_delay(final String acme_post_dial_delay) {
        this.acme_post_dial_delay = acme_post_dial_delay;
    }

    /**
     * PDD 취득
     *
     * @return acme_post_dial_delay PDD
     */
    public String getAcme_post_dial_delay() {
        return StringUtils.defaultString(this.acme_post_dial_delay);
    }

    /**
     * Ori. Trunk Group 설정
     *
     * @param acme_originating_trunk_group Ori. Trunk Group
     */
    public void setAcme_originating_trunk_group(final String acme_originating_trunk_group) {
        this.acme_originating_trunk_group = acme_originating_trunk_group;
    }

    /**
     * Ori. Trunk Group 취득
     *
     * @return acme_originating_trunk_group Ori. Trunk Group
     */
    public String getAcme_originating_trunk_group() {
        return StringUtils.defaultString(this.acme_originating_trunk_group);
    }

    /**
     * Term. Trunk Group 설정
     *
     * @param acme_terminating_trunk_group Term. Trunk Group
     */
    public void setAcme_terminating_trunk_group(final String acme_terminating_trunk_group) {
        this.acme_terminating_trunk_group = acme_terminating_trunk_group;
    }

    /**
     * Term. Trunk Group 취득
     *
     * @return acme_terminating_trunk_group Term. Trunk Group
     */
    public String getAcme_terminating_trunk_group() {
        return StringUtils.defaultString(this.acme_terminating_trunk_group);
    }

    /**
     * Ingress Local Addr 설정
     *
     * @param acme_ingress_local_addr Ingress Local Addr
     */
    public void setAcme_ingress_local_addr(final String acme_ingress_local_addr) {
        this.acme_ingress_local_addr = acme_ingress_local_addr;
    }

    /**
     * Ingress Local Addr 취득
     *
     * @return acme_ingress_local_addr Ingress Local Addr
     */
    public String getAcme_ingress_local_addr() {
        return StringUtils.defaultString(this.acme_ingress_local_addr);
    }

    /**
     * Ingress Remote Addr 설정
     *
     * @param acme_ingress_remote_addr Ingress Remote Addr
     */
    public void setAcme_ingress_remote_addr(final String acme_ingress_remote_addr) {
        this.acme_ingress_remote_addr = acme_ingress_remote_addr;
    }

    /**
     * Ingress Remote Addr 취득
     *
     * @return acme_ingress_remote_addr Ingress Remote Addr
     */
    public String getAcme_ingress_remote_addr() {
        return StringUtils.defaultString(this.acme_ingress_remote_addr);
    }

    /**
     * Egress Local Addr 설정
     *
     * @param acme_egress_local_addr Egress Local Addr
     */
    public void setAcme_egress_local_addr(final String acme_egress_local_addr) {
        this.acme_egress_local_addr = acme_egress_local_addr;
    }

    /**
     * Egress Local Addr 취득
     *
     * @return acme_egress_local_addr Egress Local Addr
     */
    public String getAcme_egress_local_addr() {
        return StringUtils.defaultString(this.acme_egress_local_addr);
    }

    /**
     * Egress Remote Addr 설정
     *
     * @param acme_egress_remote_addr Egress Remote Addr
     */
    public void setAcme_egress_remote_addr(final String acme_egress_remote_addr) {
        this.acme_egress_remote_addr = acme_egress_remote_addr;
    }

    /**
     * Egress Remote Addr 취득
     *
     * @return acme_egress_remote_addr Egress Remote Addr
     */
    public String getAcme_egress_remote_addr() {
        return StringUtils.defaultString(this.acme_egress_remote_addr);
    }

    /**
     * Client IP 설정
     *
     * @param client_ip_address Client IP
     */
    public void setClient_ip_address(final String client_ip_address) {
        this.client_ip_address = client_ip_address;
    }

    /**
     * Client IP 취득
     *
     * @return client_ip_address Client IP
     */
    public String getClient_ip_address() {
        return StringUtils.defaultString(this.client_ip_address);
    }

    /**
     * Uniqie Session ID 설정
     *
     * @param acct_unique_session_id Uniqie Session ID
     */
    public void setAcct_unique_session_id(final String acct_unique_session_id) {
        this.acct_unique_session_id = acct_unique_session_id;
    }

    /**
     * Uniqie Session ID 취득
     *
     * @return acct_unique_session_id Uniqie Session ID
     */
    public String getAcct_unique_session_id() {
        return StringUtils.defaultString(this.acct_unique_session_id);
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