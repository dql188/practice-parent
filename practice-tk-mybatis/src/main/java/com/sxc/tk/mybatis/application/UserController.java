package com.sxc.tk.mybatis.application;

import com.sxc.tk.mybatis.mapper.UserMapper;
import com.sxc.tk.mybatis.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserController
 * Create at:   	2018/1/18
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/18    	          ZMM           1.0          1.0 Version
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "find")
    public User find(){
        return userMapper.selectByPrimaryKey(1);
    }

}
