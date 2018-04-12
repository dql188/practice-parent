package com.sxc.practice.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	BackendApplication
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
@SpringBootApplication
public class BackendApplication {

    @Bean
    ApplicationBeanHolder applicationBeanHolder(){
        return new ApplicationBeanHolder();
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
