/*****************************************************************************
 * 프로그램명  : UsageSearchInfo.java
 * 설     명  : 통계관리>사용량추이(Cpu,Mem,Interface) 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.27   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.usage;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 사용량추이화면 검색조건 데이터 빈
 *
 * @author aot
 * @version 1.0
 */
public class UsageSearchInfo implements Serializable {
    private static final long serialVersionUID = 2542346212677679614L;

    /**
     * 사용자 언어
     */
    private String user_lang = "";

    /**
     * 검색용SBC노드아이피
     */
    private String srch_sbc_node_ip = "";

    /**
     * 검색용새로고침주기
     */
    private String srch_reload = "";

    /**
     * 사용자언어 설정
     *
     * @param user_lang 사용자언어
     */
    public String getUser_lang() {
        return this.user_lang;
    }

    /**
     * 사용자언어 취득
     *
     * @return user_lang 우편번호
     */
    public void setUser_lang(final String user_lang) {
        this.user_lang = user_lang;
    }

    /**
     * 검색용SBC노드IP 설정
     *
     * @param srch_sbc_node_ip 검색용SBC노드IP
     */
    public void setSrch_sbc_node_ip(final String srch_sbc_node_ip) {
        this.srch_sbc_node_ip = srch_sbc_node_ip;
    }

    /**
     * 검색용SBC노드IP 취득
     *
     * @return srch_sbc_node_ip 검색용SBC노드IP
     */
    public String getSrch_sbc_node_ip() {
        return StringUtils.defaultString(this.srch_sbc_node_ip);
    }

    /**
     * 검색용새로고침주기 설정
     *
     * @param srch_reload 검색용새로고침주기
     */
    public void setSrch_reload(final String srch_reload) {
        this.srch_reload = StringUtils.defaultString(srch_reload);
    }

    /**
     * 검색용새로고침주기 취득
     *
     * @return srch_reload 검색용새로고침주기
     */
    public String getSrch_reload() {
        return this.srch_reload;
    }

}