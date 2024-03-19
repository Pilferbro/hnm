package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HFileInfoDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.dao.TApprovalApplyDao;
import com.gdnybank.hnm.pub.enums.ApprovalStatusEnum;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.service.OperationFlowRecordService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 站点新增/修改/pos申请/pos及开业申请/退出
 * @author: zjh
 */
@Service
public class Tsite002Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite002Service.class);

    @Autowired
    HSiteDao siteDao;
    @Autowired
    HFileInfoDao hFileInfoDao;
    @Autowired
    HnmCommService hnmCommService;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    private ApprovalService approvalService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("approval_type"))) {
            throw new BusinessException("Tsite002ServiceException", "approval_type为必传字段");
        }
        if (ObjectUtil.isEmpty(p.get("use_up"))) {
            throw new BusinessException("Tsite002ServiceException", "use_up为必传字段");
        }
        String approval_type = p.get("approval_type").toString();
        String use_up = p.get("use_up").toString();

        //非退出流程，都要检验站长风险信息
        if (!ApprovalTypeEnum.TYPE008.getApprovalType().equals(approval_type)) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", "1");
            map.put("cert_num", p.get("master_identify_no"));
            map.put("card_no", p.get("card_no"));

            //查询站长风险情况
            if (approvalService.checkRiskCustomer(map)) {
                throw new BusinessException("Tsite002ServiceException", "该站长存在风险，不得申请支付服务点站长，若有疑问，请联系银行工作人员");
            }
        }
        //保证开业、试营业终端编号唯一性
        if (ApprovalTypeEnum.TYPE006.getApprovalType().equals(approval_type)
                || ApprovalTypeEnum.TYPE007.getApprovalType().equals(approval_type)
                || ApprovalTypeEnum.TYPE017.getApprovalType().equals(approval_type)) {
            String str = approvalService.checkTerminalInfo(p);
            if (!str.equals("pass")) {
                throw new BusinessException("Tsite002ServiceException", str);
            }

        }
        //新审批流程待用
