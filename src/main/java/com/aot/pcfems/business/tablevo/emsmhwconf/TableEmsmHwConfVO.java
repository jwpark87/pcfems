package com.aot.pcfems.business.tablevo.emsmhwconf;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

public class TableEmsmHwConfVO implements Serializable {
    private transient static final long serialVersionUID = -3230031389727949497L;
    private String hw_name;
    private String use_yn;
    private Integer sortseq;
    private String crt_id;
    private LocalDateTime crt_dt;
    private String upd_id;
    private LocalDateTime upd_dt;

    public String getHw_name() {
        return this.hw_name;
    }

    public void setHw_name(final String hw_name) {
        this.hw_name = hw_name;
    }

    public String getUse_yn() {
        return this.use_yn;
    }

    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
    }

    public Integer getSortseq() {
        return this.sortseq;
    }

    public void setSortseq(final Integer sortseq) {
        this.sortseq = sortseq;
    }

    public String getCrt_id() {
        return this.crt_id;
    }

    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    public LocalDateTime getCrt_dt() {
        return this.crt_dt;
    }

    public void setCrt_dt(final LocalDateTime crt_dt) {
        this.crt_dt = crt_dt;
    }

    public String getUpd_id() {
        return this.upd_id;
    }

    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    public LocalDateTime getUpd_dt() {
        return this.upd_dt;
    }

    public void setUpd_dt(final LocalDateTime upd_dt) {
        this.upd_dt = upd_dt;
    }

}
