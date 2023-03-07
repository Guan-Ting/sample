package com.java.sample.entity;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {
    //https://juejin.cn/post/7133466442871078943
    //https://blog.51cto.com/u_15961549/6054057

    @Test
    //给定一个数字列表，返回一个由每个数的平方构成的列表
    public void test(){
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300),new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Integer>list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.stream().map( (s) ->s*s).forEach( s ->{
            System.out.println(s);
        });


    }


}
