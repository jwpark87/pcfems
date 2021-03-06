<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/link/List.jsp
 - 설      명	: Link 현황 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.05    1.0      KYM      신규작성
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

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/obstacle/link/link.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="apc" id="apc"/>
        <input type="hidden" name="slc" id="slc"/>
        <input type="hidden" name="actionID">
    </form>

    <c:set var="STATUS">
        <spring:message code="TEXT.OBSTACLE.LINK.SEARCH.STATUS"/>
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

                    <a href="#" data-target="#linkExcelModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                    </a>
                    <a href="#" data-target="#linkInsertModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#linkModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="linkDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
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
                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_apc" id="srch_apc" placeholder="APC">
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_slc" id="srch_slc" placeholder="SLC">
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="select">
                                    <aot:select name="srch_status" id="srch_status" group="LINK_STATUS" init="YES" initCode="" initName="${STATUS}" style="height:32px;"/>
                                    <i></i>
                                </label>
                            </section>

                            <button type="button" class="btn btn-primary btn-sm" id="srch">
                                <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                            </button>
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

    <aot:select name="gd_link_status" id="gd_link_status" group="LINK_STATUS" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {
            bindClickEvent();

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/obstacle/link/link.do",
                postData: {
                    actionID: "ACTION_LINK_LIST",
                    srch_apc: $("#srch_apc").val(),
                    srch_slc: $("#srch_slc").val(),
                    srch_status: $("#srch_status").val()
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
                    //"APC",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.APC"/>",
                    //"SLC",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.SLC"/>",
                    //"NA",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.NA"/>",
                    //"SLOT_CH",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.SLOT_CH"/>",
                    //"COUNTRY_NM",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.COUNTRY_NM"/>",
                    //"CARRIER_NM",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.CARRIER_NM"/>",
                    //"E1_CD",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.E1_CD"/>",
                    //"BEARRER_NM",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.BEARRER_NM"/>",
                    //"E1_TIME_SLOT",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.E1_TIME_SLOT"/>",
                    //"CABLE_NM",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.CABLE_NM"/>",
                    //"NDCS_TIE",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.NDCS_TIE"/>",
                    //"STATUS",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.STATUS"/>",
                    //"작업자",
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.UPD_ID"/>",
                    //"작업일시"
                    "<spring:message code="TEXT.OBSTACLE.LINK.GRID.UPD_DT"/>"],
                colModel: [{
                    name: "apc",
                    index: "apc",
                    align: "left",
                    width: 200
                }, {
                    name: "slc",
                    index: "slc",
                    align: "left",
                    width: 200
                }, {
                    name: "na",
                    index: "na",
                    align: "left",
                    width: 200
                }, {
                    name: "slot_ch",
                    index: "slot_ch",
                    align: "left",
                    width: 200
                }, {
                    name: "country_nm",
                    index: "country_nm",
                    align: "left",
                    width: 200
                }, {
                    name: "carrier_nm",
                    index: "carrier_nm",
                    align: "left",
                    width: 200
                }, {
                    name: "e1_cd",
                    index: "e1_cd",
                    align: "left",
                    width: 200
                }, {
                    name: "bearrer_nm",
                    index: "bearrer_nm",
                    align: "left",
                    width: 200
                }, {
                    name: "e1_time_slot",
                    index: "e1_time_slot",
                    align: "left",
                    width: 200
                }, {
                    name: "cable_nm",
                    index: "cable_nm",
                    align: "left",
                    width: 200
                }, {
                    name: "ndcs_tie",
                    index: "ndcs_tie",
                    align: "left",
                    width: 200
                }, {
                    name: 'status',
                    index: 'status',
                    width: 100,
                    align: 'center',
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#gd_link_status > option"))
                    },
                    formatter: 'select'
                }, {
                    name: "upd_id",
                    index: "upd_id",
                    align: "left",
                    width: 150
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    align: "center",
                    width: 150
                }],
                sortname: "apc",
                rowNum: 100,
                rowList: [100, 300, 500, 1000],
                sortorder: "asc",
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
        });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goLinkList();
            });

            $("#srch_apc").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goLinkList();
                }
            });
            $("#srch_slc").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goLinkList();
                }
            });

            //삭제
            $("#linkDel").on('click', function () {
                if ($("#apc").val() == "" && $("#slc").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delLink();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#linkInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#linkModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#apc").val() == "" && $("#slc").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#linkModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });

            $('a[data-target="#linkExcelModal"]').on('click', function (ev) {
                ev.preventDefault();
                showExcelPop();
            });
            //엑셀 다운
            $("#excel").on('click', function () {
                doExcel();
            });
        }

        function showExcelPop() {
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/link/linkexcelpop.pop');

            // load the url and show modal on success
            $("#linkExcelModal").load($(this).attr("href"), function () {
                $("#linkExcelModal").modal();
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/link/linkInsertPopup.do');

            // load the url and show modal on success
            $("#linkInsertModal").load($(this).attr("href"), function () {
                $("#linkInsertModal").modal();
                $("#add_slot_ch").mask("99/99")
            });
        }

        function showUpdatePop() {
            var param = {
                apc: $("#apc").val(),
                slc: $("#slc").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/obstacle/link/linkModPopup.do');

            // load the url and show modal on success
            $("#linkModModal").load($(this).attr("href"), param, function () {
                $("#linkModModal").modal();
                $("#mod_slot_ch").mask("99/99")
            });
        }

        //삭제
        function delLink() {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/link/link.do", {
                actionID: "ACTION_LINK_DELETE_OK",
                apc: $("#apc").val(),
                slc: $("#slc").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#apc").val("");
                    $("#slc").val("");
                    goLinkList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(ret) {
            $("#apc").val(ret.apc);
            $("#slc").val(ret.slc);
        }

        //Realm별 국가 관리정보 취득
        function goLinkList() {
            $("#gridList").jqGrid('clearGridData');
            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/obstacle/link/link.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_LINK_LIST",
                    srch_apc: $("#srch_apc").val(),
                    srch_slc: $("#srch_slc").val(),
                    srch_status: $("#srch_status").val()
                }
            }).trigger("reloadGrid");
        }

        //엑셀
        function doExcel() {
            if ($("#gridList").getGridParam("reccount") === 0) {
                AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.ALERT.NO_DATA'/>");
                return;
            }
            myForm.srchSidx.value = jQuery("#gridList").getGridParam('sortname');
            myForm.srchSord.value = jQuery("#gridList").getGridParam('sortorder');
            var param = AotCommon.formToJsonParam(myForm);
            param['actionID'] = 'ACTION_GET_EXCEL';
            AotCommon.submitFormPOST('${CONTEXT_PATH}/obstacle/link/link.do', param);
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="linkInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="linkModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="linkExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
