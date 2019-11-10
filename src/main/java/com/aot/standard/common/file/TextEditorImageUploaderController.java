package com.aot.standard.common.file;

// @Controller
// public class AotTextEditorImageUploaderController {
// @RequestMapping(value = "/common/textEditor/imageUpload.proc", method = RequestMethod.POST)
// @ResponseBody
// public JsonObject imageUpload(@RequestParam(value = "file", required = true) final MultipartFile multipartFile) throws CommonResponseException {
// AotFileUtils.validateFile(multipartFile, FileType.IMAGE);
//
// JsonObject result = CommonResponseException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
// result.addProperty("url", StringUtils.replaceAll(
// CommonCode.FILEPATH_TEXT_EDITOR + "/" + AotDateUtils.getStringNow("yyyyMM") + "/" + AotFileUtils.uploadFile(multipartFile, CommonCode.FILEPATH_TEXT_EDITOR).getName(), "//", "/"));
// return result;
// }
// }
