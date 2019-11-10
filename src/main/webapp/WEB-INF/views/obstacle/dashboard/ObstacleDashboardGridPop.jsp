<%----------------------------------------------------------------------------------------
 - 파일이름	: obstacle/dashboard/Dashboard.jsp
 - 설      명	: Dashboard 화면
 - 추가내용	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.02.26    1.0     KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<c:set var="ALL">
    <spring:message code="TEXT.COMM.SEL.ALL"/>
</c:set>
<aot:html title="${TITLE}" jqGrid="YES" pace="NO" outdatebrowser="NO">
    <style>
        .smart-form .inline-group .checkbox {
            margin-right: 0;
        }

        .smart-form .btn span {
            font-size: 24px;
        }

        #chkbtn {
            padding: 0 15px;
        }

        .jarviswidget {
            margin-bottom: 10px;
        }

        div.widget-toolbar > div.btn-group > label.btn {
            cursor: default;
        }
    </style>
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
    <form name="myForm" method="POST" action="${CONTEXT_PATH}/obstacle/dashboard.do">
        <input type="hidden" name="menuUrl" value="${MENU_URL}"/>
        <input type="hidden" name="actionID">
        <!-- 	장애알람 ON/OFF 체크 값 -->
        <input type="hidden" name="soundOnOff" value="">
        <input type="hidden" name="srchErrorLevel" value="">
            <%-- 		<input type="hidden" name="srch_index_type" id="srch_index_type" value="${SEARCH_DATA.srch_index_type}" /> --%>
    </form>

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
            <!-- 			<div class="row"> -->
            <!-- 				상단화면명 -->
            <!-- 				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4"> -->
            <!-- 					<h1 class="page-title txt-color-blueDark" style="margin: 12px 0 20px;"> -->
            <!-- 						PAGE HEADER -->
            <!-- 						<i class="fa-fw fa fa-pencil-square-o"></i> -->
                <%-- 						${curr_menu_name} --%>
            <!-- 					</h1> -->
            <!-- 				</div> -->
            <!-- 			</div> -->
            <!-- 상단화면명 END -->

            <section id="widget-grid">
                <div class="row">
                    <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                        <header role="heading" class="">
                            <div id="level-number" class="widget-toolbar" role="menu" style="float: left;">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-default bg-color-red txt-color-white">
                                        <strong>0</strong>
                                    </label>
                                    <label class="btn btn-default" style="background-color: #ffbb00; color: black;">
                                        <strong>0</strong>
                                    </label>
                                    <label class="btn btn-default" style="background-color: #faed7d; color: black">
                                        <strong>0</strong>
                                    </label>
                                    <label class="btn btn-default" style="color: black;">
                                        <strong>0</strong>
                                    </label>
                                </div>
                            </div>
                            <h2 id="updateDdate2"></h2>
                            <div class="jarviswidget-ctrls" role="menu">
                                <a href="javascript:void(0);" class="button-icon" id="checked_alm"
                                   onclick="checkedAlarm();" data-placement="bottom">
                                    <label>
                                        <i class="fa fa-check"></i>
                                        <spring:message code="TEXT.COMM.SEL.CHOICE.FIRE"/>
                                    </label>
                                </a>
                                <a href="javascript:void(0);" class="button-icon" onclick="popupHistory();"
                                   data-placement="bottom">
                                    <label>
                                        <i class="fa fa-history"></i>
                                        History
                                    </label>
                                </a>
                                <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn"
                                   data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                   onclick="GridReload2();">
                                    <i class="fa fa-refresh"></i>
                                </a>
                                <a href="#" class="button-icon jarviswidget-toggle-btn" title=""
                                   data-placement="bottom">
                                    <i class="fa fa-minus"></i>
                                </a>
                                <a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" title=""
                                   data-placement="bottom">
                                    <i class="fa fa-expand"></i>
                                </a>
                            </div>
                            <span class="jarviswidget-loader" style="display: none;">
								<i class="fa fa-refresh fa-spin"></i>
							</span>
                            <div class="widget-toolbar" id="switch-1" role="menu" style="cursor: default;">
								<span class="onoffswitch-title">
									<i class="fa fa-volume-up"></i>
								</span>
                                <span class="onoffswitch">
									<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox"
                                           id="checkboxSoundPlay" onchange="saveSoundPlayOrStop();" checked>
									<label class="onoffswitch-label" for="checkboxSoundPlay">
										<span class="onoffswitch-inner" data-swchon-text="ON"
                                              data-swchoff-text="OFF"></span>
										<span class="onoffswitch-switch"></span>
									</label>
								</span>
                            </div>
                            <div class="widget-toolbar" role="menu">
                                <div class="btn-group">
                                    <aot:checkbox name="alm_level" list="${almLevelList }" inline="YES" init="YES"
                                                  place="BEFORE" initCode="ALL" initName="${ALL}"></aot:checkbox>
                                    <input type="hidden" id="srch_alm_level" name="srch_alm_level"/>
                                </div>
                            </div>
                        </header>
                        <div role="content" style="display: block;">
                            <div class="widget-body no-padding">
                                <!-- 하단 알람 정보 그리드 -->
                                <div class="col-sm-12" id="gridlist-width2">
                                    <table id="gridList"></table>
                                    <div id="gridToolbar2"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!-- content -->

        <aot:select name="gd_alm_status" id="gd_alm_status" group="ALM_STATUS" init="YES" initCode="" initName=""
                    style="display:none;width:110px;"></aot:select>
            <%-- 		<aot:select name="gd_alm_host" id="gd_alm_host" group="ALM_HOST" init="YES" initCode="" initName="" style="display:none;width:110px;"></aot:select> --%>

        <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" group="W001" init="YES"></aot:select>
        <aot:select name="select_alm_level" id="select_alm_level" style="display:none;" group="ALM_LEVEL"
                    init="YES"></aot:select>

        <audio id="PCF_Critical" class="hide" preload="auto" loop="loop">
            <source src="${PATH_ALARM_SOUND}/PCF_Critical.mp3" type="audio/mpeg"/>
            <source src="${PATH_ALARM_SOUND}/PCF_Critical.wav" type="audio/wav"/>
        </audio>
        <audio id="PCF_Major" class="hide" preload="auto" loop="loop">
            <source src="${PATH_ALARM_SOUND}/PCF_Major.mp3" type="audio/mpeg"/>
            <source src="${PATH_ALARM_SOUND}/PCF_Major.wav" type="audio/wav"/>
        </audio>
        <audio id="PCF_Minor" class="hide" preload="auto" loop="loop">
            <source src="${PATH_ALARM_SOUND}/PCF_Minor.mp3" type="audio/mpeg"/>
            <source src="${PATH_ALARM_SOUND}/PCF_Minor.wav" type="audio/wav"/>
        </audio>

    </div>
    <!-- main -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript" data-for="ready">
        let lockOnSelectRow = false;
        let soundId;
        const checkedSeqno = [];

        $(document).ready(function () {
            connectWS();

            $("input[name=alm_level]").parent().removeClass('checkbox').addClass('btn btn-xs');
            //검색조건 체크박스 전체 체크
            $("input[name=alm_level]:checkbox").prop("checked", true);

            //검색조건 체크박스 전체 체크
            $("input[name=alm_level]:checkbox").prop("checked", true);
            $(document).on('change', 'input[name=alm_level]:checkbox', function (e) {
                //전체 체크
                if ($(this).is(':checked') == true && $(this).val() == "ALL") {
                    $("input[name=alm_level]:checkbox").prop("checked", true);
                } else if ($(this).is(':checked') == false && $(this).val() == "ALL") {
                    $("input[name=alm_level]:checkbox").prop("checked", false);
                } else if ($(this).val() != "ALL" && $("input[name=alm_level]:checkbox")[0].checked === true) {
                    $("input[name=alm_level]:checkbox")[0].checked = false;
                }
                chkEvent();
            });
            chkEvent();

            jQuery("#gridList").jqGrid({
                // 				url : "${CONTEXT_PATH}/obstacle/getAlarmList.json",
                // 				postData : {
                // 					srch_alm_level : $("#srch_alm_level").val()
                // 				},
                page: "${SEARCH_DATA.jqPage}",
                // 				datatype : "json",
                // 				jsonReader : {
                // 					root : "root",
                // 					page : "page",
                // 					records : "records",
                // 					repeatitems : false
                // 				},
                datatype: 'local',
                // 				mtype : 'POST',
                autoencode: true,
                height: 300,
                cache: false,
                loadui: 'disable',
                colNames: ["",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.HOST_NM'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_OCCUR_DT'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_STATUS'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_LEVEL'/>",

                    "Alarm Group",

                    "Alarm Instance",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CODE'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_MSG'/>",

                    "<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_REMARK1'/>",

                    "Alarm Recovery", "Alarm Recovery",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_KONW_DT'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CLEAR_DT'/>",

                    // 				"<spring:message code="TEXT.ALM_HIST.GRID.SOUND_YN"/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.ALM_CONFIRM_USER'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.OCCUR_CNT'/>",

                    // 				"<spring:message code='TEXT.OBSTACLE.ALM.POP.SNMP.ALARM.GRID.FIRST_OCCUR_DT'/>",

                    ""],
                colModel: [{
                    name: "seqno",
                    index: "seqno",
                    width: 230,
                    hidden: true,
                    key: true
                },
                    /* {name:"host_nm"				,index:"host_nm"				, width:230}, */
                    {
                        // 					name : 'host_nm',
                        // 					index : 'host_nm',
                        // 					width : 100,
                        // 					align : 'left'
                        // 				}, {
                        name: "alm_occur_dt",
                        index: "alm_occur_dt",
                        width: 140,
                        align: 'center',
                        search: false
                    }, {
                        name: 'alm_status',
                        index: 'alm_status',
                        width: 100,
                        align: 'center',
                        stype: "select",
                        searchoptions: {
                            value: AotJqGrid.getSelectTagData($("#gd_alm_status > option"))
                        },
                        edittype: "select",
                        editoptions: {
                            value: AotJqGrid.getSelectTagData($("#gd_alm_status > option"))
                        },
                        formatter: 'select'
                    }, {
                        name: 'alm_level',
                        index: 'alm_level',
                        width: 90,
                        align: 'center',
                        stype: "select",
                        searchoptions: {
                            value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                        },
                        edittype: "select",
                        editoptions: {
                            value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                        },
                        formatter: 'select'
                    }, {
                        name: "alm_group",
                        index: "alm_group",
                        width: 100,
                        search: false
                    }, {
                        name: "alm_instance",
                        index: "alm_instance",
                        width: 100
                    }, {
                        name: "alm_code",
                        index: "alm_code",
                        align: 'center',
                        width: 90
                    }, {
                        name: "alm_msg",
                        index: "alm_msg",
                        width: 720
                    }, {
                        name: "alm_remark1",
                        index: "alm_remark1",
                        width: 320,
                        search: false
                    }, {
                        name: "alm_recovery_icon",
                        index: "alm_recovery_icon",
                        align: "left",
                        width: 120,
                        search: false,
                        formatter: alm_recoveryFormatter
                    }, {
                        name: "alm_recovery",
                        index: "alm_recovery",
                        align: "left",
                        hidden: true,
                        search: false
                        // 				}, {
                        // 					name : "alm_know_dt",
                        // 					index : "alm_know_dt",
                        // 					width : 140,
                        // 					align : 'center',
                        // 					search : false
                        // 				}, {
                        // 					name : "alm_clear_dt",
                        // 					index : "alm_clear_dt",
                        // 					width : 140,
                        // 					align : 'center',
                        // 					search : false
                        // 				}, {
                        // 					name : "sound_yn",
                        // 					index : "sound_yn",
                        // 					align : "center",
                        // 					stype : "select",
                        // 					searchoptions : {
                        // 						value : AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                        // 					},
                        // 					edittype : "select",
                        // 					editoptions : {
                        // 						value : AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                        // 					},
                        // 					formatter : 'select',
                        // 					width : 80

                        // 				}, {
                        // 					name : "alm_confirm_user",
                        // 					index : "alm_confirm_user",
                        // 					width : 100,
                        // 					search : false
                        // 				}, {
                        // 					name : "occur_cnt",
                        // 					index : "occur_cnt",
                        // 					width : 60,
                        // 					hidden : true,
                        // 					search : false
                        // 				}, {
                        // 					name : "first_occur_dt",
                        // 					index : "first_occur_dt",
                        // 					width : 230,
                        // 					hidden : true,
                        // 					search : false
                    }, {
                        name: "alm_category",
                        index: "alm_category",
                        width: 230,
                        hidden: true
                    }],
                sortname: "seqno",
                rowNum: 100,
                rowList: [100, 200, 300],
                sortorder: "desc",
                shrinkToFit: false,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar2",
                autowidth: true,
                multiselect: true,
                loadonce: true,
                // 				ondblClickRow : function(rowid) {
                onSelectRow: function (rowid) {
                    if (rowid) {
                        if (!lockOnSelectRow) {
                            lockOnSelectRow = true;
                            checkedSeqno.splice(0, checkedSeqno.length);
                            AotJqGrid.getSelarrrow($('#gridList')).forEach((value, index) => {
                                checkedSeqno.push(value);
                            })
                            const ret = AotJqGrid.getRowData($('#gridList'), rowid);
                            if (ret.alm_status !== 'KNOW') {
                                $.post('${CONTEXT_PATH}/obstacle/doCommentAndTerminate.json', {
                                    seqno: ret.seqno,
                                    work_type: 'COMMENT'
                                });
                            }
                        }
                        lockOnSelectRow = false;
                    }
                },
                loadComplete: function (data) {
                    $('#level-number>div.btn-group>label:eq(0)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(1)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(2)>strong').text(0);
                    $('#level-number>div.btn-group>label:eq(3)>strong').text(0);
                    data.rows.forEach((value, index) => {
                        if (value.alm_level === "CRI") {
                            $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() + 1);
                        } else if (value.alm_level === "MAJ") {
                            $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() + 1);
                        } else if (value.alm_level === "MIN") {
                            $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() + 1);
                        } else if (value.alm_level === "WAN") {
                            $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() + 1);
                        }
                        if (_.includes(checkedSeqno, value.seqno)) {
                            lockOnSelectRow = true;
                            $('#gridList').jqGrid('setSelection', value.seqno, true);
                            lockOnSelectRow = false;
                        }
                    });
                }
            });
            jQuery("#gridList").jqGrid("filterToolbar", {
                stringResult: true,
                searchOnEnter: false,
                defaultSearch: "cn",
                ignoreCase: true
            });

            AotJqGrid.setGridStyle();
