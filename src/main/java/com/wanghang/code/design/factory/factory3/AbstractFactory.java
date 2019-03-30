package com.wanghang.code.design.factory.factory3;

public abstract class AbstractFactory {
    public abstract AnimalService createAnimalService();
    
    public abstract PersonService createPersonService();

}
