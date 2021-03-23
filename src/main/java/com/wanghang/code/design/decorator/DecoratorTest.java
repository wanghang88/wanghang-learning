package com.wanghang.code.design.decorator;

import com.wanghang.code.design.decorator.Decorator.ArtificialScentDecorator;
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
