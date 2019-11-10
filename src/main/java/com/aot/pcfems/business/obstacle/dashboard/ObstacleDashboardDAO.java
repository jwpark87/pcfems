/*****************************************************************************
 * 프로그램명  : DashboardDAO.java
 * 설     명  : 진행 중 DASHBOARD 조회 persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.02.26  KYM     1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.business.obstacle.dashboard;

import com.aot.pcfems.business.tablevo.emsmhwconf.TableEmsmHwConfVO;
import com.aot.pcfems.business.tablevo.emsmvmstatus.TableEmsmVmStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 통계관리 DASHBOARD 조회 DAO persistence-layer class.
 *
 * @author eaction
 * @version 1.0
 */
@Mapper
public interface ObstacleDashboardDAO {

    List<TableEmsmHwConfVO> getEmsmHwConf();

    List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrId(final String svr_id);

    List<TableEmsmVmStatusVO> getEmsmVmStatusBySvrIdAndHwName(final Map<String, String> param);

    List<String> getResTypeList(final String svr_id);

    List<ObstacleDashboardVO> getRtgrpNodeList(final String svr_id);

    List<ObstacleDashboardVO> getEmsmRealtimeGraphByResType(final Map<String, String> params);

    List<Map<String, String>> getResTypeNGraphValueByRtgrpNode(final Map<String, String> params);
}
