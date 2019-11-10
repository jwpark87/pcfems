/*****************************************************************************
 * 프로그램명  : BoardFile.java
 * 설     명  : ȸ��첨부파일  DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * 게시판의 첨부파일 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class BoardFile implements Serializable {
    private static final long serialVersionUID = 3223719491530243168L;

    /**
     * 파일번호
     */
    private int fileNo;
    /**
     * 파일이름
     */
    private String fileName;
    /**
     * 임시파일이름
     */
    private String tempFileName;
    /**
     * 파일 크기
     */
    private long fileSize;
    /**
     * 파일 타입
     */
    private String contentType;

    /**
     * 파일목록
     */
    private List<BoardFile> file_list = null;
    /**
     * 전체경로
     */
    private String full_path = "";

    public int getFileNo() {
        return this.fileNo;
    }

    public void setFileNo(final int fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getTempFileName() {
        return this.tempFileName;
    }

    public void setTempFileName(final String tempFileName) {
        this.tempFileName = tempFileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(final long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public List<BoardFile> getFile_list() {
        return this.file_list;
    }

    public void setFile_list(final List<BoardFile> file_list) {
        this.file_list = file_list;
    }

    public String getFull_path() {
        return this.full_path;
    }

    public void setFull_path(final String full_path) {
        this.full_path = full_path;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
