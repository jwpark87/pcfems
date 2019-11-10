/*****************************************************************************
 * 프로그램명  : M3uaInfo.java
 * 설     명  : M2PA/M3UA 현황 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.04   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.m3ua;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * M3ua 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class M3uaInfo implements Serializable {
    private transient static final long serialVersionUID = -657933466642802475L;
    /**
     * 순서
     */
    private String sortseq = "";
    /**
     * 장비명
     */
    private String locality = "";
    /**
     * 장비명명
     */
    private String locality_nm = "";
    /**
     * 연동방식
     */
    private String if_type = "";
    /**
     * 국가명
     */
    private String country_nm = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * APC
     */
    private String apc = "";
    /**
     * ASP ID(M3UA)
     */
    private String asp_id = "";
    /**
     * 국내/국제
     */
    private String loc_boundary = "";
    /**
     * NA(M2PA)
     */
    private String na = "";
    /**
     * SLC(M2PA)
     */
    private Integer slc = null;
    /**
     * CLLI
     */
    private String clli = "";
    /**
     * Routing Context(M3UA)
     */
    private String routing_context = "";
    /**
     * Source Primary IP
     */
    private String src_primary_ip = "";
    /**
     * Source Secondary IP
     */
    private String src_secondary_ip = "";
    /**
     * Source Port
     */
    private Integer src_port = null;
    /**
     * Destination Primary IP
     */
    private String dst_primary_ip = "";
    /**
     * Destination Secondary IP
     */
    private String dst_secondary_ip = "";
    /**
     * Destination Port Number
     */
    private Integer dst_port = null;
    /**
     * ASP Type(M3UA)
     */
    private String asp_type = "";
    /**
     * SI(M3UA)
     */
    private String si = "";
    /**
     * Routing CPU
     */
    private String routing_cpu = "";
    /**
     * 연결상태(그룹코드:LINK_STATUS)
     */
    private String status = "";
    /**
     * 상태명
     */
    private String status_nm = "";
    /**
     * 출력여부
     */
    private String dsbd_yn = "";
    /**
     * 출력여부명
     */
    private String dsbd_yn_nm = "";

    /**
     * 출력여부 설정
     *
     * @param dsbd_yn 출력여부
     */
    public void setDsbd_yn(final String dsbd_yn) {
        this.dsbd_yn = dsbd_yn;
    }

    /**
     * 출력여부 취득
     *
     * @return dsbd_yn 출력여부
     */
    public String getDsbd_yn() {
        return StringUtils.defaultString(this.dsbd_yn);
    }

    /**
     * 출력여부명 설정
     *
     * @param dsbd_yn_nm 출력여부명
     */
    public void setDsbd_yn_nm(final String dsbd_yn_nm) {
        this.dsbd_yn_nm = dsbd_yn_nm;
    }

    /**
     * 출력여부명 취득
     *
     * @return dsbd_yn_nm 출력여부명
     */
    public String getDsbd_yn_nm() {
        return StringUtils.defaultString(this.dsbd_yn_nm);
    }

    /**
     * 상태명 설정
     *
     * @param status_nm 상태명
     */
    public void setStatus_nm(final String status_nm) {
        this.status_nm = status_nm;
    }

    /**
     * 상태명 취득
     *
     * @return status_nm 상태명
     */
    public String getStatus_nm() {
        return StringUtils.defaultString(this.status_nm);
    }

    /**
     * 순서 설정
     *
     * @param sortseq 순서
     */
    public void setSortseq(final String sortseq) {
        this.sortseq = sortseq;
    }

    /**
     * 순서 취득
     *
     * @return sortseq 순서
     */
    public String getSortseq() {
        return StringUtils.defaultString(this.sortseq);
    }

    /**
     * 장비명 설정
     *
     * @param locality 장비명
     */
    public void setLocality(final String locality) {
        this.locality = locality;
    }

    /**
     * 장비명 취득
     *
     * @return locality 장비명
     */
    public String getLocality() {
        return StringUtils.defaultString(this.locality);
    }

    /**
     * 장비명명 설정
     *
     * @param locality_nm 장비명명
     */
    public void setLocality_nm(final String locality_nm) {
        this.locality_nm = locality_nm;
    }

    /**
     * 장비명명 취득
     *
     * @return locality_nm 장비명명
     */
    public String getLocality_nm() {
        return StringUtils.defaultString(this.locality_nm);
    }

    /**
     * 연동방식 설정
     *
     * @param if_type 연동방식
     */
    public void setIf_type(final String if_type) {
        this.if_type = if_type;
    }

    /**
     * 연동방식 취득
     *
     * @return if_type 연동방식
     */
    public String getIf_type() {
        return StringUtils.defaultString(this.if_type);
    }

    /**
     * 국가명 설정
     *
     * @param country_nm 국가명
     */
    public void setCountry_nm(final String country_nm) {
        this.country_nm = country_nm;
    }

    /**
     * 국가명 취득
     *
     * @return country_nm 국가명
     */
    public String getCountry_nm() {
        return StringUtils.defaultString(this.country_nm);
    }

    /**
     * 사업자명 설정
     *
     * @param carrier_nm 사업자명
     */
    public void setCarrier_nm(final String carrier_nm) {
        this.carrier_nm = carrier_nm;
    }

    /**
     * 사업자명 취득
     *
     * @return carrier_nm 사업자명
     */
    public String getCarrier_nm() {
        return StringUtils.defaultString(this.carrier_nm);
    }

    /**
     * APC 설정
     *
     * @param apc APC
     */
    public void setApc(final String apc) {
        this.apc = apc;
    }

    /**
     * APC 취득
     *
     * @return apc APC
     */
    public String getApc() {
        return StringUtils.defaultString(this.apc);
    }

    /**
     * ASP ID(M3UA) 설정
     *
     * @param asp_id ASP ID(M3UA)
     */
    public void setAsp_id(final String asp_id) {
        this.asp_id = asp_id;
    }

    /**
     * ASP ID(M3UA) 취득
     *
     * @return asp_id ASP ID(M3UA)
     */
    public String getAsp_id() {
        return StringUtils.defaultString(this.asp_id);
    }

    /**
     * 국내/국제 설정
     *
     * @param loc_boundary 국내/국제
     */
    public void setLoc_boundary(final String loc_boundary) {
        this.loc_boundary = loc_boundary;
    }

    /**
     * 국내/국제 취득
     *
     * @return loc_boundary 국내/국제
     */
    public String getLoc_boundary() {
        return StringUtils.defaultString(this.loc_boundary);
    }

    /**
     * NA(M2PA) 설정
     *
     * @param na NA(M2PA)
     */
    public void setNa(final String na) {
        this.na = na;
    }

    /**
     * NA(M2PA) 취득
     *
     * @return na NA(M2PA)
     */
    public String getNa() {
        return StringUtils.defaultString(this.na);
    }

    /**
     * SLC(M2PA) 설정
     *
     * @param slc SLC(M2PA)
     */
    public void setSlc(final Integer slc) {
        this.slc = slc;
    }

    /**
     * SLC(M2PA) 취득
     *
     * @return slc SLC(M2PA)
     */
    public Integer getSlc() {
        return this.slc;
    }

    /**
     * CLLI 설정
     *
     * @param clli CLLI
     */
    public void setClli(final String clli) {
        this.clli = clli;
    }

    /**
     * CLLI 취득
     *
     * @return clli CLLI
     */
    public String getClli() {
        return StringUtils.defaultString(this.clli);
    }

    /**
     * Routing Context(M3UA) 설정
     *
     * @param routing_context Routing Context(M3UA)
     */
    public void setRouting_context(final String routing_context) {
        this.routing_context = routing_context;
    }

    /**
     * Routing Context(M3UA) 취득
     *
     * @return routing_context Routing Context(M3UA)
     */
    public String getRouting_context() {
        return StringUtils.defaultString(this.routing_context);
    }

    /**
     * Source Primary IP 설정
     *
     * @param src_primary_ip Source Primary IP
     */
    public void setSrc_primary_ip(final String src_primary_ip) {
        this.src_primary_ip = src_primary_ip;
    }

    /**
     * Source Primary IP 취득
     *
     * @return src_primary_ip Source Primary IP
     */
    public String getSrc_primary_ip() {
        return StringUtils.defaultString(this.src_primary_ip);
    }

    /**
     * Source Secondary IP 설정
     *
     * @param src_secondary_ip Source Secondary IP
     */
    public void setSrc_secondary_ip(final String src_secondary_ip) {
        this.src_secondary_ip = src_secondary_ip;
    }

    /**
     * Source Secondary IP 취득
     *
     * @return src_secondary_ip Source Secondary IP
     */
    public String getSrc_secondary_ip() {
        return StringUtils.defaultString(this.src_secondary_ip);
    }

    /**
     * Source Port 설정
     *
     * @param src_port Source Port
     */
    public void setSrc_port(final Integer src_port) {
        this.src_port = src_port;
    }

    /**
     * Source Port 취득
     *
     * @return src_port Source Port
     */
    public Integer getSrc_port() {
        return this.src_port;
    }

    /**
     * Destination Primary IP 설정
     *
     * @param dst_primary_ip Destination Primary IP
     */
    public void setDst_primary_ip(final String dst_primary_ip) {
        this.dst_primary_ip = dst_primary_ip;
    }

    /**
     * Destination Primary IP 취득
     *
     * @return dst_primary_ip Destination Primary IP
     */
    public String getDst_primary_ip() {
        return StringUtils.defaultString(this.dst_primary_ip);
    }

    /**
     * Destination Secondary IP 설정
     *
     * @param dst_secondary_ip Destination Secondary IP
     */
    public void setDst_secondary_ip(final String dst_secondary_ip) {
        this.dst_secondary_ip = dst_secondary_ip;
    }

    /**
     * Destination Secondary IP 취득
     *
     * @return dst_secondary_ip Destination Secondary IP
     */
    public String getDst_secondary_ip() {
        return StringUtils.defaultString(this.dst_secondary_ip);
    }

    /**
     * Destination Port Number 설정
     *
     * @param dst_port Destination Port Number
     */
    public void setDst_port(final Integer dst_port) {
        this.dst_port = dst_port;
    }

    /**
     * Destination Port Number 취득
     *
     * @return dst_port Destination Port Number
     */
    public Integer getDst_port() {
        return this.dst_port;
    }

    /**
     * ASP Type(M3UA) 설정
     *
     * @param asp_type ASP Type(M3UA)
     */
    public void setAsp_type(final String asp_type) {
        this.asp_type = asp_type;
    }

    /**
     * ASP Type(M3UA) 취득
     *
     * @return asp_type ASP Type(M3UA)
     */
    public String getAsp_type() {
        return StringUtils.defaultString(this.asp_type);
    }

    /**
     * SI(M3UA) 설정
     *
     * @param si SI(M3UA)
     */
    public void setSi(final String si) {
        this.si = si;
    }

    /**
     * SI(M3UA) 취득
     *
     * @return si SI(M3UA)
     */
    public String getSi() {
        return StringUtils.defaultString(this.si);
    }

    /**
     * Routing CPU 설정
     *
     * @param routing_cpu Routing CPU
     */
    public void setRouting_cpu(final String routing_cpu) {
        this.routing_cpu = routing_cpu;
    }

    /**
     * Routing CPU 취득
     *
     * @return routing_cpu Routing CPU
     */
    public String getRouting_cpu() {
        return StringUtils.defaultString(this.routing_cpu);
    }

    /**
     * 연결상태(그룹코드:LINK_STATUS) 설정
     *
     * @param status 연결상태(그룹코드:LINK_STATUS)
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 연결상태(그룹코드:LINK_STATUS) 취득
     *
     * @return status 연결상태(그룹코드:LINK_STATUS)
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
    }

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

}