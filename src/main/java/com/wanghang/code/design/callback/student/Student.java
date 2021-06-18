package com.wanghang.code.design.callback.student;

import com.wanghang.code.design.callback.Callback;


/**
 * 学生接口,定义了一个方法resolveQuestion()解决问题
 */
public interface Student {
     void resolveQuestion(Callback callback);
}
