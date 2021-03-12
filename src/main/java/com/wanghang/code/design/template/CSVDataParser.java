package com.wanghang.code.design.template;


/**
 * CSVData
 *        readData和processData不一致，需要单独实现
 */
public class CSVDataParser extends DataParserTemplate {
    @Override
    void readData() {
        System.out.println("Reading data from csv file");

    }

    @Override
    void processData() {
        System.out.println("Looping through loaded csv file");
    }
}
