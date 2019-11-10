<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/groupmenu/List.jsp
 - 설      명	: SBC설정관리 화면
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
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/setting/sbc/sbc.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="sbc_node_name" id="sbc_node_name"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
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


                    <a href="#" data-target="#sbcInsertModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#sbcModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="sbcDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>

            </div>
            <!-- 상단화면명 END -->

            <!-- 검색박스 START -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="well">
                        <div class="smart-form">
                            <section class="col" style="width: 10.33%;">
                                <div class="btn-group" style="margin-right: 19px;">
                                    <!-- 버튼 Select START -->
                                    <button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" id="btn_srch_sbc_group_id"></button>
                                    <ul class="dropdown-menu" id="srch_sbc_group_id">
                                    </ul>
                                    <!-- 버튼 Select END -->
                                </div>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_sbc_node_name" id="srch_sbc_node_name" placeholder="SBC Name">
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_sbc_node_ip" id="srch_sbc_node_ip" placeholder="SBC IP">
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
                                    <table id="gridSbcList"></table>
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

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        var dbSrchGroupName = "";

        $(document).ready(function () {

            selectBoxInit();
            bindClickEvent();

            jQuery("#gridSbcList").jqGrid({
                url: "${CONTEXT_PATH}/setting/sbc/sbc.do",
                postData: {
                    actionID: "ACTION_SBC_LIST",
                    srch_sbc_group_id: dbSrchGroupName,
                    srch_sbc_node_name: $("#srch_sbc_node_name").val(),
                    srch_sbc_node_ip: $("#srch_sbc_node_ip").val()
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
                    //"그룹아이디",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.SBC_GROUP_ID"/>",
                    //"그룹명",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.SBC_GROUP_NAME"/>",
                    //"SBC노드명칭",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.SBC_NODE_NAME"/>",
                    //"SBC노드IP",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.SBC_NODE_IP"/>",
                    //"비고",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.REMARK1"/>",
                    //"수정자",
                    "<spring:message code="TEXT.SETTING.SBC.GRID.UPD_ID_NAME"/>",
                    //"수정일시"
                    "<spring:message code="TEXT.SETTING.SBC.GRID.UPD_DT"/>"],
                colModel: [{
                    name: "sbc_group_id",
                    index: "sbc_group_id",
                    align: "left",
                    width: 100
                }, {
                    name: "sbc_group_name",
                    index: "sbc_group_name",
                    align: "left",
                    width: 150
                }, {
                    name: "sbc_node_name",
                    index: "sbc_node_name",
                    align: "left",
                    width: 200
                }, {
                    name: "sbc_node_ip",
                    index: "sbc_node_ip",
                    align: "left",
                    width: 150
                }, {
                    name: "remark1",
                    index: "remark1",
                    align: "left",
                    width: 300
                }, {
                    name: "upd_id_name",
                    index: "upd_id_name",
                    align: "left",
                    width: 100
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    align: "center",
                    width: 100
                }],
                sortname: "sbc_group_name, sbc_node_name",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridSbcList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridSbcList").getRowData(id);
                        var sbc_node_name = ret.sbc_node_name;
                        deatilInfo(sbc_node_name);
                    }
                },
                ondblClickRow: function (rowId) {
                    showUpdatePop();
                },
            });
            jQuery("#gridSbcList").jqGrid("navGrid", "#gridToolbar", {
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
                goSbcList();
            });

            $("#srch_sbc_node_name").on('keydown', function (key) {
                if (key.keyCode == 13) {
                    goSbcList();
                }
            });
            $("#srch_sbc_node_ip").on('keydown', function (key) {
                if (key.keyCode == 13) {
                    goSbcList();
                }
            });

            //삭제
            $("#sbcDel").on('click', function () {
                if ($("#sbc_node_name").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delSbc();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#sbcInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#sbcModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#sbc_node_name").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#sbcModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/sbc/sbcInsertPopup.do');

            // load the url and show modal on success
            $("#sbcInsertModal").load($(this).attr("href"), function () {
                $("#sbcInsertModal").modal();
            });
        }

        function showUpdatePop() {
            var sbc_node_name = $("#sbc_node_name").val().replace(/#/g, '@'); //-> 모든 #을 @로 치환(팝업을 띄우기위해 href에 get방식으로 넘기기 위해)
            $(this).attr('href', '${CONTEXT_PATH}/setting/sbc/sbcModPopup.do?sbc_node_name=' + sbc_node_name);

            // load the url and show modal on success
            $("#sbcModModal").load($(this).attr("href"), function () {
                $("#sbcModModal").modal();
            });
        }

        //삭제
        function delSbc() {
            AotAjax.excute("${CONTEXT_PATH}/setting/sbc/sbc.do", {
                actionID: "ACTION_SBC_DELETE_OK",
                sbc_node_name: $("#sbc_node_name").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#sbc_node_name").val("");
                    goSbcList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(sbc_node_name) {
            $("#sbc_node_name").val(sbc_node_name);
        }

        //SBC설치정보 취득
        function goSbcList() {
            $("#gridSbcList").jqGrid('clearGridData');
            jQuery("#gridSbcList").setGridParam({
                url: "${CONTEXT_PATH}/setting/sbc/sbc.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_SBC_LIST",
                    srch_sbc_group_id: dbSrchGroupName,
                    srch_sbc_node_name: $("#srch_sbc_node_name").val(),
                    srch_sbc_node_ip: $("#srch_sbc_node_ip").val()
                }
            }).trigger("reloadGrid");
        }

        function changeSrchSbcGroupVal(obj) {
            dbSrchGroupName = obj.id;
            var btn_target = $("#btn_srch_sbc_group_id");
            btn_target.html(obj.text + " <span class=\"caret\"></span>");
        }

        function selectBoxInit() {
            var _target = $("#srch_sbc_group_id");
            var btn_target = $("#btn_srch_sbc_group_id");
            var data = [];
            var sub_data = {};
            sub_data = {};
            sub_data.code = "";
            sub_data.code_nm = "전체";
            data.push(sub_data);
            <c:if test="${!empty srchSbcGroupList}">
            <c:forEach var="item" items="${srchSbcGroupList}" begin="0" step="1" varStatus="status">
            sub_data = {};
            <c:choose>
            <c:when test="${loginedUserVO.user_lang eq 'KOR'}">
            sub_data.code_nm = '${item.codenm_k}';
            </c:when>
            <c:when test="${loginedUserVO.user_lang eq 'ENG'}">
            sub_data.code_nm = '${item.codenm_e}';
            </c:when>
            <c:when test="${loginedUserVO.user_lang eq 'JPN'}">
            sub_data.code_nm = '${item.codenm_j}';
            </c:when>
            <c:when test="${empty loginedUserVO.user_lang}">
            sub_data.code_nm = '${item.codenm_k}';
            </c:when>
            </c:choose>
            sub_data.code = "${item.code}";
            data.push(sub_data);
            </c:forEach>
            </c:if>
            var select_db_html = "";
            $.each(data, function (i, val) {
                select_db_html += " <li><a href=\"javascript:void(0);\" onclick=\"changeSrchSbcGroupVal(this)\" id=\"" + val.code + "\">" + val.code_nm + "</a><li>";
            });
            _target.html(select_db_html);
            /* 초기값 세팅 */
            //btn_target.html(data[0].code_nm + " <span class=\"caret\"></span>");
            //dbSrchGroupName = data[0].code;
            btn_target.html("그룹명" + " <span class=\"caret\"></span>");
            dbSrchGroupName = "";
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="sbcInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="sbcModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>