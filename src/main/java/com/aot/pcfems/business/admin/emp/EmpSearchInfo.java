/*****************************************************************************
 * 프로그램명  : EmpSearchInfo.java
 * 설     명  : 사용자정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2015.05.11  WYA    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.emp;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 유저정보 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class EmpSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 155430202011100946L;
    /**
     * 검색키
     */
    private String searchKey = "";
    /**
     * 검색값
     */
    private String searchVal = "";
    /**
     * 유저그룹검색
     */
    private String searchLevel = "";

    /**
     * 시작인덱스
     */
    private int startIndex = 0;
    /**
     * 종료인덱스
     */
    private int endIndex = 0;

    /**
     * 유저아이디 리스트
     */
    private String[] arUserId = null;

    /**
     * 시퀀스
     */
    private String seqNo = "";
    /**
     * 유저아이디
     */
    private String userId = "";
    /**
     * 유저아이디
     */
    private String name = "";

    /**
     * 세션에서 로그인한 ID
     */
    private String sessionLoginId = "";
    /**
     * 사용자그룹코드
     */
    private String user_group_id = "";

    /**
     * 엑셀여부
     */
    private String search_excel_type = "";
    /**
     * 페이지
     */
    private String srch_page = "";
    /**
     * 사용자언어
     */
    private String user_lang = "";
    /**
     * 검색적용시작일
     */
    private String srch_apply_from = "";
    /**
     * 검색적용종료일
     */
    private String srch_apply_to = "";

    /**
     * 검색여부
     */
    private String search_flag = "";
    /**
     * 사용자그룹명
     */
    private String user_group_nm = "";
    /**
     * 파트너이름
     */
    private String partner_name = "";
    /**
     * 잠금여부
     */
    private String lock_yn = "";
    /**
     * 영업사원여부
     */
    private String contact_yn = "";
    /**
     * 작업감독여부
     */
    private String director_yn = "";

    /**
     * 작업감독여부 설정
     *
     * @param director_yn 작업감독여부
     */
    public void setDirector_yn(final String director_yn) {
        this.director_yn = director_yn;
    }

    /**
     * 작업감독여부 취득
     *
     * @return director_yn 작업감독여부
     */
    public String getDirector_yn() {
        return StringUtils.defaultString(this.director_yn);
    }

    /**
     * 영업사원여부 설정
     *
     * @param contact_yn 영업사원여부
     */
    public void setContact_yn(final String contact_yn) {
        this.contact_yn = contact_yn;
    }

    /**
     * 영업사원여부 취득
     *
     * @return contact_yn 영업사원여부
     */
    public String getContact_yn() {
        return StringUtils.defaultString(this.contact_yn);
    }

    /**
     * 잠금여부 설정
     *
     * @param lock_yn 잠금여부
     */
    public void setLock_yn(final String lock_yn) {
        this.lock_yn = lock_yn;
    }

    /**
     * 잠금여부 취득
     *
     * @return lock_yn 잠금여부
     */
    public String getLock_yn() {
        return StringUtils.defaultString(this.lock_yn);
    }

    /**
     * 파트너이름 설정
     *
     * @param partner_name 파트너이름
     */
    public void setPartner_name(final String partner_name) {
        this.partner_name = partner_name;
    }

    /**
     * 파트너이름 취득
     *
     * @return partner_name 파트너이름
     */
    public String getPartner_name() {
        return StringUtils.defaultString(this.partner_name);
    }

    /**
     * 사용자그룹명 설정
     *
     * @param user_group_nm 사용자그룹명
     */
    public void setUser_group_nm(final String user_group_nm) {
        this.user_group_nm = user_group_nm;
    }

    /**
     * 사용자그룹명 취득
     *
     * @return user_group_nm 사용자그룹명
     */
    public String getUser_group_nm() {
        return StringUtils.defaultString(this.user_group_nm);
    }

    /**
     * 검색여부 설정
     *
     * @param search_flag 검색여부
     */
    public void setSearch_flag(final String search_flag) {
        this.search_flag = search_flag;
    }

    /**
     * 검색여부 취득
     *
     * @return search_flag 검색여부
     */
    public String getSearch_flag() {
        return StringUtils.defaultString(this.search_flag);
    }

    /**
     * 검색적용시작일 설정
     *
     * @param srch_apply_from 검색적용시작일
     */
    public void setSrch_apply_from(final String srch_apply_from) {
        this.srch_apply_from = srch_apply_from;
    }

    /**
     * 검색적용시작일 취득
     *
     * @return srch_apply_from 검색적용시작일
     */
    public String getSrch_apply_from() {
        return StringUtils.defaultString(this.srch_apply_from);
    }

    /**
     * 검색적용종료일 설정
     *
     * @param srch_apply_to 검색적용종료일
     */
    public void setSrch_apply_to(final String srch_apply_to) {
        this.srch_apply_to = srch_apply_to;
    }

    /**
     * 검색적용종료일 취득
     *
     * @return srch_apply_to 검색적용종료일
     */
    public String getSrch_apply_to() {
        return StringUtils.defaultString(this.srch_apply_to);
    }

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 사용자언어
     */
    public String getUser_lang() {
        return StringUtils.defaultString(this.user_lang);
    }

    /**
     * 페이지 설정
     *
     * @param srch_page 페이지
     */
    public void setSrch_page(final String srch_page) {
        this.srch_page = srch_page;
    }

    /**
     * 페이지 취득
     *
     * @return srch_page 페이지
     */
    public String getSrch_page() {
        return StringUtils.defaultString(this.srch_page);
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
     * @return the sessionLoginId
     */
    public String getSessionLoginId() {
        return StringUtils.defaultString(this.sessionLoginId);
    }

    /**
     * @param sessionLoginId the sessionLoginId to set
     */
    public void setSessionLoginId(final String sessionLoginId) {
        this.sessionLoginId = sessionLoginId;
    }

    /**
     * 사용자그룹코드 설정
     *
     * @param user_group_id 사용자그룹코드
     */
    public void setUser_group_id(final String user_group_id) {
        this.user_group_id = user_group_id;
    }

    /**
     * 사용자그룹코드 취득
     *
     * @return user_group_id 사용자그룹코드
     */
    public String getUser_group_id() {
        return StringUtils.defaultString(this.user_group_id);
    }

    public String getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(final String seqNo) {
        this.seqNo = seqNo;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 생성자
     */
    public EmpSearchInfo() {
    }

    /**
     * 검색키설정
     *
     * @param str 검색키
     */
    public void setSearchKey(final String str) {
        this.searchKey = StringUtils.defaultString(str);
    }

    /**
     * 검색키취득
     *
     * @return searchKey 검색키
     */
    public String getSearchKey() {
        return this.searchKey;
    }

    /**
     * 검색값설정
     *
     * @param str 검색키
     */
    public void setSearchVal(final String str) {
        this.searchVal = StringUtils.defaultString(str);
    }

    /**
     * 검색값취득
     *
     * @return searchVal 검색값
     */
    public String getSearchVal() {
        return this.searchVal;
    }

    /**
     * 유저그룹검색값설정
     *
     * @param str 유저그룹검색값
     */
    public void setSearchLevel(final String str) {
        this.searchLevel = StringUtils.defaultString(str);
    }

    /**
     * 유저그룹검색값취득
     *
     * @return searchVal 유저그룹검색값
     */
    public String getSearchLevel() {
        return this.searchLevel;
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
     * 유저아이디 리스트 설정
     *
     * @param status 유저아이디 리스트
     */
    public void setArUserId(final String[] arUserId) {
        this.arUserId = Arrays.copyOf(arUserId, arUserId.length);
    }

    /**
     * 유저아이디 리스트 취득
     *
     * @return String 유저아이디 리스트
     */
    public String[] getArUserId() {
        return Arrays.copyOf(this.arUserId, this.arUserId.length);
    }
}