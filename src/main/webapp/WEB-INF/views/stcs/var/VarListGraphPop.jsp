<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<!-- widget grid -->
<section id="widget-grid" class="">
    <div class="row">

        <!-- NEW COL START -->
        <article class="col-sm-12 col-md-12 col-lg-12">

            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false"
                 data-widget-editbutton="false" data-widget-custombutton="false" role="widget">
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
                        <a href="javascript:void(0);" class="button-icon" rel="tooltip" onclick="makeGraph();" title=""
                           data-placement="bottom" data-original-title="Graph 출력">
                            <label>
                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                Graph 출력
                            </label>
                        </a>
                        <a href="javascript:void(0);" class="button-icon" rel="tooltip" onclick="addRowGraph('AND');"
                           title="" data-placement="bottom"
                           data-original-title="AND <spring:message code="TEXT.COMM.BTN.CONDITION.REG" />">
                            <label>
                                <i class="fa fa-plus" aria-hidden="true"></i>
                                AND
                                <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                            </label>
                        </a>
                        <a href="javascript:void(0);" class="button-icon" rel="tooltip" data-dismiss="modal"
                           aria-label="Close" title="" data-placement="bottom" data-original-title="close popup">
                            <label>
                                <i class="fa fa-times" aria-hidden="true"></i>
                            </label>
                        </a>
                    </div>
                    <span class="widget-icon">
						<i class="fa fa-bar-chart" aria-hidden="true"></i>
					</span>
                    <h2>Graph 출력 조건</h2>
                    <span class="jarviswidget-loader">
						<i class="fa fa-refresh fa-spin"></i>
					</span>
                </header>
                <!-- widget div-->
                <div role="content">
                    <!-- widget content -->
                    <div class="widget-body no-padding">
                        <form action="" class="smart-form" id="formGraphPop">
                            <input type="hidden" name="graph_srch_index_type" id="graph_srch_index_type"/>
                            <input type="hidden" name="graph_srch_es_base_url" id="graph_srch_es_base_url"/>
                            <input type="hidden" name="graph_srch_out_col_ids" id="graph_srch_out_col_ids"/>
                            <input type="hidden" name="graph_srch_out_col_names" id="graph_srch_out_col_names"/>
                            <input type="hidden" name="graph_srch_out_col_types" id="graph_srch_out_col_types"/>
                            <input type="hidden" name="graph_srch_def_col_id" id="graph_srch_def_col_id"/>
                            <input type="hidden" name="graph_srch_text_prefix" id="graph_srch_text_prefix"/>
                            <input type="hidden" name="graph_srch_filter_column" id="graph_srch_filter_column"/>
                            <input type="hidden" name="graph_srch_filter_type" id="graph_srch_filter_type"/>
                            <input type="hidden" name="graph_srch_filter_input" id="graph_srch_filter_input"/>
                            <fieldset style="padding-top: 14px;">
                                <div class="row">
                                    <section class="col col-1">
                                        <label class="label" for="graph_srch_format">
                                            <spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_FORMAT"/>
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-4">
                                        <label class="select">
                                            <aot:select name="graph_srch_format" id="graph_srch_format" init="YES"
                                                        event="onChange=\"graphStatsGetDefInfo();\"" initCode=""
                                                        initName="선택"
                                                        group="VSTAT_FORMAT"/>
                                            <i></i>
                                        </label>
                                    </section>
                                    <section class="col col-1">
                                        <label class="label text-right" for="graph_srch_interval">
                                            <spring:message code="TEXT.STCS.VAR.STATS.SRCH.VSTAT_INTERVAL"/>
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2">
                                        <label class="select">
                                            <aot:select name="graph_srch_interval" id="graph_srch_interval" init="YES"
                                                        event="onChange=\"graphStatsGetDefInfo();\"" initCode=""
                                                        initName="선택"
                                                        group="VSTAT_INTERVAL"/>
                                            <i></i>
                                        </label>
                                    </section>
                                </div>
                                <div class="row">
                                    <section class="col col-1">
                                        <label class="label" for="graph_srch_input_date_from">
                                            <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                            (from)
                                        </label>
                                    </section>
                                    <section class="col col-2">
                                        <label class="input">
                                            <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="graph_srch_input_date_from"
                                                   id="graph_srch_input_date_from">
                                        </label>
                                    </section>
                                    <!-- 검색 시작 시간 -->
                                    <section class="col col-2">
                                        <div class="input-group">
                                            <aot:select name="graph_srch_input_hh_from" id="graph_srch_input_hh_from"
                                                        group="TIME_24HH" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                            <aot:select name="graph_srch_input_mm_from" id="graph_srch_input_mm_from"
                                                        group="TIME_MM" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                        </div>
                                    </section>
                                    <section class="col col-1">
                                        <label class="label text-right" for="graph_srch_input_date_to">
                                            <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                            (to)
                                        </label>
                                    </section>
                                    <!-- 검색 종료 일자 -->
                                    <section class="col col-2">
                                        <label class="input">
                                            <i class="icon-append fa fa-calendar"></i>
                                            <input type="text" name="graph_srch_input_date_to"
                                                   id="graph_srch_input_date_to">
                                        </label>
                                    </section>
                                    <!-- 검색 종료 시간 -->
                                    <section class="col col-2">
                                        <div class="input-group">
                                            <aot:select name="graph_srch_input_hh_to" id="graph_srch_input_hh_to"
                                                        group="TIME_24HH" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                            <aot:select name="graph_srch_input_mm_to" id="graph_srch_input_mm_to"
                                                        group="TIME_MM" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                        </div>
                                    </section>
                                    <section class="col col-2">
                                        <div class="inline-group">
                                            <aot:radio name="graph_srch_date_type" id="graph_srch_date_type"
                                                       group="SEARCH_DATE_TYPE" inline="YES" cssId="radio"></aot:radio>
                                        </div>
                                    </section>
                                </div>
                            </fieldset>
                            <fieldset id="graph-column-filters" style="display: none;"></fieldset>
                            <fieldset>
                                <div class="row">
                                    <section class="col col-1">
                                        <label class="label" for="graphType">
                                            Graph 종류
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2">
                                        <label class="select">
                                            <aot:select name="graphType" id="graphType" init="NO"
                                                        group="SRCH_GRAPH_KIND" event="onchange=\"changeGraphType()\""
                                                        option="required"/>
                                            <i></i>
                                        </label>
                                    </section>
                                    <section class="col col-1">
                                        <label class="label" for="columnX">
                                            X축 컬럼
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2">
                                        <label class="select">
                                            <select id="columnX" required></select>
                                            <i></i>
                                        </label>
                                    </section>
                                    <section class="col col-1">
                                        <label class="label" for="tgt_col_type">
                                            대상구분기준
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2">
                                        <label class="select">
                                            <aot:select name="tgt_col_type" id="tgt_col_type" init="NO"
                                                        group="TGT_COL_TYPE" event="onchange=\"changeTgtColType()\""
                                                        option="required"/>
                                            <i></i>
                                        </label>
                                    </section>
                                </div>
                                <div class="row" id="div_graphColumns" style="display: none;">
                                    <section class="col col-1">
                                        <label class="label" for="graphColumns">
                                            대상 컬럼
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2" data-id="graphColumns"></section>
                                    <section class="col col-2" data-id="graphColumns"></section>
                                    <section class="col col-2" data-id="graphColumns"></section>
                                    <section class="col col-2" data-id="graphColumns"></section>
                                    <section class="col col-2" data-id="graphColumns"></section>
                                </div>
                                <div class="row" id="div_graphColumnY" style="display: none;">
                                    <section class="col col-1">
                                        <label class="label" for="columnY">
                                            Y축 컬럼
                                            <span style="color: red;"> *</span>
                                        </label>
                                    </section>
                                    <section class="col col-2" data-id="columnY"></section>
                                    <section class="col col-2" data-id="columnY"></section>
                                    <section class="col col-2" data-id="columnY"></section>
                                    <section class="col col-2" data-id="columnY"></section>
                                    <section class="col col-2" data-id="columnY"></section>
                                </div>
                            </fieldset>
                            <fieldset id="fieldset_myChart">
                                <div class="widget-body no-padding">
                                    <div class="col col-xs-12">
                                        <canvas id="myChart" style="width: 100%; height: 500px;"></canvas>
                                    </div>
                                </div>
                            </fieldset>
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
</section>
<!-- end widget grid -->

