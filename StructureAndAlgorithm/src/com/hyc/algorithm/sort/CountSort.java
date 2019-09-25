package com.hyc.algorithm.sort;

/**
 * 计数排序
 */
public class CountSort {

	public static void main(String[] args) {
		int[] arr = { 2, 5, 3, 0, 2, 3, 0, 3 };
		countingSort(arr, 8);
		for (int i = 0; i <= arr.length - 1; i++) {
			System.out.println(arr[i]);
		}
	}

	/**
	 * 计数排序
	 * 
	 * @param a 数组
	 * @param n 数组大小
	 */
	public static void countingSort(int[] a, int n) {
		if (n <= 1) {
			return;
		}

		// 查找数组中数据的范围
		int max = a[0];
		for (int i = 1; i < n; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}

		// 申请一个计数数组 c，下标大小 [0,max]
		int[] c = new int[max + 1];
		for (int i = 0; i <= max; i++) {
			c[i] = 0;
		}

		// 计算每个元素的个数，放入 c 中
		for (int i = 0; i < n; i++) {
			c[a[i]]++;
		}

		// 依次累加
		for (int i = 1; i <= max; i++) {
			c[i] = c[i - 1] + c[i];
		}

		// 临时数组 r，存储排序之后的结果
		int[] r = new int[n];
		
		// 计算排序的关键步骤，有点难理解
		for (int i = n - 1; i >= 0; i--) {
			int index = c[a[i]] - 1;
			r[index] = a[i];
			c[a[i]]--;
		}

		// 将结果拷贝给 a 数组
		for (int i = 0; i < n; i++) {
			a[i] = r[i];
		}
	}

}
