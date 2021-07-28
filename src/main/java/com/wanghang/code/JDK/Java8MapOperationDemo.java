package com.wanghang.code.JDK;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *java8对Map的操作:
 *
 */
public class Java8MapOperationDemo {

    public static void main(String[] args) {
       // forEach();

      //  computeIfAbsentjava();

      //  computeIfPresent();

     //   putIfAbsent();

     //   getOrDefault();

     //   list2Map();

     //   list2Map2();

     //   list2Map3();

        map2List();
    }



    //1:java8的方式遍历Map
    public static void forEach(){
        Map<String,Integer> map=new HashMap<>();
        map.put("USA", 100);
        map.put("jobs", 200);
        map.put("software", 50);
        map.put("technology", 70);
        map.put("opportunity", 200);
        //1.1按升序对值进行排序，使用LinkedHashMap存储排序结果来保留结果映射中元素的顺序
        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));   //todo,排序在上一步已经完成了，这一步有点不懂

        linkedHashMap.forEach((key,value)->{
            System.out.println("key:"+key+",value:"+value);
        });

        //1.2按升序对值进行排序，排序使用sorted()的compareTo,也可以反序
        LinkedHashMap<String, Integer> linkedHashMap2 = map.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        linkedHashMap2.forEach((key,value)->{
            System.out.println("linkedHashMap2 key:"+key+",linkedHashMap2 value:"+value);
        });
    }


    //2:key的值不存在就使用函数结果代替
    public static void computeIfAbsentjava(){
        Map<String, Object> map = new HashMap<>();
        //key值存在
        map.put("key","无墨生香");
        map.computeIfAbsent("key",s->{
            return "哈哈哈";
        });
        System.out.println("map:"+map);
        //key值不存在的情况,返回设置的值
        Map<String, Object> map1 = new HashMap<>();
        map1.computeIfAbsent("key",s->{
            return "默认";
        });
        System.out.println("map1:"+map1);
    }

    //3:如果key的值存在就使用函数值代替,如果 函数值为null,会移除key
    public static void computeIfPresent(){
        Map<String, Object> map = new HashMap<>();
        //key值存在,使用设置的值
        map.put("key","无墨生香");
        map.computeIfPresent("key",(key,value)->{
            return "设置值";
        });
        System.out.println("map:"+map);

        //key值不存在,设置值不成功
        Map<String, Object> map1 = new HashMap<>();
        map1.computeIfPresent("key",(key,value)-> { return "处处香";});
        System.out.println("map1:"+map1);
    }


    //4:当key的值存在时不替换值；当key的值不存在时替换key的值
    public static void putIfAbsent(){
        //key-value存在还是用原来的值
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key","无墨生香");
        hashMap.putIfAbsent("key","处处香");
        System.out.println("hashMap:"+hashMap);
        //  key-value不存在
        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.putIfAbsent("key","处处香");
        System.out.println("hashMap1:"+hashMap1);
    }


    //5:当key的值存在时获取值，否则获取指定的默认值
    public static void getOrDefault(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key","无墨生香");
        System.out.println(hashMap.getOrDefault("key","处处香"));

        //get不存在的key
        HashMap<String, Object> hashMap1 = new HashMap<>();
        System.out.println(hashMap1.getOrDefault("key","处处香"));
    }


    //List<Employee> 转成Map(这种方式的话,key是不能重复的)
    public static void list2Map(){
        List<Employee> employeeList = init3();
        Map<Employee.Status, Employee> map = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getStatus, Function.identity()));
        System.out.println("map:"+map);
    }

    //List<Employee> 转成Map,key有重复的情况,会覆盖(可以制定是取前者还是后者)
    public static void list2Map2(){
        List<Employee> employeeList = init4();
        Map<Employee.Status, Employee> map = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getStatus, Function.identity(),(key1,key2)->key1));
        System.out.println("map:"+map);
    }

    //List<Employee> 转成Map,key有重复的情况,会覆盖(可以制定是取前者还是后者),并且之用返回的Map类型，比如:LinkedHashMap
    public static void list2Map3(){
        List<Employee> employeeList = init4();
        LinkedHashMap<Employee.Status, Employee> linkedHashMap = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getStatus, Function.identity(), (key1, key2) -> key1, LinkedHashMap::new));
        System.out.println("linkedHashMap:"+linkedHashMap);
    }

    //将Map转成List
    public static void map2List(){
        List<Employee> employeeList = init4();
        Map<Employee.Status, Employee> map = employeeList
                .stream()
                .collect(Collectors.toMap(Employee::getStatus, Function.identity(),(key1,key2)->key1));
        Collection<Employee> values = map.values();
        System.out.println("values:"+values);

        List<Employee> list = new ArrayList<>(map.values());
        System.out.println("list:"+list);

        Set<Employee> set = new HashSet<>(map.values());
        System.out.println("set:"+set);

    }

    public static List<Employee> init3(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION)
        );
        return emps;
    };


    public static List<Employee> init4(){
        List<Employee>  emps= Arrays.asList(
                new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
                new Employee(102, "张6", 18, 99.90, Employee.Status.FREE),
                new Employee(103, "李四", 59, 6666.66, Employee.Status.BUSY),
                new Employee(104, "李9", 59, 6676.76, Employee.Status.BUSY),
                new Employee(105, "王五", 28, 3333.33, Employee.Status.VOCATION),
                new Employee(106, "王9", 58, 334.33, Employee.Status.VOCATION)
        );
        return emps;
    };
}
