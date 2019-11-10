/*****************************************************************************
 * 프로그램명  : SelectTag.java
 * 설     명  : Select 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.14  LYS      1.0    초기작성
 *****************************************************************************/
package com.aot.pcfems.common.taglib;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Select 태그라이브러리 설정
 *
 * @author eaction
 * @version 1.0
 */
public class SelectTag extends TagSupport {
    private static final long serialVersionUID = 2200646789192303476L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectTag.class);
    /**
     * 셀렉트박스명칭프로퍼티
     */
    private String name = "";
    /**
     * 셀렉트박스ID프로퍼티
     */
    private String id = "";
    /**
     * 이벤트정의프로퍼티
     */
    private String event = "";
    /**
     * 스타일클래스프로퍼티지정
     */
    private String cssId = "";
    /**
     * 스타일SHEET프로퍼티
     */
    private String style = "";
    /**
     * 기타OPTION프로퍼티
     */
    private String option = "";
    /**
     * 초기값유무프로퍼티
     */
    private String init = "";
    /**
     * 초기값코드프로퍼티
     */
    private String initCode = "";
    /**
     * 초기값명칭프로퍼티
     */
    private String initName = "";
    /**
     * 선택값프로퍼티
     */
    private String selected = "";
    /**
     * OPTION대상리스트프로퍼티
     */
    private List<CodeInfo> list = null;
    /**
     * 시스템코드 처리 객체에서 리스트를 취득할 경우는 취득할 그룹코드를 지정한다
     */
    private String group = "";
    /**
     * 시스템코드 처리 객체에서 상위코드가 있는 리스트를 취득할 경우는 취득할 상위코드를 지정한다
     */
    private String upCode = "";
    /**
     * 체크후 항목 타이틀
     */
    private String fname = "";
    /**
     * OPTION에 코드포함여부
     */
    private String withCode = "";

    /**
     * 시작 태그 처리
     *
     * @return String SELECT태그문자열
     */
    @Override
    public int doStartTag() throws JspException {
        final JspWriter out = this.pageContext.getOut();
        final HttpSession session = this.pageContext.getSession();
        final UserVO userInfo = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

        // if (!"".equals(StringUtils.defaultString(this.upCode))) {
        // if (!"".equals(StringUtils.defaultString(this.group))) {
        // this.setSystemUpGroupCode();
        // }
        // } else {
        if (StringUtils.isNotEmpty(this.group)) {
            try {
                this.setSystemGroupCode(userInfo);
            } catch (final UserSysException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                throw new JspException(ExceptionUtils.getStackTrace(e));
            }
        }
        // }

        try {
            final StringBuffer sbSelect = new StringBuffer();

            sbSelect.append("<select name=\"");
            sbSelect.append(this.name);
            sbSelect.append("\" ");

            if (!"".equals(this.id)) {
                sbSelect.append("id=\"");
                sbSelect.append(this.id);
                sbSelect.append("\" ");
            }
            if (!"".equals(this.cssId)) {
                sbSelect.append("class=\"");
                sbSelect.append(this.cssId);
                sbSelect.append("\" ");
            }
            if (!"".equals(this.style)) {
                sbSelect.append("style=\"");
                sbSelect.append(this.style);
                sbSelect.append("\" ");
            }
            if (!"".equals(this.fname)) {
                sbSelect.append("fname=\"");
                sbSelect.append(this.fname);
                sbSelect.append("\" ");
            }
            if (CommonCode.YES.equals(this.init)) {
                sbSelect.append("init=\"");
                sbSelect.append(CommonCode.YES);
                sbSelect.append("\" ");
            }
            sbSelect.append(this.option);
            sbSelect.append(" ");
            sbSelect.append(this.event);
            sbSelect.append(" ");
            sbSelect.append(">");

            if (CommonCode.YES.equals(this.init)) {
                sbSelect.append("<option value=\"");
                sbSelect.append(this.initCode);
                sbSelect.append("\">");
                sbSelect.append(this.initName);
                sbSelect.append("</option>");
            }
            if (this.list != null) {
                CodeInfo codeInfo = null;
                for (int i = 0; i < this.list.size(); i++) {

                    codeInfo = this.list.get(i);

                    sbSelect.append("<option value=\"");
                    sbSelect.append(codeInfo.getCode());
                    sbSelect.append("\"");

                    if (StringUtils.defaultString(this.selected).equals(codeInfo.getCode())) {
                        sbSelect.append(" selected ");
                    }

                    sbSelect.append(" >");
                    if (this.withCode.equals(CommonCode.YES)) {
                        sbSelect.append("[");
                        sbSelect.append(codeInfo.getCode());
                        sbSelect.append("]");
                    }
                    if (!"".equals(codeInfo.getCodenm_k())) {
                        if (userInfo.getUser_lang().equals("KOR")) {
                            codeInfo.setName(codeInfo.getCodenm_k());
                        } else if (userInfo.getUser_lang().equals("ENG")) {
                            codeInfo.setName(codeInfo.getCodenm_e());
                        } else if (userInfo.getUser_lang().equals("JPN")) {
                            codeInfo.setName(codeInfo.getCodenm_j());
                        } else {
                            codeInfo.setName(codeInfo.getCodenm_k());
                        }
                    }

                    sbSelect.append(codeInfo.getName());
                    sbSelect.append("</option>");
                }
            }
            sbSelect.append("</select>");

            out.print(sbSelect.toString());

        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return SKIP_BODY;
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     *
     * @param userInfo 유저정보데이터
     * @throws UserSysException
     */
    private void setSystemGroupCode(final UserVO userInfo) throws UserSysException {
        this.setList(CodeTableMng.getCodeListByGrcode(userInfo, this.group));
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     *
     * @param userInfo
     *            유저정보데이터
     */
    // private void setSystemUpGroupCode() {
    // this.setList(UpCodeTableMng.getCodeList(this.group, this.upCode));
    // }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     *
     * @param userInfo 유저정보데이터
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하 여설정한다
     *
     * @param userInfo 유저정보데이터
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * 셀렉트박스명칭프로퍼티 취득
     *
     * @return String 셀렉트박스명칭프로퍼티
     */
    public String getName() {
        return this.name;
    }

    /**
     * 셀렉트박스명칭프로퍼티 설정
     *
     * @param str 셀렉트박스명칭프로퍼티
     */
    public void setName(final String str) {
        this.name = str;
    }

    /**
     * 이벤트정의프로퍼티 취득
     *
     * @return String 이벤트정의프로퍼티
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * 이벤트정의프로퍼티 설정
     *
     * @param str 이벤트정의프로퍼티
     */
    public void setEvent(final String str) {
        this.event = str;
    }

    /**
     * 스타일클래스프로퍼티 취득
     *
     * @return String 스타일클래스프로퍼티
     */
    public String getCssId() {
        return this.cssId;
    }

    /**
     * 스타일클래스프로퍼티 설정
     *
     * @param str 스타일클래스프로퍼티
     */
    public void setCssId(final String str) {
        this.cssId = str;
    }

    /**
     * 스타일SHEET프로퍼티 취득
     *
     * @return String 스타일SHEET프로퍼티
     */
    public String getStyle() {
        return this.style;
    }

    /**
     * 스타일SHEET프로퍼티 설정
     *
     * @param str 스타일SHEET프로퍼티
     */
    public void setStyle(final String str) {
        this.style = str;
    }

    /**
     * 기타OPTION프로퍼티 취득
     *
     * @return String 기타OPTION프로퍼티
     */
    public String getOption() {
        return this.option;
    }

    /**
     * 기타OPTION프로퍼티 설정
     *
     * @param str 기타OPTION프로퍼티
     */
    public void setOption(final String str) {
        this.option = str;
    }

    /**
     * 초기값유무프로퍼티 취득
     *
     * @return String 초기값유무프로퍼티
     */
    public String getInit() {
        return this.init;
    }

    /**
     * 초기값유무프로퍼티 설정
     *
     * @param str 초기값유무프로퍼티
     */
    public void setInit(final String str) {
        this.init = str;
    }

    /**
     * 초기값코드프로퍼티 취득
     *
     * @return String 초기값코드프로퍼티
     */
    public String getInitCode() {
        return this.initCode;
    }

    /**
     * 초기값코드프로퍼티 설정
     *
     * @param str 초기값코드프로퍼티
     */
    public void setInitCode(final String str) {
        this.initCode = str;
    }

    /**
     * 초기값명칭프로퍼티 취득
     *
     * @return String 초기값명칭프로퍼티
     */
    public String getInitName() {
        return this.initName;
    }

    /**
     * 초기값명칭프로퍼티 설정
     *
     * @param str 초기값명칭프로퍼티
     */
    public void setInitName(final String str) {
        this.initName = str;
    }

    /**
     * 선택값프로퍼티 취득
     *
     * @return String 선택값프로퍼티
     */
    public String getSelected() {
        return this.selected;
    }

    /**
     * 선택값프로퍼티 설정
     *
     * @param str 선택값프로퍼티
     */
    public void setSelected(final String str) {
        this.selected = str;
    }

    /**
     * OPTION대상리스트프로퍼티 취득
     *
     * @return List OPTION대상리스트프로퍼티
     */
    public List<CodeInfo> getList() {
        return this.list;
    }

    /**
     * OPTION대상리스트프로퍼티 설정
     *
     * @param obj OPTION대상리스트프로퍼티
     */
    public void setList(final List<CodeInfo> obj) {
        this.list = obj;
    }

    /**
     * 시스템그룹코드값프로퍼티 취득
     *
     * @return String 시스템그룹코드값프로퍼티
     */
    public String getGroup() {
        return this.group;
    }

    /**
     * 시스템그룹코드값프로퍼티 설정
     *
     * @param str 시스템그룹코드값프로퍼티
     */
    public void setGroup(final String str) {
        this.group = str;
    }

    /**
     * 상위코드값프로퍼티 취득
     *
     * @return String 상위코드값프로퍼티
     */
    public String getUpCode() {
        return this.upCode;
    }

    /**
     * 상위코드값프로퍼티 설정
     *
     * @param str 상위코드값프로퍼티
     */
    public void setUpCode(final String str) {
        this.upCode = str;
    }

    /**
     * 항목명칭값프로퍼티 취득
     *
     * @return String 항목명칭값프로퍼티
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * 항목명칭값프로퍼티 설정
     *
     * @param str 항목명칭값프로퍼티
     */
    public void setFname(final String str) {
        this.fname = str;
    }

    /**
     * OPTION에 코드포함여부 프로퍼티 취득
     *
     * @return String OPTION에 코드포함여부 프로퍼티
     */
    public String getWithCode() {
        return this.withCode;
    }

    /**
     * OPTION에 코드포함여부 프로퍼티 설정
     *
     * @param str OPTION에 코드포함여부 프로퍼티
     */
    public void setWithCode(final String str) {
        this.withCode = str;
    }

}
