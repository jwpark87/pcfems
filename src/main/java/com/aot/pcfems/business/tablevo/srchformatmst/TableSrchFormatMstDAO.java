package com.aot.pcfems.business.tablevo.srchformatmst;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.tablevo.SqlForTableDAO;
import com.aot.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableSrchFormatMstDAO extends SqlForTableDAO {
    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableSrchFormatMstVO> getList(final TableSrchFormatMstVO vo, List<String> whereKey, String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableSrchFormatMstVO getOne(final TableSrchFormatMstVO vo, List<String> whereKey) throws CommonException;
}
