/*****************************************************************************
 * 프로그램명  : RealmInfo.java
 * 설     명  : REALM정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.realm;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * REALM정보 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class RealmInfo implements Serializable {
    private transient static final long serialVersionUID = -2313224010783355433L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * Realm
     */
    private String realm = "";
    /**
     * REALM코드
     */
    private String realm_cd = "";
    /**
     * REALM코드명칭
     */
    private String realm_carrier_name = "";
    /**
     * 비고
     */
    private String remark1 = "";
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
     * sbc_group_id(그룹코드: SBC_GROUP_ID)
     */
    private String sbc_group_id = "";

    public String getSbc_group_id() {
        return this.sbc_group_id;
    }

    public void setSbc_group_id(final String sbc_group_id) {
        this.sbc_group_id = sbc_group_id;
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
     * REALM코드 설정
     *
     * @param realm_cd REALM코드
     */
    public void setRealm_cd(final String realm_cd) {
        this.realm_cd = realm_cd;
    }

    /**
     * REALM코드 취득
     *
     * @return realm_cd REALM코드
     */
    public String getRealm_cd() {
        return StringUtils.defaultString(this.realm_cd);
    }

    /**
     * REALM코드명칭 설정
     *
     * @param realm_carrier_name REALM코드명칭
     */
    public void setRealm_carrier_name(final String realm_carrier_name) {
        this.realm_carrier_name = realm_carrier_name;
    }

    /**
     * REALM코드명칭 취득
     *
     * @return realm_carrier_name REALM코드명칭
     */
    public String getRealm_carrier_name() {
        return StringUtils.defaultString(this.realm_carrier_name);
    }

    /**
     * 비고 설정
     *
     * @param remark1 비고
     */
    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
    }

    /**
     * 비고 취득
     *
     * @return remark1 비고
     */
    public String getRemark1() {
        return StringUtils.defaultString(this.remark1);
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