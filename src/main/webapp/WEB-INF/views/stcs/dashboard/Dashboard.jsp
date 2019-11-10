<%----------------------------------------------------------------------------------------
 - 파일이름	: stcs/dashboard/Dashboard.jsp
 - 설      명	: Dashboard 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.26    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" chart="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>
        .bg-color-gray {
            background-color: #f3f3f3;
        }

        .well[class*=" bg-"], .well[class^=bg-] {
            border: 1px solid #ddd !important
        }

        code {
            padding: 2px 4px;
            font-size: 100%;
            border-radius: 2px;
            color: inherit;
            background-color: #f3f3f3;
        }

        .group-title {
            float: left;
            min-height: 1px;
            padding-left: 13px;
            padding-right: 13px;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .group-title-x-val {
            font-size: 25px;
            font-weight: 500;
            margin: 5px 0 5px 0;
        }

        .group-graph {
            float: left;
            min-height: 1px;
            padding-left: 13px;
            padding-right: 13px;
            margin-top: 14px;
        }

        /* 차트 세개씩은 width:547px;*/
        /* 차트 두개씩은 width:821px */
        .group-img {
            float: left;
            min-height: 1px;
            padding-left: 13px;
            padding-right: 13px;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        /* 이미지 크기 width:581;height:184 이미지크기임. */
        /* 차트 범례 스타일 */
        .legendColorBox {
            padding-left: 10px;
            vertical-align: top;
            padding-top: 9px;
        }
    </style>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/stcs/dashboard.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="srch_index_type" id="srch_index_type" value="${SEARCH_DATA.srch_index_type}"/>
    </form>

    <div id="${type}" role="main">
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
                            ${curr_menu_name}
                    </h1>
                </div>
            </div>
            <!-- 상단화면명 END -->

            <!-- 대시보드 그룹 목록 -->
            <c:if test="${!empty DASHBOARD_GRP_LIST}">
                <c:forEach var="item" items="${DASHBOARD_GRP_LIST}" begin="0" step="1" varStatus="status">
                    <section id="widget-grid" class="">
                        <div class="row">
                            <article class="col-sm-12">
                                <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                     data-widget-deletebutton="false"
                                     data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">
                                    <header>
                                        <h2>
                                            <i> ${item.dsbd_group_name}</i>
                                        </h2>
                                    </header>
                                    <div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div id="${item.dsbd_group_id}" class="row" style="margin-left: -2px;"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </section>
                </c:forEach>
            </c:if>
            <c:if test="${empty DASHBOARD_GRP_LIST}">
                <section id="widget-grid" class="">
                    <div class="row">
                        <article class="col-sm-12">
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                 data-widget-deletebutton="false"
                                 data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">
                                <header>
                                    <h2>
                                        <strong>CPU </strong>
                                        <i> Usage Statistics</i>
                                    </h2>
                                </header>
                                <div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div id="nogroup" class="row" style="margin-left: -2px;"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </article>
                    </div>
                </section>
            </c:if>
        </div>
        <!-- content -->

    </div>
    <!-- main -->


    <link rel="stylesheet" type="text/css" media="screen" href="${PATH_PUBLISH}/css/dashboard_style.css">
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        //타이머
        var timer;
        //타이머 선택 값
        var selTimerVal = "60000"; //1분   //"1000";
        var dashboardItemGetCnt = 0;

        var grp_list = [];
        var fnObj = {
            //알람 레벨 레이아웃 그리기
            title_template: {
                keywords: [
                    "[title_width]"
                    , "[title_height]"
                    , "[title_xval]"
                    , "[title_data_name]"
                ],
                layout:
                    "<div class='col group-title' style='width:[title_width]px;height:[title_height]px;'>" +
                    "<div class='well well-sm bg-color-gray txt-color-blueDark text-center'>" +
                    "<h5 class='group-title-x-val'>[title_xval]</h5>" +
                    "<code>" +
                    "[title_data_name]" +
                    "</code>" +
                    "</div>" +
                    "</div>"
                , deleteKeywords: function (str) {
                    var res = str;
                    $.each(fnObj.title_template.keywords, function (i, o) {
                        res = res.replace(o, ' ');
                    });
                    return res;
                }
            },
            graph_template: {
                keywords: [
                    "[chart_width]"
                    , "[chart_item_name]"
                    , "[legend_chart_id]"
                    , "[chart_id]"
                    , "[chart_height]"
                ],
                layout:
                    "<div class='panel2 group-graph' style='width:[chart_width]px;'>" +
                    "<div class='panel-heading'>" +
                    "<span class='panel-title'> [chart_item_name]</span>" +
                    "<span id=[legend_chart_id] class='panel-title' style='float:right;'></span>" +
                    "</div>" +
                    "<div class='panel-body bg-light dark'>" +
                    "<div class=[chart_id] style='height: [chart_height]px; width: 100%;'></div>" +
                    "</div>" +
                    "</div>"
                , deleteKeywords: function (str) {
                    var res = str;
                    $.each(fnObj.graph_template.keywords, function (i, o) {
                        res = res.replace(o, ' ');
                    });
                    return res;
                }
            },
            img_template: {
                keywords: [
                    "[img_xval]"
                    , "[img_width]"
                    , "[img_height]"
                ],
                layout:
                    "<div class='col group-img'>" +
                    "<img src='/resources/publish[img_xval]' width='[img_width]' height='[img_height]'>" +
                    "</div>"
                , deleteKeywords: function (str) {
                    var res = str;
                    $.each(fnObj.img_template.keywords, function (i, o) {
                        res = res.replace(o, ' ');
                    });
                    return res;
                }
            }
        };
        $(document).ready(function () {
            <c:if test="${!empty DASHBOARD_GRP_LIST}">
            <c:forEach var="item" items="${DASHBOARD_GRP_LIST}" begin="0" step="1" varStatus="status">
            grp_list.push('${item.dsbd_group_id}');
            </c:forEach>
            </c:if>

            //차트 초기화
            FlotCharts.init();

            dashboardItemGet("");
        });

        //타이머설정
        function setTimer(updateTimer) {
            clearInterval(timer);
            timer = setInterval(function () {
                dashboardItemGet("re");
            }, updateTimer);
        }

        function dashboardItemGet(times) {
            AotAjax.excute("${CONTEXT_PATH}/stcs/dashboard.do", {
                actionID: "DASHBOARD_GRP_LIST",
                srch_index_type: $("#srch_index_type").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var item_list = response.itemList;
                var data_list = response.dataList;
                //그룹별 그리기
                draw(grp_list, item_list, data_list, times);
            }).fail(function () {
                dashboardItemGetCnt = dashboardItemGetCnt + 1;
                if (dashboardItemGetCnt > 3) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.ALERT.PROCESS.ERROR"/>');
                    dashboardItemGetCnt = 0;
                }
            });
        }

        //각 그룹별 아이템 html 그리기
        function draw(grp_list, item_list, data_list, times) {

            if (grp_list.length > 0) {
                for (var g = 0; g < grp_list.length; g++) {
                    //그룹아이디취득
                    var group_id = grp_list[g];
                    //그룹아이디에 해당하는 아이템 목록
                    var get_item_list = item_list[group_id];
                    if (get_item_list.length > 0) {
                        //아이템들 html 모을 리스트
                        var item_html_list = [];
                        for (var i = 0; i < get_item_list.length; i++) {
                            var item_type = get_item_list[i].dsbd_item_type;
                            var item_id = get_item_list[i].dsbd_item_id;
                            if (item_type === "TITLE") {
                                //html읽어들이기
                                var title_html = fnObj.title_template.layout;
                                title_html = title_html.replace("[title_width]", get_item_list[i].dsbd_item_width);
                                title_html = title_html.replace("[title_height]", get_item_list[i].dsbd_item_height);
                                title_html = title_html.replace("[title_xval]", data_list[item_id][0].xval);
                                title_html = title_html.replace("[title_data_name]", data_list[item_id][0].data_name);
                                title_html = fnObj.title_template.deleteKeywords(title_html);
                                item_html_list.push(title_html);
                            } else if (item_type === "LINEGRAPH" || item_type === "BARGRAPH") {
                                var graph_html = fnObj.graph_template.layout;
                                graph_html = graph_html.replace("[chart_width]", get_item_list[i].dsbd_item_width);
                                graph_html = graph_html.replace("[chart_item_name]", get_item_list[i].dsbd_item_name);
                                //kym 20180326 수정 범례 아이디 값 수정
                                graph_html = graph_html.replace("[legend_chart_id]", "chart_legend_" + item_id);
                                graph_html = graph_html.replace("[chart_id]", item_id);
                                graph_html = graph_html.replace("[chart_height]", get_item_list[i].dsbd_item_height);
                                graph_html = fnObj.graph_template.deleteKeywords(graph_html);
                                item_html_list.push(graph_html);
                            } else if (item_type === "IMAGE") {
                                //html읽어들이기
                                var img_html = fnObj.img_template.layout;
                                img_html = img_html.replace("[img_xval]", data_list[item_id][0].xval);
                                img_html = img_html.replace("[img_width]", get_item_list[i].dsbd_item_width);
                                img_html = img_html.replace("[img_height]", get_item_list[i].dsbd_item_height);
                                img_html = fnObj.img_template.deleteKeywords(img_html);
                                item_html_list.push(img_html);
                            }
                        }
                        //각 아이템들을 합쳐서 한번에 그리기
                        $("#" + group_id).html(item_html_list.join(""));
                    }
                }

                draw_graph(grp_list, item_list, data_list, times);
            }
        }

        //html 작성 후 그려야 함.
        function draw_graph(grp_list, item_list, data_list, times) {
            //차트 선 색 지정  //진파랑          연녹              청녹			주황			보라		연빨
            var colors = ["#4a89dc", "#70ca63", "#3bafda", "#f6bb42", "#967adc", "#e9573f"];

            for (var g = 0; g < grp_list.length; g++) {
                //그룹아이디취득
                var group_id = grp_list[g];
                //그룹아이디에 해당하는 아이템 목록
                var get_item_list = item_list[group_id];
                if (get_item_list.length > 0) {
                    for (var i = 0; i < get_item_list.length; i++) {
                        var item_type = get_item_list[i].dsbd_item_type;
                        var item_id = get_item_list[i].dsbd_item_id;
                        if (item_type === "LINEGRAPH") {
                            chart_line_draw("LINE", item_id, data_list[item_id], colors, times);
                        } else if (item_type === "BARGRAPH") {
                            chart_line_draw("BAR", item_id, data_list[item_id], colors, times);
                        }
                    }
                }
            }
        }

        /**
         * @param p_type        :  차트 종류("LINE", "BAR")
         * @param p_datas        :  차트 데이타 배열
         * @param p_colors        :  라인 색상
         **/
        function chart_line_draw(p_type, item_id, p_datas, p_colors, times) {
            var Grid = {
                grid: {
                    show: true,
                    aboveData: true,
                    color: "#bbbbbb",
                    labelMargin: 15,
                    axisMargin: 0,
                    borderWidth: 0,
                    borderColor: null,
                    minBorderMargin: 5,
                    clickable: true,
                    hoverable: true,
                    autoHighlight: true,
                    mouseActiveRadius: 20,
                },
                tooltip: true,
                //activate tooltip
                tooltipOpts: {
                    content: function (label, xval, yval, dataObject) {
                        //데이타상에 x축 값을 그대로 툴팁에 적용
                        var XdataIndex = dataObject.dataIndex;
                        var XdataLabel = dataObject.series.xaxis.ticks[XdataIndex].label;
                        return "<span>" + XdataLabel + "</span>" + " : <b>" + yval + "</b>";
                    },
                    shifts: {
                        /* 툴팁 위치 */
                        x: -30,
                        y: -50
                    },
                    defaultTheme: false
                },
                xaxis: {
                    //categories로 해야만 x축 데이타 그대로를 순서대로 뿌릴 수 있음.
                    mode: "categories",
                    tickLength: 0
                },
                /* yaxis: {
                    min: 0,
                    tickSize: 0.5
                }, */
                legend: {
                    noColumns: 6,
                    labelBoxBorderColor: "#fff",
                    container: $("#chart_legend_" + item_id)
                }
            };

            //FLOT CHART OBJECT 구성
            var tmp_chart_data = [];
            var chart_data = [];
            var data_obj = {};
            //이름으로 찾기 - 차트 종류별 오브젝트 구성이 다름
            if (p_type === "LINE") {
                var names = Object.getOwnPropertyNames(p_datas);
                for (var i = 0; i < names.length; i++) {
                    var grp_name = names[i];
                    data_obj.data = p_datas[grp_name];
                    data_obj.label = names[i];
                    var point = {};
                    point.show = true;
                    point.radius = 5;
                    data_obj.points = point;
                    var lines = {};
                    lines.show = true;
                    data_obj.lines = lines;
                    data_obj.color = p_colors[i];
                    chart_data.push(data_obj);
                    data_obj = {};
                }
            } else if (p_type === "BAR") {
                console.log("p_datas~", p_datas.length);
                if (p_datas.length > 0) {
                    var list_bar = [];
                    //object형태를 단순 array로 변경
                    for (var i = 0; i < p_datas.length; i++) {
                        var list = [];
                        list.push(p_datas[i].xval);
                        list.push(p_datas[i].yval);
                        list_bar.push(list)
                    }
                    data_obj.data = list_bar;
                    var bars = {};
                    bars.show = true;
                    bars.barWidth = 0.6;
                    bars.align = "center";
                    bars.fill = 1;
                    bars.fillColor = {
                        colors: [{
                            opacity: 0.8
                        }, {
                            opacity: 1
                        }]
                    };
                    data_obj.bars = bars;
                    data_obj.color = p_colors[0];
                    chart_data.push(data_obj);
                    data_obj = {};
                }
            }
            //flot chart 버전업하여 메모리 이상  증상 해결.
            var holder = $("." + item_id);
            holder.unbind();
            //holder.empty();
            holder.removeData("plot").empty();
            $.plot("." + item_id, chart_data, Grid);
            //전부 읽은 후 타이머 가동
            setTimer(selTimerVal);
        }
    </script>
    </body>
</aot:html>
