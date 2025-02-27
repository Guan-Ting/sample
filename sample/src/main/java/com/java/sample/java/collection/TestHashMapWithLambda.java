package com.java.sample.java.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class TestHashMapWithLambda {
    public static void main(String [] args){
        Map<String,Double> map = new HashMap<>();
        map.put("台積電",2330D);
        map.put("鴻海", 23.17);
        map.put("大立光", 3008D);
        System.out.println("------Iterator------");
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.printf("key:%s,value:%s\n", key, map.get(key));
        }

        System.out.println("------forEach------");
        //forEach
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
        System.out.println("------lambda forEach------");


        //lambda forEach
        map.forEach(new BiConsumer<String,Double>() {
            @Override
            public void accept (String k,Double v) {
                System.out.println("key:" + k + ",value:" + v);
            }
        });

        map.forEach((k, v) -> System.out.println("key:" + k + ",value:" + v));

    }
}
