package com.java.sample.java8.thread.atomic.someexample;


import java.util.concurrent.TimeUnit;

public class BankCardTest {
    private static volatile BankCard bankCard =new BankCard("cxuan",100);

    public static void main(String[] args) {
        for(int i =0; i <10; i++){
            new Thread( ()->{
                //如果要解決原子性問題 可以用synchronized 鎖住整個區塊 以確認交易正常
                //synchronized (BankCardTest.class) {
                    final BankCard card = bankCard;
                    BankCard newCard = new BankCard(card.getAccountName(), card.getMoney() + 100);
                    System.out.println("newCard:" + newCard);
                    //把新的帳戶的引用給原帳戶
                    bankCard = newCard;
                    try {
                        TimeUnit.MICROSECONDS.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                //}
            }).start();
        }
    }


}
