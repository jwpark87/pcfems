/*****************************************************************************
 * 프로그램명  : SysCodVO.java
 * 설     명  : 시스템그룹코드 관리  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.10.23 Ventus    1.0
 *****************************************************************************/

package com.aot.pcfems.business.admin.syscod;

import com.aot.pcfems.common.jqGrid.JqGridVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

public class SysCodVO extends JqGridVO implements Serializable {
    private static final long serialVersionUID = 1687557933660896923L;

    /**
     * 시스템그룹코드코드 마스터 코드
     */

    private String grcode = "";

    private String grcodenm = "";
    /**
     * display
     */
    private String dspYn = "";

    /**
     * 삭제할키리스트
     */
    private String[] keyNum = null;

    /**
     * 모드
     */
    private String mode = "";

    /**
     * 그룹코드영어
     */
    private String grcodenm_e = "";
    /**
     * 그룹코드한국어
     */
    private String grcodenm_k = "";
    /**
     * 그룹코드일어
     */
    private String grcodenm_j = "";

    private String upd_dt = "";
    private String upd_id = "";

    public String getUpd_dt() {
        return this.upd_dt;
    }

    public void setUpd_dt(final String upd_dt) {
        this.upd_dt = upd_dt;
    }

    public String getUpd_id() {
        return this.upd_id;
    }

    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    /**
     * 그룹코드영어 설정
     *
     * @param grcodenm_e 그룹코드영어
     */
    public void setGrcodenm_e(final String grcodenm_e) {
        this.grcodenm_e = grcodenm_e;
    }

    /**
     * 그룹코드영어 취득
     *
     * @return grcodenm_e 그룹코드영어
     */
    public String getGrcodenm_e() {
        return StringUtils.defaultString(this.grcodenm_e);
    }

    /**
     * 그룹코드한국어 설정
     *
     * @param grcodenm_k 그룹코드한국어
     */
    public void setGrcodenm_k(final String grcodenm_k) {
        this.grcodenm_k = grcodenm_k;
    }

    /**
     * 그룹코드한국어 취득
     *
     * @return grcodenm_k 그룹코드한국어
     */
    public String getGrcodenm_k() {
        return StringUtils.defaultString(this.grcodenm_k);
    }

    /**
     * 그룹코드일어 설정
     *
     * @param grcodenm_j 그룹코드일어
     */
    public void setGrcodenm_j(final String grcodenm_j) {
        this.grcodenm_j = grcodenm_j;
    }

    /**
     * 그룹코드일어 취득
     *
     * @return grcodenm_j 그룹코드일어
     */
    public String getGrcodenm_j() {
        return StringUtils.defaultString(this.grcodenm_j);
    }

    public String getGrcode() {
        return this.grcode;
    }

    public void setGrcode(final String grcode) {
        this.grcode = StringUtils.defaultString(grcode);
    }

    public String getGrcodenm() {
        return this.grcodenm;
    }

    public void setGrcodenm(final String grcodenm) {
        this.grcodenm = StringUtils.defaultString(grcodenm);
    }

    public String getDspYn() {
        return this.dspYn;
    }

    public void setDspYn(final String dspYn) {
        this.dspYn = StringUtils.defaultString(dspYn);
    }

    /**
     * 삭제키 값들 설정
     *
     * @param setKeyNum the setKeyNum to set
     */
    public void setKeyNum(final String[] keyNum) {
        this.keyNum = Arrays.copyOf(keyNum, keyNum.length);
    }

    /**
     * 삭제키 값들 취득
     *
     * @return the setKeyNum
     */
    public String[] getKeyNum() {
        return Arrays.copyOf(this.keyNum, this.keyNum.length);
    }

    /**
     * 모드 설정
     *
     * @param mode 모드
     */
    public void setMode(final String mode) {
        this.mode = StringUtils.defaultString(mode);
    }

    /**
     * 모드 취득
     *
     * @return mode 모드
     */
    public String getMode() {
        return this.mode;
    }

    private String dtlGrcode = "";
    private String code = "";
    private String codenm = "";
    private String modiYn = "";
    private String childGrcode = "";
    private String seq = "";

    /**
     * 시스템그룹코드 관리 상세코드
     */
    private String levelcod = "";
    /**
     * 시스템그룹코드 관리 상세코드명
     */
    private String levelcodNm = "";
    /**
     * 사용여부코드
     */
    private String use_yn = "";
    /**
     * 사용여부코드
     */
    private String use_yn_nm = "";

    /**
     * 삭제할키리스트
     */
    private String[] keyDtlNum = null;

    /**
     * 그룹코드
     */
    private String group_code = "";
    /**
     * 기본코드
     */
    private String basic_code = "";
    /**
     * 코드명-한국어
     */
    private String codenm_k = "";
    /**
     * 코드명-일본어
     */
    private String codenm_j = "";
    /**
     * 코드명-영어
     */
    private String codenm_e = "";
    /**
     * 최대졍렬값
     */
    private String max_sort_no = "";
    /**
     * 졍렬값
     */
    private String sort_seq = "";

    public String getUse_yn_nm() {
        return this.use_yn_nm;
    }

    public void setUse_yn_nm(final String use_yn_nm) {
        this.use_yn_nm = use_yn_nm;
    }

    public String getSort_seq() {
        return this.sort_seq;
    }

