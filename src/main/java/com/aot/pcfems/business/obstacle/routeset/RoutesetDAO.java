/*****************************************************************************
 * 프로그램명  : RoutesetDAO.java
 * 설     명  : Routeset 현황 관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.routeset;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Routeset 현황 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class RoutesetDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = RoutesetDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Routeset 현황 목록 갯수
     *
     * @param RoutsetSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getRoutesetInfoListCount(final RoutesetSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getRoutesetInfoListCount", searchInfo);
    }

    /**
     * Routeset 목록
     *
     * @param RoutsetSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<RoutesetInfo> getRoutesetInfoList(final RoutesetSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getRoutesetInfoList", searchInfo);
    }

    /**
     * Routeset 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertRouteset(final RoutesetInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertRouteset", info);
        return new ProcessResult(this.CLASS_NAME, "insertRouteset", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Routeset 현황 상세 정보
     *
     * @param RoutsetInfo Routeset
     * @return User Routeset 정보 상세보기
     */

    public RoutesetInfo getRoutesetDetail(final RoutesetInfo info) throws UserSysException {
        return (RoutesetInfo) this.sqlSession.selectOne(NS + "getRoutesetDetail", info);
    }

    /**
     * Routeset 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateRouteset(final RoutesetInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateRouteset", info);
        return new ProcessResult(this.CLASS_NAME, "updateRouteset", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Routeset 정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteRouteset(final RoutesetInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteRouteset", info);
        return new ProcessResult(this.CLASS_NAME, "deleteRouteset", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Routeset 존재 체크
     *
     * @param Routeset
     * @return boolean (true:Routeset존재하지 않을때,false:Routeset존재할때)
     */

    public String getRoutesetExist(final String diameter) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getRoutesetExist", diameter);
    }
}
