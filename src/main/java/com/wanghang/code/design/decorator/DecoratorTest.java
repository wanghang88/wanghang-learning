package com.wanghang.code.design.decorator;

import com.wanghang.code.design.decorator.Component.CakeBase;
import com.wanghang.code.design.decorator.Component.PastryBase;
import com.wanghang.code.design.decorator.ConcreteComponent.ArtificialScentDecorator;
import com.wanghang.code.design.decorator.Decorator.CherryDecorator;
import com.wanghang.code.design.decorator.Decorator.CreamDecorator;
import com.wanghang.code.design.decorator.Decorator.NameCardDecorator;


/**
 * Component
 * ConcreteComponent
 *
 * Decorator
 * ConcreteDecorator
 *
 *1)装饰者模式:
 *装饰模式的设计理念主要是以对客户端透明的方式动态扩展对象的功能，是继承关系的一个替代(继承会产生大量的子类，而且代码有冗余)
 * 装饰模式可以在不创造更多子类的情况下，将对象的功能加以扩展
 *
 *
 *
 *
 *
 *
 */
public class DecoratorTest {

    public static void main(String[] args) {
        // 先创建一个简单的Cake Base
        CakeBase cBase = new CakeBase();
        Util.printProductDetails(cBase);


        // 在蛋糕上添加奶油
        CreamDecorator creamCake = new CreamDecorator(cBase);
        Util.printProductDetails(creamCake);


        // 在蛋糕上添加樱桃
        CherryDecorator cherryCake = new CherryDecorator(creamCake);
        Util.printProductDetails(cherryCake);


        // 再添加香味
        ArtificialScentDecorator scentedCake = new ArtificialScentDecorator(cherryCake);
        Util.printProductDetails(scentedCake);


        // 最后在蛋糕上添加名片
        NameCardDecorator nameCardOnCake = new NameCardDecorator(scentedCake);
        Util.printProductDetails(nameCardOnCake);


        System.out.println("创建简单的糕点开始了");


        // 现在创建一个简单的糕点
        PastryBase pastry = new PastryBase();
        Util.printProductDetails(pastry);

        // 在糕点上只添加奶油和樱桃
        CreamDecorator creamPastry = new CreamDecorator(pastry);
        CherryDecorator cherryPastry = new CherryDecorator(creamPastry);
        Util.printProductDetails(cherryPastry);
    }
}
