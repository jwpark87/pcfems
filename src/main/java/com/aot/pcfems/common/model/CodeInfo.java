/*****************************************************************************
 * 프로그램명  : CodeInfo.java
 * 설     명  : ȸ��rhdxhdqnvna공통부품(코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * 코드・명칭정보
 *
 * @author eaction
 * @version 0.1
 */

public class CodeInfo implements Serializable {
    private transient static final long serialVersionUID = -2808061602143390692L;
    /**
     * 코드
     */
    private String code = "";
    /**
     * 명칭
     */
    private String name = "";
    /**
     * 상위코드
     */
    private String upCode = "";
    /**
     * CHILD 그룹코드
     */
    private String childGroup = "";
    /**
     * 설명
     */
    private String desc = "";

    /**
     * 종별
     */
    private String kind = "";
    /**
     * 비밀번호1
     */
    private String passwd1 = "";
    /**
     * 비밀번호2
     */
    private String passwd2 = "";
    /**
     * 작업형태
     */
    private String workType = "";

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
     * 코드값
     */
    private Integer levelcod;

    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

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
    public CodeInfo() {
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strCode 코드
     * @param strName 코드명칭
     */
    public CodeInfo(final String strCode, final String strDescr, final String strDescrKor, final String strDescrEng, final String strDescrJpn) {
        this.setCode(strCode);
        this.setCodenm(strDescr);
        this.setCodenm_k(strDescrKor);
        this.setCodenm_e(strDescrEng);
        this.setCodenm_j(strDescrJpn);
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strCode 코드
     * @param strName 코드명칭
     */
    public CodeInfo(final String strCode, final String strName) {
        this.setCode(strCode);
        this.setName(strName);
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strCode    코드
     * @param strName    코드명칭
     * @param childGroup CHILD 그룹코드
     */
    public CodeInfo(final String strCode, final String strName, final String childGroup) {
        this.setCode(strCode);
        this.setName(strName);
        this.setChildGroup(childGroup);
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
     * @param code 코드값
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * 코드명칭취득
     *
     * @return String 코드명칭
     */
    public String getName() {
        return StringUtils.defaultString(this.name);
    }

    /**
     * 코드명칭설정
     *
     * @param name 코드명칭
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 상위코드값취득
     *
     * @return String 코드값
     */
    public String getUpCode() {
        return this.upCode;
    }

    /**
     * 상위코드값설정
     *
     * @param code 코드값
     */
    public void setUpCode(final String upCode) {
        this.upCode = upCode;
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

    /**
     * 설명 값취득
     *
     * @return String 설명
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 설명설정
     *
     * @param str CHILD 설명
     */
    public void setDesc(final String str) {
        this.desc = str;
    }

    /**
     * 종별 취득
     *
     * @return String 종별
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * 종별
     *
     * @param str CHILD 종별
     */
    public void setKind(final String str) {
        this.kind = str;
    }

    /**
     * 비밀번호1 취득
     *
     * @return String 비밀번호1
     */
    public String getPasswd1() {
        return this.passwd1;
    }

    /**
     * 비밀번호1
     *
     * @param str 비밀번호1
     */
    public void setPasswd1(final String str) {
        this.passwd1 = str;
    }

    /**
     * 비밀번호2 취득
     *
     * @return String 비밀번호2
     */
    public String getPasswd2() {
        return StringUtils.defaultString(this.passwd2);
    }

    /**
     * 비밀번호2
     *
     * @param str 비밀번호2
     */
    public void setPasswd2(final String str) {
        this.passwd2 = str;
    }

    /**
     * 작업형태 취득
     *
     * @return String 작업형태
     */
    public String getWorkType() {
        return StringUtils.defaultString(this.workType);
    }

    /**
     * 작업형태
     *
     * @param str 작업형태
     */
    public void setWorkType(final String str) {
        this.workType = str;
    }

    /**
     * 메뉴이름 취득
     *
     * @return String 메뉴이름
     */
    public String getCodeName() {
        String ret = "";
        final String lang = this.LANGUAGE;
        if ("KOR".equals(lang)) {
            ret = this.codenm_k;
        } else if ("ENG".equals(lang)) {
            ret = this.codenm_e;
        } else if ("JPN".equals(lang)) {
            ret = this.codenm_j;
        } else {
            ret = this.codenm_k;
        }
        if (StringUtils.isEmpty(ret)) {
            ret = this.codenm;
        }

        return ret;
    }

    public Integer getLevelcod() {
        return this.levelcod;
    }

    public void setLevelcod(final Integer levelcod) {
        this.levelcod = levelcod;
    }

}
