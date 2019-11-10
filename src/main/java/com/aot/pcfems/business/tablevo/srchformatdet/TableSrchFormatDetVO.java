package com.aot.pcfems.business.tablevo.srchformatdet;

import org.joda.time.DateTime;

import java.io.Serializable;

public class TableSrchFormatDetVO implements Serializable {
    private transient static final long serialVersionUID = -3487455947584822894L;
    private String index_type;
    private String col_cd;
    private String col_nm;
    private String text_align;
    private String grcode;
    private Short dsp_width;
    private String search_yn;
    private Short sortseq;
    private String use_yn;
    private String threshold_yn;
    private String op_code;
    private String op_value;
    private String alm_code;
    private String alm_msg;
    private String remark1;
    private String crt_id;
    private DateTime crt_dt;
    private String upd_id;
    private DateTime upd_dt;

    public String getIndex_type() {
        return this.index_type;
    }

    public void setIndex_type(final String index_type) {
        this.index_type = index_type;
    }

    public String getCol_cd() {
        return this.col_cd;
    }

    public void setCol_cd(final String col_cd) {
        this.col_cd = col_cd;
    }

    public String getCol_nm() {
        return this.col_nm;
    }

    public void setCol_nm(final String col_nm) {
        this.col_nm = col_nm;
    }

    public String getText_align() {
        return this.text_align;
    }

    public void setText_align(final String text_align) {
        this.text_align = text_align;
    }

    public String getGrcode() {
        return this.grcode;
    }

    public void setGrcode(final String grcode) {
        this.grcode = grcode;
    }

    public Short getDsp_width() {
        return this.dsp_width;
    }

    public void setDsp_width(final Short dsp_width) {
        this.dsp_width = dsp_width;
    }

    public String getSearch_yn() {
        return this.search_yn;
    }

    public void setSearch_yn(final String search_yn) {
        this.search_yn = search_yn;
    }

    public Short getSortseq() {
        return this.sortseq;
    }

    public void setSortseq(final Short sortseq) {
        this.sortseq = sortseq;
    }

    public String getUse_yn() {
        return this.use_yn;
    }

    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
    }

    public String getThreshold_yn() {
        return this.threshold_yn;
    }

    public void setThreshold_yn(final String threshold_yn) {
        this.threshold_yn = threshold_yn;
    }

    public String getOp_code() {
        return this.op_code;
    }

    public void setOp_code(final String op_code) {
        this.op_code = op_code;
    }

    public String getOp_value() {
        return this.op_value;
    }

    public void setOp_value(final String op_value) {
        this.op_value = op_value;
    }

    public String getAlm_code() {
        return this.alm_code;
    }

    public void setAlm_code(final String alm_code) {
        this.alm_code = alm_code;
    }

    public String getAlm_msg() {
        return this.alm_msg;
    }

    public void setAlm_msg(final String alm_msg) {
        this.alm_msg = alm_msg;
    }

    public String getRemark1() {
        return this.remark1;
    }

    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
    }

    public String getCrt_id() {
        return this.crt_id;
    }

    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    public DateTime getCrt_dt() {
        return this.crt_dt;
    }

    public void setCrt_dt(final DateTime crt_dt) {
        this.crt_dt = crt_dt;
    }

    public String getUpd_id() {
        return this.upd_id;
    }

    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    public DateTime getUpd_dt() {
        return this.upd_dt;
    }

    public void setUpd_dt(final DateTime upd_dt) {
        this.upd_dt = upd_dt;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
