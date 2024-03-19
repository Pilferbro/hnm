package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @desc 新增角色时查询菜单
 */
@Service
public class Trole005Service extends TXBaseService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private Tmenu004Service tmenu004Service;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

//        List<Map<String, Object>> menuList = sysMenuDao.queryForList(BaseUtils.map());
    	Map<String,Object> menuList = (Map<String,Object>)tmenu004Service.doService(env, p);
        return menuList;
    }
}
