<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/top.jsp
 - 설    명	:	탑메뉴화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2015.12.09    1.0       KYM      신규작성
 ----------------------------------------------------------
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<form name="topForm" id="topForm" method="POST">
    <input type="hidden" name="actionID" value="">
    <input type="hidden" name="root" value="">
    <input type="hidden" name="menuUrl">
    <input type="hidden" name="user_id" id="user_id" value="${loginedUserVO.user_id}">


</form>

<style>
    div.btn-header button.dropdown-toggle {
        background-color: #f8f8f8;
        background-image: -webkit-linear-gradient(top, #f8f8f8, #f1f1f1);
        margin: 10px 0 0;
        color: #6D6A69;
        border: 1px solid #bfbfbf;
        padding: 2px;
        font-weight: 700;
        height: 30px;
        text-align: center;
        font-size: 17px;
    }
</style>

<script>
    $(document).ready(function () {
        if ('${loginedUserVO.groupLevelCod}' !== '') {
            if (+'${loginedUserVO.groupLevelCod}' <= 100) {
                $('#link-to-cmp-button').removeClass('hide');
            }
        }
    });
</script>

<header id="header">
    <div id="logo-group" style="width: 400px;">
        <!-- PLACE YOUR LOGO HERE -->
        <span id="logo" style="width: 100%;">
			<a href="${CONTEXT_PATH }${HOME_URL}">
				<p class="txt-color-blueDark" style="font-size: 24px;">${PROJECT_TITLE }</p>
			</a>
		</span>
        <!-- END LOGO PLACEHOLDER -->
    </div>
    <div class="pull-right">

        <!-- collapse menu button -->
        <div id="hide-menu" class="btn-header pull-right">
			<span>
				<a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu">
					<i class="fa fa-reorder"></i>
				</a>
			</span>
        </div>
        <!-- end collapse menu -->

        <!-- logout button -->
        <!-- app.min.js userLogout -->
        <div id="logout" class="btn-header transparent pull-right">
			<span>
				<a href="#" title="Sign Out" data-action="userLogout" data-logout-msg="You can improve your security further after logging out by closing this opened browser">
					<i class="fa fa-sign-out"></i>
				</a>
			</span>
        </div>
        <!-- end logout button -->

        <!-- fullscreen button -->
        <div id="fullscreen" class="btn-header transparent pull-right">
			<span>
				<a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen">
					<i class="fa fa-arrows-alt"></i>
				</a>
			</span>
        </div>
        <!-- end fullscreen button -->

        <!-- Link to CMP button -->
        <div id="link-to-cmp-button" class="btn-header pull-right hide">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-external-link"></i>
                    CMP
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="javascript:void(0);" onclick="AotCommon.goTab('${CMP_A_URL}');" title="Link to CMP(A)">(A)</a>
                    </li>
                    <li role="separator" class="divider"></li>
                    <li>
                        <a href="javascript:void(0);" onclick="AotCommon.goTab('${CMP_B_URL}');" title="Link to CMP(B)">(B)</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- end CMP button -->

    </div>

</header>