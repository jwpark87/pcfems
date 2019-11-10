package com.aot.standard.common.interceptor;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Interceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);
    private static final String REQUEST_COMPLETE_EXECUTE_TIME_INCLUDE_JSP = "{} ....... Request Complete Execute Time(include JSP) viewName : {} ....... : {}";
    private static final String STR_STOP_WATCH = "mi.stopWatch";
    private static final String VIEW_NAME = "viewName";

    @Override
    // preHandle : controller 이벤트 호출전
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws CommonException, UserSysException {
        try {
            if (!AotSessionUtils.isLogined(request.getSession())) {
                if (!StringUtils.contains(request.getHeader("accept"), "html") && (StringUtils.startsWith(request.getContentType(), MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        || StringUtils.startsWith(request.getContentType(), MediaType.APPLICATION_JSON_VALUE))) {
                    LOGGER.warn(CommonException.EXCEPTION_ERROR_TRY_LOGIN_FIRST.getJsonObject().toString());
                    throw CommonException.EXCEPTION_ERROR_TRY_LOGIN_FIRST;
                }
                request.getSession().invalidate();
                String pathWithinApplication = new UrlPathHelper().getPathWithinApplication(request);
                final String originatingQueryString = new UrlPathHelper().getOriginatingQueryString(request);
                if (StringUtils.isNotEmpty(originatingQueryString)) {
                    pathWithinApplication = pathWithinApplication.concat("?").concat(originatingQueryString);
                }
                request.getSession().setAttribute("returnUrl", pathWithinApplication);
                response.sendRedirect(request.getContextPath() + "/login.go");
                return false;
            }
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        if (StringUtils.contains(request.getHeader("accept"), "html")) {
            request.setAttribute("ROOT_MENU", request.getParameter("root"));
            // CDR 조회 통합은 뒤에 파라미터가 붙어서 오므로 같이 확인해야 해서 단순히 request.getServletPath() 하면 안된다. 다시한번 확인
            final String menu_url = (String) request.getAttribute("MENU_URL");
            String menuUrl = request.getParameter("menuUrl");
            // 새로고침시 menuUrl 잃어 버림.
            if (StringUtils.isEmpty(menuUrl) && StringUtils.isEmpty(menu_url)) {
                menuUrl = request.getServletPath();
            } else if (StringUtils.isEmpty(menuUrl) && StringUtils.isNotEmpty(menu_url)) {
                menuUrl = menu_url;
            }
            request.setAttribute("MENU_URL", menuUrl);
            final String currMenuId = MenuMng.getMenuIdByUrl(menu_url);
            request.setAttribute("currMenuId", currMenuId);
            request.setAttribute("curr_menu_name", StringUtils.defaultString(MenuMng.getMenuName(currMenuId, AuthorityUtil.getUserInfo(request))));
        }

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(STR_STOP_WATCH, stopWatch);
        return true;
    }

    @Override
    // postHandle : controller 호출 후 view 페이지 출력전
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) {
        if (StringUtils.contains(request.getHeader("accept"), "html") && modelAndView != null) {
            request.setAttribute(VIEW_NAME, modelAndView.getViewName());
        }
    }

    @Override
    // afterCompletion : controller + view 페이지 모두 출력 후
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
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
}
