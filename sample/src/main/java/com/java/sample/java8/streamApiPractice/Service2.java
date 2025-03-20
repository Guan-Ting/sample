package com.java.sample.java8.streamApiPractice;


import com.java.sample.java.streamApiPractice.Trader;
import com.java.sample.java.streamApiPractice.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Service2 {
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


        List<Integer> list= new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list =list.stream().map(x -> x * x).collect(Collectors.toList());
        list.forEach(System.out::println);


    }
    @Test
    //找出2011年发生的所有交易， 并按交易额排序（从低到高）
    public void test2() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        transactions.stream()
                .filter(f -> f.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList()).forEach((s) -> System.out.println(s));


    }
    @Test
    //交易员都在哪些不同的城市工作过？
    public void test3() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }

    @Test
    //查找所有来自剑桥的交易员，并按姓名排序
    public void test4() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        transactions.stream()
                .filter( f ->(f.getTrader().getCity().equals("Cambridge")))
                .sorted( (t1 ,t2) -> t1.getTrader().getName().compareTo(t2.getTrader().getName()))
                .map(Transaction::getTrader)
                .collect(Collectors.toList())
                .forEach( s -> System.out.println(s));


    }
    @Test
    //返回所有交易员的姓名字符串，按字母顺序排序
    public void test5() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        transactions.stream()
                .map(s -> s.getTrader().getName())
                .sorted( (name1,name2)-> name1.compareTo(name2))
                .collect(Collectors.toList())
                .forEach( s -> System.out.println(s));

    }

    @Test
    //是否有在米兰工作的交易员
    public void test6() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );



    }
    @Test
    //打印生活在剑桥的交易员的所有交易额
    public void test7() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );



    }
    @Test
    //最高的交易额是多少
    public void test8() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


    }
    @Test
    //找到交易额最小的交易
    public void test9() {
        List<Transaction> transactions = null;

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }

}
