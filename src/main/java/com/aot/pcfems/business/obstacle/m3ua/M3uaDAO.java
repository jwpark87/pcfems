/*****************************************************************************
 * 프로그램명  : M3uaDAO.java
 * 설     명  : M2PA/M3UA 현황 관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.m3ua;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * M2PA/M3UA 현황 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class M3uaDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = M3uaDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * M2PA/M3UA 현황 목록 갯수
     *
     * @param M3uaSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getM3uaListCnt(final M3uaSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getM3uaInfoListCount", searchInfo);
    }

    /**
     * M2PA/M3UA 현황 목록
     *
     * @param M3uaSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<M3uaInfo> getM3uaInfoList(final M3uaSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getM3uaInfoList", searchInfo);
    }

    /**
     * M2PA/M3UA 현황 정보 등록처리
     *
     * @param info 입력데이타
     */

    public ProcessResult insertM3ua(final M3uaInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertM3ua", info);
        return new ProcessResult(this.CLASS_NAME, "insertM3ua", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * M2PA/M3UA 현황 상세 정보
     *
     * @param M3uaInfo Link
     * @return M3ua 정보 상세보기
     */

    public M3uaInfo getM3uaDetail(final M3uaInfo info) throws UserSysException {
        return (M3uaInfo) this.sqlSession.selectOne(NS + "getM3uaDetail", info);
    }

    /**
     * M2PA/M3UA 현황 정보 수정처리
     *
     * @param M3ua 입력데이타
     */

    public ProcessResult updateM3ua(final M3uaInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateM3ua", info);
        return new ProcessResult(this.CLASS_NAME, "updateM3ua", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * M2PA/M3UA 현황 정보 삭제처리
     *
     * @param M3ua
     */

    public ProcessResult deleteM3ua(final M3uaInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteM3ua", info);
        return new ProcessResult(this.CLASS_NAME, "deleteM3ua", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * M2PA/M3UA 현황 존재 체크
     *
     * @param M3ua
     * @return boolean
     */

    public String getM3uaExist(final M3uaInfo info) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getM3uaExist", info);
    }
}
