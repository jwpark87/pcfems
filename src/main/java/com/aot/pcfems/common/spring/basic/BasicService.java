/*****************************************************************************
 * 프로그램명  : BasicServiceImpl.java
 * 설     명  : 언어처리정보관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.29  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.basic;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 언어처리정보관리 비즈니스 로직
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class BasicService {
    @Resource
    private BasicDAO basicDAO;

    /**
     * 화면별오브젝트접근권한정보 리스트 취득
     *
     * @return List AccessInfo화면별오브젝트접근권한정보리스트
     */

    public List<AccessInfo> getAccessInfo() throws UserSysException {
        return this.basicDAO.getAccessInfo(StringUtil.getCommonPrefix());
    }

}