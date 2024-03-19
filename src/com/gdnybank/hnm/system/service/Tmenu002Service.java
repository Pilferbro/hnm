package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMobileMenuDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @description:  删除菜单
 * @author: wxm
 */
@Service
public class Tmenu002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tmenu002Service.class);

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysRoleMobileMenuDao sysRoleMobileMenuDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (StringUtils.isEmpty(p) || StringUtils.isEmpty(p.get("menu_code")) ){
            throw new BusinessException("Menu002Service","未指定菜单编号，无法删除");
        }
        List<Map<String, Object>> list = sysMenuDao.selectIsExistSubMenu(p);
        if (list != null && list.size() > 0) {
            throw new BusinessException("Menu002Service","当前菜单存在子菜单，无法删除");
        }

        int status = sysMenuDao.delete(p);
        if (status > 0){
            sysRoleMenuDao.deleteMenuFromRole(p);
            sysRoleMobileMenuDao.deleteMenuFromRole(p);
            return BaseUtils.map("success","1");
        }else {
            throw new BusinessException("Menu002Service","删除失败，无法找到对应数据");
        }
    }
}
