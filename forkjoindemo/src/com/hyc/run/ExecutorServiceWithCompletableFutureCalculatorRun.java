package com.hyc.run;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

import com.hyc.api.Calculator;
import com.hyc.api.ExecutorServiceWithCompletableFutureCalculator;

public class ExecutorServiceWithCompletableFutureCalculatorRun {

	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(1, 100000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceWithCompletableFutureCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("��ʱ��" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("���Ϊ��" + result);
	}
}
