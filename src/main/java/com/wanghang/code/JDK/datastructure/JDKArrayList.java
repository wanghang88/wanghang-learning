package com.wanghang.code.JDK.datastructure;

import com.wanghang.code.datastructure.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class JDKArrayList<E> implements List<E> {

    private Object[] elementData;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private int size;

    private int modCount = 0;



    public JDKArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public JDKArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        }
    }


    /**
     * JDKArrayList的add方法
     * @param e
     * @return
     */
    public boolean add1(E e) {

        elementData[size++] = e;
        return true;
    }


    /**
     *JDKArrayList的add2方法(其中包含扩容)
     * @param e
     * @return
     */
    public boolean add2(E e) {
        //1：判断是否需要扩容，计算一个 minCapacity=size+1
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     *ensureCapacityInternal方法中的两个方法：minCapacity为是否需要扩容的值，其值为原数组的长度size+1
     *
     * 1:calculateCapacity(minCapacity):计算最新的扩容的能力minCapacity;
     * 2:ensureExplicitCapacity,判断是否需要扩容，并完成扩容;
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        //2:判断是否需要扩容,minCapacity-elementData.length>0
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    //3:具体执行扩容，扩容的长度为elementData.length+elementData.length>>1,原数组长度的1.5倍的长度newCapacity
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }


    public static void main(String[] args) {

        JDKArrayList jDKArrayList=new JDKArrayList();

        //1:ArrayList测试方法：
     //   jDKArrayList.arrayListTest();


        //2:java.util.Collections,使用集合框架的工具类
        jDKArrayList.arrayLisJavaUtilCollections();
    }


    private  void arrayListTest() {
        ArrayList<String> list=new ArrayList();

        Integer a=8;
        System.out.println(+a>>5);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE - 8);

        Object [] objectArr={0,1,2,3,4,5};
        //1:将原数组扩大至10位,并且包含之前的元素;这个方法Arrays.copyOf(objectArr,newLength)的核心方法
        objectArr= Arrays.copyOf(objectArr, 10);
        for (int i = 0; i <objectArr.length ; i++) {
            System.out.println("objectArr[i]:"+objectArr[i]);
        }

        //2:数组的移动(在数组中新加一个元素或者删除一个元素),特别是在指定位加元素或者删除元素
        Object [] objectArr1={0,1,2,3,4,5};
        System.arraycopy(objectArr1,2,objectArr1,2+1,6-2);
        System.out.println("objectArr1:"+objectArr1);
    }


    //Collections.shuffle() 洗牌算法，其实就是将 List 集合中的元素进行打乱，一般可以用在抽奖、摇号、洗牌等各个场景中
    private void arrayLisJavaUtilCollections() {
        ArrayList<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        for (int i = 0; i < 5; i++) {
             Collections.shuffle(list);
             System.out.println("list:"+list.toString());
         }

        ArrayList<String> list2=new ArrayList<String>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list2.add("9");
        list2.add("10");

        //可以把ArrayList或者Linkedlist，从指定的某个位置开始，进行正旋或者逆旋操作。有点像把集合理解成圆盘，把要的元素转到自己这，其他的元素顺序跟随
        Collections.rotate(list2,1);
        System.out.println("list2 rotate:"+list2.toString());

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(list2,new Random(100));
            System.out.println("list2:"+list.toString());
        }
    }
}
