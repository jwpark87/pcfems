<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/routeset/Add.jsp
 - 설      명	: Routeset 현황 등록 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.03    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<c:set var="CHOICE">
    <spring:message code="TEXT.COMM.SEL.CHOICE"/>
</c:set>

<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.ROUTESET.SUBTITLE.ROUTESET_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="routesetAddForm" id="routesetAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkRouteset" id="add_checkRouteset" value="N">
                <fieldset>
                    <div class="row">

                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <!-- <input type="text" name="add_locality" id="add_locality" maxlength="10"> -->
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- APC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_apc" id="add_apc" maxlength="10">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="routesetDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_na" id="add_na" maxlength="10">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_clli" id="add_clli" maxlength="32">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country" id="add_country" maxlength="20">
                            </label>
                        </section>

                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="50">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.LOCAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_local" id="add_local" maxlength="30">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_status" id="add_status" group="LINK_STATUS"/>
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
            <button type="button" class="btn btn-primary" id="routesetAdd">
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
                return ($("#add_checkRouteset").val() == "Y") ? true : false;
            }, '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.LOCALITY'/>' + ' / ' + '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.APC'/>'
                + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

            var errorClass = 'invalid';
            var errorElement = 'em';
            $("#routesetAddForm").validate({
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
                        apcDupChk: true,
                        maxlength: 10
                    },
                    add_na: {
                        required: true,
                        maxlength: 10
                    },
                    add_clli: {
                        required: true
                    }
                },
                messages: {
                    add_locality: {
                        required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    },
                    add_apc: {
                        required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.APC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_na: {
                        required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_clli: {
                        required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    }
                },
                // Do not change code below
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                },
                submitHandler: function (form) {
                    AotAjax.excute("${CONTEXT_PATH}/obstacle/routeset/routesetInsertPopup.do", {
                        actionID: "ACTION_ROUTESET_INSERT_OK",
                        locality: $("#add_locality").val(),
                        apc: $("#add_apc").val(),
                        na: $("#add_na").val(),
                        clli: $("#add_clli").val(),
                        country: $("#add_country").val(),
                        carrier_nm: $("#add_carrier_nm").val(),
                        local: $("#add_local").val(),
                        status: $("#add_status").val()
                    }, {
                        autoResultFunctionTF: false
                    }).done(function (response) {
                        var str = response.returnMsg.split(":");
                        if (str[0] == "success") {
                            AotSmartAdmin.smallBoxsuccess(str[1]);
                            $('#routesetInsertModal').modal('hide');
                            goRoutesetList();
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
        //Routeset 중복 체크
        $("#routesetDupChk").on('click', function () {
            checkRoutesetDup();
        });
        //저장
        $("#routesetAdd").on('click', function () {
            $("#routesetAddForm").submit();
        });
        /* $("#add_host").on('keyup', function() {
            $(this).val($(this).val().replace(/[^0-9a-zA-Z_#]/g, ""));
        }); */
    }

    //Host 중복 확인
    function checkRoutesetDup() {
        if ($("#add_apc").val()) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/routeset/routesetPopup.do", {
                actionID: "ACTION_ROUTESET_CHECK",
                locality: $("#add_locality").val(),
                apc: $("#add_apc").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_locality option:checked").text() + " / " + $("#add_apc").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkRouteset").val("N");
                    $("#add_locality").val("");
                    $("#add_apc").val("");
                    $("#add_locality").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_locality option:checked").text() + " / " + $("#add_apc").val() + " <spring:message code='MSG.OBSTACLE.ROUTESET.ALERT.POSSIBLE_LOCALITY_APC'/>");
                    $("#add_checkRouteset").val("Y");
                }
            });
        }
    }
</script>
