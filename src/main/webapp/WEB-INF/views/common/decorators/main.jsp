<%----------------------------------------------------------------------------------------
* @program		:	main.jsp
* @description	:	sitemesh를 사용한 메인 Page
* @author		:	KYM
* @create		:	2017/12/09
* @update		:	
* @version		:	1.0
* @descripiton	:	
------------------------------------------------------------------------------------------%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<c:set var="TITLE">
    <spring:message code="TEXT.TOP_MAIN_TITLE"/>
</c:set>

<aot:html title="${TITLE}">

    <body class="smart-style-0">

    <page:applyDecorator name="panel" page="/top.do"/>

    <page:applyDecorator name="panel" page="/left.do"/>

    <decorator:body/>

    <page:applyDecorator name="panel" page="/footer.do"/>

    <c:if test="${retMsg ne null }">
        <script>
            AotSmartAdmin.smallBoxInfo('${retMsg }');
        </script>
    </c:if>
    </body>
</aot:html>