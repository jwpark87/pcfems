/*****************************************************************************
 * 프로그램명  :  DiameterServiceImpl.java
 * 설     명  : Diameter Host 관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameter;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.util.PagingUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Diameter Host 관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class DiameterService {
    @Resource
    private DiameterDAO diameterDAO;

    /**
     * Diameter 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Diameter 관리 리스트
     */

    public PagingUtil getDiameterInfoList(final DiameterSearchInfo searchInfo) throws UserSysException {
        if (searchInfo.getJqFilters() != null && !searchInfo.getJqFilters().isJsonNull() && searchInfo.getJqFilters().get("rules") != null) {
            final JsonArray arr = searchInfo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("locality")) {
                        searchInfo.setSrch_locality(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country_nm")) {
                        searchInfo.setCountry_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local")) {
                        searchInfo.setLocal(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("realm")) {
                        searchInfo.setRealm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("host")) {
                        searchInfo.setSrch_host(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("remote_peer_mode")) {
                        searchInfo.setRemote_peer_mode(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local_slot")) {
                        searchInfo.setLocal_slot(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local_primary_ip")) {
                        searchInfo.setLocal_primary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local_secondary_ip")) {
                        searchInfo.setLocal_secondary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local_port")) {
                        searchInfo.setLocal_port(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("remote_primary_ip")) {
                        searchInfo.setRemote_primary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("remote_secondary_ip")) {
                        searchInfo.setRemote_secondary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("remote_port")) {
                        searchInfo.setRemote_port(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("access_ctrl_list")) {
                        searchInfo.setAccess_ctrl_list(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("strInterface")) {
                        searchInfo.setStrInterface(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("sctp_tw_timer")) {
                        searchInfo.setSctp_tw_timer(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_hb_interval")) {
                        searchInfo.setSctp_hb_interval(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_assoc_max_retrans")) {
                        searchInfo.setSctp_assoc_max_retrans(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_rto_init")) {
                        searchInfo.setSctp_rto_init(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_rto_min")) {
                        searchInfo.setSctp_rto_min(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_rto_max")) {
                        searchInfo.setSctp_rto_max(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_path_max_retrans")) {
                        searchInfo.setSctp_path_max_retrans(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_max_init_retrans")) {
                        searchInfo.setSctp_max_init_retrans(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_max_seg")) {
                        searchInfo.setSctp_max_seg(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("sctp_sack_timeout")) {
                        searchInfo.setSctp_sack_timeout(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("contact_name")) {
                        searchInfo.setContact_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_email")) {
                        searchInfo.setContact_email(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_noc")) {
                        searchInfo.setContact_noc(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_fix_phone")) {
                        searchInfo.setContact_fix_phone(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_mobile_phone")) {
                        searchInfo.setContact_mobile_phone(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("vendor_name")) {
                        searchInfo.setVendor_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("status")) {
                        searchInfo.setStatus(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("dsbd_yn")) {
                        searchInfo.setDsbd_yn(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.diameterDAO.getDiameterInfoListCount(searchInfo), this.diameterDAO.getDiameterInfoList(searchInfo));
    }

    /**
     * Diameter 정보 등록처리
     *
     * @param info Diameter사업자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertDiameter(final DiameterInfo info) throws UserSysException {
        return this.diameterDAO.insertDiameter(info);
    }

    /**
     * Diameter 상세 정보
     *
     * @param sbcId 검색Diameter 아이디
     * @return Realm Diameter 관리 상세보기
     */

    public DiameterInfo getDiameterDetail(final DiameterInfo info) throws UserSysException {
        return this.diameterDAO.getDiameterDetail(info);
    }

    /**
     * Diameter 정보 수정처리
     *
     * @param DiameterInfo 수정할 Diameter
     */

    @Transactional
    public ProcessResult updateDiameter(final DiameterInfo sbcInfo) throws UserSysException {
        return this.diameterDAO.updateDiameter(sbcInfo);
    }

    /**
     * Diameter 정보 삭제처리
     *
     * @param DiameterInfo 삭제할Diameter 아이디
     */

    @Transactional
    public ProcessResult deleteDiameter(final DiameterInfo info) throws UserSysException {
        return this.diameterDAO.deleteDiameter(info);
    }

    /**
     * Diameter 존재 체크
     *
     * @param Diameter
     * @return boolean (true:Diameter가 존재하지 않을때,false:Diameter가 존재할때)
     */

    public String getDiameterExist(final String diameter) throws UserSysException {
        return "1".equals(this.diameterDAO.getDiameterExist(diameter)) ? "NG" : "OK";
    }

}
