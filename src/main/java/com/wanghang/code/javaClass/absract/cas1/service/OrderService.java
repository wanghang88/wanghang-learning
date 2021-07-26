package com.wanghang.code.javaClass.absract.cas1.service;


import com.wanghang.code.javaClass.absract.cas1.bean.OrderVo;

/**
 * 订单相关的接口
 */
public interface OrderService {

    /**
     * 通过id来查询订单
     * @param orderId
     * @return
     */
    OrderVo findOrderDetail(Long orderId);


    /**
     * 创建订单
     * @param orderVo
     */
    void create(OrderVo orderVo);
}
