package com.sxc.dubbo.api.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	UserDTO
 * Create at:   	2018/1/23
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/1/23    	          ZMM           1.0          1.0 Version
 */
@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

    private Long account;
}
