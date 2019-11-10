/*****************************************************************************
 * 프로그램명  : TdmController.java
 * 설     명  : Tdm 현황 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.tdm;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.menu.MenuMng;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.AuthorityUtil;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.file.excel.ExcelService;
import com.aot.standard.common.file.excel.ExcelVO;
import com.aot.standard.common.util.AotDateUtils;
import com.aot.standard.common.util.AotFileUtils;
import com.aot.standard.common.util.AotMessageUtils;
import com.aot.standard.context.abstractview.AbstractExcelXView;
import com.eact.web.lib.utils.ExcelToDB;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
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
 * Tdm 현황 리스트 controller-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Controller
public class TdmController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdmService tdmService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * Tdm 현황 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdm.do")
    public ModelAndView getLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/tdm/TdmList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Tdm 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdm.do", params = "actionID=ACTION_TDM_LIST")
    @ResponseBody
    public JsonObject getLinkListset(final HttpServletRequest request, final TdmSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        final PagingUtil listPage = this.tdmService.getTdmInfoList(searchInfo);
        // int 값 null 일때 fromObject 후 0으로 바뀌어 버림. 직접 jsonArray로 변경하기.
        final JsonArray jArray = new JsonArray();
        for (int i = 0; i < listPage.getCurrList().size(); i++) {
            final TdmInfo info = (TdmInfo) listPage.getCurrList().get(i);
            final JsonObject data = new JsonObject();
            data.addProperty("sortseq", info.getSortseq());
            data.addProperty("locality", info.getLocality());
            data.addProperty("gnoc_tie_nm", info.getGnoc_tie_nm());
            data.addProperty("ts", info.getTs());
            data.addProperty("gnoc_tie_dsx_dcsa_no", info.getGnoc_tie_dsx_dcsa_no());
            data.addProperty("stp_tie", info.getStp_tie());
            data.addProperty("gnoc_tie_ndcs", info.getGnoc_tie_ndcs());
            data.addProperty("shelf", StringUtils.defaultString(String.valueOf(info.getShelf())));
            data.addProperty("slot", StringUtils.defaultString(String.valueOf(info.getSlot())));
            data.addProperty("e1_port", StringUtils.defaultString(String.valueOf(info.getE1_port())));
            data.addProperty("time_slot", info.getTime_slot());
            data.addProperty("ch_number", StringUtils.defaultString(String.valueOf(info.getCh_number())));
            data.addProperty("mapping", info.getMapping());
            data.addProperty("crc", info.getCrc());
            data.addProperty("use_status", info.getUse_status());
            data.addProperty("loc_bound", info.getLoc_bound());
            data.addProperty("na", info.getNa());
            data.addProperty("apc", info.getApc());
            data.addProperty("slc", StringUtils.defaultString(String.valueOf(info.getSlc())));
            data.addProperty("error_conn", info.getError_conn());
            data.addProperty("country_nm", info.getCountry_nm());
            data.addProperty("carrier_nm", info.getCarrier_nm());
            data.addProperty("clli", info.getClli());
            data.addProperty("bearer", info.getBearer());
            data.addProperty("bearer_time_slot", info.getBearer_time_slot());
            data.addProperty("cable", info.getCable());
            data.addProperty("remark1", info.getRemark1());
            data.addProperty("status", info.getStatus());
            data.addProperty("dsbd_yn", info.getDsbd_yn());
            jArray.add(data);
        }
        return JqGridUtil.getJsonData(searchInfo, listPage, jArray);
    }

    /**
     * Tdm 현황정보 입력 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdmInsertPopup.do")
    public ModelAndView insertLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/obstacle/tdm/TdmAdd");
    }

    /**
     * Tdm 현황정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdmInsertPopup.do", params = "actionID=ACTION_TDM_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.tdmService.insertTdm(info);

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
     * Tdm 현황 수정 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdmModPopup.do")
    public ModelAndView updateLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final TdmInfo resultInfo = this.tdmService.getTdmDetail(info);
        final ModelAndView modelAndView = new ModelAndView("/obstacle/tdm/TdmEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("TDM_DETAIL_DATA", resultInfo);
        return modelAndView;
    }

    /**
     * Tdm 현황 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdmModPopup.do", params = "actionID=ACTION_TDM_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.tdmService.updateTdm(info);

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
     * Tdm 현황 삭제 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdm.do", params = "actionID=ACTION_TDM_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteLinkset(final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.tdmService.deleteTdm(info);
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
     * Tdm 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/obstacle/tdm/tdmPopup.do", params = "actionID=ACTION_TDM_CHECK")
    @ResponseBody
    public JsonObject checkLinkNodeNameDupset(final HttpServletRequest request, final TdmInfo info) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();
        final String checkRet = this.tdmService.getTdmExist(info);
        jsonObject.addProperty("checkRet", checkRet);
        return jsonObject;
    }

    /**
     * Tdm 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/tdm/tdmexcelpop.pop")
    public ModelAndView goLinksetExcel(final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/tdm/TdmExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Tdm 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping(value = "/obstacle/tdm/tdmexcelpop.pop", params = "actionID=ACTION_TDM_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOkset(final HttpServletRequest request, final MultipartHttpServletRequest multi, final TdmSearchInfo searchInfo, final TdmInfo info)
            throws UserSysException, IOException, SQLException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        final String fileNowName = AotDateUtils.getStringNow("yyyyMMddHHmmssSSS");
        String fileName = "";
        final HttpSession session = request.getSession();
        final String destDir = session.getServletContext().getRealPath(this.FILE_UPLOAD_ROOT);
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
        final JsonObject jsonObject = new JsonObject();
        try (Connection conn = this.sqlSession.getConnection()) {
            final String file_path = uploadPath + fileName;
            final StringBuffer sbResult = new StringBuffer();
            if (ExcelToDB.excelToDBSimple(conn, file_path, "conf_tdm_mst", userInfo.getUser_id(), 2, sbResult)) {
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
    @RequestMapping(value = "/obstacle/tdm/tdm.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final TdmSearchInfo searchInfo, final TdmInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.TITLE.TDM"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.STATUS"), "status_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.GNOC_TIE_NM"), "gnoc_tie_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.TS"), "ts", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.GNOC_TIE_DSX_DCSA_NO"), "gnoc_tie_dsx_dcsa_no", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.STP_TIE"), "stp_tie", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.GNOC_TIE_NDCS"), "gnoc_tie_ndcs", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.SHELF"), "shelf", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.SLOT"), "slot", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.E1_PORT"), "e1_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.TIME_SLOT"), "time_slot", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.CH_NUMBER"), "ch_number", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.MAPPING"), "mapping", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.CRC"), "crc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.USE_STATUS"), "use_status", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.LOC_BOUND"), "loc_bound", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.NA"), "na", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.APC"), "apc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.SLC"), "slc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.ERROR_CONN"), "error_conn", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.COUNTRY_NM"), "country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.CLLI"), "clli", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.BEARER"), "bearer", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.BEARER_TIME_SLOT"), "bearer_time_slot", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.CABLE"), "cable", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.REMARK1"), "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.TDM.GRID.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.tdmService.getTdmInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }
}