/*****************************************************************************
 * 프로그램명 : DashboardDAO.java 설 명 : 진행 중 DASHBOARD 조회 persistence-layer class. 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2018.02.26 KYM 1.0 초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.dashboard;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 통계관리 DASHBOARD 조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class DashboardDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = DashboardDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * 대시보드 그룹 정보 취득
     *
     * @param ObstacleDashboardSearchInfo 검색데이타
     * @return List Dashboard 그룹 리스트
     */

    public List<DashboardVO> getDashboardGrpList(final DashboardSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getDashboardGrpList", searchInfo);
    }

    /**
     * 대시보드 아이템 정보 취득
     *
     * @param ObstacleDashboardSearchInfo 검색데이타
     * @return List Dashboard 아이템 리스트
     */

    public List<DashboardVO> getDashboardItemList(final DashboardSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getDashboardItemList", searchInfo);
    }

    /**
     * 대시보드 아이템 데이타 정보 취득
     *
     * @param ObstacleDashboardSearchInfo 검색데이타
     * @return List Dashboard 아이템 리스트
     */

    public List<DashboardVO> getDashboardItemDataList(final DashboardSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getDashboardItemDataList", searchInfo);
    }

}
