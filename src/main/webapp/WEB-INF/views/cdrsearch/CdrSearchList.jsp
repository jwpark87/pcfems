<%----------------------------------------------------------------------------------------
 - 파일이름	: cdr/search/List.jsp
 - 설      명	: CDR 조회 화면
  ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.19    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>
        .datepicker {
            z-index: 1200 !important;
        }
    </style>

    <form name="myForm" method="POST">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
        <input type="hidden" name="srch_index_type" id="srch_index_type" value="${SEARCH_DATA.srch_index_type}"/>
        <input type="hidden" name="srch_text_prefix" id="srch_text_prefix"/>
        <input type="hidden" name="srch_filter_column" id="srch_filter_column"/>
        <input type="hidden" name="srch_filter_type" id="srch_filter_type"/>
        <input type="hidden" name="srch_filter_input" id="srch_filter_input"/>
        <input type="hidden" name="srch_es_base_url" id="srch_es_base_url" value="${SEARCH_DATA.srch_es_base_url}"/>
        <input type="hidden" name="srch_out_col_ids" id="srch_out_col_ids" value="${SEARCH_DATA.srch_out_col_ids}"/>
        <input type="hidden" name="srch_out_col_names" id="srch_out_col_names" value="${SEARCH_DATA.srch_out_col_names}"/>
        <input type="hidden" name="srch_out_col_types" id="srch_out_col_types" value="${SEARCH_DATA.srch_out_col_types}"/>
        <input type="hidden" name="srch_def_col_id" id="srch_def_col_id" value="${SEARCH_DATA.srch_def_col_id}"/>
        <input type="hidden" name="srch_uuid" id="srch_uuid"/>
        <input type="hidden" name="srch_grid_data" id="srch_grid_data"/>
        <c:set var="ALL">
            <spring:message code="TEXT.COMM.SEL.PLEASE"/>
        </c:set>

        <div id="${type }" role="main">
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
                    <c:if test="${record_status_yn ne 'N' }">
                    <div class="col-xs-3" style="height: 66px; padding-top: 10px; text-align: right;">
                        </c:if>
                        <c:if test="${record_status_yn eq 'N' }">
                        <div class="col-xs-6" style="height: 66px; padding-top: 10px; text-align: right;">
                            </c:if>
                            <button type="button" class="btn btn-primary btn-sm" id="srch">
                                <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" id="excel">
                                <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                            </button>
                            <c:if test="${SEARCH_DATA.srch_index_type eq 'pcfsession'}">
                                <button type="button" class="btn btn-primary btn-sm" onclick="killSession();">세션 삭제요청</button>
                            </c:if>
                        </div>
                        <c:if test="${record_status_yn ne 'N' }">
                            <div class="col-xs-3">
                                <div class="well" style="padding: 2px 2px 2px 8px; margin-bottom: 10px;">
                                    <div class="smart-form">
                                        <h5 style="text-align: center;">
                                            <spring:message code="TEXT.CDRSEARCH.WELL.TITLE"/>
                                        </h5>
                                        <div>
                                            <spring:message code="TEXT.CDRSEARCH.WELL.TOTAL_ROW_CNT"/>
                                            : ${SEARCH_DATA.srch_total_row_cnt}
                                        </div>
                                        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                                        <div>
                                            <spring:message code="TEXT.CDRSEARCH.WELL.OLDEST_DT"/>
                                            : ${SEARCH_DATA.srch_oldest_dt}
                                        </div>
                                        <!-- &nbsp;&nbsp;&nbsp; -->
                                        <div>
                                            <spring:message code="TEXT.CDRSEARCH.WELL.NEWEST_DT"/>
                                            : ${SEARCH_DATA.srch_newest_dt}
                                        </div>
                                        <!-- &nbsp; -->
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <!-- 상단화면명 END -->

                    <!-- 검색박스 START -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="well" style="margin-bottom: 10px;">
                                <div class="smart-form">
                                    <fieldset style="background-color: #fbfbfb; padding: 0px;">
                                        <div class="row">
                                            <!-- 검색 시작 일자 -->
                                            <div class="col col-1 input-group" style="height: 33px;">
                                                <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                    <spring:message code="TEXT.CHART.SRCH.DATE"/>
                                                </label>
                                            </div>
                                            <div class="col input-group">
                                                <label class="input">
                                                    <i class="icon-append fa fa-calendar"></i>
                                                    <input type="text" name="srch_input_date_from" id="srch_input_date_from">
                                                </label>
                                            </div>
                                            <!-- 검색 시작 시간 -->
                                            <div class="col input-group">
                                                <aot:select name="srch_input_hh_from" id="srch_input_hh_from" group="TIME_24HH" style="height:32px;width:45px;"/>
                                                <i></i>
                                                <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                                <aot:select name="srch_input_mm_from" id="srch_input_mm_from" group="TIME_MM" style="height:32px;width:45px;"/>
                                                <i></i>
                                                <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                            </div>
                                            <!-- 검색 종료 일자 -->
                                            <div class="col input-group">
                                                <label class="input">
                                                    <i class="icon-append fa fa-calendar"></i>
                                                    <input type="text" name="srch_input_date_to" id="srch_input_date_to">
                                                </label>
                                            </div>
                                            <!-- 검색 종료 시간 -->
                                            <div class="col input-group">
                                                <aot:select name="srch_input_hh_to" id="srch_input_hh_to" group="TIME_24HH" style="height:32px;width:45px;"/>
                                                <i></i>
                                                <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                                <aot:select name="srch_input_mm_to" id="srch_input_mm_to" group="TIME_MM" style="height:32px;width:45px;"/>
                                                <i></i>
                                                <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                            </div>
                                            <c:if test="${SEARCH_DATA.srch_index_type ne 'pcfsession'}">
                                                <!-- 라디오 버튼 영역 -->
                                                <div class="col col-3 input-group" style="height: 32px; padding-top: 5px;">
                                                    <div class="inline-group">
                                                        <aot:radio name="srch_date_type" group="SEARCH_DATE_TYPE" inline="YES" cssId="radio"></aot:radio>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                        </div>


                    </div>
                    <!-- 검색박스 END -->

                    <!-- 조건박스 START -->
                    <div class="row" style="margin-bottom: 10px;">
                        <div class="col-sm-12">
                            <div class="well smart-form">
                                <div class="table-responsive">

                                    <button type="button" class="btn btn-success btn-sm" id="addAndRow">
                                        AND
                                        <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                                    </button>

                                    <table id="filterTable" class="table table-bordered" style="margin-top: 5px;">
                                        <tbody>
                                        <tr>
                                            <td style="width: 10%; text-align: center; vertical-align: middle; padding: 3px 10px;">
                                                <a href="javascript:void(0);" id="condition" class="btn btn-success btn-sm disabled" style="opacity: 1.5; width: 100%;"> AND </a>
                                            </td>
                                            <td style="width: 15%; text-align: center; vertical-align: middle; padding: 3px 10px;">
                                                <aot:select name="filter_column" id="filter_column" list="${RESULT_DATA }" init="YES" initCode="ALL" initName="${ALL}"
                                                            style="width: 100%;height:32px;"/>
                                                <i></i>
                                            </td>
                                            <td style="width: 20%; text-align: center; vertical-align: middle; padding: 3px 10px;">
                                                <aot:select name="filter_type" id="filter_type" group="SRCH_FILTER_TYPE" style="width: 100%;height:32px;"/>
                                                <i></i>
                                            </td>
                                            <td style="width: 30%; text-align: center; vertical-align: middle; padding: 3px 10px;">
                                                <input type="text" name="filter_input" id="filter_input" style="width: 100%; height: 32px;">
                                            </td>
                                            <td style="text-align: center; vertical-align: middle; padding: 3px 10px;">
                                                <button type="button" class="btn btn-warning btn-sm" id="orAddRow">
                                                    OR
                                                    <spring:message code="TEXT.COMM.BTN.CONDITION.REG"/>
                                                </button>
                                                <button type="button" class="btn btn-danger btn-sm" id="deleteRow">
                                                    <spring:message code="TEXT.COMM.BTN.DELETE"/>
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 조건박스 END -->

                    <!-- 그리드 START -->
                    <section id="widget-grid" class="">
                        <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                        <div class="row">
                            <article class="col-sm-12">
                                <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                     data-widget-deletebutton="false"
                                     data-widget-fullscreenbutton="false" data-widget-togglebutton="false">
                                    <header></header>
                                    <div>
                                        <div class="widget-body no-padding">
                                            <table id="gridCdrSearchList"></table>
                                            <div id="gridCdrSearchToolbar"></div>
                                        </div>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </section>
                    <!-- 그리드 END -->

                </div>
                <!-- content -->

            </div>
            <!-- main -->
    </form>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        //타이머
        var timer;
        //타이머 선택 값
        var selTimerVal = "1000"; //1초
        var excel_uuid = "";
        var past_page_no = 1;

        var modObj = {
            template: {
                keyword: ["[curr_cnt]", "[tot_cnt]", "[percent]", "[write]"],
                layout: "<div id='dialog-message' title='Dialog Simple Title'>" + "<div class='hr hr-12 hr-double'></div>" + "<p><b>현재  [tot_cnt] / [curr_cnt] 진행 중</b></p>"
                    + "<p style='color: #b94a48;font-weight:bold;'> [write]</p>" + "<div class='progress progress-striped active no-margin'>"
                    + "<div class='progress-bar progress-primary' role='progressbar' style='width: [percent]%'></div>" + "</div>" + "</div>",
                deleteKeywords: function (str) {
                    var res = str;
                    $.each(modObj.template.keyword, function (i, o) {
                        res = res.replace(o, ' ');
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
            createGrid();
        });

        function createGrid() {
            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_CDR_COLUMN_LIST",
                srch_index_type: $("#srch_index_type").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                $("#gridCdrSearchList").jqGrid('GridUnload');
                drawGrid(response);
            });
        }

        function drawGrid(response) {
            var svcUrl = '${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do?v=' + moment().valueOf();

            var cnames = response.ColNames;
            var cmodel = response.ColModel;

            //console.log("cnames~~",cnames)
            //console.log("cmodel~~",cmodel)

            $("#gridCdrSearchList").jqGrid("clearGridData").jqGrid({
                datatype: "json",
                mtype: 'POST',
                jsonReader: {
                    root: "root",
                    page: "page",
                    records: "records",
                    repeatitems: false
                },
                autoencode: true,
                height: 400,
                colNames: cnames,
                colModel: cmodel,
                rowNum: 100,
                rowList: [100, 200, 500, 1000],
                shrinkToFit: false, // 가로 스크롤
                pager: "#gridCdrSearchToolbar",
                autowidth: true,
                viewrecords: true,
                <c:if test="${SEARCH_DATA.srch_index_type eq 'pcfsession'}">
                multiselect: true,
                </c:if>
                cmTemplate: {
                    sortable: false
                }
            });

            jQuery("#gridCdrSearchList").jqGrid("navGrid", "#gridCdrSearchToolbar", {
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
            AotJqGrid.setGridStyle();
        }

        //달력
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

        //모달 초기화
        function pregressModal() {
            var html = modObj.template.layout;
            var htmlList = modObj.template.layoutList;
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
        }

        //클릭이벤트
        function bindClickEvent() {
            //조회 클릭
            $("#srch").on('click', function () {
                goCdrList();
            });

            //엑셀 다운로드 클릭
            $("#excel").on('click', function () {
                var gridCdrSearchListCnt = $("#gridCdrSearchList").getGridParam("reccount");
                if (gridCdrSearchListCnt == 0) {
                    AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                } else {
                    var rowDatas = jQuery("#gridCdrSearchList").getRowData();
                    //마지막 페이지 번호
                    var last_page = $("#gridCdrSearchList").getGridParam('lastpage');
                    //마지막 페이지가 1이면 더이상 조회 할 것이 없으므로 엑셀 출력 시 다시 조회 하지 않고 바로 다운
                    if (parseInt(last_page) == 1) {
                        $("#srch_grid_data").val(JSON.stringify(rowDatas));
                        doExcelGrid();
                    } else {
                        $("#srch_grid_data").val("");
                        doExcel();
                    }
                }
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

            //AND 조건 추가 무조건 밑에 추가
            $("#addAndRow").on('click', function () {
                addRowAnd();
            });

            //로우 삭제
            $("#deleteRow").on('click', function () {
                var lastItemNo = $("#filterTable tbody tr").length;
                var clickedRow = $(this).parent().parent();
                if (clickedRow.index() == 0) {
                    AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.CDR.COMBINED.ALERT.FIRST.ROW.DEL'/>");
                    return false;
                }
                if (lastItemNo == 1) {
                    AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ERROR.MIN_ONE'/>");
                    return false;
                }
                clickedRow.remove();
                return false;
            });

            //OR 조건 추가
            $("#orAddRow").on('click', function () {
                var clickedRow = $(this).parent().parent();
                var clone = $('#filterTable tr:eq(0)').clone(true);
                var btnCondition = clone.find('#condition');

                var txt = clone.find(':text');
                //텍스트박스 초기화
                $(txt).each(function () {
                    $(this).val("");
                });

                var selectFilterColumn = clone.find('#filter_column');
                var selectFilterType = clone.find('#filter_type');
                var selectFilterInput = clone.find('#filter_input');
                //앞 텍스트 수정
                $(btnCondition).text("OR");
                $(btnCondition).removeClass("btn-success").addClass("btn-warning");
                //셀렉트박스 초기화
                $(selectFilterColumn).find('option:first').attr('selected', 'selected');
                $(selectFilterType).find('option:first').attr('selected', 'selected');
                //disabled 상태에서 복사시 상태 없애기
                if ($(selectFilterInput).is("[disabled]")) {
                    $(selectFilterInput).removeAttr('disabled');
                }
                clone.insertAfter(clickedRow);
            });

            //조건 변경 이벤트
            $('select[id^=filter_type]').on('change', function () {
                var clickedRow = $(this).parent().parent();
                var trNo = clickedRow.index();
                var value = $.trim(this.value);
                clickedRow.find("#filter_input").val("");
                //조건이 is null 또는 is not null 이면 검색어 disabled
                if (value == "ISNULL" || value == "NOTNULL") {
                    clickedRow.find("#filter_input").prop("disabled", "true");
                } else {
                    clickedRow.find("#filter_input").removeAttr('disabled');
                }
            });
        }

        //모달창 열기
        function openModal(btn_flag) {
            var modalHtml = modObj.template.layout;
            modalHtml = modalHtml.replace("[curr_cnt]", "0");
            modalHtml = modalHtml.replace("[tot_cnt]", "0");
            modalHtml = modalHtml.replace("[percent]", "0");
            modalHtml = modalHtml.replace("[write]", "");

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
                var html = modObj.template.layoutList;
                $("#dialog-message2").html(html).dialog("option", "buttons", []);
                $('#dialog-message2').dialog('open');
                return false;
            }
        }

        //진행중 CDR 조회
        function goCdrList() {
            //각 로우별 검색조건 취합
            conditionGather();
            var svcUrl = '${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do?v=' + moment().valueOf();
            var param = {
                actionID: "ACTION_CDR_DATA_LIST",
                srch_es_base_url: $("#srch_es_base_url").val(),
                srch_out_col_ids: $("#srch_out_col_ids").val(),
                srch_out_col_names: $("#srch_out_col_names").val(),
                srch_out_col_types: $("#srch_out_col_types").val(),
                srch_def_col_id: $("#srch_def_col_id").val(),
                srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from").val() + ":" + $("#srch_input_mm_from").val(),
                srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to").val() + ":" + $("#srch_input_mm_to").val(),
                srch_index_type: $("#srch_index_type").val(),
                srch_text_prefix: $("#srch_text_prefix").val(),
                srch_filter_column: $("#srch_filter_column").val(),
                srch_filter_type: $("#srch_filter_type").val(),
                srch_filter_input: $("#srch_filter_input").val()
            };

            console.info(param);
            $("#gridCdrSearchList").jqGrid('clearGridData');
            jQuery("#gridCdrSearchList").setGridParam({
                url: svcUrl,
                postData: param,
                loadBeforeSend: function (xhr, settings) {
                    //페이징 버튼을 눌러 앞으로 이동할 페이지 번호를 취득
                    //누른 페이지
                    var curr_page_no = $("#gridCdrSearchList").getGridParam('page');
                    var curr_row_num = $("#gridCdrSearchList").getGridParam('rowNum');
                    if (curr_row_num * curr_page_no >= 10000) {
                        AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.CDR.COMBINED.ALERT.SEARCH.COUNT.LIMIT'/>");
                        console.log("loadBeforeSend~~~", "curr: " + curr_page_no + " || past==  " + past_page_no);
                        $(this).setGridParam({
                            page: past_page_no
                        });
                        //$(this).trigger('reloadGrid');
                        return false;
                    } else {
                        openModal("srch");
                    }
                    return true;
                },
                onPaging: function (pgButton) {
                    //페이징버튼 클릭시 여기서 먼저 취득 현재 페이지를 취득
                    past_page_no = this.p.page;
                },
                //서버로 데이터를 요청 하기 직전에 호출
                beforeRequest: function () {
                },
                //데이타 로딩이 끝난 후 호출
                loadComplete: function () {
                    $('#dialog-message2').dialog('close');
                }
            }).trigger("reloadGrid");
        }

        //AND조건 로우 추가
        function addRowAnd() {
            var lastItemNo = $("#filterTable tr:eq(0)").find("td").length;
            var clone = $('#filterTable tr:eq(0)').clone(true);
            var lastTrNo = $("#filterTable tbody tr").length;
            var txt = clone.find(':text');
            //텍스트박스 초기화
            $(txt).each(function () {
                $(this).val("");
            });
            var selectFilterColumn = clone.find('#filter_column');
            var selectFilterType = clone.find('#filter_type');
            var selectFilterInput = clone.find('#filter_input');
            //셀렉트박스 초기화
            $(selectFilterColumn).find('option:first').attr('selected', 'selected');
            $(selectFilterType).find('option:first').attr('selected', 'selected');
            //disabled 상태에서 복사시 상태 없애기
            if ($(selectFilterInput).is("[disabled]")) {
                $(selectFilterInput).removeAttr('disabled');
            }
            $("#filterTable tbody").append(clone);
        }

        //각 로우별 검색조건 취합
        function conditionGather() {
            var textPrefix = [];
            var column = [];
            var type = [];
            var input = [];
            $('#filterTable tbody tr').each(function (i) {
                var btnCondition = $(this).find('#condition');
                //AND OR 조건
                var conditionPrefix = $(btnCondition).text();
                textPrefix.push($.trim(conditionPrefix));
                //검색 컬럼
                var conditionColumn = $(this).find('#filter_column').val();
                column.push($.trim(conditionColumn));
                //검색 조건
                var conditionType = $(this).find('#filter_type').val();
                type.push($.trim(conditionType));
                //검색어
                var conditionInput = $(this).find('#filter_input').val();
                input.push($.trim(conditionInput));
            });
            /* 	console.log(textPrefix.join());
             console.log(column.join());
             console.log(type.join());
             console.log(input.join()); */
            $("#srch_text_prefix").val(textPrefix.join());
            $("#srch_filter_column").val(column.join());
            $("#srch_filter_type").val(type.join());
            $("#srch_filter_input").val(input.join());
        }

        //화면 초기화
        function controlInit() {
            //검색 일자 기본 정보에서 가져온 값으로 초기화
            $("#srch_input_date_from").val("${srch_input_date_from}");
            $("#srch_input_hh_from").val("${srch_input_hh_from}");
            $("#srch_input_mm_from").val("${srch_input_mm_from}");

            $("#srch_input_date_to").val("${srch_input_date_to}");
            $("#srch_input_hh_to").val("${srch_input_hh_to}");
            $("#srch_input_mm_to").val("${srch_input_mm_to}");

            $('input:radio[name=srch_date_type]:input[value=DAY]').attr("checked", true);
        }

        //타이머설정
        function setTimer(updateTimer) {
            clearInterval(timer);
            timer = setInterval(function () {
                //검색, 엑셀 상태 조회
                doCheckStatus();
            }, updateTimer);
        }

        //검색, 엑셀 상태 체크 ajax
        function doCheckStatus() {
            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_CDR_EXCEL_STATUS_LIST",
                srch_uuid: excel_uuid
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    var status = response.status;
                    var total = response.total;
                    var recv = response.recv;
                    var percent = (parseInt(recv) / parseInt(total)) * 100;
                    var downPath = response.path;

                    var modalHtml = modObj.template.layout;
                    modalHtml = modalHtml.replace("[curr_cnt]", recv);
                    modalHtml = modalHtml.replace("[tot_cnt]", total);
                    modalHtml = modalHtml.replace("[percent]", percent);
                    if (status === "I" && parseInt(total) != parseInt(recv)) {
                        modalHtml = modalHtml.replace("[write]", "");
                        $("#dialog-message").html(modalHtml);
                    } else if (status === "I" && parseInt(total) == parseInt(recv)) {
                        console.log(parseInt(total) == parseInt(recv) ? true : false);
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
        }

        //중지
        function functionStop() {
            clearInterval(timer);
            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_CDR_EXCEL_STOP",
                srch_uuid: excel_uuid
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                // 				var str = response.returnMsg.split(":");
                // 				console.log("stop~~", str);
                //엑셀 uuid 초기화
                excel_uuid = "";
            });
        }

        //엑셀
        function doExcel() {
            //각 로우별 검색조건 취합
            conditionGather();

            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_CDR_EXCEL_DATA_LIST",
                srch_es_base_url: $("#srch_es_base_url").val(),
                srch_out_col_ids: $("#srch_out_col_ids").val(),
                srch_out_col_names: $("#srch_out_col_names").val(),
                srch_out_col_types: $("#srch_out_col_types").val(),
                srch_def_col_id: $("#srch_def_col_id").val(),
                srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from").val() + ":" + $("#srch_input_mm_from").val(),
                srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to").val() + ":" + $("#srch_input_mm_to").val(),
                srch_index_type: $("#srch_index_type").val(),
                srch_text_prefix: $("#srch_text_prefix").val(),
                srch_filter_column: $("#srch_filter_column").val(),
                srch_filter_type: $("#srch_filter_type").val(),
                srch_filter_input: $("#srch_filter_input").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                excel_uuid = response.uuid;
                openModal("excel");
                setTimer(selTimerVal);
            });
        }

        //그리드 내용 그대로 엑셀출력
        function doExcelGrid() {
            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_CDR_EXCEL_DATA_LIST_GRID",
                srch_es_base_url: $("#srch_es_base_url").val(),
                srch_out_col_ids: $("#srch_out_col_ids").val(),
                srch_out_col_names: $("#srch_out_col_names").val(),
                srch_out_col_types: $("#srch_out_col_types").val(),
                srch_def_col_id: $("#srch_def_col_id").val(),
                srch_date_from: $("#srch_input_date_from").val() + " " + $("#srch_input_hh_from").val() + ":" + $("#srch_input_mm_from").val(),
                srch_date_to: $("#srch_input_date_to").val() + " " + $("#srch_input_hh_to").val() + ":" + $("#srch_input_mm_to").val(),
                srch_index_type: $("#srch_index_type").val(),
                srch_grid_data: $("#srch_grid_data").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                if (response.uuid) {
                    excel_uuid = response.uuid;
                    doExcelDown(excel_uuid);
                    excel_uuid = "";
                } else {
                    AotSmartAdmin.smallBoxWarning("fail");
                }
            });
        }

        function doExcelDown(uuid) {
            $("#srch_uuid").val(uuid);
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_CDR_EXCEL_DOWN';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do', param);
        }

        function killSession() {
            var jsonStr = AotJqGrid.getSelarrrowRowData('#gridCdrSearchList');
            if (!jsonStr || jsonStr.length === 0) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                return;
            }

            $('#password').val('');
            $('#passwordModal').modal({backdrop: 'static'});
        }

        function doKillSession() {
            var jsonStr = AotJqGrid.getSelarrrowRowData('#gridCdrSearchList');
            if (!jsonStr || jsonStr.length === 0) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                $('#passwordModal').modal('hide');
                return;
            }

            AotAjax.excute("${CONTEXT_PATH}/admin/CdrSearch/cdrSearch.do", {
                actionID: "ACTION_KILL_SESSION",
                jsonStr: JSON.stringify(jsonStr),
                password: CryptoJS.SHA256($('#password').val()).toString()
            }, {
                autoResultFunctionTF: true,
                successMessage: '선택하신 Session Terminate 가 요청되었습니다. 처리 결과는 세션관리>세션차단이력조회에서 확인가능합니다.'
            }).done(function (response) {
                $('#passwordModal').modal('hide');
                goCdrList();
            });
        }
    </script>
    <div id="passwordModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Please input the password.</h4>
                </div>
                <div class="modal-body">
                    <div class="col input-group">
                        <label class="input">
                            <i class="fa fa-key" aria-hidden="true"></i>
                            <input type="password" id="password"/>
                        </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="doKillSession();">OK</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    </body>
    <script src="${PATH_PLUGIN}/crypto-js/crypto-js.min.js"></script>
    <script src="${PATH_PLUGIN}/crypto-js/sha256.min.js"></script>
</aot:html>