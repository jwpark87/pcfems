;
AotJqGrid = {
    original_grid_height: null,

    reloadGrid: function (targetObj, params) {
        return $.when().done(function () {
            $(targetObj).jqGrid('clearGridData').jqGrid('setGridParam', params).trigger("reloadGrid");
        });
    },
    getSelrow: function (targetObj) {
        return $(targetObj).getGridParam("selrow");
    },
    getSelectedRowData: function (targetObj) {
        return $(targetObj).getRowData(+AotJqGrid.getSelrow($(targetObj)));
    },
    getSelarrrow: function (targetObj) {
        return $(targetObj).getGridParam('selarrrow');
    },
    getSelarrrowRowData: function (targetObj) {
        var selarrrow = AotJqGrid.getSelarrrow($(targetObj));
        var selarrrowRowData = [];
        $.each(selarrrow, function (index, value) {
            selarrrowRowData.push(AotJqGrid.getRowData($(targetObj), value));
        });
        return selarrrowRowData;
    },
    getRowsData: function (targetObj) {
        return targetObj.jqGrid('getGridParam', 'data');
    },
    getRowData: function (targetObj, rowIdex) {
        return $(targetObj).getRowData(rowIdex);
    },
    appendData: function (targetObj, jsonData) {
        const data = AotJqGrid.getRowsData(targetObj);
        data.push(jsonData);
        AotJqGrid.reloadGridByLocalData(targetObj, data);
    },
    removeData: function (targetObj, keyName, keyValue) {
        const data = AotJqGrid.getRowsData(targetObj);
        data.forEach(function (value, index) {
            if (value[keyName] === keyValue) {
                data.splice(index, 1);
                return;
            }
        });
        AotJqGrid.reloadGridByLocalData(targetObj, data);
    },
    reloadGridByLocalData: function (targetObj, data) {
        $(targetObj).jqGrid('clearGridData').jqGrid('setGridParam', {
            datatype: 'local',
            data: data
        }).trigger("reloadGrid");
    },
    drawGridByLocalData: function (targetObj, data) {
        $(targetObj).jqGrid('clearGridData').jqGrid('setGridParam', {
            datatype: 'local',
            data: data
        }).trigger("reloadGrid");
    },
    getCell: function (targetObj, rowIdex, columnName) {
        return $(targetObj).jqGrid("getCell", rowIdex, columnName);
    },
    formatterYYYYMMDD: function (cellValue, options, rowObject) {
        if (AotCommon.isNotEmpty(cellValue)) {
            return moment(cellValue).format("YYYY-MM-DD");
        }
    },
    formatterYYYYMMDDHHMMSS: function (cellValue, options, rowObject) {
        if (AotCommon.isNotEmpty(cellValue)) {
            return moment(cellValue).format("YYYY-MM-DD HH:mm:ss");
        }
    },
    formatterCurrencyDefaultValue: function (cellValue, options, rowObject) {
        if (AotCommon.isEmpty(cellValue)) {
            return "-";
        }
        return cellValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    unformatterCurrencyDefaultValue: function (cellValue, options, rowObject) {
        return cellValue.split(',').join('');
    },
    formatterPercentDefaultValue: function (cellValue, options, rowObject) {
        if (AotCommon.isEmpty(cellValue)) {
            return "- (%)";
        }
        return cellValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' (%)';
    },
    unformatterPercentDefaultValue: function (cellValue, options, rowObject) {
        return cellValue.split(',').join('').split(' (%)').join('');
    },
    getSelectTagData: function (obj) {
        var selectData = "";
        for (i = 0; i < obj.length; i++) {
            selectData += obj.eq(i).val() + ":" + obj.eq(i).text();
            if (i < (obj.length - 1)) {
                selectData += ";";
            }
        }
        return selectData;
    },
    setGridHeightOnFullscreen: function () {
        if ($('#jarviswidget-fullscreen-mode').length > 0) { // 풀스크린 모드로 전환
            AotJqGrid.original_grid_height = $('#jarviswidget-fullscreen-mode div.ui-jqgrid-bdiv').height();
            $('#jarviswidget-fullscreen-mode div.ui-jqgrid-bdiv').css(
                'height',
                $('#jarviswidget-fullscreen-mode div[role="content"]').height() - $('header[role="heading"]').outerHeight() - $('#jarviswidget-fullscreen-mode div.ui-jqgrid-hdiv').outerHeight()
                - $('#jarviswidget-fullscreen-mode div.ui-jqgrid-pager').outerHeight());
            $('#jarviswidget-fullscreen-mode div.widget-body').removeClass('no-padding');
        } else {
            $('div.ui-jqgrid-bdiv').css('height', AotJqGrid.original_grid_height);
            $('div.widget-body').addClass('no-padding');
        }
        $('div.ui-corner-all, div.ui-jqgrid-view, div.ui-jqgrid-bdiv, div.ui-jqgrid-hdiv').css('width', '100%');
    },
    setGridStyle: function () {
        $('.ui-jqgrid-btable').addClass('table-bordered');
        // $(".ui-jqgrid").removeClass("ui-widget ui-widget-content");
        // $(".ui-jqgrid-view").children().removeClass("ui-widget-header ui-state-default");
        // $(".ui-jqgrid-pager").removeClass("ui-state-default");
        // $(".ui-jqgrid").removeClass("ui-widget-content");
        //
        // $(".ui-jqgrid-htable").addClass("table table-bordered table-hover");
        // $(".ui-jqgrid-btable").addClass("table table-bordered table-striped");
        //
        // $(".ui-icon.ui-icon-seek-prev").wrap("<div class='btn btn-sm btn-default'></div>");
        // $(".ui-icon.ui-icon-seek-prev").removeClass().addClass("fa fa-backward");
        //
        // $(".ui-icon.ui-icon-seek-first").wrap("<div class='btn btn-sm btn-default'></div>");
        // $(".ui-icon.ui-icon-seek-first").removeClass().addClass("fa fa-fast-backward");
        //
        // $(".ui-icon.ui-icon-seek-next").wrap("<div class='btn btn-sm btn-default'></div>");
        // $(".ui-icon.ui-icon-seek-next").removeClass().addClass("fa fa-forward");
        //
        // $(".ui-icon.ui-icon-seek-end").wrap("<div class='btn btn-sm btn-default'></div>");
        // $(".ui-icon.ui-icon-seek-end").removeClass().addClass("fa fa-fast-forward");
    }
};

$(window).off('resize.jqGrid').on('resize.jqGrid', function () {
    if ($('#jarviswidget-fullscreen-mode').length > 0) { // 풀스크린 모드로 전환
        // $('#content') // left-padding, right-padding
        $('#jarviswidget-fullscreen-mode .ui-jqgrid-btable').jqGrid('setGridWidth', $('#jarviswidget-fullscreen-mode div[role="content"]').width() - 30);
    } else {
        $('.ui-jqgrid-btable').each(function () {
            // $('#content') // left-padding, right-padding
            $(this).jqGrid('setGridWidth', $(window).innerWidth() -

                ($(window).outerWidth() > 979 && !$('body').hasClass('hidden-menu') ? $('#left-panel').outerWidth() : 0)

                - (!$('body').hasClass('hidden-menu') ? 28 : 37));
        });
    }
    $('.ui-jqgrid').css('width', '100%');
    AotJqGrid.setGridHeightOnFullscreen();
});
