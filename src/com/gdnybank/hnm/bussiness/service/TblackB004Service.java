package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.dao.SysDictDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询风险客户列表
 */
@Service
public class TblackB004Service extends TXBaseService {

    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private SysDictDao sysDictDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        Map<String, Object> params = new HashMap<>();

        if (ObjectUtil.isNotEmpty(p.get("cust_name"))) {
            params.put("cust_name", "%" + p.get("cust_name") + "%");
        }
        if (ObjectUtil.isNotEmpty(p.get("cert_num"))) {
            params.put("cert_num", "%" + p.get("cert_num") + "%");
        }
        if (ObjectUtil.isNotEmpty(p.get("acct_id"))) {
            params.put("acct_id", "%" + p.get("acct_id") + "%");
        }
        if (ObjectUtil.isNotEmpty(p.get("risk_level"))) {
            params.put("risk_level", p.get("risk_level"));
        }
        if (ObjectUtil.isNotEmpty(p.get("cert_type_cd"))) {
            params.put("cert_type_cd", p.get("cert_type_cd"));
        }
        if (ObjectUtil.isNotEmpty(p.get("create_time"))) {
            params.put("create_time", p.get("create_time"));
        }
        if (ObjectUtil.isNotEmpty(p.get("type_cd"))) {
            String typeCd = String.valueOf(p.get("type_cd"));
            if ("0".equals(typeCd)) {

                params.put("type_cd", "'1','2','3','4','5','6','7'");

            } else if ("4".equals(typeCd)) {
                //查询账户表的数据
                params.put("is_dep_acct_basic", "1");
            } else {
                params.put("blklist_type_cd", typeCd);
            }
        }
        if (ObjectUtil.isNotEmpty(p.get("site_no"))) {
            String siteNo = String.valueOf(p.get("site_no"));
            params.put("site_no", siteNo);
        }
        //控制截止日期解除控制后10天的日期
        Date date = DateUtils.addDay(DateUtils.getNowDate(), -10);
        String endDate = DateUtils.getDate(date, "yyyy-MM-dd");
        params.put("endDate", endDate);
        params.put("valid_flg", "1");

        setPageInfo(p);
        List<Map<String, Object>> returnList;
        long total = 0;
        List<Map<String, Object>> certType;
        try {
            returnList = blklistCtrlDao.queryForListByPage(params);

            total = MfpContextHolder.getPageInfo().getITotalDisplayRecords();

            certType = sysDictDao.queryForList(BaseUtils.map("dict_type_code", "11"));
        } catch (Exception e) {
            throw new BusinessException("TblackB004ServiceException", "查询风险客户列表失败");
        }
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("total", total);
        resultMap.put("returnList", returnList);
        resultMap.put("certtype", certType);

        return resultMap;
    }

    private void setPageInfo(Map<String, Object> p) {
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page")) ? p.get("page").toString() : "0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number")) ? p.get("number").toString() : "10");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart(page * number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }
}
