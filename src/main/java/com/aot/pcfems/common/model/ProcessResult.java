/*****************************************************************************
 * 프로그램명  : ProcessResult.java
 * 설     명  : 처리결과
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.02.27  JSM    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import com.aot.standard.common.util.AotDateUtils;

/**
 * 처리결과 정의한다
 *
 * @author eaction
 * @version 1.0
 */
public class ProcessResult extends RuntimeException {
    private transient static final long serialVersionUID = 4834628902165082972L;
    private String time = null;
    private String className = null;
    private String methodName = null;
    private int retCod = 0;
    private String retId = null;
    private String retMsg = null;
    private Object result = null;
    private String successScript = "";
    private String retMsgCd = "";
    /**
     * COUNT
     */
    private int retCnt = 0;

    /**
     * ProcessResult
     */
    public ProcessResult() {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
    }

    /**
     * ProcessResult
     *
     * @param retId 리턴아이디
     */
    public ProcessResult(final String retId) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.retId = retId;
    }

    public ProcessResult(final String retId, final String retMsg) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.retId = retId;
        this.retMsg = retMsg;
    }

    public ProcessResult(final String calssName, final String methodName, final int retCod, final String retMsg) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.className = calssName;
        this.methodName = methodName;
        this.retCod = retCod;
        this.retMsg = retMsg;
    }

    public ProcessResult(final String calssName, final String methodName, final int retCod, final int retCnt, final String retMsg) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.className = calssName;
        this.methodName = methodName;
        this.retCod = retCod;
        this.retCnt = retCnt;
        this.retMsg = retMsg;
    }

    public ProcessResult(final String calssName, final String methodName, final int retCod, final String retMsg, final Object result) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.className = calssName;
        this.methodName = methodName;
        this.retCod = retCod;
        this.retMsg = retMsg;
        this.result = result;
    }

    public ProcessResult(final int retCod, final String retMsg) {
        super();
        this.time = AotDateUtils.getStringNow("yyyy-MM-dd-HH:mm");
        this.retCod = retCod;
        this.retMsg = retMsg;
    }

    /**
     * COUNT 설정
     *
     * @param retCnt COUNT
     */
    public void setRetCnt(final int retCnt) {
        this.retCnt = retCnt;
    }

    /**
     * COUNT 취득
     *
     * @return retCnt COUNT
     */
    public int getRetCnt() {
        return this.retCnt;
    }

    /**
     * 리턴코드 취득
     *
     * @return int 리턴코드
     */
    public int getRetCod() {
        return this.retCod;
    }

    /**
     * 리턴아이디 취득
     *
     * @return java.lang.String 리턴아이디
     */
    public java.lang.String getRetId() {
        return this.retId;
    }

    /**
     * 리턴메세지 취득
     *
     * @return java.lang.String 리턴메세지
     */
    public java.lang.String getRetMsg() {
        return this.retMsg;
    }

    /**
     * 결과 발생 시간 취득
     *
     * @return java.lang.String 결과 발생 시간
     */
    public java.lang.String getTime() {
        return this.time;
    }

    /**
     * 클래스명 취득
     *
     * @return java.lang.String 클래스명
     */
    public java.lang.String getClassName() {
        return this.className;
    }

    /**
     * 메소드명 취득
     *
     * @return java.lang.String 메소드명
     */
    public java.lang.String getMethodName() {
        return this.methodName;
    }

    /**
     * 결과OBJ 취득
     *
     * @return Object 결과OBJ
     */
    public Object getResult() {
        return this.result;
    }

    /**
     * 메세지 표시후에 처리할 함수 명칭 취득
     *
     * @return String 메세지 표시후에 처리할 함수 명칭
     */
    public String getSuccessScript() {
        return this.successScript;
    }

    /**
     * 메세지 표시후에 처리할 함수 명칭 취득
     *
     * @return String 메세지 표시후에 처리할 함수 명칭
     */
    public void setSuccessScript(final String str) {
        this.successScript = str;
    }

    /**
     * 메세지코드 취득
     *
     * @return String 메세지코드
     */
    public String getRetMsgCd() {
        return this.retMsgCd;
    }

    /**
     * 메세지코드 취득
     *
     * @return String 메세지코드
     */
    public void setRetMsgCd(final String str) {
        this.retMsgCd = str;
    }
}