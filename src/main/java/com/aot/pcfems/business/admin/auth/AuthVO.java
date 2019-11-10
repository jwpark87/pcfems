/*****************************************************************************
 * 프로그램명  : Auth.java
 * 설     명  : 권한레벨관리  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2016.03.08 KYM    1.0
 *****************************************************************************/

package com.aot.pcfems.business.admin.auth;

import com.aot.pcfems.common.model.UserVO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AuthVO extends UserVO implements Serializable {
    private transient static final long serialVersionUID = -8161049155397571117L;
    /**
     * 권한코드
     */
    private String auth_code = "";
    /**
     * 권한이름-한글
     */
    private String auth_nm_kor = "";
    /**
     * 권한이름-영어
     */
    private String auth_nm_eng = "";
    /**
     * 권한이름-일어
     */
    private String auth_nm_jpn = "";
    /**
     * 정렬순서
     */
    private int sort_no = 0;
    /**
     * 사용여부
     */
    private String use_yn = "";
    /**
     * 생성자
     */
    private String crt_id = "";
    /**
     * 수정자
     */
    private String upd_id = "";
    /**
     * 생성일시
     */
    private String crt_dt = "";
    /**
     * 수정일시
     */
    private String upd_dt = "";
    /**
     * 권한이름
     */
    private String auth_nm = "";
    /**
     * 수정자이름
     */
    private String upd_nm = "";

    /**
     * 수정자이름 설정
     *
     * @param upd_nm 수정자이름
     */
    public void setUpd_nm(final String upd_nm) {
        this.upd_nm = upd_nm;
    }

    /**
     * 수정자이름 취득
     *
     * @return upd_nm 수정자이름
     */
    public String getUpd_nm() {
        return StringUtils.defaultString(this.upd_nm);
    }

    /**
     * 권한이름 설정
     *
     * @param auth_nm 권한이름
     */
    public void setAuth_nm(final String auth_nm) {
        this.auth_nm = auth_nm;
    }

    /**
     * 권한이름 취득
     *
     * @return auth_nm 권한이름
     */
    public String getAuth_nm() {
        return StringUtils.defaultString(this.auth_nm);
    }

    /**
     * 권한코드 설정
     *
     * @param auth_code 권한코드
     */
    public void setAuth_code(final String auth_code) {
        this.auth_code = auth_code;
    }

    /**
     * 권한코드 취득
     *
     * @return auth_code 권한코드
     */
    public String getAuth_code() {
        return StringUtils.defaultString(this.auth_code);
    }

    /**
     * 권한이름-한글 설정
     *
     * @param auth_nm_kor 권한이름-한글
     */
    public void setAuth_nm_kor(final String auth_nm_kor) {
        this.auth_nm_kor = auth_nm_kor;
    }

    /**
     * 권한이름-한글 취득
     *
     * @return auth_nm_kor 권한이름-한글
     */
    public String getAuth_nm_kor() {
        return StringUtils.defaultString(this.auth_nm_kor);
    }

    /**
     * 권한이름-영어 설정
     *
     * @param auth_nm_eng 권한이름-영어
     */
    public void setAuth_nm_eng(final String auth_nm_eng) {
        this.auth_nm_eng = auth_nm_eng;
    }

    /**
     * 권한이름-영어 취득
     *
     * @return auth_nm_eng 권한이름-영어
     */
    public String getAuth_nm_eng() {
        return StringUtils.defaultString(this.auth_nm_eng);
    }

    /**
     * 권한이름-일어 설정
     *
     * @param auth_nm_jpn 권한이름-일어
     */
    public void setAuth_nm_jpn(final String auth_nm_jpn) {
        this.auth_nm_jpn = auth_nm_jpn;
    }

    /**
     * 권한이름-일어 취득
     *
     * @return auth_nm_jpn 권한이름-일어
     */
    public String getAuth_nm_jpn() {
        return StringUtils.defaultString(this.auth_nm_jpn);
    }

    /**
     * 정렬순서 설정
     *
     * @param sort_no 정렬순서
     */
    public void setSort_no(final int sort_no) {
        this.sort_no = sort_no;
    }

    /**
     * 정렬순서 취득
     *
     * @return sort_no 정렬순서
     */
    public int getSort_no() {
        return this.sort_no;
    }

    /**
     * 사용여부 설정
     *
     * @param use_yn 사용여부
     */
    public void setUse_yn(final String use_yn) {
        this.use_yn = use_yn;
    }

    /**
     * 사용여부 취득
     *
     * @return use_yn 사용여부
     */
    public String getUse_yn() {
        return StringUtils.defaultString(this.use_yn);
    }

    /**
     * 생성자 설정
     *
     * @param crt_id 생성자
     */
    @Override
    public void setCrt_id(final String crt_id) {
        this.crt_id = crt_id;
    }

    /**
     * 생성자 취득
     *
     * @return crt_id 생성자
     */
    @Override
    public String getCrt_id() {
        return StringUtils.defaultString(this.crt_id);
    }

    /**
     * 수정자 설정
     *
     * @param upd_id 수정자
     */
    @Override
    public void setUpd_id(final String upd_id) {
        this.upd_id = upd_id;
    }

    /**
     * 수정자 취득
     *
     * @return upd_id 수정자
     */
    @Override
    public String getUpd_id() {
        return StringUtils.defaultString(this.upd_id);
    }

    /**
     * 생성일시 설정
     *
     * @param crt_dt 생성일시
     */
    @Override
    public void setCrt_dt(final String crt_dt) {
        this.crt_dt = crt_dt;
    }

    /**
     * 생성일시 취득
     *
     * @return crt_dt 생성일시
     */
    @Override
    public String getCrt_dt() {
        return StringUtils.defaultString(this.crt_dt);
    }

    /**
     * 수정일시 설정
     *
     * @param upd_dt 수정일시
     */
    @Override
    public void setUpd_dt(final String upd_dt) {
        this.upd_dt = upd_dt;
    }

    /**
     * 수정일시 취득
     *
     * @return upd_dt 수정일시
     */
    @Override
    public String getUpd_dt() {
        return StringUtils.defaultString(this.upd_dt);
    }

}
