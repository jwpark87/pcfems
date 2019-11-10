/*****************************************************************************
 * 프로그램명  :  CountryServiceImpl.java
 * 설     명  : COUNTRY설정정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.country;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * COUNTRY설정정보관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class CountryService {
    @Resource
    private CountryDAO countryDAO;

    /**
     * COUNTRY설정정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil COUNTRY설정정보관리 리스트
     */

    public PagingUtil getCountryInfoList(final CountrySearchInfo vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();

                    if (jo.get("field").getAsString().equals("prefix")) {
                        vo.setSrch_prefix(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country_cd")) {
                        vo.setSrch_country_cd(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("country_cd_name")) {
                        vo.setSrch_country_cd_name(jo.get("data").getAsString());
                    }
                }
            }
        }
        return new PagingUtil(this.countryDAO.getCountryListCnt(vo), this.countryDAO.getCountryInfoList(vo));
    }

    /**
     * COUNTRY설정정보 등록처리
     *
     * @param info COUNTRY설정정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertCountry(final CountryInfo info) throws UserSysException {
        return this.countryDAO.insertCountry(info);
    }

    /**
     * COUNTRY설정정보 상세 정보
     *
     * @param sbcId 검색COUNTRY설정아이디
     * @return Country COUNTRY설정정보관리 상세보기
     */

    public CountryInfo getCountryDetail(final CountryInfo info) throws UserSysException {
        return this.countryDAO.getCountryDetail(info);
    }

    /**
     * COUNTRY설정정보 수정처리
     *
     * @param sbcInfo 수정할 COUNTRY설정정보
     */

    @Transactional
    public ProcessResult updateCountry(final CountryInfo sbcInfo) throws UserSysException {
        return this.countryDAO.updateCountry(sbcInfo);
    }

    /**
     * COUNTRY설정정보 삭제처리
     *
     * @param sbcInfo 삭제할COUNTRY설정아이디
     */

    @Transactional
    public ProcessResult deleteCountry(final String sbcId) throws UserSysException {
        return this.countryDAO.deleteCountry(sbcId);
    }

    /**
     * Prefix 존재 체크
     *
     * @param prefix SBC Node Name
     * @return boolean (true:prefix가 존재하지 않을때,false:prefix가 존재할때)
     */

    public String getPrefixExist(final String prefix) throws UserSysException {
        return "1".equals(this.countryDAO.getPrefixExist(prefix)) ? "NG" : "OK";
    }

}
