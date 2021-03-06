;
AotCommon = {
    isEmpty: function (obj) {
        if (obj === undefined || obj === null || obj === '' || obj.length === 0) {
            return true;
        }
        if (typeof obj === 'object') {
            return $.isEmptyObject(obj);
        }
        return false;
    },
    isNotEmpty: function (obj) {
        return !AotCommon.isEmpty(obj);
    },
    // 재요청시 XSS가 중복으로 처리되어 &, # 등의 문자가 중복 필터처리 된다. 때문에 값을 원본으로 변환하여 .text(value), .val(value) 로 처리한다.
    unescapeXss: function (input) {
        return $('<textarea />').html(input).text();
    },
    defaultLinkOptions: {
        center: 'screen',
        // createNew : true,
        height: $(window).outerHeight() * 0.85,
        left: 0,
        location: false, // 메뉴아이콘 출력
        menubar: false, // 메뉴 바 사용
        name: null,
        onUnload: null,
        resizable: true, // 사이즈 변경 사용
        scrollbars: true, // 스크롤 바 사용
        status: false, // 하단 상태바 출력
        toolbar: false, // 도구창 사용
        top: 0,
        width: $(window).outerWidth() * 0.80
    },
    goLink: function (url, isNewWindow, paramOption) {
        // option은 다음 경로에서 확인 https://github.com/mkdynamic/jquery-popupwindow
        var tempUrl = _.trim(url);
        if (AotCommon.isEmpty(tempUrl) || tempUrl === '#' || tempUrl === 'null') {
            AotSmartAdmin.smallBoxWarning('준비중입니다.');
            return;
        }
        if (!_.startsWith(tempUrl, 'http') && !_.startsWith(tempUrl, CONTEXT_PATH)) {
            tempUrl = CONTEXT_PATH + tempUrl;
        }

        var options = AotCommon.getOptions(AotCommon.defaultLinkOptions, paramOption);

        // url 앞에 http가 있으면 새창으로 띄운다.
        if (isNewWindow === 'L') { // 이동
            location.href = tempUrl;
        } else if (isNewWindow === 'W') { // 팝업
            $.popupWindow(tempUrl, options);
        } else if (isNewWindow === 'T') { // 탭
            options['location'] = true;
            options['menubar'] = true;
            options['status'] = true;
            options['toolbar'] = true;
            $.popupWindow(tempUrl, options);
        } else { // default
            if (_.startsWith(tempUrl, 'http')) { // 탭
                options['location'] = true;
                options['menubar'] = true;
                options['status'] = true;
                options['toolbar'] = true;
                $.popupWindow(tempUrl, options);
            } else { // 이동
                location.href = tempUrl;
            }
        }
    },
    goWindow: function (url, paramOption) {
        AotCommon.goLink(url, 'W', paramOption);
    },
    goTab: function (url) {
        AotCommon.goLink(url, 'T');
    },
    getLabelFromSelectTag: function (selectObj, value) {
        return $(selectObj).children('option[value="' + value + '"]').text();
    },
    getLabelFromRadioCheckboxTag: function (radioCheckboxObj, value) {
        var result = "";
        $(radioCheckboxObj).each(function () {
            if ($(this).val() === value) {
                result = $(this).data('label');
            }
        });
        return result;
    },
    // 사용법 <img src="" onerror="AotCommon.setNoImg(this);" />
    setNoImg: function (obj, paramnoImgPath) {
        var noImgPath = _.trim(paramnoImgPath);
        if (AotCommon.isEmpty(noImgPath)) {
            noImgPath = CONTEXT_PATH + '/resources/images/no-image-available.png';
        }
        $(obj).removeAttr('onerror').attr('data-original-src', $(obj).attr('src')).attr('src', noImgPath).addClass('noImg');
    },
    getOptions: function (defaultOption, userOptions) {
        var tempOptions = _.clone(defaultOption);
        if (userOptions) {
            for (var key in userOptions) {
                tempOptions[key] = userOptions[key];
            }
        }
        return tempOptions;
    },
    getFileExtension: function (filename) {
        return filename.slice((filename.lastIndexOf(".") - 1 >>> 0) + 2);
    },
    submitFormGET: function (url, param, target) {
        var $form = $('<form method="GET" />');
        $form.attr('action', url);
        target && $form.attr('target', target);
        for (var key in param) {
            $form.append('<input type="hidden" name="' + key + '" value="' + param[key] + '" >');
        }
        $(document.body).append($form[0]);
        $form[0].submit();
    },
    submitFormPOST: function (url, param, target) {
        var $form = $('<form method="POST" />');
        $form.attr('action', url);
        target && $form.attr('target', target);
        for (var key in param) {
            $form.append('<input type="hidden" name="' + key + '" value="' + param[key] + '" >');
        }
        $(document.body).append($form[0]);
        $form[0].submit();
    },
    formToJsonParam: function (formObj) {
        var formArray = $(formObj).serializeArray();
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    },
    isLocalhost: function (url) {
        url = url || document.domain;
        return _.endsWith(url, "localhost");
    }
};
$(document).ready(function () {
    if (_.endsWith(window.location.pathname, '.pop')) {
        $('div[id="main"][role="main"]').attr('id', 'panel');
    }
    if (_.includes(window.location.search, 'login=OK')) {
        AotCommon.goWindow(CONTEXT_PATH + '/obstacle/dashboard.pop', {
            width: 2000,
            height: 1000
        });
        window.location.replace(window.location.href.split('login=OK').join(''));
    }
});
