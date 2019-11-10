/*****************************************************************************
 * 프로그램명  :  M3uaServiceImpl.java
 * 설     명  : M2PA/M3UA 현황  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.m3ua;

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
 * M2PA/M3UA 현황 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class M3uaService {
    @Resource
    private M3uaDAO m3uaDAO;

    /**
     * M2PA/M3UA 현황 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Linkset 현황 리스트
     */

    public PagingUtil getM3uaInfoList(final M3uaSearchInfo searchInfo) throws UserSysException {
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
                    if (jo.get("field").getAsString().equals("if_type")) {
                        searchInfo.setIf_type(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country_nm")) {
                        searchInfo.setCountry_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("carrier_nm")) {
                        searchInfo.setCarrier_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("apc")) {
                        searchInfo.setApc(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("asp_id")) {
                        searchInfo.setAsp_id(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("loc_boundary")) {
                        searchInfo.setLoc_boundary(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("na")) {
                        searchInfo.setNa(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("slc")) {
                        searchInfo.setSlc(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("clli")) {
                        searchInfo.setClli(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("routing_context")) {
                        searchInfo.setRouting_context(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("src_primary_ip")) {
                        searchInfo.setSrc_primary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("src_secondary_ip")) {
                        searchInfo.setSrc_secondary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("src_port")) {
                        searchInfo.setSrc_port(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("dst_primary_ip")) {
                        searchInfo.setDst_primary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("dst_secondary_ip")) {
                        searchInfo.setDst_secondary_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("dst_port")) {
                        searchInfo.setDst_port(Integer.parseInt(jo.get("data").toString()));
                    }
                    if (jo.get("field").getAsString().equals("asp_type")) {
                        searchInfo.setAsp_type(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("si")) {
                        searchInfo.setSi(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("routing_cpu")) {
                        searchInfo.setRouting_cpu(jo.get("data").getAsString());
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

        return new PagingUtil(this.m3uaDAO.getM3uaListCnt(searchInfo), this.m3uaDAO.getM3uaInfoList(searchInfo));
    }

    /**
     * M2PA/M3UA 현황 정보 등록처리
     *
     * @param info Tdm 정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertM3ua(final M3uaInfo info) throws UserSysException {
        return this.m3uaDAO.insertM3ua(info);
    }

    /**
     * M2PA/M3UA 현황 상세 정보
     *
     * @param M3uaInfo
     * @return M2PA/M3UA 현황 상세보기
     */

    public M3uaInfo getM3uaDetail(final M3uaInfo info) throws UserSysException {
        return this.m3uaDAO.getM3uaDetail(info);
    }

    /**
     * M2PA/M3UA 현황 정보 수정처리
     *
     * @param M3uaInfo 수정할 Tdm
     */

    @Transactional
    public ProcessResult updateM3ua(final M3uaInfo info) throws UserSysException {
        return this.m3uaDAO.updateM3ua(info);
    }

    /**
     * M2PA/M3UA 현황 정보 삭제처리
     *
     * @param M3uaInfo 삭제할Tdm
     */

    @Transactional
    public ProcessResult deleteM3ua(final M3uaInfo info) throws UserSysException {
        return this.m3uaDAO.deleteM3ua(info);
    }

    /**
     * M2PA/M3UA 현황 존재 체크
     *
     * @param Tdm
     * @return String
     */

    public String getM3uaExist(final M3uaInfo info) throws UserSysException {
        return "1".equals(this.m3uaDAO.getM3uaExist(info)) ? "NG" : "OK";
    }

}
