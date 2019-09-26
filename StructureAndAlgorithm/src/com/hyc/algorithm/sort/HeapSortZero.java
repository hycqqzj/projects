package com.hyc.algorithm.sort;

import java.util.Arrays;

import com.hyc.util.SwapUtil;

public class HeapSortZero {

	public static void main(String[] args) {
		int[] arr = new int[] { 16, 15, 17, 8, 10, 9, 25, 21, 24, 22, 28 };
		sort(arr, arr.length - 1);
		Arrays.stream(arr).forEach(x -> System.out.print(x + ","));
	}

	private static void sort(int[] a, int n) {
		buildHeap(a, n);
		int k = n;
		while (k >= 0) {
			SwapUtil.swap(a, 0, k);
			k--;
			heapifyTopToBottom(a, k, 0);
		}

	}

	private static void buildHeap(int[] a, int n) {
		for (int i = n / 2 + 1; i >= 0; i--) {
			heapifyTopToBottom(a, n, i);
		}
	}

	private static void heapifyTopToBottom(int[] a, int n, int i) {
		while (true) {
			int maxPos = i;
			if (2 * i + 1 <= n && a[2 * i + 1] > a[i]) {
				maxPos = 2 * i + 1;
			}
			if (2 * i + 2 <= n && a[2 * i + 2] > a[i]) {
				maxPos = 2 * i + 2;
			}
			if (maxPos == i) {
				break;
			}
			SwapUtil.swap(a, maxPos, i);
			i = maxPos;
		}
	}

}
