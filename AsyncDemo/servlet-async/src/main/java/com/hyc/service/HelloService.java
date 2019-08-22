package com.hyc.service;

/**
 * 耗时处理类模拟
 */
public class HelloService {
    public static int process() {
        int millis = 3000;
        try {
            // millis = ThreadLocalRandom.current().nextInt(2000);
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread + " sleep for " + millis + " milliseconds.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return millis;
    }
}
