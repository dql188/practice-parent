package com.sxc.dubbo.core.temp;

import com.sxc.dubbo.core.ApplicationBeanHolder;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.HttpTraceKeysInjector;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	BeanConfig
 * Create at:   	2018/4/7
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/4/7    	          ZMM           1.0          1.0 Version
 */
@Configuration
public class BeanConfig {

    @Bean
    ApplicationBeanHolder applicationBeanHolder() {
        return new ApplicationBeanHolder();
    }

    @Bean
    DubboInject dubboInject(){
        return new DubboInject();
    }

    @Bean
    DubboExtractor dubboExtractor(SleuthWebProperties sleuthWebProperties){
        return new DubboExtractor(Pattern.compile(sleuthWebProperties.getSkipPattern()));
    }
    @Bean
    HttpTraceKeysInjector httpTraceKeysInjector(Tracer tracer, TraceKeys traceKeys){
        return new HttpTraceKeysInjector(tracer,traceKeys);
    }
}
