/*****************************************************************************
 * 프로그램명  : ParamInfo.java
 * 설     명  : 시스템파라메터 정보 데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2016.01.14    KYM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.basic;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 시스템파라메터 정보
 *
 * @author aot
 * @version 1.0
 */

public class ParamInfo implements Serializable {
    private transient static final long serialVersionUID = -5544594325442974335L;
    /**
     * 프로세스 그룹 (G0~G7)
     */
    private String proc_group = "";
    /**
     * 설정정보 Key
     */
    private String key_value = "";
    /**
     * CONFIG 설명
     */
    private String config_desc = "";
    /**
     * 설정정보 상세
     */
    private String param_value = "";
    /**
     * CONFIG 설명 (한국어)
     */
    private String config_desc_kor = "";
    /**
     * CONFIG 설명 (영어)
     */
    private String config_desc_eng = "";

    /**
     * 프로세스 그룹 (G0~G7) 설정
     *
     * @param proc_group 프로세스 그룹 (G0~G7)
     */
    public void setProc_group(final String proc_group) {
        this.proc_group = proc_group;
    }

    /**
     * 프로세스 그룹 (G0~G7) 취득
     *
     * @return proc_group 프로세스 그룹 (G0~G7)
     */
    public String getProc_group() {
        return StringUtils.defaultString(this.proc_group);
    }

    /**
     * 설정정보 Key 설정
     *
     * @param key_value 설정정보 Key
     */
    public void setKey_value(final String key_value) {
        this.key_value = key_value;
    }

    /**
     * 설정정보 Key 취득
     *
     * @return key_value 설정정보 Key
     */
    public String getKey_value() {
        return StringUtils.defaultString(this.key_value);
    }

    /**
     * CONFIG 설명 설정
     *
     * @param config_desc CONFIG 설명
     */
    public void setConfig_desc(final String config_desc) {
        this.config_desc = config_desc;
    }

    /**
     * CONFIG 설명 취득
     *
     * @return config_desc CONFIG 설명
     */
    public String getConfig_desc() {
        return StringUtils.defaultString(this.config_desc);
    }

    /**
     * 설정정보 상세 설정
     *
     * @param param_value 설정정보 상세
     */
    public void setParam_value(final String param_value) {
        this.param_value = param_value;
    }

    /**
     * 설정정보 상세 취득
     *
     * @return param_value 설정정보 상세
     */
    public String getParam_value() {
        return StringUtils.defaultString(this.param_value);
    }

    /**
     * CONFIG 설명 (한국어) 설정
     *
     * @param config_desc_kor CONFIG 설명 (한국어)
     */
    public void setConfig_desc_kor(final String config_desc_kor) {
        this.config_desc_kor = config_desc_kor;
    }

    /**
     * CONFIG 설명 (한국어) 취득
     *
     * @return config_desc_kor CONFIG 설명 (한국어)
     */
    public String getConfig_desc_kor() {
        return StringUtils.defaultString(this.config_desc_kor);
    }

    /**
     * CONFIG 설명 (영어) 설정
     *
     * @param config_desc_eng CONFIG 설명 (영어)
     */
    public void setConfig_desc_eng(final String config_desc_eng) {
        this.config_desc_eng = config_desc_eng;
    }

    /**
     * CONFIG 설명 (영어) 취득
     *
     * @return config_desc_eng CONFIG 설명 (영어)
     */
    public String getConfig_desc_eng() {
        return StringUtils.defaultString(this.config_desc_eng);
    }
}
