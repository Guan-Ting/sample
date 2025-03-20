package com.java.sample.java8.concurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ClassName: CompletableFutureDemo
 * Package: com.java.sample.java8.concurrency
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/3/19
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("開始執行 CompletableFuture 批次處理示範...");

        // 模擬 onesignal_ids 列表
        List<String> mockIds = IntStream.rangeClosed(1, 35)
                .mapToObj(i -> "user_id_" + i)
                .collect(Collectors.toList());

        System.out.println("總共有 " + mockIds.size() + " 筆資料需要處理");

        // 創建結果容器
        List<String> results = new CopyOnWriteArrayList<>();

        // 設定批次大小和總處理數量
        int allSize = Math.min(mockIds.size(), 50); // 限制最多處理 50 筆
        int batchSize = 5; // 每批 5 筆

        System.out.println("設定每批處理 " + batchSize + " 筆，最多處理 " + allSize + " 筆");
        System.out.println("--------------------------------------------");

        // 批次處理
        for (int i = 0; i < allSize; i += batchSize) {
            System.out.println("\n開始處理第 " + (i / batchSize + 1) + " 批：");
            long startTime = System.currentTimeMillis();

            // 計算本批次的結束索引
            int end = Math.min(i + batchSize, allSize);
            List<String> batch = mockIds.subList(i, end);

            System.out.println("本批處理索引範圍: " + i + " 到 " + (end-1) + " (共 " + batch.size() + " 筆)");

            // 建立 CompletableFuture 列表
            List<CompletableFuture<String>> futures = batch.stream()
                    .map(id -> simulateApiCallAsync(id))
                    .collect(Collectors.toList());

            System.out.println("已創建 " + futures.size() + " 個非同步任務，等待所有任務完成...");

            // ※※※ 關鍵部分：等待所有 CompletableFuture 完成 ※※※
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

            System.out.println("本批所有任務已完成，收集結果中...");

            // 收集結果
            List<String> batchResults = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            results.addAll(batchResults);

            // 顯示本批次執行時間
            long executionTime = System.currentTimeMillis() - startTime;
            System.out.println("第 " + (i / batchSize + 1) + " 批處理完成，耗時: " + executionTime + " ms");

            // 批次之間稍微暫停，方便查看輸出
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n--------------------------------------------");
        System.out.println("所有批次處理完成，總共處理了 " + results.size() + " 筆資料");
        System.out.println("結果摘要：");

        // 顯示部分結果
        results.stream().limit(10).forEach(System.out::println);

        if (results.size() > 10) {
            System.out.println("... 還有 " + (results.size() - 10) + " 筆結果 ...");
        }
    }

    /**
     * 模擬非同步 API 調用
     * 替代原本的 fetchApiAsync 方法，僅輸出資訊到控制台
     */
    private static CompletableFuture<String> simulateApiCallAsync(String id) {
        return CompletableFuture.supplyAsync(() -> {
            // 模擬不同的處理時間 (200~1500 ms)
            int processingTime = ThreadLocalRandom.current().nextInt(200, 1500);

            try {
                System.out.println("[處理中] ID: " + id + " - 線程: " + Thread.currentThread().getName() +
                        " - 預計耗時: " + processingTime + "ms");
                TimeUnit.MILLISECONDS.sleep(processingTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "處理失敗: " + id + " - " + e.getMessage();
            }

            String result = "ID: " + id + " 處理成功 (耗時: " + processingTime + "ms)";
            System.out.println("[完成] " + result);
            return result;
        });
    }

}
