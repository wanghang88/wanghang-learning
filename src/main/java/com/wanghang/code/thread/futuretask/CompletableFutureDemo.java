package com.wanghang.code.thread.futuretask;


/**
 *CompletableFuture
 *
 * 1)参考博文:
 *         https://www.jianshu.com/p/6bac52527ca4
 *
 *
 *
 *
 *  CompletableFuture[] cfs = idVersionMap.entrySet()
 *                         .stream()
 *                         .map(entry -> CompletableFuture.supplyAsync(()
 *                                 -> findAppInstallVersion(entry.getKey(),entry.getValue().getVersionCode(),request,attributeData, appList), executorService)
 *                                 .whenComplete((s, e) -> {
 *                                     //System.out.println("分析appId:" + entry.getKey());
 *                                 })
 *                         ).toArray(CompletableFuture[]::new);
 *                 CompletableFuture jobs = CompletableFuture.allOf(cfs);
 *                 jobs.join();
 *
 *
 *
 *
 *
 */
public class CompletableFutureDemo {





}
