/*****************************************************************************
 * 프로그램명  : AccessInfo.java
 * 설     명  : 화면별 엑세스정보 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.06    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.basic;

import java.io.Serializable;

/**
 * 화면별 엑세스정보
 *
 * @author eaction
 * @version 1.0
 */
public class AccessInfo implements Serializable {
    private transient static final long serialVersionUID = -6110372426734577017L;
    /**
     * 페이지아이디
     */
    private String pageId = "";
    /**
     * 오브젝트아이디
     */
    private String objectId = "";
    /**
     * 사용가능여부
     */
    private String enableYn = "";
    /**
     * 보여줄지여부
     */
    private String visibleYn = "";
    /**
     * 그룹레벨
     */
    private String groupLevel = "";

    /**
     * 생성자
     */
    public AccessInfo() {
    }

    /**
     * 페이지아이디취득
     *
     * @return String 페이지아이디
     */
    public String getPageId() {
        return this.pageId;
    }

    /**
     * 페이지아이디설정
     *
     * @param str 페이지아이디
     */
    public void setPageId(final String str) {
        this.pageId = str;
    }

    /**
     * 오브젝트아이디취득
     *
     * @return String 오브젝트아이디
     */
    public String getObjectId() {
        return this.objectId;
    }

    /**
     * 오브젝트아이디설정
     *
     * @param str 오브젝트아이디
     */
    public void setObjectId(final String str) {
        this.objectId = str;
    }

    /**
     * 사용가능여부취득
     *
     * @return String 사용가능여부
     */
    public String getEnableYn() {
        return this.enableYn;
    }

    /**
     * 사용가능여부설정
     *
     * @param str 사용가능여부
     */
    public void setEnableYn(final String str) {
        this.enableYn = str;
    }

    /**
     * 보여줄지여부취득
     *
     * @return String 보여줄지여부
     */
    public String getVisibleYn() {
        return this.visibleYn;
    }

    /**
     * 보여줄지여부설정
     *
     * @param str 보여줄지여부
     */
    public void setVisibleYn(final String str) {
        this.visibleYn = str;
    }

    /**
     * 그룹레벨취득
     *
     * @return String 그룹레벨
     */
    public String getGroupLevel() {
        return this.groupLevel;
    }

    /**
     * 그룹레벨설정
     *
     * @param str 그룹레벨
     */
    public void setGroupLevel(final String str) {
        this.groupLevel = str;
    }
}
