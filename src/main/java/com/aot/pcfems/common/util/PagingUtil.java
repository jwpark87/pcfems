/*****************************************************************************
 * 프로그램명  : PagingTable.java
 * 설     명  : ȸ��공통부품/Utillity
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23    LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * 출력리스트페이지클래스
 *
 * @author eaction
 * @version 1.0
 */
public class PagingUtil {
    /**
     * 리스트싸이즈의기본값
     */
    private static final int BOARD_DEFAULT_PAGESIZE = 20;
    /**
     * 리스트페이지셋싸이즈의기본값
     */
    private static final int BOARD_DEFAULT_BIGPAGESIZE = 10;
    /**
     * 전체리스트싸이즈레코드수
     */
    private int totalSize;
    /**
     * 페이징단위(Ibatis용)
     */
    private int pageSize;
    /**
     * 전체페이지수 (totalSize/pageSize)
     */
    private int totalPage;
    /**
     * 현재페이지번호
     */
    private int currPage;
    /**
     * 현재페이지에서 취득한 레코드수(Ibatis용)
     */
    private int currPageRowCount;
    /**
     * 개시페이지
     */
    private int sIndex;
    /**
     * 종료페이지
     */
    private int eIndex;
    /**
     * 페이지셋싸이즈
     */
    private int bigPageSize;
    /**
     * 페이지셋수
     */
    private int bigTotalPage;
    /**
     * 페이지셋의현재위치
     */
    private int bigCurrPage;
    /**
     * 페이지셋의개시페이지
     */
    private int sBigIndex;
    /**
     * 페이지셋의종료페이지
     */
    private int eBigIndex;
    /**
     * 페이징된 리스트
     */
    private List<?> currList;
    /**
     * 페이지항번수
     */
    private int count;
    /**
     * 개시번호(Ibatis용)
     */
    private int startIndex;
    /**
     * 종료페이지번호(Ibatis용)
     */
    private int endIndex;
    /**
     * 검색조건데이터BEAN（업무별검색조건이다른경우에대한 OBJECT객체를 선언)
     */
    private Object objSearch = null;
    /**
     * 코드종별
     */
    private int kind;

    /**
     * 전체레코드카운트
     */
    private int nTotal;

    @Value("#{enviromentProperties['BOARD_4_4_PAGE_SIZE']}")
    private int BOARD_4_4_PAGE_SIZE;
    @Value("#{enviromentProperties['BOARD_4_4_PAGESET_SIZE']}")
    private int BOARD_4_4_PAGESET_SIZE;
    @Value("#{enviromentProperties['BOARD_5_5_PAGE_SIZE']}")
    private int BOARD_5_5_PAGE_SIZE;
    @Value("#{enviromentProperties['BOARD_5_5_PAGESET_SIZE']}")
    private int BOARD_5_5_PAGESET_SIZE;

    public int getNTotal() {

        return this.nTotal;
    }

    public void setNTotal() {

        this.nTotal = this.currList.size();

    }

    /**
     * 페이지기본값 초기화
     */
    public PagingUtil() {
        this.setDataZeroValue();
    }

    // jqGrid작업하면서 추가 by bestheroz 130515

    /**
     * 페이지기본값 초기화
     */
    public PagingUtil(final int total, final List<?> list) {
        this.totalSize = total;
        this.setCurrList(list);
    }

    /**
     * 페이지기본값 초기화
     *
     * @param keyBoard 게시판의종류(IPropertyKey에정의되어있는값을 지정한다
     * @param nTotal   전체레코드수
     * @param cPage    현재페이지위치
     */
    public PagingUtil(final String keyBoard, final int nTotal, final int cPage) {
        this.setDefaultInformation(keyBoard, nTotal, cPage);
    }

    /**
     * 페이지기본값 초기화
     *
     * @param keyBoard   게시판의종류(IPropertyKey에정의되어있는값을 지정한다
     * @param nTotal     전체레코드수
     * @param cPage      현재페이지위치
     * @param searchInfo 검색조건클래스
     */
    public PagingUtil(final String keyBoard, final int nTotal, final int cPage, final Object searchInfo) {
        this.setDefaultInformation(keyBoard, nTotal, cPage);
        this.setObjSearch(searchInfo);
    }

