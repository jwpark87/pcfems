/*****************************************************************************
 * 프로그램명 : DownloadController.java 설 명 : 다운로드 처리 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2008.09.25 LYS 1.0 초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.web;

/**
 * 다운로드 처리
 *
 * @author eaction
 * @version 1.0
 */
// @Controller
// public class DownloadController {
// private final Logger logger = LoggerFactory.getLogger(this.getClass());

// @RequestMapping(value = "/download.do")
// public String handleRequest(final Model model, final HttpServletRequest request) throws UserSysException {
// // 파일 전체 경로설정
// StringBuffer sbFileName = new StringBuffer(request.getSession().getServletContext().getRealPath(ConfigMng.getValue(request.getParameter("keyPath"))));
// sbFileName.append(System.getProperty("file.separator"));
// String pathSub = StringUtils.defaultString(request.getParameter("pathSub"));
// if (StringUtils.isNotEmpty(pathSub)) {
// sbFileName.append(pathSub);
// /* sbFileName.append(System.getProperty("file.separator")); */
// }
// model.addAttribute(AbstractDownloadView.DOWNLOAD_FILE, new File(sbFileName.toString()));
// model.addAttribute(AbstractDownloadView.DOWNLOAD_ORI_FILE_NAME, StringUtils.defaultIfEmpty(request.getParameter("refFileName"), request.getParameter("fileName")));
// return AbstractDownloadView.VIEW_NAME;
// }
// }
