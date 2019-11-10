/*****************************************************************************
 * 프로그램명  : LoginDAO.java
 * 설     명  : ȸ��회원로그인 관리 DAO 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.02  LYS      1.0    초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.threshold;

import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.standard.common.exception.CommonException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ȸ���� ȸ��ȸ��회원로그인 관리 DAO 구현객체
 *
 * @author eaction
 * @version 1.0
 */
@Repository
public class StcsThresholdDAO {
    // end must be point .
    private static final String NS = StcsThresholdDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    public Integer getStcsThresholdCnt(final StcsThresholdVO stcsThresholdVO) throws CommonException {
        return this.sqlSession.selectOne(NS + "getStcsThresholdCnt", stcsThresholdVO);
    }

    public List<TableEmsmVmStatusVO> getStcsThresholdList(final StcsThresholdVO stcsThresholdVO) throws CommonException {
        return this.sqlSession.selectList(NS + "getStcsThresholdList", stcsThresholdVO);
    }
}
