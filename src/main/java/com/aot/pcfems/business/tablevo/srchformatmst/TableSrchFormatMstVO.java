package com.aot.pcfems.business.tablevo.srchformatmst;

import org.joda.time.DateTime;

import java.io.Serializable;

public class TableSrchFormatMstVO implements Serializable {
    private transient static final long serialVersionUID = -3487455947584822894L;
    private String index_type;
    private String format_name;
    private String es_base_url;
    private String def_col_id;
    private String def_fromdt;
    private String def_todt;
    private Long total_row_cnt;
    private DateTime oldest_dt;
    private DateTime newest_dt;
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

    public String getFormat_name() {
        return this.format_name;
    }

    public void setFormat_name(final String format_name) {
        this.format_name = format_name;
    }

    public String getEs_base_url() {
        return this.es_base_url;
    }

    public void setEs_base_url(final String es_base_url) {
        this.es_base_url = es_base_url;
    }

    public String getDef_col_id() {
        return this.def_col_id;
    }

    public void setDef_col_id(final String def_col_id) {
        this.def_col_id = def_col_id;
    }

    public String getDef_fromdt() {
        return this.def_fromdt;
    }

    public void setDef_fromdt(final String def_fromdt) {
        this.def_fromdt = def_fromdt;
    }

    public String getDef_todt() {
        return this.def_todt;
    }

    public void setDef_todt(final String def_todt) {
        this.def_todt = def_todt;
    }

    public Long getTotal_row_cnt() {
        return this.total_row_cnt;
    }

    public void setTotal_row_cnt(final Long total_row_cnt) {
        this.total_row_cnt = total_row_cnt;
    }

    public DateTime getOldest_dt() {
        return this.oldest_dt;
    }

    public void setOldest_dt(final DateTime oldest_dt) {
        this.oldest_dt = oldest_dt;
    }

    public DateTime getNewest_dt() {
        return this.newest_dt;
    }

    public void setNewest_dt(final DateTime newest_dt) {
        this.newest_dt = newest_dt;
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

}
