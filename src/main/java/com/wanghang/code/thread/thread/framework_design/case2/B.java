package com.wanghang.code.thread.thread.framework_design.case2;

/**
 * @author wanghang
 * @version 1.0.0
 * @createTime 2021年09月30日
 * @ClassName Teacher.java
 * @Description TODO
 */
public class B {

    public void executeMessage(CallBack callBack,String question){
        System.out.println(callBack.getClass()+"问的问题--》"+question);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result="答案是2";
        callBack.solve(result);
    }
}
