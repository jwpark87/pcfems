/*****************************************************************************
 * 프로그램명  : CdrServiceImpl.java
 * 설     명  : 진행 중 CDR 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.cdr;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 진행 중 CDR 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class CdrService {
    @Resource
    private CdrDAO cdrDAO;

    /**
     * 진행 중 CDR 조회 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil 진행 중 CDR 조회 리스트
     */

    public PagingUtil getCdrIngList(final CdrSearchInfo vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("sbc_group_name")) {
                        vo.setSbc_group_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("h323_setup_time")) {
                        vo.setH323_setup_time(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("h323_connect_time")) {
                        vo.setH323_connect_time(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("calling_number")) {
                        vo.setCalling_number(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("called_number")) {
                        vo.setCalled_number(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("from_country_cd")) {
                        vo.setFrom_country_cd(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("from_country_cd_name")) {
                        vo.setFrom_country_cd_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("to_country_cd")) {
                        vo.setTo_country_cd(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("to_country_cd_name")) {
                        vo.setTo_country_cd_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acct_session_id")) {
                        vo.setAcct_session_id(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("calling_station_id")) {
                        vo.setCalling_station_id(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("called_station_id")) {
                        vo.setCalled_station_id(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("nas_ip_address")) {
                        vo.setNas_ip_address(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("nas_port")) {
                        vo.setNas_port(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_session_ingress_realm")) {
                        vo.setAcme_session_ingress_realm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_session_egress_realm")) {
                        vo.setAcme_session_egress_realm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_session_protocol_type")) {
                        vo.setAcme_session_protocol_type(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_flowtype_fs1_f")) {
                        vo.setAcme_flowtype_fs1_f(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_local_time_zone")) {
                        vo.setAcme_local_time_zone(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_post_dial_delay")) {
                        vo.setAcme_post_dial_delay(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_originating_trunk_group")) {
                        vo.setAcme_originating_trunk_group(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_terminating_trunk_group")) {
                        vo.setAcme_terminating_trunk_group(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_ingress_local_addr")) {
                        vo.setAcme_ingress_local_addr(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_ingress_remote_addr")) {
                        vo.setAcme_ingress_remote_addr(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_egress_local_addr")) {
                        vo.setAcme_egress_local_addr(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acme_egress_remote_addr")) {
                        vo.setAcme_egress_remote_addr(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("client_ip_address")) {
                        vo.setClient_ip_address(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("acct_unique_session_id")) {
                        vo.setAcct_unique_session_id(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.cdrDAO.getCdrIngListCnt(vo), this.cdrDAO.getCdrIngList(vo));
    }
}
