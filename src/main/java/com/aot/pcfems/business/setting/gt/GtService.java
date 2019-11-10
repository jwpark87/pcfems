/*****************************************************************************
 * 프로그램명  :  GtServiceImpl.java
 * 설     명  : Gt 관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.gt;

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
 * Gt 관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class GtService {
    @Resource
    private GtDAO gtDAO;

    /**
     * Gt 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Gt 관리 리스트
     */

    public PagingUtil getGtInfoList(final GtSearchInfo searchInfo) throws UserSysException {
        if (searchInfo.getJqFilters() != null && !searchInfo.getJqFilters().isJsonNull() && searchInfo.getJqFilters().get("rules") != null) {
            final JsonArray arr = searchInfo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("db_name")) {
                        searchInfo.setDb_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("gt")) {
                        searchInfo.setSrch_gt(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("pc_list_nm")) {
                        searchInfo.setPc_list_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country")) {
                        searchInfo.setCountry(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("dsbd_yn")) {
                        searchInfo.setDsbd_yn(jo.get("data").getAsString());
                    }
                }
            }
        }
        return new PagingUtil(this.gtDAO.getGtListCnt(searchInfo), this.gtDAO.getGtInfoList(searchInfo));
    }

    /**
     * Gt 정보 등록처리
     *
     * @param info Gt사업자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertGt(final GtInfo info) throws UserSysException {
        return this.gtDAO.insertGt(info);
    }

    /**
     * Gt 상세 정보
     *
     * @param sbcId 검색Gt 아이디
     * @return Realm Gt 관리 상세보기
     */

    public GtInfo getGtDetail(final GtInfo info) throws UserSysException {
        return this.gtDAO.getGtDetail(info);
    }

    /**
     * Gt 정보 수정처리
     *
     * @param GtInfo 수정할 Gt
     */

    @Transactional
    public ProcessResult updateGt(final GtInfo sbcInfo) throws UserSysException {
        return this.gtDAO.updateGt(sbcInfo);
    }

    /**
     * Gt 정보 삭제처리
     *
     * @param GtInfo 삭제할Gt 아이디
     */

    @Transactional
    public ProcessResult deleteGt(final GtInfo info) throws UserSysException {
        return this.gtDAO.deleteGt(info);
    }

    /**
     * Gt 존재 체크
     *
     * @param Gt
     * @return boolean (true:Gt가 존재하지 않을때,false:Gt가 존재할때)
     */

    public String getGtExist(final String gt) throws UserSysException {
        return "1".equals(this.gtDAO.getGtExist(gt)) ? "NG" : "OK";
    }

}
