<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/emp/Add.jsp
 - 설      명	: 사용자관리 등록 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2017.12.15    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.EMP.MGMT.SUBTITLE.EMP_MGMT_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="empAddForm" id="empAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_password" id="add_password" value="">
                <input type="hidden" name="add_checkId" id="add_checkId" value="N">

                <fieldset>
                    <div class="row">
                        <!-- 사용자 그룹 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_GROUP"/>
                            </label>
                            <label class="select">
                                <select name="add_user_group_id" id="add_user_group_id"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 아이디 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_ID"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" name="add_emp_id" id="add_emp_id">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="idDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 비밀번호 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="text" name="add_passwd1" id="add_passwd1">
                            </label>
                        </section>
                        <!-- 비밀번호 확인 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.REPASSWD"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="text" name="add_passwd2" id="add_passwd2">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 사용자이름 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_NAME"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" name="add_empNm" id="add_empNm">
                            </label>
                        </section>
                        <!-- 이메일 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMAIL"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-envelope-o"></i>
                                <input type="text" name="add_email" id="add_email">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 휴대폰 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.PHONE"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-phone"></i>
                                <input type="text" name="add_smsPhone" id="add_smsPhone" data-mask="999-999-9999" data-mask-placeholder="X">
                            </label>
                        </section>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <spring:message code="TEXT.CANCEL"/>
            </button>
            <button type="button" class="btn btn-primary" id="empAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script src="${PATH_PLUGIN}/crypto-js/crypto-js.min.js"></script>
<script src="${PATH_PLUGIN}/crypto-js/sha256.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

        AotAjax.getSelectOptions($('#add_user_group_id'), '/admin/emp/empPopup.do', {
            actionID: 'getUserGroupCodeInfo'
        }, {
            value: 'code',
            label: 'codenm_k'
        });

        //custom validation 추가
        $.validator.addMethod('idCheck', function (value, element) {
            return ($("#add_checkId").val() == "Y") ? true : false;
        }, '<spring:message code='MSG.EMP.MGMT.ALERT.EMPID_DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#empAddForm").validate({
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
            rules: {
                add_emp_id: {
                    required: true,
                    idCheck: true
                },
                add_passwd1: {
                    required: true
                },
                add_passwd2: {
                    required: true,
                    equalTo: '#add_passwd1'
                },
                add_empNm: {
                    required: true
                },
                add_email: {
                    email: true
                },
            },
            messages: {
                add_emp_id: {
                    required: '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_ID'/>'
                },
                add_passwd1: {
                    required: '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_PASSWORD'/>'
                },
                add_passwd2: {
                    required: '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_PASSWORD'/>',
                    equalTo: '<spring:message code='MSG.EMP.MGMT.ALERT.PASSWORD'/>'
                },
                add_empNm: {
                    required: '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_NAME'/>'
                },
                add_email: {
                    email: '<spring:message code='MSG.EMP.MGMT.ALERT.EMAIL'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/emp/empPopup.do", {
                    actionID: "ACTION_EMP_INSERT_OK",
                    user_group_id: $("#add_user_group_id").val(),
                    emp_id: $("#add_emp_id").val(),
                    password: CryptoJS.SHA256($("#add_passwd1").val()).toString(),
                    empNm: $("#add_empNm").val(),
                    email: $("#add_email").val(),
                    smsPhone: $("#add_smsPhone").val(),
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#empModal').modal('hide');
                        goEmpList();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            }

        });

        bindEvent();

    });

    //각 클릭 이벤트
    function bindEvent() {
        //중복 체크
        $("#idDupChk").on('click', function () {
            checkBasicUserId();
        });
        //저장
        $("#empAdd").on('click', function () {
            $("#empAddForm").submit();
        });
    }

    //아이디 중복확인
    function checkBasicUserId() {

        if ($("#add_emp_id").val()) {
            AotAjax.excute("${CONTEXT_PATH}/admin/emp/empPopup.do", {
                actionID: "ACTION_CHECK_USERID",
                id: $("#add_emp_id").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkId;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_emp_id").val() + " <spring:message code='MSG.EMP.MGMT.ALERT.ALREADY_EMPID'/>");
                    $("#add_checkId").val("N");
                    $("#add_emp_id").val("");
                    $("#add_emp_id").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_emp_id").val() + " <spring:message code='MSG.EMP.MGMT.ALERT.POSSIBLE_EMPID'/>");
                    $("#add_checkId").val("Y");
                }
            });
        }
    }
</script>