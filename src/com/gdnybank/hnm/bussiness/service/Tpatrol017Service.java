package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Tpatrol017Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tpatrol017Service.class);

    @Autowired
    private HnmCommService hnmCommService;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {
        Map<String, Object> patrolMap = new HashMap<>();
        try {
            //校验角色   总行级可以看所有
            String userId = MfpContextHolder.getLoginId();
            int userRoleLevel = hnmCommService.getUserRoleLevel(BaseUtils.map("account_id", userId));
            if (userRoleLevel == 0) {
                logger.error("登陆人员" + userId + "未配置角色");
                throw new BusinessException("Tpatrol017ServiceException", "登陆人员未配置角色");
            }
            if (ObjectUtil.isNotEmpty(p.get("status"))) {
                String str = p.get("status").toString();
                String status = str.substring(1, str.length() - 1);
                p.put("status", status);
            }
            if (userRoleLevel != 1) {
                String branchids = hnmCommService.getUserBranchids();
                p.put("orgids", branchids);
            }

            if (userRoleLevel == 4) {
                p.put("mrgid", userId);
            }

            if (p.containsKey("content_text")){
                p.put("content_text","%"+p.get("content_text")+"%");
            }
            if (p.containsKey("requirement")){
                p.put("requirement","%"+p.get("requirement")+"%");
            }
            //分页信息
            setPageInfo(p);
            List<Map<String, Object>> resultList = hPatrolLogContentDao.queryForListByPages(delkong(p));

            long total = MfpContextHolder.getPageInfo().getITotalDisplayRecords();

            resultList.sort((o1, o2) -> ((String) o2.get("created")).compareTo((String) o1.get("created")));
            patrolMap.put("resultList", resultList);
            patrolMap.put("total", total);

            return patrolMap;
        } catch (Exception e) {
            logger.info("查询风险列表失败", e);
            throw new BusinessException("Tpatrol017ServiceException", "查询风险列表失败");
        }

    }

    private void setPageInfo(Map<String, Object> p) {
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page")) ? p.get("page").toString() : "0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number")) ? p.get("number").toString() : "10");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart(page * number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }

    public Map<String, Object> delkong(Map<String, Object> data) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : data.keySet()) {
            if (data.get(key) != null && !"".equals(data.get(key))) {
                dataMap.put(key, data.get(key));
            }
        }
        return dataMap;
    }
}
