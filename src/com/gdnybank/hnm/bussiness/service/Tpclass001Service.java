package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.HProjectClassDao;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Tpclass001Service extends TXBaseService {

    @Autowired
    private HProjectClassDao hProjectClassDao;
    private static final String PROJECT_NAME = "定期巡检";

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        List<Map<String, Object>> list = hProjectClassDao.queryForList(p);
        if (list == null || list.size() <= 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("created", DateUtil.now());
            map.put("source", evn.get("userid"));
            map.put("isdeleted", 0);
            map.put("pj_classify", 1);
            map.put("project_name", PROJECT_NAME);
            map.put("updatetime", DateUtil.now());
            hProjectClassDao.save(map);
        }
        return hProjectClassDao.queryForList(p);
    }
}
