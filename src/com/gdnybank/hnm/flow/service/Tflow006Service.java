package com.gdnybank.hnm.flow.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TApprovalDetailsDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 审批类型校验（编号和名称不可重复）
 */
@Service
public class Tflow006Service extends TXBaseService {
	private static final Log logger = LogFactory.getLog(Tflow006Service.class);

    @Autowired
    TApprovalTypeDao tApprovalTypeDao;
    @Autowired
    private TApprovalDetailsDao tApprovalDetailsDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        Map<String,Object> dataMap=new HashMap<>();
        if(!p.containsKey("id")) {//新增
            dataMap.put("approval_type",p.get("approval_type"));
            int ret=Integer.parseInt(String.valueOf(tApprovalTypeDao.count(dataMap)));
            if(ret>0){
                throw new BusinessException("Tflow006ServiceException", "审批类型编号不能重复");
            }
            dataMap.clear();
            dataMap.put("approval_type_name",p.get("approval_type_name"));
            ret=Integer.parseInt(String.valueOf(tApprovalTypeDao.count(dataMap)));
            if(ret>0){
                throw new BusinessException("Tflow006ServiceException", "审批类型名称不能重复");
            }
        }else {//修改
            dataMap.put("approval_type_name", p.get("approval_type_name"));
            List<Map<String,Object>> list=tApprovalTypeDao.queryForList(dataMap);
            if(list !=null && list.size()>0){
                Object approval_type=p.get("approval_type");
                if(!approval_type.equals(list.get(0).get("approval_type"))){
                    throw new BusinessException("Tflow006ServiceException", "审批类型名称不能重复");
                }
            }
        }
        //新增免审批明细
        //已存在数据则删除后再更新
        List<Map<String, Object>> details = tApprovalDetailsDao.queryForList(BaseUtils.map("approval_type", p.get("approval_type")));
        if (details != null && details.size() > 0){
            tApprovalDetailsDao.delete(BaseUtils.map("approval_type", p.get("approval_type")));
        }
        if (ObjectUtil.isNotEmpty(p.get("approval_items"))){
            List<List<String>> items = (List<List<String>>) p.get("approval_items");

            for (List<String> item : items) {
               Map<String, Object> map = new HashMap<>();
                map.put("approval_field",item.get(0));
                map.put("approval_details",item.get(1));
                map.put("approval_type",p.get("approval_type"));
                map.put("create_time", DateUtil.now());
                map.put("update_time", DateUtil.now());
                tApprovalDetailsDao.save(map);
            }
        }
        logger.info("Tflow006Service执行完成");
        return BaseUtils.map("success","1");
    }
}
