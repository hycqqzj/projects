package com.hyc.api;

/**
 * 单线程for循环实现
 * 
 * @author Administrator
 */
public class ForLoopCalculator implements Calculator {
	@Override
	public long sumUp(long[] numbers) {
		long total = 0;
		
		for(long i : numbers) {
			total += i;
		}
		
		return total;
	}
}
