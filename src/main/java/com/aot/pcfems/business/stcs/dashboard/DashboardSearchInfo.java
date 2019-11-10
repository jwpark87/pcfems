/*****************************************************************************
 * 프로그램명 : DashboardSearchInfo.java 설 명 : 통계관리 DASHBOARD 조회 검색조건 DataBean 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2018.02.26 KYM 1.0 초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.dashboard;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 통계관리 DASHBOARD 조회 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DashboardSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -7092272841755521971L;
    /**
     * 검색테이블
     */
    private String srch_index_type = "";

    /**
     * 검색테이블 설정
     *
     * @param srch_index_type 검색테이블
     */
    public void setSrch_index_type(final String srch_index_type) {
        this.srch_index_type = srch_index_type;
    }

    /**
     * 검색테이블 취득
     *
     * @return srch_index_type 검색테이블
     */
    public String getSrch_index_type() {
        return StringUtils.defaultString(this.srch_index_type);
    }

}