package com.sxc.practice.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.UUID;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	RedisLock2
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
public class RedisLock2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock2.class);

    private String lockKey;

    private String lockValue;

    private static Integer EXPIRE_TIME = 60;

    private static final Long TIME_OUT = 100L;

    private volatile boolean isLock;

    private static final String OK = "OK";

    private StringRedisTemplate stringRedisTemplate;

    public static final String unLockLua =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "    return redis.call(\"del\",KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";


    public Boolean tryLock() {

        Long nowTime = System.nanoTime();
        lockValue = UUID.randomUUID().toString();
        while (System.nanoTime() - nowTime <= TIME_OUT) {

            if (OK.equalsIgnoreCase(set(lockKey, lockValue, EXPIRE_TIME))) {
                LOGGER.info("得到锁");
                isLock = true;
                return isLock;
            }
        }
        return isLock;
    }

    public String set(String lockKey, String lockValue, Integer EXPIRE_TIME) {
        return stringRedisTemplate.execute((RedisCallback<String>) redisConnection -> {
            Object connection = redisConnection.getNativeConnection();
            String result = null;
            if (connection instanceof JedisCluster) {
                result = ((JedisCluster) connection).set(lockKey, lockValue, "NX", "EX", EXPIRE_TIME);
            }
            if (connection instanceof Jedis) {
                result = ((Jedis) connection).set(lockKey, lockValue, "NX", "EX", EXPIRE_TIME);
            }

            return result;
        });
    }

    public Boolean unLock() {
        if (isLock) {
            stringRedisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Long result = null;

                    Object connection = redisConnection.getNativeConnection();
                    if (connection instanceof JedisCluster) {
                        result = (Long) ((JedisCluster) connection).eval(unLockLua, Arrays.asList(lockKey), Arrays.asList(lockValue));
                    }
                    if (connection instanceof Jedis) {
                        result = (Long) ((JedisCluster) connection).eval(unLockLua, Arrays.asList(lockKey), Arrays.asList(lockValue));
                    }
                    isLock = result == 0;
                    return result == 1;
                }
            });
        }

        return isLock;
    }
}
