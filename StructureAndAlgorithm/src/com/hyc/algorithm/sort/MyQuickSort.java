package com.hyc.algorithm.sort;

import com.hyc.util.SwapUtil;

public class MyQuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 15, 17, 8, 10, 9, 25, 21, 24, 6 };
		quickSort(arr, 0, arr.length - 1);

		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void quickSort(int[] a, int begin, int end) {
		if (begin >= end) {
			return;
		}
		int p = partition(a, begin, end);
		quickSort(a, begin, p - 1);
		quickSort(a, p + 1, end);
	}

	private static int partition(int[] a, int begin, int end) {
		int pivor = a[end];
		int i = begin;

		for (int j = i; j < end; j++) {
			if (a[j] < pivor) {
				if (i != j) {
					SwapUtil.swap(a, i, j);
				}
				i++;
			}
		}
		// 将基准数填入最后的坑
		SwapUtil.swap(a, i, end);
		return i;
	}

}
