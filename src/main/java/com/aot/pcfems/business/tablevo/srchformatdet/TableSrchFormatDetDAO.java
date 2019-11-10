package com.aot.pcfems.business.tablevo.srchformatdet;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.tablevo.SqlForTableDAO;
import com.aot.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableSrchFormatDetDAO extends SqlForTableDAO {
    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSrchFormatDetVO> getList(final TableSrchFormatDetVO vo, List<String> whereKey, String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSrchFormatDetVO getOne(final TableSrchFormatDetVO vo, List<String> whereKey) throws CommonException;
}