    public void setSort_seq(final String sort_seq) {
        this.sort_seq = sort_seq;
    }

    public String getUse_yn() {
        return this.use_yn;
    }

    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
    }

    /**
     * 최대졍렬값 설정
     *
     * @param max_sort_no 최대졍렬값
     */
    public void setMax_sort_no(final String max_sort_no) {
        this.max_sort_no = max_sort_no;
    }

    /**
     * 최대졍렬값 취득
     *
     * @return max_sort_no 최대졍렬값
     */
    public String getMax_sort_no() {
        return StringUtils.defaultString(this.max_sort_no);
    }

    /**
     * 코드명-한국어 설정
     *
     * @param codenm_k 코드명-한국어
     */
    public void setCodenm_k(final String codenm_k) {
        this.codenm_k = codenm_k;
    }

    /**
     * 코드명-한국어 취득
     *
     * @return codenm_k 코드명-한국어
     */
    public String getCodenm_k() {
        return StringUtils.defaultString(this.codenm_k);
    }

    /**
     * 코드명-일본어 설정
     *
     * @param codenm_j 코드명-일본어
     */
    public void setCodenm_j(final String codenm_j) {
        this.codenm_j = codenm_j;
    }

    /**
     * 코드명-일본어 취득
     *
     * @return codenm_j 코드명-일본어
     */
    public String getCodenm_j() {
        return StringUtils.defaultString(this.codenm_j);
    }

    /**
     * 코드명-영어 설정
     *
     * @param codenm_e 코드명-영어
     */
    public void setCodenm_e(final String codenm_e) {
        this.codenm_e = codenm_e;
    }

    /**
     * 코드명-영어 취득
     *
     * @return codenm_e 코드명-영어
     */
    public String getCodenm_e() {
        return StringUtils.defaultString(this.codenm_e);
    }

    /**
     * 기본코드 설정
     *
     * @param basic_code 기본코드
     */
    public void setBasic_code(final String basic_code) {
        this.basic_code = basic_code;
    }

    /**
     * 기본코드 취득
     *
     * @return basic_code 기본코드
     */
    public String getBasic_code() {
        return StringUtils.defaultString(this.basic_code);
    }

    /**
     * 그룹코드 설정
     *
     * @param group_code 그룹코드
     */
    public void setGroup_code(final String group_code) {
        this.group_code = group_code;
    }

    /**
     * 그룹코드 취득
     *
     * @return group_code 그룹코드
     */
    public String getGroup_code() {
        return StringUtils.defaultString(this.group_code);
    }

    /**
     * @return the grcode
     */
    public String getDtlGrcode() {
        return this.dtlGrcode;
    }

    /**
     * @param grcode the grcode to set
     */
    public void setDtlGrcode(final String dtlGrcode) {
        this.dtlGrcode = StringUtils.defaultString(dtlGrcode);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(final String code) {
        this.code = StringUtils.defaultString(code);
    }

    /**
     * @return the codenm
     */
    public String getCodenm() {
        return this.codenm;
    }

    /**
     * @param codenm the codenm to set
     */
    public void setCodenm(final String codenm) {
        this.codenm = StringUtils.defaultString(codenm);
    }

    /**
     * @return the modiYn
     */
    public String getModiYn() {
        return this.modiYn;
    }

    /**
     * @param modiYn the modiYn to set
     */
    public void setModiYn(final String modiYn) {
        this.modiYn = StringUtils.defaultString(modiYn);
    }

    /**
     * @return the childGrcode
     */
    public String getChildGrcode() {
        return this.childGrcode;
    }

    /**
     * @param childGrcode the childGrcode to set
     */
    public void setChildGrcode(final String childGrcode) {
        this.childGrcode = StringUtils.defaultString(childGrcode);
    }

    /**
     * 시스템그룹코드 관리 상세코드 취득
     *
     * @param str 시스템그룹코드 관리 상세코드(CODE)
     */
    public String getLevelcod() {
        return this.levelcod;
    }

    /**
     * 시스템그룹코드 관리 상세코드 설정
     *
     * @param str 시스템그룹코드 관리 상세코드(CODE)
     */
    public void setLevelcod(final String levelcod) {
        this.levelcod = StringUtils.defaultString(levelcod);
    }

    /**
     * 시스템그룹코드 관리 상세코드명 취득
     *
     * @param str 시스템그룹코드 관리 상세코드명(CODENM)
     */
    public String getLevelcodNm() {
        return this.levelcodNm;
    }

    /**
     * 시스템그룹코드 관리 상세코드명 설정
     *
     * @param str 시스템그룹코드 관리 상세코드명(CODENM)
     */
    public void setLevelcodNm(final String levelcodNm) {
        this.levelcodNm = StringUtils.defaultString(levelcodNm);
    }

    /**
     * 삭제키 값들 설정
     *
     * @param setKeyNum the setKeyNum to set
     */
    public void setKeyDtlNum(final String[] keyDtlNum) {
        this.keyDtlNum = Arrays.copyOf(keyDtlNum, keyDtlNum.length);
    }

    /**
     * 삭제키 값들 취득
     *
     * @return the setKeyNum
     */
    public String[] getKeyDtlNum() {
        return Arrays.copyOf(this.keyDtlNum, this.keyDtlNum.length);
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(final String seq) {
        this.seq = StringUtils.defaultString(seq);
    }

}
