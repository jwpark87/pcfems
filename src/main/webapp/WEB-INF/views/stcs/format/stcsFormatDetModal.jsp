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
            <h4 class="modal-title" id="myModalLabel">통계기능관리 상세</h4>
        </div>

        <div class="modal-body">

            <form id="form1" id="form1" class="smart-form" novalidate="novalidate">
                <fieldset>
                    <div class="row">
                        <!-- 인덱스 타입 -->
                        <section class="col col-xs-6">
                            <label class="label">
                                <spring:message code="TEXT.STCS.FORMAT.GRID.COL_CD"/>
                            </label>
                            <label class="input">
                                <input type="text" name="col_cd" id=col_cd data-title="<spring:message code="TEXT.STCS.FORMAT.GRID.COL_CD" />" maxlength="50" required>
                            </label>
                        </section>
                        <!-- 포맷명 -->
                        <section class="col col-xs-6">
                            <label class="label">
                                <spring:message code="TEXT.STCS.FORMAT.GRID.COL_NM"/>
                            </label>
                            <label class="input">
                                <input type="text" name="col_nm" id="col_nm" data-title="<spring:message code="TEXT.STCS.FORMAT.GRID.COL_NM" />" maxlength="100" required>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-6">
                            <label class="label"> 텍스트 정렬 </label>
                            <label class="select">
                                <select name="text_align" id="text_align" data-title="텍스트 정렬" required></select>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-xs-6">
                            <label class="label"> 그룹코드 </label>
                            <label class="select">
                                <select name="grcode" id="grcode" data-title="그룹코드"></select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-6">
                            <label class="label"> 출력 넓이 </label>
                            <label class="input">
                                <input type="text" name="dsp_width" id="dsp_width" data-title="출력 넓이" maxlength="6" required>
                            </label>
                        </section>
                        <section class="col col-xs-6">
                            <label class="label"> 검색조건 여부 </label>
                            <label class="select">
                                <select name="search_yn" id="search_yn" data-title="검색조건 여부" required></select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-6">
                            <label class="label"> 정렬순번 </label>
                            <label class="input">
                                <input type="text" name="sortseq" id="sortseq" data-title="정렬순번" maxlength="5" required>
                            </label>
                        </section>
                        <section class="col col-xs-6">
                            <label class="label"> 사용여부 </label>
                            <label class="select">
                                <select name="use_yn" id="use_yn" data-title="사용여부" required></select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-6">
                            <label class="label"> Threshold 기능 사용 여부 </label>
                            <label class="select">
                                <select name="threshold_yn" id="threshold_yn" data-title="Threshold 기능 사용 여부" required></select>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-xs-6">
                            <label class="label"> threshold check operation code </label>
                            <label class="select">
                                <select name="op_code" id="op_code" data-title="threshold check operation code"></select>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-6">
                            <label class="label"> op check value </label>
                            <label class="input">
                                <input type="text" name="op_value" id="op_value" data-title="op check value"/>
                            </label>
                        </section>
                        <section class="col col-xs-6">
                            <label class="label"> 알람메시지 </label>
                            <label class="input">
                                <input type="text" name="alm_msg" id="alm_msg" data-title="알람메시지"/>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-xs-12">
                            <label class="label"> 비고 </label>
                            <label class="input">
                                <input type="text" name="remark1" id="remark1" data-title="비고"/>
                            </label>
                        </section>
                    </div>
                    <section class="col col-xs-6 hide">
                        <label class="label"> 발생알람코드 </label>
                        <label class="input">
                            <input type="text" name="alm_code" id="alm_code" data-title="발생알람코드"/>
                        </label>
                    </section>
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
                col_cd: {
                    required: true
                },
                col_nm: {
                    required: true
                },
                text_align: {
                    required: true
                },
                dsp_width: {
                    required: true
                },
                search_yn: {
                    required: true
                },
                sortseq: {
                    required: true
                },
                use_yn: {
                    required: true
                }
            },
            messages: {
                col_cd: {
                    required: $targetModal.find('#col_cd').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                col_nm: {
                    required: $targetModal.find('#col_nm').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                text_align: {
                    required: $targetModal.find('#text_align').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                dsp_width: {
                    required: $targetModal.find('#dsp_width').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                search_yn: {
                    required: $targetModal.find('#search_yn').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                sortseq: {
                    required: $targetModal.find('#sortseq').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                },
                use_yn: {
                    required: $targetModal.find('#use_yn').data('title') + '<spring:message code="MSG.ALERT.REQUIRED"/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                var params = {
                    index_type: AotJqGrid.getSelectedRowData("#gridList").index_type,
                    col_cd: $targetModal.find("#col_cd").val(),
                    col_nm: $targetModal.find("#col_nm").val(),
                    text_align: $targetModal.find("#text_align>option:selected").val(),
                    grcode: $targetModal.find("#grcode>option:selected").val(),
                    dsp_width: $targetModal.find("#dsp_width").val(),
                    search_yn: $targetModal.find("#search_yn>option:selected").val(),
                    sortseq: $targetModal.find("#sortseq").val(),
                    use_yn: $targetModal.find("#use_yn>option:selected").val(),
                    threshold_yn: $targetModal.find("#threshold_yn>option:selected").val(),
                    op_code: $targetModal.find("#op_code>option:selected").val(),
                    op_value: $targetModal.find("#op_value").val(),
                    alm_code: $targetModal.find("#alm_code").val(),
                    alm_msg: $targetModal.find("#alm_msg").val(),
                    remark1: $targetModal.find("#remark1").val()
                };
                if (_.startsWith($targetModal.attr('id'), "add")) {
                    params['actionID'] = "insertSrchFormatDet";
                } else {
                    params['actionID'] = "updateSrchFormatDet";
                }
                AotAjax.excute("${CONTEXT_PATH}/stcs/format/stcsFormatList.do?v=" + moment().valueOf(), params, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    if (_.startsWith(response.responseCode, "S")) {
                        $targetModal.html('').modal('hide');
                        GridReload2(AotJqGrid.getSelectedRowData("#gridList").index_type);
                    }
                });
            }
        });
    });
</script>
