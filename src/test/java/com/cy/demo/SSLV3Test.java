package com.cy.demo;

import com.cy.sort.HttpsClientService;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by chenying on 2017/11/2.
 */
public class SSLV3Test {

    @Test
    public void test(){
        System.out.println(System.getProperty("java.version"));
        HttpsClientService httpsClientService = new HttpsClientService();
        String result = httpsClientService.post("https://wtest.133.cn/huolitrip/lyancoffee/order/callback.action",new HashMap<>());
        System.out.println(result);
    }
}
