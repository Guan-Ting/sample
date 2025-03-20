package com.java.sample.java8.lambda;

import java.util.stream.Stream;

/**
 * ClassName: TestAnyMatchWithFalse
 * Package: com.java.sample.java8.lambda
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/3
 */
public class TestAnyMatchWithFalse {
    public static void main(String[] args) {
        // 定義變數
        boolean a = true;
        boolean b = true;
        boolean c = false; // 假設有一個條件為 false
        boolean d = true;
        boolean e = true;

        if(Stream.of(a,b,c,d,e).anyMatch(val -> !val )){
            System.out.println("至少有一條件為false");
        }else{
            System.out.println("全部為true");
        }

        // 使用 Stream.of 和 anyMatch 檢查是否有任一條件為 false
//        if (Stream.of(a, b, c, d, e).anyMatch(val -> !val)) {
//            System.out.println("至少有一個條件為 false！");
//        } else {
//            System.out.println("所有條件都為 true！");
//        }

    }
}
