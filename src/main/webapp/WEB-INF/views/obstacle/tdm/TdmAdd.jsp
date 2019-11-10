<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/tdm/Add.jsp
 - 설      명	: Tdm 현황 등록 page 
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
<c:set var="CHOICE">
    <spring:message code="TEXT.COMM.SEL.CHOICE"/>
</c:set>

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
            <form id="tdmAddForm" id="tdmAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkTdm" id="add_checkTdm" value="N">

                <fieldset>
                    <div class="row">
                        <!-- sortseq -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SORTSEQ"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sortseq" id="add_sortseq">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="tdmDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- gnoc 타이명 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_gnoc_tie_nm" id="add_gnoc_tie_nm" maxlength="20">
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
                                <input type="text" name="add_ts" id="add_ts" maxlength="10">
                            </label>
                        </section>
                        <!--  GNOC_Tie (DSX/DCSA No) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_DSX_DCSA_NO"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_gnoc_tie_dsx_dcsa_no" id="add_gnoc_tie_dsx_dcsa_no" maxlength="20">
                            </label>
                        </section>
                        <!--  STP TIE(부산용) -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.STP_TIE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_stp_tie" id="add_stp_tie" maxlength="50">
                            </label>
                        </section>
                        <!--  GNOC NDCS/NDCS TIE -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.GNOC_TIE_NDCS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_gnoc_tie_ndcs" id="add_gnoc_tie_ndcs" maxlength="20">
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
                                <input type="text" name="add_shelf" id="add_shelf">
                            </label>
                        </section>
                        <!--  SLOT -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_slot" id="add_slot">
                            </label>
                        </section>
                        <!--  PORT(e1) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.E1_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_e1_port" id="add_e1_port">
                            </label>
                        </section>
                        <!--  Time Slot -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_time_slot" id="add_time_slot" maxlength="10">
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
                                <input type="text" name="add_ch_number" id="add_ch_number">
                            </label>
                        </section>
                        <!--  MAPPING(부산용) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.MAPPING"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_mapping" id="add_mapping" maxlength="20">
                            </label>
                        </section>
                        <!--  CRC(부산용) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CRC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_crc" id="add_crc" maxlength="20">
                            </label>
                        </section>
                        <!--  사용상태 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.USE_STATUS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_use_status" id="add_use_status" maxlength="10">
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
                                <input type="text" name="add_loc_bound" id="add_loc_bound" maxlength="20">
                            </label>
                        </section>
                        <!--  NA -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_na" id="add_na" maxlength="10">
                            </label>
                        </section>
                        <!--  APC -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_apc" id="add_apc" maxlength="20">
                            </label>
                        </section>
                        <!--  SLC -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.SLC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_slc" id="add_slc">
                            </label>
                        </section>
                        <!--  Error Connection -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.ERROR_CONN"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_error_conn" id="add_error_conn" maxlength="20">
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
                                <input type="text" name="add_country_nm" id="add_country_nm" maxlength="50">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="50">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_clli" id="add_clli" maxlength="50">
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
                                <input type="text" name="add_bearer" id="add_bearer" maxlength="100">
                            </label>
                        </section>
                        <!--  Bearer Time Slot(서울용) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.BEARER_TIME_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_bearer_time_slot" id="add_bearer_time_slot" maxlength="10">
                            </label>
                        </section>
                        <!--  케이블 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.CABLE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_cable" id="add_cable" maxlength="20">
                            </label>
                        </section>
                        <!--  status -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_status" id="add_status" group="LINK_STATUS"/>
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
                                <input type="text" name="add_remark1" id="add_remark1" maxlength="1000">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.TDM.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_dsbd_yn" id="add_dsbd_yn" group="W001" style="height:32px;" selected="N"/>
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
            <button type="button" class="btn btn-primary" id="tdmAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('tdmDupChk', function (value, element) {
            return ($("#add_checkTdm").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.OBSTACLE.TDM.DETAIL.SORTSEQ'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#tdmAddForm").validate({
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
                add_sortseq: {
                    required: true,
                    tdmDupChk: true,
                    digits: true
                },
                add_locality: {
                    required: true
                },
                add_gnoc_tie_nm: {
                    maxlength: 20
                },
                add_ts: {
                    maxlength: 10
                },
                add_gnoc_tie_dsx_dcsa_no: {
                    maxlength: 20
                },
                add_stp_tie: {
                    maxlength: 50
                },
                add_gnoc_tie_ndcs: {
                    maxlength: 20
                },
                add_shelf: {
                    digits: true
                },
                add_slot: {
                    digits: true
                },
                add_e1_port: {
                    digits: true
                },
                add_time_slot: {
                    maxlength: 10
                },
                add_ch_number: {
                    digits: true
                },
                add_mapping: {
                    maxlength: 20
                },
                add_crc: {
                    maxlength: 20
                },
                add_use_status: {
                    maxlength: 10
                },
                add_loc_bound: {
                    maxlength: 20
                },
                add_na: {
                    maxlength: 10
                },
                add_apc: {
                    maxlength: 20
                },
                add_slc: {
                    digits: true
                },
                add_error_conn: {
                    maxlength: 20
                },
                add_country_nm: {
                    maxlength: 50
                },
                add_carrier_nm: {
                    maxlength: 50
                },
                add_clli: {
                    maxlength: 50
                },
                add_bearer: {
                    maxlength: 100
                },
                add_bearer_time_slot: {
                    maxlength: 10
                },
                add_cable: {
                    maxlength: 20
                },
                add_remark1: {
                    maxlength: 1000
                }
            },
            messages: {
                add_sortseq: {
                    required: '<spring:message code='TEXT.OBSTACLE.TDM.DETAIL.SORTSEQ'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_locality: {
                    required: '<spring:message code='TEXT.OBSTACLE.TDM.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_gnoc_tie_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_ts: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_gnoc_tie_dsx_dcsa_no: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_stp_tie: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_gnoc_tie_ndcs: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_shelf: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_slot: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_e1_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_time_slot: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_ch_number: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_mapping: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_crc: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_use_status: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_loc_bound: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_na: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_apc: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_slc: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_error_conn: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_country_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_carrier_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_clli: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_bearer: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_bearer_time_slot: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_cable: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_remark1: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/tdm/tdmInsertPopup.do", {
                    actionID: "ACTION_TDM_INSERT_OK",
                    sortseq: $("#add_sortseq").val(),
                    locality: $("#add_locality").val(),
                    gnoc_tie_nm: $("#add_gnoc_tie_nm").val(),
                    ts: $("#add_ts").val(),
                    gnoc_tie_dsx_dcsa_no: $("#add_gnoc_tie_dsx_dcsa_no").val(),
                    stp_tie: $("#add_stp_tie").val(),
                    gnoc_tie_ndcs: $("#add_gnoc_tie_ndcs").val(),
                    shelf: $("#add_shelf").val(),
                    slot: $("#add_slot").val(),
                    e1_port: $("#add_e1_port").val(),
                    time_slot: $("#add_time_slot").val(),
                    ch_number: $("#add_ch_number").val(),
                    mapping: $("#add_mapping").val(),
                    crc: $("#add_crc").val(),
                    use_status: $("#add_use_status").val(),
                    loc_bound: $("#add_loc_bound").val(),
                    na: $("#add_na").val(),
                    apc: $("#add_apc").val(),
                    slc: $("#add_slc").val(),
                    error_conn: $("#add_error_conn").val(),
                    country_nm: $("#add_country_nm").val(),
                    carrier_nm: $("#add_carrier_nm").val(),
                    clli: $("#add_clli").val(),
                    bearer: $("#add_bearer").val(),
                    bearer_time_slot: $("#add_bearer_time_slot").val(),
                    cable: $("#add_cable").val(),
                    remark1: $("#add_remark1").val(),
                    status: $("#add_status").val(),
                    dsbd_yn: $("#add_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#tdmInsertModal').modal('hide');
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
        //tdm 중복 체크
        $("#tdmDupChk").on('click', function () {
            checkTdmDup();
        });
        //저장
        $("#tdmAdd").on('click', function () {
            $("#tdmAddForm").submit();
        });
        //숫자만
        $("#add_sortseq,#add_shelf,#add_slot	 ,#add_e1_port,#add_ch_number,#add_slc").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }

    //Host 중복 확인
    function checkTdmDup() {
        AotAjax.excute("${CONTEXT_PATH}/obstacle/tdm/tdmPopup.do", {
            actionID: "ACTION_TDM_CHECK",
            sortseq: $("#add_sortseq").val()
        }, {
            autoResultFunctionTF: false
        }).done(function (response) {
            var str = response.checkRet;
            if (str == "NG") {
                AotSmartAdmin.smallBoxWarning($("#add_sortseq").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                $("#add_checkTdm").val("N");
                $("#add_sortseq").val("");
                $("#add_sortseq").focus();
            } else {
                AotSmartAdmin.smallBoxsuccess($("#add_sortseq").val() + " <spring:message code='MSG.OBSTACLE.TDM.ALERT.POSSIBLE_SORTSEQ'/>");
                $("#add_checkTdm").val("Y");
            }
        });
    }
</script>
