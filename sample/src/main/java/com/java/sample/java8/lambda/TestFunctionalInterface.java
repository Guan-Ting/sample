package com.java.sample.java8.lambda;


public class TestFunctionalInterface {
    public static void main(String[] args) {
        Greeting greeting =(message)-> System.out.println(message);
        greeting.say("你好");

    }

}
