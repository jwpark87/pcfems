/*****************************************************************************
 * 프로그램명  : CodeServiceImpl.java
 * 설     명  : 코드정보관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.24  LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.common.spring.code;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 코드정보관리 비즈니스 로직
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class CodeService {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CodeDAO codeDAO;

    /**
     * 코드 리스트 취득
     *
     * @return List 코드리스트를 담은 객체
     */

    // public List<GroupCodeInfo> getCodeInfo(final Locale locale) throws UserSysException {
    // // 코드의 총갯수를 취득
    // return this.codeDAO.getCodeInfo(StringUtil.getCommonPrefix(locale));
    // }

    /**
     * 상위가 있는 코드 리스트 취득
     *
     * @return List 상위가 있는 코드리스트를 담은 객체
     */

    // public List<GroupCodeInfo> getUpCodeInfo() throws UserSysException {
    // // 코드를 취득
    // return this.codeDAO.getUpCodeInfo();
    // }

    /**
     * 특정 사용자그룹에 속한 사용자 리스트를 취득
     *
     * @param code 특정사용자그룹
     * @return List 사용자 리스트를 담은 객체
     */

    // public List<CodeInfo> getGroupUser(final String code) throws UserSysException {
    // return this.codeDAO.getGroupUser(code);
    // }
    public List<CodeInfo> getCodeListByGrcode(final String grcode) throws UserSysException {
        return this.codeDAO.getCodeListByGrcode(grcode);
    }

    public List<CodeInfo> getAllCodeListByGrcode(final String grcode) throws UserSysException {
        return this.codeDAO.getAllCodeListByGrcode(grcode);
    }

}