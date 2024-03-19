package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysRoleDao;
import com.gdnybank.hnm.pub.dao.SysRoleMenuDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.service.OperationFlowRecordService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色查询列表
 *
 * @Package: com.nantian.paymng.system
 * @author: wxm
 */
@Service
public class Trole003Service extends TXBaseService {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysRoleMenuDao sysRoleDao;
    @Autowired
    private HnmCommService hnmCommService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart(page * num);
        pageInfo.setIDisplayLength(num);

        MfpContextHolder.setPageInfo(pageInfo);

        String userId = MfpContextHolder.getLoginId();
        int userRole = hnmCommService.getUserRole(BaseUtils.map("account_id", userId));
        if (userRole == 0) {
            throw new BusinessException("Tbranch001ServiceException", "登陆人员未配置角色");
        }

        //超级管理员可以查所有角色
        if (userRole != 1) {
            String super_manager = MfpContextHolder.getSysParam("role", "super_manager");
            p.put("super_role_id", super_manager);
        }


        Map<String, Object> roleRuslt = new HashMap<>();
        p.put("deleted","0");
        List<Map<String, Object>> result = roleDao.queryForListByPage(p);
        long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
        roleRuslt.put("total", toatl);
        roleRuslt.put("userList", result);

        logger.info("Trole003Service执行完成");
        return roleRuslt;
    }

}
