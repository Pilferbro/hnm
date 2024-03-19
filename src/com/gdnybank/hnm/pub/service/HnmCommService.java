package com.gdnybank.hnm.pub.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.enums.*;
import com.gdnybank.hnm.pub.security.Md5Utils;
import com.gdnybank.hnm.pub.utils.Utils;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.msghandle.MessageAssemble;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.dao.CliTxinfoDao;
import com.nantian.mfp.pub.dao.SysParamDao;
import com.nantian.mfp.pub.service.SysParamService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nantian.mfp.codegen.service.CodeGenService;
import com.nantian.mfp.pub.service.SequenceService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.SocketException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * desc:公共交易
 *
 * @author pzz date:2015年10月16日 下午4:51:19
 */
@Service
@Transactional
public class HnmCommService {

    private Logger logger = Logger.getLogger(HnmCommService.class);
    /**
     * esb调用服务
     */
    @Autowired
    private EsbMsgProcService esbMsgProcService;
    @Autowired
    private MfpJsonMsgProcService mfpJsonMsgProcService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private CodeGenService codeGenService;
    @Autowired
    private SysParamDao sysParamDao;
    @Autowired
    private CliTxinfoDao cliTxinfoDao;
    @Autowired
    SysAccountRoleDao sysAccountRoleDao;
    @Autowired
    SysAccountDao sysAccountDao;
    @Autowired
    SysBranchDao sysBranchDao;
    @Autowired
    TApprovalProcessDao tApprovalProcessDao;
    @Autowired
    TApprovalApplyDao tApprovalApplyDao;
    @Autowired
    TApprovalDao tApprovalDao;
    @Autowired
    HManagerDao managerDao;
    @Autowired
    HSiteDao siteDao;
    @Autowired
    HFileInfoDao fileInfoDao;
    @Autowired
    TFlowNoDao tFlowNoDao;
    @Autowired
    HTeamDao hTeamDao;
    @Autowired
    HnmCommDao hnmCommDao;
    @Autowired
    TempBranchInfoDao tempBranchInfoDao;
    @Autowired
    SysRoleDao sysRoleDao;
    @Resource
    private MessageAssemble messageAssemble;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TtimpntDao ttimpntDao;
    @Autowired
    private TperprpDao tperprpDao;
    @Autowired
    private ThfclitDao thfclitDao;
    @Autowired
    private ThfacctDao thfacctDao;
    @Autowired
    private CupstsDao cupstsDao;
    @Autowired
    private TclientDao tclientDao;
    @Autowired
    private OperationFlowRecordService recordService;
    @Autowired
    private HRectificationDetailsDao detailsDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysRoleMobileMenuDao sysRoleMobileMenuDao;

