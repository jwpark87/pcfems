/*****************************************************************************
 * 프로그램명  : DashboardServiceImpl.java
 * 설     명  : 진행 중 DASHBOARD 조회  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.26   KYM     1.0     초기작성
 *****************************************************************************/

package com.aot.pcfems.business.obstacle.dashboard;

import com.aot.pcfems.business.tablevo.emsmconnstatus.TableEmsmConnStatusDAO;
import com.aot.pcfems.business.tablevo.emsmconnstatus.TableEmsmConnStatusVO;
import com.aot.pcfems.business.tablevo.emsmhwconf.TableEmsmHwConfVO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusDAO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import com.aot.standard.common.util.AotMapperUtils;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * DASHBOARD 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class ObstacleDashboardService {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ObstacleDashboardDAO obstacleDashboardDAO;
    @Autowired
    private TableEmsmVmStatusDAO tableEmsmVmStatusDAO;
    @Autowired
    private TableEmsmConnStatusDAO tableEmsmConnStatusDAO;

    public List<TableEmsmHwConfVO> getEmsmHwConf() {
        return this.obstacleDashboardDAO.getEmsmHwConf();
    }

    public List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrId(final String svr_id) {
        final List<TableEmsmVmStatusVO> emsmVmStatusBySvrId = this.obstacleDashboardDAO.getEmsmVmStatusBySvrId(svr_id);
        for (final TableEmsmVmStatusVO tableEmsmVmStatusVO : emsmVmStatusBySvrId) {
            tableEmsmVmStatusVO.setCrt_dt(null);
            tableEmsmVmStatusVO.setCrt_id(null);
            tableEmsmVmStatusVO.setHw_name(null);
            tableEmsmVmStatusVO.setSortseq(null);
            tableEmsmVmStatusVO.setSvr_id(null);
            tableEmsmVmStatusVO.setUpd_dt(null);
            tableEmsmVmStatusVO.setUpd_id(null);
            tableEmsmVmStatusVO.setUse_yn(null);
        }
        return emsmVmStatusBySvrId;
    }

    public List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrIdAndHwName(final String svr_id, final String hw_name) {
        final Map<String, String> param = new HashMap<>();
        param.put("svr_id", svr_id);
        param.put("hw_name", hw_name);
        return this.obstacleDashboardDAO.getEmsmVmStatusBySvrIdAndHwName(param);
    }

    public List<String> getResTypeList(final String svr_id) {
        return this.obstacleDashboardDAO.getResTypeList(svr_id);
    }

    public List<ObstacleDashboardVO> getRtgrpNodeList(final String svr_id) {
        // List<CodeInfo> codeListByGrcode = null;
        // try {
        // codeListByGrcode = CodeTableMng.getCodeListByGrcode("RTGRP_NODE");
        // } catch (UserSysException e) {
        // this.logger.warn(ExceptionUtils.getStackTrace(e));
        // throw new CommonResponseException(e);
        // }
        // List<String> result = new ArrayList<>();
        // for (CodeInfo codeInfo : codeListByGrcode) {
        // result.add(codeInfo.getCode());
        // }
        // return result;
        // List<String> result = this.obstacleDashboardDAO.getRtgrpNodeList(svr_id);
        // result.add(0, "Average");
        // return result;
        return this.obstacleDashboardDAO.getRtgrpNodeList(svr_id);
    }

    public JsonObject getEmsmRealtimeGraphByResType(final String svr_id, final String res_type) {
        final Map<String, String> params = new HashMap<>();
        params.put("svr_id", svr_id);
        params.put("res_type", res_type);
        final JsonObject result = new JsonObject();
        final List<Integer> value = new ArrayList<>();
        String before_res_type = null;
        // Integer total = 0;
        final List<ObstacleDashboardVO> emsmRealtimeGraphByResType = this.obstacleDashboardDAO.getEmsmRealtimeGraphByResType(params);
        for (final ObstacleDashboardVO obstacleDashboardVO : emsmRealtimeGraphByResType) {
            if (before_res_type != null && !StringUtils.equals(before_res_type, obstacleDashboardVO.getRes_type())) {
                // value.add(0, total / value.size());
                result.add(before_res_type, AotMapperUtils.writeObjectAsJsonElement(value));
                value.clear();
                // total = 0L;
            }
            before_res_type = obstacleDashboardVO.getRes_type();
            value.add(obstacleDashboardVO.getGraph_value());
            // total += (Long) map.get("graph_value");
        }
        result.add(before_res_type, AotMapperUtils.writeObjectAsJsonElement(value));
        return result;
    }

    public List<Map<String, String>> getResTypeNGraphValueByRtgrpNode(final String node_name, final String node_group) {
        final Map<String, String> params = new HashMap<>();
        params.put("node_name", node_name);
        params.put("node_group", node_group);
        return this.obstacleDashboardDAO.getResTypeNGraphValueByRtgrpNode(params);
    }

    public TableEmsmVmStatusVO getEmsmVmStatusVO(final String node_name) {
        final TableEmsmVmStatusVO tableEmsmVmStatusVO = new TableEmsmVmStatusVO();
        tableEmsmVmStatusVO.setNode_name(node_name);
        return this.tableEmsmVmStatusDAO.getOne(tableEmsmVmStatusVO, Arrays.asList("node_name"));
    }

    public List<TableEmsmConnStatusVO> getEmsmConnStatusVOList(final String svr_id) {
        final TableEmsmConnStatusVO tableEmsmConnStatusVO = new TableEmsmConnStatusVO();
        tableEmsmConnStatusVO.setSvr_id(svr_id);
        tableEmsmConnStatusVO.setUse_yn("Y");
        final List<TableEmsmConnStatusVO> emsmConnStatusVOList = this.tableEmsmConnStatusDAO.getList(tableEmsmConnStatusVO, Arrays.asList("svr_id", "use_yn"), "sortseq");
        for (final TableEmsmConnStatusVO vo : emsmConnStatusVOList) {
            vo.setSvr_id(null);
            vo.setSortseq(null);
            vo.setUse_yn(null);
            vo.setCrt_id(null);
            vo.setCrt_dt(null);
            vo.setUpd_id(null);
            vo.setUpd_dt(null);
        }
        return emsmConnStatusVOList;
    }
}
