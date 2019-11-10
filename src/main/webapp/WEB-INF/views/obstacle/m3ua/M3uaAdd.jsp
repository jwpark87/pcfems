<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/m3ua/Add.jsp
 - 설      명	: M2PA/M3UA 현황 등록 page 
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
                <spring:message code="TEXT.OBSTACLE.M3UA.SUBTITLE.M3UA_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="m3uaAddForm" id="m3uaAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkM3ua" id="add_checkM3ua" value="N">

                <fieldset>
                    <div class="row">
                        <!-- sortseq -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SORTSEQ"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sortseq" id="add_sortseq">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="m3uaDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
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
                                <input type="text" name="add_if_type" id="add_if_type" maxlength="20">
                            </label>
                        </section>

                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country_nm" id="add_country_nm" maxlength="50">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="50">
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
                                <input type="text" name="add_apc" id="add_apc" maxlength="20">
                            </label>
                        </section>
                        <!--  ASP ID(M3UA) -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ASP_ID"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_asp_id" id="add_asp_id" maxlength="20">
                            </label>
                        </section>
                        <!--  국내/국제 -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.LOC_BOUNDARY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_loc_boundary" id="add_loc_boundary" maxlength="20">
                            </label>
                        </section>
                        <!--  NA -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_na" id="add_na" maxlength="10">
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
                                <input type="text" name="add_slc" id="add_slc">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_clli" id="add_clli" maxlength="50">
                            </label>
                        </section>
                        <!--  Routing Context(M3UA) -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ROUTING_CONTEXT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_routing_context" id="add_routing_context" maxlength="20">
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
                                <input type="text" name="add_src_primary_ip" id="add_src_primary_ip" maxlength="20">
                            </label>
                        </section>
                        <!--  Source Secondary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SRC_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_src_secondary_ip" id="add_src_secondary_ip" maxlength="20">
                            </label>
                        </section>
                        <!--  Source Port -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SRC_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_src_port" id="add_src_port">
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
                                <input type="text" name="add_dst_primary_ip" id="add_dst_primary_ip" maxlength="20">
                            </label>
                        </section>
                        <!--  Destination Secondary IP -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DST_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_dst_secondary_ip" id="add_dst_secondary_ip" maxlength="20">
                            </label>
                        </section>
                        <!--  Destination Port Number -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DST_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_dst_port" id="add_dst_port">
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
                                <input type="text" name="add_asp_type" id="add_asp_type" maxlength="50">
                            </label>
                        </section>

                        <!--  SI(M3UA) -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.SI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_si" id="add_si" maxlength="10">
                            </label>
                        </section>
                        <!--  Routing CPU -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.ROUTING_CPU"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_routing_cpu" id="add_routing_cpu" maxlength="10">
                            </label>
                        </section>
                        <!--  상태 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_status" id="add_status" group="LINK_STATUS"/>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.DSBD_YN"/>
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
            <button type="button" class="btn btn-primary" id="m3uaAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('m3uaDupChk', function (value, element) {
            return ($("#add_checkM3ua").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.SORTSEQ'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#m3uaAddForm").validate({
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
                    m3uaDupChk: true,
                    digits: true
                },
                add_locality: {
                    required: true
                },
                add_if_type: {
                    required: true,
                    maxlength: 20
                },
                add_country_nm: {
                    maxlength: 50
                },
                add_carrier_nm: {
                    maxlength: 50
                },
                add_apc: {
                    required: true,
                    maxlength: 20
                },
                add_asp_id: {
                    maxlength: 20
                },
                add_loc_boundary: {
                    maxlength: 20
                },
                add_na: {
                    maxlength: 10
                },
                add_slc: {
                    digits: true
                },
                add_clli: {
                    required: true,
                    maxlength: 50
                },
                add_routing_context: {
                    maxlength: 20
                },
                add_src_primary_ip: {
                    required: true,
                    maxlength: 20
                },
                add_src_secondary_ip: {
                    maxlength: 20
                },
                add_src_port: {
                    digits: true
                },
                add_dst_primary_ip: {
                    required: true,
                    maxlength: 20
                },
                add_dst_secondary_ip: {
                    maxlength: 20
                },
                add_dst_port: {
                    digits: true
                },
                add_asp_type: {
                    maxlength: 50
                },
                add_si: {
                    maxlength: 10
                },
                add_routing_cpu: {
                    maxlength: 10
                }
            },
            messages: {
                add_sortseq: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.SORTSEQ'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_locality: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_if_type: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.IF_TYPE'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_country_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_carrier_nm: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_apc: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.APC'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_asp_id: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_loc_boundary: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_na: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_slc: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_clli: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_routing_context: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_src_primary_ip: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.SRC_PRIMARY_IP'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_src_secondary_ip: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_src_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_dst_primary_ip: {
                    required: '<spring:message code='TEXT.OBSTACLE.M3UA.DETAIL.DST_PRIMARY_IP'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_dst_secondary_ip: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_dst_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                add_asp_type: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_si: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_routing_cpu: {
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/m3ua/m3uaInsertPopup.do", {
                    actionID: "ACTION_M3UA_INSERT_OK",
                    sortseq: $("#add_sortseq").val(),
                    locality: $("#add_locality").val(),
                    if_type: $("#add_if_type").val(),
                    country_nm: $("#add_country_nm").val(),
                    carrier_nm: $("#add_carrier_nm").val(),
                    apc: $("#add_apc").val(),
                    asp_id: $("#add_asp_id").val(),
                    loc_boundary: $("#add_loc_boundary").val(),
                    na: $("#add_na").val(),
                    slc: $("#add_slc").val(),
                    clli: $("#add_clli").val(),
                    routing_context: $("#add_routing_context").val(),
                    src_primary_ip: $("#add_src_primary_ip").val(),
                    src_secondary_ip: $("#add_src_secondary_ip").val(),
                    src_port: $("#add_src_port").val(),
                    dst_primary_ip: $("#add_dst_primary_ip").val(),
                    dst_secondary_ip: $("#add_dst_secondary_ip").val(),
                    dst_port: $("#add_dst_port").val(),
                    asp_type: $("#add_asp_type").val(),
                    si: $("#add_si").val(),
                    routing_cpu: $("#add_routing_cpu").val(),
                    status: $("#add_status").val(),
                    dsbd_yn: $("#add_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#m3uaInsertModal').modal('hide');
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
        //M3ua 중복 체크
        $("#m3uaDupChk").on('click', function () {
            checkM3uaDup();
        });
        //저장
        $("#m3uaAdd").on('click', function () {
            $("#m3uaAddForm").submit();
        });
        //숫자만
        $("#add_sortseq,#add_slc,#add_src_port,#add_dst_port").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }

    //Host 중복 확인
    function checkM3uaDup() {
        AotAjax.excute("${CONTEXT_PATH}/obstacle/m3ua/m3uaPopup.do", {
            actionID: "ACTION_M3UA_CHECK",
            sortseq: $("#add_sortseq").val()
        }, {
            autoResultFunctionTF: false
        }).done(function (response) {
            var str = response.checkRet;
            if (str == "NG") {
                AotSmartAdmin.smallBoxWarning($("#add_sortseq").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                $("#add_checkM3ua").val("N");
                $("#add_sortseq").val("");
                $("#add_sortseq").focus();
            } else {
                AotSmartAdmin.smallBoxsuccess($("#add_sortseq").val() + " <spring:message code='MSG.OBSTACLE.M3UA.ALERT.POSSIBLE_SORTSEQ'/>");
                $("#add_checkM3ua").val("Y");
            }
        });
    }
</script>