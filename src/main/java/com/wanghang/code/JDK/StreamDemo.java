package com.wanghang.code.JDK;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sun.tools.internal.ws.wscompile.WsimportOptions;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *JDK1.8 stream.Stream的操作:
 * 参考博文:
 * https://blog.csdn.net/mu_wind/article/details/109516995(重点推荐推荐)
 * https://yuanyu.blog.csdn.net/article/details/105026457
 *
 *
 * java集合的合并操作:
  https://gitee.com/GitHaoChen/java_foundation/blob/master/guava-test/src/main/java/com/zhuoli/guava/test/java8mergelist/Java8MergeListTest.java
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
         */
    //    streamDemo.sort();


        /**
         *4:对list的collect(Collector c)收集操作
         * 将流转换为其他形式
         */
    //    streamDemo.collect();


        /**
         *5:toMap的操作(将List<Object>根据唯一的字段key,变成key-Object的map结构)
         */
    //    streamDemo.toMap();


        /**
         * 6:集合的交集,合集,差集的操作
         */
        streamDemo.mergerTest();











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


    /**
     *java8对list的collect操作;
     * toList,把流中元素收集到List,                          List<Employee> emps= list.stream().collect(Collectors.toList())
     * toSet,把流中元素收集到Set,                            Set<Employee> emps= list.stream().collect(Collectors.toSet())
     * toCollection,把流中元素收集到创建的集合,                Collection<Employee> emps =list.stream().collect(Collectors.toCollection(ArrayList::new))
     *
     * 计算
     * counting,计算流中元素的个数,                          long count = list.stream().collect(Collectors.counting())
     * summingInt,对流中元素的整数属性求和                    int total=list.stream().collect(Collectors.summingInt(Employee::getSalary))
     * averagingInt,计算流中元素Integer属性的平均值           double avg = list.stream().collect(Collectors.averagingInt(Employee::getSalary))
     * summarizingInt,收集流中Integer属性的统计值。如：平均值  int SummaryStatisticsiss= list.stream().collect(Collectors.summarizingInt(Employee::getSalary));
     *
     * maxBy,          根据比较器选择最大值  Optional<T>      Optional<Emp>max= list.stream().collect(Collectors.maxBy(comparingInt(Employee::getSalary)));
     * minBy,          根据比较器选择最小值  Optional<T>      Optional<Emp> min = list.stream().collect(Collectors.minBy(comparingInt(Employee::getSalary)));
     * reducing,       归约产生的类型,从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
     *                 int total=list.stream().collect(Collectors.reducing(0, Employee::getSalar, Integer::sum));
     *
     *
     *拼接:
     * joining, 连接流中每个字符串   String,          String str= list.stream().map(Employee::getName).collect(Collectors.joining());
     *
     *
     * collectingAndThen，转换函数返回的类型，包裹另一个收集器，对其结果转换函数。    int how= list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
     *
     *
     * 分组：
     * groupingBy， 根据某属性值对流分组，属性为K，结果为V，   Map<K, List<T>>
     *              Map<Emp.Status, List<Emp>> map= list.stream() .collect(Collectors.groupingBy(Employee::getStatus));
     *
     *partitioningBy，根据true或false进行分区。 Map<Boolean, List<T>>
     *              Map<Boolean,List<Emp>> vd = list.stream().collect(Collectors.partitioningBy(Employee::getManage));
     */

    public void collect(){
        List<Employee> employeeList = init();

        //1:collect的toList
        List<String> empNameList = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("empNameList:"+empNameList);
        System.out.println(String.join(",", empNameList));
        System.out.println("collect test toList end");

        //2:collect的toSet
        Set<String> empNameSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println("empNameSet:"+empNameSet);
        System.out.println(String.join(",", empNameSet));
        System.out.println("collect test toSet end");

        //3.1:收集到特殊的集合中(HashSet)
        HashSet<String> empNameHashSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("empNameHashSet:"+empNameHashSet);
        System.out.println(String.join(",", empNameHashSet));
        System.out.println("collect test collect HashSet end");

        //3.2:收集到特殊的集合中(ArrayList)
        List<String> empNameArrayList = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("empNameArrayList:"+empNameArrayList);
        System.out.println(String.join(",", empNameArrayList));
        System.out.println("collect test collect ArrayList end");

        //3.3:把流中元素收集到创建的集合(一般和其他的stream的api一起使用，形成新的集合)
        ArrayList<Employee> collect = employeeList.stream().
                collect(Collectors.toCollection(ArrayList::new));
        System.out.println("collect:"+collect);
        System.out.println("collect test collect  end");
    }


    /**
     *java8的toMap操作,将list变成Map
     * 将 List<Employee> employeeList里的list数据变成
     * id=Employee这样的Map结构,但是对于这个map的key值不能重复,如果重读的话则会报错的
     * todo:这个Function.identity()不是太清楚
     */
    public void toMap(){
        List<Employee> employeeList = init1();

        Map<Integer, Employee> collect = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        System.out.println("collect:"+collect);

        Map<Integer,String> map=new HashMap<>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");
        System.out.println("map:"+map);
    }


    /**
     *java对集合的交集,并集，差集的操作：
     */
    public void mergerTest(){
        List<Employee> employeesList1 = init1();
        List<Employee> employeesList2 = init2();

        //1:两个集合的交集(相同的元素)
        List<Employee> collect1 = employeesList1.stream()
                .filter(employeesList2::contains)
                .collect(Collectors.toList());
        System.out.println("交集collect1"+collect1);

        //2:两个集合的并集(去除相同元素,两个集合合在一起)
        List<Employee> collect2 = Stream.of(employeesList1, employeesList2).
                flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println("并集collect2"+collect2);

        //3:两个集合的差集:todo,这个差集的employeesList2中的  无名这个元素为啥不是差集呢？差集的定义
        List<Employee> collect3 = employeesList1.stream()
                                 .filter(x -> !employeesList2.contains(x))
                                 .collect(Collectors.toList());
        System.out.println("差集collect3:"+collect3);
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

    public static List<Employee> init1(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
                new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
             //   new Employee(104, "赵六", 8, 7779.77, Employee.Status.FREE),
              //  new Employee(104, "赵六", 8, 7707.77, Employee.Status.FREE),
                new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
        );
        return emps;
    };



    //测试list的合并
    public static List<Employee> init2(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
                new Employee(103, "无名", 30, 400.90, Employee.Status.VOCATION)
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
