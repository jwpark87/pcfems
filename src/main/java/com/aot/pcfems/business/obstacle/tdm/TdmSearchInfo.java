/*****************************************************************************
 * 프로그램명  : TdmSearchInfo.java
 * 설     명  : Tdm 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.05.03   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.tdm;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Tdm 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class TdmSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -8403773023475242004L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
    /**
     * 시작인덱스
     */
    private int startIndex = 0;
    /**
     * 종료인덱스
     */
    private int endIndex = 0;

    /**
     * 생성자
     */
    public TdmSearchInfo() {
    }

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public String getUser_lang() {
        return this.user_lang;
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 우편번호
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 엑셀여부 설정
     *
     * @param search_excel_type 엑셀여부
     */
    public void setSearch_excel_type(final String search_excel_type) {
        this.search_excel_type = search_excel_type;
    }

    /**
     * 엑셀여부 취득
     *
     * @return search_excel_type 엑셀여부
     */
    public String getSearch_excel_type() {
        return StringUtils.defaultString(this.search_excel_type);
    }

    /**
     * 시작인덱스설정
     *
     * @param str 시작인덱스
     */
    public void setStartIndex(final int str) {
        this.startIndex = str;
    }

    /**
     * 시작인덱스취득
     *
     * @return int 시작인덱스
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /**
     * 종료인덱스설정
     *
     * @param str 종료인덱스
     */
    public void setEndIndex(final int str) {
        this.endIndex = str;
    }

    /**
     * 종료인덱스취득
     *
     * @return int 종료인덱스
     */
    public int getEndIndex() {
        return this.endIndex;
    }

    /**
     * 검색순번
     */
    private String srch_sortseq = "";
    /**
     * 장비명
     */
    private String locality = "";
    /**
     * GNOC타이명
     */
    private String gnoc_tie_nm = "";
    /**
     * TS
     */
    private String ts = "";
    /**
     * GNOC_Tie (DSX/DCSA No)
     */
    private String gnoc_tie_dsx_dcsa_no = "";
    /**
     * STP TIE(부산용)
     */
    private String stp_tie = "";
    /**
     * GNOC NDCS/NDCS TIE
     */
    private String gnoc_tie_ndcs = "";
    /**
     * SHELF
     */
    private Integer shelf = null;
    /**
     * SLOT
     */
    private Integer slot = null;
    /**
     * PORT(e1)
     */
    private Integer e1_port = null;
    /**
     * Time Slot
     */
    private String time_slot = "";
    /**
     * CH Number
     */
    private Integer ch_number = null;
    /**
     * MAPPING(부산용)
     */
    private String mapping = "";
    /**
     * CRC(부산용)
     */
    private String crc = "";
    /**
     * 사용상태
     */
    private String use_status = "";
    /**
     * 국내/국제
     */
    private String loc_bound = "";
    /**
     * NA
     */
    private String na = "";
    /**
     * APC
     */
    private String apc = "";
    /**
     * SLC
     */
    private Integer slc = null;
    /**
     * Error Connection
     */
    private String error_conn = "";
    /**
     * 국가명
     */
    private String country_nm = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * CLLI
     */
    private String clli = "";
    /**
     * Bearer
     */
    private String bearer = "";
    /**
     * Bearer Time Slot(서울용)
     */
    private String bearer_time_slot = "";
    /**
     * 케이블
     */
    private String cable = "";
    /**
     * 설명
     */
    private String remark1 = "";
    /**
     * 연결상태(그룹코드:LINK_STATUS)
     */
    private String status = "";
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
     * 검색순번 설정
     *
     * @param srch_sortseq 검색순번
     */
    public void setSrch_sortseq(final String srch_sortseq) {
        this.srch_sortseq = srch_sortseq;
    }

    /**
     * 검색순번 취득
     *
     * @return srch_sortseq 검색순번
     */
    public String getSrch_sortseq() {
        return StringUtils.defaultString(this.srch_sortseq);
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
     * GNOC타이명 설정
     *
     * @param gnoc_tie_nm GNOC타이명
     */
    public void setGnoc_tie_nm(final String gnoc_tie_nm) {
        this.gnoc_tie_nm = gnoc_tie_nm;
    }

    /**
     * GNOC타이명 취득
     *
     * @return gnoc_tie_nm GNOC타이명
     */
    public String getGnoc_tie_nm() {
        return StringUtils.defaultString(this.gnoc_tie_nm);
    }

    /**
     * TS 설정
     *
     * @param ts TS
     */
    public void setTs(final String ts) {
        this.ts = ts;
    }

    /**
     * TS 취득
     *
     * @return ts TS
     */
    public String getTs() {
        return StringUtils.defaultString(this.ts);
    }

    /**
     * GNOC_Tie (DSX/DCSA No) 설정
     *
     * @param gnoc_tie_dsx_dcsa_no GNOC_Tie (DSX/DCSA No)
     */
    public void setGnoc_tie_dsx_dcsa_no(final String gnoc_tie_dsx_dcsa_no) {
        this.gnoc_tie_dsx_dcsa_no = gnoc_tie_dsx_dcsa_no;
    }

    /**
     * GNOC_Tie (DSX/DCSA No) 취득
     *
     * @return gnoc_tie_dsx_dcsa_no GNOC_Tie (DSX/DCSA No)
     */
    public String getGnoc_tie_dsx_dcsa_no() {
        return StringUtils.defaultString(this.gnoc_tie_dsx_dcsa_no);
    }

    /**
     * STP TIE(부산용) 설정
     *
     * @param stp_tie STP TIE(부산용)
     */
    public void setStp_tie(final String stp_tie) {
        this.stp_tie = stp_tie;
    }

    /**
     * STP TIE(부산용) 취득
     *
     * @return stp_tie STP TIE(부산용)
     */
    public String getStp_tie() {
        return StringUtils.defaultString(this.stp_tie);
    }

    /**
     * GNOC NDCS/NDCS TIE 설정
     *
     * @param gnoc_tie_ndcs GNOC NDCS/NDCS TIE
     */
    public void setGnoc_tie_ndcs(final String gnoc_tie_ndcs) {
        this.gnoc_tie_ndcs = gnoc_tie_ndcs;
    }

    /**
     * GNOC NDCS/NDCS TIE 취득
     *
     * @return gnoc_tie_ndcs GNOC NDCS/NDCS TIE
     */
    public String getGnoc_tie_ndcs() {
        return StringUtils.defaultString(this.gnoc_tie_ndcs);
    }

    /**
     * SHELF 설정
     *
     * @param shelf SHELF
     */
    public void setShelf(final Integer shelf) {
        this.shelf = shelf;
    }

    /**
     * SHELF 취득
     *
     * @return shelf SHELF
     */
    public Integer getShelf() {
        return this.shelf;
    }

    /**
     * SLOT 설정
     *
     * @param slot SLOT
     */
    public void setSlot(final Integer slot) {
        this.slot = slot;
    }

    /**
     * SLOT 취득
     *
     * @return slot SLOT
     */
    public Integer getSlot() {
        return this.slot;
    }

    /**
     * PORT(e1) 설정
     *
     * @param e1_port PORT(e1)
     */
    public void setE1_port(final Integer e1_port) {
        this.e1_port = e1_port;
    }

    /**
     * PORT(e1) 취득
     *
     * @return e1_port PORT(e1)
     */
    public Integer getE1_port() {
        return this.e1_port;
    }

    /**
     * Time Slot 설정
     *
     * @param time_slot Time Slot
     */
    public void setTime_slot(final String time_slot) {
        this.time_slot = time_slot;
    }

    /**
     * Time Slot 취득
     *
     * @return time_slot Time Slot
     */
    public String getTime_slot() {
        return StringUtils.defaultString(this.time_slot);
    }

    /**
     * CH Number 설정
     *
     * @param ch_number CH Number
     */
    public void setCh_number(final Integer ch_number) {
        this.ch_number = ch_number;
    }

    /**
     * CH Number 취득
     *
     * @return ch_number CH Number
     */
    public Integer getCh_number() {
        return this.ch_number;
    }

    /**
     * MAPPING(부산용) 설정
     *
     * @param mapping MAPPING(부산용)
     */
    public void setMapping(final String mapping) {
        this.mapping = mapping;
    }

    /**
     * MAPPING(부산용) 취득
     *
     * @return mapping MAPPING(부산용)
     */
    public String getMapping() {
        return StringUtils.defaultString(this.mapping);
    }

    /**
     * CRC(부산용) 설정
     *
     * @param crc CRC(부산용)
     */
    public void setCrc(final String crc) {
        this.crc = crc;
    }

    /**
     * CRC(부산용) 취득
     *
     * @return crc CRC(부산용)
     */
    public String getCrc() {
        return StringUtils.defaultString(this.crc);
    }

    /**
     * 사용상태 설정
     *
     * @param use_status 사용상태
     */
    public void setUse_status(final String use_status) {
        this.use_status = use_status;
    }

    /**
     * 사용상태 취득
     *
     * @return use_status 사용상태
     */
    public String getUse_status() {
        return StringUtils.defaultString(this.use_status);
    }

    /**
     * 국내/국제 설정
     *
     * @param loc_bound 국내/국제
     */
    public void setLoc_bound(final String loc_bound) {
        this.loc_bound = loc_bound;
    }

    /**
     * 국내/국제 취득
     *
     * @return loc_bound 국내/국제
     */
    public String getLoc_bound() {
        return StringUtils.defaultString(this.loc_bound);
    }

    /**
     * NA 설정
     *
     * @param na NA
     */
    public void setNa(final String na) {
        this.na = na;
    }

    /**
     * NA 취득
     *
     * @return na NA
     */
    public String getNa() {
        return StringUtils.defaultString(this.na);
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
     * SLC 설정
     *
     * @param slc SLC
     */
    public void setSlc(final Integer slc) {
        this.slc = slc;
    }

    /**
     * SLC 취득
     *
     * @return slc SLC
     */
    public Integer getSlc() {
        return this.slc;
    }

    /**
     * Error Connection 설정
     *
     * @param error_conn Error Connection
     */
    public void setError_conn(final String error_conn) {
        this.error_conn = error_conn;
    }

    /**
     * Error Connection 취득
     *
     * @return error_conn Error Connection
     */
    public String getError_conn() {
        return StringUtils.defaultString(this.error_conn);
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
     * Bearer 설정
     *
     * @param bearer Bearer
     */
    public void setBearer(final String bearer) {
        this.bearer = bearer;
    }

    /**
     * Bearer 취득
     *
     * @return bearer Bearer
     */
    public String getBearer() {
        return StringUtils.defaultString(this.bearer);
    }

    /**
     * Bearer Time Slot(서울용) 설정
     *
     * @param bearer_time_slot Bearer Time Slot(서울용)
     */
    public void setBearer_time_slot(final String bearer_time_slot) {
        this.bearer_time_slot = bearer_time_slot;
    }

    /**
     * Bearer Time Slot(서울용) 취득
     *
     * @return bearer_time_slot Bearer Time Slot(서울용)
     */
    public String getBearer_time_slot() {
        return StringUtils.defaultString(this.bearer_time_slot);
    }

    /**
     * 케이블 설정
     *
     * @param cable 케이블
     */
    public void setCable(final String cable) {
        this.cable = cable;
    }

    /**
     * 케이블 취득
     *
     * @return cable 케이블
     */
    public String getCable() {
        return StringUtils.defaultString(this.cable);
    }

    /**
     * 설명 설정
     *
     * @param remark1 설명
     */
    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
    }

    /**
     * 설명 취득
     *
     * @return remark1 설명
     */
    public String getRemark1() {
        return StringUtils.defaultString(this.remark1);
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

}