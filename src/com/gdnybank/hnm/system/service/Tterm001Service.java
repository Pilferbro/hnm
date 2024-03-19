package com.gdnybank.hnm.system.service;

import cn.hutool.core.date.DateUtil;
import com.gdnybank.hnm.pub.dao.TerminalInfoDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author PHL
 * @Date 2023/2/13 11:00
 * @注释 新增版本号管理
 */
@Transactional
@Service
public class Tterm001Service extends TXBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TerminalInfoDao terminalInfoDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {
        Map<String, Object> paramMap = new HashMap<>(p);
        String userId = MfpContextHolder.getLoginId();

        paramMap.put("is_delete", "0");
        paramMap.put("creater", userId);
        paramMap.put("create_time", DateUtil.now());
        String appVersion = String.valueOf(p.get("app_version"));
        try {
            List<Map<String, Object>> isExist = terminalInfoDao
                    .queryForList(BaseUtils.map("app_version", appVersion, "is_delete", "0"));
            if (isExist != null && isExist.size() > 0) {
                throw new BusinessException("Tterm001ServiceException", "版本号已存在！");
            }
            terminalInfoDao.save(paramMap);
            logger.info("新增版本号成功");
        } catch (Exception e) {
            throw new BusinessException("Tterm001ServiceException", "新增失败！");
        }
        return BaseUtils.map("success", 1);
    }
}
