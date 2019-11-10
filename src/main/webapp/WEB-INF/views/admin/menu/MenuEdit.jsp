<%----------------------------------------------------------------------------------------
 - 파일이름	:	admin/menu/Update.jsp
 - 설    명	:	메뉴데이터관리 메슈수정 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2008.09.29    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript">

    <%-- 체크처리후 등록처리 --%>

    <%-- function doUpdate() {
            // 기본입력체크
            $("#myForm").validationEngine('attach', { prettySelect: true, usePrefix: 's2id_',promptPosition: "bottomRight" ,scroll:false});
            var validateForm  = $(document.myForm).validationEngine('validate');
            if(validateForm){
                goPageTwo(myForm,    "ACTION_UPDATE_OK");
            }
        } --%>

    <%-- 글목록으로 이동 --%>

    <%-- function moveList() {
            goPageTwo(myForm,    "ACTION_LIST");
        } --%>

</SCRIPT>

<!--내용시작 -->
<div class="modal-dialog">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_MGMT"/>
            </h4>
        </div>


        <div class="modal-body">
            <form id="menuModForm" id="menuModForm" class="smart-form" novalidate="novalidate">

                <fieldset>
                    <div class="row">
                        <!-- 상위메뉴아이디 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.LIST.P_MENU_ID"/>
                            </label>
                            <label class="input state-disabled">
                                <input name="modUpMenuId" id="modUpMenuId" type="text" value="${MENU_INFO_DATA.upMenuId}" disabled="disabled">
                            </label>
                        </section>
                        <!-- 메뉴타입 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.TYPE_TEXT"/>
                            </label>
                            <label class="select">
                                <aot:select name="modMenuType" id="modMenuType" group="W004" event="onChange=\"checkInputCondition();\"" selected="${MENU_INFO_DATA.menuType}"/>
                                <i></i>
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 메뉴아이디 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_ID_TEXT"/>
                            </label>
                            <label class="input state-disabled">
                                <input name="modMenuId" id="modMenuId" type="text" value="${MENU_INFO_DATA.menuId}" disabled="disabled">
                            </label>
                        </section>
                        <!-- 메뉴명칭 한국어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_KOR"/>
                            </label>
                            <label class="input">
                                <input name="modMenuNmKor" id="modMenuNmKor" type="text" style="ime-mode: active;" value="${MENU_INFO_DATA.menuNmKor}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 메뉴명칭 영어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_ENG"/>
                            </label>
                            <label class="input">
                                <input name="modMenuNmEng" id="modMenuNmEng" type="text" value="${MENU_INFO_DATA.menuNmEng}">
                            </label>
                        </section>
                        <!-- 메뉴명칭 일어 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_JPN"/>
                            </label>
                            <label class="input">
                                <input name="modMenuNmJpn" id="modMenuNmJpn" type="text" value="${MENU_INFO_DATA.menuNmJpn}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 순번 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.SIBLING_NO_TEXT"/>
                            </label>
                            <label class="input">
                                <input name="modSeq" id="modSeq" type="text" value="${MENU_INFO_DATA.seq}">
                            </label>
                        </section>
                        <!-- 화면링크 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.URL_TEXT"/>
                            </label>
                            <label class="input">
                                <input name="modUrl" id="modUrl" type="text" value="${MENU_INFO_DATA.url}">
                            </label>
                        </section>
                    </div>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <!-- 아이콘 -->
                        <section class="col col-5">
                            <label class="label">
                                <spring:message code="TEXT.MENU.MGMT.TITLE.MENU_ICON"/>
                            </label>
                            <label class="input">
                                <input name="modMenuIcon" id="modMenuIcon" type="text" value="${MENU_INFO_DATA.menu_icon}">
                            </label>
                        </section>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <spring:message code="TEXT.CANCEL"/>
            </button>
            <button type="button" class="btn btn-primary" id="menuMod">
                <spring:message code="TEXT.COMM.BTN.SAVE"/>
            </button>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        var errorClass = 'invalid';
        var errorElement = 'em';
        $("#menuModForm").validate({
            errorClass: errorClass,
            errorElement: errorElement,
            highlight: function (element) {
                $(element).parent().removeClass('state-success').addClass("state-error");
                $(element).removeClass('valid');
            },
            unhighlight: function (element) {
                $(element).parent().removeClass("state-error").addClass('state-success');
                $(element).addClass('valid');
            },
            rules: {
                modMenuId: {
                    required: true
                },
                modMenuNmKor: {
                    required: true
                },
                modMenuNmEng: {
                    required: true
                },
            },
            messages: {
                modMenuId: {
                    required: '<spring:message code='MSG.MENU.ALERT.REQUIRE_ID'/>'
                },
                modMenuNmKor: {
                    required: '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_KR'/>'
                },
                modMenuNmEng: {
                    required: '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_ENG'/>'
                }
            },
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                AotAjax.excute("${CONTEXT_PATH}/admin/menu/menuModPopup.do", {
                    actionID: "ACTION_UPDATE_OK",
                    menuId: $("#modMenuId").val(),
                    menuNmKor: $("#modMenuNmKor").val(),
                    menuNmEng: $("#modMenuNmEng").val(),
                    menuNmJpn: $("#modMenuNmJpn").val(),
                    upMenuId: $("#modUpMenuId").val(),
                    menuType: $("#modMenuType").val(),
                    seq: $("#modSeq").val(),
                    url: $("#modUrl").val(),
                    menu_icon: $("#modMenuIcon").val()
                }, {
                    autoResultFunctionTF: false
                }).done(function (response) {
                    var str = response.returnMsg.split(":");
                    if (str[0] === "success") {
                        AotSmartAdmin.smallBoxsuccess(str[1]);
                        $('#menuModal').modal('hide');
                        window.location.reload();
                    } else {
                        AotSmartAdmin.smallBoxWarning(str[1]);
                    }
                });
            }
        });

        bindEvent();
    });

    //각 클릭 이벤트
    function bindEvent() {
        //저장
        $("#menuMod").on('click', function () {
            $("#menuModForm").submit();
        });
    }

    /* 선택된 메뉴 타입에 따라 화면링크를 쓰거나 쓸수 없게 만든다 */
    function checkInputCondition() {
        if ($("#modMenuType").val() == "G") {
            $("#modUrl").val("");
            $("#modUrl").prop("readonly", true);
        } else {
            $("#modUrl").prop("readonly", false);
        }
    }
</script>