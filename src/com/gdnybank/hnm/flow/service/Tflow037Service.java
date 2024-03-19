package com.gdnybank.hnm.flow.service;

import cn.hutool.core.img.Img;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.TApprovalFieldDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tflow037Service extends TXBaseService {

    @Autowired
    private TApprovalFieldDao tApprovalFieldDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        Assert.notNull(p.get("approval_type"), "approval_type为空。");
        //查询一级
        List<Map<String, Object>> firstItems = tApprovalFieldDao.queryForList(BaseUtils.map("approval_type", p.get("approval_type"), "pid", "0"));

        if (firstItems != null && firstItems.size() > 0) {
            for (Map<String, Object> firstItem : firstItems) {
                //查询二级
                List<Map<String, Object>> maps = tApprovalFieldDao.queryForList(BaseUtils.map("pid", firstItem.get("id")));

                //角色类需要从角色表SYS_ROLE查询数据
                BigDecimal is_role = (BigDecimal) firstItem.get("is_role");
                int roleId = Integer.parseInt(is_role.toString());
                if (roleId == 0) {
                    List<Map<String, Object>> roles = sysRoleDao.queryForList(BaseUtils.map());
                    //处理数据
                    List<Map<String, Object>> roleList = dealData(roles);
                    firstItem.put("list", roleList);
                }else {
                    firstItem.put("list", maps);
                }
            }
        }
        return firstItems;
    }

    private List<Map<String, Object>> dealData(List<Map<String, Object>> roles) {
        List<Map<String,Object>> result = new ArrayList<>();
        if (roles != null && roles.size() > 0){
            for (Map<String, Object> role : roles) {
                //剔除超级管理员
                if ("2021061500000001".equals(role.get("role_id"))) continue;

                HashMap<String, Object> map = new HashMap<>();
                map.put("id",role.get("role_id"));
                map.put("approval_items",role.get("role_id"));
                map.put("approval_name",role.get("role_name"));
                map.put("approval_type","023");
                map.put("pid","2");
                result.add(map);
            }
        }
            return result;
    }
}
