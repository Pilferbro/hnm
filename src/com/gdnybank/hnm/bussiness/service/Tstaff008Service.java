package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 人员新增 暂存
 * @author: wxm
 */
@Service
public class Tstaff008Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff008Service.class);

    @Autowired
    HManagerDao managerDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    SysRoleDao sysRoleDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(ObjectUtil.isEmpty(p.get("mgr_id"))){
            throw new BusinessException("Tstaff008ServiceException","mgr_id为必传字段");
        }else{
            p.put("mgr_id",p.get("mgr_id").toString().trim());
        }
        List<Map<String, Object>> list = managerDao.queryList(BaseUtils.map(
                "mgr_id", p.get("mgr_id"),
                "is_delete", "0",
                "approval_status", "1,2"));
        if (list.size() > 0) {
            throw new BusinessException("Tstaff008ServiceException", "已存在该员工，请勿重复新增");
        }

        //查询
        List<Map<String, Object>> savelist = managerDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                , "temp_save", "1"));
        if(savelist != null && savelist.size()>0) {
            //更新
            p.put("id", savelist.get(0).get("id"));
            p.put("creator", MfpContextHolder.getLoginId());
            p.put("create_time", savelist.get(0).get("create_time"));
            p.put("update_time", DateUtil.now());
            p.put("is_delete", "0");
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存

            //角色信息
            List<String> roleList = (List<String>) p.get("roleids");
            for(int i=0;i<roleList.size();i++){
                String role_id = roleList.get(i);
                p.put("temp_role_id", role_id);
            }
            managerDao.updateByPk(p);
        }else{
            //新增
            String id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            p.put("creator",MfpContextHolder.getLoginId());
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("update_time", createTime);
            p.put("is_delete", "0");
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存

            //角色信息
            List<String> roleList = (List<String>) p.get("roleids");
            for(int i=0;i<roleList.size();i++){
                String role_id = roleList.get(i);
                p.put("temp_role_id", role_id);
            }
            //保存人员信息
            managerDao.save(p);
        }

        logger.info("Tstaff008Service执行完成");
        return BaseUtils.map("success","1");
    }
}
