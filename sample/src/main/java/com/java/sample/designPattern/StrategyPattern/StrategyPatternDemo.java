package com.java.sample.designPattern.StrategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrategyPatternDemo {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the first value");
        float value1=Float.parseFloat(br.readLine());
        System.out.println("Enter the second value");
        float value2 =Float.parseFloat(br.readLine());
        Context context =new Context(new Addition());
        System.out.println("Addition="+context.executeStrategy(value1,value2));
        context =new Context(new Subtraction());
        System.out.println("Subtraction = " + context.executeStrategy(value1, value2));
        context = new Context(new Mutiplication());
        System.out.println("Multiplication = " + context.executeStrategy(value1, value2));
    }

}


