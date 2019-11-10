/*****************************************************************************
 * 프로그램명  : GroupCodeInfo.java
 * 설     명  : ȸ��rhdxhdqnvna공통부품(그룹코드/코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.04    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.code;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 코드・명칭정보
 *
 * @author eaction
 * @version 0.1
 */

public class GroupCodeInfo implements Serializable {
    /**
     * UID
     */
    private static final long serialVersionUID = -8727404414616095655L;

    /**
     * 그룹 레벨
     */
    private String groupLevel = "";
    /**
     * 그룹코드
     */
    private String group = "";
    /**
     * 코드
     */
    private String code = "";
    /**
     * 명칭
     */
    private String name = "";
    /**
     * 갱신가부
     */
    private String modiYn = "";
    /**
     * 상위코드
     */
    private String upCode = "";
    /**
     * CHILD 그룹코드
     */
    private String childGroup = "";

    /**
     * 코드값-한국어
     */
    private String codenm_k = "";
    /**
     * 코드값-영어
     */
    private String codenm_e = "";
    /**
     * 코드값-일본어
     */
    private String codenm_j = "";

    /**
     * 코드값
     */
    private String codenm = "";

    /**
     * 코드값 설정
     *
     * @param codenm 코드값
     */
    public void setCodenm(final String codenm) {
        this.codenm = codenm;
    }

    /**
     * 코드값 취득
     *
     * @return codenm 코드값
     */
    public String getCodenm() {
        return StringUtils.defaultString(this.codenm);
    }

    /**
     * 코드값-한국어 설정
     *
     * @param codenm_k 코드값-한국어
     */
    public void setCodenm_k(final String codenm_k) {
        this.codenm_k = codenm_k;
    }

    /**
     * 코드값-한국어 취득
     *
     * @return codenm_k 코드값-한국어
     */
    public String getCodenm_k() {
        return StringUtils.defaultString(this.codenm_k);
    }

    /**
     * 코드값-영어 설정
     *
     * @param codenm_e 코드값-영어
     */
    public void setCodenm_e(final String codenm_e) {
        this.codenm_e = codenm_e;
    }

    /**
     * 코드값-영어 취득
     *
     * @return codenm_e 코드값-영어
     */
    public String getCodenm_e() {
        return StringUtils.defaultString(this.codenm_e);
    }

    /**
     * 코드값-일본어 설정
     *
     * @param codenm_j 코드값-일본어
     */
    public void setCodenm_j(final String codenm_j) {
        this.codenm_j = codenm_j;
    }

    /**
     * 코드값-일본어 취득
     *
     * @return codenm_j 코드값-일본어
     */
    public String getCodenm_j() {
        return StringUtils.defaultString(this.codenm_j);
    }

    /**
     * 생성자
     */
    public GroupCodeInfo() {
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strGroup 그룹코드
     * @param strCode  코드
     * @param strName  코드명칭
     */
    public GroupCodeInfo(final String strGroup, final String strCode, final String strName) {
        this.setGroup(strGroup);
        this.setCode(strCode);
        this.setName(strName);
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strGroup  그룹코드
     * @param strCode   코드
     * @param strName   코드명칭
     * @param strUpCode 상위코드
     */
    public GroupCodeInfo(final String strGroup, final String strCode, final String strName, final String strUpCode) {
        this.setGroup(strGroup);
        this.setCode(strCode);
        this.setName(strName);
        this.setUpCode(strUpCode);
    }

    /**
     * 그룹레벨값취득
     *
     * @return String 그룹레벨값
     */
    public String getGroupLevel() {
        return this.groupLevel;
    }

    /**
     * 그룹레벨값설정
     *
     * @param str 그룹레벨값
     */
    public void setGroupLevel(final String str) {
        this.groupLevel = str;
    }

    /**
     * 그룹코드값취득
     *
     * @return String 그룹코드값
     */
    public String getGroup() {
        return this.group;
    }

    /**
     * 그룹코드값설정
     *
     * @param str 그룹코드값
     */
    public void setGroup(final String str) {
        this.group = str;
    }

    /**
     * 코드값취득
     *
     * @return String 코드값
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 코드값설정
     *
     * @param str 코드값
     */
    public void setCode(final String str) {
        this.code = str;
    }

    /**
     * 코드명칭취득
     *
     * @return String 코드명칭
     */
    public String getName() {
        return this.name;
    }

    /**
     * 코드명칭설정
     *
     * @param str 코드명칭
     */
    public void setName(final String str) {
        this.name = str;
    }

    /**
     * 갱신가부취득
     *
     * @return String 갱신가부
     */
    public String getModiYn() {
        return this.modiYn;
    }

    /**
     * 갱신가부설정
     *
     * @param str 갱신가부
     */
    public void setModiYn(final String str) {
        this.modiYn = str;
    }

    /**
     * 상위코드값취득
     *
     * @return String 상위코드값
     */
    public String getUpCode() {
        return this.upCode;
    }

    /**
     * 상위코드값설정
     *
     * @param str 상위코드값
     */
    public void setUpCode(final String str) {
        this.upCode = str;
    }

    /**
     * CHILD 그룹코드 값취득
     *
     * @return String CHILD 그룹코드 값
     */
    public String getChildGroup() {
        return this.childGroup;
    }

    /**
     * CHILD 그룹코드 값설정
     *
     * @param str CHILD 그룹코드 값
     */
    public void setChildGroup(final String str) {
        this.childGroup = str;
    }
}
