<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES" dataTables="YES" chartjs="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>
        .datepicker {
            z-index: 1200 !important;
        }

        .smart-form .inline-group .radio {
            margin-right: 10px;
        }
    </style>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/stcs/var/stats.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
        <input type="hidden" name="srch_index_type" id="srch_index_type"/>
        <input type="hidden" name="srch_text_prefix" id="srch_text_prefix"/>
        <input type="hidden" name="srch_filter_column" id="srch_filter_column"/>
        <input type="hidden" name="srch_filter_type" id="srch_filter_type"/>
        <input type="hidden" name="srch_filter_input" id="srch_filter_input"/>
        <input type="hidden" name="srch_es_base_url" id="srch_es_base_url"/>
        <input type="hidden" name="srch_out_col_ids" id="srch_out_col_ids"/>
        <input type="hidden" name="srch_out_col_names" id="srch_out_col_names"/>
        <input type="hidden" name="srch_out_col_types" id="srch_out_col_types"/>
        <input type="hidden" name="srch_def_col_id" id="srch_def_col_id"/>
        <input type="hidden" name="srch_uuid" id="srch_uuid"/>
        <input type="hidden" name="srch_grid_data" id="srch_grid_data"/>
    </form>
    <c:set var="ALL">
        <spring:message code="TEXT.COMM.SEL.PLEASE"/>
    </c:set>
    <c:set var="CHOICE">
        <spring:message code="TEXT.COMM.SEL.CHOICE"/>
    </c:set>
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
                <div class="col-xs-6">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-pencil-square-o"></i>
                            ${curr_menu_name }
                    </h1>
                </div>

                <!-- 상단 버튼들 -->
                <!-- 				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" style="float: right; height: 66px; padding-top: 10px; text-align: right;"> -->
                <!-- 					<button type="button" class="btn btn-primary btn-sm" id="srch"> -->
                    <%-- 						<spring:message code="TEXT.COMM.BTN.SEARCH" /> --%>
                <!-- 					</button> -->
                <!-- 					<button type="button" class="btn btn-primary btn-sm" id="excel"> -->
                    <%-- 						<spring:message code="TEXT.COMM.BTN.EXCEL" /> --%>
                <!-- 					</button> -->
                <!-- 				</div> -->

            </div>
            <!-- 상단화면명 END -->

            <!-- widget grid -->
            <section id="widget-grid" class="">
                <div class="row">

                    <!-- NEW COL START -->
                    <article class="col-sm-12 col-md-12 col-lg-12">

                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0"
                             data-widget-colorbutton="false" data-widget-editbutton="false"
                             data-widget-custombutton="false"
                             role="widget">
                            <!-- widget options:
                usage: <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">

                data-widget-colorbutton="false"
                data-widget-editbutton="false"
                data-widget-togglebutton="false"
                data-widget-deletebutton="false"
                data-widget-fullscreenbutton="false"
                data-widget-custombutton="false"
                data-widget-collapsed="true"
                data-widget-sortable="false"

                -->
                            <header role="heading">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon" rel="tooltip"
                                       onclick="goVarStatsList();" title="" data-placement="bottom"
                                       data-original-title="<spring:message code="TEXT.COMM.BTN.SEARCH" />">
                                        <label>
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon" rel="tooltip" id="excel" title=""
                                       data-placement="bottom"
                                       data-original-title="<spring:message code="TEXT.COMM.BTN.EXCEL" />">
                                        <label>
                                            <i class="fa fa-file-excel-o" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon" rel="tooltip" title=""
                                       data-placement="bottom" data-original-title="Graph 보기" onclick="viewGraph();">
                                        <label>
                                            <i class="fa fa-bar-chart" aria-hidden="true"></i>
                                            Graph 보기
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon" rel="tooltip"
                                       onclick="addRow('AND');" title="" data-placement="bottom"
                                       data-original-title="AND <spring:message code="TEXT.COMM.BTN.CONDITION.REG" />">
                                        <label>
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            AND
                                            <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-toggle-btn"
                                       rel="tooltip" title="" data-placement="bottom" data-original-title="Collapse">
                                        <i class="fa fa-minus "></i>
                                    </a>
                                </div>
                                <span class="widget-icon">
									<i class="fa fa-search"></i>
								</span>
                                <h2>Search criteria</h2>
                                <span class="jarviswidget-loader">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <!-- widget div-->
                            <div role="content">
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <form action="" class="smart-form">
                                        <fieldset style="padding-top: 14px;">
                                            <div class="row">
                                                <section class="col col-1">
                                                    <label class="label" for="srch_format">
                                                        <spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_FORMAT"/>
                                                        <span style="color: red;"> *</span>
                                                    </label>
                                                </section>
                                                <section class="col col-4">
                                                    <label class="select">
                                                        <aot:select name="srch_format" id="srch_format" init="YES"
                                                                    event="onChange=\"varStatsGetDefInfo();\""
                                                                    initCode="" initName="${CHOICE}"
                                                                    group="VSTAT_FORMAT"/>
                                                        <i></i>
                                                    </label>
                                                </section>
                                                <section class="col col-1">
                                                    <label class="label text-right" for="srch_interval">
                                                        <spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_INTERVAL"/>
                                                        <span style="color: red;"> *</span>
                                                    </label>
                                                </section>
                                                <section class="col col-2">
                                                    <label class="select">
                                                        <aot:select name="srch_interval" id="srch_interval" init="YES"
                                                                    event="onChange=\"varStatsGetDefInfo();\""
                                                                    initCode="" initName="${CHOICE}"
                                                                    group="VSTAT_INTERVAL"/>
                                                        <i></i>
                                                    </label>
                                                </section>
                                            </div>
                                            <div class="row">
                                                <section class="col col-1">
                                                    <label class="label" for="srch_input_date_from">
                                                        <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                                        (from)
                                                    </label>
                                                </section>
                                                <section class="col col-2">
                                                    <label class="input">
                                                        <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" name="srch_input_date_from"
                                                               id="srch_input_date_from">
                                                    </label>
                                                </section>
                                                <!-- 검색 시작 시간 -->
                                                <section class="col col-2">
                                                    <div class="input-group">
                                                        <aot:select name="srch_input_hh_from" id="srch_input_hh_from"
                                                                    group="TIME_24HH" style="height:32px;width:45px;"/>
                                                        <i></i>
                                                        <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                                        <aot:select name="srch_input_mm_from" id="srch_input_mm_from"
                                                                    group="TIME_MM" style="height:32px;width:45px;"/>
                                                        <i></i>
                                                        <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                                    </div>
                                                </section>
                                                <section class="col col-1">
                                                    <label class="label text-right" for="srch_input_date_to">
                                                        <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                                        (to)
                                                    </label>
                                                </section>
                                                <!-- 검색 종료 일자 -->
                                                <section class="col col-2">
                                                    <label class="input">
                                                        <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" name="srch_input_date_to"
                                                               id="srch_input_date_to">
                                                    </label>
                                                </section>
                                                <!-- 검색 종료 시간 -->
                                                <section class="col col-2">
                                                    <div class="input-group">
                                                        <aot:select name="srch_input_hh_to" id="srch_input_hh_to"
                                                                    group="TIME_24HH" style="height:32px;width:45px;"/>
                                                        <i></i>
                                                        <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                                        <aot:select name="srch_input_mm_to" id="srch_input_mm_to"
                                                                    group="TIME_MM" style="height:32px;width:45px;"/>
                                                        <i></i>
                                                        <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                                    </div>
                                                </section>
                                                <section class="col col-2">
                                                    <div class="inline-group">
                                                        <aot:radio name="srch_date_type" id="srch_date_type"
                                                                   group="SEARCH_DATE_TYPE" inline="YES"
                                                                   cssId="radio"></aot:radio>
                                                    </div>
                                                </section>
                                            </div>
                                        </fieldset>
                                        <fieldset id="column-filters" class="hide"></fieldset>
                                    </form>
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </div>
                        <!-- end widget -->
                    </article>
                    <!-- END COL -->
                </div>

                <!-- row -->
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-2"
                             data-widget-editbutton="false" data-widget-colorbutton="false"
                             data-widget-deletebutton="false"
                             role="widget">
                            <!-- widget options:
                                usage: <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">

                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"

                                -->
                            <header>
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title=""
                                       data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn"
                                       title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <span class="widget-icon">
									<i class="fa fa-table"></i>
								</span>
                                <h2>Data grid</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <table id="table1" class="table table-bordered table-hover" data-page-length="100">
                                        <thead>
                                        <tr></tr>
                                        </thead>
                                    </table>
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </div>
                        <!-- end widget -->
                    </article>
                    <!-- WIDGET END -->
                </div>
            </section>
            <!-- end widget grid -->
        </div>
        <!-- content -->

        <table id="sampleTable" class="table table-bordered table-hover hide" data-page-length="100">
            <thead>
            <tr></tr>
            </thead>
        </table>
    </div>
    <!-- main -->

    <div id="modalGraph" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog  modal-lg" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-body" style="padding: 1px;"></div>
            </div>
        </div>
    </div>

    <aot:select name="sample_filter_column" id="sample_filter_column" init="YES" initCode="ALL" initName="${ALL}"
                cssId="hide"/>
    <aot:select name="sample_filter_type" id="sample_filter_type" group="SRCH_FILTER_TYPE" cssId="hide"/>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        //타이머
        let timer;
        //타이머 선택 값
        const selTimerVal = "1000"; //1초
        let excel_uuid = "";
        let hfixed_left_col_cnt = undefined;

        let modObj = {
            template: {
                keyword: ["[curr_cnt]", "[tot_cnt]", "[percent]", "[write]"],
                layout: "<div id='dialog-message' title='Dialog Simple Title'>" + "<div class='hr hr-12 hr-double'></div>" + "<p><b>현재  [tot_cnt] / [curr_cnt] 진행 중</b></p>"
                    + "<p style='color: #b94a48;font-weight:bold;'> [write]</p>" + "<div class='progress progress-striped active no-margin'>"
                    + "<div class='progress-bar progress-primary' role='progressbar' style='width: [percent]%'></div>" + "</div>" + "</div>",
                deleteKeywords: function (str) {
                    let res = str;
                    $.each(modObj.template.keyword, function (i, o) {
                        res = res.split(o).join(' ');
                    });
                    return res;
                },
                layoutList: "<div id='dialog-message2' title='Dialog Simple Title'>" + "<div class='progress progress-striped active no-margin'>"
                    + "<div class='progress-bar progress-primary' role='progressbar' style='width: 100%'></div>" + "</div>" + "</div>"
            }
        };
        $(document).ready(function () {
            datePickerInit(); //달력 세팅
            controlInit();
            //진행중 모달 초기화
            pregressModal();
            bindClickEvent();

            // 			$('#srch_format>option[value="NetTraffic"]').prop('selected', true);
            // 			$('#srch_interval>option[value="5min"]').prop('selected', true).trigger('change');
        });

        /**조회단위 , 트래필 유형 변경 이벤트
         ** index_type 만들어 그리드 그리기 & 기본 값 세팅
         **/
        let varStatsGetDefInfo = function () {
            //둘다 값이 있을 때만
            if ($("#srch_interval>option:selected").val() !== "" && $("#srch_format>option:selected").val() !== "") {
                $("#srch_index_type").val($("#srch_format>option:selected").val() + "-" + $("#srch_interval>option:selected").val());
                /**************마스터 기본 값 취득**************/
                AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                    actionID: "ACTION_VARSTATS_DEFAULT_LIST",
                    srch_index_type: $("#srch_index_type").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    //첫번쨰 줄 제외하고 모두 지우기
                    $("#column-filters>div.row").remove();
                    $("#column-filters").addClass('hide');
                    //기본값 세팅
                    defInit(response);
                    hfixed_left_col_cnt = response.hfixed_left_col_cnt
                    //그리드 작성
                    createGrid(null);
                });
            }
        };

        //기본값 세팅
        let defInit = function (res) {
            //검색 일자 기본 정보에서 가져온 값으로 초기화
            $("#srch_es_base_url").val(res.srch_es_base_url);
            $("#srch_out_col_ids").val(res.srch_out_col_ids);
            $("#srch_out_col_names").val(res.srch_out_col_names);
            $("#srch_out_col_types").val(res.srch_out_col_types);
            $("#srch_def_col_id").val(res.srch_def_col_id);
            //필터 컬럼 콤보 값 삽입
            $("#sample_filter_column").html(res.filter);
        };

        let createGrid = function (mode) {
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_COLUMN_LIST",
                srch_index_type: $("#srch_index_type").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                drawGrid(response, mode);
            });
        };
        let table1 = undefined;
        const header = {
            dom: 'Bfrtip',
            language: AotDataTables.language,
            pagingType: "full_numbers",
            scrollY: '400px',
            scrollX: true,
            searching: false,
            ordering: false,
            "orderClasses": false,
            "deferRender": true,
            bSort: false,
            fixedColumns: {
                leftColumns: '${hfixed_left_col_cnt}',
                rightColumns: '${hfixed_right_col_cnt}'
            }
        };
        let drawGrid = function (response, mode) {
            // 			let svcUrl = '${CONTEXT_PATH}/stcs/var/stats.do?v=' + moment().valueOf();
            table1 && table1.destroy(false);
            table1 = undefined;
            $('div.widget-body:eq(1)').html($('#sampleTable').clone());
            $('div.widget-body>table').attr('id', 'table1').removeClass('hide');
            if (AotCommon.isEmpty(response)) {
                return;
            }
            $.each(response.ColNames, function (index, value) {
                $('#table1>thead>tr').append('<th>' + value + '</th>');
            });

            let columnDefs = [];
            let columns = [];
            $.each(response.ColModel, function (index, value) {
                columnDefs.push({
                    targets: index,
                    width: +value.width,
                    className: "text-" + value.align
                });
                columns.push({
                    data: value.name,
                    width: +value.width,
                    defaultContent: "-"
                });
            });
            let opt = _.cloneDeep(header);
            opt['columnDefs'] = columnDefs;
            opt['columns'] = columns;
            opt['fixedColumns']['leftColumns'] = hfixed_left_col_cnt || '${hfixed_left_col_cnt}';
            if (mode === 'search') {
                opt['serverSide'] = true;
                opt['ajax'] = {
                    url: '${CONTEXT_PATH}/stcs/var/stats.do?v=' + moment().valueOf(),
                    type: "POST",
                    data: {
                        actionID: "ACTION_VARSTATS_DATA_LIST",
                        srch_index_type: $("#srch_index_type").val(),
                        srch_es_base_url: $("#srch_es_base_url").val(),
                        srch_out_col_ids: $("#srch_out_col_ids").val(),
                        srch_out_col_names: $("#srch_out_col_names").val(),
                        srch_out_col_types: $("#srch_out_col_types").val(),
                        srch_def_col_id: $("#srch_def_col_id").val(),
                        srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from").val() + ":" + $("#srch_input_mm_from").val(),
                        srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to").val() + ":" + $("#srch_input_mm_to").val(),
                        srch_text_prefix: $("#srch_text_prefix").val(),
                        srch_filter_column: $("#srch_filter_column").val(),
                        srch_filter_type: $("#srch_filter_type").val(),
                        srch_filter_input: $("#srch_filter_input").val(),
                        srch_locality: $("#srch_locality").val()
                    },
                    dataFilter: function (data) {
                        let json = jQuery.parseJSON(data);
                        json.recordsTotal = json.records;
                        json.recordsFiltered = json.records;
                        json.data = json.root;
                        return JSON.stringify(json);
                    }
                };
            }
            table1 = $('#table1').DataTable(opt);
        };
        //달력
        let datePickerInit = function () {
            $('#srch_input_date_from').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment(),
            });

            $('#srch_input_date_to').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: moment(),
                useCurrent: false
            });
            AotDatetimePicker.setFromTo($('#srch_input_date_from'), $('#srch_input_date_to'));
        };
        //모달 초기화
        let pregressModal = function () {
            let html = modObj.template.layout;
            let htmlList = modObj.template.layoutList;
            /*CONVERT DIALOG TITLE TO HTML
             * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
             */
            $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
                _title: function (title) {
                    if (!this.options.title) {
                        title.html("&#160;");
                    } else {
                        title.html(this.options.title);
                    }
                }
            }));

            $(html).dialog({
                autoOpen: false,
                modal: true,
                resizable: false,
                width: 600,
                title: "<div class='widget-header'><h4><i class='icon-ok'></i> <spring:message code='TEXT.SEARCH.PREGRESS'/>...</h4></div>",
                open: function (event, ui) {
                    //hide close button.
                    $(this).parent().children().children('.ui-dialog-titlebar-close').hide();
                }
            });
            //검색은 상태 조회 없음.
            $(htmlList).dialog({
                autoOpen: false,
                modal: true,
                resizable: false,
                width: 600,
                title: "<div class='widget-header'><h4><i class='icon-ok'></i> <spring:message code='TEXT.SEARCH.PREGRESS'/>...</h4></div>",
                open: function (event, ui) {
                    //hide close button.
                    $(this).parent().children().children('.ui-dialog-titlebar-close').hide();
                }
            });
        };

        //클릭이벤트
        let bindClickEvent = function () {
            //엑셀 다운로드 클릭
            $("#excel").on('click', function () {
                let dataCnt = table1.data().count();
                if (dataCnt === 0 || dataCnt === undefined) {
                    AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                } else {
                    //마지막 페이지 번호
                    //마지막 페이지가 1이면 더이상 조회 할 것이 없으므로 엑셀 출력 시 다시 조회 하지 않고 바로 다운
                    if (parseInt(table1.page.info().end) == 1) {
                        $("#srch_grid_data").val(JSON.stringify(table1.rows().data()));
                        doExcelGrid();
                    } else {
                        $("#srch_grid_data").val("");
                        doExcel();
                    }
                }
            });

            // radio change 이벤트
            $("input[name=srch_date_type]").on('change', function () {
                let radioValue = $(this).val();
                if (radioValue === "DAY") {
                    $("#srch_input_date_from").val(moment().format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue === "WEEK") {
                    $("#srch_input_date_from").val(moment().subtract('days', 7).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue === "MONTH") {
                    $("#srch_input_date_from").val(moment().subtract('months', 1).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                }
            });

            //조건 변경 이벤트
            $('select[id^=filter_type]').on('change', function () {
                let clickedRow = $(this).parent().parent();
                let trNo = clickedRow.index();
                let value = $.trim(this.value);
                clickedRow.find("#filter_input").val("");
                //조건이 is null 또는 is not null 이면 검색어 disabled
                if (value === "ISNULL" || value === "NOTNULL") {
                    clickedRow.find("#filter_input").prop("disabled", true);
                } else {
                    clickedRow.find("#filter_input").prop('disabled', false);
                }
            });
        };
        //모달창 열기
        let openModal = function (btn_flag) {
            let modalHtml = modObj.template.layout;
            modalHtml = modalHtml.split("[curr_cnt]").join("0");
            modalHtml = modalHtml.split("[tot_cnt]").join("0");
            modalHtml = modalHtml.split("[percent]").join("0");
            modalHtml = modalHtml.split("[write]").join("");

            //엑셀 클릭시
            if (btn_flag === "excel") {
                $("#dialog-message").html(modalHtml).dialog("option", "buttons", [{
                    html: "<spring:message code='TEXT.COMM.BTN.STOP'/>",
                    "class": "btn btn-default btn-danger cu-stop",
                    click: function () {
                        $(this).dialog("close");
                        functionStop();
                    }
                }]);
                $("#dialog-message").dialog('open');
                return false;
                //조회 클릭시
            } else {
                let html = modObj.template.layoutList;
                $("#dialog-message2").html(html).dialog("option", "buttons", []).dialog('open');
                return false;
            }
        };

        //진행중 CDR 조회
        let goVarStatsList = function () {
            if ($("#srch_format>option:selected").val() === "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_FORMAT" />는 필수입니다.");
                return;
            }
            if ($("#srch_interval>option:selected").val() === "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_INTERVAL" />는 필수입니다.");
                return;
            }
            if ($("#srch_locality").val() === "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.STCS.VAR.STATS.SRCH.LOCALITY" />는 필수입니다.");
                return;
            }

            //각 로우별 검색조건 취합
            conditionGather();
            createGrid('search');
        };

        let staticRowNum = 0;
        //AND조건 로우 추가
        let addRow = function (type, rowNum) {
            if ($("#srch_interval>option:selected").val() === "" || $("#srch_format>option:selected").val() === "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.VAR.STATS.INDEX_TYPE.REQUIRED'/>");
                return;
            }

            $('#column-filters').removeClass('hide');
            let data = {
                type: type,
                rowNum: staticRowNum++
            };
            if (type === 'AND') {
                AotHandlebars.drawDynamicHtml($('#column-filters'), 'append', 'templateColumnFilters', data).done(function () {
                    $('select[name="filter_column"]').each(function (index, value) {
                        if ($(this).html() === '') {
                            $(this).html($('#sample_filter_column').html());
                            $('select[name="filter_type"]:eq(' + index + ')').html($('#sample_filter_type').html());
                        }
                    });
                });
            } else {
                AotHandlebars.drawDynamicHtml($('#column-filters>div.row[data-row-num="' + rowNum + '"]'), 'after', 'templateColumnFilters', data).done(function () {
                    $('select[name="filter_column"]').each(function (index, value) {
                        if ($(this).html() === '') {
                            $(this).html($('#sample_filter_column').html());
                            $('select[name="filter_type"]:eq(' + index + ')').html($('#sample_filter_type').html());
                        }
                    });
                });
            }
        };
        let deleteRow = function (rowNum) {
            $('div[data-row-num="' + (+rowNum) + '"]').remove();
            if ($('#column-filters>div.row').length === 0) {
                $('#column-filters').addClass('hide');
            }
        };
        //각 로우별 검색조건 취합
        let conditionGather = function () {
            let textPrefix = [];
            let column = [];
            let type = [];
            let input = [];
            $('#column-filters div.row').each(function () {
                //AND OR 조건
                textPrefix.push($.trim($(this).children('section:eq(0)').text()));
                //검색 컬럼
                column.push($.trim($(this).find('select[name="filter_column"]>option:selected').val()));
                //검색 조건
                type.push($.trim($(this).find('select[name="filter_type"]>option:selected').val()));
                //검색어
                input.push($.trim($(this).find('input[name="filter_input"]').val()));
            });
            $("#srch_text_prefix").val(textPrefix.join());
            $("#srch_filter_column").val(column.join());
            $("#srch_filter_type").val(type.join());
            $("#srch_filter_input").val(input.join());
        };
        //화면 초기화
        let controlInit = function () {
            //검색 일자 기본 정보에서 가져온 값으로 초기화
            $("#srch_input_date_from").val(moment().format("YYYY-MM-DD"));
            $("#srch_input_hh_from>option[value='00']").prop('selected', true);
            $("#srch_input_mm_from>option[value='00']").prop('selected', true);

            $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
            $("#srch_input_hh_to>option[value='23']").prop('selected', true);
            $("#srch_input_mm_to>option[value='55']").prop('selected', true);

            $('input:radio[name=srch_date_type]:input[value=DAY]').attr("checked", true);
        };
        //타이머설정
        let setTimer = function (updateTimer) {
            clearInterval(timer);
            timer = setInterval(function () {
                //검색, 엑셀 상태 조회
                doCheckStatus();
            }, updateTimer);
        };

        //검색, 엑셀 상태 체크 ajax
        let doCheckStatus = function () {
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_EXCEL_STATUS_LIST",
                srch_uuid: excel_uuid
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                let str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    let status = response.status;
                    let total = response.total;
                    let recv = response.recv;
                    let percent = (parseInt(recv) / parseInt(total)) * 100;
                    let downPath = response.path;

                    // 					console.log("interval~~  ", status + " , " + total + " , " + recv + " , " + percent);
                    // 					console.log(parseInt(total), parseInt(recv));
                    //console.log(parseInt(total) == parseInt(recv) ? true : false);

                    let modalHtml = modObj.template.layout;
                    modalHtml = modalHtml.replace("[curr_cnt]", recv);
                    modalHtml = modalHtml.replace("[tot_cnt]", total);
                    modalHtml = modalHtml.replace("[percent]", percent);
                    if (status === "I" && parseInt(total) != parseInt(recv)) {
                        modalHtml = modalHtml.replace("[write]", "");
                        $("#dialog-message").html(modalHtml);
                    } else if (status === "I" && parseInt(total) == parseInt(recv)) {
                        // 						console.log(parseInt(total) == parseInt(recv) ? true : false);
                        modalHtml = modalHtml.replace("[write]", " 파일을 쓰는 중 입니다...");
                        $("#dialog-message").html(modalHtml);
                    } else if (status === "S") {
                        clearInterval(timer);
                        $("#dialog-message").dialog("close");
                        doExcelDown(excel_uuid);
                        excel_uuid = "";
                    }
                }
            });
        };
        //중지
        let functionStop = function () {
            //타이머 초기화
            clearInterval(timer);
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_EXCEL_STOP",
                srch_uuid: excel_uuid
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                // 				let str = response.returnMsg.split(":");
                // 				console.log("stop~~", str);
                //엑셀 uuid 초기화
                excel_uuid = "";
            });
        };

        //엑셀
        let doExcel = function () {
            //각 로우별 검색조건 취합
            conditionGather();
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_EXCEL_DATA_LIST",
                srch_index_type: $("#srch_index_type").val(),
                srch_es_base_url: $("#srch_es_base_url").val(),
                srch_out_col_ids: $("#srch_out_col_ids").val(),
                srch_out_col_names: $("#srch_out_col_names").val(),
                srch_out_col_types: $("#srch_out_col_types").val(),
                srch_def_col_id: $("#srch_def_col_id").val(),
                srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from>option:selected").val() + ":" + $("#srch_input_mm_from>option:selected").val(),
                srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to>option:selected").val() + ":" + $("#srch_input_mm_to>option:selected").val(),
                srch_text_prefix: $("#srch_text_prefix").val(),
                srch_filter_column: $("#srch_filter_column").val(),
                srch_filter_type: $("#srch_filter_type").val(),
                srch_filter_input: $("#srch_filter_input").val(),
                srch_locality: $("#srch_locality").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                let uuid = response.uuid;
                excel_uuid = uuid;
                openModal("excel");
                setTimer(selTimerVal);
            });
        };
        //그리드 내용 그대로 엑셀출력
        let doExcelGrid = function () {
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_EXCEL_DATA_LIST_GRID",
                srch_index_type: $("#srch_index_type").val(),
                srch_es_base_url: $("#srch_es_base_url").val(),
                srch_out_col_ids: $("#srch_out_col_ids").val(),
                srch_out_col_names: $("#srch_out_col_names").val(),
                srch_out_col_types: $("#srch_out_col_types").val(),
                srch_def_col_id: $("#srch_def_col_id").val(),
                srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from>option:selected").val() + ":" + $("#srch_input_mm_from>option:selected").val(),
                srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to>option:selected").val() + ":" + $("#srch_input_mm_to>option:selected").val(),
                srch_grid_data: $("#srch_grid_data").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                let uuid = response.uuid;
                if (uuid != "") {
                    excel_uuid = uuid;
                    doExcelDown(excel_uuid);
                    excel_uuid = "";
                } else {
                    AotSmartAdmin.smallBoxWarning("fail");
                }
            });
        };
        let doExcelDown = function (uuid) {
            $("#srch_uuid").val(uuid);
            let param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_VARSTATS_EXCEL_DOWN';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/stcs/var/stats.do', param);
        };

        let viewGraph = function () {
            if ($('#formGraphPop').length === 0) {
                AotAjax.excute('/stcs/var/VarListGraphPop.pop', {}, {
                    type: 'GET',
                    dataType: 'html'
                }).done(function (response) {
                    $.when().done(function () {
                        AotAjax.drawHtml('#modalGraph .modal-body', 'html', response);
                        $('#modalGraph').modal({
                            backdrop: 'static',
                            show: true
                        });
                    }).done(function () {
                        initGraphPop();
                    });
                });
            } else {
                $.when().done(function () {
                    $('#modalGraph').modal({
                        backdrop: 'static',
                        show: true
                    });
                }).done(function () {
                    initGraphPop();
                });
            }
        };

        // 이상하게 첫번째 위젯에 타이틀이 지워짐..
        $(document).ready(function () {
            setTimeout(function () {
                $('h2:eq(0)').text('Search criteria');
            }, 100);
            setTimeout(function () {
                $('h2:eq(0)').text('Search criteria');
            }, 1000);
            setTimeout(function () {
                $('h2:eq(0)').text('Search criteria');
            }, 3000);
        });
    </script>
    <script id="templateColumnFilters" type="text/x-handlebars-template">
        <div class="row" data-row-num="{{rowNum}}">
            <section class="col col-1">
                <i class="fa fa-chain"></i>
                {{type}}
            </section>
            <section class="col col-2">
                <label class="select">
                    <select name="filter_column" id="filter_column"/>
                    <i></i>
                </label>
            </section>
            <section class="col col-2">
                <label class="select">
                    <select name="filter_type" id="filter_type"/>
                    <i></i>
                </label>
            </section>
            <section class="col col-4">
                <label class="input">
                    <input type="text" name="filter_input" id="filter_input">
                </label>
            </section>
            <section class="col col-3">
                <div class="btn-group btn-group-justified" role="group" aria-label="...">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-warning btn-sm" onclick="addRow('OR', '{{rowNum}}')">
                            OR
                            <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                        </button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-danger btn-sm" onclick="deleteRow('{{rowNum}}')">
                            <spring:message code="TEXT.COMM.BTN.DELETE"/>
                        </button>
                    </div>
                </div>
            </section>
        </div>
    </script>
    </body>
</aot:html>
