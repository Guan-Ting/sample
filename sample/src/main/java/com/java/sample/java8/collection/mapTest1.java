package com.java.sample.java8.collection;

import java.util.HashMap;
import java.util.Map;

public class mapTest1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("Robert C. Martin", "Clean Code");
        map.put("Joshua Bloch", "Effective Java");
        map.put("J.K.Rowling","Harry Potter");

        for (String key : map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key));
        }

        for (Map.Entry<String, String> book: map.entrySet()) {
            System.out.println("key: " + book.getKey() + " value: " + book.getValue());
        }
    }
}
