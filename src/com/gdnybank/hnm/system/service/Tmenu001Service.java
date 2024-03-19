package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SequenceService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @description:  新增菜单
 * @author: wxm
 */
@Service
public class Tmenu001Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tmenu001Service.class);

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        p.put("creator",env.get("userid"));
        p.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        int status = sysMenuDao.save(p);
        if (status > 0){
            return BaseUtils.map("success","1");
        }else {
            throw new BusinessException("Menu001Service","新增菜单失败");
        }
    }
}
