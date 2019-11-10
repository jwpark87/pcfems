/*****************************************************************************
 * 프로그램명  : EmpInfo.java
 * 설     명  : 사용자정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2015.05.13  WYA    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.emp;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 로그인IP 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class EmpInfo implements Serializable {
    private transient static final long serialVersionUID = 3444014526811548305L;
    /**
     * 사용자아이디
     */
    private String emp_id = "";
    /**
     * 사용자명
     */
    private String empNm = "";
    /**
     * 사용자그룹아이디
     */
    private String user_group_id = "";
    /**
     * 사용자그룹명
     */
    private String user_group_nm = "";
    /**
     * 비밀번호
     */
    private String password = "";
    /**
     * 이전 비밀번호
     */
    private String oldPassword = "";
    /**
     * 이메일
     */
    private String email = "";
    /**
     * 전화번호
     */
    private String smsPhone = "";
    /**
     * 사용자상태
     */
    private String status = "";
    /**
     * 권한레벨코드
     */
    private String levelCod = "";
    /**
     * 권한레벨코드명
     */
    private String levelCodNm = "";
    /**
     * 등록자
     */
    private String crt_user = "";
    /**
     * 등록시간
     */
    private String crtDt = "";
    /**
     * 회사id
     */
    private Integer company_id = null;
    /**
     * 회사명
     */
    private String company_nm = "";
    /**
     * 우편번호
     */
    private String home_zip = "";
    /**
     * 주소1
     */
    private String home_addr1 = "";
    /**
     * 주소2
     */
    private String home_addr2 = "";
    /**
     * 부서명
     */
    private String depart_name = "";
    /**
     * 부서id
     */
    private String depart_id = "";
    /**
     * 부서정보수정권한
     */
    private String depart_mod_auth = "";
    /**
     * 직책명
     */
    private String duty_name = "";
    /**
     * 패스워드 변경여부
     */
    private String password_chg = "";
    /**
     * 푸쉬 수신여부(Y/N)
     */
    private String push_yn = "";
    private String user_lang = "";

    public String getUser_lang() {
        return this.user_lang;
    }

    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 우편번호 설정
     *
     * @param home_zip 우편번호
     */
    public void setHome_zip(final String home_zip) {
        this.home_zip = home_zip;
    }

    /**
     * 우편번호 취득
     *
     * @return home_zip 우편번호
     */
    public String getHome_zip() {
        return StringUtils.defaultString(this.home_zip);
    }

    /**
     * 주소1 설정
     *
     * @param home_addr1 주소1
     */
    public void setHome_addr1(final String home_addr1) {
        this.home_addr1 = home_addr1;
    }

    /**
     * 주소1 취득
     *
     * @return home_addr1 주소1
     */
    public String getHome_addr1() {
        return StringUtils.defaultString(this.home_addr1);
    }

    /**
     * 주소2 설정
     *
     * @param home_addr2 주소2
     */
    public void setHome_addr2(final String home_addr2) {
        this.home_addr2 = home_addr2;
    }

    /**
     * 주소2 취득
     *
     * @return home_addr2 주소2
     */
    public String getHome_addr2() {
        return StringUtils.defaultString(this.home_addr2);
    }

    /**
     * 부서명 설정
     *
     * @param depart_name 부서명
     */
    public void setDepart_name(final String depart_name) {
        this.depart_name = depart_name;
    }

    /**
     * 부서명 취득
     *
     * @return depart_name 부서명
     */
    public String getDepart_name() {
        return StringUtils.defaultString(this.depart_name);
    }

    /**
     * 부서id 설정
     *
     * @param depart_id 부서id
     */
    public void setDepart_id(final String depart_id) {
        this.depart_id = depart_id;
    }

    /**
     * 부서id 취득
     *
     * @return depart_id 부서id
     */
    public String getDepart_id() {
        return StringUtils.defaultString(this.depart_id);
    }

    /**
     * 부서정보수정권한 설정
     *
     * @param depart_mod_auth 부서정보수정권한
     */
    public void setDepart_mod_auth(final String depart_mod_auth) {
        this.depart_mod_auth = depart_mod_auth;
    }

    /**
     * 부서정보수정권한 취득
     *
     * @return depart_mod_auth 부서정보수정권한
     */
    public String getDepart_mod_auth() {
        return StringUtils.defaultString(this.depart_mod_auth);
    }

    /**
     * 직책명 설정
     *
     * @param duty_name 직책명
     */
    public void setDuty_name(final String duty_name) {
        this.duty_name = duty_name;
    }

    /**
     * 직책명 취득
     *
     * @return duty_name 직책명
     */
    public String getDuty_name() {
        return StringUtils.defaultString(this.duty_name);
    }

    /**
     * 사용자아이디 설정
     *
     * @param emp_id 사용자아이디
     */
    public void setEmp_id(final String emp_id) {
        this.emp_id = emp_id;
    }

    /**
     * 사용자아이디 취득
     *
     * @return emp_id 사용자아이디
     */
    public String getEmp_id() {
        return StringUtils.defaultString(this.emp_id);
    }

    /**
     * 사용자명 설정
     *
     * @param empNm 사용자명
     */
    public void setEmpNm(final String empNm) {
        this.empNm = empNm;
    }

    /**
     * 사용자명 취득
     *
     * @return empNm 사용자명
     */
    public String getEmpNm() {
        return StringUtils.defaultString(this.empNm);
    }

    /**
     * 사용자그룹아이디 설정
     *
     * @param user_group_id 사용자그룹아이디
     */
    public void setUser_group_id(final String user_group_id) {
        this.user_group_id = user_group_id;
    }

    /**
     * 사용자그룹아이디 취득
     *
     * @return user_group_id 사용자그룹아이디
     */
    public String getUser_group_id() {
        return StringUtils.defaultString(this.user_group_id);
    }

    /**
     * 사용자그룹명 설정
     *
     * @param user_group_nm 사용자그룹명
     */
    public void setUser_group_nm(final String user_group_nm) {
        this.user_group_nm = user_group_nm;
    }

    /**
     * 사용자그룹명 취득
     *
     * @return user_group_nm 사용자그룹명
     */
    public String getUser_group_nm() {
        return StringUtils.defaultString(this.user_group_nm);
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
        return StringUtils.defaultString(this.email);
    }

    /**
     * 전화번호 설정
     *
     * @param smsPhone 전화번호
     */
    public void setSmsPhone(final String smsPhone) {
        this.smsPhone = smsPhone;
    }

    /**
     * 전화번호 취득
     *
     * @return smsPhone 전화번호
     */
    public String getSmsPhone() {
        return StringUtils.defaultString(this.smsPhone);
    }

    /**
     * 사용자상태 설정
     *
     * @param status 사용자상태
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 사용자상태 취득
     *
     * @return status 사용자상태
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
    }

    /**
     * 권한레벨코드 설정
     *
     * @param levelCod 권한레벨코드
     */
    public void setLevelCod(final String levelCod) {
        this.levelCod = levelCod;
    }

    /**
     * 권한레벨코드 취득
     *
     * @return levelCod 권한레벨코드
     */
    public String getLevelCod() {
        return StringUtils.defaultString(this.levelCod);
    }

    /**
     * 권한레벨코드명 설정
     *
     * @param levelCodNm 권한레벨코드명
     */
    public void setLevelCodNm(final String levelCodNm) {
        this.levelCodNm = levelCodNm;
    }

    /**
     * 권한레벨코드명 취득
     *
     * @return levelCodNm 권한레벨코드명
     */
    public String getLevelCodNm() {
        return StringUtils.defaultString(this.levelCodNm);
    }

    /**
     * 등록자 설정
     *
     * @param crt_user 등록자
     */
    public void setCrt_user(final String crt_user) {
        this.crt_user = crt_user;
    }

    /**
     * 등록자 취득
     *
     * @return crt_user 등록자
     */
    public String getCrt_user() {
        return StringUtils.defaultString(this.crt_user);
    }

    /**
     * 등록시간 설정
     *
     * @param crtDt 등록시간
     */
    public void setCrtDt(final String crtDt) {
        this.crtDt = crtDt;
    }

    /**
     * 등록시간 취득
     *
     * @return crtDt 등록시간
     */
    public String getCrtDt() {
        return StringUtils.defaultString(this.crtDt);
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

    /**
     * 회사id 설정
     *
     * @param company_id 회사id
     */
    public Integer getCompany_id() {
        return this.company_id;
    }

    /**
     * 회사id 취득
     *
     * @param company_id 회사id
     */
    public void setCompany_id(final Integer company_id) {
        this.company_id = company_id;
    }

    /**
     * 패스워드 변경여부 설정
     *
     * @param password_chg 패스워드 변경여부
     */
    public void setPassword_chg(final String password_chg) {
        this.password_chg = password_chg;
    }

    /**
     * 패스워드 변경여부 취득
     *
     * @return password_chg 패스워드 변경여부
     */
    public String getPassword_chg() {
        return StringUtils.defaultString(this.password_chg);
    }

    /**
     * 푸쉬 수신여부(Y/N) 설정
     *
     * @param push_yn 푸쉬 수신여부(Y/N)
     */
    public void setPush_yn(final String push_yn) {
        this.push_yn = push_yn;
    }

    /**
     * 푸쉬 수신여부(Y/N) 취득
     *
     * @return push_yn 푸쉬 수신여부(Y/N)
     */
    public String getPush_yn() {
        return StringUtils.defaultString(this.push_yn);
    }

}