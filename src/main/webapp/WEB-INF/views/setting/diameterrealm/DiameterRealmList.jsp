<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/diameterrealm/List.jsp
 - 설      명	: Diameter Realm 관리 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.04.28    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <c:set var="LOCALITY">
        <spring:message code="TEXT.SETTING.DIAMETER.REALM.SEARCH.LOCALITY"/>
    </c:set>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/setting/diameter/diameterrealm.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="realm" id="realm"/>
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

                        <a href="#" data-target="#diameterRealmExcelModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                        </a>
                            <%-- <a  href="#" class="btn btn-primary btn-sm" id="excelPop">
                             <spring:message code="TEXT.COMM.BTN.EXCEL_UPLOAD"/>
                        </a> --%>
                        <a href="#" data-target="#diameterRealmInsertModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.REG"/>
                        </a>
                        <a href="#" data-target="#diameterRealmModModal" class="btn btn-primary btn-sm">
                            <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                        </a>
                        <button type="button" class="btn btn-primary btn-sm" id="diameterRealmDel">
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
                                            <input type="text" name="srch_routing_peer" id="srch_routing_peer" placeholder="Routing Peer">
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

    <aot:select name="gd_locality" id="gd_locality" group="LOCALITY" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>
    <aot:select name="gd_dsbd_yn" id="gd_dsbd_yn" group="W001" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(
            function () {

                bindClickEvent();

                jQuery("#gridList").jqGrid(
                    {
                        url: "${CONTEXT_PATH}/setting/diameter/diameterrealm.do",
                        postData: {
                            actionID: "ACTION_DIAMETER_REALM_LIST",
                            srch_locality: $("#srch_locality").val(),
                            srch_realm: $("#srch_realm").val(),
                            srch_routing_peer: $("#srch_routing_peer").val()
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
                        colNames: ["<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.LOCALITY"/>", "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.COUNTRY"/>",
                            "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.CARRIER_NM"/>", "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.ROUTING_PEER"/>",
                            "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.REALM"/>", "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.CONTACT_EMAIL"/>",
                            "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.OPEN_DT"/>", "<spring:message code="TEXT.SETTING.DIAMETER.REALM.GRID.DSBD_YN"/>"

                            /*    			  //"작업자",
                             "<spring:message code="TEXT.SETTING.DIAMETER.GRID.UPD_ID"/>",
								 //"작업일시"
								 "<spring:message code="TEXT.SETTING.DIAMETER.GRID.UPD_DT"/>" */
                        ],
                        colModel: [{
                            name: "locality",
                            index: "locality",
                            align: "left",
                            width: 200,
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
                            name: "country",
                            index: "country",
                            align: "left",
                            width: 200
                        }, {
                            name: "carrier_nm",
                            index: "carrier_nm",
                            align: "left",
                            width: 300
                        }, {
                            name: "routing_peer",
                            index: "routing_peer",
                            align: "left",
                            width: 200
                        }, {
                            name: "realm",
                            index: "realm",
                            align: "left",
                            width: 300
                        }, {
                            name: "contact_email",
                            index: "contact_email",
                            align: "left",
                            width: 320
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
                                var realm = ret.realm;
                                var locality = ret.locality;
                                deatilInfo(realm, locality);
                            }
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

                jQuery("#gridList").jqGrid("filterToolbar", {
                    stringResult: true,
                    searchOnEnter: true,
                    defaultSearch: "cn",
                    ignoreCase: true
                });
                AotJqGrid.setGridStyle();

            });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goDiameterRealmList();
            });

            /* $("#srch_realm").on('keydown', function (key) {
                if(key.keyCode == 13){
                    goDiameterRealmList();
                }
            });
            $("#srch_routing_peer").on('keydown', function (key) {
                if(key.keyCode == 13){
                    goDiameterRealmList();
                }
            }); */

            $("#excelPop").on('click', function () {
                AotCommon.goWindow('${CONTEXT_PATH}/setting/diameter/diameterexcelpop.pop', {
                    width: 530,
                    height: 400
                });

                document.myForm.action = "${CONTEXT_PATH}/setting/diameter/diameterrealm.do";
                document.myForm.target = "";
                document.myForm.actionID.value = "";
            });

            //삭제
            $("#diameterRealmDel").on('click', function () {
                if ($("#realm").val() == "" && $("#locality").val()) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delDiameterRealm();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#diameterRealmInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#diameterRealmModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#realm").val() == "" && $("#locality").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#diameterRealmModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });

            //업로드 모달 호출
            $('a[data-target="#diameterRealmExcelModal"]').on('click', function (ev) {
                ev.preventDefault();
                showExcelPop();
            });
            //엑셀 다운
            $("#excel").on('click', function () {
                doExcel();
            });
        }

        function showExcelPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterrealmexcelpop.pop');

            // load the url and show modal on success
            $("#diameterRealmExcelModal").load($(this).attr("href"), function () {
                $("#diameterRealmExcelModal").modal();
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterRealmInsertPopup.do');

            // load the url and show modal on success
            $("#diameterRealmInsertModal").load($(this).attr("href"), function () {
                $("#diameterRealmInsertModal").modal();
            });
        }

        function showUpdatePop() {
            var param = {
                realm: $("#realm").val(),
                locality: $("#locality").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/setting/diameter/diameterRealmModPopup.do');

            // load the url and show modal on success
            $("#diameterRealmModModal").load($(this).attr("href"), param, function () {
                $("#diameterRealmModModal").modal();
            });
        }

        //삭제
        function delDiameterRealm() {
            AotAjax.excute("${CONTEXT_PATH}/setting/diameter/diameterrealm.do", {
                actionID: "ACTION_DIAMETER_REALM_DELETE_OK",
                realm: $("#realm").val(),
                locality: $("#locality").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#realm").val("");
                    $("#locality").val("");
                    goDiameterRealmList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(realm, locality) {
            $("#realm").val(realm);
            $("#locality").val(locality)
        }

        //Realm별 국가 관리정보 취득
        function goDiameterRealmList() {
            $("#gridList").jqGrid('clearGridData');
            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/setting/diameter/diameterrealm.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_DIAMETER_REALM_LIST"
                    /* ,srch_locality : $("#srch_locality").val()
                    ,srch_realm: $("#srch_realm").val()
                    ,srch_routing_peer: $("#srch_routing_peer").val() */
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
            AotCommon.submitFormPOST('${CONTEXT_PATH}/setting/diameter/diameterrealm.do', param);
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="diameterRealmInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="diameterRealmModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="diameterRealmExcelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
