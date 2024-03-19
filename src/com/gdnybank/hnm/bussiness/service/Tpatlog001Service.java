package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 巡查记录列表
 * @author: szm
 */
@Service
public class Tpatlog001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatlog001Service.class);

    @Autowired
    HPatrolLogDao patrolLogDao;
    @Autowired
    private HPatrolDao patrolDao;
    @Autowired
    HSiteDao siteDao;
    @Autowired
    private HPatrolLogContentDao patrolLogContentDao;
    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        Map<String, Object> returnmap = new HashMap<String, Object>();
        try {
            if (ObjectUtil.isEmpty(p.get("patrol_id"))) {
                throw new BusinessException("TpatLog001ServiceException", "patrol_id为必传字段");
            }
            List<Map<String, Object>> patrolLogList = patrolLogDao.queryForList(delkong(p));
            List<Map<String, Object>> patrolLogContents = patrolLogContentDao.queryForList(delkong(p));
            for (int i = 0; i < patrolLogContents.size(); i++) {
                patrolLogContents.get(i).put("id", i + 1);
            }
            Map<String, Object> logList = new HashMap<>();

            List<Map<String, Object>> patrols = patrolDao.queryByPatrolId(BaseUtils.map("patrol_id", p.get("patrol_id")));
            if (patrols != null && patrols.size() > 0) {
                logList.put("site_id", patrols.get(0).get("site_id"));
                logList.put("mgr_id", patrols.get(0).get("mgr_id"));
            }
            for (Map<String, Object> item : patrolLogList) {
                if ("90101".equals(item.get("patrol_content"))) {
                    if (patrolLogContents.size() == 0) {
                        Map<String, Object> logMap = new HashMap<>();
                        logMap.put("content_text", item.get("patrol_result"));
                        logMap.put("site_no", patrols.get(0).get("site_id"));
                        logMap.put("responsible", patrols.get(0).get("mgr_id"));
                        logMap.put("source", 1);
                        patrolLogContents.add(logMap);
                    }
                } else {
                    logList.put(item.get("patrol_content").toString(), item.get("patrol_result"));
                }
            }
            logList.put("contentList", patrolLogContents);

            returnmap.put("logList", logList);
            return returnmap;
        } catch (Exception e) {
            logger.info("查询巡查记录失败", e);
            throw new BusinessException("TpatLog001ServiceException", "查询巡查记录失败");
        }

    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (String key : data.keySet()) {
            if (data.get(key) != null && !"".equals(data.get(key))) {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
