package com.wanghang.code.design.template;


/**
 *模版方法模式：
 *          将一组流程定义在一个方法内，子类具有相同的功能在模版类中实现，不同的功能由子类自己实现
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
