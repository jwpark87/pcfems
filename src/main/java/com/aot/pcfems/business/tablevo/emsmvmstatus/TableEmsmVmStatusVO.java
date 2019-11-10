package com.aot.pcfems.business.tablevo.emsmvmstatus;

import org.joda.time.DateTime;

import java.io.Serializable;

public class TableEmsmVmStatusVO implements Serializable {
    private transient static final long serialVersionUID = 5759732400074496287L;

    private String svr_id;
    private String node_name;
    private String node_group;
    private String ha_status;
    private String alm_status;
    private String host_name;
    private Integer sortseq;
    private String use_yn;
    private String hw_name;
    private String vnfc_instance_id;
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

    public String getNode_group() {
        return this.node_group;
    }

    public void setNode_group(final String node_group) {
        this.node_group = node_group;
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

    public String getHw_name() {
        return this.hw_name;
    }

    public void setHw_name(final String hw_name) {
        this.hw_name = hw_name;
    }

    public String getVnfc_instance_id() {
        return this.vnfc_instance_id;
    }

    public void setVnfc_instance_id(final String vnfc_instance_id) {
        this.vnfc_instance_id = vnfc_instance_id;
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
