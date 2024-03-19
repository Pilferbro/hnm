package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class Tpatrol009Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol008Service.class);

    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        try {

            if (ObjectUtil.isNull(p.get("content_text"))) {
                throw new BusinessException("Tpatrol009ServiceException", "问题描述不能为空");
            }
            if (ObjectUtil.isNull(p.get("end_date"))) {
                throw new BusinessException("Tpatrol009ServiceException", "整改时间不能为空");
            }
            if (ObjectUtil.isNull(p.get("risk_level"))) {
                throw new BusinessException("Tpatrol009ServiceException", "风险等级不能为空");
            }

            p.put("updatetime", DateUtil.now());

            if (ObjectUtil.isNotNull(p.get("id"))) {
                //修改差错信息
                hPatrolLogContentDao.updateByPk(p);
            } else {
                //新增差错信息
                p.put("source", 2);
                p.put("discoverer", evn.get("userid"));
                p.put("isdeleted", 0);
                p.put("indexs", "1");
                p.put("created", DateUtil.now());
                hPatrolLogContentDao.save(p);
            }
            logger.info("Tpatrol009Service执行完成");
            return BaseUtils.map("success", "1");
        } catch (Exception e) {
            throw new BusinessException("Tpatrol009ServiceException", "新增差错信息失败");
        }
    }
}
