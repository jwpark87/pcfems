/*****************************************************************************
 * 프로그램명  :  LinksetServiceImpl.java
 * 설     명  : linkset 현황  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.27   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.linkset;

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
 * Linkset 현황 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class LinksetService {
    @Resource
    private LinksetDAO linksetDAO;

    /**
     * Linkset 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Linkset 현황 리스트
     */

    public PagingUtil getLinksetInfoList(final LinksetSearchInfo searchInfo) throws UserSysException {
        if (searchInfo.getJqFilters() != null && !searchInfo.getJqFilters().isJsonNull() && searchInfo.getJqFilters().get("rules") != null) {
            final JsonArray arr = searchInfo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("locality")) {
                        searchInfo.setSrch_locality(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("na")) {
                        searchInfo.setNa(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("apc")) {
                        searchInfo.setSrch_apc(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country")) {
                        searchInfo.setCountry(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("clli")) {
                        searchInfo.setClli(jo.get("data").getAsString());
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

        return new PagingUtil(this.linksetDAO.getLinksetListCnt(searchInfo), this.linksetDAO.getLinksetInfoList(searchInfo));
    }

    /**
     * Linkset 정보 등록처리
     *
     * @param info Linkset 정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertLinkset(final LinksetInfo info) throws UserSysException {
        return this.linksetDAO.insertLinkset(info);
    }

    /**
     * Linkset 상세 정보
     *
     * @param LinksetInfo Linkset
     * @return Realm Link set현황 상세보기
     */

    public LinksetInfo getLinksetDetail(final LinksetInfo info) throws UserSysException {
        return this.linksetDAO.getLinksetDetail(info);
    }

    /**
     * Linkset 정보 수정처리
     *
     * @param LinksetInfo 수정할 Linkset
     */

    @Transactional
    public ProcessResult updateLinkset(final LinksetInfo sbcInfo) throws UserSysException {
        return this.linksetDAO.updateLinkset(sbcInfo);
    }

    /**
     * Linkset 정보 삭제처리
     *
     * @param LinksetInfo 삭제할Linkset
     */

    @Transactional
    public ProcessResult deleteLinkset(final LinksetInfo info) throws UserSysException {
        final ProcessResult result = this.linksetDAO.deleteLinkset(info);
        return result;
    }

    /**
     * Linkset 존재 체크
     *
     * @param Linkset
     * @return boolean (true:Linkset가 존재하지 않을때,false:Linkset가 존재할때)
     */

    public String getLinksetExist(final LinksetInfo info) throws UserSysException {
        return "1".equals(this.linksetDAO.getLinksetExist(info)) ? "NG" : "OK";
    }

}
