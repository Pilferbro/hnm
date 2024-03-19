package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TMeasureDao;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

/**
 * 查询管控措施
 */

@Service
@Transactional
public class Tmsure001Service extends TXBaseService {

    @Autowired
    private TMeasureDao measureDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        if (p.containsKey("tableData")) {
            List<Map<String, Object>> tableData = (List<Map<String, Object>>) p.get("tableData");

            if (tableData != null && tableData.size() > 0) {

                for (Map<String, Object> map : tableData) {
                    measureDao.updateByPk(map);
                }
            }
        }
        return BaseUtils.map("success", "1");
    }

}
