/*****************************************************************************
 * 프로그램명  : DiameterInfo.java
 * 설     명  : Diameter Host 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameter;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Diameter Host 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DiameterInfo implements Serializable {
    private transient static final long serialVersionUID = -3371380620803208692L;
    /**
     * 장비명
     */
    private String locality = "";
    /**
     * Locality Nm
     */
    private String locality_nm = "";
    /**
     * 국가
     */
    private String country_nm = "";
    /**
     * 사업자
     */
    private String carrier_nm = "";
    /**
     * 국소
     */
    private String local = "";
    /**
     * Realm
     */
    private String realm = "";
    /**
     * Host
     */
    private String host = "";
    /**
     * 상대 Peer Mode
     */
    private String remote_peer_mode = "";
    /**
     * Local Slot
     */
    private String local_slot = "";
    /**
     * Local Primary Ip
     */
    private String local_primary_ip = "";
    /**
     * Local Secondary Ip
     */
    private String local_secondary_ip = "";
    /**
     * Local Port
     */
    private Integer local_port = null;
    /**
     * Remote Primary Ip
     */
    private String remote_primary_ip = "";
    /**
     * Remote Secondary Ip
     */
    private String remote_secondary_ip = "";
    /**
     * Remote Port
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
     * SCTP_TW_TIMER
     */
    private Integer sctp_tw_timer = null;
    /**
     * SCTP_HB_INTERVAL
     */
    private Integer sctp_hb_interval = null;
    /**
     * SCTP_ASSOC_MAX_RETRANS
     */
    private Integer sctp_assoc_max_retrans = null;
    /**
     * SCTP_RTO_INIT
     */
    private Integer sctp_rto_init = null;
    /**
     * SCTP_RTO_MIN
     */
    private Integer sctp_rto_min = null;
    /**
     * SCTP_RTO_MAX
     */
    private Integer sctp_rto_max = null;
    /**
     * SCTP_PATH_MAX_RETRANS
     */
    private Integer sctp_path_max_retrans = null;
    /**
     * SCTP_MAX_INIT_RETRANS
     */
    private Integer sctp_max_init_retrans = null;
    /**
     * SCTP_MAX_SEG
     */
    private Integer sctp_max_seg = null;
    /**
     * SCTP_SACK_TIMEOUT
     */
    private Integer sctp_sack_timeout = null;
    /**
     * 담당
     */
    private String contact_name = "";
    /**
     * 이메일
     */
    private String contact_email = "";
    /**
     * Noc
     */
    private String contact_noc = "";
    /**
     * 담당 전화
     */
    private String contact_fix_phone = "";
    /**
     * 핸드폰
     */
    private String contact_mobile_phone = "";
    /**
     * 벤더
     */
    private String vendor_name = "";
    /**
     * 개통일
     */
    private String open_dt = "";
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
     * Locality Nm 설정
     *
     * @param locality_nm Locality Nm
     */
    public void setLocality_nm(final String locality_nm) {
        this.locality_nm = locality_nm;
    }

    /**
     * Locality Nm 취득
     *
     * @return locality_nm Locality Nm
     */
    public String getLocality_nm() {
        return StringUtils.defaultString(this.locality_nm);
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
     * 사업자 설정
     *
     * @param carrier_nm 사업자
     */
    public void setCarrier_nm(final String carrier_nm) {
        this.carrier_nm = carrier_nm;
    }

    /**
     * 사업자 취득
     *
     * @return carrier_nm 사업자
     */
    public String getCarrier_nm() {
        return StringUtils.defaultString(this.carrier_nm);
    }

    /**
     * 국소 설정
     *
     * @param local 국소
     */
    public void setLocal(final String local) {
        this.local = local;
    }

    /**
     * 국소 취득
     *
     * @return local 국소
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
     * @param host Host
     */
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * Host 취득
     *
     * @return host Host
     */
    public String getHost() {
        return StringUtils.defaultString(this.host);
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
     * Local Slot 설정
     *
     * @param local_slot Local Slot
     */
    public void setLocal_slot(final String local_slot) {
        this.local_slot = local_slot;
    }

    /**
     * Local Slot 취득
     *
     * @return local_slot Local Slot
     */
    public String getLocal_slot() {
        return StringUtils.defaultString(this.local_slot);
    }

    /**
     * Local Primary Ip 설정
     *
     * @param local_primary_ip Local Primary Ip
     */
    public void setLocal_primary_ip(final String local_primary_ip) {
        this.local_primary_ip = local_primary_ip;
    }

    /**
     * Local Primary Ip 취득
     *
     * @return local_primary_ip Local Primary Ip
     */
    public String getLocal_primary_ip() {
        return StringUtils.defaultString(this.local_primary_ip);
    }

    /**
     * Local Secondary Ip 설정
     *
     * @param local_secondary_ip Local Secondary Ip
     */
    public void setLocal_secondary_ip(final String local_secondary_ip) {
        this.local_secondary_ip = local_secondary_ip;
    }

    /**
     * Local Secondary Ip 취득
     *
     * @return local_secondary_ip Local Secondary Ip
     */
    public String getLocal_secondary_ip() {
        return StringUtils.defaultString(this.local_secondary_ip);
    }

    /**
     * Local Port 설정
     *
     * @param local_port Local Port
     */
    public void setLocal_port(final Integer local_port) {
        this.local_port = local_port;
    }

    /**
     * Local Port 취득
     *
     * @return local_port Local Port
     */
    public Integer getLocal_port() {
        return this.local_port;
    }

    /**
     * Remote Primary Ip 설정
     *
     * @param remote_primary_ip Remote Primary Ip
     */
    public void setRemote_primary_ip(final String remote_primary_ip) {
        this.remote_primary_ip = remote_primary_ip;
    }

    /**
     * Remote Primary Ip 취득
     *
     * @return remote_primary_ip Remote Primary Ip
     */
    public String getRemote_primary_ip() {
        return StringUtils.defaultString(this.remote_primary_ip);
    }

    /**
     * Remote Secondary Ip 설정
     *
     * @param remote_secondary_ip Remote Secondary Ip
     */
    public void setRemote_secondary_ip(final String remote_secondary_ip) {
        this.remote_secondary_ip = remote_secondary_ip;
    }

    /**
     * Remote Secondary Ip 취득
     *
     * @return remote_secondary_ip Remote Secondary Ip
     */
    public String getRemote_secondary_ip() {
        return StringUtils.defaultString(this.remote_secondary_ip);
    }

    /**
     * Remote Port 설정
     *
     * @param remote_port Remote Port
     */
    public void setRemote_port(final Integer remote_port) {
        this.remote_port = remote_port;
    }

    /**
     * Remote Port 취득
     *
     * @return remote_port Remote Port
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
     * SCTP_TW_TIMER 설정
     *
     * @param sctp_tw_timer SCTP_TW_TIMER
     */
    public void setSctp_tw_timer(final Integer sctp_tw_timer) {
        this.sctp_tw_timer = sctp_tw_timer;
    }

    /**
     * SCTP_TW_TIMER 취득
     *
     * @return sctp_tw_timer SCTP_TW_TIMER
     */
    public Integer getSctp_tw_timer() {
        return this.sctp_tw_timer;
    }

    /**
     * SCTP_HB_INTERVAL 설정
     *
     * @param sctp_hb_interval SCTP_HB_INTERVAL
     */
    public void setSctp_hb_interval(final Integer sctp_hb_interval) {
        this.sctp_hb_interval = sctp_hb_interval;
    }

    /**
     * SCTP_HB_INTERVAL 취득
     *
     * @return sctp_hb_interval SCTP_HB_INTERVAL
     */
    public Integer getSctp_hb_interval() {
        return this.sctp_hb_interval;
    }

    /**
     * SCTP_ASSOC_MAX_RETRANS 설정
     *
     * @param sctp_assoc_max_retrans SCTP_ASSOC_MAX_RETRANS
     */
    public void setSctp_assoc_max_retrans(final Integer sctp_assoc_max_retrans) {
        this.sctp_assoc_max_retrans = sctp_assoc_max_retrans;
    }

    /**
     * SCTP_ASSOC_MAX_RETRANS 취득
     *
     * @return sctp_assoc_max_retrans SCTP_ASSOC_MAX_RETRANS
     */
    public Integer getSctp_assoc_max_retrans() {
        return this.sctp_assoc_max_retrans;
    }

    /**
     * SCTP_RTO_INIT 설정
     *
     * @param sctp_rto_init SCTP_RTO_INIT
     */
    public void setSctp_rto_init(final Integer sctp_rto_init) {
        this.sctp_rto_init = sctp_rto_init;
    }

    /**
     * SCTP_RTO_INIT 취득
     *
     * @return sctp_rto_init SCTP_RTO_INIT
     */
    public Integer getSctp_rto_init() {
        return this.sctp_rto_init;
    }

    /**
     * SCTP_RTO_MIN 설정
     *
     * @param sctp_rto_min SCTP_RTO_MIN
     */
    public void setSctp_rto_min(final Integer sctp_rto_min) {
        this.sctp_rto_min = sctp_rto_min;
    }

    /**
     * SCTP_RTO_MIN 취득
     *
     * @return sctp_rto_min SCTP_RTO_MIN
     */
    public Integer getSctp_rto_min() {
        return this.sctp_rto_min;
    }

    /**
     * SCTP_RTO_MAX 설정
     *
     * @param sctp_rto_max SCTP_RTO_MAX
     */
    public void setSctp_rto_max(final Integer sctp_rto_max) {
        this.sctp_rto_max = sctp_rto_max;
    }

    /**
     * SCTP_RTO_MAX 취득
     *
     * @return sctp_rto_max SCTP_RTO_MAX
     */
    public Integer getSctp_rto_max() {
        return this.sctp_rto_max;
    }

    /**
     * SCTP_PATH_MAX_RETRANS 설정
     *
     * @param sctp_path_max_retrans SCTP_PATH_MAX_RETRANS
     */
    public void setSctp_path_max_retrans(final Integer sctp_path_max_retrans) {
        this.sctp_path_max_retrans = sctp_path_max_retrans;
    }

    /**
     * SCTP_PATH_MAX_RETRANS 취득
     *
     * @return sctp_path_max_retrans SCTP_PATH_MAX_RETRANS
     */
    public Integer getSctp_path_max_retrans() {
        return this.sctp_path_max_retrans;
    }

    /**
     * SCTP_MAX_INIT_RETRANS 설정
     *
     * @param sctp_max_init_retrans SCTP_MAX_INIT_RETRANS
     */
    public void setSctp_max_init_retrans(final Integer sctp_max_init_retrans) {
        this.sctp_max_init_retrans = sctp_max_init_retrans;
    }

    /**
     * SCTP_MAX_INIT_RETRANS 취득
     *
     * @return sctp_max_init_retrans SCTP_MAX_INIT_RETRANS
     */
    public Integer getSctp_max_init_retrans() {
        return this.sctp_max_init_retrans;
    }

    /**
     * SCTP_MAX_SEG 설정
     *
     * @param sctp_max_seg SCTP_MAX_SEG
     */
    public void setSctp_max_seg(final Integer sctp_max_seg) {
        this.sctp_max_seg = sctp_max_seg;
    }

    /**
     * SCTP_MAX_SEG 취득
     *
     * @return sctp_max_seg SCTP_MAX_SEG
     */
    public Integer getSctp_max_seg() {
        return this.sctp_max_seg;
    }

    /**
     * SCTP_SACK_TIMEOUT 설정
     *
     * @param sctp_sack_timeout SCTP_SACK_TIMEOUT
     */
    public void setSctp_sack_timeout(final Integer sctp_sack_timeout) {
        this.sctp_sack_timeout = sctp_sack_timeout;
    }

    /**
     * SCTP_SACK_TIMEOUT 취득
     *
     * @return sctp_sack_timeout SCTP_SACK_TIMEOUT
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
     * 이메일 설정
     *
     * @param contact_email 이메일
     */
    public void setContact_email(final String contact_email) {
        this.contact_email = contact_email;
    }

    /**
     * 이메일 취득
     *
     * @return contact_email 이메일
     */
    public String getContact_email() {
        return StringUtils.defaultString(this.contact_email);
    }

    /**
     * Noc 설정
     *
     * @param contact_noc Noc
     */
    public void setContact_noc(final String contact_noc) {
        this.contact_noc = contact_noc;
    }

    /**
     * Noc 취득
     *
     * @return contact_noc Noc
     */
    public String getContact_noc() {
        return StringUtils.defaultString(this.contact_noc);
    }

    /**
     * 담당 전화 설정
     *
     * @param contact_fix_phone 담당 전화
     */
    public void setContact_fix_phone(final String contact_fix_phone) {
        this.contact_fix_phone = contact_fix_phone;
    }

    /**
     * 담당 전화 취득
     *
     * @return contact_fix_phone 담당 전화
     */
    public String getContact_fix_phone() {
        return StringUtils.defaultString(this.contact_fix_phone);
    }

    /**
     * 핸드폰 설정
     *
     * @param contact_mobile_phone 핸드폰
     */
    public void setContact_mobile_phone(final String contact_mobile_phone) {
        this.contact_mobile_phone = contact_mobile_phone;
    }

    /**
     * 핸드폰 취득
     *
     * @return contact_mobile_phone 핸드폰
     */
    public String getContact_mobile_phone() {
        return StringUtils.defaultString(this.contact_mobile_phone);
    }

    /**
     * 벤더 설정
     *
     * @param vendor_name 벤더
     */
    public void setVendor_name(final String vendor_name) {
        this.vendor_name = vendor_name;
    }

    /**
     * 벤더 취득
     *
     * @return vendor_name 벤더
     */
    public String getVendor_name() {
        return StringUtils.defaultString(this.vendor_name);
    }

    /**
     * 개통일 설정
     *
     * @param open_dt 개통일
     */
    public void setOpen_dt(final String open_dt) {
        this.open_dt = open_dt;
    }

    /**
     * 개통일 취득
     *
     * @return open_dt 개통일
     */
    public String getOpen_dt() {
        return StringUtils.defaultString(this.open_dt);
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
     * 엑셀
     */
    private String excel_file = "";

    /**
     * 엑셀 설정
     *
     * @param excel_file 엑셀
     */
    public void setExcel_file(final String excel_file) {
        this.excel_file = excel_file;
    }

    /**
     * 엑셀 취득
     *
     * @return excel_file 엑셀
     */
    public String getExcel_file() {
        return StringUtils.defaultString(this.excel_file);
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