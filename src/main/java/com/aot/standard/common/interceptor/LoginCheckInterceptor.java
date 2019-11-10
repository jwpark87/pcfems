package com.aot.standard.common.interceptor;

import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckInterceptor.class);
    private static final String REQUEST_COMPLETE_EXECUTE_TIME_INCLUDE_JSP = "{} ....... Request Complete Execute Time(include JSP) viewName : {} ....... : {}";
    private static final String STR_STOP_WATCH = "mi.stopWatch";
    private static final String VIEW_NAME = "viewName";
    /**
     * 로그출력객체
     */
    protected final Log logger = LogFactory.getLog(this.getClass());
    /**
     * 처리성공시 이동 페이지 URL
     */
    protected String successCallPage = null;
    /**
     * 에러발생시 이동할 페이지 URL
     */
    protected String errCallBackPage = "/common/error/systemException";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        String pathWithinApplication = new UrlPathHelper().getPathWithinApplication(request);
        final String originatingQueryString = new UrlPathHelper().getOriginatingQueryString(request);
        if (StringUtils.isNotEmpty(originatingQueryString)) {
            pathWithinApplication = pathWithinApplication.concat("?").concat(originatingQueryString);
        }
        // url.append(param);
        this.logger.info(pathWithinApplication);

        final HttpSession session = request.getSession(true);
        final UserVO user = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

        /*****/
        // session.setMaxInactiveInterval(10);
        /*****/

        final StringBuffer sbUrlBuffer = new StringBuffer();

        // 로그인 해야만 접근가능한 곳에의 요청인 경우
        if (!this.checkNeedLoginFromUrl(request)) {
            // 로그인 하지 않은경우는 로그인 화면으로 보낸다.
            if (!AuthorityUtil.checkLogin(user)) {
                // 유저정보 삭제
                // session.removeAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);
                // 세션무효화
                session.invalidate();
                // sbUrlBuffer.append(ConstUrl.LOGIN_URL);
                sbUrlBuffer.append("/forward.go");
                sbUrlBuffer.append("?");
                sbUrlBuffer.append("LOGIN_RESULT");
                sbUrlBuffer.append("=");
                sbUrlBuffer.append("LOGIN_NEED");
                response.sendRedirect(sbUrlBuffer.toString());
                return false;
            } else {
                // 어드민만 접근가능한 URL일경우
                if (this.checkNeedAdminLoginFromUrl(request)) {
                    // 어드민 권한이 아닌 경우 로그인 화면으로 이동
                    if (!AuthorityUtil.checkAdmin(user)) {
                        // 유저정보 삭제
                        // session.removeAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);
                        // 세션무효화
                        session.invalidate();
                        // sbUrlBuffer.append(ConstUrl.LOGIN_URL);
                        sbUrlBuffer.append("/forward.go");
                        sbUrlBuffer.append("?");
                        sbUrlBuffer.append("LOGIN_RESULT");
                        sbUrlBuffer.append("=");
                        sbUrlBuffer.append("LOGIN_ADMIN_NEED");
                        response.sendRedirect(sbUrlBuffer.toString());
                        return false;
                    }
                }
                // if (this.checkPermissionMenu(request, user)) {
                // sbUrlBuffer.append("/main.do");
                // sbUrlBuffer.append("?");
                // sbUrlBuffer.append("LOGIN_RESULT");
                // sbUrlBuffer.append("=");
                // sbUrlBuffer.append("LOGIN_ROLE");
                // response.sendRedirect(sbUrlBuffer.toString());
                // return false;
                // }
            }
        }

        if (StringUtils.contains(request.getHeader("accept"), "html")) {
            // String menu_url = (String) request.getAttribute("MENU_URL");
            // String menuUrl = StringUtils.defaultString(request.getParameter("menuUrl"));
            // // 새로고침시 menuUrl 잃어 버림.
            // if (StringUtils.isEmpty(menuUrl) && StringUtils.isEmpty(menu_url)) {
            // menuUrl = request.getServletPath();
            // } else if (StringUtils.isEmpty(menuUrl) && StringUtils.isNotEmpty(menu_url)) {
            // menuUrl = menu_url;
            // }
            // String indx_type = StringUtils.defaultString(request.getParameter("index_type"));
            // if (StringUtils.isNotEmpty(indx_type)) {
            // menuUrl = menuUrl + "?index_type=" + indx_type;
            // }
            // String record_status_yn = StringUtils.defaultString(request.getParameter("record_status_yn"));
            // if (StringUtils.isNotEmpty(record_status_yn)) {
            // menuUrl = menuUrl + "&record_status_yn=" + record_status_yn;
            // }
            // if (StringUtils.isEmpty(menuUrl)) {
            // menuUrl = pathWithinApplication;
            // }
            // request.setAttribute("MENU_URL", menuUrl);
            // if (request.getAttribute("MENU_URL") == null && StringUtils.isNotEmpty((String) request.getAttribute("MENU_URL"))) {
            session.setAttribute("ROOT_MENU", request.getParameter("root"));
            session.setAttribute("MENU_URL", pathWithinApplication);
            request.setAttribute("ROOT_MENU", request.getParameter("root"));
            request.setAttribute("MENU_URL", pathWithinApplication);
            // }
            LOGGER.debug("MENU_URL : {}", request.getAttribute("MENU_URL"));
            session.setAttribute("curr_menu_name", StringUtils.defaultString(MenuMng.getMenuName(MenuMng.getMenuIdByUrl(pathWithinApplication), AuthorityUtil.getUserInfo(request))));
        }

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(STR_STOP_WATCH, stopWatch);
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        // super.postHandle(request, response, handler, modelAndView);
        if (StringUtils.contains(request.getHeader("accept"), "html") && modelAndView != null) {
            request.setAttribute(VIEW_NAME, modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        final StopWatch stopWatch = (StopWatch) request.getAttribute(STR_STOP_WATCH);
        stopWatch.stop();
        LOGGER.info(REQUEST_COMPLETE_EXECUTE_TIME_INCLUDE_JSP, new UrlPathHelper().getPathWithinApplication(request), request.getAttribute(VIEW_NAME), stopWatch.toString());
        request.removeAttribute(STR_STOP_WATCH);
    }

    @Override
    // afterConcurrentHandlingStarted: 뭐 동시에 핸들링 해주는 메서드인대 정확히는 모르겠습니다
    public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        //
    }

    /**
     * 로그인을 반드시 해야만 접근할수 있는 기능인지 확인
     *
     * @param request
     * @return boolean (treu:로그인해야사용하는 화면 , false:일반화면)
     */
    private boolean checkNeedLoginFromUrl(final HttpServletRequest request) {
        boolean bCheck = false;
        final String url = StringUtils.defaultString(request.getRequestURL().toString());

        for (int i = 0; i < CommonCode.LOGIN_URL_LIST.size(); i++) {
            final String temp = CommonCode.LOGIN_URL_LIST.get(i);
            // 회원정보수정일경우
            if (url.indexOf(temp) != -1) {
                bCheck = true;
                break;
            }
        }

        return bCheck;
    }

    // private boolean checkPermissionMenu(final HttpServletRequest request, final UserVO userInfo) throws UserSysException {
    // boolean bCheck = false;
    // String callUrl = StringUtils.defaultString(request.getRequestURL().toString());
    // // 메인페이지가 아니면
    // // 메인페이지면 무조건 true 로 권한 따지지 않음
    // if (callUrl.indexOf("/main.do") == -1) {
    // // 그룹별 허용 메뉴목록
    // List<MenuInfo> arList = MenuMng.getMenuListByGroupLevelCod(request.getSession(), userInfo.getGroupLevelCod());
    // /* 전체 메뉴들 */
    // Hashtable<String, MenuInfo> tmpallList = MenuMng.getMenuMap();
    // /* 리스트로 벼경 */
    // List<MenuInfo> arrNonList = new ArrayList<>(tmpallList.values());
    // List<MenuInfo> tmpNonList = new ArrayList<>(tmpallList.values());
    // // 전체메뉴에서 그룹에 허용된 메뉴 제거
    // for (int n = 0; n < arrNonList.size(); n++) {
    // MenuInfo menuInfo1 = arrNonList.get(n);
    // for (int i = 0; i < arList.size(); i++) {
    // MenuInfo menuInfo2 = arList.get(i);
    // if (menuInfo2.getMenuId().equals(menuInfo1.getMenuId())) {
    // tmpNonList.remove(menuInfo1);
    // break;
    // }
    // }
    // }
    // // 허용안된 메뉴에 접근시 메인화면으로 보내기.
    // MenuInfo menuInfo = new MenuInfo();
    // for (int i = 0; i < tmpNonList.size(); i++) {
    // menuInfo = tmpNonList.get(i);
    // String temp = StringUtils.defaultString(menuInfo.getUrl());
    // if (!"".equals(temp) && callUrl.indexOf(temp) != -1) {
    // bCheck = true;
    // break;
    // }
    // }
    // } else {
    // // bCheck = true;
    // }
    //
    // return bCheck;
    // }

    /**
     * 어드민 권한일 경우 만 접근할수 있는 기능인지 확인
     *
     * @param request
     * @return boolean (treu:어드민만 사용하는 화면 , false:일반화면)
     */
    private boolean checkNeedAdminLoginFromUrl(final HttpServletRequest request) {
        boolean bCheck = false;
        final String url = StringUtils.defaultString(request.getRequestURL().toString());

        for (int i = 0; i < CommonCode.LOGIN_ADMIN_URL_LIST.size(); i++) {
            final String temp = CommonCode.LOGIN_ADMIN_URL_LIST.get(i);
            // 회원정보수정일경우
            if (url.indexOf(temp) != -1) {
                bCheck = true;
                break;
            }
        }

        return bCheck;
    }

    /**
     * <PRE>
     * Comment : 파라미터 KEY, VALUE를 정렬한다.
     * </PRE>
     *
     * @param map
     * @return
     */
    public static String getParammetersString(final Map<String, String[]> map) {
        final SortedMap<String, String[]> smap = Collections.synchronizedSortedMap(new TreeMap<>(map));

        final StringBuffer param = new StringBuffer();
        int count = 0;
        for (final Entry<String, String[]> entery : smap.entrySet()) {
            for (int i = 0; i < entery.getValue().length; i++) {
                if (count > 0) {
                    param.append("&");
                } else {
                    param.append("?");
                }

                param.append(entery.getKey() + "=" + entery.getValue()[i]);
                count++;
            }
        }
        return param.toString();
    }
}
