package com.sxc.dubbo.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	ApplicationBeanHolder
 * Create at:   	2018/3/22
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/22    	          ZMM           1.0          1.0 Version
 */
public class ApplicationBeanHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setCtx(applicationContext);
    }

    private static void setCtx(ApplicationContext ctx) {
        applicationContext = ctx;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static Object getBean(String classStr) {
        return applicationContext.getBean(classStr);
    }
}
