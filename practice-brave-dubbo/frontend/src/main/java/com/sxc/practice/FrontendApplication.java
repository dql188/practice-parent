package com.sxc.practice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	Controller
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
@EnableDubboConfiguration
@SpringBootApplication
@RestController
public class FrontendApplication {

    @Autowired
    Frontend frontend;

    @GetMapping(value = "/test")
    public String test(){
        return frontend.test();
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
