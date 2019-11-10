<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/tdm/Edit.jsp
 - 설      명	: Tdm 현황 수정 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.05.03    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<style>
    /* 항목이 많아 조정 KYM */
    .smart-form fieldset {
        padding: 2px 6px 5px !important;
    }

    .modal-body {
        position: relative;
        padding: 10px 20px 5px 20px;
    }
</style>
<div class="modal-dialog" style="width: 1000px;">
    <!-- 원래 600 조정  KYM -->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.TDM.SUBTITLE.TDM_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="tdmModForm" id="tdmModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- sortseq -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SORTSEQ"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sortseq" id="mod_sortseq" readonly style="background-color: #eee; opacity: 1;" value="${TDM_DETAIL_DATA.sortseq}">
                            </label>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_locality" id="mod_locality" group="LOCALITY" style="height:32px;" selected="${TDM_DETAIL_DATA.locality}"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- gnoc 타이명 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_gnoc_tie_nm" id="mod_gnoc_tie_nm" maxlength="20" value="${TDM_DETAIL_DATA.gnoc_tie_nm}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  TS -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.TS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_ts" id="mod_ts" value="${TDM_DETAIL_DATA.ts}" maxlength="10">
                            </label>
                        </section>
                        <!--  GNOC_Tie (DSX/DCSA No) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_DSX_DCSA_NO"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_gnoc_tie_dsx_dcsa_no" id="mod_gnoc_tie_dsx_dcsa_no" maxlength="20" value="${TDM_DETAIL_DATA.gnoc_tie_dsx_dcsa_no}">
                            </label>
                        </section>
                        <!--  STP TIE(부산용) -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.STP_TIE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_stp_tie" id="mod_stp_tie" maxlength="50" value="${TDM_DETAIL_DATA.stp_tie}">
                            </label>
                        </section>
                        <!--  GNOC NDCS/NDCS TIE -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_NDCS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_gnoc_tie_ndcs" id="mod_gnoc_tie_ndcs" maxlength="20" value="${TDM_DETAIL_DATA.gnoc_tie_ndcs}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  SHELF -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SHELF"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_shelf" id="mod_shelf" value="${TDM_DETAIL_DATA.shelf }">
                            </label>
                        </section>
                        <!--  SLOT -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_slot" id="mod_slot" value="${TDM_DETAIL_DATA.slot }">
                            </label>
                        </section>
                        <!--  PORT(e1) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.E1_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_e1_port" id="mod_e1_port" value="${TDM_DETAIL_DATA.e1_port }">
                            </label>
                        </section>
                        <!--  Time Slot -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_time_slot" id="mod_time_slot" value="${TDM_DETAIL_DATA.time_slot}" maxlength="10">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  CH Number -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CH_NUMBER"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_ch_number" id="mod_ch_number" value="${TDM_DETAIL_DATA.ch_number }">
                            </label>
                        </section>
                        <!--  MAPPING(부산용) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.MAPPING"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_mapping" id="mod_mapping" maxlength="20" value="${TDM_DETAIL_DATA.mapping}">
                            </label>
                        </section>
                        <!--  CRC(부산용) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CRC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_crc" id="mod_crc" maxlength="20" value="${TDM_DETAIL_DATA.crc}">
                            </label>
                        </section>
                        <!--  사용상태 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.USE_STATUS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_use_status" id="mod_use_status" maxlength="10" value="${TDM_DETAIL_DATA.use_status}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  국내/국제 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.LOC_BOUND"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_loc_bound" id="mod_loc_bound" maxlength="20" value="${TDM_DETAIL_DATA.loc_bound}">
                            </label>
                        </section>
                        <!--  NA -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_na" id="mod_na" maxlength="10" value="${TDM_DETAIL_DATA.na}">
                            </label>
                        </section>
                        <!--  APC -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_apc" id="mod_apc" maxlength="20" value="${TDM_DETAIL_DATA.apc}">
                            </label>
                        </section>
                        <!--  SLC -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SLC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_slc" id="mod_slc" value="${TDM_DETAIL_DATA.slc }">
                            </label>
                        </section>
                        <!--  Error Connection -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.ERROR_CONN"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_error_conn" id="mod_error_conn" maxlength="20" value="${TDM_DETAIL_DATA.error_conn}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country_nm" id="mod_country_nm" maxlength="50" value="${TDM_DETAIL_DATA.country_nm}">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="50" value="${TDM_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_clli" id="mod_clli" maxlength="50" value="${TDM_DETAIL_DATA.clli}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Bearer -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.BEARER"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_bearer" id="mod_bearer" maxlength="100" value="${TDM_DETAIL_DATA.bearer}">
                            </label>
                        </section>
                        <!--  Bearer Time Slot(서울용) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.BEARER_TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_bearer_time_slot" id="mod_bearer_time_slot" value="${TDM_DETAIL_DATA.bearer_time_slot}" maxlength="10">
                            </label>
                        </section>
                        <!--  케이블 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CABLE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_cable" id="mod_cable" maxlength="20" value="${TDM_DETAIL_DATA.cable}">
                            </label>
                        </section>
                        <!--  status -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" selected="${TDM_DETAIL_DATA.status}"/>
                                <i></i>
                            </label>
                        </section>

                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  설명 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.REMARK1"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remark1" id="mod_remark1" maxlength="1000" value="${TDM_DETAIL_DATA.remark1}">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_dsbd_yn" id="mod_dsbd_yn" group="W001" style="height:32px;" selected="${TDM_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="tdmMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#tdmModForm").validate({
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
                mod_locality: {
                    required: true
                },
                mod_gnoc_tie_nm: {
                    maxlength: 20
                },
                mod_ts: {
                    maxlength: 10
                },
                mod_gnoc_tie_dsx_dcsa_no: {
                    maxlength: 20
                },
                mod_stp_tie: {
                    maxlength: 50
                },
                mod_gnoc_tie_ndcs: {
                    maxlength: 20
                },
                mod_shelf: {
                    digits: true
                },
                mod_slot: {
                    digits: true
                },
                mod_e1_port: {
                    digits: true
                },
                mod_time_slot: {
                    maxlength: 10
                },
                mod_ch_number: {
                    digits: true
                },
                mod_mapping: {
                    maxlength: 20
                },
                mod_crc: {
                    maxlength: 20
                },
                mod_use_status: {
                    maxlength: 10
                },
                mod_loc_bound: {
                    maxlength: 20
                },
                mod_na: {
                    maxlength: 10
                },
                mod_apc: {
                    maxlength: 20
                },
                mod_slc: {
                    digits: true
                },
                mod_error_conn: {
                    maxlength: 20
                },
                mod_country_nm: {
                    maxlength: 50
                },
                mod_carrier_nm: {
                    maxlength: 50
                },
                mod_clli: {
                    maxlength: 50
                },
                mod_bearer: {
                    maxlength: 100
                },
                mod_bearer_time_slot: {
                    maxlength: 10
                },
                mod_cable: {
                    maxlength: 20
                },
                mod_remark1: {
                    maxlength: 1000
                }
            },
            messages: {
                mod_locality: {
                    required: '<spring:message code='TEXT.OBSTACLE.TDM.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_gnoc_tie_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_ts: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_gnoc_tie_dsx_dcsa_no: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_stp_tie: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_gnoc_tie_ndcs: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_shelf: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_slot: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_e1_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_time_slot: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_ch_number: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_mapping: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_crc: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_use_status: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_loc_bound: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_na: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_apc: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_slc: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_error_conn: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_country_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_carrier_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_clli: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_bearer: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_bearer_time_slot: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_cable: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_remark1: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/tdm/tdmModPopup.do", {
                    actionID: "ACTION_TDM_UPDATE_OK",
                    sortseq: $("#mod_sortseq").val(),
                    locality: $("#mod_locality").val(),
                    gnoc_tie_nm: $("#mod_gnoc_tie_nm").val(),
                    ts: $("#mod_ts").val(),
                    gnoc_tie_dsx_dcsa_no: $("#mod_gnoc_tie_dsx_dcsa_no").val(),
                    stp_tie: $("#mod_stp_tie").val(),
                    gnoc_tie_ndcs: $("#mod_gnoc_tie_ndcs").val(),
                    shelf: $("#mod_shelf").val(),
                    slot: $("#mod_slot").val(),
                    e1_port: $("#mod_e1_port").val(),
                    time_slot: $("#mod_time_slot").val(),
                    ch_number: $("#mod_ch_number").val(),
                    mapping: $("#mod_mapping").val(),
                    crc: $("#mod_crc").val(),
                    use_status: $("#mod_use_status").val(),
                    loc_bound: $("#mod_loc_bound").val(),
                    na: $("#mod_na").val(),
                    apc: $("#mod_apc").val(),
                    slc: $("#mod_slc").val(),
                    error_conn: $("#mod_error_conn").val(),
                    country_nm: $("#mod_country_nm").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    clli: $("#mod_clli").val(),
                    bearer: $("#mod_bearer").val(),
                    bearer_time_slot: $("#mod_bearer_time_slot").val(),
                    cable: $("#mod_cable").val(),
                    remark1: $("#mod_remark1").val(),
                    status: $("#mod_status").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#tdmModModal').modal('hide');
                        goTdmList();
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
        $("#tdmMod").on('click', function () {
            $("#tdmModForm").submit();
        });
        //숫자만
        $("#mod_sortseq ,#mod_shelf ,#mod_slot ,#mod_e1_port,#mod_ch_number,#mod_slc").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });

    }
</script>
