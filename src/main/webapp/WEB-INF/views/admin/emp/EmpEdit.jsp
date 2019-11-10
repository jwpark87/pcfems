<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/emp/Edit.jsp
 - 설      명	: 사용자관리 수정 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2017.12.15    1.0      KYM      신규작성
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
            <form id="empModForm" id="empModForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="mod_password" id="mod_password" value="">
                <input type="hidden" name="mod_password_chg" id="mod_password_chg" value="N"/>


                <fieldset>
                    <div class="row">
                        <!-- 사용자 그룹 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_GROUP"/>
                            </label>
                            <label class="select">
                                <select name="mod_user_group_id" id="mod_user_group_id"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 아이디 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_ID"/>
                            </label>
                            <label class="input state-disabled">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" name="mod_emp_id" id="mod_emp_id" value="${EMP_DETAIL_DATA.emp_id}" disabled="disabled">
                            </label>
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
                                <input type="text" name="mod_passwd1" id="mod_passwd1" placeholder="<spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD.INPUT" />" onchange="onChangePass(this)">
                            </label>
                        </section>
                        <!-- 비밀번호 확인 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.REPASSWD"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="text" name="mod_passwd2" id="mod_passwd2" placeholder="<spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD.INPUT" />">
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
                                <input type="text" name="mod_empNm" id="mod_empNm" value="${EMP_DETAIL_DATA.empNm}">
                            </label>
                        </section>
                        <!-- 이메일 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.EMP.MGMT.DETAIL.EMAIL"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-envelope-o"></i>
                                <input type="text" name="mod_email" id="mod_email" value="${EMP_DETAIL_DATA.email}">
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
                                <input type="text" name="mod_smsPhone" id="mod_smsPhone" value="${EMP_DETAIL_DATA.smsPhone}" data-mask="999-999-9999" data-mask-placeholder="X">
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
            <button type="button" class="btn btn-primary" id="empMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script src="${PATH_PLUGIN}/crypto-js/crypto-js.min.js"></script>
<script src="${PATH_PLUGIN}/crypto-js/sha256.min.js"></script>

<script type="text/javascript">
    jQuery(document).ready(function () {

        AotAjax.getSelectOptions($('#mod_user_group_id'), '/admin/emp/empPopup.do', {
            actionID: 'getUserGroupCodeInfo'
        }, {
            value: 'code',
            label: 'codenm_k'
        }).done(function (response) {
            $('#mod_user_group_id>option[value="${EMP_DETAIL_DATA.user_group_id}"]').prop('selected', true);
        });

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#empModForm").validate({
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
                mod_passwd2: {
                    equalTo: '#mod_passwd1'
                },
                mod_empNm: {
                    required: true
                },
                mod_email: {
                    email: true
                },
            },
            messages: {

                mod_passwd2: {
                    equalTo: '<spring:message code='MSG.EMP.MGMT.ALERT.PASSWORD'/>'
                },
                mod_empNm: {
                    required: '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_NAME'/>'
                },
                mod_email: {
                    email: '<spring:message code='MSG.EMP.MGMT.ALERT.EMAIL'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                if ($("#mod_password_chg").val() == "Y") {
                    $("#mod_password").val(CryptoJS.SHA256($("#mod_passwd1").val()).toString())
                } else {
                    $("#mod_password").val($("#mod_passwd1").val())
                }

                AotAjax.excute("${CONTEXT_PATH}/admin/emp/empModPopup.do", {
                    actionID: "ACTION_EMP_UPDATE_OK",
                    user_group_id: $("#mod_user_group_id").val(),
                    emp_id: $("#mod_emp_id").val(),
                    password: $("#mod_password").val(),
                    empNm: $("#mod_empNm").val(),
                    email: $("#mod_email").val(),
                    smsPhone: $("#mod_smsPhone").val(),
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#empModModal').modal('hide');
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
        //저장
        $("#empMod").on('click', function () {
            $("#empModForm").submit();
        });
    }

    //비밀번호 변경
    function onChangePass(obj) {
        $("#mod_passwd2").val("");
        $("#mod_password_chg").val("Y");
    }
</script>