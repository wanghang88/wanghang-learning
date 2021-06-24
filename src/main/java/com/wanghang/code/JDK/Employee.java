package com.wanghang.code.JDK;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {

    /**主键*/
    private Integer id;
    /**姓名*/
    private String name;
    /**年龄*/
    private Integer age;
    /**薪水*/
    private Double salary;
    /**用户状态*/
    private Status status;

    public enum Status {
        //空闲
        FREE,
        //忙碌
        BUSY,
        //
        VOCATION;
    }
    public Employee(Integer id, String name, Integer age, Double salary, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }






}
