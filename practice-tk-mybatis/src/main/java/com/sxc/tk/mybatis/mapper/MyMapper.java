package com.sxc.tk.mybatis.mapper;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	MyMapper
 * Create at:   	2018/1/18
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/18    	          ZMM           1.0          1.0 Version
 */
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectByIdsMapper<T>, ConditionMapper<T> {
}
