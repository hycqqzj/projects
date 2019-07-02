package com.hyc.api;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator implements Calculator {
	private ForkJoinPool pool;

	public ForkJoinCalculator() {
		// Ҳ����ʹ�ù��õ��̳߳� ForkJoinPool.commonPool()��
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

	// ִ������RecursiveTask���з���ֵ RecursiveAction���޷���ֵ
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

		// �˷���ΪForkJoin�ĺ��ķ�������������в�� ��ֵĺû�������Ч�ʵĸߵ�
		@Override
		protected Long compute() {
			// ����Ҫ��������ָ���С��6ʱ��ֱ�Ӳ���for loop��ʽ������
			if (to - from <= 100) {
				long total = 0;
				for (int i = from; i <= to; i++) {
					total += numbers[i];
				}
				System.out.println(String.format("currentThread:%s,total:%s,from:%s,to:%s",
						Thread.currentThread().getName(), total, from, to));
				return total;
			} else { // ���򣬰�����һ��Ϊ�����ݹ���(ע��˴��еݹ�)���ײ�ֳɶ��ٷ� ��Ҫ���ݾ����������
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
