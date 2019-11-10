<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/audit/Log.jsp
 - 설      명	: Audit 로그 조회 화면 
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.05.31    1.0      KYM      신규작성
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

    <form name="myForm" method="POST">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
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
                                ${curr_menu_name}
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
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.CRT_DT"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>

                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="srch_input_date_from" id="srch_input_date_from">
                                            </label>
                                        </div>

                                        <div class="col col-3 input-group">
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
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_input_hh_to" id="srch_input_hh_to" group="TIME_24HH" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.HOUR"/>
                                            <aot:select name="srch_input_mm_to" id="srch_input_mm_to" group="TIME_MM" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CHART.SRCH.MINUTE"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <!-- 라디오 버튼 영역 -->
                                        <div class="col col-12 input-group" style="height: 32px; padding-top: 5px;">
                                            <div class="inline-group">
                                                <aot:radio name="srch_date_type" id="srch_date_type" group="SEARCH_DATE_TYPE" inline="YES" cssId="radio"></aot:radio>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 두번째 로우  영역 -->
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.AUDIT_SUBJECT"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_audit_subject" id="srch_audit_subject">
                                                </inpuit>
                                        </div>

                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.AUDIT_DESC"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_audit_desc" id="srch_audit_desc">
                                                </inpuit>
                                        </div>

                                    </div>

                                    <!-- 세번째 로우 -->
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.AUDIT_BEFORE_DATA"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_audit_before_data" id="srch_audit_before_data">
                                                </inpuit>
                                        </div>

                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.AUDIT_AFTER_DATA"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_audit_after_data" id="srch_audit_after_data">
                                                </inpuit>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.ADMIN.AUDIT_LOG.SEARCH.CRT_NM"/>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_crt_id" id="srch_crt_id">
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
                                <header></header>
                                <div>
                                    <div class="widget-body no-padding">
                                        <table id="gridList"></table>
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

    <form name="popForm" id="popForm" method="POST">
        <input type="hidden" name="actionID">
        <input type="hidden" name="crt_dt" id="crt_dt" value="">
        <input type="hidden" name="audit_subject" id="audit_subject" value="">
        <input type="hidden" name="audit_desc" id="audit_desc" value="">
        <input type="hidden" name="crt_nm" id="crt_nm" value="">
        <input type="hidden" name="audit_before_data" id="audit_before_data" value="">
        <input type="hidden" name="audit_after_data" id="audit_after_data" value="">
    </form>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {

            controlInit();
            datePickerInit(); //달력 세팅
            bindClickEvent();

            $("#gridList").jqGrid('clearGridData');

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/admin/audit/log.do",
                postData: {
                    actionID: "ACTION_AUDIT_LOG_LIST",
                    srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                    srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                    srch_audit_subject: $("#srch_audit_subject").val(),
                    srch_audit_desc: $("#srch_audit_desc").val(),
                    srch_audit_before_data: $("#srch_audit_before_data").val(),
                    srch_audit_after_data: $("#srch_audit_after_data").val(),
                    srch_crt_id: $("#srch_crt_id").val()
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
                colNames: ["<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.CRT_DT"/>",

                    "<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_SUBJECT"/>",

                    "<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_DESC"/>",

                    "<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_BEFORE_DATA"/>",

                    "<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.AUDIT_AFTER_DATA"/>",

                    "<spring:message code="TEXT.ADMIN.AUDIT_LOG.GRID.CRT_NM"/>", ""],
                colModel: [{
                    name: "crt_dt",
                    index: "crt_dt",
                    align: "center",
                    width: 150
                }, {
                    name: "audit_subject",
                    index: "audit_subject",
                    align: "left",
                    width: 250
                }, {
                    name: "audit_desc",
                    index: "audit_desc",
                    align: "left",
                    width: 150
                }, {
                    name: "audit_before_data",
                    index: "audit_before_data",
                    align: "left",
                    width: 460,
                    cellattr: function (rowId, tv, rawObject, cm, rdata) {
                        return 'style="white-space: normal;"';
                    }
                }, {
                    name: "audit_after_data",
                    index: "audit_after_data",
                    align: "left",
                    width: 460,
                    cellattr: function (rowId, tv, rawObject, cm, rdata) {
                        return 'style="white-space: normal;"';
                    }
                }, {
                    name: "crt_nm",
                    index: "crt_nm",
                    align: "left",
                    width: 150
                }, {
                    name: "audit_log_no",
                    index: "audit_log_no",
                    align: "left",
                    hidden: true
                }

                ],
                sortname: "crt_dt desc , audit_log_no",
                rowNum: 100,
                rowList: [100, 300, 500, 1000],
                sortorder: "desc",
                shrinkToFit: false, // 가로 스크롤
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                ondblClickRow: function (rowid) {
                    if (rowid) {
                        var ret = jQuery(this).getRowData(rowid);
                        goLogPop(ret.crt_dt, ret.audit_subject, ret.audit_desc, ret.crt_nm, ret.audit_before_data, ret.audit_after_data);
                    }
                    console.log("ondblClickRow~", ret.audit_log_no)
                },
                onSelectRow: function (id) {
                    var id = jQuery("#gridList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridList").getRowData(id);
                    }
                }
            });
            jQuery("#gridList").jqGrid("navGrid", "#gridToolbar", {
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
        });

        function goLogPop(crt_dt, audit_subject, audit_desc, crt_nm, audit_before_data, audit_after_data) {

            $("#crt_dt").val(crt_dt);
            $("#audit_subject").val(audit_subject);
            $("#audit_desc").val(audit_desc);
            $("#crt_nm").val(crt_nm);
            $("#audit_before_data").val(audit_before_data);
            $("#audit_after_data").val(audit_after_data);

            AotCommon.goWindow('${CONTEXT_PATH}/admin/audit/log.pop' + $(popForm).serialize() + '&actionID=ACTION_AUDIT_LOG_DET_POP', {
                width: 830,
                height: 500
            });
        }

        function datePickerInit() {
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
        }

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goChartList();
            });

            $("#srch_audit_subject").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });

            $("#srch_audit_desc").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });
            $("#srch_audit_before_data").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });
            $("#srch_audit_after_data").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goChartList();
                }
            });
            $("#srch_crt_id").on('keydown', function (key) {
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

            $("#gridList").jqGrid('clearGridData');

            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/admin/audit/log.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_AUDIT_LOG_LIST",
                    srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                    srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                    srch_audit_subject: $("#srch_audit_subject").val(),
                    srch_audit_desc: $("#srch_audit_desc").val(),
                    srch_audit_before_data: $("#srch_audit_before_data").val(),
                    srch_audit_after_data: $("#srch_audit_after_data").val(),
                    srch_crt_id: $("#srch_crt_id").val()
                }
            }).trigger("reloadGrid");
            AotJqGrid.setGridStyle();
        }

        //엑셀
        function doExcel() {
            if ($("#gridList").getGridParam("reccount") === 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            myForm.srchSidx.value = jQuery("#gridList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridList").getGridParam('sortorder');
            myForm.srch_date_from.value = $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val();
            myForm.srch_date_to.value = $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val();
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/admin/audit/log.do', param);
        }
    </script>
    </body>
</aot:html>