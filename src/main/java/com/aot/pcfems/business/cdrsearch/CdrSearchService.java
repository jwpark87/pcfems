/*****************************************************************************
 * 프로그램명  : CdrServiceImpl.java
 * 설     명  : 진행 중 CDR 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.cdrsearch;

import com.aot.pcfems.business.login.LoginDAO;
import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.redis.RedisUtils;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 진행 중 CDR 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class CdrSearchService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CdrSearchDAO cdrSearchDAO;
    @Resource
    private LoginDAO loginDAO;

    /**
     * CDR 조회 기본값 취득
     *
     * @param CdrSearchSearchInfo 검색데이타
     * @return CdrSearch 기본값 취득
     */

    public CdrSearchVO getCdrDefaultInfo(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.cdrSearchDAO.getCdrDefaultInfo(searchInfo);
    }

    /**
     * CDR 컬럼 조회 취득
     *
     * @param searchInfo 검색정보빈
     * @return List<CdrSearch>CDR 컬럼 리스트
     */

    public Map<String, JsonElement> getCdrColumnList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        final Map<String, JsonElement> rtnMap = new HashMap<>();

        // 타이틀
        final List<String> colNames = new ArrayList<>();
        // 모델
        final List<Map<String, String>> colModel = new ArrayList<>();

        final List<CdrSearchVO> result = this.cdrSearchDAO.getCdrColumnList(searchInfo);
        if (result != null && result.size() > 0) {
            for (final CdrSearchVO obj : result) {
                colNames.add(obj.getCol_nm());
                final Map<String, String> rowMap = new HashMap<>();
                rowMap.put("name", obj.getCol_cd());
                rowMap.put("index", obj.getCol_cd());
                rowMap.put("width", obj.getDsp_width());
                if ("L".equals(obj.getText_align())) {
                    rowMap.put("align", "left");
                } else if ("R".equals(obj.getText_align())) {
                    rowMap.put("align", "right");
                } else if ("C".equals(obj.getText_align())) {
                    rowMap.put("align", "center");
                }
                colModel.add(rowMap);
            }
            rtnMap.put("ColNames", JqGridUtil.getJsonData(colNames));
            rtnMap.put("ColModel", JqGridUtil.getJsonData(colModel));
        }
        return rtnMap;
    }

    /**
     * CDR 조회 필터 컬럼 목록
     *
     * @param CdrSearchSearchInfo 검색데이타
     * @return List 필터 컬럼 리스트
     */

    public List<CodeInfo> getCdrFilterColumnList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.cdrSearchDAO.getCdrFilterColumnList(searchInfo);
    }

    public Map<String, Object> getPrcHandleSnmpalarmList(final CdrSearchSearchInfo searchInfo) throws UserSysException {
        return this.cdrSearchDAO.getPrcHandleSnmpalarmList(null);
    }

    public void killSession(final String jsonStr, final String password) throws UserSysException, BizException, CommonException {
        final UserVO paramUserInfo = new UserVO();
        paramUserInfo.setId("admin");
        paramUserInfo.setPassword(password);
        if (this.loginDAO.getUserInfo(paramUserInfo) == null) {
            throw new CommonException(CommonExceptionCode.ERROR_NOT_ALLOWED_MEMBER);
        }

        try {
            final JsonArray jsonArray = AotMapperUtils.writeObjectAsJsonArray(jsonStr);
            for (final JsonElement jsonObject : jsonArray) {
                RedisUtils.enQueue("SESSKILLREQ", jsonObject.toString());
            }
        } catch (final Throwable e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

}
