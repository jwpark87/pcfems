<%----------------------------------------------------------------------------------------
 - 파일이름	: stcs/chart/Gt.jsp
 - 설      명	: GT별 통계 화면 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.09    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <c:set var="CHOICE">
        <spring:message code="TEXT.COMM.SEL.CHOICE"/>
    </c:set>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/stcs/gtstatistic.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="stcs_excel_data" id="stcs_excel_data"/>
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
                        <button type="button" class="btn btn-primary btn-sm" id="excel">
                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                        </button>
                    </div>

                </div>
                <!-- 상단화면명 END -->

                <!-- 검색박스 START -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="well">
                            <div class="smart-form">
                                <fieldset style="background-color: #fbfbfb; padding: 0px;">

                                    <div class="row">
                                        <div class="col col-1 input-group">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>

                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="srch_input_date_from" id="srch_input_date_from">
                                            </label>
                                        </div>

                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_input_hh_from" id="srch_input_hh_from" group="TIME_24HH" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                            <aot:select name="srch_input_mm_from" id="srch_input_mm_from" group="TIME_MM" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                        </div>

                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="srch_input_date_to" id="srch_input_date_to">
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_input_hh_to" id="srch_input_hh_to" group="TIME_24HH" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                            <aot:select name="srch_input_mm_to" id="srch_input_mm_to" group="TIME_MM" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                        </div>

                                        <!-- 라디오 버튼 영역 -->
                                        <div class="col col-3 input-group" style="height: 32px; padding-top: 5px;">
                                            <div class="inline-group">
                                                <aot:radio name="srch_date_type" id="srch_date_type" group="SEARCH_DATE_TYPE" inline="YES" cssId="radio"></aot:radio>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 기준시간  영역 -->
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SRCH.OCCUR_DT"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_stcs_dt" id="srch_stcs_dt" group="SRCH_STCS_TIME" style="height:32px;width:99%;"/>
                                            <i></i>
                                        </div>

                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SRCH.LOCALITY"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_locality" id="srch_locality" group="LOCALITY" style="height:32px;width:99%;" init="YES" initCode="" initName="${CHOICE}"/>
                                            <i></i>
                                        </div>

                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CGGT"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_cg_gt" id="srch_cg_gt">
                                                </inpuit>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CDGT"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_cd_gt" id="srch_cd_gt">
                                            </label>
                                        </div>
                                    </div>


                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px; padding: 0 0 0 15px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CG_PC_LIST_NM"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group" style="padding: 0 19px 0 10px;">
                                            <label class="input">
                                                <input type="text" name="srch_cg_pc_list_nm" id="srch_cg_pc_list_nm">
                                            </label>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px; padding: 0 2px 0 10px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CD_PC_LIST_NM"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group" style="padding: 0 19px 0 10px;">
                                            <label class="input">
                                                <input type="text" name="srch_cd_pc_list_nm" id="srch_cd_pc_list_nm">
                                            </label>
                                        </div>
                                        <div class="col col-1"></div>

                                        <div class="col col-3 input-group" style="height: 32px; padding-top: 5px;">
                                            <div class="inline-group">
                                                <aot:checkbox name="srch_groupping_pc_list_nm" id="srch_groupping_pc_list_nm" list="${pc_list_nm_ls }" inline="YES" cssId="checkbox"></aot:checkbox>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px; padding: 0 0 0 15px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CG_CARRIER_NM"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_cg_carrier_nm" id="srch_cg_carrier_nm">
                                            </label>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none; text-align: left;">
                                                <spring:message code="TEXT.CHART.SEARCH.GT.CD_CARRIER_NM"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_cd_carrier_nm" id="srch_cd_carrier_nm">
                                            </label>
                                        </div>
                                    </div>


                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 검색박스 END -->

                <section id="widget-grid" class="">
                    <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                    <div class="row">
                        <article class="col-sm-12">
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                 data-widget-deletebutton="false"
                                 data-widget-fullscreenbutton="false" data-widget-togglebutton="false">

                                <header>
									<span class="widget-icon">
										<i class="fa fa-bar-chart-o"></i>
									</span>
                                    <h2>
                                        <spring:message code="TEXT.CHART.GRID.TITLE"/>
                                    </h2>
                                </header>

                                <div>
                                    <div class="widget-body no-padding">
                                        <table id="gridChartList"></table>
                                        <div id="gridToolbar"></div>
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
        $(document).ready(function () {
            controlInit();
            datePickerInit(); //달력 세팅
            bindClickEvent();

            $("#gridChartList").jqGrid('clearGridData');

            jQuery("#gridChartList").jqGrid({
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
                colNames: [
                    //기준시간
                    "<spring:message code="TEXT.CHART.GRID.GT.OCCUR_DT"/>", "<spring:message code="TEXT.CHART.GRID.GT.LOCALITY"/>",

                    //GT
                    "<spring:message code="TEXT.CHART.GRID.GT.CG.GT"/>",
                    //PC LIST NM
                    "<spring:message code="TEXT.CHART.GRID.GT.CG.PC_LIST_NM"/>",
                    //사업자
                    "<spring:message code="TEXT.CHART.GRID.GT.CG.CARRIER_NM"/>",
                    //GT
                    "<spring:message code="TEXT.CHART.GRID.GT.CD.GT"/>",
                    //PC LIST NM
                    "<spring:message code="TEXT.CHART.GRID.GT.CD.PC_LIST_NM"/>",
                    //사업자
                    "<spring:message code="TEXT.CHART.GRID.GT.CD.CARRIER_NM"/>",
                    //메시지 건수
                    "<spring:message code="TEXT.CHART.GRID.GT.IN.IN_OCCUR_CNT"/>",
                    //성공 건수
                    "<spring:message code="TEXT.CHART.GRID.GT.IN.IN_SUCC_CNT"/>",
                    //패킷 사이즈
                    "<spring:message code="TEXT.CHART.GRID.GT.IN.IN_PKT_SIZE"/>",
                    //성공율(%)
                    "<spring:message code="TEXT.CHART.GRID.GT.IN.IN_SUCC_PRECENT"/>",
                    //메시지 건수
                    "<spring:message code="TEXT.CHART.GRID.GT.OUT.OUT_OCCUR_CNT"/>",
                    //성공 건수
                    "<spring:message code="TEXT.CHART.GRID.GT.OUT.OUT_SUCC_CNT"/>",
                    //패킷 사이즈
                    "<spring:message code="TEXT.CHART.GRID.GT.OUT.OUT_PKT_SIZE"/>",
                    //성공율(%)
                    "<spring:message code="TEXT.CHART.GRID.GT.OUT.OUT_SUCC_PRECENT"/>"],
                colModel: [{
                    name: "occur_dt",
                    index: "occur_dt",
                    align: "center",
                    width: 150
                }, {
                    name: "locality",
                    index: "locality",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cg_gt",
                    index: "cg_gt",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cg_pc_list_nm",
                    index: "cg_pc_list_nm",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cg_carrier_nm",
                    index: "cg_carrier_nm",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cd_gt",
                    index: "cd_gt",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cd_pc_list_nm",
                    index: "cd_pc_list_nm",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "cd_carrier_nm",
                    index: "cd_carrier_nm",
                    align: "center",
                    width: 150,
                    formatter: setNameText
                }, {
                    name: "in_occur_cnt",
                    index: "in_occur_cnt",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "in_succ_cnt",
                    index: "in_succ_cnt",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "in_pkt_size",
                    index: "in_pkt_size",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "in_succ_percent",
                    index: "in_succ_percent",
                    align: "right",
                    formatter: AotJqGrid.formatterPercentDefaultValue,
                    unformat: AotJqGrid.unformatterPercentDefaultValue,
                    width: 150
                }, {
                    name: "out_occur_cnt",
                    index: "out_occur_cnt",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "out_succ_cnt",
                    index: "out_succ_cnt",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "out_pkt_size",
                    index: "out_pkt_size",
                    align: "right",
                    width: 150,
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "out_succ_percent",
                    index: "out_succ_percent",
                    align: "right",
                    formatter: AotJqGrid.formatterPercentDefaultValue,
                    unformat: AotJqGrid.unformatterPercentDefaultValue,
                    width: 150
                }],
                sortname: "occur_dt",
                rowNum: 100,
                rowList: [100, 300, 500, 1000],
                sortorder: "asc",
                shrinkToFit: false, // 가로 스크롤
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridChartList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridChartList").getRowData(id);
                    }
                }
            });
            jQuery("#gridChartList").jqGrid("navGrid", "#gridToolbar", {
                    edit: false,
                    add: false,
                    del: false,
                    view: false,
                    search: false,
                    refresh: false
                },// edit,add,delete,view,search,options
                {}, {}, {}, {
                    multipleSearch: true
                }, {
                    width: 500
                });

            AotJqGrid.setGridStyle();

            //아이피 헤더 그룹
            jQuery("#gridChartList").jqGrid('setGroupHeaders', {
                useColSpanStyle: true,
                groupHeaders: [{
                    startColumnName: 'cg_gt',
                    numberOfColumns: 3,
                    titleText: '<center><b><spring:message code ='TEXT.CHART.GRID.GT.CG'/></center>'
                }, {
                    startColumnName: 'cd_gt',
                    numberOfColumns: 3,
                    titleText: '<center><b><spring:message code ='TEXT.CHART.GRID.GT.CD'/></center>'
                }, {
                    startColumnName: 'in_occur_cnt',
                    numberOfColumns: 4,
                    titleText: '<center><b><spring:message code ='TEXT.CHART.GRID.GT.IN'/></center>'
                }, {
                    startColumnName: 'out_occur_cnt',
                    numberOfColumns: 4,
                    titleText: '<center><b><spring:message code ='TEXT.CHART.GRID.GT.OUT'/></center>'
                }]
            });

        });

        //"-" 형태로 쿼리에서 넘어오므로 다시 편집.
        function setNameText(cellValue, options, rowObject) {
            var result_txt = "";
            if (cellValue === "-") {
                result_txt = "<spring:message code="TEXT.COMM.SEL.ALL"/>";
            } else {
                result_txt = cellValue;
            }
            return result_txt;
        }

        function datePickerInit() {
            $('#srch_input_date_from').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment()
            });

            $('#srch_input_date_to').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment(),
                useCurrent: false
            });
            AotDatetimePicker.setFromTo($('#srch_input_date_from'), $('#srch_input_date_to'));
        }

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goChartList();
            });

            $("#srch_country").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });

            $("#srch_host").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });

            $("#srch_realm").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });

            $("#excel").on('click', function () {
                doExcel();
            });

            // radio change 이벤트
            $("input[name=srch_date_type]").on('change', function () {
                var radioValue = $(this).val();
                if (radioValue == "DAY") {
                    $("#srch_input_date_from").val(moment().format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue == "WEEK") {
                    $("#srch_input_date_from").val(moment().subtract('days', 7).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue == "MONTH") {
                    $("#srch_input_date_from").val(moment().subtract('months', 1).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                }
            });
        }

        function controlInit() {
            $("#srch_input_hh_from").val("${srch_input_hh_from}");
            $("#srch_input_mm_from").val("${srch_input_mm_from}");
            $("#srch_input_hh_to").val("${srch_input_hh_to}");
            $("#srch_input_mm_to").val("${srch_input_mm_to}");
            $('input:radio[name=srch_date_type]:input[value=DAY]').attr("checked", true);
            $("#srch_stcs_dt").val("DAY");
        }

        //조회
        function goChartList() {
            if ($("#srch_input_date_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_date_from").focus();
                return;
            }
            if ($("#srch_input_hh_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_hh_from").focus();
                return;
            }
            if ($("#srch_input_mm_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_mm_from").focus();
                return;
            }
            if ($("#srch_input_date_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_date_to").focus();
                return;
            }
            if ($("#srch_input_hh_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_hh_to").focus();
                return;
            }
            if ($("#srch_input_mm_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_mm_to").focus();
                return;
            }
            if ($("#srch_stcs_dt").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CHART.SRCH.OCCUR_DT" />는 필수입니다.');
                $("#srch_sbc_group_id").focus();
                return;
            }

            var v_srch_groupping_pc_list_nm = "N";
            //체크박스 값에 따른 변경. 기존 처리 변경 하지 않기 위해 변수에 담는 처리 유지
            $(':checkbox[id=srch_groupping_pc_list_nm]').each(function () {
                if ($(this).is(':checked') == true && $(this).val() == "Y") {
                    v_srch_groupping_pc_list_nm = "Y";
                }
            });

            $("#gridChartList").jqGrid('clearGridData');

            jQuery("#gridChartList").setGridParam({
                url: "${CONTEXT_PATH}/stcs/gtstatistic.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_GT_LIST",
                    srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                    srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                    srch_stcs_dt: $("#srch_stcs_dt").val(),
                    srch_locality: $("#srch_locality").val(),
                    srch_cg_gt: $("#srch_cg_gt").val(),
                    srch_cd_gt: $("#srch_cd_gt").val(),
                    srch_cg_pc_list_nm: $("#srch_cg_pc_list_nm").val(),
                    srch_cd_pc_list_nm: $("#srch_cd_pc_list_nm").val(),
                    srch_cg_carrier_nm: $("#srch_cg_carrier_nm").val(),
                    srch_cd_carrier_nm: $("#srch_cd_carrier_nm").val(),
                    srch_groupping_pc_list_nm: v_srch_groupping_pc_list_nm
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridChartList").getGridParam("reccount") === 0) {
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

            myForm.srchSidx.value = jQuery("#gridChartList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridChartList").getGridParam('sortorder');
            myForm.srch_date_from.value = $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val();
            myForm.srch_date_to.value = $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val();

            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/stcs/gtstatistic.do', param);
        }
    </script>
    </body>
</aot:html>
