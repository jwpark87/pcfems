<%----------------------------------------------------------------------------------------
 - 파일이름	: auth.jsp
 - 설      명	: 권한 레벨 화면
 - 작 성 자		: KYM
 - 추가내용 	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 - Date        Version   Auther   Description
 ----------------------------------------------------------
 - 2016.03.10  1.0        KYM      신규작성
------------------------------------------------------------------------------------------%>

<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/views/common/error/catchException.jsp" %>

<%@include file="/WEB-INF/views/common/include.jsp" %>

<script src="${PATH_PUBLISH}/js/dialogScript.js"></script>
<script src="${PATH_PUBLISH}/js/autosize.js"></script>

<input type="hidden" id="login_partner" name="login_partner" value="${loginedUserVO.partner}"/>
<input type="hidden" id="login_partner_nm" name="login_partner_nm" value="${loginedUserVO.partner_name}"/>
<input type="hidden" id="login_user_level" name="login_user_level" value="${loginedUserVO.user_level}"/>
<input type="hidden" id="auth_code" name="auth_code"/>

<c:set var="SEARCH">
    <spring:message code="TEXT.SEARCH"/>
</c:set>
<c:set var="UPDATE">
    <spring:message code="TEXT.COMM.BTN.UPDATE"/>
</c:set>
<c:set var="REGIST">
    <spring:message code="TEXT.COMM.BTN.REG"/>
</c:set>

<!-- 전체 외곽박스 START-->
<div class="basicBox" style="height: 90vh">
    <!-- 네비게이션 START -->

    <!-- 네비게이션 END-->

    <input type="hidden" name="menuUrl" value="${MENU_URL}"/>

    <!-- 검색박스  START-->
    <div class="searchBox">
        <div class="SearchBtBox">
            <ul>
                <li>
                    <!-- 조회 -->
                </li>
            </ul>
        </div>
    </div>
    <!--그리드  -->
    <div class="gbox" id="gbox">
        <table id="list"></table>
        <div id="pager"></div>
    </div>

    <div class="btBox">
        <ul>
            <li>
                <!-- 등록 -->
            </li>
            <li>
                <!-- 수정-->
            </li>
        </ul>
    </div>
