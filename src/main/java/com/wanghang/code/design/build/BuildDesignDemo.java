package com.wanghang.code.design.build;


import com.wanghang.code.design.build.one.concreteBuilder.AndroidPhoneBuilder;
import com.wanghang.code.design.build.one.concreteBuilder.IMobilePhoneBuilder;
import com.wanghang.code.design.build.one.concreteBuilder.WindowsPhoneBuilder;
import com.wanghang.code.design.build.one.director.Manufacturer;


/**
 *1)构造者模式的定义:
 *              将复杂对象的构造和表示分离,相同的构造流程可以创建不同的表示。
 *        构造这模式：
 *                 https://github.com/wanghang88/core-spring-patterns/blob/master/ppts/%E6%9E%84%E5%BB%BA%E8%80%85.pdf
 *
 *
 *2)构造者模式中的四种角色:
 *ConcreteBuilder:负责创建复杂产品的具体类，知道每个零部件的构造细节，案例中的：IMobilePhoneBuilder,定义构造手机(Product)的主要接口;
 *Builder:创建实际产品的接口，案例中的AndroidPhoneBuilder和WindowsPhoneBuilder,具体实现创建手机(Product);
 *Director:定义创建流程，案例中的Manufacturer;
 *Product:组装出来最后的产品手机;
 *
 *3)构造者模式的主要运用场景：
 *a)复杂对象的构建;
 *b)多步构造流程/算法;
 *c)构造类似的产品，构造的流程不同，表示不同;
 *d)构建和表示分离
 *
 *4)构造模式在实际的运用：OkHttp
 *                    https://www.jianshu.com/p/e4705ee2311a
 *
 */
public class BuildDesignDemo {

    public static void main(String[] args) {
        // 先创建导演Director
        Manufacturer manufacturer = new Manufacturer();
        // 先准备Builder接口
        IMobilePhoneBuilder phoneBuilder = null;

        // 制造一部安卓手机
        phoneBuilder = new AndroidPhoneBuilder();
        manufacturer.construct(phoneBuilder);
        String output = String.format("A new Phone built:\n\n%s", phoneBuilder.getPhone().toString());
        System.out.println(output);


        // 制造一部Windows手机
        phoneBuilder = new WindowsPhoneBuilder();
        manufacturer.construct(phoneBuilder);
        output = String.format("A new Phone built:\n\n%s", phoneBuilder.getPhone().toString());
        System.out.println(output);
    }
}
