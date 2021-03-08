package com.wanghang.code.thread.AQS.designframework;

public abstract class AbstractQueuedSynchronizer {

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
    protected int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }
    protected boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }
    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }

}
