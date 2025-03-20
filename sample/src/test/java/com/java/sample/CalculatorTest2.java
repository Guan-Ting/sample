package com.java.sample;

import com.java.sample.designpattern2.Caculator2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * ClassName: CalculatorTest2
 * Package: com.java.sample
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */
public class CalculatorTest2 {

    Caculator2 caculator2 = new Caculator2();

   @Test
   public void test_normal_case(){
     assertEquals(2, caculator2.divide(6,3));
   }

   @Test
   public void test_minus_case(){
       assertEquals(-1,caculator2.divide(6,-6));
   }

   @Test
    public void boundary_case(){
       assertEquals(1,caculator2.divide(Integer.MAX_VALUE,Integer.MAX_VALUE));
   }

   @Test
    public void exception_case(){
       assertThrows(IllegalArgumentException.class, () -> caculator2.divide(1,0));
       assertThrows(NullPointerException.class, () -> caculator2.divide(null,0));
    }
}
