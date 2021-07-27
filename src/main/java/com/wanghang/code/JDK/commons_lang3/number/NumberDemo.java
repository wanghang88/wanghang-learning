package com.wanghang.code.JDK.commons_lang3.number;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberDemo {

    public static void main(String[] args) {
        //1:NumberUtils.isDigits() :判断字符串中是否全为数字
        System.out.println(NumberUtils.isDigits("0000000000.596"));   //false
        System.out.println(NumberUtils.isDigits("0000000000596"));   //true


        //2：NumberUtils 字符串转换为数字
        System.out.println(NumberUtils.toInt("5"));
        System.out.println(NumberUtils.toLong("5"));
        System.out.println(NumberUtils.toByte("3"));        //byte类型的数据
        System.out.println( NumberUtils.toDouble("4"));     //double类型的数据
        System.out.println(NumberUtils.toShort("3"));      //short类型的数据
        System.out.println( NumberUtils.toFloat(""));     //float类型的数据

       //3：NumberUtils.max() :找出最大的一个
        System.out.println("max:"+NumberUtils.max(new int[]{3,5,6}));  //结果是6
        System.out.println("max:"+NumberUtils.max(3, 1, 7));  //结果是7


        //4：NumberUtils.min() :找出最小的一个
        System.out.println("max:"+NumberUtils.min(new int[]{3,5,6}));  //结果是3
        System.out.println("max:"+NumberUtils.min(3, 1, 7));  //结果是1


        //5:NumberUtils.createBigDecimal()通过字符串创建BigDecimal类型,支持创建long、int、float、double、number等数值
        System.out.println(NumberUtils.createBigDecimal("1"));//1
        System.out.println(NumberUtils.createDouble("1"));//1.0
        System.out.println(NumberUtils.createLong("1"));//1
        System.out.println(NumberUtils.createBigInteger("1"));//1
        System.out.println(NumberUtils.createFloat("1"));//1.0









        ;




    }
}
