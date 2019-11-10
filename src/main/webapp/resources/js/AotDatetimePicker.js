;
AotDatetimePicker = {
    init: function (targetObj) {
        targetObj = targetObj || $(document);
        $(targetObj).find('.datepicker').each(function () {
            AotDatetimePicker.setDatePicker(this);
        });
        $(targetObj).find('.datepicker-yyyymmdd').each(function () {
            AotDatetimePicker.setDatePickerYYYYMMDD(this);
        });
        $(targetObj).find('.datepicker-yyyymm').each(function () {
            AotDatetimePicker.setDatePickerYYYYMM(this);
        });
        $(targetObj).find('.datepicker-yyyy').each(function () {
            AotDatetimePicker.setDatePickerYYYY(this);
        });

        $(targetObj).find('.timepicker').each(function () {
            AotDatetimePicker.setTimePicker(this);
        });
        $(targetObj).find('.timepicker-hhmmss').each(function () {
            AotDatetimePicker.setTimePickerHHmmss(this);
        });
        $(targetObj).find('.timepicker-hhmm').each(function () {
            AotDatetimePicker.setTimePickerHHmm(this);
        });
        $(targetObj).find('.timepicker-hh').each(function () {
            AotDatetimePicker.setTimePickerHH(this);
        });

        $(targetObj).find('.datetimepicker').each(function () {
            AotDatetimePicker.setDateTimePicker(this);
        });
        $(targetObj).find('.datetimepicker-yyyymmddhhmmss').each(function () {
            AotDatetimePicker.setDateTimePickerYYYYMMDDHHmmss(this);
        });
        $(targetObj).find('.datetimepicker-yyyymmddhhmm').each(function () {
            AotDatetimePicker.setDateTimePickerYYYYMMDDHHmm(this);
        });
        $(targetObj).find('.datetimepicker-yyyymmddhh').each(function () {
            AotDatetimePicker.setDateTimePickerYYYYMMDDHH(this);
        });

    },
    setDatePicker: function (targetObj) {
        AotDatetimePicker.setDatePickerYYYYMMDD(targetObj);
        return $(targetObj);
    },
    setDatePickerYYYYMMDD: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'YYYY-MM-DD'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setDatePickerYYYYMM: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'YYYY-MM'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setDatePickerYYYY: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'YYYY'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },

    setTimePicker: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setTimePickerHHmm(targetObj);
        }
        return $(targetObj);
    },
    setTimePickerHHmmss: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'HH:mm:ss'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setTimePickerHHmm: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'HH:mm'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setTimePickerHH: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: 'HH'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },

    setDateTimePicker: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setDateTimePickerYYYYMMDDHHmm(targetObj);
        }
        return $(targetObj);
    },
    setDateTimePickerYYYYMMDDHHmmss: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                // sideBySide : true,
                format: 'YYYY-MM-DD HH:mm:ss'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setDateTimePickerYYYYMMDDHHmm: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                // sideBySide : true,
                format: 'YYYY-MM-DD HH:mm'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },
    setDateTimePickerYYYYMMDDHH: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                // sideBySide : true,
                format: 'YYYY-MM-DD HH'
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },

    setDateTimePickerDefault: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({});
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },

    setDateTimePickerFormat: function (targetObj, paramFormat) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
        } else {
            AotDatetimePicker.setParentPositionRelative(targetObj);
            $(targetObj).datetimepicker({
                format: paramFormat,
                // sideBySide : true
            });
            AotDatetimePicker.setDefaultOption(targetObj);
        }
        return $(targetObj);
    },

    setFromTo: function (fromObj, toObj) {
        if ($(fromObj).length === 0 || $(toObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        $(fromObj).on("dp.change", function (e) {
            if (_.isEmpty($(fromObj).val())) {
                $(toObj).data("DateTimePicker").minDate(false);
            } else {
                $(toObj).data("DateTimePicker").minDate($(fromObj).data("DateTimePicker").date());
            }
        });
        $(toObj).on("dp.change", function (e) {
            if (_.isEmpty($(toObj).val())) {
                $(fromObj).data("DateTimePicker").maxDate(false);
            } else {
                $(fromObj).data("DateTimePicker").maxDate($(toObj).data("DateTimePicker").date());
            }
        });
        $(fromObj).trigger('dp.change').trigger('change');
        $(toObj).trigger('dp.change').trigger('change');
    },

    setDatePickerYYYYMMDDWithFromTo: function (fromObj, toObj) {
        if ($(fromObj).length === 0 || $(toObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        AotDatetimePicker.setDatePickerYYYYMMDD($(fromObj));
        AotDatetimePicker.setDatePickerYYYYMMDD($(toObj));
        $(fromObj).on("dp.change", function (e) {
            if (_.isEmpty($(fromObj).val())) {
                $(toObj).data("DateTimePicker").minDate(false);
            } else {
                $(toObj).data("DateTimePicker").minDate($(fromObj).data("DateTimePicker").date());
            }
        });
        $(toObj).on("dp.change", function (e) {
            if (_.isEmpty($(toObj).val())) {
                $(fromObj).data("DateTimePicker").maxDate(false);
            } else {
                $(fromObj).data("DateTimePicker").maxDate($(toObj).data("DateTimePicker").date());
            }
        });
        $(fromObj).trigger('dp.change').trigger('change');
        $(toObj).trigger('dp.change').trigger('change');
    },

    // 달력이 모달이나 새로 띄워진 화면에 띄우려면 위치를 지정해줘야한다.
    setParentPositionRelative: function (targetObj) {
        // var datetimepickerId = moment().valueOf() + _.random(0, 999);
        // $('<span style="position:relative;" data-datetimepicker-id="' + datetimepickerId + '">').insertBefore(targetObj);
        // $('span[data-datetimepicker-id="' + datetimepickerId + '"]').css({
        // margin : 0,
        // padding : 0,
        // border : 0
        // }).html($(targetObj));
    },

    setDefaultOption: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        $(targetObj).data("DateTimePicker").showClear(true).showTodayButton(true).locale(new moment().local('locale')).ignoreReadonly(true).useCurrent(false);
    },
    destroy: function (targetObj) {
        if ($(targetObj).length === 0) {
            console.warn('올바르지않은 대상');
            return;
        }
        $(targetObj).data("DateTimePicker").destroy();
    }
};
$(document).ready(function () {
    AotDatetimePicker.init();
});
