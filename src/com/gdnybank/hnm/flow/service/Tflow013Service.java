package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.dao.TApprovalDao;
import com.gdnybank.hnm.pub.dao.TApprovalProcessDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 审批流程删除
 * @author: wxm

 */
@Service
public class Tflow013Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow013Service.class);

    @Autowired
    TApprovalProcessDao tApprovalProcessDao;
	@Autowired
    HnmCommService hnmCommService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private TApprovalDao tApprovalDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {
        //获取当前用户角色 分行管理员没有删除总行审批流程的权限
        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if(userRoleLevel==0){
            logger.error("登陆人员"+userId+"未配置角色");
            throw new BusinessException("Tflow013ServiceException", "登陆人员未配置角色");
        }
        // 先查询该流程
        List<Map<String, Object>> list = tApprovalProcessDao.queryForListByPK(p);
        if (list != null && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            if (ObjectUtil.isEmpty(map.get("branch_id"))) {// 全行的流程
                if (userRoleLevel !=1) {// 分行
                    logger.error("你没有权限删除此流程");
                    throw new BusinessException("Tflow013ServiceException", "你没有权限删除此流程");
                }
            }
            // 该流程在审批流中时 不允许删除
            List<Map<String, Object>> mapList = tApprovalDao.queryForListAndApply(BaseUtils.map("approval_process_id",map.get("id")));
            if(mapList!=null&&mapList.size()>0){//在审批流程表有记录
                for (Map<String, Object> map1 : mapList){
                    if(ObjectUtil.isNotEmpty(map1.get("approval_status"))&& ApprovalStatusEnum.STATUS_1.getApprovalStatus().equals(map1.get("approval_status").toString())){
                        throw new BusinessException("Tflow013ServiceException", "该审批流程有正在审批中的项目，暂不允许删除");
                    }
                }
            }
        }

        tApprovalProcessDao.deleteByPk(p);
		logger.info("Tflow013Service执行完成");
		return BaseUtils.map("success","1");
	}

}
