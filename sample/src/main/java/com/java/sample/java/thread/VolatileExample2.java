package com.java.sample.java.thread;


public class VolatileExample2 {

    //volatile 可確保Visibility
    //此變數會改為不使用各執行緒的working memory，永遠從主記憶體做存取與讀寫。


    static volatile  int num;

    public static void main (String[] args) {

        Thread readerThread = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != num) {
                    temp = num;
                    System.out.println("reader: value of num = " + num);
                }
            }
        });

        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                num++;
                System.out.println("writer: changed value to = " + num);

                // 進入睡眠，以讓readerThread有足夠時間讀到int num的改變(因為num++非具原子性的操作，readerThread仍有一定機會讀到錯誤的值)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //離開程式，否則readerThread會一直等待變數int num的值改變
            System.exit(0);
        });

        readerThread.start();
        writerThread.start();
    }
}
