package com.wanghang.code.design.template;

public class DatabaseDataParser extends DataParserTemplate {
    @Override
    void readData() {
        System.out.println("Reading data from database");
    }

    @Override
    void processData() {
        System.out.println("Looping through records in DB");
    }
}
