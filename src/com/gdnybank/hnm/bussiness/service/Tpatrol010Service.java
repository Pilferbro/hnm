package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Tpatrol010Service extends TXBaseService {

    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        List<String> idList = (List<String>) p.get("ids");

        String ids="";
        for(String id:idList){
            ids +=",'"+id+"'";
        }
        if(!StringUtils.isEmpty(ids)){
            ids = ids.substring(1);
        }

        hPatrolLogContentDao.deleteByIDs(BaseUtils.map("ids",ids,"updatetime", DateUtil.now()));
        return BaseUtils.map("success","1");
    }
}
