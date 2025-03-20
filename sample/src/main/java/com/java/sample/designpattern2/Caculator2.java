package com.java.sample.designpattern2;

/**
 *  ClassName: Caculator2
 *  Package: com.java.sample.designpattern2
 *  Description:
 *  @Author: Howard
 *  @Create: 2025/2/24
 *  
*/
public class Caculator2 {
    public int divide(Integer a , Integer b){

        if (a == null || b==null ){
            throw new NullPointerException("不得為null");
        }

        if (b == 0){
            throw new  IllegalArgumentException("除數不得為0");
        }
        return a / b;
    }
}
