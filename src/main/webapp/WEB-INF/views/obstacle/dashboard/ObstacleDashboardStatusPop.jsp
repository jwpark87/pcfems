<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/dashboard/Dashboard.jsp
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
<c:set var="ALL">
    <spring:message code="TEXT.COMM.SEL.ALL"/>
</c:set>
<aot:html title="${TITLE}" jqGrid="YES" dataTables="YES">
    <style>
        .smart-form .inline-group .checkbox {
            margin-right: 0;
        }

        .smart-form .btn span {
            font-size: 24px;
        }

        #chkbtn {
            padding: 0 15px;
        }

        .jarviswidget {
            margin-bottom: 10px;
        }

        div.widget-toolbar > div.btn-group > label.btn {
            cursor: default;
        }

        #resTypeNGraphValue > section {
            font-size: 16px;
        }

        #resTypeNGraphValueDisk div.progress-sm {
            margin-bottom: 14px;
        }

        #resTypeNGraphValueDisk {
            padding: 8px;
        }

        #resTypeNGraphValue .col, #resTypeNGraphValueDisk .col {
            margin-bottom: 0px;
        }

        #resTypeNGraphValueDisk .col-10 {
            border: 1px solid gray;
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
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
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
            <section id="widget-grid">
                <div class="row">

                    <!-- NEW COL START -->
                    <article class="col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">

                        <!-- Widget ID (each widget will need unique ID)-->
                        <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false"
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
                            <header role="heading" class="ui-sortable-handle">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="GridReload2();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <span class="widget-icon">
									<i class="fa fa-info-circle"></i>
								</span>
                                <h2>Resources Status</h2>

                                <span class="jarviswidget-loader">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>

                            <!-- widget div-->
                            <div role="content">

                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->

                                </div>
                                <!-- end widget edit box -->

                                <!-- widget content -->
                                <div class="widget-body no-padding" style="min-height: 67px;">
                                    <form action="" class="smart-form">
                                        <fieldset style="border-color: #9cb4c5; color: #305d8c; background-color: #d6dde7; padding: 8px;">
                                            <div class="row" id="resTypeNGraphValue">
                                                <section class="col col-1">&nbsp;</section>
                                                <section class="col col-2">${node_name }</section>
                                                <section class="col col-2"></section>
                                                <section class="col col-2"></section>
                                                <section class="col col-2"></section>
                                                <section class="col col-2"></section>
                                            </div>
                                        </fieldset>

                                        <fieldset id="resTypeNGraphValueDisk" style="display: none; max-height: 154px; overflow: auto;">
                                            <div id="diskGraph1" class="col col-3"></div>
                                            <div id="diskGraph2" class="col col-3"></div>
                                            <div id="diskGraph3" class="col col-3"></div>
                                            <div id="diskGraph4" class="col col-3"></div>
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

                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div id="level-number" class="widget-toolbar" role="menu" style="float: left;">
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default bg-color-red txt-color-white">
                                            <strong>0</strong>
                                        </label>
                                        <label class="btn btn-default" style="background-color: #ffbb00; color: black;">
                                            <strong>0</strong>
                                        </label>
                                        <label class="btn btn-default" style="background-color: #faed7d; color: black">
                                            <strong>0</strong>
                                        </label>
                                        <label class="btn btn-default" style="color: black;">
                                            <strong>0</strong>
                                        </label>
                                    </div>
                                </div>
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon txt-color-white" id="checked_alm" onclick="checkedAlarm();" data-placement="bottom">
                                        <label>
                                            <i class="fa fa-check"></i>
                                            <spring:message code="TEXT.COMM.SEL.CHOICE.FIRE"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="GridReload2();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <span class="jarviswidget-loader" style="display: none;">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <div role="content" style="display: block;">
                                <div class="widget-body no-padding">
                                    <!-- 하단 알람 정보 그리드 -->
                                    <div class="col-sm-12" id="gridlist-width2">
                                        <table id="gridList2"></table>
                                        <div id="gridToolbar2"></div>
                                    </div>
                                </div>
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
                                       onclick="GridReload2();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <span class="jarviswidget-loader" style="display: none;">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <div role="content" style="display: block;">
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
        $(document).ready(function () {
            $(window).on('resize', function (e) {
                $('#gridList2').jqGrid('setGridHeight', ($(window).height() - $('#header').height() - $('#ribbon').height() - $('div.row:eq(0)').height() - 220) / 2 - 50);
                $('#table1').parent().css({
                    height: ($(window).height() - $('#header').height() - $('#ribbon').height() - $('div.row:eq(0)').height() - 220) / 2 - 50
                })
            });

            getResTypeNGraphValueByRtgrpNode();
            getEmsmVmStatusVO();
            createGrid();

            $("input[name=alm_level]").parent().removeClass('checkbox').addClass('btn btn-default');
            //검색조건 체크박스 전체 체크
            $("input[name=alm_level]:checkbox").prop("checked", true);
            jQuery("#gridList2").jqGrid({
                url: "${CONTEXT_PATH}/obstacle/getAlarmList.json",
                postData: {
                    srch_alm_instance: '${host_name}'
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
                height: 160,
                colNames: ["",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.HOST_NM'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_OCCUR_DT'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_STATUS'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_LEVEL'/>",

                    "Alarm Category",

                    "Alarm Instance",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CODE'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_MSG'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_REMARK1'/>",

                    "Alarm Recovery",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_KONW_DT'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CLEAR_DT'/>",

                    // 				"<spring:message code="TEXT.ALM_HIST.GRID.SOUND_YN"/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CONFIRM_USER'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.OCCUR_CNT'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.FIRST_OCCUR_DT'/>",

                    ""],
                colModel: [{
                    name: "seqno",
                    index: "seqno",
                    width: 230,
                    hidden: true
                },
                    /* {name:"host_nm"				,index:"host_nm"				, width:230}, */
                    {
                        // 					name : 'host_nm',
                        // 					index : 'host_nm',
                        // 					width : 100,
                        // 					align : 'left'
                        // 				}, {
                        name: "alm_occur_dt",
                        index: "alm_occur_dt",
                        width: 140,
                        align: 'center',
                        search: false
                    }, {
                        name: 'alm_status',
                        index: 'alm_status',
                        width: 100,
                        align: 'center',
                        formatter: setStatusText,
                        stype: "select",
                        searchoptions: {
                            value: AotJqGrid.getSelectTagData($("#gd_alm_status > option"))
                        }
                    }, {
                        name: 'alm_level',
                        index: 'alm_level',
                        width: 100,
                        align: 'center',
                        stype: "select",
                        searchoptions: {
                            value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                        },
                        edittype: "select",
                        editoptions: {
                            value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                        },
                        formatter: 'select'
                    }, {
                        name: "alm_group",
                        index: "alm_group",
                        width: 100,
                        search: false
                    }, {
                        name: "alm_instance",
                        index: "alm_instance",
                        width: 100,
                        search: false
                    }, {
                        name: "alm_code",
                        index: "alm_code",
                        align: 'center',
                        width: 100
                    }, {
                        name: "alm_msg",
                        index: "alm_msg",
                        width: 330
                    }, {
                        name: "alm_remark1",
                        index: "alm_remark1",
                        width: 330,
                        search: false
                    }, {
                        name: "alm_recovery",
                        index: "alm_recovery",
                        align: "left",
                        width: 500,
                        search: false
                        // 				}, {
                        // 					name : "alm_know_dt",
                        // 					index : "alm_know_dt",
                        // 					width : 140,
                        // 					align : 'center',
                        // 					search : false
                        // 				}, {
                        // 					name : "alm_clear_dt",
                        // 					index : "alm_clear_dt",
                        // 					width : 140,
                        // 					align : 'center',
                        // 					search : false
                        // 				}, {
                        // 					name : "sound_yn",
                        // 					index : "sound_yn",
                        // 					align : "center",
                        // 					stype : "select",
                        // 					searchoptions : {
                        // 						value : AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                        // 					},
                        // 					edittype : "select",
                        // 					editoptions : {
                        // 						value : AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                        // 					},
                        // 					formatter : 'select',
                        // 					width : 80

                        // 				}, {
                        // 					name : "alm_confirm_user",
                        // 					index : "alm_confirm_user",
                        // 					width : 100,
                        // 					search : false
                        // 				}, {
                        // 					name : "occur_cnt",
                        // 					index : "occur_cnt",
                        // 					width : 60,
                        // 					hidden : true,
                        // 					search : false
                        // 				}, {
                        // 					name : "first_occur_dt",
                        // 					index : "first_occur_dt",
                        // 					width : 230,
                        // 					hidden : true,
                        // 					search : false
                    }, {
                        name: "alm_category",
                        index: "alm_category",
                        width: 230,
                        hidden: true
                    }],
                sortname: "seqno",
                rowNum: 100,
                rowList: [100, 200, 300],
                sortorder: "desc",
                shrinkToFit: false,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar2",
                autowidth: true,
                multiselect: true,
                onSelectRow: function (id) {
                },
                onSelectAll: function (aRowids, status) {
                },
                ondblClickRow: function (rowid) {
                    if (rowid) {
                        var ret = jQuery(this).getRowData(rowid);
                        goAlarmMstPop(ret.seqno, ret.host_nm, ret.alm_category, ret.alm_occur_dt, ret.alm_code, ret.alm_msg, ret.alm_level, ret.alm_remark1);
                    }
                },
                loadComplete: function () {
                    $('#level-number>div.btn-group>label:eq(0)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(1)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(2)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(3)>strong').text(0);

                    var rowIds = $("#gridList2").getDataIDs();
                    $.each(rowIds, function (idx, rowId) {
                        var rowData = $("#gridList2").getRowData(rowId);
                        if (rowData.alm_level == "CRI") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#a90329",
                                color: "#fff"
                            });
                            $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() + 1);
                        } else if (rowData.alm_level == "MAJ") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#ffbb00"
                            });
                            $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() + 1);
                        } else if (rowData.alm_level == "MIN") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#faed7d"
                            });
                            $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() + 1);
                        } else if (rowData.alm_level == "WAN") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#fff"
                            });
                            $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() + 1);
                        }
                    });
                    $(window).trigger('resize');
                }
            });
            jQuery("#gridList2").jqGrid("filterToolbar", {
                stringResult: true,
                searchOnEnter: false,
                defaultSearch: "cn",
                ignoreCase: true
            });

            AotJqGrid.setGridStyle();
        });
    </script>
    <script>
        //선택 일괄 해지
        function checkedAlarm() {
            var id = jQuery("#gridList2").getGridParam('selarrrow');
            if (id) {
                if (id.length === 0) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>')
                } else {
                    var cellValues = [];
                    for (i = 0, n = id.length; i < n; i++) {
                        cellValues.push(jQuery("#gridList2").jqGrid("getCell", id[i], "seqno"));
                    }
                    var ret = jQuery("#gridList2").getRowData(id[0]);

                    goAlarmMstPop(cellValues.join(","), ret.host_nm, ret.alm_category, ret.alm_occur_dt, ret.alm_code, ret.alm_msg, ret.alm_level, ret.alm_remark1);
                }
            }
        }

        function setStatusText(cellValue, options, rowObject) {
            var result_txt;
            if (cellValue === "HAPPEN") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.HAPPEN'/>";
            } else if (cellValue === "TERMINATE") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.TERMINATE'/>";
            } else if (cellValue === "KNOW") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.KNOW'/>";
            } else {
                result_txt = "";
            }
            return result_txt;
        }

        /* 알람 목록 리로드 */
        function GridReload2() {
            $("#gridList2").jqGrid('clearGridData');
            var param = {
                srch_alm_instance: '${host_name}'
            };
            $("#gridList2").jqGrid('setGridParam', {
                url: "${CONTEXT_PATH}/obstacle/getAlarmList.json",
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: "seqno",
                sortorder: "desc",
                page: 1,
                postData: param,
                search: true,
            }).trigger("reloadGrid");
        }

        //코멘트수정 & 해지 처리 팝업
        function goAlarmMstPop(seqno, host_nm, alm_category, alm_occur_dt, alm_code, alm_msg, alm_level, alm_remark1) {
            AotCommon.goWindow('/obstacle/goRemarkPop.pop?seqno=' + seqno, {
                height: 550,
                width: 830
            });
        }

        function getResTypeNGraphValueByRtgrpNode() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/dashboardStatus.pop", {
                actionID: "getResTypeNGraphValueByRtgrpNode",
                node_name: '${node_name}',
                node_group: '${node_group}'
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                // 				console.info(response);
                var idx = 0;
                $.each(response, function (index, value) {
                    if (value.res_type === 'CPU') {
                        $('#resTypeNGraphValue>section').eq(2).text(value.res_type + ": " + value.graph_value + " %");
                    } else if (value.res_type === 'MEM') {
                        $('#resTypeNGraphValue>section').eq(3).text(value.res_type + ": " + value.graph_value + " %");
                    } else if (value.res_type === 'TOT') {
                        $('#resTypeNGraphValue>section').eq(4).text(value.res_type + ": " + $.number(value.graph_value));
                    } else if (_.startsWith(value.res_type, 'DISK')) {
                        $('#resTypeNGraphValueDisk').show();
                        if (idx % 4 === 0) {
                            AotHandlebars.drawDynamicHtml($('#diskGraph1'), 'append', 'templateResTypeNGraphValueDisk', value);
                        } else if (idx % 4 === 1) {
                            AotHandlebars.drawDynamicHtml($('#diskGraph2'), 'append', 'templateResTypeNGraphValueDisk', value);
                        } else if (idx % 4 === 2) {
                            AotHandlebars.drawDynamicHtml($('#diskGraph3'), 'append', 'templateResTypeNGraphValueDisk', value);
                        } else {
                            AotHandlebars.drawDynamicHtml($('#diskGraph4'), 'append', 'templateResTypeNGraphValueDisk', value);
                        }
                        idx++;
                    }
                });
                if (!response) {
                    $('#resTypeNGraphValue>section').eq(2).text("CPU:  -");
                    $('#resTypeNGraphValue>section').eq(3).text("MEM:  -");
                    $('#resTypeNGraphValue>section').eq(4).text("TOT:  -");
                }
            });
        }

        function getEmsmVmStatusVO() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/dashboardStatus.pop", {
                actionID: "getEmsmVmStatusVO",
                node_name: '${node_name}'
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                // 				console.info(response);
                $('#resTypeNGraphValue>section').eq(5).text(response.hw_name);
            });
        }

        function createGrid() {
            AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                actionID: "ACTION_VARSTATS_COLUMN_LIST",
                srch_index_type: 'NetTraffic-5min'
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                drawGrid(response);
            });
        }

        var table1 = undefined;

        function drawGrid(response) {
            // 			var svcUrl = '${CONTEXT_PATH}/stcs/var/stats.do?v=' + moment().valueOf();
            if (AotCommon.isEmpty(response)) {
                return;
            }
            $.each(response.ColNames, function (index, value) {
                $('#table1>thead>tr').append('<th>' + value + '</th>');
            });

            var columnDefs = [];
            var columns = [];
            $.each(response.ColModel, function (index, value) {
                columnDefs.push({
                    targets: index,
                    width: +value.width,
                    className: "text-" + value.align
                });
                columns.push({
                    data: value.name,
                    width: +value.width,
                    defaultContent: ""
                });
            });
            $.when().done(function () {
                AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                    actionID: "ACTION_VARSTATS_DEFAULT_LIST",
                    srch_index_type: 'NetTraffic-5min'
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    //기본값 세팅
                    $("#srch_es_base_url").val(response.srch_es_base_url);
                    $("#srch_out_col_ids").val(response.srch_out_col_ids);
                    $("#srch_out_col_names").val(response.srch_out_col_names);
                    $("#srch_out_col_types").val(response.srch_out_col_types);
                    $("#srch_def_col_id").val(response.srch_def_col_id);
                }).done(function () {
                    $.when().done(function () {
                        table1 = $('#table1').DataTable({
                            serverSide: true,
                            ajax: {
                                url: '${CONTEXT_PATH}/stcs/var/stats.do?v=' + moment().valueOf(),
                                type: "POST",
                                data: {
                                    actionID: "ACTION_VARSTATS_DATA_LIST",
                                    srch_index_type: 'NetTraffic-5min',
                                    srch_es_base_url: $("#srch_es_base_url").val(),
                                    srch_out_col_ids: $("#srch_out_col_ids").val(),
                                    srch_out_col_names: $("#srch_out_col_names").val(),
                                    srch_out_col_types: $("#srch_out_col_types").val(),
                                    srch_def_col_id: $("#srch_def_col_id").val(),
                                    srch_date_from: moment().subtract(2, 'hours').format('YYYY-MM-DD HH:mm'),
                                    srch_date_to: moment().format('YYYY-MM-DD HH:mm'),
                                    srch_text_prefix: 'AND',
                                    srch_filter_column: 'Name',
                                    srch_filter_type: '=',
                                    srch_filter_input: '${node_name}',
                                    srch_locality: $("#srch_locality").val()
                                },
                                dataFilter: function (data) {
                                    var json = jQuery.parseJSON(data);
                                    json.recordsTotal = json.records;
                                    json.recordsFiltered = json.records;
                                    json.data = json.root;
                                    return JSON.stringify(json);
                                }
                            },
                            dom: 'Bfrtip',
                            language: AotDataTables.language,
                            pagingType: "full_numbers",
                            scrollY: '400px',
                            scrollX: true,
                            searching: false,
                            ordering: false,
                            bSort: false,
                            fixedColumns: {
                                leftColumns: '${hfixed_left_col_cnt}',
                                rightColumns: '${hfixed_right_col_cnt}'
                            },
                            columnDefs: columnDefs,
                            columns: columns
                        });
                    }).done(function () {
                        $('#table1').parent().css({
                            height: ($(window).height() - $('#header').height() - $('#ribbon').height() - $('div.row:eq(0)').height() - 220) / 2 - 50
                        })
                    });
                });
            }).done(function () {
                // 				AotAjax.excute("${CONTEXT_PATH}/stcs/var/stats.do", {
                // 					actionID : "ACTION_VARSTATS_DEFAULT_LIST",
                // 					srch_index_type : 'NetTraffic-5min'
                // 				}, {
                // 					autoResultFunctionTF : false
                // 				}).done(function(response) {
                // 					AotAjax.excute('${CONTEXT_PATH}/stcs/var/stats.do?v=' + moment().valueOf(), {
                // 						actionID : "ACTION_VARSTATS_DATA_LIST",
                // 						srch_index_type : 'NetTraffic-5min',
                // 						srch_es_base_url : response.srch_es_base_url,
                // 						srch_out_col_ids : response.srch_out_col_ids,
                // 						srch_out_col_names : response.srch_out_col_names,
                // 						srch_out_col_types : response.srch_out_col_types,
                // 						srch_def_col_id : response.srch_def_col_id,
                // 						srch_date_from : moment().subtract(2, 'hours').format('YYYY-MM-DD HH:mm'),
                // 						srch_date_to : moment().format('YYYY-MM-DD HH:mm'),
                // 						srch_text_prefix : 'AND',
                // 						srch_filter_column : 'Name',
                // 						srch_filter_type : '=',
                // 						srch_filter_input : '${node_name}'
                // 					}).done(function(response) {
                // 						$('#table1').DataTable().clear().rows.add(response).draw();
                // 					});
                // 				});
            });
        }
    </script>
    </body>

    <script id="templateResTypeNGraphValueDisk" type="text/x-handlebars-template">
        <div class="row">
            <div class="col col-1">&nbsp;</div>
            <div class="col col-10">
		<span class="text">
			{{res_type}}
			<span class="pull-right">{{graph_value}}%</span>
		</span>
                <div class="progress-sm">
                    <div class="progress-bar bg-color-blue" style="width: {{graph_value}}%;"></div>
                </div>
            </div>
        </div>
    </script>
</aot:html>
