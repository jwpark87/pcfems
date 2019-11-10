package com.aot.pcfems.business.tablevo.emsmhwconf;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.tablevo.SqlForTableDAO;
import com.aot.standard.common.tablevo.SqlForTableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface TableEmsmHwConfDAO extends SqlForTableDAO {
    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT)
    List<TableEmsmHwConfVO> getList(final TableEmsmHwConfVO vo, List<String> whereKey, String orderByColumns) throws CommonException;

    @SelectProvider(type = SqlForTableVO.class, method = SqlForTableVO.SELECT_ONE)
    TableEmsmHwConfVO getOne(final TableEmsmHwConfVO vo, List<String> whereKey) throws CommonException;
}
