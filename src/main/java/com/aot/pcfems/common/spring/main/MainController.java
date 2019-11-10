/*****************************************************************************
 * 프로그램명  : MainController.java
 * 설      명  : 메인 화면
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2013.06.24 SJH     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.main;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 메인 화면
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * 메인 화면
     *
     * @param request  요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체
     */
    @RequestMapping(value = "/main.do")
    public String goController(final Model model, final HttpServletRequest request) throws UserSysException {
        if (StringUtils.equals(StringUtils.defaultString(request.getParameter("LOGIN_RESULT")), "LOGIN_ROLE")) {
            model.addAttribute("LOGIN_RESULT", "LOGIN_ROLE");
        }
        return "/init/init";
    }
}
