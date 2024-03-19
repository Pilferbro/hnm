package com.gdnybank.hnm.bussiness.service;


import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.HRectificationDetailsDao;
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

import java.util.List;
import java.util.Map;

/**
 * 查询风险处置详情
 */
@Service
@Transactional
public class Tpatrol014Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol014Service.class);

    @Autowired
    private HRectificationDetailsDao detailsDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        if (ObjectUtil.isNull(p.get("apply_id"))) {
            throw new BusinessException("Tpatrol014ServiceException", "审核工单缺失");
        }
        //查询风险信息工单号
        List<Map<String, Object>> detailsMap = detailsDao.queryByApplyId(BaseUtils.map("id",p.get("apply_id")));

        setPageInfo(p);
        List<Map<String, Object>> resultList = null;
        //通过风险信息工单号查找整改详情
        if (detailsMap != null && detailsMap.size() > 0) {
            resultList = hPatrolLogContentDao.queryByPage(BaseUtils.map("id", detailsMap.get(0).get("patrol_lc_id")));
        }

        Map<String, Object> resultMap = null;
        if (resultList != null && resultList.size() > 0) {
            resultMap = resultList.get(0);
            resultMap.put("status",resultMap.get("tmp_status"));
            resultMap.remove("tmp_status");
        }
        return resultMap;
    }

    private void setPageInfo(Map<String, Object> p) {
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page")) ? p.get("page").toString() : "0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number")) ? p.get("number").toString() : "10");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart(page * number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }
}
