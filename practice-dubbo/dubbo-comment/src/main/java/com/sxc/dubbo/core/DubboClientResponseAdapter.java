package com.sxc.dubbo.core;

import com.alibaba.dubbo.rpc.Result;
import com.github.kristofa.brave.ClientResponseAdapter;
import com.github.kristofa.brave.KeyValueAnnotation;

import java.util.Collection;
import java.util.Collections;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	DubboClientResponseAdapter
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
public class DubboClientResponseAdapter implements ClientResponseAdapter {

    @Override
    public Collection<KeyValueAnnotation> responseAnnotations() {
        return Collections.emptyList();
    }
}
