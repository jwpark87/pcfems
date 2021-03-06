package com.aot.standard.common.util;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tika.Tika;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AotFileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AotFileUtils.class);
    private static final String FILE_ROOT_PATH = "/workspace/uploadRootPath/";
    private static final String STR_DOT = ".";
    private static final String STR_INFO_MESSAGE = "Target for uploading file : {}";
    private static final String STR_UNDERLINE = "_";

    private AotFileUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean deleteDirectory(final File file) throws CommonException {
        forceDelete(file);
        LOGGER.info("Target for deleting dir : {}", file.getAbsolutePath());
        return true;
    }

    public static boolean deleteDirectory(final String filePath) throws CommonException {
        return deleteFile(getFile(filePath));
    }

    public static boolean deleteFile(final File file) throws CommonException {
        forceDelete(file);
        LOGGER.info("Target for deleting file : {}", file.getAbsolutePath());
        return true;
    }

    public static boolean deleteFile(final String filePath) throws CommonException {
        return deleteFile(getFile(filePath));
    }

    private static File forceDelete(final File file) throws CommonException {
        try {
            FileUtils.forceDelete(file);
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            // throw new CommonResponseException(e);
        }
        return file;
    }

    public static String getEncodedFileName(final HttpServletRequest request, final String fileName) throws CommonException {
        try {
            final String header = request.getHeader("User-Agent");

            String encodedFilename;
            if (StringUtils.contains(header, "MSIE")) {
                encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()).replaceAll("\\+", "%20");
            } else if (StringUtils.contains(header, "Trident")) {
                encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()).replaceAll("\\+", "%20");
            } else if (StringUtils.contains(header, "Chrome")) {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < fileName.length(); i++) {
                    final char c = fileName.charAt(i);
                    if (c > '~') {
                        sb.append(URLEncoder.encode("" + c, StandardCharsets.UTF_8.displayName()));
                    } else {
                        sb.append(c);
                    }
                }
                encodedFilename = sb.toString();
            } else if (StringUtils.contains(header, "Opera")) {
                encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
                // } else if (StringUtils.contains(header, "Safari")) {
                // encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
                // encodedFilename = URLDecoder.decode(encodedFilename, StandardCharsets.UTF_8.displayName());
            } else {
                encodedFilename = "\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"";
                encodedFilename = URLDecoder.decode(encodedFilename, StandardCharsets.UTF_8.displayName());
            }
            return encodedFilename;
        } catch (final UnsupportedEncodingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static File getFile(final String filePath) throws CommonException {
        final String path = RegExUtils.replaceAll(getFileRoot() + filePath, "\\\\", "/").replaceAll("//", "/");
        final File file = FileUtils.getFile(path);
        if (file.isDirectory() && !StringUtils.endsWith(path, "/")) {
            LOGGER.warn("{} : {}", path, CommonExceptionCode.ERROR_DIR_PATH_MUST_ENDS_WITH_SLASH.toString());
            throw new CommonException(CommonExceptionCode.ERROR_DIR_PATH_MUST_ENDS_WITH_SLASH);
        }
        return file;
    }

    public static File getDirectory(final String dirPath) throws CommonException {
        final String path = RegExUtils.replaceAll(getFileRoot() + dirPath, "\\\\", "/").replaceAll("//", "/");
        if (!StringUtils.endsWith(path, "/")) {
            LOGGER.warn("{} : {}", path, CommonExceptionCode.ERROR_DIR_PATH_MUST_ENDS_WITH_SLASH.toString());
            throw new CommonException(CommonExceptionCode.ERROR_DIR_PATH_MUST_ENDS_WITH_SLASH);
        }
        final File file = getFile(path);
        if (!AotNullUtils.exists(file)) {
            try {
                FileUtils.forceMkdir(file);
            } catch (final IOException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                // ignored
            }
        }
        return file;
    }

    private static String getFileRoot() {
        if (StringUtils.containsIgnoreCase(System.getProperty("os.name"), "win")) {
            return "C:" + FILE_ROOT_PATH;
        } else {
            return FILE_ROOT_PATH;
        }
    }

    public static boolean isExistsFile(final String filePath) throws CommonException {
        return AotNullUtils.exists(getFile(filePath));
    }

    public static List<File> uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) throws CommonException {
        final Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        if (AotNullUtils.size(fileMap) < 1) {
            return null;
        }
        getDirectory(targetDirPath);
        final Iterator<String> fileNames = mRequest.getFileNames();
        final List<File> savedFiles = new ArrayList<>();
        while (AotNullUtils.hasNext(fileNames)) {
            final MultipartFile multipartFile = fileMap.get(fileNames.next());

            final StringBuilder fileName = new StringBuilder(80);
            fileName.append(DateTime.now().toString(AotDateUtils.YYYYMMDDHHMMSS)).append(STR_UNDERLINE).append(DigestUtils.md5Hex(multipartFile.getOriginalFilename()));
            if (StringUtils.isNotEmpty(getExtension(multipartFile))) {
                fileName.append(STR_DOT).append(getExtension(multipartFile));
            }
            final File file = getFile(targetDirPath + "/" + fileName);
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), file);
            } catch (final IOException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                throw new CommonException(e);
            }
            savedFiles.add(file);
            LOGGER.info(STR_INFO_MESSAGE, file.getAbsolutePath());
        }
        return savedFiles;
    }

    public static File uploadFile(final MultipartFile multipartFile, final String targetDirPath) throws CommonException {
        if (AotNullUtils.isEmpty(multipartFile)) {
            return null;
        }
        getDirectory(targetDirPath);
        final StringBuilder fileName = new StringBuilder(80);
        fileName.append(DateTime.now().toString(AotDateUtils.YYYYMMDDHHMMSS)).append(STR_UNDERLINE).append(DigestUtils.md5Hex(multipartFile.getOriginalFilename()));
        if (StringUtils.isNotEmpty(getExtension(multipartFile))) {
            fileName.append(STR_DOT).append(getExtension(multipartFile));
        }
        final File file = getFile(targetDirPath + "/" + fileName);
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), file);
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        LOGGER.info(STR_INFO_MESSAGE, file.getAbsolutePath());
        return file;
    }

    public enum FileType {
        IMAGE(Arrays.asList("gif", "jpg", "jpeg", "tif", "tiff", "png", "bmp"), Arrays.asList("image/gif", "image/jpeg", "image/pjpeg", "image/tiff", "image/x-tiff", "image/png", "image/bmp")),

        EXCEL(Arrays.asList("xlsx", "xls"),
                Arrays.asList("application/excel", "application/vnd.ms-excel", "application/x-excel", "application/x-msexcel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),

        WORD(Arrays.asList("docx", "doc", "dotx"), Arrays.asList("application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.template")),

        PDF(Arrays.asList("pdf"), Arrays.asList("application/pdf", "application/x-pdf")),

        ILLEGAL(Arrays.asList("exe", "sh", "csh", "ai"),
                Arrays.asList("application/octet-stream", "application/x-sh", "application/x-shar", "text/x-script.sh", "application/x-csh", "text/x-script.csh", "application/postscript"));

        FileType(final List<String> extList, final List<String> mimeTypeList) {
            this.extList = extList;
            this.mimeTypeList = mimeTypeList;
        }

        private List<String> extList;
        private List<String> mimeTypeList;
    }

    private static final Tika TIKA_INSTANCE = new Tika();

    // 업로드 하려는 파일의 검증(MultipartFile 이용)
    public static void validateFile(final MultipartFile multipartFile) throws CommonException {
        if (FileType.ILLEGAL.extList.contains(getExtension(multipartFile))) {
            LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_EXT.toString(), multipartFile.getOriginalFilename());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_EXT);
        }
        if (FileType.ILLEGAL.mimeTypeList.contains(getMimeType(multipartFile))) {
            LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE.toString(), multipartFile.getOriginalFilename());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE);
        }
    }

    // 업로드된 파일의 검증(File 이용)
    public static void validateFile(final File file) throws CommonException {
        if (FileType.ILLEGAL.extList.contains(getExtension(file))) {
            LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_EXT.toString(), file.getAbsolutePath());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_EXT);
        }
        if (FileType.ILLEGAL.mimeTypeList.contains(getMimeType(file))) {
            LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE.toString(), file.getAbsolutePath());
            throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE);
        }
    }

    // 업로드 하려는 파일의 검증(MultipartFile 이용)
    public static void validateFile(final MultipartFile multipartFile, final FileType fileType) throws CommonException {
        try {
            if (!fileType.extList.contains(getExtension(multipartFile))) {
                LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_EXT.toString(), multipartFile.getOriginalFilename());
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_EXT);
            }
            if (!fileType.mimeTypeList.contains(getMimeType(multipartFile))) {
                LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE.toString(), multipartFile.getOriginalFilename());
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE);
            }

            validateFile(multipartFile);
        } catch (final CommonException e) {
            throw setResultCodeByFileType(e, fileType);
        }
    }

    // 업로드된 파일의 검증(File 이용)
    public static void validateFile(final File file, final FileType fileType) throws CommonException {
        try {
            if (!fileType.extList.contains(getExtension(file))) {
                LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_EXT.toString(), file.getAbsolutePath());
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_EXT);
            }
            if (!fileType.mimeTypeList.contains(getMimeType(file))) {
                LOGGER.warn("{}{}", CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE.toString(), file.getAbsolutePath());
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE);
            }

            validateFile(file);
        } catch (final CommonException e) {
            throw setResultCodeByFileType(e, fileType);
        }
    }

    private static CommonException setResultCodeByFileType(final CommonException e, final FileType fileType) {
        if (FileType.IMAGE.equals(fileType)) {
            if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_EXT)) {
                return new CommonException(CommonExceptionCode.ERROR_FAIL_IMAGE_EXT);
            } else if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE)) {
                return new CommonException(CommonExceptionCode.ERROR_FAIL_IMAGE_MIMETYPE);
            }
        } else if (FileType.EXCEL.equals(fileType)) {
            if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_EXT)) {
                return new CommonException(CommonExceptionCode.ERROR_FAIL_EXCEL_EXT);
            } else if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE)) {
                return new CommonException(CommonExceptionCode.ERROR_FAIL_EXCEL_MIMETYPE);
            }
        } else if (FileType.WORD.equals(fileType)) {
            if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_EXT)) {
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_WORD_EXT);
            } else if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE)) {
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_WORD_MIMETYPE);
            }
        } else if (FileType.PDF.equals(fileType)) {
            if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_EXT)) {
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_PDF_EXT);
            } else if (e.isEquals(CommonExceptionCode.ERROR_FAIL_FILE_MIMETYPE)) {
                throw new CommonException(CommonExceptionCode.ERROR_FAIL_PDF_MIMETYPE);
            }
        }
        return e;
    }

    public static boolean isFileType(final MultipartFile multipartFile, final FileType fileType) throws CommonException {
        return fileType.extList.contains(getExtension(multipartFile)) && fileType.mimeTypeList.contains(getMimeType(multipartFile));
    }

    public static boolean isFileType(final File file, final FileType fileType) throws CommonException {
        return fileType.extList.contains(getExtension(file)) && fileType.mimeTypeList.contains(getMimeType(file));
    }

    public static String getMimeType(final MultipartFile multipartFile) throws CommonException {
        try {
            if (multipartFile == null) {
                return null;
            }
            return TIKA_INSTANCE.detect(multipartFile.getBytes()).toLowerCase();
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static String getMimeType(final File file) throws CommonException {
        try {
            if (file == null) {
                return null;
            }
            return TIKA_INSTANCE.detect(file).toLowerCase();
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static String getFullName(final MultipartFile multipartFile) throws CommonException {
        if (multipartFile == null) {
            return null;
        }
        return getFullName(multipartFile.getOriginalFilename());
    }

    public static String getFullName(final File file) {
        if (file == null) {
            return null;
        }
        return getFullName(file.getName());
    }

    public static String getFullName(final String fileName) {
        return FilenameUtils.getName(fileName);
    }

    public static String getBaseName(final MultipartFile multipartFile) throws CommonException {
        if (multipartFile == null) {
            return null;
        }
        return getBaseName(multipartFile.getOriginalFilename());
    }

    public static String getBaseName(final File file) {
        if (file == null) {
            return null;
        }
        return getBaseName(file.getName());
    }

    public static String getBaseName(final String fileName) {
        return FilenameUtils.getBaseName(fileName);
    }

    public static String getExtension(final MultipartFile multipartFile) throws CommonException {
        if (multipartFile == null) {
            return null;
        }
        return getExtension(multipartFile.getOriginalFilename());
    }

    public static String getExtension(final File file) {
        if (file == null) {
            return null;
        }
        return getExtension(file.getName());
    }

    public static String getExtension(final String fileName) {
        return FilenameUtils.getExtension(fileName).toLowerCase(Locale.getDefault());
    }
}
