package com.java.sample.java.concurrency;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownThreadExample  implements  Runnable{

    private CountDownLatch countDownLatch;
    public CountDownThreadExample(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        try {
            String currentThread = Thread.currentThread().getName();
            System.out.println("current Thread" + currentThread);
        }catch (Exception e ){
            throw new RuntimeException();
        }finally{
            //呼叫countDown數值會減1，減到 0 時就可以執行主程式 await 方法後面的程式
            this.countDownLatch.countDown();
            System.out.println("countDownLatch:"+countDownLatch.getCount());
        }
    }
    /*
       問題:

       將將迴圈設為i=2
       會因為CountDownLatch沒用完無法觸發await()

       將迴圈設為i=4
       會是四個Thread順序不一 印出

       例如:
       current Threadpool-1-thread-1
       current Threadpool-1-thread-3
       current Threadpool-1-thread-2
       current Threadpool-1-thread-4
       thread running finish

       將迴圈設為i=6
       為甚麼同個thread會在前四次中跑超過一次?
       例如:

       current Threadpool-1-thread-2
       current Threadpool-1-thread-3
       current Threadpool-1-thread-2
       current Threadpool-1-thread-3
       current Threadpool-1-thread-1
       thread running finish
       current Threadpool-1-thread-4

       current Threadpool-1-thread-2
       current Threadpool-1-thread-3
       current Threadpool-1-thread-1
       current Threadpool-1-thread-2
       current Threadpool-1-thread-3
       thread running finish
       current Threadpool-1-thread-4



    */
    public static void main(String args[]) {
        //newFixedThreadPool:執行緒 Pool 只會有固定數量的執行緒再執行，如果啟動的數量超過 pool 的數量那執行緒就會被放入 Queue 等待被執行。
        ExecutorService es = Executors.newFixedThreadPool(4);
        try {
            CountDownLatch cdt = new CountDownLatch(4);

            for (int i = 0; i < 6; i++) {
                es.execute(new CountDownThreadExample(cdt));
                //System.out.println("CountDownLatch amount:"+cdt.getCount());
            }

            //呼叫countDown數值會減1，減到 0 時就可以執行主程式 await 方法後面的程式
            cdt.await();
            System.out.println("thread running finish");
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            es.shutdown();
        }
    }

}
