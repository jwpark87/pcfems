/*****************************************************************************
 * 프로그램명  : LoginServiceImpl.java
 * 설     명  : ȸ��회원 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.24   LYS    1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.threshold;

import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusDAO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.pcfems.common.jqGrid.JqGridUtil;
import com.aot.pcfems.common.util.PagingUtil;
import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * ȸ�� 회원 정보 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
public class StcsThresholdService {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 로그인 처리 DAO
     */
    @Resource
    private StcsThresholdDAO stcsThresholdDAO;
    @Autowired
    private TableEmsmVmStatusDAO tableEmsmVmStatusDAO;

    public JsonObject getStcsThresholdList(final StcsThresholdVO stcsThresholdVO) throws CommonException {
        if (stcsThresholdVO.getJqFilters() != null && !stcsThresholdVO.getJqFilters().isJsonNull() && stcsThresholdVO.getJqFilters().get("rules") != null) {
            final JsonArray arr = stcsThresholdVO.getJqFilters().get("rules").getAsJsonArray();
            if (arr != null) {
                for (final JsonElement jsonElement : arr) {
                    final JsonObject jo = jsonElement.getAsJsonObject();
                    if (jo != null && !jo.isJsonNull()) {
                        if (jo.get("field").getAsString().equals("svr_id")) {
                            stcsThresholdVO.setSvr_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("node_name")) {
                            stcsThresholdVO.setNode_name(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("ha_status")) {
                            stcsThresholdVO.setHa_status(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("alm_status")) {
                            stcsThresholdVO.setAlm_status(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("host_name")) {
                            stcsThresholdVO.setHost_name(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("use_yn")) {
                            stcsThresholdVO.setUse_yn(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("hw_name")) {
                            stcsThresholdVO.setHw_name(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("vnfc_instance_id")) {
                            stcsThresholdVO.setVnfc_instance_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("crt_id")) {
                            stcsThresholdVO.setCrt_id(jo.get("data").getAsString());
                        }
                        if (jo.get("field").getAsString().equals("upd_id")) {
                            stcsThresholdVO.setUpd_id(jo.get("data").getAsString());
                        }
                    }
                }
            }
        }

        final JsonObject result = JqGridUtil.getJsonData(stcsThresholdVO,
                new PagingUtil(this.stcsThresholdDAO.getStcsThresholdCnt(stcsThresholdVO), this.stcsThresholdDAO.getStcsThresholdList(stcsThresholdVO)));
        result.add("SEARCH_DATA", AotMapperUtils.writeObjectAsJsonElement(stcsThresholdVO));
        return result;
    }

    public void insertEmsmVmStatus(final TableEmsmVmStatusVO tableEmsmVmStatusVO) {
        this.tableEmsmVmStatusDAO.insert(tableEmsmVmStatusVO);
    }

    public void updateEmsmVmStatus(final TableEmsmVmStatusVO tableEmsmVmStatusVO) {
        this.tableEmsmVmStatusDAO.update(tableEmsmVmStatusVO, Arrays.asList("svr_id", "node_name"), Arrays.asList("vnfc_instance_id"));
    }

    public void deleteEmsmVmStatus(final TableEmsmVmStatusVO tableEmsmVmStatusVO) throws CommonException {
        this.tableEmsmVmStatusDAO.delete(tableEmsmVmStatusVO, Arrays.asList("svr_id", "node_name"));
    }

}
