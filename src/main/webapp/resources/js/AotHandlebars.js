;
AotHandlebars = {
    templates: {},
    setTemplateScript: function (targetObj, isAppendMode) {
        if (!isAppendMode) {
            AotHandlebars.templates = {};
        }
        targetObj = targetObj || $(document);

        $(targetObj).find('script[type="text/x-handlebars-template"]').each(function () {
            if (AotCommon.isNotEmpty($(this).attr('id'))) {
                if (isAppendMode && AotHandlebars.templates[$(this).attr('id')]) {
                    console.warn('이미 존재하는 templates 값입니다 ==> ' + $(this).attr('id'));
                    return;
                }
                AotHandlebars.templates[$(this).attr('id')] = Handlebars.compile($(this).html());
            }
        });
    },
    removeTemplateScript: function (targetObj) {
        targetObj = targetObj || $(document);
        $(targetObj).find('script[type="text/x-handlebars-template"]').each(function () {
            if (AotCommon.isNotEmpty($(this).attr('id'))) {
                delete AotHandlebars.templates[$(this).attr('id')];
            }
        });
    },
    drawDynamicHtml: function (targetObj, mode, template, data, options) {
        return $.when().done(function () {
            if (typeof AotHandlebars.templates[template] === 'function') {
                if (mode === 'html') {
                    $(targetObj).html('').html(AotHandlebars.templates[template](data));
                } else if (mode === 'append') {
                    $(targetObj).append(AotHandlebars.templates[template](data));
                } else if (mode === 'appendTo') {
                    $(targetObj).appendTo(AotHandlebars.templates[template](data));
                } else if (mode === 'prepend') {
                    $(targetObj).prepend(AotHandlebars.templates[template](data));
                } else if (mode === 'prependTo') {
                    $(targetObj).prependTo(AotHandlebars.templates[template](data));
                } else if (mode === 'after') {
                    $(targetObj).after(AotHandlebars.templates[template](data));
                } else if (mode === 'before') {
                    $(targetObj).before(AotHandlebars.templates[template](data));
                } else if (mode === 'text') {
                    $(targetObj).text(AotHandlebars.templates[template](data));
                }
            } else {
                console.warn('handlebars 잘못된 template 값 에러 ==> ' + template);
                console.warn('등록된 templates ==> ' + JSON.stringify(AotHandlebars.templates));
            }
        }).done(function () {
            AotAjax.initView(targetObj);
        });
    },
    init: function () {
        Handlebars.registerHelper({
            // if 모음
            if_eq: function (v1, v2, options) {
                if (v1 === v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_empty: function (value, options) {
                if (AotCommon.isEmpty(value)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_not_empty: function (value, options) {
                if (AotCommon.isNotEmpty(value)) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },
            if_size: function (v1, v2, options) {
                if (v1 > v2) {
                    return options.fn(this);
                }
                return options.inverse(this);
            },

            // String 처리 모음
            substring: function (str, beginIndex, endIndex) {
                if (AotCommon.isEmpty(str)) {
                    return '';
                }
                return str.substring(beginIndex, endIndex);
            },
            replace: function (str, beforeStr, afterStr) {
                if (AotCommon.isEmpty(str)) {
                    return '';
                }
                return str.split(beforeStr).join(afterStr);
            },
            unescapeXss: function (str) {
                return AotCommon.unescapeXss(str);
            },
            unescapeHtml: function (str) {
                return new Handlebars.SafeString(AotCommon.unescapeXss(str));
            },
            thousandComma: function (number) {
                if (AotCommon.isNotEmpty(number) && _.isNumber(number)) {
                    return $.number(number);
                }
                return number;
            },
            getYYYYMMDD: function (longValue) {
                if (AotCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD');
            },
            getYYYYMMDDHHMM: function (longValue) {
                if (AotCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD HH:mm');
            },
            getYYYYMMDDHHMMSS: function (longValue) {
                if (AotCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format('YYYY-MM-DD HH:mm:ss');
            },
            formatDate: function (longValue, pattern) {
                if (AotCommon.isEmpty(longValue)) {
                    return '';
                }
                return moment(longValue).format(pattern);
            },
            getMobileTelFull: function (str) {
                if (AotCommon.isEmpty(str)) {
                    return '';
                }

                var mobile = str.split('-').join('');
                if (mobile.length === 7) {
                    mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 7);
                } else if (mobile.length === 8) {
                    mobile = mobile.substring(0, 4) + '-' + mobile.substring(4, 8);
                } else if (mobile.length === 9) {
                    if (mobile.substring(0, 2) === '02') {
                        mobile = mobile.substring(0, 2) + '-' + mobile.substring(2, 5) + '-' + mobile.substring(5, 9);
                    } else {
                        mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 5) + '-' + mobile.substring(5, 9);
                    }
                } else if (mobile.length === 10) {
                    if (mobile.substring(0, 2) === '02') {
                        mobile = mobile.substring(0, 2) + '-' + mobile.substring(2, 6) + '-' + mobile.substring(6, 10);
                    } else {
                        mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 6) + '-' + mobile.substring(6, 10);
                    }
                } else if (mobile.length === 11) {
                    mobile = mobile.substring(0, 3) + '-' + mobile.substring(3, 7) + '-' + mobile.substring(7, 11);
                }
                return mobile;
            },

            // index + 1 을 사용할때
            inc: function (value, options) {
                return parseInt(value) + 1;
            },

            // 기타
            eq: function (v1, v2) {
                return v1 === v2;
            },
            ne: function (v1, v2) {
                return v1 !== v2;
            },
            lt: function (v1, v2) {
                return v1 < v2;
            },
            gt: function (v1, v2) {
                return v1 > v2;
            },
            lte: function (v1, v2) {
                return v1 <= v2;
            },
            gte: function (v1, v2) {
                return v1 >= v2;
            },
            and: function (v1, v2) {
                return v1 && v2;
            },
            or: function (v1, v2) {
                return v1 || v2;
            },
            contains: function (v1, v2) {
                return !v1 ? false : !!~v1.indexOf(v2);
            },
            notContains: function (v1, v2) {
                return !v1 ? false : !!~~v1.indexOf(v2);
            }
        });

    }
};
$(document).ready(function () {
    AotHandlebars.init();
    AotHandlebars.setTemplateScript();
});