package com.wanghang.code.design.observer;

import com.wanghang.code.design.observer.Observer.Follower;
import com.wanghang.code.design.observer.Subject.OfficialAccount;

public class ObserverDemo {

    public static void main(String[] args) {

        OfficialAccount bobo = new OfficialAccount("bobo");
        OfficialAccount infoq = new OfficialAccount("infoq");

        Follower mark = new Follower("Mark");
        Follower eric = new Follower("Eric");

        bobo.register(mark);   //1:bobo(Subject)主题,提供注册主题的方法register(),和取消订阅的方法unregister()
        infoq.register(eric);  //以及发布主题的方法:pushArticle(),通知所用的订阅者，有消息变化

        bobo.pushArticle("observer design pattern video course");  //发布主题通知订阅者
        infoq.pushArticle("spring 5.0 is out");
    }
}
