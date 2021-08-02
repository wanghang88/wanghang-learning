package com.wanghang.code.JDK.reflect;

import com.wanghang.code.JDK.reflect.annotation.MyAnnotation;
import com.wanghang.code.JDK.reflect.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 *java中的反射:
 *
 * a)java中创建对象的方式:
 * a.1)使用new关键字：这是我们最常见的也是最简单的创建对象的方式.
 * a.2)使用Clone的方法：无论何时我们调用一个对象的clone方法，JVM就会创建一个新的对象，将前面的对象的内容全部拷贝进去.
 *
 * b)java中的反射:
 *  反射就是把Java类中的各个组成部分进行解剖，并映射成一个个的Java对象，拿到这些对象后可以做一些事情,并且是在程序的运行中
 *  java类中的各组成部分:
 *                   一个类有：构造方法，方法，成员变量(字段)，等信息，利用反射技术咱们可以把这些组成部分映射成一个个对象.
 *                   拿到构造方法之后就可以用来生成对象, 拿到方法之后就可以执行方法,拿到字段后就可以获取它的值或者改变它的值。
 *  反射的作用:
 *           一般来说反射是用来做框架的，或者说可以做一些抽象度比较高的底层代码，反射在日常的开发中用到的不多，但是咱们还必须搞懂它，
 *              因为搞懂了反射以后，可以帮助咱们理解框架的一些原理。所以说有一句很经典的话：反射是框架设计的灵魂。
 *
 * c)反射参考的博文:
 * https://blog.csdn.net/ju_362204801/article/details/90578678
 * 基本涵盖了反射操作的api:
 * https://blog.csdn.net/qq_39209361/article/details/81239189
 * 反射与注解的结合使用:
 * https://blog.csdn.net/ju_362204801/article/details/90697479
 */
public class ReflectDemo {


    public static void main(String[] args) throws Exception {
       // test1();

      //  test2();


      //  test3();

      //  test4();

      // test5();

      // test6();

        test7();

    }
    /**
     *a)利用反射机制可以获取类对象,有三种方法(也就是我们前面介绍的类对象，获取类对象之后我们便获取了类的模板，可以对类进行一些操作)。
     * 1.类名.class()
     * 2.对象名.getClass()
     * 3.Class.forName(具体的类名)
     * 在类加载的三个阶段里都可以获取类对象，其中第三种方法，在源码中获取类对象是最常用的，也是反射机制在框架中的应用
     */
    private static void test1() throws ClassNotFoundException {
        Class clz = User.class;
        System.out.println(clz);

        Class clz1 = new User().getClass();
        System.out.println(clz==clz1);

        Class clz2 = Class.forName("com.wanghang.code.JDK.reflect.bean.User");
        System.out.println(clz==clz2);
    }


    /**
     *b)
     *获取类对象之后就可以对类进行一些创建对象、调用方法、访问成员变量的操作了：
     *创建对象
     * Object obj = 类对象.newInstance();
     */
    private static void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");
        System.out.println(clz);


