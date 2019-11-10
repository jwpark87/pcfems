<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/linkset/Add.jsp
 - 설      명	: Linkset 현황 등록 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.04.27    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<style>
    /* 항목이 많아 조정 KYM */
    .smart-form fieldset {
        padding: 10px 14px 5px !important;
    }
</style>
<c:set var="CHOICE">
    <spring:message code="TEXT.COMM.SEL.CHOICE"/>
</c:set>
<div class="modal-dialog" style="width: 780px;">
    <!-- 원래 600 조정  KYM -->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.LINKSET.SUBTITLE.LINKSET_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="linksetAddForm" id="linksetAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkLinkset" id="add_checkLinkset" value="N">

                <fieldset>
                    <div class="row">
                        <!--  LOCALITY -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- APC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_apc" id="add_apc" maxlength="10">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="linksetDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  NA -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_na" id="add_na" maxlength="10">
                            </label>
                        </section>
                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country" id="add_country" maxlength="20">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="50">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  교환(E1)단자 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_clli" id="add_clli" maxlength="32">
                            </label>
                        </section>
                        <!-- 상태 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_status" id="add_status" group="LINK_STATUS"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 출력여부 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_dsbd_yn" id="add_dsbd_yn" group="W001" selected="N"/>
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
            <button type="button" class="btn btn-primary" id="linksetAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(
        function () {

            //custom validation 추가
            $.validator.addMethod('apcDupChk', function (value, element) {
                return ($("#add_checkLinkset").val() == "Y") ? true : false;
            }, '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.LOCALITY'/>' + ' / ' + '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.APC'/>'
                + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

            var errorClass = 'invalid';
            var errorElement = 'em';
            $("#linksetAddForm").validate({
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
                    add_locality: {
                        required: true
                    },
                    add_apc: {
                        required: true,
                        apcDupChk: true
                    },
                    add_na: {
                        required: true
                    },
                    add_clli: {
                        required: true
                    }
                },
                messages: {
                    add_locality: {
                        required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    },
                    add_apc: {
                        required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.APC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    },
                    add_na: {
                        required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    },
                    add_clli: {
                        required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    }
                },
                // Do not change code below
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                },
                submitHandler: function (form) {
                    AotAjax.excute("${CONTEXT_PATH}/obstacle/linkset/linksetInsertPopup.do", {
                        actionID: "ACTION_LINKSET_INSERT_OK",
                        locality: $("#add_locality").val(),
                        apc: $("#add_apc").val(),
                        na: $("#add_na").val(),
                        country: $("#add_country").val(),
                        carrier_nm: $("#add_carrier_nm").val(),
                        clli: $("#add_clli").val(),
                        status: $("#add_status").val(),
                        dsbd_yn: $("#add_dsbd_yn").val()
                    }, {
                        autoResultFunctionTF: false
                    }).done(function (response) {
                        var str = response.returnMsg.split(":");
                        if (str[0] == "success") {
                            AotSmartAdmin.smallBoxsuccess(str[1]);
                            $('#linksetInsertModal').modal('hide');
                            goLinksetList();
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
        //Linkset 중복 체크
        $("#linksetDupChk").on('click', function () {
            checkLinksetDup();
        });
        //저장
        $("#linksetAdd").on('click', function () {
            $("#linksetAddForm").submit();
        });
        //숫자만
        $("#add_na").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }

    //Host 중복 확인
    function checkLinksetDup() {
        if ($("#add_locality").val() && $("#add_apc").val()) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/linkset/linksetPopup.do", {
                actionID: "ACTION_LINKSET_CHECK",
                locality: $("#add_locality").val(),
                apc: $("#add_apc").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_locality option:checked").text() + " / " + $("#add_apc").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkLinkset").val("N");
                    $("#add_locality").val("");
                    $("#add_apc").val("");
                    $("#add_apc").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_locality option:checked").text() + " / " + $("#add_apc").val() + " <spring:message code='MSG.OBSTACLE.LINKSET.ALERT.POSSIBLE_APC_LOCALITY'/>");
                    $("#add_checkLinkset").val("Y");
                }
            });
        } else {
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.OBSTACLE.LINKSET.ALERT.NOT_EMPTY_APC_LOCALITY'/>")
        }
    }
</script>
