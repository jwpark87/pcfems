/*****************************************************************************
 * 프로그램명  : Dashboard.java
 * 설     명  : 통계관리 DASHBOARD 조회 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.26   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.dashboard;

import java.io.Serializable;

/**
 * 통계관리 DASHBOARD 조회 데이터 빈
 *
 * @author eaction
 * @version 1.0
 */
public class ObstacleDashboardVO implements Serializable {
    private transient static final long serialVersionUID = 6066199294872711206L;

    private String res_type;
    private String rtgrp_node;
    private Integer graph_value;

    public String getRes_type() {
        return this.res_type;
    }

    public void setRes_type(final String res_type) {
        this.res_type = res_type;
    }

    public String getRtgrp_node() {
        return this.rtgrp_node;
    }

    public void setRtgrp_node(final String rtgrp_node) {
        this.rtgrp_node = rtgrp_node;
    }

    public Integer getGraph_value() {
        return this.graph_value;
    }

    public void setGraph_value(final Integer graph_value) {
        this.graph_value = graph_value;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}