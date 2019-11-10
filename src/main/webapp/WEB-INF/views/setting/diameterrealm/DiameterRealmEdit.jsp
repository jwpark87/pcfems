<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameterrealm/Edit.jsp
 - 설      명	: Diameter Realm 관리 수정 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.04.28    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

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
            <form id="diameterRealmModForm" id="diameterRealmModForm" class="smart-form" novalidate="novalidate">


                <fieldset>
                    <div class="row">
                        <!--  Realm -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.REALM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_realm" id="mod_realm" value="${DIAMETER_REALM_DETAIL_DATA.realm}" maxlength="64" readonly style="background-color: #eee; opacity: 1;">
                            </label>
                        </section>
                        <!--  장비명 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_locality" id="mod_locality" option="disabled" group="LOCALITY" style="height:32px;background-color: #eee;opacity: 1;"
                                            selected="${DIAMETER_REALM_DETAIL_DATA.locality}"/>
                                <i style="background-color: #eee; box-shadow: 0 0 0 9px #eee;"></i>
                            </label>
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
                                <input type="text" name="mod_country" id="mod_country" value="${DIAMETER_REALM_DETAIL_DATA.country}" maxlength="24">
                            </label>
                        </section>
                        <!--  사업자 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" value="${DIAMETER_REALM_DETAIL_DATA.carrier_nm}" maxlength="26">
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
                                <input type="text" name="mod_routing_peer" id="mod_routing_peer" value="${DIAMETER_REALM_DETAIL_DATA.routing_peer}" maxlength="30">
                            </label>
                        </section>
                        <!--  contact_email -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.CONTACT_EMAIL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_contact_email" id="mod_contact_email" value="${DIAMETER_REALM_DETAIL_DATA.contact_email}" maxlength="320">
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
                                <input type="text" name="mod_open_dt" id="mod_open_dt" value="${DIAMETER_REALM_DETAIL_DATA.open_dt} }">
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.DIAMETER.REALM.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_dsbd_yn" id="mod_dsbd_yn" group="W001" style="height:32px;" selected="${DIAMETER_REALM_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="diameterRealmMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#diameterRealmModForm").validate({
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
                mod_country: {
                    required: true,
                    maxlength: 24
                },
                mod_carrier_nm: {
                    required: true,
                    maxlength: 26
                },
                mod_routing_peer: {
                    required: true
                }

            },
            messages: {
                mod_country: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.COUNTRY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_carrier_nm: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_routing_peer: {
                    required: '<spring:message code='TEXT.SETTING.DIAMETER.REALM.DETAIL.ROUTING_PEER'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterRealmModPopup.do", {
                    actionID: "ACTION_DIAMETER_REALM_UPDATE_OK",
                    locality: $("#mod_locality").val(),
                    realm: $("#mod_realm").val(),
                    country: $("#mod_country").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    routing_peer: $("#mod_routing_peer").val(),
                    contact_email: $("#mod_contact_email").val(),
                    open_dt: $("#mod_open_dt").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#diameterRealmModModal').modal('hide');
                        $("#realm").val("");
                        $("#locality").val("");
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
        //저장
        $("#diameterRealmMod").on('click', function () {
            $("#diameterRealmModForm").submit();
        });
    }

    //달력
    function datePickerInitAdd() {
        $('#mod_open_dt').datetimepicker({
            format: "YYYY-MM-DD"
        });
    }
</script>
