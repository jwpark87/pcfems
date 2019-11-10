<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/gt/Edit.jsp
 - 설      명	: Gt 관리 수정 page 
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
                <spring:message code="TEXT.SETTING.GT.SUBTITLE.GT_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="gtModForm" id="gtModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- gt -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.GT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_gt" id="mod_gt" value="${GT_DETAIL_DATA.gt}" readonly style="background-color: #eee; opacity: 1;">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.DB_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_db_name" id="mod_db_name" value="${GT_DETAIL_DATA.db_name}" maxlength="30">
                            </label>
                        </section>
                        <!--  PC_LIST_NM -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.PC_LIST_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_pc_list_nm" id="mod_pc_list_nm" maxlength="16" value="${GT_DETAIL_DATA.pc_list_nm}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.COUNTRY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_country" id="mod_country" maxlength="50" value="${GT_DETAIL_DATA.country}">
                            </label>
                        </section>
                        <!--  시업자 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_carrier_nm" id="mod_carrier_nm" maxlength="50" value="${GT_DETAIL_DATA.carrier_nm}">
                            </label>
                        </section>
                    </div>
                </fieldset>
                <fieldset>
                    <div class="row">
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.DSBD_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_mod_yn" id="mod_dsbd_yn" group="W001" style="height:32px;" selected="${GT_DETAIL_DATA.dsbd_yn}"/>
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
            <button type="button" class="btn btn-primary" id="gtMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#gtModForm").validate({
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
                mod_db_name: {
                    required: true,
                    maxlength: 30
                },
                mod_gt: {
                    required: true,
                    maxlength: 16
                },
                mod_pc_list_nm: {
                    required: true,
                    maxlength: 16
                },
                mod_country: {
                    required: true,
                    maxlength: 50
                },
                mod_carrier_nm: {
                    required: true,
                    maxlength: 50
                }
            },
            messages: {
                mod_db_name: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.DB_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_gt: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.GT'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_pc_list_nm: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.PC_LIST_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_country: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.COUNTRY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                mod_carrier_nm: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/gt/gtModPopup.do", {
                    actionID: "ACTION_GT_UPDATE_OK",
                    db_name: $("#mod_db_name").val(),
                    gt: $("#mod_gt").val(),
                    pc_list_nm: $("#mod_pc_list_nm").val(),
                    country: $("#mod_country").val(),
                    carrier_nm: $("#mod_carrier_nm").val(),
                    dsbd_yn: $("#mod_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#gtModModal').modal('hide');
                        $("#gt").val("");
                        goGtList();
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
        $("#gtMod").on('click', function () {
            $("#gtModForm").submit();
        });
    }
</script>