// 			GridReload2();
        });
        let alm_recoveryFormatter = function (cellvalue, options, rowObject) {
            let html = '<a href="javascript:void(0);" onclick="goAlarmRecoveryModal(';
            html += '\'' + options.rowId + '\'';
            html += ');" >...<i class="fa fa-question-circle"></i></a>';
            return html;
        };
        let goAlarmRecoveryModal = function (rowId) {
            $('#alarmRecoveryModal div.modal-body').text(AotJqGrid.getRowData("#gridList", rowId).alm_recovery);
            $('#alarmRecoveryModal').modal({
                backdrop: 'static'
            });
        }
    </script>
    <script>
        let connectWS = function () {
            if (window["WebSocket"]) {
                let conn = new WebSocket('${wsUrl}');
                conn.onopen = function (evt) {
                    console.info("WS: Connection open.");
                    jQuery("#gridList").jqGrid('clearGridData');
                    setTimeout(function () {
                        GridReload2();
                    }, 100);
                };
                conn.onclose = function (evt) {
                    console.warn("WS: Connection closed.");
                    jQuery("#gridList").jqGrid('clearGridData');
                    AotSmartAdmin.smallBoxWarning("WS 연결이 해제되어 재 연결합니다...", 2500);
                    setTimeout(function () {
                        connectWS();
                    }, 3000);
                };
                conn.onmessage = function (evt) {
                    let json = JSON.parse(evt.data);
                    if ($('input[name="alm_level"][value="' + json.alm_level + '"]').prop('checked')) {
                        if (json.cmd === 'I') {
                            let rowsData = AotJqGrid.getRowsData($('#gridList'));
                            for (let i = 0; i < rowsData.length; i++) {
                                if (json.seqno === rowsData[i].seqno) {
                                    return;
                                }
                            }
                            AotJqGrid.appendData($('#gridList'), json);
                            if (json.alm_level === "CRI") {
                                $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() + 1);
                            } else if (json.alm_level === "MAJ") {
                                $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() + 1);
                            } else if (json.alm_level === "MIN") {
                                $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() + 1);
                            } else if (json.alm_level === "WAN") {
                                $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() + 1);
                            }
                        } else if (json.cmd === 'U') {
                            AotJqGrid.removeData($('#gridList'), 'seqno', json.seqno);
                            if (json.alm_level === "CRI") {
                                $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() - 1);
                            } else if (json.alm_level === "MAJ") {
                                $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() - 1);
                            } else if (json.alm_level === "MIN") {
                                $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() - 1);
                            } else if (json.alm_level === "WAN") {
                                $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() - 1);
                            }
                            AotJqGrid.appendData($('#gridList'), json);
                            if (json.alm_level === "CRI") {
                                $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() + 1);
                            } else if (json.alm_level === "MAJ") {
                                $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() + 1);
                            } else if (json.alm_level === "MIN") {
                                $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() + 1);
                            } else if (json.alm_level === "WAN") {
                                $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() + 1);
                            }
                        } else if (json.cmd === 'D') {
                            AotJqGrid.removeData($('#gridList'), 'seqno', json.seqno);
                            if (json.alm_level === "CRI") {
                                $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() - 1);
                            } else if (json.alm_level === "MAJ") {
                                $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() - 1);
                            } else if (json.alm_level === "MIN") {
                                $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() - 1);
                            } else if (json.alm_level === "WAN") {
                                $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() - 1);
                            }
                            _.pull(checkedSeqno, json.seqno);
                        }
                    }
                    let rowsData = AotJqGrid.getRowsData($('#gridList'));
                    for (let i = 0; i < rowsData.length; i++) {
                        if (_.includes(checkedSeqno, rowsData[i].seqno)) {
                            $('.cbox').eq(i + 1).prop('checked', true).data('checked', 'true');
                        }
                    }
                    loadComplete();
                };
                conn.onerror = function (err) {
                    console.error(err);
                    jQuery("#gridList").jqGrid('clearGridData');
                };
            } else {
                console.warn("WS: Your browser does not support WebSockets.");
                AotSmartAdmin.smallBoxWarning("Your browser does not support WebSockets.");
            }
        };
        let soundPlayOrStop = function () {
            $.post('${CONTEXT_PATH}/obstacle/getAlarmStatus.json', {}, function (response) {
                $.when().done(function () {
                    if ((response.responseData === 'SOUND_PLAY' && !$('#checkboxSoundPlay').prop('checked')) || (response.responseData === 'SOUND_STOP' && $('#checkboxSoundPlay').prop('checked'))) {
                        $('#checkboxSoundPlay').trigger('click');
                    }
                }).done(function () {
                    $('#PCF_Critical')[0].pause();
                    $('#PCF_Major')[0].pause();
                    $('#PCF_Minor')[0].pause();
                    if ($('#checkboxSoundPlay').prop('checked')) {
                        $('#' + soundId)[0].currentTime = 0;
                        catchPlaySound();
                    }
                });
            }, "json");
        };
        let firstPolicy = true;
        let catchPlaySound = function () {
            let resolves = $('#' + soundId)[0].play();
            resolves.then(
                // 이행값 기록
                function (val) {
                    firstPolicy = false;
                    $('#checkboxSoundPlay').prop('checked', true).on('change');
                })
                .catch(
                    // 거부 이유 기록
                    function (reason) {
                        console.warn(reason);
                        if (firstPolicy) {
                            AotSmartAdmin.smallBoxWarning('구글 크롬 정책으로 인해 최초 1회 이곳을 클릭하셔야 알람을 받으실 수 있습니다.');
                            firstPolicy = false;
                        }
                        $('#checkboxSoundPlay').prop('checked', false).on('change');
                        setTimeout(function () {
                            catchPlaySound();
                        }, 500);
                    });
        };
        let saveSoundPlayOrStop = function () {
            $.post('${CONTEXT_PATH}/obstacle/doSouneSet.json', {
                kind: $('#checkboxSoundPlay').prop('checked') ? 'SOUND_PLAY' : 'SOUND_STOP'
            }, function (response) {
                GridReload2();
            }, "json");
        };

        let chkEvent = function () {
            let checkdVal = [];
            $(':checkbox[name=alm_level]').each(function () {
                //전체 제외한 체크값 저장
                if ($(this).is(':checked') == true && $(this).val() != "ALL") {
                    checkdVal.push($(this).val());
                }
            });
            $("#srch_alm_level").val(checkdVal);
        };
        //선택 일괄 해지
        let checkedAlarm = function () {
            if (checkedSeqno.length == 0) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
            } else {
                goAlarmMstPop(checkedSeqno.join(","));
            }
        };

        /* 알람 목록 리로드 */
        let GridReload2 = function () {
            checkedSeqno.length = 0;
            $.post('${CONTEXT_PATH}/obstacle/getAlarmList.json', {
// 				srch_alm_level : $("#srch_alm_level").val(),
                srch_alm_level: 'CRI,MAJ,MIN,INFO',
                rows: 100,
                page: 1,
                sidx: 'seqno',
                sord: 'desc',
                loadonce: true
            }, function (response) {
                AotJqGrid.drawGridByLocalData($('#gridList'), response.root);
                loadComplete();
            });
        };
        //코멘트수정 & 해지 처리 팝업
        let goAlarmMstPop = function (seqno) {
            AotCommon.goWindow('/obstacle/goRemarkPop.pop?seqno=' + seqno, {
                height: 600,
                width: 830
            });
        };

        let popupHistory = function () {
            AotCommon.goWindow('/obstaclehist/alarmhistory.pop', {});
        };

        let alm_level_list = [];
        let loadComplete = function () {
            // $.each($("#gridList").getDataIDs(), function (idx, rowId) {
            //     let rowData = $("#gridList").getRowData(rowId);
            //
            //     if (rowData.alm_level === "CRI") {
            //         $('#level-number>div.btn-group>label:eq(0)>strong').text(+$('#level-number>div.btn-group>label:eq(0)>strong').text() + 1);
            //     } else if (rowData.alm_level === "MAJ") {
            //         $('#level-number>div.btn-group>label:eq(1)>strong').text(+$('#level-number>div.btn-group>label:eq(1)>strong').text() + 1);
            //     } else if (rowData.alm_level === "MIN") {
            //         $('#level-number>div.btn-group>label:eq(2)>strong').text(+$('#level-number>div.btn-group>label:eq(2)>strong').text() + 1);
            //     } else if (rowData.alm_level === "WAN") {
            //         $('#level-number>div.btn-group>label:eq(3)>strong').text(+$('#level-number>div.btn-group>label:eq(3)>strong').text() + 1);
            //     }
            // });
            // let srch_alm_level = $("#srch_alm_level").val();
            // alm_level_list.length = 0;
            // $.each($("#gridList").getDataIDs(), function (idx, rowId) {
            //     let rowData = $("#gridList").getRowData(rowId);
            //     if (!_.includes(srch_alm_level, rowData.alm_level)) {
            //         alm_level_list.push(rowData.seqno);
            //     }
            // });
            // $.each(alm_level_list, function (idx, value) {
            //     AotJqGrid.removeData($('#gridList'), 'seqno', value);
            // });
            alm_level_list.length = 0;
            $.each($("#gridList").getDataIDs(), function (idx, rowId) {
                let rowData = $("#gridList").getRowData(rowId);

                let background = '#fff';
                let color = '#000';
                if (rowData.alm_level === "CRI") {
                    background = "#a90329", color = "#fff";
                } else if (rowData.alm_level === "MAJ") {
                    background = "#ffbb00";
                } else if (rowData.alm_level === "MIN") {
                    background = "#faed7d";
                } else if (rowData.alm_level === "WAN") {
                    background = "#fff";
                }

                $("#gridList").setRowData(rowId, false, {
                    background: background,
                    color: color
                });

                if (rowData.alm_status === 'HAPPEN') {
                    alm_level_list.push(rowData.alm_level);
                }
            });
            $('#updateDate2').html('<i class="fa fa-clock-o" aria-hidden="true"></i> Last Updated: ' + moment().format('YY/MM/DD HH:mm:ss'));
            if (alm_level_list.length === 0) {
                $('#PCF_Critical')[0].pause();
                $('#PCF_Major')[0].pause();
                $('#PCF_Minor')[0].pause();
            } else {
                if (_.includes(alm_level_list, "CRI")) {
                    soundId = "PCF_Critical";
                } else if (_.includes(alm_level_list, "MAJ")) {
                    soundId = "PCF_Major";
                } else if (_.includes(alm_level_list, "MIN")) {
                    soundId = "PCF_Minor";
                }
                soundPlayOrStop();
            }
            $('.cbox:eq(0)').hide();
        }
    </script>
    </body>
</aot:html>
