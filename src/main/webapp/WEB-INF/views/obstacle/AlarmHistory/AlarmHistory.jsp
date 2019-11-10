<%----------------------------------------------------------------------------------------
 - 파일이름	: obstaclehist/AlarmHistory.jsp
 - 설      명	: 알람이력조회 화면
 - 작 성 자	    : HSI
 - 작성날짜	: 2017.12.21
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2017.12.21    1.0      HSI      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES" fileDownloader="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/obstaclehist/alarmhistory.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>


        <c:set var="ALL">
            <spring:message code="TEXT.COMM.SEL.ALL"/>
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
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
                        <h1 class="page-title txt-color-blueDark">
                            <!-- PAGE HEADER -->
                            <i class="fa-fw fa fa-pencil-square-o"></i>
                                ${curr_menu_name }
                        </h1>
                    </div>

                    <!-- 상단 버튼들 -->
                    <div class="col-lg-4" style="float: right; height: 66px; padding-top: 10px; text-align: right;">
                        <button type="button" class="btn btn-primary btn-sm" id="srch">
                            <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm" id="excel">
                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                        </button>
                    </div>

                </div>
                <!-- 상단화면명 END -->

                <div class="row" style="margin-bottom: 10px;">
                    <!-- 검색박스 START -->
                    <div class="col-sm-12">
                        <div class="well smart-form" style="padding: 19px 19px 2px 19px;">

                            <div class="row">
                                <!-- 데이타셋 -->
                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.ALM_HIST.SRCH.DATE"/>
                                    <span style="color: red;"> *</span>
                                </label>
                                <section class="col">
                                    <label class="input">
                                        <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="srch_input_date_from" id="srch_input_date_from">
                                    </label>
                                </section>
                                <section class="col">
                                    <aot:select name="srch_input_hh_from" id="srch_input_hh_from" list="${srch24HHList }" style="height:32px;width:45px;"/>
                                    <i></i>
                                    <spring:message code="TEXT.ALM_HIST.SRCH.HOUR"/>
                                    <aot:select name="srch_input_mm_from" id="srch_input_mm_from" list="${srchMMList }" style="height:32px;width:45px;"/>
                                    <i></i>
                                    <spring:message code="TEXT.ALM_HIST.SRCH.MINUTE"/>
                                </section>

                                <label class="col-sm-1" style="text-align: center; width: 3%; margin-bottom: 0; padding-top: 7px;">~</label>
                                <section class="col">
                                    <label class="input">
                                        <i class="icon-append fa fa-calendar"></i>
                                        <input type="text" name="srch_input_date_to" id="srch_input_date_to">
                                    </label>
                                </section>
                                <section class="col">
                                    <aot:select name="srch_input_hh_to" id="srch_input_hh_to" list="${srch24HHList }" style="height:32px;width:45px;"/>
                                    <i></i>
                                    <spring:message code="TEXT.ALM_HIST.SRCH.HOUR"/>
                                    <aot:select name="srch_input_mm_to" id="srch_input_mm_to" list="${srchMMList }" style="height:32px;width:45px;"/>
                                    <i></i>
                                    <spring:message code="TEXT.ALM_HIST.SRCH.MINUTE"/>
                                </section>

                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.ALM_HIST.SRCH.HOST_NM"/>
                                </label>
                                <section class="col-sm-1">
                                    <label class="input">
                                        <input type="text" name="srch_alm_host" id="srch_alm_host">
                                    </label>

                                        <%-- 									<aot:select name="srch_alm_host" id="srch_alm_host" group="ALM_HOST" init="YES" initCode="" initName="${ALL}" style="height:32px;"></aot:select> --%>
                                </section>

                            </div>

                            <div class="row">
                                <!-- 적용기간 -->
                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.ALM_HIST.SRCH.ALM_CODE"/>
                                </label>
                                <section class="col">
                                    <label class="input">
                                        <input type="text" name="srch_alm_code" id="srch_alm_code">
                                    </label>
                                </section>
                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.ALM_HIST.SRCH.COMMENTS"/>
                                </label>
                                <section class="col">
                                    <label class="input">
                                        <input type="text" name="srch_alm_remark1" id="srch_alm_remark1">
                                    </label>
                                </section>

                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 4px;">Alm instance</label>
                                <section class="col">
                                    <label class="input">
                                        <input type="text" name="srch_alm_instance" id="srch_alm_instance">
                                    </label>
                                </section>

                            </div>
                            <div class="row">
                                <label for="srch_alm_level" class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 4px;">
                                    <spring:message code="TEXT.ALM_HIST.SRCH.ALM_LEVEL"/>
                                </label>
                                <section class="col">
                                    <div class="inline-group">
                                        <aot:checkbox name="alm_level" id="alm_level" list="${almLevelList }" inline="YES" cssId="checkbox" init="YES" place="BEFORE" initCode="ALL"
                                                      initName="${ALL}"></aot:checkbox>
                                        <!-- event="onchange='typeChange()'" -->
                                    </div>
                                    <input type="hidden" id="srch_alm_level" name="srch_alm_level"/>
                                </section>

                                <label for="srch_alm_msg" class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 4px;">알람 메시지</label>
                                <section class="col">
                                    <label class="input">
                                        <input type="text" name="srch_alm_msg" id="srch_alm_msg">
                                    </label>
                                </section>

                            </div>
                        </div>

                    </div>
                    <!-- 검색박스 END -->
                </div>


                <section id="widget-grid" class="">
                    <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                    <div class="row">
                        <article class="col-sm-12">
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">
                                <header></header>
                                <div>
                                    <div class="widget-body no-padding">
                                        <table id="gridAlarmHistoryList"></table>
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
        $(document).ready(
            function () {

                controlInit();
                datePickerInit(); //달력 세팅
                bindClickEvent();

                jQuery("#gridAlarmHistoryList").jqGrid(
                    {
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
                        height: 460,
                        colNames: ["<spring:message code="TEXT.ALM_HIST.GRID.ALM_STATUS"/>", "<spring:message code="TEXT.ALM_HIST.GRID.HOST_NM"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.ALM_OCCUR_DT"/>",
                            // 										"<spring:message code="TEXT.ALM_HIST.GRID.ALM_KNOW_DT"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.ALM_CLEAR_DT"/>", "Alarm Category", "<spring:message code="TEXT.ALM_HIST.GRID.ALM_CODE"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.ALM_LEVEL"/>", "<spring:message code="TEXT.ALM_HIST.GRID.SOUND_YN"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.ALM_MSG"/>", "Alarm Instance", "<spring:message code="TEXT.ALM_HIST.GRID.ALM_REMARK1"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.ALM_CONFIRM_USER"/>", "Alarm Recovery", "<spring:message code="TEXT.ALM_HIST.GRID.OCCUR_CNT"/>",
                            "<spring:message code="TEXT.ALM_HIST.GRID.FIRST_OCCUR_DT"/>"],
                        colModel: [{
                            name: "alm_status",
                            index: "alm_status",
                            align: "center",
                            width: 100,
                            formatter: setStatusText
                        }, {
                            name: "host_nm",
                            index: "host_nm",
                            align: "left",
                            width: 100
                        }, {
                            name: "alm_occur_dt",
                            index: "alm_occur_dt",
                            align: "center",
                            width: 160
                            // 								}, {
                            // 									name : "alm_know_dt",
                            // 									index : "alm_know_dt",
                            // 									align : "center",
                            // 									width : 160
                        }, {
                            name: "alm_clear_dt",
                            index: "alm_clear_dt",
                            align: "center",
                            width: 160
                        }, {
                            name: "alm_group",
                            index: "alm_group",
                            align: "center",
                            width: 100,
                            search: false
                        }, {
                            name: "alm_code",
                            index: "alm_code",
                            align: "center",
                            width: 100
                        }, {
                            name: "alm_level",
                            index: "alm_level",
                            align: "center",
                            width: 100
                        }, {
                            name: "sound_yn",
                            index: "sound_yn",
                            align: "center",
                            width: 80
                        }, {
                            name: "alm_msg",
                            index: "alm_msg",
                            align: "left",
                            width: 400
                        }, {
                            name: "alm_instance",
                            index: "alm_instance",
                            align: "left",
                            width: 100,
                            search: false
                        }, {
                            name: "alm_remark1",
                            index: "alm_remark1",
                            align: "left",
                            width: 400
                        }, {
                            name: "alm_confirm_user",
                            index: "alm_confirm_user",
                            align: "center",
                            width: 100
                        }, {
                            name: "alm_recovery",
                            index: "alm_recovery",
                            align: "left",
                            search: false,
                            width: 400
                        }, {
                            name: "occur_cnt",
                            index: "occur_cnt",
                            align: "right",
                            formatter: AotJqGrid.formatterCurrencyDefaultValue,
                            unformat: AotJqGrid.unformatterCurrencyDefaultValue,
                            hidden: true,
                            width: 80
                        }, {
                            name: "first_occur_dt",
                            index: "first_occur_dt",
                            align: "center",
                            hidden: true,
                            width: 160
                        }],
                        sortname: "alm_occur_dt",
                        rowNum: 100,
                        rowList: [100, 300, 500, 1000],
                        sortorder: "desc",
                        shrinkToFit: false, // 가로 스크롤
                        toolbarfilter: false,
                        viewrecords: true,
                        pager: "#gridToolbar",
                        autowidth: true,
                        onSelectRow: function (id) {
                        },
                        ondblClickRow: function (rowId) {
                        },
                    });
                jQuery("#gridAlarmHistoryList").jqGrid("navGrid", "#gridToolbar", {
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
            });

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
                result_txt = "<spring:message code='TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.HAPPEN'/>";
            } else if (cellValue === "TERMINATE") {
                result_txt = "<spring:message code='TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.TERMINATE'/>";
            } else if (cellValue === "KNOW") {
                result_txt = "<spring:message code='TEXT.ALM_HIST.GRI.TEXT.ALM_STATUS.KNOW'/>";
            }
            return result_txt;
        }

        function datePickerInit() {
            let srch_input_date_from = '${srch_input_yyyymmdd_from}' === '' ? moment() : '${srch_input_yyyymmdd_from}';
            let srch_input_date_to = '${srch_input_yyyymmdd_to}' === '' ? moment() : '${srch_input_yyyymmdd_to}';

            $('#srch_input_date_from').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: srch_input_date_from
            });

            $('#srch_input_date_to').datetimepicker({
                format: "YYYY-MM-DD",
                defaultDate: srch_input_date_to,
                useCurrent: false
            });
            AotDatetimePicker.setFromTo($('#srch_input_date_from'), $('#srch_input_date_to'));
        }

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goAlarmHistoryList();
            });

            $("#excel").on('click', function () {
                doExcel();
            });

            // radio change 이벤트
            <%-- $("input[name=srch_date_type]").on('change', function() {
                var radioValue = $(this).val();
                if (radioValue == "DAY") {
                    $("#srch_input_date_from").val(moment().format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue == "WEEK") {
                    $("#srch_input_date_from").val(moment().subtract('days',7).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                } else if (radioValue == "MONTH") {
                    $("#srch_input_date_from").val(moment().subtract('months',1).format("YYYY-MM-DD"));
                    $("#srch_input_date_to").val(moment().format("YYYY-MM-DD"));
                }
            }); --%>
        }

        function controlInit() {
            $("#srch_input_hh_from").val("${srch_input_hh_from}");
            $("#srch_input_mm_from").val("${srch_input_mm_from}");
            $("#srch_input_hh_to").val("${srch_input_hh_to}");
            $("#srch_input_mm_to").val("${srch_input_mm_to}");
            //$('input:radio[name=srch_date_type]:input[value=DAY]').attr("checked", true);
        }

        //알람이력조회
        function goAlarmHistoryList() {
            if ($("#srch_input_date_from").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_date_from").focus();
                return;
            }
            if ($("#srch_input_hh_from").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_hh_from").focus();
                return;
            }
            if ($("#srch_input_mm_from").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_mm_from").focus();
                return;
            }
            if ($("#srch_input_date_to").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_date_to").focus();
                return;
            }
            if ($("#srch_input_hh_to").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_hh_to").focus();
                return;
            }
            if ($("#srch_input_mm_to").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.ALM_HIST.SRCH.DATE" />는 필수입니다.");
                $("#srch_input_mm_to").focus();
                return;
            }

            $("#gridAlarmHistoryList").jqGrid('clearGridData');
            jQuery("#gridAlarmHistoryList").setGridParam({
                url: "${CONTEXT_PATH}/obstaclehist/alarmhistory.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_ALARM_HISTORY_LIST",
                    srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                    srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                    srch_alm_host: $("#srch_alm_host").val(),
                    srch_alm_code: $("#srch_alm_code").val(),
                    srch_alm_remark1: $("#srch_alm_remark1").val(),
                    srch_alm_instance: $("#srch_alm_instance").val(),
                    srch_alm_level: $("#srch_alm_level").val(),
                    srch_alm_msg: $("#srch_alm_msg").val()
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridAlarmHistoryList").getGridParam("reccount") == 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            myForm.srchSidx.value = jQuery("#gridAlarmHistoryList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridAlarmHistoryList").getGridParam('sortorder');
            myForm.srch_date_from.value = $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val();
            myForm.srch_date_to.value = $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val();
            var dataParam = {
                actionID: 'ACTION_GET_EXCEL',
                srchSidx: jQuery("#gridAlarmHistoryList").getGridParam('sortname'),
                srchSord: jQuery("#gridAlarmHistoryList").getGridParam('sortorder'),
                srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                srch_alm_host: $("#srch_alm_host").val(),
                srch_alm_code: $("#srch_alm_code").val(),
                srch_alm_remark1: $("#srch_alm_remark1").val(),
                srch_alm_instance: $("#srch_alm_instance").val(),
                srch_alm_level: $("#srch_alm_level").val()
            };
            AotAjax.downloadFile('${CONTEXT_PATH}/obstaclehist/alarmhistory.do', dataParam);
        }
    </script>
    </body>
</aot:html>