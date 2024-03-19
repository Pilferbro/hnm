package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 删除风险客户
 */

@Service
@Transactional
public class TblackB003Service extends TXBaseService {

    @Autowired
    private BlklistCtrlDao blklistCtrlDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("id"))) {
            throw new BusinessException("TblackB002ServiceException", "不存在该客户信息");
        }

        blklistCtrlDao.deleteByPk(p);

        return BaseUtils.map("success", "1");
    }


}
