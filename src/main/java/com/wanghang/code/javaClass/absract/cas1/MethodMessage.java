package com.wanghang.code.javaClass.absract.cas1;


import com.wanghang.code.javaClass.absract.cas1.service.OrderService;

import java.util.Map;

public abstract class MethodMessage {

    protected final Map<String,Object> params;


    protected final OrderService orderService;


    public MethodMessage(Map<String,Object> params,OrderService orderService){
        this.orderService=orderService;
        this.params=params;
    }

    //抽象方法:
    public abstract void excute();
}
