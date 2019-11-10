/*****************************************************************************
 * 프로그램명  : TopMenuController.java
 * 설     명  : Top 메뉴 제어
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Top 메뉴 제어
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class TopMenuController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("#{enviromentProperties['PROJECT_TITLE']}")
    private String PROJECT_TITLE;
    @Value("#{enviromentProperties['HOME_URL']}")
    private String HOME_URL;
    @Value("#{enviromentProperties['CMP_A_URL']}")
    private String CMP_A_URL;
    @Value("#{enviromentProperties['CMP_B_URL']}")
    private String CMP_B_URL;

    /**
     * doProcess 메소드
     *
     * @param HttpServletRequest  요청
     * @param HttpServletResponse 응답
     * @return ModelAndView 뷰표시처리객체
     */
    @RequestMapping(value = "/top.do")
    public String getPage(final Model model) {
        model.addAttribute("PROJECT_TITLE", this.PROJECT_TITLE);
        model.addAttribute("HOME_URL", this.HOME_URL);
        model.addAttribute("CMP_A_URL", this.CMP_A_URL);
        model.addAttribute("CMP_B_URL", this.CMP_B_URL);
        return "/common/menu/top";
    }

    // @RequestMapping(value = "/top.do", params = "actionID=ACTION_RESET_SESSION")
    // @ResponseBody
    // public JsonObject setSession(final HttpServletRequest request) {
    // HttpSession session = request.getSession();
    // session.setMaxInactiveInterval(session.getMaxInactiveInterval());
    // this.logger.debug("Session 유효시간 재할당시간 : " + new DateTime(session.getLastAccessedTime()).toString(AotDateUtils.YYYY_MM_DD_HH_MM_SS));
    //
    // JsonObject jsonObject = new JsonObject();
    // jsonObject.addProperty("retCode", "00");
    // jsonObject.addProperty("retMsg", session.getMaxInactiveInterval());
    // return jsonObject;
    // }
}
