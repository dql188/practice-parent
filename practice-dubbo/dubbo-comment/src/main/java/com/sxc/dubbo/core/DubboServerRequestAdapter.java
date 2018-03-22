package com.sxc.dubbo.core;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.kristofa.brave.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboServerRequestAdapter
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
public class DubboServerRequestAdapter implements ServerRequestAdapter {

    private Map<String, String> carrier;

    private String spanName;

    public DubboServerRequestAdapter(RpcContext context, String spanName) {
        this.carrier = context.getAttachments();
        this.spanName = spanName;
    }

    @Override
    public TraceData getTraceData() {
        final String sampled = carrier.get(DubboTraceConstant.SAMPLED);
        if (sampled != null) {
            if (sampled.equals("0") || sampled.toLowerCase().equals("false")) {
                return TraceData.builder().sample(false).build();
            } else {
                final String parentSpanId = carrier.get(DubboTraceConstant.PARENT_SPAN_ID);
                final String traceId = carrier.get(DubboTraceConstant.TRACE_ID);
                final String spanId = carrier.get(DubboTraceConstant.SPAN_ID);
                if (traceId != null && spanId != null) {
                    SpanId span = getSpanId(traceId, spanId, parentSpanId);
                    return TraceData.builder().sample(true).spanId(span).build();
                }
            }
        }
        return TraceData.builder().build();
    }

    static SpanId getSpanId(String traceId, String spanId, String parentSpanId) {
        return SpanId.builder().traceId(IdConversion.convertToLong(traceId)).spanId(IdConversion.convertToLong(spanId))
                .parentId(parentSpanId == null ? null : IdConversion.convertToLong(parentSpanId)).build();
    }

    @Override
    public String getSpanName() {
        return spanName;
    }

    @Override
    public Collection<KeyValueAnnotation> requestAnnotations() {
        return Collections.emptyList();
    }
}
