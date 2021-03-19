package com.wanghang.code.innerClass;


/**
 *静态内部类：
 */
public class StaticOut {


    //外部类成员变量
    private int outerVariable = 1;


    //外部类和内部类同名的成员变量
    private int commonVariable = 2;

    //外部类静态成员变量
    private static int outerStaticVariable = 3;

    //外部类的静态代码块
    static {
        System.out.println("外部类的静态代码块被执行了……");
    }




    //外部类的普通成员方法
    public void outerMothod() {
        System.out.println("我是外部类的outerMethod方法");
    }
    //外部了的静态成员方法
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }



    //静态内部类
    static class StaticInner{

        //内部类的成员 变量:
        private int innerVariable = 10;

        //内部类的成员变量和外部类同名:
        private int commonVariable = 20;

        //内部类中静态成员变量:
        private static int innerStaticVariable = 30;

        static {
            System.out.println("StaticOut.StaticInner内部静态类的静态代码块实行了……");
        }

        //内部静态类的成员方法：
        public void innerShow() {
            System.out.println("内部普通方法访问外部普通变量innerVariable:" + innerVariable);
            System.out.println("内部普通方法访问同名的变量commonVariable:" + commonVariable);
            System.out.println("内部普通方法访问外部静态变量outerStaticVariable:"+outerStaticVariable);
            outerStaticMethod();
        }

        //内部静态类的静态成员方法：
        public static void innerStaticShow() {
            //被调用时会先加载Outer类
            System.out.println("内部静态方法innerStaticShow()执行了");
            System.out.println("内部静态方法访问外部静态变量outerStaticVariable:"+outerStaticVariable);
            outerStaticMethod();
        }
    }


    //外部类获取静态内部类的方法
    public StaticOut.StaticInner  getStaticInner(){
        return new StaticOut.StaticInner();
    }






}