    /**
     * 기본페이지정보를 설정한다
     *
     * @param keyBoard 게시판의종류(IPropertyKey에정의되어있는값을 지정한다
     * @param nTotal   전체레코드수
     * @param cPage    현재페이지위치
     */
    private void setDefaultInformation(final String keyBoard, final int nTotal, final int cPage) {
        int cPageSize;
        int cBigPageSize;
        if (StringUtils.equals(keyBoard, "BOARD_4_4_")) {
            cPageSize = this.BOARD_4_4_PAGE_SIZE;
            cBigPageSize = this.BOARD_4_4_PAGESET_SIZE;
        } else if (StringUtils.equals(keyBoard, "BOARD_5_5_")) {
            cPageSize = this.BOARD_5_5_PAGE_SIZE;
            cBigPageSize = this.BOARD_5_5_PAGESET_SIZE;
        } else {
            cPageSize = BOARD_DEFAULT_PAGESIZE;
            cBigPageSize = BOARD_DEFAULT_BIGPAGESIZE;
        }
        this.setPagingTable(nTotal, cPageSize, cPage, cBigPageSize);
    }

    /**
     * 취득하는대상페이지레코드만취득한다
     *
     * @param nTotal       전체레코드수
     * @param cPageSize    취득하는페이지단위
     * @param cPage        현재페이지
     * @param cBigPageSize 페이지셋싸이즈
     */
    public void setPagingTable(final int nTotal, final int cPageSize, final int cPage, final int cBigPageSize) {
        if (nTotal == 0) {
            this.setDataZeroValue();
        } else {
            this.setPageValue(nTotal, cPageSize, cPage);
            this.setBigValue(cBigPageSize);
        }
    }

    /**
     * 데이터가존재하지 않는경우 기본설정
     */
    private void setDataZeroValue() {
        this.totalSize = 0; // 전체싸이즈(전체레코드)
        this.pageSize = 0; // 페이징단위
        this.totalPage = 0; // 전체페이지수(totalSize/pageSize)
        this.currPage = 0; // 현재페이지
        this.currPageRowCount = 0; // 현재페이지의 취득 데이터 싸이즈
        this.sIndex = 0; // 개시위치
        this.eIndex = 0; // 종료위치
        this.bigPageSize = 0; // 페이지셋싸이즈
        this.bigTotalPage = 0; // 페이지셋수
        this.bigCurrPage = 0; // 현재페이지쎗
        this.sBigIndex = 0; // 페이지셋개시
        this.eBigIndex = 0; // 페이지셋종료
        this.startIndex = 0; // 페이지개시번호
        this.endIndex = 0; // 페이지종료번호
        this.currList = new ArrayList<>(); // 페이지의레코드
    }

    /**
     * 페이지관리데이터를 설정한다
     *
     * @param vtList    취득대상레코드
     * @param cPageSize 취득하는페이지싸이즈
     * @param cPage     현재페이지번호
     */
    private void setPageValue(final int nTotal, final int cPageSize, int cPage) {
        this.pageSize = cPageSize;
        this.totalSize = nTotal;
        if (this.totalSize % this.pageSize == 0) {
            this.totalPage = this.totalSize / this.pageSize;
        } else {
            this.totalPage = this.totalSize / this.pageSize + 1;
        }
        if (cPage > this.totalPage) {
            cPage = this.totalPage;
        }
        this.currPage = cPage;
        this.currPageRowCount = this.currPage * cPageSize;

        this.sIndex = this.pageSize * (this.currPage - 1) + 1;
        this.eIndex = this.pageSize * this.currPage;

        this.count = this.totalSize - this.sIndex + 1;

        if (this.eIndex > this.totalSize) {
            this.eIndex = this.totalSize;
        }
        this.startIndex = this.sIndex;
        this.endIndex = this.eIndex;

        if (this.currPage == this.totalPage && this.totalSize % cPageSize > 0) {
            this.pageSize = this.totalSize % cPageSize;
        }
    }

