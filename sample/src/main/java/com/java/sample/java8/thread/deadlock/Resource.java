package com.java.sample.java8.thread.deadlock;



public class Resource {
    private String name;
    private int resource;

    public Resource(String name, int resource) {
        this.name = name;
        this.resource = resource;
    }

    public String getName(){
        return name;
    }
    synchronized int doSome(){
        return ++resource;
    }

    synchronized void cooperate(Resource resource){
        resource.doSome();
        System.out.printf("%s 整合 %s 的資源%n",this.name,resource.getName());
    }

}
