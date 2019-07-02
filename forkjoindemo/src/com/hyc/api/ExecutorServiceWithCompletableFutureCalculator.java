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

	// �������������߳�
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

		// ������ֽ�Ϊ n �ݣ����� n ���̴߳��� 4���� �͵ȷֳ�4��
		// Ȼ���ÿһ�ݶ��Ӹ�һ��SumTask�߳� ���д���
		int part = numbers.length / parallism;
		for (int i = 0; i < parallism; i++) {
			int from = i * part; // ��ʼλ��
			int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1; // ����λ��

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
