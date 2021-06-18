package com.wanghang.code.design.callback.student;

import com.wanghang.code.design.callback.Callback;

public class WangHang implements Student{
    @Override
    public void resolveQuestion(Callback callback) {
        //1:模拟解决老师问题
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        //2:思考完毕后告诉老师答案:1000
        callback.tellAnswer(1000);
    }
}
