package com.java.sample.java8.concurrent;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: LinkedBlockingQueueExample
 * Package: com.java.sample.java8.concurrent
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/3/7
 */
public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
        // 1. 初始化共享資源和統計數據
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1000); // 隊列最大容量 1000
        AtomicInteger successCount = new AtomicInteger(0); // 成功打印計數
        AtomicInteger failureCount = new AtomicInteger(0); // 失敗打印計數
        Random random = new Random(); // 用於模擬隨機失敗

        // 2. 記錄程序開始的時間
        long startTime = System.currentTimeMillis();

        // 3. 線程A：生產數據（1~10000）
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 1001; i++) {
                    queue.put(i); // 放入隊列（阻塞操作，若滿則等待）
                }
                queue.put(9999); // 插入結束標誌
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("線程A被中斷");
            }
        }, "ProducerThread");

        // 4. 線程B：消費數據並統計成功與失敗打印
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    Integer data = queue.take(); // 從隊列中取出數據（阻塞操作，若空則等待）
                    if (data == 9999) break; // 如果取到結束標誌，退出循環

                    // 模擬隨機失敗條件和成功打印
                    if (random.nextInt(10) < 8) { // 假設 80% 成功打印
                        System.out.println("打印數據: " + data);
                        successCount.incrementAndGet(); // 成功計數 +1
                    } else {
                        System.err.println("打印數據失敗: " + data);
                        failureCount.incrementAndGet(); // 失敗計數 +1
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("線程B被中斷");
            }
        }, "ConsumerThread");

        // 5. 啟動線程A和線程B
        producerThread.start();
        consumerThread.start();

        // 6. 等待線程A和線程B完成
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("主線程被中斷");
        }

        // 7. 計算總耗時並打印統計結果
        long endTime = System.currentTimeMillis();
        System.out.println("========== 統計結果 ==========");
        System.out.println("成功打印數量: " + successCount.get());
        System.out.println("失敗打印數量: " + failureCount.get());
        System.out.println("程序總耗時: " + (endTime - startTime) + " 毫秒");
    }

}

