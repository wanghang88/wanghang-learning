package com.wanghang.code.MianShi;

/**
 *关于Java面试的总结:
 *
  1:java基础部：
  1.1)HashMap的原理？hashmap为啥用红黑树来树化链表，为啥不是B+树？
  1.2)ConcurrentHashMap底层原理，如何保证线程安全的
  1.3)如果让你设计一个数组结构，数组怎么扩容
  1.4)CAS？
        CAS（compareAndSwap）也叫比较交换，是一种无锁原子算法。它包含3个参数CAS(V，E，N),
        V表示待更新的内存值(从内存中获取到的值)，E表示预期值(原值)，N表示新值(更新后的值)，当 V值等于E值时，才会将V值更新成N值，如果V值和E值不等，不做更新，这就是一次CAS的操作,
        Java中CAS操作的执行依赖于Unsafe类的方法.
        CAS也会产生问题(缺点):
                          只能保证一个共享变量的原子操作,只能针对一个共享变量使用，如果多个共享变量就只能使用锁了(如何把多个变量变成一个变量)
                          自旋时间太长，当一个线程获取锁时失败，不进行阻塞挂起，而是间隔一段时间再次尝试获取，直到成功为止，线程在长时间内持有锁，等待竞争锁的线程一直自旋，即CPU一直空转，资源浪费在毫无意义的地方，所以一般会限制自旋次数。
                          ABA问题：CAS需要检查待更新的内存值有没有被修改，如果没有则更新，但是存在这样一种情况，如果一个值原来是A，变成了B，然后又变成了A，在CAS检查的时候会发现没有被修改。
                                  只是简单的数据结构，确实不会有什么问题，如果是复杂的数据结构可能就会有问题了，要解决ABA问题也非常简单，只要追加版本号即可，每次改变时加1(在Java中提供了AtomicStampedRdference可以实现这个方案).


  1.5)AQS？
  1.6)锁synchronized与ReentrantLock
                 Synchronized一直都是元老级的角色，Jdk 1.6以前大家都称呼它为重量级锁，相对于JUC包提供的Lock，它会显得笨重，不过随着Jdk1.6对Synchronized进行各种优化后，Synchronized性能已经非常快了
                 Synchronized的原理:
                                  Synchronization)基于进入和退出管程(Monitor)对象实现,monitor对象存在于每个Java对象的对象头中(存储的指针的指向),



  1.7)volatile关键字原理
    volatile修饰的实例变量或者类变量具备如下两层语义:
                                             保证了不同线程之间对共享变量操作时的可见性，也就是说当一个线程修改volatile修饰的变量，另外一个线程会理解看到最新的值,
                                             禁止对指令进行重排序。
  1.8)ThreadLocal的理解?

  1.9)ReentrantLock的实现原理？公平锁非公平锁
  1.10)线程池？
  1.11)TCP 和 UDP 的区别?
       UDP协议和TCP协议都是传输层协议
       TCP:提供的是面向连接，可靠的字节流服务。即客户和服务器交换数据前，必须现在双方之间建立一个TCP连接，之后才能传输数据。并且提供超时重发，丢弃重复数据，检验数据,保证数据能从一端传到另一端。
       UDP:是一个简单的面向数据报的运输层协议。它不提供可靠性，只是把应用程序传给IP层的数据报发送出去，但是不能保证它们能到达目的地,由于UDP在传输数据报前不用再客户和服务器之间建立一个连接，且没有超时重发等机制，所以传输速度很快。
       TCP面向连接，可靠，基于字节流，而UDP不面向连接，不可靠，基于数据报;
  1.12)http协议的三次回收和四次握手？







 *
 *
 *2)JVM相关:
   2.1)java类是如何加载到JVM的？

   2.1)JVM运行时内存区域?
       线程私有:  虚拟机栈，生命周期与线程相同，每个方法执行的时候，虚拟机都会为方法创建一个栈帧将其压入虚拟机栈，栈帧包括局部变量表，操作数栈，动态链接，方法出口，等其他信息。
                程序计数器，线程执行的字节码的行号指示器。
                本地方法栈
       线程共享:  堆,线程共享，是内存管理的主要区域,代大多数的垃圾收集器都是基于分代理论，所以会出现新生代，老年代，Eden空间，From区域，To区域，
                   当线程在堆中分配对象的时候是分配在线程私有的TLAB缓冲区域java堆可以通过虚拟机参数-Xms和-Xmx来设置，当java堆无法扩展,会报OOM。
                 方法区:方法区主要存放的是一些加载的类型信息，常量，静态变量等。
                       以前永久代的概念就是方法区的实现使得垃圾收集器像管理堆内存一样管理方法区，但是这样更容易遇到内存溢出的问题,内存大小受限于JVM对运行时数据区的内存分配，
                       在JDK8时候将永久代的实现理念替换为元空间,使用计算机本身的内存。这部分也有垃圾回收，主要针对的是常量池的垃圾回收和类型卸载。
                 元空间:元空间主要用来储存类的元数据，元数据包括类、字段、方法定义及其他信息。类和它的元数据的生命周期是和它的类加载器的生命周期一致的。也就是说，只要类的类加载器是存活的，在Metaspace中的类元数据也是存活的，不能被释放。
                 直接内存:dk4的时候出现NIO基于通道与缓冲区的I/O方式,操作堆外内存通过存储在堆里面的一个对象(Buffer)引用对这块内存进行操作。
                 运行时常量池:是方法区的一部分,用于存放编译期生成的各种字面量和符号引用,当常量池无法再申请到内存时会抛出OutOfMemoryError。



 2.1)线上频繁fullGC怎么解决？
   2.2)Java虚拟机内存划分，GC回收算法？
   2.3)内存溢出排查,OOM场景分析？
   2.4)线上CPU很高怎么排查?
   2.5)常见的JVM参数设置？
        Xms：初始分配的内，默认是物理内存的 1/64,
        -Xmx：最大分配的内存由,默认是物理内存的 1/4,
        默认空余堆内存小于 40% 时，JVM 就会增大堆直到-Xmx 的最大限制；空余堆内存大于 70% 时，JVM 会减少堆直到 -Xms 的最小限制,因此服务器一般设置-Xms、-Xmx 相等以避免在每次 GC 后调整堆的大小.
        -Xmn2G：设置年轻代大小为 2G
        -XX:SurvivorRatio，设置年轻代中 Eden 区与 Survivor 区的比值。
        -XX:PretenureSizeThreadhold,指令大于这个参数值的对象直接在老年代中分配
        -XX:MaxTenuringThreshold:对象晋升老年代的年龄阈值，默认为15岁，在年轻代每经过一次Minor GC，年龄就增加1岁，增加到一定程度将会晋升到老年代。
        -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息。

   2.6)GC 什么时候开始？
        Minor GC:
        GC 经常发生的区域是堆区，堆区还可以细分为新生代、老年代，新生代还分为一个 Eden 区和两个 Survivor 区。
        对象优先在 Eden 中分配，当 Eden 中没有足够空间时，虚拟机将发生一次 Minor GC，因为 Java 大多数对象都是朝生夕灭，所以 Minor GC 非常频繁，而且速度也很快；

        Full GC:
        Full GC，发生在老年代的 GC，当老年代没有足够的空间时即发生 Full GC，发生 Full GC,
        发生 Minor GC 时，虚拟机会检测之前每次晋升到老年代的平均大小是否大于老年代的剩余空间大小，如果大于，则进行一次 Full GC，如果小于，则查看 HandlePromotionFailure 设置是否允许担保失败，如果允许，那只会进行一次 Minor GC，如果不允许，则改为进行一次 Full GC。
        大对象直接进入老年代，如很长的字符串数组，虚拟机提供一个；XX:PretenureSizeThreadhold 参数，令大于这个参数值的对象直接在老年代中分配，避免在 Eden 区和两个 Survivor 区发生大量的内存拷贝

    2.7)jvm有哪几种垃圾回收器，各自的应用场景?
    2.8)JVM调优思路？以及调优的参数






 * 3)框架相关(Spring,SpringBoot,Springmvc,mybatis,SpringCloud,mybatis)
    3.1)spring的IOC和AOP？
               IOC:IOC叫做控制反转，指的是通过Spring来管理对象的创建、配置和生命周期，这样相当于把控制权交给了Spring，不需要人工来管理对象之间复杂的依赖关系，这样做的好处就是解耦。
                  在Spring里面，主要提供了 BeanFactory 和 ApplicationContext 两种 IOC 容器，通过他们来实现对 Bean 的管理。
               AOP:叫做面向切面编程,基于动态代理的方式实现，如果是实现了接口的话就会使用 JDK 动态代理，反之则使用CGLIB代理，Spring中AOP 的应用主要体现在 事务、日志、异常处理等方面,通过在代码的前后做一些增强处理,
                  可以实现对业务逻辑的隔离，提高代码的模块化能力，同时也是解耦.



    3.2)spring Bean的生命周期？
             Spring容器 从XML 文件中读取bean的定义，并实例化bean。
             Spring根据bean的定义填充所有的属性。
             如果bean实现了BeanNameAware 接口，Spring 传递bean 的ID 到 setBeanName方法。
             如果Bean 实现了 BeanFactoryAware 接口， Spring传递beanfactory 给setBeanFactory 方法。
             如果有任何与bean相关联的BeanPostProcessors，Spring会在postProcesserBeforeInitialization()方法内调用它们。
             如果bean实现IntializingBean了，调用它的afterPropertySet方法，如果bean声明了初始化方法，调用此初始化方法。
             如果有BeanPostProcessors 和bean 关联，这些bean的postProcessAfterInitialization() 方法将被调用。
             如果bean实现了 DisposableBean，它将调用destroy()方法。

   3.3)FactoryBean 和 BeanFactory区别?


   3.5)spring事务？

   3.6)Spring Mvc的原理？
   3.7)springBoot的原理？
   3.8)Spring cloud相关:
       spring cloud的核心组件有哪些?
                Eureka:服务注册于发现,
                Feign：基于动态代理机制,根据注解和选择的机器,拼接请求 url 地址，发起请求,
                Ribbon：实现负载均衡，从一个服务的多台机器中选择一台,
                Zuul：网关管理，由 Zuul 网关转发请求给对应的服务,
                Hystrix：提供线程池，不同的服务走不同的线程池，实现了不同服务调用的隔离，避免了服务雪崩的问题。
      什么是feigin？它的优点是什么？以及与Ribbon的区别？
                feign采用的是基于接口的注解,
                feign整合了ribbon，具有负载均衡的能力,
                整合了Hystrix，具有熔断的能力。
                feigin的使用：添加pom依赖，启动类添加@EnableFeignClients，定义一个接口@FeignClient(name=“xxx”)指定调用哪个服务。
                区别：
                feigin与Ribbon都是对微服务接口的调用，但是方式不同
                启动类注解不同，Ribbon是@RibbonClient feign的是@EnableFeignClients,
                服务指定的位置不同，Ribbon是在@RibbonClient注解上声明，Feign则是在定义抽象方法的接口中使用@FeignClient声明,
                调用方式不同，Ribbon需要自己构建http请求，模拟http请求然后使用RestTemplate发送给其他服务，步骤相当繁琐。Feign需要将调用的方法定义成抽象方法即可.

       什么是Hystrix?
                 防雪崩利器，具备服务降级，服务熔断，依赖隔离，监控等功能，其中服务降级,则是通过HystrixCommand注解指定fallbackMethod(回退函数)中具体实现降级逻辑。
                 在复杂的分布式系统中,微服务之间的相互调用,有可能出现各种各样的原因导致服务的阻塞,在高并发场景下,服务的阻塞意味着线程的阻塞,导致当前线程不可用,服务器的线程全部阻塞,导致服务器崩溃,
                 由于服务之间的调用关系是同步的,会对整个微服务系统造成服务雪崩,为了解决某个微服务的调用响应时间过长或者不可用进而占用越来越多的系统资源引起雪崩效应就需要进行服务熔断和服务降级处理.
                 服务熔断指:某个服务故障或异常会触发熔断机制，类似于保险丝，通过维护一个自己的线程池,当线程达到阈值的时候就启动服务降级,如果其他请求继续访问就直接返回fallback的默认值。

       Eureka如何实现？以及Eureka和ZooKeeper作为服务注册与发现的区别？以及Eureka的容错机制？
                 Eureka如何实现废物注册：
                                     服务发布时，指定对应的服务名,将服务注册到 注册中心(eureka zookeeper);
                                     注册中心加@EnableEurekaServer,服务用@EnableDiscoveryClient，然后用ribbon或feign进行服务直接的调用发现。
                 Eureka和ZooKeeperd的区:
                                       ZooKeeper保证的是CP,Eureka保证的是AP;
                                       ZooKeeper在选举期间注册服务瘫痪,虽然服务最终会恢复,但是选举期间不可用的
                                       Eureka各个节点是平等关系,只要有一台Eureka就可以保证服务可用,Eureka可以很好的应对因网络故障导致部分节点失去联系的情况,而不会像ZooKeeper一样使得整个注册系统瘫痪。

                 Eureka的容错机制(保护高可用)：









 * 4)关于中间件Redis,rabbitMq,Ngnix,ZooKeeper,ES,doobu,netty,kafka,RocketMq,mysql,RPC)

 *       a)redis:
                 redis缓存一致性问题如何解决?
                     对于并发量低的系统采取,先删除缓存然后再更新数据库。

 *        b)mq:
              mq怎么保证消息可靠性？





 *       c)netty:
               1)netty启动过程
               2)netty的线程模型，有几种线程池
               3)说到boss线程和work线程时，问了netty新连接接入的过程，怎么从boss线程转到word线程
               4)pipeline的组成元素，主要组件，IO事件传播顺序
               5)netty如何解决粘包拆包问题



 *       d)RPC:
               rpc框架实现原理？








 *       e)mysql:










 *
 *5)系统设计，分布式系统：
 * 1)如何实现分布式事务？
 * 2)维服务如何保证系统安全？
   3)维服务中如何实现Session共享？
      在微服务中，一个完整的项目被拆分成多个不相同的独立的服务，各个服务独立部署在不同的服务器上，各自的 session 被从物理空间上隔离开了，但是经常，我们需要在不同微服务之间共享session
      常见的方案就是 Spring Session + Redis 来实现 session 共享。
      将所有微服务的 session 统一保存在 Redis 上，当各个微服务对 session 有相关的读写操作时，
      都去操作 Redis 上的 session 。这样就实现了 session 共享，Spring Session 基于 Spring 中的代理过滤器实现，使得 session 的同步操作对开发人员而言是透明的，非常简便。
      session 共享大家可以参考：Spring Boot 一个依赖搞定 session 共享，没有比这更简单的方案了!

   4)分库分表怎么做？基于什么维度去做？
   5)设计一个抢红包系统?
   6)设计一个高可用高并发的电商系统?













 *6)系统运维以及DevOps以及Docker,K8s






 *7)程序设计，代码优化(项目分层啊,DDD领域设计,java设计模式)？
 *
 *
 */

public class MianShiDemo {
}
