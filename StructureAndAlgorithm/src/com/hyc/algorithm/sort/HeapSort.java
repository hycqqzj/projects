package com.hyc.algorithm.sort;

import java.util.Arrays;

import com.hyc.util.SwapUtil;

public class HeapSort {
	public static void main(String[] args) {
		int[] arr = new int[] {0, 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr, arr.length - 1);
		Arrays.stream(arr).forEach(x -> System.out.print(x + ","));
	}

	// n 表示数据的个数，数组 a 中的数据从下标 1 到 n 的位置。
	public static void sort(int[] a, int n) {
		buildHeap(a, n);
		int k = n;
		while (k > 1) {
			SwapUtil.swap(a, 1, k);
			--k;
			heapify(a, k, 1);
		}
	}

	private static void buildHeap(int[] a, int n) {
		for (int i = n / 2; i >= 1; --i) {
			heapify(a, n, i);
		}
	}

	private static void heapify(int[] a, int n, int i) {
		while (true) {
			int maxPos = i;
			if (i * 2 <= n && a[i] < a[i * 2])
				maxPos = i * 2;
			if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1])
				maxPos = i * 2 + 1;
			if (maxPos == i)
				break;
			SwapUtil.swap(a, i, maxPos);
			i = maxPos;
		}
	}

}
