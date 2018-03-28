package com.sxc.dubbo.web.application;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sxc.dubbo.api.provider.account.AccountQueryProvider;
import com.sxc.dubbo.api.provider.user.UserQueryProvider;
import com.sxc.dubbo.api.result.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	WebHandler
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
@Component
public class WebHandler {


    @Reference
    private AccountQueryProvider accountQueryProvider;

    @Reference
    private UserQueryProvider userQueryProvider;

    public UserDTO queryById(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(userQueryProvider.queryName(id));
//        userDTO.setAccount(accountQueryProvider.queryById(id));
        return userDTO;
    }
}
