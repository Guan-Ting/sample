//package com.java.sample.java8.lambda;
//
//import java.util.Comparator;
//
//public class TestSortingNoLambda {
//    public static void main(String[] args) {
//        List<Developer> listDevs = getDevelopers();
//        System.out.println("Before Sort");
//        for (Developer developer :listDevs){
//            System.out.println(developer);
//        }
//        //sort by age
//        Collections.sort(listDevs,new Comparator<Developer>(){
//            @Override
//            public int compare(Developer o1 ,Developer o2){
//                return o1.getAge() - o2.getAge();
//            }
//
//        });
//        System.out.println("After sort");
//        for(Developer developer :listDevs){
//            System.out.println(developer);
//        }
//
//    }
//}
