package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.HPatrolLogContentDao;
import com.gdnybank.hnm.pub.dao.HProjectClassDao;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Transactional
public class Tpclass002Service extends TXBaseService {

    @Autowired
    private HProjectClassDao hProjectClassDao;
    @Autowired
    private HPatrolLogContentDao hPatrolLogContentDao;

    @Override
    public Object doService(Map<String, Object> map, Map<String, Object> p) {

        String project_name = String.valueOf(p.get("project_name"));

        if (StringUtils.isEmpty(project_name)) {
            throw new BusinessException("TPClass002ServiceException", "项目名称不能为空");
        }

        p.put("updatetime", DateUtil.now());
        if (ObjectUtil.isNotNull(p.get("pj_classify"))) {
            long count = hPatrolLogContentDao.queryByPjClassify(p);
            if (count > 0){
                throw new BusinessException("TPClass002ServiceException", "存在逻辑关联，不可删除");
            }
            p.put("source", map.get("userid"));
            hProjectClassDao.updateByPJ(p);
        } else {

            long isExist = hProjectClassDao.countByNmae(BaseUtils.map("project_name", project_name));
            if (isExist > 0) {
                throw new BusinessException("TPClass002ServiceException", "项目名称已存在");
            }
            long count = hProjectClassDao.count(BaseUtils.map());
            p.put("created", DateUtil.now());
            p.put("source", map.get("userid"));
            p.put("isdeleted", 0);
            p.put("pj_classify", count + 1);
            hProjectClassDao.save(p);
        }
        return BaseUtils.map("success", "1");
    }
}
