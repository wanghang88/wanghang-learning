package com.wanghang.code.datastructure;


import java.util.HashMap;


/**
 *HashMap的实现原理参考博文：
 * https://www.cnblogs.com/jajian/p/13965678.html   （码辣架构）
 *
 * bugstack虫洞栈：
 https://bugstack.cn/interview/2020/08/13/%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C-%E7%AC%AC4%E7%AF%87-HashMap%E6%95%B0%E6%8D%AE%E6%8F%92%E5%85%A5-%E6%9F%A5%E6%89%BE-%E5%88%A0%E9%99%A4-%E9%81%8D%E5%8E%86-%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.html
 *
 *
 *
 *
 *
 *
 */

/**
 *HashMap的实现原理:
 * 博文:
  苦味代码，初始化，数据寻址-hash方法，数据存储-put方法,扩容-resize方法，只要理解了这四个点的原理和调用时机，也就理解了整个HashMap的设计
  https://mp.weixin.qq.com/s/UOr9BOWrv67d8l1VQxqUkA
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class JDK18HashMap {

    public static void main(String[] args) {
        HashMap<String,Object> hashMap=new HashMap<>();

        hashMap.put("a",1);
        hashMap.put("b",2);
        hashMap.put("c",3);
        hashMap.put("a",4);

        System.out.println("hashMap:"+hashMap);


        int a=19;
        int b =826637135;
        //& 位与运算：两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0
        //^ 位异或运算:两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1
        // | 位或运算符:两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0
        //~ 位非运算符:
        System.out.println((a-1) & b);

        JDK18HashMap.binaryToDecimal(10);
    }



        public static void binaryToDecimal(int n){
            String result = Integer.toBinaryString(n);
            System.out.println("result:"+result);
    }



}
