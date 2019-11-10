<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/linkset/Edit.jsp
 - 설      명	: Linkset 현황 수정 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.04.27    1.0      KYM      신규작성
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
                <spring:message code="TEXT.OBSTACLE.LINKSET.SUBTITLE.LINKSET_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="linksetModForm" id="linksetModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!--  LOCALITY -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_locality" id="mod_locality" option="disabled" group="LOCALITY" style="height:32px;background-color: #eee;opacity: 1;"
                                            selected="${LINKSET_DETAIL_DATA.locality}"/>
                                <i style="background-color: #eee; box-shadow: 0 0 0 9px #eee;"></i>
                            </label>
                        </section>
                        <!-- APC -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_apc" id="mod_apc" readonly style="background-color: #eee; opacity: 1;" value="${LINKSET_DETAIL_DATA.apc}">
                            </label>
                        </section>

                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  NA -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_na" id="mod_na" maxlength="10" value="${LINKSET_DETAIL_DATA.na}">
                            </label>
                        </section>
                        <!--  국가명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country" id="mod_country" maxlength="20" value="${LINKSET_DETAIL_DATA.country}">
                            </label>
                        </section>
                        <!--  사업자명 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="26" value="${LINKSET_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>

                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">

                        <!--  clli -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_clli" id="mod_clli" maxlength="32" value="${LINKSET_DETAIL_DATA.clli}">
                            </label>
                        </section>
                        <!-- 상태 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" selected="${LINKSET_DETAIL_DATA.status}"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 출력여부 -->
                        <section class="col col-4">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.LINKSET.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_dsbd_yn" id="mod_dsbd_yn" group="W001" selected="${LINKSET_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="linksetMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#linksetModForm").validate({
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
                mod_clli: {
                    required: true
                }
            },
            messages: {
                mod_na: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_clli: {
                    required: '<spring:message code='TEXT.OBSTACLE.LINKSET.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/linkset/linksetModPopup.do", {
                    actionID: "ACTION_LINKSET_UPDATE_OK",
                    locality: $("#mod_locality").val(),
                    apc: $("#mod_apc").val(),
                    na: $("#mod_na").val(),
                    country: $("#mod_country").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    clli: $("#mod_clli").val(),
                    status: $("#mod_status").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#linksetModModal').modal('hide');
                        goLinksetList();
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
        $("#linksetMod").on('click', function () {
            $("#linksetModForm").submit();
        });
        //숫자만
        $("#mod_na").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
        });
    }
</script>