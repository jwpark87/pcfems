/*****************************************************************************
 * 프로그램명  : LoginDAO.java
 * 설     명  : ȸ��회원로그인 관리 DAO 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.02  LYS      1.0    초기작성
 *****************************************************************************/
package com.aot.pcfems.business.login;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ȸ���� ȸ��ȸ��회원로그인 관리 DAO 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class LoginDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = LoginDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    /**
     * ȸ�� 회원 정보 취득
     *
     * @param paramUserInfo 회원 정보
     * @throws BizException     업무 예외
     * @throws UserSysException 시스템 예외
     */

    public UserVO getUserInfo(final UserVO paramUserInfo) throws BizException, UserSysException {
        return (UserVO) this.sqlSession.selectOne(NS + "getUserInfo", paramUserInfo);
    }

    /**
     * ȸ�� 로그인/로그아웃시 로그 생성
     *
     * @param str 입력한 비밀번호
     * @return String 암호화된 비밀번호
     */

    public ProcessResult setConnLog(final String inParam, final UserVO user) throws BizException, UserSysException {
        this.sqlSession.insert(NS + "setConnLog", user);
        return new ProcessResult(this.CLASS_NAME, "setConnLogs", 0, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }
}
