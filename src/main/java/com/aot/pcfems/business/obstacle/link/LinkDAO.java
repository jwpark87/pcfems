/*****************************************************************************
 * 프로그램명  : LinkDAO.java
 * 설     명  : Link 현황 관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.link;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Link 현황 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class LinkDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = LinkDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Link 현황 목록 갯수
     *
     * @param LinkSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getLinkListCnt(final LinkSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getLinkListCnt", searchInfo);
    }

    /**
     * Link 목록
     *
     * @param LinkSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<LinkInfo> getLinkInfoList(final LinkSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getLinkInfoList", searchInfo);
    }

    /**
     * Link 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertLink(final LinkInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertLink", info);
        return new ProcessResult(this.CLASS_NAME, "insertLink", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Link 현황 상세 정보
     *
     * @param LinkInfo Link
     * @return User Link 정보 상세보기
     */

    public LinkInfo getLinkDetail(final LinkInfo info) throws UserSysException {
        return (LinkInfo) this.sqlSession.selectOne(NS + "getLinkDetail", info);
    }

    /**
     * Link 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateLink(final LinkInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateLink", info);
        return new ProcessResult(this.CLASS_NAME, "updateLink", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Link 정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteLink(final LinkInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteLink", info);
        return new ProcessResult(this.CLASS_NAME, "deleteLink", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Link 존재 체크
     *
     * @param Link
     * @return boolean (true:Link존재하지 않을때,false:Link존재할때)
     */

    public String getLinkExist(final LinkInfo info) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getLinkExist", info);
    }
}
