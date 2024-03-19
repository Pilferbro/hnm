package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.AllTradeInfoDao;
import com.gdnybank.hnm.pub.dao.AllTradeInfoRiskDao;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Tpatrol018Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol018Service.class);


    @Autowired
    private AllTradeInfoRiskDao allTradeInfoRiskDao;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {
        if (ObjectUtil.isEmpty(p.get("risk_id"))) {
            throw new BusinessException("Tpatrol018ServiceException", "id不可为空！");
        }
        try {
            return allTradeInfoRiskDao.queryForListByRiskId(BaseUtils.map("risk_id", p.get("risk_id")));
        } catch (Exception e) {
            logger.info("查询交易列表失败", e);
            throw new BusinessException("Tpatrol018ServiceException", "查询交易失败");
        }

    }

    private void setPageInfo(Map<String, Object> p) {
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page")) ? p.get("page").toString() : "0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number")) ? p.get("number").toString() : "10");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart(page * number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : data.keySet()) {
            if (data.get(key) != null && !"".equals(data.get(key))) {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
