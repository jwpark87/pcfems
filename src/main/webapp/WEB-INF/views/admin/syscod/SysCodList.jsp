<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/syscod/List.jsp
 - 설      명	: 시스템그룹코드관리 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2017.12.21    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/jsp/common/error/systemException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form id="myForm" name="myForm">
        <input type="hidden" id="sel_grcode" name="sel_grcode">
        <input type="hidden" id="sel_code" name="sel_code">
    </form>

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

            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-pencil-square-o"></i>
                            ${curr_menu_name }
                    </h1>
                </div>
            </div>
            <div class="row text-right" style="margin-bottom: 5px;">
                <div class="col-sm-12">
                    <a href="#" data-target="#grCdModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#grCdModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="grCdDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>
            </div>

            <div class="row" id="gridrow">
                <div class="col-sm-12">
                    <table id="gridList"></table>
                    <div id="gridToolbar"></div>
                </div>
            </div>

            <div class="row text-right" style="margin-top: 30px; margin-bottom: 5px;">
                <div class="col-sm-12">
                    <a href="#" data-target="#grCdDtModal" class="btn btn-success btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#grCdDtModModal" class="btn btn-success btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-success btn-sm" id="grCdDtDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>
            </div>


            <div class="row" id="gridrow">
                <div class="col-sm-12">
                    <table id="gridList2"></table>
                    <div id="gridToolbar2"></div>
                </div>
            </div>


        </div>

    </div>
    <!-- Script 영역 -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {
            bindClickEvent();

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/admin/syscod/syscod.do",
                postData: {
                    actionID: "ACTION_SYSCOD_LIST"
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
                height: 200,
                colNames: [
                    //그룹코드
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE'/>",
                    //그룹코드명
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_K'/>",
                    //그룹코드명
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_E'/>",
                    //그룹코드명
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_J'/>",
                    //작업일시
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.WORKER_DATE'/>",
                    //작업자
                    "<spring:message code='TEXT.SYS.MGMT.POPUP.WORKER_NAME'/>"],
                colModel: [{
                    name: "grcode",
                    index: "grcode",
                    width: 230
                }, {
                    name: "grcodenm_k",
                    index: "grcodenm_k",
                    width: 230
                }, {
                    name: "grcodenm_e",
                    index: "grcodenm_e",
                    width: 230
                }, {
                    name: "grcodenm_j",
                    index: "grcodenm_j",
                    width: 230
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    width: 180,
                    align: "center",
                    search: false
                }, {
                    name: "upd_id",
                    index: "upd_id",
                    width: 138,
                    align: "center",
                    search: false
                }],
                sortname: "grcode",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridList").getRowData(id);
                        //상세 그리드 리로드
                        GridReload2(ret.grcode);
                        //선택한 그룹 코드
                        $("#sel_grcode").val(ret.grcode);
                        $("#sel_code").val("");
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
                {}, {}, {}, {}, {
                    width: 500
                });
            jQuery("#gridList").jqGrid("filterToolbar", {
                stringResult: true,
                searchOnEnter: false,
                defaultSearch: "cn",
                ignoreCase: true
            });

            jQuery("#gridList2").jqGrid({
                url: "${CONTEXT_PATH}/admin/syscod/syscod.do",
                postData: {
                    actionID: "ACTION_SYSCOD_LIST_AJAX"
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
                height: 200,
                colNames: ["",
                    //상세 코드
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE'/>",
                    //코드명
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_K'/>",
                    //코드명
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_E'/>",
                    //코드명
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_J'/>",
                    //사용여부
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.USEYN'/>", "", "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.LEVEL'/>", "",
                    //작업일시
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_WORKER_DT'/>",
                    //작업자
                    "<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_WORKER_NAME'/>"],
                colModel: [{
                    name: "grcode",
                    index: "code",
                    width: 230,
                    hidden: true
                }, {
                    name: "code",
                    index: "code",
                    width: 230
                }, {
                    name: "codenm_k",
                    index: "codenm_k",
                    width: 230
                }, {
                    name: "codenm_e",
                    index: "codenm_e",
                    width: 230
                }, {
                    name: "codenm_j",
                    index: "codenm_j",
                    width: 230
                }, {
                    name: "use_yn_nm",
                    index: "use_yn_nm",
                    width: 230
                }, {
                    name: "use_yn",
                    index: "use_yn",
                    width: 230,
                    hidden: true
                }, {
                    name: "levelcod",
                    index: "levelcod",
                    width: 230,
                    hidden: true
                }, {
                    name: "levelcodNm",
                    index: "levelcodNm",
                    width: 230
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    width: 180,
                    align: "center"
                }, {
                    name: "upd_id",
                    index: "upd_id",
                    width: 138,
                    align: "center"
                }],
                sortname: "grcode",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar2",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridList2").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridList2").getRowData(id);
                        //선택한 코드값
                        $("#sel_code").val(ret.code);
                    }
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

            AotJqGrid.setGridStyle();
        });

        /* 그룹 코드 목록 리로드 */
        function GridReload(sort, order) {
            if (sort == null || sort == "") {
                sort = "grcode";
            }

            if (order == null || order == "") {
                order = "asc";
            }

            $("#gridList").jqGrid('clearGridData');
            var param = {
                actionID: "ACTION_SYSCOD_LIST"
            };
            $("#gridList").jqGrid('setGridParam', {
                url: "${CONTEXT_PATH}/admin/syscod/syscod.do",
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

        /* 상세 코드 목록 리로드 */
        function GridReload2(grcode) {
            $("#gridList2").jqGrid('clearGridData');
            var param = {
                actionID: "ACTION_SYSCOD_LIST_AJAX",
                grcode: grcode
            };
            $("#gridList2").jqGrid('setGridParam', {
                url: "${CONTEXT_PATH}/admin/syscod/syscod.do",
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: "sortseq",
                sortorder: "asc",
                page: 1,
                postData: param,
                search: true,
            }).trigger("reloadGrid");
        }

        function bindClickEvent() {
            //그룹 코드 등록 모달 호출
            $('a[data-target="#grCdModal"]').on('click', function (ev) {
                ev.preventDefault();
                $(this).attr('href', '${CONTEXT_PATH}/admin/syscod/grcodePopup.do');

                // load the url and show modal on success
                $("#grCdModal").load($(this).attr("href"), function () {
                    $("#grCdModal").modal();
                });
            });

            //그룹 코드 수정 모달 호출
            $('a[data-target="#grCdModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#sel_grcode").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#grCdModModal").modal("hide");
                } else {
                    var selrow = $("#gridList").jqGrid('getGridParam', 'selrow');
                    var grid1 = $("#gridList").jqGrid('getRowData', selrow);
                    var params = {
                        grcode: $("#sel_grcode").val(),
                        grcodenm_k: grid1.grcodenm_k,
                        grcodenm_e: grid1.grcodenm_e,
                        grcodenm_j: grid1.grcodenm_j
                    };
                    var str = jQuery.param(params);

                    $(this).attr('href', '${CONTEXT_PATH}/admin/syscod/grcodeModPopup.do?' + str);

                    // load the url and show modal on success
                    $("#grCdModModal").load($(this).attr("href"), function () {
                        $("#grCdModModal").modal();
                    });
                }
            });
            //삭제
            $("#grCdDel").on('click', function () {
                if ($("#sel_grcode").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delGrCd();
                    });
                }
            });

            /*****************************상세 코드*************************************/
            //코드 등록 모달 호출
            $('a[data-target="#grCdDtModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#sel_grcode").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#grCdDtModal").modal("hide");
                } else {
                    $(this).attr('href', '${CONTEXT_PATH}/admin/syscod/codePopup.do?grcode=' + $("#sel_grcode").val());

                    // load the url and show modal on success
                    $("#grCdDtModal").load($(this).attr("href"), function () {
                        $("#grCdDtModal").modal();
                    });
                }
            });

            //코드 수정 모달 호출
            $('a[data-target="#grCdDtModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#sel_code").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#grCdDtModModal").modal("hide");
                } else {
                    var selrow = $("#gridList2").jqGrid('getGridParam', 'selrow');
                    var grid2 = $("#gridList2").jqGrid('getRowData', selrow);
                    var params = {
                        grcode: $("#sel_grcode").val(),
                        code: $("#sel_code").val(),
                        codenm_k: grid2.codenm_k,
                        codenm_e: grid2.codenm_e,
                        codenm_j: grid2.codenm_j,
                        levelcod: grid2.levelcod,
                        use_yn: grid2.use_yn
                    };
                    var str = jQuery.param(params);

                    $(this).attr('href', '${CONTEXT_PATH}/admin/syscod/codeModPopup.do?' + str);

                    // load the url and show modal on success
                    $("#grCdDtModModal").load($(this).attr("href"), function () {
                        $("#grCdDtModModal").modal();
                    });
                }
            });
            //상세 코드 삭제
            $("#grCdDtDel").on('click', function () {
                if ($("#sel_code").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
                        delCd();
                    }
                }
            });

        }

        //그룹코드 삭제 전 상세 코드 체크
        function delGrCd() {
            AotAjax.excute("${CONTEXT_PATH}/admin/syscod/syscod.do", {
                actionID: "ACTION_SYSCOD_DELETE_CNT",
                grcode: $("#sel_grcode").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                if (response.returnMsg <= 0) {
                    GrCodeDelete();

                } else {
                    AotSmartAdmin.smallBoxWarning("<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODE_DEL_EXIST_CD'/>", '');

                }
            });
        }

        //그룹코드 삭제
        function GrCodeDelete() {
            AotAjax.excute("${CONTEXT_PATH}/admin/syscod/syscod.do", {
                actionID: "ACTION_SYSCOD_DELETE_OK",
                grcode: $("#sel_grcode").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    GridReload();
                    $("#gridList2").jqGrid('clearGridData');
                    $("#sel_grcode").val("");

                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);

                }
            });
        }

        //상세 코드 삭제
        function delCd() {
            AotAjax.excute("${CONTEXT_PATH}/admin/syscod/syscod.do", {
                actionID: "ACTION_SYSCODDTL_DELETE_OK",
                grcode: $("#sel_grcode").val(),
                code: $("#sel_code").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    GridReload2($("#sel_grcode").val());
                    $("#sel_code").val("");

                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);

                }
            });
        }
    </script>
    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- 그룹코드 -->
    <div class="modal fade" id="grCdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="grCdModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

    <!-- 그룹코드 상세-->
    <div class="modal fade" id="grCdDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="grCdDtModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>


    </body>
</aot:html>