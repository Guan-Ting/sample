package com.java.sample.java8.lambda.LambdaExpressionExample;

interface Drawable1 {
    public void draw();
}
public class LambdaExpressionExampleWithLambda {
    public static void main(String[] args) {
        int width =10;

        Drawable1 d=()->{
            System.out.println("Drawing"+width);
        };
        d.draw();

    }
}
