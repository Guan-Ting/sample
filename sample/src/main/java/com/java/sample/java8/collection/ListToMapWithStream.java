package com.java.sample.java8.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;


public class ListToMapWithStream {

    public static void main(String[] args) {
        List itemList =Arrays.asList(
                new Item(1L, "Stone"),
                new Item(2L, "Grass"),
                new Item(3L, "Dirt")

        );

        Map<Long, String> itemMap = (Map<Long, String >) itemList .stream()
                .collect(Collectors.toMap(Item::getId, Item::getName));

        itemMap.forEach((k, v) -> {
            System.out.println("key:" + k + ", value:" + v);

        });

    }



}
