package com.java.sample.Algo;

import org.junit.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ClassName: maxStack2
 * Package: com.java.sample.Algo
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/3/11
 */
public class maxStack2 {

    private Stack<Integer> stack = new Stack<Integer>();

    private Stack<Integer> maxStack = new Stack<Integer>();

    public void push(int x) {
        stack.push(x);

        if(maxStack.isEmpty() || x >= maxStack.peek()){
            maxStack.push(x);
        }
    }

    public int pop(){

        if (stack.peek().equals(maxStack.peek())){
            maxStack.pop();
        }

        return stack.pop();
    }

    public int getTop(){
        return stack.peek();
    }

    public int getMax(){
        return maxStack.peek();
    }


    @Test
    public void testmaxStack2(){
       MaxStack maxStack= new MaxStack();

       maxStack.push(5);
       assertEquals(5,maxStack.getMax(),"最大值應為5");
    }



}
