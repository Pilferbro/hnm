package com.gdnybank.hnm.system.service;

import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 修改登录密码或交易密码
 */
@Service
public class Tuser006Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Object doService(Map<String, Object> map, Map<String, Object> p) {
        Map<String,Object> param = new HashMap<String,Object>();
        if(StringUtils.isEmpty(p.get("account_id"))){
            throw new BusinessException("Tuser006Service001","用户编号为必传字段");
        }
        if(StringUtils.isEmpty(p.get("flag"))){
            throw new BusinessException("Tuser006Service001","修改标识为必传字段");
        }

        param.put("account_id",p.get("account_id"));
        if("1".equals(p.get("flag"))){
            param.put("login_pwd",passwordEncoder.encode("123456"));
        }
        if("2".equals(p.get("flag"))){
            param.put("business_pwd",passwordEncoder.encode("123456"));
        }
        sysAccountDao.updateByPk(param);

        return BaseUtils.map("success","1");
    }
}
