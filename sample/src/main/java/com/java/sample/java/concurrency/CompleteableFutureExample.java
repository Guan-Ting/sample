//package com.java.sample.java8.concurrency;
//
//
//import com.sun.xml.internal.ws.util.CompletedFuture;
//
//import java.time.LocalTime;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//
//class UserRating{
//    String id;
//    String rate;
//}
//
//class UserInfo{
//    CompletedFuture<UserRating>getRating(){
//        return CompletableFuture.completedFuture(new UserRating());
//    }
//
//    CompletedFuture<UserRating>getUserRating(UserInfo info){
//        return info.getRating();
//    }
//}
//
//public class CompleteableFutureExample {
//    LocalTime now;
//
//    public static void printThread(String functionName){
//        System.out.println("Function : " + functionName + " is running on " + Thread.currentThread().toString());
//    }
//
//    public void basicTest() throws ExecutionException,InterruptedException{
//        //Basic Use
//        now =LocalTime.now();
//        CompletableFuture.supplyAsync()
//
//    }
//
//
//
//
//
//}
