package com.wanghang.code.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *算法刷题：
 *https://github.com/guang19/framework-learning
 *
 * 刷算法：
 * https://labuladong.gitee.io/algo/
 *
 *
 * 刷设计模式
 * https://github.com/wanghang88/core-spring-patterns
 *
 */
public class SelectionSort {

    /*
     * 第一次:  -9,5,0,3,8,30,25,8,10    已排序序列为{},最小值为: -9
     * 第二次:  -9,0,5,3,8,30,25,8,10    已排序序列为{-9},最小值为: 0 ，
     * 第三次:  -9,0,3,5,8,30,25,8,10    已排序序列为{-9,0},最小值为: 3
     * 第四次:  -9,0,3,5,8,30,25,8,10    已排序序列为{-9,0,3},最小值为: 5
     * 第五次:  -9,0,3,5,8,30,25,8,10    已排序序列为{-9,0,3,5},最小值为8
     * 第六次:  -9,0,3,5,8,8,25,30,10    已排序序列为{-9,0,3,5,8},最小值为8
     * 第七次： -9,0,3,5,8InsertionSort,8,10,30,25    已排序序列为{-9,0,3,5,8,8},最小值为10
     * 第八次:  -9,0,3,5,8,8,10,25,30    已排序序列为{-9,0,3,5,8,8,10},最小值为25
     *
     * 选择排序需要比较的次数为集合的长度减1，每次比较都找出剩余未排序序列的最小值，将它与未排序的序列的第一个元素交换。
     *
     * 有没有优化空间?
     * 我们可以发现: 未排序序列中，当最小值就是未排序序列的第一个元素的时候，此时是无需交换的,
     * 比如第三次排序的时候，3就是未排序序列的第一个元素

     */
    public static void main(String[] args){
        int[] arr = {3,5,0,-9,8,30,25,8,10};
        List<Integer> list=new ArrayList<Integer>();
        //选择排序需要比较的次数为集合的长度减1，每次比较都找出剩余未排序序列的最小值，将它与未排序的序列的第一个元素交换。
        int minValIndex;
        int minVal;
        //1.1):循环的次数为数组的长度减1;
        for (int i = 0 ; i < arr.length - 1; ++i){
            //1.2):假设当前最小值为未排序序列的第1个元素
            minValIndex = i;

            //1.3):遍历剩余未排序序列中比最小值还要小的最小值
            for (int j = i + 1; j < arr.length; ++j){
                //如果当前未排序的值比最小值还小，那么就将当前未排序的值的下标设置为minIndex
                if (arr[j] < arr[minValIndex]){
                    minValIndex = j;
                }
            }
            //2.1):如果最小值就是未排序序列的第一个元素，那么无需交换
            if(minValIndex != i){
                minVal = arr[minValIndex];
                arr[minValIndex] = arr[i];
                arr[i] = minVal;
            }
        }
        //3:排序之后的数组为：
        System.out.println("排序之后的数组为arr:"+ Arrays.toString(arr));
    }



}
