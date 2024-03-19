package com.gdnybank.hnm.system.service;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @desc  清除用户登陆错误锁定
 */
@Service
public class Tuser011Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {
        //查询用户
        List<Map<String, Object>> list = sysAccountDao.queryForList(BaseUtils.map("account_id",p.get("account_id")));
        if(list!=null && list.size()>0){
            Map<String, Object> map = list.get(0);
            sysAccountDao.clearErrorCount(BaseUtils.map("account_id",p.get("account_id")));

        }else{
            throw new BusinessException("Tuser011ServiceException", "未查询到用户");
        }

        return BaseUtils.map("success","1");

    }
}
