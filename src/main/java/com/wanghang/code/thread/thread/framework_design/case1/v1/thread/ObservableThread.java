package com.wanghang.code.thread.thread.framework_design.case1.v1.thread;


import com.wanghang.code.thread.thread.framework_design.case1.v1.Task;
import com.wanghang.code.thread.thread.framework_design.case1.v1.TaskLifecycle;
import com.wanghang.code.thread.thread.framework_design.case1.v1.observe.Observable;

public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;
    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(),task);
    }

    public <T> ObservableThread(TaskLifecycle lifecycle, Task task){
        super();
        if(task==null)
            throw  new IllegalArgumentException("The task is required");
        this.task=task;
        this.lifecycle=lifecycle;
    }


    public final void run(){
        //在执行线程逻辑单元的时候,分别触发相应的事件
        this.update(Cycle.START,null,null);
       try{
           this.update(Cycle.RUNNING,null,null);

           T reslut = this.task.call();

           this.update(Cycle.RUNNING,reslut,null);
       }catch (Exception e){
           this.update(Cycle.RUNNING,null,e);
       }
    }



    private void update(Cycle cycle, T reslut, Exception  e){
        this.cycle=cycle;
        if(lifecycle==null){
            return;
        }
        try{
            switch (cycle){
                case START:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread());
                    break;
                case ERROR:
                     this.lifecycle.onError(currentThread());
                     break;
                }
            }catch (Exception ex){
            if(cycle==Cycle.ERROR){
                throw ex;
            }
        }
    }


    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
