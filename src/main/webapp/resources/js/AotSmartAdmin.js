;
AotSmartAdmin = {
    smallBoxWarning: function (msg, timeout) {
        $.smallBox({
            title: "경고",
            content: msg,
            timeout: timeout,
            color: "#c26565",
            sound_file: 'voice_alert',
            icon: "fa fa-times bounce animated"
        });
    },
    smallBoxQuestion: function (msg, timeout) {
        $.smallBox({
            title: "확인",
            content: msg,
            timeout: timeout,
            color: "#9cb4c5",
            sound_file: 'smallbox',
            icon: "fa fa-question bounce animated",
        });
    },
    smallBoxInfo: function (msg, timeout) {
        $.smallBox({
            title: "알림",
            content: msg,
            timeout: timeout || 3000,
            color: "#3276B1",
            sound_file: 'bigbox',
            icon: "fa fa-info bounce animated"
        });
    },
    smallBoxsuccess: function (msg, timeout) {
        $.smallBox({
            title: "성공",
            content: msg,
            timeout: timeout || 3000,
            color: "#739E73",
            sound_file: 'bigbox',
            icon: "fa fa-check bounce animated"
        });
    },
    confirmBox: function (msg, yesFunction, noFunction) {
        $.SmartMessageBox({
            title: msg,
            buttons: '[No][Yes]'
        }, function (ButtonPressed) {
            if (ButtonPressed === "Yes") {
                setTimeout(function () {
                    if (typeof yesFunction === 'function') {
                        yesFunction();
                    } else {
                        console.warn('yesFunction은 function 타입이 아닙니다. type => ' + typeof yesFunction);
                    }
                }, 100);
            }
            if (ButtonPressed === "No") {
                setTimeout(function () {
                    if (typeof noFunction === 'function') {
                        noFunction();
                    } else {
                        console.warn('noFunction은 function 타입이 아닙니다. type => ' + typeof noFunction);
                    }
                }, 100);
            }
        });
    }
};
