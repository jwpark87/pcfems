/*****************************************************************************
 * 프로그램명  :  RealmServiceImpl.java
 * 설     명  : REALM사업자정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.realm;

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
 * REALM사업자정보관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class RealmService {
    @Resource
    private RealmDAO realmDAO;

    /**
     * REALM사업자정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil REALM사업자정보관리 리스트
     */

    public PagingUtil getRealmInfoList(final RealmSearchInfo vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();

                    if (jo.get("field").getAsString().equals("realm")) {
                        vo.setSrch_realm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("realm_cd")) {
                        vo.setSrch_realm_cd(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("realm_carrier_name")) {
                        vo.setSrch_realm_carrier_name(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.realmDAO.getRealmInfoListCount(vo), this.realmDAO.getRealmInfoList(vo));
    }

    /**
     * REALM사업자정보 등록처리
     *
     * @param info REALM사업자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertRealm(final RealmInfo info) throws UserSysException {
        return this.realmDAO.insertRealm(info);
    }

    /**
     * REALM사업자정보 상세 정보
     *
     * @param sbcId 검색REALM사업자아이디
     * @return Realm REALM사업자정보관리 상세보기
     */

    public RealmInfo getRealmDetail(final RealmInfo info) throws UserSysException {
        return this.realmDAO.getRealmDetail(info);
    }

    /**
     * REALM사업자정보 수정처리
     *
     * @param sbcInfo 수정할 REALM사업자정보
     */

    @Transactional
    public ProcessResult updateRealm(final RealmInfo sbcInfo) throws UserSysException {
        return this.realmDAO.updateRealm(sbcInfo);
    }

    /**
     * REALM사업자정보 삭제처리
     *
     * @param sbcInfo 삭제할REALM사업자아이디
     */

    @Transactional
    public ProcessResult deleteRealm(final String sbcId) throws UserSysException {
        return this.realmDAO.deleteRealm(sbcId);
    }

    /**
     * Realm 존재 체크
     *
     * @param realm SBC Node Name
     * @return boolean (true:realm가 존재하지 않을때,false:realm가 존재할때)
     */

    public String getRealmExist(final String realm) throws UserSysException {
        return "1".equals(this.realmDAO.getRealmExist(realm)) ? "NG" : "OK";
    }

}
