/*****************************************************************************
 * 프로그램명 : ConfigMng.java 설 명 : 프로퍼티 파일 취득 빈 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2008.05.23 LYS 1.0 초기작성
 *****************************************************************************/

package com.aot.pcfems.common.file;

/**
 * 프로퍼티 파일 관리빈
 *
 * @author eaction
 * @version 1.0
 */
// public class ConfigMng {
// private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMng.class);
// private static final String CLASS_NAME = "ConfigMng";
// /** 프로퍼티 파일 명칭 */
// private static final String DEFAULT_CONFIG_FILE_NAME = "/properties/environment.properties";
// private static Properties props = new Properties();
// /** 취득하려는 파일 명칭 */
// private static String strFileName = "";
// /** 최종갱신되었는지를 저장한다 */
// private static long last_modified = 0;
//
// @Value("#{enviromentProperties['DEF_LOCALE']}")
// private static String DEF_LOCALE;
//
// /**
// * ȯ�프로퍼티 관리빈의 초기화
// */
// static {
// try {
// // 파일 전체경로취득
// String fullFilePath = getFullFilePath();
// // 파일이름 설정
// setFileName(fullFilePath);
// // 없는경우 신규생성
// initialize();
// } catch (Exception ex) {
// LOGGER.debug(ExceptionUtils.getStackTrace(ex));
// throw new RuntimeException(ex);
// }
// }
//
// /**
// * 프로퍼티 파일 풀패스 취득
// */
// private static String getFullFilePath() throws UserSysException {
// // 경로에 공백이 있으면 아스키코드 %20으로 받아온다, 그거 변환해주는 코드
// String filePath = ConfigMng.class.getClassLoader().getResource(DEFAULT_CONFIG_FILE_NAME).getPath().replaceAll("%20", " ");
// try {
// if (StringUtils.isEmpty(filePath)) {
// throw new UserSysException(CLASS_NAME, "getFullFilePath");
// }
// } catch (UserSysException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw ex;
// }
// return filePath;
// }
//
// /**
// * 프로퍼티 관리빈 초기화설정
// */
// private static void initialize() throws UserSysException {
// setInitFileData();
// }
//
// /**
// * 파일을 취득하여 파일 객체를 생성
// */
// private static void setInitFileData() throws UserSysException {
// try {
// // 파일객체생성
// File confFile = new File(getFileName());
// // 파일을 읽을수 없는 경우 에러
// if (!confFile.canRead()) {
// throw new UserSysException(CLASS_NAME, "setInitFileData");
// }
// // ȯ�프로퍼티값들을 취득 하는 함수 호출
// setConfigValues(confFile);
// } catch (UserSysException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw ex;
// } catch (Exception ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw new UserSysException(CLASS_NAME, "setInitFileData", ex);
// } // try
// }
//
// /**
// * 기 능 : 파일이 새로 갱신되었는지를 체크해서 갱신되었으면 새로 읽어들인다.
// */
// private static void checkModified() throws UserSysException {
// try {
// // 파일객체생성
// File confFile = new File(getFileName());
// // 파일을 읽을수 없는 경우 에러
// if (!confFile.canRead()) {
// throw new UserSysException(CLASS_NAME, "checkModified");
// }
// // 프로퍼티가 설정되어있는지 파일이 새로 갱신되었는지 확인한다.
// if (last_modified != confFile.lastModified() || props == null) {
// // 환경설정파일
// setConfigValues(confFile);
// }
// } catch (UserSysException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw ex;
// } catch (Exception ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw new UserSysException(CLASS_NAME, "checkModified", ex);
// } // try
// }
//
// /**
// * 키에 대한 값을 취득
// *
// * @param key
// * 키값
// * @return strReturn 키에대한 값
// */
// public static String getValue(final String key) {
// if (key != null && LOG_LEVEL.equals(key)) {
// try {
// checkModified();
// } catch (UserSysException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// }
// }
// String strReturn = "";
//
// if (key != null && props.get(key) != null) {
// strReturn = (String) props.get(key);
// strReturn = strReturn.trim();
// }
// return strReturn;
// }
//
// /**
// * 키에 대한 값을 취득(정수값취득)
// *
// * @param key
// * 키값
// * @return strReturn 키에대한 값
// */
// public static int getValueInt(final String key) {
// int nValue = 0;
// String value = getValue(key);
// if (!value.equals("")) {
// try {
// nValue = Integer.parseInt(value);
// } catch (NumberFormatException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// nValue = 0;
// }
// }
// return nValue;
// }
//
// /**
// * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
// *
// * @param :
// * conf_File 파일 객체
// */
// private static void setConfigValues(final File conf_File) throws UserSysException {
// try (FileInputStream conf_in = new FileInputStream(conf_File); BufferedInputStream bufferedInputStream = new BufferedInputStream(conf_in)) {
// props.load(bufferedInputStream);
// last_modified = conf_File.lastModified();
// } catch (FileNotFoundException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw new UserSysException(CLASS_NAME, "setConfigValues", ex);
// } catch (IOException ex) {
// LOGGER.warn(ExceptionUtils.getStackTrace(ex));
// throw new UserSysException(CLASS_NAME, "setConfigValues", ex);
// }
// }
//
// // Locale 취득
// public static Locale getLocale(final HttpServletRequest request) {
// // ko로 고정 18-04-27
// return new Locale(DEF_LOCALE);
// }
//
// public static String getLanguage(final Locale locale) {
// String language = DEF_LOCALE;
//
// if (locale == null) {
// return language;
// }
//
// if ("ko".equals(locale.getLanguage())) {
// language = "KOR";
// } else if ("en".equals(locale.getLanguage())) {
// language = "ENG";
// } else if ("ja".equals(locale.getLanguage())) {
// language = "JPN";
// } else {
// language = "KOR";
// }
//
// return language;
// }
//
// /**
// * 파일이름을 취득한다
// *
// * @return String 파일명칭
// */
// private static String getFileName() {
// return strFileName;
// }
//
// /**
// * 파일 이름을 설정한다
// *
// * @param fileName
// * 파일이름
// */
// private static void setFileName(final String fileName) {
// strFileName = fileName;
// }
// }
