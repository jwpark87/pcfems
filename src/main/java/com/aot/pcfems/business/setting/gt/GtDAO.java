/*****************************************************************************
 * 프로그램명  : GtDAO.java
 * 설     명  : Gt 정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.gt;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Gt 관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class GtDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = GtDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Gt 관리 목록 갯수
     *
     * @param GtSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getGtListCnt(final GtSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getGtInfoListCount", searchInfo);
    }

    /**
     * Gt 목록
     *
     * @param GtSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<GtInfo> getGtInfoList(final GtSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getGtInfoList", searchInfo);
    }

    /**
     * Gt 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertGt(final GtInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertGt", info);
        return new ProcessResult(this.CLASS_NAME, "insertGt", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Gt 관리 상세 정보
     *
     * @param GtInfo Gt
     * @return User Gt 정보 상세보기
     */

    public GtInfo getGtDetail(final GtInfo info) throws UserSysException {
        return (GtInfo) this.sqlSession.selectOne(NS + "getGtDetail", info);
    }

    /**
     * Gt 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateGt(final GtInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateGt", info);
        return new ProcessResult(this.CLASS_NAME, "updateGt", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Gt 정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteGt(final GtInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteGt", info);
        return new ProcessResult(this.CLASS_NAME, "deleteGt", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Gt 존재 체크
     *
     * @param Gt
     * @return boolean (true:Gt존재하지 않을때,false:Gt존재할때)
     */

    public String getGtExist(final String diameter) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getGtExist", diameter);
    }
}
