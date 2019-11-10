/*****************************************************************************
 * 프로그램명  : AlarmLevelSet.java
 * 설     명  : 알람 등급  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.05 KYM    1.0
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmlevelset;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AlarmLevelSet extends JqGridVO implements Serializable {
    private transient static final long serialVersionUID = 5141376482667972913L;
    /**
     * 등록자아이디
     */
    private String crt_id = "";
    /**
     * 등록시간
     */
    private String crt_dt = "";
    /**
     * 수정자아이디
     */
    private String upd_id = "";
    /**
     * 수정시간
     */
    private String upd_dt = "";
    /**
     * 알람코드
     */
    private String alm_code = "";
    /**
     * 알람등급명
     */
    private String alm_level_nm = "";
    /**
     * 알람설명
     */
    private String alm_desc = "";
    /**
     * 가청여부명
     */
    private String sound_yn_nm = "";
    /**
     * 사용여부명
     */
    private String use_yn_nm = "";
    /**
     * 수정자이름
     */
    private String upd_id_nm = "";
    /**
     * 알람등급
     */
    private String alm_level = "";
    /**
     * 알람카테고리
     */
    private String alm_category = "";
    /**
     * 가청여부
     */
    private String sound_yn = "";
    /**
     * 사용여부
     */
    private String use_yn = "";
    /**
     * 알람명
     */
    private String snmp_name = "";
    /**
     * 알람 클리어 코드
     */
    private String alm_clear_code = "";
    /**
     * 알람 클리어 코드
     */
    private String alm_recovery = "";

    /**
     * 검색언어
     */
    private String user_lang = "";
    private String oper_comments = "";

    /**
     * 알람명 설정
     *
     * @param snmp_name 알람명
     */
    public void setSnmp_name(final String snmp_name) {
        this.snmp_name = snmp_name;
    }

    /**
     * 알람명 취득
     *
     * @return snmp_name 알람명
     */
    public String getSnmp_name() {
        return StringUtils.defaultString(this.snmp_name);
    }

    /**
     * 알람 클리어 코드 설정
     *
     * @param alm_clear_code 알람 클리어 코드
     */
    public void setAlm_clear_code(final String alm_clear_code) {
        this.alm_clear_code = alm_clear_code;
    }

    /**
     * 알람 클리어 코드 취득
     *
     * @return alm_clear_code 알람 클리어 코드
     */
    public String getAlm_clear_code() {
        return StringUtils.defaultString(this.alm_clear_code);
    }

    /**
     * 알람코드 설정
     *
     * @param alm_code 알람코드
     */
    public void setAlm_code(final String alm_code) {
        this.alm_code = alm_code;
    }

    /**
     * 알람코드 취득
     *
     * @return alm_code 알람코드
     */
    public String getAlm_code() {
        return StringUtils.defaultString(this.alm_code);
    }

    /**
     * 알람등급명 설정
     *
     * @param alm_level_nm 알람등급명
     */
    public void setAlm_level_nm(final String alm_level_nm) {
        this.alm_level_nm = alm_level_nm;
    }

    /**
     * 알람등급명 취득
     *
     * @return alm_level_nm 알람등급명
     */
    public String getAlm_level_nm() {
        return StringUtils.defaultString(this.alm_level_nm);
    }

    /**
     * 알람설명 설정
     *
     * @param alm_desc 알람설명
     */
    public void setAlm_desc(final String alm_desc) {
        this.alm_desc = alm_desc;
    }

    /**
     * 알람설명 취득
     *
     * @return alm_desc 알람설명
     */
    public String getAlm_desc() {
        return StringUtils.defaultString(this.alm_desc);
    }

    /**
     * 가청여부명 설정
     *
     * @param sound_yn_nm 가청여부명
     */
    public void setSound_yn_nm(final String sound_yn_nm) {
        this.sound_yn_nm = sound_yn_nm;
    }

    /**
     * 가청여부명 취득
     *
     * @return sound_yn_nm 가청여부명
     */
    public String getSound_yn_nm() {
        return StringUtils.defaultString(this.sound_yn_nm);
    }

    /**
     * 사용여부명 설정
     *
     * @param use_yn_nm 사용여부명
     */
    public void setUse_yn_nm(final String use_yn_nm) {
        this.use_yn_nm = use_yn_nm;
    }

    /**
     * 사용여부명 취득
     *
     * @return use_yn_nm 사용여부명
     */
    public String getUse_yn_nm() {
        return StringUtils.defaultString(this.use_yn_nm);
    }

    /**
     * 수정자이름 설정
     *
     * @param upd_id_nm 수정자이름
     */
    public void setUpd_id_nm(final String upd_id_nm) {
        this.upd_id_nm = upd_id_nm;
    }

    /**
     * 수정자이름 취득
     *
     * @return upd_id_nm 수정자이름
     */
    public String getUpd_id_nm() {
        return StringUtils.defaultString(this.upd_id_nm);
    }

    /**
     * 알람등급 설정
     *
     * @param alm_level 알람등급
     */
    public void setAlm_level(final String alm_level) {
        this.alm_level = alm_level;
    }

    /**
     * 알람등급 취득
     *
     * @return alm_level 알람등급
     */
    public String getAlm_level() {
        return StringUtils.defaultString(this.alm_level);
    }

    /**
     * 알람카테고리 설정
     *
     * @param alm_category 알람카테고리
     */
    public void setAlm_category(final String alm_category) {
        this.alm_category = alm_category;
    }

    /**
     * 알람카테고리 취득
     *
     * @return alm_category 알람카테고리
     */
    public String getAlm_category() {
        return StringUtils.defaultString(this.alm_category);
    }

    /**
     * 가청여부 설정
     *
     * @param sound_yn 가청여부
     */
    public void setSound_yn(final String sound_yn) {
        this.sound_yn = sound_yn;
    }

    /**
     * 가청여부 취득
     *
     * @return sound_yn 가청여부
     */
    public String getSound_yn() {
        return StringUtils.defaultString(this.sound_yn);
    }

    /**
     * 사용여부 설정
     *
     * @param use_yn 사용여부
     */
    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
    }

    /**
     * 사용여부 취득
     *
     * @return use_yn 사용여부
     */
    public String getUse_yn() {
        return StringUtils.defaultString(this.use_yn);
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

    public String getAlm_recovery() {
        return this.alm_recovery;
    }

    public void setAlm_recovery(final String alm_recovery) {
        this.alm_recovery = alm_recovery;
    }

    public String getUser_lang() {
        return this.user_lang;
    }

    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    public String getOper_comments() {
        return this.oper_comments;
    }

    public void setOper_comments(final String oper_comments) {
        this.oper_comments = oper_comments;
    }

}
