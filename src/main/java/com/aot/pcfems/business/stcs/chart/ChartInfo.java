/*****************************************************************************
 * 프로그램명  : ChartInfo.java
 * 설     명  : 통계관리 CHART 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.chart;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 통계관리 CHART 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class ChartInfo implements Serializable {
    private transient static final long serialVersionUID = -1300876228143307617L;

    /**
     * 사용자 언어
     */
    private String user_lang = "";

    /**
     * 기준시간
     */
    private String occur_dt = "";
    /**
     * 통계용기준시간
     */
    private String stcs_dt = "";
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
     * 발신사업자
     */
    private String acme_session_ingress_realm = "";
    /**
     * 착신사업자
     */
    private String acme_session_egress_realm = "";
    /**
     * 시도호건수
     */
    private String try_call_cnt = "";
    /**
     * 소통호건수
     */
    private String conn_call_cnt = "";
    /**
     * 완료호건수
     */
    private String comp_call_cnt = "";
    /**
     * 통화시간
     */
    private String acct_session_time = "";
    /**
     * pdd
     */
    private String acme_post_dial_delay = "";
    /**
     * calling avg jitter
     */
    private String acme_calling_rtp_avg_jitter_fs1 = "";
    /**
     * called avg jitter
     */
    private String acme_called_rtp_avg_jitter_fs1 = "";
    /**
     * calling r-factor
     */
    private String acme_calling_r_factor = "";
    /**
     * called r-factor
     */
    private String acme_called_r_factor = "";
    /**
     * calling packet lost
     */
    private String acme_calling_rtp_packets_lost_fs1 = "";
    /**
     * called packet lost
     */
    private String acme_called_rtp_packets_lost_fs1 = "";
    /**
     * calling 패킷수
     */
    private String acme_calling_packets_fs1 = "";
    /**
     * calling 패킷사이즈
     */
    private String acme_calling_octets_fs1 = "";
    /**
     * called 패킷수
     */
    private String acme_called_packets_fs1 = "";
    /**
     * called 패킷사이즈
     */
    private String acme_called_octets_fs1 = "";
    /**
     * 완료율(%)
     */
    private String percent_comp_call = "";

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
     * 발신사업자명
     */
    private String ingress_realm_name = "";
    /**
     * 착신사업자명
     */
    private String egress_realm_name = "";

    /**
     * 발신사업자명 설정
     *
     * @param ingress_realm_name 발신사업자명
     */
    public void setIngress_realm_name(final String ingress_realm_name) {
        this.ingress_realm_name = ingress_realm_name;
    }

    /**
     * 발신사업자명 취득
     *
     * @return ingress_realm_name 발신사업자명
     */
    public String getIngress_realm_name() {
        return StringUtils.defaultString(this.ingress_realm_name);
    }

    /**
     * 착신사업자명 설정
     *
     * @param egress_realm_name 착신사업자명
     */
    public void setEgress_realm_name(final String egress_realm_name) {
        this.egress_realm_name = egress_realm_name;
    }

    /**
     * 착신사업자명 취득
     *
     * @return egress_realm_name 착신사업자명
     */
    public String getEgress_realm_name() {
        return StringUtils.defaultString(this.egress_realm_name);
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
     * 기준시간 설정
     *
     * @param occur_dt 기준시간
     */
    public void setOccur_dt(final String occur_dt) {
        this.occur_dt = occur_dt;
    }

    /**
     * 기준시간 취득
     *
     * @return occur_dt 기준시간
     */
    public String getOccur_dt() {
        return StringUtils.defaultString(this.occur_dt);
    }

    /**
     * 통계용기준시간 설정
     *
     * @param stcs_dt 통계용기준시간
     */
    public void setStcs_dt(final String stcs_dt) {
        this.stcs_dt = stcs_dt;
    }

    /**
     * 통계용기준시간 취득
     *
     * @return stcs_dt 통계용기준시간
     */
    public String getStcs_dt() {
        return StringUtils.defaultString(this.stcs_dt);
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
     * 발신사업자 설정
     *
     * @param acme_session_ingress_realm 발신사업자
     */
    public void setAcme_session_ingress_realm(final String acme_session_ingress_realm) {
        this.acme_session_ingress_realm = acme_session_ingress_realm;
    }

    /**
     * 발신사업자 취득
     *
     * @return acme_session_ingress_realm 발신사업자
     */
    public String getAcme_session_ingress_realm() {
        return StringUtils.defaultString(this.acme_session_ingress_realm);
    }

    /**
     * 착신사업자 설정
     *
     * @param acme_session_egress_realm 착신사업자
     */
    public void setAcme_session_egress_realm(final String acme_session_egress_realm) {
        this.acme_session_egress_realm = acme_session_egress_realm;
    }

    /**
     * 착신사업자 취득
     *
     * @return acme_session_egress_realm 착신사업자
     */
    public String getAcme_session_egress_realm() {
        return StringUtils.defaultString(this.acme_session_egress_realm);
    }

    /**
     * 시도호건수 설정
     *
     * @param try_call_cnt 시도호건수
     */
    public void setTry_call_cnt(final String try_call_cnt) {
        this.try_call_cnt = try_call_cnt;
    }

    /**
     * 시도호건수 취득
     *
     * @return try_call_cnt 시도호건수
     */
    public String getTry_call_cnt() {
        return StringUtils.defaultString(this.try_call_cnt);
    }

    /**
     * 소통호건수 설정
     *
     * @param conn_call_cnt 소통호건수
     */
    public void setConn_call_cnt(final String conn_call_cnt) {
        this.conn_call_cnt = conn_call_cnt;
    }

    /**
     * 소통호건수 취득
     *
     * @return conn_call_cnt 소통호건수
     */
    public String getConn_call_cnt() {
        return StringUtils.defaultString(this.conn_call_cnt);
    }

    /**
     * 완료호건수 설정
     *
     * @param comp_call_cnt 완료호건수
     */
    public void setComp_call_cnt(final String comp_call_cnt) {
        this.comp_call_cnt = comp_call_cnt;
    }

    /**
     * 완료호건수 취득
     *
     * @return comp_call_cnt 완료호건수
     */
    public String getComp_call_cnt() {
        return StringUtils.defaultString(this.comp_call_cnt);
    }

    /**
     * 통화시간 설정
     *
     * @param acct_session_time 통화시간
     */
    public void setAcct_session_time(final String acct_session_time) {
        this.acct_session_time = acct_session_time;
    }

    /**
     * 통화시간 취득
     *
     * @return acct_session_time 통화시간
     */
    public String getAcct_session_time() {
        return StringUtils.defaultString(this.acct_session_time);
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
     * calling avg jitter 설정
     *
     * @param acme_calling_rtp_avg_jitter_fs1 calling avg jitter
     */
    public void setAcme_calling_rtp_avg_jitter_fs1(final String acme_calling_rtp_avg_jitter_fs1) {
        this.acme_calling_rtp_avg_jitter_fs1 = acme_calling_rtp_avg_jitter_fs1;
    }

    /**
     * calling avg jitter 취득
     *
     * @return acme_calling_rtp_avg_jitter_fs1 calling avg jitter
     */
    public String getAcme_calling_rtp_avg_jitter_fs1() {
        return StringUtils.defaultString(this.acme_calling_rtp_avg_jitter_fs1);
    }

    /**
     * called avg jitter 설정
     *
     * @param acme_called_rtp_avg_jitter_fs1 called avg jitter
     */
    public void setAcme_called_rtp_avg_jitter_fs1(final String acme_called_rtp_avg_jitter_fs1) {
        this.acme_called_rtp_avg_jitter_fs1 = acme_called_rtp_avg_jitter_fs1;
    }

    /**
     * called avg jitter 취득
     *
     * @return acme_called_rtp_avg_jitter_fs1 called avg jitter
     */
    public String getAcme_called_rtp_avg_jitter_fs1() {
        return StringUtils.defaultString(this.acme_called_rtp_avg_jitter_fs1);
    }

    /**
     * calling r-factor 설정
     *
     * @param acme_calling_r_factor calling r-factor
     */
    public void setAcme_calling_r_factor(final String acme_calling_r_factor) {
        this.acme_calling_r_factor = acme_calling_r_factor;
    }

    /**
     * calling r-factor 취득
     *
     * @return acme_calling_r_factor calling r-factor
     */
    public String getAcme_calling_r_factor() {
        return StringUtils.defaultString(this.acme_calling_r_factor);
    }

    /**
     * called r-factor 설정
     *
     * @param acme_called_r_factor called r-factor
     */
    public void setAcme_called_r_factor(final String acme_called_r_factor) {
        this.acme_called_r_factor = acme_called_r_factor;
    }

    /**
     * called r-factor 취득
     *
     * @return acme_called_r_factor called r-factor
     */
    public String getAcme_called_r_factor() {
        return StringUtils.defaultString(this.acme_called_r_factor);
    }

    /**
     * calling packet lost 설정
     *
     * @param acme_calling_rtp_packets_lost_fs1 calling packet lost
     */
    public void setAcme_calling_rtp_packets_lost_fs1(final String acme_calling_rtp_packets_lost_fs1) {
        this.acme_calling_rtp_packets_lost_fs1 = acme_calling_rtp_packets_lost_fs1;
    }

    /**
     * calling packet lost 취득
     *
     * @return acme_calling_rtp_packets_lost_fs1 calling packet lost
     */
    public String getAcme_calling_rtp_packets_lost_fs1() {
        return StringUtils.defaultString(this.acme_calling_rtp_packets_lost_fs1);
    }

    /**
     * called packet lost 설정
     *
     * @param acme_called_rtp_packets_lost_fs1 called packet lost
     */
    public void setAcme_called_rtp_packets_lost_fs1(final String acme_called_rtp_packets_lost_fs1) {
        this.acme_called_rtp_packets_lost_fs1 = acme_called_rtp_packets_lost_fs1;
    }

    /**
     * called packet lost 취득
     *
     * @return acme_called_rtp_packets_lost_fs1 called packet lost
     */
    public String getAcme_called_rtp_packets_lost_fs1() {
        return StringUtils.defaultString(this.acme_called_rtp_packets_lost_fs1);
    }

    /**
     * calling 패킷수 설정
     *
     * @param acme_calling_packets_fs1 calling 패킷수
     */
    public void setAcme_calling_packets_fs1(final String acme_calling_packets_fs1) {
        this.acme_calling_packets_fs1 = acme_calling_packets_fs1;
    }

    /**
     * calling 패킷수 취득
     *
     * @return acme_calling_packets_fs1 calling 패킷수
     */
    public String getAcme_calling_packets_fs1() {
        return StringUtils.defaultString(this.acme_calling_packets_fs1);
    }

    /**
     * called 패킷수 설정
     *
     * @param acme_called_packets_fs1 called 패킷수
     */
    public void setAcme_called_packets_fs1(final String acme_called_packets_fs1) {
        this.acme_called_packets_fs1 = acme_called_packets_fs1;
    }

    /**
     * called 패킷수 취득
     *
     * @return acme_called_packets_fs1 called 패킷수
     */
    public String getAcme_called_packets_fs1() {
        return StringUtils.defaultString(this.acme_called_packets_fs1);
    }

    /**
     * calling 패킷사이즈 설정
     *
     * @param acme_calling_octets_fs1 calling 패킷사이즈
     */
    public void setAcme_calling_octets_fs1(final String acme_calling_octets_fs1) {
        this.acme_calling_octets_fs1 = acme_calling_octets_fs1;
    }

    /**
     * calling 패킷사이즈 취득
     *
     * @return acme_calling_octets_fs1 calling 패킷사이즈
     */
    public String getAcme_calling_octets_fs1() {
        return StringUtils.defaultString(this.acme_calling_octets_fs1);
    }

    /**
     * called 패킷사이즈 설정
     *
     * @param acme_called_octets_fs1 called 패킷사이즈
     */
    public void setAcme_called_octets_fs1(final String acme_called_octets_fs1) {
        this.acme_called_octets_fs1 = acme_called_octets_fs1;
    }

    /**
     * called 패킷사이즈 취득
     *
     * @return acme_called_octets_fs1 called 패킷사이즈
     */
    public String getAcme_called_octets_fs1() {
        return StringUtils.defaultString(this.acme_called_octets_fs1);
    }

    /**
     * 완료율 설정
     *
     * @param percent_comp_call 완료율
     */
    public void setPercent_comp_call(final String percent_comp_call) {
        this.percent_comp_call = percent_comp_call;
    }

    /**
     * 완료율 취득
     *
     * @return percent_comp_call 완료율
     */
    public String getPercent_comp_call() {
        return StringUtils.defaultString(this.percent_comp_call);
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

    /**
     * 비완료호
     */
    private String not_comp_call_cnt = "";
    /**
     * 평균시간
     */
    private String avg_use_time = "";
    /**
     * 평균PDD
     */
    private String avg_pdd = "";
    /**
     * 평균CALLING R-FACTOR
     */
    private String avg_calling_r_factor = "";
    /**
     * 평균 CALLED R-FACTOR
     */
    private String avg_called_r_factor = "";
    /**
     * 평균 CALLING Packet Loss
     */
    private String avg_calling_rtp_packets_lost_fs1 = "";
    /**
     * 평균 CALLED Packet Loss
     */
    private String avg_called_rtp_packets_lost_fs1 = "";

    /**
     * 비완료호 설정
     *
     * @param not_comp_call_cnt 비완료호
     */
    public void setNot_comp_call_cnt(final String not_comp_call_cnt) {
        this.not_comp_call_cnt = not_comp_call_cnt;
    }

    /**
     * 비완료호 취득
     *
     * @return not_comp_call_cnt 비완료호
     */
    public String getNot_comp_call_cnt() {
        return StringUtils.defaultString(this.not_comp_call_cnt);
    }

    /**
     * 평균시간 설정
     *
     * @param avg_use_time 평균시간
     */
    public void setAvg_use_time(final String avg_use_time) {
        this.avg_use_time = avg_use_time;
    }

    /**
     * 평균시간 취득
     *
     * @return avg_use_time 평균시간
     */
    public String getAvg_use_time() {
        return StringUtils.defaultString(this.avg_use_time);
    }

    /**
     * 평균PDD 설정
     *
     * @param avg_pdd 평균PDD
     */
    public void setAvg_pdd(final String avg_pdd) {
        this.avg_pdd = avg_pdd;
    }

    /**
     * 평균PDD 취득
     *
     * @return avg_pdd 평균PDD
     */
    public String getAvg_pdd() {
        return StringUtils.defaultString(this.avg_pdd);
    }

    /**
     * 평균CALLING R-FACTOR 설정
     *
     * @param avg_calling_r_factor 평균CALLING R-FACTOR
     */
    public void setAvg_calling_r_factor(final String avg_calling_r_factor) {
        this.avg_calling_r_factor = avg_calling_r_factor;
    }

    /**
     * 평균CALLING R-FACTOR 취득
     *
     * @return avg_calling_r_factor 평균CALLING R-FACTOR
     */
    public String getAvg_calling_r_factor() {
        return StringUtils.defaultString(this.avg_calling_r_factor);
    }

    /**
     * 평균 CALLED R-FACTOR 설정
     *
     * @param avg_called_r_factor 평균 CALLED R-FACTOR
     */
    public void setAvg_called_r_factor(final String avg_called_r_factor) {
        this.avg_called_r_factor = avg_called_r_factor;
    }

    /**
     * 평균 CALLED R-FACTOR 취득
     *
     * @return avg_called_r_factor 평균 CALLED R-FACTOR
     */
    public String getAvg_called_r_factor() {
        return StringUtils.defaultString(this.avg_called_r_factor);
    }

    /**
     * 평균 CALLING Packet Loss 설정
     *
     * @param avg_calling_rtp_packets_lost_fs1 평균 CALLING Packet Loss
     */
    public void setAvg_calling_rtp_packets_lost_fs1(final String avg_calling_rtp_packets_lost_fs1) {
        this.avg_calling_rtp_packets_lost_fs1 = avg_calling_rtp_packets_lost_fs1;
    }

    /**
     * 평균 CALLING Packet Loss 취득
     *
     * @return avg_calling_rtp_packets_lost_fs1 평균 CALLING Packet Loss
     */
    public String getAvg_calling_rtp_packets_lost_fs1() {
        return StringUtils.defaultString(this.avg_calling_rtp_packets_lost_fs1);
    }

    /**
     * 평균 CALLED Packet Loss 설정
     *
     * @param avg_called_rtp_packets_lost_fs1 평균 CALLED Packet Loss
     */
    public void setAvg_called_rtp_packets_lost_fs1(final String avg_called_rtp_packets_lost_fs1) {
        this.avg_called_rtp_packets_lost_fs1 = avg_called_rtp_packets_lost_fs1;
    }

    /**
     * 평균 CALLED Packet Loss 취득
     *
     * @return avg_called_rtp_packets_lost_fs1 평균 CALLED Packet Loss
     */
    public String getAvg_called_rtp_packets_lost_fs1() {
        return StringUtils.defaultString(this.avg_called_rtp_packets_lost_fs1);
    }

    /**
     * 총실패호
     */
    private String fail_call_cnt = "";
    /**
     * 평균통화시간
     */
    private String avg_call_time = "";

    /**
     * 총실패호 설정
     *
     * @param fail_call_cnt 총실패호
     */
    public void setFail_call_cnt(final String fail_call_cnt) {
        this.fail_call_cnt = fail_call_cnt;
    }

    /**
     * 총실패호 취득
     *
     * @return fail_call_cnt 총실패호
     */
    public String getFail_call_cnt() {
        return StringUtils.defaultString(this.fail_call_cnt);
    }

    /**
     * 평균통화시간 설정
     *
     * @param avg_call_time 평균통화시간
     */
    public void setAvg_call_time(final String avg_call_time) {
        this.avg_call_time = avg_call_time;
    }

    /**
     * 평균통화시간 취득
     *
     * @return avg_call_time 평균통화시간
     */
    public String getAvg_call_time() {
        return StringUtils.defaultString(this.avg_call_time);
    }

    /**
     * 평균 Calling Jitter
     */
    private String avg_calling_jitter = "";
    /**
     * 평균 Called Jitter
     */
    private String avg_called_jitter = "";

    /**
     * 평균 Calling Jitter 설정
     *
     * @param avg_calling_jitter 평균 Calling Jitter
     */
    public void setAvg_calling_jitter(final String avg_calling_jitter) {
        this.avg_calling_jitter = avg_calling_jitter;
    }

    /**
     * 평균 Calling Jitter 취득
     *
     * @return avg_calling_jitter 평균 Calling Jitter
     */
    public String getAvg_calling_jitter() {
        return StringUtils.defaultString(this.avg_calling_jitter);
    }

    /**
     * 평균 Called Jitter 설정
     *
     * @param avg_called_jitter 평균 Called Jitter
     */
    public void setAvg_called_jitter(final String avg_called_jitter) {
        this.avg_called_jitter = avg_called_jitter;
    }

    /**
     * 평균 Called Jitter 취득
     *
     * @return avg_called_jitter 평균 Called Jitter
     */
    public String getAvg_called_jitter() {
        return StringUtils.defaultString(this.avg_called_jitter);
    }

    /**
     * 국가
     */
    private String country_nm = "";
    /**
     * 사업자
     */
    private String carrier_nm = "";
    /**
     * 국소
     */
    private String local = "";
    /**
     * 국소
     */
    private String locality = "";
    /**
     * realm
     */
    private String realm = "";
    /**
     * host
     */
    private String host = "";
    /**
     * routing_peer
     */
    private String routing_peer = "";
    /**
     * 메시지건수
     */
    private String in_occur_cnt = "";
    /**
     * tps
     */
    private String in_tps = "";
    /**
     * 패킷사이즈
     */
    private String in_pkt_size = "";
    /**
     * 성공건수
     */
    private String in_succ_cnt = "";
    /**
     * 실패건수
     */
    private String in_fail_cnt = "";
    /**
     * 성공율
     */
    private String in_succ_percent = "";
    /**
     * 실패율
     */
    private String in_fail_percent = "";
    /**
     * 메시지건수
     */
    private String out_occur_cnt = "";
    /**
     * tps
     */
    private String out_tps = "";
    /**
     * 패킷사이즈
     */
    private String out_pkt_size = "";
    /**
     * 성공건수
     */
    private String out_succ_cnt = "";
    /**
     * 실패건수
     */
    private String out_fail_cnt = "";
    /**
     * 성공율
     */
    private String out_succ_percent = "";
    /**
     * 실패율
     */
    private String out_fail_percent = "";

    /**
     * Destination 국가
     */
    private String dst_country_nm = "";
    /**
     * Destination 사업자
     */
    private String dst_carrier_nm = "";
    /**
     * Destination 국소
     */
    private String dst_local = "";
    /**
     * Destination Realm
     */
    private String dst_realm = "";
    /**
     * Destination Peer
     */
    private String dst_host = "";
    /**
     * Dest Routing peer
     */
    private String dst_routing_peer = "";

    /**
     * In MAP_발생건수
     */
    private String in_map_occur_cnt = "";
    /**
     * In MAP_패킷 사이즈 합계
     */
    private String in_map_pkt_size = "";
    /**
     * Out MAP_발생건수
     */
    private String out_map_occur_cnt = "";
    /**
     * Out MAP_패킷 사이즈 합계
     */
    private String out_map_pkt_size = "";
    /**
     * 링크셋
     */
    private String linkset = "";
    /**
     * clli
     */
    private String clli = "";

    /**
     * clli 설정
     *
     * @param clli clli
     */
    public void setClli(final String clli) {
        this.clli = clli;
    }

    /**
     * clli 취득
     *
     * @return clli clli
     */
    public String getClli() {
        return StringUtils.defaultString(this.clli);
    }

    /**
     * In MAP_발생건수 설정
     *
     * @param in_map_occur_cnt In MAP_발생건수
     */
    public void setIn_map_occur_cnt(final String in_map_occur_cnt) {
        this.in_map_occur_cnt = in_map_occur_cnt;
    }

    /**
     * In MAP_발생건수 취득
     *
     * @return in_map_occur_cnt In MAP_발생건수
     */
    public String getIn_map_occur_cnt() {
        return StringUtils.defaultString(this.in_map_occur_cnt);
    }

    /**
     * In MAP_패킷 사이즈 합계 설정
     *
     * @param in_map_pkt_size In MAP_패킷 사이즈 합계
     */
    public void setIn_map_pkt_size(final String in_map_pkt_size) {
        this.in_map_pkt_size = in_map_pkt_size;
    }

    /**
     * In MAP_패킷 사이즈 합계 취득
     *
     * @return in_map_pkt_size In MAP_패킷 사이즈 합계
     */
    public String getIn_map_pkt_size() {
        return StringUtils.defaultString(this.in_map_pkt_size);
    }

    /**
     * Out MAP_발생건수 설정
     *
     * @param out_map_occur_cnt Out MAP_발생건수
     */
    public void setOut_map_occur_cnt(final String out_map_occur_cnt) {
        this.out_map_occur_cnt = out_map_occur_cnt;
    }

    /**
     * Out MAP_발생건수 취득
     *
     * @return out_map_occur_cnt Out MAP_발생건수
     */
    public String getOut_map_occur_cnt() {
        return StringUtils.defaultString(this.out_map_occur_cnt);
    }

    /**
     * Out MAP_패킷 사이즈 합계 설정
     *
     * @param out_map_pkt_size Out MAP_패킷 사이즈 합계
     */
    public void setOut_map_pkt_size(final String out_map_pkt_size) {
        this.out_map_pkt_size = out_map_pkt_size;
    }

    /**
     * Out MAP_패킷 사이즈 합계 취득
     *
     * @return out_map_pkt_size Out MAP_패킷 사이즈 합계
     */
    public String getOut_map_pkt_size() {
        return StringUtils.defaultString(this.out_map_pkt_size);
    }

    /**
     * 링크셋 설정
     *
     * @param linkset 링크셋
     */
    public void setLinkset(final String linkset) {
        this.linkset = linkset;
    }

    /**
     * 링크셋 취득
     *
     * @return linkset 링크셋
     */
    public String getLinkset() {
        return StringUtils.defaultString(this.linkset);
    }

    /**
     * Dest Routing peer 설정
     *
     * @param dst_routing_peer Dest Routing peer
     */
    public void setDst_routing_peer(final String dst_routing_peer) {
        this.dst_routing_peer = dst_routing_peer;
    }

    /**
     * Dest Routing peer 취득
     *
     * @return dst_routing_peer Dest Routing peer
     */
    public String getDst_routing_peer() {
        return StringUtils.defaultString(this.dst_routing_peer);
    }

    /**
     * Destination 국가 설정
     *
     * @param dst_country_nm Destination 국가
     */
    public void setDst_country_nm(final String dst_country_nm) {
        this.dst_country_nm = dst_country_nm;
    }

    /**
     * Destination 국가 취득
     *
     * @return dst_country_nm Destination 국가
     */
    public String getDst_country_nm() {
        return StringUtils.defaultString(this.dst_country_nm);
    }

    /**
     * Destination 사업자 설정
     *
     * @param dst_carrier_nm Destination 사업자
     */
    public void setDst_carrier_nm(final String dst_carrier_nm) {
        this.dst_carrier_nm = dst_carrier_nm;
    }

    /**
     * Destination 사업자 취득
     *
     * @return dst_carrier_nm Destination 사업자
     */
    public String getDst_carrier_nm() {
        return StringUtils.defaultString(this.dst_carrier_nm);
    }

    /**
     * Destination 국소 설정
     *
     * @param dst_local Destination 국소
     */
    public void setDst_local(final String dst_local) {
        this.dst_local = dst_local;
    }

    /**
     * Destination 국소 취득
     *
     * @return dst_local Destination 국소
     */
    public String getDst_local() {
        return StringUtils.defaultString(this.dst_local);
    }

    /**
     * Destination Realm 설정
     *
     * @param dst_realm Destination Realm
     */
    public void setDst_realm(final String dst_realm) {
        this.dst_realm = dst_realm;
    }

    /**
     * Destination Realm 취득
     *
     * @return dst_realm Destination Realm
     */
    public String getDst_realm() {
        return StringUtils.defaultString(this.dst_realm);
    }

    /**
     * Destination Peer 설정
     *
     * @param dst_host Destination Peer
     */
    public void setDst_host(final String dst_host) {
        this.dst_host = dst_host;
    }

    /**
     * Destination Peer 취득
     *
     * @return dst_host Destination Peer
     */
    public String getDst_host() {
        return StringUtils.defaultString(this.dst_host);
    }

    /**
     * 국가 설정
     *
     * @param country_nm 국가
     */
    public void setCountry_nm(final String country_nm) {
        this.country_nm = country_nm;
    }

    /**
     * 국가 취득
     *
     * @return country_nm 국가
     */
    public String getCountry_nm() {
        return StringUtils.defaultString(this.country_nm);
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
     * 국소 설정
     *
     * @param local 국소
     */
    public void setLocal(final String local) {
        this.local = local;
    }

    /**
     * 국소 취득
     *
     * @return local 국소
     */
    public String getLocal() {
        return StringUtils.defaultString(this.local);
    }

    /**
     * 국소 설정
     *
     * @param locality 국소
     */
    public void setLocality(final String locality) {
        this.locality = locality;
    }

    /**
     * 국소 취득
     *
     * @return locality 국소
     */
    public String getLocality() {
        return StringUtils.defaultString(this.locality);
    }

    /**
     * realm 설정
     *
     * @param realm realm
     */
    public void setRealm(final String realm) {
        this.realm = realm;
    }

    /**
     * realm 취득
     *
     * @return realm realm
     */
    public String getRealm() {
        return StringUtils.defaultString(this.realm);
    }

    /**
     * host 설정
     *
     * @param host host
     */
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * host 취득
     *
     * @return host host
     */
    public String getHost() {
        return StringUtils.defaultString(this.host);
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
     * 메시지건수 설정
     *
     * @param in_occur_cnt 메시지건수
     */
    public void setIn_occur_cnt(final String in_occur_cnt) {
        this.in_occur_cnt = in_occur_cnt;
    }

    /**
     * 메시지건수 취득
     *
     * @return in_occur_cnt 메시지건수
     */
    public String getIn_occur_cnt() {
        return StringUtils.defaultString(this.in_occur_cnt);
    }

    /**
     * tps 설정
     *
     * @param in_tps tps
     */
    public void setIn_tps(final String in_tps) {
        this.in_tps = in_tps;
    }

    /**
     * tps 취득
     *
     * @return in_tps tps
     */
    public String getIn_tps() {
        return StringUtils.defaultString(this.in_tps);
    }

    /**
     * 패킷사이즈 설정
     *
     * @param in_pkt_size 패킷사이즈
     */
    public void setIn_pkt_size(final String in_pkt_size) {
        this.in_pkt_size = in_pkt_size;
    }

    /**
     * 패킷사이즈 취득
     *
     * @return in_pkt_size 패킷사이즈
     */
    public String getIn_pkt_size() {
        return StringUtils.defaultString(this.in_pkt_size);
    }

    /**
     * 성공건수 설정
     *
     * @param in_succ_cnt 성공건수
     */
    public void setIn_succ_cnt(final String in_succ_cnt) {
        this.in_succ_cnt = in_succ_cnt;
    }

    /**
     * 성공건수 취득
     *
     * @return in_succ_cnt 성공건수
     */
    public String getIn_succ_cnt() {
        return StringUtils.defaultString(this.in_succ_cnt);
    }

    /**
     * 실패건수 설정
     *
     * @param in_fail_cnt 실패건수
     */
    public void setIn_fail_cnt(final String in_fail_cnt) {
        this.in_fail_cnt = in_fail_cnt;
    }

    /**
     * 실패건수 취득
     *
     * @return in_fail_cnt 실패건수
     */
    public String getIn_fail_cnt() {
        return StringUtils.defaultString(this.in_fail_cnt);
    }

    /**
     * 성공율 설정
     *
     * @param in_succ_percent 성공율
     */
    public void setIn_succ_percent(final String in_succ_percent) {
        this.in_succ_percent = in_succ_percent;
    }

    /**
     * 성공율 취득
     *
     * @return in_succ_percent 성공율
     */
    public String getIn_succ_percent() {
        return StringUtils.defaultString(this.in_succ_percent);
    }

    /**
     * 실패율 설정
     *
     * @param in_fail_percent 실패율
     */
    public void setIn_fail_percent(final String in_fail_percent) {
        this.in_fail_percent = in_fail_percent;
    }

    /**
     * 실패율 취득
     *
     * @return in_fail_percent 실패율
     */
    public String getIn_fail_percent() {
        return StringUtils.defaultString(this.in_fail_percent);
    }

    /**
     * 메시지건수 설정
     *
     * @param out_occur_cnt 메시지건수
     */
    public void setOut_occur_cnt(final String out_occur_cnt) {
        this.out_occur_cnt = out_occur_cnt;
    }

    /**
     * 메시지건수 취득
     *
     * @return out_occur_cnt 메시지건수
     */
    public String getOut_occur_cnt() {
        return StringUtils.defaultString(this.out_occur_cnt);
    }

    /**
     * tps 설정
     *
     * @param out_tps tps
     */
    public void setOut_tps(final String out_tps) {
        this.out_tps = out_tps;
    }

    /**
     * tps 취득
     *
     * @return out_tps tps
     */
    public String getOut_tps() {
        return StringUtils.defaultString(this.out_tps);
    }

    /**
     * 패킷사이즈 설정
     *
     * @param out_pkt_size 패킷사이즈
     */
    public void setOut_pkt_size(final String out_pkt_size) {
        this.out_pkt_size = out_pkt_size;
    }

    /**
     * 패킷사이즈 취득
     *
     * @return out_pkt_size 패킷사이즈
     */
    public String getOut_pkt_size() {
        return StringUtils.defaultString(this.out_pkt_size);
    }

    /**
     * 성공건수 설정
     *
     * @param out_succ_cnt 성공건수
     */
    public void setOut_succ_cnt(final String out_succ_cnt) {
        this.out_succ_cnt = out_succ_cnt;
    }

    /**
     * 성공건수 취득
     *
     * @return out_succ_cnt 성공건수
     */
    public String getOut_succ_cnt() {
        return StringUtils.defaultString(this.out_succ_cnt);
    }

    /**
     * 실패건수 설정
     *
     * @param out_fail_cnt 실패건수
     */
    public void setOut_fail_cnt(final String out_fail_cnt) {
        this.out_fail_cnt = out_fail_cnt;
    }

    /**
     * 실패건수 취득
     *
     * @return out_fail_cnt 실패건수
     */
    public String getOut_fail_cnt() {
        return StringUtils.defaultString(this.out_fail_cnt);
    }

    /**
     * 성공율 설정
     *
     * @param out_succ_percent 성공율
     */
    public void setOut_succ_percent(final String out_succ_percent) {
        this.out_succ_percent = out_succ_percent;
    }

    /**
     * 성공율 취득
     *
     * @return out_succ_percent 성공율
     */
    public String getOut_succ_percent() {
        return StringUtils.defaultString(this.out_succ_percent);
    }

    /**
     * 실패율 설정
     *
     * @param out_fail_percent 실패율
     */
    public void setOut_fail_percent(final String out_fail_percent) {
        this.out_fail_percent = out_fail_percent;
    }

    /**
     * 실패율 취득
     *
     * @return out_fail_percent 실패율
     */
    public String getOut_fail_percent() {
        return StringUtils.defaultString(this.out_fail_percent);
    }

    /**
     * 루트
     */
    private String pcap_file_root = "";
    /**
     * 이름
     */
    private String pcap_file_name = "";
    /**
     * 유형
     */
    private String pcap_file_type = "";
    /**
     * 크기
     */
    private String pcap_file_size = "";
    /**
     * 상위
     */
    private String pcap_file_parent = "";
    /**
     * 파일명뷰
     */
    private String pcap_file_view = "";

    /**
     * 파일명뷰 설정
     *
     * @param pcap_file_view 파일명뷰
     */
    public void setPcap_file_view(final String pcap_file_view) {
        this.pcap_file_view = pcap_file_view;
    }

    /**
     * 파일명뷰 취득
     *
     * @return pcap_file_view 파일명뷰
     */
    public String getPcap_file_view() {
        return StringUtils.defaultString(this.pcap_file_view);
    }

    /**
     * 상위 설정
     *
     * @param pcap_file_parent 상위
     */
    public void setPcap_file_parent(final String pcap_file_parent) {
        this.pcap_file_parent = pcap_file_parent;
    }

    /**
     * 상위 취득
     *
     * @return pcap_file_parent 상위
     */
    public String getPcap_file_parent() {
        return StringUtils.defaultString(this.pcap_file_parent);
    }

    /**
     * 루트 설정
     *
     * @param pcap_file_root 루트
     */
    public void setPcap_file_root(final String pcap_file_root) {
        this.pcap_file_root = pcap_file_root;
    }

    /**
     * 루트 취득
     *
     * @return pcap_file_root 루트
     */
    public String getPcap_file_root() {
        return StringUtils.defaultString(this.pcap_file_root);
    }

    /**
     * 이름 설정
     *
     * @param pcap_file_name 이름
     */
    public void setPcap_file_name(final String pcap_file_name) {
        this.pcap_file_name = pcap_file_name;
    }

    /**
     * 이름 취득
     *
     * @return pcap_file_name 이름
     */
    public String getPcap_file_name() {
        return StringUtils.defaultString(this.pcap_file_name);
    }

    /**
     * 유형 설정
     *
     * @param pcap_file_type 유형
     */
    public void setPcap_file_type(final String pcap_file_type) {
        this.pcap_file_type = pcap_file_type;
    }

    /**
     * 유형 취득
     *
     * @return pcap_file_type 유형
     */
    public String getPcap_file_type() {
        return StringUtils.defaultString(this.pcap_file_type);
    }

    /**
     * 크기 설정
     *
     * @param pcap_file_size 크기
     */
    public void setPcap_file_size(final String pcap_file_size) {
        this.pcap_file_size = pcap_file_size;
    }

    /**
     * 크기 취득
     *
     * @return pcap_file_size 크기
     */
    public String getPcap_file_size() {
        return StringUtils.defaultString(this.pcap_file_size);
    }

    /**
     * cg gt
     */
    private String cg_gt = "";
    /**
     * cg pc list nm
     */
    private String cg_pc_list_nm = "";
    /**
     * cg 사업자
     */
    private String cg_carrier_nm = "";
    /**
     * cd gt
     */
    private String cd_gt = "";
    /**
     * cd pc list nm
     */
    private String cd_pc_list_nm = "";
    /**
     * cd 사업자
     */
    private String cd_carrier_nm = "";

    /**
     * cg gt 설정
     *
     * @param cg_gt cg gt
     */
    public void setCg_gt(final String cg_gt) {
        this.cg_gt = cg_gt;
    }

    /**
     * cg gt 취득
     *
     * @return cg_gt cg gt
     */
    public String getCg_gt() {
        return StringUtils.defaultString(this.cg_gt);
    }

    /**
     * cg pc list nm 설정
     *
     * @param cg_pc_list_nm cg pc list nm
     */
    public void setCg_pc_list_nm(final String cg_pc_list_nm) {
        this.cg_pc_list_nm = cg_pc_list_nm;
    }

    /**
     * cg pc list nm 취득
     *
     * @return cg_pc_list_nm cg pc list nm
     */
    public String getCg_pc_list_nm() {
        return StringUtils.defaultString(this.cg_pc_list_nm);
    }

    /**
     * cg 사업자 설정
     *
     * @param cg_carrier_nm cg 사업자
     */
    public void setCg_carrier_nm(final String cg_carrier_nm) {
        this.cg_carrier_nm = cg_carrier_nm;
    }

    /**
     * cg 사업자 취득
     *
     * @return cg_carrier_nm cg 사업자
     */
    public String getCg_carrier_nm() {
        return StringUtils.defaultString(this.cg_carrier_nm);
    }

    /**
     * cd gt 설정
     *
     * @param cd_gt cd gt
     */
    public void setCd_gt(final String cd_gt) {
        this.cd_gt = cd_gt;
    }

    /**
     * cd gt 취득
     *
     * @return cd_gt cd gt
     */
    public String getCd_gt() {
        return StringUtils.defaultString(this.cd_gt);
    }

    /**
     * cd pc list nm 설정
     *
     * @param cd_pc_list_nm cd pc list nm
     */
    public void setCd_pc_list_nm(final String cd_pc_list_nm) {
        this.cd_pc_list_nm = cd_pc_list_nm;
    }

    /**
     * cd pc list nm 취득
     *
     * @return cd_pc_list_nm cd pc list nm
     */
    public String getCd_pc_list_nm() {
        return StringUtils.defaultString(this.cd_pc_list_nm);
    }

    /**
     * cd 사업자 설정
     *
     * @param cd_carrier_nm cd 사업자
     */
    public void setCd_carrier_nm(final String cd_carrier_nm) {
        this.cd_carrier_nm = cd_carrier_nm;
    }

    /**
     * cd 사업자 취득
     *
     * @return cd_carrier_nm cd 사업자
     */
    public String getCd_carrier_nm() {
        return StringUtils.defaultString(this.cd_carrier_nm);
    }

    public static final Comparator<ChartInfo> ChartTypeComparator = new Comparator<ChartInfo>() {
        @Override
        public int compare(final ChartInfo s1, final ChartInfo s2) {
            final String TypeName1 = s1.getPcap_file_type().toUpperCase();
            final String TypeName2 = s2.getPcap_file_type().toUpperCase();

            // ascending order
            return TypeName1.compareTo(TypeName2);

            // descending order
            // return StudentName2.compareTo(StudentName1);
        }
    };

}