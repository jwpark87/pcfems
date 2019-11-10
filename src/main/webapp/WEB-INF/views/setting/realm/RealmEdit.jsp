<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/realm/Edit.jsp
 - 설    명	: REALM사업자관리 수정 page
 - 작 성 자	: LHN
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
            <form id="realmModForm" id="realmModForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="mod_checkRealmNodeIp" id="mod_checkRealmNodeIp" value="Y">

                <fieldset>
                    <div class="row">
                        <!-- Realm -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_realm" id="mod_realm" value="${REALM_DETAIL_DATA.realm}" disabled="disabled">
                            </label>
                            <p class="note">
                                <strong>Note:</strong>
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM_NOTE"/>
                            </p>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- REALM -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.REALM.DETAIL.REALM_CARRIER_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_realm_carrier_name" id="mod_realm_carrier_name" value="${REALM_DETAIL_DATA.realm_carrier_name}">
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
                                <aot:select name="mod_sbc_group_id" id="mod_sbc_group_id" group="SBC_GROUP_ID" selected="${REALM_DETAIL_DATA.sbc_group_id}"></aot:select>
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
                                <input type="text" name="mod_remark1" id="mod_remark1" value="${REALM_DETAIL_DATA.remark1}">
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
            <button type="button" class="btn btn-primary" id="realmMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#realmModForm").validate({
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
                mod_realm: {
                    required: true
                },
                mod_realm_carrier_name: {
                    required: true
                },
            },
            messages: {
                mod_realm: {
                    required: '<spring:message code='TEXT.SETTING.REALM.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_realm_carrier_name: {
                    required: '<spring:message code='TEXT.SETTING.REALM.DETAIL.REALM_CARRIER_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/realm/sbcModPopup.do", {
                    actionID: "ACTION_REALM_UPDATE_OK",
                    realm: $("#mod_realm").val(),
                    realm_carrier_name: $("#mod_realm_carrier_name").val(),
                    sbc_group_id: $("#mod_sbc_group_id").val(),
                    remark1: $("#mod_remark1").val(),
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#realmModModal').modal('hide');
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
        //저장
        $("#realmMod").on('click', function () {
            $("#realmModForm").submit();
        });
    }
</script>
