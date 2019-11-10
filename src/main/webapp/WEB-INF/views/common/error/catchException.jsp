<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/error/catch403.jsp
 - 설    명	:	시스템에러시 표시하는화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.09.09    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" isErrorPage="true" %>

<%@include file="/WEB-INF/views/common/include.jsp" %>

<aot:html title="${TITLE}">

    <body>
    <div id="main" role="main">

        <!-- RIBBON -->

        <!-- END RIBBON -->

        <!-- MAIN CONTENT -->
        <div id="content">

            <!-- row -->
            <div class="row">

                <div class="col-sm-12">

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="text-center error-box">


                                <h1 class="error-text tada animated">
                                    <i class="fa fa-times-circle text-danger error-icon-shadow"></i>
                                    Error 500
                                </h1>
                                <h2 class="font-xl">
                                    <strong>Oooops, Something went wrong!</strong>
                                </h2>
                                <br/>
                                <p class="lead semi-bold">
                                    <strong>You have experienced a technical error. We apologize.</strong>
                                    <br>
                                    <br>
                                    <small> A RequestDispatcher could not be located for the default servlet 'WorkerServlet'] with root cause</small>
                                </p>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
            <!-- end row -->

        </div>
        <!-- END MAIN CONTENT -->

    </div>

    </body>

</aot:html>
