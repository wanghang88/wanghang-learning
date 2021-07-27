package com.wanghang.code.JDK.guava;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.wanghang.code.javaClass.absract.cas1.bean.OrderVo;

import java.util.List;

/**
 *Preconditions 前置校验,前置条件适用于当判断与设置的条件不符合时，抛出异常的操作
 * 例子：
 * 1）对象判空，抛出异常
 * 2）List对象判空，抛出异常
 * 3）数字类型条件判断，抛出异常
 *
 */
public class PreconditionsDemo {


    public static void main(String[] args) {

        /**
         * 对象判空处理
         */
        OrderVo orderVo = null;
        Preconditions.checkNotNull(orderVo, "orderVo不能为null");



        /**
         * List对象判空处理
         */
        List<String> list = Lists.newArrayList();
        Preconditions.checkNotNull(list, "传入的list不能为null");

        /**
         * 数值类型判断处理
         *
         */
        Long projectId = -12L;
        Preconditions.checkNotNull(projectId, "projectId不能为null");
        Preconditions.checkArgument(projectId > 0, "输入projectId必须大于0", projectId);
    }
}
