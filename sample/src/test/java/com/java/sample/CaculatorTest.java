package com.java.sample;

import com.java.sample.designpattern2.Calculator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * ClassName: CaculatorTest
 * Package: com.java.sample
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */

@SpringBootTest
public class CaculatorTest {

   Calculator calculator = new Calculator();

   @Test
   public void testDivide_NormalCase() {
       // 測試一般情況// 0 / 5 = 0
       assertEquals(1, calculator.divide(5, 5));

       assertEquals(1,calculator.divide(5,5));// 5 / 5 = 1
   }

    @Test
    public void testDivide_NegativeNumbers() {
        // 測試負數輸入
        assertEquals(-2, calculator.divide(10, -5)); // 10 / -5 = -2
        assertEquals(-2, calculator.divide(-10, 5)); // -10 / 5 = -2
        assertEquals(2, calculator.divide(-10, -5)); // -10 / -5 = 2
        assertEquals(2,calculator.divide(-10,-5));
        assertEquals(0,calculator.divide(0,-10));
    }

    @Test
    public void testDivide_BoundaryCases() {
        // 測試邊界值
        assertEquals(0, calculator.divide(0, Integer.MAX_VALUE)); // 0 / x = 0
        assertEquals(1, calculator.divide(Integer.MAX_VALUE, Integer.MAX_VALUE)); // max / max = 1
        assertEquals(-1, calculator.divide(Integer.MIN_VALUE, Integer.MAX_VALUE)); // min / max = -1

        assertEquals(1,calculator.divide(Integer.MAX_VALUE,Integer.MIN_VALUE));
    }
    @Test
    public void testDivide_DivideByZero_shouldThrowException() {
        // 測試除以 0 的情況
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            calculator.divide(10, 0);
//        });

        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));

        assertEquals("除數不能為0", exception.getMessage());
    }





}
