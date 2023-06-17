package com.java.sample.OnJava.Exception.example3;

/**
 * ClassName: ExtraFeatures
 * Package: com.java.sample.OnJava.Exception.example3
 * Description:
 *
 * @Author: Howard
 * @Create: 2023/6/17
 */
public class ExtraFeatures {
    public static void f() throws MyException2{
        System.out.println("Throwing myException2 from f()");
        throw new   MyException2();
    }
    public static void g() throws MyException2{
        System.out.println("Throwing myException2 from g()");
        throw new   MyException2("originated in g()");
    }
    public static void h() throws MyException2{
        System.out.println("Throwing myException2 from h()");
        throw new  MyException2("originated in h()",47);
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException2 myException2) {
            myException2.printStackTrace(System.out);
        }
        try {
            g();
        } catch (MyException2 myException2) {
            myException2.printStackTrace(System.out);
        }
        try {
            h();
        } catch (MyException2 myException2) {
            myException2.printStackTrace(System.out);
            System.out.println("e.val"+ myException2.val());
        }

    }


}
