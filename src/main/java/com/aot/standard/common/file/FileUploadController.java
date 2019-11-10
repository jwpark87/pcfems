package com.aot.standard.common.file;

// @Controller
// public class AotFileUploadController {
// @RequestMapping(value = "/common/file/upload/fileUpload", method = RequestMethod.POST)
// @ResponseBody
// public JsonObject uploadFile(@RequestParam("file") final MultipartFile multipartFile, final String targetDirPath) throws CommonResponseException {
// JsonObject result = CommonResponseException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
// result.addProperty("fileName", AotFileUtils.uploadFile(multipartFile, targetDirPath).getName());
// return result;
// }
//
// @RequestMapping(value = "/common/file/upload/uploadAllFiles", method = RequestMethod.POST)
// @ResponseBody
// public JsonObject uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) throws CommonResponseException {
// JsonObject result = CommonResponseException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
//
// List<File> uploadAllFiles = AotFileUtils.uploadAllFiles(mRequest, targetDirPath);
// StringBuffer stringBuffer = new StringBuffer();
// for (int i = 0; i < uploadAllFiles.size(); i++) {
// stringBuffer.append(uploadAllFiles.get(i).getName()).append("\n");
// }
// result.addProperty("fileName", stringBuffer.toString());
// return result;
// }
// }
