<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameter/List.jsp
 - 설      명	: Diameter Host 관리 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.03.02    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <c:set var="LOCALITY">
        <spring:message code="TEXT.SETTING.DIAMETER.SEARCH.LOCALITY"/>
    </c:set>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/setting/diameter/diameter.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="host" id="host"/>
        <input type="hidden" name="locality" id="locality"/>
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

                        <a href="#" data-target="#diameterExcelModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                        </a>
                            <%-- <a  href="#" class="btn btn-primary btn-sm" id="excelPop">
                             <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                        </a> --%>
                        <a href="#" data-target="#diameterInsertModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.REG"/>
                        </a>
                        <a href="#" data-target="#diameterModModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                        </a>
                        <button type="button" class="btn btn-primary btn-sm" id="diameterDel">
                            <spring:message code="TEXT.COMM.BTN.DELETE"/>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm" id="excel">
                            <spring:message code="TEXT.COMM.BTN.EXCEL"/>
                        </button>
                    </div>

                </div>
                <!-- 상단화면명 END -->

                <!-- 검색박스 START -->
                    <%-- <div class="row">
                    <div class="col-sm-12">
                        <div class="well">
                            <div class="smart-form">

                                    <section class="col col-2">
                                        <label class="select">
                                            <aot:select name="srch_locality" id="srch_locality" group="LOCALITY" init="YES"
                                            initCode="" initName="${LOCALITY}" style="height:32px;"/>
                                            <i></i>
                                        </label>
                                    </section>

                                    <section class="col col-2">
                                        <label class="input">
                                            <input type="text" name="srch_realm" id="srch_realm" placeholder="Realm">
                                        </label>
                                    </section>

                                    <section class="col col-2">
                                        <label class="input">
                                            <input type="text" name="srch_host" id="srch_host" placeholder="Host">
                                        </label>
                                    </section>

                                    <button type="button" class="btn btn-primary btn-sm" id="srch">
                                        <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                                    </button>
                            </div>
                        </div>
                    </div>
                </div> --%>
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

    </form>

    <aot:select name="gd_link_status" id="gd_link_status" group="LINK_STATUS" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
    <aot:select name="gd_locality" id="gd_locality" group="LOCALITY" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
    <aot:select name="gd_dsbd_yn" id="gd_dsbd_yn" group="W001" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {

            bindClickEvent();

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/setting/diameter/diameter.do",
                postData: {
                    actionID: "ACTION_DIAMETER_LIST"
                    /* ,srch_locality : $("#srch_locality").val()
                    ,srch_realm: $("#srch_realm").val()
                    ,srch_host: $("#srch_host").val() */
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
                colNames: ["<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCALITY"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.STATUS"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.COUNTRY_NM"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CARRIER_NM"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCAL"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.REALM"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.HOST"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.REMOTE_PEER_MODE"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCAL_SLOT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCAL_PRIMARY_IP"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCAL_SECONDARY_IP"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.LOCAL_PORT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.REMOTE_PRIMARY_IP"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.REMOTE_SECONDARY_IP"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.REMOTE_PORT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.ACCESS_CTRL_LIST"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.INTERFACE"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_TW_TIMER"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_HB_INTERVAL"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_ASSOC_MAX_RETRANS"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_RTO_INIT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_RTO_MIN"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_RTO_MAX"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_PATH_MAX_RETRANS"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_MAX_INIT_RETRANS"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_MAX_SEG"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.SCTP_SACK_TIMEOUT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CONTACT_NAME"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CONTACT_EMAIL"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CONTACT_NOC"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CONTACT_FIX_PHONE"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.CONTACT_MOBILE_PHONE"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.VENDOR_NAME"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.OPEN_DT"/>",

                    "<spring:message code="TEXT.SETTING.DIAMETER.GRID.DSBD_YN"/>"

                    /*    			  //"작업자",
                     "<spring:message code="TEXT.SETTING.DIAMETER.GRID.UPD_ID"/>",
				 //"작업일시"
				 "<spring:message code="TEXT.SETTING.DIAMETER.GRID.UPD_DT"/>" */
                ],
                colModel: [{
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
                    name: "local",
                    index: "local",
                    align: "left",
                    width: 100
                }, {
                    name: "realm",
                    index: "realm",
                    align: "left",
                    width: 100
                }, {
                    name: "host",
                    index: "host",
                    align: "left",
                    width: 100
                }, {
                    name: "remote_peer_mode",
                    index: "remote_peer_mode",
                    align: "left",
                    width: 100
                }, {
                    name: "local_slot",
                    index: "local_slot",
                    align: "left",
                    width: 100
                }, {
                    name: "local_primary_ip",
                    index: "local_primary_ip",
                    align: "left",
                    width: 100
                }, {
                    name: "local_secondary_ip",
                    index: "local_secondary_ip",
                    align: "left",
                    width: 100
                }, {
                    name: "local_port",
                    index: "local_port",
                    align: "left",
                    width: 100
                }, {
                    name: "remote_primary_ip",
                    index: "remote_primary_ip",
                    align: "left",
                    width: 100
                }, {
                    name: "remote_secondary_ip",
                    index: "remote_secondary_ip",
                    align: "left",
                    width: 100
                }, {
                    name: "remote_port",
                    index: "remote_port",
                    align: "left",
                    width: 100
                }, {
                    name: "access_ctrl_list",
                    index: "access_ctrl_list",
                    align: "left",
                    width: 100
                }, {
                    name: "strInterface",
                    index: "strInterface",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_tw_timer",
                    index: "sctp_tw_timer",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_hb_interval",
                    index: "sctp_hb_interval",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_assoc_max_retrans",
                    index: "sctp_assoc_max_retrans",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_rto_init",
                    index: "sctp_rto_init",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_rto_min",
                    index: "sctp_rto_min",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_rto_max",
                    index: "sctp_rto_max",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_path_max_retrans",
                    index: "sctp_path_max_retrans",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_max_init_retrans",
                    index: "sctp_max_init_retrans",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_max_seg",
                    index: "sctp_max_seg",
                    align: "left",
                    width: 100
                }, {
                    name: "sctp_sack_timeout",
                    index: "sctp_sack_timeout",
                    align: "left",
                    width: 100
                }, {
                    name: "contact_name",
                    index: "contact_name",
                    align: "left",
                    width: 100
                }, {
                    name: "contact_email",
                    index: "contact_email",
                    align: "left",
                    width: 100
                }, {
                    name: "contact_noc",
                    index: "contact_noc",
                    align: "left",
                    width: 100
                }, {
                    name: "contact_fix_phone",
                    index: "contact_fix_phone",
                    align: "left",
                    width: 100
                }, {
                    name: "contact_mobile_phone",
                    index: "contact_mobile_phone",
                    align: "left",
                    width: 100
                }, {
                    name: "vendor_name",
                    index: "vendor_name",
                    align: "left",
                    width: 100
                }, {
                    name: "open_dt",
                    index: "open_dt",
                    align: "left",
                    width: 100,
                    search: false
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
                }

                    /* {name:"upd_id"				,index:"upd_id"				,align:"left"  ,width:150},
                    {name:"upd_dt"				,index:"upd_dt"			,align:"center",width:150} */
                ],
                sortname: "locality",
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
                        var host = ret.host;
                        var locality = ret.locality;
                        deatilInfo(host, locality);
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

            //아이피 헤더 그룹
            jQuery("#gridList").jqGrid('setGroupHeaders', {
                useColSpanStyle: true,
                groupHeaders: [{
                    startColumnName: 'local_slot',
                    numberOfColumns: 4,
                    titleText: '<center><b><spring:message code ='TEXT.SETTING.DIAMETER.GRID.HEADER.GROUP.LOCAL_IP_ADDRESS'/></center>'
                }, {
                    startColumnName: 'remote_primary_ip',
                    numberOfColumns: 3,
                    titleText: '<center><b><spring:message code ='TEXT.SETTING.DIAMETER.GRID.HEADER.GROUP.REMOTE_IP_ADDRESS'/></center>'
                }, {
                    startColumnName: 'sctp_tw_timer',
                    numberOfColumns: 10,
                    titleText: '<center><b><spring:message code ='TEXT.SETTING.DIAMETER.GRID.HEADER.GROUP.SCTP'/></center>'
                }, {
                    startColumnName: 'contact_name',
                    numberOfColumns: 5,
                    titleText: '<center><b><spring:message code ='TEXT.SETTING.DIAMETER.GRID.HEADER.GROUP.CONTRACT'/></center>'
                }]
            });

            jQuery("#gridList").jqGrid("filterToolbar", {
                stringResult: true,
                searchOnEnter: true,
                defaultSearch: "cn",
                ignoreCase: true
            });
            AotJqGrid.setGridStyle();
            $("#gs_local_port,#gs_remote_port, #gs_sctp_tw_timer, #gs_sctp_hb_interval, #gs_sctp_assoc_max_retrans, #gs_sctp_rto_init," +

                "#gs_sctp_rto_min, #gs_sctp_rto_max, #gs_sctp_path_max_retrans , #gs_sctp_max_init_retrans, #gs_sctp_max_seg, #gs_sctp_sack_timeout").on('keyup', function () {
                $(this).val($(this).val().replace(/[^0-9_#]/g, ""));
            });

        });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goDiameterList();
            });

            /* $("#srch_realm").on('keydown', function (key) {
                if(key.keyCode == 13){
                    goDiameterList();
                }
            });
            $("#srch_host").on('keydown', function (key) {
                if(key.keyCode == 13){
                    goDiameterList();
                }
            }); */

            $("#excelPop").on('click', function () {
                AotCommon.goWindow('${CONTEXT_PATH}/setting/diameter/diameterexcelpop.pop', {
                    width: 530,
                    height: 400
                });

                document.myForm.action = "${CONTEXT_PATH}/setting/diameter/diameter.do";
                document.myForm.target = "";
                document.myForm.actionID.value = "";
            });

            //삭제
            $("#diameterDel").on('click', function () {
                if ($("#host").val() == "" && $("#locality").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delDiameter();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#diameterInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#diameterModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#host").val() == "" && $("#locality").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#diameterModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });

            //업로드 모달 호출
            $('a[data-target="#diameterExcelModal"]').on('click', function (ev) {
                ev.preventDefault();
                showExcelPop();
            });
            //엑셀 다운
            $("#excel").on('click', function () {
                doExcel();
            });
        }

        function showExcelPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterexcelpop.pop');

            // load the url and show modal on success
            $("#diameterExcelModal").load($(this).attr("href"), function () {
                $("#diameterExcelModal").modal();
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterInsertPopup.do');

            // load the url and show modal on success
            $("#diameterInsertModal").load($(this).attr("href"), function () {
                $("#diameterInsertModal").modal();
            });
        }

        function showUpdatePop() {
            var param = {
                host: $("#host").val(),
                locality: $("#locality").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterModPopup.do');

            // load the url and show modal on success
            $("#diameterModModal").load($(this).attr("href"), param, function () {
                $("#diameterModModal").modal();
            });
        }

        //삭제
        function delDiameter() {
            AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameter.do", {
                actionID: "ACTION_DIAMETER_DELETE_OK",
                host: $("#host").val(),
                locality: $("#locality").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#host").val("");
                    $("#locality").val("");
                    goDiameterList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(host, locality) {
            $("#host").val(host);
            $("#locality").val(locality);
        }

        //Realm별 국가 관리정보 취득
        function goDiameterList() {
            $("#gridList").jqGrid('clearGridData');
            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/setting/diameter/diameter.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_DIAMETER_LIST"
                    /* ,srch_locality : $("#srch_locality").val()
                    ,srch_realm: $("#srch_realm").val()
                    ,srch_host: $("#srch_host").val() */
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
            AotCommon.submitFormPOST('${CONTEXT_PATH}/setting/diameter/diameter.do', param);
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="diameterInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="diameterModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="diameterExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
