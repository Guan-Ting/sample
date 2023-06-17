package com.java.sample.DS.Queue;


import java.util.Scanner;

public class ArrayQueue {
    private int maxSize;
    private int front; //頭
    private int rear; //尾部
    private int [] arr;//該數據用於存放數據 模擬對列

    //創建佇列建構子

    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front = -1;//柱列頭部 分析出front是指向對列頭前一個位置
        rear = -1; //指向 對列尾的數據 (就是最後一個數據)
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
    public void addQueue(int n){
        //判斷對列是否滿
        if(isFull()){
            System.out.println("對列滿,不能增加數據");
            return;
        }
        rear++; //讓rear後移
        arr[rear]=n;
    }

    //獲取對列的數據 出隊列
    public int getQueue(){
        //判斷是否為空
        if (isEmpty()){
            //通過拋出異常處理
            throw new RuntimeException("對列空,不能取數據");

        }
        front++;
        return arr[front];
    }

    //顯示 對列的所有數據
    public void showQueue(){
        //遍歷
        if(isEmpty()){
            System.out.println("對列空的,沒有數據");
            return ;
        }
        for(int i=0; i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //顯示 對列的頭部數據,不是取出數據
    public int  headQueue(){
        //判斷
        if (isEmpty()){
            System.out.println("對列空的,沒有數據");
            throw new RuntimeException("對列空的,沒有數據");
        }
        return arr[front+1];
    }
    public static void main(String[] args) {
        ArrayQueue queue=new ArrayQueue(3);
        char key=' ';//接收用戶輸入
        Scanner sc=new Scanner(System.in);
        boolean loop = true ;
        while(loop){
            System.out.println("");
        }

    }

}
