package com.java.sample.java8.Generic;


import java.util.LinkedList;
import java.util.List;

public class Generic03 {
    public <T> void test0(List<? extends  T> dest, List<? extends  T> src){

    }

    public static void main(String[] args) {
       List<String> a1= new LinkedList<>();
       List<Number> a2 =new  LinkedList<>();
       new Generic03().test0(a1,a2);
    }

}
