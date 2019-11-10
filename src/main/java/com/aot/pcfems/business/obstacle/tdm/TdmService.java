/*****************************************************************************
 * 프로그램명  :  TdmServiceImpl.java
 * 설     명  : Tdm 현황  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.tdm;

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
 * Tdm 현황 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class TdmService {
    @Resource
    private TdmDAO tdmDAO;

    /**
     * Tdm 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Linkset 현황 리스트
     */

    public PagingUtil getTdmInfoList(final TdmSearchInfo searchInfo) throws UserSysException {
        if (searchInfo.getJqFilters() != null && !searchInfo.getJqFilters().isJsonNull() && searchInfo.getJqFilters().get("rules") != null) {
            final JsonArray arr = searchInfo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("sortseq")) {
                        searchInfo.setSrch_sortseq(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("locality")) {
                        searchInfo.setLocality(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("gnoc_tie_nm")) {
                        searchInfo.setGnoc_tie_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("ts")) {
                        searchInfo.setTs(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("gnoc_tie_dsx_dcsa_no")) {
                        searchInfo.setGnoc_tie_dsx_dcsa_no(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("stp_tie")) {
                        searchInfo.setStp_tie(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("gnoc_tie_ndcs")) {
                        searchInfo.setGnoc_tie_ndcs(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("shelf")) {
                        searchInfo.setShelf(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("slot")) {
                        searchInfo.setSlot(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("e1_port")) {
                        searchInfo.setE1_port(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("time_slot")) {
                        searchInfo.setTime_slot(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("ch_number")) {
                        searchInfo.setCh_number(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("mapping")) {
                        searchInfo.setMapping(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("crc")) {
                        searchInfo.setCrc(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("use_status")) {
                        searchInfo.setUse_status(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("loc_bound")) {
                        searchInfo.setLoc_bound(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("na")) {
                        searchInfo.setNa(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("apc")) {
                        searchInfo.setApc(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("slc")) {
                        searchInfo.setSlc(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("error_conn")) {
                        searchInfo.setError_conn(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country_nm")) {
                        searchInfo.setCountry_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("clli")) {
                        searchInfo.setClli(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("bearer")) {
                        searchInfo.setBearer(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("bearer_time_slot")) {
                        searchInfo.setBearer_time_slot(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("cable")) {
                        searchInfo.setCable(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("remark1")) {
                        searchInfo.setRemark1(jo.get("data").getAsString());
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

        return new PagingUtil(this.tdmDAO.getTdmListCnt(searchInfo), this.tdmDAO.getTdmInfoList(searchInfo));
    }

    /**
     * Tdm 정보 등록처리
     *
     * @param info Tdm 정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertTdm(final TdmInfo info) throws UserSysException {
        return this.tdmDAO.insertTdm(info);
    }

    /**
     * Tdm 상세 정보
     *
     * @param TdmInfo
     * @return Tdm 현황 상세보기
     */

    public TdmInfo getTdmDetail(final TdmInfo info) throws UserSysException {
        return this.tdmDAO.getTdmDetail(info);
    }

    /**
     * Tdm 정보 수정처리
     *
     * @param TdmInfo 수정할 Tdm
     */

    @Transactional
    public ProcessResult updateTdm(final TdmInfo info) throws UserSysException {
        return this.tdmDAO.updateTdm(info);
    }

    /**
     * Tdm 정보 삭제처리
     *
     * @param TdmInfo 삭제할Tdm
     */

    @Transactional
    public ProcessResult deleteTdm(final TdmInfo info) throws UserSysException {
        return this.tdmDAO.deleteTdm(info);
    }

    /**
     * Tdm 존재 체크
     *
     * @param Tdm
     * @return String
     */

    public String getTdmExist(final TdmInfo info) throws UserSysException {
        return "1".equals(this.tdmDAO.getTdmExist(info)) ? "NG" : "OK";
    }

}
