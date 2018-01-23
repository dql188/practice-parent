package com.sxc.lottery.orm;

import lombok.Data;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	Award
 * Create at:   	2018/1/22
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/22    	          ZMM           1.0          1.0 Version
 */
@Data
public class Award {

    /**
     * id
     */
    private Integer id;

    /**
     * 描述
     */
    private String desc;

    /**
     * 金额
     */
    private Long fee;

    /**
     * 概率
     */
    private Integer rate;

    /**
     * 库存
     */
    private Integer totalNum;


}
