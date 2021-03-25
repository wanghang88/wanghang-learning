package com.wanghang.code.thread.Final;


import com.wanghang.code.thread.threadpool.cas1.Job;
import lombok.Data;

/**
 *
 *final域的修饰可以参考：
 * https://yuanyu.blog.csdn.net/article/details/104091625
 *
 *java中的fianl关键字：
 * 1)final修饰方法的参数: 基本变量和引用变量？
 *    final参数,当函数参数为final类型时，你可以读取使用该参数，但是无法改变该参数的值,
 *             注：父类的private成员方法是不能被子类方法覆盖的，因此private类型的方法默认是final类型的。 final不能用于修饰构造方法。
 *
 * 2)final类不能被继承:
 *             因此final类的成员方法没有机会被覆盖，默认都是final的。在设计类时候，
 *                如果这个类不需要有子类，类的实现细节不允许改变，并且确信这个类不会再被扩展，那么就设计为final类
 *
 *3)final方法:如果一个类不允许其子类覆盖某个方法，则可以把这个方法声明为final方法,使用final方法的原因有二;
 *            a)把方法锁定，防止任何继承类修改它的意义和实现;
 *            b)第二、高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。
 *
 *
 *4)final变量(常量),一般为类的成员变量，用final修饰的成员变量表示常量，只能被赋值一次，赋值后值无法改变，一般有三种：
 *                静态变量：
 *                实例变量：
 *                局部变量：
 *            另外，final变量定义的时候，可以先声明，而不给初值，这种变量也称为final空白，无论什么情况，
 *                   编译器都确保空白final在使用之前必须被初始化；
 *                一个类中的final数据成员就可以实现依对象而有所不同，却有保持其恒定不变的特征；
 *
 *
 *5)final的内存语意：
 *               1)final域的重排序规则:
 *                   a)在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序(例如：一个类中有final的成员,
 *                                             随后把这个对象的引用赋值给另一个引用变量,那么这两个操作是不可以重排序的)。
 *                    JMM的规范：
 *                            1)JMM禁止编译器把final域的写重排序到构造函数之外。
 *                            2)编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。
 *                              这个屏障禁止处理器把final域的写重排序到构造函数之外。
 *                              写final域的重排序规则可以确保：在对象引用为任意线程可见之前，对象的final域已经被正确初始化过了，而普通域不具有这个保障
 *
 *                    b)初次读一个包含final域的对象的引用，与随后初次读这个final域，这两个操作之间不能重排序(这个规则仅仅针对处理器);
 *                             1)编译器会在读final域操作的前面插入一个LoadLoad屏障。初次读对象引用与初次读该对象包含的final域，这两个操作之间存在间接依赖关系,
 *                                 由于编译器遵守间接依赖关系，因此编译器不会重排序这两个操作,大多数处理器也会遵守间接依赖，也不会重排序这两个操作。
 *                                 但有少数处理器允许对存在间接依赖关系的操作做重排序(比如alpha处理器),这个规则就是专门用来针对这种处理器的;
 *
 *                              2)读final域的重排序规则可以确保：在读一个对象的final域之前，一定会先读包含这个final域的对象的引用;
 *
 *               2)final域为引用类型,
 *                                对于引用类型，写final域的重排序规则对编译器和处理器增加了如下约束:
 *                                  在构造函数内对一个final引用的对象的成员域的写入，与随后在构造函数外把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序,
 *                                  这一规则确保了其他线程能读到被正确初始化的final引用对象的成员域。
 *6:java中类的初始化;
 *
 */
public class FinalDemo {

    private final Integer age;

    private final Integer age1=200;       //这个已经赋值了

    /*
       构造函数只能有下面这个，这个构造函数没能给age赋值
       public FinalDemo(){
         super();
       }
    */

    public FinalDemo(Integer age){
        this.age=age;
    }


    public static void main(String[] args) {
        FinalDemo finalDemo=new FinalDemo(100);


    //  finalDemo.modifiedMethodParameter(3);
        Job job=new Job();
        job.setJobId(100);
        job.setJobName("wanghang100");
        finalDemo.modifiedMethodParameter2(job);



       //3:演示含有继承的类的初始化：
        Employees employees=new Employees();
    }


    //1:final修饰方法的参数,如果是基础变量的话,其值是不能概连的;
    private void modifiedMethodParameter(final Integer i) {
        System.out.println("i:"+i);
       // i++;
    }

    //1:final修饰方法的参数,如果是引用变量的话,这个引用变量的指针是不能变的;
    private void modifiedMethodParameter2(final Job job) {
        System.out.println(job);

        job.setJobId(200);
        job.setJobName("wanghang200");
        System.out.println(job);
    }




}

@Data
class Person{
    private String name;
    private Integer age;

    private Job job=new Job();

    //无参构造
    public Person(){
        super();
        System.out.println("person的成员变量job已经完成初始化了:"+job);
        System.out.println("person被初始化了");
    }

    public Person(String name,Integer age){
        this.age=age;
        this.name=name;
    }
}

@Data
class Employees extends Person{

    private String position;
    private String company;

    public Employees(){
        super();
        System.out.println("Employees被初始化了");
    }

    public Employees(String position,String company){
        this.company=company;
        this.position=position;
    }
}
