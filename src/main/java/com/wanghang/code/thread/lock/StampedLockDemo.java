package com.wanghang.code.thread.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 *ReentrantReadWriteLock读写锁：
 *                           可以解决多线程同时读，但只有一个线程能写的问题
 *
 *2)ReadWriteLock存在的缺陷：
 *                        如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，也就是读的过程中是不允许写的,是一种悲观的读锁
 *
 *3)java8需要进一步提升并发执行效率：引入了StampedLock
 *      StampedLock相较于ReadWriteLock的改进之处：
 *                        读的过程中也允许获取写锁后写入,并且在读的时候会判断读的过程中是否有写入，这种读锁是一种乐观锁
 *
 *4)也就是ReentrantReadWriteLock和StampedLock的区别：
 *        ReentrantReadWriteLock采用悲观读锁:悲观读锁则是读的过程中拒绝有写入，也就是写入必须等待；
 *        StampedLock采用乐观读锁:乐观地估计读的过程中大概率不会有写入,乐观读锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行
 */
public class StampedLockDemo {

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo=new StampedLockDemo();

   //   stampedLockDemo.operationSafeStampedLockCache();

        stampedLockDemo.operationSafeStampedLockCache1();       //乐观地读写

 //     stampedLockDemo.operationSafeStampedLockCache2();     //悲观地读写
    }


    //StampedLock操作示例1
    private void operationSafeStampedLockCache() {
        SafeStampedLockCache safeStampedLockCache=new SafeStampedLockCache();
         for(int i=1;i<=5;i++){
             final int tempInt=i;
             new Thread(()->{
                 safeStampedLockCache.put(tempInt +"",tempInt +"");
             },String.valueOf(i)).start();
         }


          for(int i=1;i<=5;i++){
              final int tempInt = i;
              new Thread(()->{
                  safeStampedLockCache.get(tempInt + "");
              },String.valueOf(i)).start();
          }
    }


    private void operationSafeStampedLockCache1() {
        //1:实例话SafeStampedLockCache缓存
        SafeStampedLockCache safeStampedLockCache=new SafeStampedLockCache();
        for (int i = 1; i <=100 ; i++) {
            safeStampedLockCache.put(i+"",i+"");
        }

        //2:实用化读写锁操作缓存
        for (int i = 1; i <=100; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                if(tempInt%5==0){
                    safeStampedLockCache.put(tempInt + "", tempInt +"");
                }else {
                    safeStampedLockCache.optimisticGet(tempInt+"");
                }
            }, String.valueOf(i)).start();
        }
    }



    //StampedLock操作示例2
    private void operationSafeStampedLockCache2() {
        //1:实例话SafeStampedLockCache缓存
        SafeStampedLockCache safeStampedLockCache=new SafeStampedLockCache();
        for (int i = 1; i <=100 ; i++) {
            safeStampedLockCache.put(i+"",i+"");
        }

        //2:实用化读写锁操作缓存
        for (int i = 1; i <=100; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                if(tempInt%5==0){
                    safeStampedLockCache.put(tempInt + "", tempInt +"");
                }else {
                    safeStampedLockCache.get(tempInt + "");
                }
            }, String.valueOf(i)).start();
        }
    }
}



class SafeStampedLockCache{
    private volatile Map<String,String> safeStampedLockCache=new HashMap<>();

    private StampedLock stampedLock=new StampedLock();

    public void put(String key, String value){
        long stamp=0;
        try{
             stamp = stampedLock.writeLock();
             System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);

             //让线程休眠3秒钟,相当于是网络延迟
             TimeUnit.SECONDS.sleep(3);
             safeStampedLockCache.put(key,value);

             System.out.println(Thread.currentThread().getName()+"\t 写入完成");
            }catch (Exception e){

            }finally {
            stampedLock.unlockWrite(stamp);
            }
    }


    //get()悲观地读取
    public void get(String key){
        long stamp=0;
        try{
             stamp = stampedLock.readLock();
             System.out.println(Thread.currentThread().getName()+"\t 开始读取");
             TimeUnit.SECONDS.sleep(3);

             String value= safeStampedLockCache.get(key);

             System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
        }catch (Exception e){

        }finally {
             stampedLock.unlockRead(stamp);
        }
    }

    //乐观地读取:
    public void optimisticGet(String key){
        //1:尝试去获取一个乐观锁
        long stamp = stampedLock.tryOptimisticRead();

        //2：检查乐观读锁之后是否有其他写锁发生
        if(!stampedLock.validate(stamp)){
            try{
                stamp = stampedLock.readLock(); // 获取一个悲观读锁
                System.out.println(Thread.currentThread().getName()+"\t 开始读取");
                TimeUnit.SECONDS.sleep(3);

                String value= safeStampedLockCache.get(key);
                System.out.println(Thread.currentThread().getName()+"\t 读取完成:"+value);
            }catch (Exception e){

            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
    }
}




