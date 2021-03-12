package com.wanghang.code.design.chain;


/**
 *责任链模式中定义处理请求的接口：
 * 以请假流程为例，定义请假流程的接口
 *
 */
public interface ApproveHandler {

    //1：设置下一个处理对象:
    void setNextHandler(ApproveHandler nextHandler);

    //2：处理审批流程:
    void approve(Leave leave);
}
