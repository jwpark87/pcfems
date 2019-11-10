package com.aot.pcfems.business.tablevo.emsmconnstatus;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.tablevo.SqlForTableDAO;
import com.aot.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableEmsmConnStatusDAO extends SqlForTableDAO {
    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableEmsmConnStatusVO> getList(final TableEmsmConnStatusVO vo, List<String> whereKey, String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableEmsmConnStatusVO getOne(final TableEmsmConnStatusVO vo, List<String> whereKey) throws CommonException;
}
