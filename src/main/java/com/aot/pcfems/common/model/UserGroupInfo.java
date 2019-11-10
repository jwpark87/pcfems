/*****************************************************************************
 * 프로그램명  : GroupCodeInfo.java
 * 설     명  : ȸ��rhdxhdqnvna공통부품(그룹코드/코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.04    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 코드・명칭정보
 *
 * @author eaction
 * @version 0.1
 */

public class UserGroupInfo implements Serializable {
    private transient static final long serialVersionUID = 5361477895699981708L;

    /**
     * 그룹 레벨
     */
    private String groupLevel = "";
    /**
     * 코드
     */
    private String code = "";
    /**
     * 명칭
     */
    private String name = "";
    /**
     * 비고
     */
    private String remark = "";
    /**
     * 등록유저
     */
    private String crtUser = "";
    /**
     * 갱신유저
     */
    private String updtUser = "";
    /**
     * 수정자명
     */
    private String updtUserNm = "";
    /**
     * 등록날짜
     */
    private String crtDt = "";
    /**
     * 수정날짜
     */
    private String updtDt = "";
    /**
     * 권한레벨코드명
     */
    private String levelcodnm = "";

    /**
     * 생성자
     */
    public UserGroupInfo() {
    }

    /**
     * 생성자(기본값을 설정한다)
     *
     * @param strGroup 그룹코드
     * @param strCode  코드
     * @param strName  코드명칭
     */
    public UserGroupInfo(final String groupLevel, final String strCode, final String strName) {
        this.setGroupLevel(groupLevel);
        this.setCode(strCode);
        this.setName(strName);
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
     * 비고취득
     *
     * @return String 비고
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 비고설정
     *
     * @param str 비고
     */
    public void setRemark(final String str) {
        this.remark = str;
    }

    /**
     * 등록유저취득
     *
     * @return String 등록유저
     */
    public String getCrtUser() {
        return this.crtUser;
    }

    /**
     * 등록유저설정
     *
     * @param str 등록유저
     */
    public void setCrtUser(final String str) {
        this.crtUser = str;
    }

    /**
     * 갱신유저 값취득
     *
     * @return String 갱신유저
     */
    public String getUpdtUser() {
        return this.updtUser;
    }

    /**
     * 갱신유저 값설정
     *
     * @param str 갱신유저
     */
    public void setUpdtUser(final String str) {
        this.updtUser = str;
    }

    /**
     * 수정자명 설정
     *
     * @param updtUserNm 수정자명
     */
    public void setUpdtUserNm(final String updtUserNm) {
        this.updtUserNm = updtUserNm;
    }

    /**
     * 수정자명 취득
     *
     * @return updtUserNm 수정자명
     */
    public String getUpdtUserNm() {
        return this.updtUserNm;
    }

    /**
     * 등록날짜 값취득
     *
     * @return String 등록날짜
     */
    public String getCrtDt() {
        return this.crtDt;
    }

    /**
     * 등록날짜 값설정
     *
     * @param str 등록날짜
     */
    public void setCrtDt(final String str) {
        this.crtDt = str;
    }

    /**
     * 수정날짜 값취득
     *
     * @return String 수정날짜
     */
    public String getUpdtDt() {
        return this.updtDt;
    }

    /**
     * 수정날짜 값설정
     *
     * @param str 수정날짜
     */
    public void setUpdtDt(final String str) {
        this.updtDt = str;
    }

    /**
     * @return the levelcodnm
     */
    public String getLevelcodnm() {
        return StringUtils.defaultString(this.levelcodnm);
    }

    /**
     * @param levelcod the levelcodnm to set
     */
    public void setLevelcodnm(final String levelcodnm) {
        this.levelcodnm = levelcodnm;
    }
}
