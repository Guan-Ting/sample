package com.java.sample.designPattern.StrategyPattern;

public class Addition implements Strategy {
    public float calculation(float a, float b) {
        return a+b;
    }
}
