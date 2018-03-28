package com.sxc.practice;

import com.alibaba.dubbo.config.annotation.Service;
//import com.sxc.good.nocloud.GoodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	Backend
 * Create at:   	2018/3/28
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/28    	          ZMM           1.0          1.0 Version
 */
@Service(interfaceClass = HelloService.class)
@Component
public class Backend implements HelloService {
//
//    @Autowired
//    GoodClient goodClient;

    @Override
    public String say() {
        return "";
//        return goodClient.getAll().toString();
    }
}
