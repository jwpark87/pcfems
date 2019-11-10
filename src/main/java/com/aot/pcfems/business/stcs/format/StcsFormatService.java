/*****************************************************************************
 * 프로그램명  : LoginServiceImpl.java
 * 설     명  : ȸ��회원 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.24   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.format;

import com.aot.pcfems.business.tablevo.srchformatdet.TableSrchFormatDetDAO;
import com.aot.pcfems.business.tablevo.srchformatdet.TableSrchFormatDetVO;
import com.aot.pcfems.business.tablevo.srchformatmst.TableSrchFormatMstDAO;
import com.aot.pcfems.business.tablevo.srchformatmst.TableSrchFormatMstVO;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotMapperUtils;
import com.aot.standard.common.valuelabel.ValueLabelVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * ȸ�� 회원 정보 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class StcsFormatService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 로그인 처리 DAO
     */
    @Resource
    private StcsFormatDAO stcsFormatDAO;
    @Autowired
    private TableSrchFormatMstDAO tableSrchFormatMstDAO;
    @Autowired
    private TableSrchFormatDetDAO tableSrchFormatDetDAO;

    public JsonObject getSrchFormatMstList(final StcsFormatVO stcsFormatVO) throws CommonException {
        if (stcsFormatVO.getJqFilters() != null && !stcsFormatVO.getJqFilters().isJsonNull() && stcsFormatVO.getJqFilters().get("rules") != null) {
            final JsonArray arr = stcsFormatVO.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo != null && !jo.isJsonNull()) {
                        if (jo.get("field").getAsString().equals("index_type")) {
                            stcsFormatVO.setIndex_type(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("format_name")) {
                            stcsFormatVO.setFormat_name(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("es_base_url")) {
                            stcsFormatVO.setEs_base_url(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("def_col_id")) {
                            stcsFormatVO.setDef_col_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("def_fromdt")) {
                            stcsFormatVO.setDef_fromdt(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("def_todt")) {
                            stcsFormatVO.setDef_todt(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("crt_id")) {
                            stcsFormatVO.setCrt_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("upd_id")) {
                            stcsFormatVO.setUpd_id(jo.get("data").getAsString());
                        }
                    }
                }
            }
        }

        final JsonObject result = JqGridUtil.getJsonData(stcsFormatVO, new PagingUtil(this.stcsFormatDAO.getSrchFormatMstCnt(stcsFormatVO), this.stcsFormatDAO.getSrchFormatMstList(stcsFormatVO)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(stcsFormatVO));
        return result;
    }

    public void insertSrchFormatMst(final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.setOldest_dt(DateTime.now());
        stcsFormatVO.setNewest_dt(DateTime.now());
        this.tableSrchFormatMstDAO.insert(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatMstVO.class));
    }

    public void updateSrchFormatMst(final StcsFormatVO stcsFormatVO) {
        this.tableSrchFormatMstDAO.update(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatMstVO.class), Arrays.asList("index_type"), null);
    }

    @Transactional
    public void deleteSrchFormatMst(final StcsFormatVO stcsFormatVO) throws CommonException {
        try {
            this.tableSrchFormatDetDAO.delete(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatDetVO.class), Arrays.asList("index_type"));
        } catch (final CommonException e) {
            if (!e.isExceptionNoDataSuccesss()) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                throw e;
            }
        }
        this.tableSrchFormatMstDAO.delete(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatMstVO.class), Arrays.asList("index_type"));
    }

    public JsonObject getSrchFormatDetList(final StcsFormatVO stcsFormatVO) throws CommonException {
        if (stcsFormatVO.getJqFilters() != null && !stcsFormatVO.getJqFilters().isJsonNull() && stcsFormatVO.getJqFilters().get("rules") != null) {
            final JsonArray arr = stcsFormatVO.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo != null && !jo.isJsonNull()) {
                        if (jo.get("field").getAsString().equals("col_cd")) {
                            stcsFormatVO.setCol_cd(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("col_nm")) {
                            stcsFormatVO.setCol_nm(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("text_align")) {
                            stcsFormatVO.setText_align(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("grcode")) {
                            stcsFormatVO.setGrcode(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("search_yn")) {
                            stcsFormatVO.setSearch_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("sortseq")) {
                            stcsFormatVO.setSortseq(jo.get("data").getAsShort());
                        }
                        if (jo.get("field").getAsString().equals("use_yn")) {
                            stcsFormatVO.setUse_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("threshold_yn")) {
                            stcsFormatVO.setThreshold_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("op_code")) {
                            stcsFormatVO.setOp_code(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("op_value")) {
                            stcsFormatVO.setOp_value(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_code")) {
                            stcsFormatVO.setAlm_code(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_msg")) {
                            stcsFormatVO.setAlm_msg(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("remark1")) {
                            stcsFormatVO.setRemark1(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("crt_id")) {
                            stcsFormatVO.setCrt_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("upd_id")) {
                            stcsFormatVO.setUpd_id(jo.get("data").getAsString());
                        }
                    }
                }
            }
        }
        final JsonObject result = JqGridUtil.getJsonData(stcsFormatVO, new PagingUtil(this.stcsFormatDAO.getSrchFormatDetCnt(stcsFormatVO), this.stcsFormatDAO.getSrchFormatDetList(stcsFormatVO)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(stcsFormatVO));
        return result;
    }

    public void insertSrchFormatDet(final StcsFormatVO stcsFormatVO) {
        stcsFormatVO.setOldest_dt(DateTime.now());
        stcsFormatVO.setNewest_dt(DateTime.now());
        this.tableSrchFormatDetDAO.insert(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatDetVO.class));
    }

    public void updateSrchFormatDet(final StcsFormatVO stcsFormatVO) {
        this.tableSrchFormatDetDAO.update(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatDetVO.class), Arrays.asList("index_type", "col_cd"), Arrays.asList("**"));
    }

    public void deleteSrchFormatDet(final StcsFormatVO stcsFormatVO) throws CommonException {
        this.tableSrchFormatDetDAO.delete(AotMapperUtils.writeObjectAsObject(stcsFormatVO, TableSrchFormatDetVO.class), Arrays.asList("index_type", "col_cd"));
    }

    public List<ValueLabelVO> getGrcodeValueLabelList() throws CommonException {
        return this.stcsFormatDAO.getGrcodeValueLabelList();
    }

}
