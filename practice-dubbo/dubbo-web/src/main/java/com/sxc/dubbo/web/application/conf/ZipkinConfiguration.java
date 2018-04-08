//package com.sxc.dubbo.web.application.conf;
//
//import com.github.kristofa.brave.Brave;
//import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
//import com.github.kristofa.brave.Sampler;
//import com.github.kristofa.brave.SpanCollector;
//import com.github.kristofa.brave.http.DefaultSpanNameProvider;
//import com.github.kristofa.brave.http.HttpSpanCollector;
//import com.github.kristofa.brave.servlet.BraveServletFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Description:
// *
// * @author: ZMM
// * @version: 1.0
// * Filename:    	ZipkinConfiguration
// * Create at:   	2018/3/21
// * <p>
// * Copyright:   	Copyright (c)2018
// * Company:     	songxiaocai
// * <p>
// * Modification History:
// * Date        		      Author          Version      Description
// * ------------------------------------------------------------------
// * 2018/3/21    	          ZMM           1.0          1.0 Version
// */
//@Configuration
//public class ZipkinConfiguration {
//
//    @Bean
//    public SpanCollector spanCollector() {
//        return HttpSpanCollector.create("http://localhost:9411", new EmptySpanCollectorMetricsHandler());
//    }
//
///*
//    @Bean
//    public Brave brave(SpanCollector spanCollector) {
//        return new Brave.Builder("web-service")
//                .spanCollector(spanCollector)
//                .traceSampler(Sampler.ALWAYS_SAMPLE)
//                .build();
//    }
//*/
//
//
//
//
//
//}
