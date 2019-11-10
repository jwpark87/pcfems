/*****************************************************************************
 * 프로그램명  : LinksetController.java
 * 설     명  : Linkset 현황 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.27   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.linkset;

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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Linkset 현황 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class LinksetController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LinksetService linksetService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;

    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Linkset 현황 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linkset.do")
    public ModelAndView getLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/linkset/LinksetList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Linkset 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linkset.do", params = "actionID=ACTION_LINKSET_LIST")
    @ResponseBody
    public JsonObject getLinksetInfoList(final HttpServletRequest request, final LinksetSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        return JqGridUtil.getJsonData(searchInfo, this.linksetService.getLinksetInfoList(searchInfo));
    }

    /**
     * Linkset 현황정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linksetInsertPopup.do")
    public ModelAndView insertLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/obstacle/linkset/LinksetAdd");
    }

    /**
     * Linkset 현황정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linksetInsertPopup.do", params = "actionID=ACTION_LINKSET_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linksetService.insertLinkset(info);

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
     * Linkset 현황 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linksetModPopup.do")
    public ModelAndView updateLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final ModelAndView modelAndView = new ModelAndView("/obstacle/linkset/LinksetEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("LINKSET_DETAIL_DATA", this.linksetService.getLinksetDetail(info));
        return modelAndView;
    }

    /**
     * Linkset 현황 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linksetModPopup.do", params = "actionID=ACTION_LINKSET_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linksetService.updateLinkset(info);

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
     * Linkset 현황 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linkset.do", params = "actionID=ACTION_LINKSET_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteLinkset(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linksetService.deleteLinkset(info);
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
     * Linkset 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/obstacle/linkset/linksetPopup.do", params = "actionID=ACTION_LINKSET_CHECK")
    @ResponseBody
    public JsonObject checkLinkNodeNameDupset(final HttpServletRequest request, final LinksetInfo info) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        /*
         * String apc = request.getParameter("apc"); String slc = request.getParameter("slc"); apc = new String(apc.getBytes("UTF-8"),"EUC-JP"); slc = new String(slc.getBytes("UTF-8"),"EUC-JP");
         * logger.debug(" incoding     :  "+request.getCharacterEncoding()); logger.debug(" Link :  "+apc); logger.debug(" Link :  "+slc);
         */
        // 갱신처리를 수행(OK:중복없음, NG:중복)1

        jsonObject.addProperty("checkRet", this.linksetService.getLinksetExist(info));
        return jsonObject;
    }

    /**
     * Linkset 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/linkset/linksetexcelpop.pop")
    public ModelAndView goLinksetExcel(final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/linkset/LinksetExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Linkset 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/obstacle/linkset/linksetexcelpop.pop", params = "actionID=ACTION_LINKSET_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOkset(final HttpServletRequest request, final MultipartHttpServletRequest multi, final LinksetSearchInfo searchInfo, final LinksetInfo info)
            throws UserSysException, IOException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
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

            final File file = new File(uploadPath + fileName);
            try {
                multiFile.transferTo(file);
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
            if (ExcelToDB.excelToDBSimple(conn, file_path, "conf_linkset_mst", userInfo.getUser_id(), 2, sbResult)) {
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
    @RequestMapping(value = "/obstacle/linkset/linkset.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final LinksetSearchInfo searchInfo, final LinksetInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.TITLE.LINKSET"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.STATUS"), "status_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.NA"), "na", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.APC"), "apc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.COUNTRY"), "country", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.CLLI"), "clli", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINKSET.GRID.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.linksetService.getLinksetInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }
}