/*****************************************************************************
 * 프로그램명  : AuditLogServiceImpl.java
 * 설     명  : Audit Log 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.05.31   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.admin.audit;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.util.PagingUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Audit Log 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class AuditLogService {
    @Resource
    private AuditLogDAO auditLogDAO;

    /**
     * Audit Log 조회 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Audit Log 조회 리스트
     */

    public PagingUtil getAuditLogList(final AuditLogSearchInfo searchInfo) throws UserSysException {
        return new PagingUtil(this.auditLogDAO.getAuditLogListCnt(searchInfo), this.auditLogDAO.getAuditLogList(searchInfo));
    }
}
