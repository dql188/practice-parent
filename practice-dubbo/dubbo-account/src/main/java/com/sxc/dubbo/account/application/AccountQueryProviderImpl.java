package com.sxc.dubbo.account.application;

import com.alibaba.dubbo.config.annotation.Service;
import com.sxc.dubbo.api.provider.account.AccountQueryProvider;

import java.util.Random;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	AccountQueryProviderImpl
 * Create at:   	2018/1/23
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/23    	          ZMM           1.0          1.0 Version
 */
@Service
public class AccountQueryProviderImpl implements AccountQueryProvider {

    @Override
    public Long queryById(Integer id) {
        return new Random(Long.MAX_VALUE).nextLong();
    }
}
