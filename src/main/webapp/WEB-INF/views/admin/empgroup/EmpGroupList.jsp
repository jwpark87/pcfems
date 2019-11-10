<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/empgroup/List.jsp
 - 설      명	: 사용자그룹관리 목록 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2011.03.14   3.2       Once      웹표준 적용
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/jsp/common/error/systemException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/admin/empgroup/empgroup.do">
        <input type="hidden" name="actionID" value="ACTION_LIST">
        <input type="hidden" name="groupLevel" id="groupLevel"/>
        <input type="hidden" name="chkGroupId" id="chkGroupId" value="">
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
                    <a href="#" data-target="#empGroupModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#empGroupModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="groupDel">
                        <spring:message code="TEXT.COMM.BTN.DELETE"/>
                    </button>
                </div>
            </div>
            <!-- 상단화면명 END -->

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
                                <div class="jarviswidget-editbox"></div>
                                <div class="widget-body no-padding">
                                    <table id="gridGroupList"></table>
                                    <div id="gridGroupToolbar"></div>
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
        $(document).ready(function () {
            bindClickEvent();

            jQuery("#gridGroupList").jqGrid({
                url: "${CONTEXT_PATH}/admin/empgroup/empgroup.do",
                postData: {
                    actionID: "ACTION_LIST"
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
                    //그룹ID
                    "<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_NAME'/>",
                    //그룹ID
                    "<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_NAME'/>",
                    //권한레벨
                    "<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_LEVEL'/>",
                    //사용자그룹설명
                    "<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_EXPLAIN'/>"],
                colModel: [{
                    name: "code",
                    index: "code",
                    hidden: true
                }, {
                    name: "name",
                    index: "name",
                    width: 180,
                    align: "center"
                }, {
                    name: "groupLevel",
                    index: "groupLevel",
                    width: 130,
                    align: "center"
                }, {
                    name: "remark",
                    index: "remark",
                    width: 628
                }],
                sortname: "groupLevel",
                sortorder: "desc",
                rowNum: 20,
                rowList: [10, 20, 30],
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridGroupToolbar",
                autowidth: true,
                onSelectRow: function () { //row가 선택되었을 경우만 삭제할수 있다.
                    var id = jQuery("#gridGroupList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridGroupList").getRowData(id);
                        $("#groupLevel").val(ret.groupLevel);
                        $("#chkGroupId").val(ret.code);
                    }
                }
            });
            jQuery("#gridGroupList").jqGrid("navGrid", "#gridGroupToolbar", {
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
            $(".ui-jqgrid-labels, .ui-search-toolbar").children().removeClass("ui-state-default ui-th-column ui-th-ltr");
        });

        //사용자 정보 취득
        function goEmpGroupList() {
            $("#gridGroupList").jqGrid('clearGridData');
            jQuery("#gridGroupList").setGridParam({
                url: "${CONTEXT_PATH}/admin/empgroup/empgroup.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_LIST"
                }
            }).trigger("reloadGrid");
        }

        //클릭이벤트
        function bindClickEvent() {
            //삭제
            $("#groupDel").on('click', function () {
                if ($("#chkGroupId").val() === "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delEmpGroup();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#empGroupModal"]').on('click', function (ev) {
                ev.preventDefault();

                $(this).attr('href', '${CONTEXT_PATH}/admin/empgroup/empgroupPopup.do');

                // load the url and show modal on success
                $("#empGroupModal").load($(this).attr("href"), function () {
                    $("#empGroupModal").modal();
                });

            });

            //수정 모달 호출
            $('a[data-target="#empGroupModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#chkGroupId").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#empGroupModModal").modal("hide");
                } else {
                    $(this).attr('href', '${CONTEXT_PATH}/admin/empgroup/empgroupModPopup.do?groupId=' + $("#chkGroupId").val());

                    // load the url and show modal on success
                    $("#empGroupModModal").load($(this).attr("href"), function () {
                        $("#empGroupModModal").modal();
                    });
                }
            });
        }

        //삭제
        function delEmpGroup() {
            AotAjax.excute("${CONTEXT_PATH}/admin/empgroup/empgroup.do", {
                actionID: "ACTION_DELETE_OK",
                groupId: $("#chkGroupId").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#chkGroupId").val("");
                    goEmpGroupList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }
    </script>

    <div class="modal fade" id="empGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="empGroupModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>