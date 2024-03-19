package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.utils.CustInfoVerify;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改风险客户
 */

@Service
@Transactional
public class TblackB002Service extends TXBaseService {


    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private CustInfoVerify custInfoVerify;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        if (ObjectUtil.isEmpty(p.get("id"))) {
            throw new BusinessException("TblackB002ServiceException", "不存在该客户信息");
        }
        String certType = "";
        String cert_num = "";
        String acct_id = "";

        Map<String, Object> queryMap = new HashMap<>();
        if (ObjectUtil.isEmpty(p.get("set_ctrl_dt")) || ObjectUtil.isEmpty(p.get("closing_dt"))) {
            throw new BusinessException("TblackB001ServiceException", "控制开始日期和控制截至日期不能为空");
        }
        if (ObjectUtil.isEmpty(p.get("cust_name"))) {
            throw new BusinessException("TblackB001ServiceException", "客户姓名不能为空");
        }

        String setCtrlDt = String.valueOf(p.get("set_ctrl_dt"));
        String closingDt = String.valueOf(p.get("closing_dt"));
        String cust_name = String.valueOf(p.get("closing_dt"));

        if (cust_name.length() > 45) {
            throw new BusinessException("TblackB001ServiceException", "客户姓名填写非法");
        }
        if (setCtrlDt.compareTo(closingDt) > 0) {
            throw new BusinessException("TblackB001ServiceException", "控制开始日期不能早于控制截至日期");
        }

        if (ObjectUtil.isEmpty(p.get("cert_num")) && ObjectUtil.isEmpty(p.get("acct_id"))) {
            throw new BusinessException("TblackB001ServiceException", "证件号码和账号不能同时为空");
        } else {
            //证件号码检验
            if (ObjectUtil.isNotEmpty(p.get("cert_num"))) {

                cert_num = String.valueOf(p.get("cert_num"));
                certType = String.valueOf(p.get("cert_type_cd"));
                if ("110001".equals(certType) || "110005".equals(certType) || "110003".equals(certType)) {
                    cert_num = cert_num.toUpperCase();
                    p.put("cert_num", cert_num);
                }

                String custInfo = custInfoVerify.checkCustInfo(certType, cert_num);

                if (!"correct".equals(custInfo)) {
                    throw new BusinessException("TblackB001ServiceException", custInfo);
                }

            }
            if (ObjectUtil.isNotEmpty(p.get("acct_id"))) {
                acct_id = String.valueOf(p.get("acct_id"));
                if (acct_id.length() > 22) {
                    throw new BusinessException("TblackB001ServiceException", "账号填写非法");
                }
            }
            queryMap.put("cert_num", cert_num);
            queryMap.put("acct_id", acct_id);
        }
        List<Map<String, Object>> mapList = blklistCtrlDao.isExist(queryMap);

        if (mapList != null && mapList.size() > 0) {
            for (Map<String, Object> map : mapList) {
                if (!map.get("id").equals(p.get("id"))) {
                    throw new BusinessException("TblackB001ServiceException", "证件号码或账号已存在");
                }
            }
        }
        String date = DateUtils.getDate("yyyy-MM-dd");
        p.put("update_time", date);
        blklistCtrlDao.updateByPk(p);

        return BaseUtils.map("success", "1");
    }


}
