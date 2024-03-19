package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.SysRoleMobileMenuDao;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @desc 审批时查询详情
 */
@Service
public class Trole008Service extends TXBaseService {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysRoleMobileMenuDao sysRoleMobileMenuDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        String roleId = p.get("role_id").toString();
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException("Trole008ServiceException", "角色编号不能为空");
        }
        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Trole008ServiceException", "审批类型不能为空");
        }
        List<Map<String, Object>> queryRole;

        queryRole = sysRoleDao.queryTemp(p);

        logger.info("---------------->" + queryRole);


        List<String> list = new ArrayList<>();
        List<String> mobileList = new ArrayList<>();
        if (queryRole.size() == 1) {

            List<Map<String, Object>> queryForRoleMenu = sysRoleMenuDao.queryForList(p);
            logger.info("---------------->queryForRoleMenu:" + queryForRoleMenu);
            for (Map<String, Object> roleMenu : queryForRoleMenu) {
                String menuCode = (String) roleMenu.get("menu_code");
                list.add(menuCode);
            }

            List<Map<String, Object>> queryForRoleMobileMenu = sysRoleMobileMenuDao.queryForList(p);
            logger.info("---------------->queryForRoleMobileMenu:" + queryForRoleMobileMenu);
            for (Map<String, Object> roleMenu : queryForRoleMobileMenu) {
                String menuCode = (String) roleMenu.get("menu_code");
                mobileList.add(menuCode);
            }

        } else {
            throw new BusinessException("Trole008ServiceException", "查出多条或者无此数据");
        }


        return BaseUtils.map("role_msg", queryRole, "menu_code", list, "mobile_menu_code", mobileList);
    }
}
