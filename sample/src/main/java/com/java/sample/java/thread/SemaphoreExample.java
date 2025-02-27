package com.java.sample.java.thread;


import java.util.concurrent.Semaphore;

public class SemaphoreExample implements  Runnable {
    private String threadName;
    private Semaphore semaphore;


    public SemaphoreExample(String threadName, Semaphore semaphore) {
        this.threadName = threadName;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            this.semaphore.acquire();
            Thread.sleep(1000L);
            System.out.println("thread name is:" + this.threadName);
            this.semaphore.release();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
