package com.java.sample.Algo;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaxStack {
    // 主堆疊用於存儲所有元素
    private Stack<Integer> stack = new Stack<>();
    // 輔助堆疊用於存儲每一層對應的最大值
    private Stack<Integer> maxStack = new Stack<>();

    /**
     * 將元素壓入堆疊
     * @param x 欲壓入的元素
     */
    public void push(int x) {
        stack.push(x);
        // 若 maxStack 為空 或 x 大於等於當前的最大值，則將 x 同時壓入 maxStack
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }

    /**
     * 彈出並返回堆疊頂端的元素
     * @return 堆疊頂部的元素
     */
    public int pop() {

        if (stack.isEmpty()){
            throw new EmptyStackException();
        }
        // 若彈出的元素同樣是當前最大值，則同步彈出 maxStack 的元素
        if (stack.peek().equals(maxStack.peek())) {
            maxStack.pop();
        }
        return stack.pop();
    }

    /**
     * 返回堆疊頂端的元素但不移除
     * @return 堆疊頂部的元素
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.peek();
    }

    /**
     * 獲取堆疊當前的最大值
     * @return 堆疊中的最大值
     */
    public int getMax() {
        if (maxStack.isEmpty()) {
            throw new EmptyStackException();
        }

        // 直接訪問 maxStack 的頂部元素即可
        return maxStack.peek();
    }

    // 測試方法
    @Test
    void testMaxStackOperations() {
        MaxStack maxStack = new MaxStack();

        // 測試 push 和 getMax
        maxStack.push(5);
        assertEquals(5, maxStack.getMax(), "最大值應為 5");

        maxStack.push(1);
        assertEquals(5, maxStack.getMax(), "最大值應仍為 5");

        maxStack.push(5);
        assertEquals(5, maxStack.getMax(), "最大值應仍為 5");

        // 測試 pop
        assertEquals(5, maxStack.pop(), "彈出值應為 5");
        assertEquals(5, maxStack.getMax(), "最大值應仍為 5");

        assertEquals(1, maxStack.pop(), "彈出值應為 1");
        assertEquals(5, maxStack.getMax(), "最大值應仍為 5");

        assertEquals(5, maxStack.pop(), "彈出值應為 5");

        // 測試空堆疊狀態
        assertThrows(EmptyStackException.class, maxStack::getMax, "空堆疊應拋出例外");

        assertThrows(EmptyStackException.class,()-> maxStack.getMax());
    }

}
