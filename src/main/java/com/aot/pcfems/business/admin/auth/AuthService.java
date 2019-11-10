/*****************************************************************************
 * 프로그램명  : AuthAuthServiceImpl.java
 * 설     명  : 권한레벨  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       	 or		Version Description
 * ---------- --------- ------- ----------------------------------------------
 * 2016.03.08  kym    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.auth;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.util.AotMessageUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 권한레벨관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class AuthService {
    @Resource
    private AuthDAO authDAO;

    /**
     * 권한레벨 목록
     *
     * @param AuthVO 검색데이타
     * @return PagingUtil 리스트
     */

    public PagingUtil getAuthLevelList(final AuthVO vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();

                    if (jo.get("field").getAsString().equals("auth_code")) {
                        vo.setAuth_code(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("auth_nm_kor")) {
                        vo.setAuth_nm_kor(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("auth_nm_eng")) {
                        vo.setAuth_nm_eng(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("auth_nm_jpn")) {
                        vo.setAuth_nm_jpn(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("use_yn")) {
                        vo.setUse_yn(jo.get("data").getAsString());
                    }
                }
            }
        }

        vo.setUser_id(userInfo.getUser_id());
        vo.setGroupId(userInfo.getGroupId());
        vo.setUser_lang(userInfo.getUser_lang());

        for (final AuthVO authVO : this.authDAO.getAuthLevelList(vo)) {
            authVO.setUse_yn(CodeTableMng.getName(userInfo, "USE_YN", authVO.getUse_yn()));
        }
        return new PagingUtil(this.authDAO.getAuthLevelCount(vo), this.authDAO.getAuthLevelList(vo));
    }

    /**
     * 권한레벨 정보
     *
     * @param AuthVO 검색데이타
     * @return Auth 정보
     */

    public AuthVO getAuthLevelInfo(final AuthVO Info) throws UserSysException {
        return this.authDAO.getAuthLevelInfo(Info);
    }

    /**
     * 권한레벨의 등록처리
     *
     * @param info 권한레벨정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertAuthLevel(final AuthVO info) throws UserSysException {
        ProcessResult result = null;

        final int cnt = this.authDAO.getAuthLevelExist(info);
        if (cnt == 0) {
            result = this.authDAO.insertAuthLevel(info);
        } else {
            result = new ProcessResult(this.getClass().getName(), "insertAuthLevel", 0, AotMessageUtils.getMessage("MSG.KEY.DUPLICATION"));
        }
        return result;
    }

    /**
     * 권한레벨 수정처리
     *
     * @param info 권한레벨정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult updateAuthLevel(final AuthVO info) throws UserSysException {
        return this.authDAO.updateAuthLevel(info);
    }

    /**
     * 권한레벨의 삭제처리
     *
     * @param info 권한레벨정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult deleteAuthLevel(final AuthVO info) throws UserSysException {
        return this.authDAO.deleteAuthLevel(info);
    }

}
