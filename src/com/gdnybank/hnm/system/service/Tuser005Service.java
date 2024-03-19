package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 删除用户
 */
@Service
public class Tuser005Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;
    @Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HTeamDao hTeamDao;
    @Autowired
    private TApprovalDao tApprovalDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    TApprovalApplyDao tApprovalApplyDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        List<String> idList = (List<String>) p.get("accountIds");

        StringBuilder sb = new StringBuilder();

        for (String user_id : idList) {
//
            //先校验用户下是否有站点
            List<Map<String, Object>> list = hSiteDao.queryForListBycondition(BaseUtils.map("mgr_id", user_id,
                    "is_delete", "0", "approval_status", "2", "status", "'0','1','2'"));
            if (list != null && list.size() > 0) {

                sb.append("用户").append(user_id).append("有管辖站点，不允许删除！");
            }
            //校验用户是否团队/片区负责人
            List<Map<String, Object>> listTeam = hTeamDao.queryList(BaseUtils.map("team_leader", user_id,
                    "is_delete", "0", "approval_status", "2"));
            if (listTeam != null && listTeam.size() > 0) {

                sb.append("用户").append(user_id).append("是团队/片区负责人，不允许删除！");
            }
            List<Map<String, Object>> approvalList = tApprovalDao.queryByApprovalUser(BaseUtils.map("approval_status", "1", "approval_user", user_id));
            if (approvalList != null && approvalList.size() > 0) {
                sb.append("用户").append(user_id).append("存在未审批的工单，请先处理再停用！");

            }
            List<Map<String, Object>> patrolLogContentList = hPatrolLogContentDao.queryForExport(BaseUtils.map("status", 0, "responsible", user_id));

            if (patrolLogContentList != null && patrolLogContentList.size() > 0) {
                sb.append("用户").append(user_id).append("存在未处理的风险信息，请先处理再停用！");

            }
            List<Map<String, Object>> result = tApprovalApplyDao.queryForFlow(BaseUtils.map("operator", user_id, "approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus()));
            if (result != null && result.size() > 0) {
              sb.append("用户").append(user_id).append("存在未办结的申请工单，请先处理再停用！");
            }
        }
        if (!sb.toString().equals("")) {
            throw new BusinessException("Tuser005ServiceException",sb.toString());
        }

        String ids = "";
        for (String id : idList) {
            ids += ",'" + id + "'";
        }
        if (!StringUtils.isEmpty(ids)) {
            ids = ids.substring(1);
        }
        sysAccountDao.deleteByIds(ids);
        sysAccountRoleDao.deleteByIds(ids);
        //逻辑删除客户经理表
        hManagerDao.updateStatus("1", ids);

        return BaseUtils.map("success", "1");
    }
}
