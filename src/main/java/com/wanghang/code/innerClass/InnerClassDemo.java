package com.wanghang.code.innerClass;


/**
 *1)内部类：
 *      https://blog.csdn.net/weixin_42762133/article/details/82890555
 *
 *
 *       将一个类的定义放在里另一个类的内部，这就是内部类，广义上我们将内部类分为四种：成员内部类、静态内部类、局部（方法）内部类、匿名内部类。
 *
 *2)为啥要有内部类？
 *      每个内部类都能独立地继承或者实现接口，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。——《Think in java》
 *      也就是说内部类拥有类的基本特征，可以继承父类，实现接口。
 *      在实际问题中我们会遇到一些接口无法解决或难以解决的问题，此时我们可以使用内部类继承某个具体的或抽象的类，间接解决类无法多继承的问题。
 *
 * 3)内部类的好吃？
 *     内部类提供了更好的封装，除了该外围类，其他类都不能访问。
 *     创建内部类对象的时刻并不依赖于外围类对象的创建
 *
 *
 */

public class InnerClassDemo {

    public static void main(String[] args) {
        InnerClassDemo innerClassDemo=new InnerClassDemo();


        // innerClassDemo.membersInner();    //成员内部类

        innerClassDemo.staticOut();         //成员静态内部类
    }



    /**
     *1:成员内部类：
     *  a)内部类的内部不能有静态信息;
     *  b)内部类也是类,该继承继承，该重写重写，该重载重载，this和super随便用;
     *  c)外部类可以访问内部类的信息( MembersOut.MembersInner membersInner=new MembersInner()的方式);
     *  d)内部类可以使用外部类的任何信(成员变量或者成员方法,静态的成员变量和成员方法也可以);
     *  e)其他类在获取到外部类对象之后，就可以访问这个类的内部类了;
     */
    private void membersInner() {
        MembersOut membersOut=new MembersOut();
        MembersOut.MembersInner membersInner = membersOut.getMembersInner();

        membersInner.innerShow();
    }


    /**
     *2:静态内部类：
     * a)静态内部类的方法只能访问外部类的static关联的信息;
     * b)外部类访问内部静态中静态的信息的方式,直接外部类.内部类.静态信息就可以了(StaticOut.StaticInner.innerStaticShow());
     * c)外部类访问内部静态类中的普通成员变量或者成员方法，外部类.内部类 引用=new 外部类.内部类(),然后利用引用.成员信息(属性、方法)调用(new StaticOut.StaticInner().innerShow());
     * d)静态内部类可以独立存在，不依赖于其他外围类;
     */
    private void staticOut() {
        //1:外部类访问静态类中的静态方法，StaticInner类被加载,此时外部类未被加载，独立存在，不依赖于外部类
        StaticOut.StaticInner.innerStaticShow();

        //2:外部类访问静态内部类中的普通方法
        StaticOut staticOut=new StaticOut();
        staticOut.getStaticInner().innerShow();
    }

}
