package com.java.sample.java.Stream;


public class TestStreamMethod {
    public static void main(String[] args) {
        //Before java8 collection
        //List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        //for (Integer s :list){
        //    System.out.println(s);
        //}
        //Stream.of()
        //Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9);
        //stream.forEach(p-> System.out.println(p));

        //Stream.of(array)
        //Stream<Integer> stream=Stream.of(new Integer[]{1,2,3,4,5,6,7,8,9});
        //stream.forEach(p -> System.out.println(p));


        //List.stream()
//        List<Integer> list=new ArrayList<Integer>();
//
//        for(int i =1; i<10;i++){
//            list.add(i);
//        }
//        Stream<Integer>stream =list.stream();
//        stream.forEach(p -> System.out.println(p));

         //Stream<Integer> randomNumbers = Stream.generate(() -> (new Random().nextInt(100)));
        //randomNumbers.limit(20).forEach( p -> System.out.println(p));
        //randomNumbers.limit(20).forEach(System.out::println);

//        IntStream stream = "S".chars();
//        stream.forEach(p -> System.out.println(p));
        // 特殊符號必须得加\\
//        Stream<String> stream =Stream.of("A$B$C".split("\\$"));
//        stream.forEach(System.out::println);


        //stream.collect
//        List<Integer> list =new ArrayList<Integer>();
//        for(int i =0 ;i< 10 ;i++){
//            list.add(i);
//        }

        //Stream<Integer> stream =list.stream();
        //先filter 再collect 出一個 collection
//        List<Integer> evenNumbersList =stream.filter(i -> i%2==0).collect(Collectors.toList());
//        System.out.println(evenNumbersList);

//        List<Integer> list =new ArrayList<Integer>();
//        for (int i = 0; i<10 ; i++){
//            list.add(i);
//        }
//        Stream<Integer> stream =list.stream();
//        Integer[] evenNumbersArr=stream.filter(i ->i%2 ==0 ).toArray(Integer[]:: new );
//        System.out.println(evenNumbersArr);



    }
}
