package com.sxc.dubbo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserApplication
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
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.sxc.dubbo")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
