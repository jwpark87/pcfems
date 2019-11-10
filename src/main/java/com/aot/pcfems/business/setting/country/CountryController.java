/*****************************************************************************
 * 프로그램명  : CountryController.java
 * 설     명  : COUNTRY설정관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.country;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * COUNTRY설정 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class CountryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CountryService countryService;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * COUNTRY설정 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/country.do")
    public ModelAndView getCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/country/CountryList", "SEARCH_DATA", searchInfo);
    }

    /**
     * COUNTRY설정 관리 목록 조회 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/country.do", params = "actionID=ACTION_COUNTRY_LIST")
    @ResponseBody
    public JsonObject getCountryList(final HttpServletRequest request, final CountrySearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);

        final JsonObject result = JqGridUtil.getJsonData(searchInfo, this.countryService.getCountryInfoList(searchInfo, AuthorityUtil.getUserInfo(request)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(searchInfo));
        return result;
    }

    /**
     * COUNTRY설정관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/sbcInsertPopup.do")
    public ModelAndView insertCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/setting/country/CountryAdd");
    }

    /**
     * COUNTRY설정관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/sbcInsertPopup.do", params = "actionID=ACTION_COUNTRY_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo, final CountryInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.countryService.insertCountry(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * COUNTRY설정관리정보 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/sbcModPopup.do")
    public ModelAndView updateCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo, final CountryInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        info.setPrefix(request.getParameter("prefix").replace("@", "#"));
        info.setUser_lang(this.LANGUAGE);
        final ModelAndView modelAndView = new ModelAndView("/setting/country/CountryEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("COUNTRY_DETAIL_DATA", this.countryService.getCountryDetail(info));
        return modelAndView;
    }

    /**
     * COUNTRY설정관리정보 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/sbcModPopup.do", params = "actionID=ACTION_COUNTRY_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo, final CountryInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.countryService.updateCountry(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * COUNTRY설정관리정보 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/country/country.do", params = "actionID=ACTION_COUNTRY_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteCountry(final HttpServletRequest request, final CountrySearchInfo searchInfo, final UserVO info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.countryService.deleteCountry(request.getParameter("prefix"));

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        jsonObject.addProperty("returnMsg", returnMsg);

        return jsonObject;
    }

    /**
     * Prefix 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/country/sbcPopup.do", params = "actionID=ACTION_PREFIX_CHECK")
    @ResponseBody
    public JsonObject checkCountryNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String prefix = request.getParameter("prefix");
        prefix = new String(prefix.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug(" prefix :  " + prefix);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        jsonObject.addProperty("checkRet", this.countryService.getPrefixExist(prefix));
        return jsonObject;
    }

    /**
     * 엑셀출력
     *
     * @param request
     * @param response
     * @return modelAndView
     * @throws UserSysException
     * @throws IOException
     */
    @RequestMapping(value = "/setting/country/country.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final CountrySearchInfo searchInfo, final CountryInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.TITLE.COUNTRY_MGMT"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.COUNTRY_ID"), "prefix", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.COUNTRY_NAME"), "country_cd", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.COUNTRY_GROUP"), "country_cd_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.AUTH_LEVEL"), "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.TEL"), "upd_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.COUNTRY.MGMT.GRID.EMAIL"), "upd_id_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage(""), "upd_dt", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.countryService.getCountryInfoList(searchInfo, userInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}