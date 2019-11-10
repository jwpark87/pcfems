/*****************************************************************************
 * 프로그램명  : ErrorInfo.java
 * 설     명  : ȸ��rhdxhdqnvna공통부품(에러정보객체)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.28    LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 에러정보객체 (1)User정의Exception 에서 생성해서 멤버변수로 처리함 (2)에러처리에 필요한 내용을 관리하므로 필요한 내용을 추가해서 관리.
 *
 * @author eaction
 * @version 1.0
 */
public class ErrorInfo implements Serializable {
    private transient static final long serialVersionUID = 7022851850482188006L;
    /**
     * 에러 발생 클래스
     */
    private String className = "";
    /**
     * 에러 발생 메소드
     */
    private String methodName = "";
    /**
     * 에러 코드
     */
    private String errorCode = "";
    /**
     * 에러 메세지
     */
    private String errorMessage = "";
    /**
     * 에러 발생 시간(6자리 문자열)
     */
    private String dateTime = "";
    /**
     * 객체
     */
    private Object paramObj = null;
    /**
     * 에러 발생 원본 예외
     */
    private Exception originException = null;

    /**
     * 생성자
     */
    public ErrorInfo() {
    }

    /**
     * 에러 발생 클래스를 취득한다
     *
     * @return String 에러 발생 클래스
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * 에러 발생 클래스를 설정한다.
     *
     * @param str 에러 발생 클래스
     */
    public void setClassName(final String str) {
        this.className = StringUtils.defaultString(str);
    }

    /**
     * 에러발생메소드를 취득한다
     *
     * @return String 에러발생메소드
     */
    public String getMethodName() {
        return this.methodName;
    }

    /**
     * 에러발생메소드를 설정한다.
     *
     * @param str 에러발생메소드
     */
    public void setMethodName(final String str) {
        this.methodName = StringUtils.defaultString(str);
    }

    /**
     * 에러코드를 취득한다
     *
     * @return String 에러코드
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 에러코드를 설정한다.
     *
     * @param str 에러코드
     */
    public void setErrorCode(final String str) {
        this.errorCode = StringUtils.defaultString(str);
    }

    /**
     * 에러메세지를 취득한다
     *
     * @return String 에러메세지
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * 에러메세지를 설정한다.
     *
     * @param str 에러메세지
     */
    public void setErrorMessage(final String str) {
        this.errorMessage = StringUtils.defaultString(str);
    }

    /**
     * 에러 발생 날짜를 취득한다
     *
     * @return String 에러 발생 날짜
     */
    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * 에러 발생 날짜를 설정한다.
     *
     * @param str 에러 발생 날짜
     */
    public void setDateTime(final String str) {
        this.dateTime = StringUtils.defaultString(str);
    }

    /**
     * 파라미터OBJ 취득
     *
     * @return Object 파라미터OBJ
     */
    public Object getParamObj() {
        return this.paramObj;
    }

    /**
     * 파라미터OBJ를 설정한다.
     *
     * @param obj 파라미터OBJ
     */
    public void setParamObj(final Object obj) {
        this.paramObj = obj;
    }

    /**
     * 에러 발생 원본 예외을 취득한다 <br>
     * (받는곳에서 instanceof를 조사해서 처리한다)
     *
     * @return Exception 에러 발생 원본 예외
     */
    public Exception getOriginException() {
        return this.originException;
    }

    /**
     * 에러 발생 원본 예외를 설정한다.
     *
     * @param Exception 에러 발생 원본 예외
     */
    public void setOriginException(final Exception except) {
        this.originException = except;
    }
}