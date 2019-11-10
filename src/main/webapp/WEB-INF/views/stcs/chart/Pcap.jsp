<%----------------------------------------------------------------------------------------
 - 파일이름	: stcs/chart/Pcap.jsp
 - 설      명	: Pcap 파일 다운로드 화면 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.06    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/stcs/pcapfile.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="stcs_excel_data" id="stcs_excel_data"/>

        <input type="hidden" name="srch_pcap_file_parent" id="srch_pcap_file_parent"/>
        <input type="hidden" name="srch_pcap_file" id="srch_pcap_file"/>
        <div id="main" role="main">
            <!-- RIBBON -->
            <div id="ribbon">
                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <aot:navi menuUrl="${MENU_URL}"></aot:navi>
                </ol>
                <!-- end breadcrumb -->
            </div>
            <div id="content">
                <!-- 상단화면명 START -->
                <div class="row">

                    <!-- 상단화면명 -->
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark" style="margin: 12px 0 20px;">
                            <!-- PAGE HEADER -->
                            <i class="fa-fw fa fa-pencil-square-o"></i>
                                ${curr_menu_name }
                        </h1>
                    </div>

                    <!-- 상단 버튼들 -->
                    <div class="col-lg-4" style="float: right; height: 58px; padding-top: 10px; text-align: right;">
                        <button type="button" class="btn btn-primary btn-sm" id="srch">
                            <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                        </button>
                            <%-- <button type="button" class="btn btn-primary btn-sm" id="excel">
                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                        </button> --%>
                    </div>

                </div>
                <!-- 상단화면명 END -->

                <section id="widget-grid" class="">
                    <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                    <div class="row">
                        <article class="col-sm-12">
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                 data-widget-deletebutton="false"
                                 data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">
                                <header></header>
                                <div>
                                    <div class="widget-body no-padding">
                                        <table id="gridChartList"></table>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </div>
                </section>

            </div>
            <!-- content -->

        </div>
        <!-- main -->
    </form>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(
            function () {

                bindClickEvent();

                $("#gridChartList").jqGrid('clearGridData');

                jQuery("#gridChartList").jqGrid(
                    {
                        url: "${CONTEXT_PATH}/stcs/pcapfile.do",
                        postData: {
                            actionID: "ACTION_PCAP_LIST",
                            srch_pcap_file_folder: "",
                            srch_pcap_file_parent: "",
                        },
                        page: "${SEARCH_DATA.jqPage}",
                        datatype: "json",
                        jsonReader: {
                            root: "root",
                            page: "page",
                            records: "records",
                            repeatitems: false
                        },
                        mtype: 'POST',
                        autoencode: true,
                        height: 360,
                        colNames: ["", "", "", "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_NAME"/>", "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_TYPE"/>",
                            "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_SIZE"/>"],
                        colModel: [{
                            name: "pcap_file_root",
                            index: "pcap_file_root",
                            align: "center",
                            width: 150,
                            hidden: true
                        }, {
                            name: "pcap_file_parent",
                            index: "pcap_file_parent",
                            align: "center",
                            width: 150,
                            hidden: true
                        }, {
                            name: "pcap_file_name",
                            index: "pcap_file_name",
                            align: "left",
                            width: 350,
                            hidden: true
                        }, {
                            name: "pcap_file_view",
                            index: "pcap_file_view",
                            align: "left",
                            width: 350,
                            formatter: setViewText
                        }, {
                            name: "pcap_file_type",
                            index: "pcap_file_type",
                            align: "center",
                            width: 350,
                            formatter: setNameText
                        }, {
                            name: "pcap_file_size",
                            index: "pcap_file_size",
                            align: "right",
                            formatter: AotJqGrid.formatterCurrencyDefaultValue,
                            unformat: AotJqGrid.unformatterCurrencyDefaultValue,
                            width: 350
                        }],
                        rowNum: 1000000,
                        rowList: [100, 300, 500, 1000],
                        shrinkToFit: true, // 가로 스크롤
                        toolbarfilter: true,
                        viewrecords: true,
                        autowidth: true,
                        onSelectRow: function (id) {
                            var id = jQuery("#gridChartList").getGridParam("selrow");
                            if (id) {
                                //폴더를 선택하면 이동, 파일을 선택하면 다운로드
                                var ret = jQuery("#gridChartList").getRowData(id);
                                if (ret.pcap_file_type === "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_TYPE.TEXT.FILE"/>") {
                                    doFileDown(ret.pcap_file_parent, ret.pcap_file_name);
                                } else {
                                    goChartList(ret.pcap_file_parent, ret.pcap_file_name);
                                }
                            }
                        }
                    });

                AotJqGrid.setGridStyle();
            });

        //아이콘 삽입
        function setViewText(cellValue, options, rowObject) {
            var result_txt = "";
            if (rowObject.pcap_file_type === "D") {
                result_txt = "<i class='fa-fw fa fa-folder-open'></i>";
            } else if (rowObject.pcap_file_type === "F") {
                result_txt = "<i class='fa-fw fa  fa-save'></i>";
                //이전으로 이동
            } else if (rowObject.pcap_file_type === "P") {
                result_txt = "<i class='fa-fw fa   fa-mail-reply'></i>";
            }
            return result_txt + "  " + cellValue;
        }

        //파일유형 텍스트
        function setNameText(cellValue, options, rowObject) {
            var result_txt = "";
            if (cellValue === "D") {
                result_txt = "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_TYPE.TEXT.DIRECTORY"/>";
            } else if (cellValue === "F") {
                result_txt = "<spring:message code="TEXT.CHART.GRID.PCAP.FILE_TYPE.TEXT.FILE"/>";
            } else {
                result_txt = "";
            }
            return result_txt;
        }

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goChartList("", "");
            });
        }

        //조회
        function goChartList(pcap_file_parent, pcap_file_name) {
            $("#gridChartList").jqGrid('clearGridData');
            jQuery("#gridChartList").setGridParam({
                url: "${CONTEXT_PATH}/stcs/pcapfile.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_PCAP_LIST",
                    srch_pcap_file_folder: pcap_file_name,
                    srch_pcap_file_parent: pcap_file_parent
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridChartList").getGridParam("reccount") == 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            var rowDatas = jQuery("#gridChartList").getRowData();
            //전체 갯수
            var curr_tot_count = $("#gridChartList").getGridParam("records");
            //한 페이지 갯수
            var curr_page_num = $("#gridChartList").getGridParam('rowNum');
            //마지막 페이지 번호
            var last_page = $("#gridChartList").getGridParam('lastpage');

            //마지막 페이지가 1이면 더이상 조회 할 것이 없으므로 엑셀 출력 시 다시 조회 하지 않고 바로 다운
            if (parseInt(last_page) == 1) {
                $("#stcs_excel_data").val(JSON.stringify(rowDatas));
            } else {
                $("#stcs_excel_data").val("");
            }
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/stcs/pcapfile.do', param);
        }

        function doFileDown(pcap_file_parent, pcap_file_name) {
            $("#srch_pcap_file_parent").val(pcap_file_parent);
            $("#srch_pcap_file").val(pcap_file_name);
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_PCAP_FILE_DOWN';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/stcs/pcapfile.do', param);
        }
    </script>

    </body>
</aot:html>
