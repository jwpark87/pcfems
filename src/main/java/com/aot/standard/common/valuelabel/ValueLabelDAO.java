package com.aot.standard.common.valuelabel;

import com.aot.standard.common.exception.CommonException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ValueLabelDAO {
    @Autowired
    private SqlSession sqlSession;
    // end must be point .
    private static final String NS = ValueLabelDAO.class.getPackage().getName() + ".";

    public List<ValueLabelVO> getValueLabelItemsToList(final String grcode) throws CommonException {
        return this.sqlSession.selectList(NS + "getValueLabelItemsToList", grcode);
    }

    public String getLabelByValue(final Map<String, Object> params) throws CommonException {
        return this.sqlSession.selectOne(NS + "getLabelByValue", params);
    }
}
