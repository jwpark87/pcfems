/*****************************************************************************
 * 프로그램명  : CheckboxTag.java
 * 설     명  : Checkbox 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.08  KYM      1.0    초기작성
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
 * Checkbox 태그라이브러리 설정
 *
 * @author eaction
 * @version 1.0
 */
public class CheckboxTag extends TagSupport {
    private transient static final long serialVersionUID = -834820449977147965L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckboxTag.class);
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
    private String checked = "";
    /**
     * 초기값존재위치프로퍼티
     */
    private String place = "";
    /**
     * 값들사이의구분자프로퍼티
     */
    private String format = "";
    /**
     * OPTION대상리스트프로퍼티
     */
    private List<CodeInfo> list = null;
    /**
     * 시스템코드 처리 객체에서 리스트를 취득할 경우는 취득할 그룹코드를 지정한다
     */
    private String group = "";
    /**
     * 라디오버튼 inline
     */
    private String inline = "";

    /**
     * 시작 태그 처리
     *
     * @return String checkbox태그문자열
     */
    @Override
    public int doStartTag() throws JspException {
        final JspWriter out = this.pageContext.getOut();

        if (!"".equals(StringUtils.defaultString(this.group))) {
            final HttpSession session = this.pageContext.getSession();
            final UserVO userInfo = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);
            try {
                this.setSystemGroupCode(userInfo);
            } catch (final UserSysException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                throw new JspException(ExceptionUtils.getStackTrace(e));
            }
        }

        try {
            final StringBuffer sbCheckbox = new StringBuffer();

            if (CommonCode.YES.equals(this.init)) {
                if ("BEFORE".equals(this.place)) {

                    // 인라인 뷰
                    if (CommonCode.YES.equals(this.inline)) {
                        sbCheckbox.append("<label class=\"checkbox\">");
                    } else {
                        sbCheckbox.append("<div class=\"col\">");
                        sbCheckbox.append("<label class=\"checkbox\">");
                    }

                    sbCheckbox.append("<input type=\"checkbox\" name=\"");
                    sbCheckbox.append(this.name);
                    sbCheckbox.append("\" ");
                    if (!"".equals(this.id)) {
                        sbCheckbox.append("id=\"");
                        sbCheckbox.append(this.id);
                        sbCheckbox.append("\"");
                    }
                    sbCheckbox.append(" value=\"");
                    sbCheckbox.append(this.initCode);
                    sbCheckbox.append("\" ");
                    if (this.checked.equals(this.initCode)) {
                        sbCheckbox.append("checked ");
                    }
                    sbCheckbox.append(this.getCommonString());
                    sbCheckbox.append("<i></i>");
                    sbCheckbox.append(this.initName);
                    sbCheckbox.append("&nbsp;&nbsp;");
                    sbCheckbox.append(this.format);

                    // 인라인 뷰
                    if (CommonCode.YES.equals(this.inline)) {
                        sbCheckbox.append("</label>");
                    } else {
                        sbCheckbox.append("</label>");
                        sbCheckbox.append("</div>");
                    }
                }
            }

            if (this.list != null) {
                CodeInfo codeInfo = null;
                for (int i = 0; i < this.list.size(); i++) {

                    codeInfo = this.list.get(i);

                    // 인라인 뷰
                    if (CommonCode.YES.equals(this.inline)) {
                        sbCheckbox.append("<label class=\"checkbox\">");
                    } else {
                        sbCheckbox.append("<div class=\"col\">");
                        sbCheckbox.append("<label class=\"checkbox\">");
                    }

                    sbCheckbox.append("<input type=\"checkbox\" name=\"");
                    sbCheckbox.append(this.name);
                    sbCheckbox.append("\" ");
                    if (!"".equals(this.id)) {
                        sbCheckbox.append("id=\"");
                        sbCheckbox.append(this.id);
                        sbCheckbox.append("\"");
                    }
                    sbCheckbox.append(" value=\"");
                    sbCheckbox.append(codeInfo.getCode());
                    sbCheckbox.append("\" ");
                    if (this.checked.equals(codeInfo.getCode())) {
                        sbCheckbox.append(" checked ");
                    }
                    sbCheckbox.append(this.getCommonString());
                    sbCheckbox.append("<i></i>");
                    sbCheckbox.append(codeInfo.getCodeName());
                    sbCheckbox.append("&nbsp;&nbsp;");
                    if (i < this.list.size() - 1) {
                        sbCheckbox.append(this.format);
                    }
                    // 인라인 뷰
                    if (CommonCode.YES.equals(this.inline)) {
                        sbCheckbox.append("</label>");
                    } else {
                        sbCheckbox.append("</label>");
                        sbCheckbox.append("</div>");
                    }
                }
            }

            out.print(sbCheckbox.toString());

        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return SKIP_BODY;
    }

    /**
     * 공통으로 들어갈 문자열들을 취득한다
     *
     * @return String 공통문자열
     */
    private String getCommonString() {
        final StringBuffer sbCheckbox = new StringBuffer();

        sbCheckbox.append(this.event);
        sbCheckbox.append(" ");
        if (!"".equals(this.cssId)) {
            sbCheckbox.append("class=\"");
            sbCheckbox.append(this.cssId);
            sbCheckbox.append("\" ");
        }
        if (!"".equals(this.style)) {
            sbCheckbox.append("style=\"");
            sbCheckbox.append(this.style);
            sbCheckbox.append("\" ");
        }
        sbCheckbox.append(this.option);
        sbCheckbox.append(">");

        return sbCheckbox.toString();
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
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
    public String getChecked() {
        return this.checked;
    }

    /**
     * 선택값프로퍼티 설정
     *
     * @param str 선택값프로퍼티
     */
    public void setChecked(final String str) {
        this.checked = str;
    }

    /**
     * 초기값존재위치프로퍼티 취득
     *
     * @return String 초기값존재위치프로퍼티
     */
    public String getPlace() {
        return this.place;
    }

    /**
     * 초기값존재위치프로퍼티 설정
     *
     * @param str 초기값존재위치프로퍼티
     */
    public void setPlace(final String str) {
        this.place = str;
    }

    /**
     * 값들사이의구분자프로퍼티 취득
     *
     * @return String 값들사이의구분자프로퍼티
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * 값들사이의구분자프로퍼티 설정
     *
     * @param str 값들사이의구분자프로퍼티
     */
    public void setFormat(final String str) {
        this.format = str;
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
     * 라디오버튼 inline 설정
     *
     * @param inline 라디오버튼 inline
     */
    public void setInline(final String inline) {
        this.inline = inline;
    }

    /**
     * 라디오버튼 inline 취득
     *
     * @return inline 라디오버튼 inline
     */
    public String getInline() {
        return StringUtils.defaultString(this.inline);
    }
}
