/*****************************************************************************
 * 프로그램명  :  DiameterRealmServiceImpl.java
 * 설     명  : Diameter Realm 관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.28   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.diameterrealm;

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
 * Diameter Realm 관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class DiameterRealmService {
    @Resource
    private DiameterRealmDAO diameterRealmDAO;

    /**
     * DiameterRealm 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Diameter 관리 리스트
     */

    public PagingUtil getDiameterRealmInfoList(final DiameterRealmSearchInfo searchInfo) throws UserSysException {
        if (searchInfo.getJqFilters() != null && !searchInfo.getJqFilters().isJsonNull() && searchInfo.getJqFilters().get("rules") != null) {
            final JsonArray arr = searchInfo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("locality")) {
                        searchInfo.setSrch_locality(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country")) {
                        searchInfo.setCountry(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("routing_peer")) {
                        searchInfo.setRouting_peer(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("realm")) {
                        searchInfo.setSrch_realm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_email")) {
                        searchInfo.setContact_email(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("dsbd_yn")) {
                        searchInfo.setDsbd_yn(jo.get("data").getAsString());
                    }
                }
            }
        }
        return new PagingUtil(this.diameterRealmDAO.getDiameterRealmInfoListCount(searchInfo), this.diameterRealmDAO.getDiameterRealmInfoList(searchInfo));
    }

    /**
     * DiameterRealm 정보 등록처리
     *
     * @param info DiameterRealm 정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertDiameterRealm(final DiameterRealmInfo info) throws UserSysException {
        return this.diameterRealmDAO.insertDiameterRealm(info);
    }

    /**
     * DiameterRealm 상세 정보
     *
     * @param sbcId 검색DiameterRealm
     * @return Realm DiameterRealm 관리 상세보기
     */

    public DiameterRealmInfo getDiameterRealmDetail(final DiameterRealmInfo info) throws UserSysException {
        return this.diameterRealmDAO.getDiameterRealmDetail(info);
    }

    /**
     * DiameterRealm 정보 수정처리
     *
     * @param DiameterRealmInfo 수정할 DiameterRealm
     */

    @Transactional
    public ProcessResult updateDiameterRealm(final DiameterRealmInfo sbcInfo) throws UserSysException {
        return this.diameterRealmDAO.updateDiameterRealm(sbcInfo);
    }

    /**
     * DiameterRealm 정보 삭제처리
     *
     * @param DiameterRealmInfo 삭제할DiameterRealm
     */

    @Transactional
    public ProcessResult deleteDiameterRealm(final DiameterRealmInfo info) throws UserSysException {
        return this.diameterRealmDAO.deleteDiameterRealm(info);
    }

    /**
     * DiameterRealm 존재 체크
     *
     * @param DiameterRealm
     * @return boolean (true:DiameterRealm가 존재하지 않을때,false:DiameterRealm가 존재할때)
     */

    public String getDiameterRealmExist(final String diameter) throws UserSysException {
        return "1".equals(this.diameterRealmDAO.getDiameterRealmExist(diameter)) ? "NG" : "OK";
    }

}
