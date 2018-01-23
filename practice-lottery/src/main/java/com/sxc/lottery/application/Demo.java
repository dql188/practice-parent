package com.sxc.lottery.application;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxc.lottery.orm.Award;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 *
 * @author: ZMM
 * @version: 1.0
 * Filename:    	Demo
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
public class Demo {

    public static void main(String[] args) {
        List<Award> awards = Lists.newArrayList();

        Award one = new Award();
        one.setId(1);
        one.setDesc("100人民币");
        one.setFee(10000L);
        one.setRate(100);
        one.setTotalNum(1);
        awards.add(one);

        Award two = new Award();
        two.setId(2);
        two.setDesc("50人民币");
        two.setFee(5000L);
        two.setRate(500);
        two.setTotalNum(10);
        awards.add(two);

        Award three = new Award();
        three.setId(3);
        three.setDesc("20人民币");
        three.setFee(2000L);
        three.setRate(1400);
        three.setTotalNum(20);
        awards.add(three);

        Award four = new Award();
        four.setId(4);
        four.setDesc("10人民币");
        four.setFee(1000L);
        four.setRate(3000);
        four.setTotalNum(50);
        awards.add(four);

        Award five = new Award();
        five.setId(5);
        five.setDesc("5人民币");
        five.setFee(500L);
        five.setRate(3000);
        five.setTotalNum(50);
        awards.add(five);

        Award six = new Award();
        six.setId(6);
        six.setDesc("1人民币");
        six.setFee(100L);
        six.setRate(5000);
        six.setTotalNum(5000);
        awards.add(six);

        /**
         * 1 两台服务器 说明库存只能放在redis中
         * 2 在写数据的时候使用乐观锁
         * 3
         */

        //总权重随着库存变化
        Map<Integer, LotteryBO> lotteryBOMap = Maps.newConcurrentMap();
        int prev = 0;
        int totalWeight = 0;
        for (Award award : awards) {
            LotteryBO lotteryBO = new LotteryBO();
            int space = award.getRate() * award.getTotalNum();
            totalWeight += space;
            lotteryBO.setFront(prev);
            lotteryBO.setEnd(prev + space);
            lotteryBO.setId(award.getId());
            prev = prev + space;
            lotteryBOMap.put(award.getId(), lotteryBO);
        }
        //生成随机数
        int randNum = new Random().nextInt(totalWeight);
        int id = 0;
        for (Map.Entry<Integer, LotteryBO> m : lotteryBOMap.entrySet()) {
            if (randNum >= m.getValue().getFront() && randNum < m.getValue().getEnd()) {
                id = m.getKey();
                break;
            }
        }
        System.out.println(id);

    }

    @Data
    public static class LotteryBO {

        private Integer id;

        private Integer front;

        private Integer end;

    }
}
