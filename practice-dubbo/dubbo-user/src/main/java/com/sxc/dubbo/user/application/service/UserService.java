package com.sxc.dubbo.user.application.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sxc.dubbo.api.provider.account.AccountQueryProvider;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserService
 * Create at:   	2018/2/26
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/2/26    	          ZMM           1.0          1.0 Version
 */
@Service
public class UserService {

    @Reference
    private AccountQueryProvider accountQueryProvider;

    public Long queryById(Integer id) {
        return accountQueryProvider.queryById(id);
    }
}
