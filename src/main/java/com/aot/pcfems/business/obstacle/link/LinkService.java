/*****************************************************************************
 * 프로그램명  :  LinkServiceImpl.java
 * 설     명  : link 현황  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.03.03   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.link;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.pcfems.common.util.PagingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Link 현황 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class LinkService {
    @Resource
    private LinkDAO linkDAO;

    /**
     * Link 정보 리스트 취득
     *
     * @param searchInfo 검색정보빈
     * @return PagingUtil Link 현황 리스트
     */

    public PagingUtil getLinkInfoList(final LinkSearchInfo searchinfo) throws UserSysException {
        return new PagingUtil(this.linkDAO.getLinkListCnt(searchinfo), this.linkDAO.getLinkInfoList(searchinfo));
    }

    /**
     * Link 정보 등록처리
     *
     * @param info Link사업자정보
     * @return ProcessResult 결과데이터
     */

    @Transactional
    public ProcessResult insertLink(final LinkInfo info) throws UserSysException {
        return this.linkDAO.insertLink(info);
    }

    /**
     * Link 상세 정보
     *
     * @param sbcId 검색Link 아이디
     * @return Realm Link 현황 상세보기
     */

    public LinkInfo getLinkDetail(final LinkInfo info) throws UserSysException {
        return this.linkDAO.getLinkDetail(info);
    }

    /**
     * Link 정보 수정처리
     *
     * @param RoutsetInfo 수정할 Link
     */

    @Transactional
    public ProcessResult updateLink(final LinkInfo sbcInfo) throws UserSysException {
        return this.linkDAO.updateLink(sbcInfo);
    }

    /**
     * Link 정보 삭제처리
     *
     * @param RoutsetInfo 삭제할Link 아이디
     */

    @Transactional
    public ProcessResult deleteLink(final LinkInfo info) throws UserSysException {
        return this.linkDAO.deleteLink(info);
    }

    /**
     * Link 존재 체크
     *
     * @param Link
     * @return boolean (true:Link가 존재하지 않을때,false:Link가 존재할때)
     */

    public String getLinkExist(final LinkInfo info) throws UserSysException {
        return "1".equals(this.linkDAO.getLinkExist(info)) ? "NG" : "OK";
    }

}
