package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.TApprovalItemDao;
import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.gdnybank.hnm.pub.dao.TApprovalTypeDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 查询审批角色对应审批人员
 *
 * @author: phl
 */
@Service
public class Tflow036Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Tflow036Service.class);

    @Autowired
    TApprovalTypeDao tApprovalTypeDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    TApprovalItemDao tApprovalItemDao;
    @Autowired
    TApprovalProcessDao tApprovalProcessDao;
    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        Assert.notNull(p.get("approval_type"), "approval_type不能为空");
        String approvalType = p.get("approval_type").toString();

        //查找审批流第一节点
        Map<String, Object> approvalProcessMap = hnmCommService.getApprovalProcess(approvalType, hnmCommService.getUserBranchid());
        String approvalProcess = hnmCommService.getNewApprovalProcess(p, approvalProcessMap);

        if(StringUtils.isEmpty(approvalProcess)){
            logger.info("Tflow036ServiceException，不存在审批流节点");
            throw new BusinessException("Tflow036Service","请联系管理人添加审批流节点");
        }

        String[] approvalProcessArr = approvalProcess.split("-");
        //获取首个流程节点
        return sysAccountDao.queryForListBycondition(BaseUtils.map("role_id", approvalProcessArr[0]));
    }

}
