package com.wanghang.code.JDK.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.time.DateFormatUtils;


import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RetryerDemo {


    public static void main(String[] args) throws Exception {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())                                      // 设置自定义段元重试源
                .retryIfExceptionOfType(Exception.class)                                          // 设置异常重试源
                .retryIfRuntimeException()                                                        // 设置异常重试源
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))               // 设置重试次数    设置重试超时时间
                .withWaitStrategy(WaitStrategies.fixedWait(1L, TimeUnit.SECONDS))        // 设置每次重试间隔
                .build();

        Callable<Boolean> task = new Callable<Boolean>() {
            int i = 0;
            @Override
            public Boolean call() throws Exception {
                Integer random = getRandom();
                System.out.println("产生的随机数为:"+random);
                if(random>5){
                    return true;
                }else{
                    i++;
                    System.out.println("第"+i+"次执行,执行时间为:"+ DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));
                    return false;
                }
            }
        };
        try {
            retryer.call(task);
        } catch (ExecutionException e) {
          System.out.println("error:"+e);
        } catch (RetryException e) {
            System.out.println("error:"+e);
        }
        Boolean result = task.call();
        System.out.println("成功输出结果为:"+result);
    }

    public static Integer getRandom(){
        //(最小值+Math.random()*(最大值-最小值+1)), 使用Math.random()返回1到10的随机数
        return  (int) (1 + Math.random() * (10 - 1 + 1));
    }
}
