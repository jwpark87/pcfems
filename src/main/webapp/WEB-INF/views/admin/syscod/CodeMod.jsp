<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/syscod/CodeMod.jsp
 - 설      명	: 시스템그룹코드관리 수정 / 추가 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2009.10.20    1.0     ventus      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.SYS.MGMT.SUBTITLE.SYSCOD_MGMT"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="grCdDtModForm" id="grCdDtModForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="mod_code_grcode" id="mod_code_grcode" value="${SYSCODDTL_INSERT_DATA.grcode}">


                <fieldset>
                    <div class="row">
                        <!-- 상세 코드 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_code" id="mod_code" value="${SYSCODDTL_INSERT_DATA.code}" disabled="disabled">
                                <i></i>
                            </label>
                        </section>
                        <!-- 코드명 한글 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_K"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_codenm_k" id="mod_codenm_k" value="${SYSCODDTL_INSERT_DATA.codenm_k}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 코드명 영어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_E"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_codenm_e" id="mod_codenm_e" value="${SYSCODDTL_INSERT_DATA.codenm_e}">
                            </label>
                        </section>
                        <!-- 코드명 일어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_J"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_codenm_j" id="mod_codenm_j" value="${SYSCODDTL_INSERT_DATA.codenm_j}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 권한 레벨 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.LEVEL"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_levelcod" id="mod_levelcod" list="${AUTHLEVEL_LIST }" selected="${SYSCODDTL_INSERT_DATA.levelcod}"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 사용여부 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.USEYN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_use_yn" id="mod_use_yn" group="W001" selected="${SYSCODDTL_INSERT_DATA.use_yn}"/>
                                <i></i>
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
            <button type="button" class="btn btn-primary" id="grCdDtMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#mod_levelcod").val("999");

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#grCdDtModForm").validate({
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
                mod_code: {
                    required: true,
                    maxlength: 50
                },
                mod_codenm_k: {
                    required: true,
                    maxlength: 100
                }
            },
            messages: {
                mod_code: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.CODE_REQUIRE'/>'
                },
                mod_codenm_k: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.CODENM_K_REQUIRE'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/syscod/codeModPopup.do", {
                    actionID: "ACTION_SYSCODDTL_UPDATE_OK",
                    grcode: $("#mod_code_grcode").val(),
                    code: $("#mod_code").val(),
                    codenm: $("#mod_codenm_k").val(),
                    codenm_k: $("#mod_codenm_k").val(),
                    codenm_e: $("#mod_codenm_e").val(),
                    codenm_j: $("#mod_codenm_j").val(),
                    levelcod: $("#mod_levelcod").val(),
                    use_yn: $("#mod_use_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#grCdDtModModal').modal('hide');
                        GridReload2($("#mod_code_grcode").val());
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
        $("#grCdDtMod").on('click', function () {
            $("#grCdDtModForm").submit();
        });

    }
</script>
