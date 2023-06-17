package com.java.sample.OnJava.Exception.example2;


/**
 * ClassName: FullConstructors
 * Package: com.java.sample.OnJava.Exception.example2
 * Description:
 *
 * @Author: Howard
 * @Create: 2023/6/14
 */
public class FullConstructors {
    public static void f() throws  MyException{
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException {
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try{
            f();
        }catch(MyException e){
            e.printStackTrace(System.out);
        }

        try{
            g();
        } catch(MyException e){
            e.printStackTrace(System.out);
        }
    }
}
