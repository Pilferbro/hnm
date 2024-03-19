package com.gdnybank.hnm.market.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HSiteWarnRecordDao;
import com.gdnybank.hnm.pub.dao.SysDatadictionaryDao;
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

/**
 * @description:  查询站点状态提醒记录列表
 * @author: wxm
 */
@Service
public class Tmarket003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tmarket003Service.class);

    @Autowired
    HSiteWarnRecordDao hSiteWarnRecordDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        add(p.get("site_name"),p,"site_name");

        //校验角色   总行级可以看所有
        // 获取当前登陆用户
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            logger.error("登陆人员"+userId+"未配置角色");
            throw new BusinessException("Tflow007ServiceException", "登陆人员未配置角色");
        }

        if(userRoleLevel != 1){
            String branchids = hnmCommService.getUserBranchids();
            p.put("orgids", branchids);
        }

        if(userRoleLevel == 4){
            //非管理员只能查看自己的
            p.put("mgr_id", userId);
        }

        if(ObjectUtil.isNotEmpty(p.get("orgid"))){
            p.put("orgids", hnmCommService.getUserBranchidsBybranch(p.get("orgid").toString()));
        }

        //处理申请开始时间及结束时间
        if(p.containsKey("start_date") && ObjectUtil.isNotEmpty(p.get("start_date"))){
            p.put("start_date",p.get("start_date")+" 00:00:00");
        }
        if(p.containsKey("end_date") && ObjectUtil.isNotEmpty(p.get("end_date"))){
            p.put("end_date",p.get("end_date")+" 24:59:59");
        }

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart(page*num);
        pageInfo.setIDisplayLength(num);
        MfpContextHolder.setPageInfo(pageInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String, Object>> list = hSiteWarnRecordDao.queryForListByconditionByPage(delkong(p));
        long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
        result.put("total",toatl);
        result.put("siteWarnRecordList",list);
        logger.info("Tmarket003Service执行完成");
        return result;
    }

    private void add(Object object, Map<String, Object> p,String name) {
        if(object!=null){
            p.put(name, "%"+object.toString()+"%");
        }

    }

    public Map<String,Object> delkong(Map<String,Object> data){
        Map<String,Object> dataMap=new HashMap<String , Object>();
        for (String key  : data.keySet()) {
            if(data.get(key)==null||"".equals(data.get(key))){

            }else{
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }


}
