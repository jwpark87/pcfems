/*****************************************************************************
 * 프로그램명  : ForwardMultiController.java
 * 설     명  : 로그인화면으로 포워드 처리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2009.01.22  JSM    1.0     초기작성
 ******************************************************************************/
package com.aot.pcfems.business.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 포워드 처리 컨트롤러
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class ForwardController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/forward.go")
    public String doProcess(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return "/login/Forward";
    }

}
