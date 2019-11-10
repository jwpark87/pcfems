<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/country/Edit.jsp
 - 설    명	: COUNTRY설정관리 수정 page
 - 작 성 자	: LHN
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
            <form id="countryModForm" id="countryModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- Prefix -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.PREFIX"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_prefix" id="mod_prefix" value="${COUNTRY_DETAIL_DATA.prefix}" disabled="disabled">
                            </label>
                            <p class="note">
                                <strong>Note:</strong>
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.PREFIX_NOTE"/>
                            </p>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- COUNTRY -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.COUNTRY.DETAIL.COUNTRY_CD_NAME"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_country_cd" id="mod_country_cd" list="${COUNTRY_CD }" selected="${COUNTRY_DETAIL_DATA.country_cd}"/>
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
                                <input type="text" name="mod_remark1" id="mod_remark1" value="${COUNTRY_DETAIL_DATA.remark1}">
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
            <button type="button" class="btn btn-primary" id="countryMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#countryModForm").validate({
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
                mod_prefix: {
                    required: true
                },
                mod_country_cd: {
                    required: true
                },
            },
            messages: {
                mod_prefix: {
                    required: '<spring:message code='TEXT.SETTING.COUNTRY.DETAIL.PREFIX'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_country_cd: {
                    required: '<spring:message code='TEXT.SETTING.COUNTRY.DETAIL.COUNTRY_CD_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/country/sbcModPopup.do", {
                    actionID: "ACTION_COUNTRY_UPDATE_OK",
                    prefix: $("#mod_prefix").val(),
                    country_cd: $("#mod_country_cd").val(),
                    remark1: $("#mod_remark1").val(),
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#countryModModal').modal('hide');
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
        //저장
        $("#countryMod").on('click', function () {
            $("#countryModForm").submit();
        });
    }
</script>
