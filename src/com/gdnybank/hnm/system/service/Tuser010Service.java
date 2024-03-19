package com.gdnybank.hnm.system.service;
import com.gdnybank.hnm.pub.dao.SysAccountDao;
import com.gdnybank.hnm.pub.dao.SysAccountRoleDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc  修改用户登陆密码
 */
@Service
public class Tuser010Service extends TXBaseService {

    @Autowired
    private SysAccountDao sysAccountDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        //查询用户
        List<Map<String, Object>> list = sysAccountDao.queryForList(BaseUtils.map("account_id", env.get("userid")));
        if(list!=null && list.size()>0){
            Map<String, Object> map = list.get(0);
            if(!passwordEncoder.matches(p.get("old_pwd").toString(), map.get("login_pwd").toString())){
                throw new BusinessException("Tuser010ServiceException", "请输入正确的原密码");
            }
            //更新用户信息
            String new_pwd = passwordEncoder.encode(p.get("new_pwd").toString());
            sysAccountDao.updateByPk(BaseUtils.map("account_id",env.get("userid"),"login_pwd",new_pwd));

        }else{
            throw new BusinessException("Tuser010ServiceException", "未查询到用户");
        }

        return BaseUtils.map("success","1");

    }
}
