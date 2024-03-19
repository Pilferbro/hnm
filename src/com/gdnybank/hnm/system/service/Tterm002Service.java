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
 * @注释 修改版本号管理
 */
@Transactional
@Service
public class Tterm002Service extends TXBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TerminalInfoDao terminalInfoDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {
        Map<String, Object> paramMap = new HashMap<>(p);
        String userId = MfpContextHolder.getLoginId();

        paramMap.put("updater",userId);
        paramMap.put("update_time", DateUtil.now());

        try {
            if (paramMap.containsKey("id")){

                terminalInfoDao.updateByPk(paramMap);
            }else {
                terminalInfoDao.updateDistanc(paramMap);
            }
            logger.info("修改成功！");
        } catch (Exception e) {
            throw new BusinessException("Tterm002ServiceException", "修改失败！");
        }
        return BaseUtils.map("success", 1);
    }
}
