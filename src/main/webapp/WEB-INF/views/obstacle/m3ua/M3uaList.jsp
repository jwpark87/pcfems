<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/m3ua/List.jsp
 - 설      명	: M2PA/M3UA 현황 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.05.04    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <style>
        /* 마우스 오버시 글자 검정색으로 */
        .ui-jqgrid .ui-state-hover td {
            background: #ecf3f8 !important;
            color: #000000 !important;
        }
    </style>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/obstacle/m3ua/m3ua.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="sortseq" id="sortseq"/>
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
                        <h1 class="page-title txt-color-blueDark">
                            <!-- PAGE HEADER -->
                            <i class="fa-fw fa fa-pencil-square-o"></i>
                                ${curr_menu_name }
                        </h1>
                    </div>

                    <!-- 상단 버튼들 -->
                    <div class="col-lg-4" style="float: right; height: 66px; padding-top: 10px; text-align: right;">

                        <a href="#" data-target="#m3uaExcelModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                        </a>
                        <a href="#" data-target="#m3uaInsertModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.REG"/>
                        </a>
                        <a href="#" data-target="#m3uaModModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                        </a>
                        <button type="button" class="btn btn-primary btn-sm" id="m3uaDel">
                            <spring:message code="TEXT.COMM.BTN.DELETE"/>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm" id="excel">
                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                        </button>
                    </div>

                </div>
                <!-- 상단화면명 END -->


                <section id="widget-grid" class="">
                    <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                    <div class="row">
                        <article class="col-sm-12">
                            <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false"
                                 data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-togglebutton="false" data-widget-sortable="false">
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

    <aot:select name="gd_link_status" id="gd_link_status" group="LINK_STATUS" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
    <aot:select name="gd_locality" id="gd_locality" group="LOCALITY" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
    <aot:select name="gd_dsbd_yn" id="gd_dsbd_yn" group="W001" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(
            function () {

                bindClickEvent();

                jQuery("#gridList").jqGrid(
                    {
                        url: "${CONTEXT_PATH}/obstacle/m3ua/m3ua.do",
                        postData: {
                            actionID: "ACTION_M3UA_LIST"
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
                        colNames: [
                            /* "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SORTSEQ"/>", */
                            "", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.LOCALITY"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.STATUS"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.IF_TYPE"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.COUNTRY_NM"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.CARRIER_NM"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.APC"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.ASP_ID"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.LOC_BOUNDARY"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.NA"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SLC"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.CLLI"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.ROUTING_CONTEXT"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SRC_PRIMARY_IP"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SRC_SECONDARY_IP"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SRC_PORT"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.DST_PRIMARY_IP"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.DST_SECONDARY_IP"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.DST_PORT"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.ASP_TYPE"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.SI"/>",
                            "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.ROUTING_CPU"/>", "<spring:message code="TEXT.OBSTACLE.M3UA.GRID.DSBD_YN"/>"],
                        colModel: [{
                            name: "sortseq",
                            index: "sortseq",
                            align: "left",
                            width: 100,
                            hidden: true
                        }, {
                            name: "locality",
                            index: "locality",
                            align: "left",
                            width: 100,
                            stype: "select",
                            searchoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_locality > option"))
                            },
                            edittype: "select",
                            editoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_locality > option"))
                            },
                            formatter: 'select'
                        }, {
                            name: "status",
                            index: "status",
                            align: "left",
                            width: 100,
                            stype: "select",
                            searchoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_link_status > option"))
                            },
                            edittype: "select",
                            editoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_link_status > option"))
                            },
                            formatter: 'select'
                        }, {
                            name: "if_type",
                            index: "if_type",
                            align: "left",
                            width: 100
                        }, {
                            name: "country_nm",
                            index: "country_nm",
                            align: "left",
                            width: 100
                        }, {
                            name: "carrier_nm",
                            index: "carrier_nm",
                            align: "left",
                            width: 100
                        }, {
                            name: "apc",
                            index: "apc",
                            align: "left",
                            width: 100
                        }, {
                            name: "asp_id",
                            index: "asp_id",
                            align: "left",
                            width: 100
                        }, {
                            name: "loc_boundary",
                            index: "loc_boundary",
                            align: "left",
                            width: 100
                        }, {
                            name: "na",
                            index: "na",
                            align: "left",
                            width: 100
                        }, {
                            name: "slc",
                            index: "slc",
                            align: "left",
                            width: 100
                        }, {
                            name: "clli",
                            index: "clli",
                            align: "left",
                            width: 150
                        }, {
                            name: "routing_context",
                            index: "routing_context",
                            align: "left",
                            width: 100
                        }, {
                            name: "src_primary_ip",
                            index: "src_primary_ip",
                            align: "left",
                            width: 150
                        }, {
                            name: "src_secondary_ip",
                            index: "src_secondary_ip",
                            align: "left",
                            width: 150
                        }, {
                            name: "src_port",
                            index: "src_port",
                            align: "left",
                            width: 100
                        }, {
                            name: "dst_primary_ip",
                            index: "dst_primary_ip",
                            align: "left",
                            width: 150
                        }, {
                            name: "dst_secondary_ip",
                            index: "dst_secondary_ip",
                            align: "left",
                            width: 150
                        }, {
                            name: "dst_port",
                            index: "dst_port",
                            align: "left",
                            width: 100
                        }, {
                            name: "asp_type",
                            index: "asp_type",
                            align: "left",
                            width: 200
                        }, {
                            name: "si",
                            index: "si",
                            align: "left",
                            width: 100
                        }, {
                            name: "routing_cpu",
                            index: "routing_cpu",
                            align: "left",
                            width: 100
                        }, {
                            name: "dsbd_yn",
                            index: "dsbd_yn",
                            align: "left",
                            width: 100,
                            stype: "select",
                            searchoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_dsbd_yn > option"))
                            },
                            edittype: "select",
                            editoptions: {
                                value: AotJqGrid.getSelectTagData($("#gd_dsbd_yn > option"))
                            },
                            formatter: 'select'
                        }],
                        sortname: "sortseq",
                        rowNum: 100,
                        rowList: [100, 300, 500, 1000],
                        sortorder: "desc",
                        shrinkToFit: false,
                        toolbarfilter: true,
                        viewrecords: true,
                        pager: "#gridToolbar",
                        autowidth: true,
                        onSelectRow: function (id) {
                            var id = jQuery("#gridList").getGridParam("selrow");
                            if (id) {
                                var ret = jQuery("#gridList").getRowData(id);
                                deatilInfo(ret);
                            }
                        },
                        loadComplete: function () {
                            var rowIds = $("#gridList").getDataIDs();
                            $.each(rowIds, function (idx, rowId) {
                                var rowData = $("#gridList").getRowData(rowId);
                                if (rowData.status == "F") {
                                    $("#gridList").setRowData(rowId, false, {
                                        background: "#a90329",
                                        color: "#fff"
                                    });
                                } else if (rowData.status == "D") {
                                    $("#gridList").setRowData(rowId, false, {
                                        background: "#FF5E00",
                                        color: "#fff"
                                    });
                                }
                            });
                        },
                        ondblClickRow: function (rowId) {
                            showUpdatePop();
                        },
                    });
                jQuery("#gridList").jqGrid("navGrid", "#gridToolbar", {
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

                //아이피 헤더 그룹
                jQuery("#gridList").jqGrid('setGroupHeaders', {
                    useColSpanStyle: true,
                    groupHeaders: [{
                        startColumnName: 'src_primary_ip',
                        numberOfColumns: 3,
                        titleText: '<center><b><spring:message code ='TEXT.OBSTACLE.M3UA.GRID.GROUP.SOURCE'/></center>'
                    }, {
                        startColumnName: 'dst_primary_ip',
                        numberOfColumns: 3,
                        titleText: '<center><b><spring:message code ='TEXT.OBSTACLE.M3UA.GRID.GROUP.DESTINATION'/></center>'
                    },]
                });

                jQuery("#gridList").jqGrid("filterToolbar", {
                    stringResult: true,
                    searchOnEnter: true,
                    defaultSearch: "cn",
                    ignoreCase: true
                });

                $("#gs_ts,#gs_shelf, #gs_slot, #gs_e1_port, #gs_time_slot, #gs_ch_number, #gs_slc, #gs_bearer_time_slot").on('keyup', function () {
                    $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
                });

            });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goM3uaList();
            });

            //삭제
            $("#m3uaDel").on('click', function () {
                if ($("#sortseq").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delM3ua();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#m3uaInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#m3uaModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#sortseq").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#m3uaModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });

            $('a[data-target="#m3uaExcelModal"]').on('click', function (ev) {
                ev.preventDefault();
                showExcelPop();
            });
            //엑셀 다운
            $("#excel").on('click', function () {
                doExcel();
            });
        }

        function showExcelPop() {
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/m3ua/m3uaexcelpop.pop');

            // load the url and show modal on success
            $("#m3uaExcelModal").load($(this).attr("href"), function () {
                $("#m3uaExcelModal").modal();
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/m3ua/m3uaInsertPopup.do');

            // load the url and show modal on success
            $("#m3uaInsertModal").load($(this).attr("href"), function () {
                $("#m3uaInsertModal").modal();
            });
        }

        function showUpdatePop() {
            var param = {
                sortseq: $("#sortseq").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/m3ua/m3uaModPopup.do');

            // load the url and show modal on success
            $("#m3uaModModal").load($(this).attr("href"), param, function () {
                $("#m3uaModModal").modal();
            });
        }

        //삭제
        function delM3ua() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/m3ua/m3ua.do", {
                actionID: "ACTION_M3UA_DELETE_OK",
                sortseq: $("#sortseq").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#sortseq").val("");
                    goM3uaList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(ret) {
            $("#sortseq").val(ret.sortseq);
        }

        //Realm별 국가 관리정보 취득
        function goM3uaList() {
            $("#gridList").jqGrid('clearGridData');
            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/obstacle/m3ua/m3ua.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_M3UA_LIST"
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridList").getGridParam("reccount") == 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            myForm.srchSidx.value = jQuery("#gridList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridList").getGridParam('sortorder');
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/obstacle/m3ua/m3ua.do', param);
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="m3uaInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="m3uaModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="m3uaExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
