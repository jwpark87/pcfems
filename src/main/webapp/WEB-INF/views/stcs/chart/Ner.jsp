<%----------------------------------------------------------------------------------------
 - 파일이름	: stcs/chart/Ner.jsp
 - 설      명	: NER 보고서 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.13    1.0      KYM     신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES" chart="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/stcs/nerreport.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="srch_stcs_country" id="srch_stcs_country"/>
        <input type="hidden" name="srch_stcs_realm" id="srch_stcs_realm"/>

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
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
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

                                    <!-- 그룹 / 기준시간 / 국가별체크박스 영역 -->
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CHART.SRCH.SBC_GROUP_NAME"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_sbc_group_id" id="srch_sbc_group_id" group="SBC_GROUP_ID" style="height:32px;width: 63.6%;"/>
                                            <i></i>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CHART.SRCH.OCCUR_DT"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_stcs_dt" id="srch_stcs_dt" group="SRCH_STCS_TIME" style="height:32px;width:63.6%;"/>
                                            <i></i>
                                        </div>
                                        <div class="col col-1"></div>

                                        <div class="col col-3 input-group" style="height: 32px; padding-top: 5px;">
                                            <div class="inline-group">
                                                <aot:checkbox name="srch_stcs_group" id="srch_stcs_group" group="BY_COUNTRY_REALM" inline="YES" cssId="checkbox"></aot:checkbox>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 발신국가 / 착신국가 영역 -->
                                        <%-- <div class="row" style="padding-top:10px;">
                                        <div class="col col-1 input-group" style="height:32px;">
                                            <label class="input-group-addon" style="background-color:#fbfbfb;border:none;"><spring:message code="TEXT.CHART.SRCH.FROM_COUNTRY_CD" /></label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_from_country_cd" id="srch_from_country_cd" group="COUNTRY_CD"  init="YES" initCode="" initName="전체"
                                                style="height:32px;width:63.6%;"/>
                                            <i></i>
                                        </div>
                                        <div class="col col-1 input-group" style="height:32px;">
                                            <label class="input-group-addon" style="background-color:#fbfbfb;border:none;"><spring:message code="TEXT.CHART.SRCH.TO_COUNTRY_CD" /></label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_to_country_cd" id="srch_to_country_cd" group="COUNTRY_CD"  init="YES" initCode="" initName="전체"
                                                style="height:32px;width:63.6%;"/>
                                            <i></i>
                                        </div>
                                    </div> --%>

                                    <!-- 발신사업자 / 착신사업자 / 버튼 영역 -->
                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CHART.SRCH.ACME_SESSION_INGRESS_REALM"/>
                                            </label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_acme_session_ingress_realm" id="srch_acme_session_ingress_realm" list="${REALM_LIST}" init="YES" initCode="" initName="전체"
                                                        style="height:32px;width:63.6%;"/>
                                            <i></i>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CHART.SRCH.ACME_SESSION_EGRESS_REALM"/>
                                            </label>
                                        </div>
                                        <div class="col col-3 input-group">
                                            <aot:select name="srch_acme_session_egress_realm" id="srch_acme_session_egress_realm" list="${REALM_LIST}" init="YES" initCode="" initName="전체"
                                                        style="height:32px;width:63.6%;"/>
                                            <i></i>
                                        </div>

                                        <div class="col col-1"></div>

                                        <div class="col col-3 input-group" style="height: 32px;"></div>
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
                                 data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">

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

                <!-- Widget ID (each widget will need unique ID)-->

                <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0">
                    <header style="border-color: #ccc !important; background: #fff; color: #666;">
                        <!-- 위젯 뱃지로 범례 꾸미기 -->
                        <div class="widget-toolbar-s" style="display: none;">
                            <div class="label" style="background-color: #4a89dc">
                                <spring:message code="TEXT.CHART.GRID.ACCT_SESSION_TIME"/>
                            </div>
                        </div>
                        <div class="widget-toolbar-s" style="display: none;">
                            <div class="label" style="background-color: #70ca63">
                                <spring:message code="TEXT.CHART.GRID.AVG_CALL_TIME"/>
                            </div>
                        </div>
                        <div class="widget-toolbar-s" style="display: none;">
                            <div class="label" style="background-color: #f6bb42">
                                <spring:message code="TEXT.CHART.GRID.AVG_PDD"/>
                            </div>
                        </div>
                    </header>
                    <!-- widget div-->
                    <div>
                        <!-- widget content -->
                        <div class="widget-body">

                            <div class="chart3" style="height: 220px; width: 100%;"></div>

                        </div>
                        <!-- end widget content -->

                    </div>
                    <!-- end widget div -->

                </div>
                <!-- end widget -->
            </div>
            <!-- content -->

        </div>
        <!-- main -->
    </form>


    <script src="${PATH_PUBLISH}/js/vendor/plugins/flot.js"></script>
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {

            controlInit();
            datePickerInit(); //달력 세팅
            bindClickEvent();

            $("#gridChartList").jqGrid('clearGridData');
            var v_srch_stcs_country = "N";
            var v_srch_stcs_realm = "N";


            jQuery("#gridChartList").jqGrid({
                <%-- url: "${CONTEXT_PATH}/stcs/asrreport.do",
                postData: {
                    actionID: "ACTION_ASR_LIST"
                        ,srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val()
                        ,srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val()
                        ,srch_sbc_group_id: $("#srch_sbc_group_id").val()
                        ,srch_stcs_dt: $("#srch_stcs_dt").val()
                        ,srch_stcs_country: v_srch_stcs_country
                        ,srch_stcs_realm: v_srch_stcs_realm
                        ,srch_from_country_cd: $("#srch_from_country_cd").val()
                        ,srch_to_country_cd: $("#srch_to_country_cd").val()
                        ,srch_acme_session_ingress_realm: $("#srch_acme_session_ingress_realm").val()
                        ,srch_acme_session_egress_realm: $("#srch_acme_session_egress_realm").val()
                }, --%>
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
                    "<spring:message code="TEXT.CHART.GRID.OCCUR_DT"/>",
                    /* "<spring:message code="TEXT.CHART.GRID.FROM_COUNTRY_CD_NAME"/>",
				"<spring:message code="TEXT.CHART.GRID.TO_COUNTRY_CD_NAME"/>", */
                    "<spring:message code="TEXT.CHART.GRID.ACME_SESSION_INGRESS_REALM"/>",
                    "<spring:message code="TEXT.CHART.GRID.ACME_SESSION_EGRESS_REALM"/>",
                    "<spring:message code="TEXT.CHART.GRID.TOT_TRY_CALL_CNT"/>",
                    "<spring:message code="TEXT.CHART.GRID.FAIL_CALL_CNT"/>",
                    "<spring:message code="TEXT.CHART.GRID.ACCT_SESSION_TIME"/>",
                    "<spring:message code="TEXT.CHART.GRID.AVG_CALL_TIME"/>",
                    "<spring:message code="TEXT.CHART.GRID.AVG_PDD"/>",
                    /* "<spring:message code="TEXT.CHART.GRID.ACME_CALLING_PACKETS_FS1"/>",
				"<spring:message code="TEXT.CHART.GRID.ACME_CALLING_OCTETS_FS1"/>",
				"<spring:message code="TEXT.CHART.GRID.ACME_CALLED_PACKETS_FS1"/>",
				"<spring:message code="TEXT.CHART.GRID.ACME_CALLED_OCTETS_FS1"/>" */
                ],
                colModel: [
                    {name: "occur_dt", index: "occur_dt", align: "center", width: 200},
                    /* {name:"from_country_cd_name"				,index:"from_country_cd_name"				,align:"left"  	,width:200, formatter:setNameText},
                    {name:"to_country_cd_name"					,index:"to_country_cd_name"					,align:"left"  	,width:200, formatter:setNameText}, */
                    {name: "ingress_realm_name", index: "ingress_realm_name", align: "left", width: 200, formatter: setNameText},
                    {name: "egress_realm_name", index: "egress_realm_name", align: "left", width: 200, formatter: setNameText},
                    {name: "try_call_cnt", index: "try_call_cnt", align: "right", width: 200, formatter: AotJqGrid.formatterCurrencyDefaultValue, unformat: AotJqGrid.unformatterCurrencyDefaultValue},
                    {
                        name: "fail_call_cnt",
                        index: "fail_call_cnt",
                        align: "right",
                        width: 200,
                        formatter: AotJqGrid.formatterCurrencyDefaultValue,
                        unformat: AotJqGrid.unformatterCurrencyDefaultValue
                    },
                    {
                        name: "acct_session_time",
                        index: "acct_session_time",
                        align: "right",
                        width: 200,
                        formatter: AotJqGrid.formatterCurrencyDefaultValue,
                        unformat: AotJqGrid.unformatterCurrencyDefaultValue
                    },
                    {name: "avg_call_time", index: "avg_call_time", align: "right", width: 200},
                    {name: "avg_pdd", index: "avg_pdd", align: "right", width: 200},
                    /* {name:"acme_calling_packets_fs1"			,index:"acme_calling_packets_fs1"			,align:"right"  	,width:150, formatter : AotJqGrid.formatterCurrencyDefaultValue, unformat : AotJqGrid.unformatterCurrencyDefaultValue},
                    {name:"acme_calling_octets_fs1"				,index:"acme_calling_octets_fs1"				,align:"right"  	,width:150, formatter : AotJqGrid.formatterCurrencyDefaultValue, unformat : AotJqGrid.unformatterCurrencyDefaultValue},
                    {name:"acme_called_packets_fs1"				,index:"acme_called_packets_fs1"			,align:"right"  	,width:150, formatter : AotJqGrid.formatterCurrencyDefaultValue, unformat : AotJqGrid.unformatterCurrencyDefaultValue},
                    {name:"acme_called_octets_fs1"				,index:"acme_called_octets_fs1"				,align:"right"  	,width:150, formatter : AotJqGrid.formatterCurrencyDefaultValue, unformat : AotJqGrid.unformatterCurrencyDefaultValue} */
                ],
                sortname: "occur_dt",
                rowNum: 100,
                rowList: [100, 300, 500, 1000],
                sortorder: "asc",
                shrinkToFit: false,    // 가로 스크롤
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridChartList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridChartList").getRowData(id);
                        var sbc_group_id = ret.sbc_group_id;
                    }
                },
                ondblClickRow: function (rowId) {
                    //showUpdatePop();
                },
                loadComplete: function (data) {
                    ajaxCallChart();
                }
            });
            jQuery("#gridChartList").jqGrid("navGrid", "#gridToolbar", {
                    edit: false, add: false, del: false, view: false, search: false, refresh: false
                },// edit,add,delete,view,search,options
                {}, {},
                {}, {multipleSearch: true}, {
                    width: 500
                }
            );

            AotJqGrid.setGridStyle();

            //차트 초기화
            FlotCharts.init();
        });

        //국가 사업자가 없으면 "-" 형태로 쿼리에서 넘어오므로 다시 편집.
        function setNameText(cellValue, options, rowObject) {
            var result_txt = "";
            if (cellValue === "-") {
                result_txt = "<spring:message code="TEXT.COMM.SEL.ALL"/>"
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

            $("#srch_sbc_group_id").on('keydown', function (key) {
                if (key.keyCode == 13) {
                    goChartList();
                }
            });
            // from country change 이벤트
            /* $("#srch_from_country_cd").on('change', function() {
                if($("#srch_from_country_cd").val() != "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked= true;
                }else if($("#srch_from_country_cd").val() == "" && $("#srch_to_country_cd").val() == "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked= false;
                }
            });	 */
            // to country change 이벤트
            /* $("#srch_to_country_cd").on('change', function() {
                if($("#srch_to_country_cd").val() != "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked= true;
                }else if($("#srch_from_country_cd").val() == "" && $("#srch_to_country_cd").val() == "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked= false;
                }
            }); */
            //산단 국가 없어지면서 아래 인덱스 0으로 조정
            // 발신사업자 change 이벤트
            $("#srch_acme_session_ingress_realm").on('change', function () {
                if ($("#srch_acme_session_ingress_realm").val() != "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked = true;
                } else if ($("#srch_acme_session_ingress_realm").val() == "" && $("#srch_acme_session_egress_realm").val() == "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked = false;
                }
            });
            // 착신사업자 change 이벤트
            $("#srch_acme_session_egress_realm").on('change', function () {
                if ($("#srch_acme_session_egress_realm").val() != "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked = true;
                } else if ($("#srch_acme_session_ingress_realm").val() == "" && $("#srch_acme_session_egress_realm").val() == "") {
                    $("input[name=srch_stcs_group]:checkbox")[0].checked = false;
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
            $("input[name=srch_stcs_group]:checkbox")[0].checked = true;
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

            $("#gridChartList").jqGrid('clearGridData');

            var v_srch_stcs_country = "N";
            var v_srch_stcs_realm = "N";
            //체크박스 값에 따른 변경. 기존 처리 변경 하지 않기 위해 변수에 담는 처리 유지
            $(':checkbox[id=srch_stcs_group]').each(function () {
                if ($(this).is(':checked') == true && $(this).val() == "COUNTRY") {
                    v_srch_stcs_country = "Y";
                } else if ($(this).is(':checked') == true && $(this).val() == "REALM") {
                    v_srch_stcs_realm = "Y";
                }
            });

            jQuery("#gridChartList").setGridParam({
                url: "${CONTEXT_PATH}/stcs/nerreport.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_NER_LIST"
                    , srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val()
                    , srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val()
                    , srch_sbc_group_id: $("#srch_sbc_group_id").val()
                    , srch_stcs_dt: $("#srch_stcs_dt").val()
                    , srch_stcs_country: v_srch_stcs_country
                    , srch_stcs_realm: v_srch_stcs_realm
                    , srch_from_country_cd: $("#srch_from_country_cd").val()
                    , srch_to_country_cd: $("#srch_to_country_cd").val()
                    , srch_acme_session_ingress_realm: $("#srch_acme_session_ingress_realm").val()
                    , srch_acme_session_egress_realm: $("#srch_acme_session_egress_realm").val()
                }
            }).trigger("reloadGrid");

        }

        function ajaxCallChart() {
            console.log("ajaxCallChart....");
//그래프 데이타 취득		
            var param = {
                actionID: "ACTION_NER_GRAPH"
                , srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val()
                , srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val()
                , srch_sbc_group_id: $("#srch_sbc_group_id").val()
                , srch_stcs_dt: $("#srch_stcs_dt").val()
                , srch_from_country_cd: $("#srch_from_country_cd").val()
                , srch_to_country_cd: $("#srch_to_country_cd").val()
                , srch_acme_session_ingress_realm: $("#srch_acme_session_ingress_realm").val()
                , srch_acme_session_egress_realm: $("#srch_acme_session_egress_realm").val()
            };
            Pace.track(function () {
                Pace.restart();
                $.ajax({
                    type: "POST",
                    dataType: "text",
                    data: param,
                    async: false,
                    global: false,
                    url: "${CONTEXT_PATH}/stcs/nerreport.do",
                    success: function (response) {
                        //차트 선 색 지정
                        var colors = ["#4a89dc", "#70ca63", "#f6bb42"];
                        //결과 리스트
                        var result = JSON.parse(response);

                        if (result.length > 0) {
                            var val_max = 0;
                            var val_min = "";
                            //3개의 배열안에 각 목록 중 첫번째 항목 시간 타입 변경
                            for (var y = 0; y < result.length; y++) {
                                var sub_list = result[y];
                                for (var s = 0; s < sub_list.length; s++) {
                                    sub_list[s][0] = new Date(sub_list[s][0]).getTime();
                                }
                            }
                            var dt_type = $("#srch_stcs_dt").val();
                            var type_tick_size = [];
                            var time_format = "";
                            if (dt_type === "MIN05" || dt_type === "MIN30" || dt_type === "TIME") {
                                val_min = $("#srch_input_date_from").val() + "T00:00Z";
                                val_max = $("#srch_input_date_to").val() + "T24:00Z";
                                type_tick_size.push(6);
                                type_tick_size.push('hour');
                                time_format = "%Y-%m-%d %H:%M";
                            } else if (dt_type === "DAY") {
                                val_min = $("#srch_input_date_from").val();
                                val_max = $("#srch_input_date_to").val();
                                type_tick_size.push(1);
                                type_tick_size.push('day');
                                time_format = "%Y-%m-%d";
                            } else if (dt_type === "MONTH") {
                                val_min = $("#srch_input_date_from").val().substr(0, 7);
                                val_max = $("#srch_input_date_to").val().substr(0, 7);
                                type_tick_size.push(1);
                                type_tick_size.push('month');
                                time_format = "%Y-%m";
                            }
                            $(".widget-toolbar-s").show();
                            chart_draw(result, colors, new Date(val_min).getTime(), new Date(val_max).getTime(), type_tick_size, time_format);
                        } else {
                            $('.chart3').empty();
                            $(".widget-toolbar-s").hide();
                        }

                    },
                    error: function (e, exception) {
                        AotSmartAdmin.smallBoxWarning("error:" + exception)
                    }
                });
            });
        }

        /**
         * @param p_datas        :  차트 데이타 배열
         * @param p_colors        :  라인 색상
         * @param p_x_min        :  x축 최소값
         * @param p_x_max        :  x축 최대값
         * @param tick_size        :  x축 데이타 간격
         * @param time_format    :  x축 데이타 시간 포맷
         **/
        function chart_draw(p_datas, p_colors, p_x_min, p_x_max, tick_size, time_format) {
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
                    content: "%x : %y.0",
                    shifts: {
                        /* 툴팁 위치 */
                        x: -30,
                        y: -50
                    },
                    defaultTheme: false
                }
                ,
                xaxis: {
                    min: p_x_min,
                    max: p_x_max,
                    mode: "time",
                    timeformat: time_format,//"%Y-%m-%d %H:%M",
                    minTickSize: tick_size// [1, 'day']
                },
                yaxis: {
                    tickDecimals: 2
                }
            };
            //FLOT CHART OBJECT 구성
            var chart_data = [];
            var data_obj = {};
            for (var i = 0; i < p_datas.length; i++) {
                data_obj.data = p_datas[i];
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
            console.log(chart_data);

            $.plot(".chart3", chart_data, Grid);
        }

        //엑셀
        function doExcel() {
            if ($("#gridChartList").getGridParam("reccount") === 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            var v_srch_stcs_country = "N";
            var v_srch_stcs_realm = "N";
            //체크박스 값에 따른 변경. 기존 처리 변경 하지 않기 위해 변수에 담는 처리 유지
            $(':checkbox[id=srch_stcs_group]').each(function () {
                if ($(this).is(':checked') == true && $(this).val() == "COUNTRY") {
                    v_srch_stcs_country = "Y";
                } else if ($(this).is(':checked') == true && $(this).val() == "REALM") {
                    v_srch_stcs_realm = "Y";
                }
            });
            myForm.srch_stcs_country.value = v_srch_stcs_country;
            myForm.srch_stcs_realm.value = v_srch_stcs_realm;
            myForm.srchSidx.value = jQuery("#gridChartList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridChartList").getGridParam('sortorder');
            myForm.srch_date_from.value = $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val();
            myForm.srch_date_to.value = $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val();
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/stcs/nerreport.do', param);
        }

    </script>
    </body>
</aot:html>
