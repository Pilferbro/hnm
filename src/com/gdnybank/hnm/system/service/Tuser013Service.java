package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @desc   查询所有用户列表
 */
@Service
public class Tuser013Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;

    @Override
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        return sysAccountDao.queryForListBycondition(BaseUtils.map());
    }
}