        Object obj = clz.newInstance();
        System.out.println(obj);
    }


    /**
     *c)调用方法：
     * Method md = 类对象.getMethod("methodName");
     * Method lm = 类对象.getMethod("methodName",参数的类对象...);
     *
     *
     *
     * 注意：
     * 1)类对象.getMethod(), 方法只能获取public修饰的方法,
     * 2)类对象.getMethod()获取制定的方法是根据  方法名和指定的参数类型来或取的，不然会报错,   Method lm = 类对象.getMethod("有参方法方法名",参数的类对象...);
     * 3)invoke()方法的执行, 必须要一个对象的实例和对应的参数, 不然会执行报错。
     * 4)invoke()执行后返回的是Object对像,和原来方法的返回值是一致的。 Object obj1 = lm.invoke(类的对象,"参数1","参数2");
     *
     *
     */
    private static void test3() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user2 = new User("wanghang",20);


        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");

        //根据方法名exit获取对应的方法
        Method em = clz.getMethod("exit");
        //执行exit()方法
        Object obj = em.invoke(user2);
        System.out.println(obj);        //由于exit()方法没有返回值,所以执行exit()方法后返回值obj是null

        //根据方法名login和参数String,String获取login方法
        Method lm = clz.getMethod("login",String.class,String.class);
        Object obj1 = lm.invoke(user2,"老赵","aixiaoba");
        System.out.println(obj1);      //由于执行login()方法没有返回值,也是执行login()方法返回值obj1也是null


        Method getUserNameMethod = clz.getMethod("getUserName", String.class);

        Object getUserNameMethodInvoke1 = getUserNameMethod.invoke(user2,"wanghang1");
        System.out.println(getUserNameMethodInvoke1);
        Object getUserNameMethodInvoke2 = getUserNameMethod.invoke(user2,"wanghang2");
        System.out.println(getUserNameMethodInvoke2);
    }


    /**
     *
     * 获取私有方法
     * 规则和获取共有方法一样,执行方法也是和公有方法一致
     * Method dm = 类对象.getDeclaredMethod("methodName");
     *
     * 不过要执行这个私有方法的话则需要：
     * dm.setAccessible(true);
     */
    private static void test4() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u2 = new User("老赵",20);

        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");

        Method dm = clz.getDeclaredMethod("CheckInfo");

        dm.setAccessible(true);
        Object obj = dm.invoke(u2);
        System.out.println(obj);
    }


    /**
     * 访问共有(public)字段Field
     *Field nf = 类对象.getField("filedName");
     *
     * User的name是public属性
     */
    private static void test5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");
        Object obj = clz.newInstance();
        Field nf = clz.getField("name");
        Object object1 = nf.get(obj);

        System.out.println("获取字段后没有设置值获取为:"+object1);

        nf.set(obj, "wanghang");

        Object object2 = nf.get(obj);
        System.out.println("获取字段后设置值获取为:"+object2);
    }


    /**
     *访问私有字段(private)字段Field
     *
     * Field af = 类对象.getDeclaredField("fileName");
     * 要访问的话需要设置
     * af.setAccessible(true);
     *
     * User的age是private属性
     */
    private static void test6() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");
        Object obj = clz.newInstance();

        Field nf = clz.getDeclaredField("age");
        nf.setAccessible(true);

        Object object1 = nf.get(obj);
        System.out.println("没有设置age字段的值获取的结果为:"+object1);

        nf.set(obj, 100);
        Object object2= nf.get(obj);
        System.out.println("设置age字段的值获取的结果为:"+object2);

    }


    /**
     * 反射加注解：
     * 注解会在class字节码文件中存在，在运行时可以通过反射获取到
     *
     */
    private static void test7() throws ClassNotFoundException {
        Class clz = Class.forName("com.wanghang.code.JDK.reflect.bean.User");
        Method[] methods = clz.getMethods();

        IntStream.range(0,methods.length).forEach(index->{
            if(methods[index].isAnnotationPresent(MyAnnotation.class)){
                int age = methods[index].getAnnotation(MyAnnotation.class).age();
                String name = methods[index].getAnnotation(MyAnnotation.class).name();
                try {
                    //1:构造函数实例话对象
                    Constructor userConstructor = clz.getDeclaredConstructor(String.class, int.class);
                    userConstructor.setAccessible(true);
                    Object object = userConstructor.newInstance(name, age);

                    //2:执行方法,因为这个方法执行不需要参数
                    Object invokeReslut = methods[index].invoke(object);
                    System.out.println("执行的方法名为:"+methods[index].getName()+"执行的结果为:"+invokeReslut);
                } catch (NoSuchMethodException e) {

                } catch (IllegalAccessException e) {

                } catch (InstantiationException e) {

                } catch (InvocationTargetException e) {

                }
            }
        });

    }

}
