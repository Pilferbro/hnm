package com.gdnybank.hnm.flow.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.ApprovalTypeEnum;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询审批历史列表
 *
 * @author: wxm
 */
@Service
public class Tflow021Service extends TXBaseService {
    private static final Log logger = LogFactory.getLog(Tflow021Service.class);

    @Autowired
    private TApprovalHisDao tApprovalHisDao;
    @Autowired
    private TApprovalConnectHisDao tApprovalConnectHisDao;
    @Autowired
    private TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    private HManagerDao hManagerDao;
    @Autowired
    private HTeamDao hTeamDao;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private TOperationDao operationDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        List<Map<String, Object>> list = new ArrayList<>();

        List<Map<String, Object>> list1 = tApprovalApplyDao.queryForListForHis(BaseUtils.map("id", p.get("apply_id")));
        if (list1 != null && list1.size() > 0) {
            Map<String, Object> map = list1.get(0);
            String message = "";
            String approval_type = map.get("approval_type").toString();
            String approval_type_name = String.valueOf(map.get("approval_type_name"));
            String relation_id = map.get("relation_id").toString();

            //查询操作流程
            List<Map<String, Object>> operationList = operationDao.queryForList(BaseUtils.map("approval_his_apply_id", p.get("apply_id")));
            if (operationList != null && operationList.size() > 0) {
                for (Map<String, Object> oMap : operationList) {
                    Map<String, Object> rMap = new HashMap<>();
                    rMap.put("create_time", oMap.get("created"));
                    rMap.put("role_name", "起草节点");
                    rMap.put("approval_user", map.get("operator"));
                    rMap.put("name", map.get("name"));
                    rMap.put("approval_opinion", "提交:"+approval_type_name);
                    rMap.put("approval_desc",oMap.get("suggestion"));
                    list.add(rMap);
                }
            } else {

                Map<String, Object> rMap = new HashMap<>();
                rMap.put("create_time", map.get("create_time"));
                rMap.put("role_name", "起草节点");
                rMap.put("approval_user", map.get("operator"));
                rMap.put("name", map.get("name"));
                rMap.put("approval_opinion", "提交:"+approval_type_name);

                if (ApprovalTypeEnum.TYPE002.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员新增申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE003.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE004.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点新增（试营业）申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE005.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE006.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点助农POS申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE007.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点开业申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE008.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点退出申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE009.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队新增申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE010.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队基本信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE011.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队类型调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE012.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队所属机构调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE013.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队负责人调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE014.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员角色调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE015.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员所属机构调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE016.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点站长信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE017.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点POS信息调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE018.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点管辖客户经理调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE019.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点落地支行调整申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE020.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hTeamDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("branch_name"))) {
                            message = resList.get(0).get("branch_name") + "团队删除申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE021.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hManagerDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("mgr_name"))) {
                            message = resList.get(0).get("mgr_name") + "人员删除申请";
                        }
                    }
                } else if (ApprovalTypeEnum.TYPE022.getApprovalType().equals(approval_type)) {
                    List<Map<String, Object>> resList = hSiteDao.queryForList(BaseUtils.map("id", relation_id));
                    if (resList != null && resList.size() > 0) {
                        if (ObjectUtil.isNotEmpty(resList.get(0).get("site_name"))) {
                            message = resList.get(0).get("site_name") + "站点转试营业申请";
                        }
                    }
                }

                rMap.put("approval_desc", message);
                list.add(rMap);

                //拷贝新增申请表到操作流程表
                HashMap<String, Object> tempMap = new HashMap<>();
                tempMap.put("id",map.get("id"));
                tempMap.put("approval_his_apply_id",p.get("apply_id"));
                tempMap.put("suggestion",message);
                tempMap.put("approval_type",map.get("approval_type"));
                tempMap.put("created", map.get("create_time"));
                operationDao.save(tempMap);
            }
        }

        List<Map<String, Object>> list2 = tApprovalHisDao.queryForListByTime(BaseUtils.map("apply_id", p.get("apply_id")));
        if (list2 != null && list2.size() > 0) {
            for (Map<String, Object> map : list2) {
                Map<String, Object> rMap = new HashMap<>();
                rMap.put("create_time", map.get("create_time"));
                rMap.put("role_name", map.get("role_name"));
                rMap.put("approval_user", map.get("approval_user"));
                rMap.put("name", map.get("name"));

                String userids = "";
                String usernames = "";
                if (ObjectUtil.isNotEmpty(map.get("to_approval_user"))) {
                    String[] users = map.get("to_approval_user").toString().split(",");
                    for (String userid : users) {
                        userids = userids + ",'" + userid + "'";
                    }
                    if (userids.startsWith(",")) {
                        userids = userids.substring(1, userids.length());
                    }

                    if (StringUtils.isNotEmpty(userids)) {
                        List<Map<String, Object>> mapList = sysAccountDao.selectExistAccountIdInfo(userids);
                        if (mapList != null && mapList.size() > 0) {
                            for (Map<String, Object> usermap : mapList) {
                                usernames = usernames + "," + usermap.get("name");
                            }
                        }
                        if (usernames.startsWith(",")) {
                            usernames = usernames.substring(1, usernames.length());
                        }
                    }
                }

                String msg = "";
                String approval_opinion = map.get("approval_opinion").toString();
                if ("1".equals(approval_opinion)) {
                    //审批通过
                    msg = "通过";
                    if (StringUtils.isNotEmpty(usernames)) {
                        msg = msg + ":下一环节处理人:" + usernames;
                    }
                } else if ("2".equals(approval_opinion)) {
                    //审批不通过
                    msg = "不通过";
                } else if ("3".equals(approval_opinion)) {
                    //转办
                    msg = "转办";
                    if (StringUtils.isNotEmpty(usernames)) {
                        msg = msg + ":" + usernames;
                    }
                } else if ("4".equals(approval_opinion)) {
                    //驳回
                    msg = "驳回";
                } else if ("5".equals(approval_opinion)) {
                    //沟通
                    msg = "沟通";
                    if (StringUtils.isNotEmpty(usernames)) {
                        msg = msg + ":" + usernames;
                    }
                }
                rMap.put("approval_opinion", msg);
                rMap.put("approval_desc", map.get("approval_desc"));
                list.add(rMap);
            }
        }

        List<Map<String, Object>> list3 = tApprovalConnectHisDao.queryForListByTime(BaseUtils.map("apply_id", p.get("apply_id")));
        if (list3 != null && list3.size() > 0) {
            for (Map<String, Object> map : list3) {
                Map<String, Object> rMap = new HashMap<>();
                rMap.put("create_time", map.get("create_time"));
                rMap.put("role_name", map.get("role_name"));
                rMap.put("approval_user", map.get("gt_approval_user"));
                rMap.put("name", map.get("gt_approval_user_name"));

                String msg = "回复";
                if (ObjectUtil.isNotEmpty(map.get("approval_user_name"))) {
                    msg = msg + ":" + map.get("approval_user_name");
                }
                rMap.put("approval_opinion", msg);

                rMap.put("approval_desc", map.get("approval_desc"));
                list.add(rMap);
            }
        }

        //list排序
        //匿名实现Comparator接口进行排序
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                //进行判断
                return ((String) o1.get("create_time")).compareTo((String) o2.get("create_time"));
            }
        });

        logger.info("Tflow021Service执行完成");
        return list;
    }
}
