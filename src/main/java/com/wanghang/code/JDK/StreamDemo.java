package com.wanghang.code.JDK;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *JDK1.8 stream.Stream的操作:
 * 参考博文:
 * https://blog.csdn.net/mu_wind/article/details/109516995(重点推荐推荐)
 * https://yuanyu.blog.csdn.net/article/details/105026457
 */

public class StreamDemo {
    public static void main(String[] args) {
        StreamDemo streamDemo=new StreamDemo();

        /**
         *1:java8对list的filter操作;
         */
      //  streamDemo.filter();


        /**
         * 2:对list进行分页：分页的pageInde是从第1页开始
         */
     //   streamDemo.listPage(1,5);


        /**
         *3:对list的排序操作
         *
         *
         */
        streamDemo.sort();

    }


    //1:对java8的操作过滤操作
    public void filter(){
        //1:过滤掉年龄大于35的
        List<Employee> employeeList = init();
        Stream<Employee> stream = employeeList.stream()
                .filter((e) -> {
                    return e.getAge() <= 35;
                });
        stream.forEach(System.out::println);
        System.out.println("filter end");


        //2:filter结合limit使用,限制只取几条数据
        employeeList.stream()
                .filter((e) -> {
                    return e.getSalary() >= 5000;
                }).limit(3)   //找到了 3 条 后面的就不在去找了
                .forEach(System.out::println);
        System.out.println("filter limit end");

        //3:filter结合skip使用,跳过前面几条数据(去掉前面薪水大于等于5000的2条数据)
        employeeList.parallelStream()
                .filter((e) -> e.getSalary() >= 5000)
                .skip(2)
                .forEach(System.out::println);

    }



    //2:java8对list进行分页操作
    public void listPage(int pageIndex1,int pageSize2){
        int pageSize=4;
        List<Employee> employeeList = init();
        for (int pageIndex = 1; (pageIndex - 1) * pageSize < employeeList.size(); pageIndex++) { //从第一页开开始,查看每页的分页后的数据
            System.out.println("第" + pageIndex+"页数据:"+ JSONObject.toJSONString(getPageResult(employeeList,pageIndex,pageSize)));
        }

        List<Employee> list1 = getPageResult(employeeList, pageIndex1, pageSize2);     //根据pageIndex和pageSize2获取分页后的数据;
        System.out.println("取第"+pageIndex1+"的数据:"+JSONObject.toJSONString(list1));
    }

    public static List<Employee> getPageResult(List<Employee> list, int pageIndex, int pageSize) {
        //模拟分页效果
        return list.stream().skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }



     //java8对list的排序操作
    public void sort(){
        List<Employee> employeeList = init();

        //1:sorted会产生一个新的流,按照自认顺序排列, todo：整相当于只返回了个salary字段的List
        employeeList.stream()
                .map(Employee::getSalary)
                .sorted()
                .forEach(System.out::println);
        System.out.println("sorted test1 end");

        //2.2.1:按照年龄升序
        employeeList.stream()
                .sorted((x, y) -> x.getAge() - y.getAge()).forEach(System.out::println);
        System.out.println("sorted test2 end");

        //2.2.2:按照年龄降序
        employeeList.stream()
                .sorted((x, y) -> y.getAge() - x.getAge()).forEach(System.out::println);

        System.out.println("sorted test3 end");

        //2.3.1,按照年龄升序
        employeeList.stream()
                .sorted((x, y) -> x.getAge().compareTo(y.getAge())).forEach(System.out::println);
        System.out.println("sorted test4 end");
        //2.3.1,按照年龄降序
        employeeList.stream()
                .sorted((x, y) -> y.getAge().compareTo(x.getAge())).forEach(System.out::println);
        System.out.println("sorted test5 end");

        //5.1:按照薪水升序
        employeeList.stream()
                .sorted((x, y) -> {
                    Double xSalary = x.getSalary();
                    Double ySalary = y.getSalary();
                    return xSalary.compareTo(ySalary);
                })
                .forEach(System.out::println);
        System.out.println("sorted test6 end");

        //5.2:按照薪水降序
        employeeList.stream()
                .sorted((x, y) -> {
                    Double xSalary = x.getSalary();
                    Double ySalary = y.getSalary();
                    return ySalary.compareTo(xSalary);
                })
                .forEach(System.out::println);
        System.out.println("sorted test7 end");

        //6.1:按年龄升序,如果过年龄相同的话,则按薪水升序
        employeeList.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getSalary().compareTo(y.getSalary());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }



















    public static List<Employee> init(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
                new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
                new Employee(104, "赵六", 8, 7779.77, Employee.Status.FREE),
                new Employee(104, "赵六", 8, 7707.77, Employee.Status.FREE),
                new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
        );
        return emps;
    };
}













@Data
@ToString
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
