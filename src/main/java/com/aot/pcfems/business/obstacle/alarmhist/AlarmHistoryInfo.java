/*****************************************************************************
 * 프로그램명  : AlarmHistoryInfo.java
 * 설     명  : 알람이력조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   HSI     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmhist;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 알람이력조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class AlarmHistoryInfo implements Serializable {
    private transient static final long serialVersionUID = 3201006835004731812L;

    /**
     * 사용자 언어
     */
    private String user_lang = "";

    /**
     * 발생순차번호
     */
    private String seqno = "";
    /**
     * 알람상태
     */
    private String alm_status = "";
    /**
     * 호스트명
     */
    private String host_nm = "";
    /**
     * 프로세스명
     */
    private String proc_nm = "";
    /**
     * 알람 발생 Unique Identifier
     */
    private String alm_pid = "";
    /**
     * 알람카테고리(그룹코드:ALM_CATEGORY)
     */
    private String alm_category = "";
    /**
     * 알람코드
     */
    private String alm_code = "";
    /**
     * 알람발생일시
     */
    private String alm_occur_dt = "";
    /**
     * 알람레벨(그룹코드:ALM_LEVEL)
     */
    private String alm_level = "";
    /**
     * 가청여부
     */
    private String sound_yn = "";
    /**
     * 알람메시지
     */
    private String alm_msg = "";
    /**
     * 발생건수
     */
    private String occur_cnt = "";
    /**
     * 최초발생일시
     */
    private String first_occur_dt = "";
    /**
     * 알람비고
     */
    private String alm_remark1 = "";
    /**
     * 알람해제일시
     */
    private String alm_clear_dt = "";
    /**
     * 알람확인자
     */
    private String alm_confirm_user = "";
    /**
     * 알람확인일시
     */
    private String alm_confirm_dt = "";
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
     * 인지시간
     */
    private String alm_know_dt = "";
    private String alm_group = "";
    private String alm_instance = "";
    private String alm_recovery = "";

    /**
     * 인지시간 설정
     *
     * @param alm_know_dt 인지시간
     */
    public void setAlm_know_dt(final String alm_know_dt) {
        this.alm_know_dt = alm_know_dt;
    }

    /**
     * 인지시간 취득
     *
     * @return alm_know_dt 인지시간
     */
    public String getAlm_know_dt() {
        return StringUtils.defaultString(this.alm_know_dt);
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
     * 발생순차번호 설정
     *
     * @param seqno 발생순차번호
     */
    public void setSeqno(final String seqno) {
        this.seqno = seqno;
    }

    /**
     * 발생순차번호 취득
     *
     * @return seqno 발생순차번호
     */
    public String getSeqno() {
        return StringUtils.defaultString(this.seqno);
    }

    /**
     * 알람상태 설정
     *
     * @param alm_status 알람상태
     */
    public void setAlm_status(final String alm_status) {
        this.alm_status = alm_status;
    }

    /**
     * 알람상태 취득
     *
     * @return alm_status 알람상태
     */
    public String getAlm_status() {
        return StringUtils.defaultString(this.alm_status);
    }

    /**
     * 호스트명 설정
     *
     * @param host_nm 호스트명
     */
    public void setHost_nm(final String host_nm) {
        this.host_nm = host_nm;
    }

    /**
     * 호스트명 취득
     *
     * @return host_nm 호스트명
     */
    public String getHost_nm() {
        return StringUtils.defaultString(this.host_nm);
    }

    /**
     * 프로세스명 설정
     *
     * @param proc_nm 프로세스명
     */
    public void setProc_nm(final String proc_nm) {
        this.proc_nm = proc_nm;
    }

    /**
     * 프로세스명 취득
     *
     * @return proc_nm 프로세스명
     */
    public String getProc_nm() {
        return StringUtils.defaultString(this.proc_nm);
    }

    /**
     * 알람 발생 Unique Identifier 설정
     *
     * @param alm_pid 알람 발생 Unique Identifier
     */
    public void setAlm_pid(final String alm_pid) {
        this.alm_pid = alm_pid;
    }

    /**
     * 알람 발생 Unique Identifier 취득
     *
     * @return alm_pid 알람 발생 Unique Identifier
     */
    public String getAlm_pid() {
        return StringUtils.defaultString(this.alm_pid);
    }

    /**
     * 알람카테고리(그룹코드:ALM_CATEGORY) 설정
     *
     * @param alm_category 알람카테고리(그룹코드:ALM_CATEGORY)
     */
    public void setAlm_category(final String alm_category) {
        this.alm_category = alm_category;
    }

    /**
     * 알람카테고리(그룹코드:ALM_CATEGORY) 취득
     *
     * @return alm_category 알람카테고리(그룹코드:ALM_CATEGORY)
     */
    public String getAlm_category() {
        return StringUtils.defaultString(this.alm_category);
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
     * 알람발생일시 설정
     *
     * @param alm_occur_dt 알람발생일시
     */
    public void setAlm_occur_dt(final String alm_occur_dt) {
        this.alm_occur_dt = alm_occur_dt;
    }

    /**
     * 알람발생일시 취득
     *
     * @return alm_occur_dt 알람발생일시
     */
    public String getAlm_occur_dt() {
        return StringUtils.defaultString(this.alm_occur_dt);
    }

    /**
     * 알람레벨(그룹코드:ALM_LEVEL) 설정
     *
     * @param alm_level 알람레벨(그룹코드:ALM_LEVEL)
     */
    public void setAlm_level(final String alm_level) {
        this.alm_level = alm_level;
    }

    /**
     * 알람레벨(그룹코드:ALM_LEVEL) 취득
     *
     * @return alm_level 알람레벨(그룹코드:ALM_LEVEL)
     */
    public String getAlm_level() {
        return StringUtils.defaultString(this.alm_level);
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
     * 알람메시지 설정
     *
     * @param alm_msg 알람메시지
     */
    public void setAlm_msg(final String alm_msg) {
        this.alm_msg = alm_msg;
    }

    /**
     * 알람메시지 취득
     *
     * @return alm_msg 알람메시지
     */
    public String getAlm_msg() {
        return StringUtils.defaultString(this.alm_msg);
    }

    /**
     * 발생건수 설정
     *
     * @param occur_cnt 발생건수
     */
    public void setOccur_cnt(final String occur_cnt) {
        this.occur_cnt = occur_cnt;
    }

    /**
     * 발생건수 취득
     *
     * @return occur_cnt 발생건수
     */
    public String getOccur_cnt() {
        return StringUtils.defaultString(this.occur_cnt);
    }

    /**
     * 최초발생일시 설정
     *
     * @param first_occur_dt 최초발생일시
     */
    public void setFirst_occur_dt(final String first_occur_dt) {
        this.first_occur_dt = first_occur_dt;
    }

    /**
     * 최초발생일시 취득
     *
     * @return first_occur_dt 최초발생일시
     */
    public String getFirst_occur_dt() {
        return StringUtils.defaultString(this.first_occur_dt);
    }

    /**
     * 알람비고 설정
     *
     * @param alm_remark1 알람비고
     */
    public void setAlm_remark1(final String alm_remark1) {
        this.alm_remark1 = alm_remark1;
    }

    /**
     * 알람비고 취득
     *
     * @return alm_remark1 알람비고
     */
    public String getAlm_remark1() {
        return StringUtils.defaultString(this.alm_remark1);
    }

    /**
     * 알람해제일시 설정
     *
     * @param alm_clear_dt 알람해제일시
     */
    public void setAlm_clear_dt(final String alm_clear_dt) {
        this.alm_clear_dt = alm_clear_dt;
    }

    /**
     * 알람해제일시 취득
     *
     * @return alm_clear_dt 알람해제일시
     */
    public String getAlm_clear_dt() {
        return StringUtils.defaultString(this.alm_clear_dt);
    }

    /**
     * 알람확인자 설정
     *
     * @param alm_confirm_user 알람확인자
     */
    public void setAlm_confirm_user(final String alm_confirm_user) {
        this.alm_confirm_user = alm_confirm_user;
    }

    /**
     * 알람확인자 취득
     *
     * @return alm_confirm_user 알람확인자
     */
    public String getAlm_confirm_user() {
        return StringUtils.defaultString(this.alm_confirm_user);
    }

    /**
     * 알람확인일시 설정
     *
     * @param alm_confirm_dt 알람확인일시
     */
    public void setAlm_confirm_dt(final String alm_confirm_dt) {
        this.alm_confirm_dt = alm_confirm_dt;
    }

    /**
     * 알람확인일시 취득
     *
     * @return alm_confirm_dt 알람확인일시
     */
    public String getAlm_confirm_dt() {
        return StringUtils.defaultString(this.alm_confirm_dt);
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

    public String getAlm_group() {
        return this.alm_group;
    }

    public void setAlm_group(final String alm_group) {
        this.alm_group = alm_group;
    }

    public String getAlm_instance() {
        return this.alm_instance;
    }

    public void setAlm_instance(final String alm_instance) {
        this.alm_instance = alm_instance;
    }

    public String getAlm_recovery() {
        return this.alm_recovery;
    }

    public void setAlm_recovery(final String alm_recovery) {
        this.alm_recovery = alm_recovery;
    }

}