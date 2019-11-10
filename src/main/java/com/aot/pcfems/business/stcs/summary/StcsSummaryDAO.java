/*****************************************************************************
 * 프로그램명  : VarStatsDAO.java
 * 설     명  : var/stats 통합 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.21   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.summary;

import com.aot.pcfems.business.stcs.varstats.VarStats;
import com.aot.pcfems.business.stcs.varstats.VarStatsSearchInfo;
import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * var/stats 조회 통합 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Mapper
public interface StcsSummaryDAO {
    /**
     * 조회 기본값 취득
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return VarStats 기본값 취득
     */

    List<VarStats> getVarStatsDefaultInfo(final VarStatsSearchInfo searchInfo) throws UserSysException;

    /**
     * 조회 컬럼 목록
     *
     * @param VarStatsSearchInfo 검색데이타
     * @return List 컬럼 리스트
     */

    List<VarStats> getVarStatsColumnList(final VarStatsSearchInfo searchInfo) throws UserSysException;

}
