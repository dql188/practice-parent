package com.sxc.dubbo.core.temp;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.SpanTextMap;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboInject
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
public class DubboInject implements SpanInjector<SpanTextMap> {

    private static final DubboSpanMapper SPAN_CARRIER_MAPPER = new DubboSpanMapper();

    @Override
    public void inject(Span span, SpanTextMap map) {
        Map<String, String> carrier = SPAN_CARRIER_MAPPER.convert(map);
        setHeader(map, carrier, Span.TRACE_ID_NAME, span.traceIdString());
        setIdHeader(map, carrier, Span.SPAN_ID_NAME, span.getSpanId());
        setHeader(map, carrier, Span.SAMPLED_NAME, span.isExportable() ? Span.SPAN_SAMPLED : Span.SPAN_NOT_SAMPLED);
        setHeader(map, carrier, Span.SPAN_NAME_NAME, span.getName());
        setIdHeader(map, carrier, Span.PARENT_ID_NAME, getParentId(span));
        setHeader(map, carrier, Span.PROCESS_ID_NAME, span.getProcessId());
        for (Map.Entry<String, String> entry : span.baggageItems()) {
            map.put(prefixedKey(entry.getKey()), entry.getValue());
        }
    }

    private String prefixedKey(String key) {
        if (key.startsWith(Span.SPAN_BAGGAGE_HEADER_PREFIX
                + DubboSpanMapper.HEADER_DELIMITER)) {
            return key;
        }
        return Span.SPAN_BAGGAGE_HEADER_PREFIX + DubboSpanMapper.HEADER_DELIMITER
                + key;
    }

    private Long getParentId(Span span) {
        return !span.getParents().isEmpty() ? span.getParents().get(0) : null;
    }

    private void setIdHeader(SpanTextMap map, Map<String, String> carrier, String name, Long value) {
        if (value != null) {
            setHeader(map, carrier, name, Span.idToHex(value));
        }
    }

    private void setHeader(SpanTextMap map, Map<String, String> carrier, String name, String value) {
        if (StringUtils.hasText(value) && !carrier.containsKey(name)) {
            map.put(name, value);
        }
    }
}