//        if(ApprovalTypeEnum.TYPE007.getApprovalType().equals(approval_type)){
//            //开业申请 校验条件
//            checkItem(p);
//        }

        if (ApprovalTypeEnum.TYPE004.getApprovalType().equals(approval_type) || ApprovalTypeEnum.TYPE022.getApprovalType().equals(approval_type)) {
            //试营业
            p.put("status", "0");//试营业
        } else if (ApprovalTypeEnum.TYPE006.getApprovalType().equals(approval_type)) {
            p.put("status", "1");//试营业（已申请pos）
        } else if (ApprovalTypeEnum.TYPE007.getApprovalType().equals(approval_type)) {
            p.put("status", "2");//开业
        } else if (ApprovalTypeEnum.TYPE008.getApprovalType().equals(approval_type)) {
            p.put("status", "3");//退出
        }

        boolean flag = false;
        if (ObjectUtil.isNotEmpty(p.get("req_place"))) {
            String req_place = p.get("req_place").toString();
            if ("1".equals(req_place)) {
                //由我的申请页面过来
                flag = true;
            }
        }
        //是否免审批
        if (approvalService.checkApproval(p, approval_type)) {
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
                    p.put("update_time", DateUtil.now());
                    p.put("creator", MfpContextHolder.getLoginId());
                    p.put("is_delete", "0");

                    //修改保存站点信息
                    p.put("temp_save", "0");
                    siteDao.updateByPk(p);

                    //更新图片资料
                    hFileInfoDao.updateBusiId(BaseUtils.map("busi_id", relation_id));

                    //新增图片资料
                    if (ObjectUtil.isNotEmpty(p.get("fileIdList"))) {
                        List<String> fileIdList = (List<String>) p.get("fileIdList");
                        if (fileIdList != null && fileIdList.size() > 0) {
                            String ids = "";
                            for (String fileId : fileIdList) {
                                ids += ",'" + fileId + "'";
                            }
                            if (StringUtils.isNotEmpty(ids) && ids.startsWith(",")) {
                                ids = ids.substring(1, ids.length());
                                hFileInfoDao.updateByPkS(BaseUtils.map("ids", ids, "busi_id", relation_id));
                            }
                        }
                    }

                    //更新申请表状态
                    Map<String, Object> parms = new HashMap<>();
                    parms.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());
                    parms.put("approval_status_name", ApprovalStatusEnum.STATUS_1.getApprovalStatusName());
                    parms.put("id", apply_id);
                    parms.put("approval_type", approval_type);
                    tApprovalApplyDao.updateStatus(parms);
                } else {
                    throw new BusinessException("Tsite002ServiceException", "未查到该申请记录");
                }
            } else {
                throw new BusinessException("Tsite002ServiceException", "apply_id为必传字段");
            }
            //保存操作记录
            recordService.saveOperationRecord(p);
        } else {
            //按照规则生成id
            String id = IdUtil.randomUUID().replace("-", "");
            p.put("id", id);
            p.put("creator", MfpContextHolder.getLoginId());
            String createTime = DateUtil.now();
            p.put("create_time", createTime);
            p.put("update_time", createTime);
            p.put("is_delete", "0");

            if ("0".equals(use_up)) {
                //未启用审批
                if (approval_type.equals(ApprovalTypeEnum.TYPE004.getApprovalType())) {
                    //新增站点
                    //新增时生成站点编号
                    //site_no站点编号生产规则---6位数
                    String siteNo = "300001";
                    //查询最大站点编号
                    List<Map<String, Object>> maxList = siteDao.queryMaxSiteNo(BaseUtils.map());
                    if (maxList != null && maxList.size() > 0) {
                        Map<String, Object> map = maxList.get(0);
                        if (ObjectUtil.isNotEmpty(map) && ObjectUtil.isNotEmpty(map.get("site_no"))) {
                            String maxSiteNo = map.get("site_no").toString();
                            siteNo = Long.parseLong(maxSiteNo) + 1 + "";
                        }
                    }
                    p.put("site_no", siteNo);
                }
            }

            p.put("temp_save", "0");
            siteDao.save(p);
            //新增图片资料
            if (ObjectUtil.isNotEmpty(p.get("fileIdList"))) {
                List<String> fileIdList = (List<String>) p.get("fileIdList");
                if (fileIdList != null && fileIdList.size() > 0) {
                    String ids = "";
                    for (String fileId : fileIdList) {
                        //先查询
                        Map<String, Object> map = hFileInfoDao.queryForMap(BaseUtils.map("id", fileId));
                        if (ObjectUtil.isNotEmpty(map.get("busi_id"))) {
                            //新增一条
                            //按照规则生成id
                            String file_id = IdUtil.randomUUID().replace("-", "");
                            map.put("id", file_id);
                            map.put("busi_id", id);
                            hFileInfoDao.save(map);
                        } else {
                            ids += ",'" + fileId + "'";
                        }
                    }
                    if (StringUtils.isNotEmpty(ids) && ids.startsWith(",")) {
                        ids = ids.substring(1, ids.length());
                        hFileInfoDao.updateByPkS(BaseUtils.map("ids", ids, "busi_id", id));
                    }
                }
            }
            //审批逻辑
            if ("0".equals(use_up)) {
                //未启用审批 走新流程
                hnmCommService.saveApprovalApplyNoProcess(p, BaseUtils.map("approval_type",
                        p.get("approval_type"), "relation_id", id,
                        "operator", MfpContextHolder.getLoginId(),
                        "approval_user", p.get("approval_user"), "update_items", p.get("update_items"), "suggestion", p.get("suggestion")));

                List<Map<String, Object>> siteList = siteDao.queryForList(BaseUtils.map(
                        "site_no", p.get("site_no"), "approval_status", "2"));
                for (Map<String, Object> siteMap : siteList) {
                    String siteId = siteMap.get("id").toString();
                    if (id.equals(siteId)) continue;
                    siteDao.updateByPk(BaseUtils.map("id", siteId, "is_delete", "1"));
                    hFileInfoDao.updateByBusiId(BaseUtils.map("busi_id", siteId, "is_delete", "1"));
                }
                //站点审批通过后 调用销管系统的接口 同步数据
                List<Map<String, Object>> datalist = siteDao.queryForList(BaseUtils.map("id", id));
                if (datalist != null && datalist.size() > 0) {
                    Map<String, Object> map = datalist.get(0);
                    if (ApprovalTypeEnum.TYPE004.getApprovalType().equals(approval_type)) {
                        map.put("trans_type", "A");
                    } else {
                        map.put("trans_type", "U");
                    }
                    hnmCommService.sendToXG(map);
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
            if (approval_type.equals(ApprovalTypeEnum.TYPE004.getApprovalType())) {
                //查询
                List<Map<String, Object>> list = siteDao.queryForList(BaseUtils.map("creator", MfpContextHolder.getLoginId(), "temp_save", "1"));
                if (list != null && list.size() > 0) {
                    siteDao.deleteByPk(BaseUtils.map("id", list.get(0).get("id")));
                }
            }
        }

        logger.info("Tsite002Service执行完成");
        return BaseUtils.map("success", "1");
    }

    private void checkItem(Map<String, Object> p) {
        if (ObjectUtil.isEmpty(p.get("occupation"))) {
            throw new BusinessException("Tsite002ServiceException", "站长候选人信息情况为必传字段");
        }
        String occupation = p.get("occupation").toString();
        if ("0".equals(occupation) || "1".equals(occupation) || "2".equals(occupation)) {
            //企业主或个体户/村委及家属/企事单位职员
            throw new BusinessException("Tsite002ServiceException", "站长候选人信息情况不满足条件,禁止开业申请");
        }

        if (ObjectUtil.isEmpty(p.get("education"))) {
            throw new BusinessException("Tsite002ServiceException", "学历为必传字段");
        }
        String education = p.get("education").toString();
        if ("0".equals(education) || "1".equals(education)) {
            //初中及以下/高中(含中专等)
            throw new BusinessException("Tsite002ServiceException", "站长学历不满足条件,禁止开业申请");
        }

        if (ObjectUtil.isEmpty(p.get("master_identify_no"))) {
            throw new BusinessException("Tsite002ServiceException", "站长身份证号码为必传字段");
        }
        String age = hnmCommService.evaluate(String.valueOf(p.get("master_identify_no")));
        if (Integer.parseInt(age) > 40) {
            //40
            throw new BusinessException("Tsite002ServiceException", "站长年龄不满足条件,禁止开业申请");
        }


        if (ObjectUtil.isEmpty(p.get("credit_investigation"))) {
            throw new BusinessException("Tsite002ServiceException", "站长征信逾期情况为必传字段");
        }
        String credit_investigation = p.get("credit_investigation").toString();
        if ("0".equals(credit_investigation)) {
            //当期有逾期
            throw new BusinessException("Tsite002ServiceException", "站长征信逾期情况不满足条件,禁止开业申请");
        }

        if (ObjectUtil.isEmpty(p.get("max_due_term"))) {
            throw new BusinessException("Tsite002ServiceException", "站长近一年贷款最长连续逾期期数为必传字段");
        }
        String max_due_term = p.get("max_due_term").toString();
        if (Integer.parseInt(max_due_term) > 2) {
            //2
            throw new BusinessException("Tsite002ServiceException", "站长近一年贷款最长连续逾期期数不满足条件,禁止开业申请");
        }

        if (ObjectUtil.isEmpty(p.get("total_due_term"))) {
            throw new BusinessException("Tsite002ServiceException", "站长近一年贷款累计逾期期数为必传字段");
        }
        String total_due_term = p.get("total_due_term").toString();
        if (Integer.parseInt(total_due_term) > 2) {
            //2
            throw new BusinessException("Tsite002ServiceException", "站长近一年贷款累计逾期期数不满足条件,禁止开业申请");
        }

        if (ObjectUtil.isEmpty(p.get("credit_bal"))) {
            throw new BusinessException("Tsite002ServiceException", "站长信用类、保证类贷款余额为必传字段");
        }
        String credit_bal = p.get("credit_bal").toString();
        if (Double.parseDouble(credit_bal) > 500000) {
            //500000
            throw new BusinessException("Tsite002ServiceException", "站长信用类、保证类贷款余额不满足条件,禁止开业申请");
        }


    }

}
