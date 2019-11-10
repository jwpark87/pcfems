package com.aot.pcfems.business.tablevo.emsmvmstatus;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.tablevo.SqlForTableDAO;
import com.aot.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableEmsmVmStatusDAO extends SqlForTableDAO {
    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableEmsmVmStatusVO> getList(final TableEmsmVmStatusVO vo, final List<String> whereKey, final String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableEmsmVmStatusVO getOne(final TableEmsmVmStatusVO vo, final List<String> whereKey) throws CommonException;
}
