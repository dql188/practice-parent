package com.sxc.dubbo.core;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	BraveFactory
 * Create at:   	2018/3/22
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/22    	          ZMM           1.0          1.0 Version
 */
//@Configuration
public class BraveFactory {

    @Bean
    SpanCollector spanCollector() {
        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
//        默认false，span在transport之前是否会被gzipped
                .compressionEnabled(false)
                .connectTimeout(5000)
                .flushInterval(1)
                .readTimeout(6000)
                .build();
        SpanCollector spanCollector = HttpSpanCollector.create("http://localhost:9411", config, new EmptySpanCollectorMetricsHandler());
        return spanCollector;
    }

    @Bean
    Brave brave(SpanCollector spanCollector) {
        return new Brave.Builder().spanCollector(spanCollector).traceSampler(Sampler.ALWAYS_SAMPLE).build();
    }

    @Bean
    public ApplicationContextAware holder() {
        return new ApplicationBeanHolder();
    }
}
