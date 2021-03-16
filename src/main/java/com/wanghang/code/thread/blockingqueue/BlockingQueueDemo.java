package com.wanghang.code.thread.blockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *阻塞队列：
 *      线程1往阻塞队列中添加元素，而线程2从阻塞队列中移除元素；
 *        当阻塞队列是空时，从队列中获取元素的操作将会被阻塞
 *        当阻塞队列是满时，从队列中添加元素的操作将会被阻塞
 *
 * 现实的场景及好处：
 *       去海底捞吃饭，大厅满了，需要进入等待区进入等待， 这个等待去就相当于阻塞队列
 *       好处：好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切阻塞队列已经实现了
 *
 *阻塞队列的实现类：
 *      ArrayBlockQueue：由数组结构组成的有界阻塞队列
 *      LinkedBlockingQueue：由链表结构组成的有界（但是默认大小 Integer.MAX_VALUE）的阻塞队列(有界，但是界限非常大，相当于无界，可以当成无界)
 *      SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列,一种特殊的队列;多线程插入一个，消费一个(实现比较经典的生产者-消费者的多线程模式)
 *
 *阻塞队列对应的api：新增和移除，以及查看，主要有4组,
 * 1)抛出异常组：
 * add():当阻塞队列满时：在往队列中add插入元素会抛出 IIIegalStateException,插入方法，成功true，失败false
 * remove():Queue full 当阻塞队列空时：再往队列中remove移除元素，会抛出NoSuchException,移除方法：成功返回出队列元素，队列没有就返回空
 * element():
 *
 *2)特殊值组：
 *  offer():添加元素时候，如果阻塞队列满了后，会返回false，否者返回true
 *  poll(): 取元素的时候,如果队列已空，那么会返回null
 *
 *3)阻塞队列阻：
 * put()方法添加元素时候，如果阻塞队列满了后，添加元素的线程会一致阻塞，直到队列元素减少才可以添加
 *                     当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列中有元素可以消费
 *      使用场景：
 *      一般在消息中间件，比如RabbitMQ中会使用到，因为需要保证消息百分百不丢失，因此只有让它阻塞
 *take()取出元素；
 *
 *4)超时(不见不散组)：当阻塞队列满时，队里会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 *  offer(),offer插入的时候，需要指定时间，如果2秒还没有插入，那么就放弃插入
 *  poll(), poll取出时也需要加时间
 */
public class BlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {

        BlockingQueueDemo blockingQueueDemo=new BlockingQueueDemo();

        //1:抛出异常组 add(),remove()组
     //   blockingQueueDemo.operation1();

        //2:操作返回boolean值,offer(),poll()
     //   blockingQueueDemo.operation2();

        //3：阻塞组put()和take(), RabbitMq用的就是这个类型
     //   blockingQueueDemo.operation3();

        //
        blockingQueueDemo.operation4();



    }



    //1: add(),remove()组
    private void operation1() {
        // 阻塞队列，需要填入默认值
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //测试add(),再放入第四个时,阻塞队列已满抛出异常,java.lang.IllegalStateException: Queue full
       // System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //队列为空的时候去取队列里的值也是抛出异常：java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
    }

    //2:offer(),poll()
    private void operation2() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //阻塞队列满了之后，再往里面添加元素返回false
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //阻塞队列为空时，取不到元素返回null
        System.out.println(blockingQueue.poll());
    }


    //3:put(),take()组
    private void operation3() throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        //队列满了之后，再继续往里添加元素的话，生产者线程会在这阻塞
       // blockingQueue.put("d");
        System.out.println("================");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        //阻塞队列为空的情况下，还往队列里取元素的话， 消费者线程会阻塞在这里
        System.out.println(blockingQueue.take());
    }


    //4：offer(),poll()插入和取出时加时间：
    private void operation4() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));

        //队列满了之后，2秒之后还是插入不成功的话就放弃
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));

        System.out.println("================");

        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));

        //队列空了之后，还是取不到值，两秒钟之后就会放弃， 返回null
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }
}
