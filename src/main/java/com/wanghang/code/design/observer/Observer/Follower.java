package com.wanghang.code.design.observer.Observer;

//Observer(主题)
public class Follower implements Observer {

    private String followerName;

    public Follower(String followerName) {
        this.followerName = followerName;
    }

    @Override
    public void update(String oaName, String article) {
        System.out.println(followerName + " has received "+ oaName + "'s article :: " + article );
    }


    public String getFollowerName() {
        return followerName;
    }
}
