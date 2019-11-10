/*****************************************************************************
 * 프로그램명  : LinkController.java
 * 설     명  : Link/Link 현황 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.link;

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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Link 현황 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class LinkController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LinkService linkService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;

    /**
     * Link 현황 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/link.do")
    public ModelAndView getLink(final HttpServletRequest request, final LinkSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/link/LinkList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Link 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/link.do", params = "actionID=ACTION_LINK_LIST")
    @ResponseBody
    public JsonObject getLinkInfoList(final HttpServletRequest request, final LinkSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return JqGridUtil.getJsonData(searchInfo, this.linkService.getLinkInfoList(searchInfo));
    }

    /**
     * Link 현황정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/linkInsertPopup.do")
    @ResponseBody
    public ModelAndView insertLink(final HttpServletRequest request, final LinkSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/obstacle/link/LinkAdd");
    }

    /**
     * Links 현황정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/linkInsertPopup.do", params = "actionID=ACTION_LINK_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertLink(final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linkService.insertLink(info);

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
     * Link 현황 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/linkModPopup.do")
    public ModelAndView updateLink(final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final LinkInfo resultInfo = this.linkService.getLinkDetail(info);

        final ModelAndView modelAndView = new ModelAndView("/obstacle/link/LinkEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("LINK_DETAIL_DATA", resultInfo);
        return modelAndView;
    }

    /**
     * Link 현황 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/linkModPopup.do", params = "actionID=ACTION_LINK_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateLink(final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linkService.updateLink(info);

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
     * Link 현황 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/link.do", params = "actionID=ACTION_LINK_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteLink(final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.linkService.deleteLink(info);

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
     * Link 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/obstacle/link/linkPopup.do", params = "actionID=ACTION_LINK_CHECK")
    @ResponseBody
    public JsonObject checkLinkNodeNameDup(final HttpServletRequest request, final LinkInfo info) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        /*
         * String apc = request.getParameter("apc"); String slc = request.getParameter("slc"); apc = new String(apc.getBytes("UTF-8"),"EUC-JP"); slc = new String(slc.getBytes("UTF-8"),"EUC-JP");
         * logger.debug(" incoding     :  "+request.getCharacterEncoding()); logger.debug(" Link :  "+apc); logger.debug(" Link :  "+slc);
         */
        // 갱신처리를 수행(OK:중복없음, NG:중복)1

        jsonObject.addProperty("checkRet", this.linkService.getLinkExist(info));
        return jsonObject;
    }

    /**
     * Link 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/link/linkexcelpop.pop")
    public ModelAndView goLinkExcel(final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/link/LinkExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Link 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/obstacle/link/linkexcelpop.pop", params = "actionID=ACTION_LINK_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOk(final HttpServletRequest request, final MultipartHttpServletRequest multi, final LinkSearchInfo searchInfo, final LinkInfo info)
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
            if (ExcelToDB.excelToDBSimple(conn, file_path, "conf_link_mst", userInfo.getUser_id(), 2, sbResult)) {
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
    @RequestMapping(value = "/obstacle/link/link.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final LinkSearchInfo searchInfo, final LinkInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.TITLE.LINK"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.APC"), "apc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.SLC"), "slc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.NA"), "na", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.SLOT_CH"), "slot_ch", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.COUNTRY_NM"), "country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.E1_CD"), "e1_cd", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.BEARRER_NM"), "bearrer_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.E1_TIME_SLOT"), "e1_time_slot", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.CABLE_NM"), "cable_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.NDCS_TIE"), "ndcs_tie", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.STATUS"), "status", ExcelService.CellType.STRING,
                CodeTableMng.getCodeListToJsonElement(userInfo, "LINK_STATUS"));
        // CodeTableMng.getName2(userInfo, "LINK_STATUS", result.getStatus())
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.UPD_ID"), "upd_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.LINK.GRID.UPD_DT"), "upd_dt", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.linkService.getLinkInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }
}