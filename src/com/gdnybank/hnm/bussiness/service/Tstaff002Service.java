package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.service.OperationFlowRecordService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 人员新增/更新
 * @author: zjh
 */
@Service
public class Tstaff002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tstaff002Service.class);

    @Autowired
    HManagerDao managerDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    private SysAccountRoleDao sysAccountRoleDao;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    SysRoleDao sysRoleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private HTeamDao hTeamDao;
    @Autowired
    private TApprovalDao tApprovalDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    private ApprovalService approvalService;
    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Tstaff002ServiceException", "approval_type为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("mgr_id"))) {
            throw new BusinessException("Tstaff002ServiceException", "mgr_id为必传字段");
        } else {
            p.put("mgr_id", p.get("mgr_id").toString().trim());
        }
        if (ObjectUtil.isEmpty(p.get("use_up"))) {
            throw new BusinessException("Tstaff002ServiceException", "use_up为必传字段");
        }
        String approval_type = p.get("approval_type").toString();
        String use_up = p.get("use_up").toString();
        StringBuilder sb = new StringBuilder();
        //停用人员
        if (ApprovalTypeEnum.TYPE021.getApprovalType().equals(approval_type)) {
            //先校验用户下是否有站点
            List<Map<String, Object>> list = hSiteDao.queryForListBycondition(BaseUtils.map("mgr_id", p.get("mgr_id"),
                    "is_delete", "0", "approval_status", "2", "status", "'0','1','2'"));
            if (list != null && list.size() > 0) {
                sb.append("用户").append(p.get("mgr_id")).append("有管辖站点，不允许停用!");
              //  throw new BusinessException("Tstaff002ServiceException", "用户" + p.get("mgr_id") + "有管辖站点，不允许停用");
            }
            //校验用户是否团队/片区负责人
            List<Map<String, Object>> listTeam = hTeamDao.queryList(BaseUtils.map("team_leader", p.get("mgr_id"),
                    "is_delete", "0", "approval_status", "2"));
            if (listTeam != null && listTeam.size() > 0) {
                sb.append("用户").append(p.get("mgr_id")).append("是团队/片区负责人，不允许停用!");
                //throw new BusinessException("Tstaff002ServiceException", "用户" + p.get("mgr_id") + "是团队/片区负责人，不允许停用");
            }
            List<Map<String, Object>> approvalList = tApprovalDao.queryByApprovalUser(BaseUtils.map("approval_status", "1", "approval_user", p.get("mgr_id")));
            if (approvalList != null && approvalList.size() > 0) {
                sb.append("用户").append(p.get("mgr_id")).append("存在未审批的工单，请先处理再停用!");
//                throw new BusinessException("Tstaff002ServiceException", "用户" + p.get("mgr_id") + "存在未审批的工单，请先处理再停用");
//
            }

            List<Map<String, Object>> patrolLogContentList = hPatrolLogContentDao.queryForExport(BaseUtils.map("status", 0, "responsible", p.get("mgr_id")));
            if (patrolLogContentList != null && patrolLogContentList.size() > 0) {
                sb.append("用户").append(p.get("mgr_id")).append("存在未处理的风险信息，请先处理再停用!");
//                throw new BusinessException("Tstaff002ServiceException", "用户" + p.get("mgr_id") + "存在未处理的风险信息，请先处理再停用");
//
            }
            List<Map<String, Object>> result = tApprovalApplyDao.queryForFlow(BaseUtils.map("operator", p.get("mgr_id"), "approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus()));
            if (result != null && result.size() > 0) {
                sb.append("用户").append(p.get("mgr_id")).append("存在未办结的申请工单，请先处理再停用!");
//                throw new BusinessException("Tstaff002ServiceException", "用户" + p.get("mgr_id") + "存在未办结的申请工单，请先处理再停用");
//                errorMap.put("approvalList",approvalList);
            }
        }
        if (!sb.toString().equals("")) {
            throw new BusinessException("Tstaff002ServiceException",sb.toString());
        }
        if (ApprovalTypeEnum.TYPE002.getApprovalType().equals(approval_type)) {
            //新增
            List<Map<String, Object>> list = managerDao.queryList(BaseUtils.map(
                    "mgr_id", p.get("mgr_id"),
                    "is_delete", "0",
                    "approval_status", "1,2"));
            if (list.size() > 0) {
                throw new BusinessException("Tstaff002ServiceException", "已存在该员工，请勿重复新增");
            }
        }

        boolean flag = false;
        if (ObjectUtil.isNotEmpty(p.get("req_place"))) {
            String req_place = p.get("req_place").toString();
            if ("1".equals(req_place)) {
                //由我的申请页面过来
                flag = true;
            }
        }

        if (approvalService.checkApproval(p,approval_type)){
            use_up = "0";
        }
        if (flag) {
            //由我的申请页面过来
            if (ObjectUtil.isNotEmpty(p.get("apply_id"))) {
                String apply_id = p.get("apply_id").toString();
                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("id", apply_id));
                if (list != null && list.size() > 0) {
                    String relation_id = list.get(0).get("relation_id").toString();
                    p.put("id", relation_id);
                    p.put("creator", MfpContextHolder.getLoginId());
                    p.put("update_time", DateUtil.now());
                    p.put("is_delete", "0");
                    //角色信息
                    List<String> roleList = (List<String>) p.get("roleids");
                    for (int i = 0; i < roleList.size(); i++) {
                        String role_id = roleList.get(i);
                        p.put("temp_role_id", role_id);
                    }
                    //修改保存人员信息
                    p.put("temp_save", "0");
                    managerDao.updateByPk(p);

                    //更新申请表状态
                    Map<String, Object> parms = new HashMap<>();
                    parms.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());
                    parms.put("approval_status_name", ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
                    parms.put("id", apply_id);
                    parms.put("approval_type", approval_type);
                    tApprovalApplyDao.updateStatus(parms);
                } else {
                    throw new BusinessException("Tstaff002ServiceException", "未查到该申请记录");
                }
            } else {
                throw new BusinessException("Tstaff002ServiceException", "apply_id为必传字段");
            }
            //保存操作记录
            recordService.saveOperationRecord(p);
        } else {
            String id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            p.put("creator", MfpContextHolder.getLoginId());
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("update_time", createTime);
            p.put("is_delete", "0");

            //角色信息
            List<String> roleList = (List<String>) p.get("roleids");
            for (int i = 0; i < roleList.size(); i++) {
                String role_id = roleList.get(i);
                p.put("temp_role_id", role_id);
            }
            //保存人员信息
            p.put("temp_save", "0");
            managerDao.save(p);

            if ("0".equals(use_up)) {
                //未启用审批 走新流程
                hnmCommService.saveApprovalApplyNoProcess(p, BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"), "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));

                List<Map<String, Object>> managerList = managerDao.queryList(BaseUtils.map(
                        "mgr_id", p.get("mgr_id"), "approval_status", "2"));

                if (ApprovalTypeEnum.TYPE021.getApprovalType().equals(approval_type)) {
                    //人员删除

                    for (Map<String, Object> managerMap : managerList) {
                        String managerMapId = managerMap.get("id").toString();
                        managerDao.updateByPk(BaseUtils.map("id", managerMapId, "is_delete", "1"));
                    }
                    //删除用户表
                    Map<String, Object> map = new HashMap<>();
                    map.putAll(p);
                    List<Map<String, Object>> accountList = sysAccountDao.queryForList(BaseUtils.map("account_id", p.get(
                            "mgr_id")));
                    if (accountList != null && accountList.size() > 0) {
                        //查询角色 看是否可以删除
                        Map<String, Object> userMap = accountList.get(0);
                        List<Map<String, Object>> userRoleList = sysAccountRoleDao.queryForList(BaseUtils.map("account_id",
                                userMap.get("account_id")));
                        if (userRoleList == null || userRoleList.size() == 0) {
                            throw new BusinessException("Tstaff002ServiceException", "该用户未配置角色");
                        }

                        //校验角色级别
                        String userRoleLevel = "";
                        List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", userRoleList.get(0).get("role_id")));
                        if (mapList != null && mapList.size() > 0) {
                            userRoleLevel = mapList.get(0).get("role_level").toString();
                        }
                        if (!"2".equals(userRoleLevel) && !"3".equals(userRoleLevel) && !"4".equals(userRoleLevel)) {
                            throw new BusinessException("Tstaff002ServiceException", "该用户角色不在此审批流可删除范围内");
                        }
                    }
                    //删除用户表
                    sysAccountDao.deleteByPk(BaseUtils.map("account_id", map.get("mgr_id")));
                    //删除用户角色信息表
                    sysAccountRoleDao.delete(BaseUtils.map("account_id", map.get("mgr_id")));

                } else {
                    for (Map<String, Object> managerMap : managerList) {
                        String managerMapId = managerMap.get("id").toString();
                        if (id.equals(managerMapId)) continue;
                        managerDao.updateByPk(BaseUtils.map("id", managerMapId, "is_delete", "1"));
                    }
                    //更改用户表  添加或更新用户
                    Map<String, Object> map = new HashMap<>();
                    map.putAll(p);
                    List<Map<String, Object>> accountList = sysAccountDao.queryForList(BaseUtils.map("account_id", p.get(
                            "mgr_id")));
                    if (accountList != null && accountList.size() > 0) {
                        //更新
                        //查询角色 看是否可以更新
                        Map<String, Object> userMap = accountList.get(0);
                        List<Map<String, Object>> userRoleList = sysAccountRoleDao.queryForList(BaseUtils.map("account_id",
                                userMap.get("account_id")));
                        if (userRoleList == null || userRoleList.size() == 0) {
                            throw new BusinessException("Tstaff002ServiceException", "该用户已存在并未配置角色");
                        }

                        //校验角色级别
                        String userRoleLevel = "";
                        List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", userRoleList.get(0).get("role_id")));
                        if (mapList != null && mapList.size() > 0) {
                            userRoleLevel = mapList.get(0).get("role_level").toString();
                        }
                        if (!"2".equals(userRoleLevel) && !"3".equals(userRoleLevel) && !"4".equals(userRoleLevel)) {
                            throw new BusinessException("Tstaff002ServiceException", "该用户已存在且其角色不在此审批流可修改范围内");
                        }
                        map.put("account_id", map.get("mgr_id"));
                        map.put("name", map.get("mgr_name"));
                        map.put("email", map.get("e_mail"));
                        map.remove("create_time");
                        sysAccountDao.updateByPk(map);
                    } else {
                        //新增
                        map.put("account_id", map.get("mgr_id"));
                        map.put("name", map.get("mgr_name"));
                        map.put("email", map.get("e_mail"));
                        map.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                        map.put("login_pwd", passwordEncoder.encode("123456"));//默认密码
                        //错误次数默认0
                        map.put("error_count", "0");
                        //用户状态
                        map.put("status", "1");
                        map.put("deleted", 0);
                        sysAccountDao.save(map);
                    }
                    //保存用户角色信息 先删再加
                    sysAccountRoleDao.delete(BaseUtils.map("account_id", map.get("mgr_id")));
                    sysAccountRoleDao.save(BaseUtils.map("role_id", map.get("temp_role_id"), "account_id", map.get("mgr_id")));
                }
            } else {
                //启用审批 走之前的流程
                //审批逻辑
                hnmCommService.saveApprovalApply(p, BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"), "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));
            }

            //删除暂存记录
            if (approval_type.equals(ApprovalTypeEnum.TYPE002.getApprovalType())) {
                //查询
                List<Map<String, Object>> list = managerDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId(), "temp_save", "1"));
                if (list != null && list.size() > 0) {
                    managerDao.deleteByPk(BaseUtils.map("id", list.get(0).get("id")));
                }
            }
        }

        logger.info("Tstaff002Service执行完成");
        return BaseUtils.map("success", "1");
    }
}
