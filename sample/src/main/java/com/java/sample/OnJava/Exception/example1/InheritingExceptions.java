package com.java.sample.OnJava.Exception.example1;

/**
 * ClassName: InheritingExceptions
 * Package: com.java.sample.OnJava.exception
 * Description:
 *
 * @Author: Howard
 * @Create: 2023/6/14
 */
public class InheritingExceptions {
    public void f() throws SimpleException{
        System.out.println("throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed=new InheritingExceptions();
        try {
            sed.f();
        }catch (SimpleException e){
            System.out.println("Caught it");
        }
    }
}
