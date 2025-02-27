package com.java.sample.java.collection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestArrayList2 {
    public static void main(String[] args) {
       List<String> list=  new ArrayList<String>();
       list.add("小名");
       list.add("曉華");
       list.add("小子");


    }

    private void sortUsingJava7(List<String> names ){
        Collections.sort(names,new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return s1.compareTo(s2);
            }
        });
    }

    private void sortUsingJava8(List<String> names){
        Collections.sort(names,(s1,s2)-> s1.compareTo(s2));
    }
}

