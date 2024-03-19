package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SmsSettingDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询短信任务
 */
@Service
public class Tsmsset004Service extends TXBaseService {

    @Autowired
    private SmsSettingDao smsSettingDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        setPageInfo(p);
        List<Map<String, Object>> returnList = smsSettingDao.queryForListByPage(p);
        long total = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
        return BaseUtils.map("returnList", returnList, "total", total);

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
