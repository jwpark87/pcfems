/*****************************************************************************
 * 프로그램명  : LoginDAO.java
 * 설     명  : ȸ��회원로그인 관리 DAO 구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.10.02  LYS      1.0    초기작성
 *****************************************************************************/
package com.aot.pcfems.business.stcs.format;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.valuelabel.ValueLabelVO;
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
public class StcsFormatDAO {
    // end must be point .
    private static final String NS = StcsFormatDAO.class.getPackage().getName() + ".";

    @Autowired
    private SqlSession sqlSession;

    public Integer getSrchFormatMstCnt(final StcsFormatVO stcsFormatVO) throws CommonException {
        return this.sqlSession.selectOne(NS + "getSrchFormatMstCnt", stcsFormatVO);
    }

    public List<StcsFormatVO> getSrchFormatMstList(final StcsFormatVO stcsFormatVO) throws CommonException {
        return this.sqlSession.selectList(NS + "getSrchFormatMstList", stcsFormatVO);
    }

    public Integer getSrchFormatDetCnt(final StcsFormatVO stcsFormatVO) throws CommonException {
        return this.sqlSession.selectOne(NS + "getSrchFormatDetCnt", stcsFormatVO);
    }

    public List<StcsFormatVO> getSrchFormatDetList(final StcsFormatVO stcsFormatVO) throws CommonException {
        return this.sqlSession.selectList(NS + "getSrchFormatDetList", stcsFormatVO);
    }

    public List<ValueLabelVO> getGrcodeValueLabelList() throws CommonException {
        return this.sqlSession.selectList(NS + "getGrcodeValueLabelList");
    }
}
