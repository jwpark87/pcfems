<%----------------------------------------------------------------------------------------
 - 파일이름	: stcs/usage/List.jsp
 - 설      명	: CPU분석 보고서 화면
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

    <form name="popForm" method="POST" action="${CONTEXT_PATH}/stcs/usage/cpu.pop">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="srchSidx"/>
        <input type="hidden" name="srchSord"/>
        <input type="hidden" name="prefix" id="prefix"/>
        <input type="hidden" name="actionID">
        <input type="hidden" name="page" id="page"/>
        <input type="hidden" name="rowNum" id="rowNum"/>
        <div id="panel" role="main">
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
                    <div class="col-lg-4" style="float: right; height: 66px; padding-top: 10px; text-align: right;"></div>

                </div>
                <!-- 상단화면명 END -->

                <!-- 검색박스 START -->
                <div class="row" style="margin-bottom: 5px;">
                    <div class="col-sm-12">
                        <p style="text-align: right;">
                            <spring:message code="TEXT.STCS.USAGE.LAST_UPDATE"/>
                            : ${now }
                        </p>
                        <div class="well smart-form" style="padding: 19px 19px 2px 19px;">
                            <div class="row">
                                <label class="col-sm-1" style="text-align: right; width: 6%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.STCS.USAGE.SBC_NODE_IP"/>
                                </label>
                                <section class="col col-5">
                                    <label class="select">
                                        <aot:select name="srch_sbc_node_ip" id="srch_sbc_node_ip" list="${srchSbcNodeIpList}" selected="${SEARCH_DATA.srch_sbc_node_ip}"/>
                                        <i></i>
                                    </label>
                                </section>

                                <label class="col-sm-1" style="text-align: right; width: 16%; margin-bottom: 0; padding-top: 7px;">
                                    <spring:message code="TEXT.STCS.USAGE.RELOAD"/>
                                </label>
                                <section class="col col-2">
                                    <label class="select">
                                        <aot:select name="srch_reload" id="srch_reload" list="${srchReloadList}" selected="${SEARCH_DATA.srch_reload}"/>
                                        <i></i>
                                    </label>
                                </section>

                                <button type="button" class="btn btn-primary btn-sm" id="srch">
                                    <spring:message code="TEXT.COMM.BTN.APPLY"/>
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
                                 data-widget-deletebutton="false">
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
										<i class="fa fa-bar-chart-o"></i>
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

                                        <!-- 그래프 표시 START -->
                                        <c:if test="${!empty chartList}">
                                            <!-- 그래프 복수개-->
                                            <div style="border: none;">
                                                <c:forEach var="item" items="${chartList}" begin="0" step="1" varStatus="status">
                                                    <jsp:useBean id="now" class="java.util.Date"/>
                                                    <!-- 그래프 -->
                                                    <img src="${PATH_STCS_IMG}/${item} ?+${now}">
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                        <!-- 그래프 표시 END -->

                                    </div>

                                </div>
                            </div>
                        </article>
                    </div>
                </section>

                <!-- 하단 버튼 -->
                <div class="row">
                    <div class="col-lg-12" style="float: center; height: 50px; text-align: center;">
                        <button type="button" class="btn btn-primary btn-sm" id="popClose">
                            <spring:message code="TEXT.COMM.BTN.CLOSE"/>
                        </button>
                    </div>
                </div>
                <!-- 하단 버튼 END -->

            </div>
            <!-- content -->

        </div>
        <!-- main -->

    </form>


    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {

            bindClickEvent();
        });

        //클릭이벤트
        function bindClickEvent() {
            $("#srch").on('click', function () {
                goUsageList();
            });
            $("#popClose").on('click', function () {
                self.close();
            });
        }

        //검색버튼 눌렀을 경우 리스트 데이터 취득처리
        function goUsageList() {
            popForm.action = "/stcs/usage/cpu.pop";
            popForm.submit();
        }

        //그래프 재작성
        function redrawGraph() {
            goUsageList();
        }

        // 타이머
        setInterval(redrawGraph, ${SEARCH_DATA.srch_reload} * 1000 * 60;
        )

    </script>
    </body>
</aot:html>
