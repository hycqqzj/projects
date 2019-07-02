package com.hyc.run;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableRun {
	private static class SumTask implements Supplier<Long> {
        @Override
        public Long get() {
            return 100L;
        }
    }

	public static void main(String[] args) throws Exception {
		Executor executor = Executors.newFixedThreadPool(10);
		CompletableFuture<Long> future = CompletableFuture.supplyAsync(new SumTask(), executor);

		// Block and get the result of the Future
		Long result = future.get();
		System.out.println(result);
	}
}
