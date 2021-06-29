package com.wanghang.code.JDK;


import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *JDK1.8 stream.Stream的操作:
 * 1)参考博文:
 * https://blog.csdn.net/mu_wind/article/details/109516995  (重点推荐推荐)
 * https://yuanyu.blog.csdn.net/article/details/105026457
 *
 * https://www.jianshu.com/p/5493b7685b96   (stream一些函数的综合运用,这一篇也是不错的)
 *
 *
 * 2)java集合的合并操作:
                     https://gitee.com/GitHaoChen/java_foundation/blob/master/guava-test/src/main/java/com/zhuoli/guava/test/java8mergelist/Java8MergeListTest.java

   3):java8的综合操作:
                    https://www.jianshu.com/p/442532d890e5
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
    //    streamDemo.mergerTest();


        /**
         *7:集合操作之分组:将List<Object>根据字段key进行分组，得到一个Map<key,List<Object>>的结构
         */
    //    streamDemo.aggregation();


        /**
         *映射(map/flatMap)
         * 可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap,
         * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素.
         * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流.
         *
         *
         *
         */
      //  streamDemo.mapOrFlatMap();


        streamDemo.reduce();


        streamDemo.collectionsTest();
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
     *
     *
     *
     *常见的转换
     *List<Employee>  employeeList ->List<String> nameList
     *List<Employee>  employeeList->Map<Integer, Employee> map
     *List<Interge> list->  List<Employee>  employeeList
     *
     */
    public void collect(){
        List<Employee> employeeList = init();
        //1.1:collect的joining()操作,   张三李四王五赵六赵六赵六田七
        String collect1 = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println("collect(Collectors.joining()):"+collect1);

        //1.2:collect的joining(CharSequence delimiter)操作2, 张三,李四,王五,赵六,赵六,赵六,田七
        String collect2 = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println("collect(Collectors.joining()):"+collect2);

        //1.3:collect的joining(CharSequence delimiter,CharSequence prefix,CharSequence suffix)操作3, @张三,李四,王五,赵六,赵六,赵六,田七@
        String collect3= employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","@","@"));
        System.out.println("collect(Collectors.joining()):"+collect3);

        //1.4:collect的toList
        List<String> empNameList = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("empNameList:"+empNameList);
        System.out.println(String.join(",", empNameList));
        System.out.println("collect test toList end");

        List<String> list=new ArrayList<>();
        list.add("100");
        list.add("200");
        String newStr = String.join(",", list);
        System.out.println("newStr:"+newStr);

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


       //3.4:将 List<String> list变成List<Employee>,也就是map的操作;
        List<Integer> idList=new ArrayList<>();
        idList.add(101);
        idList.add(102);
        idList.add(103);

        List<Employee> employeeListNew = idList.stream().map(e -> {
            if(e!=null){
                Employee Employee = new Employee(e, "wanghang", null, null, null);   //e.getId()则表示还是用原来的值
                return Employee;
            }else {
                return null;             //这个等于null则通过:filter(Objects::isNull)这中方式过滤
            }
        }).filter(Objects::isNull).collect(Collectors.toList());
        System.out.println("employeeListNew:"+employeeListNew);


        //3.5将字符串已逗号分割,变成List<Long>
        String str="100,200,300,400,500";
        String[] strArr = str.split(",");
        List<Long> collect4 = Arrays.stream(strArr).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        System.out.println("collect:"+collect4);
    }



    /**
     *java8的toMap操作,将list变成Map
     * 将 List<Employee> employeeList里的list数据变成
     * id=Employee这样的Map结构,但是对于这个map的key值不能重复,如果重读的话则会报错的
     *
     * List<Employee> ->Map<key,Employee>
     *
     * todo:这个Function.identity()不是太清楚
     */
    public void toMap(){
        List<Employee> employeeList = init1();

        Map<Integer, Employee> collect = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        System.out.println("collect:"+collect);

        //1:Map的数据形式:
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


    /**
     * java的聚合操作
     */
    public void aggregation(){
        List<Employee> employeesList = init();

        //1.1:分组:
        Map<Employee.Status, List<Employee>> map = employeesList.stream()
                                                 .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println("对employeesList分组之后的map为:"+map);

        //1.2:求最值:
        Optional<Employee> max = employeesList.stream().max(Comparator.comparingDouble(Employee::getSalary));//Comparator.comparingDouble(),根据字段的类型还有,Long,Int等，以及一些其他的api
        System.out.println("工资最高的人max"+max.get());
    }


    /**
     *映射(map/flatMap),映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap
     *
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素,
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流.
     *
     * map,通过函数，将原来employeesList里的每一个元素的name和salary都更改了(是每一个元素).
     */
    public void mapOrFlatMap(){
        //1.1:英文字符串数组的元素全部改为大写(变成新的List)
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("strList:"+strList);

        //1.2:整数数组每个元素+3(也是变成新的List)
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("intListNew:"+intListNew);

        List<Employee> employeesList = init3();
        List<Employee> employeesListNew = employeesList.stream()
                .map(e -> {
                    Employee Employee = new Employee(e.getId(), e.getName(), null, null, null);   //e.getId()则表示还是用原来的值
                    return Employee;
                }).collect(Collectors.toList());
        System.out.println("一次改动前employeesList"+employeesList);
        System.out.println("一次改动后employeesListNew"+employeesListNew);


        //也是直接更改了原来所有元素的那么值
        List<Employee> employeesListNew1 = employeesList.stream()
                .map(e -> {
                    e.setName("wanghang");
                    return e;
                }).collect(Collectors.toList());
        System.out.println("二次改动前employeesList"+employeesList);
        System.out.println("二次改动后employeesListNew1"+employeesListNew1);


        //3.5,List的flatMap,将三个字符连在一起
        String str1="1,2,3,4";
        String str2="100";
        String str3="a,b,c,d";
        List<String> strList1 = Arrays.asList(str1, str2,str3);
        System.out.println("strList:"+strList1);
        String allStr = str1 + str2 + str3;
        System.out.println("allStr:"+allStr);   //三个字符串自然地架子啊一起

        List<String> collect5 = strList1.stream().flatMap(s -> {
            String[] strArr1 = s.split(",");
            Stream<String> stream = Arrays.stream(strArr1);
            return stream;
        }).collect(Collectors.toList());
        System.out.println("通过flatMap将字符串连在一起:"+collect5);
    }


    /**
     *规约(reduce):顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作
     * 求和：sum
     * 求乘积：
     * 求最大值：
     */
    public void  reduce(){
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        //1.1:求和方式1
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        //1.1:求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println("list求和：" + sum1.get() + "," + sum2.get() + "," + sum);

        //1.2:求乘积：
        Optional<Integer> reduce = list.stream().reduce((x, y) -> x * y);
        System.out.println("list求积：" + reduce.get());

        //1.3:求最大值
        Optional<Integer> max1 = list.stream().reduce((x, y) -> x > y ? x : y);
        Optional<Integer> max2 = list.stream().reduce(Integer::max);
        System.out.println("list求最大值：" + max1.get() + "," + max2.get());


        List<Employee> employeeList = init();

        //2:年龄求和：先变成List<Interge>,然后通过这个reduce来求和：
        Stream<Integer> integerStream = employeeList.stream().map(Employee::getAge);
        Optional<Integer> ageSum = employeeList.stream().map(Employee::getAge).reduce(Integer::sum);
        System.out.println("年龄之和：" + max1.get() + "," + max2.get());

        //3:求平均工资：
        Double averagSalary = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均工资为:" +averagSalary);

        //4:求最值(最高工资):先变成Salary的stream，然后在这个stream里求最值
        Optional<Double> maxCollect = employeeList.stream().map(Employee::getSalary).collect(Collectors.maxBy(Double::compareTo));
        System.out.println("最高工资为:" +maxCollect.get());

        //5:求工资之和：
        DoubleSummaryStatistics doublecollect = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("工资和为:" +doublecollect.getSum());   //可以求出这一列的和，平均数，最大值，最小值
        System.out.println("doublecollect最高工资:" +doublecollect.getMax());

        //6:根据Status分组:
        Map<Employee.Status, List<Employee>> groupingByCollectMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println("根据Status分组的Map为:"+groupingByCollectMap);
    }


    /**
     *java.util.Collections中常见的api：
     * singletonList()
     * sort()
     * binarySearch()
     * get()
     * reverse()
     * copy()

     (小傅哥)Collections对list的操作额封装(包括二分查找, 旋转,洗牌等算法)
     https://bugstack.cn/interview/2020/09/10/%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C-%E7%AC%AC10%E7%AF%87-%E6%89%AB%E7%9B%B2java.util.Collections%E5%B7%A5%E5%85%B7%E5%8C%85-%E5%AD%A6%E4%B9%A0%E6%8E%92%E5%BA%8F-%E4%BA%8C%E5%88%86-%E6%B4%97%E7%89%8C-%E6%97%8B%E8%BD%AC%E7%AE%97%E6%B3%95.html
     */
    public void collectionsTest(){
        Long versionCode=100L;
        List<Long> longs = Collections.singletonList(versionCode);
        System.out.println("longs:"+longs);
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
    public static List<Employee> init3(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION)
        );
        return emps;
    };
}




















