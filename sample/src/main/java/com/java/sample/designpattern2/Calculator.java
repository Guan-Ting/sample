package com.java.sample.designpattern2;

/**
 * ClassName: Calculator
 * Package: com.java.sample.designpattern2
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */
public class Calculator {

    public int divide(int a, int b){
        if (b ==0){
            throw new IllegalArgumentException("除數不能為0");
        }
        return a / b;
    }
}
