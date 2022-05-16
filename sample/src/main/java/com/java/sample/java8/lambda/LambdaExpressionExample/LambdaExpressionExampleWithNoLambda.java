package com.java.sample.java8.lambda.LambdaExpressionExample;

interface Drawable {
    public void draw();
}
public class LambdaExpressionExampleWithNoLambda {
    public static void main(String[] args) {
        int width=10;

        //如果沒有用lambda Drawable implementation 會用匿名函式
        Drawable d=new Drawable(){
            @Override
            public void draw() {
                System.out.println("Drawing "+width);
            }
        };
        d.draw();
    }
}
