package com.sxc.dubbo.core;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.kristofa.brave.ClientRequestAdapter;
import com.github.kristofa.brave.IdConversion;
import com.github.kristofa.brave.KeyValueAnnotation;
import com.github.kristofa.brave.SpanId;
import com.twitter.zipkin.gen.Endpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Description: 将Span等信息放入RpcContext
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboClientRequestAdapter
 * Create at:   	2018/3/21
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/21    	          ZMM           1.0          1.0 Version
 */
public class DubboClientRequestAdapter implements ClientRequestAdapter {

    private Map<String, String> carrier;

    private String spanName;

    public DubboClientRequestAdapter(RpcContext rpcContext, String spanName) {
        this.carrier = rpcContext.getAttachments();
        this.spanName = spanName;
    }

    @Override
    public String getSpanName() {
        return spanName;
    }

    @Override
    public void addSpanIdToRequest(SpanId spanId) {
        if (spanId == null) {
            carrier.put("dubbo.trace.sampled", "0");
        } else {
            carrier.put("dubbo.trace.sampled", "1");
            carrier.put("dubbo.trace.traceid", IdConversion.convertToString(spanId.traceId));
            carrier.put("dubbo.trace.spanid", IdConversion.convertToString(spanId.spanId));
            if (spanId.nullableParentId() != null) {
                carrier.put("dubbo.trace.parentid", IdConversion.convertToString(spanId.parentId));
            }
        }
    }

    @Override
    public Collection<KeyValueAnnotation> requestAnnotations() {
        return Collections.emptyList();
    }

    @Override
    public Endpoint serverAddress() {
        return null;
    }
}
