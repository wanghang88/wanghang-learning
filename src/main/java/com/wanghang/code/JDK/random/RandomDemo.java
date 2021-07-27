package com.wanghang.code.JDK.random;

import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 *
 *
 * 主要是commons-lang3包中常见的工具类
 * ObjectUtils
 * NumberUtils
 * BooleanUtils
 * RandomStringUtils
 * RandomUtils
 * SystemUtils
 * DateFormatUtils
 * DateUtils
 *
 * EnumUtils  枚举例工具类

 *
 */
public class RandomDemo {


    public static void main(String[] args) {

        //1：从所有字符集中选择字符，产生5位长度的随机字符串，中文环境下是乱码
        String randomStr1 = RandomStringUtils.random(5);
        System.out.println("randomStr1："+randomStr1);


        /*
         * count 长度
         * letters 生成的字符串是否包括字母字符
         * numbers 生成的字符串是否包含数字字符
         */
        String randomStr2 = RandomStringUtils.random(15, true, false);
        System.out.println("randomStr2:"+randomStr2);



        //使用指定的字符生成5位长度的随机字符串，第二个参数如果NULL，则使用所有字符集
        String randomStr3 = RandomStringUtils.random(5, new char[]{'a', 'b', 'c', 'd', 'e', 'f', '1', '2', '3'});
        System.out.println("randomStr3:"+randomStr3);


        //生成指定长度的字母和数字组成的随机组合字符串
        String random4Str= RandomStringUtils.randomAlphanumeric(5);
        System.out.println("random4Str:"+random4Str);


        //生成随机数字字符串
        String random5Str= RandomStringUtils.randomNumeric(5);
        System.out.println("random5Str:"+random5Str);


        //生成随机[a-z,A-Z]字符串，包含大小写
        String random6Str = RandomStringUtils.randomAlphabetic(5);
        System.out.println("random6Str:"+random6Str);
    }

}
