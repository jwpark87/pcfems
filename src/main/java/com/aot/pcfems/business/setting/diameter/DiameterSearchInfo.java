/*****************************************************************************
 * 프로그램명  : DiameterSearchInfo.java
 * 설     명  : Diameter Host 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.03.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameter;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DiameterSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -4577799815054830681L;
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
     * 검색키
     */
    private String srch_key = "";
    /**
     * 검색값
     */
    private String srch_val = "";

    /**
     * 생성자
     */
    public DiameterSearchInfo() {
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
     * 장비명
     */
    private String srch_locality = "";
    /**
     * 국가
     */
    private String country_nm = "";
    /**
     * 사업자명
     */
    private String carrier_nm = "";
    /**
     * 국소(설치지역)
     */
    private String local = "";
    /**
     * Realm
     */
    private String realm = "";
    /**
     * Host
     */
    private String srch_host = "";
    /**
     * 상대 Peer Mode
     */
    private String remote_peer_mode = "";
    /**
     * local_slot
     */
    private String local_slot = "";
    /**
     * Primary IP
     */
    private String local_primary_ip = "";
    /**
     * Secondary IP
     */
    private String local_secondary_ip = "";
    /**
     * local_port
     */
    private Integer local_port = null;
    /**
     * remote_primary_ip
     */
    private String remote_primary_ip = "";
    /**
     * remote_secondary_ip
     */
    private String remote_secondary_ip = "";
    /**
     * remote_port
     */
    private Integer remote_port = null;
    /**
     * Access Control List
     */
    private String access_ctrl_list = "";
    /**
     * Interface
     */
    private String strInterface = "";
    /**
     * sctp_tw_timer
     */
    private Integer sctp_tw_timer = null;
    /**
     * sctp_hb_interval
     */
    private Integer sctp_hb_interval = null;
    /**
     * sctp_assoc_max_retrans
     */
    private Integer sctp_assoc_max_retrans = null;
    /**
     * sctp_rto_init
     */
    private Integer sctp_rto_init = null;
    /**
     * sctp_rto_min
     */
    private Integer sctp_rto_min = null;
    /**
     * sctp_rto_max
     */
    private Integer sctp_rto_max = null;
    /**
     * sctp_path_max_retrans
     */
    private Integer sctp_path_max_retrans = null;
    /**
     * sctp_max_init_retrans
     */
    private Integer sctp_max_init_retrans = null;
    /**
     * sctp_max_seg
     */
    private Integer sctp_max_seg = null;
    /**
     * sctp_sack_timeout
     */
    private Integer sctp_sack_timeout = null;
    /**
     * 담당
     */
    private String contact_name = "";
    /**
     * contact_email
     */
    private String contact_email = "";
    /**
     * contact_noc
     */
    private String contact_noc = "";
    /**
     * contact_fix_phone
     */
    private String contact_fix_phone = "";
    /**
     * contact_mobile_phone
     */
    private String contact_mobile_phone = "";
    /**
     * vendor_name
     */
    private String vendor_name = "";
    /**
     * 연결상태(그룹코드: LINK_STATUS)
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
     * 국가 설정
     *
     * @param country_nm 국가
     */
    public void setCountry_nm(final String country_nm) {
        this.country_nm = country_nm;
    }

    /**
     * 국가 취득
     *
     * @return country_nm 국가
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
     * 국소(설치지역) 설정
     *
     * @param local 국소(설치지역)
     */
    public void setLocal(final String local) {
        this.local = local;
    }

    /**
     * 국소(설치지역) 취득
     *
     * @return local 국소(설치지역)
     */
    public String getLocal() {
        return StringUtils.defaultString(this.local);
    }

    /**
     * Realm 설정
     *
     * @param realm Realm
     */
    public void setRealm(final String realm) {
        this.realm = realm;
    }

    /**
     * Realm 취득
     *
     * @return realm Realm
     */
    public String getRealm() {
        return StringUtils.defaultString(this.realm);
    }

    /**
     * Host 설정
     *
     * @param srch_host Host
     */
    public void setSrch_host(final String srch_host) {
        this.srch_host = srch_host;
    }

    /**
     * Host 취득
     *
     * @return srch_host Host
     */
    public String getSrch_host() {
        return StringUtils.defaultString(this.srch_host);
    }

    /**
     * 상대 Peer Mode 설정
     *
     * @param remote_peer_mode 상대 Peer Mode
     */
    public void setRemote_peer_mode(final String remote_peer_mode) {
        this.remote_peer_mode = remote_peer_mode;
    }

    /**
     * 상대 Peer Mode 취득
     *
     * @return remote_peer_mode 상대 Peer Mode
     */
    public String getRemote_peer_mode() {
        return StringUtils.defaultString(this.remote_peer_mode);
    }

    /**
     * local_slot 설정
     *
     * @param local_slot local_slot
     */
    public void setLocal_slot(final String local_slot) {
        this.local_slot = local_slot;
    }

    /**
     * local_slot 취득
     *
     * @return local_slot local_slot
     */
    public String getLocal_slot() {
        return StringUtils.defaultString(this.local_slot);
    }

    /**
     * Primary IP 설정
     *
     * @param local_primary_ip Primary IP
     */
    public void setLocal_primary_ip(final String local_primary_ip) {
        this.local_primary_ip = local_primary_ip;
    }

    /**
     * Primary IP 취득
     *
     * @return local_primary_ip Primary IP
     */
    public String getLocal_primary_ip() {
        return StringUtils.defaultString(this.local_primary_ip);
    }

    /**
     * Secondary IP 설정
     *
     * @param local_secondary_ip Secondary IP
     */
    public void setLocal_secondary_ip(final String local_secondary_ip) {
        this.local_secondary_ip = local_secondary_ip;
    }

    /**
     * Secondary IP 취득
     *
     * @return local_secondary_ip Secondary IP
     */
    public String getLocal_secondary_ip() {
        return StringUtils.defaultString(this.local_secondary_ip);
    }

    /**
     * local_port 설정
     *
     * @param local_port local_port
     */
    public void setLocal_port(final Integer local_port) {
        this.local_port = local_port;
    }

    /**
     * local_port 취득
     *
     * @return local_port local_port
     */
    public Integer getLocal_port() {
        return this.local_port;
    }

    /**
     * remote_primary_ip 설정
     *
     * @param remote_primary_ip remote_primary_ip
     */
    public void setRemote_primary_ip(final String remote_primary_ip) {
        this.remote_primary_ip = remote_primary_ip;
    }

    /**
     * remote_primary_ip 취득
     *
     * @return remote_primary_ip remote_primary_ip
     */
    public String getRemote_primary_ip() {
        return StringUtils.defaultString(this.remote_primary_ip);
    }

    /**
     * remote_secondary_ip 설정
     *
     * @param remote_secondary_ip remote_secondary_ip
     */
    public void setRemote_secondary_ip(final String remote_secondary_ip) {
        this.remote_secondary_ip = remote_secondary_ip;
    }

    /**
     * remote_secondary_ip 취득
     *
     * @return remote_secondary_ip remote_secondary_ip
     */
    public String getRemote_secondary_ip() {
        return StringUtils.defaultString(this.remote_secondary_ip);
    }

    /**
     * remote_port 설정
     *
     * @param remote_port remote_port
     */
    public void setRemote_port(final Integer remote_port) {
        this.remote_port = remote_port;
    }

    /**
     * remote_port 취득
     *
     * @return remote_port remote_port
     */
    public Integer getRemote_port() {
        return this.remote_port;
    }

    /**
     * Access Control List 설정
     *
     * @param access_ctrl_list Access Control List
     */
    public void setAccess_ctrl_list(final String access_ctrl_list) {
        this.access_ctrl_list = access_ctrl_list;
    }

    /**
     * Access Control List 취득
     *
     * @return access_ctrl_list Access Control List
     */
    public String getAccess_ctrl_list() {
        return StringUtils.defaultString(this.access_ctrl_list);
    }

    /**
     * Interface 설정
     *
     * @param strInterface Interface
     */
    public void setStrInterface(final String strInterface) {
        this.strInterface = strInterface;
    }

    /**
     * Interface 취득
     *
     * @return strInterface Interface
     */
    public String getStrInterface() {
        return StringUtils.defaultString(this.strInterface);
    }

    /**
     * sctp_tw_timer 설정
     *
     * @param sctp_tw_timer sctp_tw_timer
     */
    public void setSctp_tw_timer(final Integer sctp_tw_timer) {
        this.sctp_tw_timer = sctp_tw_timer;
    }

    /**
     * sctp_tw_timer 취득
     *
     * @return sctp_tw_timer sctp_tw_timer
     */
    public Integer getSctp_tw_timer() {
        return this.sctp_tw_timer;
    }

    /**
     * sctp_hb_interval 설정
     *
     * @param sctp_hb_interval sctp_hb_interval
     */
    public void setSctp_hb_interval(final Integer sctp_hb_interval) {
        this.sctp_hb_interval = sctp_hb_interval;
    }

    /**
     * sctp_hb_interval 취득
     *
     * @return sctp_hb_interval sctp_hb_interval
     */
    public Integer getSctp_hb_interval() {
        return this.sctp_hb_interval;
    }

    /**
     * sctp_assoc_max_retrans 설정
     *
     * @param sctp_assoc_max_retrans sctp_assoc_max_retrans
     */
    public void setSctp_assoc_max_retrans(final Integer sctp_assoc_max_retrans) {
        this.sctp_assoc_max_retrans = sctp_assoc_max_retrans;
    }

    /**
     * sctp_assoc_max_retrans 취득
     *
     * @return sctp_assoc_max_retrans sctp_assoc_max_retrans
     */
    public Integer getSctp_assoc_max_retrans() {
        return this.sctp_assoc_max_retrans;
    }

    /**
     * sctp_rto_init 설정
     *
     * @param sctp_rto_init sctp_rto_init
     */
    public void setSctp_rto_init(final Integer sctp_rto_init) {
        this.sctp_rto_init = sctp_rto_init;
    }

    /**
     * sctp_rto_init 취득
     *
     * @return sctp_rto_init sctp_rto_init
     */
    public Integer getSctp_rto_init() {
        return this.sctp_rto_init;
    }

    /**
     * sctp_rto_min 설정
     *
     * @param sctp_rto_min sctp_rto_min
     */
    public void setSctp_rto_min(final Integer sctp_rto_min) {
        this.sctp_rto_min = sctp_rto_min;
    }

    /**
     * sctp_rto_min 취득
     *
     * @return sctp_rto_min sctp_rto_min
     */
    public Integer getSctp_rto_min() {
        return this.sctp_rto_min;
    }

    /**
     * sctp_rto_max 설정
     *
     * @param sctp_rto_max sctp_rto_max
     */
    public void setSctp_rto_max(final Integer sctp_rto_max) {
        this.sctp_rto_max = sctp_rto_max;
    }

    /**
     * sctp_rto_max 취득
     *
     * @return sctp_rto_max sctp_rto_max
     */
    public Integer getSctp_rto_max() {
        return this.sctp_rto_max;
    }

    /**
     * sctp_path_max_retrans 설정
     *
     * @param sctp_path_max_retrans sctp_path_max_retrans
     */
    public void setSctp_path_max_retrans(final Integer sctp_path_max_retrans) {
        this.sctp_path_max_retrans = sctp_path_max_retrans;
    }

    /**
     * sctp_path_max_retrans 취득
     *
     * @return sctp_path_max_retrans sctp_path_max_retrans
     */
    public Integer getSctp_path_max_retrans() {
        return this.sctp_path_max_retrans;
    }

    /**
     * sctp_max_init_retrans 설정
     *
     * @param sctp_max_init_retrans sctp_max_init_retrans
     */
    public void setSctp_max_init_retrans(final Integer sctp_max_init_retrans) {
        this.sctp_max_init_retrans = sctp_max_init_retrans;
    }

    /**
     * sctp_max_init_retrans 취득
     *
     * @return sctp_max_init_retrans sctp_max_init_retrans
     */
    public Integer getSctp_max_init_retrans() {
        return this.sctp_max_init_retrans;
    }

    /**
     * sctp_max_seg 설정
     *
     * @param sctp_max_seg sctp_max_seg
     */
    public void setSctp_max_seg(final Integer sctp_max_seg) {
        this.sctp_max_seg = sctp_max_seg;
    }

    /**
     * sctp_max_seg 취득
     *
     * @return sctp_max_seg sctp_max_seg
     */
    public Integer getSctp_max_seg() {
        return this.sctp_max_seg;
    }

    /**
     * sctp_sack_timeout 설정
     *
     * @param sctp_sack_timeout sctp_sack_timeout
     */
    public void setSctp_sack_timeout(final Integer sctp_sack_timeout) {
        this.sctp_sack_timeout = sctp_sack_timeout;
    }

    /**
     * sctp_sack_timeout 취득
     *
     * @return sctp_sack_timeout sctp_sack_timeout
     */
    public Integer getSctp_sack_timeout() {
        return this.sctp_sack_timeout;
    }

    /**
     * 담당 설정
     *
     * @param contact_name 담당
     */
    public void setContact_name(final String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * 담당 취득
     *
     * @return contact_name 담당
     */
    public String getContact_name() {
        return StringUtils.defaultString(this.contact_name);
    }

    /**
     * contact_email 설정
     *
     * @param contact_email contact_email
     */
    public void setContact_email(final String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * contact_email 취득
     *
     * @return contact_email contact_email
     */
    public String getContact_email() {
        return StringUtils.defaultString(this.contact_email);
    }

    /**
     * contact_noc 설정
     *
     * @param contact_noc contact_noc
     */
    public void setContact_noc(final String contact_noc) {
        this.contact_noc = contact_noc;
    }

    /**
     * contact_noc 취득
     *
     * @return contact_noc contact_noc
     */
    public String getContact_noc() {
        return StringUtils.defaultString(this.contact_noc);
    }

    /**
     * contact_fix_phone 설정
     *
     * @param contact_fix_phone contact_fix_phone
     */
    public void setContact_fix_phone(final String contact_fix_phone) {
        this.contact_fix_phone = contact_fix_phone;
    }

    /**
     * contact_fix_phone 취득
     *
     * @return contact_fix_phone contact_fix_phone
     */
    public String getContact_fix_phone() {
        return StringUtils.defaultString(this.contact_fix_phone);
    }

    /**
     * contact_mobile_phone 설정
     *
     * @param contact_mobile_phone contact_mobile_phone
     */
    public void setContact_mobile_phone(final String contact_mobile_phone) {
        this.contact_mobile_phone = contact_mobile_phone;
    }

    /**
     * contact_mobile_phone 취득
     *
     * @return contact_mobile_phone contact_mobile_phone
     */
    public String getContact_mobile_phone() {
        return StringUtils.defaultString(this.contact_mobile_phone);
    }

    /**
     * vendor_name 설정
     *
     * @param vendor_name vendor_name
     */
    public void setVendor_name(final String vendor_name) {
        this.vendor_name = vendor_name;
    }

    /**
     * vendor_name 취득
     *
     * @return vendor_name vendor_name
     */
    public String getVendor_name() {
        return StringUtils.defaultString(this.vendor_name);
    }

    /**
     * 연결상태(그룹코드: LINK_STATUS) 설정
     *
     * @param status 연결상태(그룹코드: LINK_STATUS)
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 연결상태(그룹코드: LINK_STATUS) 취득
     *
     * @return status 연결상태(그룹코드: LINK_STATUS)
     */
    public String getStatus() {
        return StringUtils.defaultString(this.status);
    }

}