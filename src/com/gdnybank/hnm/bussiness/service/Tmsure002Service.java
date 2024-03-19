package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.TMeasureDao;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 查询管控措施
 */

@Service
@Transactional
public class Tmsure002Service extends TXBaseService {

    @Autowired
    private TMeasureDao measureDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        return measureDao.queryForList(p);
    }


}
