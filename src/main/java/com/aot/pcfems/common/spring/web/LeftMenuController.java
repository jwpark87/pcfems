/*****************************************************************************
 * 프로그램명  : LeftMenuController.java
 * 설     명  : 메뉴 제어
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 왼쪽 메뉴 제어
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class LeftMenuController {

    /**
     * doProcess 메소드
     *
     * @param HttpServletRequest  요청
     * @param HttpServletResponse 응답
     * @return ModelAndView 뷰표시처리객체
     */
    @RequestMapping(value = "/left.do")
    public String getPage(final HttpServletRequest request) {
        // ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("ROOT_MENU", root);
        // // CDR 조회 통합은 뒤에 파라미터가 붙어서 오므로 같이 확인해야 해서 단순히 request.getServletPath() 하면 안된다. 다시한번 확인
        // String menu_url = (String) request.getAttribute("MENU_URL");
        // String menuUrl = StringUtils.defaultString(request.getParameter("menuUrl"));
        // // 새로고침시 menuUrl 잃어 버림.
        // if ("".equals(menuUrl) && "".equals(menu_url)) {
        // menuUrl = request.getServletPath();
        // } else if ("".equals(menuUrl) && !"".equals(menu_url)) {
        // menuUrl = menu_url;
        // }
        // modelAndView.addObject("MENU_URL", menuUrl);
        // modelAndView.setViewName("/common/menu/left");
        if (StringUtils.endsWith(StringUtils.substringBefore((String) request.getSession().getAttribute("MENU_URL"), "?"), ".pop")) {
            return "/common/menu/emptyMenu";
        } else {
            return "/common/menu/left";
        }
    }

}
