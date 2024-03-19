package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description:  修改菜单
 * @author: wxm
 */
@Service
public class Tmenu003Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tmenu003Service.class);

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
       if (StringUtils.isEmpty(p) || StringUtils.isEmpty(p.get("menu_code")) ){
            throw new BusinessException("Menu003Service","参数错误");
        }
        int status = sysMenuDao.update(p);
        if (status > 0){
            return BaseUtils.map("status","1");
        }else {
            throw new BusinessException("Menu003Service","修改失败，无法找到对应数据");
        }
    }
}
