<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/empgroup/Edit.jsp
 - 설      명	: 사용자그룹관리 수정 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2011.03.14   3.2       Once      웹표준 적용
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/jsp/common/error/systemException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_MGMT"/>
            </h4>
        </div>

        <div class="modal-body">
            <form id="empGroupModForm" id="empGroupModForm" class="smart-form" novalidate="novalidate">
                <input type="hidden" name="mod_code" id="mod_code" value="${EMPGROUP_INFO_DATA.code}">
                <fieldset>
                    <div class="row">
                        <!-- 사용자 권한 레벨 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_LEVEL"/>
                            </label>
                            <label class="select">
                                <aot:select name="mod_groupLevel" id="mod_groupLevel" list="${AUTHLEVEL_LIST }" selected="${EMPGROUP_INFO_DATA.groupLevel}"/>
                                <i></i>
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 레벨명 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_name" id="mod_name" value="${EMPGROUP_INFO_DATA.name}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 설명 -->
                        <section class="col col-10">
                            <label class="label">
                                <spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_EXPLAIN"/>
                            </label>
                            <label class="input">
                                <input type="text" name="mod_remark" id="mod_remark" value="${EMPGROUP_INFO_DATA.remark}">
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
            <button type="button" class="btn btn-primary" id="empGroupMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#empGroupModForm").validate({
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
                mod_name: {
                    required: true,
                    maxlength: 50
                },
                mod_remark: {
                    maxlength: 100
                }
            },
            messages: {
                mod_name: {
                    required: '<spring:message code='MSG.EMP.GROUP.ALERT.REQUIRE_NAME'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/empgroup/empgroupModPopup.do", {
                    actionID: "ACTION_UPDATE_OK",
                    name: $("#mod_name").val(),
                    remark: $("#mod_remark").val(),
                    groupLevel: $("#mod_groupLevel").val(),
                    code: $("#mod_code").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#empGroupModModal').modal('hide');
                        goEmpGroupList();
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
        $("#empGroupMod").on('click', function () {
            $("#empGroupModForm").submit();
        });
    }
</script>
