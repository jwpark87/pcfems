<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/auth/authModPopup.jsp
 - 설    명	:	권한 레벨 팝업 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2016.03.10    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<c:set var="SEL_INIT">
    <spring:message code="message.selectbox.select"/>
</c:set>
<c:set var="CANCEL">
    <spring:message code='message.button.Cancel'/>
</c:set>
<c:set var="REGIST">
    <spring:message code='message.button.Submit'/>
</c:set>
<c:set var="DELETE">
    <spring:message code='TEXT.COMM.BTN.DELETE'/>
</c:set>
<div class="basicBox_500">
    <form id="frm" name="frm">
        <!-- ADD,MODIFY박스 -->
        <div class="popOutBox">
            <div class="view">

                <table class="newBox">
                    <tr>
                        <!-- 권한코드 -->
                        <td class="title">
                            <spring:message code="text.Auth.Mgmt.Pop.Title.Auth_code"/>
                        </td>
                        <td>
                            <input type="text" id="pop_auth_code" name="pop_auth_code" disabled value="${AUTH_DATA.auth_code}">
                        </td>
                    </tr>
                    <tr>
                        <!-- 권한명 - 한글 -->
                        <td class="title">
                            <spring:message code="text.Auth.Mgmt.Pop.Title.Auth_nm_kor"/>
                            <label>*</label>
                        </td>
                        <td>
                            <input type="text" id="pop_auth_nm_kor" name="pop_auth_nm_kor" class="validate[required,maxSize[50]]" value="${AUTH_DATA.auth_nm_kor}">
                        </td>
                    </tr>
                    <tr>
                        <!-- 권한명 - 영어-->
                        <td class="title">
                            <spring:message code="text.Auth.Mgmt.Pop.Title.Auth_nm_eng"/>
                            <label>*</label>
                        </td>
                        <td>
                            <input type="text" id="pop_auth_nm_eng" name="pop_auth_nm_eng" class="validate[required,maxSize[50]]" value="${AUTH_DATA.auth_nm_eng}">
                        </td>
                    </tr>
                    <tr>
                        <!-- 권한명 - 일어-->
                        <td class="title">
                            <spring:message code="text.Auth.Mgmt.Pop.Title.Auth_nm_jpn"/>
                        </td>
                        <td>
                            <input type="text" id="pop_auth_nm_jpn" name="pop_auth_nm_jpn" class="validate[maxSize[50]]" value="${AUTH_DATA.auth_nm_jpn}">
                        </td>
                    </tr>
                    <tr>
                        <!-- 사용여부 -->
                        <td class="title">
                            <spring:message code="text.Auth.Mgmt.Pop.Title.Auth_use_yn"/>
                        </td>
                        <td>
                            <!-- <aot:select name="pop_use_yn" id="pop_use_yn" group="USE_YN" /> -->
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </form>
    <!-- 버튼박스 -->

    <div class="btBox">
        <ul>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>

</div>
<!-- html End-->
<!-- Script Start -->
<script type="text/javascript">
    $(document).ready(function () {
        //수정한 사항 확인 - 색 변화
        $.common.modifyUI();
        $("#pop_use_yn").select2({
            minimumResultsForSearch: -1,
            height: 'copy',
            width: '168px'
        }).select2("val", "${AUTH_DATA.use_yn}");
    });

    //등록 처리
    $("#AUTH_SAVE_B").on("click", function () {
        var confirmMsg = '<spring:message code="MSG.CONFIRM.UPDATE"/>';

        $("#frm").validationEngine('attach', {
            prettySelect: true,
            usePrefix: 's2id_',
            promptPosition: "bottomRight",
            scroll: false
        });
        var validateForm = $(document.frm).validationEngine('validate');

        if (validateForm) {
            AotSmartAdmin.confirmBox(confirmMsg, function () {
                AotAjax.excute("${CONTEXT_PATH}/admin/auth/authPopup.do", {
                    actionID: "ACTION_AUTH_UPDATE_OK",
                    auth_code: $("#pop_auth_code").val(),
                    auth_nm_kor: $("#pop_auth_nm_kor").val(),
                    auth_nm_eng: $("#pop_auth_nm_eng").val(),
                    auth_nm_jpn: $('#pop_auth_nm_jpn').val(),
                    use_yn: $('#pop_use_yn').val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        doSearch();
                        PopClose();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            });
        }
    });

    //삭제 처리
    $("#AUTH_DELETE_B").on("click", function () {
        var confirmMsg = '<spring:message code="MSG.CONFIRM.DELETE"/>';

        $("#frm").validationEngine('attach', {
            prettySelect: true,
            usePrefix: 's2id_',
            promptPosition: "bottomRight",
            scroll: false
        });
        var validateForm = $(document.frm).validationEngine('validate');

        if (validateForm) {
            AotSmartAdmin.confirmBox(confirmMsg, function () {
                AotAjax.excute("${CONTEXT_PATH}/admin/auth/authPopup.do", {
                    actionID: "ACTION_AUTH_DELETE_OK",
                    auth_code: $("#pop_auth_code").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] == "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        doSearch();
                        PopClose();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            });
        }
    });
    //팝업닫기
    $("#AUTH_CLOSE_B").on("click", function () {
        PopClose();
    });

    function PopClose() {
        $.common.cancelUI();
        $.common.close("authModPopup");
    }
</script>