package com.gdnybank.hnm.bussiness.service;

import com.gdnybank.hnm.pub.dao.HSiteDao;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.pub.service.TXBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  生成站点二维码
 * @author: wxm
 */
@Service
public class Tsite009Service extends TXBaseService {

    private static final Logger logger = LoggerFactory.getLogger(Tsite009Service.class);

    @Autowired
    private HSiteDao siteDao;
    @Autowired
    private SysParamService sysParamService;

    @Override
    @Transactional
    public Object doService(Map<String, Object> env, Map<String, Object> p) {

        if(p.get("codeType") == null){
            throw new BusinessException("Tsite009ServiceException", "codeType不能为空");
        }
        String codeType = p.get("codeType").toString();

        try{
            Map<String,Object> map =new HashMap<String,Object>();
            //查询站点信息
            List<Map<String, Object>> list = siteDao.queryForList(p);
            if(list != null && list.size() > 0){
                Map<String, Object> siteMap = list.get(0);
                String urlcode = "";
                String urlcode1 = sysParamService.getSysParam("BASE_CONFIG", "WX_URL", "https://www.baidu.com");
                String urlcode2 = sysParamService.getSysParam("BASE_CONFIG", "WX_URL2", "https://www.baidu.com");

                if("0".equals(codeType)){
                    urlcode = urlcode1 + "?siteNo="+siteMap.get("site_no");
                }else if("1".equals(codeType)){
                    urlcode = urlcode2 + "?siteNo="+siteMap.get("site_no");
                }

                //生成二维码
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                //设置二维码图片宽高
                BitMatrix bitMatrix = qrCodeWriter.encode(urlcode, BarcodeFormat.QR_CODE,600,600);
                //写出到输出流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix,"PNG",byteArrayOutputStream);
                //转化为base64
                Base64.Encoder encoder = Base64.getEncoder();
                String qrcode = "data:image/jpeg;base64," + encoder.encodeToString(byteArrayOutputStream.toByteArray());

                String site_name = "";
                if("0".equals(codeType)){
                    site_name = siteMap.get("site_name") + "(粤分享)";
                }else if("1".equals(codeType)){
                    site_name = siteMap.get("site_name") + "(线上存款)";
                }

                map.put("site_name",site_name);
                map.put("qrcode",qrcode);
            }else{
                throw new BusinessException("Tsite009ServiceException", "查询站点失败");
            }
            return map;
        }catch (Exception e){
            logger.error("生成站点二维码失败");
            throw new BusinessException("Tsite009ServiceException", "生成站点二维码失败");
        }
    }
}
