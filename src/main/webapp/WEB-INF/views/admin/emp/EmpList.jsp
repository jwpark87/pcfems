<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/emp/List.jsp
 - 설      명	: 사용자관리 화면
 - 작 성 자	: WYA
 - 작성날짜	: 2017.12.13
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2017.12.13    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" picker="YES" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/admin/emp/emp.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="emp_id" id="emp_id"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
        <input type="hidden" name="search_flag" id="search_flag" value="${SEARCH_DATA.search_flag}"/>
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
                    <a href="#" data-target="#empModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#empModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="empDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>

            </div>
            <!-- 상단화면명 END -->

            <!-- 검색박스 START -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="well">
                        <div class="btn-group">
                            <!-- 버튼 Select START -->
                            <button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" id="btn_select_srch_gb"></button>
                            <ul class="dropdown-menu" id="select_srch_gb">
                            </ul>
                            <!-- 버튼 Select END -->
                        </div>
                        <label class="input">
                            <input type="text" name="searchVal" id="searchVal" style="width: 140px">
                        </label>
                        <button type="button" class="btn btn-primary btn-sm" id="srch">
                            <spring:message code="TEXT.COMM.BTN.SEARCH"/>
                        </button>
                    </div>
                </div>
            </div>
            <!-- 검색박스 END -->

            <section id="widget-grid" class="">
                <!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false">
                            <!-- widget options:
                                        data-widget-colorbutton="false"
                                        data-widget-editbutton="false"
                                        data-widget-togglebutton="false"
                                        data-widget-deletebutton="false"
                                        data-widget-fullscreenbutton="false"
                                        data-widget-custombutton="false"
                                        data-widget-collapsed="true"
                                        data-widget-sortable="false"
                                    -->
                            <header>
								<span class="widget-icon">
									<i class="fa fa-edit"></i>
								</span>
                                <h2></h2>
                            </header>
                            <div>
                                <!-- 위젯 타이틀 변경 브라우져 닫으면 없어짐. -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                    <input class="form-control" type="text">
                                </div>
                                <div class="widget-body no-padding">
                                    <table id="gridEmpList"></table>
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
        var dbSrchGb = "";
        $(document).ready(function () {

            selectBoxInit();
            bindClickEvent();


            jQuery("#gridEmpList").jqGrid({
                url: "${CONTEXT_PATH}/admin/emp/emp.do",
                postData: {
                    actionID: "ACTION_EMP_LIST"
                    , searchKey: $("#searchKey").val()
                    , searchVal: $("#searchVal").val()
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
                    //"유저아이디",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.USER_ID"/>",
                    //"유저아이디",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.USER_ID"/>",
                    //"유저이름",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.USER_NAME"/>",
                    //"사용자그룹",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.EMP_GROUP"/>",
                    //"권한레벨코드",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.AUTH_LEVEL_CODE"/>",
                    //"이메일",
                    "<spring:message code="TEXT.EMP.MGMT.GRID.EMAIL"/>",
                    //"전화번호"
                    "<spring:message code="TEXT.EMP.MGMT.GRID.TEL"/>"
                ],
                colModel: [
                    {name: "emp_id", index: "emp_id", align: "center", width: 100, hidden: true},
                    {name: "emp_id", index: "emp_id", align: "center", width: 205},
                    {name: "empNm", index: "empNm", align: "center", width: 205},
                    {name: "user_group_nm", index: "user_group_nm", align: "center", width: 205},
                    {name: "levelCod", index: "levelCod", align: "center", hidden: true},
                    {name: "email", index: "email", align: "center", width: 213},
                    {name: "smsPhone", index: "smsPhone", align: "center", width: 205}
                ],
                sortname: "empNm",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridEmpList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridEmpList").getRowData(id);
                        var emp_id = ret.emp_id;
                        var groupLevel = ret.levelCod;
                        deatilInfo(emp_id, groupLevel);
                    }
                }
            });
            jQuery("#gridEmpList").jqGrid("navGrid", "#gridToolbar", {
                    edit: false, add: false, del: false, view: false, search: false, refresh: false
                },// edit,add,delete,view,search,options
                {}, {},
                {}, {}, {
                    width: 500
                }
            );
            AotJqGrid.setGridStyle();
            $(".ui-pg-div").removeClass().addClass("btn btn-sm btn-primary");
            $(".ui-icon.ui-icon-plus").removeClass().addClass("fa fa-plus");
            $(".ui-icon.ui-icon-pencil").removeClass().addClass("fa fa-pencil");
            $(".ui-icon.ui-icon-trash").removeClass().addClass("fa fa-trash-o");
            $(".ui-icon.ui-icon-search").removeClass().addClass("fa fa-search");
            $(".ui-icon.ui-icon-refresh").removeClass().addClass("fa fa-refresh");
            $(".ui-icon.ui-icon-disk").removeClass().addClass("fa fa-save").parent(".btn-primary").removeClass("btn-primary").addClass("btn-success");
            $(".ui-icon.ui-icon-cancel").removeClass().addClass("fa fa-times").parent(".btn-primary").removeClass("btn-primary").addClass("btn-danger");

        });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goEmpList();
            });
            $("#searchVal").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goEmpList();
                }
            });
            //삭제
            $("#empDel").on('click', function () {
                if ($("#emp_id").val() === "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delEmp();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#empModal"]').on('click', function (ev) {
                ev.preventDefault();

                $(this).attr('href', '${CONTEXT_PATH}/admin/emp/empPopup.do');


                // load the url and show modal on success
                $("#empModal").load($(this).attr("href"), function () {
                    $("#empModal").modal();
                });

            });

            //수정 모달 호출
            $('a[data-target="#empModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#emp_id").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#empModModal").modal("hide");
                } else {
                    $(this).attr('href', '${CONTEXT_PATH}/admin/emp/empModPopup.do?emp_id=' + $("#emp_id").val());


                    // load the url and show modal on success
                    $("#empModModal").load($(this).attr("href"), function () {
                        $("#empModModal").modal();
                    });
                }
            });
        }

        //삭제
        function delEmp() {
            AotAjax.excute("${CONTEXT_PATH}/admin/emp/emp.do", {
                actionID: "ACTION_EMP_DELETE_OK",
                emp_id: $("#emp_id").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] == "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#emp_id").val("");
                    goEmpList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(emp_id) {
            $("#emp_id").val(emp_id);
        }

        //사용자 정보 취득
        function goEmpList() {
            $("#gridEmpList").jqGrid('clearGridData');
            jQuery("#gridEmpList").setGridParam({
                url: "${CONTEXT_PATH}/admin/emp/emp.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_EMP_LIST"
                    , searchKey: dbSrchGb
                    , searchVal: $("#searchVal").val()
                }
            }).trigger("reloadGrid");
        }

        function selectBoxInit() {
            var _target = $("#select_srch_gb");
            var btn_target = $("#btn_select_srch_gb");
            var data = [];
            var sub_data = {};
            <c:if test="${!empty srchGubunList}">
            <c:forEach var="item" items="${srchGubunList}" begin="0" step="1" varStatus="status">
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
            var select_db_html;
            select_db_html = "";
            $.each(data, function (i, val) {
                select_db_html += " <li><a href=\"javascript:void(0);\" onclick=\"changeSrchGbVal(this)\" id=\"" + val.code + "\">" + val.code_nm + "</a><li>";
            });
            _target.html(select_db_html);
            btn_target.html(data[0].code_nm + " <span class=\"caret\"></span>");
            /* 초기값 세팅 */
            dbSrchGb = data[0].code;
        }

        function changeSrchGbVal(obj) {
            dbSrchGb = obj.id;
            var btn_target = $("#btn_select_srch_gb");
            btn_target.html(obj.text + " <span class=\"caret\"></span>");
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="empModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="empModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>