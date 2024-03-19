package com.nantian.hnm;

import com.gdnybank.hnm.pub.service.EsbMsgProcService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.test.base.ServiceTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class EsbMsgProcServiceTest extends ServiceTests {


    @Autowired
    private EsbMsgProcService esbMsgProcService;

    @Test
    public void testEsb002(){
        Map<String, Object> map = esbMsgProcService.sendAndReceiveMsg(BaseUtils.map("service_code", "11003000008", "service_scene", "58","txcode","ESB002"), false);
        System.out.println(map);
    }
}
