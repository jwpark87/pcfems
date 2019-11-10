/*****************************************************************************
 * 프로그램명  : CdrInfo.java
 * 설     명  : CDR 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.cdrsearch;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * CDR 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class CdrSearchVO implements Serializable {
    private transient static final long serialVersionUID = -1498405193725365364L;
    /**
     * 테이블
     */
    private String index_type = "";
    /**
     * 컬럼코드
     */
    private String col_cd = "";
    /**
     * 컬럼이름
     */
    private String col_nm = "";
    /**
     * 텍스트정렬
     */
    private String text_align = "";
    /**
     * 출력시 그룹코드
     */
    private String grcode = "";
    /**
     * 컬럼너비
     */
    private String dsp_width = "";
    /**
     * 검색조건여부
     */
    private String search_yn = "";
    /**
     * 정렬순번
     */
    private String sortseq = "";

    /**
     * 포맷명
     */
    private String format_name = "";
    /**
     * ElasticSearch URL
     */
    private String es_base_url = "";
    /**
     *
     */
    private String out_col_ids = "";
    /**
     *
     */
    private String out_col_names = "";
    private String out_col_types = "";
    /**
     * 검색기준일자 컬럼아이디
     */
    private String def_col_id = "";
    /**
     * 기준일자 시작
     */
    private String def_fromdt = "";
    /**
     * 기준일자 종료
     */
    private String def_todt = "";
    /**
     * 전체로우갯수
     */
    private String total_row_cnt = "";
    /**
     * 구
     */
    private String oldest_dt = "";
    /**
     * 신
     */
    private String newest_dt = "";

    /**
     * 전체로우갯수 설정
     *
     * @param total_row_cnt 전체로우갯수
     */
    public void setTotal_row_cnt(final String total_row_cnt) {
        this.total_row_cnt = total_row_cnt;
    }

    /**
     * 전체로우갯수 취득
     *
     * @return total_row_cnt 전체로우갯수
     */
    public String getTotal_row_cnt() {
        return StringUtils.defaultString(this.total_row_cnt);
    }

    /**
     * 구 설정
     *
     * @param oldest_dt 구
     */
    public void setOldest_dt(final String oldest_dt) {
        this.oldest_dt = oldest_dt;
    }

    /**
     * 구 취득
     *
     * @return oldest_dt 구
     */
    public String getOldest_dt() {
        return StringUtils.defaultString(this.oldest_dt);
    }

    /**
     * 신 설정
     *
     * @param newest_dt 신
     */
    public void setNewest_dt(final String newest_dt) {
        this.newest_dt = newest_dt;
    }

    /**
     * 신 취득
     *
     * @return newest_dt 신
     */
    public String getNewest_dt() {
        return StringUtils.defaultString(this.newest_dt);
    }

    /**
     * 포맷명 설정
     *
     * @param format_name 포맷명
     */
    public void setFormat_name(final String format_name) {
        this.format_name = format_name;
    }

    /**
     * 포맷명 취득
     *
     * @return format_name 포맷명
     */
    public String getFormat_name() {
        return StringUtils.defaultString(this.format_name);
    }

    /**
     * ElasticSearch URL 설정
     *
     * @param es_base_url ElasticSearch URL
     */
    public void setEs_base_url(final String es_base_url) {
        this.es_base_url = es_base_url;
    }

    /**
     * ElasticSearch URL 취득
     *
     * @return es_base_url ElasticSearch URL
     */
    public String getEs_base_url() {
        return StringUtils.defaultString(this.es_base_url);
    }

    /**
     * 설정
     *
     * @param out_col_ids
     */
    public void setOut_col_ids(final String out_col_ids) {
        this.out_col_ids = out_col_ids;
    }

    /**
     * 취득
     *
     * @return out_col_ids
     */
    public String getOut_col_ids() {
        return StringUtils.defaultString(this.out_col_ids);
    }

    /**
     * 설정
     *
     * @param out_col_names
     */
    public void setOut_col_names(final String out_col_names) {
        this.out_col_names = out_col_names;
    }

    /**
     * 취득
     *
     * @return out_col_names
     */
    public String getOut_col_names() {
        return StringUtils.defaultString(this.out_col_names);
    }

    public String getOut_col_types() {
        return this.out_col_types;
    }

    public void setOut_col_types(final String out_col_types) {
        this.out_col_types = out_col_types;
    }

    /**
     * 검색기준일자 컬럼아이디 설정
     *
     * @param def_col_id 검색기준일자 컬럼아이디
     */
    public void setDef_col_id(final String def_col_id) {
        this.def_col_id = def_col_id;
    }

    /**
     * 검색기준일자 컬럼아이디 취득
     *
     * @return def_col_id 검색기준일자 컬럼아이디
     */
    public String getDef_col_id() {
        return StringUtils.defaultString(this.def_col_id);
    }

    /**
     * 기준일자 시작 설정
     *
     * @param def_fromdt 기준일자 시작
     */
    public void setDef_fromdt(final String def_fromdt) {
        this.def_fromdt = def_fromdt;
    }

    /**
     * 기준일자 시작 취득
     *
     * @return def_fromdt 기준일자 시작
     */
    public String getDef_fromdt() {
        return StringUtils.defaultString(this.def_fromdt);
    }

    /**
     * 기준일자 종료 설정
     *
     * @param def_todt 기준일자 종료
     */
    public void setDef_todt(final String def_todt) {
        this.def_todt = def_todt;
    }

    /**
     * 기준일자 종료 취득
     *
     * @return def_todt 기준일자 종료
     */
    public String getDef_todt() {
        return StringUtils.defaultString(this.def_todt);
    }

    /**
     * 테이블 설정
     *
     * @param index_type 테이블
     */
    public void setIndex_type(final String index_type) {
        this.index_type = index_type;
    }

    /**
     * 테이블 취득
     *
     * @return index_type 테이블
     */
    public String getIndex_type() {
        return StringUtils.defaultString(this.index_type);
    }

    /**
     * 컬럼코드 설정
     *
     * @param col_cd 컬럼코드
     */
    public void setCol_cd(final String col_cd) {
        this.col_cd = col_cd;
    }

    /**
     * 컬럼코드 취득
     *
     * @return col_cd 컬럼코드
     */
    public String getCol_cd() {
        return StringUtils.defaultString(this.col_cd);
    }

    /**
     * 컬럼이름 설정
     *
     * @param col_nm 컬럼이름
     */
    public void setCol_nm(final String col_nm) {
        this.col_nm = col_nm;
    }

    /**
     * 컬럼이름 취득
     *
     * @return col_nm 컬럼이름
     */
    public String getCol_nm() {
        return StringUtils.defaultString(this.col_nm);
    }

    /**
     * 텍스트정렬 설정
     *
     * @param text_align 텍스트정렬
     */
    public void setText_align(final String text_align) {
        this.text_align = text_align;
    }

    /**
     * 텍스트정렬 취득
     *
     * @return text_align 텍스트정렬
     */
    public String getText_align() {
        return StringUtils.defaultString(this.text_align);
    }

    /**
     * 출력시 그룹코드 설정
     *
     * @param grcode 출력시 그룹코드
     */
    public void setGrcode(final String grcode) {
        this.grcode = grcode;
    }

    /**
     * 출력시 그룹코드 취득
     *
     * @return grcode 출력시 그룹코드
     */
    public String getGrcode() {
        return StringUtils.defaultString(this.grcode);
    }

    /**
     * 컬럼너비 설정
     *
     * @param dsp_width 컬럼너비
     */
    public void setDsp_width(final String dsp_width) {
        this.dsp_width = dsp_width;
    }

    /**
     * 컬럼너비 취득
     *
     * @return dsp_width 컬럼너비
     */
    public String getDsp_width() {
        return StringUtils.defaultString(this.dsp_width);
    }

    /**
     * 검색조건여부 설정
     *
     * @param search_yn 검색조건여부
     */
    public void setSearch_yn(final String search_yn) {
        this.search_yn = search_yn;
    }

    /**
     * 검색조건여부 취득
     *
     * @return search_yn 검색조건여부
     */
    public String getSearch_yn() {
        return StringUtils.defaultString(this.search_yn);
    }

    /**
     * 정렬순번 설정
     *
     * @param sortseq 정렬순번
     */
    public void setSortseq(final String sortseq) {
        this.sortseq = sortseq;
    }

    /**
     * 정렬순번 취득
     *
     * @return sortseq 정렬순번
     */
    public String getSortseq() {
        return StringUtils.defaultString(this.sortseq);
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