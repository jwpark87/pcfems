<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/syscod/GrCodeAdd.jsp
 - 설      명	: 시스템그룹코드관리 수정 / 추가 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2017.12.21    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">VM노드관리</h4>
        </div>

        <div class="modal-body">

            <form id="form1" id="form1" class="smart-form" novalidate="novalidate">
                <fieldset>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">서버ID</label>
                            <label class="input">
                                <input type="text" name="svr_id" id="svr_id" data-title="서버ID" maxlength="50" required>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">노드이름</label>
                            <label class="input">
                                <input type="text" name="node_name" id="node_name" data-title="노드이름" maxlength="100" required>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="label">노드그룹</label>
                            <label class="input">
                                <input type="text" name="node_group" id="node_group" data-title="노드그룹" maxlength="50" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">상태</label>
                            <label class="select">
                                <select name="ha_status" id="ha_status" data-title="상태" required>
                                    <option value="A">Active</option>
                                    <option value="S">Standby</option>
                                </select>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="label">알람상태</label>
                            <label class="select">
                                <select name="alm_status" id="alm_status" data-title="알람상태" required></select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">호스트명</label>
                            <label class="input">
                                <input type="text" name="host_name" id="host_name" data-title="호스트명" maxlength="20" required>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="label">정렬순서</label>
                            <label class="input">
                                <input type="text" name="sortseq" id="sortseq" data-title="정렬순서" maxlength="20" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-6">
                            <label class="label">사용여부</label>
                            <label class="select">
                                <select name="use_yn" id="use_yn" data-title="사용여부" required></select>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="label">h/w 이름</label>
                            <label class="input">
                                <input type="text" name="hw_name" id="hw_name" data-title="h/w 이름" maxlength="20" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <!-- 검색 base url -->
                        <section class="col col-6">
                            <label class="label">vnfc_instance_id</label>
                            <label class="input">
                                <input type="text" name="vnfc_instance_id" id="vnfc_instance_id" data-title="vnfc_instance_id" maxlength="50">
                            </label>
                        </section>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <i class="fa fa-window-close-o" aria-hidden="true"></i>
                <spring:message code="TEXT.CANCEL"/>
            </button>
            <button type="button" class="btn btn-primary" onclick="$targetModal.find('#form1').submit();">
                <i class="fa fa-floppy-o" aria-hidden="true"></i>
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $targetModal.find("#form1").validate({
            errorClass: 'invalid',
            errorElement: 'em',
            highlight: function (element) {
                $(element).parent().removeClass('state-success').addClass("state-error");
                $(element).removeClass('valid');
            },
            unhighlight: function (element) {
                $(element).parent().removeClass("state-error").addClass('state-success');
                $(element).addClass('valid');
            },
            rules: {
                svr_id: {
                    required: true
                },
                node_name: {
                    required: true
                },
                node_group: {
                    required: true
                },
                ha_status: {
                    required: true
                },
                alm_status: {
                    required: true
                },
                host_name: {
                    required: true
                },
                sortseq: {
                    required: true
                },
                use_yn: {
                    required: true
                },
                hw_name: {
                    required: true
                }
            },
            messages: {
                svr_id: {
                    required: $targetModal.find('#svr_id').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                node_name: {
                    required: $targetModal.find('#node_name').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                node_group: {
                    required: $targetModal.find('#node_group').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                ha_status: {
                    required: $targetModal.find('#ha_status').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                alm_status: {
                    required: $targetModal.find('#alm_status').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                host_name: {
                    required: $targetModal.find('#host_name').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                sortseq: {
                    required: $targetModal.find('#sortseq').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                use_yn: {
                    required: $targetModal.find('#use_yn').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                hw_name: {
                    required: $targetModal.find('#hw_name').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                var params = {
                    svr_id: $targetModal.find("#svr_id").val(),
                    node_name: $targetModal.find("#node_name").val(),
                    node_group: $targetModal.find("#node_group").val(),
                    ha_status: $targetModal.find("#ha_status>option:selected").val(),
                    alm_status: $targetModal.find("#alm_status>option:selected").val(),
                    host_name: $targetModal.find("#host_name").val(),
                    sortseq: $targetModal.find("#sortseq").val(),
                    use_yn: $targetModal.find("#use_yn>option:selected").val(),
                    hw_name: $targetModal.find("#hw_name").val(),
                    vnfc_instance_id: $targetModal.find("#vnfc_instance_id").val()
                };
                if (_.startsWith($targetModal.attr('id'), "add")) {
                    params['actionID'] = "insertStcsThreshold";
                } else {
                    params['actionID'] = "updateStcsThreshold";
                }
                AotAjax.excute("${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do?v=" + moment().valueOf(), params, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    if (_.startsWith(response.responseCode, "S")) {
                        $targetModal.html('').modal('hide');
                        GridReload('upd_dt', 'desc');
                    }
                });
            }

        });
    });
</script>
