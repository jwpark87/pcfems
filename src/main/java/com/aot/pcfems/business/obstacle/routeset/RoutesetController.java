/*****************************************************************************
 * 프로그램명  : RoutesetController.java
 * 설     명  : Routeset 현황 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.routeset;

import com.aot.pcfems.common.code.CodeTableMng;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotFileUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.eact.web.lib.utils.ExcelToDB;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Routeset 현황 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class RoutesetController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RoutesetService routesetService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Routeset 현황 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routeset.do")
    public ModelAndView getRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/routeset/RoutesetList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Routeset 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routeset.do", params = "actionID=ACTION_ROUTESET_LIST")
    @ResponseBody
    public JsonObject getRoutesetList(final HttpServletRequest request, final RoutesetSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        return JqGridUtil.getJsonData(searchInfo, this.routesetService.getRoutesetInfoList(searchInfo));
    }

    /**
     * Routeset 현황정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routesetInsertPopup.do")
    public ModelAndView insertRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/obstacle/routeset/RoutesetAdd");
    }

    /**
     * Routeset 현황정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routesetInsertPopup.do", params = "actionID=ACTION_ROUTESET_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.routesetService.insertRouteset(info);

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
     * Routeset 현황 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routesetModPopup.do")
    public ModelAndView updateRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        info.setUser_lang(this.LANGUAGE);
        final RoutesetInfo resultInfo = this.routesetService.getRoutesetDetail(info);

        final ModelAndView modelAndView = new ModelAndView("/obstacle/routeset/RoutesetEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("ROUTESET_DETAIL_DATA", resultInfo);
        return modelAndView;
    }

    /**
     * Routeset 현황 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routesetModPopup.do", params = "actionID=ACTION_ROUTESET_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.routesetService.updateRouteset(info);

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
     * Routeset 현황 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routeset.do", params = "actionID=ACTION_ROUTESET_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteRouteset(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.routesetService.deleteRouteset(info);

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
     * Routeset 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/obstacle/routeset/routesetPopup.do", params = "actionID=ACTION_ROUTESET_CHECK")
    @ResponseBody
    public JsonObject checkRoutesetNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String apc = request.getParameter("apc");
        apc = new String(apc.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug(" Routeset :  " + apc);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        final String checkRet = this.routesetService.getRoutesetExist(apc);

        jsonObject.addProperty("checkRet", checkRet);
        return jsonObject;
    }

    /**
     * Routeset 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/routeset/routesetexcelpop.pop")
    public ModelAndView goRoutesetExcel(final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/routeset/RoutesetExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Gt 관리 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/obstacle/routeset/routesetexcelpop.pop", params = "actionID=ACTION_ROUTESET_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOk(final HttpServletRequest request, final MultipartHttpServletRequest multi, final RoutesetSearchInfo searchInfo, final RoutesetInfo info)
            throws UserSysException, IOException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        String fileName = "";
        final HttpSession session = request.getSession();
        final String uploadPath = session.getServletContext().getRealPath(this.FILE_UPLOAD_ROOT) + "/tmpexcel/";
        final MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        final MultipartFile multiFile = mpRequest.getFile("files");
        if (multiFile != null && multiFile.getSize() > 0) {
            final File work = new File(uploadPath);
            if (!work.exists()) {
                FileUtils.forceMkdir(work);
            }
            fileName = FilenameUtils.getBaseName(fileName) + AotDateUtils.getStringNow("yyyyMMddHHmmssSSS") + FilenameUtils.getExtension(multiFile.getOriginalFilename());

            try {
                multiFile.transferTo(new File(uploadPath + fileName));
            } catch (final IllegalStateException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
            } catch (final IOException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
            }
        }
        final JsonObject jsonObject = new JsonObject();
        try (Connection conn = this.sqlSession.getConnection()) {
            final String file_path = uploadPath + fileName;
            final StringBuffer sbResult = new StringBuffer();
            if (ExcelToDB.excelToDBSimple(conn, file_path, "conf_routeset_mst", userInfo.getUser_id(), 2, sbResult)) {
                jsonObject.addProperty("returnMsg", sbResult.toString());
            }
            AotFileUtils.deleteFile(file_path);
        } catch (final SQLException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
        }

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
    @RequestMapping(value = "/obstacle/routeset/routeset.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final RoutesetSearchInfo searchInfo, final RoutesetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.TITLE.ROUTESET"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.STATUS"), "status", ExcelService.CellType.STRING,
                CodeTableMng.getCodeListToJsonElement(userInfo, "LINK_STATUS"));
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.NA"), "na", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.APC"), "apc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.CLLI"), "clli", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.COUNTRY"), "country", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.ROUTESET.GRID.LOCAL"), "local", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.routesetService.getRoutesetInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}