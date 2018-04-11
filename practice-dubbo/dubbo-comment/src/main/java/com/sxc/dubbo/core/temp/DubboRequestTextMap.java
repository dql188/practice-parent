package com.sxc.dubbo.core.temp;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.cloud.sleuth.SpanTextMap;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboRequestTextMap
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
public class DubboRequestTextMap implements SpanTextMap {

    private final RpcContext delegate;

    public DubboRequestTextMap(RpcContext delegate) {
        this.delegate = delegate;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return this.delegate.getAttachments().entrySet().iterator();
    }

    @Override
    public void put(String key, String value) {
        if (!StringUtils.hasText(value)) {
            return;
        }
        this.delegate.getAttachments().put(key, value);
    }
}
