/*****************************************************************************
 * 프로그램명  :  RoutesetServiceImpl.java
 * 설     명  : Routeset 현황  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.routeset;

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
 * Routeset 현황 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class RoutesetService {
    @Resource
    private RoutesetDAO routesetDAO;

    /**
     * Routeset 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Routeset 현황 리스트
     */

    public PagingUtil getRoutesetInfoList(final RoutesetSearchInfo searchInfo) throws UserSysException {
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
                    if (jo.get("field").getAsString().equals("clli")) {
                        searchInfo.setClli(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country")) {
                        searchInfo.setCountry(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("local")) {
                        searchInfo.setLocal(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("status")) {
                        searchInfo.setStatus(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.routesetDAO.getRoutesetInfoListCount(searchInfo), this.routesetDAO.getRoutesetInfoList(searchInfo));
    }

    /**
     * Routeset 정보 등록처리
     *
     * @param info Routeset사업자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertRouteset(final RoutesetInfo info) throws UserSysException {
        return this.routesetDAO.insertRouteset(info);
    }

    /**
     * Routeset 상세 정보
     *
     * @param sbcId 검색Routeset 아이디
     * @return Realm Routeset 현황 상세보기
     */

    public RoutesetInfo getRoutesetDetail(final RoutesetInfo info) throws UserSysException {
        return this.routesetDAO.getRoutesetDetail(info);
    }

    /**
     * Routeset 정보 수정처리
     *
     * @param RoutsetInfo 수정할 Routeset
     */

    @Transactional
    public ProcessResult updateRouteset(final RoutesetInfo sbcInfo) throws UserSysException {
        return this.routesetDAO.updateRouteset(sbcInfo);
    }

    /**
     * Routeset 정보 삭제처리
     *
     * @param RoutsetInfo 삭제할Routeset 아이디
     */

    @Transactional
    public ProcessResult deleteRouteset(final RoutesetInfo info) throws UserSysException {
        return this.routesetDAO.deleteRouteset(info);
    }

    /**
     * Routeset 존재 체크
     *
     * @param Routeset
     * @return boolean (true:Routeset가 존재하지 않을때,false:Routeset가 존재할때)
     */

    public String getRoutesetExist(final String gt) throws UserSysException {
        return "1".equals(this.routesetDAO.getRoutesetExist(gt)) ? "NG" : "OK";
    }

}
