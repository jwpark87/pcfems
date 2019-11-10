<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameter/Add.jsp
 - 설      명	: Diameter Host 관리 등록 page 
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
            <form id="diameterAddForm" id="diameterAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkHost" id="add_checkHost" value="N">

                <fieldset>
                    <div class="row">
                        <!-- Host -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.HOST"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_host" id="add_host" maxlength="64">
                            </label>
                        </section>

                        <!--  장비명 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="hostDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>

                        <!-- 국가 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.COUNTRY_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country_nm" id="add_country_nm" maxlength="24">
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
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="26">
                            </label>
                        </section>
                        <!--  국소 -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_local" id="add_local" maxlength="20">
                            </label>
                        </section>
                        <!--  Realm -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REALM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_realm" id="add_realm" maxlength="64">
                            </label>
                        </section>
                        <!--  상대 Peer Mode -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_PEER_MODE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_remote_peer_mode" id="add_remote_peer_mode" maxlength="30">
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
                                <input type="text" name="add_local_slot" id="add_local_slot" maxlength="20">
                            </label>
                        </section>
                        <!--  Local Primary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_PRIMARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_local_primary_ip" id="add_local_primary_ip" maxlength="32">
                            </label>
                        </section>
                        <!--  Local Secondary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_local_secondary_ip" id="add_local_secondary_ip" maxlength="32">
                            </label>
                        </section>
                        <!--  Local Secondary Ip -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.LOCAL_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_local_port" id="add_local_port">
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
                                <input type="text" name="add_remote_primary_ip" id="add_remote_primary_ip" maxlength="32">
                            </label>
                        </section>
                        <!--  Remote Secondary Ip -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_SECONDARY_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_remote_secondary_ip" id="add_remote_secondary_ip" maxlength="32">
                            </label>
                        </section>
                        <!--  Remote Port -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.REMOTE_PORT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_remote_port" id="add_remote_port">
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
                                <input type="text" name="add_access_ctrl_list" id="add_access_ctrl_list" maxlength="500">
                            </label>
                        </section>
                        <!--  Interface -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.INTERFACE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_interface" id="add_interface" maxlength="20">
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
                                <input type="text" name="add_sctp_tw_timer" id="add_sctp_tw_timer">
                            </label>
                        </section>
                        <!--  Sctp Hb Interval -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_HB_INTERVAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_hb_interval" id="add_sctp_hb_interval">
                            </label>
                        </section>
                        <!--  Sctp Assoc Max Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_ASSOC_MAX_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_assoc_max_retrans" id="add_sctp_assoc_max_retrans">
                            </label>
                        </section>
                        <!--  Sctp Rto Init -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_RTO_INIT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_rto_init" id="add_sctp_rto_init">
                            </label>
                        </section>
                        <!--  Sctp Rto Min -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_RTO_MIN"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_rto_min" id="add_sctp_rto_min">
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
                                <input type="text" name="add_sctp_rto_max" id="add_sctp_rto_max">
                            </label>
                        </section>
                        <!--  Sctp Path Max Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_PATH_MAX_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_path_max_retrans" id="add_sctp_path_max_retrans">
                            </label>
                        </section>
                        <!--  Sctp Max Init Retrans -->
                        <section class="col col-3">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_MAX_INIT_RETRANS"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_max_init_retrans" id="add_sctp_max_init_retrans">
                            </label>
                        </section>
                        <!--  Sctp Max Seg -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_MAX_SEG"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_max_seg" id="add_sctp_max_seg">
                            </label>
                        </section>
                        <!--  Sctp Sack Timeout -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.SCTP_SACK_TIMEOUT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_sctp_sack_timeout" id="add_sctp_sack_timeout">
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
                                <input type="text" name="add_contact_name" id="add_contact_name" maxlength="100">
                            </label>
                        </section>
                        <!--  Contact Email -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_EMAIL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_contact_email" id="add_contact_email" maxlength="320">
                            </label>
                        </section>
                        <!--  Contact Noc -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_NOC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_contact_noc" id="add_contact_noc" maxlength="100">
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
                                <input type="text" name="add_contact_fix_phone" id="add_contact_fix_phone" maxlength="32">
                            </label>
                        </section>
                        <!--  Contact Mobile Phone -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.CONTACT_MOBILE_PHONE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_contact_mobile_phone" id="add_contact_mobile_phone" maxlength="32">
                            </label>
                        </section>
                        <!--  Vendor Name -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.VENDOR_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_vendor_name" id="add_vendor_name" maxlength="20">
                            </label>
                        </section>
                        <!--  Open Dt -->
                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.OPEN_DT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_open_dt" id="add_open_dt">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_status" id="add_status" group="LINK_STATUS" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.DSBD_YN"/>
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
            <button type="button" class="btn btn-primary" id="diameterAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(
        function () {

            //custom validation 추가
            $.validator.addMethod('hostDupChk', function (value, element) {
                return ($("#add_checkHost").val() == "Y") ? true : false;
            }, '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.HOST'/>' + ' / ' + '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.LOCALITY'/>'
                + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

            var errorClass = 'invalid';
            var errorElement = 'em';
            $("#diameterAddForm").validate({
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
                    add_host: {
                        required: true,
                        hostDupChk: true,
                        maxlength: 64
                    },
                    add_locality: {
                        required: true
                    },
                    add_country_nm: {
                        required: true,
                        maxlength: 24
                    },
                    add_carrier_nm: {
                        required: true,
                        maxlength: 26
                    },
                    add_realm: {
                        required: true,
                        maxlength: 64
                    },
                    add_contact_email: {
                        email: true
                    },
                    add_local_port: {
                        digits: true
                    },
                    add_remote_port: {
                        digits: true
                    },
                    add_sctp_tw_timer: {
                        digits: true
                    },
                    add_sctp_hb_interval: {
                        digits: true
                    },
                    add_sctp_assoc_max_retrans: {
                        digits: true
                    },
                    add_sctp_rto_init: {
                        digits: true
                    },
                    add_sctp_rto_min: {
                        digits: true
                    },
                    add_sctp_rto_max: {
                        digits: true
                    },
                    add_sctp_path_max_retrans: {
                        digits: true
                    },
                    add_sctp_max_init_retrans: {
                        digits: true
                    },
                    add_sctp_max_seg: {
                        digits: true
                    },
                    add_sctp_sack_timeout: {
                        digits: true
                    }
                },
                messages: {
                    add_host: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.HOST'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_locality: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    },
                    add_country_nm: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.COUNTRY_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_carrier_nm: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_realm: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_contact_email: {
                        email: '<spring:message code='MSG.SETTING.DIAMETER.ALERT.EMAIL'/>'
                    },
                    add_local_port: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_remote_port: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_tw_timer: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_hb_interval: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_assoc_max_retrans: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_rto_init: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_rto_min: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_rto_max: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_path_max_retrans: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_max_init_retrans: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_max_seg: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    },
                    add_sctp_sack_timeout: {
                        digits: '<spring:message code='MSG.ALERT.DIGIT'/>'
                    }

                },
                // Do not change code below
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                },
                submitHandler: function (form) {
                    AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterInsertPopup.do", {
                        actionID: "ACTION_DIAMETER_INSERT_OK",
                        locality: $("#add_locality").val(),
                        country_nm: $("#add_country_nm").val(),
                        carrier_nm: $("#add_carrier_nm").val(),
                        local: $("#add_local").val(),
                        realm: $("#add_realm").val(),
                        host: $("#add_host").val(),
                        remote_peer_mode: $("#add_remote_peer_mode").val(),
                        local_slot: $("#add_local_slot").val(),
                        local_primary_ip: $("#add_local_primary_ip").val(),
                        local_secondary_ip: $("#add_local_secondary_ip").val(),
                        local_port: $("#add_local_port").val(),
                        remote_primary_ip: $("#add_remote_primary_ip").val(),
                        remote_secondary_ip: $("#add_remote_secondary_ip").val(),
                        remote_port: $("#add_remote_port").val(),
                        access_ctrl_list: $("#add_access_ctrl_list").val(),
                        strInterface: $("#add_interface").val(),
                        sctp_tw_timer: $("#add_sctp_tw_timer").val(),
                        sctp_hb_interval: $("#add_sctp_hb_interval").val(),
                        sctp_assoc_max_retrans: $("#add_sctp_assoc_max_retrans").val(),
                        sctp_rto_init: $("#add_sctp_rto_init").val(),
                        sctp_rto_min: $("#add_sctp_rto_min").val(),
                        sctp_rto_max: $("#add_sctp_rto_max").val(),
                        sctp_path_max_retrans: $("#add_sctp_path_max_retrans").val(),
                        sctp_max_init_retrans: $("#add_sctp_max_init_retrans").val(),
                        sctp_max_seg: $("#add_sctp_max_seg").val(),
                        sctp_sack_timeout: $("#add_sctp_sack_timeout").val(),
                        contact_name: $("#add_contact_name").val(),
                        contact_email: $("#add_contact_email").val(),
                        contact_noc: $("#add_contact_noc").val(),
                        contact_fix_phone: $("#add_contact_fix_phone").val(),
                        contact_mobile_phone: $("#add_contact_mobile_phone").val(),
                        vendor_name: $("#add_vendor_name").val(),
                        open_dt: $("#add_open_dt").val(),
                        status: $("#add_status").val(),
                        dsbd_yn: $("#add_dsbd_yn").val()
                    }, {
                        autoResultFunctionTF: false
                    }).done(function (response) {
                        var str = response.returnMsg.split(":");
                        if (str[0] === "success") {
                            AotSmartAdmin.smallBoxsuccess(str[1]);
                            $('#diameterInsertModal').modal('hide');
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
        $('#add_open_dt').datetimepicker({
            format: "YYYY-MM-DD"
        });
    }

    //각 클릭 이벤트
    function bindEvent() {
        //Diameter 중복 체크
        $("#hostDupChk").on('click', function () {
            checkHostDup();
        });
        //저장
        $("#diameterAdd").on('click', function () {
            $("#diameterAddForm").submit();
        });

        //숫자만
        $("#add_local_port, #add_remote_port, #add_sctp_tw_timer, #add_sctp_hb_interval, #add_sctp_assoc_max_retrans, #add_sctp_rto_init, #add_sctp_rto_min").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
        $("#add_sctp_rto_max,#add_sctp_path_max_retrans, #add_sctp_max_init_retrans, #add_sctp_max_seg, #add_sctp_sack_timeout").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });

    }

    //Host 중복 확인
    function checkHostDup() {
        if ($("#add_host").val() && $("#add_locality").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterPopup.do", {
                actionID: "ACTION_DIAMETER_CHECK",
                host: $("#add_host").val(),
                locality: $("#add_locality").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str == "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_host").val() + " / " + $("#add_locality option:checked").text() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkHost").val("N");
                    $("#add_locality").val("");
                    $("#add_host").val("");
                    $("#add_host").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_host").val() + " / " + $("#add_locality option:checked").text() + " <spring:message code='MSG.SETTING.DIAMETER.ALERT.POSSIBLE_HOST'/>");
                    $("#add_checkHost").val("Y");
                }
            });
        }
    }
</script>
