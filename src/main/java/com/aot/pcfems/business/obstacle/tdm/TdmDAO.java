/*****************************************************************************
 * 프로그램명  : TdmDAO.java
 * 설     명  : Tdm 현황 관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.03   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.tdm;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Tdm 현황 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class TdmDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = TdmDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Tdm 현황 목록 갯수
     *
     * @param TdmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getTdmListCnt(final TdmSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getTdmInfoListCount", searchInfo);
    }

    /**
     * Tdm 목록
     *
     * @param TdmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<TdmInfo> getTdmInfoList(final TdmSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getTdmInfoList", searchInfo);
    }

    /**
     * Tdm 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertTdm(final TdmInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertTdm", info);
        return new ProcessResult(this.CLASS_NAME, "insertTdm", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Tdm 현황 상세 정보
     *
     * @param TdmInfo Link
     * @return Tdm 정보 상세보기
     */

    public TdmInfo getTdmDetail(final TdmInfo info) throws UserSysException {
        return (TdmInfo) this.sqlSession.selectOne(NS + "getTdmDetail", info);
    }

    /**
     * Tdm 정보 수정처리
     *
     * @param Tdm 입력데이타
     */

    public ProcessResult updateTdm(final TdmInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateTdm", info);
        return new ProcessResult(this.CLASS_NAME, "updateTdm", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Tdm 정보 삭제처리
     *
     * @param Tdm
     */

    public ProcessResult deleteTdm(final TdmInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteTdm", info);
        return new ProcessResult(this.CLASS_NAME, "deleteTdm", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Tdm 존재 체크
     *
     * @param Tdm
     * @return boolean
     */

    public String getTdmExist(final TdmInfo info) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getTdmExist", info);
    }
}
