package com.java.sample.java.thread.atomic.someexample;



public class BankCard {
    private final String accountName;
    private final int money;

    //建構子

    public BankCard(String accountName, int money) {
        this.accountName = accountName;
        this.money = money;
    }

    public String getAccountName(){
        return accountName;
    }
    public int getMoney(){
        return money;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "accountName='" + accountName + '\'' +
                ", money=" + money +
                '}';
    }
}
