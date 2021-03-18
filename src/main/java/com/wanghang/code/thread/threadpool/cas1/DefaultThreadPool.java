package com.wanghang.code.thread.threadpool.cas1;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

//线程池的具体实现
public class DefaultThreadPool<T extends Runnable> implements ThreadPool<T> {

    //线程池子最大限制数
    private static final  int MAX_WORKER_NUMBERS=10;
    //线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS=5;

    //这是一个工作列表,将会向里面插入工作
    private final LinkedList<Job> jobs=new LinkedList<Job>();

    //任务列表
    private final List<Worker> workers=new CopyOnWriteArrayList<Worker>();

    //工作者的线程数：
    private int workerNum=DEFAULT_WORKER_NUMBERS;


    //线程编号的生成
    private AtomicLong threadNum=new AtomicLong();


    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum=num>MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num;
        initializeWorkers(workerNum);
    }


    //添加一个job,实际上是往jobs里面加一个元素，然后通知消费者线程执行来消费
    public void execute(Job job) {
        if (job!=null){
            synchronized (jobs){
                jobs.add(job);
                jobs.notifyAll();
            }
        }
    }

    //限制新加Worker的数量不能超过workers的最大数量
    public void addWorkers(int num) {
        synchronized (jobs){
            if(num+this.workerNum>MAX_WORKER_NUMBERS){
                num=MAX_WORKER_NUMBERS-this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum+=num;
        }
    }


    //删除任务
    public void removeWorkers(int num) {
        synchronized (jobs){
            if (num>this.workerNum){
                throw  new IllegalArgumentException("beyond workNum");
            }
            //按照给定的数量停止Worker
            int count=0;
            while (count<num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutDown();
                    count++;
                }
            }
            this.workerNum-=count;
        }
    }


    //获取目前所有还没执行任务的数量
    public int getJobSize() {
        return jobs.size();
    }



    //关闭所有的工作线程
    public void shutDown() {
        for (Worker worker:workers){
            worker.shutDown();
        }
    }

    //初始化线程工作者
    public void initializeWorkers(int num){
         for (int i = 0; i < num; i++) {
             Worker worker=new Worker();
             workers.add(worker);
             Thread thread=new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
             thread.start();
         }
    }

    /**
     *工作者,负责消费任务
     *
     */
    class Worker implements Runnable {
        private volatile boolean running=true;

        public void run() {
            while (running){
                Job job=null;
                synchronized (jobs){
                    //判断工作列表是否为空，为空则wait()
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    //取出一个job
                    job=jobs.removeFirst();
                    if(job!=null){
                        job.execute(job.getJobId());
                    }
                }
            }
        }

       //让消费者线程停止消费;
        public void shutDown(){
            running=false;
        }
    }
}




