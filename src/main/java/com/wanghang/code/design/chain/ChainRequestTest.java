package com.wanghang.code.design.chain;


/**
 *java设计模式值责任链模式：
 * 通过让多个处理对象依次处理请求的方式，将请求和处理对象解耦;
 * 处理对象组织成链状结构，并且请求在链中依传递,直到某个对象能够处理请求
 *
 * 问题：
 *    处理对象的请求只能从第一个节点开始处理吗？
 *
 *
 *应用：
 *    Spring Security以及微服务网关的过滤器
 */
public class ChainRequestTest {


    public static void main(String[] args) {
        TeamLeader teamLeader = new TeamLeader();
        ProjectLeader projectLeader = new ProjectLeader();
        HR hr = new HR();

        //1:在这里形成处理链条，teamLeader直接领导为第一个处理对象
        teamLeader.setNextHandler(projectLeader);
        projectLeader.setNextHandler(hr);

        teamLeader.approve(new Leave(1, 5));    //直接领导
        teamLeader.approve(new Leave(2, 15));   //项目经理
        teamLeader.approve(new Leave(3, 25));   //HR审批
        teamLeader.approve(new Leave(4, 35));   //没人审批--------？
    }
}
