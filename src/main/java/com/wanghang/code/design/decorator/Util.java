package com.wanghang.code.design.decorator;

import com.wanghang.code.design.decorator.Component.BakeryComponent;

public class Util {
    public static void printProductDetails(BakeryComponent bComponent) {
        String out = "Item: " + bComponent.getName() + ", " + "Price: " + bComponent.getPrice();
        System.out.println(out);
    }
}
