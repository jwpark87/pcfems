<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/AlarmlevelSet/AlarmlevelSet.jsp
 - 설      명	: 알람 등급 설정 관리 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.05    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST">
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="sel_alm_code" id="sel_alm_code"/>
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
            </div>
            <!-- 상단화면명 END -->

            <section id="widget-grid">
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="goList();">
                                        <i class="fa fa-refresh"></i>
                                    </a>
                                    <a href="#" class="button-icon jarviswidget-toggle-btn" title="" data-placement="bottom">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title="" data-placement="bottom">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </div>
                                <span class="jarviswidget-loader" style="display: none;">
									<i class="fa fa-refresh fa-spin"></i>
								</span>
                            </header>
                            <div role="content" style="display: block;">
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
        <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" group="W001" init="YES"></aot:select>
        <aot:select name="select_alm_level" id="select_alm_level" style="display:none;" group="ALM_LEVEL" init="YES"></aot:select>
    </div>
    <!-- main -->

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {
            bindClickEvent();

            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/obstaclemst/alarmlevel.do",
                postData: {
                    actionID: "ALM_LEVEL_MST_LIST"
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
                height: 500,
                colNames: [
                    //알람 코드,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_CODE"/>",
                    //알람 등급,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_LEVEL"/>",
                    //알람명
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.SNMP_NAME"/>", "운영자 Comment",
                    //알람 설명,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_DESC"/>",
                    //알람 카테고리,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_CATEGORY"/>",
                    //클리어 알람 코드
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_CLEAR_CODE"/>",
                    //Recovery
                    "Recovery",
                    //알람 가청 여부,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_SOUND_YN"/>",
                    //알람 사용여부,
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_USE_YN"/>",
                    //"수정자"
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_UPD_ID"/>",
                    //"수정일시"
                    "<spring:message code="TEXT.ALM.SET.LEVEL.GRID.ALM_UPD_DT"/>"],
                colModel: [{
                    name: "alm_code",
                    index: "alm_code",
                    align: "center",
                    width: 80
                }, {
                    name: "alm_level",
                    index: "alm_level",
                    align: "center",
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                    },
                    formatter: 'select',
                    width: 80
                }, {
                    name: "snmp_name",
                    index: "snmp_name",
                    align: "left",
                    width: 400
                }, {
                    name: "oper_comments",
                    index: "oper_comments",
                    align: "left",
                    width: 400
                }, {
                    name: "alm_desc",
                    index: "alm_desc",
                    align: "left",
                    width: 700
                }, {
                    name: "alm_category",
                    index: "alm_category",
                    align: "center",
                    width: 100
                }, {
                    name: "alm_clear_code",
                    index: "alm_clear_code",
                    align: "center",
                    hidden: true,
                    width: 100
                }, {
                    name: "alm_recovery",
                    index: "alm_recovery",
                    align: "left",
                    width: 500
                }, {
                    name: "sound_yn",
                    index: "sound_yn",
                    align: "center",
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    formatter: 'select',
                    width: 60
                }, {
                    name: "use_yn",
                    index: "use_yn",
                    align: "center",
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    formatter: 'select',
                    width: 60
                }, {
                    name: "upd_id_nm",
                    index: "upd_id_nm",
                    align: "center",
                    width: 80,
                    search: false
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    align: "center",
                    width: 150,
                    search: false
                }],
                sortname: "alm_code",
                rowNum: 100,
                rowList: [100, 200, 300],
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
                        $("#sel_alm_code").val(ret.alm_code);
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
                searchOnEnter: false,
                defaultSearch: "cn",
                ignoreCase: true
            });
            AotJqGrid.setGridStyle();
        });

        function bindClickEvent() {
            //클릭이벤트
            $("#srch").on('click', function () {
                goList();
            });
        }

        function showUpdatePop() {
            var param = {
                alm_code: $("#sel_alm_code").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/obstaclemst/updalarmlevelPopup.do');

            // load the url and show modal on success
            $("#almMstModModal").load($(this).attr("href"), param, function () {
                $("#almMstModModal").modal();
            });
        }

        //SBC설치정보 취득
        function goList() {
            $("#gridList").jqGrid('clearGridData');
            jQuery("#gridList").setGridParam({
                url: "${CONTEXT_PATH}/obstaclemst/alarmlevel.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ALM_LEVEL_MST_LIST"
                },
                search: true
            }).trigger("reloadGrid");
        }

        //선택 초기화
        function init_selected() {
            $("#sel_alm_code").val("");
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="almMstModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>