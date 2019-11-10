;
AotAjax = {
    defaultOptions: {
        type: 'POST',
        async: true,
        cache: false,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        successMessage: undefined,
        autoResultFunctionTF: false,
        // autoAjaxErrorFunctionTF : 오픈 후 false로... true는 개발용
        autoAjaxErrorFunctionTF: false,
        // 아래는 getSelectOptions() 옵션
        placeholder: '선택하세요.',
        withCodeTF: false,
        value: 'value',
        label: 'label'
    },

    excute: function (paramUrl, paramData, paramOption) {
        var chk = AotAjax.checkMaxPostSize(paramData);
        if (chk !== null) {
            return chk;
        }
        var promise;
        Pace.track(function () {
            Pace.restart();
            var options = AotCommon.getOptions(AotAjax.defaultOptions, paramOption);
            options['url'] = paramUrl;
            options['data'] = paramData;
            promise = $.ajax(options).done(function (response) {
                AotAjax.doneDefaultFunction(response, options);
            }).done(function (response) {
                AotAjax.doneAutoResultFunction(response, options);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                AotAjax.failFunction(jqXHR, textStatus, errorThrown, options);
            });
        });
        return promise;
    },
    excuteWithFile: function (paramUrl, paramFormData, paramOption) {
        var chk = AotAjax.checkMaxPostSize(paramFormData);
        if (chk !== null) {
            return chk;
        }
        var promise;
        Pace.track(function () {
            Pace.restart();
            var options = AotCommon.getOptions(AotAjax.defaultOptions, paramOption);
            options['url'] = paramUrl;
            options['data'] = paramFormData;
            options['processData'] = false;
            options['contentType'] = false;
            promise = $.ajax(options).done(function (response) {
                AotAjax.doneDefaultFunction(response, options);
            }).done(function (response) {
                AotAjax.doneAutoResultFunction(response, options);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                AotAjax.failFunction(jqXHR, textStatus, errorThrown, options);
            });
        });
        return promise;
    },

    getSelectOptions: function (paramTargetObj, paramUrl, paramData, paramOption) {
        var chk = AotAjax.checkMaxPostSize(paramData);
        if (chk !== null) {
            return chk;
        }
        var promise;
        Pace.track(function () {
            Pace.restart();
            var options = AotCommon.getOptions(AotAjax.defaultOptions, paramOption);
            options['url'] = paramUrl;
            options['data'] = paramData;
            promise = $.ajax(options).done(function (response) {
                AotAjax.doneDefaultFunction(response, options)
            }).done(function (response) {
                $(paramTargetObj).html('');
                if (!$(paramTargetObj).prop('required') || (AotCommon.isNotEmpty(paramOption) && AotCommon.isNotEmpty(paramOption.placeholder))) {
                    $(paramTargetObj).append("<option value=''>" + options.placeholder + "</option>");
                }
                $.each(response, function (index, value) {
                    if (options.withCodeTF) {
                        $(paramTargetObj).append("<option value='" + value[options.value] + "'>" + "[" + value[options.value] + "]" + value[options.label] + "</option>");
                    } else {
                        $(paramTargetObj).append("<option value='" + value[options.value] + "'>" + value[options.label] + "</option>");
                    }
                });
            }).fail(function (jqXHR, textStatus, errorThrown) {
                AotAjax.failFunction(jqXHR, textStatus, errorThrown, options);
            });
        });
        return promise;
    },
    downloadFile: function (paramUrl, paramData, paramOption) {
        var chk = AotAjax.checkMaxPostSize(paramData);
        if (chk !== null) {
            return chk;
        }
        var promise;
        Pace.track(function () {
            Pace.restart();
            var options = AotCommon.getOptions(AotAjax.defaultOptions, paramOption);
            promise = $.fileDownload(paramUrl, {
                httpMethod: options.type,
                data: paramData,
                prepareCallback: function (url) {
                    //
                }
            }).fail(function (responseHtml, url, error) {
                console.warn("responseHtml : " + responseHtml + "\nurl : " + url + "\nerror : " + error);
                var response = AotAjax.getJsonFromWrappedInPreTag(responseHtml);
                if (AotCommon.isNotEmpty(response.responseMessage)) {
                    AotSmartAdmin.smallBoxsuccess(response.responseMessage);
                } else {
                    AotSmartAdmin.smallBoxWarning('파일이 존재하지 않습니다.');
                }
            });
        });
        return promise;
    },
    checkMaxPostSize: function (paramData) {
        if (AotCommon.isNotEmpty(paramData) && JSON.stringify(paramData).length > 8388608) { // 현재8MB : server.xml maxPoolSize값 참고
            AotSmartAdmin.smallBoxWarning('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.');
            return $.Deferred().reject('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.').promise();
        } else {
            return null;
        }
    },
    // 가끔 ajax 에러발생시 <pre>를 포함한 데이터가 넘어올 때가 있다.
    getJsonFromWrappedInPreTag: function (paramHtml) {
        if (!!~paramHtml.toString().indexOf('<pre ') || !!~paramHtml.toString().indexOf('<pre>')) {
            try {
                return JSON.parse(paramHtml.substring(paramHtml.indexOf('>') + 1, paramHtml.indexOf('</')));
            } catch (e) {
                console.warn(e);
            }
        }
        return paramHtml;
    },
    doneDefaultFunction: function (response, options) {
        if (options.dataType === 'json') {
            response = AotAjax.getJsonFromWrappedInPreTag(response);
        }
        if (response.responseCode === 'E011') {
            AotSmartAdmin.confirmBox("페이지 시간초과\n페이지를 새로고침하여 로그인페이지로 안내합니다.", function () {
                window.location.reload(true);
            });
            return false;
        }
    },
    doneAutoResultFunction: function (response, options) {
        if (options.autoResultFunctionTF) {
            if (AotCommon.isNotEmpty(response.responseCode) && AotCommon.isNotEmpty(response.responseMessage)) {
                var message = null;
                if (AotCommon.isNotEmpty(options.successMessage) && _.startsWith(response.responseCode, 'S')) {
                    message = options.successMessage;
                } else {
                    message = response.responseMessage;
                }
                if (AotCommon.isNotEmpty(response.additionalMessage)) {
                    message += "\n" + response.additionalMessage;
                }
                if (_.startsWith(response.responseCode, 'S')) {
                    AotSmartAdmin.smallBoxsuccess(message);
                } else {
                    AotSmartAdmin.smallBoxWarning(message);
                }
            }
        }
    },
    failFunction: function (jqXHR, textStatus, errorThrown, options) {
        console.warn("jqXHR : " + jqXHR + "\ntextStatus : " + textStatus + "\nerrorThrown : " + errorThrown);
        if (textStatus === "timeout") {
            console.warn("요청시간 초과");
            if (options.autoAjaxErrorFunctionTF) {
                AotSmartAdmin.smallBoxWarning("요청시간 초과");
            }
        } else {
            console.warn("시스템에러: return null 또는 HTTP 500 ");
            if (options.autoAjaxErrorFunctionTF) {
                AotSmartAdmin.smallBoxWarning("잠시후 다시 시도해주세요.");
            }
        }
    },
    drawHtml: function (targetObj, mode, html) {
        return $.when().done(function () {
            if (mode === 'html') {
                AotHandlebars.removeTemplateScript(targetObj);
                $(targetObj).html('').html(html);
            } else if (mode === 'append') {
                $(targetObj).append(html);
            } else if (mode === 'appendTo') {
                $(targetObj).appendTo(html);
            } else if (mode === 'prepend') {
                $(targetObj).prepend(html);
            } else if (mode === 'prependTo') {
                $(targetObj).prependTo(html);
            } else if (mode === 'after') {
                $(targetObj).after(html);
            } else if (mode === 'before') {
                $(targetObj).before(html);
            }
        }).done(function () {
            AotAjax.initView(targetObj);
            // pageSetUp();
            // AotAjax.removeDeplicationModal(targetObj);
        });
    },
    initView: function (targetObj) {
        targetObj = targetObj || $(document);

        // Handlebars
        try {
            AotHandlebars.setTemplateScript(targetObj, true);
        } catch (e) {
            // console.warn(e);
        }
        // MaxLength
        try {
            AotMaxlength.init($(targetObj));
        } catch (e) {
            // console.warn(e);
        }
        // AotTextEditor
        try {
            AotTextEditor.init($(targetObj));
        } catch (e) {
            // console.warn(e);
        }

        // AotDatetimePicker
        // try {
        // AotDatetimePicker.init($(targetObj));
        // } catch (e) {
        // console.warn(e);
        // }
        // },
        // removeDeplicationModal : function(targetObj) {
        // if (!targetObj) {
        // return;
        // }
        // // ajax를 통해 가져온 (기존)중복된 Model 제거
        // try {
        // $.each($(targetObj).find('.jmodal'), function(index, value) {
        // if ($('body > #' + $(value).prop('id')) !== $(value)) {
        // $('body > #' + $(value).prop('id')).remove();
        // }
        // });
        // } catch (e) {
        // console.warn(e);
        // }
    }
};