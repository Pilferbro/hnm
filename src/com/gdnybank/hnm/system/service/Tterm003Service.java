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
import java.util.Map;

/**
 * @Author PHL
 * @Date 2023/2/13 11:00
 * @注释 删除版本号
 */
@Transactional
@Service
public class Tterm003Service extends TXBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TerminalInfoDao terminalInfoDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {
        Map<String, Object> paramMap = new HashMap<>(p);
        String userId = MfpContextHolder.getLoginId();

        paramMap.put("updater",userId);
        paramMap.put("is_delete", "1");
        paramMap.put("update_time", DateUtil.now());

        try {
            terminalInfoDao.updateByPk(paramMap);
            logger.info("删除成功！");
        } catch (Exception e) {
            throw new BusinessException("Tterm003ServiceException", "删除失败！");
        }
        return BaseUtils.map("success", 1);
    }
}
