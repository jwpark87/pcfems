/*****************************************************************************
 * 프로그램명  : BasicDAO.java
 * 설     명  : 언어별처리정보관리 DB처리 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.29   LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.spring.basic;

import com.aot.pcfems.common.exception.UserSysException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 언어별처리정보관리 DB처리 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class BasicDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // end must be point .
    private static final String NS = BasicDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * 화면표시타이틀정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과화면표시정보리스트
     */

    public List<ObjectInfo> getObjectInfo(final String inParam) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetObjectInfo", inParam.split("=")[1]);
    }

    /**
     * 화면표시탭정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과탭리스트정보
     */

    public List<TabInfo> getTabInfo(final String inParam) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetTabInfo", inParam.split("=")[1]);

    }

    /**
     * 화면별객체의접근권한정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과권한정보리스트정보
     */

    public List<AccessInfo> getAccessInfo(final String inParam) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetAccessInfo");

    }

    /**
     * 시스템파라메터 정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과파라메터리스트정보
     */

    public List<ParamInfo> getParamInfo(final String inParam) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetParamInfo");
    }

    /**
     * 페이지별정보및 메뉴매핑정보 취득
     *
     * @param inParam 입력파라메터
     * @return List 결과화면표시정보리스트
     */

    public List<MenuPageInfo> getMenuPageInfo(final String inParam) throws UserSysException {
        return this.sqlSession.selectList(NS + "doGetMenuPageInfo", inParam.split("=")[1]);
    }

}
