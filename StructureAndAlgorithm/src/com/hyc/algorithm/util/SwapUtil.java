package com.hyc.algorithm.util;

public class SwapUtil {
	
	/**
	 * ���������е�i��Ԫ�غ͵�j��Ԫ��
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