<script>
    let initGraphPop = function () {
        AotDatetimePicker.init();
        AotDatetimePicker.setDatePickerYYYYMMDD($('#graph_srch_input_date_from, #graph_srch_input_date_to'));
        AotDatetimePicker.setFromTo($('#graph_srch_input_date_from'), $('#graph_srch_input_date_to'));
        $("#graph_srch_format>option[value='" + $("#srch_format>option:selected").val() + "']").prop('selected', true);
        $("#graph_srch_interval>option[value='" + $("#srch_interval>option:selected").val() + "']").prop('selected', true).trigger('change');
        $("#graph_srch_input_date_from").val($("#srch_input_date_from").val());
        $("#graph_srch_input_hh_from>option[value='" + $("#srch_input_hh_from>option:selected").val() + "']").prop('selected', true);
        $("#graph_srch_input_mm_from>option[value='" + $("#srch_input_mm_from>option:selected").val() + "']").prop('selected', true);
        $("#graph_srch_input_date_to").val($("#srch_input_date_to").val());
        $("#graph_srch_input_hh_to>option[value='" + $("#srch_input_hh_to>option:selected").val() + "']").prop('selected', true);
        $("#graph_srch_input_mm_to>option[value='" + $("#srch_input_mm_to>option:selected").val() + "']").prop('selected', true);
        $("input[name='graph_srch_date_type'][value='" + $("input[name='srch_date_type']:checked").val() + "']").prop('checked', true);
        graphColumnsData.splice(0, graphColumnsData.length);
        $("#graph-column-filters>div.row").remove();
        $("#graph-column-filters").hide();
        $("#sample_filter_column").html('');

        drawGraphColumnsAll();

        $('#fieldset_myChart').hide();

        $(document).on('change', '#graphColumnsAll', function () {
            if ($(this).prop('checked')) {
                $('input[name="graphColumns"]').prop('checked', true).prop('disabled', true);
                $('input[name="graphColumns"]').parent().addClass('state-disabled');
            } else {
                $('input[name="graphColumns"]').prop('checked', false).prop('disabled', false);
                $('input[name="graphColumns"]').parent().removeClass('state-disabled');
            }
        });
        $(document).on('change', '#columnYAll', function () {
            if ($(this).prop('checked')) {
                $('input[name="columnY"]').prop('checked', true).prop('disabled', true);
                $('input[name="columnY"]').parent().addClass('state-disabled');
            } else {
                $('input[name="columnY"]').prop('checked', false).prop('disabled', false);
                $('input[name="columnY"]').parent().removeClass('state-disabled');
            }
        });

        $(document).on('change', 'input[name="graph_srch_date_type"]', function () {
            var radioValue = $(this).val();
            if (radioValue === "DAY") {
                $("#graph_srch_input_date_from").val(moment().format("YYYY-MM-DD"));
                $("#graph_srch_input_date_to").val(moment().format("YYYY-MM-DD"));
            } else if (radioValue === "WEEK") {
                $("#graph_srch_input_date_from").val(moment().subtract('days', 7).format("YYYY-MM-DD"));
                $("#graph_srch_input_date_to").val(moment().format("YYYY-MM-DD"));
            } else if (radioValue === "MONTH") {
                $("#graph_srch_input_date_from").val(moment().subtract('months', 1).format("YYYY-MM-DD"));
                $("#graph_srch_input_date_to").val(moment().format("YYYY-MM-DD"));
            }
        });

        $(document).on('change', 'select[id^=graph_filter_type]', function () {
            var clickedRow = $(this).parent().parent();
            var trNo = clickedRow.index();
            var value = $.trim(this.value);
            clickedRow.find("#graph_filter_input").val("");
            //조건이 is null 또는 is not null 이면 검색어 disabled
            if (value === "ISNULL" || value === "NOTNULL") {
                clickedRow.find("#graph_filter_input").prop("disabled", true);
            } else {
                clickedRow.find("#graph_filter_input").prop("disabled", false);
            }
        });

    };
    let graphColumnsData = [];
    let setGraphColumn = function () {
        $('#fieldset_myChart').hide();
        if ($("#graph_srch_format>option:selected").val() === "") {
            return;
        }
        if ($("#graph_srch_interval>option:selected").val() === "") {
            return;
        }

        // 		AotAjax.getSelectOptions('#nodeName', "${CONTEXT_PATH}/stcs/var/getNodeNameListBySvrId.do", {
        // 			svrId : 'PCF01'
        // 		}, {
        // 			autoResultFunctionTF : false,
        // 			value : 'node_name',
        // 			label : 'node_name'
        // 		});

        if (graphColumnsData.length === 0) {
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_COLUMN_LIST",
                srch_index_type: $("#graph_srch_index_type").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                if (response.ColModel) {
                    graphColumnsData = response.ColModel;
                } else {
                    graphColumnsData.splice(0, graphColumnsData.length);
                }
                drawGraphColumnsAll();
            });
        } else {
            drawGraphColumnsAll();
        }
    };
    let drawGraphColumnsAll = function () {
        $('#columnX').html('');
        $('#columnX').append('<option value="">선택</option>');
        $.each(graphColumnsData, function (index, value) {
            $('#columnX').append('<option value="' + value.index + '">' + value.display_name + '</option>');
        });
        $('#graphType').trigger('change');

        drawGraphColumns();

        $('section[data-id="columnY"]').html('');
        AotHandlebars.drawDynamicHtml($('section[data-id="columnY"]').eq(0), 'append', 'templateCheckboxToogleColumnY', {
            name: '전체선택'
        });
        i = 1;
        $.each(graphColumnsData, function (index, value) {
            if (value.align === 'right') {
                AotHandlebars.drawDynamicHtml($('section[data-id="columnY"]').eq((i) % 5), 'append', 'templateCheckboxColumnY', value);
                i++;
            }
        });
        changeTgtColType();
    };

    let drawGraphColumns = function () {
        $('section[data-id="graphColumns"]').html('');
        let i = 0;
        $.each(graphColumnsData, function (index, value) {
            if ($('#tgt_col_type>option:selected').val() === 'COLNAME' && value.align === 'right') {
                AotHandlebars.drawDynamicHtml($('section[data-id="graphColumns"]').eq((i) % 5), 'append', 'templateCheckboxColumns', value);
                i++;
            } else if ($('#tgt_col_type>option:selected').val() === 'COLVAL' && value.align !== 'right') {
                AotHandlebars.drawDynamicHtml($('section[data-id="graphColumns"]').eq((i) % 5), 'append', 'templateCheckboxColumns', value);
                i++;
            }
        });
        if (graphColumnsData.length > 0) {
            $('#div_graphColumns').show();
        } else {
            $('#div_graphColumns').hide();
        }
    };

    let changeTgtColType = function () {
        drawGraphColumns();
        if ($('#tgt_col_type>option:selected').val() === 'COLNAME') {
            $('section[data-id="graphColumns"]').each(function (index, value) {
                $(value).html($(value).html().split('radio').join('checkbox'));
            });
            $('#div_graphColumnY').hide();
            $('#div_graphColumnY input').prop('checked', false);
        } else if ($('#tgt_col_type>option:selected').val() === 'COLVAL') {
            $('section[data-id="graphColumns"]').each(function (index, value) {
                $(value).html($(value).html().split('checkbox').join('radio'));
            });
            $('#div_graphColumnY').show();
        }
    };
    let changeGraphType = function () {
        let selectedGraphType = $('#graphType>option:selected').val();
        if (selectedGraphType === 'LINE') {
            $('#columnX').prop('disabled', false);
            $('#columnX').parent('label.select').removeClass('state-disabled');
            $('#columnX>option[value="StartTime"]').prop('selected', true);
        } else if (selectedGraphType === 'BAR') {
            $('#columnX').prop('disabled', false);
            $('#columnX').parent('label.select').removeClass('state-disabled');
            $('#columnX>option[value="StartTime"]').prop('selected', true);
        } else if (selectedGraphType === 'PIE') {
            $('#columnX').prop('disabled', true);
            $('#columnX').parent('label.select').addClass('state-disabled');
            $('#columnX>option[value=""]').prop('selected', true);
        } else if (selectedGraphType === 'POLAR_AREA') {
            $('#columnX').prop('disabled', true);
            $('#columnX').parent('label.select').addClass('state-disabled');
            $('#columnX>option[value=""]').prop('selected', true);
        }
        $('input[name="graphColumns"][value="PolicyServer"]').prop('checked', true);
    };
    const global_graphColr = ["#3e95cd", "#8A2BE2", "#7FFF00", "#6495ED", "#DC143C", "#008B8B", "#006400", "#FF8C00", "#2F4F4F", "#FF00FF", "#FFD700", "#ADFF2F", "#20B2AA"];
    let datasets = [];
    let labels;
    let makeGraph = function () {
        if ($("#graph_srch_interval>option:selected").val() === "" || $("#graph_srch_format>option:selected").val() === "") {
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.VAR.STATS.INDEX_TYPE.REQUIRED'/>");
            return;
        }
        if ($('input[name="graphColumns"]:checked').length === 0) {
            AotSmartAdmin.smallBoxWarning("대상 컬럼을 선택해주세요.");
            return;
        }
        if ($('#tgt_col_type>option:selected').val() === 'COLVAL' && $('input[name="columnY"]:checked').length === 0) {
            AotSmartAdmin.smallBoxWarning("Y축 컬럼을 선택해주세요.");
            return;
        }

        let selectedGraphType = $('#graphType>option:selected').val();
        let chartType;
        let options;
        let graphColumns = [];
        let columnYs = [];
        $('input[name="graphColumns"]').each(function (index, value) {
            if ($(this).prop('checked')) {
                graphColumns.push($(this).val());
            }
        });
        $('input[name="columnY"]').each(function (index, value) {
            if ($(this).prop('checked')) {
                columnYs.push($(this).val());
            }
        });
        conditionGatherGraph();
        AotAjax.excute('${CONTEXT_PATH}/stcs/var/getChartData.do?v=' + moment().valueOf(), {
            srch_index_type: $("#graph_srch_index_type").val(),
            srch_es_base_url: $("#graph_srch_es_base_url").val(),
            srch_out_col_ids: $("#graph_srch_out_col_ids").val(),
            srch_out_col_names: $("#graph_srch_out_col_names").val(),
            srch_out_col_types: $("#graph_srch_out_col_types").val(),
            srch_def_col_id: $("#graph_srch_def_col_id").val(),
            srch_date_from: $("#graph_srch_input_date_from").val() + " " + $("#graph_srch_input_hh_from").val() + ":" + $("#graph_srch_input_mm_from").val(),
            srch_date_to: $("#graph_srch_input_date_to").val() + " " + $("#graph_srch_input_hh_to").val() + ":" + $("#graph_srch_input_mm_to").val(),
            srch_text_prefix: $("#graph_srch_text_prefix").val(),
            srch_filter_column: $("#graph_srch_filter_column").val(),
            srch_filter_type: $("#graph_srch_filter_type").val(),
            srch_filter_input: $("#graph_srch_filter_input").val(),
            strXColumnId: $('#columnX>option:selected').val(),
            strTgtType: $('#tgt_col_type>option:selected').val(),
            strTgtColumnIdList: _.join(graphColumns, ','),
            strYColumnIdList: _.join(columnYs, ','),
            strGraphType: selectedGraphType
        }, {
            autoResultFunctionTF: false
        }).done(function (response) {
            datasets.splice(0, datasets.length);
            try {
                chartJsObject.destroy();
            } catch (e) {
            }
            delete chartJsObject;
            $('#fieldset_myChart').show();
            if (selectedGraphType === 'LINE') {
                chartType = 'line';
                for (var key in response) {
                    labels = response[key].xvalList;
                    break;
                }
                options = {
                    animation: {
                        duration: 0
                    },
                    title: {
                        display: false
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    },
                    maintainAspectRatio: false
                };
                for (var key in response) {
                    datasets.push({
                        data: response[key].yvalList,
                        backgroundColor: global_graphColr[datasets.length % global_graphColr.length],
                        borderColor: global_graphColr[datasets.length % global_graphColr.length],
                        borderWidth: 1,
                        pointRadius: 2,
                        // 					continuity : 1,
                        lineTension: 0,
                        label: response[key].tgt_name,
                        fill: false
                    });
                }
            } else if (selectedGraphType === 'BAR') {
                chartType = 'bar';
                for (var key in response) {
                    labels = response[key].xvalList;
                    break;
                }
                options = {
                    animation: {
                        duration: 0
                    },
                    title: {
                        display: false
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    },
                    maintainAspectRatio: false
                };
                for (var key in response) {
                    datasets.push({
                        data: response[key].yvalList,
                        backgroundColor: global_graphColr[datasets.length % global_graphColr.length],
                        borderColor: global_graphColr[datasets.length % global_graphColr.length],
                        borderWidth: 1,
                        pointRadius: 2,
                        // 					continuity : 1,
                        lineTension: 0,
                        label: response[key].tgt_name,
                        fill: false
                    });
                }
            } else if (selectedGraphType === 'PIE') {
                chartType = 'pie';

                labels = [];
                for (var key in response) {
                    labels.push(response[key].tgt_name);
                }
                options = {
                    responsive: true
                };
                let data = [];
                let backgroundColor = [];
                let borderColor = [];
                for (var key in response) {
                    data.push(response[key].yvalList);
                    backgroundColor.push(global_graphColr[data.length % global_graphColr.length]);
                    borderColor.push(global_graphColr[data.length % global_graphColr.length]);
                }
                datasets.push({
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor
                });
            } else if (selectedGraphType === 'POLAR_AREA') {
                chartType = 'polarArea';

                labels = [];
                for (var key in response) {
                    labels.push(response[key].tgt_name);
                }
                options = {
                    responsive: true,
                    scale: {
                        ticks: {
                            beginAtZero: true
                        },
                        reverse: false
                    }
                };
                let data = [];
                let backgroundColor = [];
                let borderColor = [];
                for (var key in response) {
                    data.push(response[key].yvalList);
                    backgroundColor.push(global_graphColr[data.length % global_graphColr.length]);
                    borderColor.push(global_graphColr[data.length % global_graphColr.length]);
                }
                datasets.push({
                    data: data,
                    backgroundColor: backgroundColor,
                    borderColor: borderColor
                });
            }

            // 			if (selectedGraphType === 'POLAR_AREA') {
            // 				chartJsObject = Chart.PolarArea(document.getElementById('myChart'), {
            // 					data : {
            // 						labels : labels,
            // 						datasets : datasets
            // 					},
            // 					options : options
            // 				});
            // 			} else {
            chartJsObject = new Chart($('#myChart')[0].getContext('2d'), {
                type: chartType,
                data: {
                    labels: labels,
                    datasets: datasets
                },
                options: options
            });
            // 			}
        });
    };

    let graphStatsGetDefInfo = function () {
        //둘다 값이 있을 때만
        if ($("#graph_srch_interval>option:selected").val() !== "" && $("#graph_srch_format>option:selected").val() !== "") {
            graphColumnsData.splice(0, graphColumnsData.length);
            $("#graph_srch_index_type").val($("#graph_srch_format>option:selected").val() + "-" + $("#graph_srch_interval>option:selected").val());
            /**************마스터 기본 값 취득**************/
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_DEFAULT_LIST",
                srch_index_type: $("#graph_srch_index_type").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                //첫번쨰 줄 제외하고 모두 지우기
                $("#graph-column-filters>div.row").remove();
                $("#graph-column-filters").hide;

                //기본값 세팅
                $("#graph_srch_es_base_url").val(response.srch_es_base_url);
                $("#graph_srch_out_col_ids").val(response.srch_out_col_ids);
                $("#graph_srch_out_col_names").val(response.srch_out_col_names);
                $("#graph_srch_out_col_types").val(response.srch_out_col_types);
                $("#graph_srch_def_col_id").val(response.srch_def_col_id);
                $("#sample_filter_column").html('');
                $("#sample_filter_column").append(response.filter);
            }).done(function (response) {
                if ($("#graph_srch_interval>option:selected").val() === $("#srch_interval>option:selected").val()

                    && $("#graph_srch_format>option:selected").val() === $("#srch_format>option:selected").val()) {
                    $('#column-filters div.row').each(function (index, value) {
                        //AND OR 조건
                        let condition = $.trim($(value).children('section:eq(0)').text());
                        $.when().done(function () {
                            addRowGraph('AND');
                        }).done(function () {
                            $('#graph-column-filters div.row:eq(' + index + ')>section:eq(0)').html('<i class="fa fa-chain"></i>' + condition);
                            //검색 컬럼
                            $('#graph-column-filters div.row:eq(' + index + ') select[name="graph_filter_column"]>option[value="'

                                + $(value).find('select[name="filter_column"]>option:selected').val() + '"]').prop('selected', true);

                            //검색 조건
                            $('#graph-column-filters div.row:eq(' + index + ') select[name="graph_filter_type"]>option[value="'

                                + $(value).find('select[name="filter_type"]>option:selected').val() + '"]').prop('selected', true);

                            //검색어
                            $('#graph-column-filters div.row:eq(' + index + ') input[name="graph_filter_input"]').val($(value).find('input[name="filter_input"]').val());
                        });
                    });
                }
            });
            setGraphColumn();
        }
    };

    let staticGraphRowNum = 0;
    //AND조건 로우 추가
    let addRowGraph = function (type, rowNum) {
        if ($("#graph_srch_interval>option:selected").val() === "" || $("#graph_srch_format>option:selected").val() === "") {
            AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.VAR.STATS.INDEX_TYPE.REQUIRED'/>");
            return;
        }

        $('#graph-column-filters').show();
        let data = {
            type: type,
            rowNum: staticGraphRowNum++
        };
        if (type === 'AND') {
            AotHandlebars.drawDynamicHtml($('#graph-column-filters'), 'append', 'templateColumnFiltersGraph', data).done(function () {
                $('select[name="graph_filter_column"]').each(function (index, value) {
                    if ($(this).html() === '') {
                        $(this).html($('#sample_filter_column').html());
                        $('select[name="graph_filter_type"]:eq(' + index + ')').html($('#sample_filter_type').html());
                    }
                });
            });
        } else {
            AotHandlebars.drawDynamicHtml($('#graph-column-filters>div.row[data-row-num-graph="' + rowNum + '"]'), 'after', 'templateColumnFiltersGraph', data).done(function () {
                $('select[name="graph_filter_column"]').each(function (index, value) {
                    if ($(this).html() === '') {
                        $(this).html($('#sample_filter_column').html());
                        $('select[name="graph_filter_type"]:eq(' + index + ')').html($('#sample_filter_type').html());
                    }
                });
            });
        }
    };
    let deleteRowGraph = function (rowNum) {
        $('div[data-row-num-graph="' + (+rowNum) + '"]').remove();
        if ($('#graph-column-filters>div.row').length === 0) {
            $('#graph-column-filters').hide();
        }
    };
    //각 로우별 검색조건 취합
    let conditionGatherGraph = function () {
        let textPrefix = [];
        let column = [];
        let type = [];
        let input = [];
        $('#graph-column-filters div.row').each(function () {
            //AND OR 조건
            textPrefix.push($.trim($(this).children('section:eq(0)').text()));
            //검색 컬럼
            column.push($.trim($(this).find('select[name="graph_filter_column"]>option:selected').val()));
            //검색 조건
            type.push($.trim($(this).find('select[name="graph_filter_type"]>option:selected').val()));
            //검색어
            input.push($.trim($(this).find('input[name="graph_filter_input"]').val()));
        });
        $("#graph_srch_text_prefix").val(textPrefix.join());
        $("#graph_srch_filter_column").val(column.join());
        $("#graph_srch_filter_type").val(type.join());
        $("#graph_srch_filter_input").val(input.join());
    }
