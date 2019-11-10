<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/M3ua/Excel.jsp
 - 설      명	: M2PA/M3UA 현황 엑셀 업로드  page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.05.03    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.OBSTACLE.M3UA.SUBTITLE_EXCEL.M3UA_DETAIL"/>
            </h4>
        </div>

        <div class="modal-body">

            <form method="POST" enctype="multipart/form-data" id="fileUploadForm">

                <fieldset>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2 control-label">
                                <spring:message code="TEXT.OBSTACLE.M3UA.DETAIL.EXCEL.FILE_SEL"/>
                            </label>
                            <div class="col-md-10">
                                <input type="file" class="btn btn-default" id="files" name="files">
                            </div>
                        </div>
                    </div>
                </fieldset>

                <fieldset style="margin: 10px 0 0 0;">
                    <div class="row">
                        <div id="info-block" class="alert alert-info fade in" style="display: none;">
                            <i class="fa-fw fa fa-info"></i>
                            <span class="help-block-m">
								<spring:message code="MSG.ALERT.EXCEL_FILE"/>
							</span>
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="btnSubmit">
                <spring:message code="TEXT.COMM.BTN.UPLOAD"/>
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <spring:message code="TEXT.CANCEL"/>
            </button>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        $("#files").on('change', function () {
            var fileExtension = ['xlsx', 'xls'];
            if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
                $("#files").val("");
                AotSmartAdmin.smallBoxWarning("<spring:message code="MSG.ALERT.FILE_EXTENSION.FIRST" />" + fileExtension.join(', ') + "<spring:message code="MSG.ALERT.FILE_EXTENSION.END" />");
            }
        });

        $("#btnSubmit").on('click', function (event) {
            if ($("#files").val() === "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.FILE" />" + " " + "<spring:message code="MSG.ALERT.REQUIRED.SELECT" />");
                return;
            }
            //stop submit the form, we will post it manually.
            event.preventDefault();

            // Get form
            var form = $('#fileUploadForm')[0];

            // Create an FormData object
            var data = new FormData(form);

            // If you want to add an extra field for the FormData
            //data.append("CustomField", "This is some extra data, testing");

            $("#btnSubmit").prop("disabled", true);
            Pace.track(function () {
                Pace.restart();
                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: '${CONTEXT_PATH}/obstacle/m3ua/m3uaexcelpop.pop?actionID=ACTION_M3UA_EXCEL_UPLOAD_OK',
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        var jData = $.parseJSON(data);

                        $("#info-block").show();
                        $(".help-block-m").html(jData.returnMsg);
                        $("#files").val("");
                        goM3uaList();
                        $("#btnSubmit").prop("disabled", false);
                    },
                    error: function (e) {
                        $(".help-block-m").html(e.responseText);
                        console.log("ERROR : ", e);
                        $("#btnSubmit").prop("disabled", false);
                    }
                });
            });
        });
    })
</script>