</div>
<!-- 전체 외곽 박스 END -->
<script type="text/javascript">
    jQuery(document).ready(function () {

    });

    $(function () {
        //grid1
        var grid1_options = {};
        grid1_options.type = "Normal"; //그리드 유형
        grid1_options.pager = "#pager"; //페이저 아이디
        grid1_options.rownumbers = false;
        grid1_options.rowNum = 20;
        grid1_options.height = 518;
        grid1_options.rowList = [10, 20, 50, 100];
        grid1_options.sortname = "auth_code";
        grid1_options.sortorder = "asc";

        grid1_options.names = [
            /* 권한레벨 코드 */
            '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_code'/>'
            /* 권한명칭 -한글 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_nm_kor'/>'
            /* 권한명칭 - 영어 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_nm_eng'/>'
            /* 권한명칭 - 일어 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_nm_jpn'/>'
            /* 사용여부 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_use_yn'/>'
            /* 작업일시 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_upd_dt'/>'
            /* 작업자 */
            , '<spring:message code ='text.Auth.Mgmt.Grid.Title.Auth_upd_id'/>'];
        grid1_options.colModels = [{
            name: 'auth_code',
            index: 'auth_code',
            align: 'center',
            width: '80',
            key: true
        }, {
            name: 'auth_nm_kor',
            index: 'auth_nm_kor',
            align: 'left',
            width: '220'
        }, {
            name: 'auth_nm_eng',
            index: 'auth_nm_eng',
            align: 'left',
            width: '220'
        }, {
            name: 'auth_nm_jpn',
            index: 'auth_nm_jpn',
            align: 'left',
            width: '220'
        }, {
            name: 'use_yn',
            index: 'use_yn',
            align: 'center',
            width: '80',
            stype: "select",
            searchoptions: {
                value: AotJqGrid.getSelectTagData($("#use_yn > option"))
            }
        }, {
            name: 'upd_dt',
            index: 'upd_dt',
            align: 'center',
            width: '140',
            search: false
        }, {
            name: 'upd_id',
            index: 'upd_id',
            align: 'center',
            width: '80',
            search: false
        }];

        // json 데이터를 읽는 방법 지정
        grid1_options.jsonReaders = {
            resultSuccess: function (obj) {
                if ("FAIL" == obj)
                    AotSmartAdmin.smallBoxWarning("Data Load Fail");
            },
            root: "root", // ListData Root
            page: "page", // Current Page
            total: "total", // Total Pages
            records: "records", // Total Records
            repeatitems: false

        };
        grid1_options.navGridButton = {
            add: false,
            edit: false,
            del: false,
            search: false,
            refresh: false
        };

        grid1_options.onSelectRow = function (id) {
            $("#auth_code").val(id);
        };

        //jqGrid 생성
        $("#list").skyGrid(grid1_options);

        $("#list").jqGrid('filterToolbar', {
            searchOnEnter: false,
            ignoreCase: true,
            searchOperators: true,
            defaultSearch: 'cn'
        });

        $(window).on('resize', function () {
            $('#list').setGridWidth($('.basicBox').width(), false);
        }).trigger('resize');

        fixSearchOperators1();

        $("#list").jqGrid("navButtonAdd", "#pager", {
            caption: "",
            title: "",
            buttonicon: "ui-icon-arrowreturn-1-w",
            id: "clearFilter",
            onClickButton: function () {
                var grid = $("#list");
                //common.js 그리드 검색값 초기화 & 리로드
                initGridSearchFilter(grid);
            }
        });
        $("#list").jqGrid("navButtonAdd", "#pager", {
            caption: "",
            title: "",
            buttonicon: "ui-icon-arrow-4-diag",
            id: "autoFixHeight",
            onClickButton: function () {
                var gridHeight = $("#list").closest(".ui-jqgrid-bdiv").height();
                if ((gridHeight) == window.innerHeight - 300) {
                    fixHeight = 518; //원래 height를 입력
                } else {
                    fixHeight = window.innerHeight - 300;
                }
                jQuery("#list").setGridHeight(fixHeight);
            }
        });
        AotJqGrid.setGridStyle();

    });

    //조회
    function doSearch() {
        var param = {
            actionID: "ACTION_AUTH_LIST"
        };

        $("#list").jqGrid('setGridParam', {
            url: "${CONTEXT_PATH}/admin/auth/auth.do",
            datatype: "json",
            search: true,
            async: false,
            postData: param,
            mtype: "POST",
            gridComplete: function () {
                var ids = $('#list').jqGrid("getDataIDs");
                if (ids.length > 0) {
                    $('#list').jqGrid("setSelection", ids[0]);
                }
            }
        }).trigger("reloadGrid");
    }

    function fixSearchOperators1() {
        var $grid = $("#list"), columns = $grid.jqGrid('getGridParam', 'colModel'), filterToolbar = $($grid[0].grid.hDiv).find("tr.ui-search-toolbar");

        filterToolbar.find("th").each(function (index) {
            var $searchOper = $(this).find(".ui-search-oper");
            if (!(columns[index].searchoptions && columns[index].searchoptions.searchOperators)) {
                $searchOper.hide();
            }
        });
    }

    //등록
    function doReg() {
        var param = {
            actionID: "ACTION_AUTH_INSERT"
        };
        var options = {
            id: "authAddPopup",
            title: '<spring:message code="text.Auth.Mgmt.Pop.Title.Main_top"/>',
            width: "400px",
            url: "${CONTEXT_PATH}/admin/auth/authPopup.do",
            param: param
        };
        $.common.modal(options);
    }

    //수정
    function doUpdate() {
        var selrow = $("#list").jqGrid('getGridParam', 'selrow');
        if (selrow == null) {
            AotSmartAdmin.smallBoxWarning('<spring:message code="MSG.NONE.SELECT"/>');
        } else {

            var param = {
                actionID: "ACTION_AUTH_UPDATE",
                auth_code: $("#auth_code").val()
            };
            var options = {
                id: "authModPopup",
                title: '<spring:message code="text.Auth.Mgmt.Pop.Title.Main_top"/>',
                width: "400px",
                url: "${CONTEXT_PATH}/admin/auth/authPopup.do",
                param: param
            };
            $.common.modal(options);

        }
    }
</script>

