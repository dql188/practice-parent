package com.sxc.practice.consumer;

import com.alibaba.dubbo.config.annotation.Service;

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
@Service(version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class Backend implements HelloService {

    @Override
    public String say() {
        return UUID.randomUUID().toString();
//        return goodClient.getAll().toString();
    }
}
