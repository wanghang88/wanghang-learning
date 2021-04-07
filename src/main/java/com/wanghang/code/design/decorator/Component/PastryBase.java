package com.wanghang.code.design.decorator.Component;


public class PastryBase implements BakeryComponent {

    private String name = "Pastry Base";
    private double price = 20.0;


    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public double getPrice() {
        return this.price;
    }
}
