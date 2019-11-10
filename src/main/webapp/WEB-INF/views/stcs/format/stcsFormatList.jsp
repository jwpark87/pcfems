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
                                    <a href="javascript:void(0);" class="button-icon" onclick="deleteSrchFormatMst();">
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
                <div class="row">
                    <article class="col-sm-12">
                        <div class="jarviswidget jarviswidget-color-blueDark" role="widget">
                            <header role="heading" class="">
                                <div class="jarviswidget-ctrls" role="menu">
                                    <a href="javascript:void(0);" data-target="#addModal2" class="button-icon">
                                        <label>
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.REG"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" data-target="#modModal2" class="button-icon">
                                        <label>
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.UPDATE"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon" onclick="deleteSrchFormatDet();">
                                        <label>
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                            <spring:message code="TEXT.COMM.BTN.DELETE"/>
                                        </label>
                                    </a>
                                    <a href="javascript:void(0);" class="button-icon jarviswidget-refresh-btn" data-loading-text="&nbsp;&nbsp;Loading...&nbsp;" title="" data-placement="bottom"
                                       onclick="GridReload2();">
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
                                    <table id="gridList2"></table>
                                    <div id="gridToolbar2"></div>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
            </section>

            <aot:select name="select_use_yn" id="select_use_yn" style="display:none;" group="W001" init="YES"></aot:select>
            <aot:select name="select_search_yn" id="select_search_yn" style="display:none;" group="W001" init="YES"></aot:select>
            <aot:select name="select_text_align" id="select_text_align" style="display:none;" group="UI_ALIGN" init="YES"></aot:select>
            <aot:select name="select_grcode" id="select_grcode" style="display:none;" init="YES"></aot:select>
            <aot:select name="select_op_code" id="select_op_code" style="display:none;" group="SRCH_FILTER_TYPE" init="YES"></aot:select>
        </div>
    </div>
    <!-- Script 영역 -->
    <c:import url="${CONTEXT_PATH }/footer.do"/>
    <script type="text/javascript">
        var $targetModal;
        $(document).ready(function () {
            AotAjax.getSelectOptions($('#select_grcode'), '${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=getGrcodeValueLabelList', {}, {
                placeholder: ''
            }).done(function () {
                initJqGrid();
            });

            $('a[data-target="#addModal"]').on('click', function (ev) {
                ev.preventDefault();
                $targetModal = $("#addModal");
                $("#addModal").load('${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=mstModal', function () {
                    $targetModal.modal();
                    $targetModal.find("#index_type").prop('disabled', false).parent('label').removeClass('state-disabled');
                    AotMaxlength.init($targetModal);
                });
            });

            $('a[data-target="#modModal"]').on('click', function (ev) {
                ev.preventDefault();
                if (!AotJqGrid.getSelrow($("#gridList"))) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    return;
                }
                $targetModal = $("#modModal");
                $("#modModal").load('${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=mstModal', function () {
                    $targetModal.modal();
                    var rowData = AotJqGrid.getSelectedRowData($("#gridList"));
                    $targetModal.find("#index_type").val(rowData.index_type).prop('disabled', true).parent('label').addClass('state-disabled');
                    $targetModal.find("#format_name").val(rowData.format_name);
                    $targetModal.find("#es_base_url").val(rowData.es_base_url);
                    $targetModal.find("#def_col_id").val(rowData.def_col_id);
                    $targetModal.find("#def_fromdt").val(rowData.def_fromdt);
                    $targetModal.find("#def_todt").val(rowData.def_todt);
                    AotMaxlength.init($targetModal);
                });
            });
            $('a[data-target="#addModal2"]').on('click', function (ev) {
                ev.preventDefault();
                if (!AotJqGrid.getSelrow($("#gridList"))) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    return;
                }
                $targetModal = $("#addModal2");
                $("#addModal2").load('${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=detModal', function () {
                    $.when(AotAjax.getSelectOptions($targetModal.find('#use_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#text_align'), '/common/valuelabel/initSelect.json', {
                        grcode: 'UI_ALIGN'
                    }), AotAjax.getSelectOptions($targetModal.find('#search_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#threshold_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#op_code'), '/common/valuelabel/initSelect.json', {
                        grcode: 'SRCH_FILTER_TYPE'
                    }), AotAjax.getSelectOptions($targetModal.find('#grcode'), '${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=getGrcodeValueLabelList', {}, {
                        withCodeTF: true
                    })).done(function () {
                        $targetModal.modal();
                        $targetModal.find("#col_cd").prop('disabled', false).parent('input').removeClass('state-disabled');
                        $targetModal.find('#threshold_yn>option[value="N"]').prop('selected', true);
                        AotMaxlength.init($targetModal);
                    });
                });
            });

            $('a[data-target="#modModal2"]').on('click', function (ev) {
                ev.preventDefault();
                if (!AotJqGrid.getSelrow($("#gridList2"))) {
                    AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                    return;
                }
                $targetModal = $("#modModal2");
                $("#modModal2").load('${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=detModal', function () {
                    $.when(AotAjax.getSelectOptions($targetModal.find('#use_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#text_align'), '/common/valuelabel/initSelect.json', {
                        grcode: 'UI_ALIGN'
                    }), AotAjax.getSelectOptions($targetModal.find('#search_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#threshold_yn'), '/common/valuelabel/initSelect.json', {
                        grcode: 'W001'
                    }), AotAjax.getSelectOptions($targetModal.find('#op_code'), '/common/valuelabel/initSelect.json', {
                        grcode: 'SRCH_FILTER_TYPE'
                    }), AotAjax.getSelectOptions($targetModal.find('#grcode'), '${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=getGrcodeValueLabelList', {}, {
                        withCodeTF: true
                    })).done(function () {
                        $targetModal.modal();
                        // 		var rowData = AotJqGrid.getSelectedRowData($("#gridList2"));
                        var rowData = AotJqGrid.getRowsData($("#gridList2"))[$('#gbox_gridList2 tr.selected-row').index() - 1];
                        $targetModal.find("#col_cd").val(rowData.col_cd).prop('disabled', true).parent('label').addClass('state-disabled');
                        $targetModal.find("#col_nm").val(rowData.col_nm);
                        $targetModal.find("#text_align").val(rowData.text_align);
                        $targetModal.find("#text_align>option[value='" + rowData.text_align + "']").prop('selected', true);
                        $targetModal.find("#grcode>option[value='" + rowData.grcode + "']").prop('selected', true);
                        $targetModal.find("#dsp_width").val(rowData.dsp_width);
                        $targetModal.find("#search_yn>option[value='" + rowData.search_yn + "']").prop('selected', true);
                        $targetModal.find("#sortseq").val(rowData.sortseq);
                        $targetModal.find("#use_yn>option[value='" + rowData.use_yn + "']").prop('selected', true);
                        $targetModal.find("#threshold_yn>option[value='" + rowData.threshold_yn + "']").prop('selected', true);
                        $targetModal.find("#op_code>option[value='" + rowData.op_code + "']").prop('selected', true);
                        $targetModal.find("#op_value").val(rowData.op_value);
                        $targetModal.find("#alm_code").val(rowData.alm_code);
                        $targetModal.find("#alm_msg").val(rowData.alm_msg);
                        $targetModal.find("#remark1").val(rowData.remark1);
                        AotMaxlength.init($targetModal);
                    });
                });
            });
        });

        function initJqGrid() {
            jQuery("#gridList").jqGrid({
                url: "${CONTEXT_PATH}/stcs/format/stcsFormatList.do",
                postData: {
                    actionID: "getSrchFormatMstList"
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
                height: 200,
                colNames: [
                    //인덱스 타입
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.INDEX_TYPE'/>",
                    //포맷명
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.FORMAT_NAME'/>",
                    //검색 base url
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.ES_BASE_URL'/>",
                    //검색기준일자 컬럼 ID
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.DEF_COL_ID'/>",
                    //검색일자 기본값(FROM)
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.DEF_FROMDT'/>",
                    //검색일자 기본값(TO)
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.DEF_TODT'/>",
                    //작업일자
                    "<spring:message code='TEXT.UPDTDT'/>",
                    //작성자
                    "<spring:message code='TEXT.UPDT_USER'/>"],
                colModel: [{
                    name: "index_type",
                    index: "index_type"
                }, {
                    name: "format_name",
                    index: "format_name"
                }, {
                    name: "es_base_url",
                    index: "es_base_url",
                    width: 200
                }, {
                    name: "def_col_id",
                    index: "def_col_id"
                }, {
                    name: "def_fromdt",
                    index: "def_fromdt"
                }, {
                    name: "def_todt",
                    index: "def_todt"
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
                sortname: "index_type",
                rowNum: 20,
                rowList: [10, 20, 30],
                sortorder: "asc",
                shrinkToFit: true,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar",
                autowidth: false,
                onSelectRow: function (id) {
                    if (AotJqGrid.getSelrow(this)) {
                        var ret = AotJqGrid.getSelectedRowData(this);
                        //상세 그리드 리로드
                        GridReload2();
                    }
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

            jQuery("#gridList2").jqGrid({
                url: "${CONTEXT_PATH}/stcs/format/stcsFormatList.do",
                postData: {
                    actionID: "getSrchFormatDetList"
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
                height: 200,
                colNames: ["",
                    //컬럼 코드
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.COL_CD'/>",
                    //컬럼 이름
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.COL_NM'/>",
                    //텍스트 정렬
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.TEXT_ALIGN'/>",
                    //그룹코드
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.GRCODE'/>",
                    //출력 넓이
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.DSP_WIDTH'/>",
                    //검색조건 여부
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.SEARCH_YN'/>",
                    //정렬순번
                    "<spring:message code='TEXT.STCS.FORMAT.GRID.SORTSEQ'/>",
                    //사용여부
                    "<spring:message code='TEXT.USE_YN'/>", "Threshold사용여부", "Threshold operation code", "op check value", "발생알람코드", "알람메시지", "비고",
                    //최종작업일자
                    "<spring:message code='TEXT.UPDTDT'/>",
                    //작성자
                    "<spring:message code='TEXT.UPDT_USER'/>"],
                colModel: [{
                    name: "index_type",
                    index: "index_type",
                    hidden: true
                }, {
                    name: "col_cd",
                    index: "col_cd",
                    key: true
                }, {
                    name: "col_nm",
                    index: "col_nm"
                }, {
                    name: "text_align",
                    index: "text_align",
                    width: 130,
                    align: "center",
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_text_align > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_text_align > option"))
                    },
                    formatter: 'select'
                }, {
                    name: "grcode",
                    index: "grcode",
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_grcode > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_grcode > option"))
                    },
                    formatter: 'select',
                    width: 100
                }, {
                    name: "dsp_width",
                    index: "dsp_width",
                    width: 80,
                    align: "right",
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue,
                    search: false
                }, {
                    name: "search_yn",
                    index: "search_yn",
                    width: 80,
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_search_yn > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_search_yn > option"))
                    },
                    formatter: 'select',
                    align: "center"
                }, {
                    name: "sortseq",
                    index: "sortseq",
                    width: 80,
                    align: "right",
                    formatter: AotJqGrid.formatterCurrencyDefaultValue,
                    unformat: AotJqGrid.unformatterCurrencyDefaultValue,
                    sorttype: "number"
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
                    name: "threshold_yn",
                    index: "threshold_yn",
                    width: 120,
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
                    name: "op_code",
                    index: "op_code",
                    width: 120,
                    stype: "select",
                    searchoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_op_code > option"))
                    },
                    edittype: "select",
                    editoptions: {
                        value: AotJqGrid.getSelectTagData($("#select_op_code > option"))
                    },
                    formatter: 'select',
                    align: "center"
                }, {
                    name: "op_value",
                    index: "op_value",
                    width: 100,
                    align: "left"
                }, {
                    name: "alm_code",
                    index: "alm_code",
                    width: 100,
                    align: "left",
                    hidden: true
                }, {
                    name: "alm_msg",
                    index: "alm_msg",
                    width: 300,
                    align: "left"
                }, {
                    name: "remark1",
                    index: "remark1",
                    width: 300,
                    align: "left"
                }, {
                    name: "upd_dt",
                    index: "upd_dt",
                    width: 180,
                    align: "center",
                    formatter: AotJqGrid.formatterYYYYMMDDHHMMSS,
                    search: false
                }, {
                    name: "upd_id",
                    index: "upd_id",
                    width: 140,
                    align: "center",
                    search: false
                }],
                sortname: "col_cd",
                rowNum: 100,
                rowList: [100, 200, 300],
                sortorder: "asc",
                shrinkToFit: false,
                toolbarfilter: true,
                viewrecords: true,
                pager: "#gridToolbar2",
                autowidth: true,
                cellEdit: true,
                cellsubmit: 'remote',
                cellurl: '${CONTEXT_PATH}/stcs/format/stcsFormatList.do?actionID=updateSrchFormatDet',
                beforeSubmitCell: function (rowid, cellname, value) { // submit 전
                    return AotJqGrid.getRowData(this, rowid);
                },
                afterSubmitCell: function (res) { // 변경 후
                    return [_.startsWith(res.responseJSON.responseCode, "S"), res.responseJSON.responseMessage];
                },
                // 				onSelectRow : function(id) {
                // 					if (AotJqGrid.getSelrow(this)) {
                // 						var ret = AotJqGrid.getSelectedRowData(this);
                // 						//선택한 코드값
                // 						$("#sel_code").val(ret.code);
                // 					}
                // 				},
                ondblClickRow: function (rowid, iRow, iCol) {
                    var colModels = $(this).getGridParam('colModel');
                    var colName = colModels[iCol].name;
                    if (!_.includes('col_cd upd_dt upd_id', colName)) {
                        $(this).setColProp(colName, {
                            editable: true
                        });
                        $(this).editCell(iRow, iCol, true);
                    }
                },
                afterEditCell: function (rowid, cellname, value, iRow, iCol) {
                    $(this).setColProp(cellname, {
                        editable: false
                    });
                    var ret = AotJqGrid.getRowData(this, rowid);
                }
            });
            jQuery("#gridList2").jqGrid("navGrid", "#gridToolbar2", {
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
            jQuery("#gridList2").jqGrid("filterToolbar", {
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
                url: "${CONTEXT_PATH}/stcs/format/stcsFormatList.do",
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: sort,
                sortorder: order,
                page: 1,
                postData: {
                    actionID: "getSrchFormatMstList"
                }, // 검색조건
                search: true
                // 검색유무
            });
        }

        /* 상세 코드 목록 리로드 */
        function GridReload2() {
            AotJqGrid.reloadGrid($("#gridList2"), {
                url: "${CONTEXT_PATH}/stcs/format/stcsFormatList.do?index_type=" + AotJqGrid.getSelectedRowData("#gridList").index_type,
                datatype: "json",
                mtype: "POST",
                ignoreCase: true,
                sortname: "sortseq",
                sortorder: "asc",
                page: 1,
                postData: {
                    actionID: "getSrchFormatDetList"
                },
                search: true,
            });
        }

        //그룹코드 삭제
        function deleteSrchFormatMst() {
            if (!AotJqGrid.getSelrow($("#gridList"))) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                return;
            }
            AotSmartAdmin.confirmBox('<spring:message code="MSG.CONFIRM.DELETE"/>', function () {
                AotAjax.excute("${CONTEXT_PATH}/stcs/format/stcsFormatList.do?v=" + moment().valueOf(), {
                    actionID: "deleteSrchFormatMst",
                    index_type: AotJqGrid.getSelectedRowData("#gridList").index_type
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    GridReload('upd_dt', 'desc');
                });
            });
        }

        function deleteSrchFormatDet() {
            // 			if (!AotJqGrid.getSelrow($("#gridList2"))) {
            if ($('#gbox_gridList2 tr.selected-row').index() === -1) {
                AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
                return;
            }
            AotSmartAdmin.confirmBox('<spring:message code="MSG.CONFIRM.DELETE"/>', function () {
                var rowData = AotJqGrid.getRowsData($("#gridList2"))[$('#gbox_gridList2 tr.selected-row').index() - 1];
                AotAjax.excute("${CONTEXT_PATH}/stcs/format/stcsFormatList.do?v=" + moment().valueOf(), {
                    actionID: "deleteSrchFormatDet",
                    index_type: rowData.index_type,
                    col_cd: rowData.col_cd
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    GridReload2();
                });
            });
        }
    </script>
    <!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
    <!-- 그룹코드 -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="modModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

    <!-- 그룹코드 상세-->
    <div class="modal fade" id="addModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    <div class="modal fade" id="modModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
    </body>
</aot:html>
