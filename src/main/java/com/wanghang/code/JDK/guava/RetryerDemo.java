package com.wanghang.code.JDK.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;


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
                .withWaitStrategy(WaitStrategies.fixedWait(5L, TimeUnit.SECONDS))        // 设置每次重试间隔
                .build();
        Callable<Boolean> task = new Callable<Boolean>() {
            int i = 0;
            @Override
            public Boolean call() throws Exception {
                i++;
                System.out.println("第"+i+"次执行");
                if (i<3) {
                    System.out.println("执行失败");
                    throw new Exception("异常");
                }
                return true;
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
}
