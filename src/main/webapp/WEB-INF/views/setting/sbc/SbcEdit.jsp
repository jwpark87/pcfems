<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/sbc/Edit.jsp
 - 설      명	: SBC설정관리 수정 page
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
                <spring:message code="TEXT.SETTING.SBC.SUBTITLE.SBC_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="sbcModForm" id="sbcModForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="mod_checkSbcNodeIp" id="mod_checkSbcNodeIp" value="Y">

                <fieldset>
                    <div class="row">
                        <!-- SBC 그룹 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.SBC.DETAIL.SBC_GROUP_NAME"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_sbc_group_id" id="mod_sbc_group_id" list="${SBC_GROUP_ID }" selected="${SBC_DETAIL_DATA.sbc_group_id}"/>
                                <i></i>
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- SBC Name -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.SBC.DETAIL.SBC_NODE_NAME"/>
                            </label>
                            <label class="input state-disabled">
                                <input type="text" name="mod_sbc_node_name" id="mod_sbc_node_name" value="${SBC_DETAIL_DATA.sbc_node_name}" disabled="disabled">
                            </label>
                            <p class="note">
                                <strong>Note:</strong>
                                <spring:message code="TEXT.SETTING.SBC.DETAIL.SBC_NODE_NAME_NOTE"/>
                            </p>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- SBC IP -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.SBC.DETAIL.SBC_NODE_IP"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_sbc_node_ip" id="mod_sbc_node_ip" value="${SBC_DETAIL_DATA.sbc_node_ip}" onchange="onChangeIp(this)">
                            </label>
                        </section>
                        <!-- 체크버튼 -->
                        <section class="col col-2">
                            <label class="label">&nbsp;</label>
                            <button type="button" class="btn btn-primary btn-sm" id="sbcNodeIpDupChk">
                                <spring:message code="TEXT.COMM.BTN.DUP"/>
                            </button>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!--  비고 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.SETTING.SBC.DETAIL.REMARK1"/>
                            </label>
                            <label class="input">
                                <i class="icon-append fa fa-comment"></i>
                                <input type="text" name="mod_remark1" id="mod_remark1" value="${SBC_DETAIL_DATA.remark1}">
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
            <button type="button" class="btn btn-primary" id="sbcMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        $.validator.addMethod('sbcNodeIpCheck', function (value, element) {
            return ($("#mod_checkSbcNodeIp").val() == "Y") ? true : false;
        }, '<spring:message code='TEXT.SETTING.SBC.DETAIL.SBC_NODE_IP'/>' + '<spring:message code='MSG.ALERT.ALERT.CHECK.DUP'/>');

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#sbcModForm").validate({
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
                mod_sbc_group_id: {
                    required: true
                },
                mod_sbc_node_name: {
                    required: true
                },
                mod_sbc_node_ip: {
                    required: true,
                    sbcNodeIpCheck: true
                }
            },
            messages: {
                mod_sbc_group_id: {
                    required: '<spring:message code='TEXT.SETTING.SBC.DETAIL.SBC_GROUP_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_sbc_node_name: {
                    required: '<spring:message code='TEXT.SETTING.SBC.DETAIL.SBC_NODE_NAME'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                },
                mod_sbc_node_ip: {
                    required: '<spring:message code='TEXT.SETTING.SBC.DETAIL.SBC_NODE_IP'/>' + '<spring:message code='MSG.ALERT.REQUIRED'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/setting/sbc/sbcModPopup.do", {
                    actionID: "ACTION_SBC_UPDATE_OK",
                    sbc_group_id: $("#mod_sbc_group_id").val(),
                    sbc_node_name: $("#mod_sbc_node_name").val(),
                    sbc_node_ip: $("#mod_sbc_node_ip").val(),
                    remark1: $("#mod_remark1").val(),
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#sbcModModal').modal('hide');
                        goSbcList();
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
        //SBC Node IP 중복 체크
        $("#sbcNodeIpDupChk").on('click', function () {
            checkSbcNodeIpDup();
        });
        //저장
        $("#sbcMod").on('click', function () {
            $("#sbcModForm").submit();
        });
    }

    //IP 변경
    function onChangeIp(obj) {
        $("#mod_checkSbcNodeIp").val("N");
    }

    //SBC Node IP 중복 확인
    function checkSbcNodeIpDup() {
        // 		AotSmartAdmin.smallBoxInfo($("#mod_sbc_node_ip").val());
        if ($("#mod_sbc_node_ip").val()) {
            AotAjax.excute("${CONTEXT_PATH}/setting/sbc/sbcPopup.do", {
                actionID: "ACTION_SBC_IP_CHECK",
                sbc_node_ip: $("#mod_sbc_node_ip").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.checkRet;
                if (str === "NG") {
                    AotSmartAdmin.smallBoxWarning($("#mod_sbc_node_ip").val() + " <spring:message code='MSG.ALERT.ALERT.ALREADY.USED'/>");
                    $("#mod_checkSbcNodeIp").val("N");
                    $("#mod_sbc_node_ip").val("");
                    $("#mod_sbc_node_ip").focus();
                } else {
                    AotSmartAdmin.smallBoxsuccess($("#mod_sbc_node_ip").val() + " <spring:message code='MSG.SETTING.SBC.ALERT.POSSIBLE_SBC_NODE_IP'/>");
                    $("#mod_checkSbcNodeIp").val("Y");
                }
            });
        }
    }
</script>
