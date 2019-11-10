/*****************************************************************************
 * 프로그램명  : User.java
 * 설     명  : ȸ��유저 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 유저 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class UserVO extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -1378071513507034024L;
    /**
     * 유저아이디
     */
    private String id = "";
    /**
     * EMPID
     */
    private String emp_Id = "";
    /**
     * 유저이름
     */
    private String name = "";
    /**
     * 비밀번호
     */
    private String password = "";
    /**
     * 이전 비밀번호
     */
    private String oldPassword = "";
    private String tempPassword = "";
    /**
     * 부서아이디
     */
    private String deptId = "";
    /**
     * 부서명칭
     */
    private String deptName = "";
    /**
     * 권한레벨코드
     */
    private String levelcod = "";
    /**
     * 권한레벨코드명
     */
    private String levelcodnm = "";

    /**
     * 갱신날짜
     */
    private String updDate = "";
    /**
     * 로그인날짜
     */
    private String loginDate = "";
    /**
     * 로그인시간
     */
    private String loginTime = "";
    /**
     * 로그아웃날짜
     */
    private String logoutDate = "";
    /**
     * 접속아이피
     */
    private String remoteIp = "";
    /**
     * 세션아이디
     */
    private String sesionId = "";

    /**
     * 사용자그룹코드
     */
    private String groupId = "";
    /**
     * 사용자그룹코드명
     */
    private String groupNm = "";
    /**
     * 그룹레벨코드
     */
    private String groupLevelCod = "";
    /**
     * 그룹레벨코드명
     */
    private String groupLevelCodNm = "";
    /**
     * 이메일
     */
    private String email = "";
    /**
     * 연락처
     */
    private String phone = "";
    /**
     * SMS전화번호
     */
    private String smsPhone = "";

    /**
     * 시스템 어드민 여부
     */
    private String admin = "";
    /**
     * 사용여부
     */
    private String status = "";

    /**
     * 등록자아이디
     */
    private String regUserId = "";

    /**
     * MID or GID
     */
    private String xid = "";
    /**
     * 작성날
     */
    private String crtdt = "";

    /**
     * 사용자이름
     */
    private String empnm = "";

    /**
     * 회사id
     */
    private String company_id = "";
    /**
     * 회사명
     */
    private String company_nm = "";

    /**
     * 사용자언어
     */
    private String user_lang = "";

    /**
     * 빌링계정 이름
     */
    private String cust_acct_name = "";
    /**
     * 로그인아이디
     */
    private String login_id = "";
    /**
     * 로그인암호
     */
    private String login_pwd = "";

    /**
     * 알람레벨
     */
    private String alarmLevel = "";
    /**
     * 알람상태
     */
    private String alarmStatus = "";
    /**
     * 알람상태설정시간
     */
    private String alarmStatusSetTime = "";

    /**
     * 알람레벨 설정
     *
     * @param alarmLevel 알람레벨
     */
    public void setAlarmLevel(final String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    /**
     * 알람레벨 취득
     *
     * @return alarmLevel 알람레벨
     */
    public String getAlarmLevel() {
        return this.alarmLevel;
    }

    /**
     * 알람상태 설정
     *
     * @param alarmStatus 알람상태
     */
    public void setAlarmStatus(final String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    /**
     * 알람상태 취득
     *
     * @return alarmStatus 알람상태
     */
    public String getAlarmStatus() {
        return this.alarmStatus;
    }

    /**
     * 알람상태설정시간 설정
     *
     * @param alarmStatusSetTime 알람상태설정시간
     */
    public void setAlarmStatusSetTime(final String alarmStatusSetTime) {
        this.alarmStatusSetTime = alarmStatusSetTime;
    }

    /**
     * 알람상태설정시간 취득
     *
     * @return alarmStatusSetTime 알람상태설정시간
     */
    public String getAlarmStatusSetTime() {
        return this.alarmStatusSetTime;
    }

    /**
     * 로그인암호 설정
     *
     * @param login_pwd 로그인암호
     */
    public void setLogin_pwd(final String login_pwd) {
        this.login_pwd = login_pwd;
    }

    /**
     * 로그인암호 취득
     *
     * @return login_pwd 로그인암호
     */
    public String getLogin_pwd() {
        return StringUtils.defaultString(this.login_pwd);
    }

    /**
     * 빌링계정 이름 설정
     *
     * @param cust_acct_name 빌링계정 이름
     */
    public void setCust_acct_name(final String cust_acct_name) {
        this.cust_acct_name = cust_acct_name;
    }

    /**
     * 빌링계정 이름 취득
     *
     * @return cust_acct_name 빌링계정 이름
     */
    public String getCust_acct_name() {
        return StringUtils.defaultString(this.cust_acct_name);
    }

    /**
     * 로그인아이디 설정
     *
     * @param login_id 로그인아이디
     */
    public void setLogin_id(final String login_id) {
        this.login_id = login_id;
    }

    /**
     * 로그인아이디 취득
     *
     * @return login_id 로그인아이디
     */
    public String getLogin_id() {
        return StringUtils.defaultString(this.login_id);
    }

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = StringUtils.defaultString("KOR");
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 사용자언어
     */
    public String getUser_lang() {
        return StringUtils.defaultString(this.user_lang, "KOR");
    }

    /**
     * 사용자이름 설정
     *
     * @param empnm 사용자이름
     */
    public void setEmpnm(final String empnm) {
        this.empnm = empnm;
    }

    /**
     * 사용자이름 취득
     *
     * @return empnm 사용자이름
     */
    public String getEmpnm() {
        return StringUtils.defaultString(this.empnm);
    }

    /**
     * 작성날 설정
     *
     * @param crtdt 작성날
     */
    public void setCrtdt(final String crtdt) {
        this.crtdt = crtdt;
    }

    /**
     * 작성날 취득
     *
     * @return crtdt 작성날
     */
    public String getCrtdt() {
        return StringUtils.defaultString(this.crtdt);
    }

    public UserVO() {
    }

    /**
     * @return the levelcod
     */
    public String getLevelcod() {
        return this.levelcod;
    }

    /**
     * @param levelcod the levelcod to set
     */
    public void setLevelcod(final String levelcod) {
        this.levelcod = levelcod;
    }

    /**
     * @return the levelcodnm
     */
    public String getLevelcodnm() {
        return StringUtils.defaultString(this.levelcodnm);
    }

    /**
     * @param levelcod the levelcodnm to set
     */
    public void setLevelcodnm(final String levelcodnm) {
        this.levelcodnm = levelcodnm;
    }

    /**
     * 유저아이디 설정
     *
     * @param id 유저아이디
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * 유저아이디 취득
     *
     * @return String 유저아이디
     */
    public String getId() {
        return this.id;
    }

    /**
     * EMPID 설정
     *
     * @param id 유저아이디
     */
    public void setEmp_Id(final String emp_Id) {
        this.emp_Id = emp_Id;
    }

    /**
     * EMPID 취득
     *
     * @return String 유저아이디
     */
    public String getEmp_Id() {
        return this.emp_Id;
    }

    /**
     * 유저이름 설정
     *
     * @param name 유저이름
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 유저이름 취득
     *
     * @return String 유저이름
     */
    public String getName() {
        return this.name;
    }

    /**
     * 비밀번호 설정
     *
     * @param password 비밀번호
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * 비밀번호 취득
     *
     * @return password 비밀번호
     */
    public String getPassword() {
        return StringUtils.defaultString(this.password);
    }

    /**
     * 이전 비밀번호 설정
     *
     * @param oldPassword 이전 비밀번호
     */
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * 이전 비밀번호 취득
     *
     * @return String 이전 비밀번호
     */
    public String getOldPassword() {
        return this.oldPassword;
    }

    /**
     * 부서아이디 설정
     *
     * @param deptId 부서아이디
     */
    public void setDeptId(final String deptId) {
        this.deptId = deptId;
    }

    /**
     * 부서아이디 취득
     *
     * @return String 부서아이디
     */
    public String getDeptId() {
        return this.deptId;
    }

    /**
     * 부서명칭 설정
     *
     * @param deptName 부서명칭
     */
    public void setDeptName(final String deptName) {
        this.deptName = deptName;
    }

    /**
     * 부서명칭 취득
     *
     * @return String 부서명칭
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * 갱신날짜 설정
     *
     * @param updDate 갱신날짜
     */
    public void setUpdDate(final String updDate) {
        this.updDate = updDate;
    }

    /**
     * 갱신날짜 취득
     *
     * @return String 갱신날짜
     */
    public String getUpdDate() {
        return this.updDate;
    }

    /**
     * 로그인날짜 설정
     *
     * @param loginDate 로그인날짜
     */
    public void setLoginDate(final String loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 로그인날짜 취득
     *
     * @return String 로그인날짜
     */
    public String getLoginDate() {
        return this.loginDate;
    }

    /**
     * 로그인시간 설정
     *
     * @param loginTime 로그인시간
     */
    public void setLoginTime(final String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 로그인시간 취득
     *
     * @return String 로그인시간
     */
    public String getLoginTime() {
        return this.loginTime;
    }

    /**
     * 로그아웃날짜 설정
     *
     * @param logoutDate 로그아웃날짜
     */
    public void setLogoutDate(final String logoutDate) {
        this.logoutDate = logoutDate;
    }

    /**
     * 로그아웃날짜 취득
     *
     * @return String 로그아웃날짜
     */
    public String getLogoutDate() {
        return this.logoutDate;
    }

    /**
     * 접속아이피 설정
     *
     * @param remoteIp 접속아이피
     */
    public void setRemoteIp(final String remoteIp) {
        this.remoteIp = remoteIp;
    }

    /**
     * 접속아이피 취득
     *
     * @return String 접속아이피
     */
    public String getRemoteIp() {
        return this.remoteIp;
    }

    /**
     * 세션아이디 설정
     *
     * @param sesionId 세션아이디
     */
    public void setSesionId(final String sesionId) {
        this.sesionId = sesionId;
    }

    /**
     * 세션아이디 취득
     *
     * @return String 세션아이디
     */
    public String getSesionId() {
        return this.sesionId;
    }

    /**
     * 사용자그룹코드 설정
     *
     * @param groupId 사용자그룹코드
     */
    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    /**
     * 사용자그룹코드 취득
     *
     * @return groupId 사용자그룹코드
     */
    public String getGroupId() {
        return StringUtils.defaultString(this.groupId);
    }

    /**
     * 사용자그룹코드명 설정
     *
     * @param groupNm 사용자그룹코드명
     */
    public void setGroupNm(final String groupNm) {
        this.groupNm = groupNm;
    }

    /**
     * 사용자그룹코드명 취득
     *
     * @return groupNm 사용자그룹코드명
     */
    public String getGroupNm() {
        return StringUtils.defaultString(this.groupNm);
    }

    /**
     * 그룹레벨코드 설정
     *
     * @param groupLevelCod 그룹레벨코드
     */
    public void setGroupLevelCod(final String groupLevelCod) {
        this.groupLevelCod = groupLevelCod;
    }

    /**
     * 그룹레벨코드 취득
     *
     * @return groupLevelCod 그룹레벨코드
     */
    public String getGroupLevelCod() {
        return StringUtils.defaultString(this.groupLevelCod);
    }

    /**
     * 그룹레벨코드명 설정
     *
     * @param groupLevelCodNm 그룹레벨코드명
     */
    public void setGroupLevelCodNm(final String groupLevelCodNm) {
        this.groupLevelCodNm = groupLevelCodNm;
    }

    /**
     * 그룹레벨코드명 취득
     *
     * @return groupLevelCodNm 그룹레벨코드명
     */
    public String getGroupLevelCodNm() {
        return StringUtils.defaultString(this.groupLevelCodNm);
    }

    /**
     * 이메일 설정
     *
     * @param email 이메일
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * 이메일 취득
     *
     * @return email 이메일
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 연락처 설정
     *
     * @param phone 연락처
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * 연락처 취득
     *
     * @return phone 연락처
     */
    public String getPhone() {
        return StringUtils.defaultString(this.phone);
    }

    /**
     * SMS전화번호 설정
     *
     * @param smsPhone SMS전화번호
     */
    public void setSmsPhone(final String smsPhone) {
        this.smsPhone = smsPhone;
    }

    /**
     * SMS전화번호 취득
     *
     * @return email SMS전화번호
     */
    public String getSmsPhone() {
        return this.smsPhone;
    }

    /**
     * 시스템 어드민 여부 설정
     *
     * @param admin 시스템 어드민 여부
     */
    public void setAdmin(final String admin) {
        this.admin = admin;
    }

    /**
     * 시스템 어드민 여부 취득
     *
     * @return String 시스템 어드민 여부
     */
    public String getAdmin() {
        return this.admin;
    }

    /**
     * 사용여부 설정
     *
     * @param status 사용여부
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 사용여부 취득
     *
     * @return status 사용여부
     */
    public String getStatus() {
        return this.status;
    }

    public String getXid() {
        return StringUtils.defaultString(this.xid);
    }

    public void setXid(final String xid) {
        this.xid = xid;
    }

    /**
     * 등록자아이디 설정
     *
     * @param regUserId 등록자아이디
     */
    public void setRegUserId(final String regUserId) {
        this.regUserId = regUserId;
    }

    /**
     * 등록자아이디 취득
     *
     * @return regUserId 등록자아이디
     */
    public String getRegUserId() {
        return StringUtils.defaultString(this.regUserId);
    }

    /**
     * 회사id 설정
     *
     * @param company_id 회사id
     */
    public void setCompany_id(final String company_id) {
        this.company_id = company_id;
    }

    /**
     * 회사id 취득
     *
     * @return company_id 회사id
     */
    public String getCompany_id() {
        return StringUtils.defaultString(this.company_id);
    }

    /**
     * 회사명 설정
     *
     * @param company_nm 회사명
     */
    public void setCompany_nm(final String company_nm) {
        this.company_nm = company_nm;
    }

    /**
     * 회사명 취득
     *
     * @return company_nm 회사명
     */
    public String getCompany_nm() {
        return StringUtils.defaultString(this.company_nm);
    }

    public String getTempPassword() {
        return StringUtils.defaultString(this.tempPassword);
    }

    public void setTempPassword(final String tempPassword) {
        this.tempPassword = tempPassword;
    }

    private String user_id;
    private String user_pwd;
    private String user_level;
    private String user_level_nm;
    private String user_name;
    private String descr;
    private String user_email;
    private String user_phone;
    private String partner;
    private String permission;
    private String crt_dt;
    private String crt_id;
    private String upd_dt;
    private String upd_id;
    private String upd_id_nm;
    private String apply_from;
    private String apply_to;

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(final String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pwd() {
        return this.user_pwd;
    }

    public void setUser_pwd(final String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_level() {
        return this.user_level;
    }

    public void setUser_level(final String user_level) {
        this.user_level = user_level;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(final String user_name) {
        this.user_name = user_name;
    }

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(final String descr) {
        this.descr = descr;
    }

    public String getUser_email() {
        return this.user_email;
    }

    public void setUser_email(final String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return this.user_phone;
    }

    public void setUser_phone(final String user_phone) {
        this.user_phone = user_phone;
    }

    public String getPartner() {
        return this.partner;
    }

    public void setPartner(final String partner) {
        this.partner = partner;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(final String permission) {
        this.permission = permission;
    }

    public String getCrt_dt() {
        return this.crt_dt;
    }

    public void setCrt_dt(final String crt_dt) {
        this.crt_dt = crt_dt;
    }

    public String getCrt_id() {
        return this.crt_id;
    }

    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    public String getUpd_dt() {
        return this.upd_dt;
    }

    public void setUpd_dt(final String upd_dt) {
        this.upd_dt = upd_dt;
    }

    public String getUpd_id() {
        return this.upd_id;
    }

    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    public String getApply_from() {
        return this.apply_from;
    }

    public void setApply_from(final String apply_from) {
        this.apply_from = apply_from;
    }

    public String getApply_to() {
        return this.apply_to;
    }

    public void setApply_to(final String apply_to) {
        this.apply_to = apply_to;
    }

    public String getUser_level_nm() {
        return this.user_level_nm;
    }

    public void setUser_level_nm(final String user_level_nm) {
        this.user_level_nm = user_level_nm;
    }

    public String getUpd_id_nm() {
        return this.upd_id_nm;
    }

    public void setUpd_id_nm(final String upd_id_nm) {
        this.upd_id_nm = upd_id_nm;
    }

}
