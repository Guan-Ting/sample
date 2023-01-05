package com.java.sample.DS.Queue;



public class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int [] arr;

    //創建佇列建構子

    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front = -1;//柱列頭部
        rear = -1; //柱列尾部
    }

    //判斷柱列是否滿
    public boolean isFull(){
        return rear == maxSize-1;

    }

    //判斷柱列是否為空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加數據到佇列

}
