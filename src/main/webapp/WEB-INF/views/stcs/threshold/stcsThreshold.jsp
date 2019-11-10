<%@page contentType="text/html; charset=utf-8" errorPage="/jsp/common/error/systemException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<aot:html title="${TITLE}" jqGrid="YES">
    <body>
    <c:import url="${CONTEXT_PATH }/top.do"/>
    <c:import url="${CONTEXT_PATH }/left.do"/>
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
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-title txt-color-blueDark">
                        <!-- PAGE HEADER -->
                        <i class="fa-fw fa fa-pencil-square-o"></i>
                            ${curr_menu_name }
                    </h1>
                </div>
            </div>

            <section id="widget-grid">
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" data-target="#addModal" class="button-icon">
                                        <label>
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.REG"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" data-target="#modModal" class="button-icon">
                                        <label>
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon" onclick="deleteStcsThreshold();">
                                        <label>
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.DELETE"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="GridReload();">
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

            <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" init="YES" group="W001"></aot:select>
            <select name="select_alm_level" id="select_alm_level" style="display: none;"></select>
            <select name="select_active_standby" id="select_active_standby" style="display: none;">
                <option value=""></option>
                <option value="A">Active</option>
                <option value="S">Standby</option>
            </select>
        </div>
    </div>
    <!-- Script 영역 -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        var $targetModal;
        $(document).ready(function () {
            $('a[data-target="#addModal"]').on('click', function (ev) {
                ev.preventDefault();
                $targetModal = $("#addModal");
                $("#addModal").load('${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do?actionID=stcsThresholdModal', function () {
                    $.when(AotAjax.getSelectOptions($targetModal.find('#use_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#alm_status'), '/common/valuelabel/initSelect.json', {
                        grcode: 'ALM_LEVEL'
                    })).done(function () {
                        $targetModal.modal();
                        $targetModal.find("#svr_id").prop('disabled', false).parent('label').removeClass('state-disabled');
                        $targetModal.find("#node_name").prop('disabled', false).parent('label').removeClass('state-disabled');
                        $targetModal.find('#alm_status>option[value="CLEAR"]').prop('selected', true);
                        AotMaxlength.init($targetModal);
                    });
                });
            });

            $('a[data-target="#modModal"]').on('click', function (ev) {
                ev.preventDefault();
                if (!AotJqGrid.getSelrow($("#gridList"))) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    return;
                }
                $targetModal = $("#modModal");
                $("#modModal").load('${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do?actionID=stcsThresholdModal', function () {
                    $.when(AotAjax.getSelectOptions($targetModal.find('#use_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#alm_status'), '/common/valuelabel/initSelect.json', {
                        grcode: 'ALM_LEVEL'
                    })).done(function () {
                        $targetModal.modal();
                        var rowData = AotJqGrid.getSelectedRowData($("#gridList"));
                        $targetModal.find("#svr_id").val(rowData.svr_id).prop('disabled', true).parent('label').addClass('state-disabled');
                        $targetModal.find("#node_name").val(rowData.node_name).prop('disabled', true).parent('label').addClass('state-disabled');
                        $targetModal.find("#node_group").val(rowData.node_group);
                        $targetModal.find('#ha_status>option[value="' + rowData.ha_status + '"]').prop('selected', true);
                        $targetModal.find('#alm_status>option[value="' + rowData.alm_status + '"]').prop('selected', true);
                        $targetModal.find("#host_name").val(rowData.host_name);
                        $targetModal.find("#sortseq").val(rowData.sortseq);
                        $targetModal.find('#use_yn>option[value="' + rowData.use_yn + '"]').prop('selected', true);
                        $targetModal.find("#hw_name").val(rowData.hw_name);
                        $targetModal.find("#vnfc_instance_id").val(rowData.vnfc_instance_id);
                        AotMaxlength.init($targetModal);
                    });
                });
            });

            AotAjax.getSelectOptions($('#select_alm_level'), '${CONTEXT_PATH}/common/valuelabel/initSelect.json', {
                grcode: 'ALM_LEVEL'
            }, {
                placeholder: ''
            }).done(function () {
                initJqGrid();
            });

        });

        function initJqGrid() {
            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do",
                postData: {
                    actionID: "getStcsThresholdList"
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
                colNames: ["서버ID", "노드이름", "노드그룹", "상태", "알람상태", "호스트명", "정렬순서", "사용여부", 'h/w 이름', 'vnfc_instance_id', "작업일시", "작업자"],
                colModel: [{
                    name: "svr_id",
                    index: "svr_id",
                    width: 100
                }, {
                    name: "node_name",
                    index: "node_name",
                    width: 100
                }, {
                    name: "node_group",
                    index: "node_group",
                    width: 100
                }, {
                    name: "ha_status",
                    index: "ha_status",
                    width: 100,
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_active_standby > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_active_standby > option"))
                    },
                    formatter: 'select',
                    align: "center"
                }, {
                    name: "alm_status",
                    index: "alm_status",
                    width: 100,
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_alm_level > option"))
                    },
                    formatter: 'select',
                    align: "center"
                }, {
                    name: "host_name",
                    index: "host_name",
                    width: 200
                }, {
                    name: "sortseq",
                    index: "sortseq",
                    width: 60,
                    sorttype: "number",
                    align: "right",
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue
                }, {
                    name: "use_yn",
                    index: "use_yn",
                    width: 80,
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_use_yn > option"))
                    },
                    formatter: 'select',
                    align: "center"
                }, {
                    name: "hw_name",
                    index: "hw_name",
                    width: 120
                }, {
                    name: "vnfc_instance_id",
                    index: "vnfc_instance_id",
                    width: 280
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    width: 180,
                    align: "center",
                    search: false,
                    formatter: AotJqGrid.formatterYYYYMMDDHHMMSS
                }, {
                    name: "upd_id",
                    index: "upd_id",
                    width: 140,
                    align: "center",
                    search: false
                }],
                sortname: "sortseq",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: false,
                onSelectRow: function (id) {
                }
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
        }

        /* 그룹 코드 목록 리로드 */
        function GridReload(sort, order) {
            sort = sort || $('#gbox_gridList .ui-jqgrid-labels>th').eq(0).attr('id').split('gridList_').join('');
            order = order || "asc";
            AotJqGrid.reloadGrid($("#gridList"), {
                url: "${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do",
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: sort,
                sortorder: order,
                page: 1,
                postData: {
                    actionID: "getStcsThresholdList"
                }, // 검색조건
                search: true
                // 검색유무
            });
        }

        //그룹코드 삭제
        function deleteStcsThreshold() {
            if (!AotJqGrid.getSelrow($("#gridList"))) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                return;
            }
            AotSmartAdmin.confirmBox('<spring:message code="MSG.CONFIRM.DELETE"/> <br/><i class="fa fa-warning"></i>삭제시 VM 정보가 사라지고, Dashboard 화면에서도 사라집니다. 계속 하시겠습니까?', function () {
                AotAjax.excute("${CONTEXT_PATH}/stcs/threshold/stcsThreshold.do?v=" + moment().valueOf(), {
                    actionID: "deleteStcsThreshold",
                    svr_id: AotJqGrid.getSelectedRowData("#gridList").svr_id,
                    node_name: AotJqGrid.getSelectedRowData("#gridList").node_name
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    GridReload('upd_dt', 'desc');
                });
            });
        }
    </script>
    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- 그룹코드 -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="modModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

    </body>
</aot:html>
