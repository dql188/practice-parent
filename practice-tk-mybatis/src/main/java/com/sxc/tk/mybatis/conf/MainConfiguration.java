package com.sxc.tk.mybatis.conf;

import com.sxc.tk.mybatis.mapper.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	MainConfiguration
 * Create at:   	2018/1/17
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/17    	          ZMM           1.0          1.0 Version
 */
@Configuration
@MapperScan(basePackages = "com.sxc.tk.mybatis.mapper",markerInterface = MyMapper.class)
public class MainConfiguration {
}
