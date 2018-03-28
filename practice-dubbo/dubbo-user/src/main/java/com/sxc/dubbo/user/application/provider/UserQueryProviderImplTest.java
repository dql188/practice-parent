package com.sxc.dubbo.user.application.provider;

import com.sxc.dubbo.user.UserApplication;
import com.sxc.good.nocloud.GoodClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserQueryProviderImplTest
 * Create at:   	2018/3/27
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/27    	          ZMM           1.0          1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserQueryProviderImplTest {

    @Autowired
    GoodClient goodClient;

    @Test
    public void queryName() {
        System.out.println(goodClient.getAll());
    }
}