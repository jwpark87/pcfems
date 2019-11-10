<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/gt/Add.jsp
 - 설      명	: Gt 관리 등록 page 
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
            <form id="gtAddForm" id="gtAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkGt" id="add_checkGt" value="N">
                <fieldset>
                    <div class="row">
                        <!-- gt -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.GT"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_gt" id="add_gt" maxlength="16">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="gtDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
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
                                <input type="text" name="add_db_name" id="add_db_name" maxlength="30">
                            </label>
                        </section>
                        <!--  PC_LIST_NM -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.PC_LIST_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_pc_list_nm" id="add_pc_list_nm" maxlength="16">
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
                                <input type="text" name="add_country" id="add_country" maxlength="50">
                            </label>
                        </section>
                        <!--  시업자 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.GT.DETAIL.CARRIER_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_carrier_nm" id="add_carrier_nm" maxlength="50">
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
            <button type="button" class="btn btn-primary" id="gtAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('gtDupChk', function (value, element) {
            return ($("#add_checkGt").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.SETTING.GT.DETAIL.GT'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#gtAddForm").validate({
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
                add_db_name: {
                    required: true,
                    maxlength: 30
                },
                add_gt: {
                    required: true,
                    gtDupChk: true,
                    maxlength: 16
                },
                add_pc_list_nm: {
                    required: true,
                    maxlength: 16
                },
                add_country: {
                    required: true,
                    maxlength: 50
                },
                add_carrier_nm: {
                    required: true,
                    maxlength: 50
                }
            },
            messages: {
                add_db_name: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.DB_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_gt: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.GT'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_pc_list_nm: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.PC_LIST_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_country: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.COUNTRY'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                },
                add_carrier_nm: {
                    required: '<spring:message code='TEXT.SETTING.GT.DETAIL.CARRIER_NM'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>',
                    maxlength: '<spring:message code='MSG.ALERT.ALLOW_OVER'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/gt/gtInsertPopup.do", {
                    actionID: "ACTION_GT_INSERT_OK",
                    db_name: $("#add_db_name").val(),
                    gt: $("#add_gt").val(),
                    pc_list_nm: $("#add_pc_list_nm").val(),
                    country: $("#add_country").val(),
                    carrier_nm: $("#add_carrier_nm").val(),
                    dsbd_yn: $("#add_dsbd_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#gtInsertModal').modal('hide');
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
        //Gt 중복 체크
        $("#gtDupChk").on('click', function () {
            checkGtDup();
        });
        //저장
        $("#gtAdd").on('click', function () {
            $("#gtAddForm").submit();
        });
        /* $("#add_host").on('keyup', function() {
            $(this).val($(this).val().replace(/[^0-9a-zA-Z_#]/g, ""));
        }); */
    }

    //Host 중복 확인
    function checkGtDup() {
        if ($("#add_gt").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/gt/gtPopup.do", {
                actionID: "ACTION_GT_CHECK",
                gt: $("#add_gt").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = e.checkRet;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_gt").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkGt").val("N");
                    $("#add_gt").val("");
                    $("#add_gt").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_gt").val() + " <spring:message code='MSG.SETTING.GT.ALERT.POSSIBLE_GT'/>");
                    $("#add_checkGt").val("Y");
                }
            });
        }
    }
</script>
