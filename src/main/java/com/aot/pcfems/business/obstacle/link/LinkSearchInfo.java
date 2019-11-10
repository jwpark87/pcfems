/*****************************************************************************
 * 프로그램명  : LinkSearchInfo.java
 * 설     명  : Link 정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.03.02   KYM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.link;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Link 정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class LinkSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -4954180569777639354L;
    /**
     * 사용자 언어
     */
    private String user_lang = "";
    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
    /**
     * 시작인덱스
     */
    private int startIndex = 0;
    /**
     * 종료인덱스
     */
    private int endIndex = 0;

    /**
     * access point code(linkset)
     */
    private String srch_apc = "";
    /**
     * link
     */
    private String srch_slc = "";
    /**
     * 연결상태
     */
    private String srch_status = "";

    /**
     * access point code(linkset) 설정
     *
     * @param srch_apc access point code(linkset)
     */
    public void setSrch_apc(final String srch_apc) {
        this.srch_apc = srch_apc;
    }

    /**
     * access point code(linkset) 취득
     *
     * @return srch_apc access point code(linkset)
     */
    public String getSrch_apc() {
        return StringUtils.defaultString(this.srch_apc);
    }

    /**
     * link 설정
     *
     * @param srch_slc link
     */
    public void setSrch_slc(final String srch_slc) {
        this.srch_slc = srch_slc;
    }

    /**
     * link 취득
     *
     * @return srch_slc link
     */
    public String getSrch_slc() {
        return StringUtils.defaultString(this.srch_slc);
    }

    /**
     * 연결상태 설정
     *
     * @param srch_status 연결상태
     */
    public void setSrch_status(final String srch_status) {
        this.srch_status = srch_status;
    }

    /**
     * 연결상태 취득
     *
     * @return srch_status 연결상태
     */
    public String getSrch_status() {
        return StringUtils.defaultString(this.srch_status);
    }

    /**
     * 생성자
     */
    public LinkSearchInfo() {
    }

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public String getUser_lang() {
        return this.user_lang;
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 우편번호
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 엑셀여부 설정
     *
     * @param search_excel_type 엑셀여부
     */
    public void setSearch_excel_type(final String search_excel_type) {
        this.search_excel_type = search_excel_type;
    }

    /**
     * 엑셀여부 취득
     *
     * @return search_excel_type 엑셀여부
     */
    public String getSearch_excel_type() {
        return StringUtils.defaultString(this.search_excel_type);
    }

    /**
     * 시작인덱스설정
     *
     * @param str 시작인덱스
     */
    public void setStartIndex(final int str) {
        this.startIndex = str;
    }

    /**
     * 시작인덱스취득
     *
     * @return int 시작인덱스
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /**
     * 종료인덱스설정
     *
     * @param str 종료인덱스
     */
    public void setEndIndex(final int str) {
        this.endIndex = str;
    }

    /**
     * 종료인덱스취득
     *
     * @return int 종료인덱스
     */
    public int getEndIndex() {
        return this.endIndex;
    }

}