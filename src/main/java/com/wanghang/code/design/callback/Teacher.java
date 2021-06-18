package com.wanghang.code.design.callback;


import com.wanghang.code.design.callback.student.Student;

/**
 * 老师类,提问题
 */
public class Teacher implements Callback {

    private Student student;

    public Teacher(Student student){
        this.student=student;
    }


    //老师问问题接口askQuestion，实际调用的是学生解决问题的方法
    public void askQuestion(){
        student.resolveQuestion(this);
    }

    //回调函数要做的事情
    @Override
    public void tellAnswer(int answer) {
        System.out.println("学生思考完毕后,回答的答案为:"+ answer);
    }
}
