/*****************************************************************************
 * 프로그램명  : ChartServiceImpl.java
 * 설     명  : 진행 중 CHART 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.util.PagingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 진행 중 CHART 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ChartService {
    @Resource
    private ChartDAO chartDAO;

    /**
     * Realm정보 취득
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Realm정보리스트(코드용)
     */

    public List<CodeInfo> getRealmCodeList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.chartDAO.getRealmCodeList(searchInfo);
    }

    /**
     * 호처리 조회 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil 호처리 조회 리스트
     */

    public PagingUtil getChartCallProcessList(final ChartSearchInfo chartSearchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getChartCallProcessListCnt(chartSearchInfo), this.chartDAO.getChartCallProcessList(chartSearchInfo));
    }

    /**
     * 호처리 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List 호처리 CHART 리스트
     */

    public List<List<List<Object>>> getCallGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        final List<List<List<Object>>> rtn_dt = new ArrayList<>();
        final List<List<Object>> try_call = new ArrayList<>();
        final List<List<Object>> conn_call = new ArrayList<>();
        final List<List<Object>> comp_call = new ArrayList<>();
        final List<ChartInfo> resultList = this.chartDAO.getCallGraph(searchInfo);
        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                final ChartInfo info = resultList.get(i);
                final List<Object> try_c = new ArrayList<>();
                try_c.add(info.getStcs_dt());
                try_c.add(Integer.parseInt(info.getTry_call_cnt()));
                try_call.add(try_c);
                final List<Object> conn_c = new ArrayList<>();
                conn_c.add(info.getStcs_dt());
                conn_c.add(Integer.parseInt(info.getConn_call_cnt()));
                conn_call.add(conn_c);

                final List<Object> comp_c = new ArrayList<>();
                comp_c.add(info.getStcs_dt());
                comp_c.add(Integer.parseInt(info.getComp_call_cnt()));
                comp_call.add(comp_c);
            }
            rtn_dt.add(try_call);
            rtn_dt.add(conn_call);
            rtn_dt.add(comp_call);
        }
        return rtn_dt;
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * ASR 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List ASR 보고서 리스트
     */

    public PagingUtil getChartAsrList(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getChartCallProcessListCnt(searchInfo), this.chartDAO.getChartAsrList(searchInfo));
    }

    /**
     * ASR 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List ASR 보고서 CHART 리스트
     */

    public List<List<List<Object>>> getAsrGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        final List<List<List<Object>>> rtn_dt = new ArrayList<>();
        // 평균사용시간
        final List<List<Object>> avg_use_time = new ArrayList<>();
        // 평균PDD
        final List<List<Object>> avg_pdd = new ArrayList<>();
        // 평균 CALLING R-FACTOR
        final List<List<Object>> avg_calling_r_factor = new ArrayList<>();
        // 평균 CALLED R-FACTOR
        final List<List<Object>> avg_called_r_factor = new ArrayList<>();
        // 평균 CALLING Packet Loss
        final List<List<Object>> avg_calling_rtp_packets_lost_fs1 = new ArrayList<>();
        // 평균 CALLED Packet Loss
        final List<List<Object>> avg_called_rtp_packets_lost_fs1 = new ArrayList<>();

        final List<ChartInfo> resultList = this.chartDAO.getAsrGraph(searchInfo);

        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                final ChartInfo info = resultList.get(i);

                final List<Object> avg_use_time_c = new ArrayList<>();
                avg_use_time_c.add(info.getStcs_dt());
                avg_use_time_c.add(Double.parseDouble(info.getAvg_use_time()));
                avg_use_time.add(avg_use_time_c);

                final List<Object> avg_pdd_c = new ArrayList<>();
                avg_pdd_c.add(info.getStcs_dt());
                avg_pdd_c.add(Double.parseDouble(info.getAvg_pdd()));
                avg_pdd.add(avg_pdd_c);

                final List<Object> avg_calling_r_factor_c = new ArrayList<>();
                avg_calling_r_factor_c.add(info.getStcs_dt());
                avg_calling_r_factor_c.add(Double.parseDouble(info.getAvg_calling_r_factor()));
                avg_calling_r_factor.add(avg_calling_r_factor_c);

                final List<Object> avg_called_r_factor_c = new ArrayList<>();
                avg_called_r_factor_c.add(info.getStcs_dt());
                avg_called_r_factor_c.add(Double.parseDouble(info.getAvg_called_r_factor()));
                avg_called_r_factor.add(avg_called_r_factor_c);

                final List<Object> avg_calling_rtp_packets_lost_fs1_c = new ArrayList<>();
                avg_calling_rtp_packets_lost_fs1_c.add(info.getStcs_dt());
                avg_calling_rtp_packets_lost_fs1_c.add(Double.parseDouble(info.getAvg_calling_rtp_packets_lost_fs1()));
                avg_calling_rtp_packets_lost_fs1.add(avg_calling_rtp_packets_lost_fs1_c);

                final List<Object> avg_called_rtp_packets_lost_fs1_c = new ArrayList<>();
                avg_called_rtp_packets_lost_fs1_c.add(info.getStcs_dt());
                avg_called_rtp_packets_lost_fs1_c.add(Double.parseDouble(info.getAvg_called_rtp_packets_lost_fs1()));
                avg_called_rtp_packets_lost_fs1.add(avg_called_rtp_packets_lost_fs1_c);

            }
            rtn_dt.add(avg_use_time);
            rtn_dt.add(avg_pdd);
            rtn_dt.add(avg_calling_r_factor);
            rtn_dt.add(avg_called_r_factor);
            rtn_dt.add(avg_calling_rtp_packets_lost_fs1);
            rtn_dt.add(avg_called_rtp_packets_lost_fs1);
        }
        return rtn_dt;
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * NER 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List NER 보고서 리스트
     */

    public PagingUtil getChartNerList(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getChartCallProcessListCnt(searchInfo), this.chartDAO.getChartNerList(searchInfo));
    }

    /**
     * NER 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List NER 보고서 CHART 리스트
     */

    public List<List<List<Object>>> getNerGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        final List<List<List<Object>>> rtn_dt = new ArrayList<>();
        // 총통화시간
        final List<List<Object>> acct_session_time = new ArrayList<>();
        // 평균통화시간
        final List<List<Object>> avg_call_time = new ArrayList<>();
        // 평균PDD
        final List<List<Object>> avg_pdd = new ArrayList<>();

        final List<ChartInfo> resultList = this.chartDAO.getNerGraph(searchInfo);

        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                final ChartInfo info = resultList.get(i);

                final List<Object> acct_session_time_c = new ArrayList<>();
                acct_session_time_c.add(info.getStcs_dt());
                acct_session_time_c.add(Double.parseDouble(info.getAcct_session_time()));
                acct_session_time.add(acct_session_time_c);

                final List<Object> avg_call_time_c = new ArrayList<>();
                avg_call_time_c.add(info.getStcs_dt());
                avg_call_time_c.add(Double.parseDouble(info.getAvg_call_time()));
                avg_call_time.add(avg_call_time_c);

                final List<Object> avg_pdd_c = new ArrayList<>();
                avg_pdd_c.add(info.getStcs_dt());
                avg_pdd_c.add(Double.parseDouble(info.getAvg_pdd()));
                avg_pdd.add(avg_pdd_c);
            }
            rtn_dt.add(acct_session_time);
            rtn_dt.add(avg_call_time);
            rtn_dt.add(avg_pdd);
        }
        return rtn_dt;
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * QOS 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List QOS 보고서 리스트
     */

    public PagingUtil getChartQosList(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getChartCallProcessListCnt(searchInfo), this.chartDAO.getChartQosList(searchInfo));
    }

    /**
     * QOS 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List QOS 보고서 CHART 리스트
     */

    public List<List<List<Object>>> getQosGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        final List<List<List<Object>>> rtn_dt = new ArrayList<>();
        // 평균PDD
        final List<List<Object>> avg_pdd = new ArrayList<>();
        // 평균사용시간
        final List<List<Object>> avg_call_time = new ArrayList<>();
        // 평균 calling Jitter
        final List<List<Object>> avg_calling_jitter = new ArrayList<>();
        // 평균 Called Jitter
        final List<List<Object>> avg_called_jitter = new ArrayList<>();
        // 평균 CALLING R-FACTOR
        final List<List<Object>> avg_calling_r_factor = new ArrayList<>();
        // 평균 CALLED R-FACTOR
        final List<List<Object>> avg_called_r_factor = new ArrayList<>();
        // 평균 CALLING Packet Loss
        final List<List<Object>> avg_calling_rtp_packets_lost_fs1 = new ArrayList<>();
        // 평균 CALLED Packet Loss
        final List<List<Object>> avg_called_rtp_packets_lost_fs1 = new ArrayList<>();

        final List<ChartInfo> resultList = this.chartDAO.getQosGraph(searchInfo);

        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                final ChartInfo info = resultList.get(i);

                final List<Object> avg_pdd_c = new ArrayList<>();
                avg_pdd_c.add(info.getStcs_dt());
                avg_pdd_c.add(Double.parseDouble(info.getAvg_pdd()));
                avg_pdd.add(avg_pdd_c);

                final List<Object> avg_call_time_c = new ArrayList<>();
                avg_call_time_c.add(info.getStcs_dt());
                avg_call_time_c.add(Double.parseDouble(info.getAvg_call_time()));
                avg_call_time.add(avg_call_time_c);

                final List<Object> avg_calling_jitter_c = new ArrayList<>();
                avg_calling_jitter_c.add(info.getStcs_dt());
                avg_calling_jitter_c.add(Double.parseDouble(info.getAvg_calling_jitter()));
                avg_calling_jitter.add(avg_calling_jitter_c);

                final List<Object> avg_called_jitter_c = new ArrayList<>();
                avg_called_jitter_c.add(info.getStcs_dt());
                avg_called_jitter_c.add(Double.parseDouble(info.getAvg_called_jitter()));
                avg_called_jitter.add(avg_called_jitter_c);

                final List<Object> avg_calling_r_factor_c = new ArrayList<>();
                avg_calling_r_factor_c.add(info.getStcs_dt());
                avg_calling_r_factor_c.add(Double.parseDouble(info.getAvg_calling_r_factor()));
                avg_calling_r_factor.add(avg_calling_r_factor_c);

                final List<Object> avg_called_r_factor_c = new ArrayList<>();
                avg_called_r_factor_c.add(info.getStcs_dt());
                avg_called_r_factor_c.add(Double.parseDouble(info.getAvg_called_r_factor()));
                avg_called_r_factor.add(avg_called_r_factor_c);

                final List<Object> avg_calling_rtp_packets_lost_fs1_c = new ArrayList<>();
                avg_calling_rtp_packets_lost_fs1_c.add(info.getStcs_dt());
                avg_calling_rtp_packets_lost_fs1_c.add(Double.parseDouble(info.getAvg_calling_rtp_packets_lost_fs1()));
                avg_calling_rtp_packets_lost_fs1.add(avg_calling_rtp_packets_lost_fs1_c);

                final List<Object> avg_called_rtp_packets_lost_fs1_c = new ArrayList<>();
                avg_called_rtp_packets_lost_fs1_c.add(info.getStcs_dt());
                avg_called_rtp_packets_lost_fs1_c.add(Double.parseDouble(info.getAvg_called_rtp_packets_lost_fs1()));
                avg_called_rtp_packets_lost_fs1.add(avg_called_rtp_packets_lost_fs1_c);

            }
            rtn_dt.add(avg_pdd);
            rtn_dt.add(avg_call_time);
            rtn_dt.add(avg_calling_jitter);
            rtn_dt.add(avg_called_jitter);
            rtn_dt.add(avg_calling_r_factor);
            rtn_dt.add(avg_called_r_factor);
            rtn_dt.add(avg_calling_rtp_packets_lost_fs1);
            rtn_dt.add(avg_called_rtp_packets_lost_fs1);
        }
        return rtn_dt;
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /**
     * Peer별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Peer별 통계 리스트
     */

    public PagingUtil getPeerStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getPeerStatisticCnt(searchInfo), this.chartDAO.getPeerStatistic(searchInfo));
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /**
     * Gt별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Gt별 통계 리스트
     */

    public PagingUtil getGtStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getGtStatisticCnt(searchInfo), this.chartDAO.getGtStatistic(searchInfo));
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /**
     * Realm 별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Realm 별 통계 리스트
     */

    public PagingUtil getRealmStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getRealmStatisticCnt(searchInfo), this.chartDAO.getRealmStatistic(searchInfo));
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/
    /**
     * Linkset 별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Linkset 별 통계 리스트
     */

    public PagingUtil getLinksetStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.chartDAO.getLinksetStatisticCnt(searchInfo), this.chartDAO.getLinksetStatistic(searchInfo));
    }

}
