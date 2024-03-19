package com.nantian.hnm;

import com.gdnybank.hnm.pub.service.EsbMsgProcService;
import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.MapUtils;
import com.nantian.mfp.pub.service.SysParamService;
import com.nantian.mfp.test.base.ServiceTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonTest extends ServiceTests {

    @Resource
    private HnmCommService hnmCommService;
    @Resource
    private SysParamService sysParamService;
    @Autowired
    private EsbMsgProcService esbMsgProcService;

    @Test
    public void test1() {

        Map<String, Object> p = new HashMap<String, Object>();
        Map<String, Object> q = new HashMap<String, Object>();

        p.put("trans_type", "U");
        p.put("user_no","300984");
        p.put("user_name","测试站点");
        p.put("branch_no","9609");
        p.put("branch_name","广州分行");

        /*p.put("iden_type","110");
        p.put("iden_no","371522199501011010");
        p.put("phone_no","13250040010");
        p.put("email","wwwww@qq");*/
        p.put("iden_type","");
        p.put("iden_no","");
        p.put("phone_no","");
        p.put("email","");

        p.put("txcode", sysParamService.getSysParam("DEFAULT_USERINFO", "TXCODE", "COM000000"));

        /*p.put("bus_seq_no", hnmCommService.getFlowNo("008754"));
        p.put("userid", "008754");
        p.put("branch_id", "9600");

        q.put("tran_date", DateUtils.getDate("yyyyMMdd"));
        q.put("branch_id", "9600");
        q.put("user_id", "008754");
        q.put("rural_branch_id", "0000");
        p.put("HEADKEY", q);*/

        p.put("service_code", "11002000122"); // esb交易码
        p.put("service_scene", "03");// 场景号

        Map<String, Object> receiveMap = new HashMap<String, Object>();
        try {
            receiveMap = esbMsgProcService.sendAndReceiveMsg(p, false);
        } catch (Exception e) {
            log.error("失败，原因:", e);
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            } else {
                throw new BusinessException("test1", "失败");
            }
        }
        if ("0".equals(esbMsgProcService.getRetStatus(receiveMap))) { // 1，成功；0，失败；
            String retCodeAndMsg = esbMsgProcService.getRetCodeMsg(receiveMap);
            throw new BusinessException("test1", retCodeAndMsg);
        }

        System.out.println(receiveMap);

    }
    @Test
    public void doDistance() {
        try {
            // 根据上送的经纬度以及规则的经纬度和半径进行计算，判断是否在半径内
            // 经度
            double siteLongitude = Double.parseDouble("110.33762");
            // 纬度
            double siteLatitude = Double.parseDouble("21.217599");
            log.info("站点经度："+siteLongitude+",站点纬度："+siteLatitude);
            // 半径
            double radius = Double.parseDouble("5");

            // 上送的经度
            double uLongitude = Double.parseDouble("110.337576");
            // 上送的纬度
            double uLatitude = Double.parseDouble("21.217517");
            double rRadius = MapUtils.getDistance(siteLongitude, siteLatitude, uLongitude, uLatitude) / 1000;
            log.debug("两个坐标点的距离:" + rRadius + "公里");


                log.debug(rRadius <= radius);



        } catch (Exception e) {
            e.printStackTrace();
            log.error("设备位置匹配参数解析出错");
        }

    }
}
