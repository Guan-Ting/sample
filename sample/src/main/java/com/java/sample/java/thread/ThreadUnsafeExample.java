package com.java.sample.java.thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUnsafeExample {
    private int cnt =0;

    public void add(){
        cnt++;
    }

    public int get(){
        return cnt;
    }

    public static void main(String[] args) throws  Exception {
        final int threadSize=1000;
        ThreadUnsafeExample example =new ThreadUnsafeExample();
        final CountDownLatch countDownLatch=new CountDownLatch(threadSize);
        //newCacheThreadPool：執行緒 Pool 發現有執行緒執行完之後，會將資源回收重新的再使用。如果沒有回收的資源，那就會再去建立執行緒。
        ExecutorService executorService  =Executors.newCachedThreadPool();
        for (int i =0 ; i< threadSize ;i++){
            executorService.execute( () -> {
                example.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.get());


    }
}
