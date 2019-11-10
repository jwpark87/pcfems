package com.aot.pcfems.business.login;
/*****************************************************************************
 * 프로그램명  : LoginController.java
 * 설     명  : 로그인 처리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.22  LYS    1.0     초기작성
 ******************************************************************************/

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.CookieMng;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotSessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 로그인 처리 컨트롤러
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;
    @Value("#{enviromentProperties['SET_CONNLOG']}")
    private String SET_CONNLOG;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;
    @Value("#{enviromentProperties['PROJECT_TITLE']}")
    private String PROJECT_TITLE;

    @RequestMapping(value = {"/", "/login.go"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView postPage(final Model model, final HttpServletRequest request, @ModelAttribute("user") final UserVO paramUserInfo) throws UserSysException {

        final StringBuffer sbUrlBuffer = new StringBuffer();
        String goController = "";
        String init_page = "";
        final HttpSession session = request.getSession(true);
        this.logger.debug("request.getSession()   :   " + request.getSession());
        this.logger.debug("session.getUser_id()   :   " + session.getId());

        final String actionID = StringUtils.defaultIfEmpty(request.getParameter("actionID"), "");
        this.logger.debug("액션아이디(ACTIONID) : " + actionID);

        // 2012.8.23 보안관련 이슈로 사용자 IP로그 확인 을 위한 코드 추가 ..Once
        String clientIP = request.getHeader("Proxy-Client-IP");

        final String result_login = StringUtils.defaultString(request.getParameter("LOGIN_RESULT"));
        // 로그인 매니저
        // LoginManager loginManager = LoginManager.getInstance();

        // 로그인화면 표시
        if ("".equals(actionID) || "LOGIN_INIT".equals(actionID)) {
            goController = this.goLogin(model, request);
            if (!"".equals(result_login) && result_login.equals("LOGIN_NEED")) {
                model.addAttribute("LOGIN_RESULT", "LOGIN_NEED");
                return new ModelAndView(goController);
            }

            // 로그인 처리를 요청
        } else if ("LOGIN_OK".equals(actionID)) {
            // 2012.8.23 보안관련 이슈로 사용자 IP로그 확인 을 위한 코드 추가 ..Once
            // client의 IP를 log에 남김...
            if (clientIP == null) {
                clientIP = request.getHeader("WL-Proxy-Client-IP");
                if (clientIP == null) {
                    clientIP = request.getHeader("X-Forwared-For");
                }
                if (clientIP == null) {
                    clientIP = request.getRemoteAddr();
                }
            }

            this.logger.debug("clientIP  = " + clientIP);

            // 유저정보빈을 취득
            UserVO user = null;
            try {
                user = this.loginService.getUserInfo(paramUserInfo);
            } catch (final UserSysException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                request.setAttribute("ERROR", "error : " + e);
            } catch (final BizException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                request.setAttribute("ERROR", "error : " + e);
            }
            final CookieMng cookieMng = new CookieMng();

            // 로그인에 성공한 경우
            if (AuthorityUtil.checkLogin(user)) {
                // 유저의 어드민 여부를 체크해서 설정한다
                AuthorityUtil.setAdmin(user);
                // 로그인시 사운드 play 기본값
                user.setAlarmStatus("SOUND_PLAY");
                user.setAlarmStatusSetTime(AotDateUtils.getStringNow(AotDateUtils.YYYY_MM_DD_HH_MM_SS));
                user.setUser_lang(this.LANGUAGE);

                // 유저정보 세션에 설정
                session.setAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO, user);

                // 로그인시 로그등록
                final UserVO userInfo = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

                if ("YES".equals(this.SET_CONNLOG)) {
                    try {
                        this.loginService.setConnLog(userInfo, "LOGIN");
                    } catch (final UserSysException e) {
                        this.logger.warn(ExceptionUtils.getStackTrace(e));
                        request.setAttribute("ERROR", "error : " + e);
                    } catch (final BizException e) {
                        this.logger.warn(ExceptionUtils.getStackTrace(e));
                        request.setAttribute("ERROR", "error : " + e);
                    }
                }

                // 그룹별 초기 페이지 지정
                init_page = CodeTableMng.getCodeNameNotLang(user, "AUTHLEVEL_INDEX_URL", user.getGroupLevelCod());
                if ("".equals(init_page)) {
                    init_page = "/obstaclehist/alarmhistory.do";
                }
                sbUrlBuffer.append(init_page);
                if (StringUtils.contains(init_page, "?")) {
                    sbUrlBuffer.append("&login=OK");
                } else {
                    sbUrlBuffer.append("?login=OK");
                }
                // URL에 뒤에 파라미터 붙는것 방지위해 사용
                final RedirectView redirectView = new RedirectView();
                redirectView.setUrl(sbUrlBuffer.toString());
                redirectView.setContextRelative(true);

                redirectView.setExposeModelAttributes(false);

                final ModelAndView destScreen = new ModelAndView();
                // 리다이렉트 보낼때 파라미터 안보이게 FlashMap 에 담아 보내기 - intercepter에서 먼저 받음
                final FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
                fm.put("MENU_URL", init_page);
                fm.put("user", paramUserInfo);

                destScreen.setView(redirectView);
                return destScreen;

                // sbUrlBuffer.append("redirect:");
                // 첫페이지를 모니터링 페이지로 함
                /* sbUrlBuffer.append("/admin/emp/emp.do"); */
                // sbUrlBuffer.append("/obstaclemst/alarmlevel.do");
                // sbUrlBuffer.append("/obstaclehist/alarmhistory.do");

                // goController = sbUrlBuffer.toString();

                // 로그인 이 안된경우
            } else {
                // 로그인 화면으로 이동
                // goController = "/login/Login";
                goController = this.goLoginFail(model, request, cookieMng);
                model.addAttribute("LOGIN_RESULT", "LOGIN_FAIL");
                return new ModelAndView(goController);
            }

            // 로그아웃처리
        } else if ("LOGOUT_OK".equals(actionID)) {
            // 로그아웃시 로그등록
            final UserVO userInfo = (UserVO) session.getAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);

            if ("YES".equals(this.SET_CONNLOG)) {
                try {
                    this.loginService.setConnLog(userInfo, "LOGINOUT");
                } catch (final UserSysException e) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                    request.setAttribute("ERROR", "error : " + e);
                } catch (final BizException e) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                    request.setAttribute("ERROR", "error : " + e);
                }
            }
            // 유저정보 삭제
            session.removeAttribute(AotSessionUtils.SESSION_VALUE_OF_LOGIN_VO);
            // 세션무효화
            session.invalidate();

            // 로그인 화면으로 이동
            sbUrlBuffer.append("redirect:");
            sbUrlBuffer.append("/login.go");
            goController = sbUrlBuffer.toString();
            // goController = goLogin(request);

            // 타임아웃 로그아웃
        } else if ("TIMEOUT_LOGOUT_OK".equals(actionID)) {

            final String user_id = StringUtils.defaultString(request.getParameter("user_id"));
            final UserVO user = new UserVO();
            user.setUser_id(user_id);
            if ("YES".equals(this.SET_CONNLOG)) {
                try {
                    this.loginService.setConnLog(user, "TIME_OUT");
                } catch (final UserSysException e) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                    request.setAttribute("ERROR", "error : " + e);
                } catch (final BizException e) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                    request.setAttribute("ERROR", "error : " + e);
                }
            }
            // 세션무효화
            session.invalidate();
            // 로그인 화면으로 이동
            goController = this.goLogin(model, request);

        }
        /*
         * if(!"".equals(init_page)){ redirectAttributes.addFlashAttribute("menuUrl", init_page); }
         */
        model.addAttribute("user", paramUserInfo);
        model.addAttribute("PROJECT_TITLE", this.PROJECT_TITLE);
        return new ModelAndView(goController);
    }

    private String goLogin(final Model model, final HttpServletRequest request) {
        final CookieMng cookieMng = new CookieMng(request);
        final String cookieSave = StringUtils.defaultString(cookieMng.getValue("COOKIE_SAVE"));
        String cookieId = "";
        String cookiePasswd = "";
        if ("checked".equals(cookieSave)) {
            cookieId = StringUtils.defaultString(cookieMng.getValue("COOKIE_ID"));
            cookiePasswd = StringUtils.defaultString(cookieMng.getValue("COOKIE_PASSWD"));
        }

        request.setAttribute("COOKIE_SAVE", cookieSave);
        request.setAttribute("COOKIE_ID", cookieId);
        request.setAttribute("COOKIE_PASSWD", cookiePasswd);

        // 로그인 화면으로 이동
        model.addAttribute("PROJECT_TITLE", this.PROJECT_TITLE);
        return "/login/Login";
    }

    private String goLoginFail(final Model model, final HttpServletRequest request, final CookieMng cookieMng) {
        final String cookieSave = StringUtils.defaultString(cookieMng.getValue("COOKIE_SAVE"));
        String cookieId = "";
        String cookiePasswd = "";
        if ("checked".equals(cookieSave)) {
            cookieId = StringUtils.defaultString(cookieMng.getValue("COOKIE_ID"));
            cookiePasswd = StringUtils.defaultString(cookieMng.getValue("COOKIE_PASSWD"));
        }

        request.setAttribute("COOKIE_SAVE", cookieSave);
        request.setAttribute("COOKIE_ID", cookieId);
        request.setAttribute("COOKIE_PASSWD", cookiePasswd);

        // 로그인 화면으로 이동
        model.addAttribute("PROJECT_TITLE", this.PROJECT_TITLE);
        return "/login/Login";
    }
}
