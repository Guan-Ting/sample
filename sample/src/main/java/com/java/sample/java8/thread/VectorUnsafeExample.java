package com.java.sample.java8.thread;


import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VectorUnsafeExample {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.printf("加第%s個",i);
                vector.add(i);
            }
            ExecutorService executorService = Executors.newCachedThreadPool();
             executorService.execute(() -> {
                 synchronized (vector) {
                     for (int i = 0; i < vector.size(); i++) {
                         System.out.printf("移除第%s個",i);
                         vector.remove(i);
                     }
                 }
            });
             executorService.execute(() -> {
                 synchronized (vector) {
                     for (int i = 0; i < vector.size(); i++) {
                         vector.get(i);
                     }
                 }
            });
            executorService.shutdown();
        }
    }

}
