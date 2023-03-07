package com.java.sample.java8.thread.atomic;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAtomicInteger {
    private AtomicInteger i =new AtomicInteger(0);
    public int add(){
        return i.addAndGet(1);
    }

}
