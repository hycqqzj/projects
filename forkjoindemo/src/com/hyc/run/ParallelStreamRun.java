package com.hyc.run;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * JDK8的ParallelStream
 */
public class ParallelStreamRun {
	
	public static void main(String[] args) {
		Instant start = Instant.now();
		
        long result = LongStream.rangeClosed(0, 10000000).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
	}
}
