<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/syscod/GrCodeMod.jsp
 - 설      명	: 시스템그룹코드관리 수정 / 추가 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2017.12.21    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>

<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.SYS.MGMT.TITLE.SYSCOD_MGMT"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="grCdModForm" id="grCdModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- 그룹 코드 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_grcode" id="mod_grcode" value="${SYSCOD_INSERT_DATA.grcode}" disabled="disabled">
                                <i></i>
                            </label>
                        </section>
                        <!-- 코드명 한글 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_K"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_grcodenm_k" id="mod_grcodenm_k" value="${SYSCOD_INSERT_DATA.grcodenm_k}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 코드명 영어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_E"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_grcodenm_e" id="mod_grcodenm_e" value="${SYSCOD_INSERT_DATA.grcodenm_e}">
                            </label>
                        </section>
                        <!-- 코드명 일어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_J"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_grcodenm_j" id="mod_grcodenm_j" value="${SYSCOD_INSERT_DATA.grcodenm_j}">
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
            <button type="button" class="btn btn-primary" id="grCdMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<!-- Script 영역 -->
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#grCdModForm").validate({
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
                add_grcode: {
                    required: true,
                    maxlength: 32
                },
                add_grcodenm_k: {
                    required: true,
                    maxlength: 100
                }
            },
            messages: {
                mod_grcode: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODE_REQUIRE'/>'
                },
                mod_grcodenm_k: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODENM_K_REQUIRE'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/syscod/grcodeModPopup.do", {
                    actionID: "ACTION_SYSCOD_UPDATE_OK",
                    grcode: $("#mod_grcode").val(),
                    grcodenm_k: $("#mod_grcodenm_k").val(),
                    grcodenm_e: $("#mod_grcodenm_e").val(),
                    grcodenm_j: $("#mod_grcodenm_j").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#grCdModModal').modal('hide');
                        GridReload('upd_dt', 'desc');
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            }
        });
        //클릭 이벤트
        bindClick();

    });

    function bindClick() {
        //저장
        $("#grCdMod").on('click', function () {
            $("#grCdModForm").submit();
        });

    }
</script>