/*****************************************************************************
 * 프로그램명  : RealmDAO.java
 * 설     명  : REALM사업자정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.realm;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * REALM사업자정보관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class RealmDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = RealmDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * REALM사업자정보관리 목록 갯수
     *
     * @param RealmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getRealmInfoListCount(final RealmSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getRealmInfoListCount", searchInfo);
    }

    /**
     * REALM사업자정보 목록
     *
     * @param RealmSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<RealmInfo> getRealmInfoList(final RealmSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getRealmInfoList", searchInfo);
    }

    /**
     * REALM사업자정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertRealm(final RealmInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertRealmInfo", info);
        return new ProcessResult(this.CLASS_NAME, "insertRealm", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * REALM사업자관리 상세 정보
     *
     * @param userId 검색REALM사업자아이디
     * @return User REALM사업자정보 상세보기
     */

    public RealmInfo getRealmDetail(final RealmInfo info) throws UserSysException {
        return (RealmInfo) this.sqlSession.selectOne(NS + "getRealmDetail", info);
    }

    /**
     * REALM사업자정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateRealm(final RealmInfo info) throws UserSysException {
        this.sqlSession.update(NS + "doUpdateRealmInfo", info);
        return new ProcessResult(this.CLASS_NAME, "updateRealm", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * REALM사업자정보 삭제처리
     *
     * @param userInfo 삭제할REALM사업자아이디
     */

    public ProcessResult deleteRealm(final String inParam) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteRealmInfo", inParam);
        return new ProcessResult(this.CLASS_NAME, "deleteRealm", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Realm 존재 체크
     *
     * @param realm SBC Node Name
     * @return boolean (true:realm가 존재하지 않을때,false:realm가 존재할때)
     */

    public String getRealmExist(final String realm) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getRealmExist", realm);
    }
}
