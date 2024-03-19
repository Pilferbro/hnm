package com.gdnybank.hnm.bussiness.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.gdnybank.hnm.pub.dao.BlklistCtrlDao;
import com.gdnybank.hnm.pub.dao.ThfacctDao;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.gdnybank.hnm.pub.utils.CustInfoVerify;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
import com.nantian.mfp.pub.service.TXBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增风险客户
 */

@Service
@Transactional
public class TblackB001Service extends TXBaseService {

    @Autowired
    private BlklistCtrlDao blklistCtrlDao;
    @Autowired
    private CustInfoVerify custInfoVerify;
    @Autowired
    private ThfacctDao thfacctDao;

    @Override
    public Object doService(Map<String, Object> evn, Map<String, Object> p) {

        String date = DateUtils.getDate("yyyy-MM-dd");
        String id = IdUtil.randomUUID().replace("_", "");
        String certType = "";
        String certNum = "";
        String acctId = "";

        Map<String, Object> queryMap = new HashMap<>();

        if (ObjectUtil.isEmpty(p.get("set_ctrl_dt")) || ObjectUtil.isEmpty(p.get("closing_dt"))) {
            throw new BusinessException("TblackB001ServiceException", "控制开始日期和控制截至日期不能为空");
        }
        if (ObjectUtil.isEmpty(p.get("cust_name"))) {
            throw new BusinessException("TblackB001ServiceException", "客户姓名不能为空");
        }

        String setCtrlDt = String.valueOf(p.get("set_ctrl_dt"));
        String cust_name = String.valueOf(p.get("cust_name"));
        String closingDt = String.valueOf(p.get("closing_dt"));

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

                certNum = String.valueOf(p.get("cert_num"));
                certType = String.valueOf(p.get("cert_type_cd"));
                if ("110001".equals(certType) || "110005".equals(certType) || "110003".equals(certType)) {
                    certNum = certNum.toUpperCase();
                    p.put("cert_num", certNum);
                }

                String custInfo = custInfoVerify.checkCustInfo(certType, certNum);

                if (!"correct".equals(custInfo)) {
                    throw new BusinessException("TblackB001ServiceException", custInfo);
                }

            }
            if (ObjectUtil.isNotEmpty(p.get("acct_id"))) {
                acctId = String.valueOf(p.get("acct_id"));
                if (acctId.length() > 22) {
                    throw new BusinessException("TblackB001ServiceException", "账号填写非法");
                }

                List<Map<String, Object>> acctList = thfacctDao.queryCltnbrByRichnbr(BaseUtils.map("richnbr", acctId));
                if (acctList != null && acctList.size() > 0) {
                    //添加客户号
                    queryMap.put("cust_id",acctList.get(0).get("cltnbr"));
                }
            }
        }
        queryMap.put("acct_id", acctId);
        queryMap.put("cert_num", certNum);

        List<Map<String, Object>> mapList = blklistCtrlDao.isExist(queryMap);

        if (mapList != null && mapList.size() > 0) {
            throw new BusinessException("TblackB001ServiceException", "证件号码或账号已存在");
        }

        p.put("create_time", date);
        p.put("id", id);
        p.put("src", "100001");
        p.put("valid_flg", "1");
        p.put("blklist_type_cd", MfpContextHolder.getLoginId());

        blklistCtrlDao.save(p);

        return BaseUtils.map("success", "1");
    }

}
