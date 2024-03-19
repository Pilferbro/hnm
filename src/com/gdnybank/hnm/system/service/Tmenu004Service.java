package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description:  查询菜单
 * @author: wxm
 */
@Service
public class Tmenu004Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tmenu004Service.class);

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        List<Map<String, Object>> list = sysMenuDao.queryMenuInfo(p);
        List<Map<String,Object>> baseList = new LinkedList<>();
        for (Map<String,Object> treeInfo : list){
            if (StringUtils.isEmpty(treeInfo.get("super_code"))){
                //说明是第一层菜单
                baseList.add(treeInfo);
            }
        }
        for (Map<String,Object> treeInfo : baseList) {
            treeInfo.put("sub_tree",getChild(treeInfo.get("menu_code").toString(), list));
        }
        return BaseUtils.map("status","1","menu_list",baseList);
    }

    // 获取子级菜单
    private List<Map<String, Object>> getChild(String pid, List<Map<String, Object>> menus) {
        List<Map<String, Object>> childs = new ArrayList<>();
        for (Map<String, Object> e : menus) {
            if (e.get("super_code") != null) {
                if (e.get("super_code").toString().equals(pid)) {
                    // 子菜单的下级菜单　　
                    e.put("sub_tree",getChild(e.get("menu_code").toString(), menus));
                    childs.add(e);
                }
            }
        }
        //如果没有子级菜单就停止查找
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }
}
