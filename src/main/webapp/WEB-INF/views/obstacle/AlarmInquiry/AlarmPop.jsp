<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/AlarmInquiry/AlarmPop.jsp
 - 설      명	: 알람 조회 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2018.02.01    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/jsp/common/error/systemException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>

        /*상태바 width는 개별 정의*/
        .progerssbar_container {
            border: 1px solid #ddd;
            border-radius: 2px;
            overflow: hidden;
            display: inline-block;
            margin: 0px 5px 0px 0px;
            vertical-align: top;
        }

        .progressbar {
            color: #000000;
            text-align: right;
            height: 20px;
            width: 0;
            /* background-color: #0ba1b5; */
            background-color: #017CFF;
            border-radius: 2px;
        }

        .blink {
            color: #a90329 !important;
            font-weight: 700;
            animation: blink 1s infinite;
        }

        /* 마우스 오버시 글자 검정색으로 */
        .ui-jqgrid .ui-state-hover td {
            background: #ecf3f8 !important;
            color: #000000 !important;
        }
    </style>
    <form id="myForm" name="myForm">
        <input type="hidden" name="actionID">
        <input type="hidden" name="root" value="${ROOT_MENU}">
        <input type="hidden" name="menuUrl" value="${MENU_URL}">
        <!-- 	장애알람 ON/OFF 체크 값 -->
        <input type="hidden" name="soundOnOff" value="">
        <input type="hidden" name="srchErrorLevel" value="">

        <!-- 	조치상태 조회에 필요한 파라미터정의 -->
        <input type="hidden" name="seqno" id="seqno" value="">
        <input type="hidden" name="host_nm" id="host_nm" value="">
        <input type="hidden" name="alm_category" id="alm_category" value="">
        <input type="hidden" name="alm_occur_dt" id="alm_occur_dt" value="">
        <input type="hidden" name="alm_code" id="alm_code" value="">
        <input type="hidden" name="alm_msg" id="alm_msg" value="">
        <input type="hidden" name="alm_level" id="alm_level" value="">
        <input type="hidden" name="alm_remark1" id="alm_remark1" value="">
    </form>

    <c:set var="ALL">
        <spring:message code="TEXT.COMM.SEL.ALL"/>
    </c:set>

    <div id="panel" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <aot:navi menuUrl="${MENU_URL}"></aot:navi>
            </ol>
            <!-- <ol class="breadcrumb" style="color:#E4E4E4!important;">
                현재 로우를 선택 함으로 갱신 작업이 중지 되어 있는 상태입니다.
            </ol> -->
            <!-- end breadcrumb -->
        </div>

        <div id="content">

            <div class="row">
                <div class="col-sm-8">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-pencil-square-o"></i>
                            ${curr_menu_name }
                    </h1>
                </div>
                <div class="col-sm-4 text-right" style="margin-top: 8px;">
                    <div class="btn-group">
                        <!-- 버튼 Select START -->
                        <button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" id="btn_select_timer"></button>
                        <ul class="dropdown-menu" id="select_timer"></ul>
                        <!-- 버튼 Select END -->
                    </div>
                    <!-- 재 갱신 버튼 -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary btn-sm" id="restart">
                            <spring:message code="TEXT.COMM.BTN.INTERVAL.RE.START"/>
                        </button>
                    </div>
                </div>

                <!-- 중지 메시지 -->
                <div class="col-sm-12">
                    <h5 class="blink" style="margin: 13px 0">
                        <i>
                            <spring:message code="MSG.ALM.INQUIRY.POP.STOP.INTERVAL"/>
                        </i>
                    </h5>
                </div>

            </div>

            <!-- 사운드 메세지 & 사운드 설정 & 갱신주기 콤보 -->
            <div class="row" id="dvAlarmMsg" style="display: none; margin-bottom: 5px;">
                <div class="col-sm-12">
                    <div class="well smart-form" style="padding: 19px 19px 2px 19px;">
                        <div class="row">
                            <section class="col">
                                <div id="idMessage"></div>
                            </section>
                            <section class="col" style="padding-right: 5px;">
                                <div id="divSound"></div>
                                <div id="dvSoundPlay" style="display: none">
                                    <img src="${PATH_PUBLISH }/img/icon_sound_off.gif" style="cursor: pointer;" onClick="soundPlay(); return false;" alt="PLAY">
                                </div>
                                <div id="dvSoundStop" style="display: none">
                                    <img src="${PATH_PUBLISH }/img/icon_sound_on.gif" style="cursor: pointer;" onClick="soundStop(); return false;" alt="STOP">
                                </div>
                            </section>
                            <section class="col" style="padding-left: 0px;">
                                <div>Control Alarm</div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 상단 CPU/MEM 그리드 -->
            <div class="row">
                <div class="col-sm-12" id="gridlist-width">
                    <table id="gridList"></table>
                    <!-- <div id="gridToolbar"></div> -->
                </div>
            </div>

            <!-- 알람 레벨 건수 -->
            <div class="row" style="padding: 10px 0 10px 15px;">
                <div id="view_condition" class="row smart-form" style="margin: auto; right: 0; top: 0; bottom: 0;">
                    <div class="col-sm-3 col-md-3 col-lg-2">
                        <div class="well-sm">
                            <div class="form-inline" id="cntBox"></div>
                        </div>
                    </div>
                    <!-- 알람등급 체크박스 검색 조건 -->
                    <div class="col-sm-6 col-md-7 col-lg-8">
                        <div class="inline-group" style="margin: 18px 0 0 0;">
                            <aot:checkbox name="alm_level" id="alm_level" list="${almLevelList }" inline="YES" cssId="checkbox" init="YES" place="BEFORE" initCode="ALL"
                                          initName="${ALL}"></aot:checkbox>
                            <!-- event="onchange='typeChange()'" -->
                        </div>
                        <input type="hidden" id="srch_alm_level" name="srch_alm_level"/>
                    </div>

                    <div class="col col-sm-2 col-md-2 col-lg-2" style="text-align: right; padding: 10px 14px 0 0;">
                        <button type="button" class="btn btn-primary btn-sm" id="checked_alm">
                            <spring:message code="TEXT.COMM.SEL.CHOICE.FIRE"/>
                        </button>
                    </div>

                </div>
            </div>

            <!-- 하단 알람 정보 그리드 -->
            <div class="row">
                <div class="col-sm-12" id="gridlist-width2">
                    <table id="gridList2"></table>
                    <div id="gridToolbar2"></div>
                </div>
            </div>

        </div>
        <!-- content END -->

        <aot:select name="gd_alm_status" id="gd_alm_status" group="ALM_STATUS" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
        <aot:select name="gd_alm_level" id="gd_alm_level" group="ALM_LEVEL" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>

        <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" group="W001" init="YES"></aot:select>
    </div>
    <!-- panel END -->
    <!-- Script 영역 -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script src="${PATH_PUBLISH}/js/ProgressBar.js"></script>
    <script type="text/javascript">
        //타이머 선택 값
        var selTimerVal = "";
        //타이머
        var timer;

        var doSearchAlarmCnt = 0;
        var drawLevelNumber = 0;

        var fnObj = {
            //알람 레벨 레이아웃 그리기
            template: {
                keywords: ["[level_cri]", "[level_maj]", "[level_min]", "[level_wan]"],
                layout: "<div class=\"form-group\" style=\"padding-right: 5px;\">"
                    + "<a href=\"javascript:void(0);\" id=\"chkbtn\" class=\"btn btn-sm disabled bg-color-red txt-color-white\" style=\"padding: 0px 15px 0px;opacity:1.5;\">"
                    + "<span style=\"font-size:30px;\">[level_cri]</span>" + "</a>" + "</div>" + "<div class=\"form-group\" style=\"padding-right: 5px;\">"
                    + "<a href=\"javascript:void(0);\" id=\"chkbtn\" class=\"btn btn-sm disabled\"  style=\"background-color:#ffbb00;color:black!important;padding: 0px 15px 0px;opacity:1.5;\">"
                    + "<span style=\"font-size:30px;\">[level_maj]</span>" + "</a>" + "</div>" + "<div class=\"form-group\" style=\"padding-right: 5px;\">"
                    + "<a href=\"javascript:void(0);\" id=\"chkbtn\" class=\"btn btn-sm disabled\" style=\"background-color:#faed7d;color:black!important;padding: 0px 15px 0px;opacity:1.5;\">"
                    + "<span style=\"font-size:30px;\">[level_min]</span>" + "</a>" + "</div>" + "<div class=\"form-group\" style=\"padding-right: 5px;\">"
                    + "<a href=\"javascript:void(0);\" id=\"chkbtn\" class=\"btn btn-default  btn-sm disabled\" style=\"color:black!important;padding: 0px 15px 0px;opacity:1.5;\">"
                    + "<span style=\"font-size:30px;\">[level_wan]</span>" + "</a>" + "</div>",
                deleteKeywords: function (str) {
                    var res = str;
                    $.each(fnObj.template.keywords, function (i, o) {
                        res = res.replace(o, ' ');
                    });
                    return res;
                }
            }
        };

        $(document).ready(function () {

            //갱신주기 콤보
            selectBoxInit();
            bindClickEvent();
            //알람 취득 처리
            doSearchAlarm();
            //타이머 세팅
            doSetInitTimer();
            //알람 레벨 건수 그리기
            draw_level_number();
            //갱신 중지 문구 숨기기
            $(".blink").hide();

            //검색조건 체크박스 전체 체크
            $("input[name=alm_level]:checkbox").prop("checked", true);
            chkEvent();

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/obstacle/alarmpop.pop",
                postData: {
                    actionID: "CURR_CPUMEM_LIST"
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
                height: 145,
                colNames: ["<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.TGT_NAME'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER2'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER3'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER4'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER5'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER10'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER11'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER12'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER13'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER16'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER17'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.CPU.MEM.GRID.USAGE_PER18'/>"],
                colModel: [{
                    name: "tgt_name",
                    index: "tgt_name",
                    width: 130
                }, {
                    name: "usage_per1",
                    index: "usage_per1",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per2",
                    index: "usage_per2",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per3",
                    index: "usage_per3",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per4",
                    index: "usage_per4",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per5",
                    index: "usage_per5",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per6",
                    index: "usage_per6",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per7",
                    index: "usage_per7",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per8",
                    index: "usage_per8",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per9",
                    index: "usage_per9",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per10",
                    index: "usage_per10",
                    width: 130,
                    formatter: setProgerssBar
                }, {
                    name: "usage_per11",
                    index: "usage_per11",
                    width: 130,
                    formatter: setProgerssBar
                }],
                sortname: "sort_no",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                /* pager: "#gridToolbar", */
                autowidth: true,
                loadComplete: function () {
                    //그리드 내 프로그레스 그리기
                    setProgress();
                }
            });

            jQuery("#gridList2").jqGrid({
                url: "${CONTEXT_PATH}/obstacle/getAlarmList.json",
                postData: {
                    srch_alm_level: $("#srch_alm_level").val()
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
                height: 450,
                colNames: ["", "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_STATUS'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.HOST_NM'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_OCCUR_DT'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_KONW_DT'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CLEAR_DT'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CODE'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_LEVEL'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_MSG'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_REMARK1'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CONFIRM_USER'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.OCCUR_CNT'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.FIRST_OCCUR_DT'/>", ""

                ],
                colModel: [{
                    name: "seqno",
                    index: "seqno",
                    width: 230,
                    hidden: true
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
                },
                    /* {name:"host_nm"				,index:"host_nm"				, width:230}, */
                    {
                        name: 'host_nm',
                        index: 'host_nm',
                        width: 100,
                        align: 'left'
                    }, {
                        name: "alm_occur_dt",
                        index: "alm_occur_dt",
                        align: 'center',
                        width: 230,
                        search: false
                        // 				}, {
                        // 					name : "alm_know_dt",
                        // 					index : "alm_know_dt",
                        // 					align : 'center',
                        // 					width : 230,
                        // 					search : false
                    }, {
                        name: "alm_clear_dt",
                        index: "alm_clear_dt",
                        align: 'center',
                        width: 230,
                        search: false
                    }, {
                        name: "alm_code",
                        index: "alm_code",
                        align: 'center',
                        width: 100
                    }, {
                        name: 'alm_level',
                        index: 'alm_level',
                        width: 100,
                        align: 'center',
                        stype: "select",
                        searchoptions: {
                            value: AotJqGrid.getSelectTagData($("#gd_alm_level > option"))
                        },
                        edittype: "select",
                        editoptions: {
                            value: AotJqGrid.getSelectTagData($("#gd_alm_level > option"))
                        },
                        formatter: 'select'
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
                        name: "alm_confirm_user",
                        index: "alm_confirm_user",
                        width: 100,
                        search: false
                    }, {
                        name: "occur_cnt",
                        index: "occur_cnt",
                        width: 60,
                        align: 'right',
                        formatter: AotJqGrid.formatterCurrencyDefaultValue,
                        unformat: AotJqGrid.unformatterCurrencyDefaultValue,
                        search: false
                    }, {
                        name: "first_occur_dt",
                        index: "first_occur_dt",
                        align: 'center',
                        width: 230,
                        search: false
                    }, {
                        name: "alm_category",
                        index: "alm_category",
                        width: 230,
                        hidden: true
                    },],
                sortname: "seqno",
                rowNum: 100,
                rowList: [100, 200, 300],
                sortorder: "desc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar2",
                autowidth: true,
                multiselect: true,
                onSelectRow: function (id) {
                    clearInterval(timer);
                    $(".blink").show();
                },
                onSelectAll: function (aRowids, status) {
                    clearInterval(timer);
                    $(".blink").show();
                },
                ondblClickRow: function (rowid) {
                    if (rowid) {
                        var ret = jQuery(this).getRowData(rowid);
                        goAlarmMstPop(ret.seqno, ret.host_nm, ret.alm_category, ret.alm_occur_dt, ret.alm_code, ret.alm_msg, ret.alm_level, ret.alm_remark1);
                    }
                    console.log("ondblClickRow~", ret.alm_category)
                },
                loadComplete: function () {
                    var rowIds = $("#gridList2").getDataIDs();
                    $.each(rowIds, function (idx, rowId) {
                        var rowData = $("#gridList2").getRowData(rowId);
                        if (rowData.alm_level == "CRI") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#a90329",
                                color: "#fff"
                            });
                        } else if (rowData.alm_level == "MAJ") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#ffbb00"
                            });
                        } else if (rowData.alm_level == "MIN") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#faed7d"
                            });
                        } else if (rowData.alm_level == "WAN") {
                            $("#gridList2").setRowData(rowId, false, {
                                background: "#fff"
                            });
                        }
                    });
                }
            });
            jQuery("#gridList2").jqGrid("navGrid", "#gridToolbar2", {
                    edit: false,
                    add: false,
                    del: false,
                    view: false,
                    search: false,
                    refresh: false
                },// edit,add,delete,view,search,options
                {}, {}, {}, {}, {
                    width: 500
                });

            jQuery("#gridList2").jqGrid("filterToolbar", {
                stringResult: true,
                searchOnEnter: false,
                defaultSearch: "cn",
                ignoreCase: true
            });
            AotJqGrid.setGridStyle();
        });

        //선택 일괄 해지
        function checkedAlarm() {
            var id = jQuery("#gridList2").getGridParam('selarrrow');
            if (id) {
                if (id.length == 0) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>')
                } else {
                    var cellValues = [];
                    for (i = 0, n = id.length; i < n; i++) {
                        cellValues.push(jQuery("#gridList2").jqGrid("getCell", id[i], "seqno"));
                    }
                    var ret = jQuery("#gridList2").getRowData(id[0]);
                    console.log("onbtn~", ret.alm_category);

                    goAlarmMstPop(cellValues.join(","), ret.host_nm, ret.alm_category, ret.alm_occur_dt, ret.alm_code, ret.alm_msg, ret.alm_level, ret.alm_remark1);
                }
            }
        }

        $(document).on('change', 'input[name=alm_level]:checkbox', function (e) {
            //전체 체크
            if ($(this).is(':checked') == true && $(this).val() == "ALL") {
                $("input[name=alm_level]:checkbox").prop("checked", true);
            } else if ($(this).is(':checked') == false && $(this).val() == "ALL") {
                $("input[name=alm_level]:checkbox").prop("checked", false);
            } else if ($(this).val() != "ALL" && $("input[name=alm_level]:checkbox")[0].checked === true) {
                $("input[name=alm_level]:checkbox")[0].checked = false;
            }
            chkEvent();
        });

        function chkEvent() {
            var checkdVal = [];
            $(':checkbox[id=alm_level]').each(function () {
                //전체 제외한 체크값 저장
                if ($(this).is(':checked') == true && $(this).val() != "ALL") {
                    checkdVal.push($(this).val());
                }
            });
            $("#srch_alm_level").val(checkdVal);
        }

        //알람 상태 한글 표시
        function setStatusText(cellValue, options, rowObject) {
            var result_txt = "";
            if (cellValue === "HAPPEN") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.HAPPEN'/>";
            } else if (cellValue === "TERMINATE") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.TERMINATE'/>";
            } else if (cellValue === "KNOW") {
                result_txt = "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.TEXT.ALM_STATUS.KNOW'/>";
            }
            return result_txt;
        }

        //알람 레벨 건수 그리기
        function draw_level_number() {
            var html = fnObj.template.layout;

            AotAjax.excute("${CONTEXT_PATH}/obstacle/alarmpop.pop", {
                actionID: "ACTION_SNMPALARM_ALM_LEVEL_NUMBER"
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                html = html.replace("[level_cri]", response.cri_num);
                html = html.replace("[level_maj]", response.maj_num);
                html = html.replace("[level_min]", response.min_num);
                html = html.replace("[level_wan]", response.wan_num);
                html = fnObj.template.deleteKeywords(html);
                $("#cntBox").html(html);
            });
        }

        function setProgress() {
            var progressBarWidth = 0;
            $('.progressbar').each(function () {
                var value = parseInt($(this).text());
                //값이 없으면 NaN으로 나옵.
                if ($(this).text() !== "") {
                    progressBarWidth = value * $(".progerssbar_container").width() / 100;
                    $(this).empty().width(progressBarWidth).html(value + "%&nbsp;");
                    /* $(this).empty().animate({ width: progressBarWidth }, 500).width(progressBarWidth).html(value + "%&nbsp;"); */
                } else {
                    $(this).empty().width(0).html(0 + "%&nbsp;");
                    /* $(this).empty().animate({ width: 0 }, 500).width(0).html(0 + "%&nbsp;"); */
                }
            });
        }

        //그리드 프로그레스 바 삽입
        function setProgerssBar(cellValue, options, rowObject) {
            var progerss_class = "";
            var font_color = "";
            var cellval = parseInt(cellValue);
            //색지정
            if (cellval < 80) {
                progerss_class = "bg-color-green";
            } else if (cellval >= 80 && cellval <= 89) {
                progerss_class = "bg-color-orange";
            } else if (cellval >= 90) {
                progerss_class = "bg-color-red";
            }
            //20미만이면 숫자가 안보여 블랙으로 변경
            if (cellval < 20) {
                font_color = "black";
            } else {
                font_color = "white";
            }
            var p = "<div class=\"progerssbar_container\" style=\"width: 100%;\"><div class=\"progressbar " + progerss_class + "\" style=\"color: " + font_color + ";\">" + cellValue + "</div></div>";
            return p;
        }

        function selectBoxInit() {
            var _target = $("#select_timer");
            var btn_target = $("#btn_select_timer");

            var data = [];
            var sub_data = {};
            <c:forEach var="item" items="${timerList}" begin="0" step="1" varStatus="status">
            sub_data = {};
            sub_data.code = "${item.code}";
            sub_data.code_nm = "${item.name}";
            data.push(sub_data);
            </c:forEach>
            var select_timer_html;
            select_timer_html = "";
            $.each(data, function (i, val) {
                select_timer_html += " <li><a href=\"javascript:void(0);\" onclick=\"onChangeUpdateTimer(this)\" id=\"" + val.code + "\">" + val.code_nm + "</a><li>";
            });
            _target.html(select_timer_html);
            btn_target.html(data[0].code_nm + " <span class=\"caret\"></span>");
            /* 초기값 세팅 */
            selTimerVal = data[0].code;
        }

        //초기 타이머세팅
        function doSetInitTimer() {
            var updateTimer = selTimerVal;
            if (updateTimer != '0') {
                setTimer(updateTimer);
            }
        }

        //갱신 주기 콤보 change 이벤트
        function onChangeUpdateTimer(obj) {
            selTimerVal = obj.id;
            var btn_target = $("#btn_select_timer");
            btn_target.html(obj.text + " <span class=\"caret\"></span>");
            // 타이머제거
            if (timer != "") {
                clearInterval(timer);
            }
            var updateTimer = selTimerVal;
            if (updateTimer != '0') {
                setTimer(updateTimer);
            }
        }

        //타이머설정
        function setTimer(updateTimer) {
            clearInterval(timer);
            timer = setInterval(function () {
                doSearchAlarm();
                GridReload();
                GridReload2();
                draw_level_number();
            }, updateTimer);
        }

        function doSearchAlarm() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/alarmpop.pop", {
                actionID: "ACTION_OBSTACLE_ALARM_LIST"
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                showAlarmInfo(response);
            }).fail(function () {
                doSearchAlarmCnt = doSearchAlarmCnt + 1;
                if (doSearchAlarmCnt > 3) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.ALERT.PROCESS.ERROR"/>');
                    doSearchAlarmCnt = 0;
                }
            });
        }

        // 알람정보취득후처리
        function showAlarmInfo(data) {
            var rowNum = 1; // 배열 수
            var result;
            var message;
            var level;
            var file;
            var status;
            console.log("showAlarmInfo~~~", data);

            if (data !== null) {
                result = data.result;
                message = data.message;
                level = data.level;
                file = data.file;
                status = data.status;
            }
            //20180604 KYM 값이 없어도 사운드 부분 보여지게 수정 / 사운드 on/off는 장애 데이타와 상관없이 유지. 기존 안보이는 처리 제거
            $("#dvAlarmMsg").show();
            document.getElementById("idMessage").innerHTML = message;

            if (status == "SOUND_PLAY") {
                if (file != "") {
                    var sound = "<DIV style='FONT-SIZE: 10pt; FONT-FAMILY: 994268_10'>";
                    sound += "<audio controls='controls' autoplay hidden loop='loop'>";
                    sound += "<source src='${PATH_ALARM_SOUND}" + file + "' />";
                    sound += "</audio>";
                    sound += "</DIV>";
                    document.getElementById("divSound").innerHTML = sound;
                } else {
                    var sound = "<DIV style='FONT-SIZE: 10pt; FONT-FAMILY: 994268_10'>";
                    sound += "</DIV>";
                    document.getElementById("divSound").innerHTML = sound;
                }

                $("#dvSoundStop").show();
                $("#dvSoundPlay").hide();
            } else {
                if (file != "") {
                    var sound = "<DIV style='FONT-SIZE: 10pt; FONT-FAMILY: 994268_10'>";
                    sound += "<audio controls='controls' hidden loop='loop'>";
                    sound += "<source src='${PATH_ALARM_SOUND}" + file + "' />";
                    sound += "</audio>";
                    sound += "</DIV>";
                    document.getElementById("divSound").innerHTML = sound;
                } else {
                    var sound = "<DIV style='FONT-SIZE: 10pt; FONT-FAMILY: 994268_10'>";
                    sound += "</DIV>";
                    document.getElementById("divSound").innerHTML = sound;
                }
                $("#dvSoundStop").hide();
                $("#dvSoundPlay").show();
            }
            document.myForm.srchErrorLevel.value = level;
        }

        function soundPlay() {
            document.myForm.soundOnOff.value = "SOUND_PLAY";
            var allAudioEls = $('audio');
            allAudioEls.each(function () {
                var a = $(this).get(0);
                a.play();
            });
            doSetSound("SOUND_PLAY");

            $("#dvSoundStop").show();
            $("#dvSoundPlay").hide();
        }

        function soundStop() {
            document.myForm.soundOnOff.value = "SOUND_STOP";
            var allAudioEls = $('audio');
            allAudioEls.each(function () {
                var a = $(this).get(0);
                a.pause();
            });
            // 	세션에 세션상태 및 설정시간을 세팅한다.(AJAX)
            doSetSound("SOUND_STOP");

            $("#dvSoundStop").hide();
            $("#dvSoundPlay").show();
        }

        // 사운드 설정 처리
        function doSetSound(kind) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/doSouneSet.json", {
                kind: kind
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
            });
        }

        /* CPU/MEM 목록 리로드 */
        function GridReload(sort, order) {
            if (sort == null || sort == "") {
                sort = "sort_no";
            }
            if (order == null || order == "") {
                order = "asc";
            }
            $("#gridList").jqGrid('clearGridData');
            var param = {
                actionID: "CURR_CPUMEM_LIST"
            };
            $("#gridList").jqGrid('setGridParam', {
                url: "${CONTEXT_PATH}/obstacle/alarmpop.pop",
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: sort,
                sortorder: order,
                page: 1,
                postData: param, //검색조건
                search: true, //검색유무
            }).trigger("reloadGrid");
        }

        /* 알람 목록 리로드 */
        function GridReload2() {
            $("#gridList2").jqGrid('clearGridData');
            var param = {
                srch_alm_level: $("#srch_alm_level").val()
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
            console.info(seqno);
            $("#seqno").val(seqno);
            $("#host_nm").val(host_nm);
            $("#alm_category").val(alm_category);
            $("#alm_occur_dt").val(alm_occur_dt);
            $("#alm_code").val(alm_code);
            $("#alm_msg").val(alm_msg);
            $("#alm_level").val(alm_level);
            $("#alm_remark1").val(alm_remark1);
            AotCommon.goWindow('${CONTEXT_PATH}/obstacle/goRemarkPop.pop' + $(document.myForm).serialize(), {
                width: 830,
                height: 500
            });
            document.myForm.action = "";
            document.myForm.target = "";
            document.myForm.actionID.value = "";
        }

        function bindClickEvent() {
            //선택 로우 일괄 해지/수정
            $("#checked_alm").on('click', function () {
                checkedAlarm();
            });
            //갱신 다시 시작
            $("#restart").on('click', function () {
                doSetInitTimer();
                $(".blink").hide();
            });

            /*임시 테스트*/
            $("#btn_temp").on('click', function () {
                clearInterval(timer);
                goAlarmMstPop(1, "PUSAN#2", "PROC", "2017-02-02 16:02:02", "3412", "장애가 발생 하였습니다.", "CRI", "장애가 발생 장애가 발생 장애가 발생장애가 발생 장애가 발생 장애가 발생장애가 발생 장애가 발생 장애가 발생장애가 발생 장애가 발생 장애가 발생");
            });

        }
    </script>
    </body>
</aot:html>