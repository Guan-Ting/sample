package com.java.sample.designPattern.StrategyPattern;

public class Subtraction implements Strategy{
    public float calculation(float a, float b) {
        return a-b;
    }
}
