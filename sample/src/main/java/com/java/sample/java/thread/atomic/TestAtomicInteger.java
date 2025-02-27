package com.java.sample.java.thread.atomic;


import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    private AtomicInteger i =new AtomicInteger(0);
    public int add(){
        return i.addAndGet(1);
    }

}