    /**
     * 페이지셋관련값설정
     *
     * @param cBigPageSize 현재페이지셋번호
     */
    private void setBigValue(final int cBigPageSize) {
        this.bigPageSize = cBigPageSize;

        if (this.currPage % this.bigPageSize == 0) {
            this.bigCurrPage = this.currPage / this.bigPageSize;
        } else {
            this.bigCurrPage = this.currPage / this.bigPageSize + 1;
        }

        if (this.totalPage % this.bigPageSize == 0) {
            this.bigTotalPage = this.totalPage / this.bigPageSize;
        } else {
            this.bigTotalPage = this.totalPage / this.bigPageSize + 1;
        }

        this.sBigIndex = this.bigPageSize * (this.bigCurrPage - 1) + 1;
        this.eBigIndex = this.bigPageSize * this.bigCurrPage;

        if (this.totalPage < this.eBigIndex) {
            this.eBigIndex = this.totalPage;
        }
    }

    /*
     * 전체레코드수취득
     *
     * @return int 전체레코드수
     */
    public int getTotalSize() {

        return this.totalSize;
    }

    /*
     * 페이지단위취득
     *
     * @return int 페이지단위
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /*
     * 전체페이지수취득
     *
     * @return int 전체페이지수
     */
    public int getTotalPage() {
        return this.totalPage;
    }

    /*
     * 현재페이지취득
     *
     * @return int 현재페이지
     */
    public int getCurrPage() {
        return this.currPage;
    }

    /*
     * 페이지의데이터싸이즈취득
     *
     * @return int 페이지의데이터싸이즈
     */
    public int getCurrPageRowCount() {
        return this.currPageRowCount;
    }

    /*
     * 페이지개시위치취득
     *
     * @return int 페이지개시위치
     */
    public int getSIndex() {
        return this.sIndex;
    }

    /*
     * 페이지종료위치취득
     *
     * @return int 페이지종료위치
     */
    public int getEIndex() {
        return this.eIndex;
    }

    /*
     * 페이지셋싸이즈취득
     *
     * @return int 페이지셋싸이즈
     */
    public int getBigPageSize() {
        return this.bigPageSize;
    }

    /*
     * 전체페이지셋수취득
     *
     * @return int 전체페이지셋수
     */
    public int getBigTotalPage() {
        return this.bigTotalPage;
    }

    /*
     * 페이지셋의현재위치취득
     *
     * @return int 페이지셋의현재위치
     */
    public int getBigCurrPage() {
        return this.bigCurrPage;
    }

    /*
     * 페이지셋의개시위치취득
     *
     * @return int 페이지셋의개시위치
     */
    public int getSBigIndex() {
        return this.sBigIndex;
    }

    /*
     * 페이지셋의종료위치취득
     *
     * @return int 페이지셋의종료위치
     */
    public int getEBigIndex() {
        return this.eBigIndex;
    }

    /*
     * 페이지항번의개시번호취득
     *
     * @return int 페이지항번의개시번호
     */
    public int getCount() {
        return this.count;
    }

    /*
     * 페이징하는대상리스트설정
     *
     * @param currList 페이징하는대상리스트
     */
    public void setCurrList(final List<?> currList) {
        this.currList = currList;
    }

    /*
     * 페이징하는대상리스트취득
     *
     * @return List 페이징하는대상리스트
     */
    public List<?> getCurrList() {
        return this.currList;
    }

    /*
     * 페이지개시위치취득
     *
     * @return int 페이지개시위치
     */
    public int getStartIndex() {
        return this.startIndex;
    }

    /*
     * 페이지종료위치취득
     *
     * @return int 페이지종료위치
     */
    public int getEndIndex() {
        return this.endIndex;
    }

    /*
     * 검색조건（업무별）클래스설정
     *
     * @param objSearch 검색조건（업무별）클래스
     */
    public void setObjSearch(final Object objSearch) {
        this.objSearch = objSearch;
    }

    /**
     * 검색조건（업무별）클래스취득
     *
     * @return Object 검색조건（업무별）클래스
     */
    public Object getObjSearch() {
        return this.objSearch;
    }

    /*
     * 코드종별설정
     *
     * @param kind 코드종별
     */
    public void setKind(final int kind) {
        this.kind = kind;
    }

    /**
     * 코드종별취득
     *
     * @return kind 코드종별
     */
    public int getKind() {
        return this.kind;
    }
}
