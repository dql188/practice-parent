package com.sxc.dubbo.core;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboTraceConstant
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
public interface DubboTraceConstant {

    String SAMPLED = "dubbo.trace.sampled";
    String PARENT_SPAN_ID = "dubbo.trace.parentSpanId";

    String SPAN_ID = "dubbo.trace.spanId";

    String TRACE_ID = "dubbo.trace.traceId";

    String STATUS_CODE = "dubbo.trace.staus_code";
}
