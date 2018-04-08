package com.sxc.dubbo.core.temp;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanTextMap;

import java.util.*;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboSpanMapper
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
public class DubboSpanMapper {

    static final String HEADER_DELIMITER = "-";
    static final String BAGGAGE_PREFIX = Span.SPAN_BAGGAGE_HEADER_PREFIX
            + HEADER_DELIMITER;
    static final String URI_HEADER = "X-Span-Uri";

    private static Comparator<String> IGNORE_CASE_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
    };

    /**
     * Acceptable span fields
     */
    private static final Set<String> SPAN_FIELDS;

    static {
        TreeSet<String> fields = new TreeSet<>(IGNORE_CASE_COMPARATOR);
        Collections.addAll(fields, Span.SPAN_FLAGS, Span.TRACE_ID_NAME, Span.SPAN_ID_NAME,
                Span.PROCESS_ID_NAME, Span.SPAN_NAME_NAME, Span.PARENT_ID_NAME,
                Span.SAMPLED_NAME, URI_HEADER);
        SPAN_FIELDS = Collections.unmodifiableSet(fields);
    }

    /**
     * Create new Map of carrier values
     */
    Map<String, String> convert(SpanTextMap textMap) {
        Map<String, String> carrier = new TreeMap<>(IGNORE_CASE_COMPARATOR);
        for (Map.Entry<String, String> entry : textMap) {
            if (isAcceptable(entry.getKey())) {
                carrier.put(entry.getKey(), entry.getValue());
            }
        }
        return Collections.unmodifiableMap(carrier);
    }

    private boolean isAcceptable(String key) {
        return SPAN_FIELDS.contains(key) || key.startsWith(BAGGAGE_PREFIX);
    }
}
