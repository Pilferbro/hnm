package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.gdnybank.hnm.pub.service.ApprovalService;
import com.nantian.mfp.framework.utils.BaseUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Operate18 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private ApprovalService approvalService;

    @Override
    public Object doHandle(Map<String, Object> p) {

        String operateArea = String.valueOf(p.get("operate_area"));

        List<Map<String, Object>> siteList = new ArrayList<>();

        //2、查询符合条件的服务点
        //第一层筛选 整改范围
        List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                "approval_status", "2", "statuss", operateArea));
        //第二层筛选 该服务点站长是否为中高风险人员
        if (list1 != null && list1.size() > 0) {
            for (Map<String, Object> map1 : list1) {
                //校验站长是否为风险人员
                boolean flag = approvalService.checkRiskCustomer(BaseUtils.map("id", "1", "cert_num", map1.get("master_identify_no"), "card_no", map1.get("card_no")));
                if (flag) {
                    siteList.add(map1);
                }
            }
        }

        p.put("site_list", siteList);
        p.put("yyyyMM", null);
        p.put("type", 2);

        return p;
    }

}
