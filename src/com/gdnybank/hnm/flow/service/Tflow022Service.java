package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 查询我的待审批列表
 *
 * @author: wxm
 */
@Service
public class Tflow022Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Tflow022Service.class);

    @Autowired
    TApprovalDao tApprovalDao;
    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private HTeamDao hTeamDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    private SysRoleDao roleDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart(page * num);
        pageInfo.setIDisplayLength(num);
        MfpContextHolder.setPageInfo(pageInfo);
        Map<String, Object> typeRuslt = new HashMap<String, Object>();

        //校验角色  超级管理员角色可查看所有
        // 获取当前登陆用户
        String userId = MfpContextHolder.getLoginId();
        int userRole = hnmCommService.getUserRole(BaseUtils.map("account_id", userId));
        if (userRole == 0) {
            logger.error("登陆人员" + userId + "未配置角色");
            throw new BusinessException("Tflow022ServiceException", "登陆人员未配置角色");
        }
        if (userRole != 1) {
            p.put("approval_user", userId);
        }

        //处理申请开始时间及结束时间
        if (p.containsKey("start_date") && ObjectUtil.isNotEmpty(p.get("start_date"))) {
            p.put("start_date", p.get("start_date") + " 00:00:00");
        }
        if (p.containsKey("end_date") && ObjectUtil.isNotEmpty(p.get("end_date"))) {
            p.put("end_date", p.get("end_date") + " 24:59:59");
        }

        p.put("approval_status", "1");

        List<Map<String, Object>> result = tApprovalDao.queryForListBycondition1(delkong(p));
        long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();

        if (result != null && result.size() > 0) {
            for (Map<String, Object> map : result) {
                String message = "";
                String approvalType = map.get("approval_type").toString();
                String relationId = map.get("relation_id").toString();
                if (ApprovalTypeEnum.TYPE002.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员新增申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE003.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE004.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点新增（试营业）申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE005.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE006.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点助农POS申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE007.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点开业申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE008.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点退出申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE009.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队新增申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE010.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE011.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队类型调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE012.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队所属机构调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE013.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队负责人调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE014.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员角色调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE015.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员所属机构调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE016.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点站长信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE017.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点POS信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE018.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点管辖客户经理调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE019.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点落地支行调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE020.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队删除申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE021.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员删除申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE022.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点转试营业申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE023.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = hPatrolLogContentDao.querySiteName(BaseUtils.map("id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + ApprovalTypeEnum.TYPE023.getApprovalTypeName();
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE024.getApprovalType().equals(approvalType)||ApprovalTypeEnum.TYPE025.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = roleDao.queryTemp(BaseUtils.map("role_id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("role_name"))) {
                            message = Objects.requireNonNull(ApprovalTypeEnum.findByApprovalType(approvalType)).getApprovalTypeName() + ":" + resList.get(0).get("role_name");
                        }
                    }
                }else if (ApprovalTypeEnum.TYPE026.getApprovalType().equals(approvalType)) {
                    List<Map<String, Object>> resList = roleDao.queryTemp(BaseUtils.map("role_id", relationId));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("role_name"))) {
                            message = ApprovalTypeEnum.TYPE026.getApprovalTypeName() + ":" + resList.get(0).get("role_name");
                        }
                    }
                }
                map.put("message", message);

                //add by wxm 20210910
                if (ObjectUtil.isNotEmpty(map.get("gt_approval_user"))) {
                    //有沟通人员
                    map.put("approval_status_name", "审批(沟通)中");
                }
            }
        }
        typeRuslt.put("total", toatl);
        typeRuslt.put("approvalList", result);

        logger.info("Tflow022Service执行完成");
        return typeRuslt;
    }

    private void add(Object object, Map<String, Object> p, String name) {
        if (object != null) {
            p.put(name, "%" + object.toString() + "%");
        }

    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (String key : data.keySet()) {
            if (data.get(key) == null || "".equals(data.get(key))) {

            } else {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }

}
