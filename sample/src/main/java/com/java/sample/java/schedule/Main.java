package com.java.sample.java.schedule;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss SSS");

        ScheduleService scheduleService = new ScheduleService();
        scheduleService.schedule(()->{
            System.out.println(LocalDateTime.now().format(formatter)+"這是100毫秒一次的");
        },100);

        Thread.sleep(1000);
        System.out.println("添加一個每200毫秒打印一個2的定時任務");
        scheduleService.schedule(()->{
            System.out.println(LocalDateTime.now().format(formatter)+"這是200毫秒一次的");
        },200);


    }
}
