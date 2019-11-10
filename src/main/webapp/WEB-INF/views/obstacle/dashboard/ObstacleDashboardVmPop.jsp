<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<c:set var="ALL">
    <spring:message code="TEXT.COMM.SEL.ALL"/>
</c:set>
<aot:html title="${TITLE}" chartjs="YES">
    <style>
        .smart-form .inline-group .checkbox {
            margin-right: 0;
        }

        .smart-form .btn span {
            font-size: 24px;
        }

        .jarviswidget {
            margin-bottom: 10px;
        }

        #commVmStatus button {
            cursor: default;
            margin-top: 7px;
            width: 98%;
            padding: 3px;
        }

        div.widget-toolbar > div.btn-group > label.btn {
            cursor: default;
        }

        #myChart {
            padding-top: 10px;
        }

        #myChart div.col-xs-4 {
            padding: 0 3px;
        }

        #myChart canvas {
            width: 100%;
            height: 200px;
        }

        #myChart .panel-body, #myChart .panel-footer {
            margin: 0;
            padding: 0;
        }

        button.status-active {
            border: solid 8px #32CD32;
        }

        button.status-standby {
            border: solid 8px #696969;
        }

        #emsmHwConf div.col-xs-1 {
            padding: 0 3px;
        }
    </style>
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/obstacle/dashboard.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="actionID">
        <!-- 	장애알람 ON/OFF 체크 값 -->
        <input type="hidden" name="soundOnOff" value="">
        <input type="hidden" name="srchErrorLevel" value="">
            <%-- 		<input type="hidden" name="srch_index_type" id="srch_index_type" value="${SEARCH_DATA.srch_index_type}" /> --%>
    </form>

    <div id="panel" role="main">
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
            <!-- 			<div class="row"> -->
            <!-- 				상단화면명 -->
            <!-- 				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4"> -->
            <!-- 					<h1 class="page-title txt-color-blueDark" style="margin: 12px 0 20px;"> -->
            <!-- 						PAGE HEADER -->
            <!-- 						<i class="fa-fw fa fa-pencil-square-o"></i> -->
                <%-- 						${curr_menu_name} --%>
            <!-- 					</h1> -->
            <!-- 				</div> -->
            <!-- 			</div> -->
            <!-- 상단화면명 END -->

            <section id="widget-grid">
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="getCommVmStatus();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <h2>${VM_HW_NAME }</h2>
                                <span class="jarviswidget-loader" style="display: none;">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <div role="content" style="display: block;">
                                <div id="emsmHwConf" class="widget-body no-padding" style="min-height: 30px;"></div>
                            </div>
                        </div>
                    </article>
                </div>
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="drawChart();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <h2>
                                    <i>Real time Graph</i>
                                </h2>
                                <span class="jarviswidget-loader" style="display: none;">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <div role="content" style="display: block;">
                                <div class="widget-body no-padding">
                                    <div class="col col-xs-12" id="myChart">
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_CPU"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_PCFS"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_RX"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_MEM"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_MRAB"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-xs-4">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <canvas id="chart_TOT"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
            </section>
        </div>
        <!-- content -->

        <aot:select name="gd_alm_status" id="gd_alm_status" group="ALM_STATUS" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
            <%-- 		<aot:select name="gd_alm_host" id="gd_alm_host" group="ALM_HOST" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select> --%>

        <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" group="W001" init="YES"></aot:select>
        <aot:select name="select_alm_level" id="select_alm_level" style="display:none;" group="ALM_LEVEL" init="YES"></aot:select>

    </div>
    <!-- main -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript" data-for="ready">
        //타이머 선택 값
        var selTimerVal = "";
        //타이머
        var timer;
        var refreshTimer = 10000;

        var doSearchAlarmCnt = 0;
        var drawLevelNumber = 0;
        var global_rtgrpNodeList;
        var global_graphColr = ["#3e95cd", "#8A2BE2", "#7FFF00", "#6495ED", "#DC143C", "#008B8B", "#006400", "#FF8C00", "#2F4F4F", "#FF00FF", "#FFD700", "#ADFF2F", "#20B2AA"];

        $(document).ready(function () {
            getEmsmHwConf();
            setInterval(function () {
                getEmsmHwConf();
            }, refreshTimer);
            //갱신주기 콤보

            AotAjax.excute('/obstacle/getRtgrpNodeList.json', {}).done(function (response) {
                global_rtgrpNodeList = response;
            }).done(function (response) {
                setTimeout(function () {
                    drawChart();
                }, 500);
            });
        });
    </script>
    <script>
        function getEmsmHwConf() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/dashboard.do", {
                actionID: "getEmsmHwConf"
            }).done(function (response) {
                $('#emsmHwConf').html('');
                for (var i = 0; i < response.length; i++) {
                    $('#emsmHwConf').append('<div class="col-xs-12" style="margin:5px 0;"></div>');
                    getEmsmVmStatusBySvrIdAndHwName(i, response[i].hw_name);
                }
            });
        }

        function getEmsmVmStatusBySvrIdAndHwName(index, hw_name) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/dashboard.do", {
                actionID: "getEmsmVmStatusBySvrIdAndHwName",
                hw_name: hw_name
            }).done(function (response) {
                var data = {
                    hw_name: hw_name,
                    list: response
                };
                AotHandlebars.drawDynamicHtml($('#emsmHwConf>div.col-xs-12').eq(index), 'html', 'templateCommVmStatus', data).done(function () {
                    $('#emsmHwConf>div.col-xs-12').eq(index).find('button').each(function (index, value) {
                        if ($(this).data('ha-status') === 'S') {
                            $(this).addClass('status-standby');
                        } else if ($(this).data('ha-status') === 'A') {
                            $(this).addClass('status-active');
                        }
                        if ($(this).data('alm-status') === 'NOR') {
                            $(this).css('background-color', '#fff');
                        } else if ($(this).data('alm-status') === 'MIN') {
                            $(this).css('background-color', '#faed7d');
                        } else if ($(this).data('alm-status') === 'MAJ') {
                            $(this).css('background-color', '#ffbb00');
                        } else if ($(this).data('alm-status') === 'CRI') {
                            $(this).css('background-color', '#a90329');
                        }
                    });
                }).done(function () {
                    $('#emsmHwConf').parent().css({
                        'min-height': $('#emsmHwConf>div.col-xs-12').length * 56,
                        'height': $('#emsmHwConf>div.col-xs-12').length * 56
                    });
                });
            });
        }

        var chart_CPU;
        var chart_PCFS;
        var chart_RX;
        var chart_MEM;
        var chart_MRAB;
        var chart_TOT;

        function drawChart() {
            setInterval(function () {
                AotAjax.excute('/obstacle/getEmsmRealtimeGraphByResType.json', {
                    svr_id: 'PCF01'
                }).done(function (response) {
                    for (var key in response) {
                        AotChartjs.moveChart(window['chart_' + key], moment().format('mm:ss'), response[key]);
                    }
                });
            }, refreshTimer);

            var datasets = {};
            datasets['CPU'] = [];
            datasets['PCFS'] = [];
            datasets['RX'] = [];
            datasets['MEM'] = [];
            datasets['MRAB'] = [];
            datasets['TOT'] = [];
            $.each(global_rtgrpNodeList, function (index, value) {
                datasets[value.res_type].push({
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                    backgroundColor: global_graphColr[datasets[value.res_type].length],
                    borderColor: global_graphColr[datasets[value.res_type].length],
                    borderWidth: 1,
                    pointRadius: 2,
                    // 					continuity : 1,
                    lineTension: 0,
                    label: value.rtgrp_node,
                    fill: false
                });
            });

            chart_CPU = new Chart($('#chart_CPU')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['CPU']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'CPU Usage(%)'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                max: 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            chart_PCFS = new Chart($('#chart_PCFS')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['PCFS']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'Active Session'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                // max : 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            chart_RX = new Chart($('#chart_RX')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['RX']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'Rx TPS'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                // max : 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            chart_MEM = new Chart($('#chart_MEM')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['MEM']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'Memory Usage(%)'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                max: 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            chart_MRAB = new Chart($('#chart_MRAB')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['MRAB']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'MRA Binding'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                // max : 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
            chart_TOT = new Chart($('#chart_TOT')[0].getContext('2d'), {
                type: 'line',
                data: {
                    labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    datasets: datasets['TOT']
                },
                options: {
                    animation: {
                        duration: 0
                    },
                    legend: {
                        position: "right",
                        labels: {
                            boxWidth: 10,
                            fontSize: 8,
                            padding: 4
                        }
                    },
                    title: {
                        display: true,
                        text: 'Total TPS'
                    },
                    scales: {
                        xAxes: [{
                            display: false,
                            scaleLabel: {
                                display: false
                            }
                        }],
                        yAxes: [{
                            display: true,
                            ticks: {
                                beginAtZero: true,
                                // max : 100
                            }
                        }]
                    },
                    maintainAspectRatio: false
                }
            });
        }
    </script>
    </body>
    <script id="templateCommVmStatus" type="text/x-handlebars-template">
        <div class="col col-xs-2" style="line-height: 9px;">
            {{hw_name}}&nbsp;<img src="${CONTEXT_PATH}/resources/images/hw.png" style="width:100%;height:36px;">
        </div>
        {{#each list}}
        <div class="col col-xs-1">
            <div class="btn-group btn-group-justified" role="group" aria-label="...">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default btn-sm" data-ha-status="{{ha_status}}" data-alm-status="{{alm_status}}">{{node_name}}</button>
                </div>
            </div>
        </div>
        {{/each}}
    </script>
</aot:html>
