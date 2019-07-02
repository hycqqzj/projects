package com.hyc.run;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

import com.hyc.api.ForLoopCalculator;

public class ForLoopRun {

	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
		
		Instant start = Instant.now();
		ForLoopCalculator cal = new ForLoopCalculator();
		long result = cal.sumUp(numbers);
		Instant end = Instant.now();
        System.out.println("��ʱ��" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("���Ϊ��" + result); 
	}

}
