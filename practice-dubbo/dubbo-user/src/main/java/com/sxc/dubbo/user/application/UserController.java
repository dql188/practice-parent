package com.sxc.dubbo.user.application;

import com.sxc.dubbo.api.result.UserDTO;
import com.sxc.dubbo.user.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserController
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
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public UserDTO query(@PathVariable("id") Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setAccount(userService.queryById(id));
        return userDTO;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
