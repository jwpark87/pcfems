/*****************************************************************************
 * 프로그램명  : FooterController.java
 * 설     명  : 풋터 제어
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.11.12  JSM    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.web;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 풋터 제어
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class FooterController {
    @Value("#{enviromentProperties['PROJECT_TITLE']}")
    private String PROJECT_TITLE;

    /**
     * 풋터 표시처리
     *
     * @param request  요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체
     */
    @RequestMapping(value = "/footer.do")
    public String goController(final Model model, final HttpServletRequest request) throws UserSysException {
        model.addAttribute("PROJECT_TITLE", this.PROJECT_TITLE);
        if (StringUtils.endsWith(StringUtils.substringBefore((String) request.getSession().getAttribute("MENU_URL"), "?"), ".pop")) {
            return "/common/menu/emptyFooter";
        } else {
            return "/common/menu/footer";
        }

    }
}
