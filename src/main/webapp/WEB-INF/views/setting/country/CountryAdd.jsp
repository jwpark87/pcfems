<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/country/Add.jsp
 - 설      명	: COUNTRY설정관리 등록 page
 - 작 성 자	    : LHN
 - 작성날짜	: 2017.12.21
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2017.12.21    1.0      LHN      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.SETTING.COUNTRY.SUBTITLE.COUNTRY_DETAIL"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="countryAddForm" id="countryAddForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="add_checkPrefix" id="add_checkPrefix" value="N">
                <fieldset>
                    <div class="row">
                        <!-- Prefix -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.PREFIX"/>
                            </label>
                            <label class="input">
                                <input type="text" name="add_prefix" id="add_prefix" maxlength="20">
                            </label>
                            <p class="note">
                                <strong>Note:</strong>
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.PREFIX_NOTE"/>
                            </p>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="prefixDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 국가명 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.COUNTRY_CD_NAME"/>
                            </label>
                            <label class="select">
                                <aot:select name="add_country_cd" id="add_country_cd" list="${COUNTRY_CD }"/>
                                <i></i>
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  비고 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.REMARK1"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-comment"></i>
                                <input type="text" name="add_remark1" id="add_remark1" maxlength="200">
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
            <button type="button" class="btn btn-primary" id="countryAdd">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        //custom validation 추가
        $.validator.addMethod('prefixDupChk', function (value, element) {
            return ($("#add_checkPrefix").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.SETTING.COUNTRY.DETAIL.PREFIX'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#countryAddForm").validate({
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
                add_prefix: {
                    required: true,
                    prefixDupChk: true
                },
                add_country_cd: {
                    required: true
                },
            },
            messages: {
                add_prefix: {
                    required: '<spring:message code='TEXT.SETTING.COUNTRY.DETAIL.PREFIX'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                add_country_cd: {
                    required: '<spring:message code='TEXT.SETTING.COUNTRY.DETAIL.COUNTRY_CD_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/country/sbcInsertPopup.do", {
                    actionID: "ACTION_COUNTRY_INSERT_OK",
                    prefix: $("#add_prefix").val(),
                    country_cd: $("#add_country_cd").val(),
                    remark1: $("#add_remark1").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#countryInsertModal').modal('hide');
                        goCountryList();
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
        //Prefix 중복 체크
        $("#prefixDupChk").on('click', function () {
            checkPrefixDup();
        });
        //저장
        $("#countryAdd").on('click', function () {
            $("#countryAddForm").submit();
        });
        $("#add_prefix").on('keyup', function () {
            $(this).val($(this).val().replace(/[^0-9a-zA-Z_#]/g, ""));
        });

    }

    //Prefix 중복 확인
    function checkPrefixDup() {

        if ($("#add_prefix").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/country/sbcPopup.do", {
                actionID: "ACTION_PREFIX_CHECK",
                prefix: $("#add_prefix").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str == "NG") {
                    AotSmartAdmin.smallBoxWarning($("#add_prefix").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#add_checkPrefix").val("N");
                    $("#add_prefix").val("");
                    $("#add_prefix").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#add_prefix").val() + " <spring:message code='MSG.SETTING.COUNTRY.ALERT.POSSIBLE_PREFIX'/>");
                    $("#add_checkPrefix").val("Y");
                }
            });
        }
    }
</script>
