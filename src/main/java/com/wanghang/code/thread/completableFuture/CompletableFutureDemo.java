package com.wanghang.code.thread.completableFuture;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *CompletableFuture:
 *Java8带来了 CompletableFuture,CompletableFuture类实现了CompletionStage和Future接口,
 *                        提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，
 *                        提供了函数式编程的能力，可以通过回调的方式处理计算结果，
 *                         并且提供了转换和组合CompletableFuture的方法.
 * 参考博文：
 *        https://blog.csdn.net/mu_wind/article/details/103099834
 *
 *
 *
 *
 *
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo completableFutureDemo=new CompletableFutureDemo();


        //1:CompletableFuture执行线程
      //  completableFutureDemo.tompletableFutureTest1();


        //2:CompletableFuture执行线程之后的结果处理:
      //  completableFutureDemo.tompletableFutureTest2();


        /**
         * 3:CompletableFuture执行线程之后的结果转换thenApply()和thenCompose();
         *thenApply和thenCompose的区别:
         * thenApply 转换的是泛型中的类型，返回的是同一个CompletableFuture,
         * thenCompose:是将上一步CompletableFutre用作下一步的参数，最后生成一个新的CompletableFuture.
         */
      //  completableFutureDemo.tompletableFutureTest31();
      //  completableFutureDemo.tompletableFutureTest32();


        /**
         *4:CompletableFuture执行线程之后的结果消费：
         */
       // completableFutureDemo.tompletableFutureTest41();
       // completableFutureDemo.tompletableFutureTest42();
       // completableFutureDemo.tompletableFutureTest43();


        /**
         *5:CompletableFuture执行线程之后合并两个线程的结果:
         */
        completableFutureDemo.tompletableFutureTest5();

    }




    /**
     * 1:CompletableFuture执行异步的线程:
     */
    public void tompletableFutureTest1() throws ExecutionException, InterruptedException {

        //1.1:CompletableFuture.runAsync()方法执行普通的异步线程,当然也可以已java8的写法
        System.out.println(CompletableFuture.runAsync(new WorkRRunnable()).get());

        //1.2:CompletableFuture.supplyAsync()方法执行有返回值的异步任务,这个返回值可以是任意值(Object,String,Interge等),返回一个CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行有返回值的线程");
            return "Hello World";
        });
        String result = future.get();
        System.out.println(result);
    }
    class WorkRRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("WorkRRunnable没有返回结果的线程");
        }
    }



    /**
     *2:CompletableFuture结果处理;
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，我们可以执行特定的 Action
     *
     * todo:CompletableFuture执行异步线程,结果出现异常后,执行了异常处理的结果future.exceptionally(),但是还是会执行future.whenComplete有点不明白
     */
    public void tompletableFutureTest2(){
        //2.1:执行线程返回一个CompletableFuture对象;
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
            if (new Random().nextInt() % 2 == 0) {
                int i = 12 / 0;
            }
        });


        //对CompletableFuture执行异步线程的结果进行处理
        //2.1线程正常执行的处理
        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("CompletableFuture执行线程成功后的处理");
            }
        });

        //2.2线程执行异常的处理:
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("CompletableFuture执行线程失败,异常信息为:"+t.getMessage());
                return null;
            }
        }).join();
    }


    /**
     *3:CompletableFuture结果转换
     * 所谓结果转换，就是将上一段任务的执行结果作为下一阶段任务的入参参与重新计算，产生新的结果
     * thenApply()
     */
    public void  tompletableFutureTest31() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("thenApply一阶段:" + result);
            return result;
        });

        future.thenApply(s->{        //这个s是CompletableFuture执行异步线程的结果,是一个Integer类型的返回结果，但是要是是一个java的实体对象呢？也是这么写的吗？

            int result=s*5;
            System.out.println("thenApply二阶段:"+result);
            return result;
        });
        System.out.println("thenApply最终的执行结果:"+future.get());
    }


    /**
     *3:CompletableFuture结果转换
     * thenCompose()
     */
    public void tompletableFutureTest32() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future= CompletableFuture.supplyAsync(new Supplier<Integer>() {   //这个Supplier里的泛型可以是执行,现在指定的是Integer,到时候改成java的Bean试下
            @Override
            public Integer get() {
                int number = new Random().nextInt(15);
                System.out.println("thenCompose第一阶段：" + number);
                return number;
            }
        });
        CompletableFuture<Integer> newFuture  = future.thenCompose(new Function<Integer, CompletionStage<Integer>>() {   //这里Function的泛型应该根据上一部的泛型Integer，CompletionStage应该是最终结果的需要返回的泛型,到时候改成java的bean试下
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                System.out.println("param:" + param);

                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = param * 2;
                        System.out.println("thenCompose第二阶段：" + number);
                        return number;
                    }
                });
            }
        });
        System.out.println("thenCompose最终结果"+newFuture.get());
    }


    /**
     *4:CompletableFuture结果消费
     * 结果处理和结果转换系列函数返回一个新的 CompletableFuture 不同，结果消费系列函数只对结果执行Action，而不返回新的计算值
     * 根据结果的处理方式,可分为：
     * thenAccept系列：对单个结果进行消费,
     * thenRun系列：不关心结果，只对结果执行Action
     */
    public void tompletableFutureTest41() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("thenAccept第一阶段：" + number);
            return number;
        });
        future.thenAccept(s->{ //s是上一步的结果，根据泛型应该可以写成Object对象，上异步结果处理后没有返回值。
            System.out.println("thenAccept第二阶段：" + s * 5);
        });
        System.out.println("thenAccept后最后future的结果"+future.get());
    }

    /**
     *
     *thenAcceptBoth系列：对两个结果进行消费,
     *
     */
    public void tompletableFutureTest42() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {    //Supplier里的泛型表示返回值,应该可以定义成java的bean
            @Override
            public Integer get() {
                int number = new Random().nextInt(3) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {

                }
                System.out.println("thenAcceptBoth第一阶段：" + number);
                return number;
            }
        });
        System.out.println("第一阶段的结果为:"+future1.get());

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(3) + 1;

                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {

                }
                System.out.println("thenAcceptBoth第二阶段：" + number);
                return number;
            }
        });
        System.out.println("第二阶段的结果为:"+future2.get());

        future1.thenAcceptBoth(future2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer future1Reslut, Integer future2Reslut) {
                System.out.println("最终结果:"+(future1Reslut+future2Reslut));
            }
        }).join();       //todo: 这个地方加join()是什么意思？加和不加的结果是一样的？
    }
    /**
     *thenRun,也是对结果消费，并且不关心结果,与thenAccept不同的是,thenRun会在上一个线程执行完成之后执行下一个线程，这个线程的执行不会使用上一个线程的结果。
     */
    public void tompletableFutureTest43() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("thenRun第一阶段：" + number);
            return number;
        });
        future.thenRun(()->{                                  //去执行下一个线程,和上一个线程结果没有关系，并且执行后没有结果
            System.out.println("thenRun第二阶段执行");
        });
        System.out.println("thenRun消费后最终的结果为：" + future.get());
    }


    /**
     * 5:CompletableFuture结果合并
     * 合并两个线程任务的结果，并进一步处理
     * thenCombine
     */
    public void tompletableFutureTest5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            int number = new Random().nextInt(20);
            System.out.println("thenCombine的第一个结果：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(30);
                System.out.println("thenCombine的第二个结果：" + number);
                return number;
            }
        });

        CompletableFuture<Object> newFuture = future1.thenCombine(future2, new BiFunction<Integer, Integer, Object>() {   //BiFunction 泛型的第一个Integer为future1结果的返回值,第二个Integer为future2结果的返回值,第三个Integer为两个线程结果合并后的返回值,也可以定义为Object的类型
            @Override
            public Object apply(Integer future1Reslut, Integer future2Reslut) {
                return future1Reslut + future2Reslut;
            }
        });
        System.out.println("thenCombine合并两个线程计算结果为:"+newFuture.get());
    }

}
