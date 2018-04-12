package com.sxc.dubbo.user.application.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.sxc.dubbo.api.provider.user.UserQueryProvider;
import com.sxc.good.nocloud.GoodClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserQueryProviderImpl
 * Create at:   	2018/3/23
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/23    	          ZMM           1.0          1.0 Version
 */
@Service
public class UserQueryProviderImpl implements UserQueryProvider {

    private static Logger logger = LoggerFactory.getLogger(UserQueryProviderImpl.class);

    @Autowired
    GoodClient goodClient;

    @Override
    public String queryName(Integer id) {
        logger.info("userquery");
//        System.out.println(goodClient.getAll());
        return UUID.randomUUID().toString();
    }
}
