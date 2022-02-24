package com.java.sample.java8.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestFilter {
    public static void main(String[] args) {
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        //將把A起首的字篩選出來
        memberNames.stream().filter(s -> s.startsWith("A")).forEach(p -> System.out.println(p));
        System.out.println("-----------");
        //將A起首的字全部轉成大寫
        memberNames.stream().filter(s ->s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);
        System.out.println("-----------");
        //將A起首的排列
        //the sorted() method only creates a sorted view of the stream without manipulating the ordering of the source Collection
        memberNames.stream().sorted().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("-----------");
        //查詢stream 裡面是否符合條件
        boolean matchedResult=memberNames.stream().anyMatch(s ->s.startsWith("A"));
        boolean matchedResult2=memberNames.stream().noneMatch(s ->s.startsWith("A"));


        System.out.println("matchedResult:"+matchedResult);
        System.out.println("matchedResult2:"+matchedResult2);

        long totalMatched =memberNames.stream().filter(s ->s.startsWith("A")).count();
        System.out.println("totalMatched:"+totalMatched);
        System.out.println("-----------");
        Optional<String> reduced =memberNames.stream().reduce((s1, s2) -> s1 +"#" +s2);
        reduced.ifPresent(System.out::println);






    }
}
