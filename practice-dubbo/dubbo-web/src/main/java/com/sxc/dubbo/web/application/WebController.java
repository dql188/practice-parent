package com.sxc.dubbo.web.application;

import com.sxc.dubbo.api.result.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	WebController
 * Create at:   	2018/3/23
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/23    	          ZMM           1.0          1.0 Version
 */
@RestController
public class WebController {

    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    WebHandler webHandler;

    @GetMapping("/user/{id}")
    public UserDTO query(@PathVariable("id") Integer id) {
        logger.info("web");
        UserDTO userDTO = webHandler.queryById(id);
        return userDTO;
    }
}
