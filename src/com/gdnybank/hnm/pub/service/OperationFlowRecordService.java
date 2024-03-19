package com.gdnybank.hnm.pub.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TOperationDao;
import com.nantian.mfp.framework.err.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;




/**
 * 用于记录每个站点变更操作的流程记录
 * 主要保存的信息包含：审核类型、工单编号、处理意见及处理时间。
 */
@Service
public class OperationFlowRecordService {

    @Autowired
    TOperationDao operationDao;

    /**
     * 保存操作记录
     * @param p
     */
    public void saveOperationRecord(Map<String, Object> p){

        if (ObjectUtil.isEmpty(p.get("suggestion"))){
            throw new BusinessException("OperationFlowRecordService","suggestion为必传字段");
        }
        Map<String, Object> map = new HashMap<>();

        map.put("approval_his_apply_id",p.get("apply_id"));
        map.put("suggestion",p.get("suggestion"));
        map.put("approval_type",p.get("approval_type"));
        map.put("created", DateUtil.now());

        operationDao.save(map);
    }
}
