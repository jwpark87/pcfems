/*****************************************************************************
 * 프로그램명  : ChartDAO.java
 * 설     명  : 진행 중 CHART 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.chart;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 통계관리 CHART 조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class ChartDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = ChartDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Realm정보 취득
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Realm정보리스트(코드용)
     */

    public List<CodeInfo> getRealmCodeList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getRealmCodeList", searchInfo);
    }

    /**
     * 호처리 CHART 조회 목록 갯수
     *
     * @param ChartSearchInfo 검색데이타
     * @return int 호처리 리스트 갯수
     */

    public int getChartCallProcessListCnt(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getChartCallProcessListCnt", searchInfo);
    }

    /**
     * 호처리 CHART 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List 호처리 리스트
     */

    public List<ChartInfo> getChartCallProcessList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getChartCallProcessList", searchInfo);
    }

    /**
     * 호처리 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List 호처리 CHART 리스트
     */

    public List<ChartInfo> getCallGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCallGraph", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * ASR 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List ASR 보고서 리스트
     */

    public List<ChartInfo> getChartAsrList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getChartAsrList", searchInfo);
    }

    /**
     * ASR 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List ASR 보고서 CHART 리스트
     */

    public List<ChartInfo> getAsrGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getAsrGraph", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * NER 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List NER 보고서 리스트
     */

    public List<ChartInfo> getChartNerList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getChartNerList", searchInfo);
    }

    /**
     * NER 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List NER 보고서 CHART 리스트
     */

    public List<ChartInfo> getNerGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getNerGraph", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * QOS 보고서 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List QOS 보고서 리스트
     */

    public List<ChartInfo> getChartQosList(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getChartQosList", searchInfo);
    }

    /**
     * QOS 보고서 CHART 조회
     *
     * @param ChartSearchInfo 검색데이타
     * @return List QOS 보고서 CHART 리스트
     */

    public List<ChartInfo> getQosGraph(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getQosGraph", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * Peer별 통계 조회 목록 갯수
     *
     * @param ChartSearchInfo 검색데이타
     * @return int Peer별 통계 리스트 갯수
     */

    public int getPeerStatisticCnt(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getPeerStatisticCnt", searchInfo);
    }

    /**
     * Peer별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Peer별 통계 리스트
     */

    public List<ChartInfo> getPeerStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getPeerStatistic", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * Gt별 통계 조회 목록 갯수
     *
     * @param ChartSearchInfo 검색데이타
     * @return int Gt별 통계 리스트 갯수
     */

    public int getGtStatisticCnt(final ChartSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getGtStatisticCnt", searchInfo);
    }

    /**
     * Gt별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Gt별 통계 리스트
     */

    public List<ChartInfo> getGtStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getGtStatistic", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * Realm별 통계 조회 목록 갯수
     *
     * @param ChartSearchInfo 검색데이타
     * @return int Realm 별 통계 리스트 갯수
     */

    public int getRealmStatisticCnt(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getRealmStatisticCnt", searchInfo);
    }

    /**
     * Realm별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Realm 별 통계 리스트
     */

    public List<ChartInfo> getRealmStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getRealmStatistic", searchInfo);
    }

    /*****************************************************************************************************************************************************/
    /*****************************************************************************************************************************************************/

    /**
     * Linkset 별 통계 조회 목록 갯수
     *
     * @param ChartSearchInfo 검색데이타
     * @return int Linkset 별 통계 리스트 갯수
     */

    public int getLinksetStatisticCnt(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getLinksetStatisticCnt", searchInfo);
    }

    /**
     * Linkset 별 통계 조회 목록
     *
     * @param ChartSearchInfo 검색데이타
     * @return List Linkset 별 통계 리스트
     */

    public List<ChartInfo> getLinksetStatistic(final ChartSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getLinksetStatistic", searchInfo);
    }
}
