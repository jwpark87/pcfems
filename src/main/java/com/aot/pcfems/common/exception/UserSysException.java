/*****************************************************************************
 * 프로그램명  : UserSysException.java
 * 설     명  : System Exception
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2014.06.13  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.exception;

import com.aot.pcfems.common.model.ErrorInfo;
import com.aot.standard.common.util.AotDateUtils;

/**
 * 시스템 예외
 *
 * @author eaction
 * @version 1.0
 */
public class UserSysException extends Exception {
    private transient static final long serialVersionUID = 3538052585864772834L;
    /**
     * 에러정보 객체
     */
    private transient ErrorInfo errorInfo = new ErrorInfo();

    /**
     * UserSysException
     *
     * @param className  클래스명
     * @param methodName 메소드명
     * @param errId      에러아이디
     */
    public UserSysException(final String className, final String methodName) {
        super();
        this.errorInfo.setClassName(className);
        this.errorInfo.setMethodName(methodName);
        this.errorInfo.setDateTime(AotDateUtils.getStringNow(AotDateUtils.YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * UserSysException
     *
     * @param className  클래스명
     * @param methodName 메소드명
     * @param errId      에러아이디
     * @param e          예외
     */
    public UserSysException(final String className, final String methodName, final Exception originException) {
        super(originException);
        this.errorInfo.setClassName(className);
        this.errorInfo.setMethodName(methodName);
        this.errorInfo.setDateTime(AotDateUtils.getStringNow(AotDateUtils.YYYY_MM_DD_HH_MM_SS));
        this.errorInfo.setOriginException(originException);
    }

    /**
     * 에러정보객체 취득
     *
     * @return ErrorInfo 에러정보객체
     */
    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    /**
     * 에러정보객체 취득
     *
     * @param errorInfo 에러정보객체
     */
    public void setErrorInfo(final ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}