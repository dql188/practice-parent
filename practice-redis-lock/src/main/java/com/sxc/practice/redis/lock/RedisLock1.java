package com.sxc.practice.redis.lock;

import org.springframework.data.redis.connection.RedisConnection;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	RedisLock1
 * Create at:   	2018/3/15
 * <p>
 * Copyright:   	Copyright (c)2018
 * Company:     	songxiaocai
 * <p>
 * Modification History:
 * Date        		      Author          Version      Description
 * ------------------------------------------------------------------
 * 2018/3/15    	          ZMM           1.0          1.0 Version
 */
public class RedisLock1 {

    private final byte[] lockKey;

    public RedisLock1(byte[] lockKey) {
        this.lockKey = lockKey;
    }

    private boolean tryLock(RedisConnection conn, int lockSeconds) throws Exception {
        long nowTime = System.currentTimeMillis();
        System.nanoTime();
        long expireTime = nowTime + lockSeconds * 1000 + 1000; // 容忍不同服务器时间有1秒内的误差
        if (conn.setNX(lockKey, longToBytes(expireTime))) {
            conn.expire(lockKey, lockSeconds);
            return true;
        } else {
            byte[] oldValue = conn.get(lockKey);
            if (oldValue != null && bytesToLong(oldValue) < nowTime) {
                // 这个锁已经过期了，可以获得它
                // PS: 如果setNX和expire之间客户端发生崩溃，可能会出现这样的情况
                byte[] oldValue2 = conn.getSet(lockKey, longToBytes(expireTime));
                if (Arrays.equals(oldValue, oldValue2)) {
                    // 获得了锁
                    conn.expire(lockKey, lockSeconds);
                    return true;
                } else {
                    // 被别人抢占了锁(此时已经修改了lockKey中的值，不过误差很小可以忽略)
                    return false;
                }
            }
        }
        return false;
    }

    public byte[] longToBytes(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
        buffer.putLong(value);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        if (bytes.length != Long.SIZE / Byte.SIZE) {
            throw new IllegalArgumentException("wrong length of bytes!");
        }
        return ByteBuffer.wrap(bytes).getLong();
    }

}
