package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HTeamDao;
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
 * @description:  团队/片区新增时暂存详情
 * @author: wxm
 */
@Service
public class Tteam008Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam008Service.class);

    @Autowired
    HTeamDao hTeamDao;


    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        try{
            Map<String,Object> teamMap =new HashMap<String,Object>();
            List<Map<String, Object>> teamList = hTeamDao.queryForListDetail(BaseUtils.map("creator", MfpContextHolder.getLoginId()
                    , "temp_save", "1"));
            if(teamList != null && teamList.size()>0){
                Map<String, Object> map = teamList.get(0);
                teamMap.put("teamDetail",map);
            }
            return teamMap;
        }catch (Exception e){
            throw new BusinessException("Tteam008ServiceException", "查询团队/片区暂存详情失败");
        }
    }
}
