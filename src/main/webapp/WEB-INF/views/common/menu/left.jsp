<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/leftmenu.jsp
 - 설    명	:	왼쪽메뉴화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2015.12.09    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>


<style>
    /* 로그인 사용자 이름 부분 마우스 오버 무효화 */
    .login-info a:hover {
        color: #c0bbb7
    }
</style>
<form name="leftForm" method="POST">
    <input type="hidden" name="actionID" value="">
</form>
<aside id="left-panel">

    <!-- UserVO info -->
    <div class="login-info">
		<span>
			<!-- User image size is adjusted inside CSS, it should stay as it -->
			<a href="javascript:void(0);" id="show-shortcut" style="cursor: default;">
				<img src="${CONTEXT_PATH}/resources/plugin/smartAdmin/img/avatars/male.png" alt="me" class="online"/>
				<span> ${loginedUserVO.user_name} </span>
			</a>

		</span>
    </div>
    <!-- end user info -->

    <aot:menu root="${ROOT_MENU}" menu_url="${MENU_URL}"/>


    <span class="minifyme" data-action="minifyMenu">
		<i class="fa fa-arrow-circle-left hit"></i>
	</span>

</aside>

