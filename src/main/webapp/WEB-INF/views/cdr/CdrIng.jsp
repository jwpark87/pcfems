<%----------------------------------------------------------------------------------------
 - 파일이름	: cdr/ing/List.jsp
 - 설      명	: 진행 중 CDR 조회 화면
 - 작 성 자	    : LHN
 - 작성날짜	: 2017.12.21
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2017.12.21    1.0      LHN      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="srch_date_from"/>
        <input type="hidden" name="srch_date_to"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
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

                <!-- 검색박스 START -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="well">
                            <div class="smart-form">
                                <fieldset style="background-color: #fbfbfb; padding: 0px;">
                                    <div class="row">
                                        <div class="col col-1 input-group">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CDR.SRCH.DATE"/>
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
                                            <aot:select name="srch_input_hh_from" id="srch_input_hh_from" list="${srch24HHList }" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.HOUR"/>
                                            <aot:select name="srch_input_mm_from" id="srch_input_mm_from" list="${srchMMList }" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.MINUTE"/>
                                        </div>

                                        <div class="col col-2 input-group">
                                            <label class="input">
                                                <i class="icon-append fa fa-calendar"></i>
                                                <input type="text" name="srch_input_date_to" id="srch_input_date_to">
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_input_hh_to" id="srch_input_hh_to" list="${srch24HHList }" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.HOUR"/>
                                            <aot:select name="srch_input_mm_to" id="srch_input_mm_to" list="${srchMMList }" style="height:32px;width:45px;"/>
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.MINUTE"/>
                                        </div>

                                        <div class="col col-3 input-group" style="height: 32px; padding-top: 5px;">
                                            <input type="radio" id="srch_date_type" name="srch_date_type" value="DAY">
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.TODAY"/>
                                            &nbsp;
                                            <input type="radio" id="srch_date_type" name="srch_date_type" value="WEEK">
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.WEEK"/>
                                            &nbsp;
                                            <input type="radio" id="srch_date_type" name="srch_date_type" value="MONTH">
                                            <i></i>
                                            <spring:message code="TEXT.CDR.SRCH.MONTH"/>
                                            &nbsp;
                                        </div>
                                    </div>

                                    <div class="row" style="padding-top: 10px;">
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CDR.SRCH.SBC_GROUP_NAME"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>
                                        <div class="col col-2 input-group">
                                            <aot:select name="srch_sbc_group_id" id="srch_sbc_group_id" list="${sbcGroupList }" style="height:32px;width:99%"/>
                                            <i></i>
                                        </div>
                                        <div class="col col-1 input-group" style="height: 32px;">
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CDR.SRCH.SBC_PROC_TIME_SECOND"/>
                                                <span style="color: red;"> *</span>
                                            </label>
                                        </div>
                                        <div class="col col-1 input-group">
                                            <label class="input">
                                                <input type="text" name="srch_ing_time_s" id="srch_ing_time_s" style="">
                                            </label>
                                            <label class="input-group-addon" style="background-color: #fbfbfb; border: none;">
                                                <spring:message code="TEXT.CDR.SRCH.OVER"/>
                                                &nbsp;
                                            </label>
                                        </div>

                                        <div class="col col-3"></div>


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
                                        <table id="gridCdrList"></table>
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
                selectBoxInit();
                bindClickEvent();

                jQuery("#gridCdrList").jqGrid(
                    {
                        url: "${CONTEXT_PATH}/cdr/ing.do",
                        postData: {
                            actionID: "ACTION_ING_LIST",
                            srch_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                            srch_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                            srch_sbc_group_id: $("#srch_sbc_group_id").val(),
                            srch_ing_time_s: $("#srch_ing_time_s").val()
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
                        height: 460,
                        colNames: ["<spring:message code="TEXT.CDR.GRID.SBC_GROUP_ID"/>", "<spring:message code="TEXT.CDR.GRID.SBC_GROUP_NAME"/>",
                            "<spring:message code="TEXT.CDR.GRID.H323_SETUP_TIME"/>", "<spring:message code="TEXT.CDR.GRID.H323_CONNECT_TIME"/>",
                            "<spring:message code="TEXT.CDR.GRID.CALLING_NUMBER"/>", "<spring:message code="TEXT.CDR.GRID.CALLED_NUMBER"/>",
                            "<spring:message code="TEXT.CDR.GRID.FROM_COUNTRY_CD"/>", "<spring:message code="TEXT.CDR.GRID.FROM_COUNTRY_CD_NAME"/>",
                            "<spring:message code="TEXT.CDR.GRID.TO_COUNTRY_CD"/>", "<spring:message code="TEXT.CDR.GRID.TO_COUNTRY_CD_NAME"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACCT_SESSION_ID"/>", "<spring:message code="TEXT.CDR.GRID.CALLING_STATION_ID"/>",
                            "<spring:message code="TEXT.CDR.GRID.CALLED_STATION_ID"/>", "<spring:message code="TEXT.CDR.GRID.NAS_IP_ADDRESS"/>",
                            "<spring:message code="TEXT.CDR.GRID.NAS_PORT"/>", "<spring:message code="TEXT.CDR.GRID.ACME_SESSION_INGRESS_REALM"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_SESSION_EGRESS_REALM"/>", "<spring:message code="TEXT.CDR.GRID.ACME_SESSION_PROTOCOL_TYPE"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_FLOWTYPE_FS1_F"/>", "<spring:message code="TEXT.CDR.GRID.ACME_LOCAL_TIME_ZONE"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_POST_DIAL_DELAY"/>", "<spring:message code="TEXT.CDR.GRID.ACME_ORIGINATING_TRUNK_GROUP"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_TERMINATING_TRUNK_GROUP"/>", "<spring:message code="TEXT.CDR.GRID.ACME_INGRESS_LOCAL_ADDR"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_INGRESS_REMOTE_ADDR"/>", "<spring:message code="TEXT.CDR.GRID.ACME_EGRESS_LOCAL_ADDR"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACME_EGRESS_REMOTE_ADDR"/>", "<spring:message code="TEXT.CDR.GRID.CLIENT_IP_ADDRESS"/>",
                            "<spring:message code="TEXT.CDR.GRID.ACCT_UNIQUE_SESSION_ID"/>"],
                        colModel: [{
                            name: "sbc_group_id",
                            index: "sbc_group_id",
                            align: "left",
                            width: 200,
                            hidden: true
                        }, {
                            name: "sbc_group_name",
                            index: "sbc_group_name",
                            align: "left",
                            width: 150
                        }, {
                            name: "h323_setup_time",
                            index: "h323_setup_time",
                            align: "center",
                            width: 200
                        }, {
                            name: "h323_connect_time",
                            index: "h323_connect_time",
                            align: "center",
                            width: 200
                        }, {
                            name: "calling_number",
                            index: "calling_number",
                            align: "left",
                            width: 150
                        }, {
                            name: "called_number",
                            index: "called_number",
                            align: "left",
                            width: 150
                        }, {
                            name: "from_country_cd",
                            index: "from_country_cd",
                            align: "left",
                            width: 150,
                            hidden: true
                        }, {
                            name: "from_country_cd_name",
                            index: "from_country_cd_name",
                            align: "left",
                            width: 150
                        }, {
                            name: "to_country_cd",
                            index: "to_country_cd",
                            align: "left",
                            width: 150,
                            hidden: true
                        }, {
                            name: "to_country_cd_name",
                            index: "to_country_cd_name",
                            align: "left",
                            width: 200
                        }, {
                            name: "acct_session_id",
                            index: "acct_session_id",
                            align: "left",
                            width: 300
                        }, {
                            name: "calling_station_id",
                            index: "calling_station_id",
                            align: "left",
                            width: 600
                        }, {
                            name: "called_station_id",
                            index: "called_station_id",
                            align: "left",
                            width: 600
                        }, {
                            name: "nas_ip_address",
                            index: "nas_ip_address",
                            align: "left",
                            width: 100
                        }, {
                            name: "nas_port",
                            index: "nas_port",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_session_ingress_realm",
                            index: "acme_session_ingress_realm",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_session_egress_realm",
                            index: "acme_session_egress_realm",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_session_protocol_type",
                            index: "acme_session_protocol_type",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_flowtype_fs1_f",
                            index: "acme_flowtype_fs1_f",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_local_time_zone",
                            index: "acme_local_time_zone",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_post_dial_delay",
                            index: "acme_post_dial_delay",
                            align: "left",
                            width: 100
                        }, {
                            name: "acme_originating_trunk_group",
                            index: "acme_originating_trunk_group",
                            align: "left",
                            width: 200
                        }, {
                            name: "acme_terminating_trunk_group",
                            index: "acme_terminating_trunk_group",
                            align: "left",
                            width: 200
                        }, {
                            name: "acme_ingress_local_addr",
                            index: "acme_ingress_local_addr",
                            align: "left",
                            width: 200
                        }, {
                            name: "acme_ingress_remote_addr",
                            index: "acme_ingress_remote_addr",
                            align: "left",
                            width: 200
                        }, {
                            name: "acme_egress_local_addr",
                            index: "acme_egress_local_addr",
                            align: "left",
                            width: 200
                        }, {
                            name: "acme_egress_remote_addr",
                            index: "acme_egress_remote_addr",
                            align: "left",
                            width: 200
                        }, {
                            name: "client_ip_address",
                            index: "client_ip_address",
                            align: "left",
                            width: 100
                        }, {
                            name: "acct_unique_session_id",
                            index: "acct_unique_session_id",
                            align: "left",
                            width: 300
                        }],
                        sortname: "sbc_group_id, h323_setup_time",
                        rowNum: 100,
                        rowList: [100, 300, 500, 1000],
                        sortorder: "asc",
                        shrinkToFit: false, // 가로 스크롤
                        toolbarfilter: true,
                        viewrecords: true,
                        pager: "#gridToolbar",
                        autowidth: true,
                        onSelectRow: function (id) {
                            var id = jQuery("#gridCdrList").getGridParam("selrow");
                            if (id) {
                                var ret = jQuery("#gridCdrList").getRowData(id);
                                var sbc_group_id = ret.sbc_group_id;
                            }
                        },
                        ondblClickRow: function (rowId) {
                            //showUpdatePop();
                        },
                    });
                jQuery("#gridCdrList").jqGrid("navGrid", "#gridToolbar", {
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

                jQuery("#gridCdrList").jqGrid("filterToolbar", {
                    stringResult: true,
                    searchOnEnter: true,
                    defaultSearch: "cn",
                    ignoreCase: true
                });
                AotJqGrid.setGridStyle();
            });

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
                goCdrList();
            });

            $("#srch_sbc_group_id").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goCdrList();
                }
            });
            $("#srch_ing_time_s").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goCdrList();
                }
            });
            //숫자만
            $("#srch_ing_time_s").on('keyup', function () {
                $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
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
            $("#srch_ing_time_s").val("0");
        }

        function selectBoxInit() {
        }

        //진행중 CDR 조회
        function goCdrList() {
            if ($("#srch_input_date_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_date_from").focus();
                return;
            }
            if ($("#srch_input_hh_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_hh_from").focus();
                return;
            }
            if ($("#srch_input_mm_from").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_mm_from").focus();
                return;
            }
            if ($("#srch_input_date_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_date_to").focus();
                return;
            }
            if ($("#srch_input_hh_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_hh_to").focus();
                return;
            }
            if ($("#srch_input_mm_to").val() == "") {
                AotSmartAdmin.smallBoxWarning('<spring:message code="TEXT.CDR.SRCH.DATE" />는 필수입니다.');
                $("#srch_input_mm_to").focus();
                return;
            }
            if ($("#srch_sbc_group_id").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.CDR.SRCH.SBC_GROUP_NAME" />는 필수입니다.");
                $("#srch_sbc_group_id").focus();
                return;
            }
            if ($("#srch_ing_time_s").val() == "") {
                AotSmartAdmin.smallBoxWarning("<spring:message code="TEXT.CDR.SRCH.SBC_PROC_TIME_SECOND" />는 필수입니다.");
                $("#srch_ing_time_s").focus();
                return;
            }

            $("#gridCdrList").jqGrid('clearGridData');
            jQuery("#gridCdrList").setGridParam({
                url: "${CONTEXT_PATH}/cdr/ing.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_ING_LIST",
                    srch_input_date_from: $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val(),
                    srch_input_date_to: $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val(),
                    srch_sbc_group_id: $("#srch_sbc_group_id").val(),
                    srch_ing_time_s: $("#srch_ing_time_s").val()
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridCdrList").getGridParam("reccount") == 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            myForm.srchSidx.value = jQuery("#gridCdrList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridCdrList").getGridParam('sortorder');
            myForm.srch_date_from.value = $("#srch_input_date_from").val().replace(/-/gi, "") + $("#srch_input_hh_from").val() + $("#srch_input_mm_from").val();
            myForm.srch_date_to.value = $("#srch_input_date_to").val().replace(/-/gi, "") + $("#srch_input_hh_to").val() + $("#srch_input_mm_to").val();
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/cdr/ing.do', param);
        }
    </script>
    </body>
</aot:html>