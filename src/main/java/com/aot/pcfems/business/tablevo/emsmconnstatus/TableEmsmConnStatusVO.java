package com.aot.pcfems.business.tablevo.emsmconnstatus;

import org.joda.time.DateTime;

import java.io.Serializable;

public class TableEmsmConnStatusVO implements Serializable {
    private static final long serialVersionUID = -586570815610077841L;
    private String svr_id;
    private String node_name;
    private String ha_status;
    private String alm_status;
    private String host_name;
    private Integer sortseq;
    private String use_yn;
    private String crt_id;
    private DateTime crt_dt;
    private String upd_id;
    private DateTime upd_dt;

    public String getSvr_id() {
        return this.svr_id;
    }

    public void setSvr_id(final String svr_id) {
        this.svr_id = svr_id;
    }

    public String getNode_name() {
        return this.node_name;
    }

    public void setNode_name(final String node_name) {
        this.node_name = node_name;
    }

    public String getHa_status() {
        return this.ha_status;
    }

    public void setHa_status(final String ha_status) {
        this.ha_status = ha_status;
    }

    public String getAlm_status() {
        return this.alm_status;
    }

    public void setAlm_status(final String alm_status) {
        this.alm_status = alm_status;
    }

    public String getHost_name() {
        return this.host_name;
    }

    public void setHost_name(final String host_name) {
        this.host_name = host_name;
    }

    public Integer getSortseq() {
        return this.sortseq;
    }

    public void setSortseq(final Integer sortseq) {
        this.sortseq = sortseq;
    }

    public String getUse_yn() {
        return this.use_yn;
    }

    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
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
