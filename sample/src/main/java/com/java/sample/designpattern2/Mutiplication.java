package com.java.sample.designpattern2;

/**
 * ClassName: Mutiplication
 * Package: com.java.sample.designpattern2
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */
public class Mutiplication implements Strategy {
    @Override
    public float caculate(int a, int b) {
        return a * b;
    }
}
