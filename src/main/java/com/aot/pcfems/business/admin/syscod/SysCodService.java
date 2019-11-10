/*****************************************************************************
 * 프로그램명  : SysCodServiceImpl.java
 * 설     명  : 시스템그룹코드  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       	 or		Version Description
 * ---------- --------- ------- ----------------------------------------------
 * 2009.10.23  Ventus    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.syscod;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 시스템그룹코드관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class SysCodService {
    @Resource
    private SysCodDAO sysCodDAO;

    /**
     * 시스템그룹코드 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @param userInfo   유저정보
     * @return PagingUtil 시스템그룹코드정보리스트
     */

    public PagingUtil getSysCodList(final SysCodVO vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo.get("field").getAsString().equals("grcode")) {
                        vo.setGrcode(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("grcodenm_k")) {
                        vo.setGrcodenm_k(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("grcodenm_e")) {
                        vo.setGrcodenm_e(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("grcodenm_j")) {
                        vo.setGrcodenm_j(jo.get("data").getAsString());
                    }
                }
            }
        }
        return new PagingUtil(this.sysCodDAO.getSysCodCnt(vo), this.sysCodDAO.getSysCodList(vo));
    }

    /**
     * 보안관리>시스템그룹코드의 등록처리
     *
     * @param info     시스템그룹코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertSysCod(final SysCodVO info, final UserVO userInfo) throws BizException, UserSysException {

        ProcessResult result = null;

        final int cnt = this.sysCodDAO.getSysCodExist(info);
        if (cnt == 0) {
            result = this.sysCodDAO.insertSysCod(info);
        } else {
            result = new ProcessResult(this.getClass().getName(), "insertSysCod", 2, AotMessageUtils.getMessage("MSG.SYSCODE.MGMT.ALERT.GRCODE_DUP"));
        }

        return result;
    }

    /**
     * 구성관리>시스템그룹코드 수정처리
     *
     * @param info     시스템그룹코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult updateSysCod(final SysCodVO info, final UserVO userInfo) throws BizException, UserSysException {
        return this.sysCodDAO.updateSysCod(info);
    }

    /**
     * 삭제전 하위 코드 검색
     *
     * @param inParam   환경정보
     * @param dataParam 입력데이타
     * @return int 카운트
     */

    public int detailCodeCount(final SysCodVO info) throws UserSysException {
        return this.sysCodDAO.detailCodeCount(info);
    }

    /**
     * 보안관리>시스템그룹코드의 삭제처리
     *
     * @param info     시스템그룹코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult deleteSysCod(final SysCodVO info, final UserVO userInfo) throws BizException, UserSysException {
        return this.sysCodDAO.deleteSysCod(StringUtil.getCommonPrefix(userInfo), info);
    }

    /**
     * @param SysCodVO 검색데이타
     * @return int 카운트
     */

    public SysCodVO getMaxSortNo(final SysCodVO info) throws UserSysException {
        return this.sysCodDAO.getMaxSortNo(info);
    }

    /********************************** 상세정보 *************************************/
    /**
     * 시스템그룹코드 리스트 취득 - 상세
     *
     * @param searchInfo 검색정보빈
     * @param userInfo   유저정보
     * @return PagingUtil 시스템그룹코드정보리스트- 상세
     */

    public PagingUtil getSysCodDtl(final SysCodVO vo, final UserVO userInfo) throws UserSysException {
        return new PagingUtil(this.sysCodDAO.getSysCodDtlCnt(vo), this.sysCodDAO.getSysCodDtl(vo));
    }

    /**
     * 시스템그룹코드 리스트 취득 - 상세 등록처리
     *
     * @param info     시스템그룹상세코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertSysCodDtl(final SysCodVO info, final UserVO userInfo) throws BizException, UserSysException {

        ProcessResult result = null;

        final int cnt = this.sysCodDAO.getSysCodDtlExist(info);
        if (cnt == 0) {
            result = this.sysCodDAO.insertSysCodDtl(info);
        } else {
            result = new ProcessResult(this.getClass().getName(), "insertSysCodDtl", 2, AotMessageUtils.getMessage("MSG.SYSCODE.MGMT.ALERT.CODE_DUP"));
        }

        return result;
    }

    /**
     * 시스템그룹코드 리스트 취득 - 상세코드 수정처리
     *
     * @param info     시스템그룹코드 리스트 취득 - 상세 코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult updateSysCodDtl(final SysCodVO info, final UserVO userInfo) throws BizException, UserSysException {
        return this.sysCodDAO.updateSysCodDtl(StringUtil.getCommonPrefix(userInfo), info);
    }

    /**
     * 시스템그룹코드 리스트 취득 - 상세코드 삭제처리
     *
     * @param info     시스템그룹상세코드정보
     * @param userInfo 유저정보
     * @return ProcessResult 결과데이터
     */

    public ProcessResult deleteSysCodDtl(final SysCodVO vo) throws BizException, UserSysException {
        return this.sysCodDAO.deleteSysCodDtl(vo);
    }

    /**
     * 권한레벨 정보 취득처리
     *
     * @param userInfo 유저정보
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getAuthLevelList(final UserVO userInfo) throws UserSysException {
        return this.sysCodDAO.getAuthLevelList(userInfo);
    }
}
