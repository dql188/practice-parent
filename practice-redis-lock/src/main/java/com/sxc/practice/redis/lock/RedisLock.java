package com.sxc.practice.redis.lock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	RedisLock
 * Create at:   	2018/3/8
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/8    	          ZMM           1.0          1.0 Version
 */
public class RedisLock {

    private String lockKey;

    /**
     * 默认过期时间(s)
     */
    private static final Integer EXPIRE_TIME = 60;

    /**
     * 请求锁 默认过期时间
     */
    private static final Long TIME_OUT = 100L;

    /**
     * 是否得到锁
     */
    private volatile boolean isLock = false;

    /**
     * spring-boot-start-data-redis 引擎
     */
    private StringRedisTemplate stringRedisTemplate;


    public Boolean tryLock() {
        Long nowTime = System.nanoTime();
        Long timeOut = TIME_OUT * 10000;
        //不断请求锁
        while (System.nanoTime() - nowTime <= timeOut) {
            if (stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "LOCK")) {
                isLock = true;

                stringRedisTemplate.expire(lockKey, EXPIRE_TIME, TimeUnit.SECONDS);
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return isLock;
    }


    public Boolean tryLock1() {
        Long nowTime = System.nanoTime();
        Long timeOut = TIME_OUT * 10000;
        //不断请求锁

        if (stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "LOCK")) {
            isLock = true;
            stringRedisTemplate.expire(lockKey, EXPIRE_TIME, TimeUnit.SECONDS);
        }
        return isLock;
    }

    public void unlock() {
        if (isLock) {
            stringRedisTemplate.delete(lockKey);
        }
    }

}
