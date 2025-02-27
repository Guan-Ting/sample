package com.java.sample.java.thread.atomic.someexample;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class BankTestAtomicReferenceExample {
    private static AtomicReference<BankCard> bankCard=new AtomicReference<BankCard>(new BankCard("xuan",100));

    public static void main(String[] args) {
        for(int i = 0; i <10; i ++){
            new Thread( () ->{
                //使用AtomicReferance 獲取
               final BankCard card =bankCard.get();
               BankCard newCard =new BankCard(card.getAccountName(),card.getMoney()+100);
               //使用CAS樂觀鎖進行非阻塞更新
                //bankCard.compareAndSet(card,newCard);
                System.out.println("newCard:"+newCard);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
