<%----------------------------------------------------------------------------------------
 - 파일이름	:	obstacle/AlarmInquiry/RemarkTerminatePop.jsp
 - 설    명	:	장애관리/조치내용 조회 화면
 - 추가내용   :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.02.05    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}">
    <body>
    <style>
        table.table {
            border: #cacaca 1px solid;
            border-top: #9e9e9e 1px solid;
            width: 100%;
        }

        table.table thead th, table.table tbody th {
            padding: 5px 15px;
            height: 33px;
            border-right: #e4e4e4 1px solid;
            border-bottom: #e4e4e4 1px solid;
            font-size: 13px;
            color: #000;
            vertical-align: middle;
        }

        table.table thead th:last-child {
            border-right: #cacaca 1px solid;
        }

        table.table tbody th:last-child {
            border-right: 0;
        }

        table.table tbody td {
            padding: 5px 15px;
            height: 33px;
            border-right: #e4e4e4 1px solid;
            border-bottom: #e4e4e4 1px solid;
            font-size: 13px;
            vertical-align: middle;
            background-color: #fafafa;
        }

        table.table tbody td:last-child {
            border-right: 0;
        }

        table.table tbody tr:last-child th, table tbody tr:last-child td {
            border-bottom: 0;
        }

        table.table_default thead th {
            height: 50px;
            font-size: 13px;
        }

        table.table_default tbody th, table.table_default tbody td {
            height: 45px;
        }

        table.table-form {
            border-left: #e4e4e4 1px solid;
            border-right: #e4e4e4 1px soild
        }

        table.table-form tbody th, table.table-form tbody td {
            height: 47px;
            text-align: left;
        }

        table.table.fontsize13 td {
            font-size: 13px
        }

        table.table.tac td {
            text-align: center;
        }

        .popover {
            max-width: 90%; /* Max Width of the popover (depending on the container!) */
        }
    </style>

    <form name="myForm" method="POST">
        <input type="hidden" name="actionID" value="">
    </form>

    <div id="panel" role="main">

        <div id="content">

            <div class="row">
                <div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-search"></i>
                        <spring:message code="TEXT.OBSTACLE.ALM.POP.SNMP.REMARK.TERMINATE.TITLE"/>
                    </h1>
                </div>
            </div>

            <div class="row">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <!-- 서버명 -->
                            <th>
                                <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.HOST_NM"/>
                            </th>
                            <td>${SEARCH_DATA.host_nm }</td>

                            <!-- 장애 Seq -->
                            <th>
                                <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.SEQNO"/>
                            </th>
                            <td>${seqno }</td>
                        </tr>

                        <tr>
                            <!-- 알람카테고리 -->
                            <th>
                                <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.ALM_CATEGORY"/>
                            </th>
                            <td>${SEARCH_DATA.alm_category}</td>
                            <!-- 처리자 -->
                            <th>
                                <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.ALM_CONFIRM_USER"/>
                            </th>
                            <td>
                                <input name="alm_confirm_user" id="alm_confirm_user" type="text" value="${loginedUserVO.user_name}">

                            </td>
                        </tr>
                        <tr>
                            <!-- 알람카테고리 -->
                            <th>Alarm Category</th>
                            <td>${SEARCH_DATA.alm_group}</td>
                            <!-- 처리자 -->
                            <th>Alarm Instance</th>
                            <td>${SEARCH_DATA.alm_instance}</td>
                        </tr>
                        <tr>
                            <!-- 알람메시지 -->
                            <th>
                                <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.ALM_MSG"/>
                            </th>
                            <td colspan="3">${SEARCH_DATA.alm_msg }</td>
                        </tr>
                        <tr>
                            <!-- 알람메시지 -->
                            <th>Recovery</th>
                            <td colspan="3">
                                <a href="javascript:void(0);" class="btn btn-default" rel="popover" data-placement="top" data-original-title="Alarm recovery"
                                   data-content="${SEARCH_DATA.alm_recovery }">
                                    <i class="fa fa-arrow-up"></i>
                                    <strong>
                                        내용보기
                                        <span class="glyphicon glyphicon-comment"></span>
                                    </strong>
                                </a>
                            </td>
                        </tr>

                        <tr>
                            <!-- 비고 -->
                            <th>
                                    <spring:message code="TEXT.BTN.ALM.COMMENT.UPDATE.POP.ALM_REMARK1"/>
                            </td>
                            <td colspan="3">
                                <textarea name="alm_remark1" id="alm_remark1" class="form-control" rows="10">${SEARCH_DATA.alm_remark1}</textarea>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- 버튼영역 -->
            <div class="row" style="text-align: right; margin-top: 13px;">
                <section class="col-sm-12">
                    <a href="javascript:void(0);" class="btn btn-lg btn-primary" onclick="doAlmUpd('COMMENT'); return false;">Comments 저장</a>
                    <a href="javascript:void(0);" class="btn btn-lg btn-primary" onclick="doAlmUpd('TERMINATE'); return false;">
                        <spring:message code="TEXT.BTN.ALM.TERMINATE.UPDATE"/>
                    </a>
                    <a href="javascript:void(0);" class="btn btn-lg btn-primary" onclick="doClose(); return false;">
                        <spring:message code="TEXT.COMM.BTN.CANCEL"/>
                    </a>
                </section>
            </div>
        </div>
    </div>

    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:if test="${SEARCH_DATA} eq null">
            alert('이미 해지되었습니다.');
            doClose();
            </c:if>
        });

        //코멘트 수정
        function doAlmUpd(work_type) {
            AotAjax.excute("${CONTEXT_PATH}/obstacle/doCommentAndTerminate.json", {
                seqno: "${seqno}",
                alm_remark1: $("#alm_remark1").val(),
                work_type: work_type
            }, {
                autoResultFunctionTF: false
            }).done(function (response) {
                var str = response.returnMsg.split(":");
                if (str[0] === "success") {
                    try {
                        window.opener.doSearchAlarm();
                    } catch (e) {
                        console.warn(e);
                    }
                    try {
                        window.opener.GridReload();
                    } catch (e) {
                        console.warn(e);
                    }
                    try {
                        window.opener.GridReload2();
                    } catch (e) {
                        console.warn(e);
                    }
                    try {
                        window.opener.draw_level_number();
                    } catch (e) {
                        console.warn(e);
                    }
                    alert(str[1]);
                    window.close();
                } else {
                    AotSmartAdmin.smallBoxWarning(str[1]);
                }
            });
        }

        //창 닫을때 타이머 다시 시작
        function doClose() {
            window.close();
        }
    </script>
    </body>
</aot:html>