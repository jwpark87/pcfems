/*****************************************************************************
 * 프로그램명 : DashboardServiceImpl.java 설 명 : 진행 중 DASHBOARD 조회 persistence-layer class. 참고 사항 : 없음
 *****************************************************************************
 * Date Author Version Description ---------- ------- ------- ----------------------------------------------- 2018.02.26 KYM 1.0 초기작성
 *****************************************************************************/

package com.aot.pcfems.business.stcs.dashboard;

import com.aot.pcfems.common.exception.UserSysException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.Map.Entry;

/**
 * DASHBOARD 조회 서비스
 *
 * @author eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class DashboardService {
    @Resource
    private DashboardDAO dashboardDAO;

    /**
     * 대시보드 그룹 정보 취득
     *
     * @param ObstacleDashboardSearchInfo 검색데이타
     * @return List Dashboard 그룹 리스트
     */

    public List<DashboardVO> getDashboardGrpList(final DashboardSearchInfo searchInfo) throws UserSysException {
        return this.dashboardDAO.getDashboardGrpList(searchInfo);
    }

    /**
     * 대시보드 아이템 정보 취득
     *
     * @param ObstacleDashboardSearchInfo 검색데이타
     * @return List Dashboard 아이템 리스트
     */

    public Map<String, Object> getDashboardItemDataList(final DashboardSearchInfo searchInfo) throws UserSysException {
        final Map<String, List<DashboardVO>> resultItem = new LinkedHashMap<>();
        final Map<String, Object> resultData = new LinkedHashMap<>();
        Map<String, List<List<String>>> resultLineData = new LinkedHashMap<>();
        final Map<String, Object> result = new LinkedHashMap<>();

        List<DashboardVO> item = new ArrayList<>();
        List<DashboardVO> data = new ArrayList<>();
        List<List<String>> line = new ArrayList<>();

        final List<DashboardVO> grpList = this.dashboardDAO.getDashboardGrpList(searchInfo);
        final List<DashboardVO> itemList = this.dashboardDAO.getDashboardItemList(searchInfo);
        final List<DashboardVO> dataList = this.dashboardDAO.getDashboardItemDataList(searchInfo);

        if (itemList.size() > 0) {
            // ******************************아이템 목록 그룹아이디로 묶음
            for (final DashboardVO grp : grpList) {
                final StringBuffer bufItemKey = new StringBuffer();
                bufItemKey.append(grp.getDsbd_group_id());
                for (final DashboardVO obj : itemList) {
                    if (grp.getDsbd_group_id().equals(obj.getDsbd_group_id())) {
                        item.add(obj);
                    }
                }
                resultItem.put(bufItemKey.toString(), item);
                item = new ArrayList<>();
            }
            // ******************************아이템 목록 그룹아이디로 묶음

            // ******************************데이타 목록 아이템아이디로 묶음
            for (final DashboardVO obj : itemList) {
                final StringBuffer bufDataKey = new StringBuffer();
                bufDataKey.append(obj.getDsbd_item_id());
                for (final DashboardVO objDt : dataList) {
                    if (obj.getDsbd_item_id().equals(objDt.getDsbd_item_id())) {
                        data.add(objDt);
                    }
                }
                if ("LINEGRAPH".equals(obj.getDsbd_item_type())) {

                    final Map<String, List<DashboardVO>> groupedData = new HashMap<>();
                    for (final DashboardVO ds : data) {
                        final String key = ds.getData_name();
                        if (groupedData.get(key) == null) {
                            groupedData.put(key, new ArrayList<DashboardVO>());
                        }
                        groupedData.get(key).add(ds);
                    }
                    // data_name으로 그룹화 해서 데이타 재 취득
                    for (final Entry<String, List<DashboardVO>> entry : groupedData.entrySet()) {
                        // 그룹화하면서 xval,yval만 리스트에 담기
                        for (final DashboardVO arrTmp : entry.getValue()) {
                            final List<String> lineDt = new ArrayList<>();
                            lineDt.add(arrTmp.getXval());
                            lineDt.add(arrTmp.getYval());
                            line.add(lineDt);
                        }
                        resultLineData.put(entry.getKey(), line);
                        line = new ArrayList<>();
                        // logger.debug(key + " : " + value);
                    }
                    resultData.put(bufDataKey.toString(), resultLineData);
                    // kym 20180326 수정 기존 라인 정보를 계속 삽입하는 문제 발생. 초기화 추가
                    resultLineData = new LinkedHashMap<>();
                } else {
                    resultData.put(bufDataKey.toString(), data);
                }
                data = new ArrayList<>();
            }
            // ******************************데이타 목록 아이템아이디로 묶음
        }
        result.put("itemList", resultItem);
        result.put("dataList", resultData);
        return result;
    }

}
