package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
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
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 巡查记录新增或更新
 * @author: szm
 */
@Service
public class Tpatlog002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatlog002Service.class);

    @Autowired
    HPatrolLogDao patrolLogDao;
    @Autowired
    private HPatrolLogContentDao patrolLogContentDao;
    @Autowired
    HSiteDao siteDao;

    @Autowired
    private HnmCommService hnmCommService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

            if (ObjectUtil.isEmpty(p.get("patrol_id"))) {
                throw new BusinessException("TpatLog002ServiceException", "patrol_id为必传字段");
            }
            List<Map<String, Object>> tableDataList = null;
            String ids = "";
            //获取原有的巡查问题id
            if (ObjectUtil.isNotEmpty(p.get("contentList"))) {
                tableDataList = (List<Map<String, Object>>) p.get("contentList");
                for (Map<String, Object> map : tableDataList) {
                    if (ObjectUtil.isEmpty(map.get("site_no"))) {
                        logger.info("TpatLog002ServiceException，site_no不能为空");
                        throw new BusinessException("TpatLog002ServiceException", "site_no为必传字段");
                    }
                    if (ObjectUtil.isNotNull(map.get("ids"))) {
                        ids = ids + "'" + map.get("ids") + "',";
                    }
                }
                if (ids.endsWith(",")) {
                    ids = ids.substring(0, ids.length() - 1);
                }
            }
        try {
            String patrol_id = p.get("patrol_id").toString();
            List<Map<String, Object>> patrolLogList = patrolLogDao.queryForList(delkong(p));
            if (patrolLogList != null && patrolLogList.size() > 0) {
                //删除旧数据
                Map<String, Object> delParm = new HashMap<>();
                delParm.put("patrol_id", patrol_id);
                patrolLogDao.delete(delParm);
            }

            List<Map<String, Object>> patrolLogContentList = patrolLogContentDao.queryForList(delkong(BaseUtils.map("patrol_id", patrol_id)));
            if (patrolLogContentList != null && patrolLogContentList.size() > 0) {
                for (Map<String, Object> map : patrolLogContentList) {
                    int status = Integer.parseInt(map.get("status").toString());
                    if (0 == status) {
                        Map<String, Object> delParm = new HashMap<>();

                        if (StringUtils.hasText(ids)) {
                            delParm.put("ids", ids);
                        }
                        delParm.put("patrol_id", patrol_id);
                        delParm.put("updatetime", DateUtil.now());
                        //删除旧数据
                        patrolLogContentDao.deleteByPatrolId(delParm);
                    }
                }
            }
            if (ObjectUtil.isNotEmpty(p.get("contentList"))) {

                for (Map<String, Object> stringMap : tableDataList) {
                    if (ObjectUtil.isNotNull(stringMap.get("status"))) {
                        int status = Integer.parseInt(stringMap.get("status").toString());
                        if (status != 0) {
                            continue;
                        }
                    }
                    if (ObjectUtil.isNotNull(stringMap.get("source"))) {
                        //修改巡查问题
                        stringMap.put("id", stringMap.get("ids"));
                        stringMap.put("updatetime", DateUtil.now());
                        patrolLogContentDao.updateByPk(stringMap);
                    } else {
                        stringMap.put("discoverer", env.get("userid"));
                        stringMap.put("indexs", stringMap.get("id"));
                        stringMap.put("patrol_id", patrol_id);
                        stringMap.put("created", DateUtil.now());
                        stringMap.put("source", 1);
                        stringMap.put("isdeleted", 0);
                        stringMap.put("updatetime", DateUtil.now());
                        patrolLogContentDao.save(stringMap);
                    }
                }
            }
            //新增
            String id = "";
            p.remove("contentList");
            p.remove("source");
            p.remove("patrol_id");
            p.remove("txcode");
            for (String pkey : p.keySet()) {
                if (p.get(pkey) != null && !"".equals(p.get(pkey))) {
                    Map<String, Object> logItem = new HashMap<String, Object>();
                    //按照规则生成id
                    id = IdUtil.randomUUID().replace("-", "");
                    logItem.put("log_id", id);
                    logItem.put("patrol_id", patrol_id);
                    logItem.put("patrol_content", pkey);
                    logItem.put("patrol_result", p.get(pkey));
                    logItem.put("isdeleted", 0);
                    patrolLogDao.save(logItem);
                }
            }

            logger.info("TpatLog002Service执行完成");
            return BaseUtils.map("success", "1");
        } catch (Exception e) {
            throw new BusinessException("TpatLog001ServiceException", "新增或修改巡查记录失败");
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