    /**
     * 南粤超级管理员返回1
     * 南粤总行管理员返回2
     * 其他操作员返回9
     * 未查询到角色返回0
     *
     * @param p
     * @return
     */
    public int getUserRole(Map<String, Object> p) {

        String super_manager = MfpContextHolder.getSysParam("role", "super_manager");
        String head_manager = MfpContextHolder.getSysParam("role", "head_manager");

        List<Map<String, Object>> list = sysAccountRoleDao.queryForList(p);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Object roleid = map.get("role_id");
                if (roleid.equals(super_manager)) {
                    return 1;
                }
                if (roleid.equals(head_manager)) {
                    return 2;
                }
            }
            return 9;
        }
        return 0;
    }

    /**
     * 根据用户获取用户级别
     * 1 总行级
     * 2 分行级
     * 3 客户经理级
     * 未找到角色返回0
     *
     * @param p
     * @return
     */
    public int getUserRoleLevel(Map<String, Object> p) {

        List<Map<String, Object>> list = sysAccountRoleDao.queryForList(p);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Object roleid = map.get("role_id");
                //根据角色id查询角色级别
                List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", roleid));
                if (mapList != null && mapList.size() > 0) {
                    return Integer.parseInt(String.valueOf(mapList.get(0).get("role_level")).trim());
                }
            }
        }
        return 0;
    }

    /**
     * 获取请求流水号
     *
     * @return
     */
    public String getReqNumber() {
        String reqNumber = sequenceService.getTableFlowNo("sys_param", "paramkey");
        return sysParamService.getSysParam("ESB_CONFIG", "ESB_CONSUMER_ID", "010404") + reqNumber;
    }

    /**
     * 根据法人编号获取其对应核心会计日期
     *
     * @param rural_branch_id
     * @return 找到则返回字符串格式核心会计日期，否则返回null；
     * @author RZT
     */
    public String getCoreDate(String rural_branch_id) {
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("paramkey", "core_date_" + rural_branch_id);
        p.put("groupid", "system");
        List<Map<String, Object>> list = sysParamDao.queryForList(p);
        if (!Utils.isNullOrEmpty(list)) {
            return (String) list.get(0).get("defparamvalue");
        }
        return null;
    }

    /**
     * 根据交易码从交易信息表cli_txinfo获取交易名称
     *
     * @param txcode
     * @return
     * @author rzt
     */
    public String getTxName(String txcode) {
        String txname = "";
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("txcode", txcode);
        List<Map<String, Object>> list = cliTxinfoDao.queryForList(p);
        if (!Utils.isNullOrEmpty(list)) {
            txname = String.valueOf(list.get(0).get("txname"));
        }
        return txname;
    }

    /**
     * 获取当前登陆用户的机构和子级机构
     *
     * @return
     */
    public String getUserBranchids() {
        // 获取当前登录用户可以查询的机构集合
        String loginUserBranchId = "";
        int isManage = getUserRoleLevel(BaseUtils.map("account_id", MfpContextHolder.getLoginId()));
        if (1 == isManage) {
            // 当前用户是南粤超级管理员或南粤总行管理员
            loginUserBranchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_BRANCHID", "9999");
        } else {
            loginUserBranchId = getUserBranchid();
        }
        List<String> branchIdList = queryOrgid(BaseUtils.map("branch_id", loginUserBranchId));
        String branchIds = ",'" + loginUserBranchId + "'";
        for (String string : branchIdList) {
            branchIds += ",'" + string + "'";
        }
        if (branchIds.startsWith(",")) {
            branchIds = branchIds.substring(1, branchIds.length());
        }
        return branchIds;
    }

    /**
     * 根据机构号获取本机构及下属机构
     *
     * @return
     */
    public String getUserBranchidsBybranch(String branch_id) {
        List<String> branchIdList = queryOrgid(BaseUtils.map("branch_id", branch_id));
        String branchIds = ",'" + branch_id + "'";
        for (String string : branchIdList) {
            branchIds += ",'" + string + "'";
        }
        if (branchIds.startsWith(",")) {
            branchIds = branchIds.substring(1, branchIds.length());
        }
        return branchIds;
    }

    /**
     * 根据机构号获取该机构号的所有下级机构id
     *
     * @param userMap
     */
    public List<String> queryOrgid(Map<String, Object> userMap) {
        List<String> branchIdlist = new ArrayList<String>();
        List<Map<String, Object>> branchList = sysBranchDao.queryBeforeOrgid(userMap);
        Iterator<Map<String, Object>> iterator = branchList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> branchMap = iterator.next();
            String branchId = String.valueOf(branchMap.get("branch_id"));
            // 如果该机构和当前查询条件中的机构号相等，则跳过，避免出现某机构的上级机构号为自身而进入死循环
            if (!branchId.equals(userMap.get("branch_id"))) {
                branchIdlist.add(branchId);
                branchIdlist.addAll(queryOrgid(branchMap));
            }
        }
        return branchIdlist;
    }

    /**
     * 根据登陆用户id获取其机构id
     */
    public String getUserBranchid() {
        String branch_id = "";
        List<Map<String, Object>> list = sysAccountDao.queryForList(BaseUtils.map("account_id", MfpContextHolder.getLoginId()));
        if (list != null && list.size() > 0) {
            if (ObjectUtil.isNotEmpty(list.get(0).get("branch_id"))) {
                branch_id = list.get(0).get("branch_id").toString();
            }
        }
        return branch_id;
    }

    /**
     * desc: 根据压缩文件名拆分的信息，获取解压后文件存放路径<br/>
     * 目录规则如下： ｛文件目录｝/hnm_media/柜员号／业务流水号／平台交易码（如：FIN000001）/影像图片
     *
     * @param infoMap
     * @return 解压后文件存放路径
     * @author pzz
     * =====================================================================
     * 影像资料可以补录
     * 修改目录如下： ｛文件目录｝/hnm_media/柜员号／业务流水号／平台交易码（如：FIN000001）/生成时间yyyyMMddHHmmss/影像图片
     * modified by pzz 2017-02-17 15:51
     */
    public String getDirByInfo(Map<String, Object> infoMap) {
        String busSeqNo = infoMap.get("busi_seq_no").toString();
        StringBuffer dirName = new StringBuffer();
        String baseDirName = sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info");
        dirName.append(baseDirName).append(File.separator).append("hnm_media").append(File.separator).append(infoMap.get("operno")).append(File.separator).append(busSeqNo).append(File.separator).append(infoMap.get("txcode"));
        //modified by pzz 2017-02-17 15:51
        if (infoMap.containsKey("generate_time") && ObjectUtil.isNotEmpty(infoMap.get("generate_time"))) {
            dirName.append(File.separator).append(infoMap.get("generate_time"));
        }
        return dirName.toString();
    }

    /**
     * 根据审批类型  机构 获取审批流程
     */
    public Map<String, Object> getApprovalProcess(String approvalType, String branchid) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        Map<String, Object> rMapTmp = new HashMap<String, Object>();
        Map<String, Object> qMap = new HashMap<String, Object>();
        qMap.put("approval_type", approvalType);
        List<Map<String, Object>> list = tApprovalProcessDao.queryForList(qMap);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                if (ObjectUtil.isNotEmpty(map.get("branch_id"))) {
                    //分行自有流程
                    List<String> branchids = queryOrgid(BaseUtils.map("branch_id", map.get("branch_id")));
                    branchids.add(map.get("branch_id").toString());
                    if (branchids.contains(branchid)) {
                        rMap = map;
                        break;
                    }
                } else {//全行默认流程
                    rMapTmp = map;
                }
            }
            if (rMap.isEmpty() && !rMapTmp.isEmpty()) {
                rMap = rMapTmp;
            }
        }
        return rMap;
    }

    /**
     * 保存审批申请表、审批流程表
     */
    public int saveApprovalApply(Map<String, Object> req_map, Map<String, Object> map) {
        List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("approval_type", map.get(
                "approval_type"), "relation_id", map.get("relation_id")));
        int ret = 0;
        //审批类型
        String approval_type = String.valueOf(map.get("approval_type"));
        if (list == null || list.size() == 0) {//无则新增
            //保存申请表
            map.put("approval_status", ApprovalStatusEnum.STATUS_1.getApprovalStatus());//审批中
            map.put("approval_status_name", ApprovalStatusEnum.STATUS_1.getApprovalStatusName());//审批中
            map.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            map.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            ret = tApprovalApplyDao.save(map);
            //保存审批表
            //获取当前用户所在的机构
            String branch_id = getUserBranchid();
            //审批流程
            Map<String, Object> approvalProcessMap = getApprovalProcess(approval_type, branch_id);

//			if(approvalProcessMap.get("approval_process") == null){
//				throw new BusinessException("HnmCommServiceException", "未能获取审批流程");
//			}
            //String  approval_process = String.valueOf(approvalProcessMap.get("approval_process"));

            //add by wxm 20211221 新审批流程待用
            if (approvalProcessMap == null || approvalProcessMap.isEmpty()) {
                throw new BusinessException("HnmCommServiceException", "未能获取审批流程");
            }
            String approval_process = getNewApprovalProcess(req_map, approvalProcessMap);
            if (StringUtils.isEmpty(approval_process)) {
                throw new BusinessException("HnmCommServiceException", "准入条件不符合，拒绝提交");
            }

            Map<String, Object> reMap = new HashMap<>();
            reMap.put("approval_type", approval_type);
            reMap.put("approval_process_id", approvalProcessMap.get("id"));
            reMap.put("apply_id", map.get("id"));
            reMap.put("approval_process", approval_process);
            //获取下个审批角色
            String[] approval_processArr = approval_process.split("-");
            if (approval_processArr.length >= 1) {
                reMap.put("approval_role", approval_processArr[0]);
                reMap.put("approval_user", map.get("approval_user"));
            }
            if (approval_processArr.length >= 2) {
                reMap.put("next_role", approval_processArr[1]);
            }
            //截取审批流程表中的审批流程
            if (approval_processArr.length >= 2) {
                String[] newArray = Arrays.copyOfRange(approval_processArr, 1, approval_processArr.length);
                String new_approval_process = StringUtils.join(newArray, "-");
                //剩余审批流程
                reMap.put("odd_approval_process", new_approval_process);
            }
            reMap.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            reMap.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            //保存审批表
            tApprovalDao.save(reMap);
            //保存申请操作记录
            reMap.put("suggestion", map.get("suggestion"));
            recordService.saveOperationRecord(reMap);
        } else {
            throw new BusinessException("HnmCommServiceException", "已有该申请记录" + list.get(0).get("id"));
        }
        return ret;
    }

    /**
     * 保存审批申请表(未启用审批流)
     */
    public int saveApprovalApplyNoProcess(Map<String, Object> req_map, Map<String, Object> map) {

        //审批类型
        String approval_type = String.valueOf(map.get("approval_type"));
        //获取当前用户所在的机构
        String branch_id = getUserBranchid();
        //审批流程
        Map<String, Object> approvalProcessMap = getApprovalProcess(approval_type, branch_id);

        if (approvalProcessMap != null && !approvalProcessMap.isEmpty()) {
            //add by wxm 20211221 新审批流程待用
            String approval_process = getNewApprovalProcess(req_map, approvalProcessMap);
            if (StringUtils.isEmpty(approval_process)) {
                throw new BusinessException("HnmCommServiceException", "准入条件不符合，拒绝提交");
            }
        }


        List<Map<String, Object>> list = tApprovalApplyDao.queryForList(BaseUtils.map("approval_type", map.get(
                "approval_type"), "relation_id", map.get("relation_id")));
        int ret = 0;
        if (list == null || list.size() == 0) {//无则新增
            //保存申请表
            map.put("approval_status", ApprovalStatusEnum.STATUS_2.getApprovalStatus());//审批通过
            map.put("approval_status_name", ApprovalStatusEnum.STATUS_2.getApprovalStatusName());//审批通过
            map.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            map.put("update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            ret = tApprovalApplyDao.save(map);
            //保存申请操作记录
            Map<String, Object> reMap = new HashMap<>();
            reMap.put("approval_type", map.get("approval_type"));
            reMap.put("apply_id", map.get("id"));
            reMap.put("suggestion", map.get("suggestion"));
            recordService.saveOperationRecord(reMap);
        } else {
            throw new BusinessException("HnmCommServiceException", "已有该申请记录" + list.get(0).get("id"));
        }
        return ret;
    }


    /**
     * 审批后回调方法
     *
     * @param p(map内包含： approval_type---审批类型;
     *                  approval_status---审批状态;
     *                  id---业务表id;)
     * @return
     */
    public boolean doAfterApproval(Map<String, Object> p) {
        //校验参数
        Object approvalTypeObj = p.get("approval_type");
        Object approvalStatusObj = p.get("approval_status");
        Object idObj = p.get("id");
        if (ObjectUtil.isEmpty(approvalTypeObj) || ObjectUtil.isEmpty(approvalStatusObj) || ObjectUtil.isEmpty(idObj))
            return false;
        String approvalType = approvalTypeObj.toString();
        String approvalStatus = approvalStatusObj.toString();
        String id = idObj.toString();
        //审批通过：编辑处理-逻辑删除旧数据（审批不通过和新增不作处理）
        if (approvalStatus.equals(ApprovalStatusEnum.STATUS_2.getApprovalStatus())) {
            if (approvalType.equals(ApprovalTypeEnum.TYPE002.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE003.getApprovalType())
                    || approvalType.equals(ApprovalTypeEnum.TYPE014.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE015.getApprovalType())) {
                //(人员管理)
                List<Map<String, Object>> list = managerDao.queryForList(BaseUtils.map("id", id));
                if (list.size() < 1) return false;
                Map<String, Object> map = list.get(0);
                List<Map<String, Object>> managerList = managerDao.queryList(BaseUtils.map(
                        "mgr_id", map.get("mgr_id"), "approval_status", "2"));
                for (Map<String, Object> managerMap : managerList) {
                    String managerMapId = managerMap.get("id").toString();
                    if (id.equals(managerMapId)) {
                        managerDao.updateByPk(BaseUtils.map("id", managerMapId, "update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")));
                    } else {
                        managerDao.updateByPk(BaseUtils.map("id", managerMapId, "is_delete", "1"));
                    }
                }
                //更改用户表  添加或更新用户
                List<Map<String, Object>> accountList = sysAccountDao.queryForList(BaseUtils.map("account_id", map.get(
                        "mgr_id")));
                if (accountList != null && accountList.size() > 0) {
                    //更新
                    //查询角色 看是否可以更新
                    Map<String, Object> userMap = accountList.get(0);
                    List<Map<String, Object>> userRoleList = sysAccountRoleDao.queryForList(BaseUtils.map("account_id",
                            userMap.get("account_id")));
                    if (userRoleList == null || userRoleList.size() == 0) {
                        throw new BusinessException("HnmCommServiceException", "该用户已存在并未配置角色");
                        //return false;
                    }

                    //校验角色级别
                    String userRoleLevel = "";
                    List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", userRoleList.get(0).get("role_id")));
                    if (mapList != null && mapList.size() > 0) {
                        userRoleLevel = mapList.get(0).get("role_level").toString();
                    }
                    if (!"2".equals(userRoleLevel) && !"3".equals(userRoleLevel) && !"4".equals(userRoleLevel)) {
                        throw new BusinessException("HnmCommServiceException", "该用户已存在且其角色不在此审批流可修改范围内");
                        //return false;
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

            } else if (approvalType.equals(ApprovalTypeEnum.TYPE004.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE005.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE006.getApprovalType())
                    || approvalType.equals(ApprovalTypeEnum.TYPE007.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE008.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE022.getApprovalType())
                    || approvalType.equals(ApprovalTypeEnum.TYPE016.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE017.getApprovalType())
                    || approvalType.equals(ApprovalTypeEnum.TYPE018.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE019.getApprovalType())) {
                //（站点管理）
                List<Map<String, Object>> list = siteDao.queryForList(BaseUtils.map("id", id));
                if (list.size() < 1) return false;
                if (approvalType.equals(ApprovalTypeEnum.TYPE004.getApprovalType())) {
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
                    try {
                        sysAccountDao.queryForMap(BaseUtils.map("account_id", list.get(0).get("mgr_id"), "deleted", 0));
                    } catch (Exception e) {
                        throw new BusinessException("HnmCommServiceException", "该站点管辖客户经理不存在");
                    }
                    siteDao.updateByPk(BaseUtils.map("id", id, "site_no", siteNo, "update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")));
                } else {
                    //入库前再次检查终端信息是否已存在
                    if (ApprovalTypeEnum.TYPE006.getApprovalType().equals(approvalType)
                            || ApprovalTypeEnum.TYPE007.getApprovalType().equals(approvalType)
                            || ApprovalTypeEnum.TYPE017.getApprovalType().equals(approvalType)) {
                        String str = approvalService.checkTerminalInfo(p);
                        if (!str.equals("pass")) {
                            throw new BusinessException("HnmCommServiceException", str);
                        }
                    }
                    Map<String, Object> map = list.get(0);
                    List<Map<String, Object>> siteList = siteDao.queryForList(BaseUtils.map(
                            "site_no", map.get("site_no"), "approval_status", "2"));
                    for (Map<String, Object> siteMap : siteList) {
                        String siteId = siteMap.get("id").toString();
                        if (id.equals(siteId)) {
                            try {
                                sysAccountDao.queryForMap(BaseUtils.map("account_id", map.get("mgr_id"), "deleted", 0));
                            } catch (Exception e) {
                                throw new BusinessException("HnmCommServiceException", "该站点管辖客户经理不存在");
                            }
                            siteDao.updateByPk(BaseUtils.map("id", siteId, "update_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")));
                        } else {
                            siteDao.updateByPk(BaseUtils.map("id", siteId, "is_delete", "1"));
                            fileInfoDao.updateByBusiId(BaseUtils.map("busi_id", siteId, "is_delete", "1"));
                        }
                    }
                }
                //站点审批通过后 调用销管系统的接口 同步数据
                List<Map<String, Object>> datalist = siteDao.queryForList(BaseUtils.map("id", id));
                if (datalist != null && datalist.size() > 0) {
                    Map<String, Object> map = datalist.get(0);
                    if (ApprovalTypeEnum.TYPE004.getApprovalType().equals(approvalType)) {
                        map.put("trans_type", "A");
                    } else {
                        map.put("trans_type", "U");
                    }
                    sendToXG(map);
                }
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE009.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE010.getApprovalType())
                    || approvalType.equals(ApprovalTypeEnum.TYPE011.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE012.getApprovalType()) || approvalType.equals(ApprovalTypeEnum.TYPE013.getApprovalType())) {
                //（团队管理）
                List<Map<String, Object>> list = hTeamDao.queryForList(BaseUtils.map("id", id));
                if (list.size() < 1) return false;
                Map<String, Object> map = list.get(0);
                if (approvalType.equals(ApprovalTypeEnum.TYPE009.getApprovalType())) {
                    //新增
                    //新增时生成机构编号
                    //获取上级编码生成下级机构号
                    String branch_id = "";
                    List<Map<String, Object>> maxList = hTeamDao.queryMaxOrgid(BaseUtils.map("porgid", map.get(
                            "porgid")));
                    if (maxList != null && maxList.size() > 0) {
                        Map<String, Object> maxMap = maxList.get(0);
                        if (ObjectUtil.isNotEmpty(maxMap) && ObjectUtil.isNotEmpty(maxMap.get("maxid"))) {
                            String maxid = maxMap.get("maxid").toString();
                            branch_id = Long.parseLong(maxid) + 1 + "";
                        } else {
                            branch_id = map.get("porgid") + "001";
                        }
                    } else {
                        branch_id = map.get("porgid") + "001";
                    }
                    map.put("branch_id", branch_id);
                    hTeamDao.updateByPk(BaseUtils.map("id", id, "branch_id", branch_id));
                } else {
                    List<Map<String, Object>> teamList = hTeamDao.queryList(BaseUtils.map(
                            "branch_id", map.get("branch_id"), "approval_status", "2"));
                    for (Map<String, Object> teamMap : teamList) {
                        String teamId = teamMap.get("id").toString();
                        if (id.equals(teamId)) continue;
                        hTeamDao.updateByPk(BaseUtils.map("id", teamId, "is_delete", "1"));
                    }
                }

                //更改机构表  添加或更新机构
                List<Map<String, Object>> branchList = sysBranchDao.queryForList(BaseUtils.map("branch_id", map.get(
                        "branch_id")));
                String ruralBranchId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_RURALBRANCHID", "0000");
                if (branchList != null && branchList.size() > 0) {
                    //更新
                    map.remove("create_time");
                    map.put("rural_branch_id", ruralBranchId);
                    map.put("bran_level", "2");
                    sysBranchDao.updateByPk(map);
                } else {
                    //新增
                    map.put("rural_branch_id", ruralBranchId);
                    map.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                    map.put("bran_level", "2");
                    sysBranchDao.save(map);
                }
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE020.getApprovalType())) {
                //团队管理-删除团队
                List<Map<String, Object>> list = hTeamDao.queryForList(BaseUtils.map("id", id));
                if (list.size() < 1) return false;
                Map<String, Object> map = list.get(0);
                List<Map<String, Object>> teamList = hTeamDao.queryList(BaseUtils.map(
                        "branch_id", map.get("branch_id"), "approval_status", "2"));
                for (Map<String, Object> teamMap : teamList) {
                    String teamId = teamMap.get("id").toString();
                    hTeamDao.updateByPk(BaseUtils.map("id", teamId, "is_delete", "1"));
                }
                //删除机构表
                sysBranchDao.deleteByPk(map);
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE021.getApprovalType())) {
                List<Map<String, Object>> list = managerDao.queryForList(BaseUtils.map("id", id));
                if (list.size() < 1) return false;
                Map<String, Object> map = list.get(0);
                List<Map<String, Object>> managerList = managerDao.queryList(BaseUtils.map(
                        "mgr_id", map.get("mgr_id"), "approval_status", "2"));
                for (Map<String, Object> managerMap : managerList) {
                    String managerMapId = managerMap.get("id").toString();
                    managerDao.updateByPk(BaseUtils.map("id", managerMapId, "is_delete", "1"));
                }

                //删除用户表
                List<Map<String, Object>> accountList = sysAccountDao.queryForList(BaseUtils.map("account_id", map.get(
                        "mgr_id")));
                if (accountList != null && accountList.size() > 0) {
                    //查询角色 看是否可以删除
                    Map<String, Object> userMap = accountList.get(0);
                    List<Map<String, Object>> userRoleList = sysAccountRoleDao.queryForList(BaseUtils.map("account_id",
                            userMap.get("account_id")));
                    if (userRoleList == null || userRoleList.size() == 0) {
                        throw new BusinessException("HnmCommServiceException", "该用户未配置角色");
                    }

                    //校验角色级别
                    String userRoleLevel = "";
                    List<Map<String, Object>> mapList = sysRoleDao.queryForList(BaseUtils.map("role_id", userRoleList.get(0).get("role_id")));
                    if (mapList != null && mapList.size() > 0) {
                        userRoleLevel = mapList.get(0).get("role_level").toString();
                    }
                    if (!"2".equals(userRoleLevel) && !"3".equals(userRoleLevel) && !"4".equals(userRoleLevel)) {
                        throw new BusinessException("HnmCommServiceException", "该用户角色不在此审批流可删除范围内");
                    }
                    //再次校验用户下是否有站点
                    List<Map<String, Object>> siteList = siteDao.queryForListBycondition(BaseUtils.map("mgr_id", map.get("mgr_id"),
                            "is_delete", "0", "approval_status", "2", "status", "'0','1','2'"));
                    if (siteList != null && siteList.size() > 0) {
                        throw new BusinessException("HnmCommServiceException", "用户" + map.get("mgr_id") + "有管辖站点，不允许删除");
                    }
                    //再次校验用户是否团队/片区负责人
                    List<Map<String, Object>> listTeam = hTeamDao.queryList(BaseUtils.map("team_leader", map.get("mgr_id"),
                            "is_delete", "0", "approval_status", "2"));
                    if (listTeam != null && listTeam.size() > 0) {
                        throw new BusinessException("HnmCommServiceException", "用户" + map.get("mgr_id") + "是团队/片区负责人，不允许删除");
                    }

                    //再次校验用户是否有待审核工单
                    List<Map<String, Object>> approvalList = tApprovalDao.queryByApprovalUser(BaseUtils.map("approval_status", "1", "approval_user", map.get("mgr_id")));
                    if (approvalList != null && approvalList.size() > 0) {
                        throw new BusinessException("HnmCommServiceException", "用户" + map.get("mgr_id") + "存在未审批的工单，请先处理再停用");
                    }

                    //再次校验用户是否团队/片区负责人
                    List<Map<String, Object>> patrolLogContentList = hPatrolLogContentDao.queryForExport(BaseUtils.map("status", 0, "responsible", map.get("mgr_id")));
                    if (patrolLogContentList != null && patrolLogContentList.size() > 0) {
                        throw new BusinessException("HnmCommServiceException", "用户" + map.get("mgr_id") + "存在未处理的风险信息，请先处理再停用");
                    }
                }
                //删除用户表
                sysAccountDao.deleteByPk(BaseUtils.map("account_id", map.get("mgr_id")));
                //删除用户角色信息表
                sysAccountRoleDao.delete(BaseUtils.map("account_id", map.get("mgr_id")));
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE023.getApprovalType())) {
                List<Map<String, Object>> details = detailsDao.queryByApplyId(BaseUtils.map("relation_id", idObj));
                Map<String, Object> map = details.get(0);
                map.put("status", map.get("tmp_status"));
                detailsDao.updateById(map);
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE024.getApprovalType()) ||
                    approvalType.equals(ApprovalTypeEnum.TYPE025.getApprovalType())) {

                List<Map<String, Object>> tempList = sysRoleDao.queryTemp(BaseUtils.map("role_id", idObj));
                Map<String, Object> roleMap = tempList.get(0);
                roleMap.put("deleted", "0");
                roleMap.put("create_time", DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                if (approvalType.equals(ApprovalTypeEnum.TYPE024.getApprovalType())) {
                    sysRoleDao.save(roleMap);
                } else if (approvalType.equals(ApprovalTypeEnum.TYPE025.getApprovalType())) {
                    roleMap.put("role_id", roleMap.get("update_id"));
                    sysRoleDao.updateByPk(roleMap);
                    //删除原有旧数据
                    sysRoleMenuDao.deleteByPk(BaseUtils.map("role_id", roleMap.get("update_id")));
                    sysRoleMobileMenuDao.deleteByPk(BaseUtils.map("role_id", roleMap.get("update_id")));
                }
                List<Map<String, Object>> menuList = sysRoleMenuDao.queryForList(BaseUtils.map("role_id", idObj));
                List<Map<String, Object>> mobilemenuList = sysRoleMobileMenuDao.queryForList(BaseUtils.map("role_id", idObj));

                List<Map<String, Object>> param = new ArrayList<>();
                for (Map<String, Object> map : menuList) {
                    map.put("role_id", roleMap.get("role_id"));
                    param.add(map);
                }
                sysRoleMenuDao.saveList(param);

                List<Map<String, Object>> mobileparam = new ArrayList<>();
                for (Map<String, Object> map : mobilemenuList) {
                    map.put("role_id", roleMap.get("role_id"));
                    mobileparam.add(map);
                }
                sysRoleMobileMenuDao.saveList(mobileparam);
            } else if (approvalType.equals(ApprovalTypeEnum.TYPE026.getApprovalType())) {
                List<Map<String, Object>> tempList = sysRoleDao.queryTemp(BaseUtils.map("role_id", idObj));
                Map<String, Object> roleMap = tempList.get(0);
                Map<String, Object> map = new HashMap<>();
                map.put("role_id", roleMap.get("update_id"));
                map.put("deleted", "1");
                //删除角色
                sysRoleDao.updateByPk(map);
            }
        }
        return true;
    }

    /**
     * 判断User-Agent 是不是来自于手机(且为南粤通内登陆)
     *
     * @param request
     * @return
     */
    public boolean checkAgentIsMobile(HttpServletRequest request) {

        boolean b1 = false;
        boolean b2 = false;
        String ua = request.getHeader("User-Agent");

        logger.info("ua=" + ua);
        String[] deviceArray = new String[]{"android", "iphone", "ipod",
                "ipad", "blackberry", "ucweb", "windows phone"};
        if (ua != null) {
            //校验是否手机端登陆
            ua = ua.toLowerCase();
            for (String string : deviceArray) {
                if (ua.indexOf(string) > 0) {
                    b1 = true;
                }
            }
            //校验是否南粤通内登陆
            if (b1) {
                //校验token
                Map<String, Object> receiveMap = new HashMap<>();
                String access_token = request.getHeader("access_token");
                String backCode = "/checkTokenValid";
                String qusCode = "/checkTokenValid?access_token=" + access_token;
                try {
                    receiveMap = mfpJsonMsgProcService.sendAndReceiveMsg(BaseUtils.map(), backCode, qusCode);
                } catch (Exception e) {
                    if (e instanceof BusinessException) {
                        throw (BusinessException) e;
                    } else { //通讯异常
                        throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
                    }
                }
                String result = String.valueOf(receiveMap.get("result"));
                if ("true".equals(result)) {
                    b2 = true;
                }
            }

        }
        return b2;
    }

    /**
     * 拼接原始路径和新增的路径，适配原始路径末尾带“／”或者不带“／”
     *
     * @param dir    原始路径
     * @param addDir 增加的路径
     * @return 增加后的路径
     */
    public String appendDir(String dir, String addDir) {
        if ((dir.length() - 1) == dir.lastIndexOf(File.separator)) {
            return dir + addDir;
        } else {
            return dir + File.separator + addDir;
        }
    }

    /**
     * 获取从ODS同步信息的数据文件的目录
     *
     * @return 从ODS同步信息的数据文件的目录
     * @author chenhao
     */
    public String getSynInfoDataDir() {
        String dir = appendDir(sysParamService.getSysParam("BASE_CONFIG", "FILE_BASE_DIR", "/home/weblogic/hnm/info"), "synInfoData");
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        logger.info("获取“从ODS同步信息的数据文件的目录”成功： " + dir);
        return dir;
    }

    /**
     * desc: 连接ftp 下载文件至本地文件夹
     *
     * @param remoteFile     远程文件
     * @param targetFileDir  预存本地文件目录
     * @param targetFileName 预存本地文件名
     * @param ftpIP          ftp地址
     * @param ftpUserName    ftp用户名
     * @param ftpPwd         ftp密码
     * @return 下载成功与否标识 true：成功  false：失败
     * @throws SocketException
     * @throws IOException
     * @author pzz
     */
    public boolean ftpGet(String remoteFile, String targetFileDir, String targetFileName, String ftpIP, String ftpUserName, String ftpPwd) throws SocketException, IOException {
        FileOutputStream fos = null;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpIP);
            ftpClient.login(ftpUserName, ftpPwd);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            File file = new File(targetFileDir + File.separator + targetFileName);
            File dirFile = new File(targetFileDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            fos = new FileOutputStream(file);
            boolean flag = ftpClient.retrieveFile(remoteFile, fos);
            //　第二期注意： 当 flag=false 的时候 或者有异常时，需要删掉 targetFileDir
            return flag;
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (ftpClient != null) {
                ftpClient.disconnect();
            }
        }
    }

    /**
     * 读取本地数据文件，把文件中的数据导入至对应的表中
     *
     * @param tableName  目标表名
     * @param localFile  本地文件（带路径）
     * @param readerCode 读取本地文件使用的编码格式
     * @param splitStr   本地文件中数据的分隔符
     * @return true : 导入成功 ； false : 导入失败；
     * @DES 本方法不控制由于主键冲突导致的部分插入失败，该控制请调用方自行实现
     * @author chenhao
     */
    public boolean fileInTable(String tableName, String localFile, String readerCode, String splitStr) {
        //获取该表的所有字段
        String[] columns = codeGenService.getTableColumnsName(tableName);
        List<Map<String, Object>> batchValues = new ArrayList<Map<String, Object>>();
        int batchCommitSize = 5000;
        try {
            batchCommitSize = Integer.valueOf(sysParamService.getSysParam("system", "BATCH_COMMIT_SIZE", "5000"));
        } catch (Exception e) {
            logger.error("获取系统参数：批量导入提交次数，失败，原因：", e);
        }
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader reader = null;
        try {
            //根据传入的编码值来读取文件
            fi = new FileInputStream(localFile);
            is = new InputStreamReader(fi, readerCode);
            reader = new BufferedReader(is);
            String lienStr = "";
            int lineSize = 0;
            //逐行处理数据
            while (null != (lienStr = reader.readLine())) {
                String[] strs = lienStr.split(splitStr);
                Map<String, Object> lineMap = new HashMap<String, Object>();
                for (int i = 0; i < strs.length; i++) {
                    lineMap.put(columns[i], strs[i]);
                }
                //当获取的值个数小于列的个数时，后续值赋值为空，避免以二进制字符为分隔符时，末尾丢失的情况发生
                for (int i = 0; i < columns.length - strs.length; i++) {
                    lineMap.put(columns[strs.length + i], "");
                }
                batchValues.add(lineMap);
                lineSize++;
                if (batchValues.size() == batchCommitSize) { //待提交列表中记录数已经达到配置的 提交记录数，先提交一次,防止文件数据记录数太多，一次性提交失败
                    hnmCommDao.batchUpdate(tableName, columns, batchValues);
                    batchValues.clear();
                }
            }
            logger.info("处理文件 " + localFile + " ,共读取 " + lineSize + " 行数据。");
        } catch (FileNotFoundException e) { //本地文件 %s不存在,请查证
            BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService026, localFile);
            logger.error(exception.getMessage(), e);
            throw exception;
        } catch (IOException e) { //读取本地文件 %s失败
            BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService027, localFile);
            logger.error(exception.getMessage(), e);
            throw exception;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    logger.error("关闭流失败，原因：", e);
                }
            }
        }

        if (batchValues.size() > 0) { //batchValues 还有未提交的数据，最后再次提交一次
            hnmCommDao.batchUpdate(tableName, columns, batchValues);
        }
        return true;
    }

    /**
     * <h1>同步核心会计日期</h1>
     *
     * @param p 请求参数<br/>
     * @return 返回同步结果，如果都同步成功，则返回同步成功；否则提示未同步成功的branchid；
     * @author RZT
     */
    public Map<String, Object> synCoreDate(Map<String, Object> p) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        p.put("service_code", "11002000037");//esb服务代码
        p.put("service_scene", "01");//esb服务应用场景号，新核心日切通知

        p.put("job_flag", "0");
        p.put("flag", "1");

        //获取机构编号
        List<Map<String, Object>> branchList = sysBranchDao.queryRuralBranchId(p);

        Map<String, Object> reveMap = new HashMap<String, Object>();
        if (!Utils.isNullOrEmpty(branchList)) {
            for (Map<String, Object> m : branchList) {
                Map<String, Object> map = new HashMap<String, Object>();
                String acctLcass = String.valueOf(m.get("rural_branch_id"));
                if (!Utils.isNullOrEmpty(acctLcass) && "null".equals(acctLcass)) {
                    p.put("acct_lcass", acctLcass);

                    // 根据不同的法人编号获取对应的虚拟柜员信息
                    String userId = sysParamService.getSysParam("DEFAULT_USERINFO", acctLcass + "_USERID", "E9999");
                    String branch_id = sysParamService.getSysParam("DEFAULT_USERINFO", acctLcass + "_BRANCHID", "9999");
                    String rural_branch_id = sysParamService.getSysParam("DEFAULT_USERINFO", acctLcass + "_RURALBRANCHID", "0000");
                    p.put("app_head_user_id", userId);
                    p.put("app_head_branch_id", branch_id);
                    p.put("app_head_rural_branch_id", rural_branch_id);
                    p.put("txcode", sysParamService.getSysParam("DEFAULT_USERINFO", "TXCODE", "COM000000"));
                    p.put("bus_seq_no", this.getFlowNo(userId));

                    reveMap = esbMsgProcService.sendAndReceiveMsg(p, false);
                    if (!Utils.isNullOrEmpty(reveMap) && reveMap.containsKey("body") && reveMap.get("body") != null && reveMap.get("body") instanceof Map) {
                        Map<String, Object> bodyMap = (Map<String, Object>) reveMap.get("body");
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        String paramkey = "core_date_" + String.valueOf(acctLcass);
                        String setDate = String.valueOf(bodyMap.get("set_date"));
                        paramMap.put("paramkey", paramkey);
                        paramMap.put("paramlabel", "日切日期");
                        paramMap.put("defparamvalue", setDate);
                        paramMap.put("memo", "更新的日切会计日期");
                        paramMap.put("groupid", "system");
                        paramMap.put("groupname", "系统参数");
//                        TODO 需要等sys_param更新
                        sysParamDao.saveOrUpdate(paramMap);
                        map.put("retStr", "同步成功！");
                    } else {
                        //同步不成功
                        logger.error("同步核心会计日期失败！");
                        String retStr = esbMsgProcService.getRetCodeMsg(reveMap);
                        map.put("retStr", retStr);
                    }
                } else {
                    logger.error("查询法人编号失败");
                }
                list.add(map);
            }
            retMap.put("tips", list);
        } else {
            logger.error("查询法人编号列表失败");
        }

        return retMap;
    }

    /**
     * 当天的机构信息同步方法
     * 根据系统参数表中“SYN_BRANCH_INFO”组的各项配置，
     * 先获取“.ok”文件（获取出错或者失败都直接抛错返回）
     * 再获取数据文件存至本地（获取出错或者失败都抛错返回）
     * 读取本地数据文件把数据导入“TEMP_BRANCH_INFO”表
     * 同步“TEMP_BRANCH_INFO”表中的数据到“SYS_BRANCH”中
     *
     * @return true:同步成功；false:同步失败；
     */
    public boolean synBranchInfo() {
        //获取昨天日期
        String yesterDay = DateUtils.format(DateUtils.addDay(new Date(), -1), "yyyyMMdd");
        //lcoalDir 本地文件目录；dateFileName
        String lcoalDir = appendDir(getSynInfoDataDir(), yesterDay);
        //okFileName ok文件名；okFile 远程目录下的ok文件
        String okFileName = sysParamService.getSysParam("SYN_BRANCH_INFO", "OK_FILE_NAME", "T_MSAP_COMC_BRANCH.ok");
        //dateFileName data文件名；dateFile 远程目录下的data文件
        String dateFileName = sysParamService.getSysParam("SYN_BRANCH_INFO", "DATE_FILE_NAME", "T_MSAP_COMC_BRANCH.dat");
        //获取系统开关判断是否通过ftp获取数据文件，1:通过ftp，0:直接读取本地
        if ("1".equals(sysParamService.getSysParam("SYS_SWITCH", "SYN_BRANCH_INFO_BY_FTP", "1"))) {
            //先检查“.ok”是否存在，不存在直接抛错返回
            boolean isOk = false;
            //lcoalDir 本地文件目录,remoteDir 远程文件目录,ftpIP ftp地址,ftpUserName ftp用户名,ftpPwd ftp密码
            String ftpIP = sysParamService.getSysParam("SYN_INFO_BY_ODS", "FTP_IP", "");
            String ftpUserName = sysParamService.getSysParam("SYN_INFO_BY_ODS", "FTP_USER_NAME", "");
            String ftpPwd = sysParamService.getSysParam("SYN_INFO_BY_ODS", "FTP_PWD", "");
            String okFile = appendDir(yesterDay, okFileName);
            try {
                isOk = ftpGet(okFile, lcoalDir, okFileName, ftpIP, ftpUserName, ftpPwd);
            } catch (Exception e) { //获取“.ok”文件出错
                BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService035);
                logger.error(exception.getMessage(), e);
                throw exception;
            }
            if (false == isOk) { //获取“.ok”文件失败
                BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService036);
                logger.error(exception.getMessage());
                throw exception;
            }
            //获取数据文件存至本地
            isOk = false;
            //dateFile 远程数据文件名
            String dateFile = appendDir(yesterDay, dateFileName);
            try {
                isOk = ftpGet(dateFile, lcoalDir, dateFileName, ftpIP, ftpUserName, ftpPwd);
            } catch (Exception e) { //获取远程数据文件出错
                BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService028);
                logger.error(exception.getMessage(), e);
                throw exception;
            }
            if (false == isOk) { //获取远程数据文件失败
                BusinessException exception = ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService029);
                logger.error(exception.getMessage());
                throw exception;
            }
        }
        //通过系统参数表获取 目标表名、本地数据文件、读取编码格式、数据分隔符
        String localDateFile = appendDir(lcoalDir, dateFileName);
        String tableName = "TEMP_BRANCH_INFO";
        String readerCode = sysParamService.getSysParam("SYN_BRANCH_INFO", "READER_CODE", "UTF-8");
        String splitStr = sysParamService.getSysParam("SYN_BRANCH_INFO", "SPLIT_STR", "0x1b");
        byte[] splitByte = {Byte.decode(splitStr)};
        splitStr = new String(splitByte);
        //清空临时表“TEMP_BRANCH_INFO”的数据
        tempBranchInfoDao.truncate();
        //把文件中的数据入库
        this.fileInTable(tableName, localDateFile, readerCode, splitStr);
        //同步“TEMP_BRANCH_INFO”表中的数据到“SYS_ORG”中
        sysBranchDao.synByTempBranchInfo();
        return true;
    }

    /**
     * desc: 获取业务流水号<br/>
     * 规则：根据当前柜员号和当天日期 yyyyMMdd查询t_flow_no， 获取每天由00000001递增的业务流水号，并更新t_flow_no表
     *
     * @param accountId 柜员号
     * @return yyyyMMdd+8位序列号
     * @author pzz
     */
    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public synchronized String getFlowNo(String accountId) {
        String busSeqNo = sysParamService.getSysParam("BASE_CONFIG", "FLOW_INIT_VALUE", "00000001"); //8位流水
        String nowDate = DateUtils.getDate("yyyyMMdd");
        List<Map<String, Object>> flowNoMapList = tFlowNoDao.queryForList(BaseUtils.map("account_id", accountId, "trandate", nowDate));
        if (flowNoMapList != null && flowNoMapList.size() > 0) { //柜员当天已经生成过流水号
            Map<String, Object> flowNoMap = flowNoMapList.get(0);
            String busiSeqNoStr = (!flowNoMap.containsKey("busi_seq_no") || flowNoMap.get("busi_seq_no") == null || StringUtils.isBlank(flowNoMap.get("busi_seq_no").toString()))
                    ? "0" : flowNoMap.get("busi_seq_no").toString().trim();
            Long busiSeqNoLong = Long.valueOf(busiSeqNoStr) + 1;
            busSeqNo = Utils.fillLeft(busiSeqNoLong + "", '0', 8); //左补0
            int updateCount = 0;
            try {
                updateCount = tFlowNoDao.update(BaseUtils.map("account_id", accountId, "trandate", nowDate, "busi_seq_no", busSeqNo));
            } catch (Exception e) { //获取业务流水号失败
                logger.error(ErrorCodeEnum.HnmCommService016.getDesc(), e);
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService016);
            }
            if (updateCount != 1) { //获取业务流水号失败
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService017);
            }
        } else { //柜员当天第一个流水号
            int saveCount = 0;
            try {
                saveCount = tFlowNoDao.save(BaseUtils.map("account_id", accountId, "trandate", nowDate, "busi_seq_no", busSeqNo));
            } catch (Exception e) { //获取业务流水号失败
                logger.error(ErrorCodeEnum.HnmCommService018.getDesc(), e);
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService018);
            }
            if (saveCount != 1) { //获取业务流水号失败
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService019);
            }
        }
        return sysParamService.getSysParam("ESB_CONFIG", "ESB_CONSUMER_ID", "010404") + nowDate + busSeqNo;
    }

    /**
     * 发送短信
     */
    public void sendMobileMsg(String smsTplName, Map<String, Object> p) {
        String mobileMsgContent = "";
        //获取短信内容
        try {
            mobileMsgContent = messageAssemble.assemble(p, "/msg/" + smsTplName);
        } catch (Exception e) { //获取短信内容失败
            logger.error(ErrorCodeEnum.HnmCommService020.getDesc(), e);
            throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService020);
        }
        String msgChannleCode = sysParamService.getSysParam("SMS_CONFIG", "SMS_CHANNEL_CODE", "MSAP");//由短信平台提供的渠道号，安全认证用
        String msgPassword = sysParamService.getSysParam("SMS_CONFIG", "SMS_PASSWORD", "ms12ap");//由短信平台提供的密码，安全认证用
        String msgMobile = Utils.getNotNullValueString(p, "msg_mobile");
        if (msgMobile == null || StringUtils.isBlank(msgMobile)) { //接收短信的手机号不能为空
            logger.error(ErrorCodeEnum.HnmCommService021.getDesc());
            throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService021);
        }
        String md5CalculateStr = msgChannleCode + msgPassword + msgMobile + mobileMsgContent; //MD5计算值
        String verifyValue = "";
        try {
            verifyValue = Md5Utils.getEncryptedStr(md5CalculateStr, "UTF-8");
        } catch (Exception e) { //发送短信失败[短信内容加密失败]
            logger.error(ErrorCodeEnum.HnmCommService022.getDesc(), e);
            throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService022);
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.putAll(p);
        dataMap.put("bus_seq_no", Utils.getNotNullValueString(p, "bus_seq_no"));
        if (ObjectUtil.isEmpty(dataMap.get("bus_seq_no"))) {
            String userId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_USERID", "E9999");
            dataMap.put("bus_seq_no", this.getFlowNo(userId));
        }
        dataMap.put("auth_user_id", Utils.getNotNullValueString(p, "auth_user_id"));
        //dataMap.put("txcode", Utils.getNotNullValueString(p, "txcode"));
        p.put("txcode", sysParamService.getSysParam("DEFAULT_USERINFO", "TXCODE", "COM000000"));
        dataMap.put("channel_code", msgChannleCode);//由短信平台提供渠道号，安全认证用
        dataMap.put("msg_type", Utils.getNotNullValueString(p, "msg_type"));
        dataMap.put("flag", Utils.getNotNullValueString(p, "msg_flag"));
        dataMap.put("priority_level", Utils.getNotNullValueString(p, "msg_priority_level"));
        dataMap.put("inform_time", Utils.getNotNullValueString(p, "msg_inform_time"));
        dataMap.put("acct_no", Utils.getNotNullValueString(p, "msg_acct_no"));
        dataMap.put("mobile", msgMobile);
        dataMap.put("verify_type", "MD5");
        dataMap.put("verify_value", verifyValue);
        dataMap.put("msg", mobileMsgContent);
        dataMap.put("service_code", "11002000003"); // esb交易码
        dataMap.put("service_scene", "04");// 场景号
        Map<String, Object> receiveMap = null;
        try {
            receiveMap = esbMsgProcService.sendAndReceiveMsg(dataMap, false);
        } catch (Exception e) {
            logger.error("发送短信通讯异常，原因：", e);
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            } else { //通讯异常
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
            }
        }
        if ("0".equals(esbMsgProcService.getRetStatus(receiveMap))) { //1，成功；0，失败；
            String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap); //获取短信内容失败，原因：%s
            String msg = "获取短信内容失败，原因：" + retCodeAndMsg;
            logger.error(msg);
            throw new BusinessException("HnmCommService", msg);
        }
    }

    /**
     * 推送消息
     */
    public void pushMsg(Map<String, Object> p) {

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.putAll(p);
        dataMap.put("bus_seq_no", Utils.getNotNullValueString(p, "bus_seq_no"));
        if (ObjectUtil.isEmpty(dataMap.get("bus_seq_no"))) {
            String userId = sysParamService.getSysParam("DEFAULT_USERINFO", "0000_USERID", "E9999");
            dataMap.put("bus_seq_no", this.getFlowNo(userId));
        }
        dataMap.put("auth_user_id", Utils.getNotNullValueString(p, "auth_user_id"));
        //dataMap.put("txcode", Utils.getNotNullValueString(p, "txcode"));
        p.put("txcode", sysParamService.getSysParam("DEFAULT_USERINFO", "TXCODE", "COM000000"));
        dataMap.put("service_code", "11002000003"); // esb交易码
        dataMap.put("service_scene", "04");// 场景号
        Map<String, Object> receiveMap = null;
        try {
            receiveMap = esbMsgProcService.sendAndReceiveMsg(dataMap, false);
        } catch (Exception e) {
            logger.error("推送消息异常，原因：", e);
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            } else { //通讯异常
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
            }
        }
        if ("0".equals(esbMsgProcService.getRetStatus(receiveMap))) { //1，成功；0，失败；
            String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap); //推送消息失败，原因：%s
            String msg = "推送消息失败，原因：" + retCodeAndMsg;
            logger.error(msg);
            throw new BusinessException("HnmCommService", msg);
        }
    }

    /**
     * 新增或者修改站点后  同步至销管系统
     */
    public boolean sendToXG(Map<String, Object> map) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("user_no", map.get("site_no"));
        dataMap.put("user_name", map.get("site_name"));
        dataMap.put("trans_type", map.get("trans_type"));
        dataMap.put("branch_no", map.get("branch_id"));
        dataMap.put("branch_name", map.get("branch_name"));

        dataMap.put("iden_type", "");
        dataMap.put("iden_no", "");
        dataMap.put("phone_no", "");
        dataMap.put("email", "");

        dataMap.put("txcode", sysParamService.getSysParam("DEFAULT_USERINFO", "TXCODE", "COM000000"));
        dataMap.put("service_code", "11002000122"); // esb交易码
        dataMap.put("service_scene", "03");// 场景号
        Map<String, Object> receiveMap = null;
        try {
            receiveMap = esbMsgProcService.sendAndReceiveMsg(dataMap, false);
        } catch (Exception e) {
            logger.error("同步至销管系统数据异常，原因：", e);
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            } else { //通讯异常
                throw ErrorCodeEnum.throwBusinessException(ErrorCodeEnum.HnmCommService023);
            }
        }
        if ("0".equals(esbMsgProcService.getRetStatus(receiveMap))) { //1，成功；0，失败；
            String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap); //推送消息失败，原因：%s
            String msg = "同步至销管系统数据失败，原因：" + retCodeAndMsg;
            logger.error(msg);
            throw new BusinessException("HnmCommService", msg);
        }
        return true;
    }

    /**
     * 记录每天站点信息
     */
    public void logSite() {
        siteDao.logSite();
    }


    /**
     * 查询账户信息
     */
    public List<Map<String, Object>> qureyAch(Map<String, Object> parms) {
        parms.put("flag", "1");
        parms.put("rcdstatus", "A");
        List<Map<String, Object>> achList = thfacctDao.query(parms);
        return achList;
    }

    /**
     * 查询客户信息
     */
    public List<Map<String, Object>> qureyCus(Map<String, Object> parms) {
        List<Map<String, Object>> cusList = tclientDao.query(parms);
        return cusList;
    }

    /**
     * 查询资产信息-客户纬度
     */
    public List<Map<String, Object>> qureyZC(Map<String, Object> parms) {
        List<Map<String, Object>> cusList = tperprpDao.queryZC(parms);
        return cusList;
    }

    /**
     * 查询存款信息-客户纬度
     */
    public List<Map<String, Object>> qureyCK(Map<String, Object> parms) {
        List<Map<String, Object>> cusList = tperprpDao.queryCK(parms);
        return cusList;
    }

    /**
     * 查询资产信息-账户纬度
     */
    public List<Map<String, Object>> qureyZC2(Map<String, Object> parms) {
        parms.put("flag", "1");
        parms.put("rcdstatus1", "A");
        parms.put("rcdstatus2", "A");
        List<Map<String, Object>> cusList = ttimpntDao.queryZC(parms);
        return cusList;
    }

    /**
     * 查询存款信息-账户纬度
     */
    public List<Map<String, Object>> qureyCK2(Map<String, Object> parms) {
        parms.put("flag", "1");
        parms.put("rcdstatus1", "A");
        parms.put("rcdstatus2", "A");
        List<Map<String, Object>> cusList = ttimpntDao.queryCK(parms);
        return cusList;
    }

    /**
     * 查询助农取款信息
     */
    public List<Map<String, Object>> qureyZN(Map<String, Object> parms) {
        parms.put("stat", "1");
        parms.put("prids", "'56','58','57','01'");
        List<Map<String, Object>> znList = cupstsDao.query(parms);
        return znList;
    }

    /**
     * 处理BigDecimal 四舍五入 保留两位小数
     */
    public BigDecimal dealVal(BigDecimal big) {
        big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
        return big;
    }

    /**
     * 组装审批流程 新版
     */
    public String getNewApprovalProcess(Map<String, Object> req_map, Map<String, Object> approval_map) {
        String approval_process = "";
        if (ObjectUtil.isNotEmpty(approval_map.get("new_approval_process"))) {
            List<Map<String, Object>> approvalProcessList = (List<Map<String, Object>>) JSON.parse(String.valueOf(approval_map.get(
                    "new_approval_process")));
            StringBuffer approval_process_sb = new StringBuffer();
            //递归 拼装审批流程
            try {
                getApprovalProcessDetail(approval_process_sb, req_map, approvalProcessList);
            } catch (Exception e) {
                return approval_process;
            }

            if (approval_process_sb.length() > 0) {
                approval_process = approval_process_sb.substring(0, approval_process_sb.length() - 1);
            }
        }
        return approval_process;
    }

    public void getApprovalProcessDetail(StringBuffer approval_process_sb, Map<String, Object> req_map, List<Map<String, Object>> approvalProcessList) {
        if (approvalProcessList != null && approvalProcessList.size() > 0) {
            for (Map<String, Object> map : approvalProcessList) {
                if (ObjectUtil.isNotEmpty(map.get("node_item"))) {
                    Map<String, Object> node_item_map = (Map<String, Object>) map.get("node_item");
                    Set<String> keys = node_item_map.keySet();
                    List<Boolean> blist = Collections.synchronizedList(new ArrayList<>());
                    getApprovalProcessDetailDG(blist, node_item_map, keys, req_map);
                    if (blist != null && blist.size() > 0 && blist.contains(true)) {
                        approval_process_sb.append(map.get("node_id")).append("-");
                        if (ObjectUtil.isNotEmpty(map.get("sub_tree"))) {
                            //递归
                            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("sub_tree");
                            getApprovalProcessDetail(approval_process_sb, req_map, list);
                        }
                        break;
                    }
                } else {
                    approval_process_sb.append(map.get("node_id")).append("-");
                    if (ObjectUtil.isNotEmpty(map.get("sub_tree"))) {
                        //递归
                        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("sub_tree");
                        getApprovalProcessDetail(approval_process_sb, req_map, list);
                    }
                    break;
                }
            }
        }
    }

    public void getApprovalProcessDetailDG(List<Boolean> blist, Map<String, Object> node_item_map, Set<String> keys, Map<String, Object> req_map) {
        boolean flag = false;
        for (String key : keys) {
            if ("或项".equals(key)) {
                if (node_item_map.get(key) instanceof List) {
                    List<Map<String, Object>> slist = (List<Map<String, Object>>) node_item_map.get(key);
                    boolean dgflag = false;
                    for (Map<String, Object> smap : slist) {
                        if (ObjectUtil.isNotEmpty(smap.get("或项")) || ObjectUtil.isNotEmpty(smap.get("和项"))) {
                            //需要递归
                            dgflag = true;
                            break;
                        }
                    }
                    if (dgflag) {
                        //递归
                        for (Map<String, Object> smap : slist) {
                            blist.clear();
                            Set<String> keys2 = smap.keySet();
                            getApprovalProcessDetailDG(blist, smap, keys2, req_map);
                            if (blist.contains(true)) {
                                break;
                            }
                        }
                    } else {
                        //做判断 需要slist满足其一即可
                        if (blist.contains(true)) {
                            //flag = true;
                            break;
                        } else {
                            int num = 0;
                            for (Map<String, Object> smap : slist) {
                                if (ObjectUtil.isNotEmpty(smap.get("项目"))) {
                                    boolean sflag = checkNodeItem(smap, req_map);
                                    if (sflag) {
                                        num++;
                                        break;
                                    }
                                }
                            }
                            if (num > 0) {
                                flag = true;
                                blist.add(flag);
                                break;
                            } else {
                                flag = false;
                                blist.add(flag);
                                break;
                            }
                        }
                    }
                } else {
                    Map<String, Object> smap = (Map<String, Object>) node_item_map.get(key);
                    if (ObjectUtil.isNotEmpty(smap.get("或项")) || ObjectUtil.isNotEmpty(smap.get("和项"))) {
                        //递归
                        blist.clear();
                        Set<String> keys2 = smap.keySet();
                        getApprovalProcessDetailDG(blist, smap, keys2, req_map);
                    } else {
                        //做判断 满足即可
                        if (blist.contains(true)) {
                            //flag = true;
                            break;
                        } else {
                            if (ObjectUtil.isNotEmpty(smap.get("项目"))) {
                                flag = checkNodeItem(smap, req_map);
                                blist.add(flag);
                                break;
                            }
                        }
                    }
                }
            } else if ("和项".equals(key)) {
                if (node_item_map.get(key) instanceof List) {
                    List<Map<String, Object>> slist = (List<Map<String, Object>>) node_item_map.get(key);
                    boolean dgflag = false;
                    for (Map<String, Object> smap : slist) {
                        if (ObjectUtil.isNotEmpty(smap.get("或项")) || ObjectUtil.isNotEmpty(smap.get("和项"))) {
                            //需要递归
                            dgflag = true;
                            break;
                        }
                    }
                    if (dgflag) {
                        //递归
                        for (Map<String, Object> smap : slist) {
                            blist.clear();
                            Set<String> keys2 = smap.keySet();
                            getApprovalProcessDetailDG(blist, smap, keys2, req_map);
                            if (blist.contains(false)) {
                                blist.clear();
                                break;
                            }
                        }
                    } else {
                        //做判断 需要slist满足所有
                        if (blist.contains(true)) {
                            //flag = true;
                            break;
                        } else {
                            int num = 0;
                            for (Map<String, Object> smap : slist) {
                                if (ObjectUtil.isNotEmpty(smap.get("项目"))) {
                                    boolean sflag = checkNodeItem(smap, req_map);
                                    if (sflag) {
                                        num++;
                                    }
                                }
                            }
                            if (num == slist.size()) {
                                flag = true;
                                blist.add(flag);
                                break;
                            } else {
                                flag = false;
                                blist.add(flag);
                                break;
                            }
                        }
                    }
                } else {
                    Map<String, Object> smap = (Map<String, Object>) node_item_map.get(key);
                    if (ObjectUtil.isNotEmpty(smap.get("或项")) || ObjectUtil.isNotEmpty(smap.get("和项"))) {
                        blist.clear();
                        Set<String> keys2 = smap.keySet();
                        getApprovalProcessDetailDG(blist, smap, keys2, req_map);
                    } else {
                        //做判断 满足即可
                        if (blist.contains(true)) {
                            //flag = true;
                            break;
                        } else {
                            if (ObjectUtil.isNotEmpty(smap.get("项目"))) {
                                flag = checkNodeItem(smap, req_map);
                                blist.add(flag);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkNodeItem(Map<String, Object> smap, Map<String, Object> req_map) {
        boolean flag = false;
        String node_item = String.valueOf(smap.get("项目"));
        try {
            String[] node_item_arr = node_item.split(" ");
            if (node_item_arr.length != 3) {
                logger.info("校验参数非法：" + node_item);
                throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
            }
            String item = node_item_arr[0];
            String symbol = node_item_arr[1];
            String value = node_item_arr[2];

            if ("年龄".equals(item)) {
                //年龄
                if (ObjectUtil.isNotEmpty(req_map.get("master_identify_no"))) {
                    String age = evaluate(String.valueOf(req_map.get("master_identify_no")));
                    if (StringUtils.isEmpty(age)) {
                        logger.info("校验参数非法：" + node_item);
                        throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                    }

                    if (">".equals(symbol)) {//>
                        if (Integer.parseInt(age) > Integer.parseInt(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if (">=".equals(symbol)) {//>=
                        if (Integer.parseInt(age) >= Integer.parseInt(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("<".equals(symbol)) {//<
                        if (Integer.parseInt(age) < Integer.parseInt(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("<=".equals(symbol)) {//<=
                        if (Integer.parseInt(age) <= Integer.parseInt(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("=".equals(symbol)) {//=
                        if (Integer.parseInt(value) == Integer.parseInt(age)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (Integer.parseInt(value) != Integer.parseInt(age)) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("信用类、保证类贷款余额".equals(item)) {
                //信用类、保证类贷款余额
                if (ObjectUtil.isNotEmpty(req_map.get("credit_bal"))) {
                    String credit_bal = String.valueOf(req_map.get("credit_bal"));
                    if (">".equals(symbol)) {//>
                        if (Double.parseDouble(credit_bal) > Double.parseDouble(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if (">=".equals(symbol)) {//>=
                        if (Double.parseDouble(credit_bal) >= Double.parseDouble(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("<".equals(symbol)) {//<
                        if (Double.parseDouble(credit_bal) < Double.parseDouble(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("<=".equals(symbol)) {//<=
                        if (Double.parseDouble(credit_bal) <= Double.parseDouble(value)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("=".equals(symbol)) {//=
                        if (Double.parseDouble(value) == Double.parseDouble(credit_bal)) {
                            //满足条件
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (Double.parseDouble(value) != Double.parseDouble(credit_bal)) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("征信逾期情况".equals(item)) {
                //征信逾期情况
                if (ObjectUtil.isNotEmpty(req_map.get("credit_investigation"))) {
                    String credit_investigation = String.valueOf(req_map.get("credit_investigation"));
                    if ("=".equals(symbol)) {//=
                        if (value.equals(CreditInvestigationEnum.findByItem(credit_investigation).getValue())) {
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (!value.equals(CreditInvestigationEnum.findByItem(credit_investigation).getValue())) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("学历".equals(item)) {
                //学历
                if (ObjectUtil.isNotEmpty(req_map.get("education"))) {
                    String education = String.valueOf(req_map.get("education"));
                    boolean sflag = false;
                    if ("包含".equals(symbol)) {//包含
                        String[] node_item_value = value.split("\\|");
                        for (String item_value : node_item_value) {
                            if (item_value.equals(EducationEnum.findByItem(education).getValue())) {
                                sflag = true;
                                break;
                            }
                        }
                        if (sflag) {
                            flag = true;
                        }
                    } else if ("不包含".equals(symbol)) {//不包含
                        String[] node_item_value = value.split("\\|");
                        for (String item_value : node_item_value) {
                            if (item_value.equals(EducationEnum.findByItem(education).getValue())) {
                                sflag = true;
                                break;
                            }
                        }
                        if (!sflag) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("站长候选人信息情况".equals(item)) {
                //站长候选人信息情况
                if (ObjectUtil.isNotEmpty(req_map.get("occupation"))) {
                    String occupation = String.valueOf(req_map.get("occupation"));
                    boolean sflag = false;
                    if ("包含".equals(symbol)) {//包含
                        String[] node_item_value = value.split("\\|");
                        for (String item_value : node_item_value) {
                            if (item_value.equals(OccupationEnum.findByItem(occupation).getValue())) {
                                sflag = true;
                                break;
                            }
                        }
                        if (sflag) {
                            flag = true;
                        }
                    } else if ("不包含".equals(symbol)) {//不包含
                        String[] node_item_value = value.split("\\|");
                        for (String item_value : node_item_value) {
                            if (item_value.equals(OccupationEnum.findByItem(occupation).getValue())) {
                                sflag = true;
                                break;
                            }
                        }
                        if (!sflag) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("站长是否特殊人员".equals(item)) {
                //站长是否特殊人员
                if (ObjectUtil.isNotEmpty(req_map.get("special_staff"))) {
                    String special_staff = String.valueOf(req_map.get("special_staff"));
                    if ("=".equals(symbol)) {//=
                        if (value.equals(TrueOrFalseEnum.findByItem(special_staff).getValue())) {
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (!value.equals(TrueOrFalseEnum.findByItem(special_staff).getValue())) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("近2年是否发生过90天及以上的逾期".equals(item)) {
                //近2年是否发生过90天及以上的逾期
                if (ObjectUtil.isNotEmpty(req_map.get("over_due_term"))) {
                    String over_due_term = String.valueOf(req_map.get("over_due_term"));
                    if ("=".equals(symbol)) {//=
                        if (value.equals(TrueOrFalseEnum.findByItem(over_due_term).getValue())) {
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (!value.equals(TrueOrFalseEnum.findByItem(over_due_term).getValue())) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else if ("风险等级".equals(item)) {

                if (ObjectUtil.isNotEmpty(req_map.get("risk_level"))) {
                    String risk_level = String.valueOf(req_map.get("risk_level"));
                    if ("=".equals(symbol)) {//=
                        if (value.equals(risk_level)) {
                            flag = true;
                        }
                    } else if ("!=".equals(symbol)) {//!=
                        if (!value.equals(risk_level)) {
                            flag = true;
                        }
                    }
                } else {
                    logger.info("校验参数非法：" + node_item);
                    throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
                }
            } else {
                logger.info("校验参数非法：" + node_item);
                throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
            }
        } catch (Exception e) {
            logger.info("校验参数非法：" + node_item);
            throw new BusinessException("HnmCommServiceException", "校验参数非法：" + node_item);
        }
        return flag;
    }


    /**
     * 根据身份证号码计算年龄
     */
    public String evaluate(String sfzjh) {
        if (sfzjh == null || "".equals(sfzjh)) {
            return "";
        }
        if (sfzjh.length() != 15 && sfzjh.length() != 18) {
            return "";
        }

        String age = "";

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);

        int year = Integer.valueOf(sfzjh.substring(6, 10));
        int month = Integer.valueOf(sfzjh.substring(10, 12));
        int day = Integer.valueOf(sfzjh.substring(12, 14));

        if (!isDate(year + "-" + month + "-" + day)) {
            return "";
        }

        if ((month < monthNow) || (month == monthNow && day <= dayNow)) {
            age = String.valueOf(yearNow - year);
        } else {
            age = String.valueOf(yearNow - year - 1);
        }
        return age;
    }

    public boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
