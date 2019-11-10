<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameter/Edit.jsp
 - 설      명	: Diameter Host 관리 수정 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.03    1.0      KYM      신규작성
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
                <spring:message code="TEXT.SETTING.DIAMETER.SUBTITLE.DIAMETER_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="diameterModForm" id="diameterModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- Host -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.HOST"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_host" id="mod_host" value="${DIAMETER_DETAIL_DATA.host}" readonly style="background-color: #eee; opacity: 1;">
                            </label>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_locality" id="mod_locality" option="disabled" group="LOCALITY" style="height:32px;background-color: #eee;opacity: 1;"
                                            selected="${DIAMETER_DETAIL_DATA.locality}"/>
                                <i style="background-color: #eee; box-shadow: 0 0 0 9px #eee;"></i>
                            </label>
                        </section>
                        <!-- 국가 -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country_nm" id="mod_country_nm" maxlength="24" value="${DIAMETER_DETAIL_DATA.country_nm}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  사업자 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="26" value="${DIAMETER_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>
                        <!--  국소 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local" id="mod_local" maxlength="20" value="${DIAMETER_DETAIL_DATA.local}">
                            </label>
                        </section>
                        <!--  Realm -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REALM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_realm" id="mod_realm" maxlength="64" value="${DIAMETER_DETAIL_DATA.realm}">
                            </label>
                        </section>
                        <!--  상대 Peer Mode -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_PEER_MODE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remote_peer_mode" id="mod_remote_peer_mode" maxlength="30" value="${DIAMETER_DETAIL_DATA.remote_peer_mode}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Local Slot -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_SLOT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local_slot" id="mod_local_slot" maxlength="20" value="${DIAMETER_DETAIL_DATA.local_slot}">
                            </label>
                        </section>
                        <!--  Local Primary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_PRIMARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local_primary_ip" id="mod_local_primary_ip" maxlength="32" value="${DIAMETER_DETAIL_DATA.local_primary_ip}">
                            </label>
                        </section>
                        <!--  Local Secondary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local_secondary_ip" id="mod_local_secondary_ip" maxlength="32" value="${DIAMETER_DETAIL_DATA.local_secondary_ip}">
                            </label>
                        </section>
                        <!--  Local Secondary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local_port" id="mod_local_port" value="${DIAMETER_DETAIL_DATA.local_port }">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Remote Primary Ip -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_PRIMARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remote_primary_ip" id="mod_remote_primary_ip" maxlength="32" value="${DIAMETER_DETAIL_DATA.remote_primary_ip}">
                            </label>
                        </section>
                        <!--  Remote Secondary Ip -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remote_secondary_ip" id="mod_remote_secondary_ip" maxlength="32" value="${DIAMETER_DETAIL_DATA.remote_secondary_ip}">
                            </label>
                        </section>
                        <!--  Remote Port -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remote_port" id="mod_remote_port" value="${DIAMETER_DETAIL_DATA.remote_port }">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Access Ctrl List -->
                        <section class="col col-9">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.ACCESS_CTRL_LIST"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_access_ctrl_list" id="mod_access_ctrl_list" maxlength="500" value="${DIAMETER_DETAIL_DATA.access_ctrl_list}">
                            </label>
                        </section>
                        <!--  Interface -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.INTERFACE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_interface" id="mod_interface" maxlength="20" value="${DIAMETER_DETAIL_DATA.strInterface}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Sctp Tw Timer -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_TW_TIMER"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_tw_timer" id="mod_sctp_tw_timer" value="${DIAMETER_DETAIL_DATA.sctp_tw_timer }">
                            </label>
                        </section>
                        <!--  Sctp Hb Interval -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_HB_INTERVAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_hb_interval" id="mod_sctp_hb_interval" value="${DIAMETER_DETAIL_DATA.sctp_hb_interval }">
                            </label>
                        </section>
                        <!--  Sctp Assoc Max Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_ASSOC_MAX_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_assoc_max_retrans" id="mod_sctp_assoc_max_retrans" value="${DIAMETER_DETAIL_DATA.sctp_assoc_max_retrans }">
                            </label>
                        </section>
                        <!--  Sctp Rto Init -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_RTO_INIT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_rto_init" id="mod_sctp_rto_init" value="${DIAMETER_DETAIL_DATA.sctp_rto_init }">
                            </label>
                        </section>
                        <!--  Sctp Rto Min -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_RTO_MIN"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_rto_min" id="mod_sctp_rto_min" value="${DIAMETER_DETAIL_DATA.sctp_rto_min }">
                            </label>
                        </section>

                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  Sctp Rto Max -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_RTO_MAX"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_rto_max" id="mod_sctp_rto_max" value="${DIAMETER_DETAIL_DATA.sctp_rto_max }">
                            </label>
                        </section>
                        <!--  Sctp Path Max Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_PATH_MAX_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_path_max_retrans" id="mod_sctp_path_max_retrans" value="${DIAMETER_DETAIL_DATA.sctp_path_max_retrans }">
                            </label>
                        </section>
                        <!--  Sctp Max Init Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_MAX_INIT_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_max_init_retrans" id="mod_sctp_max_init_retrans" value="${DIAMETER_DETAIL_DATA.sctp_max_init_retrans }">
                            </label>
                        </section>
                        <!--  Sctp Max Seg -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_MAX_SEG"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_max_seg" id="mod_sctp_max_seg" value="${DIAMETER_DETAIL_DATA.sctp_max_seg }">
                            </label>
                        </section>
                        <!--  Sctp Sack Timeout -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_SACK_TIMEOUT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sctp_sack_timeout" id="mod_sctp_sack_timeout" value="${DIAMETER_DETAIL_DATA.sctp_sack_timeout }">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  Contact Name -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_name" id="mod_contact_name" maxlength="100" value="${DIAMETER_DETAIL_DATA.contact_name}">
                            </label>
                        </section>
                        <!--  Contact Email -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_EMAIL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_email" id="mod_contact_email" maxlength="320" value="${DIAMETER_DETAIL_DATA.contact_email}">
                            </label>
                        </section>
                        <!--  Contact Noc -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_NOC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_noc" id="mod_contact_noc" maxlength="100" value="${DIAMETER_DETAIL_DATA.contact_noc}">
                            </label>
                        </section>
                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  Contact Fix Phone -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_FIX_PHONE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_fix_phone" id="mod_contact_fix_phone" maxlength="32" value="${DIAMETER_DETAIL_DATA.contact_fix_phone}">
                            </label>
                        </section>
                        <!--  Contact Mobile Phone -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_MOBILE_PHONE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_mobile_phone" id="mod_contact_mobile_phone" maxlength="32" value="${DIAMETER_DETAIL_DATA.contact_mobile_phone}">
                            </label>
                        </section>
                        <!--  Vendor Name -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.VENDOR_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_vendor_name" id="mod_vendor_name" maxlength="20" value="${DIAMETER_DETAIL_DATA.vendor_name}">
                            </label>
                        </section>
                        <!--  Open Dt -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.OPEN_DT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_open_dt" id="mod_open_dt" value="${DIAMETER_DETAIL_DATA.open_dt }">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" style="height:32px;" selected="${DIAMETER_DETAIL_DATA.status}"/>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_dsbd_yn" id="mod_dsbd_yn" group="W001" style="height:32px;" selected="${DIAMETER_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="diameterMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#diameterModForm").validate({
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
                mod_country_nm: {
                    required: true,
                    maxlength: 24
                },
                mod_carrier_nm: {
                    required: true,
                    maxlength: 26
                },
                mod_locality: {
                    required: true,
                    maxlength: 16
                },
                mod_realm: {
                    required: true,
                    maxlength: 64
                },
                mod_contact_email: {
                    email: true
                },
                mod_local_port: {
                    digits: true
                },
                mod_remote_port: {
                    digits: true
                },
                mod_sctp_tw_timer: {
                    digits: true
                },
                mod_sctp_hb_interval: {
                    digits: true
                },
                mod_sctp_assoc_max_retrans: {
                    digits: true
                },
                mod_sctp_rto_init: {
                    digits: true
                },
                mod_sctp_rto_min: {
                    digits: true
                },
                mod_sctp_rto_max: {
                    digits: true
                },
                mod_sctp_path_max_retrans: {
                    digits: true
                },
                mod_sctp_max_init_retrans: {
                    digits: true
                },
                mod_sctp_max_seg: {
                    digits: true
                },
                mod_sctp_sack_timeout: {
                    digits: true
                }
            },
            messages: {
                mod_country_nm: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.COUNTRY_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_carrier_nm: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_locality: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_realm: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_contact_email: {
                    email: '<spring:message code='MSG.SETTING.DIAMETER.ALERT.EMAIL'/>'
                },
                mod_local_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_remote_port: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_tw_timer: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_hb_interval: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_assoc_max_retrans: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_rto_init: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_rto_min: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_rto_max: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_path_max_retrans: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_max_init_retrans: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_max_seg: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                },
                mod_sctp_sack_timeout: {
                    digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterModPopup.do", {
                    actionID: "ACTION_DIAMETER_UPDATE_OK",
                    locality: $("#mod_locality").val(),
                    country_nm: $("#mod_country_nm").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    local: $("#mod_local").val(),
                    realm: $("#mod_realm").val(),
                    host: $("#mod_host").val(),
                    remote_peer_mode: $("#mod_remote_peer_mode").val(),
                    local_slot: $("#mod_local_slot").val(),
                    local_primary_ip: $("#mod_local_primary_ip").val(),
                    local_secondary_ip: $("#mod_local_secondary_ip").val(),
                    local_port: $("#mod_local_port").val(),
                    remote_primary_ip: $("#mod_remote_primary_ip").val(),
                    remote_secondary_ip: $("#mod_remote_secondary_ip").val(),
                    remote_port: $("#mod_remote_port").val(),
                    access_ctrl_list: $("#mod_access_ctrl_list").val(),
                    strInterface: $("#mod_interface").val(),
                    sctp_tw_timer: $("#mod_sctp_tw_timer").val(),
                    sctp_hb_interval: $("#mod_sctp_hb_interval").val(),
                    sctp_assoc_max_retrans: $("#mod_sctp_assoc_max_retrans").val(),
                    sctp_rto_init: $("#mod_sctp_rto_init").val(),
                    sctp_rto_min: $("#mod_sctp_rto_min").val(),
                    sctp_rto_max: $("#mod_sctp_rto_max").val(),
                    sctp_path_max_retrans: $("#mod_sctp_path_max_retrans").val(),
                    sctp_max_init_retrans: $("#mod_sctp_max_init_retrans").val(),
                    sctp_max_seg: $("#mod_sctp_max_seg").val(),
                    sctp_sack_timeout: $("#mod_sctp_sack_timeout").val(),
                    contact_name: $("#mod_contact_name").val(),
                    contact_email: $("#mod_contact_email").val(),
                    contact_noc: $("#mod_contact_noc").val(),
                    contact_fix_phone: $("#mod_contact_fix_phone").val(),
                    contact_mobile_phone: $("#mod_contact_mobile_phone").val(),
                    vendor_name: $("#mod_vendor_name").val(),
                    open_dt: $("#mod_open_dt").val(),
                    status: $("#mod_status").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#diameterModModal').modal('hide');
                        $("#host").val("");
                        goDiameterList();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            }
        });
        bindEvent();
        datePickerInitAdd();
    });

    //달력
    function datePickerInitAdd() {
        $('#mod_open_dt').datetimepicker({
            format: "YYYY-MM-DD"
        });
    }

    //각 클릭 이벤트
    function bindEvent() {
        //저장
        $("#diameterMod").on('click', function () {
            $("#diameterModForm").submit();
        });

        //숫자만
        $("#mod_local_port, #mod_remote_port, #mod_sctp_tw_timer, #mod_sctp_hb_interval, #mod_sctp_assoc_max_retrans, #mod_sctp_rto_init, #mod_sctp_rto_min").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
        $("#mod_sctp_rto_max,#mod_sctp_path_max_retrans, #mod_sctp_max_init_retrans, #mod_sctp_max_seg, #mod_sctp_sack_timeout").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });

    }
</script>