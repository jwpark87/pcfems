/*****************************************************************************
 * 프로그램명  : LoginServiceImpl.java
 * 설     명  : ȸ��회원 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.24   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.login;

import com.aot.pcfems.common.exception.BizException;
import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.model.UserVO;
import com.aot.pcfems.common.util.StringUtil;
import com.aot.standard.common.util.AotDateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ȸ�� 회원 정보 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class LoginService {
    /**
     * 로그인 처리 DAO
     */
    @Resource
    private LoginDAO loginDAO;

    /**
     * ȸ�� 회원 정보 저장
     *
     * @param paramUserInfo ȸ�� 회원 정보
     * @return User 로그인 유저정보
     * @throws BizException     업무 예외
     * @throws UserSysException 시스템 예외
     */

    public UserVO getUserInfo(final UserVO paramUserInfo) throws BizException, UserSysException {
        // 회원 정보 가져오기
        return this.loginDAO.getUserInfo(paramUserInfo);
    }

    /**
     * ȸ�� 로그인/로그아웃시 로그 생성
     *
     * @param str 입력한 비밀번호
     * @return String 암호화된 비밀번호
     */

    @Transactional
    public ProcessResult setConnLog(final UserVO user, final String str) throws BizException, UserSysException {
        final String inParam = StringUtil.getCommonPrefix(user); // 환경정보

        user.setStatus(str);
        user.setLoginDate(AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD));
        user.setLoginTime(AotDateUtils.getStringNow(AotDateUtils.HHMMSS));
        user.setUpdDate(AotDateUtils.getStringNow(AotDateUtils.YYYYMMDD) + AotDateUtils.getStringNow(AotDateUtils.HHMMSS));

        return this.loginDAO.setConnLog(inParam, user);
    }
}
