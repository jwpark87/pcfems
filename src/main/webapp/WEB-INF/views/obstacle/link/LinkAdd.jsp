<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/link/Add.jsp
 - 설      명	: Link 현황 등록 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.05    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<style>
    /* 항목이 많아 조정 KYM */
    .smart-form fieldset {
        padding: 10px 14px 5px !important;
    }
</style>
<div class="modal-dialog" style="width: 780px;">
    <!-- 원래 600 조정  KYM -->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.LINK.SUBTITLE.LINK_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="linkAddForm" id="linkAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkLink" id="add_checkLink" value="N">

                <fieldset>
                    <div class="row">
                        <!-- APC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_apc" id="add_apc" maxlength="10">
                            </label>
                        </section>
                        <!--  SLC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.SLC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_slc" id="add_slc" maxlength="2">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="linkDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  NA -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_na" id="add_na" maxlength="10">
                            </label>
                        </section>
                        <!--  SLOT/CH -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.SLOT_CH"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_slot_ch" id="add_slot_ch">
                            </label>
                        </section>
                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country_nm" id="add_country_nm" maxlength="24">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="26">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  교환(E1)단자 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.E1_CD"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_e1_cd" id="add_e1_cd" maxlength="10">
                            </label>
                        </section>
                        <!--  Bearrer명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.BEARRER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_bearrer_nm" id="add_bearrer_nm" maxlength="32">
                            </label>
                        </section>
                        <!--  E1 Time Slot -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.E1_TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_e1_time_slot" id="add_e1_time_slot" maxlength="2">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">

                        <!--  Cable 명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.CABLE_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_cable_nm" id="add_cable_nm" maxlength="10">
                            </label>
                        </section>
                        <!--  NDCS TIE -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.NDCS_TIE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_ndcs_tie" id="add_ndcs_tie" maxlength="12">
                            </label>
                        </section>
                        <!-- 상태 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.STATUS"/>
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
            <button type="button" class="btn btn-primary" id="linkAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('apcDupChk', function (value, element) {
            return ($("#add_checkLink").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.APC'/>' + ' / ' + '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.SLC'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#linkAddForm").validate({
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
                add_apc: {
                    required: true
                },
                add_slc: {
                    required: true,
                    apcDupChk: true
                },
                add_na: {
                    required: true
                },
                add_slot_ch: {
                    required: true
                },
                add_country_nm: {
                    required: true
                },
                add_carrier_nm: {
                    required: true
                },
                add_e1_cd: {
                    required: true
                },
                add_bearrer_nm: {
                    required: true
                },
                add_e1_time_slot: {
                    required: true
                },
                add_cable_nm: {
                    required: true
                },
                add_ndcs_tie: {
                    required: true
                }
            },
            messages: {
                add_apc: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.APC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_slc: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.SLC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_na: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_slot_ch: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.SLOT_CH'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_country_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.COUNTRY_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_carrier_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_e1_cd: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.E1_CD'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_bearrer_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.BEARRER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_e1_time_slot: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.E1_TIME_SLOT'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_cable_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.CABLE_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_ndcs_tie: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.NDCS_TIE'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/link/linkInsertPopup.do", {
                    actionID: "ACTION_LINK_INSERT_OK",
                    apc: $("#add_apc").val(),
                    slc: $("#add_slc").val(),
                    na: $("#add_na").val(),
                    slot_ch: $("#add_slot_ch").val(),
                    country_nm: $("#add_country_nm").val(),
                    carrier_nm: $("#add_carrier_nm").val(),
                    e1_cd: $("#add_e1_cd").val(),
                    bearrer_nm: $("#add_bearrer_nm").val(),
                    e1_time_slot: $("#add_e1_time_slot").val(),
                    cable_nm: $("#add_cable_nm").val(),
                    ndcs_tie: $("#add_ndcs_tie").val(),
                    status: $("#add_status").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#linkInsertModal').modal('hide');
                        goLinkList();
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
        //Link 중복 체크
        $("#linkDupChk").on('click', function () {
            checkLinkDup();
        });
        //저장
        $("#linkAdd").on('click', function () {
            $("#linkAddForm").submit();
        });
        //숫자만, 32보다 작은 값
        $("#add_slc").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
            if ($(this).val() > 32) {
                $(this).val("");
            }
        });
        //숫자만
        $("#add_na").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }

    //Host 중복 확인
    function checkLinkDup() {
        if ($("#add_apc").val() && $("#add_slc").val()) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/link/linkPopup.do", {
                actionID: "ACTION_LINK_CHECK",
                apc: $("#add_apc").val(),
                slc: $("#add_slc").val(),
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_apc").val() + " / " + $("#add_slc").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkLink").val("N");
                    $("#add_apc").val("");
                    $("#add_slc").val("");
                    $("#add_apc").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_apc").val() + " / " + $("#add_slc").val() + " <spring:message code='MSG.OBSTACLE.LINK.ALERT.POSSIBLE_APC_SLC'/>");
                    $("#add_checkLink").val("Y");
                }
            });
        } else {
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.OBSTACLE.LINK.ALERT.NOT_EMPTY_APC_SLC'/>")
        }
    }
</script>