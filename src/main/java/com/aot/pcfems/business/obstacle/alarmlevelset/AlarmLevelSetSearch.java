/*****************************************************************************
 * 프로그램명  : AlarmLevelSetSearch.java
 * 설     명  : 알람 등급  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.05 KYM    1.0
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.alarmlevelset;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AlarmLevelSetSearch extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -6448328601501846036L;
    /**
     * 검색알람코드
     */
    private String srch_alm_code = "";
    /**
     * 검색알람설명
     */
    private String srch_alm_desc = "";
    /**
     * 검색언어
     */
    private String user_lang = "";

    /**
     * 검색언어 설정
     *
     * @param user_lang 검색언어
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 검색언어 취득
     *
     * @return user_lang 검색언어
     */
    public String getUser_lang() {
        return StringUtils.defaultString(this.user_lang);
    }

    /**
     * 검색알람코드 설정
     *
     * @param srch_alm_code 검색알람코드
     */
    public void setSrch_alm_code(final String srch_alm_code) {
        this.srch_alm_code = srch_alm_code;
    }

    /**
     * 검색알람코드 취득
     *
     * @return srch_alm_code 검색알람코드
     */
    public String getSrch_alm_code() {
        return StringUtils.defaultString(this.srch_alm_code);
    }

    /**
     * 검색알람설명 설정
     *
     * @param srch_alm_desc 검색알람설명
     */
    public void setSrch_alm_desc(final String srch_alm_desc) {
        this.srch_alm_desc = srch_alm_desc;
    }

    /**
     * 검색알람설명 취득
     *
     * @return srch_alm_desc 검색알람설명
     */
    public String getSrch_alm_desc() {
        return StringUtils.defaultString(this.srch_alm_desc);
    }

}
