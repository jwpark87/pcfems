<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/AlarmlevelSet/UpdAlarmlevelSet.jsp
 - 설      명	: 알람 등급 수정 page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.05    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog modal-lg">
    <div class="modal-content">


        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.TITLE"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="almModForm" id="almModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- 알람 코드 -->
                        <section class="col col-sm-12 col-lg-12">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_CODE"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_alm_code" id="mod_alm_code" value="${INFO_DATA.alm_code}" disabled="disabled">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 알람 등급 -->
                        <section class="col col-sm-6 col-lg-6">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_LEVEL"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_alm_level" id="mod_alm_level" group="ALM_LEVEL" selected="${INFO_DATA.alm_level}"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 알람 카테고리 -->
                        <section class="col col-sm-6 col-lg-6">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_CATEGORY"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_alm_category" id="mod_alm_category" value="${INFO_DATA.alm_category}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  알람 명 -->
                        <section class="col col-sm-12 col-lg-12">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.SNMP_NAME"/>
                            </label>
                            <label class="input state-disabled">
                                <i class="icon-append fa fa-comment"></i>
                                <input type="text" name="mod_snmp_name" id="mod_snmp_name" value="${INFO_DATA.snmp_name}" disabled="disabled">
                            </label>
                        </section>
                    </div>
                </fieldset>


                <fieldset>
                    <div class="row">
                        <!--  알람 설명 -->
                        <section class="col col-sm-12 col-lg-12">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_DESC"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-comment"></i>
                                <input type="text" name="mod_alm_desc" id="mod_alm_desc" value="${INFO_DATA.alm_desc}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  클리어 알람 코드 -->
                        <section class="col col-sm-12 col-lg-12">
                            <label class="label">
                                <%-- 								<spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_CLEAR_CODE" /> --%>
                                Recovery
                            </label>
                            <!-- 							<label class="input state-disabled"> -->
                            <!-- 								<i class="icon-append fa fa-comment"></i> -->
                            <%-- 								<input type="text" name="mod_alm_clear_code" id="mod_alm_clear_code" value="${INFO_DATA.alm_clear_code}" disabled="disabled"> --%>
                            <textarea name="mod_alm_recovery" id="mod_alm_recovery" class="form-control" rows="4">${INFO_DATA.alm_recovery}</textarea>
                            <!-- 							</label> -->
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 가청여부 -->
                        <section class="col col-sm-6 col-lg-6">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_SOUND_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_sound_yn" id="mod_sound_yn" group="W001" selected="${INFO_DATA.sound_yn}"/>
                                <i></i>
                            </label>
                        </section>
                        <!-- 사용여부 -->
                        <section class="col col-sm-6 col-lg-6">
                            <label class="label">
                                <spring:message code="TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_USE_YN"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_use_yn" id="mod_use_yn" group="W001" selected="${INFO_DATA.use_yn}"/>
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
            <button type="button" class="btn btn-primary" id="almMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#almModForm").validate({
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
                mod_alm_level: {
                    required: true
                },
                mod_sound_yn: {
                    required: true
                },
                mod_use_yn: {
                    required: true
                }
            },
            messages: {
                mod_alm_level: {
                    required: '<spring:message code='TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_LEVEL'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_sound_yn: {
                    required: '<spring:message code='TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_SOUND_YN'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_use_yn: {
                    required: '<spring:message code='TEXT.ALM.SET.LEVEL.UPD.MODAL.ALM_USE_YN'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/obstaclemst/updalarmlevelPopup.do?", {
                    actionID: "ACTION_ALM_LEVEL_UPDATE_PROCESS",
                    alm_code: "${INFO_DATA.alm_code}",
                    alm_level: $("#mod_alm_level").val(),
                    alm_desc: $("#mod_alm_desc").val(),
                    alm_category: $("#mod_alm_category").val(),
                    alm_recovery: $("#mod_alm_recovery").val(),
                    sound_yn: $("#mod_sound_yn").val(),
                    use_yn: $("#mod_use_yn").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#almMstModModal').modal('hide');
                        init_selected();
                        goList();
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
        $("#almMod").on('click', function () {
            $("#almModForm").submit();
        });
    }
</script>