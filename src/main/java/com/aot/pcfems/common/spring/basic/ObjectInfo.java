/*****************************************************************************
 * 프로그램명  : ObjectInfo.java
 * 설     명  : 화면별 오브젝트명칭 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.30    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.basic;

import java.io.Serializable;

/**
 * 화면별 오브젝트명칭 정보
 *
 * @author eaction
 * @version 1.0
 */

public class ObjectInfo implements Serializable {

    /**
     * UID
     */
    private static final long serialVersionUID = 6063168228803476743L;

    /**
     * 코드
     */
    private String pageId = "";
    /**
     * 타입
     */
    private String objectId = "";
    /**
     * 명칭
     */
    private String objectNm = "";

    /**
     * 생성자
     */
    public ObjectInfo() {
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
     * 오브젝트명칭취득
     *
     * @return String 오브젝트명칭
     */
    public String getObjectNm() {
        return this.objectNm;
    }

    /**
     * 오브젝트명칭설정
     *
     * @param str 오브젝트명칭
     */
    public void setObjectNm(final String str) {
        this.objectNm = str;
    }
}
