package com.aot.pcfems.business.setting.gt;
/*****************************************************************************
 * 프로그램명  : GtController.java
 * 설     명  : Gt 관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/

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
 * Gt 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class GtController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private GtService gtService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;

    /**
     * Gt 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gt.do")
    public ModelAndView getGt(final HttpServletRequest request, final GtSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/gt/GtList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Gt 관리 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gt.do", params = "actionID=ACTION_GT_LIST")
    @ResponseBody
    public JsonObject getGtList(final HttpServletRequest request, final GtSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return JqGridUtil.getJsonData(searchInfo, this.gtService.getGtInfoList(searchInfo));
    }

    /**
     * Gt 관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gtInsertPopup.do")
    public ModelAndView insertGt(final HttpServletRequest request, final GtSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/setting/gt/GtAdd");
    }

    /**
     * Gt 관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gtInsertPopup.do", params = "actionID=ACTION_GT_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertGt(final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.gtService.insertGt(info);

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
     * Gt 관리 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gtModPopup.do")
    public ModelAndView updateGt(final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final GtInfo resultInfo = this.gtService.getGtDetail(info);

        final ModelAndView modelAndView = new ModelAndView("/setting/gt/GtEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("GT_DETAIL_DATA", resultInfo);
        return modelAndView;
    }

    /**
     * Gt 관리 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gtModPopup.do", params = "actionID=ACTION_GT_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateGt(final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.gtService.updateGt(info);

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
     * Gt 관리 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gt.do", params = "actionID=ACTION_GT_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteGt(final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.gtService.deleteGt(info);

        } catch (final Exception e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            result = new ProcessResult("", "", 0, "");
        }
        if (result.getRetCod() == 1) {
            returnMsg = "success:" + result.getRetMsg();
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
        return jsonObject;
    }

    /**
     * Gt 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/gt/gtPopup.do", params = "actionID=ACTION_GT_CHECK")
    @ResponseBody
    public JsonObject checkGtNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String gt = request.getParameter("gt");
        gt = new String(gt.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug(" Gt :  " + gt);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        final String checkRet = this.gtService.getGtExist(gt);

        jsonObject.addProperty("checkRet", checkRet);
        return jsonObject;
    }

    /**
     * GT 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/gt/gtexcelpop.pop")
    public ModelAndView goGtExcelUpload(final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/gt/GtExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Gt 관리 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/setting/gt/gtexcelpop.pop", params = "actionID=ACTION_GT_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOk(final HttpServletRequest request, final MultipartHttpServletRequest multi, final GtSearchInfo searchInfo, final GtInfo info)
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
        final JsonObject result = new JsonObject();
        try (Connection conn = this.sqlSession.getConnection()) {
            final String filePath = uploadPath + fileName;
            final StringBuffer sbResult = new StringBuffer();
            if (ExcelToDB.excelToDBSimple(conn, filePath, "conf_gt_mst", userInfo.getUser_id(), 2, sbResult)) {
                result.addProperty("returnMsg", sbResult.toString());
            }
            AotFileUtils.deleteFile(filePath);
        } catch (final SQLException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
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
    @RequestMapping(value = "/setting/gt/gt.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final GtSearchInfo searchInfo, final GtInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.SETTING.GT.TITLE.GT"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.DB_NAME"), "db_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.GT"), "gt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.PC_LIST_NM"), "pc_list_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.COUNTRY"), "country", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.GT.GRID.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.gtService.getGtInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}