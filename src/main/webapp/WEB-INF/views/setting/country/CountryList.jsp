<%----------------------------------------------------------------------------------------
 - 파일이름	: setting/country/List.jsp
 - 설      명	: COUNTRY설정관리 화면
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

    <form name="myForm" method="POST" action="${CONTEXT_PATH}/setting/country/country.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="prefix" id="prefix"/>
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


                    <a href="#" data-target="#countryInsertModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.REG"/>
                    </a>
                    <a href="#" data-target="#countryModModal" class="btn btn-primary btn-sm">
                        <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                    </a>
                    <button type="button" class="btn btn-primary btn-sm" id="countryDel">
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
                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_prefix" id="srch_prefix" placeholder="Prefix">
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_country_cd" id="srch_country_cd" placeholder="국가코드">
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="srch_country_cd_name" id="srch_country_cd_name" placeholder="국가명">
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
                                    <table id="gridCountryList"></table>
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
        $(document).ready(function () {
            selectBoxInit();
            bindClickEvent();

            jQuery("#gridCountryList").jqGrid({
                url: "${CONTEXT_PATH}/setting/country/country.do",
                postData: {
                    actionID: "ACTION_COUNTRY_LIST",
                    srch_prefix: $("#srch_prefix").val(),
                    srch_country_cd: $("#srch_country_cd").val(),
                    srch_country_cd_name: $("#srch_country_cd_name").val()
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
                    //"Prefix",
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.PREFIX"/>",
                    //"국가코드",
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.COUNTRY_CD"/>",
                    //"국가명",
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.COUNTRY_CD_NAME"/>",
                    //"비고",
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.REMARK1"/>",
                    //"수정자",
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.UPD_ID_NAME"/>",
                    //"수정일시"
                    "<spring:message code="TEXT.SETTING.COUNTRY.GRID.UPD_DT"/>"],
                colModel: [{
                    name: "prefix",
                    index: "prefix",
                    align: "left",
                    width: 200
                }, {
                    name: "country_cd",
                    index: "country_cd",
                    align: "left",
                    width: 100
                }, {
                    name: "country_cd_name",
                    index: "country_cd_name",
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
                sortname: "prefix",
                rowNum: 100,
                rowList: [100, 300, 500, 1000],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: true,
                onSelectRow: function (id) {
                    var id = jQuery("#gridCountryList").getGridParam("selrow");
                    if (id) {
                        var ret = jQuery("#gridCountryList").getRowData(id);
                        var prefix = ret.prefix;
                        deatilInfo(prefix);
                    }
                },
                ondblClickRow: function (rowId) {
                    showUpdatePop();
                },
            });
            jQuery("#gridCountryList").jqGrid("navGrid", "#gridToolbar", {
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
                goCountryList();
            });

            $("#srch_prefix").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goCountryList();
                }
            });
            $("#srch_country_cd").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goCountryList();
                }
            });
            $("#srch_country_cd_name").on('keydown', function (key) {
                if (key.keyCode === 13) {
                    goCountryList();
                }
            });

            //삭제
            $("#countryDel").on('click', function () {
                if ($("#prefix").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                } else {
                    AotSmartAdmin.confirmBox("<spring:message code='MSG.CONFIRM.DELETE'/>", function () {
                        delCountry();
                    });
                }
            });

            //등록 모달 호출
            $('a[data-target="#countryInsertModal"]').on('click', function (ev) {
                ev.preventDefault();
                showInsertPop();
            });

            //수정 모달 호출
            $('a[data-target="#countryModModal"]').on('click', function (ev) {
                ev.preventDefault();
                if ($("#prefix").val() == "") {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    $("#countryModModal").modal("hide");
                } else {
                    showUpdatePop();
                }
            });
        }

        function showInsertPop() {
            $(this).attr('href', '${CONTEXT_PATH}/setting/country/sbcInsertPopup.do');

            // load the url and show modal on success
            $("#countryInsertModal").load($(this).attr("href"), function () {
                $("#countryInsertModal").modal();
            });
        }

        function showUpdatePop() {
            //-> 모든 #을 @로 치환(팝업을 띄우기위해 href에 get방식으로 넘기기 위해)
            /* var prefix = $("#prefix").val().replace(/#/g, '@'); */
            var param = {
                prefix: $("#prefix").val()
            };
            $(this).attr('href', '${CONTEXT_PATH}/setting/country/sbcModPopup.do');

            // load the url and show modal on success
            $("#countryModModal").load($(this).attr("href"), param, function () {
                $("#countryModModal").modal();
            });
        }

        //삭제
        function delCountry() {
            AotAjax.excute("${CONTEXT_PATH}/setting/country/country.do", {
                actionID: "ACTION_COUNTRY_DELETE_OK",
                prefix: $("#prefix").val()
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    AotSmartAdmin.smallBoxsuccess(str[1]);
                    $("#prefix").val("");
                    goCountryList();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        function deatilInfo(prefix) {
            $("#prefix").val(prefix);
        }

        //Prefix별 국가 관리정보 취득
        function goCountryList() {
            $("#gridCountryList").jqGrid('clearGridData');
            jQuery("#gridCountryList").setGridParam({
                url: "${CONTEXT_PATH}/setting/country/country.do?v=" + moment().valueOf(),
                postData: {
                    actionID: "ACTION_COUNTRY_LIST",
                    srch_prefix: $("#srch_prefix").val(),
                    srch_country_cd: $("#srch_country_cd").val(),
                    srch_country_cd_name: $("#srch_country_cd_name").val()
                }
            }).trigger("reloadGrid");
        }

        function selectBoxInit() {
        }
    </script>

    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
    <div class="modal fade" id="countryInsertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="countryModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
