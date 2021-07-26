package com.wanghang.code.thread.thread.framework_design.case1.v2;

public class DelegateRunnable implements Runnable{

    private Delegate delegate;
    private Object data;


    public interface Delegate {
        void willStart(DelegateRunnable runnable);
        int run(DelegateRunnable runnable);
        void finish(int res, DelegateRunnable runnable);
    }

    public DelegateRunnable() {
        System.out.println("创建对象:" + this);
    }

    @Override
    public void run() {
        if (delegate != null) {
            delegate.willStart(this);
            int res = delegate.run(this);
            delegate.finish(res, this);
        }
    }




    public Delegate getDelegate() {
        return delegate;
    }
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object userObject) {
        this.data = userObject;
    }
}
