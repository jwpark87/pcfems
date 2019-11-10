<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/groupmenu/List.jsp
 - 설    명	:	권한별메뉴관리 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2008.11.12    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" id="myForm" method="post" action="${CONTEXT_PATH}/admin/groupmenu/menu.do">
        <input type="hidden" name="actionID" value="">
        <input type="hidden" id="groupLevel" name="groupLevel">
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
                        <button type="button" class="btn btn-primary btn-sm" id="groupMenuAdd" onclick="doGroupMenuSetting();">
                            <spring:message code="TEXT.GROUPMENU.MGMT.BUTTON.GROUPMENU_MENU_REGIST"/>
                        </button>
                        <button type="button" class="btn btn-primary btn-sm" id="groupMenuReload" onclick="doReload();">
                            <spring:message code="TEXT.COMM.BTN.RELOAD"/>
                        </button>
                    </div>

                </div>
                <!-- 상단화면명 END -->

                <!-- 검색박스 START -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="well">
                            <!-- <div class="smart-form"> -->
                            <section class="col" style="width: 10.33%;">
                                <div class="btn-group" style="margin-right: 19px;">
                                    <!-- 버튼 Select START -->
                                    <button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" id="btn_select_groupLevel"></button>
                                    <ul class="dropdown-menu" id="select_groupLevel">
                                    </ul>
                                    <!-- 버튼 Select END -->
                                </div>
                            </section>
                            <!-- </div> -->
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
                                    <div class="jarviswidget-editbox"></div>
                                    <div class="widget-body no-padding">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th style="font-size: 14px">
                                                        <spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_NO"/>
                                                    </th>
                                                    <th style="font-size: 14px">
                                                        <spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_TYPE"/>
                                                    </th>
                                                    <th style="font-size: 14px">
                                                        <spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_ID"/>
                                                    </th>
                                                    <th style="font-size: 14px">
                                                        <spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_P_MENU_ID"/>
                                                    </th>
                                                    <th style="font-size: 14px">
                                                        <spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_NAME"/>
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${!empty GROUPMENU_LIST}">
                                                    <c:forEach var="item" items="${GROUPMENU_LIST}" begin="0" step="1" varStatus="status">
                                                        <tr>
                                                            <td>${item.seq }</td>
                                                            <td style="text-align: center">${item.menuType }</td>
                                                            <td>${item.menuId}</td>
                                                            <td>${item.upMenuId}</td>
                                                            <td style="text-align: left;">${item.menuTitle }<input type="checkbox" name="menuId" value="${item.menuId}" ${item.isChecked}
                                                                                                                   onClick="doCheckRelation('${status.index}');">
                                                                <span style="vertical-align: middle;">${item.menuNm}</span>
                                                                <input type="hidden" name="level" value="${item.level}">
                                                                <input type="hidden" name="upMenuId" value="${item.upMenuId}">
                                                                <input type="hidden" name="menuIdStr">
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty listData}">
                                                    <tr height="21">
                                                        <td colspan="5" style="text-align: center">
                                                            <spring:message code="TEXT.EMPGROUPMENU.MGMT.LIST.MENU_NO_DATA"/>
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
    </form>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        var selgrlvl = "${groupLevel}";
        var groupLvl = "";

        $(document).ready(function () {

            selectBoxInit();
            selectedBox();
            bindClickEvent();

        });

        //검색 값 유지
        function selectedBox() {
            if (selgrlvl !== "") {
                var btn_target = $("#btn_select_groupLevel");
                <c:if test="${!empty AUTHLEVEL_LIST}">
                <c:set var="loop_flag" value="false" />
                <c:forEach var="item" items="${AUTHLEVEL_LIST}" begin="0" step="1" varStatus="status">
                <c:if test="${not loop_flag }">
                <c:if test="${item.code eq groupLevel}">
                <c:choose>
                <c:when test="${loginedUserVO.user_lang eq 'KOR'}">
                btn_target.html('${item.codenm_k}<span class="caret"></span>');
                </c:when>
                <c:when test="${loginedUserVO.user_lang eq 'ENG'}">
                btn_target.html('${item.codenm_e}<span class="caret"></span>');
                </c:when>
                <c:when test="${loginedUserVO.user_lang eq 'JPN'}">
                btn_target.html('${item.codenm_j}<span class="caret"></span>');
                </c:when>
                <c:when test="${empty loginedUserVO.user_lang}">
                btn_target.html('${item.codenm_k}<span class="caret"></span>');
                </c:when>
                </c:choose>
                <c:set var="loop_flag" value="true" />
                </c:if>
                </c:if>
                </c:forEach>
                </c:if>
                groupLvl = selgrlvl;
            }
        }

        //버튼 콤보박스 생성
        function selectBoxInit() {
            var _target = $("#select_groupLevel");
            var btn_target = $("#btn_select_groupLevel");
            var data = [];
            var sub_data = {};
            sub_data = {};
            sub_data.code = "";
            sub_data.code_nm = "<spring:message code="TEXT.COMM.SEL.CHOICE" />";
            data.push(sub_data);

            <c:if test="${!empty AUTHLEVEL_LIST}">
            <c:forEach var="item" items="${AUTHLEVEL_LIST}" begin="0" step="1" varStatus="status">
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
            var select_html;
            select_html = "";
            console.info(data);
            $.each(data, function (i, val) {
                select_html += " <li><a href=\"javascript:void(0);\" onclick=\"changeGroupLevel(this)\" id=\"" + val.code + "\">" + val.code_nm + "</a><li>";
            });
            _target.html(select_html);
            btn_target.html(data[0].code_nm + " <span class=\"caret\"></span>");
            /* 초기값 세팅 */
            groupLvl = data[0].code;
        }

        //변경시 버튼에 텍스트 변경
        function changeGroupLevel(obj) {
            groupLvl = obj.id;
            var btn_target = $("#btn_select_groupLevel");
            btn_target.html(obj.text + " <span class=\"caret\"></span>");
            searchList();
        }

        //검색
        function searchList() {
            $("#myForm input[name=actionID]").val("GROUPMENU_ACTION_LIST");
            $("#groupLevel").val(groupLvl);
            $('#myForm').submit();
        }

        function bindClickEvent() {
        }

        <%-- 새로운 메뉴를 등록하는 화면으로 이동 --%>

        function doGroupMenuSetting() {
            var total = 0;
            if (document.myForm.menuId) {
                total = 1;
            }
            if (groupLvl === '') {
                AotSmartAdmin.smallBoxWarning('권한을 우선 선택해주세요.');
                return;
            } else if (document.myForm.menuId.length) {
                total = document.myForm.menuId.length;
            }
            if (total > 0) {
                for (var i = 0; i < total; i++) {
                    if (document.myForm.menuId[i].checked) {
                        document.myForm.menuIdStr[i].value = document.myForm.menuId[i].value + ";" + document.myForm.upMenuId[i].value + ";";
                    }
                }
            }
            $("#myForm input[name=actionID]").val("GROUPMENU_ACTION_MENU_SETTING");
            $("#groupLevel").val(groupLvl);
            $('#myForm').submit();
        }

        //변경반영
        function doReload() {
            AotSmartAdmin.confirmBox("<spring:message code='MSG.ALERT.PROCESS'/>", function () {
                $("#myForm input[name=actionID]").val("GROUPMENU_ACTION_RELOAD_OK");
                $("#groupLevel").val(groupLvl);
                $('#myForm').submit();
            });
        }

        //트리메뉴 체크
        function doCheckRelation(index) {
            //여러개의 체크박스가 있는 경우만 위아래 노드 체크 처리를 수행한다
            if (document.myForm.menuId.length) {
                var total = document.myForm.menuId.length;
                var level = document.myForm.level[index].value;
                var upMenuId = document.myForm.upMenuId[index].value;
                var menuId = document.myForm.menuId[index].value;
                var bCheck = document.myForm.menuId[index].checked;
                //하위노드 체크처리
                doCheckDownNode(bCheck, menuId, level, index, total);
                //상위노드 체크처리
                doCheckUpNode(bCheck, upMenuId, index, total);
            }
        }

        //자식노드가하나라도 존재하는지 체크한다
        function checkEndNodeExist(upMenuId, j, total) {
            var bCheck = false;
            for (var k = j; k < total; k++) {
                if (upMenuId == document.myForm.upMenuId[k].value) {
                    if (document.myForm.menuId[k].checked) {
                        bCheck = true;
                        break;
                    }
                }
            }

            return bCheck;
        }

        //자식항목들을 모두 체크상태나 비 체크 상태로 바꾼다
        function doCheckUpNode(bCheck, upMenuId, idx, total) {
            //체크된 상태의 경우 위의 부모항목도 체크한다
            if (bCheck) {
                for (var j = idx - 1; j >= 0; j--) {
                    if (upMenuId == document.myForm.menuId[j].value) {
                        document.myForm.menuId[j].checked = bCheck;
                        doCheckUpNode(bCheck, document.myForm.upMenuId[j].value, j, total);
                        break;
                    }
                }
                //체크를 해제한 경우는 다른 하위항목이 없을경우 체크를 해제한다
            } else {
                for (var j = idx - 1; j >= 0; j--) {
                    if (upMenuId == document.myForm.menuId[j].value) {
                        if (!checkEndNodeExist(upMenuId, j, total)) {
                            document.myForm.menuId[j].checked = bCheck;
                            doCheckUpNode(bCheck, document.myForm.upMenuId[j].value, j, total);
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        //자식항목들을 모두 체크상태나 비 체크 상태로 바꾼다
        function doCheckDownNode(bCheck, menuId, level, idx, total) {
            //마지막항목이 아닌경우는 계속 수행
            if (idx < (total - 1)) {
                for (var j = (idx + 1); j < total; j++) {
                    if (level >= document.myForm.level[j].value) {
                        break;
                    } else {
                        if (menuId == document.myForm.upMenuId[j].value) {
                            document.myForm.menuId[j].checked = bCheck;
                            doCheckDownNode(bCheck, document.myForm.menuId[j].value, document.myForm.level[j].value, j, total);
                        } //else {
                          //  break;
                        //}
                    }
                }
            }
        }
    </script>
    </body>
</aot:html>