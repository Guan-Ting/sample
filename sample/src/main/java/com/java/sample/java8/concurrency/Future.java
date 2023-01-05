package com.java.sample.java8.concurrency;


import lombok.var;

import java.util.concurrent.Callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Future<T> implements Runnable {

    //執行Callable會返回Future物件
    private Callable<T> callable;
    private T r;
    Future(Callable<T> callable){
        this.callable=callable;
    }


    boolean isDone(){
        //if r is null return false ; if r is not null return true
        return r !=null;
    }

    synchronized T get() throws  InterruptedException {
        while (r ==null ){
            wait();
        }
        return r;
    }

    public void run() {
        try {
            synchronized(this) {
                r = callable.call();
                notify();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> Future<T> submit(Callable<T> callable){
        Future future =new Future<>(callable);
        new Thread(future).start();
        return future;
    }

    static long fibonacci(long n ){
        if (n <=1){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n -2 );
    }

    public static void main(String[] args) throws InterruptedException {
        //Future future =Future.submit(() -> fibonacci(30));


        Callable callable = () -> fibonacci(30);
        Future future=Future.submit(callable);

        System.out.println("老闆，我要第 30 個費式數，待會來拿...");
        while (!future.isDone()){
            System.out.println("老闆，好了嗎...");
            System.out.println("先忙別的事");
        }
//        System.out.printf("第 30 個費式數：%d%n", future.get());
//        var service = Executors.newCachedThreadPool();
//        var future=service.submit(() -> fibonacci(30));
//        System.out.println("老闆，我要第 30 個費式數，待會來拿...");
//        while(!future.isDone()) {
//            System.out.println("忙別的事去...");
//        }
//        System. out.printf("第 30 個費式數：%d%n", future.get());


    }


}
