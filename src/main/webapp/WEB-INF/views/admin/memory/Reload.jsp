<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/memory/Reload.jsp
 - 설    명	:	메모리로드 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2009.03.16    1.0       JSM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="post" action="${CONTEXT_PATH}/admin/memory/load.do">
        <input type="hidden" name="actionID" value="">
    </form>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <hbis:navi menuUrl="${MENU_URL}"></hbis:navi>
            </ol>
            <!-- end breadcrumb -->
        </div>

        <div id="content">

            <div class="row">
                <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-pencil-square-o"></i>
                            ${curr_menu_name }
                    </h1>
                </div>
            </div>

            <div class="row">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <!-- 번호 -->
                            <th width="80">
                                    <spring:message code="TEXT.MEMORY.MGMT.LIST.NO"/>
                            </td>
                            <!-- 설명 -->
                            <th>
                                    <spring:message code="TEXT.MEMORY.MGMT.LIST.EXPLAIN"/>
                            </td>
                            <th width="250">
                                    <spring:message code="TEXT.MEMORY.MGMT.LIST.RELOAD"/>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>
                                <spring:message code="TEXT.MEMORY.MGMT.LIST.GROUP_CODE_RELOAD"/>
                            </td>
                            <td>
                                <button type="button" class="btn btn-warning btn-default" id="grcode_reload" onClick="doReload('ACTION_RELOAD_CODETABLE');return false;">
                                    <spring:message code="TEXT.MEMORY.MGMT.BUTTON.GROUP_CODE_RELOAD"/>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>
                                <spring:message code="TEXT.MEMORY.MGMT.LIST.GROUP_MENU_RELOAD"/>
                            </td>
                            <td>
                                <button type="button" class="btn btn-warning btn-default" id="menu_reload" onClick="doReload('ACTION_RELOAD_MENU');return false;">
                                    <spring:message code="TEXT.MEMORY.MGMT.BUTTON.GROUP_MENU_RELOAD"/>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>


                <%-- <input type="text" name="finishdate" id="finishdate"> --%>
        </div>

    </div>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //달력 세팅
            //datePickerInit();
        });

        function datePickerInit() {
            $('#startdate').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment()
            });

            $('#finishdate').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment(),
                useCurrent: false
            });
            AotDatetimePicker.setFromTo($('#startdate'), $('#finishdate'));
        }

        function doReload(actionID) {
            AotAjax.excute("${CONTEXT_PATH}/admin/memory/load.do", {
                actionID: actionID
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }
    </script>
    </body>
</aot:html>