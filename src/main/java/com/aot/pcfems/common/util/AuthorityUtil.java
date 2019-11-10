/*****************************************************************************
 * 프로그램명  : AuthorityUtil.java
 * 설     명  : 권한별 체크 설정.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.12  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.util;

import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 권한 체크 클래스
 *
 * @author eaction
 * @version 1.0
 */
public class AuthorityUtil {

    /**
     * 유저정보를 취득한다 (서블릿쪽에서 사용)
     *
     * @param request 요청객체
     * @return User 유저정보객체
     */
    public static UserVO getUserInfo(final HttpServletRequest request) {
        return getUserInfo(request.getSession());
    }

    public static UserVO getUserInfo(final HttpSession session) {
        return AotSessionUtils.getUserVO(session);
    }

    public static void setUserInfo(final HttpSession session, final UserVO userVO) {
        AotSessionUtils.setUserVO(session, userVO);
    }

    /**
     * 유저의 어드민 여부 설정
     *
     * @param userInfo 유저정보빈
     */
    public static void setAdmin(final UserVO userInfo) {
        final String groupLevel = CommonCode.ADMIN_GROUP_LEVEL;
        final String superGroupLevel = CommonCode.SUPERADMIN_GROUP_LEVEL;
        if (userInfo != null && !"".equals(userInfo.getUser_id())) {
            if (groupLevel.equals(userInfo.getGroupLevelCod())) {
                userInfo.setAdmin(CommonCode.ADMIN_KIND_ADMIN);
            } else if (superGroupLevel.equals(userInfo.getGroupLevelCod())) {
                userInfo.setAdmin(CommonCode.ADMIN_KIND_SUPERADMIN);
            } else {
                userInfo.setAdmin(CommonCode.ADMIN_KIND_USER);
            }
        }
    }

    /**
     * 로그인을 했는지 체크
     *
     * @param userInfo 유저정보빈
     * @return boolean (true:관리자나 본인 , false:그외)
     */
    public static boolean checkLogin(final UserVO userInfo) {
        return userInfo != null && !"".equals(userInfo.getUser_id());
    }

    /**
     * 시스템어드민 권한이 있는지 체크
     *
     * @param userInfo 유저정보빈
     * @return boolean 관리자여부(true:관리자 , false:관리자 이외)
     */
    public static boolean checkAdmin(final UserVO userInfo) {
        return checkLogin(userInfo) && (CommonCode.ADMIN_KIND_ADMIN.equals(userInfo.getAdmin()) || CommonCode.ADMIN_KIND_SUPERADMIN.equals(userInfo.getAdmin()));
    }

    /**
     * 글쓴 본인인경우 true, 그외는 false를 리턴한다
     *
     * @param userInfo
     *            유저정보빈
     * @param userId
     *            유저아이디(글의)
     * @return boolean (true:관리자나 본인 , false:그외)
     */
    // public static boolean checkSelf(final UserVO userInfo, final String userId) {
    // return checkLogin(userInfo) && userId.equals(userInfo.getUser_id());
    // }

    /**
     * 글쓴 본인인나 관리자 인지 체크
     *
     * @param userInfo
     *            유저정보빈
     * @param userId
     *            유저아이디(글의)
     * @return boolean (true:관리자나 본인 , false:그외)
     */
    // public static boolean checkAdminSlef(final UserVO userInfo, final String userId) {
    // boolean check = false;
    // // 로그인 한경우만 대상으로 한다
    // if (checkAdmin(userInfo)) {
    // check = true;
    // } else if (checkSlef(userInfo, userId)) {
    // check = true;
    // }
    // return check;
    // }

    /**
     * 댓글을 쓴 본인이나 관리자이거나 본문을 쓴 유저인지 체크
     *
     * @param userInfo
     *            유저정보빈
     * @param lineUserId
     *            댓글쓴 유저 아이디
     * @param mainUserId
     *            본문의 유저 아이디
     * @return boolean (true:관리자나 본인 , false:그외)
     */
    // public static boolean checkLineAdminSlef(final UserVO userInfo, final String lineUserId, final String mainUserId) {
    // // 관리자의 경우true
    // // 메인 글을 쓴 유저의 경우 true
    // // 댓글을 쓴 유저의 경우 true
    // // 시스템 어드민일 경우 true
    // return checkAdmin(userInfo) || checkSelf(userInfo, mainUserId) || checkSelf(userInfo, lineUserId);
    // }

    /**
     * 답변형게시판일 경우 답변 버튼을 보여줘서 답변등록이 가능하도록 한다
     *
     * @param answerYn
     *            답변형유무(0:답변형, 1:일반형)
     * @return boolean (true:답변형 , false:일반형)
     */
    // public static boolean checkAnswerBoard(final String answerYn) {
    // boolean check = false;
    // // 관리자의 경우true
    // if (CommonCode.YES.equals(answerYn)) {
    // check = true;
    // }
    //
    // return check;
    // }

    /**
     * 관리자 모드일 경우에 실행하는 URL앞에 /admin/경로를 붙여준다.
     *
     * @param request
     *            Request객체
     * @return String URL문자열(/admin/)
     */
    // public static String addAdminUrl(final HttpServletRequest request) {
    // String url = "";
    //
    // if (checkAdminUrl(request)) {
    // url = "/admin";
    // }
    // return url;
    // }

