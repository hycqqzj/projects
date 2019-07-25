package com.hyc.sort;

import com.hyc.util.SwapUtil;

public class InsertSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 16, 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 插入排序： 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的 
	 * 如此反复循环，直到全部排好顺序
	 * 平均时间复杂度：O(n2)
	 * 
	 * @param arr
	 */
	private static void sort(int[] arr) {
		for (int i = 1; i <= arr.length - 1; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					SwapUtil.swap(arr, j - 1, j);
				} else {
					// 已经是有序的不需要遍历
					break;
				}
			}
		}

	}
}
