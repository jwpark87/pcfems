/*****************************************************************************
 * 프로그램명  : ChartSearchInfo.java
 * 설     명  : 통계관리 CHART 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 통계관리 CHART 조회 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class ChartSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -5980254803661127081L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
    /**
     * 페이지
     */
    private String srch_page = "";
    /**
     * 시작인덱스
     */
    private int startIndex = 0;
    /**
     * 종료인덱스
     */
    private int endIndex = 0;

    /**
     * 검색키
     */
    private String srch_key = "";
    /**
     * 검색값
     */
    private String srch_val = "";

    /**
     * 검색용FROM날짜
     */
    private String srch_date_from = "";
    /**
     * 검색용TO날짜
     */
    private String srch_date_to = "";
    /**
     * 검색용그룹ID
     */
    private String srch_sbc_group_id = "";
    /**
     * 검색용통계기준시간
     */
    private String srch_stcs_dt = "";
    /**
     * 검색용통계기준국가별
     */
    private String srch_stcs_country = "";
    /**
     * 검색용통계기준사업자별
     */
    private String srch_stcs_realm = "";
    /**
     * 검색용발신국가
     */
    private String srch_from_country_cd = "";
    /**
     * 검색용착신국가
     */
    private String srch_to_country_cd = "";
    /**
     * 검색용발신사업자
     */
    private String srch_acme_session_ingress_realm = "";
    /**
     * 검색용착신사업자
     */
    private String srch_acme_session_egress_realm = "";
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
     * 그리드 대이타
     */
    private String stcs_excel_data = "";

    /**
     * 검색 국가
     */
    private String srch_country = "";
    /**
     * 검색 Host
     */
    private String srch_host = "";
    /**
     * 검색 Realm
     */
    private String srch_realm = "";

    /**
     * 검색폴더
     */
    private String srch_pcap_file_folder = "";
    /**
     * 상위
     */
    private String srch_pcap_file_parent = "";

    /**
     * 파일
     */
    private String srch_pcap_file = "";
    /**
     * 검색 사업자
     */
    private String srch_carrier_nm = "";

    /**
     * 검색 Dest 국가
     */
    private String srch_dst_country = "";
    /**
     * 검색 Dest 사업자
     */
    private String srch_dst_carrier_nm = "";
    /**
     * 검색 Dest Realm
     */
    private String srch_dst_realm = "";

    /**
     * 링크셋
     */
    private String srch_linkset = "";
    /**
     * peer
     */
    private String srch_dst_host = "";
    /**
     * 검색 clli
     */
    private String srch_clli = "";
    /**
     * srch_groupping_pc_list_nm
     */
    private String srch_groupping_pc_list_nm = "";

    /**
     * srch_groupping_pc_list_nm 설정
     *
     * @param srch_groupping_pc_list_nm srch_groupping_pc_list_nm
     */
    public void setSrch_groupping_pc_list_nm(final String srch_groupping_pc_list_nm) {
        this.srch_groupping_pc_list_nm = srch_groupping_pc_list_nm;
    }

    /**
     * srch_groupping_pc_list_nm 취득
     *
     * @return srch_groupping_pc_list_nm srch_groupping_pc_list_nm
     */
    public String getSrch_groupping_pc_list_nm() {
        return StringUtils.defaultString(this.srch_groupping_pc_list_nm);
    }

    /**
     * 검색 clli 설정
     *
     * @param srch_clli 검색 clli
     */
    public void setSrch_clli(final String srch_clli) {
        this.srch_clli = srch_clli;
    }

    /**
     * 검색 clli 취득
     *
     * @return srch_clli 검색 clli
     */
    public String getSrch_clli() {
        return StringUtils.defaultString(this.srch_clli);
    }

    /**
     * peer 설정
     *
     * @param srch_dst_host peer
     */
    public void setSrch_dst_host(final String srch_dst_host) {
        this.srch_dst_host = srch_dst_host;
    }

    /**
     * peer 취득
     *
     * @return srch_dst_host peer
     */
    public String getSrch_dst_host() {
        return StringUtils.defaultString(this.srch_dst_host);
    }

    /**
     * 링크셋 설정
     *
     * @param srch_linkset 링크셋
     */
    public void setSrch_linkset(final String srch_linkset) {
        this.srch_linkset = srch_linkset;
    }

    /**
     * 링크셋 취득
     *
     * @return srch_linkset 링크셋
     */
    public String getSrch_linkset() {
        return StringUtils.defaultString(this.srch_linkset);
    }

    /**
     * 검색 Dest 국가 설정
     *
     * @param srch_dst_country 검색 Dest 국가
     */
    public void setSrch_dst_country(final String srch_dst_country) {
        this.srch_dst_country = srch_dst_country;
    }

    /**
     * 검색 Dest 국가 취득
     *
     * @return srch_dst_country 검색 Dest 국가
     */
    public String getSrch_dst_country() {
        return StringUtils.defaultString(this.srch_dst_country);
    }

    /**
     * 검색 Dest 사업자 설정
     *
     * @param srch_dst_carrier_nm 검색 Dest 사업자
     */
    public void setSrch_dst_carrier_nm(final String srch_dst_carrier_nm) {
        this.srch_dst_carrier_nm = srch_dst_carrier_nm;
    }

    /**
     * 검색 Dest 사업자 취득
     *
     * @return srch_dst_carrier_nm 검색 Dest 사업자
     */
    public String getSrch_dst_carrier_nm() {
        return StringUtils.defaultString(this.srch_dst_carrier_nm);
    }

    /**
     * 검색 Dest Realm 설정
     *
     * @param srch_dst_realm 검색 Dest Realm
     */
    public void setSrch_dst_realm(final String srch_dst_realm) {
        this.srch_dst_realm = srch_dst_realm;
    }

    /**
     * 검색 Dest Realm 취득
     *
     * @return srch_dst_realm 검색 Dest Realm
     */
    public String getSrch_dst_realm() {
        return StringUtils.defaultString(this.srch_dst_realm);
    }

    /**
     * 검색 사업자 설정
     *
     * @param srch_carrier_nm 검색 사업자
     */
    public void setSrch_carrier_nm(final String srch_carrier_nm) {
        this.srch_carrier_nm = srch_carrier_nm;
    }

    /**
     * 검색 사업자 취득
     *
     * @return srch_carrier_nm 검색 사업자
     */
    public String getSrch_carrier_nm() {
        return StringUtils.defaultString(this.srch_carrier_nm);
    }

    /**
     * 검색 cg gt
     */
    private String srch_cg_gt = "";
    /**
     * 검색 cd gt
     */
    private String srch_cd_gt = "";
    /**
     * 검색 cg pc list nm
     */
    private String srch_cg_pc_list_nm = "";
    /**
     * 검색 cg pc list nm
     */
    private String srch_cd_pc_list_nm = "";
    /**
     * 검색 cg 사업자
     */
    private String srch_cg_carrier_nm = "";
    /**
     * 검색 cd 사업자
     */
    private String srch_cd_carrier_nm = "";

    /**
     * 장비명
     */
    private String srch_locality = "";

    /**
     * 장비명 설정
     *
     * @param srch_locality 장비명
     */
    public void setSrch_locality(final String srch_locality) {
        this.srch_locality = srch_locality;
    }

    /**
     * 장비명 취득
     *
     * @return srch_locality 장비명
     */
    public String getSrch_locality() {
        return StringUtils.defaultString(this.srch_locality);
    }

    /**
     * 검색 cg gt 설정
     *
     * @param srch_cg_gt 검색 cg gt
     */
    public void setSrch_cg_gt(final String srch_cg_gt) {
        this.srch_cg_gt = srch_cg_gt;
    }

    /**
     * 검색 cg gt 취득
     *
     * @return srch_cg_gt 검색 cg gt
     */
    public String getSrch_cg_gt() {
        return StringUtils.defaultString(this.srch_cg_gt);
    }

    /**
     * 검색 cd gt 설정
     *
     * @param srch_cd_gt 검색 cd gt
     */
    public void setSrch_cd_gt(final String srch_cd_gt) {
        this.srch_cd_gt = srch_cd_gt;
    }

    /**
     * 검색 cd gt 취득
     *
     * @return srch_cd_gt 검색 cd gt
     */
    public String getSrch_cd_gt() {
        return StringUtils.defaultString(this.srch_cd_gt);
    }

    /**
     * 검색 cg pc list nm 설정
     *
     * @param srch_cg_pc_list_nm 검색 cg pc list nm
     */
    public void setSrch_cg_pc_list_nm(final String srch_cg_pc_list_nm) {
        this.srch_cg_pc_list_nm = srch_cg_pc_list_nm;
    }

    /**
     * 검색 cg pc list nm 취득
     *
     * @return srch_cg_pc_list_nm 검색 cg pc list nm
     */
    public String getSrch_cg_pc_list_nm() {
        return StringUtils.defaultString(this.srch_cg_pc_list_nm);
    }

    /**
     * 검색 cg pc list nm 설정
     *
     * @param srch_cd_pc_list_nm 검색 cg pc list nm
     */
    public void setSrch_cd_pc_list_nm(final String srch_cd_pc_list_nm) {
        this.srch_cd_pc_list_nm = srch_cd_pc_list_nm;
    }

    /**
     * 검색 cg pc list nm 취득
     *
     * @return srch_cd_pc_list_nm 검색 cg pc list nm
     */
    public String getSrch_cd_pc_list_nm() {
        return StringUtils.defaultString(this.srch_cd_pc_list_nm);
    }

    /**
     * 검색 cg 사업자 설정
     *
     * @param srch_cg_carrier_nm 검색 cg 사업자
     */
    public void setSrch_cg_carrier_nm(final String srch_cg_carrier_nm) {
        this.srch_cg_carrier_nm = srch_cg_carrier_nm;
    }

    /**
     * 검색 cg 사업자 취득
     *
     * @return srch_cg_carrier_nm 검색 cg 사업자
     */
    public String getSrch_cg_carrier_nm() {
        return StringUtils.defaultString(this.srch_cg_carrier_nm);
    }

    /**
     * 검색 cd 사업자 설정
     *
     * @param srch_cd_carrier_nm 검색 cd 사업자
     */
    public void setSrch_cd_carrier_nm(final String srch_cd_carrier_nm) {
        this.srch_cd_carrier_nm = srch_cd_carrier_nm;
    }

    /**
     * 검색 cd 사업자 취득
     *
     * @return srch_cd_carrier_nm 검색 cd 사업자
     */
    public String getSrch_cd_carrier_nm() {
        return StringUtils.defaultString(this.srch_cd_carrier_nm);
    }

    /**
     * 파일 설정
     *
     * @param srch_pcap_file 파일
     */
    public void setSrch_pcap_file(final String srch_pcap_file) {
        this.srch_pcap_file = srch_pcap_file;
    }

    /**
     * 파일 취득
     *
     * @return srch_pcap_file 파일
     */
    public String getSrch_pcap_file() {
        return StringUtils.defaultString(this.srch_pcap_file);
    }

    /**
     * 상위 설정
     *
     * @param srch_pcap_file_parent 상위
     */
    public void setSrch_pcap_file_parent(final String srch_pcap_file_parent) {
        this.srch_pcap_file_parent = srch_pcap_file_parent;
    }

    /**
     * 상위 취득
     *
     * @return srch_pcap_file_parent 상위
     */
    public String getSrch_pcap_file_parent() {
        return StringUtils.defaultString(this.srch_pcap_file_parent);
    }

    /**
     * 검색폴더 설정
     *
     * @param srch_pcap_file_folder 검색폴더
     */
    public void setSrch_pcap_file_folder(final String srch_pcap_file_folder) {
        this.srch_pcap_file_folder = srch_pcap_file_folder;
    }

    /**
     * 검색폴더 취득
     *
     * @return srch_pcap_file_folder 검색폴더
     */
    public String getSrch_pcap_file_folder() {
        return StringUtils.defaultString(this.srch_pcap_file_folder);
    }

    /**
     * 검색 국가 설정
     *
     * @param srch_country 검색 국가
     */
    public void setSrch_country(final String srch_country) {
        this.srch_country = srch_country;
    }

    /**
     * 검색 국가 취득
     *
     * @return srch_country 검색 국가
     */
    public String getSrch_country() {
        return StringUtils.defaultString(this.srch_country);
    }

    /**
     * 검색 Host 설정
     *
     * @param srch_host 검색 Host
     */
    public void setSrch_host(final String srch_host) {
        this.srch_host = srch_host;
    }

    /**
     * 검색 Host 취득
     *
     * @return srch_host 검색 Host
     */
    public String getSrch_host() {
        return StringUtils.defaultString(this.srch_host);
    }

    /**
     * 검색 Realm 설정
     *
     * @param srch_realm 검색 Realm
     */
    public void setSrch_realm(final String srch_realm) {
        this.srch_realm = srch_realm;
    }

    /**
     * 검색 Realm 취득
     *
     * @return srch_realm 검색 Realm
     */
    public String getSrch_realm() {
        return StringUtils.defaultString(this.srch_realm);
    }

    public String getStcs_excel_data() {
        return this.stcs_excel_data;
    }

    public void setStcs_excel_data(final String stcs_excel_data) {
        this.stcs_excel_data = stcs_excel_data;
    }

    /**
     * 생성자
     */
    public ChartSearchInfo() {
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
     * 페이지 설정
     *
     * @param srch_page 페이지
     */
    public void setSrch_page(final String srch_page) {
        this.srch_page = srch_page;
    }

    /**
     * 페이지 취득
     *
     * @return srch_page 페이지
     */
    public String getSrch_page() {
        return StringUtils.defaultString(this.srch_page);
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
     * 검색키설정
     *
     * @param srch_key 검색키
     */
    public void setSearch_key(final String srch_key) {
        this.srch_key = StringUtils.defaultString(srch_key);
    }

    /**
     * 검색키취득
     *
     * @return srch_key 검색키
     */
    public String getSearch_key() {
        return this.srch_key;
    }

    /**
     * 검색값설정
     *
     * @param srch_val 검색키
     */
    public void setSearch_val(final String srch_val) {
        this.srch_val = StringUtils.defaultString(srch_val);
    }

    /**
     * 검색값취득
     *
     * @return srch_val 검색값
     */
    public String getSearch_val() {
        return this.srch_val;
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

    /**
     * 검색용그룹ID 설정
     *
     * @param srch_sbc_group_id 검색용그룹ID
     */
    public void setSrch_sbc_group_id(final String srch_sbc_group_id) {
        this.srch_sbc_group_id = srch_sbc_group_id;
    }

    /**
     * 검색용그룹ID 취득
     *
     * @return srch_sbc_group_id 검색용그룹ID
     */
    public String getSrch_sbc_group_id() {
        return StringUtils.defaultString(this.srch_sbc_group_id);
    }

    /**
     * 검색용통계기준시간 설정
     *
     * @param srch_stcs_dt 검색용통계기준시간
     */
    public void setSrch_stcs_dt(final String srch_stcs_dt) {
        this.srch_stcs_dt = srch_stcs_dt;
    }

    /**
     * 검색용통계기준시간 취득
     *
     * @return srch_stcs_dt 검색용통계기준시간
     */
    public String getSrch_stcs_dt() {
        return StringUtils.defaultString(this.srch_stcs_dt);
    }

    /**
     * 검색용발신국가 설정
     *
     * @param srch_stcs_country 검색용발신국가
     */
    public void setSrch_stcs_country(final String srch_stcs_country) {
        this.srch_stcs_country = srch_stcs_country;
    }

    /**
     * 검색용발신국가 취득
     *
     * @return srch_stcs_country 검색용발신국가
     */
    public String getSrch_stcs_country() {
        return StringUtils.defaultString(this.srch_stcs_country);
    }

    /**
     * 검색용발신국가 설정
     *
     * @param srch_stcs_realm 검색용발신국가
     */
    public void setSrch_stcs_realm(final String srch_stcs_realm) {
        this.srch_stcs_realm = srch_stcs_realm;
    }

    /**
     * 검색용발신국가 취득
     *
     * @return srch_stcs_realm 검색용발신국가
     */
    public String getSrch_stcs_realm() {
        return StringUtils.defaultString(this.srch_stcs_realm);
    }

    /**
     * 검색용발신국가 설정
     *
     * @param srch_from_country_cd 검색용발신국가
     */
    public void setSrch_from_country_cd(final String srch_from_country_cd) {
        this.srch_from_country_cd = srch_from_country_cd;
    }

    /**
     * 검색용발신국가 취득
     *
     * @return srch_from_country_cd 검색용발신국가
     */
    public String getSrch_from_country_cd() {
        return StringUtils.defaultString(this.srch_from_country_cd);
    }

    /**
     * 검색용착신국가 설정
     *
     * @param srch_to_country_cd 검색용착신국가
     */
    public void setSrch_to_country_cd(final String srch_to_country_cd) {
        this.srch_to_country_cd = srch_to_country_cd;
    }

    /**
     * 검색용착신국가 취득
     *
     * @return srch_to_country_cd 검색용착신국가
     */
    public String getSrch_to_country_cd() {
        return StringUtils.defaultString(this.srch_to_country_cd);
    }

    /**
     * 검색용발신사업자 설정
     *
     * @param srch_acme_session_ingress_realm 검색용발신사업자
     */
    public void setSrch_acme_session_ingress_realm(final String srch_acme_session_ingress_realm) {
        this.srch_acme_session_ingress_realm = srch_acme_session_ingress_realm;
    }

    /**
     * 검색용발신사업자 취득
     *
     * @return srch_acme_session_ingress_realm 검색용발신사업자
     */
    public String getSrch_acme_session_ingress_realm() {
        return StringUtils.defaultString(this.srch_acme_session_ingress_realm);
    }

    /**
     * 검색용착신사업자 설정
     *
     * @param srch_acme_session_egress_realm 검색용착신사업자
     */
    public void setSrch_acme_session_egress_realm(final String srch_acme_session_egress_realm) {
        this.srch_acme_session_egress_realm = srch_acme_session_egress_realm;
    }

    /**
     * 검색용착신사업자 취득
     *
     * @return srch_acme_session_egress_realm 검색용착신사업자
     */
    public String getSrch_acme_session_egress_realm() {
        return StringUtils.defaultString(this.srch_acme_session_egress_realm);
    }

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