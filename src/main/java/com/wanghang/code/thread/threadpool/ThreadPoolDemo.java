package com.wanghang.code.thread.threadpool;

import java.util.concurrent.*;

/**
 *线程池：
 * https://gitee.com/moxi159753/LearningNotes/tree/master/%E6%A0%A1%E6%8B%9B%E9%9D%A2%E8%AF%95/JUC/10_%E7%BA%BF%E7%A8%8B%E6%B1%A0
 *
 * 1)线程池的作用及特点：
 *                 线程池做的主要工作就是控制运行的线程的数量，处理过程中，将任务放入到队列中，然后线程创建后，
 *                   启动这些任务，如果线程数量超过了最大数量的线程排队等候，等其它线程执行完毕，再从队列中取出任务来执行。
 *                 它的主要特点为：线程复用、控制最大并发数、管理线程。
 *
 *2)使用线程池的好处：
 *               和使用Spring的ioc一样，原来我们实例化对象的时候，是使用 new关键字进行创建，
 *               到了Spring后，我们学了IOC依赖注入，发现Spring帮我们将对象已经加载到了Spring容器中，
 *               只需要通过@Autowrite注解，就能够自动注入，从而使用
                 因此使用多线程有下列的好处：
                 1)降低资源消耗(线程的复用),过重复利用已创建的线程，降低线程创建和销毁造成的消耗;
                 2)提高响应速度(线程池中有线程)，当任务到达时，任务可以不需要等到线程创建就立即执行;
                 3)管理线程(统一管理和可以监控)：线程是稀缺资源，如果无线创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控
 *
 *3)Java中线程池是通过Executor框架实现的,一般不用Executors的这种方式创建线程池,一般通过手工的方式创建线程池：
 *              原因：Executors的方式创建线程Executors.newFixedThreadPool(int nThreads),
 *                                       Executors.newSingleThreadExecutor(),    --一个线程数
 *                                       Executors.newCachedThreadPool(),        --自动扩容的方式创建线程池
 *
 *             运行的请求队列长度为：Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM
 *
 *
 *
 *4)手工创建线程池的7大参数：
 *  corePoolSize：核心线程数,也就是线程池中的常驻核心线程数；
 *  maximumPoolSize：线程池能够容纳同时执行的最大线程数;
 *  keepAliveTime:多余的空闲线程存活时间;
 *                当线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，多余的空闲线程会被销毁，直到只剩下corePoolSize个线程为止.
 *                默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用;
 * unit:keepAliveTime的单位;
 * workQueue(阻塞队列):被提交的但未被执行的任务(类似于银行里面的候客区);
 *                   LinkedBlockingQueue：链表阻塞队列
 *                   SynchronousBlockingQueue：同步阻塞队列
 *threadFactory:表示生成线程池线创建线程的工厂, 一般用默认即可;
 *handler:拒绝策略,表示阻塞队列满了并且，线程池中的线程数也达到了最大线程数，如何来拒绝请求执行的Runnable的策略。
 *        一般常见的拒绝策略：
 *        AbortPolicy：默认，直接抛出RejectedExcutionException异常，阻止系统正常运行；
 *        DiscardPolicy：直接丢弃任务，不予任何处理也不抛出异常，如果运行任务忍许丢失，这是一种好方案；
 *        CallerRunsPolicy：该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者；
 *        DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 *
 *5)线程池的工作原理：
 *  a)在创建了线程池后，等待提交过来的任务请求
 *  b)当调用execute()方法添加一个请求任务时，线程池会做出如下判断:
 *    1:如果正在运行的线程池数量小于corePoolSize，那么马上创建线程运行这个任务;
 *    2:如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列;
 *    3:如果这时候队列满了，并且正在运行的线程数量还小于maximumPoolSize，那么还是创建非核心线程来运行这个任务;
 *    4:如果队列满了并且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行;
 * c)当一个线程完成任务时，它会从队列中取下一个任务来执行;
 * d)当一个线程的空闲时间超过keepAliveTime时，线程池会判断：
 *    1:如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉,所以线程池的所有任务完成后，它会最终收缩到corePoolSize的大小.
 *
 *
 * 6)线程池的拒绝策略代码演示：
 *
 * 7关于线程池中的线程数的设置：生产环境中如何配置 corePoolSize 和 maximumPoolSize？
 *    CPU密集型：
 *            该任务需要大量的运算，而没有阻塞，CPU一直全速运行；CPU密集任务只有在真正的多核CPU上才可能得到加速（通过多线程),而在单核CPU上，无论你开几个模拟的多线程该任务都不可能得到加速，因为CPU总的运算能力就那些
 *            CPU密集型任务配置尽可能少的线程数量
 *            配置线程数：CPU核数 + 1个线程数，可以通过api获取当前服务器的核数。
 *   IO密集型：
 *           IO密集型，即该任务需要大量的IO操作，即大量的阻塞，在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力花费在等待上
 *           所以IO密集型任务中使用多线程可以大大的加速程序的运行，即使在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间
 *           IO密集时，大部分线程都被阻塞，故需要多配置线程数：(也可以通过api获取到当前服务器的)
 *                                                  参考公式：CPU核数 / (1 - 阻塞系数) 阻塞系数在0.8 ~ 0.9左右
 *                                                  例如：8核CPU：8/ (1 - 0.9) = 80个线程数
 *
 *
 */
