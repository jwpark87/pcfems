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
                <spring:message code="TEXT.OBSTACLE.M3UA.SUBTITLE.M3UA_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="m3uaModForm" id="m3uaModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- sortseq -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SORTSEQ"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sortseq" id="mod_sortseq" readonly style="background-color: #eee; opacity: 1;" value="${M3UA_DETAIL_DATA.sortseq}">
                            </label>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_locality" id="mod_locality" group="LOCALITY" style="height:32px;" selected="${M3UA_DETAIL_DATA.locality}"/>
                                <i></i>
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 연동방식 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.IF_TYPE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_if_type" id="mod_if_type" maxlength="20" value="${M3UA_DETAIL_DATA.if_type}">
                            </label>
                        </section>

                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country_nm" id="mod_country_nm" maxlength="50" value="${M3UA_DETAIL_DATA.country_nm}">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="50" value="${M3UA_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>

                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  APC -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_apc" id="mod_apc" maxlength="20" value="${M3UA_DETAIL_DATA.apc}">
                            </label>
                        </section>
                        <!--  ASP ID(M3UA) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ASP_ID"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_asp_id" id="mod_asp_id" maxlength="20" value="${M3UA_DETAIL_DATA.asp_id}">
                            </label>
                        </section>
                        <!--  국내/국제 -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.LOC_BOUNDARY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_loc_boundary" id="mod_loc_boundary" maxlength="20" value="${M3UA_DETAIL_DATA.loc_boundary}">
                            </label>
                        </section>
                        <!--  NA -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_na" id="mod_na" maxlength="10" value="${M3UA_DETAIL_DATA.na}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  SLC -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SLC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_slc" id="mod_slc" value="${M3UA_DETAIL_DATA.slc }">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_clli" id="mod_clli" maxlength="50" value="${M3UA_DETAIL_DATA.clli}">
                            </label>
                        </section>
                        <!--  Routing Context(M3UA) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ROUTING_CONTEXT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_routing_context" id="mod_routing_context" maxlength="20" value="${M3UA_DETAIL_DATA.routing_context}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Source Primary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SRC_PRIMARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_src_primary_ip" id="mod_src_primary_ip" maxlength="20" value="${M3UA_DETAIL_DATA.src_primary_ip}">
                            </label>
                        </section>
                        <!--  Source Secondary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SRC_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_src_secondary_ip" id="mod_src_secondary_ip" maxlength="20" value="${M3UA_DETAIL_DATA.src_secondary_ip}">
                            </label>
                        </section>
                        <!--  Source Port -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SRC_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_src_port" id="mod_src_port" value="${M3UA_DETAIL_DATA.src_port }">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Destination Primary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DST_PRIMARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_dst_primary_ip" id="mod_dst_primary_ip" maxlength="20" value="${M3UA_DETAIL_DATA.dst_primary_ip}">
                            </label>
                        </section>
                        <!--  Destination Secondary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DST_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_dst_secondary_ip" id="mod_dst_secondary_ip" maxlength="20" value="${M3UA_DETAIL_DATA.dst_secondary_ip}">
                            </label>
                        </section>
                        <!--  Destination Port Number -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DST_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_dst_port" id="mod_dst_port" value="${M3UA_DETAIL_DATA.dst_port }">
                            </label>
                        </section>

                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  ASP Type(M3UA) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ASP_TYPE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_asp_type" id="mod_asp_type" maxlength="50" value="${M3UA_DETAIL_DATA.asp_type}">
                            </label>
                        </section>

                        <!--  SI(M3UA) -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_si" id="mod_si" maxlength="10" value="${M3UA_DETAIL_DATA.si}">
                            </label>
                        </section>
                        <!--  Routing CPU -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ROUTING_CPU"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_routing_cpu" id="mod_routing_cpu" maxlength="10" value="${M3UA_DETAIL_DATA.routing_cpu}">
                            </label>
                        </section>
                        <!--  상태 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" selected="${M3UA_DETAIL_DATA.status}"/>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_dsbd_yn" id="mod_dsbd_yn" group="W001" selected="${M3UA_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="m3uaMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#m3uaModForm").validate({
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
                mod_if_type: {
                    required: true,
                    maxlength: 20
                },
                mod_country_nm: {
                    maxlength: 50
                },
                mod_carrier_nm: {
                    maxlength: 50
                },
                mod_apc: {
                    required: true,
                    maxlength: 20
                },
                mod_asp_id: {
                    maxlength: 20
                },
                mod_loc_boundary: {
                    maxlength: 20
                },
                mod_na: {
                    maxlength: 10
                },
                mod_slc: {
                    digits: true
                },
                mod_clli: {
                    required: true,
                    maxlength: 50
                },
                mod_routing_context: {
                    maxlength: 20
                },
                mod_src_primary_ip: {
                    required: true,
                    maxlength: 20
                },
                mod_src_secondary_ip: {
                    maxlength: 20
                },
                mod_src_port: {
                    digits: true
                },
                mod_dst_primary_ip: {
                    required: true,
                    maxlength: 20
                },
                mod_dst_secondary_ip: {
                    maxlength: 20
                },
                mod_dst_port: {
                    digits: true
                },
                mod_asp_type: {
                    maxlength: 50
                },
                mod_si: {
                    maxlength: 10
                },
                mod_routing_cpu: {
                    maxlength: 10
                }
            },
            messages: {
                mod_locality: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_if_type: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.IF_TYPE'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_country_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_carrier_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_apc: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.APC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_asp_id: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_loc_boundary: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_na: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_slc: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_clli: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_routing_context: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_src_primary_ip: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.SRC_PRIMARY_IP'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_src_secondary_ip: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_src_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_dst_primary_ip: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.DST_PRIMARY_IP'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_dst_secondary_ip: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_dst_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_asp_type: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_si: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_routing_cpu: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/m3ua/m3uaModPopup.do", {
                    actionID: "ACTION_M3UA_UPDATE_OK",
                    sortseq: $("#mod_sortseq").val(),
                    locality: $("#mod_locality").val(),
                    if_type: $("#mod_if_type").val(),
                    country_nm: $("#mod_country_nm").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    apc: $("#mod_apc").val(),
                    asp_id: $("#mod_asp_id").val(),
                    loc_boundary: $("#mod_loc_boundary").val(),
                    na: $("#mod_na").val(),
                    slc: $("#mod_slc").val(),
                    clli: $("#mod_clli").val(),
                    routing_context: $("#mod_routing_context").val(),
                    src_primary_ip: $("#mod_src_primary_ip").val(),
                    src_secondary_ip: $("#mod_src_secondary_ip").val(),
                    src_port: $("#mod_src_port").val(),
                    dst_primary_ip: $("#mod_dst_primary_ip").val(),
                    dst_secondary_ip: $("#mod_dst_secondary_ip").val(),
                    dst_port: $("#mod_dst_port").val(),
                    asp_type: $("#mod_asp_type").val(),
                    si: $("#mod_si").val(),
                    routing_cpu: $("#mod_routing_cpu").val(),
                    status: $("#mod_status").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#m3uaModModal').modal('hide');
                        goM3uaList();
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
        $("#m3uaMod").on('click', function () {
            $("#m3uaModForm").submit();
        });
        //숫자만
        $("#add_sortseq,#add_slc,#add_src_port,#add_dst_port").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });

    }
</script>
