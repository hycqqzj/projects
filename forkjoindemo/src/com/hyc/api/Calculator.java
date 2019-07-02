package com.hyc.api;

/**
 * 耗时任务接口
 */
public interface Calculator {
	/**
     * 把传进来的所有numbers 做求和处理
     *
     * @param numbers
     * @return 总和
     */
    long sumUp(long[] numbers);
}
