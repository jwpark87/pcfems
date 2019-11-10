/*****************************************************************************
 * 프로그램명  : EmpGroupSearchInfo.java
 * 설     명  : 유저그룹ȸ��dbwjrhksfl 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.01.09  LYS    1.0
 *****************************************************************************/

package com.aot.pcfems.business.admin.empgroup;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 유저그룹ȸ 검색조건의 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class EmpGroupSearchInfo extends JqGridVO implements Serializable {
    private static final long serialVersionUID = -7725455338657361372L;
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
    private String[] arGroupId = null;

    /**
     * 세션에서 로그인한 ID
     */
    private String sessionLoginId = "";
    /**
     * 사용자그룹코드
     */
    private String user_group_id = "";
    /**
     * 사용자언어
     */
    private String user_lang = "";

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
     * 생성자
     */
    public EmpGroupSearchInfo() {
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
     * 유저그룹 리스트 설정
     *
     * @param status 유저그룹 리스트
     */
    public void setArGroupId(final String[] arGroupId) {
        this.arGroupId = Arrays.copyOf(arGroupId, arGroupId.length);
    }

    /**
     * 유저그룹 리스트 취득
     *
     * @return String[] 유저그룹 리스트
     */
    public String[] getArGroupId() {
        return Arrays.copyOf(this.arGroupId, this.arGroupId.length);
    }
}