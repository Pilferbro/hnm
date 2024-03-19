package com.gdnybank.hnm.report.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.*;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 客户明细
 */
@Service
public class Treport017Service extends TXBaseService {

    private static final Log logger = LogFactory.getLog(Treport017Service.class);

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private TclientDao tclientDao;
    @Autowired
    private TperprpDao tperprpDao;
    @Autowired
    private ThfacctDao thfacctDao;
    @Autowired
    private TtimpntDao ttimpntDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("query_type"))) {
            logger.error("query_level为必传字段");
            throw new BusinessException("Treport017ServiceException", "query_type为必传字段");
        }
        String query_type = p.get("query_type").toString();

        if (ObjectUtil.isEmpty(p.get("query_org"))) {
            logger.error("query_org为必传字段");
            throw new BusinessException("Treport017ServiceException", "query_org为必传字段");
        }
        String query_org = p.get("query_org").toString();


        String query_branch = "";

        String userId = MfpContextHolder.getLoginId();
        int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
        if (userRoleLevel == 0) {
            throw new BusinessException("Treport017ServiceException", "登陆人员未配置角色");
        }

        String branchids = "";
        //总行级可以看所有
        if (userRoleLevel != 1) {
            branchids = hnmCommService.getUserBranchids();
        }

        if (userRoleLevel == 4) {
            //客户经理级别
            p.put("mgr_id", userId);
        }

        if (!StringUtils.isEmpty(query_branch)) {
            branchids = hnmCommService.getUserBranchidsBybranch(query_branch);
        }

        PageInfo pageInfo = new PageInfo();
        int page = Integer.parseInt(p.get("page").toString());
        int num = Integer.parseInt(p.get("number").toString());
        pageInfo.setIDisplayStart((page - 1) * num);
        pageInfo.setIDisplayLength(num);
        MfpContextHolder.setPageInfo(pageInfo);

        Map<String, Object> result = new HashMap<String, Object>();


        //查询所有服务点
        List<Map<String, Object>> siteAllList = new ArrayList<>();
        if (!StringUtils.isEmpty(branchids)) {
            if ("1".equals(query_org)) {
                //所属机构
                p.put("orgids", branchids);
            } else if ("2".equals(query_org)) {
                //落地支行
                p.put("branchids", branchids);
            }
            p.put("is_delete", "0");
            p.put("approval_status", "2");
            siteAllList = hSiteDao.querySites(delkong(p));
        } else {
            p.put("is_delete", "0");
            p.put("approval_status", "2");
            siteAllList = hSiteDao.querySites(delkong(p));
        }
        //查询服务点下所有的客户
        String siteIds = queryIds(siteAllList);
        p.put("siteIds", siteIds);

        List<Map<String, Object>> returnList = new ArrayList<>();

        Map<String, Object> cMap = queryConditionEncapsulation(p);

        //客户纬度
        if ("1".equals(query_type)) {

            List<Map<String, Object>> clients = tclientDao.queryClients(delkong(cMap));

            Long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", clients);
        }

        //账户纬度
        if ("2".equals(query_type)) {

            List<Map<String, Object>> hfaccts = thfacctDao.queryByclientIds(delkong(cMap));
            if (hfaccts != null && hfaccts.size() > 0) {

                for (Map<String, Object> hfacctMap : hfaccts) {

                    //卡号展示前后4位
                    String str = String.valueOf(hfacctMap.get("richnbr"));
                    String richnbr = str.substring(0, 4) + "******" + str.substring(str.length() - 4);
                    hfacctMap.put("richnbr", richnbr);
                    hfacctMap.put("currents", "0");
                    hfacctMap.put("termly", "0");

                    returnList.add(hfacctMap);
                }
            }
            Long toatl = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            result.put("total", toatl);
            result.put("returnList", returnList);
        }

        logger.info("Treport017Service执行完成");
        return result;
    }

    /**
     * 封装查询条件
     */
    private Map<String, Object> queryConditionEncapsulation(Map<String, Object> p) {


        Date date = new Date();
        if (p.get("query_type").equals("1")) {

            if (ObjectUtil.isNotEmpty(p.get("cusname"))) {
                p.put("cltname", p.get("cusname"));
            }


            if (ObjectUtil.isNotEmpty(p.get("query_date"))) {
                date = DateUtil.parse(p.get("query_date").toString());
            }

        }

        Map<String, Object> dateMap = dealDate(date);
        p.put("yyyyMM", dateMap.get("yyyyMM"));
        p.put("onlbal", dateMap.get("onlbal"));


        if ("2".equals(p.get("query_type"))) {
            if (ObjectUtil.isNotEmpty(p.get("minDate"))) {
                String minDate = p.get("minDate").toString();
                p.put("minDate", DateUtils.getDate(DateUtil.parse(minDate), "yyyyMMdd"));
            }
            if (ObjectUtil.isNotEmpty(p.get("maxDate"))) {
                String maxDate = p.get("maxDate").toString();
                p.put("maxDate", DateUtils.getDate(DateUtil.parse(maxDate), "yyyyMMdd"));
            }
        }

        return p;
    }


    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : data.keySet()) {
            if (data.get(key) == null || "".equals(data.get(key))) {

            } else {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }

    public String queryIds(List<Map<String, Object>> list) {
        if (list != null || list.size() > 0) {

            String strIds = "";

            for (Map<String, Object> map : list) {
                strIds += ",'" + map.get("site_no") + "'";
            }
            if (strIds.startsWith(",")) {
                strIds = strIds.substring(1);
            }
            return strIds;
        }
        return null;
    }

    /**
     * 日期处理
     */
    public Map<String, Object> dealDate(Date date) {
        if (ObjectUtil.isNotEmpty(date)) {
            Map<String, Object> dataMap = new HashMap<>();

            //获取当前月份
            String yyyyMM = DateUtils.getDate(date, "yyyyMM");
            dataMap.put("yyyyMM", yyyyMM);
            //获取当前日期所在月的天数
            int day = DateUtil.dayOfMonth(date);
            String day_str = String.valueOf(day);
            if (day_str.length() == 1) {
                day_str = "0" + day_str;
            }
            String onlbal = "onlbal" + day_str;
            dataMap.put("onlbal", onlbal);
            return dataMap;
        }
        return null;
    }

}
