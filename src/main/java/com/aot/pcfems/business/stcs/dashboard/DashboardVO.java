/*****************************************************************************
 * 프로그램명 : Dashboard.java 설 명 : 통계관리 DASHBOARD 조회 검색조건 DataBean 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2018.02.26 KYM 1.0 초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.dashboard;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 통계관리 DASHBOARD 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class DashboardVO implements Serializable {
    private transient static final long serialVersionUID = 6066199294872711206L;
    /**
     * 그룹id
     */
    private String dsbd_group_id = "";
    /**
     * dashboard 그룹
     */
    private String dsbd_group_name = "";
    /**
     * 메뉴유형
     */
    private String index_type = "";
    /**
     * 정렬순번
     */
    private String sortseq = "";

    /**
     * dashboard item 고유id
     */
    private String dsbd_item_id = "";
    /**
     * 아이템(항목) 이름
     */
    private String dsbd_item_name = "";
    /**
     * 아이템유형(bargraph, linegraph, image, title)
     */
    private String dsbd_item_type = "";
    /**
     * 아이템의 너비
     */
    private String dsbd_item_width = "";
    /**
     * 아이템의 높이
     */
    private String dsbd_item_height = "";

    /**
     * 데이터 이름(라인의 경우 라인이름, bar 의 경우 bar 이름)
     */
    private String data_name = "";
    /**
     * 발생일시(라인그래프의 경우 x축에 해당하는 시간값(yyyymmddhh24miss)이 들어감.)
     */
    private String xval = "";
    /**
     * Y축 값
     */
    private String yval = "";

    /**
     * 데이터 이름(라인의 경우 라인이름, bar 의 경우 bar 이름) 설정
     *
     * @param data_name 데이터 이름(라인의 경우 라인이름, bar 의 경우 bar 이름)
     */
    public void setData_name(final String data_name) {
        this.data_name = data_name;
    }

    /**
     * 데이터 이름(라인의 경우 라인이름, bar 의 경우 bar 이름) 취득
     *
     * @return data_name 데이터 이름(라인의 경우 라인이름, bar 의 경우 bar 이름)
     */
    public String getData_name() {
        return StringUtils.defaultString(this.data_name);
    }

    /**
     * 발생일시(라인그래프의 경우 x축에 해당하는 시간값(yyyymmddhh24miss)이 들어감.) 설정
     *
     * @param xval 발생일시(라인그래프의 경우 x축에 해당하는 시간값(yyyymmddhh24miss)이 들어감.)
     */
    public void setXval(final String xval) {
        this.xval = xval;
    }

    /**
     * 발생일시(라인그래프의 경우 x축에 해당하는 시간값(yyyymmddhh24miss)이 들어감.) 취득
     *
     * @return xval 발생일시(라인그래프의 경우 x축에 해당하는 시간값(yyyymmddhh24miss)이 들어감.)
     */
    public String getXval() {
        return StringUtils.defaultString(this.xval);
    }

    /**
     * Y축 값 설정
     *
     * @param yval Y축 값
     */
    public void setYval(final String yval) {
        this.yval = yval;
    }

    /**
     * Y축 값 취득
     *
     * @return yval Y축 값
     */
    public String getYval() {
        return StringUtils.defaultString(this.yval);
    }

    /**
     * dashboard item 고유id 설정
     *
     * @param dsbd_item_id dashboard item 고유id
     */
    public void setDsbd_item_id(final String dsbd_item_id) {
        this.dsbd_item_id = dsbd_item_id;
    }

    /**
     * dashboard item 고유id 취득
     *
     * @return dsbd_item_id dashboard item 고유id
     */
    public String getDsbd_item_id() {
        return StringUtils.defaultString(this.dsbd_item_id);
    }

    /**
     * 아이템(항목) 이름 설정
     *
     * @param dsbd_item_name 아이템(항목) 이름
     */
    public void setDsbd_item_name(final String dsbd_item_name) {
        this.dsbd_item_name = dsbd_item_name;
    }

    /**
     * 아이템(항목) 이름 취득
     *
     * @return dsbd_item_name 아이템(항목) 이름
     */
    public String getDsbd_item_name() {
        return StringUtils.defaultString(this.dsbd_item_name);
    }

    /**
     * 아이템유형(bargraph, linegraph, image, title) 설정
     *
     * @param dsbd_item_type 아이템유형(bargraph, linegraph, image, title)
     */
    public void setDsbd_item_type(final String dsbd_item_type) {
        this.dsbd_item_type = dsbd_item_type;
    }

    /**
     * 아이템유형(bargraph, linegraph, image, title) 취득
     *
     * @return dsbd_item_type 아이템유형(bargraph, linegraph, image, title)
     */
    public String getDsbd_item_type() {
        return StringUtils.defaultString(this.dsbd_item_type);
    }

    /**
     * 아이템의 너비 설정
     *
     * @param dsbd_item_width 아이템의 너비
     */
    public void setDsbd_item_width(final String dsbd_item_width) {
        this.dsbd_item_width = dsbd_item_width;
    }

    /**
     * 아이템의 너비 취득
     *
     * @return dsbd_item_width 아이템의 너비
     */
    public String getDsbd_item_width() {
        return StringUtils.defaultString(this.dsbd_item_width);
    }

    /**
     * 아이템의 높이 설정
     *
     * @param dsbd_item_height 아이템의 높이
     */
    public void setDsbd_item_height(final String dsbd_item_height) {
        this.dsbd_item_height = dsbd_item_height;
    }

    /**
     * 아이템의 높이 취득
     *
     * @return dsbd_item_height 아이템의 높이
     */
    public String getDsbd_item_height() {
        return StringUtils.defaultString(this.dsbd_item_height);
    }

    /**
     * 그룹id 설정
     *
     * @param dsbd_group_id 그룹id
     */
    public void setDsbd_group_id(final String dsbd_group_id) {
        this.dsbd_group_id = dsbd_group_id;
    }

    /**
     * 그룹id 취득
     *
     * @return dsbd_group_id 그룹id
     */
    public String getDsbd_group_id() {
        return StringUtils.defaultString(this.dsbd_group_id);
    }

    /**
     * dashboard 그룹 설정
     *
     * @param dsbd_group_name dashboard 그룹
     */
    public void setDsbd_group_name(final String dsbd_group_name) {
        this.dsbd_group_name = dsbd_group_name;
    }

    /**
     * dashboard 그룹 취득
     *
     * @return dsbd_group_name dashboard 그룹
     */
    public String getDsbd_group_name() {
        return StringUtils.defaultString(this.dsbd_group_name);
    }

    /**
     * 메뉴유형 설정
     *
     * @param index_type 메뉴유형
     */
    public void setIndex_type(final String index_type) {
        this.index_type = index_type;
    }

    /**
     * 메뉴유형 취득
     *
     * @return index_type 메뉴유형
     */
    public String getIndex_type() {
        return StringUtils.defaultString(this.index_type);
    }

    /**
     * 정렬순번 설정
     *
     * @param sortseq 정렬순번
     */
    public void setSortseq(final String sortseq) {
        this.sortseq = sortseq;
    }

    /**
     * 정렬순번 취득
     *
     * @return sortseq 정렬순번
     */
    public String getSortseq() {
        return StringUtils.defaultString(this.sortseq);
    }

}