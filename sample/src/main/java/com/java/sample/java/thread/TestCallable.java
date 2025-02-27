package com.java.sample.java.thread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//class MyCallable implements Callable<Integer> {
//    @Override
//    public Integer call(){
//        return 123;
//    }
//}

public class TestCallable {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        FutureTask<Integer> ft=new FutureTask<>( () -> {
            return 123;
        });
        Thread thread=new Thread(ft);
        thread.start();
        System.out.println(ft.get());

    }
}
