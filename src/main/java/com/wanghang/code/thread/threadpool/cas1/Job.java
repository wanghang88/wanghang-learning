package com.wanghang.code.thread.threadpool.cas1;


import lombok.Data;
import lombok.ToString;

/**
 *具体的任务
 */

@Data
public class Job{
    private Integer jobId;
    private String jobName;

    public Job(){
        super();
    }

    public Job(Integer jobId,String jobName){
        this.jobId=jobId;
        this.jobName=jobName;
    }




    //执行任务要做的事情：
    public void execute(Integer jobId){
        System.out.println("jobId为:"+jobId+",已处理完成");
    }
}
