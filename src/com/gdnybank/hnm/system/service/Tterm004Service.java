package com.gdnybank.hnm.system.service;

import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.TerminalInfoDao;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author PHL
 * @Date 2023/2/13 11:00
 * @注释 查询版本号
 */
@Transactional
@Service
public class Tterm004Service extends TXBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TerminalInfoDao terminalInfoDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {
        //查询初始版本控制信息
        List<Map<String, Object>> maps = terminalInfoDao.queryForList(BaseUtils.map("id","2023022100000004"));
        String distance = String.valueOf(maps.get(0).get("distance"));
        String ctrlNo = String.valueOf(maps.get(0).get("ctrl_no"));
        if ("null".equals(distance)){
            distance = "";
        }
        boolean ctrl_no = "1".equals(ctrlNo);

        p.put("is_delete", "0");
        setPageInfo(p);
        try {
            List<Map<String, Object>> returnList = terminalInfoDao.queryForListByPage(p);
            long total = MfpContextHolder.getPageInfo().getITotalDisplayRecords();
            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("distance", distance);
            resultMap.put("ctrl_no", ctrl_no);
            resultMap.put("total", total);
            resultMap.put("returnList", returnList);

            logger.info("查询列表！");
            return resultMap;

        } catch (Exception e) {
            throw new BusinessException("Tterm004ServiceException", "查询失败！");
        }

    }
    private void setPageInfo(Map<String, Object> p) {
        int page = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("page")) ? p.get("page").toString() : "0");
        int number = Integer.parseInt(ObjectUtil.isNotEmpty(p.get("number")) ? p.get("number").toString() : "10");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart(page * number);
        pageInfo.setIDisplayLength(number);
        MfpContextHolder.setPageInfo(pageInfo);
    }
}
