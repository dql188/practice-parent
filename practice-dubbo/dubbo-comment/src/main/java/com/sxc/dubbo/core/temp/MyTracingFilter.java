package com.sxc.dubbo.core.temp;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.support.RpcUtils;
import com.sxc.dubbo.core.ApplicationBeanHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.HttpTraceKeysInjector;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.sleuth.sampler.NeverSampler;

import java.util.Collections;


/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	MyTracingFilter
 * Create at:   	2018/3/30
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/30    	          ZMM           1.0          1.0 Version
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class MyTracingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTracingFilter.class);

    private static final String DUBBO_COMPONENT = "rpc";

    Tracer tracer;
    DubboExtractor dubboExtractor;
    DubboInject injector;
    SpanReporter spanReporter;
    HttpTraceKeysInjector httpTraceKeysInjector;


    protected void addRequestTags(RpcContext context) {
        this.httpTraceKeysInjector.addRequestTags(
                context.getUrl().getPath() + "/" + context.getMethodName(),
                context.getUrl().getHost(),
                context.getUrl().getPath(),
                context.getUrl().getProtocol(),
                Collections.emptyMap());
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        tracer = ApplicationBeanHolder.getBean(Tracer.class);
        if (tracer == null) {
            return invoker.invoke(invocation);
        } else {
            injectBean();
        }

        RpcContext rpcContext = RpcContext.getContext();
        Span span;
        String spanName = DUBBO_COMPONENT + ":/" + invoker.getInterface().getSimpleName() + "/" + RpcUtils.getMethodName(invocation);
        if (rpcContext.isConsumerSide()) {
            span = tracer.createSpan(spanName);
            injector.inject(span, new DubboRequestTextMap(RpcContext.getContext()));
            addRequestTags(RpcContext.getContext());
            span.logEvent(Span.CLIENT_SEND);
            Result result;
            try {
                result = invoker.invoke(invocation);
                span.logEvent(Span.CLIENT_RECV);
            } finally {
                closeSpan(span);
            }
            return result;
        } else {
            Span parentSpan = dubboExtractor.joinTrace(new DubboRequestTextMap(RpcContext.getContext()));
            if (parentSpan != null) {
                //FIXME
                span = parentSpan;
                tracer.continueSpan(span);
                if (span.isRemote()) {
                    span.logEvent(Span.SERVER_RECV);
                }
                addClassTag(span, invocation, invoker);
            } else {
                String header = RpcContext.getContext().getAttachment(Span.SPAN_FLAGS);
                if (Span.SPAN_SAMPLED.equals(header)) {
                    span = tracer.createSpan(spanName, new AlwaysSampler());
                } else {
                    span = tracer.createSpan(spanName);
                }
                addRequestTags(RpcContext.getContext());
                addClassTag(span, invocation, invoker);
                span.logEvent(Span.SERVER_RECV);
            }
            Result result;
            try {
                result = invoker.invoke(invocation);
            } catch (Exception e) {
                addErrorTag(span, e);
                throw e;
            } finally {
                recordParentSpan(span);
                closeSpan(span);
            }
            return result;
        }
    }

    private void addClassTag(Span span, Invocation invocation, Invoker invoker) {
        span.tag("provider.name", invoker.getInterface().getSimpleName());
        span.tag("provider.methodName", RpcUtils.getMethodName(invocation));
    }

    private void addErrorTag(Span span, Exception e) {
        if (e instanceof RpcException) {
            span.tag("dubbo.error,code", Integer.toString(((RpcException) e).getCode()));
        }
        span.tag("error", e.getMessage());
    }

    private void recordParentSpan(Span parent) {
        if (parent == null) {
            return;
        }
        if (parent.isRemote()) {
            tracer.getCurrentSpan().logEvent(Span.SERVER_SEND);
            parent.stop();
            this.spanReporter.report(parent);
        }
    }

    private void injectBean() {
        injector = ApplicationBeanHolder.getBean(DubboInject.class);
        dubboExtractor = ApplicationBeanHolder.getBean(DubboExtractor.class);
        httpTraceKeysInjector = ApplicationBeanHolder.getBean(HttpTraceKeysInjector.class);
        spanReporter = ApplicationBeanHolder.getBean(SpanReporter.class);
    }

    private void closeSpan(Span span) {
        if (span != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Closing Dubbo span " + span);
            }
            tracer.close(span);
        }
    }
}
