package com.wanghang.code.design.observer.Subject;


import com.wanghang.code.design.observer.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

//Subject
public class OfficialAccount implements Subject {

    private String oaName;
    private List<Observer> followers;

    public OfficialAccount(String oaName) {
        this.oaName = oaName;
        followers = new ArrayList<Observer>();
    }


    //订阅主题
    @Override
    public void register(Observer o) {
        followers.add(o);
        System.out.println(o + " has started following " + oaName);
    }

    //取消订阅
    @Override
    public void unregister(Observer o) {
        followers.remove(o);
        System.out.println(o + " has stopped following " + oaName);
    }


    //有变化的话发布通知
    @Override
    public void notifyAllObservers(String article) {
        for(Observer follower : followers) {
            follower.update(oaName, article);
        }
        System.out.println();
    }

    public void pushArticle(String article) {
        System.out.println("\n" + oaName + " has pushed :: " + article);
        notifyAllObservers(article);
    }
}
