package com.java.sample.java.thread.deadlock;



public class DeadLockDemo {
    public static void main(String[] args) {

        //偶爾會發生兩個執行緒都處於 你不放開resource1的鎖定，我就不放開resource2鎖定的狀態
        Resource resource1=new Resource("resource",10);
        Resource resource2=new Resource("resource2",20);

        Thread thread1 =new Thread( () ->{
            for(int i =0; i<10 ; i++){
                resource1.cooperate(resource2);
            }
        });
        Thread thread2 =new Thread( () ->{
            for(int i =0; i<10 ; i++){
                resource2.cooperate(resource2);
            }
        });
        thread1.start();
        thread2.start();


    }
}
