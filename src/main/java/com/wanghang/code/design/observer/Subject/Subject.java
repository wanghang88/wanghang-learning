package com.wanghang.code.design.observer.Subject;

import com.wanghang.code.design.observer.Observer.Observer;

public interface Subject {

     void register(Observer o);


     void unregister(Observer o);


     void notifyAllObservers(String s);
}
