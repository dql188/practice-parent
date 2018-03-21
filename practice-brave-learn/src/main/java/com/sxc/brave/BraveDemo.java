package com.sxc.brave;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.context.log4j2.ThreadContextCurrentTraceContext;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	BraveDemo
 * Create at:   	2018/3/16
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/16    	          ZMM           1.0          1.0 Version
 */
public class BraveDemo {

    public static void main(String[] args) {
        Sender sender = OkHttpSender.create("http://localhost:9411/api/v2/spans");
        AsyncReporter reporter = AsyncReporter.builder(sender).build(SpanBytesEncoder.JSON_V2);

        Tracing tracing = Tracing.newBuilder()
                .localServiceName("demo1")
                .spanReporter(reporter)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadContextCurrentTraceContext.create())
                .build();

        Tracer tracer = tracing.tracer();

        Span span1 = tracer.newTrace().name("span1").start();
        try {
            doSomethingExpensive();
        } finally {
            span1.finish();
        }


        Span span2 = tracer.newTrace().name("span2").start();
        Span span2Child1 = tracer.newChild(span2.context()).name("span2Child1").start();
        try {
            prepare();
        } finally {
            span2Child1.finish();
        }

        Span span2Child2 = tracer.newChild(span2.context()).name("span2Child2").start();
        try {
            commit();
        } finally {
            span2Child2.finish();
        }
        span2.finish();
        //如果立即结束的话 会出现信息还没有发送到zipkin的情况
        //这样就只会出现一个span
        sleep(1000);

    }

    private static void doSomethingExpensive() {
        sleep(500);
    }

    private static void commit() {
        sleep(500);
    }

    private static void prepare() {
        sleep(500);
    }

    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
