package com.gdnybank.hnm.system.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.SmsSettingDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class Tsmsset001Service extends TXBaseService {

    @Autowired
    private SmsSettingDao smsSettingDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {
        if (ObjectUtil.isEmpty(p.get("use_up"))) {
            throw new BusinessException("Tsmsset001ServiceException", "trigger_condition is empty");
        }

        String userId = MfpContextHolder.getLoginId();
        //修改短信任务参数
        if (ObjectUtil.isNotEmpty(p.get("id"))) {
            p.put("update_time", DateUtil.now());
            p.put("updater", userId);
            //新增短信任务
        } else {
            //判断任务是否存在
            long count = smsSettingDao.count(BaseUtils.map("sms_pro", p.get("sms_pro")));
            if (count > 0) {
                throw new BusinessException("Tsmsset001ServiceException", "该任务已存在");
            }
            p.put("create_time", DateUtil.now());
            p.put("creater", userId);
        }

        smsSettingDao.saveOrUpdate(p);
        return BaseUtils.map("success", 1);
    }
}
