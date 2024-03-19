package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.HManagerDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.HTeamDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 恢复我的申请
 * @author: wxm

 */
@Service
public class Tflow028Service extends TXBaseService{
	private static final Log logger = LogFactory.getLog(Tflow028Service.class);

    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    HManagerDao managerDao;
    @Autowired
    HTeamDao hTeamDao;
    @Autowired
    private HSiteDao hSiteDao;

	@Override
	@Transactional
	public Object doService(Map<String, Object> env, Map<String, Object> p) {

        String apply_id = String.valueOf(p.get("apply_id"));
        if(StringUtils.isEmpty(apply_id)) {
            throw new BusinessException("Tflow028ServiceException","申请编号不能为空");
        }
        String approval_type = String.valueOf(p.get("approval_type"));
        if(StringUtils.isEmpty(approval_type)) {
            throw new BusinessException("Tflow028ServiceException","approval_type不能为空");
        }

        Map<String,Object> parms = new HashMap<>();
        parms.put("approval_status",ApprovalStatusEnum.STATUS_1.getApprovalStatus());
        parms.put("approval_status_name",ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
        parms.put("id",apply_id);
        parms.put("approval_type",approval_type);

        String relation_id = "";
        List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("id", apply_id));
        if(list != null && list.size()>0){
            relation_id = list.get(0).get("relation_id").toString();
        }else{
            throw new BusinessException("Tflow028ServiceException","未查询到我的申请信息");
        }

        if(approval_type.equals(ApprovalTypeEnum.TYPE002) || approval_type.equals(ApprovalTypeEnum.TYPE003)
        || approval_type.equals(ApprovalTypeEnum.TYPE014) || approval_type.equals(ApprovalTypeEnum.TYPE015) || approval_type.equals(ApprovalTypeEnum.TYPE021)){
            //人员
            List<Map<String, Object>> forList = managerDao.queryForList(BaseUtils.map("id", relation_id));
            Object mgr_id = null;
            if(forList != null && forList.size() > 0){
                mgr_id = forList.get(0).get("mgr_id");
            }

            if(mgr_id != null){
                List<Map<String, Object>> mapList = managerDao.queryList(BaseUtils.map("mgr_id",
                        mgr_id, "approval_status", "1"));
                //有在审批中的
                if(mapList!=null && mapList.size()>0){
                    throw new BusinessException("Tflow028ServiceException","该人员有其他在审批中的流程，此申请不可恢复");
                }
            }
        }else if(approval_type.equals(ApprovalTypeEnum.TYPE009) || approval_type.equals(ApprovalTypeEnum.TYPE010)
                || approval_type.equals(ApprovalTypeEnum.TYPE011) || approval_type.equals(ApprovalTypeEnum.TYPE012)
                || approval_type.equals(ApprovalTypeEnum.TYPE013) || approval_type.equals(ApprovalTypeEnum.TYPE020)
        ){
            //团队
            List<Map<String, Object>> forList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
            Object branch_id = null;
            if(forList != null && forList.size() > 0){
                branch_id = forList.get(0).get("branch_id");
            }

            if(branch_id != null){
                List<Map<String, Object>> mapList = hTeamDao.queryList(BaseUtils.map("branch_id",
                        branch_id, "approval_status", "1"));
                //有在审批中的
                if(mapList!=null && mapList.size()>0){
                    throw new BusinessException("Tflow028ServiceException","该团队有其他在审批中的流程，此申请不可恢复");
                }
            }
        }else if(approval_type.equals(ApprovalTypeEnum.TYPE004) || approval_type.equals(ApprovalTypeEnum.TYPE005)
                || approval_type.equals(ApprovalTypeEnum.TYPE006) || approval_type.equals(ApprovalTypeEnum.TYPE007)
                || approval_type.equals(ApprovalTypeEnum.TYPE008) || approval_type.equals(ApprovalTypeEnum.TYPE016)
                || approval_type.equals(ApprovalTypeEnum.TYPE017) || approval_type.equals(ApprovalTypeEnum.TYPE018)
                || approval_type.equals(ApprovalTypeEnum.TYPE019) || approval_type.equals(ApprovalTypeEnum.TYPE022)

        ){
            //站点
            List<Map<String, Object>> forList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
            Object site_no = null;
            if(forList != null && forList.size() > 0){
                site_no = forList.get(0).get("site_no");
            }

            if(site_no != null){
                List<Map<String, Object>> mapList = hSiteDao.queryForListBycondition(BaseUtils.map("site_no",
                        site_no, "approval_status", "1"));
                //有在审批中的
                if(mapList!=null && mapList.size()>0){
                    throw new BusinessException("Tflow028ServiceException","该站点有其他在审批中的流程，此申请不可恢复");
                }
            }
        }

        tApprovalApplyDao.updateStatus(parms);

		logger.info("Tflow028Service执行完成");
		return BaseUtils.map("success","1");
	}

}
