/*****************************************************************************
 * 프로그램명  : DiameterDAO.java
 * 설     명  : Diameter Host 정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.02   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.diameter;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Diameter Host 관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class DiameterDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = DiameterDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Diameter 관리 목록 갯수
     *
     * @param DiameterSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getDiameterInfoListCount(final DiameterSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getDiameterInfoListCount", searchInfo);
    }

    /**
     * Diameter 목록
     *
     * @param DiameterSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<DiameterInfo> getDiameterInfoList(final DiameterSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getDiameterInfoList", searchInfo);
    }

    /**
     * Diameter 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertDiameter(final DiameterInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertDiameter", info);
        return new ProcessResult(this.CLASS_NAME, "insertDiameter", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Diameter 관리 상세 정보
     *
     * @param DiameterInfo Diameter
     * @return User Diameter 정보 상세보기
     */

    public DiameterInfo getDiameterDetail(final DiameterInfo info) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getDiameterDetail", info);
    }

    /**
     * Diameter 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateDiameter(final DiameterInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateDiameter", info);
        return new ProcessResult(this.CLASS_NAME, "updateDiameter", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Diameter 정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteDiameter(final DiameterInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteDiameter", info);
        return new ProcessResult(this.CLASS_NAME, "deleteDiameter", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Diameter 존재 체크
     *
     * @param Diameter
     * @return boolean (true:Diameter존재하지 않을때,false:Diameter존재할때)
     */

    public String getDiameterExist(final String diameter) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getDiameterExist", diameter);
    }
}
