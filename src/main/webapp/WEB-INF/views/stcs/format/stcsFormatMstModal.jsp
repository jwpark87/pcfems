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
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.STCS.FORMAT.TITLE"/>
            </h4>
        </div>

        <div class="modal-body">

            <form id="form1" id="form1" class="smart-form" novalidate="novalidate">
                <fieldset>
                    <div class="row">
                        <!-- 인덱스 타입 -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.STCS.FORMAT.GRID.INDEX_TYPE"/>
                            </label>
                            <label class="input">
                                <input type="text" name="index_type" id="index_type" data-title="<spring:message code="TEXT.STCS.FORMAT.GRID.INDEX_TYPE" />" maxlength="50" required>
                            </label>
                        </section>
                        <!-- 포맷명 -->
                        <section class="col col-6">
                            <label class="label">
                                <spring:message code="TEXT.STCS.FORMAT.GRID.FORMAT_NAME"/>
                            </label>
                            <label class="input">
                                <input type="text" name="format_name" id="format_name" data-title="<spring:message code="TEXT.STCS.FORMAT.GRID.FORMAT_NAME" />" maxlength="100" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <!-- 검색 base url -->
                        <section class="col col-6">
                            <label class="label"> 검색 base url </label>
                            <label class="input">
                                <input type="text" name="es_base_url" id="es_base_url" data-title="검색 base url" maxlength="250" required>
                            </label>
                        </section>
                        <!-- 코드명 일어 -->
                        <section class="col col-6">
                            <label class="label"> 검색기준일자 컬럼 ID </label>
                            <label class="input">
                                <input type="text" name="def_col_id" id="def_col_id" data-title="검색기준일자 컬럼 ID" maxlength="50" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <!-- 검색 base url -->
                        <section class="col col-6">
                            <label class="label"> 검색일자 기본값(FROM) </label>
                            <label class="input">
                                <input type="text" name="def_fromdt" id="def_fromdt" data-title="검색일자 기본값(FROM)" maxlength="20" required>
                            </label>
                        </section>
                        <!-- 코드명 일어 -->
                        <section class="col col-6">
                            <label class="label"> 검색일자 기본값(TO) </label>
                            <label class="input">
                                <input type="text" name="def_todt" id="def_todt" data-title="검색일자 기본값(TO)" maxlength="20" required>
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
                index_type: {
                    required: true,
                    maxlength: 50
                },
                format_name: {
                    required: true,
                    maxlength: 100
                },
                es_base_url: {
                    required: true,
                    maxlength: 250
                },
                def_col_id: {
                    required: true,
                    maxlength: 50
                },
                def_fromdt: {
                    required: true,
                    maxlength: 20
                },
                def_todt: {
                    required: true,
                    maxlength: 20
                }
            },
            messages: {
                index_type: {
                    required: $targetModal.find('#index_type').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                format_name: {
                    required: $targetModal.find('#format_name').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                es_base_url: {
                    required: $targetModal.find('#es_base_url').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                def_col_id: {
                    required: $targetModal.find('#def_col_id').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                def_fromdt: {
                    required: $targetModal.find('#def_fromdt').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                def_todt: {
                    required: $targetModal.find('#def_todt').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                var params = {
                    index_type: $targetModal.find("#index_type").val(),
                    format_name: $targetModal.find("#format_name").val(),
                    es_base_url: $targetModal.find("#es_base_url").val(),
                    def_col_id: $targetModal.find("#def_col_id").val(),
                    def_fromdt: $targetModal.find("#def_fromdt").val(),
                    def_todt: $targetModal.find("#def_todt").val()
                };
                if (_.startsWith($targetModal.attr('id'), "add")) {
                    params['actionID'] = "insertSrchFormatMst";
                } else {
                    params['actionID'] = "updateSrchFormatMst";
                }
                AotAjax.excute("${CONTEXT_PATH}/stcs/format/stcsFormatList.do?v=" + moment().valueOf(), params, {
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
