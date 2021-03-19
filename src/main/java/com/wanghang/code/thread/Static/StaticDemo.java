package com.wanghang.code.thread.Static;




/**
 *关于java 中的static关键字：
 *
 * 1)生命周期：
 *   用static声明的成员变量为静态成员变量，也成为类变量。类变量的生命周期和类相同，在整个应用程序执行期间都有效
 *   a)static修饰的成员变量和方法，从属于类,非实例对象，在JVM加载类时，就已经存在内存中，不会被虚拟机GC回收掉，这样内存负荷会很大),但是非static方法会在运行完毕后被虚拟机GC掉，减轻内存压力.
 *   b)普通变量和方法从属于对象;
 *   c)静态方法不能调用非静态成员，编译会报错(但是,普通的方法可以访问静态的成员变量和静态的成员方法)
 *
 * 2)用途：
 *      方便在没有创建对象的情况下进行调用(方法/变量);
 *      static关键字修饰的方法或者变量不需要依赖于对象来进行访问，只要类被加载了，就可以通过类名去进行访问;
 *      static可以用来修饰类的成员方法、类的成员变量，另外也可以编写static代码块来优化程序性能(类的成员变量或者方法,或者类的静态代码块)
 *
 *
 *3)关于static修饰变量,方法,代码块
 *   3.1)方法：static方法是属于类的，非实例对象，在JVM加载类时，就已经存在内存中，不会被虚拟机GC回收掉，这样内存负荷会很大，
 *            但是非static方法会在运行完毕后被虚拟机GC掉，减轻内存压力
 *
 *  3.2)变量：static成员变量初始化顺序按照定义的顺序来进行初始化
 *          静态变量被所有对象共享，在内存中只有一个副本，在类初次加载的时候才会初始化;
 *          非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响;
 *
 *       可以通过类直接访问静态的成员变量(变量和方法),当然也可以通过对象访问类的成员变量和成员方法；
 *
 *
 *
 *
 *
 *  3.3)static块:
 *          静态代码块，用于类的初始化操作,而构造方法用于对象的初始化。
 *          静态代码块中不能直接访问非staic成员
 *          静态代码块使用场景：很多时候会将一些只需要进行一次的初始化操作都放在static代码块中进行，静态初始化块可以置于类中的任何地方
 *                          在类初次被加载时，会按照静态初始化块的顺序来执行每个块，并且只会执行一次
 *
 * 3.4)静态代码块：
 *      类在初始化的时候，如果有静态代码块,则会限制性静态代码块，如果有继承，则还会执行父类的静态代码块，再执行自身的静态代码块。
 *
 *
 *
 *
 *
 *
 */



public class StaticDemo {


    private static String name="张三";
    private String address="李四";

    public static void main(String[] args) {

        //1:类的静态方法，直接通过类调用，不需要创建对象
        StaticDemo.staticDemoTest2();

        //1.1：普通方法,必须要通过对象去调用
        StaticDemo staticDemo=new StaticDemo();
        staticDemo.staticDemoTest1();




        //2:static变量
        staticDemo.printValue();
        staticDemo.staticDemoTest2();

    }

    //1:非静态的成员方法访问，类的静态成员变量或者静态方法
    public void staticDemoTest1(){
        System.out.println(name);
        System.out.println(address);
        staticDemoTest2();
    }

    //2:静态的方法访问普通的成员变量或者成员方法，则会报错
    // static方法是属于类的，非实例对象，在JVM加载类时，就已经存在内存中，不会被虚拟机GC回收掉，这样内存负荷会很大，但是非static方法会在运行完毕后被虚拟机GC掉，减轻内存压力
    public static void staticDemoTest2(){
        System.out.println(name);
    //    System.out.println(address);
    //    staticDemoTest1();
    }



    static int value = 33;
    private void printValue(){
        int value = 3;
        System.out.println(this.value);    //// TODO:这个地方为什么能用this访问呢？
        System.out.println(this);
        System.out.println(StaticDemo.value);
    }
}


class Test {
    Person person = new Person("Test");
    static{
        System.out.println("test static");
    }

    public Test() {
        System.out.println("test constructor");
    }


    //初始化MyClass类：MyClass继承test类
    public static void main(String[] args) {
        //执行这个main方法之前先要加载Test类：
        //1：先加载Test类的静态代码块：test static

        //然后执行new MyClass(),先加载MyClass类，发现MyClass类继承Test类，而是要先加载Test类，Test类之前已加载
        //2：加载MyClass类的静态代码块：myclass static

        //因为是通过new MyClass()对象，然后调用MyClass类的构造器生成对象，因为MyClass继承Test,需要初始化父类Test，以及初始化父类的成员变量new Person("Test")
        //父类Test以及其成员变量person的初始化：

        //3：执行person的静态代码块：person static
        //4：因为成员变量是new Person(),所以在Test类中需要执行Person的构造函数：person Test
        //5：调用父类Test构造器，完成Test类的初始化：test constructor

        //MyClass类自身以及其成员变量Person的初始化：
        //6：MyClass类中的成员变量Person的初始化,因为person的静态代码已经加载，只需要执行构造函数就可以了：person MyClass
        //7：最后调用MyClass的类构造器,完成myClass的初始化：myclass constructor

        new MyClass();
    }
}



class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}

class MyClass extends Test {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}
