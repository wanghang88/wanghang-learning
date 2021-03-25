package com.wanghang.code.JDK;


import lombok.Data;

import java.util.Arrays;
import java.util.List;


/**
 *JDK1.8 stream.Stream的操作:
 * https://yuanyu.blog.csdn.net/article/details/105026457
 */

public class StreamDemo {
    public static void main(String[] args) {
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
                new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
                new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
                new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
                new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
        );




    }
}



@Data
class Employee {
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
