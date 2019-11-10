<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/realm/Add.jsp
 - 설      명	: REALM사업자관리 등록 page
 - 작 성 자	    : LHN
 - 작성날짜	: 2017.12.21
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2017.12.21    1.0      LHN      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.SETTING.REALM.SUBTITLE.REALM_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="realmAddForm" id="realmAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkRealm" id="add_checkRealm" value="N">
                <fieldset>
                    <div class="row">
                        <!-- Realm -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_realm" id="add_realm" maxlength="20">
                            </label>
                            <p class="note">
                                <strong>Note:</strong>
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM_NOTE"/>
                            </p>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="realmDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 사업자명 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM_CARRIER_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_realm_carrier_name" id="add_realm_carrier_name" maxlength="100">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- sbc_group_id -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM_SBC_GROUP_ID"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_sbc_group_id" id="add_sbc_group_id" group="SBC_GROUP_ID"></aot:select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  비고 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REMARK1"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-comment"></i>
                                <input type="text" name="add_remark1" id="add_remark1" maxlength="200">
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
            <button type="button" class="btn btn-primary" id="realmAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('realmDupChk', function (value, element) {
            return ($("#add_checkRealm").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.SETTING.REALM.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#realmAddForm").validate({
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
                add_realm: {
                    required: true,
                    realmDupChk: true
                },
                add_realm_carrier_name: {
                    required: true
                },
            },
            messages: {
                add_realm: {
                    required: '<spring:message code='TEXT.SETTING.REALM.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_realm_carrier_name: {
                    required: '<spring:message code='TEXT.SETTING.REALM.DETAIL.REALM_CARRIER_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/realm/sbcInsertPopup.do", {
                    actionID: "ACTION_REALM_INSERT_OK",
                    realm: $("#add_realm").val(),
                    realm_carrier_name: $("#add_realm_carrier_name").val(),
                    sbc_group_id: $("#add_sbc_group_id").val(),
                    remark1: $("#add_remark1").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = e.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#realmInsertModal').modal('hide');
                        goRealmList();
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
        //Realm 중복 체크
        $("#realmDupChk").on('click', function () {
            checkRealmDup();
        });
        //저장
        $("#realmAdd").on('click', function () {
            $("#realmAddForm").submit();
        });
        $("#add_realm").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9a-zA-Z_#]/g, ""));
        });

    }

    //Realm 중복 확인
    function checkRealmDup() {

        if ($("#add_realm").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/realm/sbcPopup.do", {
                actionID: "ACTION_REALM_CHECK",
                realm: $("#add_realm").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str == "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_realm").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkRealm").val("N");
                    $("#add_realm").val("");
                    $("#add_realm").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_realm").val() + " <spring:message code='MSG.SETTING.REALM.ALERT.POSSIBLE_REALM'/>");
                    $("#add_checkRealm").val("Y");
                }
            });
        }
    }
</script>
