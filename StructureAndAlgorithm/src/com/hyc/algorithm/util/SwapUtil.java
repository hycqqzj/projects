package com.hyc.algorithm.util;

public class SwapUtil {
	
	/**
	 * 交换数组中第i个元素和第j个元素
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
