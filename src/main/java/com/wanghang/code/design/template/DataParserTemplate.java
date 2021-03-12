package com.wanghang.code.design.template;

public abstract class DataParserTemplate {

    // generic data processing flow(模版定义一组流程)
    public final void process() {
        readData();
        processData();
        writeData();
    }



    // implemented by subclass(子类的实现相同)
    abstract void readData();
    abstract void processData();



    // same for all subclass(所有的子类实现相同)
    public void writeData() {
        System.out.println("Ouput generated, writing to CSV");
    }
}
