/*****************************************************************************
 * 프로그램명  : alarmInquiry.java
 * 설     명  : 장애  관리 - 알람 조회  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.01 KYM    1.0
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarminquiry;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

public class AlarmInquiry extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -6461500859296943752L;
    /**
     * 타켓 코드
     */
    private String tgt_cd = "";
    /**
     * 타겟명
     */
    private String tgt_name = "";
    /**
     * SLOT1 사용율
     */
    private String usage_per1 = "";
    /**
     * SLOT2 사용율
     */
    private String usage_per2 = "";
    /**
     * SLOT3 사용율
     */
    private String usage_per3 = "";
    /**
     * SLOT4 사용율
     */
    private String usage_per4 = "";
    /**
     * SLOT5 사용율
     */
    private String usage_per5 = "";
    /**
     * SLOT6 사용율
     */
    private String usage_per6 = "";
    /**
     * SLOT7 사용율
     */
    private String usage_per7 = "";
    /**
     * SLOT8 사용율
     */
    private String usage_per8 = "";
    /**
     * SLOT9 사용율
     */
    private String usage_per9 = "";
    /**
     * SLOT10 사용율
     */
    private String usage_per10 = "";
    /**
     * SLOT11 사용율
     */
    private String usage_per11 = "";
    /**
     * 정렬순서
     */
    private String sort_no = "";
    /**
     * 사용여부
     */
    private String use_yn = "";
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

    private String alm_group = "";
    private String alm_instance = "";
    private String alm_recovery = "";

    /**
     * 알람 등급
     */
    private String srch_alm_level = "";
    private String srch_alm_instance = "";
    /**
     * 알람 등급 배열
     */
    private String[] ar_search_alm_level = null;

    public String getSrch_alm_level() {
        return this.srch_alm_level;
    }

    public void setSrch_alm_level(final String srch_alm_level) {
        this.srch_alm_level = srch_alm_level;
    }

    public String[] getAr_search_alm_level() {
        return Arrays.copyOf(this.ar_search_alm_level, this.ar_search_alm_level.length);
    }

    public void setAr_search_alm_level(final String[] ar_search_alm_level) {
        this.ar_search_alm_level = Arrays.copyOf(ar_search_alm_level, ar_search_alm_level.length);
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

    /**
     * 타켓 코드 설정
     *
     * @param tgt_cd 타켓 코드
     */
    public void setTgt_cd(final String tgt_cd) {
        this.tgt_cd = tgt_cd;
    }

    /**
     * 타켓 코드 취득
     *
     * @return tgt_cd 타켓 코드
     */
    public String getTgt_cd() {
        return StringUtils.defaultString(this.tgt_cd);
    }

    /**
     * 타겟명 설정
     *
     * @param tgt_name 타겟명
     */
    public void setTgt_name(final String tgt_name) {
        this.tgt_name = tgt_name;
    }

    /**
     * 타겟명 취득
     *
     * @return tgt_name 타겟명
     */
    public String getTgt_name() {
        return StringUtils.defaultString(this.tgt_name);
    }

    /**
     * SLOT1 사용율 설정
     *
     * @param usage_per1 SLOT1 사용율
     */
    public void setUsage_per1(final String usage_per1) {
        this.usage_per1 = usage_per1;
    }

    /**
     * SLOT1 사용율 취득
     *
     * @return usage_per1 SLOT1 사용율
     */
    public String getUsage_per1() {
        return StringUtils.defaultString(this.usage_per1);
    }

    /**
     * SLOT2 사용율 설정
     *
     * @param usage_per2 SLOT2 사용율
     */
    public void setUsage_per2(final String usage_per2) {
        this.usage_per2 = usage_per2;
    }

    /**
     * SLOT2 사용율 취득
     *
     * @return usage_per2 SLOT2 사용율
     */
    public String getUsage_per2() {
        return StringUtils.defaultString(this.usage_per2);
    }

    /**
     * SLOT3 사용율 설정
     *
     * @param usage_per3 SLOT3 사용율
     */
    public void setUsage_per3(final String usage_per3) {
        this.usage_per3 = usage_per3;
    }

    /**
     * SLOT3 사용율 취득
     *
     * @return usage_per3 SLOT3 사용율
     */
    public String getUsage_per3() {
        return StringUtils.defaultString(this.usage_per3);
    }

    /**
     * SLOT4 사용율 설정
     *
     * @param usage_per4 SLOT4 사용율
     */
    public void setUsage_per4(final String usage_per4) {
        this.usage_per4 = usage_per4;
    }

    /**
     * SLOT4 사용율 취득
     *
     * @return usage_per4 SLOT4 사용율
     */
    public String getUsage_per4() {
        return StringUtils.defaultString(this.usage_per4);
    }

    /**
     * SLOT5 사용율 설정
     *
     * @param usage_per5 SLOT5 사용율
     */
    public void setUsage_per5(final String usage_per5) {
        this.usage_per5 = usage_per5;
    }

    /**
     * SLOT5 사용율 취득
     *
     * @return usage_per5 SLOT5 사용율
     */
    public String getUsage_per5() {
        return StringUtils.defaultString(this.usage_per5);
    }

    /**
     * SLOT6 사용율 설정
     *
     * @param usage_per6 SLOT6 사용율
     */
    public void setUsage_per6(final String usage_per6) {
        this.usage_per6 = usage_per6;
    }

    /**
     * SLOT6 사용율 취득
     *
     * @return usage_per6 SLOT6 사용율
     */
    public String getUsage_per6() {
        return StringUtils.defaultString(this.usage_per6);
    }

    /**
     * SLOT7 사용율 설정
     *
     * @param usage_per7 SLOT7 사용율
     */
    public void setUsage_per7(final String usage_per7) {
        this.usage_per7 = usage_per7;
    }

    /**
     * SLOT7 사용율 취득
     *
     * @return usage_per7 SLOT7 사용율
     */
    public String getUsage_per7() {
        return StringUtils.defaultString(this.usage_per7);
    }

    /**
     * SLOT8 사용율 설정
     *
     * @param usage_per8 SLOT8 사용율
     */
    public void setUsage_per8(final String usage_per8) {
        this.usage_per8 = usage_per8;
    }

    /**
     * SLOT8 사용율 취득
     *
     * @return usage_per8 SLOT8 사용율
     */
    public String getUsage_per8() {
        return StringUtils.defaultString(this.usage_per8);
    }

    /**
     * SLOT9 사용율 설정
     *
     * @param usage_per9 SLOT9 사용율
     */
    public void setUsage_per9(final String usage_per9) {
        this.usage_per9 = usage_per9;
    }

    /**
     * SLOT9 사용율 취득
     *
     * @return usage_per9 SLOT9 사용율
     */
    public String getUsage_per9() {
        return StringUtils.defaultString(this.usage_per9);
    }

    /**
     * SLOT10 사용율 설정
     *
     * @param usage_per10 SLOT10 사용율
     */
    public void setUsage_per10(final String usage_per10) {
        this.usage_per10 = usage_per10;
    }

    /**
     * SLOT10 사용율 취득
     *
     * @return usage_per10 SLOT10 사용율
     */
    public String getUsage_per10() {
        return StringUtils.defaultString(this.usage_per10);
    }

    /**
     * SLOT11 사용율 설정
     *
     * @param usage_per11 SLOT11 사용율
     */
    public void setUsage_per11(final String usage_per11) {
        this.usage_per11 = usage_per11;
    }

    /**
     * SLOT11 사용율 취득
     *
     * @return usage_per11 SLOT11 사용율
     */
    public String getUsage_per11() {
        return StringUtils.defaultString(this.usage_per11);
    }

    /**
     * 정렬순서 설정
     *
     * @param sort_no 정렬순서
     */
    public void setSort_no(final String sort_no) {
        this.sort_no = sort_no;
    }

    /**
     * 정렬순서 취득
     *
     * @return sort_no 정렬순서
     */
    public String getSort_no() {
        return StringUtils.defaultString(this.sort_no);
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
     * 발생순차번호
     */
    private String seqno = "";
    /**
     * 호스트명
     */
    private String host_nm = "";
    /**
     * 프로세스명
     */
    private String proc_nm = "";
    /**
     * 알람 발생
     */
    private String alm_pid = "";
    /**
     * 알람카테고리
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
     * 알람레벨
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
     * 상태
     */
    private String alm_status = "";
    /**
     * 알람등급순위
     */
    private String alm_priority = "";

    /**
     * 결과
     */
    private String result = "";
    /**
     * 메세지
     */
    private String message = "";
    /**
     * 레벨
     */
    private String level = "";
    /**
     * 파일
     */
    private String file = "";
    /**
     * 상태
     */
    private String status = "";

    /**
     * CRI 갯수
     */
    private String cri_num = "";
    /**
     * MAJ 갯수
     */
    private String maj_num = "";
    /**
     * MIN 갯수
     */
    private String min_num = "";
    /**
     * WAN 갯수
     */
    private String wan_num = "";

    /**
     * 인지시간
     */
    private String alm_know_dt = "";

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
     * CRI 갯수 설정
     *
     * @param cri_num CRI 갯수
     */
    public void setCri_num(final String cri_num) {
        this.cri_num = cri_num;
    }

    /**
     * CRI 갯수 취득
     *
     * @return cri_num CRI 갯수
     */
    public String getCri_num() {
        return StringUtils.defaultString(this.cri_num);
    }

    /**
     * MAJ 갯수 설정
     *
     * @param maj_num MAJ 갯수
     */
    public void setMaj_num(final String maj_num) {
        this.maj_num = maj_num;
    }

    /**
     * MAJ 갯수 취득
     *
     * @return maj_num MAJ 갯수
     */
    public String getMaj_num() {
        return StringUtils.defaultString(this.maj_num);
    }

    /**
     * MIN 갯수 설정
     *
     * @param min_num MIN 갯수
     */
    public void setMin_num(final String min_num) {
        this.min_num = min_num;
    }

    /**
     * MIN 갯수 취득
     *
     * @return min_num MIN 갯수
     */
    public String getMin_num() {
        return StringUtils.defaultString(this.min_num);
    }

    /**
     * WAN 갯수 설정
     *
     * @param wan_num WAN 갯수
     */
    public void setWan_num(final String wan_num) {
        this.wan_num = wan_num;
    }

    /**
     * WAN 갯수 취득
     *
     * @return wan_num WAN 갯수
     */
    public String getWan_num() {
        return StringUtils.defaultString(this.wan_num);
    }

    /**
     * 결과 설정
     *
     * @param result 결과
     */
    public void setResult(final String result) {
        this.result = result;
    }

    /**
     * 결과 취득
     *
     * @return result 결과
     */
    public String getResult() {
        return StringUtils.defaultString(this.result);
    }

    /**
     * 메세지 설정
     *
     * @param message 메세지
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 메세지 취득
     *
     * @return message 메세지
     */
    public String getMessage() {
        return StringUtils.defaultString(this.message);
    }

    /**
     * 레벨 설정
     *
     * @param level 레벨
     */
    public void setLevel(final String level) {
        this.level = level;
    }

    /**
     * 레벨 취득
     *
     * @return level 레벨
     */
    public String getLevel() {
        return StringUtils.defaultString(this.level);
    }

    /**
     * 파일패스 설정
     *
     * @param file 파일패스
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * 파일패스 취득
     *
     * @return file 파일패스
     */
    public String getFile() {
        return StringUtils.defaultString(this.file);
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
     * 알람등급순위 설정
     *
     * @param alm_priority 알람등급순위
     */
    public void setAlm_priority(final String alm_priority) {
        this.alm_priority = alm_priority;
    }

    /**
     * 알람등급순위 취득
     *
     * @return alm_priority 알람등급순위
     */
    public String getAlm_priority() {
        return StringUtils.defaultString(this.alm_priority);
    }

    /**
     * 상태 설정
     *
     * @param alm_status 상태
     */
    public void setAlm_status(final String alm_status) {
        this.alm_status = alm_status;
    }

    /**
     * 상태 취득
     *
     * @return alm_status 상태
     */
    public String getAlm_status() {
        return StringUtils.defaultString(this.alm_status);
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
     * 알람 발생 설정
     *
     * @param alm_pid 알람 발생
     */
    public void setAlm_pid(final String alm_pid) {
        this.alm_pid = alm_pid;
    }

    /**
     * 알람 발생 취득
     *
     * @return alm_pid 알람 발생
     */
    public String getAlm_pid() {
        return StringUtils.defaultString(this.alm_pid);
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
     * 알람레벨 설정
     *
     * @param alm_level 알람레벨
     */
    public void setAlm_level(final String alm_level) {
        this.alm_level = alm_level;
    }

    /**
     * 알람레벨 취득
     *
     * @return alm_level 알람레벨
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
     * 파리미터1
     */
    private String p_src_env = "";
    /**
     * 파리미터2
     */
    private String p_data_param = "";
    /**
     * 결과코드
     */
    private int p_retcode = 0;
    /**
     * 결과메시지
     */
    private String p_retmsg = "";

    /**
     * 작업형태
     */
    private String work_type = "";

    /**
     * 작업형태 설정
     *
     * @param work_type 작업형태
     */
    public void setWork_type(final String work_type) {
        this.work_type = work_type;
    }

    /**
     * 작업형태 취득
     *
     * @return work_type 작업형태
     */
    public String getWork_type() {
        return StringUtils.defaultString(this.work_type);
    }

    /**
     * 파리미터1 설정
     *
     * @param p_src_env 파리미터1
     */
    public void setP_src_env(final String p_src_env) {
        this.p_src_env = p_src_env;
    }

    /**
     * 파리미터1 취득
     *
     * @return p_src_env 파리미터1
     */
    public String getP_src_env() {
        return StringUtils.defaultString(this.p_src_env);
    }

    /**
     * 파리미터2 설정
     *
     * @param p_data_param 파리미터2
     */
    public void setP_data_param(final String p_data_param) {
        this.p_data_param = p_data_param;
    }

    /**
     * 파리미터2 취득
     *
     * @return p_data_param 파리미터2
     */
    public String getP_data_param() {
        return StringUtils.defaultString(this.p_data_param);
    }

    /**
     * 결과코드 설정
     *
     * @param p_retcode 결과코드
     */
    public void setP_retcode(final int p_retcode) {
        this.p_retcode = p_retcode;
    }

    /**
     * 결과코드 취득
     *
     * @return p_retcode 결과코드
     */
    public int getP_retcode() {
        return this.p_retcode;
    }

    /**
     * 결과메시지 설정
     *
     * @param p_retmsg 결과메시지
     */
    public void setP_retmsg(final String p_retmsg) {
        this.p_retmsg = p_retmsg;
    }

    /**
     * 결과메시지 취득
     *
     * @return p_retmsg 결과메시지
     */
    public String getP_retmsg() {
        return StringUtils.defaultString(this.p_retmsg);
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

    public String getSrch_alm_instance() {
        return this.srch_alm_instance;
    }

    public void setSrch_alm_instance(final String srch_alm_instance) {
        this.srch_alm_instance = srch_alm_instance;
    }

}
