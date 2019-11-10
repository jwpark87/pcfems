package com.aot.pcfems.business.setting.diameterrealm;
/*****************************************************************************
 * 프로그램명  : DiameterController.java
 * 설     명  : Diameter Realm 관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.28   KYM     1.0     초기작성
 *****************************************************************************/

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.exception.CommonException;
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
 * Diameter Realm 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class DiameterRealmController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DiameterRealmService diameterRealmService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Diameter Realm 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterrealm.do")
    public ModelAndView getDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/diameterrealm/DiameterRealmList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Diameter Realm 관리 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterrealm.do", params = "actionID=ACTION_DIAMETER_REALM_LIST")
    @ResponseBody
    public JsonObject getDiameterRealmList(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        return JqGridUtil.getJsonData(searchInfo, this.diameterRealmService.getDiameterRealmInfoList(searchInfo));
    }

    /**
     * Diameter Realm 관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterRealmInsertPopup.do")
    public ModelAndView insertDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/setting/diameterrealm/DiameterRealmAdd");
    }

    /**
     * Diameter Realm 관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterRealmInsertPopup.do", params = "actionID=ACTION_DIAMETER_REALM_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());
        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.diameterRealmService.insertDiameterRealm(info);
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
     * Diameter Realm 관리 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterRealmModPopup.do")
    public ModelAndView updateDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final ModelAndView modelAndView = new ModelAndView("/setting/diameterrealm/DiameterRealmEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("DIAMETER_REALM_DETAIL_DATA", this.diameterRealmService.getDiameterRealmDetail(info));
        return modelAndView;
    }

    /**
     * Diameter Realm 관리 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterRealmModPopup.do", params = "actionID=ACTION_DIAMETER_REALM_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.diameterRealmService.updateDiameterRealm(info);
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
     * Diameter Realm 관리 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterrealm.do", params = "actionID=ACTION_DIAMETER_REALM_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteDiameterRealm(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");
        try {
            result = this.diameterRealmService.deleteDiameterRealm(info);
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
     * Diameter Realm 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/diameter/diameterRealmPopup.do", params = "actionID=ACTION_DIAMETER_REALM_CHECK")
    @ResponseBody
    public JsonObject checkDiameterNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();

        String diameter = request.getParameter("realm");
        diameter = new String(diameter.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug(" Diameter :  " + diameter);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        jsonObject.addProperty("checkRet", this.diameterRealmService.getDiameterRealmExist(diameter));
        return jsonObject;
    }

    /**
     * Diameter Realm 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterrealmexcelpop.pop")
    public ModelAndView goDiameterRealmExcelUpload(final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/diameterrealm/DiameterRealmExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Diameter Realm 관리 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws IOException
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterrealmexcelpop.pop", params = "actionID=ACTION_DIAMETER_REALM_EXCEL_UPLOAD_OK")
    // @RequestMapping(value="/setting/diameter/diameterExcelOkPopup.do", method=RequestMethod.POST, produces="text/plain")
    @ResponseBody
    public JsonObject doDiameterRealmExcelUploadOk(final MultipartHttpServletRequest mpRequest, final HttpSession session, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info)
            throws CommonException, IOException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(session);
        // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        String fileName = "";
        final String uploadPath = session.getServletContext().getRealPath(this.FILE_UPLOAD_ROOT) + "/tmpexcel/";
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
                // ignored
            } catch (final IOException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                // ignored
            }
        }
        final JsonObject jsonObject = new JsonObject();
        try (Connection conn = this.sqlSession.getConnection()) {
            final String filePath = uploadPath + fileName;
            final StringBuffer sbResult = new StringBuffer();
            if (ExcelToDB.excelToDBSimple(conn, filePath, "conf_diameter_realm", userInfo.getUser_id(), 2, sbResult)) {
                jsonObject.addProperty("returnMsg", StringUtil.convertText(sbResult.toString()));
            }
            AotFileUtils.deleteFile(filePath);
        } catch (final SQLException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            // ignored
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
    @RequestMapping(value = "/setting/diameter/diameterrealm.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final DiameterRealmSearchInfo searchInfo, final DiameterRealmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.TITLE.DIAMETER_REALM"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.COUNTRY"), "country", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.ROUTING_PEER"), "routing_peer", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.REALM"), "realm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.CONTACT_EMAIL"), "contact_email", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.OPEN_DT"), "open_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.REALM.GRID.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.diameterRealmService.getDiameterRealmInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}