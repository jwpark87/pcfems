<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/menu/List.jsp
 - 설    명	:	메뉴데이터관리 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2017.12.18    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" id="myForm" method="post" action="${CONTEXT_PATH}/admin/menu/menu.do">
        <input type="hidden" name="actionID" value="">
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

                <!-- 상단 버튼 -->
                <div class="col-lg-4" style="float: right; height: 66px; padding-top: 10px; text-align: right;">
                    <button type="button" class="btn btn-primary" id="menuReload">
                        <spring:message code="TEXT.COMM.BTN.RELOAD"/>
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
                                <div class="jarviswidget-editbox"></div>
                                <div class="widget-body no-padding">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                    <%--번호--%>
                                                <th width="50">
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.NO"/>
                                                </td>
                                                    <%--타입--%>
                                                <th width="80">
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.TYPE"/>
                                                </td>
                                                    <%--메뉴아이디--%>
                                                <th width="200">
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.MENU_ID"/>
                                                </td>
                                                    <%--상위메뉴아이디--%>
                                                <th>
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.P_MENU_ID"/>
                                                </td>
                                                    <%--메뉴명칭 --%>
                                                <th>
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.MENU_NAME"/>
                                                </td>
                                                    <%--메뉴순차번호--%>
                                                <th width="80">
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.MENU_ORDER_NO"/>
                                                </td>
                                                    <%--기능 --%>
                                                <th width="220">
                                                        <spring:message code="TEXT.MENU.MGMT.LIST.MENU_FUNC"/>
                                                </td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:if test="${!empty MENU_LIST}">
                                                <c:forEach var="item" items="${MENU_LIST}" begin="0" step="1" varStatus="status">
                                                    <tr>
                                                        <td style="text-align: center;">${status.index + 1 }</td>
                                                        <td>${item.menuType }</td>
                                                        <td>${item.menuId }</td>
                                                        <td>${item.upMenuId }</td>
                                                        <td>${item.menuTitle }${item.menuNm }</td>
                                                        <td>${item.seq }</td>
                                                        <td style="text-align: center;">
                                                            <a href="#" data-target="#menuModal" data-id="${item.menuId }|${item.seq }|${item.level }" class="btn btn-primary btn-xs">
                                                                <spring:message code="TEXT.COMM.BTN.INPUT.DOWN"/>
                                                            </a>
                                                            <a href="#" data-target="#menuModModal" data-id="${item.menuId }" class="btn btn-success btn-xs">
                                                                <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                                                            </a>
                                                            <button type="button" class="btn btn-warning btn-xs" id="delete_menu" onClick="doDelete('${item.menuId }' , '${item.seq }');return false;">
                                                                <spring:message code="TEXT.COMM.BTN.DELETE"/>
                                                            </button>
                                                        </td>
                                                    </tr>

                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty MENU_LIST}">
                                                <tr>
                                                    <td colspan="7" style="text-align: center">
                                                        <!-- 															<button type="button" class="btn btn-warning btn-xs" id="add_menu" onClick="moveDownInput('','0','0');return false;"> -->
                                                            <%-- 																<spring:message code="TEXT.COMM.BTN.INPUT.MENU" /> --%>
                                                        <!-- 															</button> -->
                                                    </td>
                                                </tr>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
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
        });

        function bindClickEvent() {
            //등록 모달 호출
            $('a[data-target="#menuModal"]').on('click', function (ev) {
                ev.preventDefault();

                var my_id_value = $(this).data('id').split("|");
                var upmenuId = my_id_value[0];
                var seq = Number(my_id_value[1]) + 1;
                var level = Number(my_id_value[2]) + 1;

                $(this).attr('href', '${CONTEXT_PATH}/admin/menu/menuPopup.do?upMenuId=' + upmenuId + '&seq=' + seq + '&level=' + level);

                // load the url and show modal on success
                $("#menuModal").load($(this).attr("href"), function () {
                    $("#menuModal").modal();
                });
            });
            //수정 모달 호출
            $('a[data-target="#menuModModal"]').on('click', function (ev) {
                ev.preventDefault();

                var my_id_value = $(this).data('id').split("|");

                $(this).attr('href', '${CONTEXT_PATH}/admin/menu/menuModPopup.do?menuId=' + my_id_value);

                // load the url and show modal on success
                $("#menuModModal").load($(this).attr("href"), function () {
                    $("#menuModModal").modal();
                });
            });

            //변경반영
            $("#menuReload").on('click', function () {
                doReload();
            });
        }

        function doReload() {
            console.log($("#myForm").attr("action"));
            AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.RELOAD'/>", function () {
                $("#myForm input[name=actionID]").val("ACTION_RELOAD_OK");
                $('#myForm').submit();
            });
        }

        function doDelete(menuId) {
            if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {

                AotAjax.excute("${CONTEXT_PATH}/admin/menu/menu.do", {
                    actionID: "ACTION_DELETE_OK",
                    menuId: menuId
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        window.location.reload();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            }
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="menuModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>