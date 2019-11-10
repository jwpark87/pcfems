/*****************************************************************************
 * 프로그램명  : EmpServiceImpl.java
 * 설     명  : 사용자정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2015.05.11  WYA    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.emp;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.PagingUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 사용자정보관리 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class EmpService {
    @Resource
    private EmpDAO empDAO;

    /**
     * 사용자정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil 사용자정보관리 리스트
     */

    public PagingUtil getUserInfoList(final EmpSearchInfo vo, final UserVO userInfo) throws UserSysException {
        if (vo.getJqFilters() != null && !vo.getJqFilters().isJsonNull() && vo.getJqFilters().get("rules") != null) {
            final JsonArray arr = vo.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();

                    if (jo.get("field").getAsString().equals("user_group_nm")) {
                        vo.setUser_group_nm(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("partner_name")) {
                        vo.setPartner_name(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("lock_yn")) {
                        vo.setLock_yn(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("contact_yn")) {
                        vo.setContact_yn(jo.get("data").getAsString());
                    }
                    if (jo.get("field").getAsString().equals("director_yn")) {
                        vo.setDirector_yn(jo.get("data").getAsString());
                    }
                }
            }
        }

        /*
         * if(result != null && result.size()>0){ for(int i=0;i<result.size();i++){ EmpInfo rsVO = (EmpInfo)result.get(i); rsVO.setLock_yn(CodeTableMng.getName2(userInfo, "USE_YN"
         * ,rsVO.getLock_yn())); rsVO.setContact_yn(CodeTableMng.getName2(userInfo, "USE_YN" ,rsVO.getContact_yn())); rsVO.setDirector_yn(CodeTableMng.getName2(userInfo, "USE_YN"
         * ,rsVO.getDirector_yn())); } }
         */

        return new PagingUtil(this.empDAO.getEmpListCnt(vo), this.empDAO.getUserInfoList(vo));
    }

    /**
     * 사용자정보 리스트 카운트 취득
     *
     * @param searchInfo
     *            검색정보빈
     * @return PagingUtil 사용자정보리스트
     */

    // public int getEmpListCnt(final EmpSearchInfo searchInfo) throws UserSysException {
    // return this.empDAO.getEmpListCnt(searchInfo);
    // }

    /**
     * 사용자정보 등록처리
     *
     * @param info 사용자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertEmp(final EmpInfo info) throws UserSysException {
        return this.empDAO.insertEmp(info);
    }

    /**
     * 사용자정보 상세 정보
     *
     * @param userId 검색사용자아이디
     * @return User 사용자정보관리 상세보기
     */

    public EmpInfo getEmpDetail(final EmpInfo info) throws UserSysException {
        return this.empDAO.getEmpDetail(info);
    }

    /**
     * 사용자정보 수정처리
     *
     * @param userInfo 수정할 유저정보
     */

    @Transactional
    public ProcessResult updateEmp(final EmpInfo userInfo) throws UserSysException {
        return this.empDAO.updateEmp(userInfo);
    }

    /**
     * 사용자정보 삭제처리
     *
     * @param userInfo 삭제할유저아이디
     */

    @Transactional
    public ProcessResult deleteEmp(final String userId) throws UserSysException {
        return this.empDAO.deleteEmp(userId);
    }

    /**
     * 사용자그룹 정보 취득처리
     *
     * @param userInfo 유저정보
     * @return List 권한레벨 정보 리스트
     */

    public List<CodeInfo> getUserGroupCodeInfo(final EmpSearchInfo searchInfo, final UserVO userInfo) throws UserSysException {
        searchInfo.setUserId(userInfo.getUser_id());
        searchInfo.setUser_group_id(userInfo.getGroupId());
        return this.empDAO.getUserGroupCodeInfo(searchInfo);
    }

    /**
     * 유저아이디 존재 체크
     *
     * @param userId ȸ��유저 아이디
     * @return boolean (true:유저가 존재하지 않을때,false:유저가 존재할때)
     */

    public String getUserInfoExist(final String emp_id) throws UserSysException {
        return "1".equals(this.empDAO.getUserInfoExist(emp_id)) ? "NG" : "OK";
    }

}
