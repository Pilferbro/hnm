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
import com.nantian.mfp.pub.service.SysParamService;
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
 * @description: 团队/片区新增/更新
 * @author: wxm
 */
@Service
public class Tteam002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tteam002Service.class);

    @Autowired
    HTeamDao hTeamDao;
    @Autowired
    SysBranchDao sysBranchDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    HSiteDao hSiteDao;
    @Autowired
    private ApprovalService approvalService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(ObjectUtil.isEmpty(p.get("approval_type"))){
            throw new BusinessException("Tteam002ServiceException","approval_type为必传字段");
        }
        if(ObjectUtil.isEmpty(p.get("use_up"))){
            throw new BusinessException("Tteam002ServiceException","use_up为必传字段");
        }
        String approval_type =  p.get("approval_type").toString();
        String use_up =  p.get("use_up").toString();

        //校验是否可新增
        List<Map<String, Object>> branchlist = sysBranchDao.queryForList(BaseUtils.map("branch_id", p.get("porgid")));
        if (branchlist != null && branchlist.size() > 0) {
            Map<String, Object> map = branchlist.get(0);
            if ("2".equals(p.get("team_flag"))) {//片区
                if (!"0".equals(map.get("team_flag"))) {
                    throw new BusinessException("Tteam002ServiceException", "上级机构请选择机构");
                }
            }
        } else {
            throw new BusinessException("Tteam002ServiceException", "查无此上级机构");
        }

        boolean flag = false;
        if(ObjectUtil.isNotEmpty(p.get("req_place"))){
            String req_place = p.get("req_place").toString();
            if("1".equals(req_place)){
                //由我的申请页面过来
                flag = true;
            }
        }
        //判断是否存在免审核项
        if (approvalService.checkApproval(p,approval_type)){
            use_up = "0";
        }
        if(flag){
            //由我的申请页面过来
            if(ObjectUtil.isNotEmpty(p.get("apply_id"))){
                String apply_id = p.get("apply_id").toString();
                List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("id", apply_id));
                if(list != null && list.size() >0){
                    String relation_id = list.get(0).get("relation_id").toString();
                    p.put("id", relation_id);
                    p.put("creator",MfpContextHolder.getLoginId());
                    p.put("is_delete", "0");

                    //修改保存团队信息
                    p.put("temp_save", "0");
                    hTeamDao.updateByPk(p);

                    //更新申请表状态
                    Map<String,Object> parms = new HashMap<>();
                    parms.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());
                    parms.put("approval_status_name",ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
                    parms.put("id",apply_id);
                    parms.put("approval_type",approval_type);
                    tApprovalApplyDao.updateStatus(parms);
                }else{
                    throw new BusinessException("Tteam002ServiceException", "未查到该申请记录");
                }
            }else{
                throw new BusinessException("Tteam002ServiceException", "apply_id为必传字段");
            }
            //保存操作记录
            recordService.saveOperationRecord(p);
        }else{
            //新增
            String id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("is_delete", "0");
            p.put("creator", MfpContextHolder.getLoginId());

            if("0".equals(use_up)){
                //未启用审批
                if(approval_type.equals(ApprovalTypeEnum.TYPE009.getApprovalType())){
                    //新增时生成机构编号
                    //获取上级编码生成下级机构号
                    String branch_id = "";
                    List<Map<String, Object>> maxList = hTeamDao.queryMaxOrgid(BaseUtils.map("porgid", p.get(
                            "porgid")));
                    if (maxList != null && maxList.size() > 0) {
                        Map<String, Object> maxMap = maxList.get(0);
                        if (ObjectUtil.isNotEmpty(maxMap) && ObjectUtil.isNotEmpty(maxMap.get("maxid"))) {
                            String maxid = maxMap.get("maxid").toString();
                            branch_id = Long.parseLong(maxid) + 1 + "";
                        } else {
                            branch_id = p.get("porgid") + "001";
                        }
                    } else {
                        branch_id = p.get("porgid") + "001";
                    }
                    p.put("branch_id",branch_id);
                }
            }
            p.put("temp_save","0");
            hTeamDao.save(p);

            if("0".equals(use_up)){
                //未启用审批 走新流程
                hnmCommService.saveApprovalApplyNoProcess(p, BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"),"update_items",p.get("update_items"),"suggestion", p.get("suggestion")));

                List<Map<String, Object>> teamList = hTeamDao.queryList(BaseUtils.map(
                        "branch_id", p.get("branch_id"),"approval_status","2"));

                if (ApprovalTypeEnum.TYPE020.getApprovalType().equals(approval_type)) {
                    //团队删除

                    //删除片区时校验其下是否有团队
                    List<Map<String, Object>> list1 = sysBranchDao.queryForList(BaseUtils.map("porgid", p.get("branch_id")));
                    if(list1 != null && list1.size() >0){
                        throw new BusinessException("Tteam002ServiceException", p.get("branch_id")+"下有团队，不允许删除");
                    }
                    //校验其下是否有客户经理
                    List<Map<String, Object>> list2 = sysAccountDao.queryForList(BaseUtils.map("branch_id", p.get("branch_id")));
                    if(list2 != null && list2.size() >0){
                        throw new BusinessException("Tteam002ServiceException", p.get("branch_id")+"下有客户经理，不允许删除");
                    }
                    //校验其是否为落地支行
                    List<Map<String, Object>> list3 = hSiteDao.queryForListBycondition(BaseUtils.map("branch_id", p.get("branch_id"),
                            "is_delete", "0", "approval_status", "2"));
                    if(list3 != null && list3.size() >0){
                        throw new BusinessException("Tbranch005ServiceException", p.get("branch_id")+"为站点落地支行，不允许删除");
                    }

                    for (Map<String, Object> teamMap : teamList){
                        String teamId = teamMap.get("id").toString();
                        hTeamDao.updateByPk(BaseUtils.map("id",teamId,"is_delete","1"));
                    }
                    //删除机构表
                    Map<String, Object> map = new HashMap<>();
                    map.putAll(p);
                    sysBranchDao.deleteByPk(map);
                }else{
                    for (Map<String, Object> teamMap : teamList){
                        String teamId = teamMap.get("id").toString();
                        if (id.equals(teamId)) continue;
                        hTeamDao.updateByPk(BaseUtils.map("id",teamId,"is_delete","1"));
                    }

                    //更改机构表  添加或更新机构
                    Map<String, Object> map = new HashMap<>();
                    map.putAll(p);
                    List<Map<String, Object>> branchList = sysBranchDao.queryForList(BaseUtils.map("branch_id", p.get(
                            "branch_id")));
                    String ruralBranchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_RURALBRANCHID", "0000");
                    if(branchList != null && branchList.size()>0){
                        //更新
                        map.remove("create_time");
                        map.put("rural_branch_id",ruralBranchId);
                        map.put("bran_level","2");
                        sysBranchDao.updateByPk(map);
                    }else{
                        //新增
                        map.put("rural_branch_id",ruralBranchId);
                        map.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                        map.put("bran_level","2");
                        sysBranchDao.save(map);
                    }
                }
            }else{
                //启用审批 走之前的流程
                //审批逻辑
                hnmCommService.saveApprovalApply(p,BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"),"update_items",p.get("update_items"),"suggestion", p.get("suggestion")));
            }

            //删除暂存记录
            if(approval_type.equals(ApprovalTypeEnum.TYPE009.getApprovalType())){
                //查询
                List<Map<String, Object>> list = hTeamDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId(),"temp_save", "1"));
                if(list != null && list.size()>0){
                    hTeamDao.deleteByPk(BaseUtils.map("id",list.get(0).get("id")));
                }
            }
        }

        logger.info("Tteam002Service执行完成");
        return BaseUtils.map("success","1");
    }
}
