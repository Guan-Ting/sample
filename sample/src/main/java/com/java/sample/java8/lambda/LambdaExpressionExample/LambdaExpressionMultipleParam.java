package com.java.sample.java8.lambda.LambdaExpressionExample;

interface Addable{
    int add(int a,int b);
}
public class LambdaExpressionMultipleParam {
    public static void main(String[] args) {

        Addable ad0 =(a,b) -> {
            return a+b ;
        };
        System.out.println(ad0.add(100,20));

        Addable ad1 =(a,b) -> a+b;
        System.out.println(ad1.add(10,20));

        Addable ad2 =(int a,int b) -> a+b;
        System.out.println(ad2.add(10,200));

    }
}
