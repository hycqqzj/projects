package com.hyc.api;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator implements Calculator {
	private ForkJoinPool pool;

	public ForkJoinCalculator() {
		// 也可以使用公用的线程池 ForkJoinPool.commonPool()：
		// pool = ForkJoinPool.commonPool()
		int parallelism = Runtime.getRuntime().availableProcessors();
		pool = new ForkJoinPool(parallelism, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);
	}

	@Override
	public long sumUp(long[] numbers) {
		SumTask task = new SumTask(numbers, 0, numbers.length - 1);
		Long result = pool.invoke(task);
		pool.shutdown();
		return result;
	}

	// 执行任务RecursiveTask：有返回值 RecursiveAction：无返回值
	private static class SumTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = 1L;

		private long[] numbers;
		private int from;
		private int to;

		public SumTask(long[] numbers, int from, int to) {
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}

		// 此方法为ForkJoin的核心方法：对任务进行拆分 拆分的好坏决定了效率的高低
		@Override
		protected Long compute() {
			// 当需要计算的数字个数小于6时，直接采用for loop方式计算结果
			if (to - from <= 100) {
				long total = 0;
				for (int i = from; i <= to; i++) {
					total += numbers[i];
				}
				System.out.println(String.format("currentThread:%s,total:%s,from:%s,to:%s",
						Thread.currentThread().getName(), total, from, to));
				return total;
			} else { // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
				int middle = (from + to) / 2;
				SumTask taskLeft = new SumTask(numbers, from, middle);
				SumTask taskRight = new SumTask(numbers, middle + 1, to);
				taskLeft.fork();
				taskRight.fork();
				return taskLeft.join() + taskRight.join();
			}
		}
	}
}
