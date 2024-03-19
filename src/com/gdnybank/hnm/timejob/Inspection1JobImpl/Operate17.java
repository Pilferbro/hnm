package com.gdnybank.hnm.timejob.Inspection1JobImpl;

import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.gdnybank.hnm.pub.interfaces.Inspection1JobStrategy;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Operate17 implements Inspection1JobStrategy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HSiteDao hSiteDao;
    @Autowired
    private BlklistCtrlDao blklistCtrlDao;

    @Override
    public Object doHandle(Map<String, Object> p) {

        Date date = (Date) p.get("date");
        String operate_area = String.valueOf(p.get("operate_area"));
        List<Map<String, Object>> site_list = new ArrayList<>();

        String toDay = DateUtils.getDate(date, "yyyy-MM-dd");
       String yyyyMMdd = DateUtils.getDate(DateUtils.addDay(date, -1), "yyyy-MM-dd");
        Map<String, Object> params = new HashMap<>();
        params.put("create_time", toDay);
        params.put("valid_flg", "1");
        params.put("is_dep_acct_basic", "1");

        //查询上日在核心系统被运营人员设置为7级和8级风险的账户数量和站点
        List<Map<String, Object>> list = blklistCtrlDao.queryByConditionToInspection(params);

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                List<Map<String, Object>> list1 = hSiteDao.queryForListBycondition(BaseUtils.map("is_delete", "0",
                        "approval_status", "2", "site_no", map.get("tlrclt"), "statuss", operate_area));
                if (list1 != null && list1.size() > 0) {

                    Map<String, Object> map1 = list1.get(0);
                    map1.put("count", map.get("count"));

                    site_list.add(map1);
                }
            }
        }
        p.put("site_list", site_list);
        p.put("yyyyMM", yyyyMMdd);
        p.put("type", 6);

        return p;
    }

}
