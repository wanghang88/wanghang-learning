package com.wanghang.code.JDK.reflect;


import com.wanghang.code.JDK.reflect.annotation.MyAnnotation;
import com.wanghang.code.JDK.reflect.bean.User;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

/**
 1)Reflections介绍：
 研究Spring扫包原理的时候，在网上查阅相关资料的时候，发现使用Reflections库可以实现扫包。
 Reflections 通过扫描 classpath，索引元数据，允许在运行时查询这些元数据，
 也可以保存收集项目中多个模块的元数据信息，值得一提的是，这东西在扫描的时候，连依赖的jar包都不放过。
 并且：Reflections 依赖 Google 的 Guava 库和 Javassist 库。

 2)Reflections功能：
  2.1)获得某个类型的所有子类型,
 2.2)获得标记了某个注解的所有类型／成员变量，支持注解参数匹配,
 2.3)使用正则表达式获得所有匹配的资源文件,
 2.4)获得所有特定（包括参数，参数注解，返回值）的方法.


 3)参考博文:
 https://blog.csdn.net/weixin_41231928/article/details/103400865

 */
public class ReflectionsDemo {

    public static void main(String[] args) {
        // 实例化Reflections，并指定要扫描的包名
        Reflections reflections = new Reflections("com.wanghang.code");

        // 获取某个类的所有子类
        Set<Class<? extends User>> subTypes = reflections.getSubTypesOf(User.class);

        // 获取包含某个注解的所有类 (todo, 当然我们这个注解是在方法上的，用在这个地方是不合适)
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyAnnotation.class);

        //ResourcesScanner 扫描资源
        Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));

        //MethodAnnotationsScanner 扫描方法（构造方法）注解,(TODO:当然这个注解放在这也是不对的)
        Set<Method> resources = reflections.getMethodsAnnotatedWith(MyAnnotation.class);
        Set<Constructor> injectables = reflections.getConstructorsAnnotatedWith(MyAnnotation.class);


        //FieldAnnotationsScanner 扫描字段注解
        Set<Field> ids = reflections.getFieldsAnnotatedWith(MyAnnotation.class);


        //MethodParameterScanner 扫描方法参数
        Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
        Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
      //  Set<Method> pathParamMethods = reflections.getMethodsWithAnyParamAnnotated(PathParam.class);

    }
}
