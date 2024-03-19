package com.gdnybank.hnm.flow.service;

import com.gdnybank.hnm.pub.dao.TApprovalFieldDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增或删除免审核枚举
 */
@Transactional
@Service
public class Tflow038Service extends TXBaseService {

    @Autowired
    private TApprovalFieldDao tApprovalFieldDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {


        try {
            //deleted 则为删除，否则为新增数据
            if (p.containsKey("deleted")) {

                tApprovalFieldDao.deleteById(p);
            } else {

                p.put("pid", p.getOrDefault("id", "0"));
                tApprovalFieldDao.save(p);
            }
        } catch (Exception e) {
            throw new BusinessException("Tflow038Service", "新增或删除失败");
        }

        return BaseUtils.map("success", "1");
    }

}
