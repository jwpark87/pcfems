/*****************************************************************************
 * 프로그램명  : CountryDAO.java
 * 설     명  : COUNTRY설정정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.country;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * COUNTRY설정정보관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class CountryDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = CountryDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * COUNTRY설정정보관리 목록 갯수
     *
     * @param CountrySearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getCountryListCnt(final CountrySearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getCountryInfoListCount", searchInfo);
    }

    /**
     * COUNTRY설정정보 목록
     *
     * @param CountrySearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<CountryInfo> getCountryInfoList(final CountrySearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getCountryInfoList", searchInfo);
    }

    /**
     * COUNTRY설정정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertCountry(final CountryInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertCountryInfo", info);
        return new ProcessResult(this.CLASS_NAME, "insertCountry", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * COUNTRY설정관리 상세 정보
     *
     * @param userId 검색COUNTRY설정아이디
     * @return User COUNTRY설정정보 상세보기
     */

    public CountryInfo getCountryDetail(final CountryInfo info) throws UserSysException {
        return (CountryInfo) this.sqlSession.selectOne(NS + "getCountryDetail", info);
    }

    /**
     * COUNTRY설정정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateCountry(final CountryInfo info) throws UserSysException {
        this.sqlSession.update(NS + "doUpdateCountryInfo", info);
        return new ProcessResult(this.CLASS_NAME, "updateCountry", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * COUNTRY설정정보 삭제처리
     *
     * @param userInfo 삭제할COUNTRY설정아이디
     */

    public ProcessResult deleteCountry(final String inParam) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteCountryInfo", inParam);
        return new ProcessResult(this.CLASS_NAME, "deleteCountry", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * Prefix 존재 체크
     *
     * @param prefix SBC Node Name
     * @return boolean (true:prefix가 존재하지 않을때,false:prefix가 존재할때)
     */

    public String getPrefixExist(final String prefix) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getPrefixExist", prefix);
    }
}
