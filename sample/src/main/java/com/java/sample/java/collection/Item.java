package com.java.sample.java.collection;

public class Item {
    private Long id;
    private String name;

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "{id=" + id + "," + "name=" + name +  "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println("程式開始執行");

        try {
            System.out.println("進入 try 區塊");
            // 故意製造一個異常：除以零
            int result = 10 / 0;
            System.out.println("這一行不會被執行，因為上一行會拋出異常");
        } catch (ArithmeticException e) {
            System.out.println("捕獲到異常: " + e.getMessage());
        }

        // 即使發生異常，這行之後的代碼仍然會被執行
        System.out.println("異常被處理後，程式繼續執行");

        // 再展示一個例子
        System.out.println("\n第二個例子:");
        try {
            System.out.println("再次進入 try 區塊");
            String str = null;
            // 故意製造另一個異常：空指針異常
            System.out.println(str.length());
            System.out.println("這一行也不會被執行");
        } catch (NullPointerException e) {
            System.out.println("捕獲到空指針異常: " + e.getMessage());
        } finally {
            System.out.println("finally 區塊總是會被執行");
        }

        System.out.println("程式正常結束");
    }

}

