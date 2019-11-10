<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameter/Excel.jsp
 - 설      명	: Diameter Host 관리 엑셀 업로드  page 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.07    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <div id="panel" role="main">

        <div id="content">

            <div class="row">
                <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <spring:message code="TEXT.SETTING.DIAMETER.SUBTITLE_EXCEL.DIAMETER_DETAIL"/>
                    </h1>
                </div>
            </div>

            <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
                <div class="row">

                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-2 control-label">
                                <spring:message code="TEXT.SETTING.DIAMETER.DETAIL.EXCEL.FILE_SEL"/>
                            </label>
                            <div class="col-md-10">
                                <input type="file" class="btn btn-default" id="files" name="files">

                                <p class="help-block">
                                    <spring:message code="MSG.SETTING.DIAMETER.ALERT.EXCEL_FILE"/>
                                </p>
                            </div>
                        </div>
                    </fieldset>

                </div>

                <div class="row" style="text-align: right; margin-top: 13px;">
                    <section class="col-sm-12">
                        <!-- <input type="submit" value="Submit" id="btnSubmit"/> -->

                        <button type="button" class="btn btn-primary" id="btnSubmit">
                            <spring:message code="TEXT.COMM.BTN.UPLOAD"/>
                        </button>
                        <a href="#" class="btn btn-primary" onclick="doClose(); return false;">
                            <spring:message code="TEXT.COMM.BTN.CANCEL"/>
                        </a>
                    </section>
                </div>


            </form>


        </div>

    </div>
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <!-- <script src="${CONTEXT_PATH}/resources//code.jquery.com/jquery-1.11.0.min.js"></script> -->
        <%-- <script src="${PATH_PUBLISH}/js/ajaxfileupload.js"></script> --%>
    <script type="text/javascript">
        $(document).ready(function () {
            //bindEvent();

            $("#btnSubmit").on('click', function (event) {

                //stop submit the form, we will post it manually.
                event.preventDefault();

                // Get form
                var form = $('#fileUploadForm')[0];

                // Create an FormData object
                var data = new FormData(form);

                // If you want to add an extra field for the FormData
                data.append("CustomField", "This is some extra data, testing");

                // disabled the submit button
                $("#btnSubmit").prop("disabled", true);
                Pace.track(function () {
                    Pace.restart();
                    $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: '${CONTEXT_PATH}/setting/diameter/diameterexcelpop.pop?actionID=ACTION_DIAMETER_EXCEL_UPLOAD_OK',
                        data: data,
                        processData: false,
                        contentType: false,
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            var jData = $.parseJSON(data);

                            $(".help-block").text(jData.returnMsg);

                            //console.log("SUCCESS : ", str[1]);
                            $("#btnSubmit").prop("disabled", false);
                        },
                        error: function (e) {
                            $(".help-block").text(e.responseText);
                            console.log("ERROR : ", e);
                            $("#btnSubmit").prop("disabled", false);

                        }
                    });
                });
            });
        });

        //각 클릭 이벤트
        function bindEvent() {

        }
    </script>
    </body>
</aot:html>
