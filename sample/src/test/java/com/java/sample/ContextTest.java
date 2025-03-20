package com.java.sample;

import com.java.sample.designpattern2.Add;
import com.java.sample.designpattern2.Context;
import com.java.sample.designpattern2.Strategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ClassName: ContextTest
 * Package: com.java.sample
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */

@SpringBootTest
public class ContextTest {
    @Test
    void testCorrectScenario_AddStrategy() {
        // 使用 "Add" 策略
        Strategy addStrategy = new Add();
        Context context = new Context(addStrategy);

        // 正確情境測試：執行加法運算
        assertEquals(5, context.executeStrategy(2, 3), "加法策略應該返回 5");
        assertEquals(-1, context.executeStrategy(-3, 2), "加法策略應該返回 -1");
    }

    @Test
    void testCorrectScenario_MultiplicationStrategy() {
        // 測試 Multiplication 策略邏輯
        Strategy multiplicationStrategy = new Strategy() {
            @Override
            public float caculate(int a, int b) {
                return a * b;
            }
        };
        Context context = new Context(multiplicationStrategy);

        // 正確情境測試：執行乘法運算
        assertEquals(6, context.executeStrategy(2, 3), "乘法策略應該返回 6");
        assertEquals(0, context.executeStrategy(0, 10), "乘法策略應該返回 0");
    }

    /**
     * 邊界情況測試
     * 測試在極端值或邊界值的情況下程式是否正確運作。
     */
    @Test
    void testBoundaryCases_AddStrategy() {
        // 使用 "Add" 策略
        Strategy addStrategy = new Add();
        Context context = new Context(addStrategy);

        // 邊界情況：最大正整數
        assertEquals(Integer.MAX_VALUE, context.executeStrategy(Integer.MAX_VALUE, 0),
                "加法策略應返回 Integer.MAX_VALUE");
        // 邊界情況：最小負整數
        assertEquals(Integer.MIN_VALUE, context.executeStrategy(Integer.MIN_VALUE, 0),
                "加法策略應返回 Integer.MIN_VALUE");
    }

    @Test
    void testBoundaryCases_MultiplicationStrategy() {
        // 測試 Multiplication 策略邏輯
        Strategy multiplicationStrategy = new Strategy() {
            @Override
            public float caculate(int a, int b) {
                return a * b;
            }
        };
        Context context = new Context(multiplicationStrategy);

        // 邊界情況：極大值運算（可能超過整數範圍）
        assertEquals((float) Integer.MAX_VALUE * 2, context.executeStrategy(Integer.MAX_VALUE, 2),
                "乘法策略可能返回溢出值");
        // 邊界情況：乘以 0
        assertEquals(0, context.executeStrategy(Integer.MAX_VALUE, 0),
                "任何數字乘以 0 都應返回 0");
    }

    /**
     * 異常情境測試
     * 測試在不正常輸入或意外情況下是否正確處理並拋出預期異常。
     */
    @Test
    void testNullStrategy() {
        // 當傳入 null 作為策略時應該拋出空指針異常
        assertThrows(NullPointerException.class, () -> {
            Context context = new Context(null);
            context.executeStrategy(1, 2);
        }, "當策略為 null 時應拋出 NullPointerException");
    }

    @Test
    void testDivisionByZero() {
        // 模擬一個 Division 策略
        Strategy divisionStrategy = new Strategy() {
            @Override
            public float caculate(int a, int b) {
                if (b == 0) {
                    throw new ArithmeticException("除以 0 的異常");
                }
                return a / (float) b;
            }
        };
        Context context = new Context(divisionStrategy);

        // 測試是否正確拋出異常
        assertThrows(ArithmeticException.class, () -> {
            context.executeStrategy(10, 0);
        }, "當 b = 0 時應拋出 ArithmeticException");
    }

}
