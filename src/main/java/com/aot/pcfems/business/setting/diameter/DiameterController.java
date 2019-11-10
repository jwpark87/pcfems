package com.aot.pcfems.business.setting.diameter;
/*****************************************************************************
 * 프로그램명  : DiameterController.java
 * 설     명  : Diameter Host 관리 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.StringUtil;
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
 * Diameter Host 관리 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class DiameterController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private DiameterService diameterService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Diameter 관리 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameter.do")
    public ModelAndView getDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/diameter/DiameterList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Diameter 관리 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameter.do", params = "actionID=ACTION_DIAMETER_LIST")
    @ResponseBody
    public JsonObject getDiameterList(final HttpServletRequest request, final DiameterSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        return JqGridUtil.getJsonData(searchInfo, this.diameterService.getDiameterInfoList(searchInfo));
    }

    /**
     * Diameter 관리정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterInsertPopup.do")
    public ModelAndView insertDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo) throws UserSysException {
        return new ModelAndView("/setting/diameter/DiameterAdd");
    }

    /**
     * Diameter 관리정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterInsertPopup.do", params = "actionID=ACTION_DIAMETER_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo, final DiameterInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.diameterService.insertDiameter(info);

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
     * Diameter 관리 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterModPopup.do")
    public ModelAndView updateDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo, final DiameterInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final ModelAndView modelAndView = new ModelAndView("/setting/diameter/DiameterEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("DIAMETER_DETAIL_DATA", this.diameterService.getDiameterDetail(info));
        return modelAndView;
    }

    /**
     * Diameter 관리 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterModPopup.do", params = "actionID=ACTION_DIAMETER_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo, final DiameterInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.diameterService.updateDiameter(info);

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
     * Diameter 관리 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameter.do", params = "actionID=ACTION_DIAMETER_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteDiameter(final HttpServletRequest request, final DiameterSearchInfo searchInfo, final DiameterInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.diameterService.deleteDiameter(info);

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
     * Diameter 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/setting/diameter/diameterPopup.do", params = "actionID=ACTION_DIAMETER_CHECK")
    @ResponseBody
    public JsonObject checkDiameterNodeNameDup(final HttpServletRequest request) throws UserSysException, UnsupportedEncodingException {

        String diameter = request.getParameter("host");
        diameter = new String(diameter.getBytes(StandardCharsets.UTF_8), "EUC-JP");
        this.logger.debug(" incoding     :  " + request.getCharacterEncoding());
        this.logger.debug(" Diameter :  " + diameter);
        // 갱신처리를 수행(OK:중복없음, NG:중복)1
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("checkRet", this.diameterService.getDiameterExist(diameter));
        return jsonObject;
    }

    /**
     * Diameter 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/setting/diameter/diameterexcelpop.pop")
    @ResponseBody
    public ModelAndView goDiameterExcelUpload(final HttpServletRequest request, final DiameterSearchInfo searchInfo, final DiameterInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/setting/diameter/DiameterExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Diameter 관리 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/setting/diameter/diameterexcelpop.pop", params = "actionID=ACTION_DIAMETER_EXCEL_UPLOAD_OK")
    // @RequestMapping(value="/setting/diameter/diameterExcelOkPopup.do", method=RequestMethod.POST, produces="text/plain")
    @ResponseBody
    public JsonObject doDiameterExcelUploadOk(final HttpServletRequest request, final MultipartHttpServletRequest multi, final DiameterSearchInfo searchInfo, final DiameterInfo info)
            throws UserSysException, IOException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        String returnMsg = "";

        boolean bRet = true;
        final StringBuffer sbResult = new StringBuffer();

        // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        final String fileNowName = AotDateUtils.getStringNow("yyyyMMddHHmmssSSS");
        String fileName = "";
        final HttpSession session = request.getSession();
        final String path = this.FILE_UPLOAD_ROOT;
        final String destDir = session.getServletContext().getRealPath(path);
        final String uploadPath = destDir + "/tmpexcel/";
        final MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
        final MultipartFile multiFile = mpRequest.getFile("files");
        if (multiFile != null && multiFile.getSize() > 0) {
            final File work = new File(uploadPath);
            if (!work.exists()) {
                FileUtils.forceMkdir(work);
            }
            fileName = FilenameUtils.getBaseName(fileName) + fileNowName + FilenameUtils.getExtension(multiFile.getOriginalFilename());

            try {
                multiFile.transferTo(new File(uploadPath + fileName));
            } catch (final IllegalStateException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
            } catch (final IOException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
            }
        }
        try (Connection conn = this.sqlSession.getConnection()) {
            final String filePath = uploadPath + fileName;
            bRet = ExcelToDB.excelToDBSimple(conn, filePath, "conf_diameter_peer", userInfo.getUser_id(), 2, sbResult);

            if (bRet) {
                returnMsg = StringUtil.convertText(sbResult.toString());
            }
            AotFileUtils.deleteFile(filePath);
        } catch (final SQLException e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            // ignored
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("returnMsg", returnMsg);
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
    @RequestMapping(value = "/setting/diameter/diameter.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final DiameterSearchInfo searchInfo) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.TITLE.DIAMETER"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.STATUS"), "status_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.COUNTRY_NM"), "country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.LOCAL"), "local", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.REALM"), "realm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.HOST"), "host", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.REMOTE_PEER_MODE"), "remote_peer_mode", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.LOCAL_SLOT"), "local_slot", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.LOCAL_PRIMARY_IP"), "local_primary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.LOCAL_SECONDARY_IP"), "local_secondary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.LOCAL_PORT"), "local_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.REMOTE_PRIMARY_IP"), "remote_primary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.REMOTE_SECONDARY_IP"), "remote_secondary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.REMOTE_PORT"), "remote_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.ACCESS_CTRL_LIST"), "access_ctrl_list", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.INTERFACE"), "strInterface", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_TW_TIMER"), "sctp_tw_timer", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_HB_INTERVAL"), "sctp_hb_interval", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_ASSOC_MAX_RETRANS"), "sctp_assoc_max_retrans", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_RTO_INIT"), "sctp_rto_init", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_RTO_MIN"), "sctp_rto_min", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_RTO_MAX"), "sctp_rto_max", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_PATH_MAX_RETRANS"), "sctp_path_max_retrans", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_MAX_INIT_RETRANS"), "sctp_max_init_retrans", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_MAX_SEG"), "sctp_max_seg", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.EXCEL.SCTP_SACK_TIMEOUT"), "sctp_sack_timeout", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CONTACT_NAME"), "contact_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CONTACT_EMAIL"), "contact_email", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CONTACT_NOC"), "contact_noc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CONTACT_FIX_PHONE"), "contact_fix_phone", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.CONTACT_MOBILE_PHONE"), "contact_mobile_phone", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.VENDOR_NAME"), "vendor_name", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.OPEN_DT"), "open_dt", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.SETTING.DIAMETER.GRID.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.diameterService.getDiameterInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

}