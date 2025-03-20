package com.java.sample.designpattern2;

/**
 * ClassName: Context
 * Package: com.java.sample.designpattern2
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public float executeStrategy(int a,int b){
        return strategy.caculate(a,b);
    }
}
