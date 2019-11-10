/*****************************************************************************
 * 프로그램명  : DiameterRealmDAO.java
 * 설     명  : Diameter Realm 정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.04.28   KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.diameterrealm;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Diameter Realm 관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class DiameterRealmDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = DiameterRealmDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * Diameter Realm 관리 목록 갯수
     *
     * @param DiameterRealmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getDiameterRealmInfoListCount(final DiameterRealmSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectOne(NS + "getDiameterRealmInfoListCount", searchInfo);
    }

    /**
     * Diameter Realm 목록
     *
     * @param DiameterRealmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<DiameterRealmInfo> getDiameterRealmInfoList(final DiameterRealmSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getDiameterRealmInfoList", searchInfo);
    }

    /**
     * Diameter Realm 정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertDiameterRealm(final DiameterRealmInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "insertDiameterRealm", info);
        return new ProcessResult(this.CLASS_NAME, "insertDiameterRealm", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * Diameter Realm 관리 상세 정보
     *
     * @param DiameterRealmInfo Diameter
     * @return User Diameter 정보 상세보기
     */

    public DiameterRealmInfo getDiameterRealmDetail(final DiameterRealmInfo info) throws UserSysException {
        return (DiameterRealmInfo) this.sqlSession.selectOne(NS + "getDiameterRealmDetail", info);
    }

    /**
     * Diameter Realm 정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateDiameterRealm(final DiameterRealmInfo info) throws UserSysException {
        this.sqlSession.update(NS + "updateDiameterRealm", info);
        return new ProcessResult(this.CLASS_NAME, "updateDiameterRealm", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * Diameter Realm 정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteDiameterRealm(final DiameterRealmInfo info) throws UserSysException {
        this.sqlSession.delete(NS + "deleteDiameterRealm", info);
        return new ProcessResult(this.CLASS_NAME, "deleteDiameterRealm", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Diameter Realm 존재 체크
     *
     * @param DiameterRealm
     * @return boolean (true:Diameter존재하지 않을때,false:Diameter존재할때)
     */

    public String getDiameterRealmExist(final String diameter) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getDiameterRealmExist", diameter);
    }
}
