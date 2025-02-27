package com.java.sample.java.thread;



public class VolatileExample {
    int x =0;
    volatile boolean  v=false;
    public void writer(){
        x=1;
        v=true;
    }

    public void reader(){
        if( v==true){

        }
    }
}
