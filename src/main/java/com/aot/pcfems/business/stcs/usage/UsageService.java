/*****************************************************************************
 * 프로그램명  : UsageServiceImpl.java
 * 설     명  : 통계관리>사용량추이(Cpu,Mem,Interface) 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.27   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.usage;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.CodeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 사용량추이화면 비즈니스로직
 *
 * @author aot
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UsageService {
    @Resource
    private final UsageDAO usageDAO = null;

    /**
     * SBC정보 취득
     *
     * @param UsageSearchInfo 검색데이타
     * @return List SBC정보리스트(코드용)
     */

    public List<CodeInfo> getSbcCodeInfoList(final UsageSearchInfo searchInfo) throws UserSysException {
        return this.usageDAO.getSbcCodeInfoList(searchInfo);
    }

}