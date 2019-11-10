<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameterrealm/Add.jsp
 - 설      명	: Diameter Realm 관리 등록 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.04.28    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<c:set var="CHOICE">
    <spring:message code="TEXT.COMM.SEL.CHOICE"/>
</c:set>

<div class="modal-dialog" style="width: 780px;">
    <!-- 원래 600 조정  KYM -->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.SETTING.DIAMETER.REALM.SUBTITLE.DIAMETER_REALM_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="diameterRealmAddForm" id="diameterRealmAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkRealm" id="add_checkRealm" value="N">
                <fieldset>
                    <div class="row">
                        <!-- Realm -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.REALM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_realm" id="add_realm" maxlength="64">
                            </label>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_locality" id="add_locality" init="YES" initCode="" initName="${CHOICE}" group="LOCALITY" style="height:32px;"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="realmDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!-- 국가 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_country" id="add_country" maxlength="24">
                            </label>
                        </section>
                        <!--  사업자 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="26">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  routing_peer -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.ROUTING_PEER"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_routing_peer" id="add_routing_peer" maxlength="30">
                            </label>
                        </section>
                        <!--  contact_email -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.CONTACT_EMAIL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_contact_email" id="add_contact_email" maxlength="320">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">

                        <!--  OPEN_DT -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.OPEN_DT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_open_dt" id="add_open_dt">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.DSBD_YN"/>
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
            <button type="button" class="btn btn-primary" id="diameterRealmAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(
        function () {

            //custom validation 추가
            $.validator.addMethod('realmDupChk', function (value, element) {
                return ($("#add_checkRealm").val() == "Y") ? true : false;
            }, '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.REALM'/>' + ' / ' + '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.LOCALITY'/>'
                + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

            var errorClass = 'invalid';
            var errorElement = 'em';
            $("#diameterRealmAddForm").validate({
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
                    add_realm: {
                        required: true,
                        realmDupChk: true,
                        maxlength: 64
                    },
                    add_locality: {
                        required: true
                    },
                    add_country: {
                        required: true,
                        maxlength: 24
                    },
                    add_carrier_nm: {
                        required: true,
                        maxlength: 26
                    },
                    add_routing_peer: {
                        required: true
                    }
                },
                messages: {
                    add_realm: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.REALM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_country: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.COUNTRY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_carrier_nm: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                        maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                    },
                    add_locality: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.LOCALITY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                    },
                    add_routing_peer: {
                        required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.ROUTING_PEER'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    }
                },
                // Do not change code below
                errorPlacement: function (error, element) {
                    error.insertAfter(element.parent());
                },
                submitHandler: function (form) {
                    AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterRealmInsertPopup.do", {
                        actionID: "ACTION_DIAMETER_REALM_INSERT_OK",
                        locality: $("#add_locality").val(),
                        realm: $("#add_realm").val(),
                        country: $("#add_country").val(),
                        carrier_nm: $("#add_carrier_nm").val(),
                        routing_peer: $("#add_routing_peer").val(),
                        contact_email: $("#add_contact_email").val(),
                        open_dt: $("#add_open_dt").val(),
                        dsbd_yn: $("#add_dsbd_yn").val()
                    }, {
                        autoResultFunctionTF: false
                    }).done(function (response) {
                        var str = response.returnMsg.split(":");
                        if (str[0] == "success") {
                            AotSmartAdmin.smallBoxsuccess(str[1]);
                            $('#diameterRealmInsertModal').modal('hide');
                            goDiameterRealmList();
                        } else {
                            AotSmartAdmin.smallBoxWarning(str[1]);
                        }
                    });
                }
            });
            bindEvent();
            datePickerInitAdd();
        });

    //각 클릭 이벤트
    function bindEvent() {
        //DiameterRealm 중복 체크
        $("#realmDupChk").on('click', function () {
            checkRealmDup();
        });
        //저장
        $("#diameterRealmAdd").on('click', function () {
            $("#diameterRealmAddForm").submit();
        });

    }

    //달력
    function datePickerInitAdd() {
        $('#add_open_dt').datetimepicker({
            format: "YYYY-MM-DD"
        });
    }

    //Host 중복 확인
    function checkRealmDup() {
        if ($("#add_realm").val() && $("#add_locality").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterRealmPopup.do", {
                actionID: "ACTION_DIAMETER_REALM_CHECK",
                realm: $("#add_realm").val(),
                locality: $("#add_locality").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str == "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_realm").val() + " / " + $("#add_locality option:checked").text() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkRealm").val("N");
                    $("#add_locality").val("");
                    $("#add_realm").val("");
                    $("#add_realm").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_realm").val() + " / " + $("#add_locality option:checked").text() + " <spring:message code='MSG.SETTING.DIAMETER.REALM.ALERT.POSSIBLE_REALM'/>");
                    $("#add_checkRealm").val("Y");
                }
            });
        }
    }
</script>