public class ThreadPoolDemo {

    // 一池5个处理线程（用池化技术，一定要记得关闭）
    ExecutorService threadPool = Executors.newFixedThreadPool(5);

    // 创建一个只有一个线程的线程池
    ExecutorService newSingleThreadPool = Executors.newSingleThreadExecutor();

    // 创建一个拥有N个线程的线程池，根据调度创建合适的线程
    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();


    public static void main(String[] args) {
     /*   ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool1.shutdown();
        }*/

        //手工创建线程池测试拒绝策略
        ThreadPoolDemo  threadPoolDemo=new ThreadPoolDemo();

        //1:AbortPolicy策略
        threadPoolDemo.creatThreadPoolExecutorAbortPolicy();
        //2：DiscardPolicy策略
        threadPoolDemo.creatThreadPoolExecutorAbortDiscardPolicy();
        //3：CallerRunsPolicy策略
        threadPoolDemo.creatThreadPoolExecutorAbortCallerRunsPolicy();
        //4：DiscardOldestPolicy策略
        threadPoolDemo.creatThreadPoolExecutorAbortDiscardOldestPolicy();
    }

    //1,AbortPolicy:请求的线程大于 阻塞队列大小 + 最大线程数 = 8 的时候，也就是说第9个线程来获取线程池中的线程时，就会抛出异常从而报错退出。
    private  void creatThreadPoolExecutorAbortPolicy() {
        // 手写线程池
        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e){

        } finally{
            threadPool.shutdown();
        }
    }

    //2:DiscardPolicy,请求的线程大于 阻塞队列大小 + 最大线程数 = 8 的时候，也就是说第9个线程来获取线程池中的线程时,会丢弃任务
    private  void creatThreadPoolExecutorAbortDiscardPolicy() {
        // 手写线程池
        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e){

        } finally{
            threadPool.shutdown();
        }
    }

    //3.CallerRunsPolicy,请求的线程大于 阻塞队列大小 + 最大线程数 = 8 的时候，也就是说第9个线程来获取线程池中的线程时,会将某些任务回退到调用者
    private  void creatThreadPoolExecutorAbortCallerRunsPolicy() {
        // 手写线程池
        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e){

        } finally{
            threadPool.shutdown();
        }
    }

    //4:DiscardOldestPolicy,请求的线程大于 阻塞队列大小 + 最大线程数 = 8 的时候，也就是说第9个线程来获取线程池中的线程时,会抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
    private  void creatThreadPoolExecutorAbortDiscardOldestPolicy() {
        // 手写线程池
        final Integer corePoolSize = 2;
        final Integer maximumPoolSize = 5;
        final Long keepAliveTime = 1L;

        ExecutorService threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 模拟10个用户来办理业务，每个用户就是一个来自外部请求线程
        try {
            // 循环十次，模拟业务办理，让5个线程处理这10个请求
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 给用户:" + tempInt + " 办理业务");
                });
            }
        } catch (Exception e){

        } finally{
            threadPool.shutdown();
        }
    }
}
