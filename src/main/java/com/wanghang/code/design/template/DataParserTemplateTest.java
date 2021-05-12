package com.wanghang.code.design.template;


/**
 *模版方法模式：
 *          抽象类中定义抽象方法的执行顺序，并将抽象方法设定为只有子类实现，但不设计独立访问的方法
 *          将一组流程定义在一个方法内，子类具有相同的功能在模版类中实现，不同的功能由子类自己实现
 *
 *2)模版方法模式参考实现:

 https://bugstack.cn/itstack-demo-design/2020/07/07/%E9%87%8D%E5%AD%A6-Java-%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-%E5%AE%9E%E6%88%98%E6%A8%A1%E6%9D%BF%E6%A8%A1%E5%BC%8F.html

 *
 *
 */
public class DataParserTemplateTest {

    public static void main(String[] args) {
        CSVDataParser csvDataParser = new CSVDataParser();
        csvDataParser.process();
        System.out.println("**********************");
        DatabaseDataParser databaseDataParser = new DatabaseDataParser();
        databaseDataParser.process();
    }
}
