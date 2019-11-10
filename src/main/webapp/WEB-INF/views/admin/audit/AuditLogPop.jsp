<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/audit/LogPop.jsp
 - 설    명	:	Audit Log Pop 화면
 - 추가내용   :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.05.31    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>
        #logtable.table {
            border: #cacaca 1px solid;
            border-top: #9e9e9e 1px solid;
            width: 98%;
        }

        #logtable.table thead th, #logtable.table tbody th {
            padding: 5px 15px;
            height: 33px;
            border-right: #e4e4e4 1px solid;
            border-bottom: #e4e4e4 1px solid;
            font-size: 13px;
            color: #000;
            vertical-align: middle;
        }

        #logtable.table thead th:last-child {
            border-right: #cacaca 1px solid;
        }

        #logtable.table tbody th:last-child {
            border-right: 0;
        }

        #logtable.table tbody td {
            padding: 5px 15px;
            height: 33px;
            border-right: #e4e4e4 1px solid;
            border-bottom: #e4e4e4 1px solid;
            font-size: 13px;
            vertical-align: middle;
            background-color: #fafafa;
        }

        #logtable.table tbody td:last-child {
            border-right: 0;
        }

        #logtable.table tbody tr:last-child th, table tbody tr:last-child td {
            border-bottom: 0;
        }

        #logtable.table_default thead th {
            height: 50px;
            font-size: 13px;
        }

        #logtable.table_default tbody th, #logtable.table_default tbody td {
            height: 45px;
        }

        #logtable.table-form {
            border-left: #e4e4e4 1px solid;
            border-right: #e4e4e4 1px soild
        }

        #logtable.table-form tbody th, table.table-form tbody td {
            height: 47px;
            text-align: left;
        }

        #logtable.table.fontsize13 td {
            font-size: 13px
        }

        #logtable.table.tac td {
            text-align: center;
        }

        /* 마우스 오버시 글자 검정색으로 */
        /* .ui-jqgrid .ui-state-hover td {
            background: #ecf3f8!important;
            color:#000000!important;
        } */
    </style>

    <form name="myForm" method="POST">
        <input type="hidden" name="actionID" value="">
    </form>

    <div id="panel" role="main">

        <div id="content">

            <div class="row">
                <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-exchange"></i>
                        <spring:message code="TEXT.ADMIN.AUDIT_LOG.POP.TITLE"/>
                    </h1>
                </div>
            </div>

            <div class="row">
                <div class="table-responsive">
                    <table id="logtable" class="table table-bordered">
                        <tbody>
                        <tr>
                            <!-- 발생일시 -->
                            <th>
                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.POP.CRT_DT"/>
                            </th>
                            <td>${SEARCH_DATA.crt_dt}</td>

                            <!-- Audit_subject -->
                            <th>
                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.POP.AUDIT_SUBJECT"/>
                            </th>
                            <td>${SEARCH_DATA.audit_subject}</td>
                        </tr>
                        <tr>
                            <!-- Audit_desc -->
                            <th>
                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.POP.AUDIT_DESC"/>
                            </th>
                            <td>${SEARCH_DATA.audit_desc}</td>

                            <!-- Crt_nm -->
                            <th>
                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.POP.CRT_NM"/>
                            </th>
                            <td>${SEARCH_DATA.crt_nm}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12" id="gridlist-width">
                    <table id="gridList"></table>
                </div>
            </div>
            <!-- 버튼영역 -->
            <div class="row" style="text-align: right; margin-top: 13px;">
                <section class="col-sm-12">
                    <a href="#" class="btn btn-lg btn-primary" onclick="doClose(); return false;">
                        <spring:message code="TEXT.COMM.BTN.CANCEL"/>
                    </a>
                </section>
            </div>
        </div>
    </div>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        jQuery(document).ready(
            function () {

                jQuery("#gridList").jqGrid(
                    {
                        url: "${CONTEXT_PATH}/admin/audit/log.pop",
                        postData: {
                            actionID: "ACTION_AUDIT_LOG_DET_POP_LIST",
                            audit_before_data: "${SEARCH_DATA.audit_before_data}",
                            audit_after_data: "${SEARCH_DATA.audit_after_data}"
                        },
                        datatype: "json",
                        jsonReader: {
                            root: "root",
                            page: "page",
                            records: "records",
                            repeatitems: false
                        },
                        mtype: 'POST',
                        autoencode: true,
                        height: 235,
                        colNames: ["<spring:message code='TEXT.ADMIN.AUDIT_LOG.POP.GRID.COLUMN'/>", "<spring:message code='TEXT.ADMIN.AUDIT_LOG.POP.GRID.AUDIT_BEFORE_DATA'/>",
                            "<spring:message code='TEXT.ADMIN.AUDIT_LOG.POP.GRID.AUDIT_AFTER_DATA'/>"],
                        colModel: [{
                            name: "column",
                            index: "column",
                            width: 130
                        }, {
                            name: "oldvalue",
                            index: "oldvalue",
                            width: 130
                        }, {
                            name: "newvalue",
                            index: "newvalue",
                            width: 130
                        },],
                        rowNum: 100000,
                        toolbarfilter: true,
                        shrinkToFit: true,
                        viewrecords: true,
                        autowidth: true,
                        hoverrows: false,
                        loadComplete: function () {
                            var rowIds = $("#gridList").getDataIDs();
                            $.each(rowIds, function (idx, rowId) {
                                var rowData = $("#gridList").getRowData(rowId);
                                if (rowData.oldvalue != rowData.newvalue) {
                                    $("#gridList").setRowData(rowId, false, {
                                        background: "#FFE400"
                                    });
                                }
                            });
                        }
                    });
                AotJqGrid.setGridStyle();
            });

        //창 닫을때 타이머 다시 시작
        function doClose() {
            window.close();
        }
    </script>

    </body>
</aot:html>