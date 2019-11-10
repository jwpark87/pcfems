/*****************************************************************************
 * 프로그램명  : AuditLogInfo.java
 * 설     명  : Audit Log 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.31   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.audit;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Audit Log 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class AuditLogInfo implements Serializable {
    private transient static final long serialVersionUID = -932427567358897671L;
    /**
     * audit 로그순번
     */
    private String audit_log_no = "";
    /**
     * 변경제목
     */
    private String audit_subject = "";
    /**
     * 변경 Description
     */
    private String audit_desc = "";
    /**
     * 변경 전 데이타
     */
    private String audit_before_data = "";
    /**
     * 변경 후 데이타
     */
    private String audit_after_data = "";
    /**
     * 생성자
     */
    private String crt_id = "";
    /**
     * 생성일시
     */
    private String crt_dt = "";
    /**
     * 생성자명
     */
    private String crt_nm = "";

    /**
     * audit 로그순번 설정
     *
     * @param audit_log_no audit 로그순번
     */
    public void setAudit_log_no(final String audit_log_no) {
        this.audit_log_no = audit_log_no;
    }

    /**
     * audit 로그순번 취득
     *
     * @return audit_log_no audit 로그순번
     */
    public String getAudit_log_no() {
        return StringUtils.defaultString(this.audit_log_no);
    }

    /**
     * 변경제목 설정
     *
     * @param audit_subject 변경제목
     */
    public void setAudit_subject(final String audit_subject) {
        this.audit_subject = audit_subject;
    }

    /**
     * 변경제목 취득
     *
     * @return audit_subject 변경제목
     */
    public String getAudit_subject() {
        return StringUtils.defaultString(this.audit_subject);
    }

    /**
     * 변경 Description 설정
     *
     * @param audit_desc 변경 Description
     */
    public void setAudit_desc(final String audit_desc) {
        this.audit_desc = audit_desc;
    }

    /**
     * 변경 Description 취득
     *
     * @return audit_desc 변경 Description
     */
    public String getAudit_desc() {
        return StringUtils.defaultString(this.audit_desc);
    }

    /**
     * 변경 전 데이타 설정
     *
     * @param audit_before_data 변경 전 데이타
     */
    public void setAudit_before_data(final String audit_before_data) {
        this.audit_before_data = audit_before_data;
    }

    /**
     * 변경 전 데이타 취득
     *
     * @return audit_before_data 변경 전 데이타
     */
    public String getAudit_before_data() {
        return StringUtils.defaultString(this.audit_before_data);
    }

    /**
     * 변경 후 데이타 설정
     *
     * @param audit_after_data 변경 후 데이타
     */
    public void setAudit_after_data(final String audit_after_data) {
        this.audit_after_data = audit_after_data;
    }

    /**
     * 변경 후 데이타 취득
     *
     * @return audit_after_data 변경 후 데이타
     */
    public String getAudit_after_data() {
        return StringUtils.defaultString(this.audit_after_data);
    }

    /**
     * 생성자 설정
     *
     * @param crt_id 생성자
     */
    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    /**
     * 생성자 취득
     *
     * @return crt_id 생성자
     */
    public String getCrt_id() {
        return StringUtils.defaultString(this.crt_id);
    }

    /**
     * 생성일시 설정
     *
     * @param crt_dt 생성일시
     */
    public void setCrt_dt(final String crt_dt) {
        this.crt_dt = crt_dt;
    }

    /**
     * 생성일시 취득
     *
     * @return crt_dt 생성일시
     */
    public String getCrt_dt() {
        return StringUtils.defaultString(this.crt_dt);
    }

    /**
     * 생성자명 설정
     *
     * @param crt_nm 생성자명
     */
    public void setCrt_nm(final String crt_nm) {
        this.crt_nm = crt_nm;
    }

    /**
     * 생성자명 취득
     *
     * @return crt_nm 생성자명
     */
    public String getCrt_nm() {
        return StringUtils.defaultString(this.crt_nm);
    }

}