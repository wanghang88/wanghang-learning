package com.wanghang.code.JDK.math;

public class MathDemo {

    public static void main(String[] args) {


        roundOrCeilOrFloor();




    }

    // 1:Math.round()，Math.ceil()，Math.floor()的区别
    private static void roundOrCeilOrFloor() {

        //1.1:Math.round()  “四舍五入”， 该函数返回的是一个四舍五入后的的整数
        double d = 3.1415926;
        double d2 = 18.58;
        double d3 = -15.23;
        double d4 = -16.85;
        long round1 = Math.round(d);    // 结果 3
        System.out.println("round1:"+round1);
        long round2 = Math.round(d2);   // 结果 19
        System.out.println("round2:"+round2);
        long round3 = Math.round(d3);   // 结果 -15
        System.out.println("round3:"+round3);
        long round4 = Math.round(d4);   // 结果 -17
        System.out.println("round4:"+round4);

        //1.2:Math.ceil()  “向上取整”， 即小数部分直接舍去，并向正数部分进1
        double ceild1 = 3.1415926;
        double ceild2 = 18.58;
        double ceild3 = -15.23;
        double ceild4 = -16.85;
        double ceild5 = -16.5;
        double ceild6 = 16.5;
        double ceil1 = Math.ceil(ceild1);    // 结果 4.0
        double ceil2 = Math.ceil(ceild2);   // 结果 19.0
        double ceil3 = Math.ceil(ceild3);   // 结果 -15.0
        double ceil4 = Math.ceil(ceild4);   // 结果 -16.0
        double ceil5 = Math.ceil(ceild5);   // 结果 -16.0
        double ceil6 = Math.ceil(ceild6);   // 结果 17.0


        //1.3:Math.floor()  “向下取整” ，即小数部分直接舍去
        double floord1 = 3.1415926;
        double floord2 = 18.58;
        double floord3 = -15.23;
        double floord4 = -16.85;
        double floord5 = -16.5;
        double floord6 = 16.5;
        double floor1 = Math.floor(floord1);    // 结果 3.0
        double floor2 = Math.floor(floord2);   // 结果 18.0
        double floor3 = Math.floor(floord3);   // 结果 -16.0
        double floor4 = Math.floor(floord4);   // 结果 -17.0
        double floor5 = Math.floor(floord5);   // 结果 -17.0
        double floor6 = Math.floor(floord6);   // 结果 16.0

    }


}
