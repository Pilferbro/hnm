package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.SysBranchDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
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
 * @description: 团队/片区 暂存
 * @author: wxm
 */
@Service
public class Tteam007Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam007Service.class);

    @Autowired
    HTeamDao hTeamDao;
    @Autowired
    SysBranchDao sysBranchDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    private SysParamService sysParamService;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        //校验是否可新增
        List<Map<String, Object>> branchlist = sysBranchDao.queryForList(BaseUtils.map("branch_id", p.get("porgid")));
        if (branchlist != null && branchlist.size() > 0) {
            Map<String, Object> map = branchlist.get(0);
            if ("2".equals(p.get("team_flag"))) {//片区
                if (!"0".equals(map.get("team_flag"))) {
                    throw new BusinessException("Tteam007ServiceException", "上级机构请选择机构");
                }
            }
        } else {
            throw new BusinessException("Tteam007ServiceException", "查无此上级机构");
        }

        //查询
        List<Map<String, Object>> list = hTeamDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                , "temp_save", "1"));
        if(list != null && list.size()>0){
            //更新
            p.put("id", list.get(0).get("id"));
            p.put("create_time", list.get(0).get("create_time"));
            p.put("is_delete", "0");
            p.put("creator", MfpContextHolder.getLoginId());
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存
            hTeamDao.updateByPk(p);
        }else{
            //新增
            String id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("is_delete", "0");
            p.put("creator", MfpContextHolder.getLoginId());
            p.put("temp_save","1");//暂存标志  0非暂存  1暂存
            hTeamDao.save(p);
        }

        logger.info("Tteam007Service执行完成");
        return BaseUtils.map("success","1");
    }
}
