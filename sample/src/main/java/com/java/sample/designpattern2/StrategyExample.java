package com.java.sample.designpattern2;

/**
 * ClassName: StrategyExample
 * Package: com.java.sample.designpattern2
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/2/24
 */
public class StrategyExample {

    public static void main(String[] args) {
       Context context= new Context(new Add());
       System.out.println(context.executeStrategy(1,2));


    }
}
