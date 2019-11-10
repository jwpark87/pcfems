<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/linkk/Edit.jsp
 - 설      명	: Link 현황 수정 page 
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
            <form id="linkModForm" id="linkModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- APC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_apc" id="mod_apc" readonly style="background-color: #eee; opacity: 1;" value="${LINK_DETAIL_DATA.apc}">
                            </label>
                        </section>
                        <!--  SLC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.SLC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_slc" id="mod_slc" readonly style="background-color: #eee; opacity: 1;" value="${LINK_DETAIL_DATA.slc}">
                            </label>
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
                                <input type="text" name="mod_na" id="mod_na" maxlength="10" value="${LINK_DETAIL_DATA.na}">
                            </label>
                        </section>
                        <!--  SLOT/CH -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.SLOT_CH"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_slot_ch" id="mod_slot_ch" value="${LINK_DETAIL_DATA.slot_ch}">
                            </label>
                        </section>
                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country_nm" id="mod_country_nm" maxlength="24" value="${LINK_DETAIL_DATA.country_nm}">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="26" value="${LINK_DETAIL_DATA.carrier_nm}">
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
                                <input type="text" name="mod_e1_cd" id="mod_e1_cd" maxlength="10" value="${LINK_DETAIL_DATA.e1_cd}">
                            </label>
                        </section>
                        <!--  Bearrer명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.BEARRER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_bearrer_nm" id="mod_bearrer_nm" maxlength="32" value="${LINK_DETAIL_DATA.bearrer_nm}">
                            </label>
                        </section>
                        <!--  E1 Time Slot -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.E1_TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_e1_time_slot" id="mod_e1_time_slot" maxlength="2" value="${LINK_DETAIL_DATA.e1_time_slot}">
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
                                <input type="text" name="mod_cable_nm" id="mod_cable_nm" maxlength="10" value="${LINK_DETAIL_DATA.cable_nm}">
                            </label>
                        </section>
                        <!--  NDCS TIE -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.NDCS_TIE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_ndcs_tie" id="mod_ndcs_tie" maxlength="12" value="${LINK_DETAIL_DATA.ndcs_tie}">
                            </label>
                        </section>
                        <!-- 상태 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINK.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" selected="${LINK_DETAIL_DATA.status}"/>
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
            <button type="button" class="btn btn-primary" id="linkMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#linkModForm").validate({
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
                mod_na: {
                    required: true
                },
                mod_slot_ch: {
                    required: true
                },
                mod_country_nm: {
                    required: true
                },
                mod_carrier_nm: {
                    required: true
                },
                mod_e1_cd: {
                    required: true
                },
                mod_bearrer_nm: {
                    required: true
                },
                mod_e1_time_slot: {
                    required: true
                },
                mod_cable_nm: {
                    required: true
                },
                mod_ndcs_tie: {
                    required: true
                }
            },
            messages: {
                mod_na: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_slot_ch: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.SLOT_CH'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_country_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.COUNTRY_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_carrier_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_e1_cd: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.E1_CD'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_bearrer_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.BEARRER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_e1_time_slot: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.E1_TIME_SLOT'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_cable_nm: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.CABLE_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_ndcs_tie: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINK.DETAIL.NDCS_TIE'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/link/linkModPopup.do", {
                    actionID: "ACTION_LINK_UPDATE_OK",
                    apc: $("#mod_apc").val(),
                    slc: $("#mod_slc").val(),
                    na: $("#mod_na").val(),
                    slot_ch: $("#mod_slot_ch").val(),
                    country_nm: $("#mod_country_nm").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    e1_cd: $("#mod_e1_cd").val(),
                    bearrer_nm: $("#mod_bearrer_nm").val(),
                    e1_time_slot: $("#mod_e1_time_slot").val(),
                    cable_nm: $("#mod_cable_nm").val(),
                    ndcs_tie: $("#mod_ndcs_tie").val(),
                    status: $("#mod_status").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#linkModModal').modal('hide');
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

        //저장
        $("#linkMod").on('click', function () {
            $("#linkModForm").submit();
        });
        //숫자만, 32보다 작은 값
        $("#mod_slc").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
            if ($(this).val() > 32) {
                $(this).val("");
            }
        });
        //숫자만
        $("#mod_na").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }
</script>
