package com.hyc.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = { 5, 9, 3, 1, 6, 2, 4, 8 };

		sort(arr, 0, arr.length - 1);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 归并排序：
	 * 将数组分成2组A，B，如果这2组组内的数据都是有序的，那么就可以很方便的将这2组数据进行排序
	 * 可以将A，B组各自再分成2组。依次类推，当分出来的小组只有1个数据时，可以认为这个小组组内已经达到了有序，然后再合并相邻的2个小组就可以了
	 * 平均时间复杂度：O(N*logN)
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	private static void sort(int[] arr, int begin, int end) {
		if(begin >= end) {
			return;
		}
		
		int mid = (begin + end) / 2;

		sort(arr, begin, mid);
		sort(arr, mid + 1, end);
		mergeArr(arr, begin, end);
	}

	private static void mergeArr(int[] arr, int begin, int end) {
		int middle = (begin + end) / 2;

		int i = 0;
		int j = 0;
		int k = begin;

		int[] left = Arrays.copyOfRange(arr, begin, middle + 1);
		int[] right = Arrays.copyOfRange(arr, middle + 1, end + 1);
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				// 此处使用<=可以让整个算法是稳定的排序算法
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}

		while (i < left.length) {
			arr[k++] = left[i++];
		}
		while (j < right.length) {
			arr[k++] = right[j++];
		}
	}

}
