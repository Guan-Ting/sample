package com.java.sample.java.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;



/*
    Trigger 自己阻塞某些時間 在它被喚醒的時候
    它可以把這個task直接丟到線程池中

    線程池本身只執行這個task
 */

public class ScheduleService {


    Trigger trigger =new Trigger();
    ExecutorService executorService =Executors.newFixedThreadPool(6);

    void schedule (Runnable task,long delay){
        Job job = new Job();
        job.setTask(task);
        job.setStartTime(System.currentTimeMillis()+delay);
        job.setDelay(delay);
        job.setRepeat(true);
        job.setMaxExecutionCount(3);
        trigger.queue.offer(job);
        trigger.wakeup();
    }
    //等待合適的時間 把對應的任務丟到線程池中
    class Trigger {

       PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();
        Thread thread = new Thread(()->{
            while(true){

                //為了解決虛假喚醒 要用while
                while (queue.isEmpty()){
                    LockSupport.park();
                }

                Job latelyJob =queue.peek();
                if (latelyJob.getStartTime() < System.currentTimeMillis()){
                    latelyJob=queue.poll();
                    executorService.execute(latelyJob.getTask());

                    //執行次數+1
                    latelyJob.setExecutionCount(latelyJob.getExecutionCount()+1);
                    System.out.println("當前ExecutionCount數量"+latelyJob.getExecutionCount());


                    if (latelyJob.isRepeat() && latelyJob.getExecutionCount() < latelyJob.getMaxExecutionCount()){
                        Job nextJob = new Job();
                        nextJob.setTask(latelyJob.getTask());
                        nextJob.setDelay(latelyJob.getDelay());
                        nextJob.setStartTime(System.currentTimeMillis()+latelyJob.getDelay());
                        nextJob.setRepeat(true);
                        nextJob.setMaxExecutionCount(latelyJob.getMaxExecutionCount());
                        nextJob.setExecutionCount(latelyJob.getExecutionCount());
                        queue.offer(nextJob);
                    }else{
                        System.out.println("任務已經執行完畢，不再進入queue");
                    }


                }else{
                    LockSupport.parkUntil(latelyJob.getStartTime());
                }

            }
        });

        {
            thread.start();
            System.out.println("觸發器啟動");
        }

        void wakeup(){
            LockSupport.unpark(thread);
        }

    }

}
