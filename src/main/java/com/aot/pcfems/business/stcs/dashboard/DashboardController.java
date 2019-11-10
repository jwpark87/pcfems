/*****************************************************************************
 * 프로그램명 : DashboardController.java 설 명 : Dashboard controller-layer class. 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2018.02.26 KYM 1.0 초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.dashboard;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.menu.MenuMng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * DASHBOARD controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class DashboardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DashboardService dashboardService;

    /**
     * Dashboard 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/dashboard.do")
    @ResponseBody
    public ModelAndView getDashboard(final HttpServletRequest request, final DashboardSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final String menu_url = (String) request.getAttribute("MENU_URL");
        final String menu_type = MenuMng.getMenuTypeByUrl(menu_url);

        final String indx_type = request.getParameter("index_type");
        searchInfo.setSrch_index_type(indx_type);

        final List<DashboardVO> grpList = this.dashboardService.getDashboardGrpList(searchInfo);

        final ModelAndView modelAndView = new ModelAndView("/stcs/dashboard/Dashboard", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("DASHBOARD_GRP_LIST", grpList);
        modelAndView.addObject("RESULT_DATA2", menu_type);
        final String menuType = (String) request.getAttribute("RESULT_DATA2");
        String type = "main";
        if ("W".equals(menuType)) {
            type = "panel";
        }
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    /**
     * Dashboard 아이템 취득
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/stcs/dashboard.do", params = "actionID=DASHBOARD_GRP_LIST")
    @ResponseBody
    public Map<String, Object> getDashboardItemDataList(final HttpServletRequest request, final DashboardSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final HttpSession session = request.getSession();
        // 하루로 계속 할당
        session.setMaxInactiveInterval(1440 * 60);
        this.logger.debug("session --- LIST ------- : " + session.getMaxInactiveInterval());

        return this.dashboardService.getDashboardItemDataList(searchInfo);
    }

}