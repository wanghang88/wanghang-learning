package com.wanghang.code.algorithm;


import com.wanghang.code.utils.DateUtil;


import java.util.Date;

/**
 *简单的超时算法
 *
 */
public class TimeOutalgorithm {



    public static void main(String[] args) {
        System.out.println("调用的时间为:"+DateUtil.format(new Date(),DateUtil.YMDHMS));
        timeOutalgorithm(3000);
    }


    /**
     * 超时算法
     * @param mills
     */
    private static void timeOutalgorithm(long  mills) {
        long remainingMills=mills;
        long endMills=System.currentTimeMillis()+remainingMills;
        while (true){
            if(remainingMills<=0){
                System.out.println("超时的时间为:"+ DateUtil.format(new Date(),DateUtil.YMDHMS));
                System.out.println("调用方法超时了,直接停止");
                break;
            }
            remainingMills=endMills-System.currentTimeMillis();
        }
    }
}
