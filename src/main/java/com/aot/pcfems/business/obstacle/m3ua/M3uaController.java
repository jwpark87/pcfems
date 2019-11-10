/*****************************************************************************
 * 프로그램명  : M3uaController.java
 * 설     명  : Tdm 현황 리스트 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.m3ua;

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
public class M3uaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private M3uaService m3uaService;

    @Autowired
    private SqlSession sqlSession;

    @Value("#{enviromentProperties['FILE_UPLOAD_ROOT']}")
    private String FILE_UPLOAD_ROOT;
    @Value("#{enviromentProperties['LANGUAGE']}")
    private String LANGUAGE;

    /**
     * M3ua 현황 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/m3ua/m3ua.do")
    public ModelAndView getM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/m3ua/M3uaList", "SEARCH_DATA", searchInfo);
    }

    /**
     * Tdm 현황 목록 조회
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/m3ua/m3ua.do", params = "actionID=ACTION_M3UA_LIST")
    @ResponseBody
    public JsonObject getListM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo) throws UserSysException {
        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(this.LANGUAGE);
        final PagingUtil listPage = this.m3uaService.getM3uaInfoList(searchInfo);
        // int 값 null 일때 fromObject 후 0으로 바뀌어 버림. 직접 jsonArray로 변경하기.
        final JsonArray jArray = new JsonArray();
        for (int i = 0; i < listPage.getCurrList().size(); i++) {
            final M3uaInfo info = (M3uaInfo) listPage.getCurrList().get(i);
            final JsonObject data = new JsonObject();
            data.addProperty("sortseq", info.getSortseq());
            data.addProperty("locality", info.getLocality());
            data.addProperty("if_type", info.getIf_type());
            data.addProperty("country_nm", info.getCountry_nm());
            data.addProperty("carrier_nm", info.getCarrier_nm());
            data.addProperty("apc", info.getApc());
            data.addProperty("asp_id", info.getAsp_id());
            data.addProperty("loc_boundary", info.getLoc_boundary());
            data.addProperty("na", info.getNa());
            data.addProperty("slc", StringUtils.defaultString(String.valueOf(info.getSlc())));
            data.addProperty("clli", info.getClli());
            data.addProperty("routing_context", info.getRouting_context());
            data.addProperty("src_primary_ip", info.getSrc_primary_ip());
            data.addProperty("src_secondary_ip", info.getSrc_secondary_ip());
            data.addProperty("src_port", StringUtils.defaultString(String.valueOf(info.getSrc_port())));
            data.addProperty("dst_primary_ip", info.getDst_primary_ip());
            data.addProperty("dst_secondary_ip", info.getDst_secondary_ip());
            data.addProperty("dst_port", StringUtils.defaultString(String.valueOf(info.getDst_port())));
            data.addProperty("asp_type", info.getAsp_type());
            data.addProperty("si", info.getSi());
            data.addProperty("routing_cpu", info.getRouting_cpu());
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
    @RequestMapping(value = "/obstacle/m3ua/m3uaInsertPopup.do")
    public ModelAndView insertM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final UserVO info) throws UserSysException {
        return new ModelAndView("/obstacle/m3ua/M3uaAdd");
    }

    /**
     * Tdm 현황정보 입력 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/m3ua/m3uaInsertPopup.do", params = "actionID=ACTION_M3UA_INSERT_OK")
    @ResponseBody
    public JsonObject doInsertM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setCrt_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.m3uaService.insertM3ua(info);

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
    @RequestMapping(value = "/obstacle/m3ua/m3uaModPopup.do")
    public ModelAndView updateM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        final ModelAndView modelAndView = new ModelAndView("/obstacle/m3ua/M3uaEdit", "SEARCH_DATA", searchInfo);
        modelAndView.addObject("M3UA_DETAIL_DATA", this.m3uaService.getM3uaDetail(info));
        return modelAndView;
    }

    /**
     * Tdm 현황 수정 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/m3ua/m3uaModPopup.do", params = "actionID=ACTION_M3UA_UPDATE_OK")
    @ResponseBody
    public JsonObject doUpdateM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        searchInfo.doJqGridBind(request);
        info.setUpd_id(userInfo.getUser_id());

        final JsonObject jsonObject = new JsonObject();
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.m3uaService.updateM3ua(info);
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
    @RequestMapping(value = "/obstacle/m3ua/m3ua.do", params = "actionID=ACTION_M3UA_DELETE_OK")
    @ResponseBody
    public JsonObject doDeleteM3ua(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        ProcessResult result = null;
        String returnMsg = "fail:" + AotMessageUtils.getMessage("MSG.ALERT.PROCESS.ERROR", "");

        try {
            result = this.m3uaService.deleteM3ua(info);
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
     * Tdm 중복체크
     *
     * @param request
     * @param response
     * @param info
     * @return
     * @throws UserSysException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/obstacle/m3ua/m3uaPopup.do", params = "actionID=ACTION_M3UA_CHECK")
    @ResponseBody
    public JsonObject checkLinkNodeNameDupset(final HttpServletRequest request, final M3uaInfo info) throws UserSysException, UnsupportedEncodingException {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("checkRet", this.m3uaService.getM3uaExist(info));
        return jsonObject;
    }

    /**
     * Tdm 관리 엑셀 업로드 화면
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     */
    @RequestMapping(value = "/obstacle/m3ua/m3uaexcelpop.pop")
    public ModelAndView goM3uaExcel(final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        searchInfo.doJqGridBind(request);
        return new ModelAndView("/obstacle/m3ua/M3uaExcel", "SEARCH_DATA", searchInfo);
    }

    /**
     * Tdm 엑셀 업로드 처리
     *
     * @param request  Request객체
     * @param response Response객체
     * @throws UserSysException 시스템예외
     * @throws IOException
     */
    @RequestMapping(value = "/obstacle/m3ua/m3uaexcelpop.pop", params = "actionID=ACTION_M3UA_EXCEL_UPLOAD_OK")
    @ResponseBody
    public JsonObject doGtExcelUploadOksetM3ua(final HttpServletRequest request, final MultipartHttpServletRequest multi, final M3uaSearchInfo searchInfo, final M3uaInfo info)
            throws UserSysException, IOException {
        // final UserVO userInfo = AuthorityUtil.getUserInfo(request);
        String fileName = "";
        final String uploadPath = request.getSession().getServletContext().getRealPath(this.FILE_UPLOAD_ROOT) + "/tmpexcel/";
        final MultipartFile multiFile = ((MultipartHttpServletRequest) request).getFile("files");
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
            if (ExcelToDB.excelToDBSimple(conn, file_path, "conf_m3ua_mst", AuthorityUtil.getUserInfo(request).getUser_id(), 2, sbResult)) {
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
    @RequestMapping(value = "/obstacle/m3ua/m3ua.do", params = "actionID=ACTION_GET_EXCEL")
    public String goExcelDown(final Model model, final HttpServletRequest request, final M3uaSearchInfo searchInfo, final M3uaInfo info) throws UserSysException {
        final UserVO userInfo = AuthorityUtil.getUserInfo(request);

        model.addAttribute(AbstractExcelXView.FILE_NAME, MenuMng.getMenuName(MenuMng.getMenuIdByUrl((String) request.getAttribute("MENU_URL")), userInfo));
        model.addAttribute(AbstractExcelXView.TITLE, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.TITLE.M3UA"));

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.LOCALITY"), "locality_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.STATUS"), "status_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.IF_TYPE"), "if_type", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.COUNTRY_NM"), "country_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.CARRIER_NM"), "carrier_nm", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.APC"), "apc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.ASP_ID"), "asp_id", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.LOC_BOUNDARY"), "loc_boundary", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.NA"), "na", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.SLC"), "slc", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.CLLI"), "clli", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.ROUTING_CONTEXT"), "routing_context", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.SRC_PRIMARY_IP"), "src_primary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.SRC_SECONDARY_IP"), "src_secondary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.SRC_PORT"), "src_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.DST_PRIMARY_IP"), "dst_primary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.DST_SECONDARY_IP"), "dst_secondary_ip", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.DST_PORT"), "dst_port", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.ASP_TYPE"), "asp_type", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.SI"), "si", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.ROUTING_CPU"), "routing_cpu", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, AotMessageUtils.getMessage("TEXT.OBSTACLE.M3UA.EXCEL.DSBD_YN"), "dsbd_yn_nm", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        searchInfo.doJqGridBind(request);
        searchInfo.setUser_lang(userInfo.getUser_lang());
        searchInfo.setSearch_excel_type("EXCEL");
        searchInfo.setJqSidx(request.getParameter("srchSidx"));
        searchInfo.setJqSord(request.getParameter("srchSord"));
        model.addAttribute(AbstractExcelXView.LIST_DATA, this.m3uaService.getM3uaInfoList(searchInfo).getCurrList());

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }
}