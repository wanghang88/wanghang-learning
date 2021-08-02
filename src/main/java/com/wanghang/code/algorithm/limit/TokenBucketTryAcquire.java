package com.wanghang.code.algorithm.limit;


/**
 *令牌桶限流算法,可以看下Guava的RateLimiter的源码
 *
 *
 */
public class TokenBucketTryAcquire implements RateLimit {

    @Override
    public boolean execute() {
        return false;
    }
}
