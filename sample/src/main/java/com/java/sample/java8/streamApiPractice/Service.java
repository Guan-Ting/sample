package com.java.sample.java8.streamApiPractice;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
//        list.stream().map( (s) ->s*s).forEach( s ->{
//            System.out.println(s);
//        });
        list=list.stream().map( (s) ->s*s).collect(Collectors.toList());
        list.forEach((s) -> {
            System.out.println(s);
        });

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

//        transactions.stream()
//                .filter((t) -> t.getYear() == 2021)
//                .sorted(Comparator.comparingInt(Transaction::getValue))
//                .collect(Collectors.toList())
//                .forEach((s) -> System.out.println("Transaction trader:"+s.getTrader()));
        transactions.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted((t1,t2)-> Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
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
        //我
        transactions.stream()
                .map((s)->s.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(city -> { System.out.println(city); });
        //參考解答
            transactions.stream()
                    .map((t) -> t.getTrader().getCity())
                    .distinct()
                    .forEach(System.out::println);
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
        //我
        transactions.stream()
                .filter((c) ->("Cambridge").equals(c.getTrader().getCity()))
                .sorted((t1,t2)-> t1.getTrader().getName().compareTo(t2.getTrader().getName()))
                .map(Transaction::getTrader)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //參考解答
//        transactions.stream()
//                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
//                .map(Transaction::getTrader)
//                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
//                .distinct()
//                .forEach(System.out::println);

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
        //我
        transactions.stream()
               .map(transaction -> transaction.getTrader().getName())
                .sorted( (name1,name2)-> name1.compareTo(name2))
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //參考
        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
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
        //我
        Boolean Boolean=transactions.stream()
                .anyMatch( transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("Boolean:"+Boolean);


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
        //我
        Optional<Integer>x =transactions.stream()
                .filter(transaction ->  transaction.getTrader().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .reduce(Integer::sum);
        System.out.println(x);


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
        //我
        Optional<Transaction> max = Optional.of(transactions.stream()
                .max(Comparator.comparing(transaction -> transaction.getValue()))
                .get());
        if(max.isPresent()){
            System.out.println(max.get().getValue());
        }
        //參考
        Optional<Integer> max1=transactions.stream()
                .map((t) -> t.getValue())
                .max(Integer::compare);
        System.out.println(max1.get());

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
        //我
        Optional<Transaction> op=transactions.stream()
                .min((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()));
        System.out.println(op);
    }

}
