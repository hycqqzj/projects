package com.hyc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class ExecutorServiceWithCompletableFutureCalculator implements Calculator {
	private int parallism;
	private ExecutorService pool;

	public ExecutorServiceWithCompletableFutureCalculator() {
		parallism = Runtime.getRuntime().availableProcessors();
		pool = Executors.newFixedThreadPool(parallism);
	}

	// 处理计算任务的线程
	private static class SumTask implements Supplier<Long> {
		private long[] numbers;
		private int from;
		private int to;

		public SumTask(long[] numbers, int from, int to) {
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}

		@Override
		public Long get() {
			long total = 0;
			for (int i = from; i <= to; i++) {
				total += numbers[i];
			}
			return total;
		}
	}

	@Override
	public long sumUp(long[] numbers) {
		List<Future<Long>> futureList = new ArrayList<>();
		List<Long> results = new ArrayList<>();

		// 把任务分解为 n 份，交给 n 个线程处理 4核心 就等分成4份
		// 然后把每一份都扔个一个SumTask线程 进行处理
		int part = numbers.length / parallism;
		for (int i = 0; i < parallism; i++) {
			int from = i * part; // 开始位置
			int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1; // 结束位置

			futureList.add(CompletableFuture.supplyAsync(new SumTask(numbers, from, to), pool).whenComplete((s, e) -> results.add(s)));
		}
		
		CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
		pool.shutdown();

		long total = 0;
		for (Long re : results) {
			total += re;
		}
		return total;
	}
}