</script>
<script id="templateCheckboxColumns" type="text/x-handlebars-template">
    <label class="radio">
        <input type="radio" name="graphColumns" value="{{index}}">
        <i></i>
        {{display_name}}
    </label>
</script>
<script id="templateCheckboxToogleColumnY" type="text/x-handlebars-template">
    <label class="toggle">
        <input type="checkbox" id="columnYAll">
        <i data-swchon-text="ON" data-swchoff-text="OFF"></i>
        {{name}}
    </label>
</script>
<script id="templateCheckboxColumnY" type="text/x-handlebars-template">
    <label class="checkbox">
        <input type="checkbox" name="columnY" value="{{index}}">
        <i></i>
        {{display_name}}
    </label>
</script>
<script id="templateColumnFiltersGraph" type="text/x-handlebars-template">
    <div class="row" data-row-num-graph="{{rowNum}}">
        <section class="col col-1">
            <i class="fa fa-chain"></i>
            {{type}}
        </section>
        <section class="col col-2">
            <label class="select">
                <select name="graph_filter_column" id="graph_filter_column"/>
                <i></i>
            </label>
        </section>
        <section class="col col-2">
            <label class="select">
                <select name="graph_filter_type" id="graph_filter_type"/>
                <i></i>
            </label>
        </section>
        <section class="col col-4">
            <label class="input">
                <input type="text" name="graph_filter_input" id="graph_filter_input">
            </label>
        </section>
        <section class="col col-3">
            <div class="btn-group btn-group-justified" role="group" aria-label="...">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-warning btn-sm" onclick="addRowGraph('OR', '{{rowNum}}')">
                        OR
                        <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                    </button>
                </div>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-danger btn-sm" onclick="deleteRowGraph('{{rowNum}}')">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>
            </div>
        </section>
    </div>
</script>
