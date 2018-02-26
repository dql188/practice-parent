package com.sxc.dubbo.user.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	MainConfiguration
 * Create at:   	2018/2/26
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/2/26    	          ZMM           1.0          1.0 Version
 */
@Configuration
@ImportResource(locations = {"classpath:spring/application-context-consumer.xml"})
public class MainConfiguration {
}
