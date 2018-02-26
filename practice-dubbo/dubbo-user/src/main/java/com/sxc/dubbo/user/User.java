package com.sxc.dubbo.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private Integer state;

    private Date createTime;

    private Date updateTime;


}