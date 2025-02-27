package com.java.sample.java.lambda;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestFunctionalInterface {
    public static void main(String[] args) {
        List<Integer> primes = Arrays.asList(new Integer[]{2,3,5,7});

        //
        //primes.forEach(element -> { factor++; });

        List<Integer> ints=primes.stream().collect(Collectors.toList());
        List<Integer> ints2=primes.stream().parallel().collect(Collectors.toList());
        ints.forEach( (s)-> System.out.println("stream:"+s));
        ints2.forEach( (s)-> System.out.println("parallel:"+s));


    }

}
