package com.wanghang.code.innerClass;


/**
 *成员内部类：
 *
 *
 *
 */
public class MembersOut {

    //外部类的成员变量：
    private int outerVariable = 1;

    //外部类的静态成员变量：
    private static int outerStaticVariable = 3;

    //外部类和内部类用共同的变量:
    private int commonVariable = 2;


    /**
     * 成员方法
     */
    public void outerMethod() {
        System.out.println("我是外部类的outerMethod方法");
    }


    /**
     * 外部类的静态成员方法
     */
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }



    //成员变量内部类:
    class  MembersInner{
        private  int commonVariable = 19;

        public MembersInner(){
            super();
        }

        public void innerShow() {
            //当和外部类冲突时，直接引内部类的成员属性
            System.out.println("内部的commonVariable:" + commonVariable);
            //当和外部类属性名重叠时，可通过外部类名.this.属性名
            System.out.println("外部类同名的成员变量commonVariable:" + MembersOut.this.commonVariable);

            //内部类访问外部类的成员变量(包含静态)
            System.out.println("外部类的成员变量outerVariable:" + outerVariable);
            System.out.println("外部类的静态成员变量outerStaticVariable:" + outerStaticVariable);

            //内部类访问外部了的成员方法(包含静态)
            outerMethod();
            outerStaticMethod();
        }
    }


    //外部类访问内部类提供的方法:
    public void outerShow() {
        MembersInner membersInner = new MembersInner();
        membersInner.innerShow();
    }


    //外部类提供接口获取内部类的方法：
    public MembersInner getMembersInner(){
       return new MembersInner();
    }

}
