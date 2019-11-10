/*****************************************************************************
 * 프로그램명  : LinksetDAO.java
 * 설     명  : Linkset 현황 관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.27   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.linkset;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Linkset 현황 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class LinksetDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = LinksetDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Linkset 현황 목록 갯수
     *
     * @param LinksetSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getLinksetListCnt(final LinksetSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getLinksetInfoListCount", searchInfo);
    }

    /**
     * Linkset 목록
     *
     * @param LinksetSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<LinksetInfo> getLinksetInfoList(final LinksetSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getLinksetInfoList", searchInfo);
    }

    /**
     * Linkset 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertLinkset(final LinksetInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertLinkset", info);
        return new ProcessResult(this.CLASS_NAME, "insertLinkset", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Linkset 현황 상세 정보
     *
     * @param LinksetInfo Link
     * @return User Linkset 정보 상세보기
     */

    public LinksetInfo getLinksetDetail(final LinksetInfo info) throws UserSysException {
        return (LinksetInfo) this.sqlSession.selectOne(NS + "getLinksetDetail", info);
    }

    /**
     * Linkset 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateLinkset(final LinksetInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateLinkset", info);
        return new ProcessResult(this.CLASS_NAME, "updateLinkset", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Linkset 정보 삭제처리
     *
     * @param Linkset
     */

    public ProcessResult deleteLinkset(final LinksetInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteLinkset", info);
        return new ProcessResult(this.CLASS_NAME, "deleteLinkset", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Linkset 존재 체크
     *
     * @param Linkset
     * @return boolean (true:Linkset존재하지 않을때,false:Linkset존재할때)
     */

    public String getLinksetExist(final LinksetInfo info) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getLinksetExist", info);
    }
}
