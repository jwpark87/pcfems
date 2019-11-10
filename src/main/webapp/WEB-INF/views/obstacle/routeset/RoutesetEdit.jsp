<%----------------------------------------------------------------------------------------
  - 파일이름	: obstacle/routeset/Edit.jsp
 - 설      명	: Routeset 현황 수정 page  
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.03    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.ROUTESET.SUBTITLE.ROUTESET_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="routesetModForm" id="routesetModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">

                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.LOCALITY"/>
                            </label>
                            <label class="select">
                                <%-- <input type="text" name="mod_locality" id="mod_locality" value="${ROUTESET_DETAIL_DATA.locality}" readonly style="background-color: #eee;opacity: 1;"> --%>
                                <aot:select name="mod_locality" id="mod_locality" option="disabled" group="LOCALITY" style="height:32px;background-color: #eee;opacity: 1;"
                                            selected="${ROUTESET_DETAIL_DATA.locality}"/>
                                <i style="background-color: #eee; box-shadow: 0 0 0 9px #eee;"></i>
                            </label>
                        </section>
                        <!-- apc -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.APC"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_apc" id="mod_apc" value="${ROUTESET_DETAIL_DATA.apc}" readonly style="background-color: #eee; opacity: 1;">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.NA"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_na" id="mod_na" maxlength="10" value="${ROUTESET_DETAIL_DATA.na}">
                            </label>
                        </section>
                        <!--  CLLI -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.CLLI"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_clli" id="mod_clli" maxlength="32" value="${ROUTESET_DETAIL_DATA.clli}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country" id="mod_country" maxlength="20" value="${ROUTESET_DETAIL_DATA.country}">
                            </label>
                        </section>

                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="50" value="${ROUTESET_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>
                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.LOCAL"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_local" id="mod_local" maxlength="30" value="${ROUTESET_DETAIL_DATA.local}">
                            </label>
                        </section>
                        <!--  STATUS -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.OBSTACLE.ROUTESET.DETAIL.STATUS"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_status" id="mod_status" group="LINK_STATUS" selected="${ROUTESET_DETAIL_DATA.status}"/>
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
            <button type="button" class="btn btn-primary" id="routesetMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#routesetModForm").validate({
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
                    required: true,
                    maxlength: 10
                },
                mod_clli: {
                    required: true,
                    maxlength: 32
                }
            },
            messages: {
                mod_na: {
                    required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.NA'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_clli: {
                    required: '<spring:message code='TEXT.OBSTACLE.ROUTESET.DETAIL.CLLI'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstacle/routeset/routesetModPopup.do", {
                    actionID: "ACTION_ROUTESET_UPDATE_OK",
                    locality: $("#mod_locality").val(),
                    apc: $("#mod_apc").val(),
                    na: $("#mod_na").val(),
                    clli: $("#mod_clli").val(),
                    country: $("#mod_country").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    local: $("#mod_local").val(),
                    status: $("#mod_status").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#routesetModModal').modal('hide');
                        $("#locality").val("");
                        $("#apc").val("");
                        goRoutesetList();
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
        $("#routesetMod").on('click', function () {
            $("#routesetModForm").submit();
        });
    }
</script>
