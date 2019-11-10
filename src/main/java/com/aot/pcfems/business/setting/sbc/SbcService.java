/*****************************************************************************
 * 프로그램명  :  SbcServiceImpl.java
 * 설     명  : SBC설정정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.sbc;

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
 * SBC설정정보관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class SbcService {
    @Resource
    private SbcDAO sbcDAO;

    /**
     * SBC설정정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil SBC설정정보관리 리스트
     */

    public PagingUtil getSbcInfoList(final SbcSearchInfo vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();

                    if (jo.get("field").getAsString().equals("sbc_node_name")) {
                        vo.setSrch_sbc_node_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("sbc_node_ip")) {
                        vo.setSrch_sbc_node_ip(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("sbc_group_name")) {
                        vo.setSrch_sbc_group_name(jo.get("data").getAsString());
                    }
                }
            }
        }

        return new PagingUtil(this.sbcDAO.getSbcListCnt(vo), this.sbcDAO.getSbcInfoList(vo));
    }

    /**
     * SBC설정정보 등록처리
     *
     * @param info SBC설정정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertSbc(final SbcInfo info) throws UserSysException {
        return this.sbcDAO.insertSbc(info);
    }

    /**
     * SBC설정정보 상세 정보
     *
     * @param sbcId 검색SBC설정아이디
     * @return Sbc SBC설정정보관리 상세보기
     */

    public SbcInfo getSbcDetail(final SbcInfo info) throws UserSysException {
        return this.sbcDAO.getSbcDetail(info);
    }

    /**
     * SBC설정정보 수정처리
     *
     * @param sbcInfo 수정할 SBC설정정보
     */

    @Transactional
    public ProcessResult updateSbc(final SbcInfo sbcInfo) throws UserSysException {
        return this.sbcDAO.updateSbc(sbcInfo);
    }

    /**
     * SBC설정정보 삭제처리
     *
     * @param sbcInfo 삭제할SBC설정아이디
     */

    @Transactional
    public ProcessResult deleteSbc(final String sbcId) throws UserSysException {
        return this.sbcDAO.deleteSbc(sbcId);
    }

    /**
     * SBC Node Name 존재 체크
     *
     * @param sbc_node_name SBC Node Name
     * @return boolean (true:SBC Node Name이 존재하지 않을때,false:SBC Node Name이 존재할때)
     */

    public String getSbcNodeNameExist(final String sbc_node_name) throws UserSysException {
        return "1".equals(this.sbcDAO.getSbcNodeNameExist(sbc_node_name)) ? "NG" : "OK";
    }

    /**
     * SBC Node Ip 존재 체크
     *
     * @param sbc_node_ip SBC Node Ip
     * @return boolean (true:SBC Node Ip가 존재하지 않을때,false:SBC Node Ip가 존재할때)
     */

    public String getSbcNodeIpExist(final String sbc_node_ip) throws UserSysException {
        return "1".equals(this.sbcDAO.getSbcNodeIpExist(sbc_node_ip)) ? "NG" : "OK";
    }
}
