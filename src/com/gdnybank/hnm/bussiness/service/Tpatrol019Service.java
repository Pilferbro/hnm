package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HRectificationDetailsDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.enums.RiskLevelEnum;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.service.OperationFlowRecordService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 风险处置整改
 *
 * @author: phl
 */
@Service
@Transactional
public class Tpatrol019Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol019Service.class);
    @Autowired
    private HRectificationDetailsDao detailsDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    HFileInfoDao hFileInfoDao;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    SysAccountRoleDao sysAccountRoleDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        if (ObjectUtil.isNull(p.get("id"))) {
            throw new BusinessException("Tpatrol012ServiceException", "id为必传字段");
        }
        if (ObjectUtil.isNull(p.get("schedule"))) {
            throw new BusinessException("Tpatrol012ServiceException", "整改进度必填");
        }
        if (ObjectUtil.isNull(p.get("written"))) {
            throw new BusinessException("Tpatrol012ServiceException", "反馈人姓名必填");
        }
        if (ObjectUtil.isNull(p.get("status"))) {
            throw new BusinessException("Tpatrol012ServiceException", "整改状态必填");
        }
        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Tpatrol012ServiceException", "approval_type为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("use_up"))) {
            throw new BusinessException("Tpatrol012ServiceException", "use_up为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("risk_level"))) {
            throw new BusinessException("Tpatrol012ServiceException", "risk_level为必传字段");
        }
        String relation_id = "";
        if (ObjectUtil.isNotEmpty(p.get("relation_id"))) {
            relation_id = (String) p.get("relation_id");
        }
        String approval_type = p.get("approval_type").toString();
        String use_up = p.get("use_up").toString();
        String risk_level = String.valueOf(p.get("risk_level"));
        String patrol_lc_id = String.valueOf(p.get("id"));
        p.remove("id");
        p.put("patrol_lc_id", patrol_lc_id);
        p.put("over_due_term", p.get("risk_level"));
        p.put("updatetime", DateUtil.now());
        p.put("source", evn.get("userid"));
        p.put("suggestion", p.get("schedule"));
        p.put("deleted", 0);

        int status = Integer.parseInt(p.get("status").toString());
        String written = String.valueOf(p.get("written"));
        boolean flag = false;
        Map<String, Object> approvalMap = new HashMap<>();
//        if (ObjectUtil.isNotEmpty(p.get("req_place"))) {
//            String req_place = p.get("req_place").toString();
//            if ("1".equals(req_place)) {
//                //由我的申请页面过来
//                flag = true;
//            }
//        }
//        List<Map<String, Object>> approvalApplyList = tApprovalApplyDao.queryForList(BaseUtils.map("approval_type",
//                approval_type, "relation_id", relation_id, "approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus()));
//        if (approvalApplyList != null && approvalApplyList.size() > 0) {
//            throw new BusinessException("Tflow020ServiceException", "该工单已在审批中，请勿重复提交");
//        }

        //未整改状态无需审批
//        if (0 == status) {
            //判断relation_id是否为空
            //存在则修改，否则新增
            if (!"".equals(relation_id)) {
                p.put("id",relation_id);
                detailsDao.updateById(p);
            } else {
                String id = IdUtil.randomUUID().replace("-", "");
                p.put("id", id);
                detailsDao.save(p);
            }
            return BaseUtils.map("success", "1");
//        }
//        List<Map<String, Object>> roleId = sysAccountRoleDao.queryForList(BaseUtils.map("account_id", written));
//        approvalMap.put("written", roleId.get(0).get("role_id"));
//        approvalMap.put("risk_level", RiskLevelEnum.findByValue(risk_level).getItem());
//        approvalMap.put("status", p.get("status"));

        //是否存在免审核项
//        if (approvalService.checkApproval(approvalMap, approval_type)) {
//            use_up = "0";
//        }
//        if ("0".equals(use_up)) {
//            p.put("tmp_status", p.get("status"));
//        } else {
//            //需要审批，保存临时整改状态
//            p.put("tmp_status", p.get("status"));
//            p.remove("status");
//        }

//        if (flag) {
////            //由我的申请页面过来
//            if (ObjectUtil.isNotEmpty(p.get("apply_id"))) {
//                String apply_id = p.get("apply_id").toString();
//                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("id", apply_id));
//                if (list != null && list.size() > 0) {
//                    p.put("id",p.get("relation_id"));
//                    //修改保存站点信息
//                    detailsDao.updateById(p);
//
//                    //更新申请表状态
//                    Map<String, Object> parms = new HashMap<>();
//
//                    parms.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());
//                    parms.put("approval_status_name", ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
//                    parms.put("id", apply_id);
//                    parms.put("approval_type", approval_type);
//                    tApprovalApplyDao.updateStatus(parms);
//                } else {
//                    throw new BusinessException("Tpatrol012ServiceException", "未查到该申请记录");
//                }
//            } else {
//                throw new BusinessException("Tpatrol012ServiceException", "apply_id为必传字段");
//            }
//            //保存操作记录
//            recordService.saveOperationRecord(p);
//        } else {
//            try {
//
//                //存在则修改为删除状态再新增新的数据
//                if (!"".equals(relation_id)) {
//
//                    detailsDao.updateById(BaseUtils.map("deleted",1,"id",relation_id));
//                }
//                String id = IdUtil.randomUUID().replace("-", "");
//                p.put("id", id);
//                detailsDao.save(p);
//
//            } catch (Exception e) {
//                logger.info("Tpatrol012ServiceException,整改失败", e);
//                throw new BusinessException("Tpatrol012ServiceException", "整改失败");
//            }
//            //审批逻辑
//            if ("0".equals(use_up)) {
//                //未启用审批 走新流程
//                hnmCommService.saveApprovalApplyNoProcess(p, BaseUtils.map("approval_type",
//                        p.get("approval_type"), "relation_id", p.get("id"),
//                        "operator", MfpContextHolder.getLoginId(),
//                        "approval_user", "admin", "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));
//            } else {
//
//                //启用审批 走之前的流程
//                //审批逻辑
//                hnmCommService.saveApprovalApply(p, BaseUtils.map("approval_type",
//                        p.get("approval_type"), "relation_id", p.get("id"),
//                        "operator", MfpContextHolder.getLoginId(),
//                        "approval_user", p.get("approval_user"), "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));
//            }
//            //更改整改状态
//            if (approval_type.equals(ApprovalTypeEnum.TYPE023.getApprovalType())) {
//                //查询
//                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("relation_id", relation_id, "approval_type", approval_type, "approval_status", "2"));
//                if (list != null && list.size() > 0) {
//                    detailsDao.updateById(BaseUtils.map("id", p.get("id"), "status", status, "source", evn.get("userid")));
//                }
//
//            }
//        }
//        return BaseUtils.map("success", "1");
    }
}