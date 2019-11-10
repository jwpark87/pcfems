/*****************************************************************************
 * 프로그램명  : VarStatsSearchInfo.java
 * 설     명  : var/stats 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.21   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.varstats;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * var/stats 조회 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class VarStatsSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -743073044442980512L;
    /**
     * 검색테이블
     */
    private String srch_index_type = "";
    /**
     * 검색용FROM날짜
     */
    private String srch_date_from = "";
    /**
     * 검색용TO날짜
     */
    private String srch_date_to = "";

    /**
     * 검색조건
     */
    private String srch_text_prefix = "";
    /**
     * 검색조건컬럼
     */
    private String srch_filter_column = "";
    /**
     * 검색조건타입
     */
    private String srch_filter_type = "";
    /**
     * 검색조건검색어
     */
    private String srch_filter_input = "";
    /**
     * ElasticSearch URL
     */
    private String srch_es_base_url = "";
    /**
     *
     */
    private String srch_out_col_ids = "";
    /**
     *
     */
    private String srch_out_col_names = "";
    private String srch_out_col_types = "";
    /**
     * 검색기준일자 컬럼아이디
     */
    private String srch_def_col_id = "";
    /**
     * 엑셀유니크아이디
     */
    private String srch_uuid = "";
    /**
     * 그리드데이타
     */
    private String srch_grid_data = "";

    /**
     * 전체로우갯수
     */
    private String srch_total_row_cnt = "";
    /**
     * 구
     */
    private String srch_oldest_dt = "";
    /**
     * 신
     */
    private String srch_newest_dt = "";
    /**
     * 국소
     */
    private String srch_locality = "";

    /**
     * 국소 설정
     *
     * @param srch_locality 국소
     */
    public void setSrch_locality(final String srch_locality) {
        this.srch_locality = srch_locality;
    }

    /**
     * 국소 취득
     *
     * @return srch_locality 국소
     */
    public String getSrch_locality() {
        return StringUtils.defaultString(this.srch_locality);
    }

    /**
     * 전체로우갯수 설정
     *
     * @param srch_total_row_cnt 전체로우갯수
     */
    public void setSrch_total_row_cnt(final String srch_total_row_cnt) {
        this.srch_total_row_cnt = srch_total_row_cnt;
    }

    /**
     * 전체로우갯수 취득
     *
     * @return srch_total_row_cnt 전체로우갯수
     */
    public String getSrch_total_row_cnt() {
        return StringUtils.defaultString(this.srch_total_row_cnt);
    }

    /**
     * 구 설정
     *
     * @param srch_oldest_dt 구
     */
    public void setSrch_oldest_dt(final String srch_oldest_dt) {
        this.srch_oldest_dt = srch_oldest_dt;
    }

    /**
     * 구 취득
     *
     * @return srch_oldest_dt 구
     */
    public String getSrch_oldest_dt() {
        return StringUtils.defaultString(this.srch_oldest_dt);
    }

    /**
     * 신 설정
     *
     * @param srch_newest_dt 신
     */
    public void setSrch_newest_dt(final String srch_newest_dt) {
        this.srch_newest_dt = srch_newest_dt;
    }

    /**
     * 신 취득
     *
     * @return srch_newest_dt 신
     */
    public String getSrch_newest_dt() {
        return StringUtils.defaultString(this.srch_newest_dt);
    }

    /**
     * 그리드데이타 설정
     *
     * @param srch_grid_data 그리드데이타
     */
    public void setSrch_grid_data(final String srch_grid_data) {
        this.srch_grid_data = srch_grid_data;
    }

    /**
     * 그리드데이타 취득
     *
     * @return srch_grid_data 그리드데이타
     */
    public String getSrch_grid_data() {
        return StringUtils.defaultString(this.srch_grid_data);
    }

    /**
     * 엑셀유니크아이디 설정
     *
     * @param srch_uuid 엑셀유니크아이디
     */
    public void setSrch_uuid(final String srch_uuid) {
        this.srch_uuid = srch_uuid;
    }

    /**
     * 엑셀유니크아이디 취득
     *
     * @return srch_uuid 엑셀유니크아이디
     */
    public String getSrch_uuid() {
        return StringUtils.defaultString(this.srch_uuid);
    }

    /**
     * 설정
     *
     * @param srch_out_col_ids
     */
    public void setSrch_out_col_ids(final String srch_out_col_ids) {
        this.srch_out_col_ids = srch_out_col_ids;
    }

    /**
     * 취득
     *
     * @return srch_out_col_ids
     */
    public String getSrch_out_col_ids() {
        return StringUtils.defaultString(this.srch_out_col_ids);
    }

    /**
     * 설정
     *
     * @param srch_out_col_names
     */
    public void setSrch_out_col_names(final String srch_out_col_names) {
        this.srch_out_col_names = srch_out_col_names;
    }

    /**
     * 취득
     *
     * @return srch_out_col_names
     */
    public String getSrch_out_col_names() {
        return StringUtils.defaultString(this.srch_out_col_names);
    }

    public String getSrch_out_col_types() {
        return this.srch_out_col_types;
    }

    public void setSrch_out_col_types(final String srch_out_col_types) {
        this.srch_out_col_types = srch_out_col_types;
    }

    /**
     * 검색기준일자 컬럼아이디 설정
     *
     * @param srch_def_col_id 검색기준일자 컬럼아이디
     */
    public void setSrch_def_col_id(final String srch_def_col_id) {
        this.srch_def_col_id = srch_def_col_id;
    }

    /**
     * 검색기준일자 컬럼아이디 취득
     *
     * @return srch_def_col_id 검색기준일자 컬럼아이디
     */
    public String getSrch_def_col_id() {
        return StringUtils.defaultString(this.srch_def_col_id);
    }

    /**
     * ElasticSearch URL 설정
     *
     * @param srch_es_base_url ElasticSearch URL
     */
    public void setSrch_es_base_url(final String srch_es_base_url) {
        this.srch_es_base_url = srch_es_base_url;
    }

    /**
     * ElasticSearch URL 취득
     *
     * @return srch_es_base_url ElasticSearch URL
     */
    public String getSrch_es_base_url() {
        return StringUtils.defaultString(this.srch_es_base_url);
    }

    /**
     * 검색조건 설정
     *
     * @param srch_text_prefix 검색조건
     */
    public void setSrch_text_prefix(final String srch_text_prefix) {
        this.srch_text_prefix = srch_text_prefix;
    }

    /**
     * 검색조건 취득
     *
     * @return srch_text_prefix 검색조건
     */
    public String getSrch_text_prefix() {
        return StringUtils.defaultString(this.srch_text_prefix);
    }

    /**
     * 검색조건컬럼 설정
     *
     * @param srch_filter_column 검색조건컬럼
     */
    public void setSrch_filter_column(final String srch_filter_column) {
        this.srch_filter_column = srch_filter_column;
    }

    /**
     * 검색조건컬럼 취득
     *
     * @return srch_filter_column 검색조건컬럼
     */
    public String getSrch_filter_column() {
        return StringUtils.defaultString(this.srch_filter_column);
    }

    /**
     * 검색조건타입 설정
     *
     * @param srch_filter_type 검색조건타입
     */
    public void setSrch_filter_type(final String srch_filter_type) {
        this.srch_filter_type = srch_filter_type;
    }

    /**
     * 검색조건타입 취득
     *
     * @return srch_filter_type 검색조건타입
     */
    public String getSrch_filter_type() {
        return StringUtils.defaultString(this.srch_filter_type);
    }

    /**
     * 검색조건검색어 설정
     *
     * @param srch_filter_input 검색조건검색어
     */
    public void setSrch_filter_input(final String srch_filter_input) {
        this.srch_filter_input = srch_filter_input;
    }

    /**
     * 검색조건검색어 취득
     *
     * @return srch_filter_input 검색조건검색어
     */
    public String getSrch_filter_input() {
        return StringUtils.defaultString(this.srch_filter_input);
    }

    /**
     * 검색테이블 설정
     *
     * @param srch_index_type 검색테이블
     */
    public void setSrch_index_type(final String srch_index_type) {
        this.srch_index_type = srch_index_type;
    }

    /**
     * 검색테이블 취득
     *
     * @return srch_index_type 검색테이블
     */
    public String getSrch_index_type() {
        return StringUtils.defaultString(this.srch_index_type);
    }

    /**
     * 검색용FROM날짜 설정
     *
     * @param srch_date_from 검색용FROM날짜
     */
    public void setSrch_date_from(final String srch_date_from) {
        this.srch_date_from = srch_date_from;
    }

    /**
     * 검색용FROM날짜 취득
     *
     * @return srch_date_from 검색용FROM날짜
     */
    public String getSrch_date_from() {
        return StringUtils.defaultString(this.srch_date_from);
    }

    /**
     * 검색용TO날짜 설정
     *
     * @param srch_date_to 검색용TO날짜
     */
    public void setSrch_date_to(final String srch_date_to) {
        this.srch_date_to = srch_date_to;
    }

    /**
     * 검색용TO날짜 취득
     *
     * @return srch_date_to 검색용TO날짜
     */
    public String getSrch_date_to() {
        return StringUtils.defaultString(this.srch_date_to);
    }

}