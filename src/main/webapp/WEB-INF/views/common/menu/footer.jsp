<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/footer.jsp
 - 설    명	:	풋터화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2008.11.12    1.0       JSM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<style>
    /* Sticky footer styles
    -------------------------------------------------- */
    html {
        position: relative;
        min-height: 100%;
    }

    body {
        /* Margin bottom by footer height */
        margin-bottom: 60px;
    }

    /* Custom page CSS
    -------------------------------------------------- */
    /* Not required for template or sticky footer method. */
    .container .text-muted {
        margin: 20px 0;
    }

    .footer > .container {
        padding-right: 15px;
        padding-left: 15px;
    }

    .page-footer {
        position: absolute;
        bottom: 0;
        width: 100%;
        padding-left: 0;
    }
</style>
<div class="page-footer">
    <div class="container text-center">
		<span class="txt-color-white">
			${PROJECT_TITLE }
			<span class="hidden-xs"> - Web Application Framework</span>
			© 2017-2019
		</span>
    </div>
</div>
