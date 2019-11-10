<%----------------------------------------------------------------------------------------
 - 파일이름	:	inti/init.jsp
 - 설    명	:	첫화면
 - 추가내용   :   
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2015.12.09    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <div id="main" role="main">
        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>Home></li>
                <li>Forms</li>
                <li>Smart Form Layouts</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        <div id="content">
            <form name="myForm" id="myForm" method="POST" action="${CONTEXT_PATH}/main.do" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="actionID">
                <input type="hidden" name="root" value="${ROOT_MENU}">
                <input type="hidden" name="menuUrl" value="${MENU_URL}">

                <div class="row">
                    <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
                        <h1 class="page-title txt-color-blueDark">

                            <!-- PAGE HEADER -->
                            <i class="fa-fw fa fa-pencil-square-o"></i>
                            Forms
                            <span>> Form Layouts </span>
                        </h1>
                    </div>


                </div>


            </form>
        </div>

    </div>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    </body>
</aot:html>