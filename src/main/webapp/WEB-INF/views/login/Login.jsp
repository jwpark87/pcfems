<%----------------------------------------------------------------------------------------
 - 파일이름	:	login/Login.jsp
 - 설    명	:	로그인 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.12.27    1.0       KYM      
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<c:set var="TITLE">
    <spring:message code="TEXT.TOP_MAIN_TITLE"/>
</c:set>
<aot:html title="${TITLE}" outdatebrowser="YES">

    <body class="animated fadeInDown">
    <header id="header">

        <div id="logo-group">
			<span id="logo" style="margin-top: 13px;">
				<!-- <img src="img/logo.png" alt="SBCSTCS">  -->
                <!-- <h1 class="txt-color-red login-header-big">PCF-EMS</h1> -->
			</span>
        </div>

    </header>

    <div id="main" role="main">

        <!-- MAIN CONTENT -->
        <div id="content" class="container">

            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
                    <h1 class="txt-color-red login-header-big">${PROJECT_TITLE }</h1>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
                    <div class="well no-padding">
                        <form id="login-form" action="${CONTEXT_PATH}/login.go" method="POST" class="smart-form client-form">
                            <input type="hidden" name="actionID" value="LOGIN_OK">
                            <header>
                                <spring:message code='TEXT.LOGIN_BTN'/>
                            </header>

                            <fieldset>

                                <section>
                                    <label class="label">ID</label>
                                    <label class="input">
                                        <i class="icon-append fa fa-user"></i>
                                        <input type="id" name="id">
                                        <b class="tooltip tooltip-top-right">
                                            <i class="fa fa-user txt-color-teal"></i>
                                            <spring:message code='MSG.ERROR.USER_ID'/>
                                        </b>
                                    </label>
                                </section>

                                <section>
                                    <label class="label">Password</label>
                                    <label class="input">
                                        <i class="icon-append fa fa-lock"></i>
                                        <input type="hidden" name="password" id="password"/>
                                        <input type="password" name="password2" id="password2">
                                        <b class="tooltip tooltip-top-right">
                                            <i class="fa fa-lock txt-color-teal"></i>
                                            <spring:message code='MSG.ERROR.PASSWORD'/>
                                        </b>
                                    </label>
                                    <div class="note">
                                        &nbsp;
                                        <!-- <a href="forgotpassword.html">Forgot password?</a> -->
                                    </div>
                                </section>

                                <!-- <section>
                                        <label class="checkbox">
                                            <input type="checkbox" name="remember" checked="">
                                            <i></i>Stay signed in</label>
                                    </section> -->
                            </fieldset>
                            <footer>
                                <button type="button" class="btn btn-primary" id="login">
                                    <spring:message code='TEXT.LOGIN_BTN'/>
                                </button>
                            </footer>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${PATH_PLUGIN}/crypto-js/crypto-js.min.js"></script>
    <script src="${PATH_PLUGIN}/crypto-js/sha256.min.js"></script>
    <!--================================================== -->
    <script type="text/javascript">
        jQuery(document).ready(function () {
            $('html').attr('id', 'extr-page');
            <c:if test="${ERROR eq 'error'}">
            //(DB상태조회(DB접속정보) 취득시 에러가 발생했습니다.
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ERROR.PROCESS'/>");
            </c:if>

            <c:choose>
            <c:when test="${LOGIN_RESULT eq 'LOGIN_FAIL'}">
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ERROR.LOGIN_FAIL'/>");
            </c:when>
            <c:when test="${LOGIN_RESULT eq 'LOGIN_NEED'}">
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ERROR.LOGIN_NEED'/>");
            </c:when>
            <c:when test="${LOGIN_RESULT eq 'LOGIN_ADMIN_NEED'}">
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ERROR.LOGIN_ADMIN_NEED'/>");
            </c:when>
            </c:choose>
            $("input[name=id]").focus();

            $("input[name=password2]").on('keydown', function (key) {
                if (key.keyCode === 13) {//키가 13이면 실행 (엔터는 13)
                    $("#login").trigger('click');
                }
            });

            $("#login").on('click', function () {
                $("#login-form").submit();
            });
            runAllForms();
        });

        var errorClass = 'invalid';
        var errorElement = 'em';
        $(function () {
            // Validation
            $("#login-form").validate({
                errorClass: errorClass,
                errorElement: errorElement,
                highlight: function (element) {
                    $(element).parent().removeClass('state-success').addClass("state-error");
                    $(element).removeClass('valid');
                },
                unhighlight: function (element) {
                    $(element).parent().removeClass("state-error").addClass('state-success');
                    $(element).addClass('valid');
                },
                submitHandler: function () {
                    $("#password").val(CryptoJS.SHA256($("#password2").val()).toString());
                    $("#password2").val("");

                    return true;

                },
                // Rules for form validation
                rules: {
                    id: {
                        required: true
                    },
                    password2: {
                        required: true,
                        maxlength: 20
                    }
                },

                // Messages for form validation
                messages: {
                    id: {
                        required: '<spring:message code='MSG.ERROR.USER_ID'/>'
                    },
                    password2: {
                        required: '<spring:message code='MSG.ERROR.PASSWORD'/>'
                    }
                },
                // Do not change code below
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                }
            });
        });
    </script>

    </body>
</aot:html>