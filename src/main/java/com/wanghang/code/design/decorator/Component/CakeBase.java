package com.wanghang.code.design.decorator.Component;



public class CakeBase implements BakeryComponent {

    private String name = "Cake Base";
    private double price = 200.0;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
