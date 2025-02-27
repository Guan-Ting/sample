package com.java.sample.java.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class arrayListTest1 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        list.add("王小明");
        list.add("陳小春");
        for (String s : list) {
            System.out.println(s);
        }

        list.forEach(new Consumer<Object>() {
            @Override
            public void accept (Object s) {
                System.out.println(s);
            }
        });

        list.forEach ((s) -> System.out.println(s));
        list.forEach(System.out::println);

    }
}
