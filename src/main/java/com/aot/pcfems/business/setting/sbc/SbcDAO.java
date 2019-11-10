/*****************************************************************************
 * 프로그램명  : SbcDAO.java
 * 설     명  : SBC설정정보관리 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2017.12.21   LHN     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.setting.sbc;

import com.aot.pcfems.common.exception.UserSysException;
import com.aot.pcfems.common.model.ProcessResult;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SBC설정정보관리 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class SbcDAO {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String CLASS_NAME = this.getClass().getName();
    // end must be point .
    private static final String NS = SbcDAO.class.getPackage().getName() + ".";
    @Autowired
    private SqlSession sqlSession;

    /**
     * SBC설정정보관리 목록 갯수
     *
     * @param SbcSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public int getSbcListCnt(final SbcSearchInfo searchInfo) throws UserSysException {
        return (Integer) this.sqlSession.selectOne(NS + "getSbcInfoListCount", searchInfo);
    }

    /**
     * SBC설정정보 목록
     *
     * @param SbcSearchInfo 검색데이타
     * @return List 일반정보리스트
     */

    public List<SbcInfo> getSbcInfoList(final SbcSearchInfo searchInfo) throws UserSysException {
        return this.sqlSession.selectList(NS + "getSbcInfoList", searchInfo);
    }

    /**
     * SBC설정정보 등록처리
     *
     * @param User 입력데이타
     */

    public ProcessResult insertSbc(final SbcInfo info) throws UserSysException {
        this.sqlSession.insert(NS + "doInsertSbcInfo", info);
        return new ProcessResult(this.CLASS_NAME, "doInsertSbcInfo", 1, AotMessageUtils.getMessage("MSG.INSERT.SUCCESS"));
    }

    /**
     * SBC설정관리 상세 정보
     *
     * @param userId 검색SBC설정아이디
     * @return User SBC설정정보 상세보기
     */

    public SbcInfo getSbcDetail(final SbcInfo info) throws UserSysException {
        return (SbcInfo) this.sqlSession.selectOne(NS + "getSbcDetail", info);
    }

    /**
     * SBC설정정보 수정처리
     *
     * @param User 입력데이타
     */

    public ProcessResult updateSbc(final SbcInfo info) throws UserSysException {
        this.sqlSession.update(NS + "doUpdateSbcInfo", info);
        return new ProcessResult(this.CLASS_NAME, "updateSbc", 1, AotMessageUtils.getMessage("MSG.UPDATE.SUCCESS"));
    }

    /**
     * SBC설정정보 삭제처리
     *
     * @param userInfo 삭제할SBC설정아이디
     */

    public ProcessResult deleteSbc(final String inParam) throws UserSysException {
        this.sqlSession.delete(NS + "doDeleteSbcInfo", inParam);
        return new ProcessResult(this.CLASS_NAME, "deleteSbc", 1, AotMessageUtils.getMessage("MSG.DELETE.SUCCESS"));
    }

    /**
     * SBC Node Name 존재 체크
     *
     * @param sbc_node_name SBC Node Name
     * @return boolean (true:SBC Node Name이 존재하지 않을때,false:SBC Node Name이 존재할때)
     */

    public String getSbcNodeNameExist(final String sbc_node_name) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getSbcNodeNameExist", sbc_node_name);
    }

    /**
     * SBC Node Ip 존재 체크
     *
     * @param sbc_node_ip SBC Node Ip
     * @return boolean (true:SBC Node Ip가 존재하지 않을때,false:SBC Node Ip가 존재할때)
     */

    public String getSbcNodeIpExist(final String sbc_node_ip) throws UserSysException {
        return (String) this.sqlSession.selectOne(NS + "getSbcNodeIpExist", sbc_node_ip);
    }

}