    /**
     * 관리자 모드인 경우를 체크한다 .
     *
     * @param admin
     *            어드민모드여부(1:어드민, 이외:일반)
     * @return String URL문자열(/admin/)
     */
    // public static boolean checkAdminMode(final String admin) {
    // boolean bCheck = false;
    // if (ADMIN_KIND_ADMIN.equals(admin) || ADMIN_KIND_SUPERADMIN.equals(admin)) {
    // bCheck = true;
    // }
    // return bCheck;
    // }

    // /**
    // * 관리자 모드인 경우를 체크한다 .
    // *
    // * @param request
    // * Request객체
    // * @return String URL문자열(/admin/)
    // */
    // public static boolean checkAdminUrl(final HttpServletRequest request) {
    // boolean bCheck = false;
    //
    // // String temp = StringUtils.defaultString(request.getRequestURL().toString());
    // // String temp = StringUtils.defaultString(request.getAttribute("javax.servlet.forward.request_uri").toString());
    // String temp = StringUtils.defaultString((String) request.getAttribute("javax.servlet.forward.request_uri")).toString();
    //
    // if (temp.equals("")) {
    // temp = request.getRequestURL().toString();
    //
    // }
    // // logger.debug("addAdminUrl(javax.servlet.forward.request_uri)="+temp);
    // // logger.debug("addAdminUrl(getAttribute)="+temp);
    // // logger.debug("addAdminUrl(getRequestURI)="+request.getRequestURI());
    // // logger.debug("addAdminUrl(getServletPath)="+request.getServletPath());
    //
    // if (temp.indexOf("ADMIN_URL") != -1) {
    // bCheck = true;
    // // logger.debug("url(in)="+url);
    // }
    // // logger.debug("url(out)="+url);
    // return bCheck;
    // }

    /**
     * 슈퍼관리자 모드인 경우를 체크한다 .
     *
     * @param admin
     *            어드민모드여부(2:슈퍼관리자, 1:관리자, 이외:일반)
     * @return boolean (true:슈퍼관리자,false:관리자)
     */
    // public static boolean checkSuperAdmin(final String admin) {
    // boolean bCheck = false;
    // if (ADMIN_KIND_SUPERADMIN.equals(admin)) {
    // bCheck = true;
    // }
    // return bCheck;
    // }

    /**
     * 그룹레벨 체크박스를전부 체크한 상태로 초기화 설정한다
     *
     * @param userInfo
     *            유저정보
     * @return groupLevel 비교기본리스트정보(그룹레벨리스트)
     */
    // public static String checkGroupLevelInitStatus(final List<CodeInfo> groupLevel) {
    // StringBuffer sbLevel = new StringBuffer();
    // for (CodeInfo codeInfo : groupLevel) {
    // sbLevel.append(codeInfo.getCode());
    // // 구분자 수정 ":"-->"," by LYJ 2011.10.31
    // sbLevel.append(",");
    // }
    // return sbLevel.toString();
    // }

    /**
     * 그룹레벨 체크박스를전부 체크한 상태로 초기화 설정한다
     *
     * @param userInfo
     *            유저정보
     * @return groupLevel 비교기본리스트정보(그룹레벨리스트)
     */
    // public static String checkGroupLevelStatus(final List groupLevel) {
    // StringBuffer sbLevel = new StringBuffer();
    // for (int i = 0; i < groupLevel.size(); i++) {
    // /*
    // * BoardLevelInfo boardLevelInfo = (BoardLevelInfo)groupLevel.get(i); sbLevel.append(boardLevelInfo.getGroupLevel());
    // */
    // // 구분자 수정 ":"-->"," by LYJ 2011.10.31
    // sbLevel.append(",");
    // }
    //
    // return sbLevel.toString();
    // }

    /**
     * 읽기 전용으로 만들 리스트들을 정의한다(본인의 레벨보다 높은경우는 읽기전용으로한다)
     *
     * @param userInfo
     *            유저정보
     * @return groupLevel 비교기본리스트정보(그룹레벨리스트)
     */
    // public static String readOnlyGroupLevelStatus(final UserVO userInfo, final List groupLevel) {
    // StringBuffer sbLevel = new StringBuffer("");
    // String ADMIN = ConfigMng.getValue(IPropertyKey.ADMIN_KIND_ADMIN);
    // int nUserLevel = Integer.parseInt(userInfo.getGroupLevelCod());
    //
    // if (!ADMIN.equals(userInfo.getAdmin())) {
    // for (int i = 0; i < groupLevel.size(); i++) {
    // CodeInfo codeInfo = (CodeInfo) groupLevel.get(i);
    // int nGroupLevel = Integer.parseInt(codeInfo.getCode());
    // if (nGroupLevel <= nUserLevel) {
    // sbLevel.append(codeInfo.getCode());
    // // 구분자 수정 ":"-->"," by LYJ 2011.10.31
    // sbLevel.append(",");
    // }
    // }
    // }
    //
    // return sbLevel.toString();
    // }
}
