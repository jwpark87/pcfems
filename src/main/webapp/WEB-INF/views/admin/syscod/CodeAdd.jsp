<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/syscod/CodeAdd.jsp
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
            <form id="grCdDtAddForm" id="grCdDtAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_code_grcode" id="add_code_grcode" value="${SYSCODDTL_INSERT_DATA.grcode }">


                <fieldset>
                    <div class="row">
                        <!-- 상세 코드 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_code" id="add_code">
                                <i></i>
                            </label>
                        </section>
                        <!-- 코드명 한글 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_K"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_codenm_k" id="add_codenm_k">
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
                                <input type="text" name="add_codenm_e" id="add_codenm_e">
                            </label>
                        </section>
                        <!-- 코드명 일어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_J"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_codenm_j" id="add_codenm_j">
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
                                <aot:select name="add_levelcod" id="add_levelcod" list="${AUTHLEVEL_LIST }"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 사용여부 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SYS.DETAIL.MGMT.POPUP.USEYN"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_use_yn" id="add_use_yn" group="W001"/>
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
            <button type="button" class="btn btn-primary" id="grCdDtAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        $("#add_levelcod").val("999");

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#grCdDtAddForm").validate({
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
                add_code: {
                    required: true,
                    maxlength: 50
                },
                add_codenm_k: {
                    required: true,
                    maxlength: 100
                }
            },
            messages: {
                add_code: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.CODE_REQUIRE'/>'
                },
                add_codenm_k: {
                    required: '<spring:message code='MSG.SYSCODE.MGMT.ALERT.CODENM_K_REQUIRE'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/syscod/codePopup.do", {
                    actionID: "ACTION_SYSCODDTL_INSERT_OK",
                    grcode: $("#add_code_grcode").val(),
                    code: $("#add_code").val(),
                    codenm: $("#add_codenm_k").val(),
                    codenm_k: $("#add_codenm_k").val(),
                    codenm_e: $("#add_codenm_e").val(),
                    codenm_j: $("#add_codenm_j").val(),
                    levelcod: $("#add_levelcod").val(),
                    use_yn: $("#add_use_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#grCdDtModal').modal('hide');
                        GridReload2($("#add_code_grcode").val());
                    } else if (str[0] === "keyfail") {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                        $("#add_code").val("");
                        $("#add_code").focus();
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
        $("#grCdDtAdd").on('click', function () {
            $("#grCdDtAddForm").submit();
        });

    }
</script>
