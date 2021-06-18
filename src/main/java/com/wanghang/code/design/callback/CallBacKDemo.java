package com.wanghang.code.design.callback;


import com.wanghang.code.design.callback.student.Student;
import com.wanghang.code.design.callback.student.WangHang;

/**
 *java的回调机制:
 *在一个应用系统中,在模块之间的调用方式
 * 1)同步调用:
 * 类A的方法a()调用类B的方法b()，一直等待b()方法执行完毕，a()方法继续往下走,这种调用方式适用于方法b()执行时间不长的情况,
 *    因为如果b()方法执行过长或者阻塞的话,a()方法余下的代码无法执行下去,造成整个流程阻塞。
 *2)异步调用:
 *类A的方法方法a()通过新起线程的方式调用类B的方法b()，代码接着直接往下执行,但是这种方式,a()方法不必等b()方法执行完，若a()需要b()执行结果的,
 *    就必须对b()的执行结果进行监听，在java中可以使用Future+Callable的方式做到这一点(Callable+Future组件介绍 https://www.cnblogs.com/xrq730/p/4872722.html)
 *
 *3)还有一种方式能处理这种场景：回调
 * 回调的思想是：
 * 类A的a()方法调用类B的b()方法,
 * 类B的b()方法执行完毕主动调用类A的callback()方法。
 *
 *4)演示案例场景：
 * 老师问学生问题,
 * 学生思考完毕
 * 回答老师
 */
public class CallBacKDemo {

    public static void main(String[] args) {
        Student student = new WangHang();
        Teacher teacher = new Teacher(student);
        //1:老师调用学生接口的方法resolveQuestion(),向学生提问
        //2:学生解决完毕问题之后调用老师的回调方法tellAnswe()
        teacher.askQuestion();
    }
}
