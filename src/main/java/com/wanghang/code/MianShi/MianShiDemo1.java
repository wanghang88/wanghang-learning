package com.wanghang.code.MianShi;


/**
    1:java面试题目总结的博文(主要是面试题):
    https://blog.csdn.net/wb_snail/article/details/108134550

    https://mp.weixin.qq.com/s?__biz=Mzg4NDMyNTIwMw==&mid=2247488123&idx=1&sn=9567232df10afbfede9688e4762c988c&chksm=cfb8bc47f8cf35512bced50381acb94713b51bb8691e895ba880a2b7863d7c882f4b54aec190&scene=21#wechat_redirect

    https://blog.csdn.net/GitChat/article/details/78957557?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-9.baidujs&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-9.baidujs

    java基础总结得比较多:
    https://github.com/irwinai/JavaInterview/blob/main/%E6%9D%A5%E8%87%AA%E6%9C%8B%E5%8F%8B%E6%9C%80%E8%BF%91%E9%98%BF%E9%87%8C%E3%80%81%E8%85%BE%E8%AE%AF%E3%80%81%E7%BE%8E%E5%9B%A2%E7%AD%89P7%E5%B2%97%E4%BD%8D%E9%9D%A2%E8%AF%95%E9%A2%98.md

    java进阶面试题,包含答案(synchronized,ReentrantLock,Java内存模型,volatile,volatile,线程池,Callable,Atomic原子等,AQS)
    https://mp.weixin.qq.com/s/cdHfTTvMpH60SwG2bjTMBw

    java基础juc中 LockSupport & AQS & 各种锁 & 分布式锁的分析，特别是对锁的分析，互斥锁，aqs，分布式锁等
    https://www.jianshu.com/p/357fa6164bc2

   常见的java面试题包含答案(触发FullGc的条件,JVM和JUC方面的比较多)
   https://blog.csdn.net/qq_26323323/article/details/83751404?spm=1001.2014.3001.5502





    2:面试题搜集无答案，可以参考下
    面试题没有答案,不过可以参考面试题(据说是头条的面试题)
    https://mp.weixin.qq.com/s/aNteUiHsyDzjGks6r7MO4g
    菜鸟面试题搜集
    https://mp.weixin.qq.com/s?__biz=MzIwNDgxNDM0MQ==&mid=2247492719&idx=1&sn=d76368b1194f82a6484b319ce9d7adba&chksm=9738c505a04f4c13cc6f01478379aa6c301e379ae38f691b4dc30ff5c2efd487c0a797c618af&scene=132#wechat_redirect



   3:围绕系统优化，系统设计，中间件方面的优化
   a)高并发、高性能、高可用系统设计经验;
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484957&idx=1&sn=e50e0808cb6503ca7214bdd6fee4f134&chksm=ceb9fab6f9ce73a0c0725e381673fc7dc50c0594fb995b5f985b263143b34371e5e2936d7be0&scene=21#wechat_redirect
   b)高并发系统redis的设计
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484947&idx=1&sn=5a70f88fba83b435b8144bf1ddd3cc9f&chksm=ceb9fab8f9ce73ae97afc43f87314dd3bb61c966b9a40c12801cddc454dcf2845bbb605694e3&scene=21#wechat_redirect
   c)如何扣减库存的设计:
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484921&idx=1&sn=b429efe7e622759fc8f3bb24c2979a90&chksm=ceb9f952f9ce7044b001528ce8ae0ec89ed63727764081c21a8400e9f8f685345ec9cb0a54d7&scene=21#wechat_redirect
   d)优惠券的玩法：
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484857&idx=1&sn=21c2986a97b3d49f98f0d7f2c57e1822&source=41#wechat_redirect
   e)如何设计一个高性能的秒杀系统
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484869&idx=1&sn=f57664eac0e9e6677d317b6bff959276&chksm=ceb9f96ef9ce7078ff6783500a28afad207d2be53f9d9e0d2e0e44f72145a7562517e3dba897&scene=21#wechat_redirect
   f)聊聊分布式锁
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484819&idx=1&sn=d7305a75cf74cd6819343bfd772de21f&chksm=ceb9f938f9ce702ed7515de51856c81f0b8595f822d0c627a930d348cfcaa400e6607d47438b&scene=178&cur_album_id=1874615391855968264#rd

   借助SPI 解决复杂业务扩展问题
   https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484842&idx=1&sn=b2ec39dba9bdc2e92378383c93c62d3d&chksm=ceb9f901f9ce7017c420a89b8ee3a5a998eb2c1b7eccf36a6c0e85906933a43243c1bce4e646&scene=178&cur_album_id=1874615391855968264#rd

   电商设计
   微关技术公众号->玩转电商(关于电商中使用的一些玩法以及设计方案的总结):
   https://mp.weixin.qq.com/mp/appmsgalbum?__biz=Mzg2NzYyNjQzNg==&action=getalbum&album_id=1874603714678751240&scene=173&from_msgid=2247484857&from_itemidx=1&count=3&nolastread=1#wechat_redirect






    4:围绕JVM线上问题的面试的阶梯方案:
    a)线上服务的FGC问题排查
    https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484858&idx=1&sn=84d2082dfdbf06e2d5fa8a459f7a3e3b&source=41#wechat_redirect




    5关于mysql相关的技术解决:
    a)单台MySQL支撑不了这么多的并发请求,使用读写分离或者分库分表
    https://mp.weixin.qq.com/s?__biz=Mzg2NzYyNjQzNg==&mid=2247484864&idx=1&sn=e25652505319d4d13dcf5fc8e265bcae&source=41#wechat_redirect
    b)分库分表,[唯一id，数据迁移，但是事务和复杂的查询到是没有详细的讲]
    https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2650995634&idx=1&sn=439ef5b9ab800417f4f4745df1c1fcd0&scene=21#wechat_redirect






    6:java面试相关的github：
    2.1)内容涵盖高并发、分布式、高可用、微服务、海量数据处理等领域知识
    https://github.com/aysaml/advanced-java
    2.2)就Hystrix介绍得比较不错,其他的不怎么行
    https://gitee.com/ckl111/Java-Interview-Advanced#%E7%AC%AC%E4%B8%80%E5%AD%A3-%E5%88%86%E5%B8%83%E5%BC%8F
    2.3)java面试涵盖得比较全面(包括Java基础,线上问题排查，Spring,redis，dubbo,mysql,系统设计)，敖丙三台子的搞的github
    https://github.com/AobingJava/JavaFamily
    2.4)JavaGuide的博客，主要有Java基础，特别是并发,JVM,Spring方面注重基础的面试总结
    https://github.com/Snailclimb/JavaGuide
    2.5)这个总结得也不错(基本上Java基础，计算机基础，JVM,中间件,spring等都总结得不错,以及还有刷题目)
    https://github.com/idaSmilence/javaP7/blob/master/JAVA.md



    7:可以学习java的github
    3.1)小傅哥(可以学习java基础的源码分析,Spring的源码分析，Netty的实战运用等)
    https://github.com/fuzhengwei/CodeGuide#netty-4x-%E4%B8%93%E9%A2%98



 *
 */
public class MianShiDemo1 {
}
