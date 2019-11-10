<%----------------------------------------------------------------------------------------
 - 파일이름	:	login/Login.jsp
 - 설    명	:	로그인 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2008.09.29    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>

<%@include file="/WEB-INF/views/common/include.jsp" %>

<aot:html title="${TITLE}">
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/login.go">
        <input type="hidden" name="actionID" value="LOGIN_INIT">
        <input type="hidden" name="LOGIN_RESULT" value="LOGIN_NEED">
    </form>
    <script type="text/javascript">
        document.myForm.submit();
    </script>
</aot:html>
