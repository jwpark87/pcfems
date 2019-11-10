/*****************************************************************************
 * 프로그램명  : SbcSearchInfo.java
 * 설     명  : SBC설정정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.setting.sbc;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * SBC설정정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class SbcSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -4784981674896111852L;
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
     * 검색키
     */
    private String srch_key = "";
    /**
     * 검색값
     */
    private String srch_val = "";

    /**
     * 검색용SBC노드명칭
     */
    private String srch_sbc_node_name = "";
    /**
     * 검색용SBC노드아이피
     */
    private String srch_sbc_node_ip = "";
    /**
     * 검색용SBC노드그룹아이디
     */
    private String srch_sbc_group_id = "";
    /**
     * 검색용SBC노드그룹명칭
     */
    private String srch_sbc_group_name = "";

    /**
     * 생성자
     */
    public SbcSearchInfo() {
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

    /**
     * 검색키설정
     *
     * @param srch_key 검색키
     */
    public void setSearch_key(final String srch_key) {
        this.srch_key = StringUtils.defaultString(srch_key);
    }

    /**
     * 검색키취득
     *
     * @return srch_key 검색키
     */
    public String getSearch_key() {
        return this.srch_key;
    }

    /**
     * 검색값설정
     *
     * @param srch_val 검색키
     */
    public void setSearch_val(final String srch_val) {
        this.srch_val = StringUtils.defaultString(srch_val);
    }

    /**
     * 검색값취득
     *
     * @return srch_val 검색값
     */
    public String getSearch_val() {
        return this.srch_val;
    }

    /**
     * 검색용SBC노드명 설정
     *
     * @param srch_sbc_node_name 검색용SBC노드명
     */
    public void setSrch_sbc_node_name(final String srch_sbc_node_name) {
        this.srch_sbc_node_name = srch_sbc_node_name;
    }

    /**
     * 검색용SBC노드명 취득
     *
     * @return srch_sbc_node_name 검색용SBC노드명
     */
    public String getSrch_sbc_node_name() {
        return StringUtils.defaultString(this.srch_sbc_node_name);
    }

    /**
     * 검색용SBC노드IP 설정
     *
     * @param srch_sbc_node_ip 검색용SBC노드IP
     */
    public void setSrch_sbc_node_ip(final String srch_sbc_node_ip) {
        this.srch_sbc_node_ip = srch_sbc_node_ip;
    }

    /**
     * 검색용SBC노드IP 취득
     *
     * @return srch_sbc_node_ip 검색용SBC노드IP
     */
    public String getSrch_sbc_node_ip() {
        return StringUtils.defaultString(this.srch_sbc_node_ip);
    }

    /**
     * 검색용SBC노드그룹아이디 설정
     *
     * @param srch_sbc_group_id 검색용SBC노드그룹아이디
     */
    public void setSrch_sbc_group_id(final String srch_sbc_group_id) {
        this.srch_sbc_group_id = srch_sbc_group_id;
    }

    /**
     * 검색용SBC노드그룹아이디 취득
     *
     * @return srch_sbc_group_id 검색용SBC노드그룹아이디
     */
    public String getSrch_sbc_group_id() {
        return StringUtils.defaultString(this.srch_sbc_group_id);
    }

    /**
     * 검색용SBC노드그룹명칭 설정
     *
     * @param srch_sbc_group_name 검색용SBC노드그룹명칭
     */
    public void setSrch_sbc_group_name(final String srch_sbc_group_name) {
        this.srch_sbc_group_name = srch_sbc_group_name;
    }

    /**
     * 검색용SBC노드그룹명칭 취득
     *
     * @return srch_sbc_group_name 검색용SBC노드그룹명칭
     */
    public String getSrch_sbc_group_name() {
        return StringUtils.defaultString(this.srch_sbc_group_name);
    }
}