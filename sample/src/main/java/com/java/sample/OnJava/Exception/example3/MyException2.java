package com.java.sample.OnJava.Exception.example3;

/**
 * ClassName: MyException2
 * Package: com.java.sample.OnJava.Exception.example3
 * Description:
 *
 * @Author: Howard
 * @Create: 2023/6/17
 */
public class MyException2 extends Exception {
    private int x;
    MyException2(){}
    MyException2(String msg){super(msg);}
    MyException2(String msg ,int x){
         super(msg);
         this.x=x;
    }
    public int val(){
        return  x;
    }


    @Override
    public String getMessage() {
        return "Detail message:"+x+""+super.getMessage();
    }

}
