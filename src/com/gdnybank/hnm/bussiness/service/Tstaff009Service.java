package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
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
 * @description:  人员管理暂存详情
 * @author: wxm
 */
@Service
public class Tstaff009Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff009Service.class);

    @Autowired
    HManagerDao managerDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> staffMap =new HashMap<String,Object>();
            List<Map<String, Object>> staffList = managerDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                    , "temp_save", "1"));
            if(staffList != null && staffList.size()>0){
                Map<String, Object> map = staffList.get(0);
                staffMap.put("staffDetail",map);

                if(ObjectUtil.isNotEmpty(map.get("temp_role_id"))){
                    List<Map<String, Object>> list = sysRoleDao.queryForList(BaseUtils.map("role_id", map.get("temp_role_id")));
                    staffMap.put("roleList",list);
                }
            }
            return staffMap;
        }catch (Exception e){
            throw new BusinessException("Tstaff009ServiceException", "查询人员暂存详情失败");
        }

    }


}
