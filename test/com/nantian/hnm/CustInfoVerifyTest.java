package com.nantian.hnm;

import com.gdnybank.hnm.pub.utils.CustInfoVerify;
import com.nantian.mfp.test.base.ServiceTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustInfoVerifyTest extends ServiceTests {

    @Autowired
    private CustInfoVerify custInfoVerify;
    @Test
    public void checkCustInfoTest(){
        custInfoVerify.checkCustInfo("110019","H12345678");
    }
}
