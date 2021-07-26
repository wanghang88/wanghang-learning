package com.wanghang.code.javaClass.absract.cas1;

import com.wanghang.code.javaClass.absract.cas1.service.OrderService;

import java.util.Map;


/**
 *1:由于在抽象类中定义了两个成员属性,并且是final和protected修饰的，在抽象类中定义了一个构造函数。
 *2:那么子类在继承的时候,子类必须重写这个构造函数。
 */
public class FindOrderDetail  extends MethodMessage{


    public FindOrderDetail(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void excute() {

    }
}
