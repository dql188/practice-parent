package com.sxc.practice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	frontend
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
@Component
public class Frontend {

    @Reference(url = "dubbo://127.0.0.1:20880")
    HelloService helloService;

   public String test(){
       return helloService.say();
   }

}
